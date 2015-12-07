package com.nv.security.youNeverWait;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nv.platform.email.sendmsg.SendEmailMsgWorkerThread;
import com.nv.platform.email.sendmsg.SendMsgCallbackEnum;
import com.nv.platform.email.sendmsg.email.SendMailMsgObj;
import com.nv.platform.email.util.StringEncoder;
import com.nv.youNeverWait.rs.dto.UserCredentials;

public class MailSendAdapter {
	private String ynwServerIpAddress;
	private String mailFrom;
	private SendEmailMsgWorkerThread mailThread;
	private static final Log log = LogFactory.getLog(MailSendAdapter.class);

	/**
	 * Method to send email for resetting password.It will perform the following
	 * operations. 1.Take default email HTML template from Apache folder
	 * 2.Create email body 3.Send email to the lab user/owner.
	 */
	public void sendEmailForResetPassword(String subject, UserCredentials user) {

		String msgBody = "";
		URL url = null;
		try {
			url = new URL(
					"http://"
							+ ynwServerIpAddress
							+ "/youNeverWait/EmailFormat/patientForgotPasswordMail.html");
			msgBody = createDefaultEmailBody(url, user);
			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody,
					user.getEmailId(), mailFrom, 0, 0, null,
					SendMsgCallbackEnum.NETMD_RESET_PWD.getId(), null);
			mailThread.addSendMsgObj(obj);
		} catch (IOException e) {
			log.error(
					"Error while sending Email when doing Netmd forgot password",
					e);
			e.printStackTrace();
		}
	}


	/**
	 * Method to create email body
	 * 
	 * @param url
	 * @param firstName
	 * @param userName
	 * @param branchName
	 * @return
	 * @throws IOException
	 */
	private String createDefaultEmailBody(URL url, String firstName,
			String userName, String branchName) throws IOException {

		StringBuffer msgBodyBfr = new StringBuffer();
		String fullMsgBody = "";
		String encryptedUserName = StringEncoder.encryptWithStaticKey(userName);
		String resetPasswordLink = "http://"
				+ ynwServerIpAddress
				+ "/youNeverWait/html/createPasswordPatientCreation.html?userName="
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
		if (userName != null && !userName.equals("")) {
			fullMsgBody = fullMsgBody.replace("{firstName}", firstName);
			fullMsgBody = fullMsgBody.replace("{userid}", userName);
		} else {
			fullMsgBody = fullMsgBody.replace("{firstName}", "Dear customer,");
		}
		fullMsgBody = fullMsgBody.replace("{lastName}", "");
		if (branchName.equals(""))
			fullMsgBody = fullMsgBody.replace("{netmdName}", "");
		else
			fullMsgBody = fullMsgBody.replace("{netmdName}", branchName);
		fullMsgBody = fullMsgBody.replace("{userid}", "");
		fullMsgBody = fullMsgBody.replace("{ResetLink}", resetPasswordLink);
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				ynwServerIpAddress);

		return fullMsgBody;
	}


	
	
	public void sendEmailForPatientCreation(String firstName, String emailId,
			String subject, String userName, String branchName, int noOfPatients) {
		
		String msgBody = "";
		URL url = null;
		try {
			
			if (noOfPatients > 1) {
				url = new URL(
						"http://"
								+ ynwServerIpAddress
								+ "/youNeverWait/EmailFormat/PatientCreationAnother.html");
			} else {
				url = new URL("http://" + ynwServerIpAddress
						+ "/youNeverWait/EmailFormat/PatientCreation.html");
			}
			msgBody = createDefaultEmailBody(url, firstName, userName,
					branchName);
			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody, emailId,
					mailFrom, 0, 0, null,
					SendMsgCallbackEnum.PATIENT_REGISTRATION.getId(), null);
			mailThread.addSendMsgObj(obj);
		} catch (IOException e) {
			log.error("Error while sending Email for patient creation ", e);
			e.printStackTrace();
		}
	}
	

	
	
	/**
	 * Method to create email body for Reset Password
	 */
	public String createDefaultEmailBody(URL url, UserCredentials user)
			throws IOException {

		StringBuffer msgBodyBfr = new StringBuffer();
		String fullMsgBody = "";
		String encryptedUserName = StringEncoder.encryptWithStaticKey(user
				.getUserName());
		String resetPasswordLink = "http://"
				+ ynwServerIpAddress
				+ "/youNeverWait/EmailFormat/NetlimsPatientResetLink.html?userName="
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
		fullMsgBody = fullMsgBody.replace("{ResetLink}", resetPasswordLink);
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				ynwServerIpAddress);

		return fullMsgBody;
	}


	public void setYnwServerIpAddress(String ynwServerIpAddress) {
		this.ynwServerIpAddress = ynwServerIpAddress;
	}


	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}


	public void setMailThread(SendEmailMsgWorkerThread mailThread) {
		this.mailThread = mailThread;
	}

	
	
	

}
