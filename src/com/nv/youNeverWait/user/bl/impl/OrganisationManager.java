/**
 * OrganisationManager.java
 * @author netvarth
 *
 * Version 1.0 Jan 26, 2014
 *
 * Copyright (c) 2014 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nv.framework.sendmsg.SendEmailMsgWorkerThread;
import com.nv.framework.sendmsg.SendMsgCallbackEnum;
import com.nv.framework.sendmsg.email.SendMailMsgObj;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.OrganisationTbl;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDetail;
import com.nv.youNeverWait.rs.dto.Organisation;
import com.nv.youNeverWait.rs.dto.OrganisationListResponseDTO;
import com.nv.youNeverWait.rs.dto.OrganisationUserDetail;
import com.nv.youNeverWait.rs.dto.OrganisationUsersList;
import com.nv.youNeverWait.rs.dto.OrganizationViewResponseDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ViewOrganisationUser;
import com.nv.youNeverWait.user.bl.service.OrganisationService;
import com.nv.youNeverWait.user.bl.validation.OrganisationValidator;
import com.nv.youNeverWait.user.pl.dao.OrganisationDao;
import com.nv.youNeverWait.util.filter.core.Filter;
import com.nv.youNeverWait.util.filter.core.FilterFactory;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;
import com.nv.youNeverWait.util.filter.core.QueryBuilderFactory;

/**
 *
 *
 * @author Luciya Jose
 */
