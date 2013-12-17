/**
 * NetRxDao.java
 *
 * @Author Luciya Jose
 * May 8, 2013 
 */
package com.nv.youNeverWait.user.pl.dao;


import org.springframework.web.bind.annotation.RequestBody;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchDetail;
import com.nv.youNeverWait.rs.dto.NetRxBranchOwnerDetails;
import com.nv.youNeverWait.rs.dto.NetRxBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxDTO;
import com.nv.youNeverWait.rs.dto.NetRxUserDTO;
import com.nv.youNeverWait.rs.dto.NetRxHeaderDTO;
import com.nv.youNeverWait.rs.dto.NetRxUserDetail;
import com.nv.youNeverWait.rs.dto.NetRxViewResponseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;




public interface NetRxDao {

	public ResponseDTO create(NetRxDTO netRx);
	public ResponseDTO update(NetRxDTO netRx);
	public ResponseDTO  deleteNetRx(int netRxId);
	public NetRxViewResponseDTO viewNetRx(int netRxId) ;

	public ResponseDTO createUser(NetRxHeaderDTO header, NetRxUserDetail netRxUser);
	public ResponseDTO updateUser(NetRxHeaderDTO header, NetRxUserDetail netRxUser);
	public String getNetRxName(int netRxId);
	public ResponseDTO deleteUser(int globalId);
	public String getNetRxBranchName(int netMdBranchId);

	public ResponseDTO createBranch(NetRxBranchDetail branch);
	public NetRxBranchOwnerDetails getBranchOwners(int globalId);
	public NetRxBranchResponseDTO viewBranch(int netrxBranchId);
	public ResponseDTO deleteBranch(int globalId);
	public NetRxUserDTO viewUser(int globalId);
	public ResponseDTO updateNetRxBranch(NetRxBranchDetail branch);
	public UserCredentials getUserCredentials(LoginDTO login);
	public ResponseDTO resetPassword(LoginDTO login);
	public ResponseDTO changePassword(@RequestBody PasswordDTO passwords);
	public ResponseDTO clearMacId(NetRxHeaderDTO header);
	public SyncFreqResponseDTO setNetRxSync(SyncFreqDTO sync);
	public SyncFreqResponseDTO setNetRxBranchSync(SyncFreqDTO sync);
	public SyncFreqDTO getBranchSyncDetails(int branchId);
	public SyncFreqDTO getNetrxSyncDetails(int netrxId);

}
