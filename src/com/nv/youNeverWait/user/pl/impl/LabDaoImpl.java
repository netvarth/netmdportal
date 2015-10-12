package com.nv.youNeverWait.user.pl.impl;

/**
 * LabDaoImpl.java
 *
 * Jan 3, 2013
 *
 * @author Asha Chandran 
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.nv.framework.util.text.StringEncoder;
import com.nv.security.youNeverWait.User;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ActionNameEnum;
import com.nv.youNeverWait.pl.entity.BranchFacilityTbl;
import com.nv.youNeverWait.pl.entity.BranchStatusEnum;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.FrequencyEnum;
import com.nv.youNeverWait.pl.entity.HealthCareOrganisationTbl;
import com.nv.youNeverWait.pl.entity.LabBranchSystemInfoTbl;
import com.nv.youNeverWait.pl.entity.LabBranchTbl;
import com.nv.youNeverWait.pl.entity.LabFacilityTbl;
import com.nv.youNeverWait.pl.entity.LabHealthMonitorTbl;
import com.nv.youNeverWait.pl.entity.LabPassphraseTbl;
import com.nv.youNeverWait.pl.entity.LabStatusEnum;
import com.nv.youNeverWait.pl.entity.LabTbl;
import com.nv.youNeverWait.pl.entity.LabUserTbl;
import com.nv.youNeverWait.pl.entity.LoginTbl;
import com.nv.youNeverWait.pl.entity.NetlimsOrderTbl;
import com.nv.youNeverWait.pl.entity.NetrxBranchTbl;
import com.nv.youNeverWait.pl.entity.UserTbl;
import com.nv.youNeverWait.pl.entity.LabUserTypeEnum;
import com.nv.youNeverWait.pl.entity.NetmdBranchTbl;
import com.nv.youNeverWait.pl.entity.NetmdDoctorTbl;
import com.nv.youNeverWait.pl.entity.NetmdTbl;
import com.nv.youNeverWait.pl.entity.OrderAmountTbl;
import com.nv.youNeverWait.pl.entity.OrderBranchTbl;
import com.nv.youNeverWait.pl.entity.OrderResultTbl;
import com.nv.youNeverWait.pl.entity.OrderTbl;
import com.nv.youNeverWait.pl.entity.OrderTransferTbl;
import com.nv.youNeverWait.pl.entity.NetmdPatientTbl;
import com.nv.youNeverWait.pl.entity.ResultTbl;
import com.nv.youNeverWait.pl.entity.StatusEnum;
import com.nv.youNeverWait.pl.entity.SuperAdminTbl;
import com.nv.youNeverWait.pl.entity.SyncFreqTypeEnum;
import com.nv.youNeverWait.pl.entity.UserStatusEnum;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.BranchFacilityListResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderCountResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderDetail;
import com.nv.youNeverWait.rs.dto.BranchOrdersResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FacilityDTO;
import com.nv.youNeverWait.rs.dto.FacilityInfo;
import com.nv.youNeverWait.rs.dto.FacilityListResponseDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LabBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchSystemInfoDetails;
import com.nv.youNeverWait.rs.dto.LabResultHeaderDTO;
import com.nv.youNeverWait.rs.dto.LabUserDTO;
import com.nv.youNeverWait.rs.dto.ListResponse;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LabBranchDTO;
import com.nv.youNeverWait.rs.dto.LabActivationResponseDTO;
import com.nv.youNeverWait.rs.dto.LabBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.LabDTO;
import com.nv.youNeverWait.rs.dto.LabResponseDTO;
import com.nv.youNeverWait.rs.dto.Order;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultRetrievalResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultTransferDTO;
import com.nv.youNeverWait.rs.dto.ResultTransferResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveLabListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveUserListResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncUser;
import com.nv.youNeverWait.rs.dto.SystemHealthMonitorDetailList;
import com.nv.youNeverWait.rs.dto.TransferNetMdResultDTO;
import com.nv.youNeverWait.rs.dto.UserBranchDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.rs.dto.UserInfo;
import com.nv.youNeverWait.rs.dto.UserInfoDetail;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.LabDao;
import com.nv.youNeverWait.util.filter.core.Filter;
import com.nv.youNeverWait.util.filter.core.FilterFactory;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;
import com.nv.youNeverWait.util.filter.core.QueryBuilderFactory;

/**
 * @author Mani E.V
 *
 */
public class LabDaoImpl extends GenericDaoHibernateImpl implements LabDao {
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;

	/**
	 * @return the queryBuilderFactory
	 */
	public QueryBuilderFactory getQueryBuilderFactory() {
		return queryBuilderFactory;
	}

	/**
	 * @param queryBuilderFactory the queryBuilderFactory to set
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
	 * @param filterFactory the filterFactory to set
	 */
	public void setFilterFactory(FilterFactory filterFactory) {
		this.filterFactory = filterFactory;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6809123736327548581L;
	@PersistenceContext()
	private EntityManager em;
	private String netlimsServerIpAddress;

	/**
	 * Create user in Lab
	 * 
	 * @param user
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO createUser(LabUserDTO user) {

		ResponseDTO response = new ResponseDTO();
		LabBranchTbl branch = null;

		/* checking whether the user already exists */
		UserTbl labUser = (UserTbl) getNetlimsUserByEmail(user.getEmail());
		if (labUser != null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.UserExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* Saving username and password in login tbl */

		String password = StringEncoder.encryptWithKey(user.getLogin()
				.getPassword());
		LoginTbl existingUser = (LoginTbl) getNetlimsUser(password, user
				.getLogin().getUserName().trim());
		if (existingUser != null) {

			ServiceException se = new ServiceException(ErrorCodeEnum.UserExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		LoginTbl loginTbl = new LoginTbl();
		loginTbl.setUserName(user.getLogin().getUserName());
		loginTbl.setPassword(password);
		loginTbl.setUserType(user.getLogin().getUserType());
		save(loginTbl);

		Date createdTime = new Date();
		UserTbl newUser = new UserTbl();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPhone(user.getPhone());
		newUser.setEmail(user.getEmail());
		newUser.setMobile(user.getMobile());
		newUser.setAddress(user.getAddress());
		newUser.setUserType(user.getUserType());
		newUser.setCreateDateTime(createdTime);
		newUser.setUpdateDateTime(createdTime);
		save(newUser);

		for (UserBranchDTO labBranch : user.getBranchIds()) {
			branch = (LabBranchTbl) getById(LabBranchTbl.class,
					labBranch.getBranchId());
			if (branch == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidBranchId);
				se.setDisplayErrMsg(true);
				throw se;
			} else {
				if (user.getLabId() != branch.getLabTbl().getId()) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.InvalidLab);
					se.addParam(new Parameter(Constants.ID, Integer
							.toString(user.getLabId())));
					se.setDisplayErrMsg(true);
					throw se;
				}
				LabUserTbl labUserTbl = new LabUserTbl();
				labUserTbl.setUserTbl(newUser);
				labUserTbl.setLabBranchTbl(branch);
				labUserTbl.setStatus(UserStatusEnum.Active.getDisplayName());
				labUserTbl.setUserTbl(newUser);
				save(labUserTbl);
			}
		}


		response.setGlobalId(newUser.getId());
		response.setSuccess(true);

		return response;

	}

