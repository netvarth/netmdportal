/**
 * 
 */
package com.nv.youNeverWait.user.pl.dao;

import com.nv.youNeverWait.rs.dto.FacilitySyncDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;

/**
 * @author Mani E.V
 *
 */
public interface FacilityDao {

	/**
	 * @param facility
	 * @param branchId 
	 * @return  facilityId
	 */
	public int create(FacilitySyncDTO facility, int branchId);

	/**
	 * @param facility
	 * @param branchId
	 * @return facilityId
	 */
	public int update(FacilitySyncDTO facility, Integer branchId);

	/**
	 * @param login
	 * @param facilityId
	 * @return LoginDTO
	 */
	public LoginDTO setLoginInfo(LoginDTO login, int facilityId);

	/**
	 * @param branchId 
	 * @return branchName
	 */
	public String getFacilityBranch(int branchId);

	/**
	 * @param facility
	 * @return 
	 */
	public int validateFacility(FacilitySyncDTO facility);

	/**
	 * Mani E.V	
	 * @param globalId
	 * @return
	 */
	public LoginDTO getLoginInfo(Integer globalId);

	/**
	 * Mani E.V	
	 * @param login
	 * @return
	 */
	public ResponseDTO resetPassword(LoginDTO login);

	
}
