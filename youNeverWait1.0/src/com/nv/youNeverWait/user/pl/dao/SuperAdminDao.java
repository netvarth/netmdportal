/**
 * SuperAdminDao.java
 *
 * Jan 2, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.pl.dao;

import javax.servlet.http.HttpServletRequest;

import com.nv.youNeverWait.rs.dto.EnableLogStatusResponseDTO;
import com.nv.youNeverWait.rs.dto.LogDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncLogDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.rs.dto.UserDetails;

public interface SuperAdminDao {
	public LoginResponseDTO login(LoginDTO login);
	public UserDetails getUser(String userName);
	public ResponseDTO changePassword(PasswordDTO passwords);
	public UserCredentials getUserCredentials(LoginDTO login);
	public ResponseDTO resetPassword(LoginDTO login);
	public ResponseDTO enableLog(LogDTO log, HttpServletRequest request);
	public EnableLogStatusResponseDTO enableLogStatus();
	public ResponseDTO enableSyncLog(SyncLogDTO syncLog, HttpServletRequest request);
	public ResponseDTO setSync(SyncFreqDTO sync);
	public SyncFreqDTO getSyncDetails();
	public EnableLogStatusResponseDTO getSyncLogStatus();

}
