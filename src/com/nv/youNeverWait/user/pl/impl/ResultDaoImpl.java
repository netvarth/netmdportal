/**
 * ResultDaoImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 5, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.youNeverWait.user.pl.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.NetmdPassphraseTbl;
import com.nv.youNeverWait.pl.entity.ResultTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.ResultDTO;
import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveResultsResponseDTO;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.ResultDao;


/**
 * 
 */
public class ResultDaoImpl extends GenericDaoHibernateImpl implements ResultDao {
	
	@PersistenceContext()
	private EntityManager em;

	/**
	 * Get patient results
	 */
	@Override
	public List<RetrieveResultsResponseDTO> getPatientResults(
			String syncTime, String passPhrase, int netmdBranchId,
			Date currentSyncTime) {
		List<RetrieveResultsResponseDTO> retrieveResultsList= new ArrayList<RetrieveResultsResponseDTO>();
		Date lastSyncTime = null;
		DateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		try {
			lastSyncTime = df.parse(syncTime);

		} catch (ParseException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		NetmdPassphraseTbl netmdPassphrase = getNetmdPassphrase(netmdBranchId,passPhrase);
		if (netmdPassphrase == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		/*Getting patient results list*/
		List<ResultTbl> resultsList= getResultsByNetMdBranchIdAndNetmdId(netmdPassphrase.getNetmdBranchTbl().getNetmdTbl().getId(),netmdBranchId,lastSyncTime,currentSyncTime);
		for (ResultTbl result : resultsList) {

			retrieveResultsList.add(new RetrieveResultsResponseDTO(result));
		}
		return retrieveResultsList;
	}

	



	/**
	 * list of results for a patient
	 */
	@Override
	@Transactional(readOnly = false)
	public ResultListResponseDTO listResult(String patientId) {
		ResultListResponseDTO response = new ResultListResponseDTO();
		List<ResultDTO> resultList = new ArrayList<ResultDTO>();
		List<ResultTbl> resultListTbl = getByPatientId(patientId);
		if (!resultListTbl.isEmpty()) {
			for (ResultTbl resultTbl : resultListTbl) {
				resultList.add(new ResultDTO(resultTbl));
			}
			response.setResultList(resultList);
		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Get result records of patient 
	 * @param id
	 * @param netmdBranchId
	 * @return
	 */
		public List<ResultTbl> getResultsByNetMdBranchIdAndNetmdId(int netmdId,
				int netmdBranchId, Date lastSyncTime, Date currentSyncTime) {
			javax.persistence.Query query = em
					.createQuery(Query.GET_RESULTS);
			query.setParameter("param1",netmdId);
			query.setParameter("param2", netmdBranchId);
			query.setParameter("param3",lastSyncTime);
			query.setParameter("param4",currentSyncTime );
			return executeQuery(ResultTbl.class,query);
			
		}

		
	/**
	 * Method for getting netmd passphrase id
	 * 
	 * @param passPhrase
	 * @param netmdBranchId
	 * @return netmd passphrase id
	 */
	public NetmdPassphraseTbl getNetmdPassphrase(int netmdBranchId,String passPhrase) {
		
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_PASSPHRASE_BY_BRANCH_ID);
		query.setParameter("param1",netmdBranchId );
		query.setParameter("param2", passPhrase);
		return executeUniqueQuery(NetmdPassphraseTbl.class,query);
	}
	
	public List<ResultTbl> getByPatientId(String patientId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_RESULT_BY_PATIENTID);
		query.setParameter("param1", Integer.parseInt(patientId));
		return executeQuery(ResultTbl.class, query);
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
	
}
