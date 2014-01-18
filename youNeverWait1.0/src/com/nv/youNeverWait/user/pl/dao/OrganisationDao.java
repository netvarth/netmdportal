/**
 * OrganisationDao.java
 * @author netvarth
 *
 * Version 1.0 Jan 26, 2014
 *
 * Copyright (c) 2014 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.pl.dao;

import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.Organisation;
import com.nv.youNeverWait.rs.dto.OrganisationUserDetail;
import com.nv.youNeverWait.rs.dto.OrganizationViewResponseDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.rs.dto.UserDetails;
import com.nv.youNeverWait.rs.dto.ViewOrganisationUser;

/**
 *
 *
 * @author Luciya Jose
 */
public interface OrganisationDao {

	/**
	 * @param organztion
	 * @return
	 */
	ResponseDTO createOrganisation(Organisation organztion);

	/**
	 * @param organztion
	 * @return
	 */
	ResponseDTO updateOrganisation(Organisation organztion);

	/**
	 * @param globalId
	 * @return
	 */
	OrganizationViewResponseDTO viewOrganisation(int globalId);

	/**
	 * @param globalId
	 * @return
	 */
	ResponseDTO deleteOrganisation(int globalId);

	/**
	 * @param organztionUser
	 * @return
	 */
	ResponseDTO createUser(OrganisationUserDetail organztionUser);

	/**
	 * @param organisationId
	 * @return
	 */
	String getOrganisationName(int organisationId);

	/**
	 * @param organztionUser
	 * @return
	 */
	ResponseDTO updateUser(OrganisationUserDetail organztionUser);

	/**
	 * @param globalId
	 * @return
	 */
	ViewOrganisationUser viewUser(int globalId);

	/**
	 * @param globalId
	 * @return
	 */
	ResponseDTO deleteUser(int globalId);

	UserCredentials getUserCredentials(LoginDTO login);

	ResponseDTO resetPassword(LoginDTO login);
	public LoginResponseDTO organisationLogin(LoginDTO login);
	public UserDetails getOrganisationUser(String userName);

}
