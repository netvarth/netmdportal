/**
 * AuthenticationDaoImpl.java
 * 
 * @Author Linu Paul
 *
 * Version 1.0 aug 22, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.youNeverWait.user.pl.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import com.nv.framework.util.text.StringEncoder;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.SuperAdminTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.EnableLogStatusResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.LogDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.rs.dto.UserDetails;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.SuperAdminDao;

public class SuperAdminDaoImpl extends GenericDaoHibernateImpl implements
SuperAdminDao {
	@PersistenceContext()
	private EntityManager em;

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
		SuperAdminTbl superAdmin = (SuperAdminTbl) getSuperAdminByUserName(decrypedUserName);
		if (superAdmin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUser);
			se.setDisplayErrMsg(true);
			throw se;
		}
		superAdmin.setPassword(newPassword);
		update(superAdmin);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Checks for the authentication of super admin
	 * 
	 * @param login
	 * @return LoginResponseDTO
	 */
	@Override
	@Transactional(readOnly = true)
	public LoginResponseDTO login(LoginDTO loginDTO) {
		LoginResponseDTO login = new LoginResponseDTO();
		SuperAdminTbl loginDetails = getUserByLoginIdAndPassword(
				loginDTO.getPassword(), loginDTO.getUserName());
		if (loginDetails != null) {
			login.setSuccess(true);
		} else {
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(ErrorCodeEnum.UserNull.getErrCode());
			error.setDisplayErrMsg(true);
			login.setError(error);
			login.setSuccess(false);
			return login;
		}
		return login;
	}

	/**
	 * Methd to retrieve email Id of superAdmin
	 * 
	 * @param login
	 * @return emailId
	 */
	@Override
	@Transactional
	public UserCredentials getUserCredentials(LoginDTO login) {
		UserCredentials user = new UserCredentials();
		SuperAdminTbl superAdmin = (SuperAdminTbl) getSuperAdminByUserName(login
				.getUserName().trim());
		if (superAdmin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUser);
			se.setDisplayErrMsg(true);
			throw se;
		}
		user.setEmailId(superAdmin.getEmail());
		user.setUserName(login.getUserName().trim());
		user.setFirstName(superAdmin.getName());
		return user;
	}

	/**
	 * Method which performs password changing
	 * 
	 * @param passwords
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO changePassword(@RequestBody PasswordDTO passwords) {
		ResponseDTO response = new ResponseDTO();
		String encPassword = StringEncoder.encryptWithKey(passwords.getOldPassword()
				.trim());
		SuperAdminTbl login = (SuperAdminTbl) getUserByLoginId(passwords
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
	 * Super Admin details
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails getUser(String userName) {
		UserDetails user = new UserDetails();
		SuperAdminTbl superAdmin = getUserByLoginId(userName);
		user.setId(superAdmin.getId());
		user.setName(superAdmin.getName());
		return user;
	}

	/**
	 * To enable/disable log
	 */
	@Override
	@Transactional
	public ResponseDTO enableLog(LogDTO log,HttpServletRequest request) {
		SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
		if (superAdmin != null) {
			superAdmin.setEnableLog(log.isEnableLog());
			update(superAdmin);
		
			if(request!=null)
				request.getSession().getServletContext()
				.setAttribute("logEnabled", superAdmin.isEnableLog());
		}
		ResponseDTO response = new ResponseDTO();
		response.setSuccess(true);
		return response;
	}

	/**
	 * To get the status of user enable log
	 * 
	 */
	@Override
	@Transactional
	public EnableLogStatusResponseDTO enableLogStatus() {
		EnableLogStatusResponseDTO response= new EnableLogStatusResponseDTO();
		SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
		if (superAdmin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUser);
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setStatus(superAdmin.isEnableLog());
		response.setSuccess(true);
		return response;
	}

	/**
	 * get User By LoginId And Password
	 * @param password
	 * @param userName
	 * @return
	 */
	private SuperAdminTbl getUserByLoginIdAndPassword(String password,
			String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_USER_BY_PASSWORD);
		query.setParameter("param1", password);
		query.setParameter("param2", userName);
		return executeUniqueQuery(SuperAdminTbl.class, query);
	}

	/**
	 * get User By LoginId
	 * @param userName
	 * @return
	 */
	private SuperAdminTbl getUserByLoginId(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_SUPER_ADMIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(SuperAdminTbl.class, query);
	}

	/**
	 * 
	 * @param userName
	 * @return SuperAdminTbl
	 */
	public SuperAdminTbl getSuperAdminByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_SUPER_ADMIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(SuperAdminTbl.class, query);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}


}
