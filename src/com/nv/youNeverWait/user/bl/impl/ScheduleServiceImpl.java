/**
 * ScheduleServiceImpl.java
 *
 * Mar 12, 2013
 *
 * @author Luciya
 */
package com.nv.youNeverWait.user.bl.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.RetrievalScheduleResponseDTO;
import com.nv.youNeverWait.rs.dto.ScheduleDetail;
import com.nv.youNeverWait.rs.dto.ScheduleListDTO;
import com.nv.youNeverWait.rs.dto.ScheduleResponseDTO;
import com.nv.youNeverWait.rs.dto.ScheduleViewResponseDTO;
import com.nv.youNeverWait.rs.dto.ViewScheduleListDTO;
import com.nv.youNeverWait.user.bl.service.ScheduleService;
import com.nv.youNeverWait.user.bl.validation.ScheduleValidator;
import com.nv.youNeverWait.user.pl.dao.ScheduleDao;

public class ScheduleServiceImpl implements ScheduleService {
	private ScheduleDao scheduleDao;
	private ScheduleValidator validator;
	private static final Log log = LogFactory.getLog(ScheduleServiceImpl.class);

	/**
	 * Method to create schedule
	 * 
	 * @param schedule
	 * @return ResponseDTO
	 */
	@Override
	
	public ScheduleResponseDTO create(HeaderDTO header, ScheduleDetail scheduleDetail) {

		validator.validateScheduleDetails(header, scheduleDetail);
		/* Checking Schedule already exists or not*/
		boolean sche = scheduleDao.checkSchedule(scheduleDetail.getStartDate(),
				scheduleDetail.getStartTime(), scheduleDetail.getEndTime(), scheduleDetail.getDoctorGlobalId());
		if (sche == false) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.ScheduleAlreadyExist);
			se.addParam(new Parameter(Constants.DATE,scheduleDetail.getStartDate()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		ScheduleResponseDTO response = scheduleDao.create(header, scheduleDetail);
		return response;
	}

	/**
	 * Method to update a schedule
	 * 
	 * @param schedule
	 * @return ResponseDTO
	 */
	@Override
	public ScheduleResponseDTO update(HeaderDTO header, ScheduleDetail scheduleDetail) {

		validator.validateScheduleDetails(header, scheduleDetail);
		validator.validateGlobalId(scheduleDetail.getScheduleGlobalId());
		/* Checking Schedule already exists or not*/
		boolean sche = scheduleDao.checkSchedule(scheduleDetail.getStartDate(),
				scheduleDetail.getStartTime(), scheduleDetail.getEndTime(), scheduleDetail.getDoctorGlobalId());
		if (sche == false) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.ScheduleAlreadyExist);
			se.addParam(new Parameter(Constants.DATE,scheduleDetail.getStartDate()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		ScheduleResponseDTO response = scheduleDao.update(header, scheduleDetail);
		return response;
	}

	/**
	 * Method to view schedule details
	 * 
	 * @param scheduleId
	 * @return ScheduleViewResponseDTO
	 */
	@Override
	public ScheduleViewResponseDTO view(int globalId) {
		
		validator.validateGlobalId(globalId);
		ScheduleViewResponseDTO response = scheduleDao.view(globalId);
		return response;
	}

	/**
	 * Method to delete schedule
	 * 
	 * @param scheduleId
	 * @return ResponseDTO
	 */
	@Override
	public ScheduleResponseDTO delete(int globalId) {
		
		validator.validateGlobalId(globalId);
		ScheduleResponseDTO response = scheduleDao.delete(globalId);
		return response;
	}

	/**
	 * current date schedule view
	 * 
	 * @param date
	 * @return ViewScheduleListDTO
	 */
	@Override
	public ViewScheduleListDTO dayView(int netMdBranchId, int doctorId,
			String date) {
		
		ViewScheduleListDTO response = new ViewScheduleListDTO();
		ErrorDTO error = validator.validateDate(date);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		response = scheduleDao.dayView(netMdBranchId, doctorId, date);
		return response;
	}

	/**
	 * single week view
	 * 
	 * @param date
	 * @return ScheduleListDTO
	 */
	@Override
	public ScheduleListDTO weeklyView(int netMdBranchId, int doctorId,
			String date) {
		
		Date fromDate;
		ScheduleListDTO response = new ScheduleListDTO();
		ArrayList<ViewScheduleListDTO> ViewScheduleList = new ArrayList<ViewScheduleListDTO>();
		ViewScheduleListDTO response1 = new ViewScheduleListDTO();
		ErrorDTO error = validator.validateDate(date);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		Calendar c = Calendar.getInstance();
		String start = date;
		// string to date
		fromDate = stringToDate(date);
		c.setTime(fromDate);
		int day_of_week = c.get(Calendar.DAY_OF_WEEK);
		// get start date
		c.add(Calendar.DATE, -(day_of_week - 1));
		start = df.format(c.getTime());
		for (int i = 0; i < 7; i++) {
			response1 = scheduleDao.dayView(netMdBranchId, doctorId, start);
			ViewScheduleList.add(response1);
			response.setScheduleList(ViewScheduleList);
			c.add(Calendar.DATE, 1);
			start = df.format(c.getTime());
		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Monthly view
	 * 
	 * @param startDate
	 * @param endDate
	 * @return ScheduleListDTO
	 */
	@Override
	public ScheduleListDTO monthlyView(int netMdBranchId, int doctorId,
			String startDate, String endDate) {

		Date fromDate, toDate;
		ScheduleListDTO response = new ScheduleListDTO();
		ArrayList<ViewScheduleListDTO> ViewScheduleList = new ArrayList<ViewScheduleListDTO>();
		ViewScheduleListDTO response1 = new ViewScheduleListDTO();
		ErrorDTO error = validator.monthilyViewValidator(startDate, endDate);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		Calendar c = Calendar.getInstance();
		String start = startDate;
		// string to date
		fromDate = stringToDate(startDate);
		toDate = stringToDate(endDate);

		c.setTime(fromDate);

		// check start date after end date
		while (!fromDate.after(toDate)) {

			c.setTime(fromDate);
			response1 = scheduleDao.dayView(netMdBranchId, doctorId, start);
			ViewScheduleList.add(response1);
			response.setScheduleList(ViewScheduleList);
			c.add(Calendar.DATE, 1);
			start = df.format(c.getTime());
			fromDate = stringToDate(start);
		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	// convert string to Date
	public Date stringToDate(String Date) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		try {
			date = df.parse(Date);
		} catch (ParseException e) {
			log.error("Error while coverting Strring to date format in Schedule", e);
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public RetrievalScheduleResponseDTO retrieveScheduleList(String lastSyncTime,
			String passPhrase, int netmdBranchId, Date currentSyncTime) {
		RetrievalScheduleResponseDTO response= scheduleDao.retrieveScheduleList(lastSyncTime, passPhrase, netmdBranchId, currentSyncTime);
		return response;
	}

	
	/**
	 * @return the scheduleDao
	 */
	public ScheduleDao getScheduleDao() {
		return scheduleDao;
	}

	/**
	 * @param scheduleDao
	 *            the scheduleDao to set
	 */
	public void setScheduleDao(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

	/**
	 * @return the validator
	 */
	public ScheduleValidator getValidator() {
		return validator;
	}

	/**
	 * @param validator
	 *            the validator to set
	 */
	public void setValidator(ScheduleValidator validator) {
		this.validator = validator;
	}

	
}
