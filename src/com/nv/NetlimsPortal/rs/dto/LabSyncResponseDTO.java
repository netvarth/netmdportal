/**
 * NetLimsSyncResponseDTO.java
 *
 * @Author Luciya Jos
 * May 10, 2013 
 */
package com.nv.NetlimsPortal.rs.dto;

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
	private RetrieveTestResponse retrieveTests= new RetrieveTestResponse();
	private RetrieveSpecimenResponse retrieveSpecimens = new RetrieveSpecimenResponse();
	private LabDTO labDetails= new LabDTO();
//	private String freqType;
//	private int intervalTime;
//	private boolean syncStatus;
	
	
//	
//	
//	/**
//	 * @return the freqType
//	 */
//	public String getFreqType() {
//		return freqType;
//	}
//
//
//
//	/**
//	 * @param freqType the freqType to set
//	 */
//	public void setFreqType(String freqType) {
//		this.freqType = freqType;
//	}
//
//
//
//	/**
//	 * @return the intervalTime
//	 */
//	public int getIntervalTime() {
//		return intervalTime;
//	}
//
//
//
//	/**
//	 * @param intervalTime the intervalTime to set
//	 */
//	public void setIntervalTime(int intervalTime) {
//		this.intervalTime = intervalTime;
//	}
//
//
//
//	/**
//	 * @return the syncStatus
//	 */
//	public boolean isSyncStatus() {
//		return syncStatus;
//	}
//
//
//
//	/**
//	 * @param syncStatus the syncStatus to set
//	 */
//	public void setSyncStatus(boolean syncStatus) {
//		this.syncStatus = syncStatus;
//	}
//
//

	/**
	 * @return the labDetails
	 */
	public LabDTO getLabDetails() {
		return labDetails;
	}



	/**
	 * @param labDetails the labDetails to set
	 */
	public void setLabDetails(LabDTO labDetails) {
		this.labDetails = labDetails;
	}



	/**
	 * @return the lastSynctime
	 */
	public String getLastSynctime() {
		return lastSynctime;
	}



	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}



	/**
	 * @return the orderAmount
	 */
	public BranchOrderCountResponseDTO getOrderAmount() {
		return orderAmount;
	}



	/**
	 * @return the retrieveLabBranchList
	 */
	public LabBranchListResponseDTO getRetrieveLabBranchList() {
		return retrieveLabBranchList;
	}



	/**
	 * @return the retrieveUserList
	 */
	public RetrieveUserListResponseDTO getRetrieveUserList() {
		return retrieveUserList;
	}



	/**
	 * @return the getResult
	 */
	public ResultRetrievalResponseDTO getGetResult() {
		return getResult;
	}



	/**
	 * @return the retrieveTests
	 */
	public RetrieveTestResponse getRetrieveTests() {
		return retrieveTests;
	}



	/**
	 * @return the retrieveSpecimens
	 */
	public RetrieveSpecimenResponse getRetrieveSpecimens() {
		return retrieveSpecimens;
	}

	/**
	 * @param retrieveSpecimens the retrieveSpecimens to set
	 */
	public void setRetrieveSpecimens(RetrieveSpecimenResponse retrieveSpecimens) {
		this.retrieveSpecimens = retrieveSpecimens;
	}

	

	/**
	 * @param retrieveTests the retrieveTests to set
	 */
	public void setRetrieveTests(RetrieveTestResponse retrieveTests) {
		this.retrieveTests = retrieveTests;
	}


	/**
	 * @param orderAmount the orderAmount to set
	 */
	public void setOrderAmount(BranchOrderCountResponseDTO orderAmount) {
		this.orderAmount = orderAmount;
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
	 * @param retrieveLabBranchList
	 *            the retrieveLabBranchList to set
	 */
	public void setRetrieveLabBranchList(
			LabBranchListResponseDTO retrieveLabBranchList) {
		this.retrieveLabBranchList = retrieveLabBranchList;
	}


	/**
	 * @param retrieveUserList
	 *            the retrieveUserList to set
	 */
	public void setRetrieveUserList(RetrieveUserListResponseDTO retrieveUserList) {
		this.retrieveUserList = retrieveUserList;
	}

}
