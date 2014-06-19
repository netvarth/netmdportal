/**
 * LabService.java
 *
 * Jan 3, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.NetlimsPortal.bl.service;

import java.util.Date;

import com.nv.NetlimsPortal.rs.dto.BranchListResponseDTO;
import com.nv.NetlimsPortal.rs.dto.BranchOrderCountResponseDTO;
import com.nv.NetlimsPortal.rs.dto.BranchOrderDetail;
import com.nv.NetlimsPortal.rs.dto.BranchOrdersResponseDTO;
import com.nv.NetlimsPortal.rs.dto.BranchSystemInfoDetails;
import com.nv.NetlimsPortal.rs.dto.FilterDTO;
import com.nv.NetlimsPortal.rs.dto.HeaderDTO;
import com.nv.NetlimsPortal.rs.dto.HealthMonitorResponse;
import com.nv.NetlimsPortal.rs.dto.LabActivationResponseDTO;
import com.nv.NetlimsPortal.rs.dto.LabBranchDTO;
import com.nv.NetlimsPortal.rs.dto.LabBranchListResponseDTO;
import com.nv.NetlimsPortal.rs.dto.LabBranchResponseDTO;
import com.nv.NetlimsPortal.rs.dto.LabDTO;
import com.nv.NetlimsPortal.rs.dto.LabListResponseDTO;
import com.nv.NetlimsPortal.rs.dto.LabOrderHeaderDTO;
import com.nv.NetlimsPortal.rs.dto.LabResponseDTO;
import com.nv.NetlimsPortal.rs.dto.LabResultHeaderDTO;
import com.nv.NetlimsPortal.rs.dto.LabUserDTO;
import com.nv.NetlimsPortal.rs.dto.LoginDTO;
import com.nv.NetlimsPortal.rs.dto.MacStatusResponseDTO;
import com.nv.NetlimsPortal.rs.dto.OrderDetails;
import com.nv.NetlimsPortal.rs.dto.OrderTransfer;
import com.nv.NetlimsPortal.rs.dto.PasswordDTO;
import com.nv.NetlimsPortal.rs.dto.ResponseDTO;
import com.nv.NetlimsPortal.rs.dto.ResultRetrievalResponseDTO;
import com.nv.NetlimsPortal.rs.dto.ResultTransferDTO;
import com.nv.NetlimsPortal.rs.dto.ResultTransferResponseDTO;
import com.nv.NetlimsPortal.rs.dto.RetrieveLabListResponseDTO;
import com.nv.NetlimsPortal.rs.dto.RetrieveUserListResponseDTO;
import com.nv.NetlimsPortal.rs.dto.SyncFreqDTO;
import com.nv.NetlimsPortal.rs.dto.SyncFreqResponseDTO;
import com.nv.NetlimsPortal.rs.dto.SystemHealthDetails;
import com.nv.NetlimsPortal.rs.dto.TransferredDetails;
import com.nv.NetlimsPortal.rs.dto.TransferredResultDetails;


public interface LabService {
	public ResponseDTO  create(LabDTO lab);
	public ResponseDTO  update(LabDTO lab);
	public ResponseDTO  delete(int labId);
	public LabResponseDTO  view(int labId);
	public LabListResponseDTO list(FilterDTO filter);
	public ResponseDTO  createBranch(LabBranchDTO branch);
	public ResponseDTO  deleteBranch(LabBranchDTO branch);
	public ResponseDTO  updateBranch(LabBranchDTO branch);
	public LabBranchResponseDTO viewBranch(int globalId);
	public BranchListResponseDTO branchList(FilterDTO filter);
	public ResultTransferResponseDTO transferResult(ResultTransferDTO resultTranferDto);
	public MacStatusResponseDTO  getMacStatus(String passPhrase);
	public LabActivationResponseDTO activateLab(HeaderDTO header);
	public ResultRetrievalResponseDTO getResult(HeaderDTO header, String lastSyncTime,Date currentTime);
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
	public ResponseDTO forgotPassword(LoginDTO login);
	public ResponseDTO resetPassword(LoginDTO login);
	public BranchOrdersResponseDTO viewBranchOrders(int globalId);
	public BranchOrdersResponseDTO orderList(FilterDTO filterDTO);
	public BranchOrderCountResponseDTO createTotalOrders(HeaderDTO header,
			BranchOrderDetail branchOrders);
	public HealthMonitorResponse checkSystemHealth(SystemHealthDetails systemHealthDetails);
	public BranchSystemInfoDetails viewBranchSystemInfoDetails(int branchId);
	public ResponseDTO updateLabBranchSystemInfo(
			BranchSystemInfoDetails details);
	public OrderDetails retrieveBranchOrders(LabOrderHeaderDTO orderHeader);
	public ResponseDTO transferOrder(OrderTransfer orderTranferDto);
	public LabDTO getLab(HeaderDTO header, String lastSyncTime,
			Date currentSyncTime);
	public SyncFreqResponseDTO setBranchSync(SyncFreqDTO sync);
	public SyncFreqResponseDTO setLabSync(SyncFreqDTO sync);
	public SyncFreqDTO getLabSyncDetails(int labId);
	public SyncFreqDTO getBranchSyncDetails(int branchId);
	public SyncFreqDTO syncEnableStatus(HeaderDTO header, String freqType,
			int interval);
	public ResponseDTO saveResult(LabResultHeaderDTO labResultHeader);
	public TransferredDetails getTransferredOrders(FilterDTO filterDTO);
	public TransferredResultDetails getTransferredResults(FilterDTO filterDTO);
	
}
