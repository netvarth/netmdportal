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
import org.springframework.transaction.annotation.Transactional;
import com.nv.framework.util.text.StringEncoder;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ActionNameEnum;
import com.nv.youNeverWait.pl.entity.BranchStatusEnum;
import com.nv.youNeverWait.pl.entity.DoctorTbl;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.FrequencyEnum;
import com.nv.youNeverWait.pl.entity.HealthMonitorTbl;
import com.nv.youNeverWait.pl.entity.BranchSystemInfoTbl;
import com.nv.youNeverWait.pl.entity.LabBranchTbl;
import com.nv.youNeverWait.pl.entity.LabLoginTbl;
import com.nv.youNeverWait.pl.entity.LabPassphraseTbl;
import com.nv.youNeverWait.pl.entity.LabStatusEnum;
import com.nv.youNeverWait.pl.entity.LabTbl;
import com.nv.youNeverWait.pl.entity.LabUserBranchTbl;
import com.nv.youNeverWait.pl.entity.LabUserTbl;
import com.nv.youNeverWait.pl.entity.LabUserTypeEnum;
import com.nv.youNeverWait.pl.entity.NetmdBranchTbl;
import com.nv.youNeverWait.pl.entity.NetmdTbl;
import com.nv.youNeverWait.pl.entity.OrderAmountTbl;
import com.nv.youNeverWait.pl.entity.OrderTbl;
import com.nv.youNeverWait.pl.entity.PatientTbl;
import com.nv.youNeverWait.pl.entity.ResultTbl;
import com.nv.youNeverWait.pl.entity.UserStatusEnum;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.BranchOrderCountResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderDetail;
import com.nv.youNeverWait.rs.dto.BranchOrdersResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.HealthMonitorResponse;
import com.nv.youNeverWait.rs.dto.LabBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.LabBranchSystemInfoDetails;
import com.nv.youNeverWait.rs.dto.LabHeaderDTO;
import com.nv.youNeverWait.rs.dto.LabUserDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LabBranchDTO;
import com.nv.youNeverWait.rs.dto.LabActivationResponseDTO;
import com.nv.youNeverWait.rs.dto.LabBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.LabDTO;
import com.nv.youNeverWait.rs.dto.LabResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultRetrievalResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultTransferDTO;
import com.nv.youNeverWait.rs.dto.ResultTransferResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveLabListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveUserListResponseDTO;
import com.nv.youNeverWait.rs.dto.SystemHealthDetails;
import com.nv.youNeverWait.rs.dto.SystemHealthMonitorDetailList;
import com.nv.youNeverWait.rs.dto.TransferNetMdResultDTO;
import com.nv.youNeverWait.rs.dto.UserBranchDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.rs.dto.UserInfoDetail;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.LabDao;

