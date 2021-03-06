/**
 * DoctorServiceImpl.java
 *
 * Dec 19, 2012
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

import org.apache.log4j.Logger;

import com.nv.platform.email.sendmsg.SendEmailMsgWorkerThread;
import com.nv.platform.email.sendmsg.SendMsgCallbackEnum;
import com.nv.platform.email.sendmsg.email.SendMailMsgObj;
import com.nv.platform.email.util.StringEncoder;
import com.nv.youNeverWait.api.sync.ReferralSyncDTO;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.NetmdBranchTbl;
import com.nv.youNeverWait.pl.entity.NetmdDoctorTbl;
import com.nv.youNeverWait.rs.dto.DoctorDetail;
import com.nv.youNeverWait.rs.dto.DoctorDetailsForPatient;
import com.nv.youNeverWait.rs.dto.DoctorListResponseDTO;
import com.nv.youNeverWait.rs.dto.DoctorLoginDTO;
import com.nv.youNeverWait.rs.dto.DoctorViewResponseDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalDoctorResponseDTO;
import com.nv.youNeverWait.user.bl.service.DoctorService;
import com.nv.youNeverWait.user.bl.validation.DoctorValidator;
import com.nv.youNeverWait.user.pl.dao.DoctorDao;
import com.nv.youNeverWait.user.pl.impl.DoctorDaoImpl;

/**
 * @author Asha Chandran 
 *
 */
public class DoctorServiceImpl implements DoctorService {

