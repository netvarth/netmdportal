/**
 * NetRxServiceImpl.java
 *
 * @Author Luciya Jos
 * May 8, 2013 
 */
package com.nv.youNeverWait.user.bl.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
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
import com.nv.youNeverWait.pl.entity.NetrxBranchTbl;
import com.nv.youNeverWait.pl.entity.NetrxTbl;
import com.nv.youNeverWait.pl.entity.NetrxUserTbl;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchDetail;
import com.nv.youNeverWait.rs.dto.NetRxBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchOwnerDetails;
import com.nv.youNeverWait.rs.dto.NetRxBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxDTO;
import com.nv.youNeverWait.rs.dto.NetRxDetail;
import com.nv.youNeverWait.rs.dto.NetRxHeaderDTO;
import com.nv.youNeverWait.rs.dto.NetRxListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxUserDTO;
import com.nv.youNeverWait.rs.dto.NetRxUserDetail;
import com.nv.youNeverWait.rs.dto.NetRxUserListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxViewResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.user.bl.service.NetRxService;
import com.nv.youNeverWait.user.bl.validation.NetRxValidator;
import com.nv.youNeverWait.user.pl.dao.NetRxDao;
import com.nv.youNeverWait.util.filter.core.Filter;
import com.nv.youNeverWait.util.filter.core.FilterFactory;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;
import com.nv.youNeverWait.util.filter.core.QueryBuilderFactory;

/**
 * @author netvarth
 * 
 */
public class NetRxServiceImpl implements NetRxService {
	private NetRxDao netRxDao;
	private NetRxValidator validator;
	private String netRxServerIpAddress;
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	private String mailFrom;
	private SendEmailMsgWorkerThread mailThread;
	private static final Log log = LogFactory.getLog(NetRxServiceImpl.class);

	/**
	 * Creates a netrx account
	 * 
	 * @param netrx
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO create(NetRxDTO netRx) {

		validator.validateNetRxAccount(netRx);
		validator.validateUserNameAndPassword(netRx.getUserName(),
				netRx.getPassword());
		ResponseDTO response = netRxDao.create(netRx);
		sendEmailToNetRxOwner(Constants.NETRX_REGISTER, netRx);
		return response;
	}

	/**
	 * Method which clears mac Id
	 * 
	 * @param header
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO clearMacId(NetRxHeaderDTO header) {

		validator.validateHeader(header);
		ResponseDTO response = netRxDao.clearMacId(header);
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
		ResponseDTO response = netRxDao.changePassword(passwords);
		return response;

	}

	/**
	 * Creates a netrx account
	 * 
	 * @param netrx
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO update(NetRxDTO netRx) {
		validator.validateNetRxAccount(netRx);
		ResponseDTO response = netRxDao.update(netRx);
		return response;
	}

	/**
	 * Creates netrx user
	 * 
	 * @param netrxUser
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO createUser(NetRxHeaderDTO header, NetRxUserDetail user) {

		validator.validateUserDetails(header, user);
		validator.validateNetRxUserCreation(user);
		ResponseDTO response = netRxDao.createUser(header, user);
		String netRxName = netRxDao.getNetRxName(user.getNetRxId());
		String netRxBranchName = netRxDao.getNetRxBranchName(user
				.getNetRxBranchId());
		sendEmailToUser(user, netRxName, netRxBranchName);
		return response;
	}

	/**
	 * Update netrx user
	 * 
	 * @param user
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO updateUser(NetRxHeaderDTO header, NetRxUserDetail user) {
		
		validator.validateUserDetails(header, user);
		ResponseDTO response = netRxDao.updateUser(header, user);
		return response;
	}

	/**
	 * Method to perform mail sending
	 * 
	 * @param user
	 * @param labName
	 */
	private void sendEmailToUser(NetRxUserDetail user, String netrxName,
			String netrxBranchName) {

		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netRxServerIpAddress
					+ "/youNeverWait/EmailFormat/NetRxUserRegistration.html");
			msgBody = createUserEmailBody(url, user, netrxName, netrxBranchName);

