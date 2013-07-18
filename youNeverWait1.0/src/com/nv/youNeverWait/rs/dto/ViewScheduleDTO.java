/**
 * ViewScheduleDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 11, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.youNeverWait.rs.dto;

/**
 * 
 */
public class ViewScheduleDTO {
	private int id;
	private int doctorId;
	private int seriesId;
	private String name;
	private String startDate;
	private String startTime;
	private String endTime;
	private String status;
	private String endDate;
	private String occuranceType;
	private String repeat;
	private int endsOn;
	private int[] weeklySunThruSatList;

	/**
	 * 
	 */
	public ViewScheduleDTO() {
		super();
	}

	/**
	 * @param id
	 * @param doctorId
	 * @param seriesId
	 * @param name
	 * @param startDate
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param endDate
	 * @param occuranceType
	 * @param repeat
	 * @param endsOn
	 * @param weeklySunThruSatList
	 */
	public ViewScheduleDTO(int id, int doctorId, int seriesId, String name,
			String startDate, String startTime, String endTime, String status,
			String endDate, String occuranceType, String repeat, int endsOn,
			int[] weeklySunThruSatList) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.seriesId = seriesId;
		this.name = name;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.endDate = endDate;
		this.occuranceType = occuranceType;
		this.repeat = repeat;
		this.endsOn = endsOn;
		this.weeklySunThruSatList = weeklySunThruSatList;
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
	 * @return the doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}

	/**
	 * @param doctorId
	 *            the doctorId to set
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * @return the seriesId
	 */
	public int getSeriesId() {
		return seriesId;
	}

	/**
	 * @param seriesId
	 *            the seriesId to set
	 */
	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the occuranceType
	 */
	public String getOccuranceType() {
		return occuranceType;
	}

	/**
	 * @param occuranceType
	 *            the occuranceType to set
	 */
	public void setOccuranceType(String occuranceType) {
		this.occuranceType = occuranceType;
	}

	/**
	 * @return the repeat
	 */
	public String getRepeat() {
		return repeat;
	}

	/**
	 * @param repeat
	 *            the repeat to set
	 */
	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	/**
	 * @return the endsOn
	 */
	public int getEndsOn() {
		return endsOn;
	}

	/**
	 * @param endsOn
	 *            the endsOn to set
	 */
	public void setEndsOn(int endsOn) {
		this.endsOn = endsOn;
	}

	/**
	 * @return the weeklySunThruSatList
	 */
	public int[] getWeeklySunThruSatList() {
		return weeklySunThruSatList;
	}

	/**
	 * @param weeklySunThruSatList
	 *            the weeklySunThruSatList to set
	 */
	public void setWeeklySunThruSatList(int[] weeklySunThruSatList) {
		this.weeklySunThruSatList = weeklySunThruSatList;
	}

}
