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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
import com.nv.youNeverWait.rs.dto.OrderTypeDTO;
import com.nv.youNeverWait.user.bl.service.LabService;
import com.nv.youNeverWait.user.bl.service.OrderManager;

/**
 *
 *
 * @author Luciya Jose
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations={"file:resource/context.xml", "file:resource/testDataSource.xml" , "file:resource/youNeverWait-context.xml"})
public class OrderServiceTest {

	@Autowired
	private ApplicationContext applicationContext;	


	@Test
	public void orderTransferSourceLabIdWrong(){
		OrderManager service =(OrderManager) applicationContext.getBean("order.manager");
		OrderTransfer orderTranferDto= new OrderTransfer();
		orderTranferDto.setSourceLabId(1933);
		orderTranferDto.setSourceLabBranchId(123);
		orderTranferDto.setDestinationLabId(193);
		orderTranferDto.setDestinationLabBranchId(123);
		orderTranferDto.setOrderDetails("orderUid");
		try{
		
			service.transferOrder(orderTranferDto);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void orderTransferWrongSourceBranchId(){
		OrderManager service =(OrderManager) applicationContext.getBean("order.manager");
		OrderTransfer orderTranferDto= new OrderTransfer();
		orderTranferDto.setSourceLabId(193);
		orderTranferDto.setSourceLabBranchId(1256);
		orderTranferDto.setDestinationLabId(193);
		orderTranferDto.setDestinationLabBranchId(123);
		orderTranferDto.setOrderDetails("orderUid");
		try{
		
			service.transferOrder(orderTranferDto);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void orderTransferwrongDestinationLabId(){
		OrderManager service =(OrderManager) applicationContext.getBean("order.manager");
		OrderTransfer orderTranferDto= new OrderTransfer();
		orderTranferDto.setSourceLabId(193);
		orderTranferDto.setSourceLabBranchId(123);
		orderTranferDto.setDestinationLabId(1933);
		orderTranferDto.setDestinationLabBranchId(123);
		orderTranferDto.setOrderDetails("orderUid");
		try{
		
			service.transferOrder(orderTranferDto);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void orderTransferWrongDestinationBranchId(){
		OrderManager service =(OrderManager) applicationContext.getBean("order.manager");
		OrderTransfer orderTranferDto= new OrderTransfer();
		orderTranferDto.setSourceLabId(193);
		orderTranferDto.setSourceLabBranchId(123);
		orderTranferDto.setDestinationLabId(193);
		orderTranferDto.setDestinationLabBranchId(12);
		orderTranferDto.setOrderDetails("orderUid");
		try{
		
			service.transferOrder(orderTranferDto);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void orderTransferOrderDetailsNull(){
		OrderManager service =(OrderManager) applicationContext.getBean("order.manager");
		OrderTransfer orderTranferDto= new OrderTransfer();
		orderTranferDto.setSourceLabId(193);
		orderTranferDto.setSourceLabBranchId(123);
		orderTranferDto.setDestinationLabId(193);
		orderTranferDto.setDestinationLabBranchId(123);
		orderTranferDto.setOrderDetails(null);
		try{
		
			service.transferOrder(orderTranferDto);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void orderTransferSuccess(){
		OrderManager service =(OrderManager) applicationContext.getBean("order.manager");
		OrderTransfer orderTranferDto= new OrderTransfer();
		orderTranferDto.setSourceLabId(193);
		orderTranferDto.setSourceLabBranchId(123);
		orderTranferDto.setDestinationLabId(193);
		orderTranferDto.setDestinationLabBranchId(123);
		orderTranferDto.setOrderDetails("orderUid");
		try{
		
			service.transferOrder(orderTranferDto);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void orderTransferUniqueIdGiven(){
		OrderManager service =(OrderManager) applicationContext.getBean("order.manager");
		OrderTransfer orderTranferDto= new OrderTransfer();
		orderTranferDto.setSourceLabId(193);
		orderTranferDto.setSourceLabBranchId(123);
		orderTranferDto.setDestinationLabId(193);
		orderTranferDto.setDestinationLabBranchId(123);
		orderTranferDto.setUniqueId(1);
		orderTranferDto.setOrderDetails("orderUidThird");
		try{
		
			service.transferOrder(orderTranferDto);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void setOrderTypeSuccess(){
		OrderManager service =(OrderManager) applicationContext.getBean("order.manager");
		OrderTypeDTO orderTypeDto= new OrderTypeDTO();
		orderTypeDto.setLabId(193);
		orderTypeDto.setOrderTypeCodes("{AgentOrder:A,BlanketOrder:B,WalkIn Order:W}");
		try{
		
			service.setOrderType(orderTypeDto);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void setOrderTypeWrong(){
		OrderManager service =(OrderManager) applicationContext.getBean("order.manager");
		OrderTypeDTO orderTypeDto= new OrderTypeDTO();
		orderTypeDto.setLabId(193);
		orderTypeDto.setOrderTypeCodes(null);
		try{
		
			service.setOrderType(orderTypeDto);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void setOrderTypeWrongLabId(){
		OrderManager service =(OrderManager) applicationContext.getBean("order.manager");
		OrderTypeDTO orderTypeDto= new OrderTypeDTO();
		orderTypeDto.setLabId(563);
		orderTypeDto.setOrderTypeCodes("AgentOrder");
		try{
		
			service.setOrderType(orderTypeDto);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	
}
