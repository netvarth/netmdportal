package com.nv.youNeverWait.user.bl.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.nv.youNeverWait.pl.entity.PatientAppointmentTbl;
import com.nv.youNeverWait.rs.dto.AppointmentsDTO;
import com.nv.youNeverWait.user.bl.service.AppointmentGroup;
import com.nv.youNeverWait.user.pl.dao.AppointmentDao;

public class AppointmentListOfCurrentWeek implements AppointmentGroup {
	private AppointmentDao appointmentDao;
	
	
	/**
	 * get name of the appointment type
	 */
	@Override
	public String getName() {
		
		return "AppointmentListOfCurrentWeek";
	}

	/**
	 * get appointment list
	 */
	@Override
	public List<AppointmentsDTO> getAppointmentList(String patientId) {
		

		List<PatientAppointmentTbl> patientAppointmentTblList = appointmentDao
				.getAppointmentsForTheWeek(patientId);
		return getAppointmentList(patientAppointmentTblList);

	}

	private List<AppointmentsDTO> getAppointmentList(
			List<PatientAppointmentTbl> patientAppointmentTblList) {
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
