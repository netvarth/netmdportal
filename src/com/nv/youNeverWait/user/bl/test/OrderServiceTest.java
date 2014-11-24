/**
 * OrderServiceTest.java
 * @author netvarth
 *
 * Version 1.0 Sep 25, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LabSyncDTO;
import com.nv.youNeverWait.rs.dto.OrderCountFilterDto;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
import com.nv.youNeverWait.rs.dto.OrderTypeDTO;
import com.nv.youNeverWait.user.bl.service.LabService;
import com.nv.youNeverWait.user.bl.service.OrderService;
import com.nv.youNeverWait.user.bl.service.SyncService;

/**
 * 
 * 
 * @author Luciya Jose
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })
public class OrderServiceTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void orderTransferSourceLabIdWrong() {
		OrderService service = (OrderService) applicationContext
				.getBean("order.service");
		OrderTransfer orderTranferDto = new OrderTransfer();
		orderTranferDto.setSourceLabId(1933);
		orderTranferDto.setSourceLabBranchId(84);
		orderTranferDto.setDestinationLabId(12);
		List<Integer> destinationBranches = new ArrayList<Integer>();

		Integer brnch1 = new Integer(170);
		destinationBranches.add(brnch1);
		Integer brnch2 = new Integer(171);
		destinationBranches.add(brnch2);
		Integer brnch3 = new Integer(172);
		destinationBranches.add(brnch3);
		Integer brnch4 = new Integer(173);
		destinationBranches.add(brnch4);

		orderTranferDto.setDestinationBranches(destinationBranches);
		orderTranferDto.setOrderDetails("orderUid");
		try {

			service.transferOrder(orderTranferDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void orderTransferWrongSourceBranchId() {
		OrderService service = (OrderService) applicationContext
				.getBean("order.service");
		OrderTransfer orderTranferDto = new OrderTransfer();
		orderTranferDto.setSourceLabId(12);
		orderTranferDto.setSourceLabBranchId(1256);
		orderTranferDto.setDestinationLabId(12);
		List<Integer> destinationBranches = new ArrayList<Integer>();

		Integer brnch1 = new Integer(170);
		destinationBranches.add(brnch1);
		Integer brnch2 = new Integer(171);
		destinationBranches.add(brnch2);
		Integer brnch3 = new Integer(172);
		destinationBranches.add(brnch3);
		Integer brnch4 = new Integer(173);
		destinationBranches.add(brnch4);
		
		orderTranferDto.setDestinationBranches(destinationBranches);
		orderTranferDto.setOrderDetails("orderUid");
		try {

			service.transferOrder(orderTranferDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void orderTransferwrongDestinationLabId() {
		OrderService service = (OrderService) applicationContext
				.getBean("order.service");
		OrderTransfer orderTranferDto = new OrderTransfer();
		orderTranferDto.setSourceLabId(12);
		orderTranferDto.setSourceLabBranchId(84);
		orderTranferDto.setDestinationLabId(1933);
		orderTranferDto.setOrderUid("JV112");
		List<Integer> destinationBranches = new ArrayList<Integer>();

		Integer brnch1 = new Integer(170);
		destinationBranches.add(brnch1);
		Integer brnch2 = new Integer(171);
		destinationBranches.add(brnch2);
		Integer brnch3 = new Integer(172);
		destinationBranches.add(brnch3);
		Integer brnch4 = new Integer(173);
		destinationBranches.add(brnch4);

		orderTranferDto.setDestinationBranches(destinationBranches);
		orderTranferDto.setOrderDetails("orderUid");
		try {

			service.transferOrder(orderTranferDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void orderTransferWrongDestinationBranchId() {
		OrderService service = (OrderService) applicationContext
				.getBean("order.service");
		OrderTransfer orderTranferDto = new OrderTransfer();
		orderTranferDto.setSourceLabId(193);
		orderTranferDto.setSourceLabBranchId(123);
		orderTranferDto.setDestinationLabId(193);
		orderTranferDto.setOrderUid("JV112");
		List<Integer> destinationBranches = new ArrayList<Integer>();

		Integer brnch1 = new Integer(140);
		destinationBranches.add(brnch1);
		Integer brnch2 = new Integer(141);
		destinationBranches.add(brnch2);
		Integer brnch3 = new Integer(142);
		destinationBranches.add(brnch3);
		Integer brnch4 = new Integer(143);
		destinationBranches.add(brnch4);

		orderTranferDto.setDestinationBranches(destinationBranches);
		orderTranferDto.setOrderDetails("orderUid");
		try {

			service.transferOrder(orderTranferDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void orderTransferOrderDetailsNull() {
		OrderService service = (OrderService) applicationContext
				.getBean("order.service");
		OrderTransfer orderTranferDto = new OrderTransfer();
		orderTranferDto.setSourceLabId(12);
		orderTranferDto.setSourceLabBranchId(84);
		orderTranferDto.setDestinationLabId(12);
		orderTranferDto.setOrderUid("JV112");
		orderTranferDto.setOrderDetails(null);/* its appeares as orderdetails="null"*/
		List<Integer> destinationBranches = new ArrayList<Integer>();

		Integer brnch1 = new Integer(170);
		destinationBranches.add(brnch1);
		Integer brnch2 = new Integer(171);
		destinationBranches.add(brnch2);
		Integer brnch3 = new Integer(172);
		destinationBranches.add(brnch3);
		Integer brnch4 = new Integer(173);
		destinationBranches.add(brnch4);
		orderTranferDto.setDestinationBranches(destinationBranches);
		orderTranferDto.setOrderDetails(null);
		try {

			service.transferOrder(orderTranferDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void orderTransferSuccess() {
		OrderService service = (OrderService) applicationContext
				.getBean("order.service");
		OrderTransfer orderTranferDto = new OrderTransfer();
		orderTranferDto.setSourceLabId(12);
		orderTranferDto.setSourceLabBranchId(84);
		orderTranferDto.setDestinationLabId(12);
		orderTranferDto.setOrderUid("SU007");
		List<Integer> destinationBranches = new ArrayList<Integer>();

		Integer brnch1 = new Integer(170);
		destinationBranches.add(brnch1);
		Integer brnch2 = new Integer(171);
		destinationBranches.add(brnch2);
		Integer brnch3 = new Integer(172);
		destinationBranches.add(brnch3);
		Integer brnch4 = new Integer(173);
		destinationBranches.add(brnch4);

		orderTranferDto.setDestinationBranches(destinationBranches);
		orderTranferDto.setOrderDetails("fsdfs");
		try {

			service.transferOrder(orderTranferDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void orderTransferUniqueIdGiven() {
		OrderService service = (OrderService) applicationContext
				.getBean("order.service");
		OrderTransfer orderTranferDto = new OrderTransfer();
		orderTranferDto.setSourceLabId(193);
		orderTranferDto.setSourceLabBranchId(123);
		orderTranferDto.setDestinationLabId(193);
		List<Integer> destinationBranches = new ArrayList<Integer>();

		Integer brnch1 = new Integer(170);
		destinationBranches.add(brnch1);
		Integer brnch2 = new Integer(171);
		destinationBranches.add(brnch2);
		Integer brnch3 = new Integer(172);
		destinationBranches.add(brnch3);
		Integer brnch4 = new Integer(173);
		destinationBranches.add(brnch4);

		orderTranferDto.setDestinationBranches(destinationBranches);
		orderTranferDto.setOrderUid("JV001");
		orderTranferDto.setOrderDetails("orderUidThird");
		try {

			service.transferOrder(orderTranferDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void setOrderTypeSuccess() {
		OrderService service = (OrderService) applicationContext
				.getBean("order.service");
		OrderTypeDTO orderTypeDto = new OrderTypeDTO();
		orderTypeDto.setLabId(193);
		orderTypeDto
				.setOrderTypeCodes("{AgentOrder:A,BlanketOrder:B,WalkIn Order:W}");
		try {

			service.setOrderType(orderTypeDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void setOrderTypeWrong() {
		OrderService service = (OrderService) applicationContext
				.getBean("order.service");
		OrderTypeDTO orderTypeDto = new OrderTypeDTO();
		orderTypeDto.setLabId(193);
		orderTypeDto.setOrderTypeCodes(null);
		try {

			service.setOrderType(orderTypeDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void setOrderTypeWrongLabId() {
		OrderService service = (OrderService) applicationContext
				.getBean("order.service");
		OrderTypeDTO orderTypeDto = new OrderTypeDTO();
		orderTypeDto.setLabId(563);
		orderTypeDto.setOrderTypeCodes("AgentOrder");
		try {

			service.setOrderType(orderTypeDto);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void netLimsRetrieveOrders() {
		System.out
				.println("########## retrieve Calling for Netlims... ################ ");
		OrderService service = (OrderService) applicationContext
				.getBean("order.service");

		HeaderDTO header = new HeaderDTO();
		header.setHeadOfficeId(12);
		header.setBranchId(171);
		header.setPassPhrase("7vPcivMcr/hoVrzGSpe8kw==");
		header.setMacId("00-1C-C0-5A-AA-7B");
		String lastSyncTime="2014-01-20 10:05:37";
		Date currentSyncTime=new Date();
		
		try {
			service.retrieveBranchOrders(header, lastSyncTime, currentSyncTime);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void getOrderCount(){
		OrderService service = (OrderService) applicationContext
				.getBean("order.service");
		OrderCountFilterDto ocf = new OrderCountFilterDto();
		ocf.setBranch(340);
		ocf.setFromDate("02-1-2014");
		ocf.setToDate("30-4-2014");
		service.getOrderCountByBranchId(ocf);
		
	}
	@Test
	public void getOrderCountofLab(){
		OrderService service = (OrderService) applicationContext
				.getBean("order.service");
		OrderCountFilterDto ocf = new OrderCountFilterDto();
		ocf.setBranch(285);
		ocf.setFromDate("29-09-2013");
		ocf.setToDate("30-09-2014");
		service.getOrderCountFromLab(ocf);
		
	}
	
	

}
