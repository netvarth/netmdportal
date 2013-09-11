/**
 * TestServiceImpl.java
 * @author netvarth
 *
 * Version 1.0 Sep 2, 2013
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
import com.nv.youNeverWait.pl.entity.TestTbl;
import com.nv.youNeverWait.rs.dto.AddTestDTO;
import com.nv.youNeverWait.rs.dto.DeleteTestResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.LabHeaderDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveTestResponse;
import com.nv.youNeverWait.rs.dto.TestDTO;
import com.nv.youNeverWait.rs.dto.TestListResponseDTO;
import com.nv.youNeverWait.rs.dto.UpdateTestResponseDTO;
import com.nv.youNeverWait.rs.dto.ViewTestResponseDTO;
import com.nv.youNeverWait.user.bl.service.TestManager;
import com.nv.youNeverWait.user.bl.validation.TestValidator;
import com.nv.youNeverWait.user.pl.dao.TestDao;
import com.nv.youNeverWait.util.filter.core.Filter;
import com.nv.youNeverWait.util.filter.core.FilterFactory;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;
import com.nv.youNeverWait.util.filter.core.QueryBuilderFactory;

/**
 *
 *
 * @author Luciya Jose
 */
public class TestServiceImpl implements TestManager {
	private TestValidator testValidator;
	private TestDao testDao;
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.TestService#testList(com.nv.youNeverWait.rs.dto.FilterDTO)
	 */
	@Override
	public TestListResponseDTO testList(FilterDTO filterDTO) {

		TestListResponseDTO response = new TestListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,
		// return result with appropriate error code
		ErrorDTO error = testValidator.validateTestFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for lab from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.TEST);
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
		TypedQuery<TestTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());

		Long count = queryBuilder.getCount();
		System.out.println("queryBuilder.getCount():" + count);
		// execute query
		List<TestTbl> tests = queryBuilder.executeQuery(q);
		response = getTestList(tests);
		response.setCount(count);
		response.setSuccess(true);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.TestManager#createTest(com.nv.youNeverWait.rs.dto.AddTestDTO)
	 */
	@Override
	public ResponseDTO createTest(AddTestDTO testDTO) {
		
		ResponseDTO response = new ResponseDTO();
		ErrorDTO error = testValidator.validateTest(testDTO);
		if(error!=null){
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response = testDao.createTest(testDTO);
		return response;
	}
	
	/**
	 * Method to set test details into a list
	 * @param tests
	 * @return
	 */
	private TestListResponseDTO getTestList(List<TestTbl> tests) {
		TestListResponseDTO response = new TestListResponseDTO();
		if (tests == null) {
			return response;
		}
		List<TestDTO> testList = new ArrayList<TestDTO>();
		for (TestTbl testTbl : tests) {
			testList.add(new TestDTO(testTbl));
		}
		response.setTestList(testList);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.TestManager#updateTest(com.nv.youNeverWait.rs.dto.AddTestDTO)
	 */
	@Override
	public UpdateTestResponseDTO updateTest(AddTestDTO testDTO) {
		UpdateTestResponseDTO response = new UpdateTestResponseDTO();
		ErrorDTO error = testValidator.validateTest(testDTO);
		if(error!=null){
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response = testDao.updateTest(testDTO);
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.TestManager#deleteTest(java.lang.String)
	 */
	@Override
	public DeleteTestResponseDTO deleteTest(String uid) {
		DeleteTestResponseDTO response = testDao.deleteTest(uid);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.TestManager#viewTest(java.lang.String)
	 */
	@Override
	public ViewTestResponseDTO viewTest(String uid) {
		ViewTestResponseDTO  response=testDao.viewTest(uid);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.LabService#getTests(com.nv.youNeverWait.rs.dto.LabHeaderDTO, java.lang.String, java.util.Date)
	 */
	@Override
	public RetrieveTestResponse getTests(LabHeaderDTO header, String lastSyncTime,
			Date currentSyncTime) {
		RetrieveTestResponse response= testDao.getTests(header,lastSyncTime,currentSyncTime);
		return response;
	}

	
	/**
	 * @return the testValidator
	 */
	public TestValidator getTestValidator() {
		return testValidator;
	}

	/**
	 * @param testValidator the testValidator to set
	 */
	public void setTestValidator(TestValidator testValidator) {
		this.testValidator = testValidator;
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
	 * @return the testDao
	 */
	public TestDao getTestDao() {
		return testDao;
	}

	/**
	 * @param testDao the testDao to set
	 */
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

	
}
