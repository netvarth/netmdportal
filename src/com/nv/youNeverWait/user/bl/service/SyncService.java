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
import com.nv.youNeverWait.rs.dto.SyncDTO;
import com.nv.youNeverWait.rs.dto.SyncResponse;
import com.nv.youNeverWait.rs.dto.SyncResponseDTO;

/**
 * @author Luciya Jose
 * 
 */
public interface SyncService {
	public SyncResponseDTO syncData(SyncDTO sync);
	public LabSyncResponseDTO syncNetLimsData(LabSyncDTO sync); 
	public List<SyncResponse> processReferral(LimsReferralBundle bundle);

}
