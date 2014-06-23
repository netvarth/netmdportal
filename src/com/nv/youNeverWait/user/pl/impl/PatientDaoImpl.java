/**
 * PatientDaoImpl.java
 */
package com.nv.youNeverWait.user.pl.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ActionNameEnum;
import com.nv.youNeverWait.pl.entity.CaseStatusEnum;
import com.nv.youNeverWait.pl.entity.CaseTbl;
import com.nv.youNeverWait.pl.entity.DepartmentTbl;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.LabBranchTbl;
import com.nv.youNeverWait.pl.entity.MedicalRecordTbl;
import com.nv.youNeverWait.pl.entity.NetlimsPatientTbl;
import com.nv.youNeverWait.pl.entity.NetmdBranchTbl;
import com.nv.youNeverWait.pl.entity.NetmdDoctorTbl;
import com.nv.youNeverWait.pl.entity.NetmdLoginTbl;
import com.nv.youNeverWait.pl.entity.NetmdPassphraseTbl;
import com.nv.youNeverWait.pl.entity.NetmdTbl;
import com.nv.youNeverWait.pl.entity.NetmdUserTbl;
import com.nv.youNeverWait.pl.entity.PatientTbl;
import com.nv.youNeverWait.pl.entity.PatientTypeEnum;
import com.nv.youNeverWait.pl.entity.ResultTbl;
import com.nv.youNeverWait.pl.entity.NetmdPatientTbl;
import com.nv.youNeverWait.pl.entity.UserTypeEnum;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.CaseDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.MedicalRecordDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.Patient;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.PatientOrderDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultDTO;
import com.nv.youNeverWait.rs.dto.RetrievalPatientResponseDTO;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.PatientDao;

/**
 * @author Luciya Jose
 * 
 */
