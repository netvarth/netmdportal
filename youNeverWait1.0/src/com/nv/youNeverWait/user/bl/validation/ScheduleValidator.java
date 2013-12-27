/**
 * ScheduleValidator.java
 *
 * Mar 12, 2013
 *
 * @author Luciya
 */
package com.nv.youNeverWait.user.bl.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.ScheduleDetail;
import com.nv.youNeverWait.rs.dto.SeriesDTO;

public class ScheduleValidator {
	/**
	 * Method to validate schedule details
	 * 
	 * @param schedule
	 */
	public void validateScheduleDetails(HeaderDTO header,
			ScheduleDetail scheduleDetail) {
		SimpleDateFormat df = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		Date startDate =null;
		if (header == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.HeaderNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		validateNetmdBranchIds(header.getHeadOfficeId(), header.getBranchId());
		if (scheduleDetail == null) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.ScheduleDetailNull);
			se.setDisplayErrMsg(true);
			throw se;
		} else {
			if (scheduleDetail.getStartDate() == null
					|| scheduleDetail.getStartDate().equals("")) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.ScheduleStartDateNull);
				se.setDisplayErrMsg(true);
				throw se;
			}
			try {
				 startDate = sdf.parse(scheduleDetail.getStartDate());
			} catch (ParseException e) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}

			if (scheduleDetail.getStartTime() == null
					|| scheduleDetail.getStartTime().equals("")) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.ScheduleStartTimeNull);
				se.setDisplayErrMsg(true);
				throw se;
			}
			if (scheduleDetail.getEndTime() == null
					|| scheduleDetail.getEndTime().equals("")) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.ScheduleEndTimeNull);
				se.setDisplayErrMsg(true);
				throw se;
			}
			if (scheduleDetail.getStartTime() != null
					&& scheduleDetail.getEndTime() != null) {
				try {
					Date startTime = df.parse(scheduleDetail.getStartTime());
					Date endTime = df.parse(scheduleDetail.getEndTime());
					if (startTime.after(endTime)) {

						ServiceException se = new ServiceException(
								ErrorCodeEnum.InvalidFromTime);
						se.setDisplayErrMsg(true);
						throw se;

					}

				} catch (ParseException e) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.InvalidTimeFormat);
					se.setDisplayErrMsg(true);
					throw se;

				}
			}
			if (scheduleDetail.getScheduleStatus() == null
					|| scheduleDetail.getScheduleStatus().equals("")) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.ScheduleStatusNull);
				se.setDisplayErrMsg(true);
				throw se;

			}
			if (scheduleDetail.getDoctorGlobalId() <= 0) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDoctorId);
				se.setDisplayErrMsg(true);
				throw se;
			}
			if (scheduleDetail.getSeries() == null) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.ScheduleSeriesNull);
				se.setDisplayErrMsg(true);
				throw se;

			} else
				if(scheduleDetail.getSeries().getEndDate()!=null){
					try {
						Date endDate = sdf.parse(scheduleDetail.getSeries().getEndDate());
						
						if (startDate.after(endDate)) {

							ServiceException se = new ServiceException(
									ErrorCodeEnum.StartDateGreater);
							se.setDisplayErrMsg(true);
							throw se;
						}

					} catch (ParseException e) {
						ServiceException se = new ServiceException(
								ErrorCodeEnum.InvalidTimeFormat);
						se.setDisplayErrMsg(true);
						throw se;

					}
				}
				validateScheduleSeriesDetails(scheduleDetail.getSeries());
		}

	}

	/**
	 * Method to validate schedule series details
	 * 
	 * @param series
	 */
	public void validateScheduleSeriesDetails(SeriesDTO series) {
		if (series.getSeriesId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSeriesId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (series.getOccuranceType() == null
				&& series.getOccuranceType().equals("")) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.OccuranceTypeNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (series.getRepeat() == null && series.getRepeat().equals("")) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.RepeatTypeNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Method to validate Netmd id and Netmd branch id
	 * 
	 * @param labId
	 * @param branchId
	 */
	public void validateNetmdBranchIds(int netmdId, int netmdBranchId) {
		if (netmdId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netmdBranchId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Validates schedule global id
	 * 
	 * @param globalId
	 */
	public void validateGlobalId(int globalId) {
		if (globalId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidGlobalId);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Validates date
	 * 
	 * @param date
	 * @return ErrorDTO
	 */
	public ErrorDTO validateDate(String date) {
		ErrorDTO error = new ErrorDTO();
		if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		try {
			Date startDate = df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	/**
	 * Validates start date and end date of monthly view
	 * 
	 * @param startDate
	 * @param endDate
	 * @return ErrorDTO
	 */
	public ErrorDTO monthilyViewValidator(String startDate, String endDate) {
		ErrorDTO error = new ErrorDTO();
		// validate start date
		Date startingDate;
		Date endingDate;
		if (startDate == null || !startDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (endDate == null || !endDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		try {
			startingDate = df.parse(startDate);
			endingDate = df.parse(endDate);
		} catch (ParseException e) {

			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (startingDate.after(endingDate)) {
			error.setErrCode(ErrorCodeEnum.StartDateGreater.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

}
