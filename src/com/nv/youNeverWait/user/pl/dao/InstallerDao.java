/**
 * 
 */
package com.nv.youNeverWait.user.pl.dao;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.nv.youNeverWait.rs.dto.InstallerDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;

/**
 * @author net varth
 *
 */
public interface InstallerDao {
	
	public ResponseDTO uploadNewVersion(HttpServletRequest request, String path);
	

}
