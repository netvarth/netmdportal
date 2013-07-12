/**
 * NetMdDao.java
 *
 * Jan 3, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.pl.dao;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestBody;

import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.NetMdActivationResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDetail;
import com.nv.youNeverWait.rs.dto.NetMdViewResponseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalUserResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveNetmdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveNetmdListResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.user.pl.impl.NetMdBranchOwnerDetails;

public interface NetMdDao {
	public ResponseDTO  create(NetMdDTO netMd);
	public ResponseDTO  update(NetMdDTO netMd);
	public ResponseDTO  delete(int netMdId);
	public ResponseDTO  updateBranch(NetMdBranchDTO branch);
	public NetMdBranchResponseDTO viewBranch(int globalId);
	public ResponseDTO createBranch(NetMdBranchDTO branch);
	public NetMdViewResponseDTO view(int netMdId);
	public ResponseDTO  deleteBranch(int globalId);
	public  String getMacByPassphrase(String passPhrase);
	public NetMdActivationResponseDTO activateNetMd(HeaderDTO header);
	public NetMdBranchOwnerDetails getBranchOwners(int globalId);
	public RetrieveNetmdListResponseDTO retrieveNetmdList(String syncTime,Date currentTime);
	public ResponseDTO createUser(HeaderDTO header,NetMdUserDetail user);
	public String getNetMdName(int netMdId);
	public String getNetMdBranchName(int netMdBranchId);
	public RetrieveNetmdBranchListResponseDTO retrieveNetmdBranchList(String syncTime, Date currentTime);
	public ResponseDTO updateUser(HeaderDTO header,NetMdUserDetail user);
	public UserCredentials getUserCredentials(LoginDTO login);
	public ResponseDTO deleteUser(int globalId);
	public NetMdUserDTO viewUser(int globalId);
	public RetrievalUserResponseDTO retrieveUserList(String lastSyncTime,String passPhrase, int netmdBranchId,Date currentSyncTime);
	public ResponseDTO resetPassword(LoginDTO login);
	public ResponseDTO clearMacId(HeaderDTO header);
	public ResponseDTO makePrimary(HeaderDTO header);
	public ResponseDTO changePassword(PasswordDTO passwords);
}
