/**
 * @author Luciya
 * June 04 2014 Wednesday
 */
package com.nv.youNeverWait.version.validation;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ApplicationNameEnum;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.SourceTypeEnum;
import com.nv.youNeverWait.pl.entity.UserTypeEnum;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.VersionDetail;

/**
 * @author Luciya
 * June 04 2014 Wednesday
 */
public class VersionValidator {
	/**
	 * Check version details
	 * 
	 * @param versionDetails
	 */
	public void validateVersionDetails(VersionDetail versionDetails) {
		//validate header details
		validateHeaderDetails(versionDetails.getHeader());
		
		//validate version details
		if (!isValidExpValue(versionDetails.getAppName())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.AppNameEmpty);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidExpValue(versionDetails.getSourceType())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.SourceTypeEmpty);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidExpValue(versionDetails.getVersionNumber())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.VersionNumberEmpty);
			se.setDisplayErrMsg(true);
			throw se;
		}
		//Checking given string is a part of required Enum type
		if(!isInEnum(versionDetails.getAppName(), ApplicationNameEnum.class)){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidApplicationName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(!isInEnum(versionDetails.getSourceType(), SourceTypeEnum.class)){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidSourceType);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}
	/**
	 * Validate header details
	 * 
	 * @param header
	 */
	public void validateHeaderDetails(HeaderDTO header) {
		if (header.getMacId() == null || header.getMacId().equals("")) {
			ServiceException se = new ServiceException(ErrorCodeEnum.MacIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getPassPhrase() == null || header.getPassPhrase().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PassPhraseNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}
	/**
	 * Checking a string is empty or not
	 * @param value
	 * @return
	 */
	private boolean isValidExpValue(String value) {
		if(value!=null && !value.equals("")){
			return true;
		}
		return false;
	}
	
	/**
	 * checks whether a given string is a part of 
	 * given Enum
	 * @param value
	 * @param enumClass
	 * @return
	 */
	public <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
		  for (E e : enumClass.getEnumConstants()) {
		    if(e.name().equals(value)) 
		    	return true; 
		  }
		  return false;
		}
}
