/**
 * ScheduleService.java
 *
 * Mar 12, 2013
 *
 * @author Luciya
 */
package com.nv.youNeverWait.user.bl.service;

import java.util.Date;

import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.RetrievalScheduleResponseDTO;
import com.nv.youNeverWait.rs.dto.ScheduleDetail;
import com.nv.youNeverWait.rs.dto.ScheduleListDTO;
import com.nv.youNeverWait.rs.dto.ScheduleResponseDTO;
import com.nv.youNeverWait.rs.dto.ScheduleViewResponseDTO;
import com.nv.youNeverWait.rs.dto.ViewScheduleListDTO;


public interface ScheduleService {
	public ScheduleResponseDTO create(HeaderDTO header, ScheduleDetail scheduleDetail);
	public ScheduleResponseDTO update(HeaderDTO header, ScheduleDetail scheduleDetail);
	public ScheduleViewResponseDTO view(int globalId);
	public ScheduleResponseDTO delete(int globalId) ;
	public ViewScheduleListDTO dayView(int netMdBranchId, int doctorId, String date);
	public ScheduleListDTO weeklyView(int netMdBranchId, int doctorId, String date);
	public ScheduleListDTO monthlyView(int netMdBranchId, int doctorId, String startDate, String endDate);
	public RetrievalScheduleResponseDTO retrieveScheduleList(String lastSyncTime,String passPhrase, int netmdBranchId,Date currentSyncTime);
}
