/**
 * LabServiceImpl.java
 *
 * Jan 3, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.bl.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.persistence.TypedQuery;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nv.platform.email.sendmsg.SendEmailMsgWorkerThread;
import com.nv.platform.email.sendmsg.SendMsgCallbackEnum;
import com.nv.platform.email.sendmsg.email.SendMailMsgObj;
import com.nv.platform.email.util.StringEncoder;
import com.nv.security.youNeverWait.User;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.globalsetting.PublicKeyGenerator;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.LabBranchTbl;
import com.nv.youNeverWait.pl.entity.LabTbl;
import com.nv.youNeverWait.pl.entity.OrderAmountTbl;
import com.nv.youNeverWait.pl.entity.OrderBranchTbl;
import com.nv.youNeverWait.pl.entity.OrderResultTbl;
import com.nv.youNeverWait.pl.entity.OrderTransferTbl;
import com.nv.youNeverWait.rs.dto.BranchOrderCountResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderDetail;
import com.nv.youNeverWait.rs.dto.BranchOrdersResponseDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.HealthCareDetailDTO;
import com.nv.youNeverWait.rs.dto.HealthMonitorResponse;
import com.nv.youNeverWait.rs.dto.LabBranchDTO;
import com.nv.youNeverWait.rs.dto.LabBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchSystemInfoDetails;
import com.nv.youNeverWait.rs.dto.LabActivationResponseDTO;
import com.nv.youNeverWait.rs.dto.LabOrderHeaderDTO;
import com.nv.youNeverWait.rs.dto.LabResultHeaderDTO;
import com.nv.youNeverWait.rs.dto.LabUserDTO;
import com.nv.youNeverWait.rs.dto.ListResponse;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.MacStatusResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchDetail;
import com.nv.youNeverWait.rs.dto.BranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.LabBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.LabDTO;
import com.nv.youNeverWait.rs.dto.LabDetail;
import com.nv.youNeverWait.rs.dto.LabListResponseDTO;
import com.nv.youNeverWait.rs.dto.LabResponseDTO;
import com.nv.youNeverWait.rs.dto.MailTransferInfo;
import com.nv.youNeverWait.rs.dto.OrderDestinationBranchDTO;
import com.nv.youNeverWait.rs.dto.OrderDetails;
import com.nv.youNeverWait.rs.dto.OrderTestResultList;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.PublicKeyDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultRetrievalResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultTransferDTO;
import com.nv.youNeverWait.rs.dto.ResultTransferResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveLabListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveNetmdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveNetmdListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveUserListResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncUser;
import com.nv.youNeverWait.rs.dto.SystemHealthDetails;
import com.nv.youNeverWait.rs.dto.SystemHealthResponse;
import com.nv.youNeverWait.rs.dto.TransferNetMdResultDTO;
import com.nv.youNeverWait.rs.dto.TransferredDetails;
import com.nv.youNeverWait.rs.dto.TransferredOrders;
import com.nv.youNeverWait.rs.dto.TransferredResultDetails;
import com.nv.youNeverWait.rs.dto.TransferredResults;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.rs.dto.UserInfo;
import com.nv.youNeverWait.user.bl.service.FacilityService;
import com.nv.youNeverWait.user.bl.service.HealthMonitorService;
import com.nv.youNeverWait.user.bl.service.LabService;
import com.nv.youNeverWait.user.bl.service.NetMdService;
import com.nv.youNeverWait.user.bl.service.OrderService;
import com.nv.youNeverWait.user.bl.service.ResultService;
import com.nv.youNeverWait.user.bl.validation.LabValidator;
import com.nv.youNeverWait.user.pl.dao.LabDao;
import com.nv.youNeverWait.user.pl.impl.BranchOwnerDetails;
import com.nv.youNeverWait.util.filter.core.Filter;
import com.nv.youNeverWait.util.filter.core.FilterFactory;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;
import com.nv.youNeverWait.util.filter.core.QueryBuilderFactory;

/**
 * @author 
 *
 */
public class LabServiceImpl implements LabService {
	private LabDao labDao;
	private LabValidator validator;
	private String netlimsServerIpAddress;
	private String mailFrom;
	private NetMdService netMdService;
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	private SendEmailMsgWorkerThread mailThread;
	private static final Log log = LogFactory.getLog(LabServiceImpl.class);
	private OrderService orderService;
	private HealthMonitorService healthService;
	private ResultService resultService;
	private FacilityService facilityService;
	private PublicKeyGenerator publicKey;


	public PublicKeyGenerator getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKeyGenerator publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * Create user in Lab
	 * 
	 * @param user
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO createUser(LabUserDTO user) {

		ResponseDTO response = new ResponseDTO();
		ErrorDTO error = validator.validateUser(user);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response = labDao.createUser(user);
		String labName = labDao.getLabName(user.getLabId());
		sendEmailToUser(user, labName);
		return response;
	}

