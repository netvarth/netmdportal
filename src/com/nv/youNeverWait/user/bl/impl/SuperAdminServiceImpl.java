/**
 * SuperAdminServiceImpl.java
 *
 * Jan 2, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.bl.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;

import com.nv.framework.sendmsg.SendEmailMsgWorkerThread;
import com.nv.framework.sendmsg.SendMsgCallbackEnum;
import com.nv.framework.sendmsg.email.SendMailMsgObj;
import com.nv.framework.util.text.StringEncoder;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.LogTbl;
import com.nv.youNeverWait.pl.entity.SyncLogTbl;
import com.nv.youNeverWait.rs.dto.BranchBillListResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchOrdersResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchSystemInfoDetails;
import com.nv.youNeverWait.rs.dto.EnableLogStatusResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LabBranchDTO;
import com.nv.youNeverWait.rs.dto.LabBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.LabDTO;
import com.nv.youNeverWait.rs.dto.LabListResponseDTO;
import com.nv.youNeverWait.rs.dto.LabResponseDTO;
import com.nv.youNeverWait.rs.dto.LogDTO;
import com.nv.youNeverWait.rs.dto.LogDetail;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdDTO;
import com.nv.youNeverWait.rs.dto.NetMdListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDTO;
import com.nv.youNeverWait.rs.dto.NetMdViewResponseDTO;
import com.nv.youNeverWait.rs.dto.NetPosDTO;
import com.nv.youNeverWait.rs.dto.NetPosListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetPosViewResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchDetail;
import com.nv.youNeverWait.rs.dto.NetRxBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxDTO;
import com.nv.youNeverWait.rs.dto.NetRxListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxViewResponseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.SpecimenListResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncLogDTO;
import com.nv.youNeverWait.rs.dto.SyncLogDetail;
import com.nv.youNeverWait.rs.dto.SyncLogListResponseDTO;
import com.nv.youNeverWait.rs.dto.TestListResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.rs.dto.UserDetails;
import com.nv.youNeverWait.rs.dto.UserLogListResponseDTO;
import com.nv.youNeverWait.user.bl.service.LabService;
import com.nv.youNeverWait.user.bl.service.NetMdService;
import com.nv.youNeverWait.user.bl.service.NetPosService;
import com.nv.youNeverWait.user.bl.service.NetRxService;
import com.nv.youNeverWait.user.bl.service.SpecimenService;
import com.nv.youNeverWait.user.bl.service.SuperAdminService;
import com.nv.youNeverWait.user.bl.service.TestService;
import com.nv.youNeverWait.user.bl.validation.SuperAdminValidator;
import com.nv.youNeverWait.user.pl.dao.SuperAdminDao;
import com.nv.youNeverWait.util.filter.core.Filter;
import com.nv.youNeverWait.util.filter.core.FilterFactory;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;
import com.nv.youNeverWait.util.filter.core.QueryBuilderFactory;

public class SuperAdminServiceImpl implements SuperAdminService {
	private NetMdService netMdService;
	private LabService labService;
	private SuperAdminDao superAdminDao;
	private SuperAdminValidator validator;
	private String netlimsServerIpAddress;
	private String mailFrom;
	private SendEmailMsgWorkerThread mailThread;
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	private NetRxService netRxService;
	private TestService testService;
	private SpecimenService specimenService;
	private NetPosService netPosService;
	private static final Log log = LogFactory.getLog(SuperAdminServiceImpl.class);

	/**
	 * Shows a list of all Netlims App
	 * 
	 * @param filter
	 * @return LabListResponseDTO
	 */
	@Override
	public LabListResponseDTO labList(FilterDTO filterDTO) {

		LabListResponseDTO response = labService.list(filterDTO);
		return response;
	}

	/**
	 * Make a device primary
	 * 
	 * @param header
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO makePrimary(HeaderDTO header) {
		ResponseDTO response = netMdService.makePrimary(header);
		return response;
	}

	/**
	 * Creates a branch for a lab
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */

	@Override
	public ResponseDTO createBranch(LabBranchDTO branch) {

		ResponseDTO response = labService.createBranch(branch);
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

		LabBranchResponseDTO response = labService.viewBranch(globalId);
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
		ResponseDTO response = labService.deleteBranch(branch);
		return response;
	}

	/**
	 * Updates details of a lab branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */

	@Override
	public ResponseDTO updateBranch(LabBranchDTO branch) {

		ResponseDTO response = labService.updateBranch(branch);
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

		BranchListResponseDTO response = labService.branchList(filterDTO);
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

		validator.validateLogin(login);
		ResponseDTO response = superAdminDao.resetPassword(login);
		return response;
	}

	/**
	 * To reset password if superAdmin forgot his password
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO forgotPassword(LoginDTO login) {
		ResponseDTO response = new ResponseDTO();
		if (login.getUserName() == null || login.getUserName().equals("")) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		UserCredentials user = 

superAdminDao.getUserCredentials(login);
		if (user.getEmailId() == null || user.getEmailId().equals("")) 

{
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidMailId);
			se.setDisplayErrMsg(true);
			throw se;
		}

		sendEmailForResetPassword(Constants.RESET_PASSWORD, user);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method to send email for resetting password.It will perform the 

following
	 * operations. 1.Take default email HTML template from Apache folder
	 * 2.Create email body 3.Send email to the superAdmin.
	 */
	private void sendEmailForResetPassword(String subject, UserCredentials user) {

		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netlimsServerIpAddress +  "/youNeverWait/EmailFormat/ForgotPassword.html");
			msgBody = createDefaultEmailBody(url, user);
			// EmailSender.sendEmail(emailId, mailFrom, subject, msgBody);
			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody,user.getEmailId(), mailFrom, 0, 0, null,SendMsgCallbackEnum.SUPERADMIN_RESET_PWD.getId(), null);
			mailThread.addSendMsgObj(obj);
		} catch (IOException e) {
			log.error("Error while sending forgot password mail to SuperAdmin ",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * Method to create email body for Reset password
	 */
	private String createDefaultEmailBody(URL url, UserCredentials user)throws IOException {

		StringBuffer msgBodyBfr = new StringBuffer();
		String fullMsgBody = "";
		String encryptedUserName = 

StringEncoder.encryptWithStaticKey(user.getUserName());
		String resetPasswordLink = "http://" + netlimsServerIpAddress+ "/youNeverWait/EmailFormat/ResetPassword.html?userName=" + encryptedUserName;
		java.net.URLConnection openConnection = url.openConnection();
		InputStream inputStream = openConnection.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String readLine = "";
		while ((readLine = in.readLine()) != null) {msgBodyBfr.append(readLine).append("\n");
		}
		in.close();
		fullMsgBody = msgBodyBfr.toString();
		if (user.getFirstName() != null && !user.getFirstName().equals("")) {
			fullMsgBody = fullMsgBody.replace("{firstname}",user.getFirstName());
		} else {
			fullMsgBody = fullMsgBody.replace("{firstname}", "admin,");
		}

		fullMsgBody = fullMsgBody.replace("{ResetLink}",resetPasswordLink);
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",netlimsServerIpAddress);

		return fullMsgBody;
	}

	/**
	 * Method which performs password changing
	 * 
	 * @param passwords
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO changePassword(@RequestBody PasswordDTO passwords) 

{
		validator.validatePasswords(passwords);
		ResponseDTO response = superAdminDao.changePassword(passwords);
		return response;
	}

	/**
	 * Create netmd account
	 * 
	 * @param netMd
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO createNetMd(NetMdDTO netMd) {
		ResponseDTO response = netMdService.createNetMd(netMd);
		return response;
	}

	/**
	 * Create netPos account
	 * 
	 * @param netpos
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO createNetPos(NetPosDTO netPos) {
		ResponseDTO response = netPosService.createNetPos(netPos);
 		return response;
	}
	
	/**
	 * Update netmd account
	 * 
	 * @param netMd
	 * @return ResponseDTO
	 */

	@Override
	public ResponseDTO updateNetMd(NetMdDTO netMd) {
		ResponseDTO response = netMdService.updateNetMd(netMd);
		return response;
	}
	
	
	/**
	 * Method to delete a NetMd account
	 */

	@Override
	public ResponseDTO deleteNetMd(int netMdId) {
		ResponseDTO response = netMdService.deleteNetMd(netMdId);
		return response;
	}
	
	/**
	 * Method to delete a NetPos account
	 */

	@Override
	public ResponseDTO deleteNetPos(int netPosId) {
		ResponseDTO response = netPosService.deleteNetPos(netPosId);
		return response;
	}

	/**
	 * Method to create a lab
	 */

	@Override
	public ResponseDTO createLab(LabDTO lab) {
		ResponseDTO response = labService.create(lab);
		return response;
	}

	/**
	 * Update a lab
	 */

	@Override
	public ResponseDTO updateLab(LabDTO lab) {
		ResponseDTO response = labService.update(lab);
		return response;
	}

	/**
	 * Method to delete a lab
	 */

	@Override
	public ResponseDTO deleteLab(int labId) {
		ResponseDTO response = labService.delete(labId);
		return response;
	}

	/**
	 * Method to view a lab
	 */

	@Override
	public LabResponseDTO viewLab(int labId) {
		LabResponseDTO response = labService.view(labId);
		return response;
	}

	/**
	 * Create netrx account
	 * 
	 * @param netRx
	 * @return ResponseDTO
	 */

	@Override
	public ResponseDTO createNetRx(NetRxDTO netRx) {
		ResponseDTO response = netRxService.create(netRx);
		return response;
	}

	/**
	 * Update netrx account
	 * 
	 * @param netrx
	 * @return ResponseDTO
	 */

	@Override
	public ResponseDTO updateNetRx(NetRxDTO netRx) {
		ResponseDTO response = netRxService.update(netRx);
		return response;
	}

	/**
	 * To retrieve netrx list
	 * 
	 * @param filter
	 * @response NetRxListResponseDTO
	 */
	@Override
	public NetRxListResponseDTO getNetRxList(FilterDTO filter) {
		NetRxListResponseDTO response = netRxService.list(filter);
		return response;
	}

	/**
	 * Checks for the authentication of super admin
	 * 
	 * @param login
	 * @return LoginResponseDTO
	 */
	@Override
	public LoginResponseDTO login(LoginDTO login) {
		LoginResponseDTO response = new LoginResponseDTO();
		validator.validateLogin(login);
		String userName = login.getUserName().trim();
		login.setUserName(userName);
		String encPassword = StringEncoder.encryptWithKey(login.getPassword().trim());
		login.setPassword(encPassword);
		response = superAdminDao.login(login);
		return response;
	}

	/**
	 * Super Admin details
	 */
	@Override
	public UserDetails getUser(String userName) {
		UserDetails user = superAdminDao.getUser(userName);
		return user;
	}

	/**
	 * To retrieve netmd list
	 * 
	 * @param filter
	 * @response NetMdListResponseDTO
	 */
	@Override
	public NetMdListResponseDTO getNetMdList(FilterDTO filter) {
		NetMdListResponseDTO response = netMdService.getNetMdList(filter);
		return response;
	}

	/**
	 * To retrieve netmd branch list
	 * 
	 * @param filter
	 * @response NetMdBranchListResponseDTO
	 */
	@Override
	public NetMdBranchListResponseDTO getBranchList(FilterDTO filter) {
		NetMdBranchListResponseDTO response = netMdService.getBranchList(filter);
		return response;
	}

	/**
	 * View netmd details
	 * 
	 * @param netMdId
	 * @response NetMdViewResponseDTO
	 */
	@Override
	public NetMdViewResponseDTO viewNetMd(int netMdId) {
		NetMdViewResponseDTO response = netMdService.viewNetMd(netMdId);
		return response;
	}

	/**
	 * Create netmd branch
	 * 
	 * @param netMdId
	 * @response ResponseDTO
	 */
	@Override
	public ResponseDTO createNetMdBranch(NetMdBranchDTO branch) {
		ResponseDTO response = netMdService.createBranch(branch);
		return response;
	}

	/**
	 * View netmd branch details
	 * 
	 * @param netMdId
	 * @response NetMdViewResponseDTO
	 */
	@Override
	public NetMdBranchResponseDTO viewNetMdBranch(int netMdBranchId) {
		NetMdBranchResponseDTO response = netMdService
				.viewBranch(netMdBranchId);
		return response;
	}

	/**
	 * Delete netmd branch
	 * 
	 * @param netMdId
	 * @response ResponseDTO
	 */
	@Override
	public ResponseDTO deleteNetMdBranch(int netMdBranchId) {
		ResponseDTO response = netMdService.deleteBranch(netMdBranchId);
		return response;
	}

	/**
	 * Update netmd branch
	 * 
	 * @param branch
	 * @response ResponseDTO
	 */
	@Override
	public ResponseDTO updateNetMdBranch(NetMdBranchDTO branch) {
		ResponseDTO response = netMdService.updateBranch(branch);
		return response;
	}

	/**
	 * Update netmd user
	 * 
	 * @param branch
	 * @response ResponseDTO
	 */
	@Override
	public ResponseDTO updateNetMdUser(NetMdUserDTO netMdUser) {
		ResponseDTO response = netMdService.updateUser(netMdUser.getHeader(),
				netMdUser.getUser());
		return response;
	}

	/**
	 * enable log process
	 * 
	 * @param log
	 * @response ResponseDTO
	 */
	@Override
	public ResponseDTO enableLog(LogDTO log, HttpServletRequest request) {
		ResponseDTO response = superAdminDao.enableLog(log, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * 

com.nv.youNeverWait.user.bl.service.SuperAdminService#userLogList(com
	 * .nv.youNeverWait.rs.dto.FilterDTO)
	 */
	@Override
	public UserLogListResponseDTO userLogList(FilterDTO filterDTO) {

		UserLogListResponseDTO response = new UserLogListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,
		// return result with appropriate error code
		ErrorDTO error = validator.validateLogFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for lab from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.LOG);
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
		TypedQuery<LogTbl> q = 

queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());

		Long count = queryBuilder.getCount();
		System.out.println("queryBuilder.getCount():" + count);
		// execute query
		List<LogTbl> logs = queryBuilder.executeQuery(q);
		response = getLogList(logs);
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
	private UserLogListResponseDTO getLogList(List<LogTbl> logs) {

		UserLogListResponseDTO response = new UserLogListResponseDTO();
		if (logs == null) {
			return response;
		}
		List<LogDetail> logDetails = new ArrayList<LogDetail>();
		for (LogTbl logTbl : logs) {
			String logOutTime = null;
			String loginTime = null;
			String actionDate = null;
			SimpleDateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_DAY_FIRST);
			if (logTbl.getLoginTime() != null) {
				loginTime = df.format(logTbl.getLoginTime());
			}
			if (logTbl.getLogoutTime() != null) {
				logOutTime = df.format(logTbl.getLogoutTime());
			}
			if (logTbl.getActionDate() != null) {
				actionDate = df.format(logTbl.getActionDate());
			}
			logDetails.add(new LogDetail(logTbl, loginTime, logOutTime,
					actionDate));
		}
		response.setLog(logDetails);
		return response;
	}

	/**
	 * Method which clears NetMd mac Id
	 * 
	 * @param header
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO clearNetMdMacId(HeaderDTO header) {
		ResponseDTO response = netMdService.clearMacId(header);
		return response;
	}

	/**
	 * Method which clears Netlims mac Id
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO clearNetlimsMacId(LabBranchDTO branch) {
		ResponseDTO response = labService.clearMacId(branch);
		return response;
	}

	/**
	 * Deletes a netRx
	 * 
	 * @param netRxId
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO deleteNetRx(int netRxId) {
		ResponseDTO response = netRxService.deleteNetRx(netRxId);
		return response;
	}

	/**
	 * To retrieve a list of netRx branches which satisfy all filter 

conditions
	 * 
	 * @param filter
	 * @return NetMdBranchListResponseDTO
	 */
	@Override
	public NetRxBranchListResponseDTO getNetRxBranchList(FilterDTO filter) 

{
		NetRxBranchListResponseDTO response = netRxService.getNetRxBranchList(filter);
		return response;
	}

	/**
	 * Creates a netRx branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO createNetRxBranch(NetRxBranchDetail branch) {
		ResponseDTO response = netRxService.createBranch(branch);
		return response;
	}

	/**
	 * Deletes a netRx branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO deleteNetRxBranch(int globalId) {
		ResponseDTO response = netRxService.deleteBranch(globalId);
		return response;
	}

	/**
	 * 
	 * View netRx account
	 * 
	 * @param netRxId
	 * @return NetRxViewResponseDTO
	 */
	@Override
	public NetRxViewResponseDTO viewNetRx(int netRxId) {
		NetRxViewResponseDTO response = netRxService.viewNetRx(netRxId);
		return response;
	}

	/**
	 * 
	 * View netRx branch account
	 * 
	 * @param netrxBranchId
	 * @return NetRxBranchResponseDTO
	 */
	@Override
	public NetRxBranchResponseDTO viewNetRxBranch(int netrxBranchId) {
		NetRxBranchResponseDTO response = netRxService.viewBranch(netrxBranchId);
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
		ResponseDTO response = netRxService.updateNetRxBranch(branch);
		return response;
	}

	/**
	 * To get the status of user enable log
	 * 
	 */
	@Override
	public EnableLogStatusResponseDTO enableLogStatus() {
		EnableLogStatusResponseDTO response = superAdminDao.enableLogStatus();
		return response;
	}

	/**
	 * To show all the total orders and its related details of each branch 

in
	 * the lab
	 * 
	 * @param globalId
	 * @return BranchOrdersResponseDTO
	 */
	@Override
	public BranchOrdersResponseDTO viewBranchOrders(int globalId) {
		BranchOrdersResponseDTO response = labService.viewBranchOrders(globalId);
		return response;
	}
	
	/**
	 * Method for listing orders
 	 */
	@Override
	public BranchOrdersResponseDTO orderList(FilterDTO filterDTO) {
		BranchOrdersResponseDTO response = labService.orderList(filterDTO);
		return response;
	}

	/**
	 * Method for viewing branch default system details
	 * 
	 * @param branchId
	 * @return
	 */
	@Override
	public BranchSystemInfoDetails viewBranchSystemInfo(int branchId) {
		BranchSystemInfoDetails response = labService.viewBranchSystemInfoDetails(branchId);
		return response;
	}

	/**
	 * Method for updating the branch default system details
	 * 
	 * @param details
	 * @return
	 */
	@Override
	public ResponseDTO updateLabBranchSystemInfo(BranchSystemInfoDetails 

details) {
		ResponseDTO response = labService.updateLabBranchSystemInfo(details);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * 

com.nv.youNeverWait.user.bl.service.SuperAdminService#testList(com.nv
	 * .youNeverWait.rs.dto.FilterDTO)
	 */
	@Override
	public TestListResponseDTO testList(FilterDTO filter) {
		TestListResponseDTO response = testService.testList(filter);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * 

com.nv.youNeverWait.user.bl.service.SuperAdminService#specimenList(com
	 * .nv.youNeverWait.rs.dto.FilterDTO)
	 */
	@Override
	public SpecimenListResponseDTO testSpecimenList(FilterDTO filter) {
		SpecimenListResponseDTO response = specimenService
				.testSpecimenList(filter);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * 

com.nv.youNeverWait.user.bl.service.SuperAdminService#enableSyncLog(com
	 * .nv.youNeverWait.rs.dto.LogDTO, 

javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ResponseDTO enableSyncLog(SyncLogDTO syncLog,
			HttpServletRequest request) {
		ResponseDTO response = superAdminDao.enableSyncLog(syncLog, 

request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * 

com.nv.youNeverWait.user.bl.service.SuperAdminService#setSyncFreq(com
	 * .nv.youNeverWait.rs.dto.SyncFreqDTO)
	 */
	@Override
	public ResponseDTO setSync(SyncFreqDTO sync) {
		// validator.validateSyncDetails(sync);
		ResponseDTO response = superAdminDao.setSync(sync);
		return response;
	}

	/**
	 * Method to set synchronization interval time for a  NetLims
	 */
	@Override
	public SyncFreqResponseDTO setLabSync(SyncFreqDTO sync) {
		SyncFreqResponseDTO response = labService.setLabSync(sync);
		return response;
	}


	/**
	 * Method to set synchronization interval time for a  NetLims branch
	 */
	@Override
	public SyncFreqResponseDTO setBranchSync(SyncFreqDTO sync) {
		SyncFreqResponseDTO response = labService.setBranchSync(sync);
		return response;
	}


	/**
	 * Method to set synchronization interval time for a  NetMd
	 */
	@Override
	public SyncFreqResponseDTO setNetMdSync(SyncFreqDTO sync) {
		SyncFreqResponseDTO response = netMdService.setNetMdSync(sync);
		return response;
	}

	/**
	 * Method to set synchronization interval time for a  NetMd branch
	 */
	@Override
	public SyncFreqResponseDTO setNetMdBranchSync(SyncFreqDTO sync) {
		SyncFreqResponseDTO response = netMdService.setNetMdBranchSync(sync);
		return response;
	}

	/**
	 * Method to set synchronization interval time for a  NetRx
	 */
	@Override
	public SyncFreqResponseDTO setNetRxSync(SyncFreqDTO sync) {
		SyncFreqResponseDTO response = netRxService.setNetRxSync(sync);
		return response;
	}

	/** 
	 * Method to set synchronization interval time for a  NetRx branch
	 */
	@Override
	public SyncFreqResponseDTO setNetRxBranchSync(SyncFreqDTO sync) {
		SyncFreqResponseDTO response = netRxService.setNetRxBranchSync(sync);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * 

com.nv.youNeverWait.user.bl.service.SuperAdminService#getSyncDetails()
	 */
	@Override
	public SyncFreqDTO getSyncDetails() {
		SyncFreqDTO response = superAdminDao.getSyncDetails();
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.bl.service.SuperAdminService#
	 * viewNetMdBranchSystemInfo(java.lang.String)
	 */
	@Override
	public BranchSystemInfoDetails viewNetMdBranchSystemInfo(String 

passphrase) {
		BranchSystemInfoDetails response = netMdService.viewNetmdBranchSystemInfoDetails(passphrase);
		return response;
	}

	/**
	 * Filter method for getting list of bills in netmd
	 * @param filter
	 * @return BranchBillListResponseDTO
	 */
	@Override
	public BranchBillListResponseDTO billList(FilterDTO filterDTO) {
		BranchBillListResponseDTO response = netMdService.billList(filterDTO);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.bl.service.SuperAdminService#
	 * updateNetmdBranchSystemInfo
	 * (com.nv.youNeverWait.rs.dto.BranchSystemInfoDetails)
	 */
	@Override
	public ResponseDTO updateNetmdBranchSystemInfo(
			BranchSystemInfoDetails systemCriticalDetails) {
		ResponseDTO response = netMdService.updateNetmdBranchSystemInfo(systemCriticalDetails);
		return response;
	}

	/**
	 * Shows a list of all sync logs
	 * 
	 * @param filter
	 * @return SyncLogListResponseDTO
	 */
	@Override
	public SyncLogListResponseDTO syncLogList(FilterDTO filterDTO) {
		SyncLogListResponseDTO response = new SyncLogListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,
		// return result with appropriate error code
		ErrorDTO error = validator.validateSyncLogFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for synclog from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.SYNC_LOG);
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
		TypedQuery<SyncLogTbl> q = 

queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());

		Long count = queryBuilder.getCount();
		System.out.println("queryBuilder.getCount():" + count);
		// execute query
		List<SyncLogTbl> logs = queryBuilder.executeQuery(q);
		response = getSyncLogList(logs);
		response.setCount(count);
		response.setSuccess(true);
		return response;
	}

	private SyncLogListResponseDTO getSyncLogList(List<SyncLogTbl> logs) {
		SyncLogListResponseDTO response = new 

SyncLogListResponseDTO();
		if (logs == null) {
			return response;
		}
		List<SyncLogDetail> syncLogList = new 

ArrayList<SyncLogDetail>();
		for (SyncLogTbl synclogTbl : logs) {

			String lastsyncTime = null;
			SimpleDateFormat df = new SimpleDateFormat(
					

Constants.DATE_FORMAT_WITH_TIME_SECONDS);
			if (synclogTbl.getLastSyncTime() != null) {
				lastsyncTime = df.format(synclogTbl.getLastSyncTime());
			}
			syncLogList.add(new SyncLogDetail(synclogTbl, lastsyncTime));

		}
		response.setSyncLog(syncLogList);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * 

com.nv.youNeverWait.user.bl.service.SuperAdminService#getSyncLogStatus()
	 */
	@Override
	public EnableLogStatusResponseDTO getSyncLogStatus() {
		EnableLogStatusResponseDTO response  = superAdminDao.getSyncLogStatus();
		return response;
	}

	// /* (non-Javadoc)
	// * @see
	// com.nv.youNeverWait.user.bl.service.SuperAdminService#enableSync(com.nv.youNeverWait.rs.dto.SyncFreqDTO)
	// */
	// @Override
	// public ResponseDTO enableSync(SyncFreqDTO sync) {
	// ResponseDTO response = superAdminDao.enableSync(sync);
	// return response;
	// }
	//
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
	 * @return the superAdminDao
	 */
	public SuperAdminDao getSuperAdminDao() {
		return superAdminDao;
	}

	/**
	 * @param superAdminDao
	 *            the superAdminDao to set
	 */
	public void setSuperAdminDao(SuperAdminDao superAdminDao) {
		this.superAdminDao = superAdminDao;
	}

	/**
	 * @return the validator
	 */
	public SuperAdminValidator getValidator() {
		return validator;
	}

	/**
	 * @param validator
	 *            the validator to set
	 */
	public void setValidator(SuperAdminValidator validator) {
		this.validator = validator;
	}

	/**
	 * @return the labService
	 */
	public LabService getLabService() {
		return labService;
	}

	/**
	 * @param labService
	 *            the labService to set
	 */
	public void setLabService(LabService labService) {
		this.labService = labService;
	}

	/**
	 * @return the mailFrom
	 */
	public String getMailFrom() {
		return mailFrom;
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
	public void setQueryBuilderFactory(QueryBuilderFactory 

queryBuilderFactory) {
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
	 * @return the netRxService
	 */
	public NetRxService getNetRxService() {
		return netRxService;
	}

	/**
	 * @param netRxService
	 *            the netRxService to set
	 */
	public void setNetRxService(NetRxService netRxService) {
		this.netRxService = netRxService;
	}

	/**
	 * @return the testService
	 */
	public TestService getTestService() {
		return testService;
	}

	/**
	 * @param testService
	 *            the testService to set
	 */
	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	/**
	 * @return the specimenService
	 */
	public SpecimenService getSpecimenService() {
		return specimenService;
	}

	/**
	 * @param specimenService
	 *            the specimenService to set
	 */
	public void setSpecimenService(SpecimenService specimenService) {
		this.specimenService = specimenService;
	}

	

	/* (non-Javadoc)
	 * @see 

com.nv.youNeverWait.user.bl.service.SuperAdminService#updateNetPos(com.nv.youNeverWait.rs.dto.NetPosDTO)
	 */
	@Override
	public ResponseDTO updateNetPos(NetPosDTO netPos) {
		ResponseDTO response = netPosService.updateNetPos(netPos);
		return response;
	}

	/* (non-Javadoc)
	 * @see 

com.nv.youNeverWait.user.bl.service.SuperAdminService#viewNetPos(int)
	 */
	@Override
	public NetPosViewResponseDTO viewNetPos(int netPosId) {
		NetPosViewResponseDTO response = 

netPosService.viewNetPos(netPosId);
		return response;
	
	}

	public NetPosService getNetPosService() {
		return netPosService;
	}

	public void setNetPosService(NetPosService netPosService) {
		this.netPosService = netPosService;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.SuperAdminService#getNetPosList(com.nv.youNeverWait.rs.dto.FilterDTO)
	 */
	@Override
	public NetPosListResponseDTO getNetPosList(FilterDTO filter) {
		NetPosListResponseDTO response = netPosService.list(filter);
		return response;
	}

	

	
	

	
	

	

	
	



	
	
	

}
