package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Fairborn
 */
public class LimsFacilityBundle {

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


}
