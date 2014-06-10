/**
 * SeriesDTO.java
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Luciya
 *
 */
public class SeriesDTO {
	private int seriesId;
	private String endDate;
	private String weeklyType;
	private int endsOn;
	private String occuranceType;
	private String repeat;
	private int seriesGlobalId;
	
	/**
	 * @return the seriesGlobalId
	 */
	public int getSeriesGlobalId() {
		return seriesGlobalId;
	}
	/**
	 * @param seriesGlobalId the seriesGlobalId to set
	 */
	public void setSeriesGlobalId(int seriesGlobalId) {
		this.seriesGlobalId = seriesGlobalId;
	}
	/**
	 * @return the seriesId
	 */
	public int getSeriesId() {
		return seriesId;
	}
	/**
	 * @param seriesId the seriesId to set
	 */
	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the weeklyType
	 */
	public String getWeeklyType() {
		return weeklyType;
	}
	/**
	 * @param weeklyType the weeklyType to set
	 */
	public void setWeeklyType(String weeklyType) {
		this.weeklyType = weeklyType;
	}
	/**
	 * @return the endsOn
	 */
	public int getEndsOn() {
		return endsOn;
	}
	/**
	 * @param endsOn the endsOn to set
	 */
	public void setEndsOn(int endsOn) {
		this.endsOn = endsOn;
	}
	/**
	 * @return the occuranceType
	 */
	public String getOccuranceType() {
		return occuranceType;
	}
	/**
	 * @param occuranceType the occuranceType to set
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
	 * @param repeat the repeat to set
	 */
	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}
	

}