public class LabDaoImpl extends GenericDaoHibernateImpl implements LabDao {

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
		LabUserTbl labUser = (LabUserTbl) getNetlimsUserByEmail(user.getEmail());
		if (labUser != null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.UserExists);
			se.setDisplayErrMsg(true);
			throw se;
		}

		/* Saving username and password in login tbl */

		String password = StringEncoder.encryptWithKey(user.getLogin()
				.getPassword());
		LabLoginTbl existingUser = (LabLoginTbl) getNetlimsUser(password, user
				.getLogin().getUserName().trim());
		if (existingUser != null) {

			ServiceException se = new ServiceException(ErrorCodeEnum.UserExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		LabLoginTbl loginTbl = new LabLoginTbl();
		loginTbl.setUserName(user.getLogin().getUserName());
		loginTbl.setPassword(password);
		loginTbl.setUserType(user.getLogin().getUserType());
		save(loginTbl);

		Date createdTime = new Date();
		LabUserTbl newUser = new LabUserTbl();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPhone(user.getPhone());
		newUser.setEmail(user.getEmail());
		newUser.setMobile(user.getMobile());
		newUser.setAddress(user.getAddress());
		newUser.setUserType(user.getUserType());
		newUser.setLabLoginTbl(loginTbl);
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
				LabUserBranchTbl newUserBranch = new LabUserBranchTbl();
				newUserBranch.setLabUserTbl(newUser);
				newUserBranch.setLabBranchTbl(branch);
				newUserBranch.setStatus(UserStatusEnum.Active.getDisplayName());
				save(newUserBranch);
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
		LabUserTbl labUser = (LabUserTbl) getById(LabUserTbl.class,
				user.getGlobalId());
		if (labUser == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (user.getFirstName() != null) {

			/* checking whether the name already exists */
			LabUserTbl userName = (LabUserTbl) getNetlimsUserByEmail(user
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
				LabUserBranchTbl userBranch = (LabUserBranchTbl) getUserByBranch(
						user.getGlobalId(), labBranch.getBranchId());
				if (userBranch == null) {
					if (labBranch.getActionName().equals(
							ActionNameEnum.ADD.getDisplayName())) {
						LabUserBranchTbl newUserBranch = new LabUserBranchTbl();
						newUserBranch.setLabBranchTbl(branch);
						newUserBranch.setLabUserTbl(labUser);
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
		LabUserTbl labUser = (LabUserTbl) getById(LabUserTbl.class, id);
		if (labUser == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<LabUserBranchTbl> userBranches = (List<LabUserBranchTbl>) getBranchByUser(labUser
				.getId());
		for (LabUserBranchTbl userBranch : userBranches) {
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
		LabUserTbl labUser = (LabUserTbl) getById(LabUserTbl.class, globalId);
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
		LabLoginTbl userLogin = getNetlimsUserByName(login.getUserName().trim());
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
			LabUserTbl labUser = getLabUserByLoginId(userLogin.getId());
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
		LabLoginTbl userLogin = getNetlimsUserByName(decrypedUserName);
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
		LabLoginTbl login = (LabLoginTbl) getLoginByUserName(passwords
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

		LabLoginTbl login = getLoginByUserName(lab.getUserName());
		if (login != null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.NetLimsAccountAlreadyExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		login = new LabLoginTbl();
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
		labTbl.setLabLoginTbl(login);
		labTbl.setStatus(LabStatusEnum.Active.getDisplayName());
		labTbl.setCreateDateTime(createdTime);
		labTbl.setUpdateDateTime(createdTime);
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

		labBranch.setLabTbl(lab);
		save(labBranch);
		response.setGlobalId(labBranch.getId());

		LabPassphraseTbl labPassPhrase = new LabPassphraseTbl();
		byte[] bytes = StringEncoder.getKey();
		String passphrase = StringEncoder.getKeyvalue(bytes);
		System.out.println("passPhrase:" + passphrase);
		labPassPhrase.setPassPhrase(passphrase);
		labPassPhrase.setLabBranchTbl(labBranch);
		save(labPassPhrase);

		/*Saving default system details in lab branch system info tbl*/
		BranchSystemInfoTbl systemDetails=new BranchSystemInfoTbl();
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
			for (LabUserBranchTbl labUserBranchTbl : labBranchTbl
					.getLabUserBranchTbls()) {
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
		for (LabUserBranchTbl user : labBranch.getLabUserBranchTbls()) {
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
		if (labTbl.getLabLoginTbl() != null) {
			LabLoginTbl ownerLogin = getById(LabLoginTbl.class, labTbl
					.getLabLoginTbl().getId());
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
	public LabActivationResponseDTO activateLab(LabHeaderDTO header) {
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
		List<LabUserBranchTbl> branchUsers = (List<LabUserBranchTbl>) getUsersInBranch(branchpassPhrase
				.getLabBranchTbl().getId());
		if (!branchUsers.isEmpty()) {
			for (LabUserBranchTbl user : branchUsers) {
				UserInfoDetail userDetail = new UserInfoDetail();
				if (user.getLabUserTbl() != null) {
					if (user.getLabUserTbl().getLabLoginTbl() != null) {
						LabLoginTbl userLogin = getById(LabLoginTbl.class, user
								.getLabUserTbl().getLabLoginTbl().getId());
						if (userLogin == null) {
							ServiceException se = new ServiceException(
									ErrorCodeEnum.UserLoginNull);
							se.addParam(new Parameter(Constants.ID, Integer
									.toString(user.getLabUserTbl()
											.getLabLoginTbl().getId())));
							se.setDisplayErrMsg(true);
							throw se;
						}
						userDetail.setUserName(userLogin.getUserName());
						userDetail.setUserType(userLogin.getUserType());
						userDetail.setPassword(userLogin.getPassword());
					}
					userDetail
					.setFirstName(user.getLabUserTbl().getFirstName());
					userDetail.setStatus(user.getStatus());
					userDetail.setEmail(user.getLabUserTbl().getEmail());
					userDetail.setPhone(user.getLabUserTbl().getPhone());
					userDetail.setMobile(user.getLabUserTbl().getMobile());
					userDetail.setAddress(user.getLabUserTbl().getAddress());
					userDetail.setGlobalId(user.getLabUserTbl().getId());
				}
				users.add(userDetail);
			}
		}
		response.setUsers(users);

		LabTbl lab = getById(LabTbl.class, branchpassPhrase.getLabBranchTbl()
				.getLabTbl().getId());
		// setting netmd details
		LabDTO newLab = new LabDTO();
		if (lab.getLabLoginTbl() != null) {
			LabLoginTbl ownerLogin = getById(LabLoginTbl.class, lab
					.getLabLoginTbl().getId());
			if (ownerLogin == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.UserLoginNull);
				se.addParam(new Parameter(Constants.ID, Integer.toString(lab
						.getLabLoginTbl().getId())));
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
		response.setLab(newLab);

		// setting branch details
		List<LabBranchDTO> branchList = new ArrayList<LabBranchDTO>();
		List<LabBranchTbl> branches = (List<LabBranchTbl>) getLabBranches(lab
				.getId());
		if (!branches.isEmpty()) {
			for (LabBranchTbl branch : branches) {
				LabBranchDTO newBranch = new LabBranchDTO();
				newBranch.setGlobalId(branch.getId());
				newBranch.setName(branch.getName());
				newBranch.setStatus(branch.getStatus());
				newBranch.setAddress(branch.getAddress());
				newBranch.setPhone(branch.getPhone());
				newBranch.setMobile(branch.getMobile());
				newBranch.setEmail(branch.getEmail());
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
				.getLabBranchId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getLabBranchTbl().getLabTbl().getId() != resultTranfer
				.getHeader().getLabId()) {
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
		List<DoctorTbl> doctorTbl = (List<DoctorTbl>) getDoctorByEmail(resultTranfer
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
		for (DoctorTbl doctr : doctorTbl) {
			count++;
			/* Getting patient record by phone */
			PatientTbl patientByPhone = getPatientByPhone(resultTranfer
					.getPatient().getFirstName().toUpperCase().trim(), doctr
					.getNetmdBranchTbl().getId(), resultTranfer.getPatient()
					.getPhone());
			if (patientByPhone == null) {
				/*
				 * Getting patient record by mobile when there is no patient
				 * record by phone
				 */
				PatientTbl patientByMobile = getPatientByMobile(resultTranfer
						.getPatient().getFirstName().toUpperCase().trim(),
						doctr.getNetmdBranchTbl().getId(), resultTranfer
						.getPatient().getMobile());
				if (patientByMobile == null) {
					/*
					 * Getting patient record by Email when there is no patient
					 * record by mobile
					 */
					PatientTbl patientByEmail = getPatientByEmail(resultTranfer
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
							result.setPatientTbl(patientByEmail);
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
						result.setPatientTbl(patientByMobile);
				}
			} else {
				/* Checking the result tbl record already exists */
				patientResults = getPatientResult(resultTranfer.getOrderUid(),
						doctr.getNetmdBranchTbl().getId(), doctr
						.getNetmdBranchTbl().getNetmdTbl().getId(),
						patientByPhone.getId(), resultTranfer.getSourceLabId(),
						resultTranfer.getSourceLabBranchId());

				if (patientResults == null)
					result.setPatientTbl(patientByPhone);
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
				.getHeader().getLabBranchId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getLabBranchTbl().getLabTbl().getId() != resultTranferDto
				.getHeader().getLabId()) {
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

		OrderTbl order = new OrderTbl();
		if (!isValidBranch(resultTranferDto.getSourceLabId(),
				resultTranferDto.getSourceBranchId())) {
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
		order.setSourceLabTbl(sourceLab);
		LabBranchTbl sourceLabBranch = getById(LabBranchTbl.class,
				resultTranferDto.getSourceBranchId());
		order.setSourceBranchTbl(sourceLabBranch);

		LabTbl destLab = getById(LabTbl.class,
				resultTranferDto.getDestinationLabId());
		order.setDestinationLabTbl(destLab);
		LabBranchTbl destLabBranch = getById(LabBranchTbl.class,
				resultTranferDto.getDestinationBranchId());
		order.setDestinationBranchTbl(destLabBranch);

		order.setLocalOrderUid(resultTranferDto.getOrderUid());
		order.setResult(resultTranferDto.getResult());
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());
		save(order);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Retrieve results from a branch
	 * 
	 * @param ResultTransferDTO
	 * @return ResultTransferResponseDTO
	 */
	@Transactional
	@Override
	public ResultRetrievalResponseDTO getResult(LabHeaderDTO header,
			String lastSyncTm, Date currentTime) {

		ResultRetrievalResponseDTO response = new ResultRetrievalResponseDTO();
		validateHeader(header);
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
		List<OrderTbl> orders = getOrders(dat, header.getLabId(),
				header.getLabBranchId());
		List<ResultTransferDTO> resultTransferDTOs = new ArrayList<ResultTransferDTO>();
		String lastSyncTime = null;

		for (OrderTbl order : orders) {

			ResultTransferDTO result = new ResultTransferDTO();
			result.setDestinationBranchId(order.getDestinationBranchTbl()
					.getId());
			result.setDestinationLabId(order.getDestinationLabTbl().getId());
			result.setOrderUid(order.getLocalOrderUid());
			result.setResult(order.getResult());
			result.setSourceBranchId(order.getSourceBranchTbl().getId());
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
	public LabBranchListResponseDTO retrieveLabBranchList(LabHeaderDTO header,
			String lastSyncTme, Date currentTime) {

		LabBranchListResponseDTO response = new LabBranchListResponseDTO();
		validateHeader(header);
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
				header.getLabId(), currentTime);
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
			ownBranchList.add(branch);

		}
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
	public RetrieveLabListResponseDTO retrieveLabList(LabHeaderDTO header,
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
		validateHeader(header);

		List<LabDTO> newLabList = new ArrayList<LabDTO>();
		List<LabDTO> updatedLabList = new ArrayList<LabDTO>();
		/* Query to get new labs */
		List<LabTbl> newLabs = getNewLabs(header.getLabId(), syncTime,
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
		LabTbl ownLab = getOwnLab(header.getLabId(), syncTime, currentTime);
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
		List<LabTbl> updatedLabs = getUpdatedLabs(header.getLabId(), syncTime,
				currentTime);
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
	public RetrieveUserListResponseDTO retrieveUserList(LabHeaderDTO header,
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
		validateHeader(header);

		List<UserInfoDetail> newUserList = new ArrayList<UserInfoDetail>();
		List<UserInfoDetail> updatedUserList = new ArrayList<UserInfoDetail>();
		List<LabUserBranchTbl> userBranches = (List<LabUserBranchTbl>) getUsersInBranch(header
				.getLabBranchId());

		if (!userBranches.isEmpty()) {
			for (LabUserBranchTbl labUserBranch : userBranches) {

				List<LabUserTbl> newUsers = getNewUsers(labUserBranch
						.getLabUserTbl().getId(), syncTime, currentTime);
				for (LabUserTbl labUserTbl : newUsers) {

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
				List<LabUserTbl> updatedUsers = getUpdatedUsers(labUserBranch
						.getLabUserTbl().getId(), syncTime, currentTime);
				for (LabUserTbl labUserTbl : updatedUsers) {
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
	public void validateHeader(LabHeaderDTO header) {

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
		if (passPhrase.getLabBranchTbl().getId() != header.getLabBranchId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getLabBranchTbl().getLabTbl().getId() != header
				.getLabId()) {
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
	 * Method to show the branch order list for a given period
	 */
	@Override
	@Transactional
	public BranchOrdersResponseDTO orderList(BranchOrderDTO orderDTO) {
		BranchOrdersResponseDTO response = new BranchOrdersResponseDTO();
		List<BranchOrderDetail> branchOrdersList = new ArrayList<BranchOrderDetail>();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);

		try {

			List<OrderAmountTbl> labBranchOrdersList = getBranchOrdersByDate(
					df.parse(orderDTO.getFromDate()),
					df.parse(orderDTO.getToDate()), orderDTO.getLabId(),
					orderDTO.getLabBranchId());
			for (OrderAmountTbl orderAmts : labBranchOrdersList) {
				BranchOrderDetail detail = new BranchOrderDetail();
				detail.setBranchId(orderAmts.getLabBranchTbl().getId());
				detail.setBranchName(orderAmts.getLabBranchTbl().getName());
				detail.setLastOrderdTime(sdf.format(orderAmts
						.getLastOrderedTime()));
				detail.setNetAmount(orderAmts.getNetAmount());
				detail.setPaidAmount(orderAmts.getPaidAmount());
				detail.setTotalOrders(orderAmts.getTotalOrders());
				detail.setOrderDate(df.format(orderAmts.getOrderDate()));
				branchOrdersList.add(detail);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public BranchOrderCountResponseDTO createTotalOrders(LabHeaderDTO header,
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
		if (passPhrase.getLabBranchTbl().getId() != header.getLabBranchId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passPhrase.getLabBranchTbl().getLabTbl().getId() != header
				.getLabId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		Date orderDte = null;
		try {
			orderDte = sdf.parse(branchOrders.getOrderDate());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		OrderAmountTbl branchOrdrs = getOrdersByBranchOrderDate(
				header.getLabId(), header.getLabBranchId(), orderDte);
		if (branchOrdrs == null) {
			OrderAmountTbl newBranchOrdrs = new OrderAmountTbl();
			newBranchOrdrs.setLabBranchTbl(passPhrase.getLabBranchTbl());
			newBranchOrdrs.setLabTbl(passPhrase.getLabBranchTbl().getLabTbl());
			try {
				newBranchOrdrs.setOrderDate(orderDte);
				newBranchOrdrs.setLastOrderedTime(df.parse(branchOrders
						.getLastOrderdTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.security.pl.dao.AuthenticationDao#getHealthMonitor
	 * (com.nv.youNeverWait.rs.dto.SystemHealthDetails)
	 */
	@Override
	@Transactional
	public HealthMonitorResponse checkSystemHealth(
			SystemHealthDetails systemHealthDetails) {
		HealthMonitorResponse response = new HealthMonitorResponse();
		HealthMonitorTbl healthMonitor = new HealthMonitorTbl();
		int intervalTym = 0;
		String frequencyType = null;
		boolean criticalFlag = false;
		Date newDate = new Date();
		validateHeader(systemHealthDetails.getHeader()); // validates header details
		LabBranchTbl labBranch = getById(LabBranchTbl.class,
				systemHealthDetails.getHeader().getLabBranchId());
		if (labBranch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		int cpuUsage = Integer.parseInt(systemHealthDetails.getCpuUsage());
		int memoryUsed = Integer.parseInt(systemHealthDetails.getMemoryUsed());
		int hardDiskUsed = Integer.parseInt(systemHealthDetails
				.getHardDiskUsed());

		int freeHardDiskSpace = Integer.parseInt(systemHealthDetails
				.getTotalHardDiskSpace()) - hardDiskUsed; // available hard disk space
		int freeMemorySpace=Integer.parseInt(systemHealthDetails.getTotalMemorySpace())- memoryUsed; // available memory space
		int freeCpuSpace=Integer.parseInt(systemHealthDetails.getTotalCpuSpace())- cpuUsage; // available cpu space
		float freeHardDiskSpaceInPercent=(freeHardDiskSpace/Integer.parseInt(systemHealthDetails.getTotalHardDiskSpace()))*100;
		float freeMemorySpaceInPercent=(freeMemorySpace/Integer.parseInt(systemHealthDetails.getTotalMemorySpace()))*100;
		float freeCpuSpaceInPercent=(freeCpuSpace/Integer.parseInt(systemHealthDetails.getTotalCpuSpace()))*100;
		/*Getting the branch default system details from branch system info tbl*/
		BranchSystemInfoTbl branchSystemInfo= getSystemDetailsByBranchId(labBranch.getId());
		if(branchSystemInfo==null)
		{
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BranchSystemInfoNull);
			se.addParam(new Parameter(Constants.ID,Integer.toString(labBranch.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}

		/* Checking whether system is in critical condition */
		if (freeHardDiskSpaceInPercent <=branchSystemInfo.getCriticalHardDiskSpaceLevel()) {
			criticalFlag = true;
			intervalTym = branchSystemInfo.getIntervalTime();
			frequencyType =branchSystemInfo.getFreqType();
		}

		if (freeMemorySpaceInPercent <= branchSystemInfo.getCriticalMemoryLevel()) {
			criticalFlag = true;
			intervalTym = branchSystemInfo.getIntervalTime();
			frequencyType =branchSystemInfo.getFreqType();
		}

		if (freeCpuSpaceInPercent <=branchSystemInfo.getCriticalCpuLevel()) {
			criticalFlag=true;
			intervalTym = branchSystemInfo.getIntervalTime();
			frequencyType =branchSystemInfo.getFreqType();
		}


		/* Saving system details in health monitor tbl */
		healthMonitor.setLabBranchTbl(labBranch);
		healthMonitor.setHardDiskUsage(hardDiskUsed);
		healthMonitor.setCpuUsage(cpuUsage);
		healthMonitor.setMemoryUsage(memoryUsed);
		healthMonitor.setFreqType(frequencyType);
		healthMonitor.setIntervalTime(intervalTym);
		healthMonitor.setCreateDateTime(newDate);
		healthMonitor.setUpdateDateTime(newDate);
		save(healthMonitor);

		response.setIntervalTime(Integer.toString(intervalTym));
		response.setFreqPeriod(frequencyType);
		response.setCritical(criticalFlag);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method for viewing branch default system details
	 * @param branchId
	 * @return
	 */
	@Override
	@Transactional
	public LabBranchSystemInfoDetails viewBranchSystemInfoDetails(int branchId) {
		LabBranchSystemInfoDetails details= new LabBranchSystemInfoDetails();
		List<SystemHealthMonitorDetailList> healthMonitorList= new ArrayList<SystemHealthMonitorDetailList>();
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		LabBranchTbl labBranch = getById(LabBranchTbl.class,branchId);
		if (labBranch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* Getting system default information like critical memory level, freq type and so on*/
		BranchSystemInfoTbl systemInfo= getSystemDetailsByBranchId(branchId);
		if(systemInfo==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BranchSystemInfoNull);
			se.addParam(new Parameter(Constants.ID,Integer.toString(labBranch.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		details.setBranchId(labBranch.getId());
		details.setBranchName(systemInfo.getLabBranchTbl().getName());
		details.setCriticalCpuLevel(Integer.toString(systemInfo.getCriticalCpuLevel()));
		details.setCriticalHardDiskSpaceLevel(Integer.toString(systemInfo.getCriticalHardDiskSpaceLevel()));
		details.setCriticalMemoryLevel(Integer.toString(systemInfo.getCriticalMemoryLevel()));
		details.setFreqType(systemInfo.getFreqType());
		details.setIntervalTime(Integer.toString(systemInfo.getIntervalTime()));
		/*Getting last 10 records of system health monitor details*/
		List<HealthMonitorTbl> healthMonitorTblList = getMonitorDetailsByBranchId(branchId); /// query for fetching the latest record corresponding to this branch 
		if(healthMonitorTblList.isEmpty()){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.SystemMonitorDetailsNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(HealthMonitorTbl hMonitor:healthMonitorTblList){
			SystemHealthMonitorDetailList systemHealth= new SystemHealthMonitorDetailList();
			systemHealth.setCpuUsage(hMonitor.getCpuUsage());
			systemHealth.setHardDiskSpaceUasge(hMonitor.getHardDiskUsage());
			systemHealth.setMemoryUsage(hMonitor.getMemoryUsage());
			systemHealth.setCreatedDateTime(sdf.format(hMonitor.getCreateDateTime()));
			healthMonitorList.add(systemHealth);
		}
		details.setHealthMonitorList(healthMonitorList);

		details.setSuccess(true);
		return details;
	}

	/**
 	 * Method for updating the branch default system details
	 * @param details
 	 * @return
     */
	@Override
	@Transactional
	public ResponseDTO updateLabBranchSystemInfo(
			LabBranchSystemInfoDetails details) {
		ResponseDTO response =new ResponseDTO();
		LabBranchTbl labBranch = getById(LabBranchTbl.class,details.getBranchId());
		if (labBranch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}

		BranchSystemInfoTbl systemInfo= getSystemDetailsByBranchId(labBranch.getId());
		if(systemInfo==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BranchSystemInfoNull);
			se.addParam(new Parameter(Constants.ID,Integer.toString(labBranch.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		systemInfo.setCriticalHardDiskSpaceLevel(Integer.parseInt(details.getCriticalHardDiskSpaceLevel()));
		systemInfo.setCriticalCpuLevel(Integer.parseInt(details.getCriticalCpuLevel()));
		systemInfo.setCriticalMemoryLevel(Integer.parseInt(details.getCriticalMemoryLevel()));
		systemInfo.setFreqType(details.getFreqType());
		systemInfo.setIntervalTime(Integer.parseInt(details.getIntervalTime()));
		update(systemInfo);
		
		response.setSuccess(true);
		return response;
	}
	
	private List<HealthMonitorTbl> getMonitorDetailsByBranchId(int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_MONITORING_DETAILS_BY_BRANCH_ID);
		query.setParameter("param1", branchId);
		query.setMaxResults(10);
		return executeQuery(HealthMonitorTbl.class, query);
	}

	private BranchSystemInfoTbl getSystemDetailsByBranchId(int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_SYSTEM_DETAILS_BY_BRANCH_ID);
		query.setParameter("param1", branchId);
		return executeUniqueQuery(BranchSystemInfoTbl.class, query);
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
	 * Get Branch Orders ByDate
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param labId
	 * @param labBranchId
	 * @return
	 */
	private List<OrderAmountTbl> getBranchOrdersByDate(Date fromDate,
			Date toDate, int labId, int labBranchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_BRANCH_ORDERS_BY_DATE);
		query.setParameter("param1", fromDate);
		query.setParameter("param2", toDate);
		query.setParameter("param3", labId);
		query.setParameter("param4", labBranchId);
		return executeQuery(OrderAmountTbl.class, query);
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
			// TODO Auto-generated catch block
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
	public List<DoctorTbl> getDoctorByEmail(String eMail) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DOCTOR_BY_EMAIL);
		query.setParameter("param1", eMail);
		return executeQuery(DoctorTbl.class, query);
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
	public PatientTbl getPatientByPhone(String patientName, int netmdBranchId,
			String phone) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_PHONE);
		query.setParameter("param1", patientName);
		query.setParameter("param2", netmdBranchId);
		query.setParameter("param3", phone);
		return executeUniqueQuery(PatientTbl.class, query);
	}

	/**
	 * 
	 * @param patientName
	 * @param netmdBranchId
	 * @param mobile
	 * @return PatientTbl
	 */
	public PatientTbl getPatientByMobile(String patientName, int netmdBranchId,
			String mobile) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_MOBILE);
		query.setParameter("param1", patientName);
		query.setParameter("param2", netmdBranchId);
		query.setParameter("param3", mobile);
		return executeUniqueQuery(PatientTbl.class, query);
	}

	/**
	 * 
	 * @param patientName
	 * @param netmdBranchId
	 * @param email
	 * @return PatientTbl
	 */
	public PatientTbl getPatientByEmail(String patientName, int netmdBranchId,
			String email) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PATIENT_BY_MAILID);
		query.setParameter("param1", patientName);
		query.setParameter("param2", netmdBranchId);
		query.setParameter("param3", email);
		return executeUniqueQuery(PatientTbl.class, query);
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
	 * @param password
	 * @param userName
	 * @return
	 */
	public LabLoginTbl getNetlimsUser(String password, String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETLIMS_USER_BY_PASSWORD);
		query.setParameter("param1", password);
		query.setParameter("param2", userName);
		return executeUniqueQuery(LabLoginTbl.class, query);
	}

	/**
	 * 
	 * @param userName
	 * @return LabLoginTbl
	 */
	public LabLoginTbl getNetlimsUserByName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETLIMS_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(LabLoginTbl.class, query);
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
	 * @return LabUserTbl
	 */
	private List<LabUserTbl> getNewUsers(int userId, Date syncTime,
			Date currentTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_NEW_USERS);
		query.setParameter("param1", userId);
		query.setParameter("param2", syncTime);
		query.setParameter("param3", currentTime);
		return executeQuery(LabUserTbl.class, query);
	}

	/**
	 * 
	 * @param userId
	 * @param syncTime
	 * @return LabUserTbl
	 */
	private List<LabUserTbl> getUpdatedUsers(int userId, Date syncTime,
			Date currentTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_UPDATED_USERS);
		query.setParameter("param1", userId);
		query.setParameter("param2", syncTime);
		query.setParameter("param3", currentTime);
		return executeQuery(LabUserTbl.class, query);
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
	 * @return LabUserBranchTbl
	 */
	private List<LabUserBranchTbl> getUsersInBranch(int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_USERS_IN_BRANCH);
		query.setParameter("param1", branchId);
		return (List<LabUserBranchTbl>) executeQuery(LabUserBranchTbl.class,
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
	 * @return LabUserTbl
	 */
	public LabUserTbl getNetlimsUserByEmail(String email) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETLIMS_USER_BY_EMAIL);
		query.setParameter("param1", email);
		return executeUniqueQuery(LabUserTbl.class, query);
	}

	/**
	 * 
	 * @param loginId
	 * @return LabUserTbl
	 */
	public LabUserTbl getLabUserByLoginId(int loginId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETLIMS_USER);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(LabUserTbl.class, query);
	}

	/***
	 * 
	 * @param userId
	 * @param branchId
	 * @return LabUserBranchTbl
	 */
	public LabUserBranchTbl getUserByBranch(int userId, int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_USER_BY_BRANCH);
		query.setParameter("param1", userId);
		query.setParameter("param2", branchId);
		return executeUniqueQuery(LabUserBranchTbl.class, query);
	}

	/**
	 * 
	 * @param userId
	 * @return LabUserBranchTbl
	 */
	public List<LabUserBranchTbl> getBranchByUser(int userId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_BRANCH_BY_USER);
		query.setParameter("param1", userId);
		return executeQuery(LabUserBranchTbl.class, query);
	}

	/**
	 * 
	 * @param userName
	 * @return LabLoginTbl
	 */
	private LabLoginTbl getLoginByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETLIMS_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(LabLoginTbl.class, query);
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

	



}
