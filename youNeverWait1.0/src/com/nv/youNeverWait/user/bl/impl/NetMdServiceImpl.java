/**
 * NetMdServiceImpl.java
 *
 * Jan 3, 2013
 *
 * @author Asha Chandran 
 */

package com.nv.youNeverWait.user.bl.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;
import com.nv.framework.sendmsg.SendEmailMsgWorkerThread;
import com.nv.framework.sendmsg.SendMsgCallbackEnum;
import com.nv.framework.sendmsg.email.SendMailMsgObj;
import com.nv.framework.util.text.StringEncoder;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.NetmdBranchTbl;
import com.nv.youNeverWait.pl.entity.NetmdTbl;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.NetMdActivationResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDetail;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdDetail;
import com.nv.youNeverWait.rs.dto.NetMdListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDetail;
import com.nv.youNeverWait.rs.dto.NetMdViewResponseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalUserResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveNetmdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveNetmdListResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.user.bl.service.NetMdService;
import com.nv.youNeverWait.user.bl.validation.NetMdValidator;
import com.nv.youNeverWait.user.pl.dao.NetMdDao;
import com.nv.youNeverWait.user.pl.impl.NetMdBranchOwnerDetails;
import com.nv.youNeverWait.util.filter.core.Filter;
import com.nv.youNeverWait.util.filter.core.FilterFactory;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;
import com.nv.youNeverWait.util.filter.core.QueryBuilderFactory;

public class NetMdServiceImpl implements NetMdService {
	private NetMdDao netMdDao;
	private NetMdValidator validator;
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	private String netMdServerIpAddress;
	private String mailFrom;
	private SendEmailMsgWorkerThread mailThread;
	private static final Log log = LogFactory.getLog(NetMdServiceImpl.class);

	/**
	 * Retrieves all Netmd list after last synchronization time
	 * 
	 * @param syncTime
	 * @return RetrieveNetmdListResponseDTO
	 */
	@Override
	@Transactional
	public RetrieveNetmdListResponseDTO retrieveNetmdList(String syncTime, Date currentTime) {

		RetrieveNetmdListResponseDTO response = new RetrieveNetmdListResponseDTO();
		if (syncTime == null || syncTime.equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}
		response = netMdDao.retrieveNetmdList(syncTime, currentTime);
		return response;
	}


	/**
	 * Retrieves all Netmd branch list after last synchronization time
	 * 
	 * @param syncTime
	 * @return RetrieveNetmdBranchListResponseDTO
	 */
	@Override
	@Transactional
	public RetrieveNetmdBranchListResponseDTO retrieveNetmdBranchList(
			String syncTime, Date currentTime) {

		RetrieveNetmdBranchListResponseDTO response = new RetrieveNetmdBranchListResponseDTO();
		if (syncTime == null || syncTime.equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}
		response = netMdDao.retrieveNetmdBranchList(syncTime, currentTime);
		return response;
	}

	/**
	 * To retrieve a list of clinics which satisfy all filter conditions
	 * 
	 * @param filterDTO
	 * @return NetMdBranchListResponseDTO
	 */
	@Override
	@Transactional
	public NetMdBranchListResponseDTO getBranchList(FilterDTO filterDTO) {

		NetMdBranchListResponseDTO response = new NetMdBranchListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,return result with appropriate error code
		ErrorDTO error = validator.validateClinicFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for netmd branch from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.NETMD_BRANCH);
		if (queryBuilder == null) {
			return response;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

			// get filter from filter factory by setting expression name and
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<NetmdBranchTbl> q = queryBuilder.buildQuery(
				filterDTO.isAsc(), filterDTO.getFrom(), filterDTO.getCount());

		// get count
		Long count = queryBuilder.getCount();

		// execute query
		List<NetmdBranchTbl> clinics = queryBuilder.executeQuery(q);
		response = getNetMdList(clinics);
		response.setCount(count);
		response.setSuccess(true);
		return response;
	}

	/**
	 * To set response with details of branches
	 * 
	 * @param branches
	 * @return NetMdBranchListResponseDTO
	 */
	private NetMdBranchListResponseDTO getNetMdList(
			List<NetmdBranchTbl> branches) {
		NetMdBranchListResponseDTO response = new NetMdBranchListResponseDTO();
		if (branches == null) {
			return response;
		}
		List<NetMdBranchDetail> netMdBranchDetails = new ArrayList<NetMdBranchDetail>();
		for (NetmdBranchTbl netmdBranchTbl : branches) {
			netMdBranchDetails.add(new NetMdBranchDetail(netmdBranchTbl));
		}
		response.setNetmdBranch(netMdBranchDetails);
		return response;
	}