public class OrganisationManager  implements OrganisationService{
	private OrganisationDao organisationDao;
	private OrganisationValidator validator;
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	private String organisationServerIpAddress;
	private String mailFrom;
	private SendEmailMsgWorkerThread mailThread;
	private static final Log log = LogFactory.getLog(NetMdServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.OrganisationService#createOrganisation(com.nv.youNeverWait.rs.dto.Organisation)
	 */
	@Override
	public ResponseDTO createOrganisation(Organisation organztion) {
		validator.validateOrganisationDetails(organztion);
		validator.validateUserNameAndPassword(organztion.getUserName(),organztion.getPassword());
		ResponseDTO response=organisationDao.createOrganisation(organztion);
		sendEmailToOrganisationOwner(Constants.ORGANISATION_REGISTER, organztion);
		return response;
	}

	/**
	 * To send Organisation registration email to the owner's email id It will perform
	 * the following operations. 1.Take default email HTML template from Apache
	 * folder 2.Create email body 3.Send email to the organisation owner.
	 * 
	 * @param subject
	 * @param organztion
	 * @return
	 */
	private void sendEmailToOrganisationOwner(String subject, Organisation organztion) {
		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + organisationServerIpAddress
					+ "/youNeverWait/EmailFormat/OrganisationRegistration.html");
			msgBody = createDefaultEmailBody(url, organztion);

			SendMailMsgObj obj = new SendMailMsgObj(subject, msgBody,
					organztion.getOwnerEmail(), mailFrom, 0, 0, null,
					SendMsgCallbackEnum.ORGANISATION_REGISTRATION.getId(), null);
			mailThread.addSendMsgObj(obj);
			// /EmailSender.sendEmail(netMd.getOwnerEmail(), mailFrom, subject,
			// msgBody);
		} catch (IOException e) {
			log.error(
					"Error while sending organisation registration email to the owner's email id ",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * To create email body
	 * 
	 * @param url
	 *            ,netMd
	 * 
	 * @return email message body
	 */
	private String createDefaultEmailBody(URL url, Organisation organztion)
			throws IOException {

		StringBuffer msgBodyBfr = new StringBuffer();
		String fullMsgBody = "";
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
		fullMsgBody = fullMsgBody.replace("{firstName}",
				organztion.getOwnerFirstName());
		if (organztion.getOwnerLastName() == null) {
			fullMsgBody = fullMsgBody.replace("{lastName}", "");
		} else {
			fullMsgBody = fullMsgBody.replace("{lastName}",
					organztion.getOwnerLastName());
		}
		fullMsgBody = fullMsgBody.replace("{OrganisationName}", organztion.getName());
		fullMsgBody = fullMsgBody.replace("{userid}", organztion.getUserName());
		fullMsgBody = fullMsgBody.replace("{password}", organztion.getPassword());
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				organisationServerIpAddress);
		return fullMsgBody;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.OrganisationService#updateOrganisation(com.nv.youNeverWait.rs.dto.Organisation)
	 */
	@Override
	public ResponseDTO updateOrganisation(Organisation organztion) {
		validator.validateGlobalId(organztion.getId());
		validator.validateOrganisationDetails(organztion);
		ResponseDTO response=organisationDao.updateOrganisation(organztion);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.OrganisationService#viewOrganisation(int)
	 */
	@Override
	public OrganizationViewResponseDTO viewOrganisation(int globalId) {
		validator.validateGlobalId(globalId);
		OrganizationViewResponseDTO response=organisationDao.viewOrganisation(globalId);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.OrganisationService#deleteOrganisation(int)
	 */
	@Override
	public ResponseDTO deleteOrganisation(int globalId) {
		validator.validateGlobalId(globalId);
		ResponseDTO response=organisationDao.deleteOrganisation(globalId);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.OrganisationService#getOrganisationList(com.nv.youNeverWait.rs.dto.FilterDTO)
	 */
	@Override
	public OrganisationListResponseDTO getOrganisationList(FilterDTO filterDTO) {
		OrganisationListResponseDTO response = new OrganisationListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,
		// return result with appropriate error code
		ErrorDTO error = validator.validateFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for lab from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.ORGANISATION);
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
		TypedQuery<OrganisationTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());

		Long count = queryBuilder.getCount();
		System.out.println("queryBuilder.getCount():" + count);
		// execute query
		List<OrganisationTbl> organisationList = queryBuilder.executeQuery(q);
		response = getOrganisationList(organisationList);
		response.setCount(count);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Assign netmd record details to response
	 * 
	 * @param netmdList
	 * @return NetMdListResponseDTO
	 */
	private OrganisationListResponseDTO getOrganisationList(List<OrganisationTbl> orgList) {
		OrganisationListResponseDTO response = new OrganisationListResponseDTO();
		if (orgList.isEmpty()) {
			return response;
		}
		List<Organisation> organisationDetails = new ArrayList<Organisation>();
		for (OrganisationTbl organisationTbl : orgList) {
			Organisation organization = new Organisation();
			organization.setGlobalId(organisationTbl.getId());
			organization.setName(organisationTbl.getName());
			organization.setOwnerAddress(organisationTbl.getOwnerAddress());
			organization.setOwnerEmail(organisationTbl.getOwnerEmail());
			organization.setOwnerMobile(organisationTbl.getOwnerMobile());
			organization.setOwnerFirstName(organisationTbl.getOwnerFirstName());
			organization.setOwnerLastName(organisationTbl.getOwnerLastName());
			organization.setOwnerPhone(organisationTbl.getOwnerPhone());
			organization.setHeadOfficeAddress(organisationTbl.getHeadOfficeAddress());
			organization.setHeadOfficeEmail(organisationTbl.getHeadOfficeEmail());
			organization.setHeadOfficeMobile(organisationTbl.getHeadOfficeMobile());
			organization.setHeadOfficePhone(organisationTbl.getHeadOfficePhone());
			organization.setHeadOfficeName(organisationTbl.getHeadOfficeName());
			organization.setUserName(organisationTbl.getOrganisationLoginTbl().getUserName());
			organization.setUserType(organisationTbl.getOrganisationLoginTbl().getUserType());
		}
		response.setOrganisation(organisationDetails);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.OrganisationService#getOrganisationUserList(com.nv.youNeverWait.rs.dto.FilterDTO)
	 */
	@Override
	public OrganisationUsersList getUserList(FilterDTO filter) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.OrganisationService#deleteOrganisationUser(int)
	 */
	@Override
	public ResponseDTO deleteUser(int globalId) {
		validator.validateGlobalId(globalId);
		ResponseDTO response = organisationDao.deleteUser(globalId);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.OrganisationService#viewOrganisationUser(int)
	 */
	@Override
	public ViewOrganisationUser viewUser(int globalId) {
		validator.validateGlobalId(globalId);
		ViewOrganisationUser response = organisationDao.viewUser(globalId);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.OrganisationService#updateOrganisationUser(com.nv.youNeverWait.rs.dto.OrganisationUserDetail)
	 */
	@Override
	public ResponseDTO updateUser(
			OrganisationUserDetail organztionUser) {
		validator.validateUserDetails(organztionUser);
		ResponseDTO response = organisationDao.updateUser(organztionUser);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.OrganisationService#createOrganisationUser(com.nv.youNeverWait.rs.dto.OrganisationUserDetail)
	 */
	@Override
	public ResponseDTO createUser(
			OrganisationUserDetail organztionUser) {
		validator.validateUserDetails(organztionUser);
		validator.validateUserLoginDetails(organztionUser);
		ResponseDTO response = organisationDao.createUser(organztionUser);
		String OrganisationName = organisationDao.getOrganisationName(organztionUser.getOrganisationId());
		sendEmailToUser(organztionUser,OrganisationName);
		return response;
	}
	
	/**
	 * @param organztionUser
	 * @param organisationName
	 */
	private void sendEmailToUser(OrganisationUserDetail organztionUser,
			String organisationName) {
		String msgBody = "";
		URL url = null;
		try {
			url = new URL("http://" + organisationServerIpAddress
					+ "/youNeverWait/EmailFormat/OrganisationUserRegistration.html");
			msgBody = createUserEmailBody(url, organztionUser, organisationName);

			SendMailMsgObj obj = new SendMailMsgObj(
					Constants.USER_REGISTRATION, msgBody, organztionUser.getEmail(),
					mailFrom, 0, 0, null,
					SendMsgCallbackEnum.ORGANISATION_USER_REGISTRATION.getId(), null);
			mailThread.addSendMsgObj(obj);
			// EmailSender.sendEmail(user.getEmail(), mailFrom,
			// Constants.USER_REGISTRATION, msgBody);
		} catch (IOException e) {
			log.error("Error while sending email to Netmd user", e);
			e.printStackTrace();
		}
		
	}


	/**
	 * Creates Email body
	 * 
	 * @param url
	 * @param user
	 * @param netMdName
	 * @throws IOException
	 */
	private String createUserEmailBody(URL url,OrganisationUserDetail organztionUser,
			String organisationName) throws IOException {

		StringBuffer msgBodyBfr = new StringBuffer();
		String fullMsgBody = "";

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
		fullMsgBody = fullMsgBody.replace("{firstName}", organztionUser.getFirstName());
		if (organztionUser.getLastName() == null) {
			fullMsgBody = fullMsgBody.replace("{lastName}", "");
		} else {
			fullMsgBody = fullMsgBody.replace("{lastName}", organztionUser.getLastName());
		}
		fullMsgBody = fullMsgBody.replace("{organisationName}", organisationName);
		
		fullMsgBody = fullMsgBody.replace("{userid}", organztionUser.getUserName());
		fullMsgBody = fullMsgBody.replace("{password}", organztionUser.getPassword());
		fullMsgBody = fullMsgBody.replace("{serverIpAddress}",
				organisationServerIpAddress);

		return fullMsgBody;
	}
	/**
	/**
	 * @return the organisationDao
	 */
	public OrganisationDao getOrganisationDao() {
		return organisationDao;
	}

	/**
	 * @param organisationDao the organisationDao to set
	 */
	public void setOrganisationDao(OrganisationDao organisationDao) {
		this.organisationDao = organisationDao;
	}

	/**
	 * @return the validator
	 */
	public OrganisationValidator getValidator() {
		return validator;
	}

	/**
	 * @param validator the validator to set
	 */
	public void setValidator(OrganisationValidator validator) {
		this.validator = validator;
	}

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
	 * @return the organisationServerIpAddress
	 */
	public String getOrganisationServerIpAddress() {
		return organisationServerIpAddress;
	}

	/**
	 * @param organisationServerIpAddress the organisationServerIpAddress to set
	 */
	public void setOrganisationServerIpAddress(String organisationServerIpAddress) {
		this.organisationServerIpAddress = organisationServerIpAddress;
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

	/**
	 * @return the log
	 */
	public static Log getLog() {
		return log;
	}

	
}
