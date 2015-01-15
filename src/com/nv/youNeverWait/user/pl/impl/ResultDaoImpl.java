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
import com.nv.youNeverWait.pl.entity.BranchFacilityTbl;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.FacilityResultTbl;
import com.nv.youNeverWait.pl.entity.LabBranchTbl;
import com.nv.youNeverWait.pl.entity.LabFacilityTbl;
import com.nv.youNeverWait.pl.entity.NetlimsOrderTbl;
import com.nv.youNeverWait.pl.entity.NetlimsPatientTbl;
import com.nv.youNeverWait.pl.entity.NetlimsReferralTbl;
import com.nv.youNeverWait.pl.entity.NetlimsResultTbl;
import com.nv.youNeverWait.pl.entity.NetmdPassphraseTbl;
import com.nv.youNeverWait.pl.entity.OrderResultTbl;
import com.nv.youNeverWait.pl.entity.OrderTransferCountTbl;
import com.nv.youNeverWait.pl.entity.PageSettingsTbl;
import com.nv.youNeverWait.pl.entity.PatientResultTbl;
import com.nv.youNeverWait.pl.entity.PatientTbl;
import com.nv.youNeverWait.pl.entity.ReferralResultTbl;
import com.nv.youNeverWait.pl.entity.ResultTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LimsPageSettingsBundle;
import com.nv.youNeverWait.rs.dto.OrderResultSyncDTO;
import com.nv.youNeverWait.rs.dto.OrderTestResult;
import com.nv.youNeverWait.rs.dto.OrderTestResultDTO;
import com.nv.youNeverWait.rs.dto.OrderTestResultList;
import com.nv.youNeverWait.rs.dto.PageLayoutSettings;
import com.nv.youNeverWait.rs.dto.ResultDTO;
import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveResultsResponseDTO;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.DoctorDao;
import com.nv.youNeverWait.user.pl.dao.FacilityDao;
import com.nv.youNeverWait.user.pl.dao.PatientDao;
import com.nv.youNeverWait.user.pl.dao.ResultDao;
import com.nv.youNeverWait.util.filter.core.JsonUtil;

/**
 * 
 */
