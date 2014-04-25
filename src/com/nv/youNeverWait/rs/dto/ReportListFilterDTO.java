package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class ReportListFilterDTO {
	private List<ReportFilterDTO> reportFilter = new ArrayList<ReportFilterDTO>();
	private Long count;
	private ErrorDTO error;
	private boolean success;

	
	
	/**
	 * @return the reportFilter
	 */
	public List<ReportFilterDTO> getReportFilter() {
		return reportFilter;
	}
	/**
	 * 
	 */
	public ReportListFilterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param reportFilter
	 * @param count
	 * @param error
	 * @param success
	 */
	private ReportListFilterDTO(List<ReportFilterDTO> reportFilter, Long count,
			ErrorDTO error, boolean success) {
		super();
		this.reportFilter = reportFilter;
		this.count = count;
		this.error = error;
		this.success = success;
	}
	/**
	 * @param reportFilter the reportFilter to set
	 */
	public void setReportFilter(List<ReportFilterDTO> reportFilter) {
		this.reportFilter = reportFilter;
	}
	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}
	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

}
