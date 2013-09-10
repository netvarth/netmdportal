/**
 * NetLimsSyncResponseDTO.java
 *
 * @Author Luciya Jos
 * May 10, 2013 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Luciya Jos
 * 
 */
public class LabSyncResponseDTO {
	private String lastSynctime;
	private ErrorDTO error;
	private boolean success;
	private BranchOrderCountResponseDTO orderAmount= new BranchOrderCountResponseDTO();
	private LabBranchListResponseDTO retrieveLabBranchList = new LabBranchListResponseDTO();
	private RetrieveUserListResponseDTO retrieveUserList = new RetrieveUserListResponseDTO();
	private ResultRetrievalResponseDTO getResult = new ResultRetrievalResponseDTO();
	private RetrieveTestResponseDTO retrieveTests= new RetrieveTestResponseDTO();
	private RetrieveSpecimenResponseDTO retrieveSpecimens = new RetrieveSpecimenResponseDTO();
	
	
	/**
	 * @return the retrieveSpecimens
	 */
	public RetrieveSpecimenResponseDTO getRetrieveSpecimens() {
		return retrieveSpecimens;
	}

	/**
	 * @param retrieveSpecimens the retrieveSpecimens to set
	 */
	public void setRetrieveSpecimens(RetrieveSpecimenResponseDTO retrieveSpecimens) {
		this.retrieveSpecimens = retrieveSpecimens;
	}

	/**
	 * @return the retrieveTests
	 */
	public RetrieveTestResponseDTO getRetrieveTests() {
		return retrieveTests;
	}

	/**
	 * @param retrieveTests the retrieveTests to set
	 */
	public void setRetrieveTests(RetrieveTestResponseDTO retrieveTests) {
		this.retrieveTests = retrieveTests;
	}

	/**
	 * @return the orderAmount
	 */
	public BranchOrderCountResponseDTO getOrderAmount() {
		return orderAmount;
	}

	/**
	 * @param orderAmount the orderAmount to set
	 */
	public void setOrderAmount(BranchOrderCountResponseDTO orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * @return the lastSynctime
	 */
	public String getLastSynctime() {
		return lastSynctime;
	}

	/**
	 * @return the getResult
	 */
	public ResultRetrievalResponseDTO getGetResult() {
		return getResult;
	}

	/**
	 * @param getResult
	 *            the getResult to set
	 */
	public void setGetResult(ResultRetrievalResponseDTO getResult) {
		this.getResult = getResult;
	}

	/**
	 * @param lastSynctime
	 *            the lastSynctime to set
	 */
	public void setLastSynctime(String lastSynctime) {
		this.lastSynctime = lastSynctime;
	}

	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
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
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the retrieveLabBranchList
	 */
	public LabBranchListResponseDTO getRetrieveLabBranchList() {
		return retrieveLabBranchList;
	}

	/**
	 * @param retrieveLabBranchList
	 *            the retrieveLabBranchList to set
	 */
	public void setRetrieveLabBranchList(
			LabBranchListResponseDTO retrieveLabBranchList) {
		this.retrieveLabBranchList = retrieveLabBranchList;
	}
	/**
	 * @return the retrieveUserList
	 */
	public RetrieveUserListResponseDTO getRetrieveUserList() {
		return retrieveUserList;
	}

	/**
	 * @param retrieveUserList
	 *            the retrieveUserList to set
	 */
	public void setRetrieveUserList(RetrieveUserListResponseDTO retrieveUserList) {
		this.retrieveUserList = retrieveUserList;
	}

}