	/**
	 * To send an email which contains login details of user
	 * 
	 * @param user
	 * @param labName
	 */
	private void sendEmailToUser(LabUserDTO user, String labName) {

		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netlimsServerIpAddress
					+ "/youNeverWait/EmailFormat/LabUserRegistration.html");
			msgBody = createUserEmailBody(url, user, labName);
			SendMailMsgObj obj = new SendMailMsgObj(
					Constants.USER_REGISTRATION, msgBody, user.getEmail(),
					mailFrom, 0, 0, null,
					SendMsgCallbackEnum.LAB_USER_REGISTRATION.getId(), null);
			mailThread.addSendMsgObj(obj);
		} catch (IOException e) {
			log.error("Error while sending Email to Lab user", e);
			e.printStackTrace();
		}
	}

	/**
	 * Creates Email body
	 * 
	 * @param url
	 * @param user
	 * @param labName
	 * @throws IOException
	 */
	private String createUserEmailBody(URL url, LabUserDTO user, String labName)
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
		fullMsgBody = fullMsgBody.replace("{firstName}", user.getFirstName());
		if (user.getLastName() == null) {
			fullMsgBody = fullMsgBody.replace("{lastName}", "");
		} else {
			fullMsgBody = fullMsgBody.replace("{lastName}", user.getLastName());
		}
		fullMsgBody = fullMsgBody.replace("{labName}", labName);
		fullMsgBody = fullMsgBody.replace("{userid}", user.getLogin()
				.getUserName());
		fullMsgBody = fullMsgBody.replace("{password}", user.getLogin()
				.getPassword());
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				netlimsServerIpAddress);

		return fullMsgBody;
	}

	/**
	 * Update a user in Lab
	 * 
	 * @param user
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO updateUser(LabUserDTO user) {

		ResponseDTO response = new ResponseDTO();
		ErrorDTO error = validator.validateUser(user);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response = labDao.updateUser(user);
		return response;
	}

	/**
	 * View a user in Lab
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@Override
	public LabUserDTO viewUser(int globalId) {

		LabUserDTO response = labDao.viewUser(globalId);
		return response;
	}

	/**
	 * Delete a user in Lab
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO deleteUser(int id) {

		ResponseDTO response = labDao.deleteUser(id);
		return response;
	}

	/**
	 * Method which performs password changing
	 * 
	 * @param passwords
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO changePassword(PasswordDTO passwords) {

		validator.validatePasswords(passwords);
		ResponseDTO response = labDao.changePassword(passwords);
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
		UserCredentials user = labDao.getUserCredentials(login);
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
	 * operations. 1.Take default email HTML template 2.Create email body 3.Send
	 * email to the lab user/owner.
	 */
	private void sendEmailForResetPassword(String subject, UserCredentials user) {

		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netlimsServerIpAddress
					+ "/youNeverWait/EmailFormat/LabForgotPassword.html");
			msgBody = createDefaultEmailBody(url, user);
			// EmailSender.sendEmail(emailId, mailFrom, subject, msgBody);

			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody,
					user.getEmailId(), mailFrom, 0, 0, null,
					SendMsgCallbackEnum.LAB_RESET_PWD.getId(), null);
			mailThread.addSendMsgObj(obj);
		} catch (IOException e) {
			log.error(
					"Error while sending Email for resetting password in lab",
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
		String resetPasswordLink = "http://" + netlimsServerIpAddress
				+ "/youNeverWait/EmailFormat/LabResetPassword.html?userName="
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
				netlimsServerIpAddress);

		return fullMsgBody;
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
		ResponseDTO response = labDao.resetPassword(login);
		return response;
	}

	/**
	 * Method which clears mac Id
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */

	@Override
	public ResponseDTO clearMacId(LabBranchDTO branch) {

		ResponseDTO response = new ResponseDTO();
		validator.validateBranchId(branch);
		response = labDao.clearMacId(branch);
		return response;
	}

	/**
	 * Checking whether mac Id exists or not
	 * 
	 * @param passPhrase
	 * @return ResponseDTO
	 */

	@Override
	public MacStatusResponseDTO getMacStatus(String passPhrase) {

		MacStatusResponseDTO response = new MacStatusResponseDTO();
		if (passPhrase == null || passPhrase.equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PassPhraseNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		String macId = (String) labDao.getMacByPassphrase(passPhrase);
		if (macId == null || macId.equals("")) {
			response.setExistMac(false);
			response.setSuccess(true);
			return response;
		} else {
			response.setExistMac(true);
			response.setSuccess(true);
			return response;
		}
	}

	/**
	 * Retrieves lab branch details and set Mac Id if not exists
	 * 
	 * @param header
	 * @return LabActivationResponseDTO
	 */

	@Override
	public LabActivationResponseDTO activateLab(HeaderDTO header) {

		LabActivationResponseDTO response = new LabActivationResponseDTO();
		validator.validateHeaderDetails(header);
		response = labDao.activateLab(header);
		return response;
	}

	/**
	 * Creates a lab
	 * 
	 * @param lab
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO create(LabDTO lab) {

		ResponseDTO response = new ResponseDTO();
		validator.validateNetLimsAccount(lab);
		validator.validateUserNameAndPassword(lab.getUserName(),
				lab.getPassword());
		response = labDao.create(lab);
		sendEmailToLabOwner(lab.getOwnerEmail(), Constants.LAB_REGISTRATION,
				lab);
		return response;
	}

	/**
	 * Method to send lab registration email to the owner's email id It will
	 * perform the following operations. 1.Take default email HTML template from
	 * Apache folder 2.Create email body 3.Send email to the lab owner.
	 */
	private void sendEmailToLabOwner(String emailId, String subject, LabDTO lab) {

		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netlimsServerIpAddress
					+ "/youNeverWait/EmailFormat/LabRegistration.html");
			msgBody = createDefaultEmailBody(url, lab);
			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody, emailId,
					mailFrom, 0, 0, null,
					SendMsgCallbackEnum.LAB_REGISTRATION.getId(), null);
			mailThread.addSendMsgObj(obj);
		} catch (IOException e) {
			log.error(
					"Error while sending lab registration email to the owner's email id ",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * Method to create email body
	 */
	private String createDefaultEmailBody(URL url, LabDTO lab)
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
				lab.getOwnerFirstName());
		if (lab.getOwnerLastName() == null) {
			fullMsgBody = fullMsgBody.replace("{lastName}", "");
		} else {
			fullMsgBody = fullMsgBody.replace("{lastName}",
					lab.getOwnerLastName());
		}
		fullMsgBody = fullMsgBody.replace("{labName}", lab.getName());
		fullMsgBody = fullMsgBody.replace("{userid}", lab.getUserName());
		fullMsgBody = fullMsgBody.replace("{password}", lab.getPassword());
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				netlimsServerIpAddress);

		return fullMsgBody;
	}

	/**
	 * Update a Lab
	 * 
	 * @param lab
	 * @return ResponseDTO
	 */

	@Override
	public ResponseDTO update(LabDTO lab) {

		ResponseDTO response = new ResponseDTO();
		validator.validateNetLimsAccount(lab);
		response = labDao.update(lab);
		return response;
	}

	/**
	 * Delete a Lab
	 * 
	 * @param lab
	 * @return ResponseDTO
	 */

	@Override
	public ResponseDTO delete(int labId) {

		ResponseDTO response = new ResponseDTO();
		if (labId <= 0) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(labId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response = labDao.delete(labId);
		return response;
	}

	/**
	 * View a Lab
	 * 
	 * @param labId
	 * @return LabResponseDTO
	 */

	@Override
	public LabResponseDTO view(int labId) {

		if (labId <= 0) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(labId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		LabResponseDTO lab = labDao.view(labId);

		return lab;
	}

	/**
	 * Creates a branch for a lab
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */

	@Override
	public ResponseDTO createBranch(LabBranchDTO branch) {

		ResponseDTO response = new ResponseDTO();
		validator.validateBranchDetails(branch);
		response = labDao.createBranch(branch);
		BranchOwnerDetails branchDetail = labDao.getBranchOwners(response
				.getGlobalId());
		sendEmailToLabBranchOwner(Constants.BRANCH_CREATION, branchDetail);
		return response;
	}

	/**
	 * Method to send branch registration email to the owner's email id It will
	 * perform the following operations. 1.Take default email HTML template from
	 * Apache folder 2.Create email body 3.Send email to the lab owner.
	 */
	private void sendEmailToLabBranchOwner(String subject,
			BranchOwnerDetails branchDetail) {

		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netlimsServerIpAddress
					+ "/youNeverWait/EmailFormat/LabBranchRegistration.html");
			msgBody = createEmailBody(url, branchDetail);
			// EmailSender.sendEmail(branchDetail.getOwnerEmail(), mailFrom,
			// subject, msgBody);
			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody,
					branchDetail.getOwnerEmail(), mailFrom, 0, 0, null,
					SendMsgCallbackEnum.LAB_BRANCH_REGISTRATION.getId(), null);
			mailThread.addSendMsgObj(obj);
		} catch (IOException e) {
			log.error("Error while sending  branch registration email to the owner's email id"
					+ e);
			e.printStackTrace();
		}
	}

	/**
	 * Method to create email body
	 */
	private String createEmailBody(URL url, BranchOwnerDetails branchDetail)
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
		fullMsgBody = fullMsgBody.replace("{labName}",
				branchDetail.getLabName());
		fullMsgBody = fullMsgBody.replace("{branchName}",
				branchDetail.getBranchName());
		fullMsgBody = fullMsgBody.replace("{passPhrase}",
				branchDetail.getPassPhrase());
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				netlimsServerIpAddress);

		return fullMsgBody;
	}

	/**
	 * Updates details of a lab branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */

	@Override
	public ResponseDTO updateBranch(LabBranchDTO branch) {

		ResponseDTO response = new ResponseDTO();
		validator.validateBranchDetails(branch);
		validator.validateBranchId(branch);
		response = labDao.updateBranch(branch);
		return response;
	}

	/**
	 * View details of a lab branch
	 * 
	 * @param globalId
	 * @return BranchResponseDTO
	 */

	@Override
	public LabBranchResponseDTO viewBranch(int globalId) {

		LabBranchResponseDTO response = new LabBranchResponseDTO();
		response = labDao.viewBranch(globalId);
		return response;
	}

	/**
	 * Deletes a branch of a lab
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */

	@Override
	public ResponseDTO deleteBranch(LabBranchDTO branch) {

		ResponseDTO response = new ResponseDTO();
		validator.validateBranchId(branch);
		response = labDao.deleteBranch(branch);
		return response;
	}

	/**
	 * Shows a list of all lab branches
	 * 
	 * @param filter
	 * @return BranchListResponseDTO
	 */

	@Override
	public BranchListResponseDTO branchList(FilterDTO filterDTO) {

		BranchListResponseDTO response = new BranchListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,
		// return result with appropriate error code
		ErrorDTO error = validator.validateBranchFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for lab from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.BRANCH);
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
		TypedQuery<LabBranchTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());

		Long count = queryBuilder.getCount();
		System.out.println("queryBuilder.getCount():" + count);
		// execute query
		List<LabBranchTbl> branches = queryBuilder.executeQuery(q);
		response = getBranchList(branches);
		response.setCount(count);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Get Branch list
	 * 
	 * @param branches
	 * @return BranchListResponseDTO
	 */
	private BranchListResponseDTO getBranchList(List<LabBranchTbl> branches) {
		BranchListResponseDTO response = new BranchListResponseDTO();
		if (branches == null) {
			return response;
		}
		List<BranchDetail> branchDetails = new ArrayList<BranchDetail>();
		for (LabBranchTbl branchTbl : branches) {

			branchDetails.add(new BranchDetail(branchTbl));
		}
		response.setBranch(branchDetails);
		return response;
	}

	/**
	 * Transfer result to NetMd
	 * 
	 * @param resultTranfer
	 * @return ResultTransferResponseDTO
	 */
	@Override
	public ResultTransferResponseDTO transferResultToNetMd(
			TransferNetMdResultDTO resultTranfer) {
		validator.validateNetMdResultDetails(resultTranfer);
		ResultTransferResponseDTO response = labDao
				.transferResultToNetMd(resultTranfer);
		return response;

	}

	/**
	 * TransferResult from one lab branch to other
	 * 
	 * @param ResultTransferDTO
	 * @return ResultTransferResponseDTO
	 */

	@Override
	public ResultTransferResponseDTO transferResult(
			ResultTransferDTO resultTranferDto) {

		validator.validateResultDetails(resultTranferDto);
		ResultTransferResponseDTO response = labDao
				.transferResult(resultTranferDto);
		return response;
	}

	/**
	 * Retrieve results from a branch
	 * 
	 * @param ResultTransferDTO
	 * @return ResultTransferResponseDTO
	 */
	@Override
	public ResultRetrievalResponseDTO getResult(HeaderDTO header,
			String lastSyncTime, Date currentTime) {
		// validator.validateLabDetails(resultRetrievalDTO);
		ResultRetrievalResponseDTO response = labDao.getResult(header,
				lastSyncTime, currentTime);
		return response;
	}

	/**
	 * Retrieves all lab branch list after last synchronization time
	 * 
	 * @param labBranchRetrievalDTO
	 * @return LabBranchListResponseDTO
	 */
	@Override
	public LabBranchListResponseDTO retrieveLabBranchList(HeaderDTO header,
			String lastSyncTime, Date currentTime) {
		LabBranchListResponseDTO response = labDao.retrieveLabBranchList(
				header, lastSyncTime, currentTime);
		return response;
	}

	/**
	 * Retrieves all lab list after last synchronization time
	 * 
	 * @param labRetrievalDTO
	 * @return RetrieveLabListResponseDTO
	 */
	@Override
	public RetrieveLabListResponseDTO retrieveLabList(HeaderDTO header,
			String lastSyncTm, Date currentTime) {

		RetrieveLabListResponseDTO response = labDao.retrieveLabList(header,
				lastSyncTm, currentTime);
		return response;
	}

	/**
	 * Retrieves all user list after last synchronization time
	 * 
	 * @param userRetrievalDTO
	 * @return RetrieveUserListResponseDTO
	 */
	@Override
	public RetrieveUserListResponseDTO retrieveUserList(HeaderDTO header,
			String lastSyncTime, Date currentTime) {

		RetrieveUserListResponseDTO response = labDao.retrieveUserList(header,
				lastSyncTime, currentTime);
		return response;
	}

	/**
	 * Retrieves all Netmd list after last synchronization time
	 * 
	 * @param labRetrievalDTO
	 * @return RetrieveNetmdListResponseDTO
	 */
	@Override
	public RetrieveNetmdListResponseDTO retrieveNetmdList(HeaderDTO header,
			String lastSyncTime, Date currentTime) {

		labDao.CheckHeaderDetails(header);
		RetrieveNetmdListResponseDTO response = netMdService.retrieveNetmdList(
				lastSyncTime, currentTime);
		return response;
	}

	/**
	 * Retrieves all Netmd branch list after last synchronization time
	 * 
	 * @param labRetrievalDTO
	 * @return RetrieveNetmdBranchListResponseDTO
	 */
	@Override
	public RetrieveNetmdBranchListResponseDTO retrieveNetmdBranchList(
			HeaderDTO header, String lastSyncTime, Date currentTime) {

		labDao.CheckHeaderDetails(header);
		RetrieveNetmdBranchListResponseDTO response = netMdService
				.retrieveNetmdBranchList(lastSyncTime, currentTime);
		return response;
	}

	/**
	 * Shows a list of all Netlims App
	 * 
	 * @param filter
	 * @return LabListResponseDTO
	 */
	@Override
	public LabListResponseDTO list(FilterDTO filterDTO) {

		LabListResponseDTO response = new LabListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,
		// return result with appropriate error code
		ErrorDTO error = validator.validateLabFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for lab from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.LAB);
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
		TypedQuery<LabTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());

		Long count = queryBuilder.getCount();
		System.out.println("queryBuilder.getCount():" + count);
		// execute query
		List<LabTbl> labs = queryBuilder.executeQuery(q);
		response = getLabList(labs);
		response.setCount(count);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Get Lab list
	 * 
	 * @param labs
	 * @return LabListResponseDTO
	 */
	private LabListResponseDTO getLabList(List<LabTbl> labs) {

		LabListResponseDTO response = new LabListResponseDTO();
		if (labs == null) {
			return response;
		}
		List<LabDetail> labDetails = new ArrayList<LabDetail>();
		for (LabTbl labTbl : labs) {
			labDetails.add(new LabDetail(labTbl));
		}
		response.setLab(labDetails);
		return response;
	}
	
	/**
	 * Shows a list of facilities
	 * 
	 * @param filter
	 * @return FacilityListResponseDTO
	 */

