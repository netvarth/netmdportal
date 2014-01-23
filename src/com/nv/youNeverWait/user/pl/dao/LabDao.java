/**
 * LabDao.java
 *
 * Jan 3, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.pl.dao;


import java.util.Date;
import com.nv.youNeverWait.rs.dto.BranchOrderCountResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderDetail;
import com.nv.youNeverWait.rs.dto.BranchOrdersResponseDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LabBranchDTO;
import com.nv.youNeverWait.rs.dto.LabBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchSystemInfoDetails;
import com.nv.youNeverWait.rs.dto.LabBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.LabDTO;
import com.nv.youNeverWait.rs.dto.LabResponseDTO;
import com.nv.youNeverWait.rs.dto.LabResultHeaderDTO;
import com.nv.youNeverWait.rs.dto.LabUserDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultRetrievalResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultTransferDTO;
import com.nv.youNeverWait.rs.dto.ResultTransferResponseDTO;
import com.nv.youNeverWait.rs.dto.LabActivationResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveLabListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveUserListResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqResponseDTO;
import com.nv.youNeverWait.rs.dto.TransferNetMdResultDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.user.pl.impl.BranchOwnerDetails;

public interface LabDao {
	public ResponseDTO  create(LabDTO netLims);
	public ResponseDTO  update(LabDTO netLims);
	public ResponseDTO  delete(int netLimsId);
	public LabResponseDTO  view(int netLimsId);
	public ResponseDTO  createBranch(LabBranchDTO branch);
	public ResponseDTO  deleteBranch(LabBranchDTO branch);
	public ResponseDTO  updateBranch(LabBranchDTO branch);
	public LabBranchResponseDTO viewBranch(int globalId);
	public  String getMacByPassphrase(String passPhrase);
	public LabActivationResponseDTO activateLab(HeaderDTO header);
	public ResultTransferResponseDTO transferResult(ResultTransferDTO resultTranferDto);
	public ResultRetrievalResponseDTO getResult(HeaderDTO header, String lastSyncTime,Date currentTime);
	public String  checkMacStatus(String macId);
	public ResponseDTO changePassword(PasswordDTO passwords);
	public ResponseDTO clearMacId(LabBranchDTO branch);
	public LabBranchListResponseDTO retrieveLabBranchList(
			HeaderDTO header, String lastSyncTime,Date currentTime);
	public RetrieveLabListResponseDTO retrieveLabList(
			HeaderDTO header, String lastSyncTime,Date currentTime);
	public ResponseDTO createUser(LabUserDTO user);
	public ResponseDTO updateUser(LabUserDTO user);
	public ResponseDTO deleteUser(int id);
	public LabUserDTO viewUser(int globalId);
	public RetrieveUserListResponseDTO retrieveUserList(HeaderDTO header, String lastSyncTime,Date currentTime);
	public UserCredentials getUserCredentials(LoginDTO login);
	public String getLabName(int labId);
	public BranchOwnerDetails getBranchOwners(int labId);
	public ResponseDTO resetPassword(LoginDTO login);
	public ResultTransferResponseDTO transferResultToNetMd(TransferNetMdResultDTO resultTranfer);
	public void CheckHeaderDetails(HeaderDTO header);
	public BranchOrdersResponseDTO viewBranchOrders(int globalId);
	public BranchOrderCountResponseDTO createTotalOrders(HeaderDTO header,
			BranchOrderDetail branchOrders);
	public BranchSystemInfoDetails viewBranchSystemInfoDetails(int branchId);
	public ResponseDTO updateLabBranchSystemInfo(
			BranchSystemInfoDetails details);
	public LabDTO getLab(HeaderDTO header, String lastSyncTime,
			Date currentSyncTime);
	public SyncFreqResponseDTO setBranchSync(SyncFreqDTO sync);
	public SyncFreqResponseDTO setLabSync(SyncFreqDTO sync);
	public SyncFreqDTO getLabSyncDetails(int labId);
	public SyncFreqDTO getBranchSyncDetails(int branchId);
	public ResponseDTO saveResult(LabResultHeaderDTO labResultHeader);

}
