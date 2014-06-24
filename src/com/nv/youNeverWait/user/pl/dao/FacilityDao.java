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

	public int processFacility(FacilitySyncDTO facility);
	
}
