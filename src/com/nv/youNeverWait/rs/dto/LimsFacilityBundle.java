package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Fairborn
 */
public class LimsFacilityBundle {
	private Integer source_branch_id;
	private List<FacilitySyncDTO> facilities=new ArrayList<FacilitySyncDTO>();

	/**
	 * @return the facilities
	 */
	public List<FacilitySyncDTO> getFacilities() {
		return facilities;
	}

	/**
	 * @param facilities the facilities to set
	 */
	public void setFacilities(List<FacilitySyncDTO> facilities) {
		this.facilities = facilities;
	}

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


}
