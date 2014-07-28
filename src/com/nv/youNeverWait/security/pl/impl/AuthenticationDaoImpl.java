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

import com.nv.framework.util.text.StringEncoder;
import com.nv.youNeverWait.rs.dto.Error;
import com.nv.youNeverWait.rs.dto.ErrorCodeListResponseDTO;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.LabFacilityTbl;
import com.nv.youNeverWait.pl.entity.LabTbl;
import com.nv.youNeverWait.pl.entity.LabUserTbl;
import com.nv.youNeverWait.pl.entity.LabUserTypeEnum;
import com.nv.youNeverWait.pl.entity.LoginTbl;
import com.nv.youNeverWait.pl.entity.NetmdDoctorTbl;
import com.nv.youNeverWait.pl.entity.NetmdPatientTbl;
import com.nv.youNeverWait.pl.entity.NetmdTbl;
import com.nv.youNeverWait.pl.entity.NetmdUserTbl;
import com.nv.youNeverWait.pl.entity.NetmdUserTypeEnum;
import com.nv.youNeverWait.pl.entity.NetrxLoginTbl;
import com.nv.youNeverWait.pl.entity.NetrxTbl;
import com.nv.youNeverWait.pl.entity.NetrxUserTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.CreatePasswordDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.rs.dto.UserDetails;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.security.pl.dao.AuthenticationDao;


/**
 * @author Asha Chandran
 *
 */
