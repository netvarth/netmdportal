package com.nv.youNeverWait.user.bl.service;

import com.nv.youNeverWait.rs.dto.FacilitySyncDTO;
import com.nv.youNeverWait.rs.dto.SyncResponse;

public interface FacilityService {

	public SyncResponse createFacility(FacilitySyncDTO facility);
	public int processFacility(FacilitySyncDTO facility);
}
