/**
 * SyncDao.java
 * 
 * @Author Luciya Jose
 *
 * April 05, 2013
 */
package com.nv.youNeverWait.user.pl.dao;

import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.HeaderResponseDTO;

/**
 * @author Luciya Jose
 * 
 */
public interface SyncDao {

	public HeaderResponseDTO getHeaderStatus(HeaderDTO header);
}
