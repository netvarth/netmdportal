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

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.nv.platform.email.sendmsg.SendEmailMsgWorkerThread;
import com.nv.platform.email.sendmsg.SendMsgCallbackEnum;
import com.nv.platform.email.sendmsg.email.SendMailMsgObj;
import com.nv.platform.email.util.StringEncoder;
import com.nv.platform.email.util.StringUtil;
import com.nv.security.youNeverWait.MailSendAdapter;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.NetmdPatientTbl;
import com.nv.youNeverWait.pl.entity.NetmdUserTypeEnum;
import com.nv.youNeverWait.pl.entity.ResultTbl;
import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.AppointmentResponse;
import com.nv.youNeverWait.rs.dto.CaseDTO;
import com.nv.youNeverWait.rs.dto.DoctorListResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.MedicalRecordDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.PastAppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.Patient;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.PatientListResponseDTO;
import com.nv.youNeverWait.rs.dto.PatientOrderDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultDTO;
import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalPatientResponseDTO;
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
	private MailSendAdapter mailSendAdapter;
	private String ynwServerIpAddress;
	private QuestionnaireService questionnaireService;
	private static final Logger log = Logger.getLogger(PatientServiceImpl.class);
	private String mailFrom;
	private SendEmailMsgWorkerThread mailThread;
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

		//validator.validateCreatePatient(patient, header);
		ResponseDTO response = patientDao.createPatient(patient, header);
//		String branchName = patientDao.getBranch(header.getBranchId());

