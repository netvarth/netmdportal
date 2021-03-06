/**
 * OrderDaoImpl.java
 * @author netvarth
 *
 * Version 1.0 Sep 25, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
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
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.nv.security.youNeverWait.User;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.FacilityResultTbl;
import com.nv.youNeverWait.pl.entity.LabBranchTbl;
import com.nv.youNeverWait.pl.entity.LabTbl;
import com.nv.youNeverWait.pl.entity.LoginTbl;
import com.nv.youNeverWait.pl.entity.NetlimsOrderTbl;
import com.nv.youNeverWait.pl.entity.NetlimsResultTbl;
import com.nv.youNeverWait.pl.entity.OrderBranchTbl;
import com.nv.youNeverWait.pl.entity.OrderTransferTbl;
import com.nv.youNeverWait.pl.entity.PatientResultTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.ListResponse;
import com.nv.youNeverWait.rs.dto.Order;
import com.nv.youNeverWait.rs.dto.OrderCountFilterDto;
import com.nv.youNeverWait.rs.dto.OrderDetails;
import com.nv.youNeverWait.rs.dto.OrderTest;
import com.nv.youNeverWait.rs.dto.OrderTestResultDTO;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
import com.nv.youNeverWait.rs.dto.OrderTypeDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.LabDao;
import com.nv.youNeverWait.user.pl.dao.OrderDao;
import com.nv.youNeverWait.util.filter.core.Filter;
import com.nv.youNeverWait.util.filter.core.FilterFactory;
import com.nv.youNeverWait.util.filter.core.JsonUtil;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;
import com.nv.youNeverWait.util.filter.core.QueryBuilderFactory;

/**
 * 
 * 
 * @author Luciya Jose
 */
