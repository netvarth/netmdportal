/**
 * SyncService.java
 * 
 * @Author Luciya Jose
 *
 * April 05, 2013
 */
package com.nv.NetlimsPortal.bl.service;

import com.nv.NetlimsPortal.rs.dto.LabSyncDTO;
import com.nv.NetlimsPortal.rs.dto.LabSyncResponseDTO;





/**
 * @author Luciya Jose
 * 
 */
public interface SyncService {
	public LabSyncResponseDTO syncNetLimsData(LabSyncDTO sync); 

}
