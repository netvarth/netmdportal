/**
 * 
 */
package com.nv.youNeverWait.user.bl.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;

import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.InstallerDTO;
import com.nv.youNeverWait.rs.dto.InstallerListDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;

/**
 * @author net varth
 *
 */
public interface InstallerService {
	
	public ResponseDTO uploadNewVersion(HttpServletRequest request);
	public InstallerListDTO list(FilterDTO filter);

}
