/**
 * AuthenticationDaoImpl.java
 *
 * Dec 3, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.security.pl.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.nv.youNeverWait.rs.dto.Error;
import com.nv.youNeverWait.rs.dto.ErrorCodeListResponseDTO;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.DoctorTbl;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.LabTbl;
import com.nv.youNeverWait.pl.entity.LabUserTbl;
import com.nv.youNeverWait.pl.entity.LabLoginTbl;
import com.nv.youNeverWait.pl.entity.LabUserTypeEnum;
import com.nv.youNeverWait.pl.entity.NetmdLoginTbl;
import com.nv.youNeverWait.pl.entity.NetmdTbl;
import com.nv.youNeverWait.pl.entity.NetmdUserTbl;
import com.nv.youNeverWait.pl.entity.NetmdUserTypeEnum;
import com.nv.youNeverWait.pl.entity.NetrxLoginTbl;
import com.nv.youNeverWait.pl.entity.NetrxTbl;
import com.nv.youNeverWait.pl.entity.NetrxUserTbl;
import com.nv.youNeverWait.pl.entity.PatientTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.UserDetails;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.security.pl.dao.AuthenticationDao;

public class AuthenticationDaoImpl extends GenericDaoHibernateImpl implements
AuthenticationDao {
	@PersistenceContext()
	private EntityManager em;

	/**
	 * Retrieves list of error messages
	 * 
	 * @return ErrorCodeListResponseDTO
	 */
	public ErrorCodeListResponseDTO getErrorCodes() {

		ErrorCodeListResponseDTO errorCodeList = new ErrorCodeListResponseDTO();
		List<Error> errorList = new ArrayList<Error>();
		for (ErrorCodeEnum enumval : ErrorCodeEnum.values()) {
			Error error = new Error();
			error.setErrCode(enumval.getErrCode());
			error.setErrMsg(enumval.getErrMsg());
			errorList.add(error);
		}
		errorCodeList.setError(errorList);
		return errorCodeList;
	}

	/**
	 * Method performed for NetLims login
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 */
	@Override
	@Transactional(readOnly = true)
	public LoginResponseDTO netlimsLogin(LoginDTO loginDTO) {

		LoginResponseDTO login = new LoginResponseDTO();
		LabLoginTbl loginDetails = getUserByUserNameAndPassword(
				loginDTO.getPassword(), loginDTO.getUserName());
		if(loginDetails==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		login.setSuccess(true);
		return login;

	}

	/**
	 * Method performed for NetMd login
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 */
	@Override
	@Transactional
	public LoginResponseDTO netmdLogin(LoginDTO loginDTO) {

		LoginResponseDTO login = new LoginResponseDTO();
		NetmdLoginTbl loginDetails = (NetmdLoginTbl) getNetMdUserByUserNameAndPassword(
				loginDTO.getPassword(), loginDTO.getUserName());
		if(loginDetails==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		login.setSuccess(true);
		return login;
	}
	/**
	 * Method performed for Netrx login
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 */
	@Override
	@Transactional
	public LoginResponseDTO netrxLogin(LoginDTO loginDTO) {

		LoginResponseDTO login = new LoginResponseDTO();
		NetrxLoginTbl loginDetails = (NetrxLoginTbl) getNetRxUserByUserNameAndPassword(
				loginDTO.getPassword(), loginDTO.getUserName());
		if(loginDetails==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNull);
			se.setDisplayErrMsg(true);
			throw se;
		}

		login.setSuccess(true);
		return login;
	}
	/**
	 * Method performed for patient login
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 */
	@Override
	@Transactional
	public LoginResponseDTO patientLogin(LoginDTO loginDTO) {

		LoginResponseDTO login = new LoginResponseDTO();
		NetmdLoginTbl loginDetails = getPatientByUserNameAndPassword(
				loginDTO.getPassword(), loginDTO.getUserName());
		if(loginDetails==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		login.setSuccess(true);
		return login;
	}

	/**
	 * Method performed to get Netlims user details
	 * 
	 * @param userName
	 * @return UserDetails
	 */
	@Override
	@Transactional
	public UserDetails getNetlimsUser(String userName) {

		LabLoginTbl netlimsLogin = (LabLoginTbl) getNetlimsLoginByUserName(userName);
		if (netlimsLogin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.LoginNotExists);
			se.addParam(new Parameter(Constants.NAME, userName));
			se.setDisplayErrMsg(true);
			throw se;
		}
		LabUserTypeEnum userType = LabUserTypeEnum.getEnum(netlimsLogin
				.getUserType());
		if (netlimsLogin.getUserType().equals(
				LabUserTypeEnum.Owner.getDisplayName())) {
			LabTbl labOwner = (LabTbl) getOwnerByLogin(netlimsLogin.getId());
			if (labOwner != null) {
				UserDetails user = new UserDetails();
				user.setId(labOwner.getId());
				user.setName(labOwner.getOwnerFirstName());
				user.setLabId(labOwner.getId());
				user.setUserType(userType.getDisplayName());
				return user;
			}
		} else {
			LabUserTbl labUser = (LabUserTbl) getNetlimsUserByLogin(netlimsLogin
					.getId());
			if (labUser != null) {
				UserDetails user = new UserDetails();
				user.setId(labUser.getId());
				user.setName(labUser.getFirstName());
				user.setUserType(userType.getDisplayName());
				return user;
			}
		}
		return null;
	}

	/**
	 * Method performed to get Netmd user details
	 * 
	 * @param userName
	 * @return UserDetails
	 */
	@Override
	@Transactional
	public UserDetails getNetmdUser(String userName) {
		NetmdLoginTbl netMdLogin = (NetmdLoginTbl) getNetMdLoginByUserName(userName
				.trim());
		if (netMdLogin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.LoginNotExists);
			se.addParam(new Parameter(Constants.NAME, userName));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetmdUserTypeEnum userType = NetmdUserTypeEnum.getEnum(netMdLogin
				.getUserType());
		if (netMdLogin.getUserType().equals(NetmdUserTypeEnum.Doctor.getDisplayName())) {
			DoctorTbl doctor = getDoctorByLogin(netMdLogin.getId());
			if (doctor != null) {
				UserDetails user = new UserDetails();
				user.setId(doctor.getId());
				user.setName(doctor.getFirstName());
				user.setUserType(userType.getDisplayName());
				return user;
			}
		} else if (netMdLogin.getUserType().equals(NetmdUserTypeEnum.Owner.getDisplayName())) {
			NetmdTbl netmdTbl = getNetMdOwnerByLoginId(netMdLogin.getId());
			if (netmdTbl != null) {
				UserDetails user = new UserDetails();
				user.setId(netmdTbl.getId());
				user.setName(netmdTbl.getOwnerFirstName());
				user.setUserType(userType.getDisplayName());
				user.setNetmdId(netmdTbl.getId());
				return user;
			}
		} else {

			NetmdUserTbl netmdUser = (NetmdUserTbl) getUserByLoginId(netMdLogin
					.getId());
			if (netmdUser != null) {
				UserDetails user = new UserDetails();
				user.setId(netmdUser.getId());
				user.setName(netmdUser.getFirstName());
				user.setUserType(userType.getDisplayName());
				return user;
			}
		}
		return null;
	}

	/**
	 * Method performed to get Netmd user details
	 * 
	 * @param userName
	 * @return UserDetails
	 */
	@Override
	@Transactional
	public UserDetails getNetrxUser(String userName) {
		NetrxLoginTbl netRxLogin = (NetrxLoginTbl) getNetRxLoginByUserName(userName
				.trim());
		if (netRxLogin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.LoginNotExists);
			se.addParam(new Parameter(Constants.NAME, userName));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetmdUserTypeEnum userType = NetmdUserTypeEnum.getEnum(netRxLogin
				.getUserType());
		if (netRxLogin.getUserType().equals(NetmdUserTypeEnum.Doctor.getDisplayName())) {
			DoctorTbl doctor = getDoctorByLogin(netRxLogin.getId());
			if (doctor != null) {
				UserDetails user = new UserDetails();
				user.setId(doctor.getId());
				user.setName(doctor.getFirstName());
				user.setUserType(userType.getDisplayName());
				return user;
			}
		} else if (netRxLogin.getUserType().equals(NetmdUserTypeEnum.Owner.getDisplayName())) {
			NetrxTbl netrxTbl = getNetRxOwnerByLoginId(netRxLogin.getId());
			if (netrxTbl != null) {
				UserDetails user = new UserDetails();
				user.setId(netrxTbl.getId());
				user.setName(netrxTbl.getOwnerFirstName());
				user.setUserType(userType.getDisplayName());
				user.setNetrxId(netrxTbl.getId());
				return user;
			}
		} else {

			NetrxUserTbl netrxUser = (NetrxUserTbl) getNetRxUserByLoginId(netRxLogin
					.getId());
			if (netrxUser != null) {
				UserDetails user = new UserDetails();
				user.setId(netrxUser.getId());
				user.setName(netrxUser.getFirstName());
				user.setUserType(userType.getDisplayName());
				return user;
			}
		}
		return null;
	}
	/**
	 * Method performed to get patient details
	 * 
	 * @param userName
	 * @return UserDetails
	 */
	@Override
	@Transactional
	public UserDetails getPatient(String userName) {

		UserDetails user = new UserDetails();
		List<PatientTbl> patientList = getPatientByLogin(userName);
		if(!patientList.isEmpty()){
			PatientTbl patient = patientList.get(0);
			user.setId(patient.getId());
			user.setNetmdId(patient.getNetmdBranchTbl().getId());
			user.setUserType(patient.getNetmdLoginTbl().getUserType());
			user.setName(userName);
		}
		return user;

	}
	/**
	 * Method performed to get patient details
	 * 
	 * @param userName
	 * @return UserDetails
	 */
	@Override
	@Transactional
	public UserDetails netrxLogin(String userName) {

		UserDetails user = new UserDetails();
		List<PatientTbl> patientList = getNetRxUserByLogin(userName);
		PatientTbl patient = patientList.get(0);
		user.setId(patient.getId());
		user.setNetmdId(patient.getNetmdBranchTbl().getId());
		user.setUserType(patient.getNetmdLoginTbl().getUserType());
		user.setName(userName);
		return user;
	}
	/**
	 * Get patient details
	 * 
	 * @param userName
	 * @return PatientTbl
	 */
	private List<PatientTbl> getNetRxUserByLogin(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeQuery(PatientTbl.class, query);
	}


	/**
	 * Get patient details
	 * 
	 * @param userName
	 * @return PatientTbl
	 */
	private List<PatientTbl> getPatientByLogin(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeQuery(PatientTbl.class, query);
	}

	/**
	 * Get doctor details
	 * 
	 * @param loginId
	 * @return DoctorTbl
	 */
	private DoctorTbl getDoctorByLogin(int loginId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DOCTOR_BY_LOGIN_ID);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(DoctorTbl.class, query);
	}

	/**
	 * Get Netmd user details
	 * 
	 * @param loginId
	 * @return NetmdUserTbl
	 */
	private NetmdUserTbl getUserByLoginId(int loginId) {
		javax.persistence.Query query = em.createQuery(Query.GET_USER);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(NetmdUserTbl.class, query);
	}

	/**
	 * Get Netrx user details
	 * 
	 * @param loginId
	 * @return NetmdUserTbl
	 */
	private NetrxUserTbl getNetRxUserByLoginId(int loginId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETRX_USER);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(NetrxUserTbl.class, query);
	}
	/**
	 * Get Lab user details
	 * 
	 * @param loginId
	 * @return LabUserTbl
	 */
	private LabUserTbl getNetlimsUserByLogin(int loginId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETLIMS_USER);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(LabUserTbl.class, query);
	}

	/**
	 * Get netmd owner details
	 * 
	 * @param loginId
	 * @return LabTbl
	 */
	private NetmdTbl getNetMdOwnerByLoginId(int loginId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_BY_LOGIN_ID);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(NetmdTbl.class, query);
	}
	/**
	 * Get netmd owner details
	 * 
	 * @param loginId
	 * @return LabTbl
	 */
	private NetrxTbl getNetRxOwnerByLoginId(int loginId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETRX_BY_LOGIN_ID);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(NetrxTbl.class, query);
	}

	/**
	 * Get Lab owner details
	 * 
	 * @param loginId
	 * @return LabTbl
	 */
	private LabTbl getOwnerByLogin(int loginId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETLIMS_OWNER);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(LabTbl.class, query);
	}

	/**
	 * Get Lab user login details
	 * 
	 * @param password
	 * @param userName
	 * @return LabLoginTbl
	 */
	private LabLoginTbl getUserByUserNameAndPassword(String password,
			String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETLIMS_USER_BY_PASSWORD);
		query.setParameter("param1", password);
		query.setParameter("param2", userName);
		return executeUniqueQuery(LabLoginTbl.class, query);
	}

	/**
	 * Get lab user login details
	 * 
	 * @param userName
	 * @return LabLoginTbl
	 */
	private LabLoginTbl getNetlimsLoginByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETLIMS_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(LabLoginTbl.class, query);
	}

	/**
	 * Get Netmd user login details
	 * 
	 * @param userName
	 * @return NetmdLoginTbl
	 */
	private NetmdLoginTbl getNetMdLoginByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_LOGIN_BY_OWNER_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(NetmdLoginTbl.class, query);
	}
	/**
	 * Get Netrx user login details
	 * 
	 * @param userName
	 * @return NetmdLoginTbl
	 */
	private NetrxLoginTbl getNetRxLoginByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETRX_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(NetrxLoginTbl.class, query);
	}

	/**
	 * Get Netmd user login details
	 * 
	 * @param password
	 * @param userName
	 * @return NetmdLoginTbl
	 */
	private NetmdLoginTbl getNetMdUserByUserNameAndPassword(String password,
			String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_USER_BY_USERNAME_PASSWORD);
		query.setParameter("param1", password);
		query.setParameter("param2", userName);
		return (NetmdLoginTbl) executeUniqueQuery(NetmdLoginTbl.class, query);
	}
	/**
	 * Get Netrx user login details
	 * 
	 * @param password
	 * @param userName
	 * @return NetrxLoginTbl
	 */
	private NetrxLoginTbl getNetRxUserByUserNameAndPassword(String password,
			String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETRX_USER_BY_USERNAME_PASSWORD);
		query.setParameter("param1", password);
		query.setParameter("param2", userName);
		return (NetrxLoginTbl) executeUniqueQuery(NetrxLoginTbl.class, query);
	}
	/**
	 * Get patient login details
	 * 
	 * @param password
	 * @param userName
	 * @return NetmdLoginTbl
	 */
	private NetmdLoginTbl getPatientByUserNameAndPassword(String password,
			String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_FOR_PATIENTLOGIN);
		query.setParameter("param1", password);
		query.setParameter("param2", userName);
		return (NetmdLoginTbl) executeUniqueQuery(NetmdLoginTbl.class, query);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