	private DoctorDao doctorDao;
	private DoctorDaoImpl doctorDaoImpl;
	private DoctorValidator validator;
	private String netMdServerIpAddress;
	private String mailFrom;
	private SendEmailMsgWorkerThread mailThread;
	private static final Logger log = Logger.getLogger(DoctorServiceImpl.class);

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
		ResponseDTO response = doctorDao.resetPassword(login);
		return response;
	}

	/**
	 * Method to perform doctor creation
	 * 
	 * @param doctor
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO create(DoctorDetail doctor, HeaderDTO header) {

		validator.validateCreateDoctor(doctor, header);
		ResponseDTO response = doctorDao.create(doctor, header);
//		NetmdBranchTbl netmdBranchTbl = doctorDao.getById(NetmdBranchTbl.class,
//				header.getBranchId());
//		String netmdBranch= netmdBranchTbl.getName();
//		/*Sending mail to doctor*/
//		sendEmailToDoctor(Constants.DOCTOR_REGISTER, doctor,netmdBranch);

		return response;
	}

	/**
	 * Method to update doctor details
	 * 
	 * @param doctor
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO update(DoctorDetail doctor, HeaderDTO header) {

		validator.validateUpdateDoctor(doctor, header);
		ResponseDTO response = doctorDao.update(doctor, header);
		return response;
	}

	/**
	 * Method to delete doctor
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO delete(int globalId) {

		validator.validateGlobalId(globalId);
		ResponseDTO response = doctorDao.delete(globalId);
		return response;
	}

	/**
	 * View a doctor by giving input as id
	 * 
	 * @param id
	 * @return DoctorDetail
	 */
	@Override
	public DoctorViewResponseDTO view(int id) {

		DoctorViewResponseDTO response = doctorDao.view(id);
		return response;
	}

	/**
	 * To send lab registration email to the owner's email id It will perform
	 * the following operations. 1.Take default email HTML template from Apache
	 * folder 2.Create email body 3.Send email to the lab owner.
	 * 
	 * @param subject
	 * @param doctor 
	 * @param netmdBranch
	 */
	private void sendEmailToDoctor(String subject, DoctorDetail doctor, String netmdBranch) {
		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + netMdServerIpAddress
					+ "/youNeverWait/EmailFormat/DoctorRegistration.html");
			msgBody = createDefaultEmailBody(url, doctor,netmdBranch);

			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody,
					doctor.getEmail(), mailFrom, 0, 0, null,
					SendMsgCallbackEnum.DOCTOR_REGISTRATION.getId(), null);
			mailThread.addSendMsgObj(obj);

		} catch (IOException e) {
			log.error("Error while sending Email to Doctor", e);
			e.printStackTrace();

		}
	}

	/**
	 * To create email body
	 * 
	 * @param url
	 * @param doctor
	 * @param netmdBranch 
	 * 
	 * @return email message body
	 */
	private String createDefaultEmailBody(URL url, DoctorDetail doctor, String netmdBranch)
			throws IOException {

		StringBuffer msgBodyBfr = new StringBuffer();
		String fullMsgBody = "";

		String encryptedUserName = StringEncoder.encryptWithStaticKey(doctor
				.getEmail());
		String resetPasswordLink = "http://"
				+ netMdServerIpAddress
				+ "/youNeverWait/EmailFormat/DoctorResetPassword.html?userName="
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
		fullMsgBody = fullMsgBody.replace("{firstName}", doctor.getFirstName());
		fullMsgBody = fullMsgBody.replace("{lastName}", doctor.getLastName());
		fullMsgBody = fullMsgBody.replace("{ResetLink}", resetPasswordLink);
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				netMdServerIpAddress);
		fullMsgBody = fullMsgBody.replace("{email}",
				doctor.getEmail());
		fullMsgBody = fullMsgBody.replace("{netmd}",
				netmdBranch);
		return fullMsgBody;
	}

	/**
	 * list Doctors
	 * 
	 * @param clinicId
	 * @return DoctorListResponseDTO
	 */
	@Override
	public DoctorListResponseDTO listDoctors(String clinicId) {

		List<NetmdDoctorTbl> doctorsList = doctorDao.listDoctors(clinicId);
		DoctorListResponseDTO response = getDoctorList(doctorsList);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method to get doctors list
	 * 
	 * @param labs
	 * @return DoctorListResponseDTO
	 */
	private DoctorListResponseDTO getDoctorList(List<NetmdDoctorTbl> doctors) {
		DoctorListResponseDTO response = new DoctorListResponseDTO();
		if (doctors == null) {
			return response;
		}
		List<DoctorDetailsForPatient> doctorDetails = new ArrayList<DoctorDetailsForPatient>();
		for (NetmdDoctorTbl doctorTbl : doctors) {
			doctorDetails.add(new DoctorDetailsForPatient(doctorTbl));
		}
		response.setDoctor(doctorDetails);
		return response;
	}

	/**
	 * Method performed to retrieve created updated and deleted doctors list
	 * from portal
	 * 
	 * @param lastSyncTime
	 * @param passPhrase
	 * @param netmdBranchId
	 * @return RetrievalDoctorResponseDTO
	 */
	@Override
	public RetrievalDoctorResponseDTO retrieveDoctorList(String lastSyncTime,
			String passPhrase, int netmdBranchId, Date currentSyncTime) {
		RetrievalDoctorResponseDTO response = doctorDao.retrieveDoctorList(
				lastSyncTime, passPhrase, netmdBranchId, currentSyncTime);
		return response;
	}

	/**
	 * Method to retrieve the password of a doctor to Netmd after resetting it
	 * in the portal
	 */
	@Override
	public List<DoctorLoginDTO> DoctorPasswordList(String lastSyncTime,
			String passPhrase, int netmdBranchId, Date currentSyncTime) {
		List<DoctorLoginDTO> response = doctorDao.DoctorPasswordList(
				lastSyncTime, passPhrase, netmdBranchId, currentSyncTime);
		return response;
	}

	/**
	 * @return doctorDaoImpl
	 */
	public DoctorDaoImpl getDoctorDaoImpl() {
		return doctorDaoImpl;
	}

	/**
	 * @param doctorDaoImpl
	 */
	public void setDoctorDaoImpl(DoctorDaoImpl doctorDaoImpl) {
		this.doctorDaoImpl = doctorDaoImpl;
	}

	/**
	 * @return the doctorDao
	 */
	public DoctorDao getDoctorDao() {
		return doctorDao;
	}

	/**
	 * @param doctorDao
	 *            the doctorDao to set
	 */
	public void setDoctorDao(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}

	/**
	 * @return the validator
	 */
	public DoctorValidator getValidator() {
		return validator;
	}

	/**
	 * @param validator
	 *            the validator to set
	 */
	public void setValidator(DoctorValidator validator) {
		this.validator = validator;
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

	@Override
	public int processReferral(ReferralSyncDTO referral) {

		return doctorDao.processReferral(referral);
	}

}