			SendMailMsgObj obj = new SendMailMsgObj(
					Constants.USER_REGISTRATION, msgBody, user.getEmail(),
					mailFrom, 0, 0, null,
					SendMsgCallbackEnum.NETRX_USER_REGISTRATION.getId(), null);
			mailThread.addSendMsgObj(obj);
		} catch (IOException e) {
			log.error("Error while sending Email to  NetRx User ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Creates Email body
	 * 
	 * @param url
	 * @param user
	 * @param netrxName
	 * @throws IOException
	 */
	private String createUserEmailBody(URL url, NetRxUserDetail user,
			String netrxName, String netrxBranchName) throws IOException {

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

		fullMsgBody = fullMsgBody.replace("{branchName}", netrxBranchName);
		fullMsgBody = fullMsgBody.replace("{userid}", user.getUserName());
		fullMsgBody = fullMsgBody.replace("{password}", user.getPassword());
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				netRxServerIpAddress);

		return fullMsgBody;
	}

	/**
	 * Delete netrx user account
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO deleteUser(int globalId) {
		validator.validateGlobalId(globalId);
		ResponseDTO response = netRxDao.deleteUser(globalId);
		return response;
	}

	/**
	 * To retrieve a list of netrx which satisfy all filter conditions
	 * 
	 * @param filterDTO
	 * @return NetRxBranchListResponseDTO
	 */
	@Override
	public NetRxListResponseDTO list(FilterDTO filterDTO) {

		NetRxListResponseDTO response = new NetRxListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,return result with appropriate error code
		ErrorDTO error = validator.validateNetRxFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for netrx branch from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.NETRX);
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
		TypedQuery<NetrxTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());

		// get count
		Long count = queryBuilder.getCount();

		// execute query
		List<NetrxTbl> netrxList = queryBuilder.executeQuery(q);
		response = getNetRxList(netrxList);
		response.setCount(count);
		response.setSuccess(true);
		return response;
	}

	/**
	 * To set response with details of netrxList
	 * 
	 * @param netrxList
	 * @return NetRxListResponseDTO
	 */
	private NetRxListResponseDTO getNetRxList(List<NetrxTbl> netrxList) {
		NetRxListResponseDTO response = new NetRxListResponseDTO();
		if (netrxList == null) {
			return response;
		}
		List<NetRxDetail> netRxDetails = new ArrayList<NetRxDetail>();
		for (NetrxTbl netrxTbl : netrxList) {
			netRxDetails.add(new NetRxDetail(netrxTbl));
		}
		response.setNetRx(netRxDetails);
		return response;
	}

	/**
	 * To retrieve a list of NetRx branches which satisfy all filter conditions
	 * 
	 * @param filterDTO
	 * @return NetRxBranchListResponseDTO
	 */
	@Override
	@Transactional
	public NetRxBranchListResponseDTO getNetRxBranchList(FilterDTO filterDTO) {

		NetRxBranchListResponseDTO response = new NetRxBranchListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,return result with appropriate error code
		ErrorDTO error = validator.validateNetRxBranchFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for netRx branch from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.NETRX_BRANCH);
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
		TypedQuery<NetrxBranchTbl> q = queryBuilder.buildQuery(
				filterDTO.isAsc(), filterDTO.getFrom(), filterDTO.getCount());

		// get count
		Long count = queryBuilder.getCount();

		// execute query
		List<NetrxBranchTbl> NetRxBranches = queryBuilder.executeQuery(q);
		response = netrxBranchList(NetRxBranches);
		response.setCount(count);
		response.setSuccess(true);
		return response;
	}

	/**
	 * To set response with details of branches
	 * 
	 * @param branches
	 * @return netrxBranchListResponseDTO
	 */
	private NetRxBranchListResponseDTO netrxBranchList(
			List<NetrxBranchTbl> branches) {
		NetRxBranchListResponseDTO response = new NetRxBranchListResponseDTO();
		if (branches == null) {
			return response;
		}
		List<NetRxBranchDetail> netRxBranchDetails = new ArrayList<NetRxBranchDetail>();
		for (NetrxBranchTbl netRxBranchTbl : branches) {
			netRxBranchDetails.add(new NetRxBranchDetail(netRxBranchTbl));
		}
		response.setNetRxBranch(netRxBranchDetails);
		return response;
	}

	/**
	 * To send netrx registration email to the owner's email id It will perform
	 * the following operations. 1.Take default email HTML template 2.Create
	 * email body 3.Send email to the lab owner.
	 * 
	 * @param subject
	 * @param netRx
	 * @return
	 */
	private void sendEmailToNetRxOwner(String subject, NetRxDTO netRx) {
		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netRxServerIpAddress
					+ "/youNeverWait/EmailFormat/NetRxRegistration.html");
			msgBody = createDefaultEmailBody(url, netRx);

			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody,
					netRx.getOwnerEmail(), mailFrom, 0, 0, null,
					SendMsgCallbackEnum.NETMD_REGISTRATION.getId(), null);
			mailThread.addSendMsgObj(obj);

		} catch (IOException e) {
			log.error(
					"Error while sending NetRxRegistration email to Owner's Id ",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * To create email body
	 * 
	 * @param url
	 *            ,netRx
	 * 
	 * @return email message body
	 */
	private String createDefaultEmailBody(URL url, NetRxDTO netRx)
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
				netRx.getOwnerFirstName());
		if (netRx.getOwnerLastName() == null) {
			fullMsgBody = fullMsgBody.replace("{lastName}", "");
		} else {
			fullMsgBody = fullMsgBody.replace("{lastName}",
					netRx.getOwnerLastName());
		}
		fullMsgBody = fullMsgBody.replace("{netRxName}", netRx.getName());
		fullMsgBody = fullMsgBody.replace("{userid}", netRx.getUserName());
		fullMsgBody = fullMsgBody.replace("{password}", netRx.getPassword());
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				netRxServerIpAddress);
		return fullMsgBody;
	}

	/**
	 * Deletes a netRx
	 * 
	 * @param netRxId
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO deleteNetRx(int netRxId) {
		ResponseDTO response = netRxDao.deleteNetRx(netRxId);
		return response;
	}

	/**
	 * View netrx user
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@Override
	public NetRxUserDTO viewUser(int globalId) {
		validator.validateGlobalId(globalId);
		NetRxUserDTO response = netRxDao.viewUser(globalId);
		return response;
	}

	/**
	 * Method to list NetRx users
	 */
	@Override
	public NetRxUserListResponseDTO listNetRxUser(FilterDTO filterDTO) {
		NetRxUserListResponseDTO response = new NetRxUserListResponseDTO();
		ErrorDTO error = validator.validateNetRxUserFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for netrx branch from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.NETRX_USER);
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
		TypedQuery<NetrxUserTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());

		// get count
		Long count = queryBuilder.getCount();

		// execute query
		List<NetrxUserTbl> users = queryBuilder.executeQuery(q);
		response = getNetRxUserList(users);
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
	private NetRxUserListResponseDTO getNetRxUserList(List<NetrxUserTbl> users) {
		NetRxUserListResponseDTO response = new NetRxUserListResponseDTO();
		if (users == null) {
			return response;
		}
		List<NetRxUserDetail> netRxUserDetails = new ArrayList<NetRxUserDetail>();
		for (NetrxUserTbl netrxUserTbl : users) {
			netRxUserDetails.add(new NetRxUserDetail(netrxUserTbl));
		}
		response.setNetRxUser(netRxUserDetails);
		return response;
	}

	/**
	 * View netRx account
	 * 
	 * @param netRxId
	 * @return NetRxViewResponseDTO
	 */
	@Override
	public NetRxViewResponseDTO viewNetRx(int netRxId) {

		if (netRxId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRx);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netRxId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetRxViewResponseDTO response = netRxDao.viewNetRx(netRxId);
		return response;
	}

	/**
	 * Creates a netRx branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO createBranch(NetRxBranchDetail branch) {
		
		validator.validateBranchDetails(branch);
		ResponseDTO response = netRxDao.createBranch(branch);
		NetRxBranchOwnerDetails branchDetail = netRxDao
				.getBranchOwners(response.getGlobalId());
		sendEmailToNetRxOwner(Constants.NETRX_BRANCH_REGISTER, branchDetail);
		return response;
	}

	/**
	 * Method to send branch registration email to the owner's email id It will
	 * perform the following operations. 1.Take default email HTML template from
	 * Apache folder 2.Create email body 3.Send email to the lab owner.
	 */
	private void sendEmailToNetRxOwner(String subject,
			NetRxBranchOwnerDetails branchDetail) {

		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netRxServerIpAddress
					+ "/youNeverWait/EmailFormat/NetRxBranchRegistration.html");
			msgBody = createEmailBody(url, branchDetail);
			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody,
					branchDetail.getOwnerEmail(), mailFrom, 0, 0, null,
					SendMsgCallbackEnum.NETRX_BRANCH_REGISTRATION.getId(), null);
			mailThread.addSendMsgObj(obj);

		} catch (IOException e) {
			log.error("Error while sending Email to NetRx Owner ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method to create email body
	 */
	private String createEmailBody(URL url, NetRxBranchOwnerDetails branchDetail)
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
		fullMsgBody = fullMsgBody.replace("{netRxName}",
				branchDetail.getNetRxName());
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
				netRxServerIpAddress);

		return fullMsgBody;
	}

	/**
	 * View netrx branch details
	 * 
	 * @param globalId
	 * @return NetRxBranchResponseDTO
	 */
	@Override
	public NetRxBranchResponseDTO viewBranch(int netrxBranchId) {
		if (netrxBranchId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranch);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(netrxBranchId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetRxBranchResponseDTO response = netRxDao.viewBranch(netrxBranchId);
		return response;
	}

	/**
	 * Deletes a netRx branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO deleteBranch(int globalId) {
		 
		if (globalId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		ResponseDTO response = netRxDao.deleteBranch(globalId);
		return response;
	}

	/**
	 * Updates netRx branch details
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO updateNetRxBranch(NetRxBranchDetail branch) {
		validator.validateUpdateBranchDetails(branch);
		ResponseDTO response = netRxDao.updateNetRxBranch(branch);
		return response;
	}

	/**
	 * Method performed when password forgotten
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO forgotPassword(LoginDTO login) {
		ResponseDTO response = new ResponseDTO();
		if (login.getUserName() == null || login.getUserName().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		UserCredentials user = netRxDao.getUserCredentials(login);
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
	 * 2.Create email body 3.Send email to the NetRx user/owner.
	 */
	private void sendEmailForResetPassword(String subject, UserCredentials user) {

		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netRxServerIpAddress
					+ "/youNeverWait/EmailFormat/NetRxForgotPassword.html");
			msgBody = createDefaultEmailBody(url, user);

			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody,
					user.getEmailId(), mailFrom, 0, 0, null,
					SendMsgCallbackEnum.NETRX_RESET_PWD.getId(), null);
			mailThread.addSendMsgObj(obj);
		} catch (IOException e) {
			log.error(
					"Error while sending Email when doing NetRx forgot password",
					e);
			e.printStackTrace();
		}
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
		String resetPasswordLink = "http://" + netRxServerIpAddress
				+ "/youNeverWait/EmailFormat/NetRxResetPassword.html?userName="
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
				netRxServerIpAddress);

		return fullMsgBody;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.NetRxService#setNetRxSync(com.nv.youNeverWait.rs.dto.SyncFreqDTO)
	 */
	@Override
	public SyncFreqResponseDTO setNetRxSync(SyncFreqDTO sync) {
		if(sync.getNetrxId()<=0){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidNetRx);
			se.addParam(new Parameter(Constants.ID, Integer.toString(sync.getNetrxId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		SyncFreqResponseDTO response = netRxDao.setNetRxSync(sync);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.NetRxService#setNetRxBranchSync(com.nv.youNeverWait.rs.dto.SyncFreqDTO)
	 */
	@Override
	public SyncFreqResponseDTO setNetRxBranchSync(SyncFreqDTO sync) {
		if(sync.getNetrxBranchId()<=0){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidNetRxBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		SyncFreqResponseDTO response = netRxDao.setNetRxBranchSync(sync);
		return response;
	}
	
	/**
	 * To reset password of netRx user/owner
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO resetPassword(LoginDTO login) {
		validator.validateUserNameAndPassword(login.getUserName(),
				login.getPassword());
		ResponseDTO response = netRxDao.resetPassword(login);
		return response;
	}

	/**
	 * @return the netRxDao
	 */
	public NetRxDao getNetRxDao() {
		return netRxDao;
	}

	/**
	 * @param netRxDao
	 *            the netRxDao to set
	 */
	public void setNetRxDao(NetRxDao netRxDao) {
		this.netRxDao = netRxDao;
	}

	/**
	 * @return the validator
	 */
	public NetRxValidator getValidator() {
		return validator;
	}

	/**
	 * @param validator
	 *            the validator to set
	 */
	public void setValidator(NetRxValidator validator) {
		this.validator = validator;
	}

	/**
	 * @return the netRxServerIpAddress
	 */
	public String getNetRxServerIpAddress() {
		return netRxServerIpAddress;
	}

	/**
	 * @param netRxServerIpAddress
	 *            the netRxServerIpAddress to set
	 */
	public void setNetRxServerIpAddress(String netRxServerIpAddress) {
		this.netRxServerIpAddress = netRxServerIpAddress;
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
