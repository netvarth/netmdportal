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
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;

import com.nv.security.youNeverWait.User;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.FacilityResultTbl;
import com.nv.youNeverWait.pl.entity.LabBranchTbl;
import com.nv.youNeverWait.pl.entity.LabFacilityTbl;
import com.nv.youNeverWait.pl.entity.LabTbl;
import com.nv.youNeverWait.pl.entity.LoginTbl;
import com.nv.youNeverWait.pl.entity.NetlimsOrderTbl;
import com.nv.youNeverWait.pl.entity.NetlimsResultTbl;
import com.nv.youNeverWait.pl.entity.OrderBranchTbl;
import com.nv.youNeverWait.pl.entity.OrderTransferTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.ListResponse;
import com.nv.youNeverWait.rs.dto.Order;
import com.nv.youNeverWait.rs.dto.OrderDetails;
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
		Date syncTime = null;
		try {
			syncTime = sdf.parse(lastSyncTime);
		} catch (ParseException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<Object> orderList = new ArrayList<Object>();
		List<OrderTransferTbl> recievedOrders = getTransferredOrders(
				header.getHeadOfficeId(), header.getBranchId(), syncTime,
				currentSyncTime);
		for (OrderTransferTbl order : recievedOrders) {
			Object orderDetails = new Object();
			ObjectMapper maper = new ObjectMapper();
			try {
				orderDetails = maper.readValue(order.getOrderBranchTbl().getOrderDetails(),
						Object.class);
			} catch (Exception e) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.OrderTransferException);
				se.setDisplayErrMsg(true);
				throw se;
			}
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
			int labBranchId, Date syncTime, Date currentSyncTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_ORDERS);
		query.setParameter("param1", labId);
		query.setParameter("param2", labBranchId);
		query.setParameter("param3", syncTime,TemporalType.TIMESTAMP);
		query.setParameter("param4", currentSyncTime,TemporalType.TIMESTAMP);
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
	public ListResponse getByFilter(FilterDTO filterParam, User user) {
		LoginTbl loginTbl = getUserByNameAndType(user.getUserName(), user.getUserType());
//		ExpressionDTO branchExp = new ExpressionDTO();
//		branchExp.setName("branchId");
//		branchExp.setOperator("eq");
//		branchExp.setValue(Integer.toString(user.getOrganisationId()));
		
		ExpressionDTO loginExp = new ExpressionDTO();
		loginExp.setName("loginId");
		loginExp.setOperator("eq");
		loginExp.setValue(Integer.toString(loginTbl.getId()));

//		LabFacilityTbl labFacilitytbl = getFacilityByEmailId(user.getUserName());
//		ExpressionDTO expr = new ExpressionDTO();
//		expr.setName("loginId");
//		expr.setOperator("eq");
//		expr.setValue(Integer.toString(labFacilitytbl.getId()));
		
		ListResponse response = new ListResponse();
		//get queryBuilder for test from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(Constants.NETLIMS_ORDERS);
		Filter brchFilter = filterFactory.getFilter(loginExp);
		queryBuilder.addFilter(brchFilter);
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
			order.setHeaderData(orderTbl.getNetlimsOrderTbl().getOrderHeader());
			//order.setHeader(orderTbl.get)
			orders.add(order);
		}
		response.setList(orders);
		response.setCount(count);
		return response;
	}

	private LabFacilityTbl getFacilityByEmailId(String email) {
		javax.persistence.Query query = em.createQuery(Query.GET_FACILITY_BY_EMAILID);
		query.setParameter("param1", email);
		return executeUniqueQuery(LabFacilityTbl.class, query);
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
		return order;
	}

}