	/**
	 * Creates a netmd account
	 * 
	 * @param netMd
	 * @return ResponseDTO
	 */

	@Override
	public ResponseDTO createNetMd(NetMdDTO netMd) {
		ResponseDTO response = new ResponseDTO();
		validator.validateNetMdAccount(netMd);
		validator.validateUserNameAndPassword(netMd.getUserName(),
				netMd.getPassword());
		response = netMdDao.create(netMd);
		sendEmailToNetMdOwner(Constants.NETMD_REGISTER, netMd);
		return response;
	}

	/**
	 * To send netmd registration email to the owner's email id It will perform
	 * the following operations. 1.Take default email HTML template from Apache
	 * folder 2.Create email body 3.Send email to the lab owner.
	 * 
	 * @param subject
	 * @param netMd
	 * @return
	 */
	private void sendEmailToNetMdOwner(String subject, NetMdDTO netMd) {
		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netMdServerIpAddress
					+ "/youNeverWait/EmailFormat/NetMdRegistration.html");
			msgBody = createDefaultEmailBody(url, netMd);

			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody,
					netMd.getOwnerEmail(), mailFrom, 0, 0, null,
					SendMsgCallbackEnum.NETMD_REGISTRATION.getId(), null);
			mailThread.addSendMsgObj(obj);
			// /EmailSender.sendEmail(netMd.getOwnerEmail(), mailFrom, subject,
			// msgBody);
		} catch (IOException e) {
			log.error("Error while sending netmd registration email to the owner's email id ", e);
			e.printStackTrace();
			/*
			 * ServiceException se = new ServiceException(
			 * ErrorCodeEnum.EmailSendFailed); se.setDisplayErrMsg(true); throw
			 * se;
			 */
		}
	}

	/**
	 * To create email body
	 * 
	 * @param url
	 *            ,netMd
	 * 
	 * @return email message body
	 */
	private String createDefaultEmailBody(URL url, NetMdDTO netMd)
			throws IOException {

		StringBuffer msgBodyBfr = new StringBuffer();
		String fullMsgBody = "";
		java.net.URLConnection openConnection = url.openConnection();
		InputStream inputStream = openConnection.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				inputStream));
		String readLine = "";
		while ((readLine = in.readLine()) != null) {
			msgBodyBfr.append(readLine).append("\n");
		}
		in.close();
		fullMsgBody = msgBodyBfr.toString();
		fullMsgBody = fullMsgBody.replace("{firstName}",
				netMd.getOwnerFirstName());
		if (netMd.getOwnerLastName() == null) {
			fullMsgBody = fullMsgBody.replace("{lastName}", "");
		} else {
			fullMsgBody = fullMsgBody.replace("{lastName}",
					netMd.getOwnerLastName());
		}
		fullMsgBody = fullMsgBody.replace("{netMdName}", netMd.getName());
		fullMsgBody = fullMsgBody.replace("{userid}", netMd.getUserName());
		fullMsgBody = fullMsgBody.replace("{password}", netMd.getPassword());
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				netMdServerIpAddress);
		return fullMsgBody;
	}

	/**
	 * Creates a netmd branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */

	@Override
	public ResponseDTO createBranch(NetMdBranchDTO branch) {
		ResponseDTO response = new ResponseDTO();
		validator.validateBranchDetails(branch);
		response = netMdDao.createBranch(branch);
		NetMdBranchOwnerDetails branchDetail = netMdDao
				.getBranchOwners(response.getGlobalId());
		sendEmailToNetMdOwner(Constants.NETMD_BRANCH_REGISTER, branchDetail);
		return response;
	}

	/**
	 * Method to send branch registration email to the owner's email id It will
	 * perform the following operations. 1.Take default email HTML template from
	 * Apache folder 2.Create email body 3.Send email to the lab owner.
	 */
	private void sendEmailToNetMdOwner(String subject,
			NetMdBranchOwnerDetails branchDetail) {

		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netMdServerIpAddress
					+ "/youNeverWait/EmailFormat/NetMdBranchRegistration.html");
			msgBody = createEmailBody(url, branchDetail);
			// EmailSender.sendEmail(branchDetail.getOwnerEmail(), mailFrom,
			// / subject, msgBody);
			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody,
					branchDetail.getOwnerEmail(), mailFrom, 0, 0, null,
					SendMsgCallbackEnum.NETMD_BRANCH_REGISTRATION.getId(), null);
			mailThread.addSendMsgObj(obj);

		} catch (IOException e) {
			log.error(" error while sending branch registration email  to the owner's email id",e);
			e.printStackTrace();
			/*
			 * ServiceException se = new ServiceException(
			 * ErrorCodeEnum.EmailSendFailed); se.setDisplayErrMsg(true); throw
			 * se;
			 */
		}
	}

	/**
	 * Method to create email body
	 */
	private String createEmailBody(URL url, NetMdBranchOwnerDetails branchDetail)
			throws IOException {

		StringBuffer msgBodyBfr = new StringBuffer();
		String fullMsgBody = "";

		java.net.URLConnection openConnection = url.openConnection();
		InputStream inputStream = openConnection.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				inputStream));
		String readLine = "";
		while ((readLine = in.readLine()) != null) {
			msgBodyBfr.append(readLine).append("\n");
		}
		in.close();
		fullMsgBody = msgBodyBfr.toString();
		fullMsgBody = fullMsgBody.replace("{firstName}",
				branchDetail.getOwnerFirstName());
		if (branchDetail.getOwnerLastName() == null) {
			fullMsgBody = fullMsgBody.replace("{lastName}", "");
		} else {
			fullMsgBody = fullMsgBody.replace("{lastName}",
					branchDetail.getOwnerLastName());

		}
		fullMsgBody = fullMsgBody.replace("{netMdName}",
				branchDetail.getNetMdName());
		fullMsgBody = fullMsgBody.replace("{branchName}",
				branchDetail.getBranchName());
		fullMsgBody = fullMsgBody.replace("{primaryPassPhrase}",
				branchDetail.getPrimaryPassPhrase());
		if (branchDetail.getPassPhrase().size() > 0) {
			String htmlTd = "<tr><td>Passphrase for other devices :-</td></tr>";
			for (String passPhrase : branchDetail.getPassPhrase()) {
				htmlTd = htmlTd + "<tr><td>&nbsp;</td></tr><tr><td><b> "
						+ passPhrase + "</b></td></tr>";
			}
			fullMsgBody = fullMsgBody.replace("{passPhrase}", htmlTd);
		} else {
			fullMsgBody = fullMsgBody.replace("{passPhrase}", "");
		}

		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				netMdServerIpAddress);

		return fullMsgBody;
	}

	/**
	 * Updates a branch details
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Transactional
	@Override
	public ResponseDTO updateBranch(NetMdBranchDTO branch) {
		ResponseDTO response = new ResponseDTO();
		validator.validateBranchDetails(branch);
		validator.validateBranchId(branch);
		response = netMdDao.updateBranch(branch);
		return response;
	}

	/**
	 * View a branch details
	 * 
	 * @param globalId
	 * @return BranchResponseDTO
	 */
	@Transactional
	@Override
	public NetMdBranchResponseDTO viewBranch(int globalId) {
		NetMdBranchResponseDTO response = new NetMdBranchResponseDTO();
		response = netMdDao.viewBranch(globalId);
		return response;
	}

	/**
	 * Deletes a branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Transactional
	@Override
	public ResponseDTO deleteBranch(int globalId) {
		ResponseDTO response = new ResponseDTO();
		response = netMdDao.deleteBranch(globalId);
		return response;
	}

	/**
	 * To check netmd is already installed on particular device, if so mac id
	 * exists on portal for given passPhrase for that device.
	 * 
	 * @param passPhrase
	 * @return ResponseDTO
	 */
	@Transactional(readOnly = false)
	@Override
	public NetMdResponseDTO getMacStatus(String passPhrase) {
		NetMdResponseDTO response = new NetMdResponseDTO();
		if (passPhrase == null || passPhrase.equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PassPhraseNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		String macId = (String) netMdDao.getMacByPassphrase(passPhrase);
		if (macId == null || macId.equals("")) {
			response.setExistMac(false);
		} else {
			response.setExistMac(true);
		}
		response.setSuccess(true);
		return response;
	}

	/**
	 * Activate netmd after installing netmd on a device. activation process
	 * means verifying netmd installed on correct device and also sending back
	 * all necessary data related to that netmd .
	 * 
	 * @param header
	 * @return
	 */
	@Transactional
	@Override
	public NetMdActivationResponseDTO activateNetMd(HeaderDTO header) {
		NetMdActivationResponseDTO response = new NetMdActivationResponseDTO();
		validator.validateHeaderDetails(header);
		response = netMdDao.activateNetMd(header);
		return response;
	}

	/**
	 * Update netmd account
	 * 
	 * @param netMd
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO updateNetMd(NetMdDTO netMd) {
		ResponseDTO response = new ResponseDTO();
		validator.validateNetMdAccount(netMd);
		response = netMdDao.update(netMd);
		return response;
	}

	/**
	 * Delete netmd
	 * 
	 * @param netMdId
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO deleteNetMd(int netMdId) {
		ResponseDTO response = new ResponseDTO();
		response = netMdDao.delete(netMdId);
		return response;
	}

	/**
	 * Creates netmd user
	 * 
	 * @param netMdUser
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO createUser(HeaderDTO header,NetMdUserDetail user) {
		ResponseDTO response = new ResponseDTO();
		validator.validateUserDetails(header,user);
		validator.validateNetMdUserLoginDetails(user);
		response = netMdDao.createUser(header,user);
		String netMdName = netMdDao.getNetMdName(user.getNetMdId());
		String netMdBranchName = netMdDao.getNetMdBranchName(user.getNetMdBranchId());
		sendEmailToUser(user, netMdName, netMdBranchName);
		return response;
	}

	/**
	 * Update netmd user
	 * 
	 * @param user
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO updateUser(HeaderDTO header,NetMdUserDetail user) {
		ResponseDTO response = new ResponseDTO();
		validator.validateUserDetails(header, user);
		response = netMdDao.updateUser(header, user);
		return response;
	}

	/**
	 * Delete netmd user account
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO deleteUser(int globalId) {
		ResponseDTO response = new ResponseDTO();
		validator.validateGlobalId(globalId);
		response = netMdDao.deleteUser(globalId);
		return response;
	}


	@Override
	@Transactional
	public ResponseDTO makePrimary(HeaderDTO header){
		ResponseDTO response = new ResponseDTO();
		response = netMdDao.makePrimary(header);
		return response;
	}

	/**
	 * Method which clears mac Id
	 * 
	 * @param header
	 * @return ResponseDTO
	 */
	@Transactional
	@Override
	public ResponseDTO clearMacId(HeaderDTO header) {

		ResponseDTO response = new ResponseDTO();
		validator.validateHeader(header);
		response = netMdDao.clearMacId(header);
		return response;
	}

	/**
	 * View netmd user
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public NetMdUserDTO viewUser(int globalId) {
		NetMdUserDTO response = new NetMdUserDTO();
		validator.validateGlobalId(globalId);
		response = netMdDao.viewUser(globalId);
		return response;
	}

	/**
	 * Retrieve netmd list which satisfy all filter conditions
	 * 
	 * @param filterDTO
	 * @return NetMdListResponseDTO
	 */
	@Transactional
	@Override
	public NetMdListResponseDTO getNetMdList(FilterDTO filterDTO) {
		NetMdListResponseDTO response = new NetMdListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,
		// return result with appropriate error code
		ErrorDTO error = validator.validateNetMdFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for lab from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.NETMD);
		if (queryBuilder == null) {
			return response;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

			// get filter from filter factory by setting expression name and
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<NetmdTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());

		Long count = queryBuilder.getCount();
		System.out.println("queryBuilder.getCount():" + count);
		// execute query
		List<NetmdTbl> netmdList = queryBuilder.executeQuery(q);
		response = getNetmdList(netmdList);
		response.setCount(count);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Assign netmd record details to response
	 * 
	 * @param netmdList
	 * @return NetMdListResponseDTO
	 */
	private NetMdListResponseDTO getNetmdList(List<NetmdTbl> netmdList) {
		NetMdListResponseDTO response = new NetMdListResponseDTO();
		if (netmdList == null) {
			return response;
		}
		List<NetMdDetail> netMdDetails = new ArrayList<NetMdDetail>();
		for (NetmdTbl netmdTbl : netmdList) {
			netMdDetails.add(new NetMdDetail(netmdTbl));
		}
		response.setNetMd(netMdDetails);
		return response;
	}

	/**
	 * Method to perform mail sending
	 * 
	 * @param user
	 * @param labName
	 */
	private void sendEmailToUser(NetMdUserDetail user, String netMdName,
			String netMdBranchName) {

		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netMdServerIpAddress
					+ "/youNeverWait/EmailFormat/NetMdUserRegistration.html");
			msgBody = createUserEmailBody(url, user, netMdName, netMdBranchName);

			SendMailMsgObj obj = new SendMailMsgObj(
					Constants.USER_REGISTRATION, msgBody, user.getEmail(),
					mailFrom, 0, 0, null,
					SendMsgCallbackEnum.NETMD_USER_REGISTRATION.getId(), null);
			mailThread.addSendMsgObj(obj);
			// EmailSender.sendEmail(user.getEmail(), mailFrom,
			// Constants.USER_REGISTRATION, msgBody);
		} catch (IOException e) {
			log.error("Error while sending email to Netmd user",e);
			e.printStackTrace();
			/*
			 * ServiceException se = new ServiceException(
			 * ErrorCodeEnum.EmailSendFailed); se.setDisplayErrMsg(true); throw
			 * se;
			 */
		}
	}

	/**
	 * Creates Email body
	 * 
	 * @param url
	 * @param user
	 * @param netMdName
	 * @throws IOException
	 */
	private String createUserEmailBody(URL url, NetMdUserDetail user,
			String netMdName, String netMdBranchName) throws IOException {

		StringBuffer msgBodyBfr = new StringBuffer();
		String fullMsgBody = "";

		java.net.URLConnection openConnection = url.openConnection();
		InputStream inputStream = openConnection.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				inputStream));
		String readLine = "";
		while ((readLine = in.readLine()) != null) {
			msgBodyBfr.append(readLine).append("\n");
		}
		in.close();
		fullMsgBody = msgBodyBfr.toString();
		fullMsgBody = fullMsgBody.replace("{firstName}", user.getFirstName());
		if (user.getLastName() == null) {
			fullMsgBody = fullMsgBody.replace("{lastName}", "");
		} else {
			fullMsgBody = fullMsgBody.replace("{lastName}", user.getLastName());
		}
		// fullMsgBody = fullMsgBody.replace("{netMdName}", netMdName);
		fullMsgBody = fullMsgBody.replace("{branchName}", netMdBranchName);
		fullMsgBody = fullMsgBody.replace("{userid}", user.getUserName());
		fullMsgBody = fullMsgBody.replace("{password}", user.getPassword());
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				netMdServerIpAddress);

		return fullMsgBody;
	}

	/**
	 * View netmd details
	 * 
	 * @param netMdId
	 * @return NetMdViewResponseDTO
	 */
	@Override
	@Transactional
	public NetMdViewResponseDTO viewNetMd(int netMdId) {
		NetMdViewResponseDTO response = new NetMdViewResponseDTO();
		if (netMdId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		response = netMdDao.view(netMdId);
		return response;
	}

	/**
	 * Method performed when password forgotten
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@Transactional
	@Override
	public ResponseDTO forgotPassword(LoginDTO login) {

		ResponseDTO response = new ResponseDTO();
		if (login.getUserName() == null || login.getUserName().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		UserCredentials user = netMdDao.getUserCredentials(login);
		if (user.getEmailId() == null || user.getEmailId().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidMailId);
			se.setDisplayErrMsg(true);
			throw se;
		}

		sendEmailForResetPassword(Constants.RESET_PASSWORD, user);
		response.setSuccess(true);
		return response;

	}

	/**
	 * Method to send email for resetting password.It will perform the following
	 * operations. 1.Take default email HTML template from Apache folder
	 * 2.Create email body 3.Send email to the lab user/owner.
	 */
	private void sendEmailForResetPassword(String subject, UserCredentials user) {

		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netMdServerIpAddress
					+ "/youNeverWait/EmailFormat/NetMdForgotPassword.html");
			msgBody = createDefaultEmailBody(url, user);
			// EmailSender.sendEmail(emailId, mailFrom, subject, msgBody);

			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody,
					user.getEmailId(), mailFrom, 0, 0, null,
					SendMsgCallbackEnum.NETMD_RESET_PWD.getId(), null);
			mailThread.addSendMsgObj(obj);
		} catch (IOException e) {
			log.error("Error while sending Email when doing Netmd forgot password",e);
			e.printStackTrace();
			/*
			 * ServiceException se = new ServiceException(
			 * ErrorCodeEnum.EmailSendFailed); se.setDisplayErrMsg(true);
			 * //throw se;
			 */}
	}

	/**
	 * Method to create email body for Reset Password
	 */
	private String createDefaultEmailBody(URL url, UserCredentials user)
			throws IOException {

		StringBuffer msgBodyBfr = new StringBuffer();
		String fullMsgBody = "";
		
		String encryptedUserName = StringEncoder.encryptWithStaticKey(user
				.getUserName());
		String resetPasswordLink = "http://" + netMdServerIpAddress
				+ "/youNeverWait/EmailFormat/NetMdResetPassword.html?userName="
				+ encryptedUserName;
		java.net.URLConnection openConnection = url.openConnection();
		InputStream inputStream = openConnection.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				inputStream));
		String readLine = "";
		while ((readLine = in.readLine()) != null) {
			msgBodyBfr.append(readLine).append("\n");
		}
		in.close();
		fullMsgBody = msgBodyBfr.toString();
		fullMsgBody = fullMsgBody.replace("{firstname}", user.getFirstName());
		if (user.getLastName() != null && !user.getLastName().equals("")) {
			fullMsgBody = fullMsgBody.replace("{lastname}", user.getLastName());
		} else {
			fullMsgBody = fullMsgBody.replace("{lastname}", "");
		}
		fullMsgBody = fullMsgBody.replace("{ResetLink}", resetPasswordLink);
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				netMdServerIpAddress);

		return fullMsgBody;
	}

	/**
	 * Method performed to retrieve created updated and deleted users list
	 * from portal
	 * 
	 * @param lastSyncTime
	 * @param passPhrase
	 * @param netmdBranchId
	 * @return RetrievalUserResponseDTO
	 */
	@Override
	public RetrievalUserResponseDTO retrieveUserList(String lastSyncTime,
			String passPhrase, int netmdBranchId, Date currentSyncTime) {
		RetrievalUserResponseDTO response = netMdDao.retrieveUserList(
				lastSyncTime, passPhrase, netmdBranchId,currentSyncTime);
		return response;
	}


	/**
	 * Method to reset password
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO resetPassword(LoginDTO login) {
		validator.validateUserNameAndPassword(login.getUserName(),
				login.getPassword());
		ResponseDTO response = netMdDao.resetPassword(login);
		return response;

	}

	/**
	 * Method to change password
	 * 
	 * @param password
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO changePassword(PasswordDTO passwords) {
		validator.validatePasswords(passwords);
		ResponseDTO response = netMdDao.changePassword(passwords);
		return response;

	}
	/**
	 * @return the netMdDao
	 */
	public NetMdDao getNetMdDao() {
		return netMdDao;
	}

	/**
	 * @param netMdDao
	 *            the netMdDao to set
	 */
	public void setNetMdDao(NetMdDao netMdDao) {
		this.netMdDao = netMdDao;
	}

	/**
	 * @return the validator
	 */
	public NetMdValidator getValidator() {
		return validator;
	}

	/**
	 * @param validator
	 *            the validator to set
	 */
	public void setValidator(NetMdValidator validator) {
		this.validator = validator;
	}

	/**
	 * @return the mailFrom
	 */
	public String getMailFrom() {
		return mailFrom;
	}

	/**
	 * @param mailFrom
	 *            the mailFrom to set
	 */
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	/**
	 * @return the queryBuilderFactory
	 */
	public QueryBuilderFactory getQueryBuilderFactory() {
		return queryBuilderFactory;
	}

	/**
	 * @param queryBuilderFactory
	 *            the queryBuilderFactory to set
	 */
	public void setQueryBuilderFactory(QueryBuilderFactory queryBuilderFactory) {
		this.queryBuilderFactory = queryBuilderFactory;
	}

	/**
	 * @return the filterFactory
	 */
	public FilterFactory getFilterFactory() {
		return filterFactory;
	}

	/**
	 * @param filterFactory
	 *            the filterFactory to set
	 */
	public void setFilterFactory(FilterFactory filterFactory) {
		this.filterFactory = filterFactory;
	}

	/**
	 * @return the netMdServerIpAddress
	 */
	public String getNetMdServerIpAddress() {
		return netMdServerIpAddress;
	}

	/**
	 * @param netMdServerIpAddress
	 *            the netMdServerIpAddress to set
	 */
	public void setNetMdServerIpAddress(String netMdServerIpAddress) {
		this.netMdServerIpAddress = netMdServerIpAddress;
	}

	/**
	 * @return the mailThread
	 */
	public SendEmailMsgWorkerThread getMailThread() {
		return mailThread;
	}

	/**
	 * @param mailThread
	 *            the mailThread to set
	 */
	public void setMailThread(SendEmailMsgWorkerThread mailThread) {
		this.mailThread = mailThread;
	}




}
