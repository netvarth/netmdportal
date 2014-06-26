package com.nv.youNeverWait.user.bl.service;

import com.nv.youNeverWait.rs.dto.FacilitySyncDTO;
import com.nv.youNeverWait.rs.dto.SyncResponse;

/**
 * @author Mani E.V
 *
 */
public interface FacilityService {
	/**
	 * @param facility
	 * @param branchId 
	 * @return globalId
	 */
	public int processFacility(FacilitySyncDTO facility, Integer branchId);
	/**
	 * @param facility
	 * @param branchId 
	 * @return facilityId
	 */
	public int create(FacilitySyncDTO facility, Integer branchId);
	
}