	/**
	 * update a user in Lab
	 * 
	 * @param user
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO updateUser(LabUserDTO user) {

		ResponseDTO response = new ResponseDTO();
		LabBranchTbl branch = null;
		UserTbl labUser = (UserTbl) getById(UserTbl.class,
				user.getGlobalId());
		if (labUser == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (user.getFirstName() != null) {

			/* checking whether the name already exists */
			UserTbl userName = (UserTbl) getNetlimsUserByEmail(user
					.getEmail());
			if (userName != null) {
				if (userName.getId() != user.getGlobalId()) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.UserNameExists);
					se.addParam(new Parameter(Constants.NAME, user
							.getFirstName()));
					se.setDisplayErrMsg(true);
					throw se;
				}
			}
			labUser.setFirstName(user.getFirstName());
		}
		/* Iterating through branch id list */
		for (UserBranchDTO labBranch : user.getBranchIds()) {
			branch = (LabBranchTbl) getById(LabBranchTbl.class,
					labBranch.getBranchId());
			if (branch == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidBranchId);
				se.setDisplayErrMsg(true);
				throw se;
			} else {
				if (user.getLabId() != branch.getLabTbl().getId()) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.InvalidLab);
					se.addParam(new Parameter(Constants.ID, Integer
							.toString(user.getLabId())));
					se.setDisplayErrMsg(true);
					throw se;
				}
				LabUserTbl userBranch = (LabUserTbl) getUserByBranch(
						user.getGlobalId(), labBranch.getBranchId());
				if (userBranch == null) {
					if (labBranch.getActionName().equals(
							ActionNameEnum.ADD.getDisplayName())) {
						LabUserTbl newUserBranch = new LabUserTbl();
						newUserBranch.setLabBranchTbl(branch);
						newUserBranch.setUserTbl(labUser);
						save(newUserBranch);
					} else {
						ServiceException se = new ServiceException(
								ErrorCodeEnum.InvalidUserBranch);
						se.addParam(new Parameter(Constants.ID, Integer
								.toString(user.getLabId())));
						se.setDisplayErrMsg(true);
						throw se;
					}
				} else {
					if (labBranch.getActionName().equals(
							ActionNameEnum.DELETE.getDisplayName())) {
						delete(userBranch);
					}

				}
			}// end of else
		}// end of for loop

		labUser.setPhone(user.getPhone());
		labUser.setEmail(user.getEmail());
		labUser.setMobile(user.getMobile());
		labUser.setAddress(user.getAddress());
		labUser.setUserType(user.getUserType());
		labUser.setUpdateDateTime(new Date());
		update(labUser);

		response.setGlobalId(labUser.getId());
		response.setSuccess(true);
		return response;
	}

	/**
	 * delete a user in Lab
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO deleteUser(int id) {
		ResponseDTO response = new ResponseDTO();
		UserTbl labUser = (UserTbl) getById(UserTbl.class, id);
		if (labUser == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<LabUserTbl> userBranches = (List<LabUserTbl>) getBranchByUser(labUser
				.getId());
		for (LabUserTbl userBranch : userBranches) {
			userBranch.setStatus(UserStatusEnum.InActive.getDisplayName());
			update(userBranch);
		}

		labUser.setUpdateDateTime(new Date());
		update(labUser);
		response.setSuccess(true);
		return response;

	}

	/**
	 * view a user in Lab
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public LabUserDTO viewUser(int globalId) {
		LabUserDTO response = new LabUserDTO();
		UserTbl labUser = (UserTbl) getById(UserTbl.class, globalId);
		if (labUser == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.UserIdNull);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setFirstName(labUser.getFirstName());
		response.setLastName(labUser.getLastName());
		response.setAddress(labUser.getAddress());
		response.setEmail(labUser.getEmail());
		response.setMobile(labUser.getMobile());
		response.setPhone(labUser.getPhone());
		response.setUserType(labUser.getUserType());
		response.setSuccess(true);
		return response;
	}
	
	/*@Override
	@Transactional
	public BranchFacilityListResponseDTO listFacility(int branchId){
		BranchFacilityListResponseDTO response = new BranchFacilityListResponseDTO();
		ArrayList<LabFacilityTbl> labFacilityList = new ArrayList<LabFacilityTbl>();
		ArrayList<FacilityDTO> facilityList = new ArrayList<FacilityDTO>();
		javax.persistence.Query query = em.createNativeQuery(Query.GET_FACILITY_LIST);
		//query.setParameter("param1", 325);
		labFacilityList = (ArrayList<LabFacilityTbl>) executeQuery(LabFacilityTbl.class, query);	
		for(Object labFacilityObj:labFacilityList){
			System.out.println(labFacilityObj.getName());
		}
//		for(LabFacilityTbl labFacilityObj:labFacilityList){
//			System.out.println(labFacilityObj.getName());
//			FacilityDTO facility = new FacilityDTO();
//			facility.setName(labFacilityObj.getName());
//			facility.setAddress(labFacilityObj.getAddress());
//			facilityList.add(facility);
//		}
		response.setLab(facilityList);
		
		return response;
	}*/

	/**
	 * Method which clears mac Id
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO clearMacId(LabBranchDTO branch) {

		ResponseDTO response = new ResponseDTO();
		LabTbl lab = getById(LabTbl.class, branch.getLabId());
		if (lab == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branch
					.getLabId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		// if(lab.getLabBranch==branch.getglobalId)
		List<LabBranchTbl> branches = (List<LabBranchTbl>) getLabBranches(lab
				.getId());
		for (LabBranchTbl labBranch : branches) {
			if (labBranch.getId() == branch.getGlobalId()) {
				LabPassphraseTbl branchHeader = (LabPassphraseTbl) getMacPassPhraseByBranch(labBranch
						.getId());
				if (branchHeader != null) {
					branchHeader.setMacId(null);
					update(branchHeader);
				}
			}
		}

		response.setSuccess(true);
		return response;
	}

	/**
	 * Methd to retrieve email Id of a lab owner/user
	 * 
	 * @param login
	 * @return emailId
	 */
	@Override
	@Transactional
	public UserCredentials getUserCredentials(LoginDTO login) {

		UserCredentials user = new UserCredentials();
		LoginTbl userLogin = getNetlimsUserByName(login.getUserName().trim());
		if (userLogin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (userLogin.getUserType().equals(
				LabUserTypeEnum.Owner.getDisplayName())) {
			LabTbl labtbl = getLabByLoginId(userLogin.getId());
			if (labtbl == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NoUserExists);
				se.addParam(new Parameter(Constants.NAME, userLogin
						.getUserName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
			user.setEmailId(labtbl.getOwnerEmail());
			user.setFirstName(labtbl.getOwnerFirstName());
			user.setLastName(labtbl.getOwnerLastName());
			user.setUserName(userLogin.getUserName());
		} else {
			UserTbl labUser = getLabUserByLoginId(userLogin.getId());
			if (labUser == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NoUserExists);
				se.addParam(new Parameter(Constants.NAME, userLogin
						.getUserName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
			user.setEmailId(labUser.getEmail());
			user.setFirstName(labUser.getFirstName());
			user.setLastName(labUser.getLastName());
			user.setUserName(userLogin.getUserName());
		}
		return user;
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
		LoginTbl userLogin = getNetlimsUserByName(decrypedUserName);
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
	 * Method which performs password changing
	 * 
	 * @param passwords
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO changePassword(PasswordDTO passwords) {
		ResponseDTO response = new ResponseDTO();
		String encPassword = StringEncoder.encryptWithKey(passwords
				.getOldPassword().trim());
		LoginTbl login = (LoginTbl) getLoginByUserNameAndPassword(passwords
				.getUsername(), encPassword);
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
	 * Mani E.V	
	 * @param username
	 * @return
	 */
	private LoginTbl getLoginByUserNameAndPassword(String userName, String password) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETLIMS_USER_BY_PASSWORD);
		query.setParameter("param1", password);
		query.setParameter("param2",userName);
		return executeUniqueQuery(LoginTbl.class, query);
	}

	/**
	 * Check mac status
	 */
	@Transactional
	@Override
	public String checkMacStatus(String passPhrase) {

		try {
			javax.persistence.Query query = em
					.createQuery(Query.GET_NETLIMS_MAC_BY_PASSPHRASE);
			query.setParameter("param1", passPhrase);
			return (String) query.getSingleResult();
		} catch (Exception e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.MacStatusNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Creates a lab
	 * 
	 * @param lab
	 * @return ResponseDTO
	 */
	@Transactional
	@Override
	public ResponseDTO create(LabDTO lab) {

		ResponseDTO response = new ResponseDTO();

		SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
		LoginTbl login = getLoginByUserName(lab.getUserName());
		if (login != null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.NetLimsAccountAlreadyExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		login = new LoginTbl();
		login.setUserType("owner");
		login.setUserName(lab.getUserName());
		String encPassword = StringEncoder.encryptWithKey(lab.getPassword()
				.trim());
		login.setPassword(encPassword);
		save(login);

		LabTbl labTbl = new LabTbl();
		if (lab.getName() != null) {

			/* checking whether the name already exists */
			String alphaDigitsOnly = lab.getName().replaceAll("[^a-zA-Z0-9]+",
					"");
			LabTbl name = (LabTbl) getLabByName(alphaDigitsOnly.toUpperCase()
					.trim());
			if (name != null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.LabNameExists);
				se.addParam(new Parameter(Constants.NAME, lab.getName()));
				se.setDisplayErrMsg(true);
				throw se;

			}
			labTbl.setName(lab.getName());
		}
		Date createdTime = new Date();

		/**
		 * Generating random alpha numeric value for lab code with length two
		 * char
		 **/
		// String CHARACTER_SET = "123456789ABCDEFGHIJKLMNPQRSTUVWXYZ";
		// Random random = new Random();
		// StringBuilder builder = new StringBuilder();
		// for(int i = 0; i < 2; i++){
		// builder.append(CHARACTER_SET.charAt(random.nextInt(CHARACTER_SET.length())));
		// }
		// System.out.println("alpha numeric value for lab code is  "+builder.toString());
		//
		// labTbl.setLabCode(builder.toString());

		labTbl.setOwnerFirstName(lab.getOwnerFirstName());
		labTbl.setOwnerLastName(lab.getOwnerLastName());
		labTbl.setOwnerEmail(lab.getOwnerEmail());
		labTbl.setOwnerAddress(lab.getOwnerAddress());
		labTbl.setOwnerMobile(lab.getOwnerMobile());
		labTbl.setOwnerPhone(lab.getOwnerPhone());
		labTbl.setHeadOfficeName(lab.getHeadOfficeName());
		labTbl.setHeadOfficeAddress(lab.getHeadOfficeAddress());
		labTbl.setHeadOfficePhone(lab.getHeadOfficePhone());
		labTbl.setHeadOfficeMobile(lab.getHeadOfficeMobile());
		labTbl.setHeadOfficeEmail(lab.getHeadOfficeEmail());
		labTbl.setLoginTbl(login);
		labTbl.setStatus(LabStatusEnum.Active.getDisplayName());
		labTbl.setCreateDateTime(createdTime);
		labTbl.setUpdateDateTime(createdTime);

		if (superAdmin.getEnableSync() == false) {
			labTbl.setEnableSync(false);
		} else {
			labTbl.setEnableSync(true);
			labTbl.setSyncFreqType(superAdmin.getSyncFreqType());
			labTbl.setSyncTime(superAdmin.getSyncTime());
		}
		save(labTbl);
		response.setGlobalId(labTbl.getId());
		response.setSuccess(true);
		return response;
	}

	/**
	 * Creates a branch for a lab
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Transactional
	@Override
	public ResponseDTO createBranch(LabBranchDTO branch) {
		ResponseDTO response = new ResponseDTO();

		LabTbl lab = getById(LabTbl.class, branch.getLabId());
		if (lab == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branch
					.getLabId())));
			se.setDisplayErrMsg(true);
			throw se;
		}

		LabBranchTbl labBranch = new LabBranchTbl();
		labBranch.setAddress(branch.getAddress());
		Date createdTime = new Date();
		labBranch.setName(branch.getName());
		labBranch.setStatus(BranchStatusEnum.Active.getDisplayName());
		labBranch.setCreateDateTime(createdTime);
		labBranch.setUpdateDateTime(createdTime);
		labBranch.setPhone(branch.getPhone());
		labBranch.setMobile(branch.getMobile());
		labBranch.setEmail(branch.getEmail());
		// labBranch.setBranchCode(branch.getBranchCode());
		labBranch.setLabTbl(lab);
		if (lab.isEnableSync() == false) {
			labBranch.setEnableSync(false);
		} else {
			labBranch.setEnableSync(true);
			labBranch.setSyncFreqType(lab.getSyncFreqType());
			labBranch.setSyncTime(lab.getSyncTime());
		}
		save(labBranch);
		response.setGlobalId(labBranch.getId());

		LabPassphraseTbl labPassPhrase = new LabPassphraseTbl();
		byte[] bytes = StringEncoder.getKey();
		String passphrase = StringEncoder.getKeyvalue(bytes);
		System.out.println("passPhrase:" + passphrase);
		labPassPhrase.setPassPhrase(passphrase);
		labPassPhrase.setLabBranchTbl(labBranch);
		save(labPassPhrase);

		/* Saving default system details in lab branch system info tbl */
		LabBranchSystemInfoTbl systemDetails = new LabBranchSystemInfoTbl();
		systemDetails.setCriticalHardDiskSpaceLevel(1);
		systemDetails.setCriticalCpuLevel(1);
		systemDetails.setCriticalMemoryLevel(2);
		systemDetails.setFreqType(FrequencyEnum.Daily.getDisplayName());
		systemDetails.setIntervalTime(1);
		systemDetails.setLabBranchTbl(labBranch);
		save(systemDetails);
		response.setSuccess(true);
		response.setGlobalId(labBranch.getId());
		return response;
	}

	/**
	 * Update a Lab
	 * 
	 * @param lab
	 * @return ResponseDTO
	 */
	@Transactional
	@Override
	public ResponseDTO update(LabDTO lab) {
		ResponseDTO response = new ResponseDTO();
		if (lab.getGlobalId() <= 0) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(lab
					.getGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		LabTbl labTbl = getById(LabTbl.class, lab.getGlobalId());
		if (labTbl == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(lab
					.getGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}

		if (lab.getName() != null) {

			/* checking whether the name already exists */
			String alphaDigitsOnly = lab.getName().replaceAll("[^a-zA-Z0-9]+",
					"");
			LabTbl name = (LabTbl) getLabByName(alphaDigitsOnly.toUpperCase()
					.trim());
			if (name != null) {
				if (name.getId() != lab.getGlobalId()) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.LabNameExists);
					se.addParam(new Parameter(Constants.NAME, lab.getName()));
					se.setDisplayErrMsg(true);
					throw se;
				}
			}
			labTbl.setName(lab.getName());
		}
		response.setGlobalId(labTbl.getId());
		labTbl.setOwnerFirstName(lab.getOwnerFirstName());
		labTbl.setOwnerLastName(lab.getOwnerLastName());
		labTbl.setOwnerEmail(lab.getOwnerEmail());
		labTbl.setOwnerAddress(lab.getOwnerAddress());
		labTbl.setOwnerMobile(lab.getOwnerMobile());
		labTbl.setOwnerPhone(lab.getOwnerPhone());
		labTbl.setHeadOfficeName(lab.getHeadOfficeName());
		labTbl.setHeadOfficeAddress(lab.getHeadOfficeAddress());
		labTbl.setHeadOfficePhone(lab.getHeadOfficePhone());
		labTbl.setHeadOfficeMobile(lab.getHeadOfficeMobile());
		labTbl.setHeadOfficeEmail(lab.getHeadOfficeEmail());
		labTbl.setUpdateDateTime(new Date());
		update(labTbl);
		response.setGlobalId(labTbl.getId());
		response.setSuccess(true);
		return response;
	}

	/**
	 * Delete a Lab
	 * 
	 * @param lab
	 * @return ResponseDTO
	 */
	@Transactional
	@Override
	public ResponseDTO delete(int labId) {
		ResponseDTO response = new ResponseDTO();
		LabTbl lab = getById(LabTbl.class, labId);
		if (lab == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(labId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<LabBranchTbl> labBranches = getLabBranches(labId);
		for (LabBranchTbl labBranchTbl : labBranches) {
			for (LabUserTbl labUserBranchTbl : labBranchTbl
					.getLabUserTbls()) {
				labUserBranchTbl.setStatus(LabStatusEnum.InActive
						.getDisplayName());
				update(labUserBranchTbl);
			}
			labBranchTbl.setStatus(BranchStatusEnum.InActive.getDisplayName());
			update(labBranchTbl);
		}
		lab.setStatus(LabStatusEnum.InActive.getDisplayName());
		lab.setUpdateDateTime(new Date());
		update(lab);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Deletes a branch of a lab
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO deleteBranch(LabBranchDTO branch) {
		ResponseDTO response = new ResponseDTO();
		LabTbl lab = getById(LabTbl.class, branch.getLabId());
		if (lab == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branch
					.getLabId())));
			se.setDisplayErrMsg(true);
			throw se;
		}

		LabBranchTbl labBranch = (LabBranchTbl) getByLabId(
				branch.getGlobalId(), branch.getLabId());
		if (labBranch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		labBranch.setStatus(BranchStatusEnum.InActive.getDisplayName());
		labBranch.setUpdateDateTime(new Date());
		update(labBranch);
		for (LabUserTbl user : labBranch.getLabUserTbls()) {
			user.setStatus(BranchStatusEnum.InActive.getDisplayName());
			update(user);
		}
		response.setSuccess(true);
		return response;
	}

	/**
	 * View details of a lab branch
	 * 
	 * @param globalId
	 * @return BranchResponseDTO
	 */
	@Override
	@Transactional
	public LabBranchResponseDTO viewBranch(int globalId) {

		LabBranchResponseDTO response = new LabBranchResponseDTO();
		LabBranchTbl labBranch = getById(LabBranchTbl.class, globalId);
		if (labBranch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		LabBranchDTO branch = new LabBranchDTO();
		branch.setName(labBranch.getName());
		branch.setAddress(labBranch.getAddress());
		branch.setMobile(labBranch.getMobile());
		branch.setPhone(labBranch.getPhone());
		branch.setStatus(labBranch.getStatus());
		branch.setGlobalId(labBranch.getId());
		branch.setLabId(labBranch.getLabTbl().getId());
		branch.setEmail(labBranch.getEmail());
		branch.setStatus(labBranch.getStatus());
		// branch.setBranchCode(labBranch.getBranchCode());
		LabPassphraseTbl branchHeader = (LabPassphraseTbl) getMacPassPhraseByBranch(labBranch
				.getId());
		if (branchHeader != null) {
			branch.setMacId(branchHeader.getMacId());
			branch.setPassPhrase(branchHeader.getPassPhrase());
		}
		response.setBranch(branch);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Updates details of a lab branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO updateBranch(LabBranchDTO branch) {
		ResponseDTO response = new ResponseDTO();
		LabTbl lab = getById(LabTbl.class, branch.getLabId());
		if (lab == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branch
					.getLabId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		LabBranchTbl labBranch = (LabBranchTbl) getByLabId(
				branch.getGlobalId(), branch.getLabId());
		if (labBranch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		labBranch.setName(branch.getName());
		labBranch.setAddress(branch.getAddress());
		labBranch.setPhone(branch.getPhone());
		labBranch.setMobile(branch.getMobile());
		labBranch.setEmail(branch.getEmail());
		// labBranch.setBranchCode(branch.getBranchCode());
		labBranch.setUpdateDateTime(new Date());
		update(labBranch);
		response.setGlobalId(labBranch.getId());
		response.setSuccess(true);
		return response;
	}

	/**
	 * View a Lab
	 * 
	 * @param labId
	 * @return LabResponseDTO
	 */
	@Transactional
	@Override
	public LabResponseDTO view(int labId) {
		LabTbl labTbl = getById(LabTbl.class, labId);
		if (labTbl == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(labId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		LabDTO lab = new LabDTO();
		lab.setGlobalId(labTbl.getId());
		lab.setName(labTbl.getName());
		lab.setOwnerAddress(labTbl.getOwnerAddress());
		lab.setOwnerEmail(labTbl.getOwnerEmail());
		lab.setOwnerMobile(labTbl.getOwnerMobile());
		lab.setOwnerFirstName(labTbl.getOwnerFirstName());
		lab.setOwnerLastName(labTbl.getOwnerLastName());
		lab.setOwnerPhone(labTbl.getOwnerPhone());
		lab.setHeadOfficeAddress(labTbl.getHeadOfficeAddress());
		lab.setHeadOfficeEmail(labTbl.getHeadOfficeEmail());
		lab.setHeadOfficeMobile(labTbl.getHeadOfficeMobile());
		lab.setHeadOfficeName(labTbl.getHeadOfficeName());
		lab.setHeadOfficePhone(labTbl.getHeadOfficePhone());
		lab.setStatus(labTbl.getStatus());
		// lab.setLabCode(labTbl.getLabCode());
		if (labTbl.getLoginTbl() != null) {
			LoginTbl ownerLogin = getById(LoginTbl.class, labTbl
					.getLoginTbl().getId());
			if (ownerLogin != null) {
				LoginDTO login = new LoginDTO();
				login.setUserName(ownerLogin.getUserName());
				login.setUserType(ownerLogin.getUserType());
				lab.setLogin(login);
			}
		}
		LabResponseDTO response = new LabResponseDTO();
		response.setLab(lab);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Retrieves lab branch details and set Mac Id if not exists
	 * 
	 * @param header
	 * @return LabActivationResponseDTO
	 */
	@Override
	@Transactional
	public LabActivationResponseDTO activateLab(HeaderDTO header) {
		LabActivationResponseDTO response = new LabActivationResponseDTO();
		LabPassphraseTbl branchpassPhrase = (LabPassphraseTbl) getLabBranchByPassphrase(header
				.getPassPhrase());
		if (branchpassPhrase == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPassphrase);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (branchpassPhrase.getMacId() != null) {
			if (!branchpassPhrase.getMacId().equals(header.getMacId())) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.MacIdExists);
				se.addParam(new Parameter(Constants.PASSPHRASE,
						branchpassPhrase.getPassPhrase()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		branchpassPhrase.setMacId(header.getMacId());
		update(branchpassPhrase);

		/* setting user details */
		List<UserInfoDetail> users = new ArrayList<UserInfoDetail>();
	/*	List<LabUserTbl> branchUsers = (List<LabUserTbl>) getUsersInBranch(branchpassPhrase
				.getLabBranchTbl().getId());
		if (!branchUsers.isEmpty()) {
			for (LabUserTbl user : branchUsers) {
				UserInfoDetail userDetail = new UserInfoDetail();
				if (user.getUserTbl() != null) {
					if (user.getLoginTbl() != null) {
						LoginTbl userLogin = getById(LoginTbl.class, user.getLoginTbl().getId());
						if (userLogin == null) {
							ServiceException se = new ServiceException(
									ErrorCodeEnum.UserLoginNull);
							se.addParam(new Parameter(Constants.ID, Integer
									.toString(user.getLoginTbl().getId())));
							se.setDisplayErrMsg(true);
							throw se;
						}
						userDetail.setUserName(userLogin.getUserName());
						userDetail.setUserType(userLogin.getUserType());
						userDetail.setPassword(userLogin.getPassword());
					}
					userDetail
					.setFirstName(user.getUserTbl().getFirstName());
					userDetail.setStatus(user.getStatus());
					userDetail.setEmail(user.getUserTbl().getEmail());
					userDetail.setPhone(user.getUserTbl().getPhone());
					userDetail.setMobile(user.getUserTbl().getMobile());
					userDetail.setAddress(user.getUserTbl().getAddress());
					userDetail.setGlobalId(user.getUserTbl().getId());
					//userDetail.setUserType(user.getUserTbl().getUserType());
				}
				users.add(userDetail);
			}
		}*/
		response.setUsers(users);

		LabTbl lab = getById(LabTbl.class, branchpassPhrase.getLabBranchTbl()
				.getLabTbl().getId());
		// setting lab details
		LabDTO newLab = new LabDTO();
		if (lab.getLoginTbl() != null) {
			LoginTbl ownerLogin = getById(LoginTbl.class, lab
					.getLoginTbl().getId());
			if (ownerLogin == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.UserLoginNull);
				se.addParam(new Parameter(Constants.ID, Integer.toString(lab
						.getLoginTbl().getId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			LoginDTO ownerLoginDetails = new LoginDTO();
			ownerLoginDetails.setUserName(ownerLogin.getUserName());
			ownerLoginDetails.setPassword(ownerLogin.getPassword());
			ownerLoginDetails.setUserType(ownerLogin.getUserType());
			newLab.setLogin(ownerLoginDetails);
		}
		newLab.setName(lab.getName());
		newLab.setOwnerFirstName(lab.getOwnerFirstName());
		newLab.setOwnerLastName(lab.getOwnerLastName());
		newLab.setOwnerAddress(lab.getOwnerAddress());
		newLab.setOwnerEmail(lab.getOwnerEmail());
		newLab.setOwnerMobile(lab.getOwnerMobile());
		newLab.setOwnerPhone(lab.getOwnerPhone());
		newLab.setGlobalId(lab.getId());
		newLab.setStatus(LabStatusEnum.Active.getDisplayName());
		newLab.setHeadOfficeAddress(lab.getHeadOfficeAddress());
		newLab.setHeadOfficeEmail(lab.getHeadOfficeEmail());
		newLab.setHeadOfficeMobile(lab.getHeadOfficeMobile());
		newLab.setHeadOfficeName(lab.getHeadOfficeName());
		newLab.setHeadOfficePhone(lab.getHeadOfficePhone());
		newLab.setAuthToSent(lab.isAuthToSentResult());
		// newLab.setLabCode(lab.getLabCode());
		newLab.setOrderType(lab.getOrderTypeCode());
		response.setLab(newLab);

		// setting branch details
		List<LabBranchDTO> branchList = new ArrayList<LabBranchDTO>();
		List<LabBranchTbl> branches = (List<LabBranchTbl>) getLabBranches(lab
				.getId());
		if (!branches.isEmpty()) {
			for (LabBranchTbl branch : branches) {
				LabBranchDTO newBranch = new LabBranchDTO();
				if (branch.getId() == branchpassPhrase.getLabBranchTbl()
						.getId()) {
					newBranch.setHomeBranch(true);
				}
				newBranch.setGlobalId(branch.getId());
				newBranch.setName(branch.getName());
				newBranch.setStatus(branch.getStatus());
				newBranch.setAddress(branch.getAddress());
				newBranch.setPhone(branch.getPhone());
				newBranch.setMobile(branch.getMobile());
				newBranch.setEmail(branch.getEmail());
				// newBranch.setBranchCode(branch.getBranchCode());
				if (branch.getId() == branchpassPhrase.getLabBranchTbl()
						.getId()) {
					newBranch.setHomeBranch(true);
				} else {
					newBranch.setHomeBranch(false);
				}
				branchList.add(newBranch);
			}
		}
		response.setBranchList(branchList);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Transfer result to NetMd
	 * 
	 * @param resultTranfer
	 * @return ResultTransferResponseDTO
	 */
	@Transactional
	@Override
	public ResultTransferResponseDTO transferResultToNetMd(
			TransferNetMdResultDTO resultTranfer) {
		ResultTransferResponseDTO response = new ResultTransferResponseDTO();
		DateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		LabPassphraseTbl passPhrase = getLabBranchByPassphrase(resultTranfer
				.getHeader().getPassPhrase());
		if (passPhrase == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getMacId() == null
				|| !passPhrase.getMacId().equals(
						resultTranfer.getHeader().getMacId())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getLabBranchTbl().getId() != resultTranfer.getHeader()
				.getBranchId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getLabBranchTbl().getLabTbl().getId() != resultTranfer
				.getHeader().getHeadOfficeId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!passPhrase.getLabBranchTbl().getLabTbl().isAuthToSentResult()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UnAuthorized);
			se.setDisplayErrMsg(true);
			throw se;
		}
		ResultTbl result = new ResultTbl();

		/* Method for checking source branch id related to source lab id */
		if (!isValidBranch(resultTranfer.getSourceLabId(),
				resultTranfer.getSourceLabBranchId())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSourceBranch);
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* getting all doctor records having given email id */
		List<NetmdDoctorTbl> doctorTbl = (List<NetmdDoctorTbl>) getDoctorByEmail(resultTranfer
				.getDoctorEmail().trim());

		if (doctorTbl.isEmpty()) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.DoctorListEmpty);
			se.addParam(new Parameter(Constants.EMAIL, resultTranfer
					.getDoctorEmail()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		int count = 0;
		boolean flag = false;
		ResultTbl patientResults = null;
		for (NetmdDoctorTbl doctr : doctorTbl) {
			count++;
			/* Getting patient record by phone */
			NetmdPatientTbl patientByPhone = getPatientByPhone(resultTranfer
					.getPatient().getFirstName().toUpperCase().trim(), doctr
					.getNetmdBranchTbl().getId(), resultTranfer.getPatient()
					.getPhone());
			if (patientByPhone == null) {
				/*
				 * Getting patient record by mobile when there is no patient
				 * record by phone
				 */
				NetmdPatientTbl patientByMobile = getPatientByMobile(resultTranfer
						.getPatient().getFirstName().toUpperCase().trim(),
						doctr.getNetmdBranchTbl().getId(), resultTranfer
						.getPatient().getMobile());
				if (patientByMobile == null) {
					/*
					 * Getting patient record by Email when there is no patient
					 * record by mobile
					 */
					NetmdPatientTbl patientByEmail = getPatientByEmail(resultTranfer
							.getPatient().getFirstName().toUpperCase().trim(),
							doctr.getNetmdBranchTbl().getId(), resultTranfer
							.getPatient().getEmail());
					if (patientByEmail == null) {
						if (count == doctorTbl.size() && flag == false) {

							ErrorDTO error = new ErrorDTO();
							error.setErrCode(ErrorCodeEnum.EmptyPatientList
									.getErrCode());
							error.setDisplayErrMsg(true);
							response.setError(error);
							response.setSuccess(false);
							return response;
						}
						continue;
					} else {
						/* Checking the result tbl record already exists */
						patientResults = getPatientResult(
								resultTranfer.getOrderUid(), doctr
								.getNetmdBranchTbl().getId(), doctr
								.getNetmdBranchTbl().getNetmdTbl()
								.getId(), patientByEmail.getId(),
								resultTranfer.getSourceLabId(),
								resultTranfer.getSourceLabBranchId());
						if (patientResults == null)
							result.setNetmdPatientTbl(patientByEmail);
					}
				} else {
					/* Checking the result tbl record already exists */
					patientResults = getPatientResult(
							resultTranfer.getOrderUid(), doctr
							.getNetmdBranchTbl().getId(), doctr
							.getNetmdBranchTbl().getNetmdTbl().getId(),
							patientByMobile.getId(),
							resultTranfer.getSourceLabId(),
							resultTranfer.getSourceLabBranchId());
					if (patientResults == null)
						result.setNetmdPatientTbl(patientByMobile);
				}
			} else {
				/* Checking the result tbl record already exists */
				patientResults = getPatientResult(resultTranfer.getOrderUid(),
						doctr.getNetmdBranchTbl().getId(), doctr
						.getNetmdBranchTbl().getNetmdTbl().getId(),
						patientByPhone.getId(), resultTranfer.getSourceLabId(),
						resultTranfer.getSourceLabBranchId());

				if (patientResults == null)
					result.setNetmdPatientTbl(patientByPhone);
			}
			if (patientResults != null) {
				patientResults.setResult(resultTranfer.getResult());
				update(patientResults);
				flag = true;
				continue;
			}
			/* Set details in result table */
			LabTbl sourceLab = getById(LabTbl.class,
					resultTranfer.getSourceLabId());
			result.setLabTbl(sourceLab);
			LabBranchTbl sourceLabBranch = getById(LabBranchTbl.class,
					resultTranfer.getSourceLabBranchId());
			result.setLabBranchTbl(sourceLabBranch);

			NetmdTbl destNetmd = getById(NetmdTbl.class, doctr
					.getNetmdBranchTbl().getNetmdTbl().getId());
			result.setNetmdTbl(destNetmd);
			NetmdBranchTbl destNetmdBranch = getById(NetmdBranchTbl.class,
					doctr.getNetmdBranchTbl().getId());
			result.setNetmdBranchTbl(destNetmdBranch);

			result.setOrderId(resultTranfer.getOrderUid());
			Date orderDte = null;
			try {
				orderDte = df.parse(resultTranfer.getOrderDate());
			} catch (ParseException e) {

				e.printStackTrace();
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
			result.setOrderDate(orderDte);
			result.setResult(resultTranfer.getResult());
			Date createdTime = new Date();
			result.setCreatedDateTime(createdTime);
			result.setUpdatedDateTime(createdTime);
			save(result);
			flag = true;
		} // end of for loop

		response.setSuccess(true);
		return response;
	}

	/**
	 * TransferResult from one lab branch to other
	 * 
	 * @param ResultTransferDTO
	 * @return ResultTransferResponseDTO
	 */
	@Transactional
	@Override
	public ResultTransferResponseDTO transferResult(
			ResultTransferDTO resultTranferDto) {
		ResultTransferResponseDTO response = new ResultTransferResponseDTO();
		LabPassphraseTbl passPhrase = getLabBranchByPassphrase(resultTranferDto
				.getHeader().getPassPhrase());
		if (passPhrase == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getMacId() == null
				|| !passPhrase.getMacId().equals(
						resultTranferDto.getHeader().getMacId())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getLabBranchTbl().getId() != resultTranferDto
				.getHeader().getBranchId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getLabBranchTbl().getLabTbl().getId() != resultTranferDto
				.getHeader().getHeadOfficeId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!passPhrase.getLabBranchTbl().getLabTbl().isAuthToSentResult()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UnAuthorized);
			se.setDisplayErrMsg(true);
			throw se;
		}

		/*
		 * Setting error message when source lab id and destination lab id are
		 * not same
		 */
		if (resultTranferDto.getSourceLabId() != resultTranferDto
				.getDestinationLabId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidTransfer);
			se.setDisplayErrMsg(true);
			throw se;
		}
		OrderResultTbl orderResult = new OrderResultTbl();
		if (!isValidBranch(resultTranferDto.getSourceLabId(),
				resultTranferDto.getSourceLabBranchId())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSourceBranch);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidBranch(resultTranferDto.getDestinationLabId(),
				resultTranferDto.getDestinationBranchId())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDestinationBranch);
			se.setDisplayErrMsg(true);
			throw se;
		}
		LabTbl sourceLab = getById(LabTbl.class,
				resultTranferDto.getSourceLabId());
		orderResult.setLabTbl(sourceLab);

		OrderBranchTbl orderBranchTbl =getByOrderUid_Branch(resultTranferDto.getOrderUid(),resultTranferDto.getDestinationBranchId());
		orderResult.setOrderBranchTbl(orderBranchTbl);
		orderResult.setTestUid(resultTranferDto.getTestUId());
		orderResult.setResult(resultTranferDto.getResult());
		orderResult.setCreatedDateTime(new Date());
		orderResult.setUpdatedDateTime(new Date());
		orderResult.setSent(false);
		LabBranchTbl sourceLabBranch = getById(LabBranchTbl.class,
				resultTranferDto.getSourceLabBranchId());
		orderResult.setLabBranchTbl(sourceLabBranch);
		LabBranchTbl destLabBranch = getById(LabBranchTbl.class,
				resultTranferDto.getDestinationBranchId());
		orderResult.setOwnerLabBranchTbl(destLabBranch);
		save(orderResult);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Mani E.V	
	 * @param orderUid
	 * @param destinationBranchId
	 * @return
	 */
	private OrderBranchTbl getByOrderUid_Branch(String orderUid,
			int destinationBranchId) {
		javax.persistence.Query query = em.createQuery(Query.GET_ORDER_BY_UID_BRANCH);
		query.setParameter("param1", orderUid);
		query.setParameter("param2", destinationBranchId);
		return executeUniqueQuery(OrderBranchTbl.class, query);
	}

	/**
	 * Retrieve results from a branch
	 * 
	 * @param ResultTransferDTO
	 * @return ResultTransferResponseDTO
	 */
	@Transactional
	@Override
	public ResultRetrievalResponseDTO getResult(HeaderDTO header,
			String lastSyncTm, Date currentTime) {

		ResultRetrievalResponseDTO response = new ResultRetrievalResponseDTO();
		CheckHeaderDetails(header);
		SimpleDateFormat sf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		Date dat = null;
		try {
			dat = sf.parse(lastSyncTm);
		} catch (ParseException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<OrderTbl> orders = getOrders(dat, header.getHeadOfficeId(),
				header.getBranchId());
		List<ResultTransferDTO> resultTransferDTOs = new ArrayList<ResultTransferDTO>();
		String lastSyncTime = null;

		for (OrderTbl order : orders) {

			ResultTransferDTO result = new ResultTransferDTO();
			result.setDestinationBranchId(order.getDestinationBranchTbl()
					.getId());
			result.setDestinationLabId(order.getDestinationLabTbl().getId());
			result.setOrderUid(order.getLocalOrderUid());
			result.setResult(order.getResult());
			result.setSourceLabBranchId(order.getSourceBranchTbl().getId());
			result.setSourceLabId(order.getSourceLabTbl().getId());
			resultTransferDTOs.add(result);
			lastSyncTime = order.getUpdateTime().toString();
		}
		response.setResult(resultTransferDTOs);
		response.setLastSyncTime(lastSyncTime);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Retrieves all lab branch list after last synchronization time
	 * 
	 * @param labBranchRetrievalDTO
	 * @return LabBranchListResponseDTO
	 */
	@Override
	@Transactional
	public LabBranchListResponseDTO retrieveLabBranchList(HeaderDTO header,
			String lastSyncTme, Date currentTime) {

		LabBranchListResponseDTO response = new LabBranchListResponseDTO();
		CheckHeaderDetails(header);
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		Date syncTime = null;
		try {
			syncTime = sdf.parse(lastSyncTme);
		} catch (ParseException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}

		List<LabBranchDTO> ownBranchList = new ArrayList<LabBranchDTO>();
		List<LabBranchTbl> ownBranches = getOwnBranches(syncTime,
				header.getHeadOfficeId(), currentTime);
		for (LabBranchTbl labBranchTbl : ownBranches) {

			LabBranchDTO branch = new LabBranchDTO();
			branch.setAddress(labBranchTbl.getAddress());
			branch.setEmail(labBranchTbl.getEmail());
			branch.setGlobalId(labBranchTbl.getId());
			branch.setLabId(labBranchTbl.getLabTbl().getId());
			branch.setMacId(header.getMacId());
			branch.setMobile(labBranchTbl.getMobile());
			branch.setName(labBranchTbl.getName());
			branch.setPassPhrase(header.getPassPhrase());
			branch.setPhone(labBranchTbl.getPhone());
			branch.setStatus(labBranchTbl.getStatus());
			// branch.setBranchCode(labBranchTbl.getBranchCode());
			ownBranchList.add(branch);

		}

		List<LabBranchDTO> newBranchList = new ArrayList<LabBranchDTO>();
		List<LabBranchTbl> newBranches = getNewBranches(syncTime,
				header.getHeadOfficeId(), currentTime);
		for (LabBranchTbl labBranchTbl : newBranches) {
			LabBranchDTO branch = new LabBranchDTO();
			branch.setAddress(labBranchTbl.getAddress());
			branch.setEmail(labBranchTbl.getEmail());
			branch.setGlobalId(labBranchTbl.getId());
			branch.setLabId(labBranchTbl.getLabTbl().getId());
			branch.setMacId(header.getMacId());
			branch.setMobile(labBranchTbl.getMobile());
			branch.setName(labBranchTbl.getName());
			branch.setPassPhrase(header.getPassPhrase());
			branch.setPhone(labBranchTbl.getPhone());
			branch.setStatus(labBranchTbl.getStatus());
			// branch.setBranchCode(labBranchTbl.getBranchCode());
			newBranchList.add(branch);
		}
		List<LabBranchDTO> updatedBranchList = new ArrayList<LabBranchDTO>();
		List<LabBranchTbl> updatedBranches = getUpdatedBranches(syncTime,
				header.getHeadOfficeId(), currentTime);
		for (LabBranchTbl labBranchTbl : updatedBranches) {
			LabBranchDTO branch = new LabBranchDTO();
			branch.setGlobalId(labBranchTbl.getId());
			branch.setLabId(labBranchTbl.getLabTbl().getId());
			branch.setMacId(header.getMacId());
			branch.setPassPhrase(header.getPassPhrase());
			branch.setStatus(labBranchTbl.getStatus());
			// branch.setBranchCode(labBranchTbl.getBranchCode());
			updatedBranchList.add(branch);
		}
		response.setNewBranchList(newBranchList);
		response.setUpdatedBranchList(updatedBranchList);
		response.setOwnBranchList(ownBranchList);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Retrieves all lab list after last synchronization time
	 * 
	 * @param labRetrievalDTO
	 * @return RetrieveLabListResponseDTO
	 */
	@Transactional
	@Override
	public RetrieveLabListResponseDTO retrieveLabList(HeaderDTO header,
			String lastSyncTm, Date currentTime) {

		RetrieveLabListResponseDTO response = new RetrieveLabListResponseDTO();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		Date syncTime = null;
		try {
			syncTime = sdf.parse(lastSyncTm);
		} catch (ParseException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* Valdates header details */
		CheckHeaderDetails(header);

		List<LabDTO> newLabList = new ArrayList<LabDTO>();
		List<LabDTO> updatedLabList = new ArrayList<LabDTO>();
		/* Query to get new labs */
		List<LabTbl> newLabs = getNewLabs(header.getHeadOfficeId(), syncTime,
				currentTime);
		// Date lastSyncTime = null;
		for (LabTbl labTbl : newLabs) {
			LabDTO newLab = new LabDTO();
			newLab.setGlobalId(labTbl.getId());
			newLab.setName(labTbl.getName());
			newLab.setOwnerFirstName(labTbl.getOwnerFirstName());
			newLab.setOwnerLastName(labTbl.getOwnerLastName());
			newLab.setOwnerAddress(labTbl.getOwnerAddress());
			newLab.setOwnerEmail(labTbl.getOwnerEmail());
			newLab.setOwnerMobile(labTbl.getOwnerMobile());
			newLab.setOwnerPhone(labTbl.getOwnerPhone());
			newLab.setGlobalId(labTbl.getId());
			newLab.setStatus(labTbl.getStatus());
			newLab.setHeadOfficeAddress(labTbl.getHeadOfficeAddress());
			newLab.setHeadOfficeEmail(labTbl.getHeadOfficeEmail());
			newLab.setHeadOfficeMobile(labTbl.getHeadOfficeMobile());
			newLab.setHeadOfficeName(labTbl.getHeadOfficeName());
			newLab.setHeadOfficePhone(labTbl.getHeadOfficePhone());
			newLab.setAuthToSent(labTbl.isAuthToSentResult());
			newLabList.add(newLab);

		}
		/* Query for getting own labs */
		LabTbl ownLab = getOwnLab(header.getHeadOfficeId(), syncTime,
				currentTime);
		if (ownLab == null) {
			response.setOwnLab(null);
		} else {
			LabDTO lab = new LabDTO();
			lab.setGlobalId(ownLab.getId());
			lab.setName(ownLab.getName());
			lab.setOwnerFirstName(ownLab.getOwnerFirstName());
			lab.setOwnerLastName(ownLab.getOwnerLastName());
			lab.setOwnerAddress(ownLab.getOwnerAddress());
			lab.setOwnerEmail(ownLab.getOwnerEmail());
			lab.setOwnerMobile(ownLab.getOwnerMobile());
			lab.setOwnerPhone(ownLab.getOwnerPhone());
			lab.setGlobalId(ownLab.getId());
			lab.setStatus(ownLab.getStatus());
			lab.setHeadOfficeAddress(ownLab.getHeadOfficeAddress());
			lab.setHeadOfficeEmail(ownLab.getHeadOfficeEmail());
			lab.setHeadOfficeMobile(ownLab.getHeadOfficeMobile());
			lab.setHeadOfficeName(ownLab.getHeadOfficeName());
			lab.setHeadOfficePhone(ownLab.getHeadOfficePhone());
			lab.setAuthToSent(ownLab.isAuthToSentResult());
			response.setOwnLab(lab);
		}
		/* Query to get updated labs */
		List<LabTbl> updatedLabs = getUpdatedLabs(header.getHeadOfficeId(),
				syncTime, currentTime);
		for (LabTbl labTbl : updatedLabs) {
			LabDTO lab = new LabDTO();
			lab.setGlobalId(labTbl.getId());
			lab.setName(labTbl.getName());
			lab.setOwnerFirstName(labTbl.getOwnerFirstName());
			lab.setOwnerLastName(labTbl.getOwnerLastName());
			lab.setOwnerAddress(labTbl.getOwnerAddress());
			lab.setOwnerEmail(labTbl.getOwnerEmail());
			lab.setOwnerMobile(labTbl.getOwnerMobile());
			lab.setOwnerPhone(labTbl.getOwnerPhone());
			lab.setGlobalId(labTbl.getId());
			lab.setStatus(labTbl.getStatus());
			lab.setHeadOfficeAddress(labTbl.getHeadOfficeAddress());
			lab.setHeadOfficeEmail(labTbl.getHeadOfficeEmail());
			lab.setHeadOfficeMobile(labTbl.getHeadOfficeMobile());
			lab.setHeadOfficeName(labTbl.getHeadOfficeName());
			lab.setHeadOfficePhone(labTbl.getHeadOfficePhone());
			lab.setAuthToSent(labTbl.isAuthToSentResult());
			updatedLabList.add(lab);
		}
		response.setNewLabList(newLabList);
		response.setUpdatedLabList(updatedLabList);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Retrieves all user list after last synchronization time
	 * 
	 * @param userRetrievalDTO
	 * @return RetrieveUserListResponseDTO
	 */
	@Transactional
	@Override
	public RetrieveUserListResponseDTO retrieveUserList(HeaderDTO header,
			String lastSyncTime, Date currentTime) {

		RetrieveUserListResponseDTO response = new RetrieveUserListResponseDTO();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		Date syncTime = null;
		try {
			syncTime = sdf.parse(lastSyncTime);
		} catch (ParseException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* Valdates header details */
		CheckHeaderDetails(header);

		List<UserInfoDetail> newUserList = new ArrayList<UserInfoDetail>();
		List<UserInfoDetail> updatedUserList = new ArrayList<UserInfoDetail>();
		List<LabUserTbl> userBranches = (List<LabUserTbl>) getUsersInBranch(header
				.getBranchId());

		if (!userBranches.isEmpty()) {
			for (LabUserTbl labUserBranch : userBranches) {

				List<UserTbl> newUsers = getNewUsers(labUserBranch
						.getUserTbl().getId(), syncTime, currentTime);
				for (UserTbl labUserTbl : newUsers) {

					UserInfoDetail newUser = new UserInfoDetail();
					newUser.setGlobalId(labUserTbl.getId());
					newUser.setFirstName(labUserTbl.getFirstName());
					newUser.setLastName(labUserTbl.getLastName());
					newUser.setEmail(labUserTbl.getEmail());
					newUser.setAddress(labUserTbl.getAddress());
					newUser.setPhone(labUserTbl.getPhone());
					newUser.setMobile(labUserTbl.getMobile());
					newUser.setUserType(labUserTbl.getUserType());
					newUserList.add(newUser);

				}
				List<UserTbl> updatedUsers = getUpdatedUsers(labUserBranch
						.getUserTbl().getId(), syncTime, currentTime);
				for (UserTbl labUserTbl : updatedUsers) {
					UserInfoDetail user = new UserInfoDetail();
					user.setGlobalId(labUserTbl.getId());
					user.setFirstName(labUserTbl.getLastName());
					user.setEmail(labUserTbl.getEmail());
					user.setAddress(labUserTbl.getAddress());
					user.setPhone(labUserTbl.getPhone());
					user.setMobile(labUserTbl.getMobile());
					user.setUserType(labUserTbl.getUserType());
					updatedUserList.add(user);

				}
			}
		}
		response.setNewUserList(newUserList);
		response.setUpdatedUserList(updatedUserList);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method to validate the header details
	 */
	public void CheckHeaderDetails(HeaderDTO header) {

		LabPassphraseTbl passPhrase = getLabBranchByPassphrase(header
				.getPassPhrase());
		if (passPhrase == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getMacId() == null
				|| !passPhrase.getMacId().equals(header.getMacId())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getLabBranchTbl().getId() != header.getBranchId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getLabBranchTbl().getLabTbl().getId() != header
				.getHeadOfficeId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Retrievs lab branch owner details
	 * 
	 * @param header
	 * @return BranchOwnerDetails
	 */
	@Override
	@Transactional
	public BranchOwnerDetails getBranchOwners(int labBranchId) {

		LabBranchTbl labBranch = getById(LabBranchTbl.class, labBranchId);
		BranchOwnerDetails response = new BranchOwnerDetails();
		response.setOwnerEmail(labBranch.getLabTbl().getOwnerEmail());
		response.setOwnerFirstName(labBranch.getLabTbl().getOwnerFirstName());
		response.setOwnerLastName(labBranch.getLabTbl().getOwnerLastName());
		response.setLabName(labBranch.getLabTbl().getName());
		LabPassphraseTbl labPassphraseTbl = getMacPassPhraseByBranch(labBranchId);
		response.setPassPhrase(labPassphraseTbl.getPassPhrase());
		response.setBranchName(labBranch.getName());
		return response;
	}

	/**
	 * To show all the total orders and its related details of each branch in
	 * the lab
	 * 
	 * @param globalId
	 * @return BranchOrdersResponseDTO
	 */
	@Override
	@Transactional
	public BranchOrdersResponseDTO viewBranchOrders(int globalId) {
		BranchOrdersResponseDTO response = new BranchOrdersResponseDTO();
		List<BranchOrderDetail> branchOrdersList = new ArrayList<BranchOrderDetail>();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		LabTbl labTbl = getById(LabTbl.class, globalId);
		if (labTbl == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<OrderAmountTbl> labBranchOrdersList = getBranchOrdersByLabId(globalId);
		for (OrderAmountTbl orderAmts : labBranchOrdersList) {
			BranchOrderDetail detail = new BranchOrderDetail();
			detail.setBranchId(orderAmts.getLabBranchTbl().getId());
			detail.setBranchName(orderAmts.getLabBranchTbl().getName());
			detail.setLastOrderdTime(sdf.format(orderAmts.getLastOrderedTime()));
			detail.setNetAmount(orderAmts.getNetAmount());
			detail.setPaidAmount(orderAmts.getPaidAmount());
			detail.setTotalOrders(orderAmts.getTotalOrders());
			detail.setOrderDate(df.format(orderAmts.getOrderDate()));
			branchOrdersList.add(detail);
		}
		response.setBranchOrders(branchOrdersList);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Create total orders in a branch
	 */
	@Override
	@Transactional
	public BranchOrderCountResponseDTO createTotalOrders(HeaderDTO header,
			BranchOrderDetail branchOrders) {
		BranchOrderCountResponseDTO response = new BranchOrderCountResponseDTO();
		DateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		DateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		LabPassphraseTbl passPhrase = getLabBranchByPassphrase(header
				.getPassPhrase());
		if (passPhrase == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getMacId() == null
				|| !passPhrase.getMacId().equals(header.getMacId())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getLabBranchTbl().getId() != header.getBranchId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getLabBranchTbl().getLabTbl().getId() != header
				.getHeadOfficeId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		Date orderDte = null;
		try {
			orderDte = sdf.parse(branchOrders.getOrderDate());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		OrderAmountTbl branchOrdrs = getOrdersByBranchOrderDate(
				header.getHeadOfficeId(), header.getBranchId(), orderDte);
		if (branchOrdrs == null) {
			OrderAmountTbl newBranchOrdrs = new OrderAmountTbl();
			newBranchOrdrs.setLabBranchTbl(passPhrase.getLabBranchTbl());
			newBranchOrdrs.setLabTbl(passPhrase.getLabBranchTbl().getLabTbl());
			try {
				newBranchOrdrs.setOrderDate(orderDte);
				newBranchOrdrs.setLastOrderedTime(df.parse(branchOrders
						.getLastOrderdTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			newBranchOrdrs.setTotalOrders(branchOrders.getTotalOrders());
			newBranchOrdrs.setPaidAmount(branchOrders.getPaidAmount());
			newBranchOrdrs.setNetAmount(branchOrders.getNetAmount());
			save(newBranchOrdrs);
			response.setGlobalId(newBranchOrdrs.getId());
		} else {
			try {
				branchOrdrs.setLastOrderedTime(df.parse(branchOrders
						.getLastOrderdTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			branchOrdrs.setTotalOrders(branchOrders.getTotalOrders());
			branchOrdrs.setPaidAmount(branchOrders.getPaidAmount());
			branchOrdrs.setNetAmount(branchOrders.getNetAmount());
			update(branchOrdrs);
			response.setGlobalId(branchOrdrs.getId());
		}
		response.setId(branchOrders.getId());
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method for viewing branch default system details
	 * 
	 * @param branchId
	 * @return BranchSystemInfoDetails
	 */
	@Override
	@Transactional
	public BranchSystemInfoDetails viewBranchSystemInfoDetails(int branchId) {
		BranchSystemInfoDetails details = new BranchSystemInfoDetails();
		List<SystemHealthMonitorDetailList> healthMonitorList = new ArrayList<SystemHealthMonitorDetailList>();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		LabBranchTbl labBranch = getById(LabBranchTbl.class, branchId);
		if (labBranch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		/*
		 * Getting system default information like critical memory level, freq
		 * type and so on
		 */
		LabBranchSystemInfoTbl systemInfo = getSystemDetailsByBranchId(branchId);
		if (systemInfo == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BranchSystemInfoNull);
			se.addParam(new Parameter(Constants.ID, Integer.toString(labBranch
					.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		details.setBranchId(labBranch.getId());
		details.setBranchName(systemInfo.getLabBranchTbl().getName());
		details.setCriticalCpuLevel(Float.toString(systemInfo
				.getCriticalCpuLevel()));
		details.setCriticalHardDiskSpaceLevel(Float.toString(systemInfo
				.getCriticalHardDiskSpaceLevel()));
		details.setCriticalMemoryLevel(Float.toString(systemInfo
				.getCriticalMemoryLevel()));
		details.setFreqType(systemInfo.getFreqType());
		details.setIntervalTime(Integer.toString(systemInfo.getIntervalTime()));
		/** Query for getting total number of records */
		int totalRecords = getTotalHealthMonitorRecords(branchId);
		int startIndex = 0;
		if (totalRecords > 10)
			startIndex = totalRecords - 10;

		/* Getting last 10 records of system health monitor details */
		List<LabHealthMonitorTbl> healthMonitorTblList = getMonitorDetailsByBranchId(
				branchId, startIndex);
		if (healthMonitorTblList.isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.SystemMonitorDetailsNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for (LabHealthMonitorTbl hMonitor : healthMonitorTblList) {
			SystemHealthMonitorDetailList systemHealth = new SystemHealthMonitorDetailList();

			systemHealth.setCpuUsage(hMonitor.getFreeCpuSpace());
			systemHealth.setHardDiskSpaceUasge(hMonitor.getFreeHardDiskSpace());
			systemHealth.setMemoryUsage(hMonitor.getFreeMemorySpace());
			systemHealth.setCreatedDateTime(sdf.format(hMonitor
					.getCreatedDateTime()));
			healthMonitorList.add(systemHealth);
			details.setTotalCpu(hMonitor.getTotalCpu());
			details.setTotalHardDisk(hMonitor.getTotalHardDisk());
			details.setTotalMemory(hMonitor.getTotalMemory());
		}
		details.setHealthMonitorList(healthMonitorList);

		details.setSuccess(true);
		return details;
	}

	/**
	 * Method for updating the branch default system details
	 * 
	 * @param details
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO updateLabBranchSystemInfo(BranchSystemInfoDetails details) {
		ResponseDTO response = new ResponseDTO();
		LabBranchTbl labBranch = getById(LabBranchTbl.class,
				details.getBranchId());
		if (labBranch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}

		LabBranchSystemInfoTbl systemInfo = getSystemDetailsByBranchId(labBranch
				.getId());
		if (systemInfo == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BranchSystemInfoNull);
			se.addParam(new Parameter(Constants.ID, Integer.toString(labBranch
					.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		systemInfo.setCriticalHardDiskSpaceLevel(Float.parseFloat(details
				.getCriticalHardDiskSpaceLevel()));
		systemInfo.setCriticalCpuLevel(Float.parseFloat(details
				.getCriticalCpuLevel()));
		systemInfo.setCriticalMemoryLevel(Float.parseFloat(details
				.getCriticalMemoryLevel()));
		systemInfo.setFreqType(details.getFreqType());
		systemInfo.setIntervalTime(Integer.parseInt(details.getIntervalTime()));
		update(systemInfo);

		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.LabDao#getLab(com.nv.youNeverWait.rs.
	 * dto.LabHeaderDTO, java.lang.String, java.util.Date)
	 */
	@Override
	@Transactional
	public LabDTO getLab(HeaderDTO header, String lastSyncTime,
			Date currentSyncTime) {
		LabDTO labDetails = new LabDTO();
		CheckHeaderDetails(header);
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		Date syncTime = null;
		try {
			syncTime = sdf.parse(lastSyncTime);
		} catch (ParseException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}

		LabTbl labTbl = getlabDetailsByTime(header.getHeadOfficeId(), syncTime,
				currentSyncTime);
		if (labTbl != null) {
			labDetails.setGlobalId(labTbl.getId());
			labDetails.setName(labTbl.getName());
			labDetails.setOwnerAddress(labTbl.getOwnerAddress());
			labDetails.setOwnerEmail(labTbl.getOwnerEmail());
			labDetails.setOwnerMobile(labTbl.getOwnerMobile());
			labDetails.setOwnerFirstName(labTbl.getOwnerFirstName());
			labDetails.setOwnerLastName(labTbl.getOwnerLastName());
			labDetails.setOwnerPhone(labTbl.getOwnerPhone());
			labDetails.setHeadOfficeAddress(labTbl.getHeadOfficeAddress());
			labDetails.setHeadOfficeEmail(labTbl.getHeadOfficeEmail());
			labDetails.setHeadOfficeMobile(labTbl.getHeadOfficeMobile());
			labDetails.setHeadOfficeName(labTbl.getHeadOfficeName());
			labDetails.setHeadOfficePhone(labTbl.getHeadOfficePhone());
			labDetails.setStatus(labTbl.getStatus());
			// labDetails.setLabCode(labTbl.getLabCode());
			labDetails.setOrderType(labTbl.getOrderTypeCode());
		}
		return labDetails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.LabDao#getLabSyncDetails(int)
	 */
	@Override
	@Transactional
	public SyncFreqDTO getLabSyncDetails(int labId) {
		SyncFreqDTO sync = new SyncFreqDTO();
		LabTbl lab = getById(LabTbl.class, labId);
		if (lab != null) {
			sync.setSyncFreqType(lab.getSyncFreqType());
			sync.setSyncTime(lab.getSyncTime());
			sync.setEnableSync(lab.isEnableSync());
			sync.setSuccess(true);
		} else {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(labId)));
			se.setDisplayErrMsg(true);
			throw se;
		}

		return sync;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.LabDao#getBranchSyncDetails(int)
	 */
	@Override
	@Transactional
	public SyncFreqDTO getBranchSyncDetails(int branchId) {
		SyncFreqDTO sync = new SyncFreqDTO();
		LabBranchTbl labBranch = getById(LabBranchTbl.class, branchId);
		if (labBranch != null) {
			sync.setSyncFreqType(labBranch.getSyncFreqType());
			sync.setSyncTime(labBranch.getSyncTime());
			sync.setEnableSync(labBranch.isEnableSync());
			sync.setSuccess(true);
		} else {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branchId)));
			se.setDisplayErrMsg(true);
			throw se;
		}

		return sync;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.LabDao#setLabSync(com.nv.youNeverWait
	 * .rs.dto.SyncFreqDTO)
	 */
	@Override
	@Transactional
	public SyncFreqResponseDTO setLabSync(SyncFreqDTO sync) {
		SyncFreqResponseDTO response = new SyncFreqResponseDTO();
		Date newDate = new Date();
		LabTbl lab = getById(LabTbl.class, sync.getLabId());
		SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
		if (lab != null) {
			if (superAdmin.getEnableSync() == false) {
				lab.setEnableSync(false);
			} else {
				lab.setEnableSync(sync.isEnableSync());
			}
			lab.setUpdateDateTime(newDate);
			update(lab);
			/**** Setting values when the sync is enabled ****/
			if (lab.isEnableSync() == true) {

				/****** Checking sync values with global sync time *****/
				checkSync(superAdmin.getSyncFreqType(), sync.getSyncFreqType(),
						sync.getSyncTime(), superAdmin.getSyncTime());

				lab.setSyncTime(sync.getSyncTime());
				lab.setSyncFreqType(sync.getSyncFreqType());
				lab.setUpdateDateTime(newDate);
				update(lab);

			} else {
				if (sync.isEnableSync())
					response.setMsg(Constants.MESSAGE);
				/****** Setting all branches of the lab as disabled *******/
				for (LabBranchTbl labBranch : lab.getLabBranchTbls()) {
					labBranch.setEnableSync(lab.isEnableSync());
					labBranch.setUpdateDateTime(newDate);
					update(labBranch);

				}// end of for loop
			}

		} else {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(sync
					.getLabId())));
			se.setDisplayErrMsg(true);
			throw se;
		}

		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.LabDao#setBranchSync(com.nv.youNeverWait
	 * .rs.dto.SyncFreqDTO)
	 */
	@Override
	@Transactional
	public SyncFreqResponseDTO setBranchSync(SyncFreqDTO sync) {
		SyncFreqResponseDTO response = new SyncFreqResponseDTO();
		Date newDate = new Date();
		LabBranchTbl labBranch = getById(LabBranchTbl.class,
				sync.getLabBranchId());
		if (labBranch != null) {
			if (labBranch.getLabTbl().isEnableSync() == false) {
				labBranch.setEnableSync(false);
			} else {
				SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
				if (superAdmin.getEnableSync() == false) {
					labBranch.setEnableSync(false);
				} else {
					labBranch.setEnableSync(sync.isEnableSync());
				}
			}
			labBranch.setUpdateDateTime(newDate);
			update(labBranch);
			if (labBranch.isEnableSync() == true) {
				/**
				 * Checking whether branch sync time is greater than lab sync
				 * time
				 **/
				checkSync(labBranch.getLabTbl().getSyncFreqType(),
						sync.getSyncFreqType(), sync.getSyncTime(), labBranch
						.getLabTbl().getSyncTime());

				labBranch.setSyncTime(sync.getSyncTime());
				labBranch.setSyncFreqType(sync.getSyncFreqType());
				labBranch.setUpdateDateTime(newDate);
				update(labBranch);
			} else {
				if (sync.isEnableSync())
					response.setMsg(Constants.MESSAGE);
			}
		} else {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setSuccess(true);
		return response;
	}

	/**
	 * @param labId
	 * @param syncFreqType
	 * @param syncTime
	 */
	private void checkSync(String priorSyncFreqType, String syncFreqType,
			int syncTime, int priorSyncTime) {
		if (priorSyncFreqType.equals(SyncFreqTypeEnum.DAILY.getDisplayName())) {
			if (syncFreqType.equals(SyncFreqTypeEnum.DAILY.getDisplayName())) {
				if (syncTime > priorSyncTime) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.SynctimeExceeds);
					se.setDisplayErrMsg(true);
					throw se;
				}
			}
		} // end of daily if loop
		if (priorSyncFreqType.equals(SyncFreqTypeEnum.HOURLY.getDisplayName())) {
			if (syncFreqType.equals(SyncFreqTypeEnum.DAILY.getDisplayName())) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.SynctimeExceeds);
				se.setDisplayErrMsg(true);
				throw se;
			} else if (syncFreqType.equals(SyncFreqTypeEnum.HOURLY
					.getDisplayName())) {
				if (syncTime > priorSyncTime) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.SynctimeExceeds);
					se.setDisplayErrMsg(true);
					throw se;
				}
			}
		} // end of hourly if loop
		if (priorSyncFreqType.equals(SyncFreqTypeEnum.MINUTES.getDisplayName())) {
			if (syncFreqType.equals(SyncFreqTypeEnum.DAILY.getDisplayName())
					|| syncFreqType.equals(SyncFreqTypeEnum.HOURLY
							.getDisplayName())) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.SynctimeExceeds);
				se.setDisplayErrMsg(true);
				throw se;
			} else if (syncFreqType.equals(SyncFreqTypeEnum.MINUTES
					.getDisplayName())) {
				if (syncTime > priorSyncTime) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.SynctimeExceeds);
					se.setDisplayErrMsg(true);
					throw se;
				}
			}
		} // end of minutes if loop
	}

	@Override
	@Transactional
	public ResponseDTO saveResult(LabResultHeaderDTO labResultHeader) {
		ResponseDTO response = new ResponseDTO();
		CheckHeaderDetails(labResultHeader.getHeader());
		OrderBranchTbl orderBrnchTbl = getByOrderUid(labResultHeader
				.getOrderUid());
		if (orderBrnchTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOrderUid);
			se.setDisplayErrMsg(true);
			throw se;
		}
		LabTbl labTbl = getById(LabTbl.class, labResultHeader.getSourceLabId());
		if (labTbl == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(labResultHeader.getSourceLabId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		LabBranchTbl labBranchTbl = getById(LabBranchTbl.class,
				labResultHeader.getSourceLabBranchId());
		if (labBranchTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		Date currentDateTime = new Date();
		OrderResultTbl orderTestResult = new OrderResultTbl();
		orderTestResult.setOrderBranchTbl(orderBrnchTbl);
		orderTestResult.setResult(labResultHeader.getResultDetails());
		orderTestResult.setTestUid(labResultHeader.getTestUId());
		orderTestResult.setLabBranchTbl(labBranchTbl);
		orderTestResult.setLabTbl(labTbl);
		orderTestResult.setOwnerLabBranchTbl(orderBrnchTbl.getLabBranchTbl());
		orderTestResult.setCreatedDateTime(currentDateTime);
		orderTestResult.setUpdatedDateTime(currentDateTime);
		save(orderTestResult);

		response.setGlobalId(orderTestResult.getId());
		response.setSuccess(true);
		return response;
	}

	@Override
	public List<OrderTransferTbl> getDestinationBranches(int orderId) {
		javax.persistence.Query query = em.createQuery(Query.GET_DESTINATION_BRANCHES_BY_ORDER_ID);
		query.setParameter("param1", orderId);
		return executeQuery(OrderTransferTbl.class, query);
	}
	private OrderBranchTbl getByOrderUid(String orderUid) {
		javax.persistence.Query query = em.createQuery(Query.GET_ORDER_BY_UID);
		query.setParameter("param1", orderUid);
		return executeUniqueQuery(OrderBranchTbl.class, query);

	}

	/**
	 * @param branchId
	 * @return
	 */
	private int getTotalHealthMonitorRecords(int branchId) {
		int totalRecords = 0;
		javax.persistence.Query query = em.createQuery(Query.GET_TOTAL_RECORDS);
		query.setParameter("param1", branchId);
		totalRecords = ((Number) query.getSingleResult()).intValue();
		return totalRecords;
	}

	/**
	 * @param labId
	 * @param syncTime
	 * @param currentSyncTime
	 * @return
	 */
	private LabTbl getlabDetailsByTime(int labId, Date lastSyncTime,
			Date currentSyncTime) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_LAB_DETAILS_BY_TIME);
		query.setParameter("param1", labId);
		query.setParameter("param2", lastSyncTime);
		query.setParameter("param3", currentSyncTime);
		return executeUniqueQuery(LabTbl.class, query);
	}

	private List<LabHealthMonitorTbl> getMonitorDetailsByBranchId(int branchId,
			int startIndex) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_MONITORING_DETAILS_BY_BRANCH_ID);
		query.setParameter("param1", branchId);
		query.setFirstResult(startIndex);
		query.setMaxResults(10);
		return executeQuery(LabHealthMonitorTbl.class, query);
	}

	/**
	 * Get Orders By Branch OrderDate
	 * 
	 * @param labId
	 * @param labBranchId
	 * @param orderDate
	 * @return
	 */
	private OrderAmountTbl getOrdersByBranchOrderDate(int labId,
			int labBranchId, Date orderDate) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_BRANCH_ORDERS_BY_LAB);
		query.setParameter("param1", labId);
		query.setParameter("param2", labBranchId);
		query.setParameter("param3", orderDate);
		return executeUniqueQuery(OrderAmountTbl.class, query);
	}

	/**
	 * Get current date
	 * 
	 * @return newDate
	 */
	private Date getCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		Date newDate = null;
		try {
			newDate = df.parse(df.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}

	/**
	 * Method to get lab name
	 */
	@Transactional
	@Override
	public String getLabName(int labId) {
		LabTbl lab = getById(LabTbl.class, labId);
		if (lab == null) {
			return "";
		}
		return lab.getName();
	}

	/**
	 * Retrieve doctor details by email id
	 * 
	 * @param eMail
	 * @return DoctorTbl
	 */
	public List<NetmdDoctorTbl> getDoctorByEmail(String eMail) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DOCTOR_BY_EMAIL);
		query.setParameter("param1", eMail);
		return executeQuery(NetmdDoctorTbl.class, query);
	}

	/**
	 * Method to retrieve new netmd list
	 * 
	 * @param syncTime
	 * @return
	 */
	public List<NetmdTbl> getNewNetmd(Date syncTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_NEW_NETMD);
		query.setParameter("param1", syncTime);
		return executeQuery(NetmdTbl.class, query);
	}

	/**
	 * Method to retrieve updated netmd list
	 * 
	 * @param syncTime
	 * @return
	 */
	public List<NetmdTbl> getUpdateNetmd(Date syncTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_UPDATE_NETMD);
		query.setParameter("param1", syncTime);
		return executeQuery(NetmdTbl.class, query);
	}

	/**
	 * 
	 * @param patientName
	 * @param netmdBranchId
	 * @param phone
	 * @return PatientTbl
	 */
	public NetmdPatientTbl getPatientByPhone(String patientName, int netmdBranchId,
			String phone) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_PHONE);
		query.setParameter("param1", patientName);
		query.setParameter("param2", netmdBranchId);
		query.setParameter("param3", phone);
		return executeUniqueQuery(NetmdPatientTbl.class, query);
	}

	/**
	 * 
	 * @param patientName
	 * @param netmdBranchId
	 * @param mobile
	 * @return PatientTbl
	 */
	public NetmdPatientTbl getPatientByMobile(String patientName, int netmdBranchId,
			String mobile) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_MOBILE);
		query.setParameter("param1", patientName);
		query.setParameter("param2", netmdBranchId);
		query.setParameter("param3", mobile);
		return executeUniqueQuery(NetmdPatientTbl.class, query);
	}

	/**
	 * 
	 * @param patientName
	 * @param netmdBranchId
	 * @param email
	 * @return PatientTbl
	 */
	public NetmdPatientTbl getPatientByEmail(String patientName, int netmdBranchId,
			String email) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_MAILID);
		query.setParameter("param1", patientName);
		query.setParameter("param2", netmdBranchId);
		query.setParameter("param3", email);
		return executeUniqueQuery(NetmdPatientTbl.class, query);
	}

	/**
	 * 
	 * @param sourceLabId
	 * @param sourceBranchId
	 * @return LabBranchTbl
	 */
	private boolean isValidBranch(int sourceLabId, int sourceBranchId) {
		javax.persistence.Query query = em.createQuery(Query.GET_BRANCH_BY_LAB);
		query.setParameter("param1", sourceLabId);
		query.setParameter("param2", sourceBranchId);
		if (executeUniqueQuery(LabBranchTbl.class, query) != null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param dat
	 * @param labId
	 * @param branchId
	 * @return
	 */
	private List<OrderTbl> getOrders(Date dat, int labId, int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ORDERS_BY_LAB_AND_BRANCH_ID);
		query.setParameter("param1", labId);
		query.setParameter("param2", branchId);
		query.setParameter("param3", dat);

		return executeQuery(OrderTbl.class, query);

	}

	/**
	 * 
	 * @param branchId
	 * @param labId
	 * @return LabBranchTbl
	 */
	public LabBranchTbl getByLabId(int branchId, int labId) {
		javax.persistence.Query query = em.createQuery(Query.GET_BRANCH);
		query.setParameter("param1", branchId);
		query.setParameter("param2", labId);
		return executeUniqueQuery(LabBranchTbl.class, query);
	}

	/**
	 * 
	 * @param password
	 * @param userName
	 * @return LabLoginTbl
	 */
	public LoginTbl getNetlimsUser(String password, String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETLIMS_USER_BY_PASSWORD);
		query.setParameter("param1", password);
		query.setParameter("param2", userName);
		return executeUniqueQuery(LoginTbl.class, query);
	}

	/**
	 * 
	 * @param userName
	 * @return LabLoginTbl
	 */
	public LoginTbl getNetlimsUserByName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETLIMS_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(LoginTbl.class, query);
	}

	/**
	 * 
	 * @param labId
	 * @param syncTime
	 * @return LabTbl
	 */
	private List<LabTbl> getNewLabs(int labId, Date syncTime, Date currentTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_NEW_LABS);
		query.setParameter("param1", labId);
		query.setParameter("param2", syncTime);
		query.setParameter("param3", currentTime);
		return executeQuery(LabTbl.class, query);
	}

	/**
	 * 
	 * @param userId
	 * @param syncTime
	 * @return UserTbl
	 */
	private List<UserTbl> getNewUsers(int userId, Date syncTime,
			Date currentTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_NEW_USERS);
		query.setParameter("param1", userId);
		query.setParameter("param2", syncTime);
		query.setParameter("param3", currentTime);
		return executeQuery(UserTbl.class, query);
	}

	/**
	 * 
	 * @param userId
	 * @param syncTime
	 * @return UserTbl
	 */
	private List<UserTbl> getUpdatedUsers(int userId, Date syncTime,
			Date currentTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_UPDATED_USERS);
		query.setParameter("param1", userId);
		query.setParameter("param2", syncTime);
		query.setParameter("param3", currentTime);
		return executeQuery(UserTbl.class, query);
	}

	/**
	 * 
	 * @param labId
	 * @param syncTime
	 * @return LabTbl
	 */
	private List<LabTbl> getUpdatedLabs(int labId, Date syncTime,
			Date currentTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_UPDATED_LABS);
		query.setParameter("param1", labId);
		query.setParameter("param2", syncTime);
		query.setParameter("param3", currentTime);
		return executeQuery(LabTbl.class, query);
	}

	/**
	 * 
	 * @param labId
	 * @param syncTime
	 * @return LabTbl
	 */
	private LabTbl getOwnLab(int labId, Date syncTime, Date currentTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_OWN_LAB);
		query.setParameter("param1", labId);
		query.setParameter("param2", syncTime);
		query.setParameter("param3", currentTime);
		return executeUniqueQuery(LabTbl.class, query);
	}

	/**
	 * 
	 * @param branchId
	 * @return LabUserTbl
	 */
	private List<LabUserTbl> getUsersInBranch(int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_USERS_IN_BRANCH);
		query.setParameter("param1", branchId);
		return (List<LabUserTbl>) executeQuery(LabUserTbl.class,
				query);
	}

	/**
	 * 
	 * @param passPhrase
	 * @return LabPassphraseTbl
	 */
	public LabPassphraseTbl getLabBranchByPassphrase(String passPhrase) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETLIMS_BRANCH_BY_PASSPHRASE);
		query.setParameter("param1", passPhrase);
		return executeUniqueQuery(LabPassphraseTbl.class, query);
	}

	/**
	 * 
	 * @param branchId
	 * @return LabPassphraseTbl
	 */
	public LabPassphraseTbl getMacPassPhraseByBranch(int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_MAC_AND_PASSPHRASE_BY_BRANCH);
		query.setParameter("param1", branchId);
		return executeUniqueQuery(LabPassphraseTbl.class, query);
	}

	/**
	 * 
	 * @param labId
	 * @return LabBranchTbl
	 */
	private List<LabBranchTbl> getLabBranches(int labId) {
		javax.persistence.Query query = em.createQuery(Query.GET_BRANCHES);
		query.setParameter("param1", labId);
		return (List<LabBranchTbl>) executeQuery(LabBranchTbl.class, query);
	}

	/**
	 * 
	 * @param labName
	 * @return LabTbl
	 */
	public LabTbl getLabByName(String labName) {
		javax.persistence.Query query = em.createQuery(Query.GET_LAB_BY_NAME);
		query.setParameter("param1", labName);
		return executeUniqueQuery(LabTbl.class, query);
	}

	/**
	 * 
	 * @param loginId
	 * @return LabTbl
	 */
	public LabTbl getLabByLoginId(int loginId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_LAB_BY_LOGIN_ID);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(LabTbl.class, query);
	}

	/**
	 * Method to get Mac Id using passphrase
	 */
	public String getMacByPassphrase(String passPhrase) {
		try {
			javax.persistence.Query query = em
					.createQuery(Query.GET_NETLIMS_MAC_BY_PASSPHRASE);
			query.setParameter("param1", passPhrase);
			return (String) query.getSingleResult();
		} catch (Exception e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPassphrase);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * @param syncTime
	 * @param labId
	 * @return LabBranchTbl
	 */
	private List<OrderAmountTbl> getBranchOrdersByLabId(int labId) {
		javax.persistence.Query query = em.createQuery(Query.GET_BRANCH_ORDERS);
		query.setParameter("param1", labId);
		query.setParameter("param2", getCurrentDate());
		return executeQuery(OrderAmountTbl.class, query);
	}

	/**
	 * @param syncTime
	 * @param labId
	 * @return LabBranchTbl
	 */
	private List<LabBranchTbl> getOwnBranches(Date syncTime, int labId,
			Date currentTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_OWN_BRANCHES);
		query.setParameter("param1", labId);
		query.setParameter("param2", syncTime);
		query.setParameter("param3", currentTime);
		return executeQuery(LabBranchTbl.class, query);
	}

	/**
	 * 
	 * @param email
	 * @return UserTbl
	 */
	public UserTbl getNetlimsUserByEmail(String email) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETLIMS_USER_BY_EMAIL);
		query.setParameter("param1", email);
		return executeUniqueQuery(UserTbl.class, query);
	}

	/**
	 * 
	 * @param loginId
	 * @return UserTbl
	 */
	public UserTbl getLabUserByLoginId(int loginId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETLIMS_USER);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(UserTbl.class, query);
	}

	/***
	 * 
	 * @param userId
	 * @param branchId
	 * @return LabUserTbl
	 */
	public LabUserTbl getUserByBranch(int userId, int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_USER_BY_BRANCH);
		query.setParameter("param1", userId);
		query.setParameter("param2", branchId);
		return executeUniqueQuery(LabUserTbl.class, query);
	}

	/**
	 * 
	 * @param userId
	 * @return LabUserTbl
	 */
	public List<LabUserTbl> getBranchByUser(int userId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_BRANCH_BY_USER);
		query.setParameter("param1", userId);
		return executeQuery(LabUserTbl.class, query);
	}

	/**
	 * 
	 * @param userName
	 * @return LabLoginTbl
	 */
	private LoginTbl getLoginByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETLIMS_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(LoginTbl.class, query);
	}

	private ResultTbl getPatientResult(String orderUid, int netmdBranchId,
			int netMdId, int patientId, int sourceLabId, int sourceLabBranchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_RESULT);
		query.setParameter("param1", orderUid);
		query.setParameter("param2", netmdBranchId);
		query.setParameter("param3", netMdId);
		query.setParameter("param4", patientId);
		query.setParameter("param5", sourceLabId);
		query.setParameter("param6", sourceLabBranchId);
		return executeUniqueQuery(ResultTbl.class, query);
	}

	private LabBranchSystemInfoTbl getSystemDetailsByBranchId(int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_SYSTEM_DETAILS_BY_BRANCH_ID);
		query.setParameter("param1", branchId);
		return executeUniqueQuery(LabBranchSystemInfoTbl.class, query);
	}

	/**
	 * @param syncTime
	 * @param labId
	 * @return LabBranchTbl
	 */
	private List<LabBranchTbl> getNewBranches(Date syncTime, int labId,
			Date currentTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_NEW_BRANCHES);
		query.setParameter("param1", labId);
		query.setParameter("param2", syncTime);
		query.setParameter("param3", currentTime);
		return executeQuery(LabBranchTbl.class, query);
	}

	/**
	 * @param syncTime
	 * @param labId
	 * @return LabBranchTbl
	 */
	private List<LabBranchTbl> getUpdatedBranches(Date syncTime, int labId,
			Date currentTime) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_UPDATED_BRANCHES);
		query.setParameter("param1", labId);
		query.setParameter("param2", syncTime);
		query.setParameter("param3", currentTime);
		return executeQuery(LabBranchTbl.class, query);
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

	@Override
	@Transactional(readOnly=false)
	public int updateBranchUser(SyncUser user, Integer source_branch_id) {
		UserTbl newUser = getById(UserTbl.class, user.getGlobalId());
		if(newUser==null)
			return 0;
		newUser.setFirstName(user.getUser().getName());
		newUser.setPhone(user.getUser().getPhone());
		newUser.setEmail(user.getUser().getEmail());
		newUser.setMobile(user.getMobile());
		newUser.setAddress(user.getUser().getAddress());
		newUser.setUserType(user.getUserType());
		newUser.setUpdateDateTime(new Date());
		newUser.setSignature(user.getUser().getSignature());
		update(newUser);		
		return newUser.getId();
	}

	@Override
	@Transactional(readOnly=false)
	public int createBranchUser(SyncUser user, Integer source_branch_id) {
		UserTbl newUser = new UserTbl();
		LabUserTbl labUserTbl = new LabUserTbl();
		Date createdTime = new Date();
		newUser.setFirstName(user.getUser().getName());
		newUser.setLastName(user.getUser().getDesignation());
		newUser.setPhone(user.getUser().getPhone());
		newUser.setEmail(user.getUser().getEmail());
		newUser.setMobile(user.getMobile());
		newUser.setAddress(user.getUser().getAddress());
		newUser.setUserType(user.getUserType());
		newUser.setCreateDateTime(createdTime);
		newUser.setUpdateDateTime(createdTime);
		newUser.setSignature(user.getUser().getSignature());
		save(newUser);
		labUserTbl.setUserTbl(newUser);
		labUserTbl.setLabBranchTbl(getById(LabBranchTbl.class, source_branch_id));
		labUserTbl.setStatus(StatusEnum.Active.getDisplayName());
		labUserTbl.setRefUid(user.getUser().getUid());
		save(labUserTbl);
		return newUser.getId();
	}

	@Override
	@Transactional
	public UserInfo getUserByReferredUid(int referredUid, int branchId) {
		UserInfo userInfo = new UserInfo();
		LabUserTbl labUser = getByReferredUid(referredUid,branchId);
		userInfo.setUid(referredUid);
		userInfo.setName(labUser.getUserTbl().getFirstName());
		userInfo.setDesignation(labUser.getUserTbl().getLastName());
		userInfo.setSignature(labUser.getUserTbl().getSignature());
		return userInfo;
	}

	private LabUserTbl getByReferredUid(int referredUid, int branchId) {
		javax.persistence.Query query = em.createQuery(Query.GET_USER_BY_REFERRALID);
		query.setParameter("param1", referredUid);
		query.setParameter("param2", branchId);
		return executeUniqueQuery(LabUserTbl.class, query);
	}

	@Override
	public ListResponse getFacilityByFilter(FilterDTO filterParam, User user) {
		ListResponse response = new ListResponse();
		//get queryBuilder for test from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(Constants.FACILITY);
		for (ExpressionDTO exp : filterParam.getExp()) {
			//get filter from filter factory by setting expression name and value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		//build query
		TypedQuery<LabFacilityTbl> q =   queryBuilder.buildQuery(filterParam.isAsc(),
				filterParam.getFrom(),filterParam.getCount()); 
		Long count = queryBuilder.getCount();
		List<FacilityDTO> facilities = new ArrayList<FacilityDTO>();
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
		List<LabFacilityTbl> fac = queryBuilder.executeQuery(q);
		for(LabFacilityTbl facilityTbl: fac){
			FacilityDTO facility= new FacilityDTO();
			facility.setId(facilityTbl.getId());
			facility.setName(facilityTbl.getName());
			facility.setPhone(facilityTbl.getPhone());
			facility.setAddress(facilityTbl.getAddress());
			
			facilities.add(facility);
		}
		response.setList(facilities);
		response.setCount(count);
		return response;
	}
	
	public HealthCareOrganisationTbl getBranchDetailsById(int id){
		HealthCareOrganisationTbl tbl=null;
		List<HealthCareOrganisationTbl> healthOrgTbl = getBranchDetail(id);
		for(HealthCareOrganisationTbl tblObj : healthOrgTbl){
			return tblObj;
		}
		 return tbl;
	} 
	
	public List<LabBranchTbl> getLabBranchDetail(int labId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DETAILS_FROM_LAB_BRANCH_TBL);
		query.setParameter("param1", labId);
		return executeQuery(LabBranchTbl.class, query);
	}
	public List<NetrxBranchTbl> getNetPharmaDetail(int labId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DETAILS_FROM_NET_RX_BRANCH_TBL);
		query.setParameter("param1", labId);
		return executeQuery(NetrxBranchTbl.class, query);
	}
	public List<NetmdBranchTbl> getClinicDetail(int labId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DETAILS_FROM_NET_MD_BRANCH_TBL);
		query.setParameter("param1", labId);
		return executeQuery(NetmdBranchTbl.class, query);
	}
	
	public List<HealthCareOrganisationTbl> getHealthCareDetail(String publicKey){
		javax.persistence.Query query = em
				.createQuery(Query.GET_HEALTH_CARE_DEATILS);
		query.setParameter("param1", publicKey);
		return executeQuery(HealthCareOrganisationTbl.class, query);
	}
	@Transactional(readOnly=false)
	public int updatePublickey(String key,int id){
		javax.persistence.Query query = em.createQuery(Query.UPDATE_PUBLIC_KEY);
		query.setParameter("param1", key);
		query.setParameter("param2", id);
		int i = query.executeUpdate();
		return i;
	}
	public List<HealthCareOrganisationTbl> getBranchDetail(int branchId){
		javax.persistence.Query query = em
				.createQuery(Query.GET_BRANCH_DETAIL_BY_ID);
		query.setParameter("param1", branchId);
		return executeQuery(HealthCareOrganisationTbl.class, query);
		
	}

}