//		if (patient.getEmail() != null && !patient.getEmail().isEmpty()){
//			// send login details and password creation link to the user
//			List<NetmdPatientTbl>   patients = patientDao.listOfPatientsOnLogin(patient.getEmail());
//			log.info("CreatePatient----Sending Mail to patient..............");
//			mailSendAdapter.sendEmailForPatientCreation(patient.getFirstName(),
//					patient.getEmail(), Constants.PATIENT_REGISTRATION,
//					patient.getEmail(), branchName,patients.size());
//			log.info("CreatePatient----Mail Sent to queue ..........");
//		}	
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
		boolean flag=patientDao.isEmailExists(patient.getGlobalId(),patient.getEmail().trim());
		ResponseDTO response = patientDao.updatePatient(patient, header);
		if(!flag){
			String branchName = patientDao.getBranch(header.getBranchId());
			// send login details and password creation link to the user
			List<NetmdPatientTbl>   patients = patientDao.listOfPatientsOnLogin(patient.getEmail());

			mailSendAdapter.sendEmailForPatientCreation(patient.getFirstName(),patient.getEmail(), Constants.PATIENT_REGISTRATION,
					patient.getEmail(), branchName,patients.size());
		}

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
		List<NetmdPatientTbl> patientList = patientDao
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
	private PatientListResponseDTO getPatientList(List<NetmdPatientTbl> patientList) {

		PatientListResponseDTO response = new PatientListResponseDTO();
		if (patientList == null) {
			return response;
		}
		List<PatientDetail> patientDetailsList = new ArrayList<PatientDetail>();
		for (NetmdPatientTbl patientTbl : patientList) {
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
		ResponseDTO respnse= new ResponseDTO();
		ResponseDTO response = patientDao.createCase(newPatientCase,header);

		//		String departmentName= patientDao.getDepartmentNameById(newPatientCase.getDepartmentId());
		//		if(departmentName.trim().equals(DepartmentTypeEnum.Obstetrics.getDisplayName()) && response.getGlobalId()!=0){
		//			QuestionAnswerDTO questionAnswer=new QuestionAnswerDTO();
		//			 questionAnswer.setCaseId(response.getGlobalId());
		//			 questionAnswer.setDepartmentId(newPatientCase.getDepartmentId());
		//			 questionAnswer.setAnswerDTO(newPatientCase.getQuestionAnswerDTO().getAnswerDTO());
		//			ResponseDTO rsponse=questionnaireService.create(questionAnswer,header);
		//		}
		respnse.setGlobalId(response.getGlobalId());
		respnse.setId(response.getId());
		respnse.setSuccess(true);
		return respnse;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.PatientService#updateCases(com.nv.youNeverWait.rs.dto.CaseDTO, com.nv.youNeverWait.rs.dto.HeaderDTO)
	 */
	@Override
	public ResponseDTO updateCase(CaseDTO updatedPatientCase, HeaderDTO header) {
		ResponseDTO respnse = new ResponseDTO();
		validator.validateUpdatedCase(updatedPatientCase);	 

		ResponseDTO response = patientDao.updateCase(updatedPatientCase, header);
		//		String departmentName= patientDao.getDepartmentNameById(updatedPatientCase.getDepartmentId());
		//		if(departmentName.trim().equals(DepartmentTypeEnum.Obstetrics.getDisplayName()) && response.getGlobalId()!=0){
		//			 QuestionAnswerDTO questionAnswer=new QuestionAnswerDTO();
		//			 questionAnswer.setCaseId(response.getGlobalId());
		//			 questionAnswer.setDepartmentId(updatedPatientCase.getDepartmentId());
		//			 questionAnswer.setAnswerDTO(updatedPatientCase.getQuestionAnswerDTO().getAnswerDTO());
		//			 ResponseDTO rsponse=questionnaireService.update(questionAnswer,header);
		//		}
		respnse.setId(response.getId());
		respnse.setGlobalId(response.getGlobalId());
		respnse.setSuccess(true);
		return respnse;
	}


	@Override
	@Transactional
	public ResponseDTO deleteCase(CaseDTO deletePatientCase, HeaderDTO header) {
		ResponseDTO respnse = new ResponseDTO();
		validator.validateDeleteCase(deletePatientCase);
		ResponseDTO response = patientDao.deleteCase(deletePatientCase, header);
		//		String departmentName= patientDao.getDepartmentNameById(deletePatientCase.getDepartmentId());
		//		if(departmentName.trim().equals(DepartmentTypeEnum.Obstetrics.getDisplayName()) && response.getGlobalId()!=0){
		//			 QuestionAnswerDTO questionAnswer=new QuestionAnswerDTO();
		//			 questionAnswer.setCaseId(response.getGlobalId());
		//			 questionAnswer.setDepartmentId(deletePatientCase.getDepartmentId());
		//			 questionAnswer.setAnswerDTO(deletePatientCase.getQuestionAnswerDTO().getAnswerDTO());
		//			 ResponseDTO rsponse=questionnaireService.delete(response.getId(),header);
		//		}
		respnse.setId(response.getId());
		respnse.setGlobalId(response.getGlobalId());
		respnse.setSuccess(true);
		return respnse;
	}


	@Override
	public ResponseDTO createMedicalRecord(MedicalRecordDTO newPatientMedicalRecord, HeaderDTO header) {
		validator.validateMedicalRecord(newPatientMedicalRecord);
		ResponseDTO response = patientDao.createMedicalRecord(newPatientMedicalRecord,header);
		return response;
	}

	@Override
	public ResponseDTO updatePatientMedicalRecord(MedicalRecordDTO updatedMedicalRecord, HeaderDTO header) {
		validator.validateMedicalRecord(updatedMedicalRecord);
		ResponseDTO response = patientDao.updateMedicalRecord(updatedMedicalRecord,header);
		return response;


	}
	@Override
	public ResponseDTO deletePatientMedicalRecord(
			MedicalRecordDTO deleteMedicalRecord, HeaderDTO header) {
		ResponseDTO response = patientDao.deleteMedicalRecord(deleteMedicalRecord,header);
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

	public QuestionnaireService getQuestionnaireService() {
		return questionnaireService;
	}

	public void setQuestionnaireService(QuestionnaireService questionnaireService) {
		this.questionnaireService = questionnaireService;
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

	public void setMailSendAdapter(MailSendAdapter mailSendAdapter) {
		this.mailSendAdapter = mailSendAdapter;
	}

	

	/**
	 * @return the ynwServerIpAddress
	 */
	public String getYnwServerIpAddress() {
		return ynwServerIpAddress;
	}

	/**
	 * @param ynwServerIpAddress the ynwServerIpAddress to set
	 */
	public void setYnwServerIpAddress(String ynwServerIpAddress) {
		this.ynwServerIpAddress = ynwServerIpAddress;
	}

	/**
	 *@author Mani E.V 
	 *return patient Id
	 *if patient no exists then create the patient
	 *return id
	 */
	@Override
	public int getPatient(Patient patient, String source_branch) {
		int patientId = patientDao.getPatientId(patient);
		if(patientId==0)
			patientId = create(patient,source_branch);
		return patientId;
	}
	
	/**
	 * author Mani E.V
	 * @param patient
	 * @param source_branch
	 * @return
	 */
	@Transactional(readOnly=false)
	private int create(Patient patient, String source_branch) {
		LoginDTO login = new LoginDTO();
		String password = StringUtil.getRandomPassword();
		login.setUserName(patient.getAddress().getEmail());
		login.setUserType(NetmdUserTypeEnum.Patient.getDisplayName());
		login.setPassword(StringEncoder.encryptWithKey(password));
		login = patientDao.setLoginInfo(login);
		int patientId = patientDao.create(patient,login);
		sendEmail(Constants.USER_REGISTRATION, patient,login, source_branch, password);
		return patientId;
	}

	/**
	 * @author Mani E.V
	 * @param subject
	 * @param facility
	 * @param login
	 * @param branch
	 * @param password
	 */
	private void sendEmail(String subject, Patient patient, LoginDTO login, String branch, String password) {

		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + ynwServerIpAddress
					+ "/youNeverWait/EmailFormat/NetlimsPatientRegistration.html");
			msgBody = createDefaultEmailBody(url, patient, login,branch, password);

			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody,
					login.getUserName(), mailFrom, 0, 0, null,
					SendMsgCallbackEnum.PATIENT_REGISTRATION.getId(), null);
			mailThread.addSendMsgObj(obj);

		} catch (IOException e) {
			log.error("Error while sending Email to Patient", e);
			e.printStackTrace();

		}
	}
	private String createDefaultEmailBody(URL url, Patient patient, LoginDTO login, String branchName, String password)
			throws IOException {

		StringBuffer msgBodyBfr = new StringBuffer();
		String fullMsgBody = "";
		String encryptedUserName = StringEncoder.encryptWithStaticKey(login.getUserName());
		String resetPasswordLink = "http://" + ynwServerIpAddress
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
		fullMsgBody = fullMsgBody.replace("{patientName}",patient.getName());
		fullMsgBody = fullMsgBody.replace("{branchName}", branchName);
		fullMsgBody = fullMsgBody.replace("{userid}", login.getUserName());
		fullMsgBody = fullMsgBody.replace("{ResetLink}",resetPasswordLink);
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				ynwServerIpAddress);
		return fullMsgBody;
	}
}