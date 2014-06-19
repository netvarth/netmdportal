/**
 * OrganisationService.java
 * @author netvarth
 *
 * Version 1.0 Jan 26, 2014
 *
 * Copyright (c) 2014 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.service;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JasperPrint;

import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.Organisation;
import com.nv.youNeverWait.rs.dto.OrganisationListResponseDTO;
import com.nv.youNeverWait.rs.dto.OrganisationUserDetail;
import com.nv.youNeverWait.rs.dto.OrganisationUsersList;
import com.nv.youNeverWait.rs.dto.OrganizationViewResponseDTO;
import com.nv.youNeverWait.rs.dto.ReportListFilterDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.UserDetails;
import com.nv.youNeverWait.rs.dto.ViewOrganisationUser;


/**
 * 
 * 
 * @author Luciya Jose
 */
public interface OrganisationService {

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
	 * @param filter
	 * @return
	 */
	OrganisationListResponseDTO getOrganisationList(FilterDTO filter);

	/**
	 * @param filter
	 * @return
	 */
	OrganisationUsersList getUserList(FilterDTO filter);

	/**
	 * @param globalId
	 * @return
	 */
	ResponseDTO deleteUser(int globalId);

	/**
	 * @param globalId
	 * @return
	 */
	ViewOrganisationUser viewUser(int globalId);

	/**
	 * @param organztionUser
	 * @return
	 */
	ResponseDTO updateUser(OrganisationUserDetail organztionUser);

	/**
	 * @param organztionUser
	 * @return
	 */
	public ResponseDTO createUser(OrganisationUserDetail organztionUser);

	public ResponseDTO forgotPassword(LoginDTO login);

	public ResponseDTO resetPassword(LoginDTO login);

	public LoginResponseDTO organisationLogin(LoginDTO login);

	public UserDetails getOrganisationUser(String userName);

	public OrganisationListResponseDTO getOrganisationList();

	public JasperPrint createReport(Map<String,Object> map, ServletContext contexPath);

	public ReportListFilterDTO getFilterList();
	

}
