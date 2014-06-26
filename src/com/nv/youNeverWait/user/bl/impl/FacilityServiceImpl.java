package com.nv.youNeverWait.user.bl.impl;

import com.nv.youNeverWait.rs.dto.FacilitySyncDTO;
import com.nv.youNeverWait.user.bl.service.FacilityService;
import com.nv.youNeverWait.user.pl.dao.FacilityDao;

/**
 * @author Mani E.V
 *
 */
public class FacilityServiceImpl implements FacilityService {
	
	private FacilityDao facilityDao;
	
	@Override
	public int processFacility(FacilitySyncDTO facility, Integer branchId) {
		return facilityDao.processFacility(facility, branchId);
	}

	@Override
	public int create(FacilitySyncDTO facility, Integer branchId) {
		return facilityDao.create(facility, branchId);
	}

	/**
	 * @return the facilityDao
	 */
	public FacilityDao getFacilityDao() {
		return facilityDao;
	}

	/**
	 * @param facilityDao the facilityDao to set
	 */
	public void setFacilityDao(FacilityDao facilityDao) {
		this.facilityDao = facilityDao;
	}

}