/*	@Override
	public BranchFacilityListResponseDTO listFacility(int branchId) {

		BranchFacilityListResponseDTO response = new BranchFacilityListResponseDTO();
		response = labDao.listFacility(branchId);
		return response;
	}*/
	/**
	 * To show all the total orders and its related details of each branch in
	 * the lab
	 * 
	 * @param globalId
	 * @return BranchOrdersResponseDTO
	 */
	@Override
	public BranchOrdersResponseDTO viewBranchOrders(int globalId) {
		BranchOrdersResponseDTO response = new BranchOrdersResponseDTO();
		if (globalId <= 0) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response = labDao.viewBranchOrders(globalId);
		return response;
	}

	/**
	 * Show the list of orders for a given period
	 */
	@Override
	public BranchOrdersResponseDTO orderList(FilterDTO filterDTO) {
		BranchOrdersResponseDTO response = new BranchOrdersResponseDTO();
		// validate filterDTO to identify invalid expressions and if there is
		// any,return result with appropriate error code
		ErrorDTO error = validator.validateOrderFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for netmd branch from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.NETLIMS_ORDER);
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
		TypedQuery<OrderAmountTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());

		// get count
		Long count = queryBuilder.getCount();

		// execute query
		List<OrderAmountTbl> orderAmountList = queryBuilder.executeQuery(q);
		response = getNetLimsOrderList(orderAmountList);
		response.setCount(count);
		response.setSuccess(true);
		return response;
	}


	/**
	 * @param orderAmountList
	 * @return
	 */
	private BranchOrdersResponseDTO getNetLimsOrderList(List<OrderAmountTbl> orderAmountList) {
		BranchOrdersResponseDTO response = new BranchOrdersResponseDTO();
		if (orderAmountList == null) {
			return response;
		}

		List<BranchOrderDetail> labOrderList = new ArrayList<BranchOrderDetail>();
		for (OrderAmountTbl labOrder : orderAmountList) {
			labOrderList.add(new BranchOrderDetail(labOrder));
		}
		response.setBranchOrders(labOrderList);
		return response; 
	}

	/**
	 * Create total orders in a branch
	 */
	@Override
	public BranchOrderCountResponseDTO createTotalOrders(HeaderDTO header,
			BranchOrderDetail branchOrders) {
		BranchOrderCountResponseDTO response = new BranchOrderCountResponseDTO();
		validator.validateHeaderDetails(header);
		validator.validateLabBranchIds(header.getHeadOfficeId(),
				header.getBranchId());
		if (branchOrders != null) {
			validator.validateOrderDetails(branchOrders);
			response = labDao.createTotalOrders(header, branchOrders);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.security.bl.service.AuthenticationService#
	 * getHealthMonitor()
	 */
	@Override
	public HealthMonitorResponse checkSystemHealth(
			SystemHealthDetails systemHealthDetails) {
		HealthMonitorResponse response = new HealthMonitorResponse();
		/** Validates pssphrase and mac id empty or not **/
		validator.validateHeaderDetails(systemHealthDetails.getHeader());

		/** Validates lab and branch ids **/
		validator.validateLabBranchIds(systemHealthDetails.getHeader()
				.getHeadOfficeId(), systemHealthDetails.getHeader()
				.getBranchId());

		/** Checking header details whether given correctly or not **/
		labDao.CheckHeaderDetails(systemHealthDetails.getHeader());

		/** Calling method for checking system in critical stage or not **/
		SystemHealthResponse healthMonitorResponse = healthService
				.checkSystemHealth(systemHealthDetails);
		if (healthMonitorResponse.getSystemHealth().isCritical()) {
			/* Getting branch owner details and sending mail */
			BranchOwnerDetails branchOwnerDetails = labDao
					.getBranchOwners(systemHealthDetails.getHeader()
							.getBranchId());
			sendEmailToLabOwner(branchOwnerDetails,
					Constants.LAB_SYSTEM_FAILURE, healthMonitorResponse,
					systemHealthDetails);

		}
		response.setFreqPeriod(healthMonitorResponse.getSystemHealth()
				.getFreqPeriod());
		response.setIntervalTime(healthMonitorResponse.getSystemHealth()
				.getIntervalTime());
		response.setCritical(healthMonitorResponse.getSystemHealth()
				.isCritical());
		response.setSuccess(true);
		return response;
	}

	/**
	 * @param branchOwnerDetails
	 * @param labSystemFailure
	 * @param response
	 * @param systemHealthDetails
	 */
	private void sendEmailToLabOwner(BranchOwnerDetails branchOwnerDetails,
			String subject, SystemHealthResponse response,
			SystemHealthDetails systemHealthDetails) {
		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netlimsServerIpAddress
					+ "/youNeverWait/EmailFormat/LabBranchHealthMonitor.html");
			msgBody = createEmailBody(url, branchOwnerDetails, response,
					systemHealthDetails);
			// EmailSender.sendEmail(branchDetail.getOwnerEmail(), mailFrom,
			// subject, msgBody);
			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody,
					branchOwnerDetails.getOwnerEmail(), mailFrom, 0, 0, null,
					SendMsgCallbackEnum.LAB_FAILURE_ALERT.getId(), null);
			mailThread.addSendMsgObj(obj);
		} catch (IOException e) {
			log.error("Error while sending  branch registration email to the owner's email id"
					+ e);
			e.printStackTrace();
		}

	}

	/**
	 * @param url
	 * @param branchOwnerDetails
	 * @param response
	 *            .getIntervalTime()
	 * @param systemHealthDetails
	 * @return
	 */
	private String createEmailBody(URL url,
			BranchOwnerDetails branchOwnerDetails,
			SystemHealthResponse response,
			SystemHealthDetails systemHealthDetails) throws IOException {
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
				branchOwnerDetails.getOwnerFirstName());
		if (branchOwnerDetails.getOwnerLastName() == null) {
			fullMsgBody = fullMsgBody.replace("{lastName}", "");
		} else {
			fullMsgBody = fullMsgBody.replace("{lastName}",
					branchOwnerDetails.getOwnerLastName());
		}
		fullMsgBody = fullMsgBody.replace("{labName}",
				branchOwnerDetails.getLabName());
		fullMsgBody = fullMsgBody.replace("{branchName}",
				branchOwnerDetails.getBranchName());
		fullMsgBody = fullMsgBody.replace("{hardDiskSpace}",
				Long.toString(response.getFreeHardDiskSpaceInPercent()));
		fullMsgBody = fullMsgBody.replace("{memoryDiskSpace}",
				Long.toString(response.getFreeMemorySpaceInPercent()));
		fullMsgBody = fullMsgBody.replace("{cpuUsage}",
				Long.toString(response.getFreeCpuSpaceInPercent()));
		fullMsgBody = fullMsgBody.replace("{intervalTime}", response
				.getSystemHealth().getIntervalTime());
		fullMsgBody = fullMsgBody.replace("{frequencyPeriod}", response
				.getSystemHealth().getFreqPeriod());
		return fullMsgBody;
	}

	/**
	 * Method for viewing branch default system details
	 * 
	 * @param branchId
	 * @return BranchSystemInfoDetails
	 */
	@Override
	public BranchSystemInfoDetails viewBranchSystemInfoDetails(int branchId) {
		if (branchId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branchId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		BranchSystemInfoDetails response = labDao
				.viewBranchSystemInfoDetails(branchId);
		return response;
	}

	/**
	 * Method for updating the branch default system details
	 * 
	 * @param details
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO updateLabBranchSystemInfo(BranchSystemInfoDetails details) {
		validator.validateSystemDefaultDetails(details);
		ResponseDTO response = labDao.updateLabBranchSystemInfo(details);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.bl.service.LabService#retrieveBranchOrders(com
	 * .nv.youNeverWait.rs.dto.LabHeaderDTO, java.lang.String, java.util.Date)
	 */
	@Override
	public OrderDetails retrieveBranchOrders(LabOrderHeaderDTO orderHeader) {
		validator.validateHeaderDetails(orderHeader.getHeader());
		validator.validateLabBranchIds(orderHeader.getHeader().getHeadOfficeId(),
				orderHeader.getHeader().getBranchId());
		DateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);

		/*Setting last sync time when the syncData calling for the first time*/
		if(orderHeader.getLastOrderSyncTime()==null){
			orderHeader.setLastOrderSyncTime(df.format(new Date(0)));

		}
		Date currentSyncTime= new Date();
		OrderDetails orderDetail = orderService.retrieveBranchOrders(orderHeader.getHeader(),
				orderHeader.getLastOrderSyncTime(),currentSyncTime);
		OrderTestResultList testResults=resultService.retrieveResults(orderHeader.getHeader(),orderHeader.getLastOrderSyncTime(),currentSyncTime);
		orderDetail.setOrderTestResult(testResults.getOrderTestResultList());
		orderDetail.setSuccess(true);

		return orderDetail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.bl.service.LabService#transferOrder(com.nv.
	 * youNeverWait.rs.dto.OrderTransfer)
	 */
	@Override
	public ResponseDTO transferOrder(OrderTransfer orderTranfer) {

		validator.validateHeaderDetails(orderTranfer.getHeader());
		validator.validateLabBranchIds(orderTranfer.getHeader()
				.getHeadOfficeId(), orderTranfer.getHeader().getBranchId());
		ResponseDTO response = orderService.transferOrder(orderTranfer);
		return response;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.bl.service.LabService#getLab(com.nv.youNeverWait
	 * .rs.dto.LabHeaderDTO, java.lang.String, java.util.Date)
	 */
	@Override
	public LabDTO getLab(HeaderDTO header, String lastSyncTime,
			Date currentSyncTime) {
		LabDTO labDetails = labDao
				.getLab(header, lastSyncTime, currentSyncTime);
		return labDetails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.bl.service.LabService#setBranchSync(com.nv.
	 * youNeverWait.rs.dto.SyncFreqDTO)
	 */
	@Override
	public SyncFreqResponseDTO setBranchSync(SyncFreqDTO sync) {
		if (sync.getLabBranchId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		SyncFreqResponseDTO response = labDao.setBranchSync(sync);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.bl.service.LabService#setLabSync(com.nv.youNeverWait
	 * .rs.dto.SyncFreqDTO)
	 */
	@Override
	public SyncFreqResponseDTO setLabSync(SyncFreqDTO sync) {
		if (sync.getLabId() <= 0) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(sync
					.getLabId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		SyncFreqResponseDTO response = labDao.setLabSync(sync);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.bl.service.LabService#getLabSyncDetails(int)
	 */
	@Override
	public SyncFreqDTO getLabSyncDetails(int labId) {
		if (labId <= 0) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(labId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		SyncFreqDTO response = labDao.getLabSyncDetails(labId);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.bl.service.LabService#getBranchSyncDetails(int)
	 */
	@Override
	public SyncFreqDTO getBranchSyncDetails(int branchId) {
		if (branchId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		SyncFreqDTO response = labDao.getBranchSyncDetails(branchId);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.bl.service.LabService#syncEnableStatus(com.nv
	 * .youNeverWait.rs.dto.LabHeaderDTO, java.lang.String, int)
	 */
	@Override
	public SyncFreqDTO syncEnableStatus(HeaderDTO header, String freqType,
			int interval) {
		validator.validateSyncDetail(freqType, interval);
		/* Getting frequency details from table */
		SyncFreqDTO syncFreqDetails = labDao.getBranchSyncDetails(header
				.getBranchId());
		return syncFreqDetails;
	}

	@Override
	public ResponseDTO saveResult(LabResultHeaderDTO labResultHeader) {
		validator.validateLabResultDetails(labResultHeader);
		ResponseDTO response = labDao
				.saveResult(labResultHeader);
		return response;
	}

	@Override
	public TransferredDetails getTransferredOrders(FilterDTO filterDTO) {
		TransferredDetails response = new TransferredDetails();
		// validate filterDTO to identify invalid expressions and if there is
		// any,return result with appropriate error code
		ErrorDTO error = validator.validateTransferredOrderFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for netlims branch from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.TRANSFERRED_ORDER);
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
		TypedQuery<OrderBranchTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());

		// get count
		Long count = queryBuilder.getCount();

		// execute query
		List<OrderBranchTbl> transferredOrderList = queryBuilder.executeQuery(q);
		response = getNetLimsTransferredOrderList(transferredOrderList);
		response.setCount(count);
		response.setSuccess(true);
		return response;
	}

	private TransferredDetails getNetLimsTransferredOrderList(
			List<OrderBranchTbl> transferredOrderList) {
		TransferredDetails response = new TransferredDetails();
		if (transferredOrderList == null) {
			return response;
		}

		List<TransferredOrders> labOrderList = new ArrayList<TransferredOrders>();
		for (OrderBranchTbl labTransferredOrder : transferredOrderList) {
			TransferredOrders trsnferredOrder= new TransferredOrders();

			List<OrderTransferTbl> orderTrasnferTblList=labDao.getDestinationBranches(labTransferredOrder.getId());
			List<OrderDestinationBranchDTO> destinationBranches= new ArrayList<OrderDestinationBranchDTO>();
			for(OrderTransferTbl orderTransferTbl:orderTrasnferTblList){
				OrderDestinationBranchDTO destbranch= new OrderDestinationBranchDTO();
				destbranch.setDestinationBranch(orderTransferTbl.getLabBranchTbl().getName());
				destbranch.setOrderSent(orderTransferTbl.isSent());
				destinationBranches.add(destbranch);
			}
			trsnferredOrder.setFromBranch(labTransferredOrder.getLabBranchTbl().getName());
			trsnferredOrder.setDestinationBranches(destinationBranches);
			trsnferredOrder.setOrderUid(labTransferredOrder.getOrderUid());
			labOrderList.add(trsnferredOrder);
		}
		response.setTransferreddetails(labOrderList);
		return response; 
	}

	@Override
	public TransferredResultDetails getTransferredResults(FilterDTO filterDTO) {
		TransferredResultDetails response = new TransferredResultDetails();
		// validate filterDTO to identify invalid expressions and if there is
		// any,return result with appropriate error code
		ErrorDTO error = validator.validateTransferredResultFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for netlims branch from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.TRANSFERRED_RESULTS);
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
		TypedQuery<OrderResultTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());

		// get count
		Long count = queryBuilder.getCount();

		// execute query
		List<OrderResultTbl> transferredResultList = queryBuilder.executeQuery(q);
		response = getNetLimsTransferredResultList(transferredResultList);
		response.setCount(count);
		response.setSuccess(true);
		return response;
	}


	private TransferredResultDetails getNetLimsTransferredResultList(
			List<OrderResultTbl> transferredResultList) {
		TransferredResultDetails response = new TransferredResultDetails();
		if (transferredResultList == null) {
			return response;
		}

		List<TransferredResults> transferreddetails= new ArrayList<TransferredResults>();
		for (OrderResultTbl orderResultTbl : transferredResultList) {
			TransferredResults trsnferredResult= new TransferredResults();
			trsnferredResult.setFromBranch(orderResultTbl.getLabTbl().getName());
			trsnferredResult.setToBracnh(orderResultTbl.getOwnerLabBranchTbl().getName());
			trsnferredResult.setOrderUid(orderResultTbl.getOrderBranchTbl().getOrderUid());
			trsnferredResult.setTestUId(orderResultTbl.getTestUid());
			trsnferredResult.setSent(orderResultTbl.isSent());
			transferreddetails.add(trsnferredResult);
		}
		response.setTransferreddetails(transferreddetails);
		return response; 
	}

	@Override
	public ResponseDTO sendMail(MailTransferInfo mailResult) {
		ResponseDTO response=new ResponseDTO();
		ByteArrayInputStream body = null;
		try {
			body = new ByteArrayInputStream(Base64.decodeBase64(mailResult.getBody()));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		ByteArrayDataSource bads = null;
		try {
			bads = new ByteArrayDataSource(body, "multipart/mixed; boundary=null");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Multipart multipart = null;
		try {
			multipart = new MimeMultipart(bads);
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		try {
			SendMailMsgObj obj = new SendMailMsgObj(mailResult.getSubject(),mailResult.getFrom(),mailResult.getTo(),multipart,SendMsgCallbackEnum.LAB_USER_REGISTRATION.getId());
			mailThread.addSendMsgObj(obj);
		} catch (Exception e) {
			log.error("Error while sending Email to admin", e);
			e.printStackTrace();
			response.setSuccess(false);
		}
		response.setSuccess(true);
		return response;
	}

	/**
	 * @return the labDao
	 */
	public LabDao getLabDao() {
		return labDao;
	}

	/**
	 * @param labDao
	 *            the labDao to set
	 */
	public void setLabDao(LabDao labDao) {
		this.labDao = labDao;
	}

	/**
	 * @return the validator
	 */
	public LabValidator getValidator() {
		return validator;
	}

	/**
	 * @param validator
	 *            the validator to set
	 */
	public void setValidator(LabValidator validator) {
		this.validator = validator;
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
	 * @return the netMdService
	 */
	public NetMdService getNetMdService() {
		return netMdService;
	}

	/**
	 * @param netMdService
	 *            the netMdService to set
	 */
	public void setNetMdService(NetMdService netMdService) {
		this.netMdService = netMdService;
	}

	/**
	 * @return the netlimsServerIpAddress
	 */
	public String getNetlimsServerIpAddress() {
		return netlimsServerIpAddress;
	}

	/**
	 * @param netlimsServerIpAddress
	 *            the netlimsServerIpAddress to set
	 */
	public void setNetlimsServerIpAddress(String netlimsServerIpAddress) {
		this.netlimsServerIpAddress = netlimsServerIpAddress;
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

	/**
	 * @return the orderService
	 */
	public OrderService getOrderService() {
		return orderService;
	}

	/**
	 * @param orderService
	 *            the orderService to set
	 */
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * @return the healthService
	 */
	public HealthMonitorService getHealthService() {
		return healthService;
	}

	/**
	 * @param healthService
	 *            the healthService to set
	 */
	public void setHealthService(HealthMonitorService healthService) {
		this.healthService = healthService;
	}

	/**
	 * @return ResultService
	 */
	public ResultService getResultService() {
		return resultService;
	}

	/**
	 * @param resultService
	 */
	public void setResultService(ResultService resultService) {
		this.resultService = resultService;
	}

	@Override
	public int processUser(SyncUser user, Integer source_branch_id) {
		int userId=0;
		if(user.getGlobalId()==0) {
			userId = create(user, source_branch_id);
		} else 
			userId = labDao.updateBranchUser(user, source_branch_id);	
		return userId;
	}

	private int create(SyncUser user, Integer source_branch_id) {
		int userId =  labDao.createBranchUser(user, source_branch_id);
		return userId;
	}

	@Override
	public UserInfo getUserByReferredUid(int referredUid, int branchId) {
		if(referredUid!=0)
			return labDao.getUserByReferredUid(referredUid, branchId);
		return null;
	}

	@Override
	public ListResponse getFacilityByFilter(
			FilterDTO filterDTO, User user) {
		return labDao.getFacilityByFilter(filterDTO, user);
	}

	/**
	 * @return the facilityService
	 */
	public FacilityService getFacilityService() {
		return facilityService;
	}

	/**
	 * @param facilityService the facilityService to set
	 */
	public void setFacilityService(FacilityService facilityService) {
		this.facilityService = facilityService;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.LabService#resetFacilityPassword(com.nv.youNeverWait.rs.dto.LoginDTO)
	 */
	@Override
	public ResponseDTO resetFacilityPassword(LoginDTO login) {
		return facilityService.resetPassword(login);
	}
	
	
	@Override
	public PublicKeyDTO getPublicKey(int branchId){
		if (branchId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		PublicKeyDTO dto  = publicKey.keyGenerator(branchId);
		return dto;
		
	}

	@Override
	public HealthCareDetailDTO getBranchDetail(String pKey) {
		
		HealthCareDetailDTO dto = publicKey.receiveBranchDetail(pKey);
		return dto;
	}
	
}
