/**
 * SyncServiceImpl.java
 * 
 * @Author Luciya Jose
 *
 * April 05, 2013
 */
package com.nv.NetlimsPortal.pl.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nv.NetlimsPortal.bl.service.LabService;
import com.nv.NetlimsPortal.bl.service.SpecimenService;
import com.nv.NetlimsPortal.bl.service.SyncService;
import com.nv.NetlimsPortal.bl.service.TestService;
import com.nv.NetlimsPortal.bl.validation.SyncValidator;
import com.nv.NetlimsPortal.general.Constants;
import com.nv.NetlimsPortal.rs.dto.BranchOrderCountResponseDTO;
import com.nv.NetlimsPortal.rs.dto.LabBranchListResponseDTO;
import com.nv.NetlimsPortal.rs.dto.LabDTO;
import com.nv.NetlimsPortal.rs.dto.LabSyncDTO;
import com.nv.NetlimsPortal.rs.dto.LabSyncResponseDTO;
import com.nv.NetlimsPortal.rs.dto.ResultRetrievalResponseDTO;
import com.nv.NetlimsPortal.rs.dto.RetrieveSpecimenResponse;
import com.nv.NetlimsPortal.rs.dto.RetrieveTestResponse;
import com.nv.NetlimsPortal.rs.dto.RetrieveUserListResponseDTO;




/**
 * @author Luciya Jose
 * 
 */
public class SyncServiceImpl implements SyncService {

	private LabService labService;
	private SpecimenService specimenService;
	private TestService testService;
	private SyncValidator validator;
	
	private static final Log log = LogFactory.getLog(SyncServiceImpl.class);


	/**
	 * Method which performs synchronization for NetLims
	 * 
	 * @param sync
	 * @return syncResponse
	 */
	@Override
	public LabSyncResponseDTO syncNetLimsData(LabSyncDTO sync) {
		LabSyncResponseDTO syncResponse = new LabSyncResponseDTO();
		DateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);

		/* Validating header details */
		validator.validateLabHeaderDetails(sync.getHeader());

		/* Validating last sync time */
		validator.validateLastSyncTime(sync.getLastSyncTime());

		/*Setting last sync time when the syncData calling for the first time*/
		if(sync.getLastSyncTime()==null)
			sync.setLastSyncTime(df.format(new Date(0)));
		
		Date currentSyncTime = new Date(); // setting current date time
		

		
		/*Retrieving all lab branches in the portal*/
		LabBranchListResponseDTO retrieveLabBranchList=labService.retrieveLabBranchList(sync.getHeader(),sync.getLastSyncTime(),currentSyncTime);
		syncResponse.setRetrieveLabBranchList(retrieveLabBranchList);

		/*Retrieving lab branch users*/
		RetrieveUserListResponseDTO retrieveUserList= labService.retrieveUserList(sync.getHeader(), sync.getLastSyncTime(), currentSyncTime);
		syncResponse.setRetrieveUserList(retrieveUserList);

		/*Retrieving  newly added and updated lab test*/
		 RetrieveTestResponse getTests= testService.getTests(sync.getHeader(),sync.getLastSyncTime(),currentSyncTime);
		 syncResponse.setRetrieveTests(getTests);
		 
		 /*Retrieving newly added and updated test specimen*/
		 RetrieveSpecimenResponse retreiveSpecimens= specimenService.getSpecimens(sync.getHeader(),sync.getLastSyncTime(),currentSyncTime);
		 syncResponse.setRetrieveSpecimens(retreiveSpecimens);
		 
		 /*Get all results from other branches of same lab*/
		ResultRetrievalResponseDTO getResult = labService.getResult(sync.getHeader(), sync.getLastSyncTime(), currentSyncTime);
		syncResponse.setGetResult(getResult);
		
		/* Save total orders and its related details of a branch*/
		BranchOrderCountResponseDTO response= labService.createTotalOrders(sync.getHeader(),sync.getBranchOrders());
		syncResponse.setOrderAmount(response);
	
		
		/* Retrieving lab details*/
		LabDTO labDetails= labService.getLab(sync.getHeader(),sync.getLastSyncTime(),currentSyncTime);
		syncResponse.setLabDetails(labDetails);
		
		syncResponse.setLastSynctime(df.format(currentSyncTime));
		return syncResponse;
	}



	/**
	 * @return the labService
	 */
	public LabService getLabService() {
		return labService;
	}

	/**
	 * @param labService the labService to set
	 */
	public void setLabService(LabService labService) {
		this.labService = labService;
	}



	/**
	 * @return the specimenService
	 */
	public SpecimenService getSpecimenService() {
		return specimenService;
	}

	/**
	 * @param specimenService the specimenService to set
	 */
	public void setSpecimenService(SpecimenService specimenService) {
		this.specimenService = specimenService;
	}

	/**
	 * @return the testService
	 */
	public TestService getTestService() {
		return testService;
	}

	/**
	 * @param testService the testService to set
	 */
	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	


}
