/**
 * SyncValidator.java
 * 
 * @Author Luciya Jose
 *
 * April 05, 2013
 */
package com.nv.youNeverWait.user.bl.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.OrderResultBundle;
import com.nv.youNeverWait.rs.dto.OrderResultSyncDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;

/**
 * @author Luciya Jose
 * 
 */
public class SyncValidator {

	/**
	 * Method to validate header details
	 * 
	 * @param header
	 */
	public void validateNetMdHeaderDetails(HeaderDTO header) {
		if (header == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidHeader);
			se.setDisplayErrMsg(true);
			throw se;
		}

		if (header.getHeadOfficeId() <= 0) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.NetMdIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getPassPhrase() == null || header.getPassPhrase().equals("")) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.PassPhraseNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getBranchId() <= 0) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.BranchMissMatch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(header
					.getBranchId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getMacId() == null || header.getMacId().equals("")) {

			ServiceException se = new ServiceException(ErrorCodeEnum.MacIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Method to validate lab header details
	 * 
	 * @param header
	 */
	public void validateLabHeaderDetails(HeaderDTO header) {
		if (header == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidHeader);
			se.setDisplayErrMsg(true);
			throw se;
		}

		if (header.getHeadOfficeId() <= 0) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidLabId);
			se.addParam(new Parameter(Constants.ID, Integer.toString(header
					.getHeadOfficeId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getPassPhrase() == null || header.getPassPhrase().equals("")) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.PassPhraseNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getBranchId() <= 0) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(header
					.getBranchId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getMacId() == null || header.getMacId().equals("")) {

			ServiceException se = new ServiceException(ErrorCodeEnum.MacIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Method to validate last synchronization time
	 * 
	 * @param syncTime
	 */
	public void validateLastSyncTime(String syncTime) {
		// if (!isValidName(syncTime)) {
		// ServiceException se = new ServiceException(
		// ErrorCodeEnum.SyncTimeNull);
		// se.setDisplayErrMsg(true);
		// throw se;
		// }
		if (syncTime != null) {
			
				DateFormat df = new SimpleDateFormat(
						Constants.DATE_FORMAT_WITH_TIME_SECONDS);
				try {
					Date synTime = df.parse(syncTime);
					System.out.println(synTime);
				} catch (ParseException e) {
					e.printStackTrace();
					ServiceException se = new ServiceException(
							ErrorCodeEnum.InvalidDateFormat);
					se.setDisplayErrMsg(true);
					throw se;
				}
		}
	}

	/**
	 * Method for null checking
	 * 
	 * @param value
	 */
	public boolean isValidName(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * @param intervalTime 
	 * @param freqType 
	 * @param syncDetails
	 */
	public void checkSyncFreq(SyncFreqDTO syncFreqDetails, String freqType, int intervalTime) {
		if(!syncFreqDetails.isEnableSync()){
			ServiceException se = new ServiceException(ErrorCodeEnum.SyncDisable);
			se.setDisplayErrMsg(true);
			throw se;
		}
		else if(syncFreqDetails.getSyncFreqType().equals(freqType)){
			if(syncFreqDetails.getSyncTime()!=intervalTime){
				ServiceException se = new ServiceException(ErrorCodeEnum.SyncFreqMissMatch);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		
	}

	public void validateOrderResultBundle(OrderResultBundle bundle) {
		if(bundle.getSource_branch_id()==null || bundle.getSource_branch_id()==0)
			throw new ServiceException(ErrorCodeEnum.BranchCodeNull);
		if(bundle.getOrderResults().isEmpty())
				throw new ServiceException(ErrorCodeEnum.EmptyResult);
	}

	public void validateOrderResult(OrderResultSyncDTO orderResult) {
		if(orderResult.getOrder().getUid()==null || orderResult.getOrder().getUid()=="")
			throw new ServiceException(ErrorCodeEnum.OrderUidNull);
	}
}
