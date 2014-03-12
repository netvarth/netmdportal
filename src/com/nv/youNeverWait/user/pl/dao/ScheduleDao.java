/**
 * ScheduleDao.java
 *
 * Mar 12, 2013
 *
 * @author Luciya
 */
package com.nv.youNeverWait.user.pl.dao;

import java.util.Date;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.RetrievalScheduleResponseDTO;
import com.nv.youNeverWait.rs.dto.ScheduleDetail;
import com.nv.youNeverWait.rs.dto.ScheduleResponseDTO;
import com.nv.youNeverWait.rs.dto.ScheduleViewResponseDTO;
import com.nv.youNeverWait.rs.dto.ViewScheduleListDTO;


public interface ScheduleDao {
	public ScheduleResponseDTO create(HeaderDTO header, ScheduleDetail scheduleDetail);
	public ScheduleResponseDTO update(HeaderDTO header, ScheduleDetail scheduleDetail);
	public ScheduleViewResponseDTO view(int globalId);
	public ScheduleResponseDTO delete(int globalId);
	public ViewScheduleListDTO dayView(int netMdBranchId,int doctorId, String date) ;
	public boolean checkSchedule(String date, String fromTime, String toTime, int doctorId);
	public RetrievalScheduleResponseDTO retrieveScheduleList(String lastSyncTime,String passPhrase, int netmdBranchId,Date currentSyncTime);
}
