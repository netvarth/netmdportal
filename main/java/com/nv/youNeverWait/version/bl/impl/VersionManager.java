/**
 * 
 */
package com.nv.youNeverWait.version.bl.impl;

import com.nv.youNeverWait.rs.dto.VersionAvailabilityResponse;
import com.nv.youNeverWait.rs.dto.VersionDetail;
import com.nv.youNeverWait.version.bl.service.VersionService;
import com.nv.youNeverWait.version.pl.dao.VersionDao;
import com.nv.youNeverWait.version.validation.VersionValidator;

/**
 * @author Luciya
 * June 04 2014 Wednesday
 */
public class VersionManager implements VersionService{

	private VersionDao versionDao;
	private VersionValidator validator;
	
	@Override
	public VersionAvailabilityResponse checkUpdates(VersionDetail details) {
		//Validating version details
		 validator.validateVersionDetails(details);
		 
		VersionAvailabilityResponse response =versionDao.checkUpdates(details);
		return response;
	}
	public VersionDao getVersionDao() {
		return versionDao;
	}
	public void setVersionDao(VersionDao versionDao) {
		this.versionDao = versionDao;
	}
	public VersionValidator getValidator() {
		return validator;
	}
	public void setValidator(VersionValidator validator) {
		this.validator = validator;
	}
	

}
