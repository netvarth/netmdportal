/**
 * 
 */
package com.nv.youNeverWait.version.pl.dao;

import com.nv.youNeverWait.rs.dto.VersionAvailabilityResponse;
import com.nv.youNeverWait.rs.dto.VersionDetail;

/**
 * @author Joshi
 *
 */
public interface VersionDao {

	VersionAvailabilityResponse checkUpdates(VersionDetail details);
	

}
