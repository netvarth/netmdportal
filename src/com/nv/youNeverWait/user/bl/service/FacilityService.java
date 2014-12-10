package com.nv.youNeverWait.user.bl.service;

import com.nv.youNeverWait.rs.dto.FacilitySyncDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;

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
	public String getFacilityBranchName(Integer source_branch_id);
	/**
	 * Mani E.V	
	 * @param login
	 * @return
	 */
	public ResponseDTO resetPassword(LoginDTO login);
	
}
