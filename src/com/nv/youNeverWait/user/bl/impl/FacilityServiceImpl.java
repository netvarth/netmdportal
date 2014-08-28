package com.nv.youNeverWait.user.bl.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.nv.framework.sendmsg.SendEmailMsgWorkerThread;
import com.nv.framework.sendmsg.SendMsgCallbackEnum;
import com.nv.framework.sendmsg.email.SendMailMsgObj;
import com.nv.framework.util.StringUtil;
import com.nv.framework.util.text.StringEncoder;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.NetmdUserTypeEnum;
import com.nv.youNeverWait.rs.dto.FacilitySyncDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.user.bl.service.FacilityService;
import com.nv.youNeverWait.user.pl.dao.FacilityDao;

/**
 * @author Mani E.V
 *
 */
public class FacilityServiceImpl implements FacilityService {
	
	private FacilityDao facilityDao;
	private String netlimsServerIpAddress;
	private String mailFrom;
	private SendEmailMsgWorkerThread mailThread;
	private static final Log log = LogFactory.getLog(FacilityServiceImpl.class);
	@Override
	public int processFacility(FacilitySyncDTO facility, Integer branchId) {
		int facilityId=0;
		if(facility.getGlobalId()==0) {
			facilityId = create(facility, branchId);
		} else 
			facilityId = facilityDao.update(facility, branchId);	
		return facilityId;
	}

	private void sendEmail(String subject, FacilitySyncDTO facility, LoginDTO login, String branch, String password) {
		
		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netlimsServerIpAddress
					+ "/youNeverWait/EmailFormat/NetlimsFacilityRegistration.html");
			msgBody = createDefaultEmailBody(url, facility, login,branch, password);

			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody,
					login.getUserName(), mailFrom, 0, 0, null,
					SendMsgCallbackEnum.LAB_BRANCH_REGISTRATION.getId(), null);
			mailThread.addSendMsgObj(obj);

		} catch (IOException e) {
			log.error("Error while sending Email to Doctor", e);
			e.printStackTrace();

		}
	}
	private String createDefaultEmailBody(URL url, FacilitySyncDTO facility, LoginDTO login, String branchName, String password)
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
		fullMsgBody = fullMsgBody.replace("{facilityName}",facility.getFacility().getName());
		fullMsgBody = fullMsgBody.replace("{branchName}", branchName);
		fullMsgBody = fullMsgBody.replace("{userid}", login.getUserName());
		fullMsgBody = fullMsgBody.replace("{password}",password);
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				netlimsServerIpAddress);
		return fullMsgBody;
	}
	@Override
	@Transactional(readOnly=false)
	public int create(FacilitySyncDTO facility, Integer branchId) {	
		int facilityId = facilityDao.validateFacility(facility);
		if(facilityId!=0)
			return facilityId;
		facilityId =  facilityDao.create(facility, branchId);	
		LoginDTO login = new LoginDTO();
		String password = StringUtil.getRandomPassword();
		login.setUserName(facility.getFacility().getAddress().getEmail());
		login.setUserType(NetmdUserTypeEnum.Facility.getDisplayName());
		login.setPassword(StringEncoder.encryptWithKey(password));
		login = facilityDao.setLoginInfo(login, facilityId);
		String branch = facilityDao.getFacilityBranch(branchId);
		sendEmail(Constants.USER_REGISTRATION, facility,login, branch, password);
		return facilityId;
	}

	/**
	 * @return the facilityDao
	 */
	public FacilityDao getFacilityDao() {
		return facilityDao;
	}

	/**
	 * @param facilityDao the facilityDao to set
	 */
	public void setFacilityDao(FacilityDao facilityDao) {
		this.facilityDao = facilityDao;
	}
	/**
	 * @return the netlimsServerIpAddress
	 */
	public String getNetlimsServerIpAddress() {
		return netlimsServerIpAddress;
	}

	/**
	 * @param netlimsServerIpAddress the netlimsServerIpAddress to set
	 */
	public void setNetlimsServerIpAddress(String netlimsServerIpAddress) {
		this.netlimsServerIpAddress = netlimsServerIpAddress;
	}

	/**
	 * @return the mailFrom
	 */
	public String getMailFrom() {
		return mailFrom;
	}

	/**
	 * @param mailFrom the mailFrom to set
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
	 * @param mailThread the mailThread to set
	 */
	public void setMailThread(SendEmailMsgWorkerThread mailThread) {
		this.mailThread = mailThread;
	}

	@Override
	public String getFacilityBranchName(Integer source_branch_id) {
		return facilityDao.getFacilityBranch(source_branch_id);
	}
}
