/**
 * ScheduleServiceImpl.java
 *
 * Mar 12, 2013
 *
 * @author Luciya
 */
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
import com.nv.youNeverWait.pl.entity.NetmdUserTbl;
import com.nv.youNeverWait.pl.entity.OccuranceTypeEnum;
import com.nv.youNeverWait.pl.entity.PatientAppointmentTbl;
import com.nv.youNeverWait.pl.entity.RepeatEnum;
import com.nv.youNeverWait.pl.entity.RepeatTypeEnum;
import com.nv.youNeverWait.pl.entity.ScheduleStatusEnum;
import com.nv.youNeverWait.pl.entity.SeriesTbl;
import com.nv.youNeverWait.pl.entity.StatusEnum;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.AppointmentDetailsDTO;
import com.nv.youNeverWait.rs.dto.DoctorDetail;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDetail;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalScheduleResponseDTO;
import com.nv.youNeverWait.rs.dto.ScheduleDetail;
import com.nv.youNeverWait.rs.dto.ScheduleResponseDTO;
import com.nv.youNeverWait.rs.dto.ScheduleViewResponseDTO;
import com.nv.youNeverWait.rs.dto.SeriesDTO;
import com.nv.youNeverWait.rs.dto.ViewScheduleDTO;
import com.nv.youNeverWait.rs.dto.ViewScheduleListDTO;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.ScheduleDao;