public class AuthenticationDaoImpl extends GenericDaoHibernateImpl implements
AuthenticationDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	 * Method to reset password
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO createPassword(CreatePasswordDTO passwords) {

		ResponseDTO response = new ResponseDTO();
		String newPassword = StringEncoder.encryptWithKey(passwords
				.getPassword());
		String decryptedUserName = StringEncoder.decryptWithStaticKey(passwords
				.getUsername());
		LoginTbl loginTbl = (LoginTbl) getLoginByUserName(decryptedUserName);
		if (loginTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUser);
			se.setDisplayErrMsg(true);
			throw se;
		}
		loginTbl.setPassword(newPassword);
		update(loginTbl);
		response.setSuccess(true);
		return response;
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
		LoginTbl loginInfo = null;
		LoginTbl loginDetails = null;
		System.out.println(loginDTO.getPassword());
		if(loginDTO.getUserType().equals(NetmdUserTypeEnum.Facility.getDisplayName()))
			loginInfo = getByUserNamePasswordAndType(
					loginDTO.getPassword(), loginDTO.getUserName(), loginDTO.getUserType());
		else
			loginDetails = getUserByUserNameAndPassword(
					loginDTO.getPassword(), loginDTO.getUserName(),loginDTO.getUserType());
		if(loginDetails==null && loginInfo==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		login.setSuccess(true);
		return login;

	}

	private LoginTbl getByUserNamePasswordAndType(String password,
			String userName, String userType) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_USER_BY_NAME_PASSWORD_TYPE);
		query.setParameter("param1", userName);
		query.setParameter("param2", password);
		query.setParameter("param3", userType);
		return executeUniqueQuery(LoginTbl.class, query);
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
		LoginTbl loginDetails = (LoginTbl) getNetMdUserByUserNameAndPassword(
				loginDTO.getPassword(), loginDTO.getUserName(),loginDTO.getUserType());
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
		LoginTbl loginDetails = getPatientByUserNameAndPassword(
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
	 * Method to reset password
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO resetPassword(LoginDTO login) {
		ResponseDTO response = new ResponseDTO();
		String newPassword = StringEncoder.encryptWithKey(login.getPassword());
		String decrypedUserName = StringEncoder.decryptWithStaticKey(login
				.getUserName());
		LoginTbl userLogin = getNetMdUserByName(decrypedUserName);
		if (userLogin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		userLogin.setPassword(newPassword);
		update(userLogin);
		response.setSuccess(true);
		return response;
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

		LoginTbl netlimsLogin = (LoginTbl) getNetlimsLoginByUserName(userName);
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
			LabUserTbl labUser = getNetlimsUserByLogin(netlimsLogin.getId());
			if (labUser != null) {
				UserDetails user = new UserDetails();
				user.setId(labUser.getId());
				user.setName(labUser.getUserTbl().getFirstName());
				user.setLabId(labUser.getLabBranchTbl().getLabTbl().getId());
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
		LoginTbl netMdLogin = (LoginTbl) getNetMdLoginByUserName(userName
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
			NetmdDoctorTbl doctor = getDoctorByLogin(netMdLogin.getId());
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
			NetmdDoctorTbl doctor = getDoctorByLogin(netRxLogin.getId());
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
		List<NetmdPatientTbl> patientList = getPatientByLogin(userName);
		if(!patientList.isEmpty()){
			NetmdPatientTbl patient = patientList.get(0);
			user.setId(patient.getId());
			user.setNetmdId(patient.getNetmdBranchTbl().getId());
			user.setUserType(patient.getLoginTbl().getUserType());
			user.setName(userName);
		}
		return user;

	}


	@Override
	@Transactional(readOnly = false)
	public ResponseDTO changePassword(PasswordDTO passwords) {
		ResponseDTO response = new ResponseDTO();
		String encPassword = StringEncoder.encryptWithKey(passwords
				.getOldPassword().trim());
		LoginTbl login = (LoginTbl) getLoginByUserName(passwords
				.getUsername());
		if (login == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNotExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!login.getPassword().equals(encPassword)) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PasswordNotExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		String encNewPassword = StringEncoder.encryptWithKey(passwords
				.getNewPassword().trim());
		login.setPassword(encNewPassword);
		update(login);
		response.setSuccess(true);
		return response;
	}


	/**
	 * Get patient details
	 * 
	 * @param userName
	 * @return NetmdPatientTbl
	 */
	private List<NetmdPatientTbl> getPatientByLogin(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeQuery(NetmdPatientTbl.class, query);
	}

	/**
	 * Get doctor details
	 * 
	 * @param loginId
	 * @return NetmdDoctorTbl
	 */
	private NetmdDoctorTbl getDoctorByLogin(int loginId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DOCTOR_BY_LOGIN_ID);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(NetmdDoctorTbl.class, query);
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
	 * @return UserTbl
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
	private LoginTbl getUserByUserNameAndPassword(String password,
			String userName,String userType) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETLIMS_USER_BY_PASSWORD);
		query.setParameter("param1", password);
		query.setParameter("param2", userName);
		return executeUniqueQuery(LoginTbl.class, query);
	}

	/**
	 * Get lab user login details
	 * 
	 * @param userName
	 * @return LabLoginTbl
	 */
	private LoginTbl getNetlimsLoginByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETLIMS_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(LoginTbl.class, query);
	}

	/**
	 * Get Netmd user login details
	 * 
	 * @param LoginTbl
	 * @return LoginTbl
	 */
	private LoginTbl getNetMdLoginByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_LOGIN_BY_OWNER_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(LoginTbl.class, query);
	}
	/**
	 * Get Netrx user login details
	 * 
	 * @param userName
	 * @return LoginTbl
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
	 * @return LoginTbl
	 */
	private LoginTbl getNetMdUserByUserNameAndPassword(String password,
			String userName,String userType) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_USER_BY_USERNAME_PASSWORD);
		query.setParameter("param1", password);
		query.setParameter("param2", userName);
		query.setParameter("param3",userType);
		return (LoginTbl) executeUniqueQuery(LoginTbl.class, query);
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
	 * @return LoginTbl
	 */
	private LoginTbl getPatientByUserNameAndPassword(String password,
			String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_FOR_PATIENTLOGIN);
		query.setParameter("param1", password);
		query.setParameter("param2", userName);
		return (LoginTbl) executeUniqueQuery(LoginTbl.class, query);
	}

	/**
	 * @return em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

	/**
	 * @param userName
	 * @return LoginTbl
	 */
	public LoginTbl getLoginByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(LoginTbl.class, query);
	}

	/**
	 * @param userName
	 * @param firstName
	 * @return List<LoginTbl> 
	 */
	public List<LoginTbl> getLoginByUserName(String userName,
			String firstName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_LOGIN_BY_USERNAME_FIRSTNAME);
		query.setParameter("param1", userName);
		query.setParameter("param2", firstName);
		return executeQuery(LoginTbl.class, query);
	}




	/**
	 * Method to retrieve details of a lab owner/user
	 * 
	 * @param login
	 * @return UserCredentials
	 */
	@Override
	@Transactional(readOnly = false)
	public UserCredentials getUserCredentials(LoginDTO login) {

		UserCredentials user = new UserCredentials();
		LoginTbl userLogin = getNetMdUserByName(login.getUserName().trim());
		if (userLogin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		user.setEmailId(userLogin.getUserName());
		user.setUserName(userLogin.getUserName());

		return user;
	}



	/**
	 * 
	 * @param userName
	 * @return LoginTbl
	 */
	public LoginTbl getNetMdUserByName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_LOGIN_PATIENT_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(LoginTbl.class, query);
	}

	/**
	 * 
	 * @param userName
	 * @param userType 
	 * @return LoginTbl
	 */
	public LoginTbl getUserByNameAndType(String userName, String userType) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_LOGIN_BY_USERNAME_USERTYPE);
		query.setParameter("param1", userName);
		query.setParameter("param2", userType);
		return executeUniqueQuery(LoginTbl.class, query);
	}


	@Override
	public UserDetails getNetlimsFacilityUser(String userName, String userType) {
		LoginTbl loginTbl = (LoginTbl) getUserByNameAndType(userName.trim(), userType.trim());
		if (loginTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.LoginNotExists);
			se.addParam(new Parameter(Constants.NAME, userName));
			se.setDisplayErrMsg(true);
			throw se;
		}
		LabFacilityTbl facility = geFacilityByLogin(loginTbl.getId());
		if (facility != null) {
			UserDetails user = new UserDetails();
			user.setId(facility.getId());
			//user.setLabId(facility.getLabBranchTbl().getLabTbl().getId());
			//user.setOrganisationId(facility.getLabBranchTbl().getId());
			user.setName(facility.getName());
			user.setUserType(userType);
			return user;
		}
		return null;
	}

	private LabFacilityTbl geFacilityByLogin(int loginId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_FACILITY_BY_LOGINID);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(LabFacilityTbl.class, query);
	}

}
