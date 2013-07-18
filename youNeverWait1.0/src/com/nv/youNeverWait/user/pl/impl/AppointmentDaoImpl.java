package com.nv.youNeverWait.user.pl.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.DoctorScheduleTbl;
import com.nv.youNeverWait.pl.entity.DoctorTbl;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.NetmdBranchTbl;
import com.nv.youNeverWait.pl.entity.NetmdPassphraseTbl;
import com.nv.youNeverWait.pl.entity.PatientAppointmentTbl;
import com.nv.youNeverWait.pl.entity.PatientTbl;
import com.nv.youNeverWait.pl.entity.ScheduleStatusEnum;
import com.nv.youNeverWait.pl.entity.StatusEnum;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentDTO;
import com.nv.youNeverWait.rs.dto.AppointmentResponse;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalAppointmentResponseDTO;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.AppointmentDao;

public class AppointmentDaoImpl extends GenericDaoHibernateImpl implements
		AppointmentDao {
	@PersistenceContext()
	private EntityManager em;
	private DateFormat df = new SimpleDateFormat(
			Constants.NEWDATE_FORMAT_FOR_APPOINTMENT);

	/**
	 * Retrieve appointment
	 * 
	 * @param syncTime
	 * @param passPhrase
	 * @param netMdBranchId
	 */
	@Override
	@Transactional
	public RetrievalAppointmentResponseDTO retrieveAppointmentForNetMd(
			String syncTime, String passPhrase, int netMdBranchId,
			Date currentSyncTime) {
		RetrievalAppointmentResponseDTO retrieveAppointmentObj = new RetrievalAppointmentResponseDTO();
		List<AppointmentDTO> createdAppointments = new ArrayList<AppointmentDTO>();
		Date lastSyncTime = null;
		DateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		try {
			lastSyncTime = df.parse(syncTime);

		} catch (ParseException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}
		int netmdPassphraseId = getNetmdPassphrase(passPhrase, netMdBranchId);
		if (netmdPassphraseId == 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<PatientAppointmentTbl> appointmentList = retrieveAppointments(
				lastSyncTime, netMdBranchId, currentSyncTime, netmdPassphraseId);
		List<PatientAppointmentTbl> appointmentListPortal = retrieveAppointmentsCreatedInPortal(
				lastSyncTime, netMdBranchId, currentSyncTime);
		appointmentList.addAll(appointmentListPortal);
		for (PatientAppointmentTbl appointmentTbl : appointmentList) {
			AppointmentDTO appointmentDto = new AppointmentDTO(
					appointmentTbl.getId(), appointmentTbl.getId(),
					appointmentTbl.getPatientTbl().getId(), appointmentTbl
							.getDoctorTbl().getId(), appointmentTbl
							.getDoctorScheduleTbl().getId(),
					appointmentTbl.getDate(), appointmentTbl.getStartingTime(),
					appointmentTbl.getCreateDateTime(),
					appointmentTbl.getUpdateDateTime(), appointmentTbl
							.getPatientTbl().getFirstName(),
					appointmentTbl.getAppointmentStatus(),
					appointmentTbl.getStatus());
			createdAppointments.add(appointmentDto);

		}
		retrieveAppointmentObj.setRetrieveAppointments(createdAppointments);
		retrieveAppointmentObj.setSuccess(true);
		return retrieveAppointmentObj;
	}

	/**
	 * 
	 * @param lastSyncTime
	 * @param netmdBranchId
	 * @param currentSyncTime
	 * @return
	 */
	private List<PatientAppointmentTbl> retrieveAppointments(Date lastSyncTime,
			int netmdBranchId, Date currentSyncTime, int netmdPassphraseId) {
		javax.persistence.Query query = em
				.createQuery(Query.RETRIEVE_APPOINTMENTS);
		query.setParameter("param1", lastSyncTime);
		query.setParameter("param2", currentSyncTime);
		query.setParameter("param3", netmdBranchId);
		query.setParameter("param4", netmdPassphraseId);
		return executeQuery(PatientAppointmentTbl.class, query);

	}
	/**
	 * 
	 * @param lastSyncTime
	 * @param netmdBranchId
	 * @param currentSyncTime
	 * @return
	 */
	private List<PatientAppointmentTbl> retrieveAppointmentsCreatedInPortal(Date lastSyncTime,
			int netmdBranchId, Date currentSyncTime) {
		javax.persistence.Query query = em
				.createQuery(Query.RETRIEVE_APPOINTMENTS_CREATED_IN_PORTAL);
		query.setParameter("param1", lastSyncTime);
		query.setParameter("param2", currentSyncTime);
		query.setParameter("param3", netmdBranchId);
		
		return executeQuery(PatientAppointmentTbl.class, query);

	}

	/**
	 * Method for getting netmd passphrase id
	 * 
	 * @param passPhrase
	 * @param netmdBranchId
	 * @return netmd passphrase id
	 */
	public Integer getNetmdPassphrase(String passPhrase, int netmdBranchId) {
		int count = 0;
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_PASSPHRASE_ID);
		query.setParameter("param1", passPhrase);
		query.setParameter("param2", netmdBranchId);
		count = (Integer) query.getSingleResult();
		return count;
	}

	/**
	 * Get confirmed appointment list for a patient for the current day
	 * 
	 * @param patientId
	 * @return List<PatientAppointmentTbl>
	 */
	@Override
	public List<PatientAppointmentTbl> getAppointmentsForTheDay(String patientId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_APPOINTMENTS_OF_CURRENTDAY);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date newDate = null;
		try {
			newDate = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query.setParameter("param1", Integer.parseInt(patientId));
		query.setParameter("param2", newDate);
		return (List<PatientAppointmentTbl>) executeQuery(
				PatientAppointmentTbl.class, query);
	}

	/**
	 * get confirmed appointment list for a patient for the next one week from
	 * the current day.
	 */
	@Override
	public List<PatientAppointmentTbl> getAppointmentsForTheWeek(
			String patientId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_APPOINTMENTS_OF_THE_WEEK);
		query.setParameter("param1", Integer.parseInt(patientId));
		query.setParameter("param2", getCurrentDate());
		Date date = new Date();
		long DAY_IN_MS = 1000 * 60 * 60 * 24;
		try {
			query.setParameter(
					"param3",
					df.parse(df.format(new Date(date.getTime()
							+ (7 * DAY_IN_MS)))));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (List<PatientAppointmentTbl>) executeQuery(
				PatientAppointmentTbl.class, query);
	}

	/**
	 * get future appointment list for a patient
	 */
	@Override
	public List<PatientAppointmentTbl> getFutureAppointments(String patientId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_FUTURE_APPOINTMENTS);
		query.setParameter("param1", Integer.parseInt(patientId));
		query.setParameter("param2", new Date());

		return (List<PatientAppointmentTbl>) executeQuery(
				PatientAppointmentTbl.class, query);
	}

	/**
	 * get past appointments
	 * 
	 * @return
	 */
	@Override
	public List<PatientAppointmentTbl> getPastAppointments(String patientId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_PAST_APPOINTMENTS);
		query.setParameter("param1", Integer.parseInt(patientId));
		query.setParameter("param2", getCurrentDate());

		return (List<PatientAppointmentTbl>) executeQuery(
				PatientAppointmentTbl.class, query);
	}

	private Date getCurrentDate() {

		Date newDate = null;
		try {
			newDate = df.parse(df.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newDate;
	}

	/**
	 * create an appointment
	 * 
	 * @param Appointment
	 * @return AppointmentResponse
	 */
	@Override
	@Transactional(readOnly = false)
	public AppointmentResponse createAppointment(Appointment appointment) {

		AppointmentResponse response = new AppointmentResponse();
		DoctorScheduleTbl schedule = new DoctorScheduleTbl();
		int test = 0;
		PatientAppointmentTbl appointmentTbl = new PatientAppointmentTbl();
		// If the request have macid and passphrase check if they match with
		// table entry to check if it's a request from a valid netmd
		if (appointment.getHeader().getMacId() != null
				&& appointment.getHeader().getPassPhrase() != null
				&& !appointment.getHeader().getMacId().isEmpty()
				&& !appointment.getHeader().getPassPhrase().isEmpty()) {

			NetmdPassphraseTbl passPhrase = getByPassphrase(appointment
					.getHeader().getPassPhrase());
			if (passPhrase == null
					|| !passPhrase.getMacId().equals(
							appointment.getHeader().getMacId())
					|| passPhrase.getNetmdBranchTbl().getId() != appointment
							.getHeader().getNetMdBranchId()
					|| passPhrase.getNetmdBranchTbl().getNetmdTbl().getId() != appointment
							.getHeader().getNetMdId()) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidNetMdAccount);
				se.setDisplayErrMsg(true);
				throw se;
			} else {
				appointmentTbl.setNetmdPassphraseTbl(passPhrase);
			}
		} else {// if macid and passphrase are null in a request then consider
				// the request is from portal
			appointmentTbl.setNetmdPassphraseTbl(null);
		}
		PatientTbl patientTbl = (PatientTbl) getById(PatientTbl.class,
				appointment.getAppointmentDetails().getPatientId());
		if (patientTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(appointment.getAppointmentDetails()
							.getPatientId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		appointmentTbl.setPatientTbl(patientTbl);
		DoctorTbl doctorTbl = (DoctorTbl) getById(DoctorTbl.class, appointment
				.getAppointmentDetails().getDoctorId());
		if (doctorTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID,
					Integer.toString(appointment.getAppointmentDetails()
							.getDoctorId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		appointmentTbl.setDoctorTbl(doctorTbl);
		DoctorScheduleTbl ScheduleTbl = (DoctorScheduleTbl) getById(
				DoctorScheduleTbl.class, appointment.getAppointmentDetails()
						.getScheduleId());
		if (ScheduleTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.NoScheduleFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(appointment.getAppointmentDetails()
							.getScheduleId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		appointmentTbl.setDoctorScheduleTbl(ScheduleTbl);
		SimpleDateFormat df1 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		DateFormat df3 = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		Date startsDate;
		Date startTime;

		try {
			startsDate = df.parse(appointment.getAppointmentDetails()
					.getStartDate());
			startTime = df1.parse(appointment.getAppointmentDetails()
					.getStartTime());
		} catch (ParseException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}

		List<DoctorScheduleTbl> doctorScheduleList = (ArrayList<DoctorScheduleTbl>) getScheduleList(
				appointment.getAppointmentDetails().getDoctorId(), startsDate);
		if (doctorScheduleList.isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.ScheduleNotExist);
			se.setDisplayErrMsg(true);
			throw se;
		}
		Date dbStartTime = new Date();
		SimpleDateFormat s = new SimpleDateFormat(Constants.TIMEWITHFORMAT);

		for (DoctorScheduleTbl doctorScheduleTbl : doctorScheduleList) {
			String d = s.format(doctorScheduleTbl.getStartingTime());
			try {
				dbStartTime = s.parse(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (!startTime.before(dbStartTime)) {
				if (!startTime.after(doctorScheduleTbl.getEndingTime())) {
					if (doctorScheduleTbl.getScheduleStatus() != null
							&& !doctorScheduleTbl.getScheduleStatus().equals(
									ScheduleStatusEnum.VACATION
											.getDisplayName())) {
						test++;
						schedule = doctorScheduleTbl;
					}
				}
			}
		}
		if (test == 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSchedule);
			se.setDisplayErrMsg(true);
			throw se;
		}
		PatientAppointmentTbl patientAppointmentTbl = (PatientAppointmentTbl) getAppointment(
				appointment.getAppointmentDetails().getDoctorId(), startsDate,
				startTime);
		// if the appointment already exists
		if (patientAppointmentTbl != null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.AppointmentAlreadyExist);
			se.setDisplayErrMsg(true);
			throw se;

		}

		NetmdBranchTbl netmdbranchTbl = (NetmdBranchTbl) getById(
				NetmdBranchTbl.class, appointment.getHeader()
						.getNetMdBranchId());
		appointmentTbl.setNetmdBranchTbl(netmdbranchTbl);
		appointmentTbl.setDate(startsDate);
		appointmentTbl.setStartingTime(startTime);
		appointmentTbl.setDoctorScheduleTbl(schedule);
		appointmentTbl.setAppointmentStatus("Confirmed");
		appointmentTbl.setStatus("active");
		Date newDate = new Date();
		appointmentTbl.setCreateDateTime(newDate);
		appointmentTbl.setUpdateDateTime(newDate);
		save(appointmentTbl);
		response.setStartTime(df1.format(appointmentTbl.getStartingTime()));
		response.setError(null);
		response.setSuccess(true);
		response.setId(appointment.getAppointmentDetails().getId());
		response.setGlobalId(appointmentTbl.getId());
		response.setCreateDateTime(df3.format(newDate));
		response.setUpdateDateTime(df3.format(newDate));
		return response;

	}

	/**
	 * update appointment
	 * 
	 * @param Appointment
	 * @return AppointmentResponse
	 */
	@Override
	@Transactional(readOnly = false)
	public AppointmentResponse update(Appointment appointment) {
		AppointmentResponse response = new AppointmentResponse();
		DateFormat df1 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		PatientAppointmentTbl patientAppointmentTbl;
		if (appointment.getHeader().getMacId() != null
				&& appointment.getHeader().getPassPhrase() != null
				&& !appointment.getHeader().getMacId().isEmpty()
				&& !appointment.getHeader().getPassPhrase().isEmpty()) {
			patientAppointmentTbl = (PatientAppointmentTbl) getById(
					PatientAppointmentTbl.class, appointment
							.getAppointmentDetails().getGlobalId());
			if (patientAppointmentTbl == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidAppointment);
				se.setDisplayErrMsg(true);
				throw se;
			}
			NetmdPassphraseTbl passPhrase = getByPassphrase(appointment
					.getHeader().getPassPhrase());
			if (passPhrase == null
					|| !passPhrase.getMacId().equals(
							appointment.getHeader().getMacId())
					|| passPhrase.getNetmdBranchTbl().getId() != appointment
							.getHeader().getNetMdBranchId()
					|| passPhrase.getNetmdBranchTbl().getNetmdTbl().getId() != appointment
							.getHeader().getNetMdId()) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidNetMdAccount);
				se.setDisplayErrMsg(true);
				throw se;
			}
		} else {
			patientAppointmentTbl = (PatientAppointmentTbl) getById(
					PatientAppointmentTbl.class, appointment
							.getAppointmentDetails().getId());

			if (patientAppointmentTbl == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidAppointment);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		PatientTbl patientTbl = (PatientTbl) getById(PatientTbl.class,
				appointment.getAppointmentDetails().getPatientId());
		if (patientTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(appointment.getAppointmentDetails()
							.getPatientId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		patientAppointmentTbl.setPatientTbl(patientTbl);
		try {
			patientAppointmentTbl.setDate(df.parse(appointment
					.getAppointmentDetails().getStartDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			patientAppointmentTbl.setStartingTime(df1.parse(appointment
					.getAppointmentDetails().getStartTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		patientAppointmentTbl.setUpdateDateTime(new Date());
		update(patientAppointmentTbl);
		response.setStartTime(df1.format(patientAppointmentTbl
				.getStartingTime()));
		response.setError(null);
		response.setSuccess(true);
		response.setId(appointment.getAppointmentDetails().getId());
		response.setGlobalId(patientAppointmentTbl.getId());
		SimpleDateFormat s = new SimpleDateFormat();
		response.setUpdateDateTime(s.format(patientAppointmentTbl
				.getUpdateDateTime()));
		return response;
	}

	/**
	 * get list of schedules
	 * 
	 * @param doctorId
	 * @param startDate
	 * @return List<DoctorScheduleTbl>
	 */
	private List<DoctorScheduleTbl> getScheduleList(int doctorId, Date startDate) {
		javax.persistence.Query query = em.createQuery(Query.GET_SCHEDULE);
		query.setParameter("param1", doctorId);
		query.setParameter("param2", startDate);
		return executeQuery(DoctorScheduleTbl.class, query);
	}

	/**
	 * get netmdpassphrase table id
	 * 
	 * @param passphrase
	 * @return List<DoctorScheduleTbl>
	 */
	private NetmdPassphraseTbl getPassphraseTbl(String passphrase) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_PASSPHRASE);
		query.setParameter("param1", passphrase);
		return executeUniqueQuery(NetmdPassphraseTbl.class, query);
	}

	/**
	 * get appointment
	 * 
	 * @param doctorId
	 * @param startDate
	 * @param startTime
	 * @return PatientAppointmentTbl
	 */
	public PatientAppointmentTbl getAppointment(int doctorId, Date startDate,
			Date startTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_APPOINTMENT);
		query.setParameter("param1", doctorId);
		query.setParameter("param2", startDate);
		query.setParameter("param3", startTime);
		return executeUniqueQuery(PatientAppointmentTbl.class, query);
	}

	/**
	 * delete appointment
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public AppointmentResponse deleteAppointment(int id) {
		AppointmentResponse response = new AppointmentResponse();
		PatientAppointmentTbl appointmentTbl = (PatientAppointmentTbl) getById(
				PatientAppointmentTbl.class, id);
		if (appointmentTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.NoAppointmentFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		appointmentTbl.setStatus(StatusEnum.InActive.getDisplayName());
		appointmentTbl.setUpdateDateTime(new Date());
		update(appointmentTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(id);
		response.setGlobalId(appointmentTbl.getId());
		return response;
	}

	/**
	 * Retrieve NetmdPassphraseTbl record by giving passPhrase
	 * 
	 * @param passPhrase
	 * @return NetmdPassphraseTbl
	 */
	private NetmdPassphraseTbl getByPassphrase(String passPhrase) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_BRANCH_PASSPHRASE);
		query.setParameter("param1", passPhrase);
		return executeUniqueQuery(NetmdPassphraseTbl.class, query);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
