/**
 * ListResponse.java
 * @author Mani
 *
 * Version 1.0 Sep 5, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

/**
 *
 *
 * @author Mani
 */
public class ListResponse {
	private Object list;
	private Long count;
	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}
	/**
	 * @return the list
	 */
	public Object getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(Object list) {
		this.list = list;
	}
	
}
