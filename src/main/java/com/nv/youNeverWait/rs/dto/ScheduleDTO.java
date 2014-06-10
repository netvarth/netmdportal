/**
 * ScheduleDTO.java
 *
 * Mar 12, 2013
 *
 * @author Luciya
 */
package com.nv.youNeverWait.rs.dto;

public class ScheduleDTO {
	
	private HeaderDTO header;
	private ScheduleDetail scheduleDetail;
	/**
	 * @return the header
	 */
	public HeaderDTO getHeader() {
		return header;
	}
	/**
	 * @param header the header to set
	 */
	public void setHeader(HeaderDTO header) {
		this.header = header;
	}
	/**
	 * @return the scheduleDetail
	 */
	public ScheduleDetail getScheduleDetail() {
		return scheduleDetail;
	}
	/**
	 * @param scheduleDetail the scheduleDetail to set
	 */
	public void setScheduleDetail(ScheduleDetail scheduleDetail) {
		this.scheduleDetail = scheduleDetail;
	}
	
}
