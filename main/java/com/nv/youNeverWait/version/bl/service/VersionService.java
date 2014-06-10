/**
 * 
 */
package com.nv.youNeverWait.version.bl.service;

import com.nv.youNeverWait.rs.dto.VersionAvailabilityResponse;
import com.nv.youNeverWait.rs.dto.VersionDetail;

/**
 * @author Luciya
 * June 04 2014 Wednesday
 *
 */
public interface VersionService {

	VersionAvailabilityResponse checkUpdates(VersionDetail details);
	

}
