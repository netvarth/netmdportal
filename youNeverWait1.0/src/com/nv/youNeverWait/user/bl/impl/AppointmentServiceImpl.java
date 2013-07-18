package com.nv.youNeverWait.user.bl.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.nv.youNeverWait.pl.entity.PatientAppointmentTbl;
import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentDTO;
import com.nv.youNeverWait.rs.dto.AppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.AppointmentResponse;
import com.nv.youNeverWait.rs.dto.AppointmentsDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.PastAppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalAppointmentResponseDTO;
import com.nv.youNeverWait.user.bl.service.AppointmentGroup;
import com.nv.youNeverWait.user.bl.service.AppointmentService;
import com.nv.youNeverWait.user.bl.validation.AppointmentValidator;
import com.nv.youNeverWait.user.pl.dao.AppointmentDao;

/**
 * Appointment services
 * 
 * @author nv
 * 
 */
public class AppointmentServiceImpl implements AppointmentService {

	private AppointmentGroup appointmentToday;
	private AppointmentGroup appointmentForCurrentWeek;
	private AppointmentGroup futureAppointments;
	private AppointmentDao appointmentDao;
	private AppointmentValidator appointmentValidator;
	
	/**
	 * @param lastSyncTime
	 * @param passPhrase
	 * @param netMdBranchId
	 * @return retrieveAppointmentObj
	 */
	@Override
	@Transactional
	public RetrievalAppointmentResponseDTO retrieveAppointmentForNetMd(String lastSyncTime, String passPhrase, int netMdBranchId, Date currentSyncTime){
		RetrievalAppointmentResponseDTO retrieveAppointmentObj= new RetrievalAppointmentResponseDTO();
		retrieveAppointmentObj = appointmentDao.retrieveAppointmentForNetMd(lastSyncTime, passPhrase, netMdBranchId, currentSyncTime);
		return retrieveAppointmentObj;
	}
	
	/**
	 * List appointment groups such as current day's, Next week's and future
	 * 
	 * @param patientId
	 * @return response
	 */
	@Override
	@Transactional
	public AppointmentListResponseDTO getAppointmentListsForPatient(
			String patientId) {
		AppointmentListResponseDTO response = new AppointmentListResponseDTO();
		List<AppointmentGroup> appointmentGroupList = new ArrayList<AppointmentGroup>(
				3);

		appointmentGroupList.add(appointmentToday);
		appointmentGroupList.add(appointmentForCurrentWeek);
		appointmentGroupList.add(futureAppointments);

		String groupName;
		for (AppointmentGroup appointment : appointmentGroupList) {
			groupName = appointment.getName();
			if (groupName.equals("AppointmentListOfToday")) {
				response.setTodaysAppointment(appointment
						.getAppointmentList(patientId));
			} else if (groupName.equals("AppointmentListOfCurrentWeek")) {
				response.setCurrentWeeksAppointment(appointment
						.getAppointmentList(patientId));
			} else if (groupName.equals("AppointmentListOfFuture")) {
				response.setFutureAppointment(appointment
						.getAppointmentList(patientId));
			}

		}

		response.setSuccess(true);
		return response;

	}

	/**
	 * List past appointments
	 * 
	 * @param patientId
	 * @return response
	 */
	@Override
	public PastAppointmentListResponseDTO getPastAppointmentList(
			String patientId) {
		PastAppointmentListResponseDTO response = new PastAppointmentListResponseDTO();
		List<PatientAppointmentTbl> patientAppointmentTblList = appointmentDao
				.getPastAppointments(patientId);// returns list of patient
												// appointment objects
		response = getPastAppointmentDTOList(patientAppointmentTblList);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Past appointment list
	 * 
	 * @param patientAppointmentTblList
	 * @return response
	 */
	private PastAppointmentListResponseDTO getPastAppointmentDTOList(
			List<PatientAppointmentTbl> patientAppointmentTblList) {
		PastAppointmentListResponseDTO response = new PastAppointmentListResponseDTO();
		List<AppointmentsDTO> appointmentDTOList = new ArrayList<AppointmentsDTO>();
		if (patientAppointmentTblList == null) {
			return response;
		}
		for (PatientAppointmentTbl patientAppointmentTbl : patientAppointmentTblList) {
			appointmentDTOList.add(new AppointmentsDTO(patientAppointmentTbl));
		}
		response.setPastAppointments(appointmentDTOList);
		return response;
	}

	/**
	 * create appointment
	 * 
	 * @param appointment
	 * @return response
	 */
	@Override
	public AppointmentResponse createAppointment(Appointment appointment) {
		AppointmentResponse response = new AppointmentResponse();
		ErrorDTO error = appointmentValidator
				.validateCreateAppointment(appointment);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response = appointmentDao.createAppointment(appointment);
		return response;
	}

	/**
	 * Update appointment
	 * 
	 * @param appointment
	 * @return response
	 */
	@Override
	public AppointmentResponse updateAppointment(Appointment appointment) {
		AppointmentResponse response = new AppointmentResponse();
		ErrorDTO error = appointmentValidator
				.validateCreateAppointment(appointment);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response = appointmentDao.update(appointment);
		return response;
	}

	/**
	 * delete appointment
	 * 
	 * @param id
	 * @return response
	 */
	@Override
	public AppointmentResponse deleteAppointment(int id) {
		AppointmentResponse response = new AppointmentResponse();
		response = appointmentDao.deleteAppointment(id);
		return response;

	}

	public AppointmentGroup getAppointmentToday() {
		return appointmentToday;
	}

	public void setAppointmentToday(AppointmentGroup appointmentToday) {
		this.appointmentToday = appointmentToday;
	}

	public AppointmentGroup getAppointmentForCurrentWeek() {
		return appointmentForCurrentWeek;
	}

	public void setAppointmentForCurrentWeek(
			AppointmentGroup appointmentForCurrentWeek) {
		this.appointmentForCurrentWeek = appointmentForCurrentWeek;
	}

	public AppointmentGroup getFutureAppointments() {
		return futureAppointments;
	}

	public void setFutureAppointments(AppointmentGroup futureAppointments) {
		this.futureAppointments = futureAppointments;
	}

	public AppointmentDao getAppointmentDao() {
		return appointmentDao;
	}

	public void setAppointmentDao(AppointmentDao appointmentDao) {
		this.appointmentDao = appointmentDao;
	}

	public AppointmentValidator getAppointmentValidator() {
		return appointmentValidator;
	}

	public void setAppointmentValidator(
			AppointmentValidator appointmentValidator) {
		this.appointmentValidator = appointmentValidator;
	}

}
