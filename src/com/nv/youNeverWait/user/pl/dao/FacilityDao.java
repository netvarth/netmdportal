/**
 * 
 */
package com.nv.youNeverWait.user.pl.dao;

import com.nv.youNeverWait.rs.dto.FacilitySyncDTO;

/**
 * @author Mani E.V
 *
 */
public interface FacilityDao {

	/**
	 * @param facility
	 * @return globalId
	 */
	public int processFacility(FacilitySyncDTO facility, int branchId);

	/**
	 * @param facility
	 * @param branchId 
	 * @return  facilityId
	 */
	public int create(FacilitySyncDTO facility, int branchId);

	
}
