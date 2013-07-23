/**
 * AppointmentServiceImpl.java
 */
package com.nv.youNeverWait.user.bl.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.PatientAppointmentTbl;
import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.AppointmentResponse;
import com.nv.youNeverWait.rs.dto.AppointmentsDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.PastAppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalAppointmentResponseDTO;
import com.nv.youNeverWait.user.bl.service.AppointmentGroup;
import com.nv.youNeverWait.user.bl.service.AppointmentService;
import com.nv.youNeverWait.user.bl.validation.AppointmentValidator;
import com.nv.youNeverWait.user.pl.dao.AppointmentDao;
import com.nv.youNeverWait.util.filter.core.Filter;
import com.nv.youNeverWait.util.filter.core.FilterFactory;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;
import com.nv.youNeverWait.util.filter.core.QueryBuilderFactory;

/**
 * Appointment services
 * 
 * @author Luciya Jose
 * 
 */
public class AppointmentServiceImpl implements AppointmentService {

	private AppointmentGroup appointmentToday;
	private AppointmentGroup appointmentForCurrentWeek;
	private AppointmentGroup futureAppointments;
	private AppointmentDao appointmentDao;
	private AppointmentValidator appointmentValidator;
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	
	/**
	 * Retrrieve appointments for  secondary device of a NetMd branch
	 * @param lastSyncTime
	 * @param passPhrase
	 * @param netMdBranchId
	 * @return retrieveAppointmentObj
	 */
	@Override
	@Transactional
	public RetrievalAppointmentResponseDTO retrieveAppointmentForSecondary(String lastSyncTime, String passPhrase, int netMdBranchId, Date currentSyncTime){
		RetrievalAppointmentResponseDTO retrieveAppointmentObj= new RetrievalAppointmentResponseDTO();
		retrieveAppointmentObj = appointmentDao.retrieveAppointmentForSecondary(lastSyncTime, passPhrase, netMdBranchId, currentSyncTime);
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
	 * create appointment from Netmd
	 * 
	 * @param appointment
	 * @return response
	 */
	@Override
	public AppointmentResponse createAppointmentFromNetMd(Appointment appointment) {
		AppointmentResponse response = new AppointmentResponse();
		ErrorDTO error = appointmentValidator
				.validateCreateAppointment(appointment);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response = appointmentDao.createAppointmentFromNetMd(appointment);
		return response;
	}

	/**
	 * create appointment from portal
	 * 
	 * @param appointment
	 * @return response
	 */
	@Override
	public AppointmentResponse createAppointmentFromPortal(Appointment appointment) {
		AppointmentResponse response = new AppointmentResponse();
		ErrorDTO error = appointmentValidator
				.validateCreateAppointment(appointment);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response = appointmentDao.createAppointmentFromPortal(appointment);
		return response;
	}
	
	/**
	 * Update appointment from Netmd
	 * 
	 * @param appointment
	 * @return response
	 */
	@Override
	public AppointmentResponse updateAppointmentFromNetMd(Appointment appointment) {
		AppointmentResponse response = new AppointmentResponse();
		ErrorDTO error = appointmentValidator
				.validateCreateAppointment(appointment);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response = appointmentDao.updateAppointmentFromNetMd(appointment);
		return response;
	}
	/**
	 * Update appointment from portal
	 * 
	 * @param appointment
	 * @return response
	 */
	@Override
	public AppointmentResponse updateAppointmentFromPortal(Appointment appointment) {
		AppointmentResponse response = new AppointmentResponse();
		ErrorDTO error = appointmentValidator
				.validateCreateAppointment(appointment);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response = appointmentDao.updateAppointmentFromPortal(appointment);
		return response;
	}

	/**
	 * delete appointment from Netmd
	 * 
	 * @param id
	 * @return response
	 */
	@Override
	public AppointmentResponse deleteAppointmentFromNetMd(int id) {
		AppointmentResponse response = new AppointmentResponse();
		response = appointmentDao.deleteAppointmentFromNetMd(id);
		return response;

	}

	/**
	 * delete appointment from portal
	 * 
	 * @param id
	 * @return response
	 */
	@Override
	public AppointmentResponse deleteAppointmentFromPortal(int id) {
		AppointmentResponse response = new AppointmentResponse();
		response = appointmentDao.deleteAppointmentFromPortal(id);
		return response;

	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.AppointmentService#retrieveAppointmentForPrimary(java.lang.String, java.lang.String, int, java.util.Date)
	 */
	@Override
	public RetrievalAppointmentResponseDTO retrieveAppointmentForPrimary(
			String lastSyncTime, String passPhrase, int netMdBranchId,
			Date currentSyncTime) {
		RetrievalAppointmentResponseDTO retrieveAppointmentObj= new RetrievalAppointmentResponseDTO();
		retrieveAppointmentObj = appointmentDao.retrieveAppointmentForPrimary(lastSyncTime, passPhrase, netMdBranchId, currentSyncTime);
		return retrieveAppointmentObj;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.AppointmentService#getPastAppointments(com.nv.youNeverWait.rs.dto.FilterDTO)
	 */
	@Override
	public PastAppointmentListResponseDTO getPastAppointments(FilterDTO filterDTO) {
		
		PastAppointmentListResponseDTO response= new PastAppointmentListResponseDTO();
		
		// validate filterDTO to identify invalid expressions and if there is
				// any,return result with appropriate error code
				ErrorDTO error = appointmentValidator.validateAppointmentFilter(filterDTO);
				if (error != null) {
					response.setError(error);
					response.setSuccess(false);
					return response;
				}

				// get queryBuilder for result from builder factory
				QueryBuilder queryBuilder = queryBuilderFactory
						.getQueryBuilder(Constants.APPOINTMENTS);
				if (queryBuilder == null) {
					return response;
				}
				for (ExpressionDTO exp : filterDTO.getExp()) {

					// get filter from filter factory by setting expression name and
					// value to filter
					Filter filter = filterFactory.getFilter(exp);
					queryBuilder.addFilter(filter);
				}
				// build query
				TypedQuery<PatientAppointmentTbl> q = queryBuilder.buildQuery(
						filterDTO.isAsc(), filterDTO.getFrom(), filterDTO.getCount());

				// get count
				Long count = queryBuilder.getCount();

				// execute query
				List<PatientAppointmentTbl> appointmnts = queryBuilder.executeQuery(q);
				response = PastAppointmentList(appointmnts);
				response.setCount(count);
				response.setSuccess(true);
				return response;
	}
	
	/**
	 * To set response with details of appointments
	 * 
	 * @param branches
	 * @return NetMdBranchListResponseDTO
	 */
	private PastAppointmentListResponseDTO PastAppointmentList(
			List<PatientAppointmentTbl> appointments) {
		PastAppointmentListResponseDTO response = new PastAppointmentListResponseDTO();
		SimpleDateFormat df = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		if (appointments == null) {
			return response;
		}
		List<AppointmentsDTO> appointmentsDTO = new ArrayList<AppointmentsDTO>();
		for (PatientAppointmentTbl appointmnt : appointments) {
			
			try {
				Date CurrentDateTime=df.parse(df.format(new Date()));
				if(appointmnt.getStartingTime().before(CurrentDateTime))
					appointmentsDTO.add(new AppointmentsDTO(appointmnt));
			} catch (ParseException e) {
				e.printStackTrace();
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;

			}
			
		}
		response.setPastAppointments(appointmentsDTO);
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

	/**
	 * @return the queryBuilderFactory
	 */
	public QueryBuilderFactory getQueryBuilderFactory() {
		return queryBuilderFactory;
	}

	/**
	 * @param queryBuilderFactory the queryBuilderFactory to set
	 */
	public void setQueryBuilderFactory(QueryBuilderFactory queryBuilderFactory) {
		this.queryBuilderFactory = queryBuilderFactory;
	}

	/**
	 * @return the filterFactory
	 */
	public FilterFactory getFilterFactory() {
		return filterFactory;
	}

	/**
	 * @param filterFactory the filterFactory to set
	 */
	public void setFilterFactory(FilterFactory filterFactory) {
		this.filterFactory = filterFactory;
	}

	

}
