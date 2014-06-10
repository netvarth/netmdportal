/**
 * OrderValidator.java
 * @author netvarth
 *
 * Version 1.0 Sep 25, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.validation;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
import com.nv.youNeverWait.rs.dto.OrderTypeDTO;
import com.nv.youNeverWait.rs.dto.Parameter;

/**
 *
 *
 * @author Luciya Jose
 */
public class OrderValidator {
	/**
	 * @param orderTranfer
	 */
	public void validateOrderTransferDetails(OrderTransfer orderTranfer) {
		if (orderTranfer.getSourceLabId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSourceLab);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(orderTranfer.getSourceLabId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (orderTranfer.getSourceLabBranchId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSourceBranch);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(orderTranfer.getSourceLabBranchId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
//		if (orderTranfer.getDestinationLabId() <= 0) {
//			ServiceException se = new ServiceException(
//					ErrorCodeEnum.InvalidDestinationLab);
//			se.addParam(new Parameter(Constants.ID, Integer
//					.toString(orderTranfer.getDestinationLabId())));
//			se.setDisplayErrMsg(true);
//			throw se;
//		}
		for (Integer destinationBranch : orderTranfer.getDestinationBranches()) {
			if (destinationBranch <= 0) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDestinationBranch);
				se.addParam(new Parameter(Constants.ID, Integer
						.toString(destinationBranch)));
				se.setDisplayErrMsg(true);
				throw se;
			}
			if (orderTranfer.getSourceLabBranchId() == destinationBranch) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.SourceDestinationBranchSame);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if (orderTranfer.getOrderDetails() == null || orderTranfer.getOrderDetails().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.OrderDetailsNull);
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	/**
	 * @param orderTypeDetails
	 */
	public void validateOrderTypeDetails(OrderTypeDTO orderTypeDetails) {
		if(orderTypeDetails.getLabId()<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidLabId);
			se.addParam(new Parameter(Constants.ID, Integer.toString(orderTypeDetails.getLabId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(orderTypeDetails.getOrderTypeCodes()==null || orderTypeDetails.getOrderTypeCodes().equals("")){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.OrderTypeNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
	}

}
