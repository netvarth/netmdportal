/**
 * NetRxService.java
 *
 * @Author Luciya Jose
 * May 8, 2013 
 */
package com.nv.youNeverWait.user.bl.service;


import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchDetail;
import com.nv.youNeverWait.rs.dto.NetRxBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxDTO;
import com.nv.youNeverWait.rs.dto.NetRxHeaderDTO;
import com.nv.youNeverWait.rs.dto.NetRxListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxUserDTO;
import com.nv.youNeverWait.rs.dto.NetRxUserDetail;
import com.nv.youNeverWait.rs.dto.NetRxUserListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxViewResponseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqResponseDTO;



public interface NetRxService {

	public ResponseDTO create(NetRxDTO netRx);
	public ResponseDTO update(NetRxDTO netRx);
	public NetRxListResponseDTO list(FilterDTO filter);
	public NetRxBranchListResponseDTO getNetRxBranchList(FilterDTO filter);
	public ResponseDTO  deleteNetRx(int netRxId);
	public NetRxViewResponseDTO viewNetRx(int netRxId);
	public ResponseDTO createUser(NetRxHeaderDTO header,NetRxUserDetail user);
	public ResponseDTO updateUser(NetRxHeaderDTO header,NetRxUserDetail user);
	public ResponseDTO deleteUser(int globalId);
	public ResponseDTO createBranch(NetRxBranchDetail branch);
	public NetRxBranchResponseDTO viewBranch(int netrxBranchId);
	public ResponseDTO deleteBranch(int globalId);
	public NetRxUserDTO viewUser(int globalId);
	public NetRxUserListResponseDTO listNetRxUser(FilterDTO filter);
	public ResponseDTO updateNetRxBranch(NetRxBranchDetail branch);
	public ResponseDTO forgotPassword(LoginDTO login);
	public ResponseDTO resetPassword(LoginDTO login);
	public ResponseDTO changePassword(PasswordDTO passwords);
	public ResponseDTO clearMacId(NetRxHeaderDTO header);
	public SyncFreqResponseDTO setNetRxSync(SyncFreqDTO sync);
	public SyncFreqResponseDTO setNetRxBranchSync(SyncFreqDTO sync);
	public SyncFreqDTO getBranchSyncDetails(int branchId);
	public SyncFreqDTO getNetrxSyncDetails(int netrxId);
				
}
