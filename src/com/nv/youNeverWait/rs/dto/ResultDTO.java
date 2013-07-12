/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

import java.text.SimpleDateFormat;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.ResultTbl;

/**
 * @author nv
 *
 */
public class ResultDTO {

	int id;
	String time;
	String testResult;
	String testName;
	String labBranch;
	String orderDate;
	String orderId;
	String labName;
	ErrorDTO error;
	boolean success;
	
	
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

	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the labName
	 */
	public String getLabName() {
		return labName;
	}

	/**
	 * @param labName the labName to set
	 */
	public void setLabName(String labName) {
		this.labName = labName;
	}

	public ResultDTO() {
		
		// TODO Auto-generated constructor stub
	}
	
	public ResultDTO(ResultTbl resultTbl) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		SimpleDateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		this.id = resultTbl.getId();
		this.time = sdf.format(resultTbl.getCreatedDateTime());
		this.testResult = resultTbl.getResult();
		this.labBranch = resultTbl.getLabBranchTbl().getName();
		this.labName=resultTbl.getLabTbl().getName();
		this.orderDate=df.format(resultTbl.getOrderDate());
		this.orderId=resultTbl.getOrderId();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the testResult
	 */
	public String getTestResult() {
		return testResult;
	}

	/**
	 * @param testResult the testResult to set
	 */
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getLabBranch() {
		return labBranch;
	}
	public void setLabBranch(String labBranch) {
		this.labBranch = labBranch;
	}

	
	
}
