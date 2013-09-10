/**
 * SpecimenDaoImpl.java
 * @author netvarth
 *
 * Version 1.0 Sep 3, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.pl.impl;

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
import com.nv.youNeverWait.pl.entity.SpecimenTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.LabHeaderDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveSpecimenResponseDTO;
import com.nv.youNeverWait.rs.dto.SpecimenDTO;
import com.nv.youNeverWait.rs.dto.SpecimenResponseDTO;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.LabDao;
import com.nv.youNeverWait.user.pl.dao.SpecimenDao;

/**
 * 
 * 
 * @author Luciya Jose
 */
public class SpecimenDaoImpl extends GenericDaoHibernateImpl implements
		SpecimenDao {

	@PersistenceContext()
	private EntityManager em;
	private LabDao labDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.SpecimenDao#createSpecimen(com.nv.
	 * youNeverWait.rs.dto.SpecimenDTO)
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO createSpecimen(SpecimenDTO specimen) {
		ResponseDTO response = new ResponseDTO();
		String specimenName = specimen.getSpecimenName().trim()
				.replaceAll(" +", " ");
		SpecimenTbl specimenTbl = getSpecimenByName(specimenName.toUpperCase());
		if (specimenTbl != null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.SpecimenNameExists);
			se.addParam(new Parameter(Constants.NAME, specimenName));
			se.setDisplayErrMsg(true);
			throw se;
		}
		Integer lastRecrd = getLastUId();
		SpecimenTbl newSpecimenTbl = new SpecimenTbl();
		Date curetnDate= new Date();
		newSpecimenTbl.setUid(lastRecrd + 1); // creating UID manually
		newSpecimenTbl.setName(specimenName.trim());
		newSpecimenTbl.setUnit(specimen.getUnit());
		newSpecimenTbl.setCreatedDateTime(curetnDate);
		newSpecimenTbl.setUpdatedDateTime(curetnDate);
		save(newSpecimenTbl);

		response.setUid(newSpecimenTbl.getUid());
		response.setId(newSpecimenTbl.getId());
		response.setSuccess(true);
		return response;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.SpecimenDao#updateSpecimen(com.nv.
	 * youNeverWait.rs.dto.SpecimenDTO)
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO updateSpecimen(SpecimenDTO specimen) {

		ResponseDTO response = new ResponseDTO();
		SpecimenTbl specimenTbl = getByUid(SpecimenTbl.class, specimen.getUid());
		if (specimenTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSpecimenUid);
			se.setDisplayErrMsg(true);
			throw se;
		}
		String specimenName = specimen.getSpecimenName().trim()
				.replaceAll(" +", " ");
		SpecimenTbl specTbl = getSpecimenByName(specimenName.toUpperCase());
		if (specTbl != null) {
			if (specimen.getUid() != specTbl.getUid()) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.SpecimenNameExists);
				se.addParam(new Parameter(Constants.NAME, specimenName));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		specimenTbl.setName(specimenName.trim());
		specimenTbl.setUid(specimen.getUid());
		specimenTbl.setUnit(specimen.getUnit());
		specimenTbl.setUpdatedDateTime(new Date());
		update(specimenTbl);

		// response.setUid(specimenTbl.getUid());
		response.setId(specimenTbl.getId());
		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.SpecimenDao#deleteSpecimen(int)
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO deleteSpecimen(int uid) {
		ResponseDTO response = new ResponseDTO();
		SpecimenTbl specimenTbl = getByUid(SpecimenTbl.class, uid);
		if (specimenTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSpecimenUid);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!specimenTbl.getTestSpecimenTbls().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.SpecimenCantDelete);
			se.setDisplayErrMsg(true);
			throw se;
		}
		delete(specimenTbl);

		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.SpecimenDao#viewSpecimen(int)
	 */
	@Override
	@Transactional(readOnly = false)
	public SpecimenResponseDTO viewSpecimen(int uid) {
		SpecimenResponseDTO response = new SpecimenResponseDTO();
		SpecimenTbl specimenTbl = getByUid(SpecimenTbl.class, uid);
		if (specimenTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSpecimenUid);
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setUid(specimenTbl.getUid());
		response.setSpecimenName(specimenTbl.getName());
		response.setUnit(specimenTbl.getUnit());
		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.SpecimenDao#getSpecimens(com.nv.youNeverWait
	 * .rs.dto.LabHeaderDTO, java.lang.String, java.util.Date)
	 */
	@Override
	@Transactional
	public RetrieveSpecimenResponseDTO getSpecimens(LabHeaderDTO header,
			String lastSyncTime, Date currentSyncTime) {
		RetrieveSpecimenResponseDTO response = new RetrieveSpecimenResponseDTO();
		labDao.validateHeader(header); // validates lab header details
		SimpleDateFormat sf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		Date lastSynTime = null;
		try {
			lastSynTime = sf.parse(lastSyncTime);
		} catch (ParseException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<SpecimenDTO>  specimenList= new ArrayList<SpecimenDTO>();
		List<SpecimenTbl> specimenTbl = getSpecimenByDate(lastSynTime,currentSyncTime);
		for(SpecimenTbl specimen:specimenTbl){
			SpecimenDTO newSpecimen= new SpecimenDTO();
			newSpecimen.setSpecimenName(specimen.getName());
			newSpecimen.setUid(specimen.getUid());
			newSpecimen.setUnit(specimen.getUnit());
			specimenList.add(newSpecimen);
		}
		response.setSpecimenList(specimenList);
		response.setSuccess(true);
		return response;
	}

	
	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.SpecimenDao#specimenList()
	 */
	@Override
	@Transactional
	public RetrieveSpecimenResponseDTO specimenList() {
		RetrieveSpecimenResponseDTO response = new RetrieveSpecimenResponseDTO();
		List<SpecimenTbl> specimenTblList=loadAll(SpecimenTbl.class);
		
		List<SpecimenDTO>  specimenList= new ArrayList<SpecimenDTO>();
		for(SpecimenTbl specimen : specimenTblList){
			specimenList.add(new SpecimenDTO(specimen));
		}
		response.setSpecimenList(specimenList);
		response.setSuccess(true);
		return response;
	}

	
	/**
	 * @param lastSynTime
	 * @param currentSyncTime
	 * @return
	 */
	private List<SpecimenTbl> getSpecimenByDate(Date lastSynTime,
			Date currentSyncTime) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_SPECIMEN_BY_DATE);
		query.setParameter("param1", lastSynTime);
		query.setParameter("param2", currentSyncTime);
		return executeQuery(SpecimenTbl.class, query);
	}

	// /**
		// * Query returning last record uid
		// * @return
		// */
		// private int getLastUId() {
		// Integer lastUid=0;
		// javax.persistence.Query query=em.createQuery(Query.GET_LAST_ID);
		// lastUid=(Integer) query.getSingleResult();
		// return lastUid;
		// }

		/**
		 * Query returning last record uid
		 * 
		 * @return
		 */
		private Integer getLastUId() {
			Integer lastId = null;
			javax.persistence.Query query = em.createQuery(Query.GET_LAST_UID);
			lastId = (Integer) query.getSingleResult();
			return lastId;
		}

		
	/**
	 * @param upperCase
	 * @return
	 */
	private SpecimenTbl getSpecimenByName(String specimenName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_SPECIMEN_BY_NAME);
		query.setParameter("param1", specimenName);
		return executeUniqueQuery(SpecimenTbl.class, query);
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
	 * @return the labDao
	 */
	public LabDao getLabDao() {
		return labDao;
	}

	/**
	 * @param labDao
	 *            the labDao to set
	 */
	public void setLabDao(LabDao labDao) {
		this.labDao = labDao;
	}

	
}
