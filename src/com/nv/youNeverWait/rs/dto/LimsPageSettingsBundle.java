/**
 * LimsPageSettingsBundle.java
 * @author Mani E.V
 *
 * Version 1.0 08-Jan-2015
 *
 * Copyright (c) 2015 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

import java.util.List;

/**
 *
 *
 * @author Mani E.V
 */
public class LimsPageSettingsBundle {
	private List<PageLayoutSettings> pageSettings;
	private int source_branch_id;
	/**
	 * @return the pageSettings
	 */
	public List<PageLayoutSettings> getPageSettings() {
		return pageSettings;
	}
	/**
	 * @param pageSettings the pageSettings to set
	 */
	public void setPageSettings(List<PageLayoutSettings> pageSettings) {
		this.pageSettings = pageSettings;
	}
	/**
	 * @return the source_branch_id
	 */
	public int getSource_branch_id() {
		return source_branch_id;
	}
	/**
	 * @param source_branch_id the source_branch_id to set
	 */
	public void setSource_branch_id(int source_branch_id) {
		this.source_branch_id = source_branch_id;
	}
	
}
