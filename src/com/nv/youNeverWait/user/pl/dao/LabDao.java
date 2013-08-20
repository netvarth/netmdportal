/**
 * NetLimsDao.java
 *
 * Jan 3, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.pl.dao;


import java.util.Date;

import com.nv.youNeverWait.rs.dto.BranchOrderCountResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderDetail;
import com.nv.youNeverWait.rs.dto.BranchOrdersResponseDTO;
import com.nv.youNeverWait.rs.dto.HealthMonitorResponse;
import com.nv.youNeverWait.rs.dto.LabBranchDTO;
import com.nv.youNeverWait.rs.dto.LabBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.LabBranchSystemInfoDetails;
import com.nv.youNeverWait.rs.dto.LabHeaderDTO;
import com.nv.youNeverWait.rs.dto.LabBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.LabDTO;
import com.nv.youNeverWait.rs.dto.LabResponseDTO;
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
import com.nv.youNeverWait.rs.dto.SystemHealthDetails;
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
	public LabActivationResponseDTO activateLab(LabHeaderDTO header);
	public ResultTransferResponseDTO transferResult(ResultTransferDTO resultTranferDto);
	public ResultRetrievalResponseDTO getResult(LabHeaderDTO header, String lastSyncTime,Date currentTime);
	public String  checkMacStatus(String macId);
	public ResponseDTO changePassword(PasswordDTO passwords);
	public ResponseDTO clearMacId(LabBranchDTO branch);
	public LabBranchListResponseDTO retrieveLabBranchList(
			LabHeaderDTO header, String lastSyncTime,Date currentTime);
	public RetrieveLabListResponseDTO retrieveLabList(
			LabHeaderDTO header, String lastSyncTime,Date currentTime);
	public ResponseDTO createUser(LabUserDTO user);
	public ResponseDTO updateUser(LabUserDTO user);
	public ResponseDTO deleteUser(int id);
	public LabUserDTO viewUser(int globalId);
	public RetrieveUserListResponseDTO retrieveUserList(LabHeaderDTO header, String lastSyncTime,Date currentTime);
	public UserCredentials getUserCredentials(LoginDTO login);
	public String getLabName(int labId);
	public BranchOwnerDetails getBranchOwners(int labId);
	public ResponseDTO resetPassword(LoginDTO login);
	public ResultTransferResponseDTO transferResultToNetMd(TransferNetMdResultDTO resultTranfer);
	public void validateHeader(LabHeaderDTO header);
	public BranchOrdersResponseDTO viewBranchOrders(int globalId);
	public BranchOrdersResponseDTO orderList(BranchOrderDTO orderDTO);
	public BranchOrderCountResponseDTO createTotalOrders(LabHeaderDTO header,
			BranchOrderDetail branchOrders);
	
	/**
	 * @param systemHealthDetails
	 * @return
	 */
	 public HealthMonitorResponse healthMonitorResponse(
			SystemHealthDetails systemHealthDetails);
	public LabBranchSystemInfoDetails viewBranchsystemInfoDetails(int branchId);
	public ResponseDTO updateLabBranchSystemInfo(
			LabBranchSystemInfoDetails details);
	
	
	
}
