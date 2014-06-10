/**
 * ScheduleDTO.java
 *
 * Mar 12, 2013
 *
 * @author Luciya
 */
package com.nv.youNeverWait.rs.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.DoctorScheduleTbl;

public class ScheduleDetail {
	private int id;
	private int doctorGlobalId;
	private SeriesDTO series;
	private String startDate;
	private String startTime;
	private String endTime;
	private String scheduleStatus;
	private int scheduleGlobalId;
	private String status;



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the scheduleGlobalId
	 */
	public int getScheduleGlobalId() {
		return scheduleGlobalId;
	}

	/**
	 * @param scheduleGlobalId the scheduleGlobalId to set
	 */
	public void setScheduleGlobalId(int scheduleGlobalId) {
		this.scheduleGlobalId = scheduleGlobalId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the doctorGlobalId
	 */
	public int getDoctorGlobalId() {
		return doctorGlobalId;
	}

	/**
	 * @param doctorGlobalId
	 *            the doctorGlobalId to set
	 */
	public void setDoctorGlobalId(int doctorGlobalId) {
		this.doctorGlobalId = doctorGlobalId;
	}

	/**
	 * @return the series
	 */
	public SeriesDTO getSeries() {
		return series;
	}

	/**
	 * @param series
	 *            the series to set
	 */
	public void setSeries(SeriesDTO series) {
		this.series = series;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the scheduleStatus
	 */
	public String getScheduleStatus() {
		return scheduleStatus;
	}

	/**
	 * @param scheduleStatus
	 *            the scheduleStatus to set
	 */
	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	/**
	 * @param id
	 * @param doctorGlobalId
	 * @param series
	 * @param startDate
	 * @param startTime
	 * @param endTime
	 * @param scheduleStatus
	 * @param scheduleGlobalId
	 * @param status
	 */
	public ScheduleDetail(DoctorScheduleTbl schedule) {
		super();
		DateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		DateFormat df = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		this.scheduleGlobalId=schedule.getId();
		this.doctorGlobalId = schedule.getDoctorTbl().getId();
		if(schedule.getDate()!=null)
			this.startDate = sdf.format(schedule.getDate());
		if(schedule.getStartingTime()!=null)
			this.startTime = df.format(schedule.getStartingTime());
		if(schedule.getEndingTime()!=null)
			this.endTime = df.format(schedule.getEndingTime());
		this.scheduleStatus = schedule.getScheduleStatus();
		this.status = schedule.getStatus();
		SeriesDTO series= new SeriesDTO();
		if(schedule.getSeriesTbl()!=null){
			if(schedule.getSeriesTbl().getEndDate()!=null)
				series.setEndDate(sdf.format(schedule.getSeriesTbl().getEndDate()));
			series.setEndsOn(schedule.getSeriesTbl().getEndsOn());
			series.setOccuranceType(schedule.getSeriesTbl().getOccuranceType());
			series.setRepeat(schedule.getSeriesTbl().getScheduleRepeat());
			series.setWeeklyType(schedule.getSeriesTbl().getWeeklyType());
			series.setSeriesGlobalId(schedule.getSeriesTbl().getId());
			series.setSeriesId(schedule.getSeriesTbl().getSeriesId());
		}
		this.series = series;
	}

	/**
	 * 
	 */
	public  ScheduleDetail() {
		super();

	}


}
