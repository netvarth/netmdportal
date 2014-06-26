package com.nv.youNeverWait.user.bl.impl;

import com.nv.youNeverWait.rs.dto.FacilitySyncDTO;
import com.nv.youNeverWait.rs.dto.SyncResponse;
import com.nv.youNeverWait.user.bl.service.FacilityService;
import com.nv.youNeverWait.user.bl.validation.FacilityValidator;
import com.nv.youNeverWait.user.pl.dao.FacilityDao;

public class FacilityServiceImpl implements FacilityService{
	private FacilityDao facilityDao;
	private FacilityValidator facilityValidator;

	@Override
	public int processFacility(FacilitySyncDTO facility) {
		
		return facilityDao.processFacility(facility);
		
	}

	@Override
	public SyncResponse createFacility(FacilitySyncDTO facility) {
		
		return null;
	}

}
