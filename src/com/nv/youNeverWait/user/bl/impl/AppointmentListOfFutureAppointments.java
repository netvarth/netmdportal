/**
 * AppointmentListOfFutureAppointments.java
 */

package com.nv.youNeverWait.user.bl.impl;

/**
 * @author nv
 *
 */

import java.util.ArrayList;
import java.util.List;
import com.nv.youNeverWait.pl.entity.PatientAppointmentTbl;
import com.nv.youNeverWait.rs.dto.AppointmentsDTO;
import com.nv.youNeverWait.user.bl.service.AppointmentGroup;
import com.nv.youNeverWait.user.pl.dao.AppointmentDao;


public class AppointmentListOfFutureAppointments implements AppointmentGroup{
	private AppointmentDao appointmentDao;
	
	/**
	 * 
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "AppointmentListOfFuture";
	}
	@Override
	public List<AppointmentsDTO> getAppointmentList(String patientId) {
		
		List<PatientAppointmentTbl> patientAppointmentTblList = appointmentDao.getFutureAppointments(patientId);
		return getAppointmentList(patientAppointmentTblList);
		 
	}
	private List<AppointmentsDTO> getAppointmentList(List<PatientAppointmentTbl> patientAppointmentTblList) {
		List<AppointmentsDTO> appointmentDTOList = new ArrayList<AppointmentsDTO>();
		for (PatientAppointmentTbl patientAppointmentTbl : patientAppointmentTblList) {
			appointmentDTOList.add(new AppointmentsDTO(patientAppointmentTbl));
		}
		return appointmentDTOList;
	}
	public AppointmentDao getAppointmentDao() {
		return appointmentDao;
	}
	public void setAppointmentDao(AppointmentDao appointmentDao) {
		this.appointmentDao = appointmentDao;
	}
	
}