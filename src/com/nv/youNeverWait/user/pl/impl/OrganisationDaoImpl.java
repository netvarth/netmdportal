/**
 * OrganisationDaoImpl.java
 * @author netvarth
 *
 * Version 1.0 Jan 26, 2014
 *
 * Copyright (c) 2014 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.pl.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.nv.framework.util.text.StringEncoder;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.OrganisationLoginTbl;
import com.nv.youNeverWait.pl.entity.OrganisationTbl;
import com.nv.youNeverWait.pl.entity.OrganisationUserTbl;
import com.nv.youNeverWait.pl.entity.StatusEnum;
import com.nv.youNeverWait.pl.entity.SuperAdminTbl;
import com.nv.youNeverWait.pl.entity.UserTypeEnum;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.Organisation;
import com.nv.youNeverWait.rs.dto.OrganisationListResponseDTO;
import com.nv.youNeverWait.rs.dto.OrganisationUserDetail;
import com.nv.youNeverWait.rs.dto.OrganizationViewResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.rs.dto.UserDetails;
import com.nv.youNeverWait.rs.dto.ViewOrganisationUser;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.OrganisationDao;

/**
 * 
 * 
 * @author Luciya Jose
 */
public class OrganisationDaoImpl extends GenericDaoHibernateImpl implements
		OrganisationDao {
	@PersistenceContext()
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.OrganisationDao#createOrganisation(com
	 * .nv.youNeverWait.rs.dto.Organisation)
	 */
	@Override
	@Transactional
	public ResponseDTO createOrganisation(Organisation organztion) {
		ResponseDTO response = new ResponseDTO();
		SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
		OrganisationLoginTbl loginTbl = getLoginByUserName(organztion
				.getUserName());
		if (loginTbl != null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.OrganisationAlreadyExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		// save login details
		OrganisationLoginTbl login = new OrganisationLoginTbl();
		login.setUserName(organztion.getUserName());
		login.setUserType(UserTypeEnum.Owner.getDisplayName());
		String password = StringEncoder.encryptWithKey(organztion.getPassword()
				.trim());
		login.setPassword(password);
		save(login);

		// checking whether the organisation with given name already exists or
		// not
		if (organztion.getName() != null) {
			String alphaDigitsOnly = organztion.getName().replaceAll(
					"[^a-zA-Z0-9]+", "");
			OrganisationTbl dupOrgnstionTbl = (OrganisationTbl) getOrganisationByName(alphaDigitsOnly
					.toUpperCase().trim());
			if (dupOrgnstionTbl != null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.DuplicateOrganisationName);
				se.addParam(new Parameter(Constants.NAME, organztion.getName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		// save organisation details to organisation table
		OrganisationTbl organisationTbl = new OrganisationTbl();
		organisationTbl.setName(organztion.getName());
		organisationTbl.setOwnerFirstName(organztion.getOwnerFirstName());
		organisationTbl.setOwnerLastName(organztion.getOwnerLastName());
		organisationTbl.setOwnerEmail(organztion.getOwnerEmail());
		organisationTbl.setOwnerAddress(organztion.getOwnerAddress());
		organisationTbl.setOwnerMobile(organztion.getOwnerMobile());
		organisationTbl.setOwnerPhone(organztion.getOwnerPhone());
		organisationTbl.setOrganisationLoginTbl(login);
		organisationTbl.setHeadOfficeAddress(organztion.getHeadOfficeAddress());
		organisationTbl.setHeadOfficeEmail(organztion.getHeadOfficeEmail());
		organisationTbl.setHeadOfficeMobile(organztion.getHeadOfficeMobile());
		organisationTbl.setHeadOfficeName(organztion.getHeadOfficeName());
		organisationTbl.setHeadOfficePhone(organztion.getHeadOfficePhone());
		organisationTbl.setStatus(StatusEnum.Active.getDisplayName());
		organisationTbl.setDepartmentType(organztion.getDepartmentType());
		Date createdTime = new Date();
		organisationTbl.setCreateDateTime(createdTime);
		organisationTbl.setUpdateDateTime(createdTime);
		if (superAdmin.getEnableSync() == false) {
			organisationTbl.setEnableSync(false);
		} else {
			organisationTbl.setEnableSync(true);
			organisationTbl.setSyncFreqType(superAdmin.getSyncFreqType());
			organisationTbl.setSyncTime(superAdmin.getSyncTime());
		}
		save(organisationTbl);
		response.setGlobalId(organisationTbl.getId());
		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.OrganisationDao#updateOrganisation(com
	 * .nv.youNeverWait.rs.dto.Organisation)
	 */
	@Override
	@Transactional
	public ResponseDTO updateOrganisation(Organisation organztion) {
		ResponseDTO response = new ResponseDTO();
		if (organztion.getGlobalId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOrganisation);
			se.addParam(new Parameter(Constants.ID, Integer.toString(organztion
					.getGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		OrganisationTbl organisationTbl = getById(OrganisationTbl.class,
				organztion.getGlobalId());
		if (organisationTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOrganisation);
			se.addParam(new Parameter(Constants.ID, Integer.toString(organztion
					.getGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* checking whether the name already exists */
		String alphaDigitsOnly = organztion.getName().replaceAll(
				"[^a-zA-Z0-9]+", "");
		OrganisationTbl dupOrgnstionTbl = (OrganisationTbl) getOrganisationByName(alphaDigitsOnly
				.toUpperCase().trim());
		if (dupOrgnstionTbl != null) {
			if (dupOrgnstionTbl.getId() != organztion.getGlobalId()) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.OrganisationNameExists);
				se.addParam(new Parameter(Constants.NAME, organztion.getName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}

		dupOrgnstionTbl.setName(organztion.getName());
		dupOrgnstionTbl.setOwnerFirstName(organztion.getOwnerFirstName());
		dupOrgnstionTbl.setOwnerLastName(organztion.getOwnerLastName());
		dupOrgnstionTbl.setOwnerEmail(organztion.getOwnerEmail());
		dupOrgnstionTbl.setOwnerAddress(organztion.getOwnerAddress());
		dupOrgnstionTbl.setOwnerMobile(organztion.getOwnerMobile());
		dupOrgnstionTbl.setOwnerPhone(organztion.getOwnerPhone());
		dupOrgnstionTbl.setHeadOfficeAddress(organztion.getHeadOfficeAddress());
		dupOrgnstionTbl.setHeadOfficeEmail(organztion.getHeadOfficeEmail());
		dupOrgnstionTbl.setHeadOfficeMobile(organztion.getHeadOfficeMobile());
		dupOrgnstionTbl.setHeadOfficeName(organztion.getHeadOfficeName());
		dupOrgnstionTbl.setHeadOfficePhone(organztion.getHeadOfficePhone());
		dupOrgnstionTbl.setUpdateDateTime(new Date());
		update(dupOrgnstionTbl);

		response.setGlobalId(dupOrgnstionTbl.getId());
		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.OrganisationDao#viewOrganisation(int)
	 */
	@Override
	@Transactional
	public OrganizationViewResponseDTO viewOrganisation(int globalId) {
		OrganizationViewResponseDTO response = new OrganizationViewResponseDTO();
		OrganisationTbl organisationTbl = getById(OrganisationTbl.class,
				globalId);
		if (organisationTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOrganisation);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		Organisation organization = new Organisation();
		organization.setGlobalId(organisationTbl.getId());
		organization.setName(organisationTbl.getName());
		organization.setOwnerAddress(organisationTbl.getOwnerAddress());
		organization.setOwnerEmail(organisationTbl.getOwnerEmail());
		organization.setOwnerMobile(organisationTbl.getOwnerMobile());
		organization.setOwnerFirstName(organisationTbl.getOwnerFirstName());
		organization.setOwnerLastName(organisationTbl.getOwnerLastName());
		organization.setOwnerPhone(organisationTbl.getOwnerPhone());
		organization.setHeadOfficeAddress(organisationTbl
				.getHeadOfficeAddress());
		organization.setHeadOfficeEmail(organisationTbl.getHeadOfficeEmail());
		organization.setHeadOfficeMobile(organisationTbl.getHeadOfficeMobile());
		organization.setHeadOfficePhone(organisationTbl.getHeadOfficePhone());
		organization.setHeadOfficeName(organisationTbl.getHeadOfficeName());
		organization.setDepartmentType(organisationTbl.getDepartmentType());
		organization.setStatus(organisationTbl.getStatus());
		organization.setUserName(organisationTbl.getOrganisationLoginTbl()
				.getUserName());
		// organization.setPassword(organisationTbl.getOrganisationLoginTbl().getPassword());
		organization.setUserType(organisationTbl.getOrganisationLoginTbl()
				.getUserType());

		response.setOrganization(organization);
		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.OrganisationDao#deleteOrganisation(int)
	 */
	@Override
	@Transactional
	public ResponseDTO deleteOrganisation(int globalId) {
		ResponseDTO response = new ResponseDTO();
		OrganisationTbl organisationTbl = getById(OrganisationTbl.class,
				globalId);
		if (organisationTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOrganisation);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		Date updatedTime = new Date();
		organisationTbl.setStatus(StatusEnum.InActive.getDisplayName());
		organisationTbl.setUpdateDateTime(updatedTime);
		/* delete users for the organisation */

		List<OrganisationUserTbl> users = getUsersByOrganisationId(globalId);
		for (OrganisationUserTbl user : users) {

			user.setStatus(StatusEnum.InActive.getDisplayName());
			user.setUpdatedDateTime(updatedTime);
			update(user);
		}

		update(organisationTbl);
		response.setGlobalId(organisationTbl.getId());
		response.setSuccess(true);
		return response;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.OrganisationDao#createUser(com.nv.
	 * youNeverWait.rs.dto.OrganisationUserDetail)
	 */
	@Override
	@Transactional
	public ResponseDTO createUser(OrganisationUserDetail organztionUser) {
		ResponseDTO response = new ResponseDTO();
		OrganisationLoginTbl loginTbl = getLoginByUserName(organztionUser
				.getUserName());
		if (loginTbl != null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserAlreadyExists);
			se.addParam(new Parameter(Constants.NAME, organztionUser
					.getUserName()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		OrganisationTbl organisationTbl = getById(OrganisationTbl.class,
				organztionUser.getOrganisationId());
		if (organisationTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOrganisation);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(organztionUser.getOrganisationId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* checking whether the email already exists */
		String organisationUserEmail = organztionUser.getEmail().trim();
		OrganisationUserTbl duplicateOrgnstionUserTbl = (OrganisationUserTbl) getOrganisationUserByEmail(
				organisationUserEmail, organisationTbl.getId());
		if (duplicateOrgnstionUserTbl != null) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserAlreadyExists);
			se.addParam(new Parameter(Constants.NAME, organztionUser
					.getFirstName()));
			se.setDisplayErrMsg(true);
			throw se;
		}

		// save login details
		OrganisationLoginTbl login = new OrganisationLoginTbl();
		login.setUserName(organztionUser.getUserName());
		login.setUserType(organztionUser.getUserType());
		String password = StringEncoder.encryptWithKey(organztionUser
				.getPassword().trim());
		login.setPassword(password);
		save(login);

		OrganisationUserTbl newUser = new OrganisationUserTbl();
		newUser.setFirstName(organztionUser.getFirstName());
		newUser.setLastName(organztionUser.getLastName());
		newUser.setMobile(organztionUser.getMobile());
		newUser.setPhone(organztionUser.getPhone());
		newUser.setAddress(organztionUser.getAddress());
		newUser.setEmail(organztionUser.getEmail());
		newUser.setOrganisationTbl(organisationTbl);
		newUser.setOrganisationLoginTbl(login);
		newUser.setStatus(StatusEnum.Active.getDisplayName());
		Date currentDate = new Date();
		newUser.setCreatedDateTime(currentDate);
		newUser.setUpdatedDateTime(currentDate);
		save(newUser);
		response.setGlobalId(newUser.getId());
		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.OrganisationDao#getOrganisationName(int)
	 */
	@Override
	@Transactional
	public String getOrganisationName(int organisationId) {
		String organisationName = "";
		OrganisationTbl organisation = getById(OrganisationTbl.class,
				organisationId);
		if (organisation == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOrganisation);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(organisationId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		organisationName = organisation.getName();
		return organisationName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.OrganisationDao#updateUser(com.nv.
	 * youNeverWait.rs.dto.OrganisationUserDetail)
	 */
	@Override
	@Transactional
	public ResponseDTO updateUser(OrganisationUserDetail organztionUser) {
		ResponseDTO response = new ResponseDTO();

		OrganisationTbl organisationTbl = getById(OrganisationTbl.class,
				organztionUser.getOrganisationId());
		if (organisationTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOrganisation);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(organztionUser.getOrganisationId())));
			se.setDisplayErrMsg(true);
			throw se;
		}

		OrganisationUserTbl updateUser = getById(OrganisationUserTbl.class,
				organztionUser.getGlobalId());
		if (updateUser == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNotExists);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(organztionUser.getGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* checking whether the email already exists */
		String organisationUserEmail = organztionUser.getEmail().trim();
		OrganisationUserTbl duplicateOrgnstionUserTbl = (OrganisationUserTbl) getOrganisationUserByEmail(
				organisationUserEmail, organisationTbl.getId());
		if (duplicateOrgnstionUserTbl != null) {
			if (duplicateOrgnstionUserTbl.getId() != organztionUser
					.getGlobalId()) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.UserAlreadyExists);
				se.addParam(new Parameter(Constants.NAME, organztionUser
						.getFirstName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		updateUser.setFirstName(organztionUser.getFirstName());
		updateUser.setLastName(organztionUser.getLastName());
		updateUser.setMobile(organztionUser.getMobile());
		updateUser.setPhone(organztionUser.getPhone());
		updateUser.setAddress(organztionUser.getAddress());
		updateUser.setEmail(organztionUser.getEmail());

		Date currentDate = new Date();
		updateUser.setUpdatedDateTime(currentDate);
		update(updateUser);
		response.setGlobalId(updateUser.getId());
		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.OrganisationDao#viewUser(int)
	 */
	@Override
	@Transactional
	public ViewOrganisationUser viewUser(int globalId) {
		ViewOrganisationUser response = new ViewOrganisationUser();
		OrganisationUserTbl user = getById(OrganisationUserTbl.class, globalId);
		if (user == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNotExists);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		OrganisationUserDetail userDetail = new OrganisationUserDetail();
		userDetail.setAddress(user.getAddress());
		userDetail.setEmail(user.getEmail());
		userDetail.setFirstName(user.getFirstName());
		userDetail.setLastName(user.getLastName());
		userDetail.setMobile(user.getMobile());
		userDetail.setOrganisationId(user.getOrganisationTbl().getId());
		userDetail.setPhone(user.getPhone());
		userDetail.setUserName(user.getOrganisationLoginTbl().getUserName());
		userDetail.setUserType(user.getOrganisationLoginTbl().getUserType());
		userDetail.setStatus(user.getStatus());
		userDetail.setGlobalId(user.getId());
		response.setUserDetails(userDetail);
		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.OrganisationDao#deleteUser(int)
	 */
	@Override
	@Transactional
	public ResponseDTO deleteUser(int globalId) {
		ResponseDTO response = new ResponseDTO();
		OrganisationUserTbl user = getById(OrganisationUserTbl.class, globalId);
		if (user == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNotExists);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		user.setStatus(StatusEnum.InActive.getDisplayName());
		update(user);
		response.setSuccess(true);
		return response;
	}

	@Override
	@Transactional
	public UserCredentials getUserCredentials(LoginDTO login) {

		UserCredentials user = new UserCredentials();
		OrganisationLoginTbl userLogin = getOrganisationUserByName(login.getUserName().trim());
		if (userLogin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		if (userLogin.getUserType().equals(UserTypeEnum.Owner.getDisplayName())) {
			OrganisationTbl orgTbl = getOrgOwnerByLoginId(userLogin.getId());
			if (orgTbl == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NoUserExists);
				se.addParam(new Parameter(Constants.NAME, userLogin
						.getUserName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
			user.setEmailId(orgTbl.getOwnerEmail());
			user.setFirstName(orgTbl.getOwnerFirstName());
			user.setLastName(orgTbl.getOwnerLastName());
			user.setUserName(userLogin.getUserName());
	
		} else {

			OrganisationUserTbl orgnUser = (OrganisationUserTbl) getOrgUserByLoginId(userLogin
					.getId());
			if (orgnUser == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NoUserExists);
				se.addParam(new Parameter(Constants.NAME, userLogin
						.getUserName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
			user.setEmailId(orgnUser.getEmail());
			user.setFirstName(orgnUser.getFirstName());
			user.setLastName(orgnUser.getLastName());
			user.setUserName(userLogin.getUserName());
		}
		return user;
	}

	@Override
	@Transactional
	public ResponseDTO resetPassword(LoginDTO login) {
		ResponseDTO response = new ResponseDTO();
		String newPassword = StringEncoder.encryptWithKey(login.getPassword());
		String decrypedUserName = StringEncoder.decryptWithStaticKey(login
				.getUserName());
		OrganisationLoginTbl userLogin = getOrganisationUserByName(decrypedUserName);
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

	@Override
	@Transactional
	public LoginResponseDTO organisationLogin(LoginDTO login) {

		LoginResponseDTO orgLogin = new LoginResponseDTO();
		OrganisationLoginTbl loginDetails = getOrgUserByUserNameAndPassword(
				login.getPassword(), login.getUserName());
		if(loginDetails==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		orgLogin.setSuccess(true);
		return orgLogin;
	}
	@Override
	@Transactional
	public UserDetails getOrganisationUser(String userName) {
		OrganisationLoginTbl orgLogin = (OrganisationLoginTbl) getOrgLoginByUserName(userName
				.trim());
		if (orgLogin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.LoginNotExists);
			se.addParam(new Parameter(Constants.NAME, userName));
			se.setDisplayErrMsg(true);
			throw se;
		}
		UserTypeEnum userType = UserTypeEnum.getEnum(orgLogin
				.getUserType());
		if (orgLogin.getUserType().equals(UserTypeEnum.Owner.getDisplayName())) {
			OrganisationTbl orgTbl = getOrgOwnerByLoginId(orgLogin.getId());
			if (orgTbl != null) {
				UserDetails user = new UserDetails();
				user.setId(orgTbl.getId());
				user.setName(orgTbl.getOwnerFirstName());
				user.setUserType(userType.getDisplayName());
				user.setOrganisationId(orgTbl.getId());
				user.setAccountName(orgTbl.getName());
				return user;
			}
		} else {

			OrganisationUserTbl orgnUser = (OrganisationUserTbl) getOrgUserByLoginId(orgLogin
					.getId());
			if (orgnUser != null) {
				UserDetails user = new UserDetails();
				user.setId(orgnUser.getId());
				user.setName(orgnUser.getFirstName());
				user.setUserType(userType.getDisplayName());
				user.setOrganisationId(orgnUser.getOrganisationTbl().getId());
				user.setAccountName(orgnUser.getOrganisationTbl().getName());
				return user;
			}
		}
		return null;
	}

	@Override
	@Transactional
	public OrganisationListResponseDTO getOrganisationList() {
		OrganisationListResponseDTO response = new OrganisationListResponseDTO();
		List<Organisation> organisationList = new ArrayList<Organisation>();
		List<OrganisationTbl> organisationTblList = getOrganisation();
		for(OrganisationTbl organisationTbl:organisationTblList){
			Organisation organisation = new Organisation();
			organisation.setName(organisationTbl.getName());
			organisationList.add(organisation);
		}
		return response;
	}

	
	private List<OrganisationTbl> getOrganisation() {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ORGANISATION);
		return executeQuery(OrganisationTbl.class, query);
	}

	private OrganisationLoginTbl getOrgUserByUserNameAndPassword(
			String password, String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ORGANISATION_USER_BY_PASSWORD);
		query.setParameter("param1", password);
		query.setParameter("param2", userName);
		return executeUniqueQuery(OrganisationLoginTbl.class, query);
	}

	private OrganisationLoginTbl getOrgLoginByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ORGANISATION_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(OrganisationLoginTbl.class, query);
	}
	
	private OrganisationUserTbl getOrgUserByLoginId(int loginId) {
		javax.persistence.Query query = em.createQuery(Query.GET_ORGANISATION_USER);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(OrganisationUserTbl.class, query);
	}
	
	private OrganisationTbl getOrgOwnerByLoginId(int loginId) {
		javax.persistence.Query query = em.createQuery(Query.GET_ORGANISATION_OWNER);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(OrganisationTbl.class, query);
	}
	
	private OrganisationLoginTbl getOrganisationUserByName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ORGANISATION_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(OrganisationLoginTbl.class, query);
	}

	/**
	 * @param name
	 * @return
	 */
	private OrganisationTbl getOrganisationByName(String name) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ORGANISATION_BY_NAME);
		query.setParameter("param1", name);
		return executeUniqueQuery(OrganisationTbl.class, query);
	}

	/**
	 * @param userName
	 * @return
	 */
	private OrganisationLoginTbl getLoginByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_LOGIN_BY_ORGANISATION_OWNER_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(OrganisationLoginTbl.class, query);
	}
	/**
	 * @param trim
	 * @return
	 */
	private OrganisationUserTbl getOrganisationUserByEmail(String email,
			int organisationId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ORGANISATION_USER_BY_EMAIL_AND_BRANCH);
		query.setParameter("param1", email);
		query.setParameter("param2", organisationId);
		return executeUniqueQuery(OrganisationUserTbl.class, query);

	}
	/**
	 * @param globalId
	 * @return
	 */
	private List<OrganisationUserTbl> getUsersByOrganisationId(int globalId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_USERS_BY_ORGANISATION_ID);
		query.setParameter("param1", globalId);

		return executeQuery(OrganisationUserTbl.class, query);
	}

	
	
}