public class PatientDaoImpl extends GenericDaoHibernateImpl implements
		PatientDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8786538233361459494L;
	@PersistenceContext()
	private EntityManager em;

	/**
	 * Retrieve patients for NetMd
	 */
	public RetrievalPatientResponseDTO retrievePatientsForNetMd(
			String lastTime, String passPhrase, int netMdBranchId,
			Date currentSyncTime) {
		RetrievalPatientResponseDTO retrievalPatientResponseDTO = new RetrievalPatientResponseDTO();
		List<PatientDetail> retrieveedPatients = new ArrayList<PatientDetail>();
		int netmdPassphraseId = getNetmdPassphrase(passPhrase, netMdBranchId);
		if (netmdPassphraseId == 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		Date lastSyncTime = null;
		DateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		try {
			lastSyncTime = df.parse(lastTime);

		} catch (ParseException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}

		// Retrieve patients
		List<NetmdPatientTbl> patients = retrievePatients(lastSyncTime,
				netmdPassphraseId, netMdBranchId, currentSyncTime);
		for (NetmdPatientTbl patientObj : patients) {
			PatientDetail patientDetail = new PatientDetail(patientObj);
			retrieveedPatients.add(patientDetail);
		}
		retrievalPatientResponseDTO.setRetrievePatients(retrieveedPatients);
		return retrievalPatientResponseDTO;
	}

	/**
	 * 
	 * @param loginId
	 * @return NetmdUserTbl
	 */
	public NetmdUserTbl getNetMdUserByLoginId(int loginId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETMD_USER);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(NetmdUserTbl.class, query);
	}

	/**
	 * 
	 * @param loginId
	 * @return NetmdTbl
	 */
	public NetmdTbl getNetMdByLoginId(int loginId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_BY_LOGIN_ID);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(NetmdTbl.class, query);
	}

	/**
	 * 
	 * @param userName
	 * @return NetmdLoginTbl
	 */
	public NetmdLoginTbl getNetMdUserByName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_LOGIN_PATIENT_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(NetmdLoginTbl.class, query);
	}

	/**
	 * Retrieve new patients
	 * 
	 * @param lastSyncTime
	 * @param passPhrase
	 * @param netMdBranchId
	 * @return List<PatientTbl>
	 */
	private List<NetmdPatientTbl> retrievePatients(Date lastSyncTime,
			int passPhrase, int netMdBranchId, Date currentSyncTime) {
		javax.persistence.Query query = em.createQuery(Query.RETRIEVE_PATIENTS);
		query.setParameter("param1", lastSyncTime);
		query.setParameter("param2", currentSyncTime);
		query.setParameter("param3", netMdBranchId);
		query.setParameter("param4", passPhrase);
		return executeQuery(NetmdPatientTbl.class, query);

	}

	/**
	 * Method for getting netmd passphrase id
	 * 
	 * @param passPhrase
	 * @param netmdBranchId
	 * @return netmd passphrase id
	 */
	public Integer getNetmdPassphrase(String passPhrase, int netmdBranchId) {
		int count = 0;
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_PASSPHRASE_ID);
		query.setParameter("param1", passPhrase);
		query.setParameter("param2", netmdBranchId);
		count = (Integer) query.getSingleResult();
		return count;
	}

	/**
	 * Method to create a patient while synchronizing from netmd
	 * 
	 * @param patient
	 * @param header
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO createPatient(PatientDetail patient, HeaderDTO header) {
		ResponseDTO response = new ResponseDTO();
		DateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		NetmdPassphraseTbl netmdBranchPassphrase = (NetmdPassphraseTbl) getBranchByPassPhrase(
				header.getPassPhrase(), header.getMacId());
		if (netmdBranchPassphrase == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdAccount);
			se.addParam(new Parameter(Constants.ID, Integer.toString(header
					.getHeadOfficeId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getBranchId() != netmdBranchPassphrase.getNetmdBranchTbl()
				.getId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BranchMissMatch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(header
					.getBranchId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getHeadOfficeId() != netmdBranchPassphrase
				.getNetmdBranchTbl().getNetmdTbl().getId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BranchMissMatch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(header
					.getHeadOfficeId())));
			se.setDisplayErrMsg(true);
			throw se;
		}

		// check if patient already exists (first name,email and branch)
		NetmdPatientTbl patientTbl = (NetmdPatientTbl) getPatientByEmail(patient
				.getEmail().trim(), patient.getFirstName(),
				patient.getBranchId());
		if (patientTbl != null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PatientExists);
			se.addParam(new Parameter(Constants.NAME, patient.getFirstName()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetmdPatientTbl newPatient = new NetmdPatientTbl();
		NetmdLoginTbl loginTbl = new NetmdLoginTbl();
		if (patient.getEmail() != null && !patient.getEmail().isEmpty()) {
			NetmdLoginTbl loginObj = getLoginByUserName(patient.getEmail());
			if (loginObj != null) {// if the userid exists in Netmdlogintbl,
									// take it
									// and set it to patient and save patient
				NetmdPatientTbl patientObj = getPatient(patient.getFirstName(),
						loginObj.getId(), header.getBranchId());
				if (patientObj != null) {// if the patient exists in the same
											// branch
											// throw error
					ServiceException se = new ServiceException(
							ErrorCodeEnum.UserExists);
					se.setDisplayErrMsg(true);
					throw se;
				} else {// else if he doesn't exists. set login obj to the new
						// patient.
					newPatient.setNetmdLoginTbl(loginObj);
				}
			} else {
				loginTbl.setUserName(patient.getEmail());
				loginTbl.setUserType(UserTypeEnum.Patient.getDisplayName());// user
																			// type
																			// corresponding
																			// to
																			// patient
				//
				save(loginTbl);
				newPatient.setNetmdLoginTbl(loginTbl);
			}
		}

		newPatient.setFirstName(patient.getFirstName());
		newPatient.setLastName(patient.getLastName());
		newPatient.setGender(patient.getGender());
		newPatient.setAge(patient.getAge());
		newPatient.setMobile(patient.getMobile());
		newPatient.setPhone(patient.getPhone());
		newPatient.setAddress(patient.getAddress());
		newPatient.setEmail(patient.getEmail());
		newPatient.setCreateDateTime(new Date());
		newPatient.setUpdateDateTime(new Date());
		newPatient.setStatus(Constants.ACTIVE);
		newPatient.setAilment(patient.getAilment());
		newPatient.setDiagnosis(patient.getDiagnosis());
		newPatient.setBloodGroup(patient.getBloodGroup());
		newPatient.setHeight(patient.getHeight());
		newPatient.setWeight(patient.getWeight());
		newPatient.setChronicDisease(patient.getChronicDisease());
		newPatient.setAllergies(patient.getAllergies());
		newPatient.setFamilyHistory(patient.getFamilyHistory());
		newPatient.setEmergencyNo(patient.getEmergencyNo());
		NetmdBranchTbl netmdbranchTbl = (NetmdBranchTbl) getById(
				NetmdBranchTbl.class, header.getBranchId());
		if (netmdbranchTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.NobranchExists);
			se.setDisplayErrMsg(true);
			throw se;
		} else {
			newPatient.setNetmdBranchTbl(netmdbranchTbl);
		}
		newPatient.setNetmdPassphraseTbl(netmdBranchPassphrase);
		save(newPatient);

		response.setSuccess(true);
		response.setGlobalId(newPatient.getId());// global id
		response.setId(patient.getId());// local id
		response.setCreateDateTime(sdf.format(newPatient.getCreateDateTime()));
		return response;

	}

	/**
	 * get patient by patient id and branchid
	 * 
	 * @param id
	 * @param branchId
	 * @return
	 */
	private NetmdPatientTbl getPatient(String firstName, int id, int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_WITH_LOGIN);
		query.setParameter("param1", id);
		query.setParameter("param2", branchId);
		query.setParameter("param3", firstName);
		return executeUniqueQuery(NetmdPatientTbl.class, query);
	}

	/**
	 * update a patient
	 * 
	 * @param patient
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO updatePatient(PatientDetail patient, HeaderDTO header) {
		ResponseDTO response = new ResponseDTO();
		DateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);

		NetmdPassphraseTbl netmdBranch = (NetmdPassphraseTbl) getBranchByPassPhrase(
				header.getPassPhrase(), header.getMacId());
		if (netmdBranch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdAccount);
			se.addParam(new Parameter(Constants.ID, Integer.toString(header
					.getHeadOfficeId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getBranchId() != netmdBranch.getNetmdBranchTbl().getId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BranchMissMatch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(header
					.getBranchId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getHeadOfficeId() != netmdBranch.getNetmdBranchTbl()
				.getNetmdTbl().getId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BranchMissMatch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(header
					.getHeadOfficeId())));
			se.setDisplayErrMsg(true);
			throw se;
		}

		int globalId = patient.getGlobalId();
		NetmdPatientTbl patientTbl = (NetmdPatientTbl) getById(NetmdPatientTbl.class, globalId);
		if (patientTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (patient.getEmail() != null && !patient.getEmail().isEmpty()) {
			NetmdLoginTbl patientLogin = getLoginByUserName(patient.getEmail());
			if(patientLogin==null){
				NetmdLoginTbl login= new NetmdLoginTbl();
				login.setUserName(patient.getEmail().trim());
				login.setUserType(UserTypeEnum.Patient.getDisplayName());
				save(login);
				patientTbl.setNetmdLoginTbl(login);
			}
			else{
			patientTbl.setNetmdLoginTbl(patientLogin);
			}

		}

		patientTbl.setFirstName(patient.getFirstName());
		patientTbl.setLastName(patient.getLastName());
		patientTbl.setGender(patient.getGender());
		patientTbl.setAge(patient.getAge());
		patientTbl.setAddress(patient.getAddress());
		patientTbl.setPhone(patient.getPhone());
		patientTbl.setMobile(patient.getMobile());
		patientTbl.setEmail(patient.getEmail());
		patientTbl.setUpdateDateTime(new Date());
		patientTbl.setStatus(patient.getStatus());
		patientTbl.setAilment(patient.getAilment());
		patientTbl.setDiagnosis(patient.getDiagnosis());
		patientTbl.setBloodGroup(patient.getBloodGroup());
		patientTbl.setHeight(patient.getHeight());
		patientTbl.setWeight(patient.getWeight());
		patientTbl.setChronicDisease(patient.getChronicDisease());
		patientTbl.setAllergies(patient.getAllergies());
		patientTbl.setFamilyHistory(patient.getFamilyHistory());
		patientTbl.setEmergencyNo(patient.getEmergencyNo());
		update(patientTbl);

		response.setSuccess(true);
		response.setGlobalId(patientTbl.getId());// global id
		response.setId(patient.getId());// local id
		response.setUpdateDateTime(sdf.format(patientTbl.getUpdateDateTime()));
		return response;
	}

	public String getBranch(int branchId) {
		NetmdBranchTbl branch = (NetmdBranchTbl) getById(NetmdBranchTbl.class,
				branchId);
		return branch.getName();
	}

	/**
	 * view details of a patient
	 * 
	 * @param globalId
	 * @return PatientDTO
	 */
	public PatientDetail viewPatient(int globalId) {
		PatientDetail response = new PatientDetail();
		NetmdPatientTbl patientTbl = (NetmdPatientTbl) getById(NetmdPatientTbl.class, globalId);
		if (patientTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidGlobalId);
			se.setDisplayErrMsg(true);
			throw se;
		}

		LoginDTO login = new LoginDTO();
		login.setUserName(patientTbl.getNetmdLoginTbl().getUserName());
		login.setPassword(patientTbl.getNetmdLoginTbl().getPassword());
		login.setUserType(patientTbl.getNetmdLoginTbl().getUserType());
		response.setLogin(login);
		response.setFirstName(patientTbl.getFirstName());
		response.setLastName(patientTbl.getLastName());
		response.setGender(patientTbl.getGender());
		response.setAge(patientTbl.getAge());
		response.setAddress(patientTbl.getAddress());
		response.setPhone(patientTbl.getPhone());
		response.setMobile(patientTbl.getMobile());
		response.setEmail(patientTbl.getEmail());
		response.setAilment(patientTbl.getAilment());
		response.setDiagnosis(patientTbl.getDiagnosis());
		response.setBloodGroup(patientTbl.getBloodGroup());
		response.setHeight(patientTbl.getHeight());
		response.setWeight(patientTbl.getWeight());
		response.setChronicDisease(patientTbl.getChronicDisease());
		response.setAllergies(patientTbl.getAllergies());
		response.setFamilyHistory(patientTbl.getFamilyHistory());
		response.setEmergencyNo(patientTbl.getEmergencyNo());
		response.setStatus(patientTbl.getStatus());
		response.setSuccess(true);
		return response;
	}

	/**
	 * deletes patient
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@Transactional(readOnly = false)
	@Override
	public ResponseDTO deletePatient(int globalId) {
		ResponseDTO response = new ResponseDTO();
		NetmdPatientTbl patientTbl = (NetmdPatientTbl) getById(NetmdPatientTbl.class, globalId);
		patientTbl.setStatus(Constants.INACTIVE);
		update(patientTbl);
		response.setGlobalId(globalId);
		response.setSuccess(true);
		return response;
	}

	/**
	 * To retrieve a result corresponding to the orderId and patient given
	 * 
	 * @param patient
	 * @return ResultDTO
	 */
	@Override
	@Transactional
	public ResultDTO patientTestResult(PatientOrderDTO patient) {
		ResultDTO response = new ResultDTO();
		ResultTbl patientResults = getPatientResultsByPatientOrderId(
				patient.getPatientId(), patient.getOrderId());
		if (patientResults == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PatientResultEmpty);
			se.addParam(new Parameter(Constants.ID, patient.getOrderId()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setTestResult(patientResults.getResult());
		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.PatientDao#createCase(com.nv.youNeverWait
	 * .rs.dto.CaseDTO, com.nv.youNeverWait.rs.dto.HeaderDTO)
	 */
	@Override
	@Transactional
	public ResponseDTO createCase(CaseDTO newPatientCase, HeaderDTO header) {
		ResponseDTO caseResponse = new ResponseDTO();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);

		NetmdPatientTbl patientTbl = getById(NetmdPatientTbl.class,
				newPatientCase.getPatientId());

		if (patientTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPatientId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		DepartmentTbl dept = getById(DepartmentTbl.class,
				newPatientCase.getDepartmentId());
		if (dept == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDepartmentId);
			se.setDisplayErrMsg(true);
			throw se;
		}

		Date currentDate = new Date();
		/* Saving case for the patient in case tbl */
		CaseTbl caseTbl = new CaseTbl();
		caseTbl.setCaseName(newPatientCase.getCaseName());
		caseTbl.setNetmdPatientTbl(patientTbl);
		if (newPatientCase.getPatientType().equals(
				PatientTypeEnum.InPatient.getDisplayName())) {
			caseTbl.setPatientType(PatientTypeEnum.InPatient.getDisplayName());
		}
		if (newPatientCase.getPatientType().equals(
				PatientTypeEnum.OutPatient.getDisplayName())) {
			caseTbl.setPatientType(PatientTypeEnum.OutPatient.getDisplayName());
		}
		if (newPatientCase.getAdmittedDate() != null) {
			try {
				caseTbl.setAdmittedDate(sdf.parse(newPatientCase
						.getAdmittedDate()));
			} catch (ParseException e) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		caseTbl.setBmi(newPatientCase.getBmi());
		caseTbl.setDepartmentTbl(dept);
		caseTbl.setDescription(newPatientCase.getDescription());
		caseTbl.setHb(newPatientCase.getHbCount());
		caseTbl.setWeight(newPatientCase.getWeight());
		caseTbl.setHeight(newPatientCase.getHeight());
		caseTbl.setStatus(CaseStatusEnum.Open.getDisplayName());
		caseTbl.setLocalAnswersetId(newPatientCase.getAnswerSetId());
		caseTbl.setLocalCaseId(newPatientCase.getId());
		caseTbl.setCreatedDateTime(currentDate);
		caseTbl.setUpdatedDateTime(currentDate);
		save(caseTbl);

		caseResponse.setGlobalId(caseTbl.getId());
		caseResponse.setId(newPatientCase.getId());
		caseResponse.setSuccess(true);
		return caseResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.PatientDao#updateCase(com.nv.youNeverWait
	 * .rs.dto.CaseDTO, com.nv.youNeverWait.rs.dto.HeaderDTO)
	 */
	@Override
	@Transactional
	public ResponseDTO updateCase(CaseDTO updatedPatientCase, HeaderDTO header) {
		ResponseDTO caseResponse = new ResponseDTO();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);

		CaseTbl caseTbl = getById(CaseTbl.class,
				updatedPatientCase.getGlobalId());
		if (caseTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidCaseId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetmdPatientTbl patientTbl = getById(NetmdPatientTbl.class,
				updatedPatientCase.getPatientId());

		if (patientTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPatientId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		DepartmentTbl dept = getById(DepartmentTbl.class,
				updatedPatientCase.getDepartmentId());
		if (dept == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDepartmentId);
			se.setDisplayErrMsg(true);
			throw se;
		}

		Date currentDate = new Date();
		/* Updating case for the patient in case tbl */
		if (updatedPatientCase.getActionName().equals(
				ActionNameEnum.UPDATE.getDisplayName())) {
			caseTbl.setCaseName(updatedPatientCase.getCaseName());
			caseTbl.setNetmdPatientTbl(patientTbl);
			if (updatedPatientCase.getPatientType().equals(
					PatientTypeEnum.InPatient.getDisplayName())) {
				caseTbl.setPatientType(PatientTypeEnum.InPatient
						.getDisplayName());
			}
			if (updatedPatientCase.getPatientType().equals(
					PatientTypeEnum.OutPatient.getDisplayName())) {
				caseTbl.setPatientType(PatientTypeEnum.OutPatient
						.getDisplayName());
			}
			if (updatedPatientCase.getAdmittedDate() != null) {
				try {
					caseTbl.setAdmittedDate(sdf.parse(updatedPatientCase
							.getAdmittedDate()));
				} catch (ParseException e) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.InvalidDateFormat);
					se.setDisplayErrMsg(true);
					throw se;
				}
			}
			caseTbl.setBmi(updatedPatientCase.getBmi());
			caseTbl.setDepartmentTbl(dept);
			caseTbl.setHeight(updatedPatientCase.getHeight());
			caseTbl.setDescription(updatedPatientCase.getDescription());
			caseTbl.setHb(updatedPatientCase.getHbCount());
			caseTbl.setWeight(updatedPatientCase.getWeight());
			if (updatedPatientCase.getStatus() != null
					&& !updatedPatientCase.getStatus().isEmpty()) {
				if (updatedPatientCase.getStatus().equals(
						CaseStatusEnum.Open.getDisplayName())) {
					caseTbl.setStatus(CaseStatusEnum.Open.getDisplayName());
				}
				if (updatedPatientCase.getStatus().equals(
						CaseStatusEnum.Closed.getDisplayName())) {
					caseTbl.setStatus(CaseStatusEnum.Closed.getDisplayName());
				}
			}

		}
		if (updatedPatientCase.getActionName().equals(
				ActionNameEnum.DELETE.getDisplayName())) {
			caseTbl.setStatus(CaseStatusEnum.Closed.getDisplayName());
		}
		caseTbl.setUpdatedDateTime(currentDate);
		update(caseTbl);
		caseResponse.setGlobalId(caseTbl.getId());
		caseResponse.setId(updatedPatientCase.getId());
		caseResponse.setSuccess(true);
		return caseResponse;
	}
	
	

	@Override
	@Transactional
	public ResponseDTO deleteCase(CaseDTO deletePatientCase, HeaderDTO header) {
		ResponseDTO caseResponse = new ResponseDTO();
		CaseTbl caseTbl = getById(CaseTbl.class,
				deletePatientCase.getGlobalId());
		if (caseTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidCaseId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetmdPatientTbl patientTbl = getById(NetmdPatientTbl.class,
				deletePatientCase.getPatientId());

		if (patientTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPatientId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		DepartmentTbl dept = getById(DepartmentTbl.class,
				deletePatientCase.getDepartmentId());
		if (dept == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDepartmentId);
			se.setDisplayErrMsg(true);
			throw se;
		}

		Date currentDate = new Date();
		caseTbl.setStatus(CaseStatusEnum.Closed.getDisplayName());
		caseTbl.setUpdatedDateTime(currentDate);
		update(caseTbl);
		caseResponse.setGlobalId(caseTbl.getId());
		caseResponse.setId(deletePatientCase.getId());
		caseResponse.setSuccess(true);
		return caseResponse;
		
	}

	@Override
	@Transactional
	public boolean isEmailExists(int id, String email) {
		boolean flag = true;
		NetmdPatientTbl patientTbl = getById(NetmdPatientTbl.class, id);
		if (patientTbl != null) {
			if (patientTbl.getEmail() == null
					|| patientTbl.getEmail().isEmpty()) {
				flag = false;
			} else {
				if (patientTbl.getEmail().equals(email)) {
					flag = true;
				} else {
					flag = false;
				}
			}

		}
		return flag;
	}

	private ResultTbl getPatientResultsByPatientOrderId(int patientId,
			String orderId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_RESULTS_BY_ORDERID);
		query.setParameter("param1", patientId);
		query.setParameter("param2", orderId);
		return executeUniqueQuery(ResultTbl.class, query);
	}


	public NetmdPassphraseTbl getBranchByPassPhrase(String passPhrase,
			String macId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_BRANCH_ID);
		query.setParameter("param1", passPhrase);
		query.setParameter("param2", macId);
		return executeUniqueQuery(NetmdPassphraseTbl.class, query);
	}

	/**
	 * @param netMdId
	 * @param passPhrase
	 * @return NetmdPassphraseTbl
	 */
	public NetmdPassphraseTbl getNetmdByPassphrase(int netMdId,
			String passPhrase) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_BY_PASSPHRASE);
		query.setParameter("param1", netMdId);
		query.setParameter("param2", passPhrase);
		return executeUniqueQuery(NetmdPassphraseTbl.class, query);
	}

	/**
	 * @param firstName
	 * @param phone
	 * @return PatientTbl
	 */
	public NetmdPatientTbl getPatientByName(String firstName, String phone) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_NAME);
		query.setParameter("param1", firstName);
		query.setParameter("param2", phone);
		return executeUniqueQuery(NetmdPatientTbl.class, query);
	}

	/**
	 * @param email
	 * @param firstName
	 * @return PatientTbl
	 */
	public NetmdPatientTbl getPatientByEmail(String email, String firstName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_EMAIL_FIRSTNAME);
		query.setParameter("param1", email);
		query.setParameter("param2", firstName);
		return executeUniqueQuery(NetmdPatientTbl.class, query);
	}

	/**
	 * @param email
	 * @param firstName
	 * @param branchId
	 * @return PatientTbl
	 */
	public NetmdPatientTbl getPatientByEmail(String email, String firstName,
			int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_EMAIL_FIRSTNAME_BRANCH);
		query.setParameter("param1", email);
		query.setParameter("param2", firstName);
		query.setParameter("param3", branchId);
		return executeUniqueQuery(NetmdPatientTbl.class, query);
	}

	/**
	 * @param email
	 * @return patient
	 */
	public NetmdPatientTbl getPatientByEmail(String email) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_EMAIL);
		query.setParameter("param1", email);
		return executeUniqueQuery(NetmdPatientTbl.class, query);
	}

	/**
	 * @param password
	 * @param loginId
	 * @return NetmdLoginTbl
	 */
	public NetmdLoginTbl getUser(String password, String loginId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_EMAIL);
		query.setParameter("param1", password);
		query.setParameter("param2", loginId);
		return executeUniqueQuery(NetmdLoginTbl.class, query);
	}

	@Override
	public List<NetmdPatientTbl> listOfPatientsOnLogin(String patientEmailId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_LIST_BY_EMAILID);
		query.setParameter("param1", patientEmailId);
		return executeQuery(NetmdPatientTbl.class, query);
	}

	@Override
	public List<NetmdDoctorTbl> listDoctors(String clinicId, String patientId) {
		return null;
	}

	@Override
	@Transactional
	public String getDepartmentNameById(int departmentId) {
		String departmentName = "";
		javax.persistence.Query query = em
				.createQuery(Query.GET_DEPARTMENT_NAME_BY_ID);
		query.setParameter("param1", departmentId);
		departmentName = (String) query.getSingleResult();
		return departmentName;
	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em
	 *            the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional
	public ResponseDTO createMedicalRecord(MedicalRecordDTO newPatientMedicalRecord, HeaderDTO header) {
		ResponseDTO medicalCaseResponse = new ResponseDTO();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);

		NetmdPatientTbl patientTbl = getById(NetmdPatientTbl.class,
				newPatientMedicalRecord.getPatientId());

		if (patientTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPatientId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		CaseTbl casetbl = getById(CaseTbl.class,
				newPatientMedicalRecord.getCaseId());
		if (casetbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidCaseId);
			se.setDisplayErrMsg(true);
			throw se;
		}
        
		NetmdDoctorTbl doctorTbl = getById(NetmdDoctorTbl.class,
				newPatientMedicalRecord.getDoctorId());

		if (doctorTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDoctorId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		Date currentDate = new Date();
		/* Saving medicalRecord for the patient in medical Record Tbl */
		MedicalRecordTbl medicalRecordTbl = new MedicalRecordTbl();
		medicalRecordTbl.setMedicalRecord(newPatientMedicalRecord.getMedicalRecord());
		medicalRecordTbl.setNetmdPatientTbl(patientTbl);
		medicalRecordTbl.setNetmdDoctorTbl(doctorTbl);
		medicalRecordTbl.setCaseTbl(casetbl);
		//MedicalRecordTypeEnum medical = MedicalRecordTypeEnum.getEnum(newPatientMedicalRecord.getType());
		//medicalRecordTbl.setType(MedicalRecordTypeEnum.getEnum(newPatientMedicalRecord.getType()).getDisplayName());
		medicalRecordTbl.setType(newPatientMedicalRecord.getType());
		medicalRecordTbl.setStatus(Constants.ACTIVE);
		medicalRecordTbl.setCreatedDateTime(currentDate);
		medicalRecordTbl.setUpdateDateTime(currentDate);
		save(medicalRecordTbl);
		medicalCaseResponse.setGlobalId(medicalRecordTbl.getId());
		medicalCaseResponse.setId(newPatientMedicalRecord.getId());
		medicalCaseResponse.setSuccess(true);
		return medicalCaseResponse;
	
	}

	@Override
	@Transactional
	public ResponseDTO updateMedicalRecord(MedicalRecordDTO updatedMedicalRecord, HeaderDTO header) {
		ResponseDTO medicalRecordResponse = new ResponseDTO();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);

		MedicalRecordTbl medicalRecordTbl = getById(MedicalRecordTbl.class,
				updatedMedicalRecord.getGlobalId());
		if (medicalRecordTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidMedicalRecordId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetmdDoctorTbl doctorTbl=getById(NetmdDoctorTbl.class,updatedMedicalRecord.getDoctorId());
		if(doctorTbl==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDoctorId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		NetmdPatientTbl patientTbl = getById(NetmdPatientTbl.class,
				updatedMedicalRecord.getPatientId());

		if (patientTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPatientId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		CaseTbl caseTbl = getById(CaseTbl.class,
				updatedMedicalRecord.getCaseId());
		if (caseTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDepartmentId);
			se.setDisplayErrMsg(true);
			throw se;
		}

		Date currentDate = new Date();
		
		medicalRecordTbl.setMedicalRecord(updatedMedicalRecord.getMedicalRecord());
		medicalRecordTbl.setNetmdPatientTbl(patientTbl);
		medicalRecordTbl.setCaseTbl(caseTbl);
		medicalRecordTbl.setCreatedDateTime(currentDate);
		medicalRecordTbl.setUpdateDateTime(currentDate);
		//MedicalRecordTypeEnum medical = MedicalRecordTypeEnum.getEnum(updatedMedicalRecord.getType());
		//medicalRecordTbl.setType(MedicalRecordTypeEnum.getEnum(updatedMedicalRecord.getType()).getDisplayName());
		medicalRecordTbl.setType(updatedMedicalRecord.getType());
		medicalRecordTbl.setMedicalRecord(updatedMedicalRecord.getMedicalRecord());
		medicalRecordTbl.setNetmdDoctorTbl(doctorTbl);
		update(medicalRecordTbl);
		medicalRecordResponse.setGlobalId(medicalRecordTbl.getId());
		medicalRecordResponse.setId(updatedMedicalRecord.getId());
		medicalRecordResponse.setSuccess(true);
		return medicalRecordResponse;
	}


	@Override
	@Transactional
	public ResponseDTO deleteMedicalRecord(MedicalRecordDTO deleteMedicalRecord, HeaderDTO header) {
		ResponseDTO medicalRecordResponse = new ResponseDTO();
		MedicalRecordTbl medicalRecordTbl = getById(MedicalRecordTbl.class,
				deleteMedicalRecord.getGlobalId());
		if (medicalRecordTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidMedicalRecordId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetmdPatientTbl patientTbl = getById(NetmdPatientTbl.class,
				deleteMedicalRecord.getPatientId());

		if (patientTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPatientId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		CaseTbl caseTbl = getById(CaseTbl.class,
				deleteMedicalRecord.getCaseId());
		if (caseTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDepartmentId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		medicalRecordTbl.setStatus(CaseStatusEnum.Closed.getDisplayName());
		update(medicalRecordTbl);
		medicalRecordResponse.setGlobalId(medicalRecordTbl.getId());
		medicalRecordResponse.setId(deleteMedicalRecord.getId());
		medicalRecordResponse.setSuccess(true);
		return medicalRecordResponse;
	}
	/**
	 * @param userName
	 * @return NetmdLoginTbl
	 */
	public NetmdLoginTbl getLoginByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(NetmdLoginTbl.class, query);
	}

	/**
	 * @param userName
	 * @param firstName
	 * @return List<NetmdLoginTbl>
	 */
	public List<NetmdLoginTbl> getLoginByUserName(String userName,
			String firstName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_LOGIN_BY_USERNAME_FIRSTNAME);
		query.setParameter("param1", userName);
		query.setParameter("param2", firstName);
		return executeQuery(NetmdLoginTbl.class, query);
	}

	/**
	 * @param passPhrase
	 * @param macId
	 * @return NetmdPassphraseTbl
	 */
	@Override
	public int getPatient(Patient patient, int branchId) {
		PatientTbl patientTbl = getPatientByEmailId(patient.getAddress().getEmail());
		if(patientTbl==null){
			patientTbl=new PatientTbl();
			patientTbl.setCreateDateTime(new Date());
		}
		patientTbl.setFirstName(patient.getName());
		patientTbl.setAddress(patient.getAddress().getPrimaryAddress());
		patientTbl.setGender(patient.getGender());
		//patientTbl.setAge(Integer.parseInt(patient.getAge()));
		patientTbl.setEmail(patient.getAddress().getEmail());
		patientTbl.setMobile(patient.getAddress().getMobile());
		patientTbl.setPhone(patient.getAddress().getPhone());
		patientTbl.setUpdateDateTime(new Date());
		
		NetlimsPatientTbl netlimsPatientTbl = new NetlimsPatientTbl();
		netlimsPatientTbl.setPatientTbl(patientTbl);
		LabBranchTbl labBranchTbl = new LabBranchTbl();
		labBranchTbl.setId(branchId);
		netlimsPatientTbl.setLabBranchTbl(labBranchTbl);
		
		
		saveOrUpdate(PatientTbl.class,patientTbl);
		return netlimsPatientTbl.getId();
	}

	private NetmdPatientTbl getByPatientEmail(String email) {
		javax.persistence.Query query = em.createQuery(Query.GET_PATIENT_BY_EMAIL);
		query.setParameter("param1", email);
		return executeUniqueQuery(NetmdPatientTbl.class, query);
	}
	
	private PatientTbl getPatientByEmailId(String email) {
		javax.persistence.Query query = em.createQuery(Query.GET_PATIENT_BY_EMAIL);
		query.setParameter("param1", email);
		return executeUniqueQuery(PatientTbl.class, query);
	}
}