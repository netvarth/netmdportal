/**
 * PatientServiceImpl.java
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
import com.nv.framework.sendmsg.SendEmailMsgWorkerThread;
import com.nv.framework.sendmsg.SendMsgCallbackEnum;
import com.nv.framework.sendmsg.email.SendMailMsgObj;
import com.nv.framework.util.text.StringEncoder;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.PatientTbl;
import com.nv.youNeverWait.pl.entity.ResultTbl;
import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.AppointmentResponse;
import com.nv.youNeverWait.rs.dto.CaseDTO;
import com.nv.youNeverWait.rs.dto.CreatePasswordDTO;
import com.nv.youNeverWait.rs.dto.DoctorListResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.PastAppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.PatientListResponseDTO;
import com.nv.youNeverWait.rs.dto.PatientOrderDTO;
import com.nv.youNeverWait.rs.dto.QuestionAnswerDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultDTO;
import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalPatientResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.user.bl.service.AppointmentService;
import com.nv.youNeverWait.user.bl.service.DoctorService;
import com.nv.youNeverWait.user.bl.service.NetMdService;
import com.nv.youNeverWait.user.bl.service.PatientService;
import com.nv.youNeverWait.user.bl.service.QuestionnaireService;
import com.nv.youNeverWait.user.bl.service.ScheduleService;
import com.nv.youNeverWait.user.bl.validation.PatientValidator;
import com.nv.youNeverWait.user.pl.dao.PatientDao;
import com.nv.youNeverWait.util.filter.core.Filter;
import com.nv.youNeverWait.util.filter.core.FilterFactory;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;
import com.nv.youNeverWait.util.filter.core.QueryBuilderFactory;

public class PatientServiceImpl implements PatientService {

	private PatientValidator validator;
	private PatientDao patientDao;
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	private NetMdService netMdService;
	private DoctorService doctorService;
	private ScheduleService scheduleService;
	private AppointmentService appointmentService;
	private String mailFrom;
	private String ynwServerIpAddress;
	private SendEmailMsgWorkerThread mailThread;
	private QuestionnaireService questionnaireService;
	private static final Log log = LogFactory.getLog(PatientServiceImpl.class);

	public DoctorService getDoctorService() {
		return doctorService;
	}

	/**
	 * Creates a patient
	 * 
	 * @param patient
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO createPatient(PatientDetail patient, HeaderDTO header) {

		validator.validateCreatePatient(patient, header);
		ResponseDTO response = patientDao.createPatient(patient, header);
		String branchName = patientDao.getBranch(header.getBranchId());

		if (patient.getEmail() != null && !patient.getEmail().isEmpty())
			// send login details and password creation link to the user
			sendEmailForPatientCreation(patient.getFirstName(),
					patient.getEmail(), Constants.PATIENT_REGISTRATION,
					patient.getEmail(), branchName);
		return response;
	}

	/**
	 * update a patient
	 * 
	 * @param patient
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO updatePatient(PatientDetail patient, HeaderDTO header) {

		validator.validateUpdatePatient(patient, header);

		ResponseDTO response = patientDao.updatePatient(patient, header);
		return response;
	}

	/**
	 * view details of a patient
	 * 
	 * @param globalId
	 * @return PatientDTO
	 */
	@Override
	public PatientDetail viewPatient(int globalId) {
		PatientDetail response = patientDao.viewPatient(globalId);
		return response;
	}

	/**
	 * deletes patient
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO deletePatient(int globalId) {
		ResponseDTO response = patientDao.deletePatient(globalId);
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
		UserCredentials user = patientDao.getUserCredentials(login);
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
	 * Method to create email body for Reset Password
	 */
	private String createDefaultEmailBody(URL url, UserCredentials user)
			throws IOException {

		StringBuffer msgBodyBfr = new StringBuffer();
		String fullMsgBody = "";
		String encryptedUserName = StringEncoder.encryptWithStaticKey(user
				.getUserName());
		String resetPasswordLink = "http://"
				+ ynwServerIpAddress
				+ "/youNeverWait/EmailFormat/PatientResetPassword.html?userName="
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
		ResponseDTO response = patientDao.resetPassword(login);
		return response;

	}

	/**
	 * Retrieve patients for the netmd
	 * 
	 * @param lastSyncTime
	 * @param passPhrase
	 * @param netMdBranchId
	 * @return RetrievalPatientResponseDTO
	 */
	@Override
	public RetrievalPatientResponseDTO retrievePatientsForNetMd(
			String lastSyncTime, String passPhrase, int netMdBranchId,
			Date currentSyncTime) {

		RetrievalPatientResponseDTO retrievalPatientResponseDTO = patientDao
				.retrievePatientsForNetMd(lastSyncTime, passPhrase,
						netMdBranchId, currentSyncTime);
		return retrievalPatientResponseDTO;
	}

	/**
	 * List patients on login
	 * 
	 * @param patientEmailId
	 * @return PatientListResponseDTO
	 */
	@Override
	public PatientListResponseDTO patientListOnLogin(String patientEmailId) {
		List<PatientTbl> patientList = patientDao
				.listOfPatientsOnLogin(patientEmailId);
		// set patient_tbl values to the PatientDetail
		PatientListResponseDTO patientResponse = getPatientList(patientList);
		patientResponse.setSuccess(true);
		return patientResponse;
	}

	/**
	 * set patient_tbl values to the PatientDetail
	 * 
	 * @param patientList
	 * @return response
	 */
	private PatientListResponseDTO getPatientList(List<PatientTbl> patientList) {

		PatientListResponseDTO response = new PatientListResponseDTO();
		if (patientList == null) {
			return response;
		}
		List<PatientDetail> patientDetailsList = new ArrayList<PatientDetail>();
		for (PatientTbl patientTbl : patientList) {
			patientDetailsList.add(new PatientDetail(patientTbl));
		}
		response.setPatientList(patientDetailsList);
		return response;
	}

	/**
	 * Method to send email for Patient Creation
	 * 
	 * @param firstName
	 * @param emailId
	 * @param subject
	 * @param userName
	 * @param branchName
	 */
	private void sendEmailForPatientCreation(String firstName, String emailId,
			String subject, String userName, String branchName) {
		List<PatientTbl> patients = new ArrayList<PatientTbl>();
		String msgBody = "";
		URL url = null;
		try {
			patients = patientDao.listOfPatientsOnLogin(emailId);
			if (patients.size() > 1) {
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

	/**
	 * Shows a list of all netmd branches
	 * 
	 * @param filter
	 * @return NetMdBranchListResponseDTO
	 */
	@Override
	public NetMdBranchListResponseDTO getBranchList(FilterDTO filterDTO) {
		NetMdBranchListResponseDTO response = netMdService
				.getBranchList(filterDTO);
		return response;
	}

	/**
	 * lists appointments for Patient
	 * 
	 * @param patientId
	 * @return response
	 */
	@Override
	public AppointmentListResponseDTO listAppointmentsForPatient(
			String patientId) {
		AppointmentListResponseDTO response = appointmentService
				.getAppointmentListsForPatient(patientId);
		return response;
	}

	/**
	 * getPastAppointmentList
	 * 
	 * @param patientId
	 * @return response
	 */
	@Override
	public PastAppointmentListResponseDTO getPastAppointmentList(
			String patientId) {
		PastAppointmentListResponseDTO response = appointmentService
				.getPastAppointmentList(patientId);
		return response;
	}

	/**
	 * Create appointment from NetMd
	 * 
	 * @param appointment
	 * @return AppointmentResponse
	 */
	@Override
	public AppointmentResponse createAppointmentFromNetMd(
			Appointment appointment) {
		AppointmentResponse appointmentResponse = appointmentService
				.createAppointmentFromNetMd(appointment);
		return appointmentResponse;
	}

	/**
	 * Create appointment from Portal
	 * 
	 * @param appointment
	 * @return AppointmentResponse
	 */
	@Override
	public AppointmentResponse createAppointmentFromPortal(
			Appointment appointment) {
		AppointmentResponse appointmentResponse = appointmentService
				.createAppointmentFromPortal(appointment);
		return appointmentResponse;
	}

	/**
	 * Update appointment from Netmd
	 * 
	 * @param appointment
	 * @return AppointmentResponse
	 */
	@Override
	public AppointmentResponse updateAppointmentFromNetMd(
			Appointment appointment) {
		AppointmentResponse appointmentResponse = appointmentService
				.updateAppointmentFromNetMd(appointment);
		return appointmentResponse;
	}

	/**
	 * Update appointment from Portal
	 * 
	 * @param appointment
	 * @return AppointmentResponse
	 */
	@Override
	public AppointmentResponse updateAppointmentFromPortal(
			Appointment appointment) {
		AppointmentResponse appointmentResponse = appointmentService
				.updateAppointmentFromPortal(appointment);

		return appointmentResponse;
	}

	/**
	 * delete appointment from Netmd
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	public AppointmentResponse deleteAppointmentFromNetmd(int id) {
		AppointmentResponse response = appointmentService
				.deleteAppointmentFromNetMd(id);
		return response;

	}

	/**
	 * list Doctors
	 * 
	 * @param clinicId
	 * @return DoctorListResponseDTO
	 */
	@Override
	public DoctorListResponseDTO listDoctors(String clinicId) {
		DoctorListResponseDTO response = doctorService.listDoctors(clinicId);
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
		ResponseDTO response = patientDao.changePassword(passwords);
		return response;
	}

	@Override
	public ResponseDTO createPassword(CreatePasswordDTO passwords) {
		validator.validatePasswordsForCreatePassword(passwords);
		ResponseDTO response = patientDao.createPassword(passwords);
		return response;
	}

	/**
	 * To retrieve a result corresponding to the orderId and patient given
	 * 
	 * @param patient
	 * @return ResultDTO
	 */
	@Override
	public ResultDTO patientTestResult(PatientOrderDTO patient) {

		validator.validatePatientOrderDetails(patient);
		ResultDTO response = patientDao.patientTestResult(patient);
		return response;
	}

	@Override
	public ResultListResponseDTO getresultList(FilterDTO filterDTO) {
		ResultListResponseDTO response = new ResultListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,return result with appropriate error code
		ErrorDTO error = validator.validateResultFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for result from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.RESULT);
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
		TypedQuery<ResultTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());

		// get count
		Long count = queryBuilder.getCount();

		// execute query
		List<ResultTbl> results = queryBuilder.executeQuery(q);
		response = resultList(results);
		response.setCount(count);
		response.setSuccess(true);
		return response;
	}

	/**
	 * To set response with details of results
	 * 
	 * @param results
	 * @return ResultListResponseDTO
	 */
	private ResultListResponseDTO resultList(List<ResultTbl> results) {
		ResultListResponseDTO response = new ResultListResponseDTO();
		if (results == null) {
			return response;
		}
		List<ResultDTO> resultsDTO = new ArrayList<ResultDTO>();
		for (ResultTbl resultTbl : results) {
			resultsDTO.add(new ResultDTO(resultTbl));
		}
		response.setResultList(resultsDTO);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.bl.service.PatientService#
	 * deleteAppointmentFromPortal(int)
	 */
	@Override
	public AppointmentResponse deleteAppointmentFromPortal(int id) {
		AppointmentResponse response = new AppointmentResponse();
		response = appointmentService.deleteAppointmentFromPortal(id);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.bl.service.PatientService#getPastAppointments
	 * (com.nv.youNeverWait.rs.dto.FilterDTO)
	 */
	@Override
	public PastAppointmentListResponseDTO getPastAppointments(FilterDTO filter) {
		PastAppointmentListResponseDTO response = new PastAppointmentListResponseDTO();
		response = appointmentService.getPastAppointments(filter);
		return response;
	}


	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.PatientService#createCase(com.nv.youNeverWait.rs.dto.CaseDTO, com.nv.youNeverWait.rs.dto.HeaderDTO)
	 */
	@Override
	public ResponseDTO createCase(CaseDTO newPatientCase, HeaderDTO header) {
		validator.validateCase(newPatientCase);
		ResponseDTO response= new ResponseDTO();
		response = patientDao.createCase(newPatientCase,header);
		
		if(newPatientCase.getDepartmentName().trim().equals(Constants.OBSTETRICS) && response.getGlobalId()!=0){
			QuestionAnswerDTO questionAnswer=new QuestionAnswerDTO();
			 questionAnswer.setCaseId(response.getId());
			 questionAnswer.setDepartmentId(newPatientCase.getDepartmentId());
			 questionAnswer.setAnswerDTO(newPatientCase.getQuestionAnswerDTO().getAnswerDTO());
			 response=questionnaireService.create(questionAnswer);
		}
		response.setSuccess(true);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.PatientService#updateCases(com.nv.youNeverWait.rs.dto.CaseDTO, com.nv.youNeverWait.rs.dto.HeaderDTO)
	 */
	@Override
	public ResponseDTO updateCase(CaseDTO updatedPatientCase, HeaderDTO header) {
		ResponseDTO response = new ResponseDTO();
		validator.validateCase(updatedPatientCase);
		response = patientDao.updateCase(updatedPatientCase, header);
		if(updatedPatientCase.getDepartmentName().trim().equals(Constants.OBSTETRICS) && response.getGlobalId()!=0){
			 QuestionAnswerDTO questionAnswer=new QuestionAnswerDTO();
			 questionAnswer.setCaseId(response.getId());
			 questionAnswer.setDepartmentId(updatedPatientCase.getDepartmentId());
			 questionAnswer.setAnswerDTO(updatedPatientCase.getQuestionAnswerDTO().getAnswerDTO());
			 response=questionnaireService.update(questionAnswer);
		}
		response.setSuccess(true);
		return response;
	}
	
	public QueryBuilderFactory getQueryBuilderFactory() {
		return queryBuilderFactory;
	}

	public void setQueryBuilderFactory(QueryBuilderFactory queryBuilderFactory) {
		this.queryBuilderFactory = queryBuilderFactory;
	}

	public FilterFactory getFilterFactory() {
		return filterFactory;
	}

	public void setFilterFactory(FilterFactory filterFactory) {
		this.filterFactory = filterFactory;
	}

	public AppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	public NetMdService getNetMdService() {
		return netMdService;
	}

	public void setNetMdService(NetMdService netMdService) {
		this.netMdService = netMdService;
	}

	/**
	 * @return the validator
	 */
	public PatientValidator getValidator() {
		return validator;
	}

	/**
	 * @param validator
	 *            the validator to set
	 */
	public void setValidator(PatientValidator validator) {
		this.validator = validator;
	}

	/**
	 * @return the patientDao
	 */
	public PatientDao getPatientDao() {
		return patientDao;
	}

	/**
	 * @param patientDao
	 *            the patientDao to set
	 */
	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	public ScheduleService getScheduleService() {
		return scheduleService;
	}

	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getYnwServerIpAddress() {
		return ynwServerIpAddress;
	}

	public void setYnwServerIpAddress(String ynwServerIpAddress) {
		this.ynwServerIpAddress = ynwServerIpAddress;
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