public class ScheduleDaoImpl extends GenericDaoHibernateImpl implements
		ScheduleDao {

	@PersistenceContext()
	private EntityManager em;

	/**
	 * Method to create schedule
	 * 
	 * @param schedule
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ScheduleResponseDTO create(HeaderDTO header,
			ScheduleDetail scheduleDetail) {

		ScheduleResponseDTO response = new ScheduleResponseDTO();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		SimpleDateFormat df = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		DoctorScheduleTbl doctorSchedule = new DoctorScheduleTbl();
		NetmdPassphraseTbl passPhrase = null;
		SeriesTbl existingSeries = null;
		// Validate header details
		if (header.getMacId() != null && header.getPassPhrase() != null
				&& !header.getMacId().isEmpty()
				&& !header.getPassPhrase().isEmpty()) {

			passPhrase = getByPassphrase(header.getPassPhrase());
			if (passPhrase == null
					|| passPhrase.getMacId() == null
					|| !passPhrase.getMacId().equals(header.getMacId())
					|| passPhrase.getNetmdBranchTbl().getId() != header
							.getNetMdBranchId()
					|| passPhrase.getNetmdBranchTbl().getNetmdTbl().getId() != header
							.getNetMdId()) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidNetMdAccount);
				se.setDisplayErrMsg(true);
				throw se;
			} else {

				doctorSchedule.setNetmdPassphraseTbl(passPhrase);
				doctorSchedule
						.setNetmdBranchTbl(passPhrase.getNetmdBranchTbl());
				/* Checking whether schedule series already saved */
				existingSeries = getExistingSeries(scheduleDetail.getSeries()
						.getSeriesId(), passPhrase.getId());
				if (existingSeries == null) {
					/* Saving schedule series details in series tbl */
					SeriesTbl newSeries = new SeriesTbl();
					Date seriesEndDate = null;
					if (scheduleDetail.getSeries().getEndDate() != null) {
						try {
							seriesEndDate = sdf.parse(scheduleDetail
									.getSeries().getEndDate());
						} catch (ParseException e) {
							ServiceException se = new ServiceException(
									ErrorCodeEnum.InvalidEndDate);
							se.setDisplayErrMsg(true);
							throw se;

						}
					}

					OccuranceTypeEnum occuranceType = OccuranceTypeEnum
							.getEnum(scheduleDetail.getSeries()
									.getOccuranceType());
					RepeatTypeEnum repeatType = RepeatTypeEnum
							.getEnum(scheduleDetail.getSeries().getRepeat());
					newSeries.setScheduleRepeat(repeatType.getDisplayName());
					newSeries.setEndDate(seriesEndDate);
					newSeries.setEndsOn(scheduleDetail.getSeries().getEndsOn());
					newSeries.setOccuranceType(occuranceType.getDisplayName());
					newSeries.setWeeklyType(scheduleDetail.getSeries()
							.getWeeklyType());
					newSeries.setSeriesId(scheduleDetail.getSeries()
							.getSeriesId());
					newSeries.setNetmdPassphraseTbl(passPhrase);

					save(newSeries);
					response.setSeriesGlobalId(newSeries.getId());
					response.setSeriesId(newSeries.getSeriesId());
					doctorSchedule.setSeriesTbl(newSeries);

				}
			}
		}
		// While creating schedule from portal
		else {
			NetmdBranchTbl netmdBranch = getById(NetmdBranchTbl.class,
					header.getNetMdBranchId());
			if (netmdBranch == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidNetMdAccount);
				se.setDisplayErrMsg(true);
				throw se;
			} else if (netmdBranch.getStatus().equals(
					StatusEnum.InActive.getDisplayName())) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InActiveNetmdAccount);
				se.setDisplayErrMsg(true);
				throw se;
			}
			doctorSchedule.setNetmdBranchTbl(netmdBranch);
		}

		/* Checking doctor details */
		DoctorTbl doctor = getById(DoctorTbl.class,
				scheduleDetail.getDoctorGlobalId());
		if (doctor == null) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(scheduleDetail.getDoctorGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}

		/* Saving doctor schedule details */
		ScheduleStatusEnum scheduleStatus = ScheduleStatusEnum
				.getEnum(scheduleDetail.getScheduleStatus());
		doctorSchedule.setScheduleStatus(scheduleStatus.getDisplayName());

		doctorSchedule.setDoctorTbl(doctor);

		Date startTime = null;
		Date endTime = null;
		Date startDate = null;

		try {
			startTime = df.parse(scheduleDetail.getStartTime());
			endTime = df.parse(scheduleDetail.getEndTime());
			startDate = sdf.parse(scheduleDetail.getStartDate());
		} catch (ParseException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;

		}
		Date createdDateTime = new Date();
		doctorSchedule.setStatus(StatusEnum.Active.getDisplayName());
		doctorSchedule.setDate(startDate);
		doctorSchedule.setStartingTime(startTime);
		doctorSchedule.setEndingTime(endTime);
		doctorSchedule.setCreateDateTime(createdDateTime);
		doctorSchedule.setUpdateDateTime(createdDateTime);
		if (existingSeries != null) {
			doctorSchedule.setSeriesTbl(existingSeries);
			response.setSeriesGlobalId(existingSeries.getId());
			response.setSeriesId(existingSeries.getSeriesId());
		}
		save(doctorSchedule);

		response.setGlobalId(doctorSchedule.getId());
		response.setId(scheduleDetail.getId());
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method to update a schedule
	 * 
	 * @param schedule
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ScheduleResponseDTO update(HeaderDTO header,
			ScheduleDetail scheduleDetail) {

		ScheduleResponseDTO response = new ScheduleResponseDTO();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		SimpleDateFormat df = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		// Validate header details
		if (header.getMacId() != null && header.getPassPhrase() != null
				&& !header.getMacId().isEmpty()
				&& !header.getPassPhrase().isEmpty()) {

			NetmdPassphraseTbl passPhrase = getByPassphrase(header
					.getPassPhrase());
			if (passPhrase == null
					|| passPhrase.getMacId() == null
					|| !passPhrase.getMacId().equals(header.getMacId())
					|| passPhrase.getNetmdBranchTbl().getId() != header
							.getNetMdBranchId()
					|| passPhrase.getNetmdBranchTbl().getNetmdTbl().getId() != header
							.getNetMdId()) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidNetMdAccount);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		// While updating schedule from portal
		else {
			NetmdBranchTbl netmdBranch = getById(NetmdBranchTbl.class,
					header.getNetMdBranchId());
			if (netmdBranch == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidNetMdAccount);
				se.setDisplayErrMsg(true);
				throw se;
			} else if (netmdBranch.getStatus().equals(
					StatusEnum.InActive.getDisplayName())) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InActiveNetmdAccount);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		/* Checking schedule globalId existing or not */
		DoctorScheduleTbl doctorSchedule = getById(DoctorScheduleTbl.class,
				scheduleDetail.getScheduleGlobalId());

		if (doctorSchedule == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidGlobalId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* Checking Series id existing or not */
		if (scheduleDetail.getSeries() != null) {

			SeriesTbl seriesTbl = getById(SeriesTbl.class, doctorSchedule
					.getSeriesTbl().getId());
			if (seriesTbl == null) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidSeriesId);

				se.setDisplayErrMsg(true);
				throw se;

			}
			/* Updating series tbl */
			Date seriesEndDate = null;
			if (scheduleDetail.getSeries().getEndDate() != null) {
				try {
					seriesEndDate = sdf.parse(scheduleDetail.getSeries()
							.getEndDate());
				} catch (ParseException e) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.InvalidEndDate);
					se.setDisplayErrMsg(true);
					throw se;
				}
			}

			OccuranceTypeEnum occuranceType = OccuranceTypeEnum
					.getEnum(scheduleDetail.getSeries().getOccuranceType());
			RepeatTypeEnum repeatType = RepeatTypeEnum.getEnum(scheduleDetail
					.getSeries().getRepeat());
			seriesTbl.setScheduleRepeat(repeatType.getDisplayName());
			seriesTbl.setEndDate(seriesEndDate);
			seriesTbl.setEndsOn(scheduleDetail.getSeries().getEndsOn());
			seriesTbl.setOccuranceType(occuranceType.getDisplayName());
			seriesTbl.setWeeklyType(scheduleDetail.getSeries().getWeeklyType());
			update(seriesTbl);
			response.setSeriesGlobalId(seriesTbl.getId());
			response.setSeriesId(seriesTbl.getSeriesId());
		}
		Date startTime = null;
		Date endTime = null;
		Date startDate = null;

		try {
			startTime = df.parse(scheduleDetail.getStartTime());
			endTime = df.parse(scheduleDetail.getEndTime());
			startDate = sdf.parse(scheduleDetail.getStartDate());
		} catch (ParseException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}

		ScheduleStatusEnum scheduleStatus = ScheduleStatusEnum
				.getEnum(scheduleDetail.getScheduleStatus());
		doctorSchedule.setDate(startDate);
		doctorSchedule.setStartingTime(startTime);
		doctorSchedule.setEndingTime(endTime);
		doctorSchedule.setScheduleStatus(scheduleStatus.getDisplayName());
		doctorSchedule.setUpdateDateTime(new Date());
		update(doctorSchedule);
		response.setGlobalId(doctorSchedule.getId());
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method to view schedule details
	 * 
	 * @param scheduleId
	 * @return ScheduleViewResponseDTO
	 */
	@Override
	@Transactional
	public ScheduleViewResponseDTO view(int scheduleId) {
		ScheduleViewResponseDTO response = new ScheduleViewResponseDTO();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		SimpleDateFormat df = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		DoctorScheduleTbl doctorSchedule = getById(DoctorScheduleTbl.class,
				scheduleId);
		if (doctorSchedule == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		ScheduleDetail scheduleDetail = new ScheduleDetail();
		scheduleDetail.setStartDate(df.format(doctorSchedule.getDate()));
		scheduleDetail.setEndTime(df.format(doctorSchedule.getEndingTime()));
		scheduleDetail.setScheduleStatus(doctorSchedule.getScheduleStatus());
		scheduleDetail.setStatus(doctorSchedule.getStatus());

		DoctorTbl doctorTbl = getById(DoctorTbl.class, doctorSchedule
				.getDoctorTbl().getId());
		if (doctorTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(doctorSchedule.getDoctorTbl().getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		DoctorDetail doctor = new DoctorDetail();
		doctor.setFirstName(doctorTbl.getFirstName());
		doctor.setLastName(doctorTbl.getLastName());
		doctor.setAddress(doctorTbl.getAddress());
		doctor.setDesignation(doctorTbl.getDesignation());
		doctor.setMobile(doctorTbl.getMobile());
		doctor.setPhone(doctorTbl.getPhone());
		doctor.setWorkHistory(doctorTbl.getWorkHistory());
		doctor.setWorkingPlaces(doctorTbl.getWorkingPlaces());
		doctor.setGender(doctorTbl.getGender());
		doctor.setEmail(doctorTbl.getEmail());
		doctor.setDateOfBirth(doctorTbl.getDateOfBirth());
		doctor.setGlobalId(doctorTbl.getId());

		SeriesTbl seriesTbl = getById(SeriesTbl.class, doctorSchedule
				.getSeriesTbl().getId());
		if (seriesTbl == null) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSeriesId);

			se.setDisplayErrMsg(true);
			throw se;

		}
		SeriesDTO series = new SeriesDTO();
		if (seriesTbl.getEndDate() != null)
			series.setEndDate(sdf.format(seriesTbl.getEndDate()));
		series.setEndsOn(seriesTbl.getEndsOn());
		series.setOccuranceType(seriesTbl.getOccuranceType());
		series.setWeeklyType(seriesTbl.getWeeklyType());
		series.setRepeat(seriesTbl.getScheduleRepeat());
		series.setSeriesId(seriesTbl.getId());
		scheduleDetail.setSeries(series);

		response.setScheduleDetail(scheduleDetail);
		response.setDoctor(doctor);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method to delete schedule
	 * 
	 * @param scheduleId
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ScheduleResponseDTO delete(int globalId) {
		ScheduleResponseDTO response = new ScheduleResponseDTO();
		DoctorScheduleTbl doctorSchedule = getById(DoctorScheduleTbl.class,
				globalId);
		if (doctorSchedule == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidGlobalId);
			se.setDisplayErrMsg(true);
			throw se;
		}

		doctorSchedule.setStatus(StatusEnum.InActive.getDisplayName());
		doctorSchedule.setUpdateDateTime(new Date());
		update(doctorSchedule);
		response.setSuccess(true);
		response.setGlobalId(globalId);
		response.setSeriesGlobalId(doctorSchedule.getSeriesTbl().getId());
		response.setSeriesId(doctorSchedule.getSeriesTbl().getSeriesId());
		return response;
	}

	/**
	 * check whether a schedule is already there in the db
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean checkSchedule(String date, String fromtime, String totime,
			int doctorId) {
		Date fromTime, toTime, newDate;
		int test = 0;

		SimpleDateFormat df1 = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		SimpleDateFormat df2 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		try {
			newDate = df1.parse(date);
			fromTime = df2.parse(fromtime);
			toTime = df2.parse(totime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<DoctorScheduleTbl> scheduleList = (ArrayList<DoctorScheduleTbl>) getDoctorScheduleByDoctorDate(
				newDate, doctorId);

		if (scheduleList.isEmpty()) {
			return true;
		} else {
			for (DoctorScheduleTbl doctorScheduleTbl : scheduleList) {
				if (!fromTime.before(doctorScheduleTbl.getStartingTime())) {
					if (!fromTime.after(doctorScheduleTbl.getEndingTime())) {
						test++;
					}
				}
				if (!toTime.before(doctorScheduleTbl.getEndingTime())) {
					if (!toTime.after(doctorScheduleTbl.getEndingTime())) {
						test++;
					}
				}
				// if(fromTime.before(doctorScheduleTbl.getStartingTime())&&toTime.before(doctorScheduleTbl.getEndingTime())){
				// test++;
				// }
				// if(fromTime.after(doctorScheduleTbl.getEndingTime())){
				// test++;
				// }

			}
			if (test != 0) {
				return false;
			}

		}
		return true;
	}

	/**
	 * get day view
	 * 
	 * @param date
	 * @return ViewScheduleListDTO
	 */

	@Override
	@Transactional(readOnly = false)
	public ViewScheduleListDTO dayView(int netMdBranchId, int doctorId,
			String date) {
		Date newDate;

		ViewScheduleListDTO response = new ViewScheduleListDTO();
		String s = "";
		ArrayList<ViewScheduleDTO> viewScheduleList = new ArrayList<ViewScheduleDTO>();
		List<AppointmentDetailsDTO> appointmentDetailsDTOList = new ArrayList<AppointmentDetailsDTO>();
		SimpleDateFormat df = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		SimpleDateFormat df1 = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		try {
			newDate = df1.parse(date);
		} catch (Exception e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<DoctorScheduleTbl> scheduleList = (List<DoctorScheduleTbl>) getDoctorScheduleByDate(
				netMdBranchId, doctorId, newDate);
		System.out.println("scheduleList--->" + scheduleList.size());
		if (!scheduleList.isEmpty()) {
			for (DoctorScheduleTbl doctorScheduleTbl : scheduleList) {
				ViewScheduleDTO viewScheduleDTO = new ViewScheduleDTO();

				List<AppointmentDetailsDTO> tempAppointmentDetailsDTOList = new ArrayList<AppointmentDetailsDTO>();
				viewScheduleDTO.setId(doctorScheduleTbl.getId());
				if (doctorScheduleTbl.getDoctorTbl() != null)
					viewScheduleDTO.setDoctorId(doctorScheduleTbl
							.getDoctorTbl().getId());
				if (doctorScheduleTbl.getSeriesTbl() != null) {
					viewScheduleDTO.setSeriesId(doctorScheduleTbl
							.getSeriesTbl().getId());
					SeriesTbl seriesTbl = getById(SeriesTbl.class,
							doctorScheduleTbl.getSeriesTbl().getId());
					if (seriesTbl == null) {
						ServiceException se = new ServiceException(
								ErrorCodeEnum.SeriesNotExist);
						se.setDisplayErrMsg(true);
						throw se;
					}
					if (seriesTbl.getEndDate() != null) {
						viewScheduleDTO.setEndDate(df1.format(seriesTbl
								.getEndDate()));
					}
					if (seriesTbl.getOccuranceType() != null) {
						viewScheduleDTO.setOccuranceType(seriesTbl
								.getOccuranceType());
					}
					viewScheduleDTO.setRepeat(seriesTbl.getScheduleRepeat());
					if (seriesTbl.getScheduleRepeat().equals(
							RepeatEnum.WEEKLY.getDisplayName())) {
						if (seriesTbl.getWeeklyType() != null) {// weekly type
							s = seriesTbl.getWeeklyType();
							s = s.trim();
							String[] items = s.split(",");
							// System.out.println("a value="+items);

							int[] results = new int[items.length];
							for (int i = 0; i < items.length; i++) {
								// giving week days
								results[i] = Integer.parseInt(items[i]);

							}
							viewScheduleDTO.setWeeklySunThruSatList(results);
						}
					}

					if (new Integer(seriesTbl.getEndsOn()) != null)
						viewScheduleDTO.setEndsOn(seriesTbl.getEndsOn());
				}
				viewScheduleDTO.setStartDate(df1.format(doctorScheduleTbl
						.getDate()));
				viewScheduleDTO.setStartTime(df.format(doctorScheduleTbl
						.getStartingTime()));
				viewScheduleDTO.setEndTime(df.format(doctorScheduleTbl
						.getEndingTime()));
				viewScheduleDTO
						.setStatus(doctorScheduleTbl.getScheduleStatus());
				tempAppointmentDetailsDTOList = getAppointmentDetailsDTOList(doctorScheduleTbl
						.getId());
				appointmentDetailsDTOList.addAll(tempAppointmentDetailsDTOList);
				viewScheduleList.add(viewScheduleDTO);
			}
		}
		response.setAppointment(appointmentDetailsDTOList);
		response.setSchedule(viewScheduleList);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * get appointment list for a doctor for the day in a branch
	 * 
	 * @param scheduleId
	 * @return List<AppointmentDetailsDTO>
	 */
	private List<AppointmentDetailsDTO> getAppointmentDetailsDTOList(
			int scheduleId) {
		List<AppointmentDetailsDTO> appointmentDetailsList = new ArrayList<AppointmentDetailsDTO>();
		List<PatientAppointmentTbl> patientAppointmenTblList = new ArrayList<PatientAppointmentTbl>();
		patientAppointmenTblList = getAppointmentList(scheduleId);

		if (!patientAppointmenTblList.isEmpty()) {
			for (PatientAppointmentTbl patientAppointmentObj : patientAppointmenTblList) {
				appointmentDetailsList.add(new AppointmentDetailsDTO(
						patientAppointmentObj));
			}
		}
		return appointmentDetailsList;
	}

	@Override
	@Transactional
	public RetrievalScheduleResponseDTO retrieveScheduleList(
			String lastSyncTime, String passPhrase, int netmdBranchId,
			Date currentSyncTime) {
		RetrievalScheduleResponseDTO response = new RetrievalScheduleResponseDTO();
		List<ScheduleDetail> retrieveSchedules = new ArrayList<ScheduleDetail>();
		Date syncTime = null;
		DateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		try {
			syncTime = df.parse(lastSyncTime);
		} catch (ParseException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}

		int netmdPassphraseId = getNetmdPassphrase(passPhrase, netmdBranchId);
		if (netmdPassphraseId == 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}

		/* Getting users list */
		List<DoctorScheduleTbl> scheduleList = getSchedules(syncTime,
				netmdPassphraseId, netmdBranchId, currentSyncTime);
		for (DoctorScheduleTbl schedule : scheduleList) {
			retrieveSchedules.add(new ScheduleDetail(schedule));
		}
		response.setRetrieveScheduleList(retrieveSchedules);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method for retrieving all created updated and deleted users after last
	 * sync time
	 * 
	 * @param lastSyncTime
	 * @param netmdPassphraseId
	 * @param netmdBranchId
	 * @return NetmdUserTbl
	 */
	private List<DoctorScheduleTbl> getSchedules(Date syncTime,
			int netmdPassphraseId, int netmdBranchId, Date currentSyncTime) {
		javax.persistence.Query query = em
				.createQuery(Query.RETRIEVE_SCHEDULES);
		query.setParameter("param1", syncTime);
		query.setParameter("param2", netmdPassphraseId);
		query.setParameter("param3", netmdBranchId);
		query.setParameter("param4", currentSyncTime);
		return executeQuery(DoctorScheduleTbl.class, query);

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
	 * get appointment list for a doctor for the day in a branch
	 * 
	 * @param scheduleId
	 * @return List<PatientAppointmentTbl>
	 */

	private List<PatientAppointmentTbl> getAppointmentList(int scheduleId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_APPOINTMENTLIST_BY_SCHEDULE);
		query.setParameter("param1", scheduleId);
		return executeQuery(PatientAppointmentTbl.class, query);
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

	/**
	 * get List if of schedule
	 * 
	 * @param newDate
	 * @return List<DoctorScheduleTbl>
	 */
	public List<DoctorScheduleTbl> getDoctorScheduleByDate(int netMdBranchId,
			int doctorId, Date newDate) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_SCHEDULE_BY_DATE_DOC_BRANCH);
		query.setParameter("param1", newDate);
		query.setParameter("param2", netMdBranchId);
		query.setParameter("param3", doctorId);
		return executeQuery(DoctorScheduleTbl.class, query);
	}

	/**
	 * get List if of schedule
	 * 
	 * @param newDate
	 * @return List<DoctorScheduleTbl>
	 */
	public List<DoctorScheduleTbl> getDoctorScheduleByDoctorDate(Date newDate,
			int doctorId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_SCHEDULE_BY_DATE_DOC);
		query.setParameter("param1", newDate);
		query.setParameter("param2", doctorId);
		return executeQuery(DoctorScheduleTbl.class, query);
	}

	/**
	 * Method to get existing schedule series
	 * 
	 * @param localSeriesId
	 * @param passPhraseId
	 * @return SeriesTbl
	 */
	public SeriesTbl getExistingSeries(int localSeriesId, int passPhraseId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_EXISTING_SERIES);
		query.setParameter("param1", localSeriesId);
		query.setParameter("param2", passPhraseId);
		return executeUniqueQuery(SeriesTbl.class, query);

	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em
	 *            the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
