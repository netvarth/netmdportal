/**
 * OrderResultBundle.java
 * @author Mani E.V 
 *
 * Version 1.0 06-Jun-2014
 *
 * Copyright (c) 2014 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Mani E.V
 */
public class OrderResultBundle {
	private Integer source_branch_id;
	List<OrderResultSyncDTO> orderResults = new ArrayList<OrderResultSyncDTO>();
	/**
	 * @return the source_branch_id
	 */
	public Integer getSource_branch_id() {
		return source_branch_id;
	}
	/**
	 * @param source_branch_id the source_branch_id to set
	 */
	public void setSource_branch_id(Integer source_branch_id) {
		this.source_branch_id = source_branch_id;
	}
	/**
	 * @return the orderResults
	 */
	public List<OrderResultSyncDTO> getOrderResults() {
		return orderResults;
	}
	/**
	 * @param orderResults the orderResults to set
	 */
	public void setOrderResults(List<OrderResultSyncDTO> orderResults) {
		this.orderResults = orderResults;
	}
	
}