public class ResultDaoImpl extends GenericDaoHibernateImpl implements ResultDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8123597707204203443L;
	private PatientDao patientDao;
	private DoctorDao doctorDao;
	private FacilityDao facilityDao;
	/**
	 * @return the patientDao
	 */
	public PatientDao getPatientDao() {
		return patientDao;
	}

	/**
	 * @param patientDao the patientDao to set
	 */
	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	@PersistenceContext()
	private EntityManager em;

	/**
	 * Get patient results
	 */
	@Override
	@Transactional
	public List<RetrieveResultsResponseDTO> getPatientResults(String syncTime,
			String passPhrase, int netmdBranchId, Date currentSyncTime) {
		List<RetrieveResultsResponseDTO> retrieveResultsList = new ArrayList<RetrieveResultsResponseDTO>();
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

		NetmdPassphraseTbl netmdPassphrase = getNetmdPassphrase(netmdBranchId,
				passPhrase);
		if (netmdPassphrase == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* Getting patient results list */
		List<ResultTbl> resultsList = getResultsByNetMdBranchIdAndNetmdId(
				netmdPassphrase.getNetmdBranchTbl().getNetmdTbl().getId(),
				netmdBranchId, lastSyncTime, currentSyncTime);
		for (ResultTbl result : resultsList) {

			retrieveResultsList.add(new RetrieveResultsResponseDTO(result));
		}
		return retrieveResultsList;
	}

	@Override
	@Transactional
	public OrderTestResultList retrieveResults(HeaderDTO header,
			String lastSyncTime, Date currentSyncTime) {
		OrderTestResultList response = new OrderTestResultList();
		List<OrderTestResult> orderTestResultList = new ArrayList<OrderTestResult>();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		Date syncTime = null;
		try {
			syncTime = sdf.parse(lastSyncTime);
		} catch (ParseException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}

		List<OrderResultTbl> orderResultTblList = getResults(
				header.getBranchId(), syncTime, currentSyncTime);

		String uid = "";
		String uidC = "";
		OrderTestResult	orderResult=null;
		for (OrderResultTbl orderTestResult : orderResultTblList) {
			uidC = orderTestResult.getOrderBranchTbl().getOrderUid();
			Object result=JsonUtil.getObject(orderTestResult.getResult());
			if (uid != uidC) {
				if (orderResult !=null){
					System.out.println(orderResult.getTestResultList().size());
					orderTestResultList.add(orderResult);
				}
				orderResult = new OrderTestResult();
				orderResult.setOrderUid(orderTestResult.getOrderBranchTbl().getOrderUid());
				orderResult.addToMap(new String(orderTestResult.getTestUid()), result);
			} else {
				orderResult.addToMap(new String(orderTestResult.getTestUid()), result);
			}
			uid = uidC;
			orderTestResult.setSent(true);  
			update(orderTestResult);
		}
		if (orderResult !=null){
			System.out.println(orderResult.getTestResultList().size());
			orderTestResultList.add(orderResult);

		}
		response.setOrderTestResultList(orderTestResultList);
		response.setSuccess(true);
		return response;
	}

	private List<OrderResultTbl> getResults(int branchId, Date syncTime,
			Date currentSyncTime) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ORDER_TEST_RESULTS);
		query.setParameter("param1", branchId);
		query.setParameter("param2", syncTime);
		query.setParameter("param3", currentSyncTime);
		return executeQuery(OrderResultTbl.class, query);
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
	 * @param netmdId 
	 * @param netmdBranchId
	 * @param lastSyncTime 
	 * @param currentSyncTime 
	 * @return List<ResultTbl> return results 
	 */
	public List<ResultTbl> getResultsByNetMdBranchIdAndNetmdId(int netmdId,
			int netmdBranchId, Date lastSyncTime, Date currentSyncTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_RESULTS);
		query.setParameter("param1", netmdId);
		query.setParameter("param2", netmdBranchId);
		query.setParameter("param3", lastSyncTime);
		query.setParameter("param4", currentSyncTime);
		return executeQuery(ResultTbl.class, query);

	}

	/**
	 * Method for getting netmd passphrase id
	 * 
	 * @param passPhrase
	 * @param netmdBranchId
	 * @return netmd passphrase id
	 */
	public NetmdPassphraseTbl getNetmdPassphrase(int netmdBranchId,
			String passPhrase) {

		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_PASSPHRASE_BY_BRANCH_ID);
		query.setParameter("param1", netmdBranchId);
		query.setParameter("param2", passPhrase);
		return executeUniqueQuery(NetmdPassphraseTbl.class, query);
	}

	/**
	 * @param patientId
	 * @return List<ResultTbl> list of results of mentioned patient id
	 */
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

	@Override
	@Transactional(readOnly = false)
	public int processOrderResult(OrderResultSyncDTO orderResult, int branchId, int patientId) {
		/*if Global Id is not equal to zero then order exists in portal
		 * if zero then save action 
		 * else update action
		 */
		NetlimsOrderTbl netlimsOrderTbl =null;
		if(orderResult.getGlobalId()!=0) 
			netlimsOrderTbl = getById(NetlimsOrderTbl.class, orderResult.getGlobalId());
		/* Save NetlimsOrderTbl
		 * if there is an record having orderuid and branchId then set then return with a status success with no action
		 * else perform the save action
		 */
		if(netlimsOrderTbl==null) {
			netlimsOrderTbl = getBy_orderuid_branchid(orderResult.getOrder().getUid(), branchId);
			if(netlimsOrderTbl!=null)
				return 0;
		}
		netlimsOrderTbl = saveOrUpdateNetlimsOrder(netlimsOrderTbl, orderResult, branchId);
		saveOrUpdateOrderPatient(netlimsOrderTbl, branchId, patientId);
		saveOrUpdateNetlimsOrderTests(netlimsOrderTbl, orderResult, branchId);//Save Results
		saveOrUpdateOrderReferral(netlimsOrderTbl, orderResult.getOrder().getReferralGlobalId());
		saveOrUpdateOrderFacility(netlimsOrderTbl, orderResult.getOrder().getFacilityGlobalId());
		return netlimsOrderTbl.getId();
	}
	@Transactional(readOnly = false)
	private void saveOrUpdateOrderFacility(NetlimsOrderTbl netlimsOrderTbl,
			int facilityId) {
		FacilityResultTbl facilityResultTbl = getFacilityResult(netlimsOrderTbl.getId());
		if(facilityId!=0){
			LabFacilityTbl labFacilityTbl = getById(LabFacilityTbl.class, facilityId);
			BranchFacilityTbl branchFacility = new BranchFacilityTbl();
			branchFacility.setLabFacilityTbl(labFacilityTbl);
			branchFacility.setLabBranchTbl(netlimsOrderTbl.getLabBranchTbl());
			saveOrUpdate(BranchFacilityTbl.class, branchFacility);
			if(facilityResultTbl==null)
				facilityResultTbl=new FacilityResultTbl();
			facilityResultTbl.setLabFacilityTbl(labFacilityTbl);
			facilityResultTbl.setNetlimsOrderTbl(netlimsOrderTbl);
			saveOrUpdate(FacilityResultTbl.class, facilityResultTbl);
		} else
			if(facilityResultTbl!=null)
				delete(facilityResultTbl);

	}
	@Transactional(readOnly = false)
	private void saveOrUpdateOrderReferral(NetlimsOrderTbl netlimsOrderTbl,
			int referralId) {
		ReferralResultTbl referralResultTbl = getReferralResult(netlimsOrderTbl.getId());
		if(referralId!=0){
			NetlimsReferralTbl netlimsReferralTbl = getById(NetlimsReferralTbl.class, referralId);
			if(referralResultTbl==null)
				referralResultTbl=new ReferralResultTbl();
			referralResultTbl.setNetlimsReferralTbl(netlimsReferralTbl);
			referralResultTbl.setNetlimsOrderTbl(netlimsOrderTbl);
			saveOrUpdate(NetlimsReferralTbl.class, netlimsReferralTbl);
		} else
			if(referralResultTbl!=null)
				delete(referralResultTbl);
	}
	@Transactional(readOnly = false)
	private void saveOrUpdateOrderPatient(NetlimsOrderTbl netlimsOrderTbl,
			int branchId, int patientId) {
		if(patientId!=0){

			NetlimsPatientTbl netlimsPatientTbl = getByPatientId_BranchId(patientId, branchId);
			if(netlimsPatientTbl==null){
				netlimsPatientTbl = new NetlimsPatientTbl();
				netlimsPatientTbl.setPatientTbl(getById(PatientTbl.class, patientId));
				netlimsPatientTbl.setLabBranchTbl(getById(LabBranchTbl.class, branchId));
			}
			saveOrUpdate(NetlimsPatientTbl.class, netlimsPatientTbl);
			PatientResultTbl patientResult = getByOrderid_PatientId(netlimsOrderTbl.getId(), patientId);
			if(patientResult==null)
				patientResult=new PatientResultTbl();
			patientResult.setNetlimsOrderTbl(netlimsOrderTbl);
			patientResult.setNetlimsPatientTbl(netlimsPatientTbl);
			saveOrUpdate(PatientResultTbl.class,patientResult);
		}
	}

	private PatientResultTbl getByOrderid_PatientId(int orderId, int patientId) {
		javax.persistence.Query query = em.createQuery(Query.GET_PATIENT_RESULT_TBL);
		query.setParameter("param1",patientId);
		query.setParameter("param2",orderId);
		return executeUniqueQuery(PatientResultTbl.class, query);
	}

	private NetlimsPatientTbl getByPatientId_BranchId(int patientId,
			int branchId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETLIMS_PATIENT_BY_PATIENTID_BRANCHID);
		query.setParameter("param1",patientId);
		query.setParameter("param2",branchId);
		return executeUniqueQuery(NetlimsPatientTbl.class, query);
	}

	private FacilityResultTbl getFacilityResult(int orderId) {
		javax.persistence.Query query = em.createQuery(Query.GET_ORDERFACILITY_BY_ORDERID);
		query.setParameter("param1",orderId);
		return executeUniqueQuery(FacilityResultTbl.class, query);
	}
	@Transactional(readOnly = false)
	private void saveOrUpdateNetlimsOrderTests(NetlimsOrderTbl netlimsOrderTbl,
			OrderResultSyncDTO orderResult, int branchId) {
		for(OrderTestResultDTO orderTestResult: orderResult.getTestResult()){
			NetlimsResultTbl resultTbl = getByTestUid_OrderId(orderTestResult.getUid(), netlimsOrderTbl.getId());
			if(resultTbl==null)
				resultTbl = new NetlimsResultTbl();
			resultTbl.setNetlimsOrderTbl(netlimsOrderTbl);
			String result = JsonUtil.getString(orderTestResult.getResult());
			if(result!=null)	
				result = result.replaceAll("microsymbol", "\u00b5");
			resultTbl.setResult(result);
			resultTbl.setTestName(orderTestResult.getTestName());
			resultTbl.setTestUid(orderTestResult.getUid());
			resultTbl.setItemId(orderTestResult.getItemId());
			saveOrUpdate(NetlimsResultTbl.class, resultTbl);
		}
	}

	private NetlimsResultTbl getByTestUid_OrderId(String testUid, int orderId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETLIMSRESULT_BY_ORDERID_TESTID);
		query.setParameter("param1", orderId);
		query.setParameter("param2",testUid);
		return executeUniqueQuery(NetlimsResultTbl.class, query);
	}
	@Transactional(readOnly=false)
	private NetlimsOrderTbl saveOrUpdateNetlimsOrder(NetlimsOrderTbl netlimsOrderTbl, OrderResultSyncDTO orderResult, int branchId ) {
		NetlimsOrderTbl orderTbl=null;
		int orderTransferCount=0;
		DateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_NO_TIME);
		Date transferDate = null ;
		try {
			transferDate = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		OrderTransferCountTbl orderTransferCountObj = getOrderCountByBranchId(transferDate,branchId);
		if(orderTransferCountObj == null){
			orderTransferCountObj = new OrderTransferCountTbl();
			orderTransferCountObj.setDate(transferDate);
			LabBranchTbl labBranchTbl = getById(LabBranchTbl.class, branchId);
			orderTransferCountObj.setLabBranchTbl(labBranchTbl);
			if(orderResult.getOrder().getFacilityGlobalId()!=0){
			LabFacilityTbl facility = getById(LabFacilityTbl.class, orderResult.getOrder().getFacilityGlobalId());
			orderTransferCountObj.setLabFacilityTbl(facility);
			}
		}
		orderTransferCount = orderTransferCountObj.getOrderCount();
		if(netlimsOrderTbl == null){
			orderTbl = new NetlimsOrderTbl();
			orderTransferCount++;
		}else
			orderTbl = netlimsOrderTbl;
		orderTbl.setCreatedDate(orderResult.getOrder().getOrderDate());
		orderTbl.setOrderId(orderResult.getOrder().getUid());
		orderTbl.setOrderStatus(orderResult.getOrder().getOrderStatus());
		orderTbl.setLabBranchTbl(getById(LabBranchTbl.class, branchId));
		orderTbl.setCreatedDate(new Date());
		orderTbl.setOrderHeader(orderResult.getOrder().getOrderHeader());
		saveOrUpdate(NetlimsOrderTbl.class, orderTbl);	
		orderTransferCountObj.setOrderCount(orderTransferCount);  
		saveOrUpdate(OrderTransferCountTbl.class,orderTransferCountObj);
		return orderTbl;
	}

	private NetlimsOrderTbl getBy_orderuid_branchid(String uid, int branchId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETLIMSORDER_BY_ORDERID_BRANCHID);
		query.setParameter("param1", uid);
		query.setParameter("param2",branchId);
		return executeUniqueQuery(NetlimsOrderTbl.class, query);
	}

	private ReferralResultTbl getReferralResult(int orderId) {
		javax.persistence.Query query= em.createQuery(Query.GET_REFERRAL_RESULTBY_ORDERID);
		query.setParameter("param1",orderId );
		return executeUniqueQuery(ReferralResultTbl.class, query);
	}
	private OrderTransferCountTbl getOrderCountByBranchId(Date createdDate, int branchId) {
		javax.persistence.Query query = em.createQuery(Query.GET_ORDER_COUNT_FOR_SAVE);
		query.setParameter("param1", branchId);
		query.setParameter("param2", createdDate);
		return executeUniqueQuery(OrderTransferCountTbl.class, query);
	}
	/**
	 * @return the doctorDao
	 */
	public DoctorDao getDoctorDao() {
		return doctorDao;
	}

	/**
	 * @param doctorDao the doctorDao to set
	 */
	public void setDoctorDao(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}

	/**
	 * @return the facilityDao
	 */
	public FacilityDao getFacilityDao() {
		return facilityDao;
	}

	/**
	 * @param facilityDao the facilityDao to set
	 */
	public void setFacilityDao(FacilityDao facilityDao) {
		this.facilityDao = facilityDao;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.ResultDao#ProcessPageSettings(com.nv.youNeverWait.rs.dto.LimsPageSettingsBundle)
	 */
	@Override
	@Transactional(readOnly=false)
	public void ProcessPageSettings(LimsPageSettingsBundle bundle) {
		for(PageLayoutSettings pagesetting:bundle.getPageSettings()){
			PageSettingsTbl layout = getByKey(pagesetting.getKey(),bundle.getSource_branch_id());
			if(layout==null){
				layout=new PageSettingsTbl();
			}
			layout.setKeyName(pagesetting.getKey());
			layout.setWidth(pagesetting.getWidth());
			layout.setHeight(pagesetting.getHeight());
			layout.setMarginTop(pagesetting.getMarginTop());
			layout.setMarginLeft(pagesetting.getMarginLeft());
			layout.setMarginRight(pagesetting.getMarginRight());
			layout.setFontSize(pagesetting.getFontSize());
			layout.setLabelValue(pagesetting.getLabel());
			if(pagesetting.isBold())
				layout.setBold(true);
			else
				layout.setBold(false);
			if(pagesetting.isItalics())
				layout.setItalics(true);
			else
				layout.setItalics(false);
			if(pagesetting.isUnderline())
				layout.setUnderline(true);
			else
				layout.setUnderline(false);
			if(pagesetting.isVisible())
				layout.setVisible(true);
			else
				layout.setVisible(false);
			if(pagesetting.isBorder())
				layout.setBorder(true);
			else
				layout.setBorder(false);		
			layout.setBgImage(pagesetting.getBgImage());
			layout.setLabBranchTbl(getById(LabBranchTbl.class, bundle.getSource_branch_id()));
			saveOrUpdate(PageSettingsTbl.class,layout);
		}	
	}

	/**
	 * Mani E.V	
	 * @param key
	 * @return
	 */
	private PageSettingsTbl getByKey(String key, int branchId) {
		javax.persistence.Query query=em.createQuery(Query.GET_PAGESETTING_BY_NAME);
		query.setParameter("param1", key);
		query.setParameter("param2", branchId);
		return (PageSettingsTbl) executeUniqueQuery(PageSettingsTbl.class, query);
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.ResultDao#getPageSettings(int)
	 */
	@Override
	public List<PageLayoutSettings> getPageSettings(int branchId) {
		List<PageLayoutSettings> pageSettingList = new ArrayList<PageLayoutSettings>();

		List<PageSettingsTbl> layouts = getLayouts(branchId);
		for(PageSettingsTbl layout:layouts){
			PageLayoutSettings pagesetting = new PageLayoutSettings();
			pagesetting.setKey(layout.getKeyName());
			pagesetting.setBold(layout.getBold());
			pagesetting.setHeight(layout.getHeight());
			pagesetting.setItalics(layout.getItalics());
			pagesetting.setLabel(layout.getLabelValue());
			pagesetting.setUnderline(layout.getUnderline());
			pagesetting.setVisible(layout.getVisible());
			pagesetting.setWidth(layout.getWidth());
			pagesetting.setMarginTop(layout.getMarginTop());
			pagesetting.setMarginLeft(layout.getMarginLeft());
			pagesetting.setMarginRight(layout.getMarginRight());
			pagesetting.setFontSize(layout.getFontSize());
			pagesetting.setBorder(layout.getBorder());
			pagesetting.setBgImage(layout.getBgImage());
			pageSettingList.add(pagesetting);
		}
		return pageSettingList;
	}

	/**
	 * Mani E.V	
	 * @param branchId 
	 * @return
	 */
	private List<PageSettingsTbl> getLayouts(int branchId) {
		javax.persistence.Query query=em.createQuery(Query.GET_PAGESETTINGS);
		query.setParameter("param1", branchId);
		return query.getResultList();
	}

}
