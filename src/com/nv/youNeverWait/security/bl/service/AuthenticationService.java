/**
 * AuthenticationService.java
 *
 * Dec 3, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.security.bl.service;

import com.nv.youNeverWait.rs.dto.ErrorCodeListResponseDTO;
import com.nv.youNeverWait.rs.dto.EnumListResponseDTO;
import com.nv.youNeverWait.rs.dto.CaptchaResponseDTO;
import com.nv.youNeverWait.rs.dto.CaptchaVerificationDTO;
import com.nv.youNeverWait.rs.dto.CaptchaVerificationResponseDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.UserDetails;

public interface AuthenticationService {
	public LoginResponseDTO netlimsLogin(LoginDTO login);
	public UserDetails getNetlimsUser(String userName);
	public LoginResponseDTO netmdLogin(LoginDTO login);
	public LoginResponseDTO patientLogin(LoginDTO login);
	public UserDetails getNetmdUser(String userName);
	public CaptchaResponseDTO getCaptcha();
	public CaptchaVerificationResponseDTO verifyCaptcha(
			CaptchaVerificationDTO captcha);
	public UserDetails getPatient(String userName);
	public EnumListResponseDTO getEnumsList();
	public ErrorCodeListResponseDTO getErrorCodes();
	public LoginResponseDTO netrxLogin(LoginDTO login);
	public UserDetails getNetrxUser(String userName);
	public LoginResponseDTO organisationLogin(LoginDTO login);
	public UserDetails getOrganisationUser(String userName);
	
	
}
