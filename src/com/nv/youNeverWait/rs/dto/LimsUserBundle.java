/**
 * UserResultBundle.java
 * @author Mani E.V 
 *
 * Version 1.0 01-Jul-2014
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
public class LimsUserBundle {
	private Integer source_branch_id;
	List<SyncUser> users = new ArrayList<SyncUser>();
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
	 * @return the users
	 */
	public List<SyncUser> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(List<SyncUser> users) {
		this.users = users;
	}
}
