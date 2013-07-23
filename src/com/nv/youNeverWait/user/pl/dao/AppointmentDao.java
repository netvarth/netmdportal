/**
 * AppointmentDao.java
 */
package com.nv.youNeverWait.user.pl.dao;

import java.util.Date;
import java.util.List;
import com.nv.youNeverWait.pl.entity.PatientAppointmentTbl;
import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentResponse;
import com.nv.youNeverWait.rs.dto.RetrievalAppointmentResponseDTO;


public interface AppointmentDao {

public List<PatientAppointmentTbl> getAppointmentsForTheDay(String patientId);
public List<PatientAppointmentTbl> getAppointmentsForTheWeek(String patientId);
public List<PatientAppointmentTbl> getFutureAppointments(String patientId);
public List<PatientAppointmentTbl> getPastAppointments(String patientId);
public RetrievalAppointmentResponseDTO retrieveAppointmentForSecondary(String lastSyncTime, String passPhrase, int netMdBranchId, Date currentSyncTime);
/**
 * @param id
 * @return
 */
public AppointmentResponse deleteAppointmentFromPortal(int id);
/**
 * @param id
 * @return
 */
AppointmentResponse deleteAppointmentFromNetMd(int id);

/**
 * @param appointment
 * @return
 */
AppointmentResponse createAppointmentFromPortal(Appointment appointment);
/**
 * @param appointment
 * @return
 */
AppointmentResponse createAppointmentFromNetMd(Appointment appointment);

/**
 * @param appointment
 * @return
 */
AppointmentResponse updateAppointmentFromPortal(Appointment appointment);
/**
 * @param appointment
 * @return
 */
AppointmentResponse updateAppointmentFromNetMd(Appointment appointment);
/**
 * @param lastSyncTime
 * @param passPhrase
 * @param netMdBranchId
 * @param currentSyncTime
 * @return
 */
public RetrievalAppointmentResponseDTO retrieveAppointmentForPrimary(
		String lastSyncTime, String passPhrase, int netMdBranchId,
		Date currentSyncTime);

}
