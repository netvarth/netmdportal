package com.nv.youNeverWait.user.pl.dao;

import java.util.Date;
import java.util.List;

import com.nv.youNeverWait.pl.entity.PatientAppointmentTbl;
import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentResponse;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalAppointmentResponseDTO;


public interface AppointmentDao {

public List<PatientAppointmentTbl> getAppointmentsForTheDay(String patientId);
public List<PatientAppointmentTbl> getAppointmentsForTheWeek(String patientId);
public List<PatientAppointmentTbl> getFutureAppointments(String patientId);
public List<PatientAppointmentTbl> getPastAppointments(String patientId);
public AppointmentResponse createAppointment(Appointment appointment);
public AppointmentResponse deleteAppointment(int id);
public AppointmentResponse update(Appointment appointment);
public RetrievalAppointmentResponseDTO retrieveAppointmentForNetMd(String lastSyncTime, String passPhrase, int netMdBranchId, Date currentSyncTime);
}
