/**
 * AuthenticationDao.java
 *
 * Dec 3, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.security.pl.dao;

import com.nv.youNeverWait.rs.dto.ErrorCodeListResponseDTO;
import com.nv.youNeverWait.pl.dao.GenericDao;
import com.nv.youNeverWait.rs.dto.CreatePasswordDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.rs.dto.UserDetails;

public interface AuthenticationDao extends GenericDao {
	public LoginResponseDTO netlimsLogin(LoginDTO loginDTO);
	public UserDetails getNetlimsUser(String userName);
	public LoginResponseDTO netmdLogin(LoginDTO login);
	public LoginResponseDTO patientLogin(LoginDTO loginDTO);
	public UserDetails getPatient(String userName);
	public UserDetails getNetmdUser(String userName);
	public ErrorCodeListResponseDTO getErrorCodes();
	public LoginResponseDTO netrxLogin(LoginDTO loginDTO);
	public UserDetails getNetrxUser(String userName);
	public ResponseDTO changePassword(PasswordDTO passwords);
	public ResponseDTO createPassword(CreatePasswordDTO passwords);
	ResponseDTO resetPassword(LoginDTO login);
	UserCredentials getUserCredentials(LoginDTO login);
	UserDetails getNetlimsFacilityUser(String userName, String userType);
	
	
}
