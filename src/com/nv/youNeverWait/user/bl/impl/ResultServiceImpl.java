/**
 * ResultServiceImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 5, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.youNeverWait.user.bl.impl;

import java.util.Date;
import java.util.List;

import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LimsPageSettingsBundle;
import com.nv.youNeverWait.rs.dto.OrderResultSyncDTO;
import com.nv.youNeverWait.rs.dto.OrderTestResultList;
import com.nv.youNeverWait.rs.dto.PageLayoutSettings;
import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveResultsResponseDTO;
import com.nv.youNeverWait.user.bl.service.FacilityService;
import com.nv.youNeverWait.user.bl.service.PatientService;
import com.nv.youNeverWait.user.bl.service.ResultService;
import com.nv.youNeverWait.user.bl.validation.ResultValidator;
import com.nv.youNeverWait.user.pl.dao.ResultDao;



/**
 * 
 */
public class ResultServiceImpl implements ResultService{
	private ResultDao resultDao;
	private ResultValidator resultValidator;
	private PatientService patientService;
	private FacilityService facilityService;

	/**
	 * @return the patientService
	 */
	public PatientService getPatientService() {
		return patientService;
	}
	/**
	 * @param patientService the patientService to set
	 */
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}
	/**
	 *list of patient result result
	 * @param id
	 * @return ResultListResponseDTO
	 */
	@Override
	public ResultListResponseDTO listResult(String patientId) {
		ResultListResponseDTO response =resultDao.listResult(patientId);
		return response;

	}
	/**
	 * Method performed for getting the results of patient
	 * @param lastSyncTime
	 * @param passPhrase
	 * @param netmdBranchId
	 * @param currentSyncTime
	 * @return RetrieveResultsResponseDTO
	 */
	@Override
	public List<RetrieveResultsResponseDTO> getPatientResults(
			String lastSyncTime, String passPhrase, int netMdBranchId,
			Date currentSyncTime) {
		List<RetrieveResultsResponseDTO> response= resultDao.getPatientResults(lastSyncTime, passPhrase, netMdBranchId, currentSyncTime);
		return response;
	}
	@Override
	public OrderTestResultList retrieveResults(HeaderDTO header,
			String lastSyncTime, Date currentSyncTime) {
		OrderTestResultList testResults=resultDao.retrieveResults(header,lastSyncTime,currentSyncTime);
		return testResults;
	}

	/**
	 * @return the resultDao
	 */
	public ResultDao getResultDao() {
		return resultDao;
	}
	/**
	 * @param resultDao the resultDao to set
	 */
	public void setResultDao(ResultDao resultDao) {
		this.resultDao = resultDao;
	}
	/**
	 * @return the resultValidator
	 */
	public ResultValidator getResultValidator() {
		return resultValidator;
	}
	/**
	 * @param resultValidator the resultValidator to set
	 */
	public void setResultValidator(ResultValidator resultValidator) {
		this.resultValidator = resultValidator;
	}
	@Override
	public int processOrderResult(OrderResultSyncDTO orderResult,
			Integer source_branch_id) {
		int patientId = 0;
		String source_branch = facilityService.getFacilityBranchName(source_branch_id);
		if(orderResult.getOrder().getPatient()!=null)
			if(orderResult.getOrder().getPatient().getAddress().getEmail()!=null && orderResult.getOrder().getPatient().getAddress().getEmail().trim()!="")
				patientId = patientService.getPatient(orderResult.getOrder().getPatient(),source_branch);
		//	throw new ServiceException(ErrorCodeEnum.EmailNull);
		return resultDao.processOrderResult(orderResult, source_branch_id, patientId);
	}
	/**
	 * @return the facilityService
	 */
	public FacilityService getFacilityService() {
		return facilityService;
	}
	/**
	 * @param facilityService the facilityService to set
	 */
	public void setFacilityService(FacilityService facilityService) {
		this.facilityService = facilityService;
	}
	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.ResultService#processPageSettings(com.nv.youNeverWait.rs.dto.LimsPageSettingsBundle)
	 */
	@Override
	public void processPageSettings(LimsPageSettingsBundle bundle) {
		resultDao.ProcessPageSettings(bundle);
		
	}
	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.ResultService#getPageSettings(int)
	 */
	@Override
	public List<PageLayoutSettings> getPageSettings(int branchId) {
		return resultDao.getPageSettings(branchId);
	}
}
