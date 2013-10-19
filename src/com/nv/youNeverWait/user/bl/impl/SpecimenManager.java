/**
 * SpecimenManagerService.java
 * @author netvarth
 *
 * Version 1.0 Sep 3, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.SpecimenTbl;
import com.nv.youNeverWait.pl.entity.TestTbl;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.LabHeaderDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveSpecimenResponse;
import com.nv.youNeverWait.rs.dto.SpecimenDTO;
import com.nv.youNeverWait.rs.dto.SpecimenListResponseDTO;
import com.nv.youNeverWait.rs.dto.SpecimenResponseDTO;
import com.nv.youNeverWait.rs.dto.TestDTO;
import com.nv.youNeverWait.rs.dto.TestListResponseDTO;
import com.nv.youNeverWait.user.bl.service.SpecimenService;
import com.nv.youNeverWait.user.bl.validation.SpecimenValidator;
import com.nv.youNeverWait.user.bl.validation.TestValidator;
import com.nv.youNeverWait.user.pl.dao.SpecimenDao;
import com.nv.youNeverWait.util.filter.core.Filter;
import com.nv.youNeverWait.util.filter.core.FilterFactory;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;
import com.nv.youNeverWait.util.filter.core.QueryBuilderFactory;

/**
 *
 *
 * @author Luciya Jose
 */
public class SpecimenManager  implements SpecimenService{
	private SpecimenValidator specimenValidator;
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	private SpecimenDao specimenDao;

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.SpecimenManager#specimenList(com.nv.youNeverWait.rs.dto.FilterDTO)
	 */
	@Override
	public SpecimenListResponseDTO testSpecimenList(FilterDTO filterDTO) {
		SpecimenListResponseDTO response = new SpecimenListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,
		// return result with appropriate error code
		ErrorDTO error = specimenValidator.validateSpecimenFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for specimen from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.SPECIMEN);
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
		TypedQuery<SpecimenTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());

		Long count = queryBuilder.getCount();
		System.out.println("queryBuilder.getCount():" + count);
		// execute query
		List<SpecimenTbl> specimens = queryBuilder.executeQuery(q);
		response = getSpecimenList(specimens);
		response.setCount(count);
		response.setSuccess(true);
		return response;
	}

	/**
	 * @param specimens
	 * @return
	 */
	private SpecimenListResponseDTO getSpecimenList(List<SpecimenTbl> specimens) {
		SpecimenListResponseDTO response = new SpecimenListResponseDTO();
		if (specimens == null) {
			return response;
		}
		List<SpecimenDTO> specimenList = new ArrayList<SpecimenDTO>();
		for (SpecimenTbl specimenTbl : specimens) {
			specimenList.add(new SpecimenDTO(specimenTbl));
		}
		response.setSpecimenList(specimenList);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.SpecimenManager#createSpecimen(com.nv.youNeverWait.rs.dto.SpecimenDTO)
	 */
	@Override
	public ResponseDTO createSpecimen(SpecimenDTO specimen) {
		specimenValidator.validateSpecimen(specimen);
		ResponseDTO response= specimenDao.createSpecimen(specimen);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.SpecimenManager#updateSpecimen(com.nv.youNeverWait.rs.dto.SpecimenDTO)
	 */
	@Override
	public ResponseDTO updateSpecimen(SpecimenDTO specimen) {
		ResponseDTO response= new ResponseDTO();
		ErrorDTO error = specimenValidator.validateSpecimenDetails(specimen);
		if(error!=null){
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response = specimenDao.updateSpecimen(specimen);
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.SpecimenManager#deleteSpecimen(int)
	 */
	@Override
	public ResponseDTO deleteSpecimen(int uid) {
		ResponseDTO response = new ResponseDTO();
		ErrorDTO error = specimenValidator.validateSpecimenUid(uid);
		if(error!=null){
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response = specimenDao.deleteSpecimen(uid);
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.SpecimenManager#viewSpecimen(int)
	 */
	@Override
	public SpecimenResponseDTO viewSpecimen(int uid) {
		SpecimenResponseDTO response = new SpecimenResponseDTO();
		ErrorDTO error = specimenValidator.validateSpecimenUid(uid);
		if(error!=null){
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response = specimenDao.viewSpecimen(uid);
		return response;
	}
	

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.LabService#getSpecimens(com.nv.youNeverWait.rs.dto.LabHeaderDTO, java.lang.String, java.util.Date)
	 */
	@Override
	public RetrieveSpecimenResponse getSpecimens(LabHeaderDTO header,
			String lastSyncTime, Date currentSyncTime) {
		RetrieveSpecimenResponse response= specimenDao.getSpecimens(header, lastSyncTime,currentSyncTime);
		return response;

	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.SpecimenManager#specimenList()
	 */
	@Override
	public RetrieveSpecimenResponse specimenList() {
		RetrieveSpecimenResponse response = specimenDao.specimenList();
		return response;
	}

	
	/**
	 * @return the specimenValidator
	 */
	public SpecimenValidator getSpecimenValidator() {
		return specimenValidator;
	}

	/**
	 * @param specimenValidator the specimenValidator to set
	 */
	public void setSpecimenValidator(SpecimenValidator specimenValidator) {
		this.specimenValidator = specimenValidator;
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
	 * @return the specimenDao
	 */
	public SpecimenDao getSpecimenDao() {
		return specimenDao;
	}

	/**
	 * @param specimenDao the specimenDao to set
	 */
	public void setSpecimenDao(SpecimenDao specimenDao) {
		this.specimenDao = specimenDao;
	}

	
	

	

	

	
}