public class OrderDaoImpl extends GenericDaoHibernateImpl implements OrderDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8100493616831291262L;
	@PersistenceContext()
	private EntityManager em;
	private LabDao labDao;
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.OrderDao#retrieveBranchOrders(com.nv.
	 * youNeverWait.rs.dto.LabHeaderDTO, java.lang.String, java.util.Date)
	 */
	@Override
	@Transactional
	public OrderDetails retrieveBranchOrders(HeaderDTO header,
			String lastSyncTime, Date currentSyncTime) {
		OrderDetails orderDetail = new OrderDetails();

		/* Checking header details */
		labDao.CheckHeaderDetails(header);

		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		String syncTime = lastSyncTime;
		String currentSync=sdf.format(currentSyncTime);
		/*Date syncTime = null;
		if(lastSyncTime!=null){
			try {
				syncTime = sdf.parse(lastSyncTime);
			} catch (ParseException e) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidSyncTime);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}*/
		List<Object> orderList = new ArrayList<Object>();
		List<OrderTransferTbl> receivedOrders = getTransferredOrders(header.getHeadOfficeId(), header.getBranchId(),
				syncTime, currentSync);
		for (OrderTransferTbl order : receivedOrders) {
			Object orderDetails = JsonUtil.getObject(order.getOrderBranchTbl().getOrderDetails());
			order.setSent(true);  
			update(order);
			orderList.add(orderDetails);
		}
		orderDetail.setOrders(orderList);
		orderDetail.setLastOrderSyncTime(sdf.format(currentSyncTime));
		orderDetail.setSuccess(true);
		return orderDetail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.LabDao#transferOrder(com.nv.youNeverWait
	 * .rs.dto.OrderTransfer)
	 */
	@Override
	@Transactional
	public ResponseDTO transferOrder(OrderTransfer orderTranfer) {
		ResponseDTO response = new ResponseDTO();
		LabBranchTbl sourceLabBranch = getLabBranchId(
				orderTranfer.getSourceLabBranchId(),
				orderTranfer.getSourceLabId());
		if (sourceLabBranch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSourceLabBranch);
			se.setDisplayErrMsg(true);
			throw se;
		}

		/* Saving orders in order branch tbl */
		OrderBranchTbl orderBranchTbl = new OrderBranchTbl();
		orderBranchTbl.setLabTbl(sourceLabBranch.getLabTbl());
		orderBranchTbl.setLabBranchTbl(sourceLabBranch);
		orderBranchTbl.setOrderDetails(orderTranfer.getOrderDetails());

		/** Setting unique id if there is no unique id given from **/
		if (orderTranfer.getOrderUid() == null || orderTranfer.getOrderUid().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.OrderUidNull);

			se.setDisplayErrMsg(true);
			throw se;
		} else {
			orderBranchTbl.setOrderUid(orderTranfer.getOrderUid());
		}

		Date createdTime = new Date();
		orderBranchTbl.setCreatedDateTime(createdTime);
		orderBranchTbl.setUpdatedDateTime(createdTime);
		save(orderBranchTbl);

		/* Saving details in order transfer tbl */
		for (Integer destinationBranch : orderTranfer.getDestinationBranches()) {
			/* Checking whether there is any labs and branches */
			LabBranchTbl destinationLabBranch = getLabBranchId(
					destinationBranch, orderTranfer.getDestinationLabId());

			if (destinationLabBranch == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDestinationLabBranch);

				se.setDisplayErrMsg(true);
				throw se;

			}
			OrderTransferTbl orderTransferTbl = new OrderTransferTbl();
			orderTransferTbl.setOrderBranchTbl(orderBranchTbl);
			orderTransferTbl.setLabBranchTbl(destinationLabBranch);
			orderTransferTbl.setLabTbl(destinationLabBranch.getLabTbl());
			orderTransferTbl.setCreatedDateTime(createdTime);
			orderTransferTbl.setUpdatedDateTime(createdTime);
			save(orderTransferTbl);

		}
		response.setGlobalId(orderBranchTbl.getId());
		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.OrderDao#setOrderType(com.nv.youNeverWait
	 * .rs.dto.OrderTypeDTO)
	 */
	@Override
	@Transactional
	public ResponseDTO setOrderType(OrderTypeDTO orderTypeDetails) {
		ResponseDTO response = new ResponseDTO();
		LabTbl lab = getById(LabTbl.class, orderTypeDetails.getLabId());
		if (lab == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(orderTypeDetails.getLabId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		lab.setOrderTypeCode(orderTypeDetails.getOrderTypeCodes());
		lab.setUpdateDateTime(new Date());
		update(lab);
		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.OrderDao#getOrderType(int)
	 */
	@Override
	@Transactional
	public OrderTypeDTO getOrderType(int labId) {
		LabTbl lab = getById(LabTbl.class, labId);
		if (lab == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(labId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		OrderTypeDTO orderType = new OrderTypeDTO();
		orderType.setOrderTypeCodes(lab.getOrderTypeCode());
		return orderType;
	}

	/**
	 * 
	 * @param branchId
	 * @param labId
	 * @return LabBranchTbl
	 */
	public LabBranchTbl getByLabId(int branchId, int labId) {
		javax.persistence.Query query = em.createQuery(Query.GET_BRANCH);
		query.setParameter("param1", branchId);
		query.setParameter("param2", labId);
		return executeUniqueQuery(LabBranchTbl.class, query);
	}

	/**
	 * @param i
	 * @param sourceLabBranchId
	 * @return
	 */
	private LabBranchTbl getLabBranchId(int labBranchId, int labId) {

		javax.persistence.Query query = em.createQuery(Query.GET_LAB_BRANCH);
		query.setParameter("param1", labBranchId);
		query.setParameter("param2", labId);
		return executeUniqueQuery(LabBranchTbl.class, query);
	}

	/**
	 * @param labId
	 * @param labBranchId
	 * @param syncTime
	 * @param currentSyncTime
	 * @return
	 */
	private List<OrderTransferTbl> getTransferredOrders(int labId,
			int labBranchId, String syncTime, String currentSyncTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_ORDERS);
		query.setParameter("param1", labId);
		query.setParameter("param2", labBranchId);
		query.setParameter("param3", syncTime);
		query.setParameter("param4", currentSyncTime);
		return executeQuery(OrderTransferTbl.class, query);
	}

	/**
	 * @param labDao
	 *            the labDao to set
	 */
	public void setLabDao(LabDao labDao) {
		this.labDao = labDao;
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
	public ListResponse getFacilityOrderByFilter(FilterDTO filterParam, User user) {
		//		ExpressionDTO branchExp = new ExpressionDTO();
		//		branchExp.setName("branchId");
		//		branchExp.setOperator("eq");
		//		branchExp.setValue(Integer.toString(user.getOrganisationId()));



		//		LabFacilityTbl labFacilitytbl = getFacilityByEmailId(user.getUserName());
		//		ExpressionDTO expr = new ExpressionDTO();
		//		expr.setName("loginId");
		//		expr.setOperator("eq");
		//		expr.setValue(Integer.toString(labFacilitytbl.getId()));

		ListResponse response = new ListResponse();
		//get queryBuilder for test from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(Constants.NETLIMS_FACILITY_ORDERS);
		//	Filter brchFilter = filterFactory.getFilter(loginExp);
		//	queryBuilder.addFilter(brchFilter);
		for (ExpressionDTO exp : filterParam.getExp()) {
			//get filter from filter factory by setting expression name and value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		//build query
		TypedQuery<FacilityResultTbl> q =   queryBuilder.buildQuery(filterParam.isAsc(),
				filterParam.getFrom(),filterParam.getCount()); 
		Long count = queryBuilder.getCount();
		List<Order> orders = new ArrayList<Order>();
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
		for(FacilityResultTbl orderTbl: queryBuilder.executeQuery(q)){
			Order order = new Order();
			order.setId(orderTbl.getNetlimsOrderTbl().getId());
			order.setUid(orderTbl.getNetlimsOrderTbl().getOrderId());
			order.setCreatedOn(df.format(orderTbl.getNetlimsOrderTbl().getCreatedDate()));
			order.setOrderStatus(orderTbl.getNetlimsOrderTbl().getOrderStatus());
			order.setBranchId(Integer.toString(orderTbl.getNetlimsOrderTbl().getLabBranchTbl().getId()));
			order.setBranchName(orderTbl.getNetlimsOrderTbl().getLabBranchTbl().getName());
			order.setHeaderData(orderTbl.getNetlimsOrderTbl().getOrderHeader());
			//order.setHeader(orderTbl.get)
			orders.add(order);
		}
		response.setList(orders);
		response.setCount(count);
		return response;
	}

	/**
	 * 
	 * @param userName
	 * @param userType 
	 * @return LoginTbl
	 */
	public LoginTbl getUserByNameAndType(String userName, String userType) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_LOGIN_BY_USERNAME_USERTYPE);
		query.setParameter("param1", userName);
		query.setParameter("param2", userType);
		return executeUniqueQuery(LoginTbl.class, query);
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
	 * @return the labDao
	 */
	public LabDao getLabDao() {
		return labDao;
	}

	@Override
	public List<OrderTestResultDTO> getTests(int orderId) {
		List<OrderTestResultDTO> orderTests = new ArrayList<OrderTestResultDTO>();
		List<NetlimsResultTbl> testResults = getResultsByOrderIdandBranchId(orderId);
		for(NetlimsResultTbl netlimsResultTbl: testResults){
			OrderTestResultDTO orderTest = new OrderTestResultDTO();
			orderTest.setTestName(netlimsResultTbl.getTestName());
			orderTest.setUid(netlimsResultTbl.getTestUid());
			orderTest.setResult(netlimsResultTbl.getResult());
			orderTests.add(orderTest);
		}
		return orderTests;
	}

	private List<NetlimsResultTbl> getResultsByOrderIdandBranchId(
			int orderId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETLIMSRESULTS_BY_ORDERID);
		query.setParameter("param1", orderId);
		return executeQuery(NetlimsResultTbl.class, query);
	}

	@Override
	public Order getByOrderId(int orderId) {
		Order order = new Order();
		NetlimsOrderTbl  netlimsOrderTbl = getById(NetlimsOrderTbl.class, orderId);
		order.setBranchId(Integer.toString(netlimsOrderTbl.getLabBranchTbl().getId()));
		//order.setCreatedOn(netlimsOrderTbl.getCreatedDate());
		order.setId(netlimsOrderTbl.getId());
		order.setOrderStatus(netlimsOrderTbl.getOrderStatus());
		order.setHeaderData(netlimsOrderTbl.getOrderHeader());

		List<NetlimsResultTbl> netlimsResults = netlimsOrderTbl.getNetlimsResultTbls();
		List<OrderTest> tests = new ArrayList<OrderTest>();
		for(NetlimsResultTbl netlimsResult : netlimsResults) {
			OrderTest test = new OrderTest();
			test.setItemId(netlimsResult.getItemId());
			test.setUid(netlimsResult.getTestUid());
			test.setTestName(netlimsResult.getTestName());
			test.setSpecimen(netlimsResult.getSpecimen());
			test.setResult(netlimsResult.getResult());
			tests.add(test);
		}
		order.setTests(tests);
		return order;
	}

	public int getOrderCountFromLab(OrderCountFilterDto ocf){
		Long orderCount;

		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		Date fromDate;
		try {
			fromDate = df.parse(ocf.getFromDate());
		} catch (ParseException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}	
		Date toDate;
		try {
			toDate = df.parse(ocf.getToDate());
		} catch (ParseException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		javax.persistence.Query query = em.createQuery(Query.GET_LAB_ORDER_COUNT);
		System.out.println(ocf.getBranch().intValue());
		query.setParameter("param1", ocf.getBranch());
		query.setParameter("param2", fromDate);
		query.setParameter("param3", toDate);
		orderCount = (Long)query.getSingleResult();
		System.out.println("ordercount--"+orderCount);
		if(orderCount==null)
			return 0;
		return orderCount.intValue();
	}
	
	public int getOrderCountByBranchId(OrderCountFilterDto ocf) {
		Long orderCount;

		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		Date fromDate;
		try {
			fromDate = df.parse(ocf.getFromDate());
		} catch (ParseException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}	
		Date toDate;
		try {
			toDate = df.parse(ocf.getToDate());
		} catch (ParseException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		javax.persistence.Query query = em.createQuery(Query.GET_ORDER_COUNT);
		query.setParameter("param1", ocf.getBranch());
		query.setParameter("param2", fromDate);
		query.setParameter("param3", toDate);
		orderCount = (Long)query.getSingleResult();
		System.out.println("ordercount--"+orderCount);
		if(orderCount==null)
			return 0;
		return orderCount.intValue();
	}
	
	public int getOrderCountForFacility(OrderCountFilterDto ocf){
		Long orderCount;

		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		Date fromDate;
		try {
			fromDate = df.parse(ocf.getFromDate());
		} catch (ParseException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}	
		Date toDate;
		try {
			toDate = df.parse(ocf.getToDate());
		} catch (ParseException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		javax.persistence.Query query = em.createQuery(Query.GET_FACILITY_ORDER_COUNT);
		query.setParameter("param1", ocf.getFacility());
		query.setParameter("param2", fromDate);
		query.setParameter("param3", toDate);
		orderCount = (Long)query.getSingleResult();
		System.out.println("ordercount--"+orderCount);
		if(orderCount==null)
			return 0;
		return orderCount.intValue();
	}
	
	@Override
	public ListResponse getByPatientFilter(FilterDTO filterParam, User user) {
		ListResponse response = new ListResponse();
		QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(Constants.PATIENT_ORDERS);
		for (ExpressionDTO exp : filterParam.getExp()) {
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		TypedQuery<PatientResultTbl> q =   queryBuilder.buildQuery(filterParam.isAsc(),
				filterParam.getFrom(),filterParam.getCount()); 
		Long count = queryBuilder.getCount();
		List<Order> orders = new ArrayList<Order>();
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
		for(PatientResultTbl orderTbl: queryBuilder.executeQuery(q)){
			Order order = new Order();
			order.setId(orderTbl.getNetlimsOrderTbl().getId());
			order.setUid(orderTbl.getNetlimsOrderTbl().getOrderId());
			order.setCreatedOn(df.format(orderTbl.getNetlimsOrderTbl().getCreatedDate()));
			order.setOrderStatus(orderTbl.getNetlimsOrderTbl().getOrderStatus());
			order.setBranchId(Integer.toString(orderTbl.getNetlimsOrderTbl().getLabBranchTbl().getId()));
			order.setBranchName(orderTbl.getNetlimsOrderTbl().getLabBranchTbl().getName());
			order.setHeaderData(orderTbl.getNetlimsOrderTbl().getOrderHeader());
			orders.add(order);
		}
		response.setList(orders);
		response.setCount(count);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.OrderDao#getByFilter(com.nv.youNeverWait.rs.dto.FilterDTO, com.nv.security.youNeverWait.User)
	 */
	@Override
	public ListResponse getByFilter(FilterDTO filterParam, User user) {
		ListResponse response = new ListResponse();
		QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(Constants.NETLIMS_ORDERS);
		for (ExpressionDTO exp : filterParam.getExp()) {
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		TypedQuery<NetlimsOrderTbl> q =   queryBuilder.buildQuery(filterParam.isAsc(),
				filterParam.getFrom(),filterParam.getCount()); 
		Long count = queryBuilder.getCount();
		List<Order> orders = new ArrayList<Order>();
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
		for(NetlimsOrderTbl orderTbl: queryBuilder.executeQuery(q)){
			Order order = new Order();
			order.setId(orderTbl.getId());
			order.setUid(orderTbl.getOrderId());
			order.setCreatedOn(df.format(orderTbl.getCreatedDate()));
			order.setOrderStatus(orderTbl.getOrderStatus());
			System.out.println(orderTbl.getLabBranchTbl().getLabTbl().getId());
			order.setBranchId(Integer.toString(orderTbl.getLabBranchTbl().getId()));
			order.setBranchName(orderTbl.getLabBranchTbl().getName());
			order.setHeaderData(orderTbl.getOrderHeader());
			orders.add(order);
		}
		response.setList(orders);
		response.setCount(count);
		return response;
	}



}
