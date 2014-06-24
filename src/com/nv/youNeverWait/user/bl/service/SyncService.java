/**
 * SyncService.java
 * 
 * @Author Luciya Jose
 *
 * April 05, 2013
 */
package com.nv.youNeverWait.user.bl.service;



import java.util.List;

import com.nv.youNeverWait.api.sync.LimsReferralBundle;
import com.nv.youNeverWait.rs.dto.LabSyncDTO;
import com.nv.youNeverWait.rs.dto.LabSyncResponseDTO;
import com.nv.youNeverWait.rs.dto.LimsFacilityBundle;
import com.nv.youNeverWait.rs.dto.OrderResultBundle;
import com.nv.youNeverWait.rs.dto.SyncDTO;
import com.nv.youNeverWait.rs.dto.SyncResponse;
import com.nv.youNeverWait.rs.dto.SyncResponseDTO;

/**
 * @author Luciya Jose
 * 
 */
public interface SyncService {
	/**
	 * @param sync
	 * @return SyncResponseDTO
	 */
	public SyncResponseDTO syncData(SyncDTO sync);
	/**
	 * @param sync
	 * @return LabSyncResponseDTO
	 */
	public LabSyncResponseDTO syncNetLimsData(LabSyncDTO sync);
	/**
	 * @param bundle
	 * @return List<SyncResponse>
	 */
	public List<SyncResponse> processOrderResult(OrderResultBundle bundle); 
	/**
	 * @param bundle
	 * @return List<SyncResponse>
	 */
	public List<SyncResponse> processReferral(LimsReferralBundle bundle);
	
	public List<SyncResponse> processFacility(LimsFacilityBundle bundle);

}
