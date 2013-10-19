/**
 * LabService.java
 *
 * Jan 3, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.bl.service;

import java.util.Date;

import com.nv.youNeverWait.rs.dto.BranchOrderCountResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderDetail;
import com.nv.youNeverWait.rs.dto.BranchOrdersResponseDTO;
import com.nv.youNeverWait.rs.dto.HealthMonitorResponse;
import com.nv.youNeverWait.rs.dto.LabBranchDTO;
import com.nv.youNeverWait.rs.dto.LabBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchSystemInfoDetails;
import com.nv.youNeverWait.rs.dto.LabHeaderDTO;
import com.nv.youNeverWait.rs.dto.LabActivationResponseDTO;
import com.nv.youNeverWait.rs.dto.LabUserDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.MacStatusResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.LabBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.LabDTO;
import com.nv.youNeverWait.rs.dto.LabListResponseDTO;
import com.nv.youNeverWait.rs.dto.LabResponseDTO;
import com.nv.youNeverWait.rs.dto.OrderDetails;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
import com.nv.youNeverWait.rs.dto.OrderTransferResponse;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultRetrievalResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultTransferDTO;
import com.nv.youNeverWait.rs.dto.ResultTransferResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveLabListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveNetmdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveNetmdListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveUserListResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqResponseDTO;
import com.nv.youNeverWait.rs.dto.SystemHealthDetails;
import com.nv.youNeverWait.rs.dto.TransferNetMdResultDTO;




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
	public LabActivationResponseDTO activateLab(LabHeaderDTO header);
	public ResultRetrievalResponseDTO getResult(LabHeaderDTO header, String lastSyncTime,Date currentTime);
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
	public ResponseDTO forgotPassword(LoginDTO login);
	public ResponseDTO resetPassword(LoginDTO login);
	public ResultTransferResponseDTO transferResultToNetMd(TransferNetMdResultDTO resultTranfer);
	public RetrieveNetmdListResponseDTO retrieveNetmdList(LabHeaderDTO header, String lastSyncTime,Date currentTime);
	public RetrieveNetmdBranchListResponseDTO retrieveNetmdBranchList(LabHeaderDTO header, String lastSyncTime,Date currentTime);
	public BranchOrdersResponseDTO viewBranchOrders(int globalId);
	public BranchOrdersResponseDTO orderList(BranchOrderDTO orderDTO);
	public BranchOrderCountResponseDTO createTotalOrders(LabHeaderDTO header,
			BranchOrderDetail branchOrders);
	public HealthMonitorResponse checkSystemHealth(SystemHealthDetails systemHealthDetails);
	public BranchSystemInfoDetails viewBranchSystemInfoDetails(int branchId);
	public ResponseDTO updateLabBranchSystemInfo(
			BranchSystemInfoDetails details);
	
	/**
	 * @param header
	 * @param lastSyncTime
	 * @param currentSyncTime
	 * @return
	 */
	public OrderDetails retrieveBranchOrders(LabHeaderDTO header,
			String lastSyncTime, Date currentSyncTime);
	
	/**
	 * @param orderTranferDto
	 * @return
	 */
	public OrderTransferResponse transferOrder(OrderTransfer orderTranferDto);
	/**
	 * @param header
	 * @param lastSyncTime
	 * @param currentSyncTime
	 * @return
	 */
	public LabDTO getLab(LabHeaderDTO header, String lastSyncTime,
			Date currentSyncTime);

	/**
	 * @param sync
	 * @return
	 */
	public SyncFreqResponseDTO setBranchSync(SyncFreqDTO sync);
	/**
	 * @param sync
	 * @return
	 */
	public SyncFreqResponseDTO setLabSync(SyncFreqDTO sync);
//	/**
//	 * @param sync
//	 * @return
//	 */
//	public ResponseDTO enableLabSync(SyncFreqDTO sync);
//	/**
//	 * @param sync
//	 * @return
//	 */
//	public ResponseDTO enableBranchSync(SyncFreqDTO sync);
	/**
	 * @param labId
	 * @return
	 */
	public SyncFreqDTO getLabSyncDetails(int labId);
	/**
	 * @param branchId
	 * @return
	 */
	public SyncFreqDTO getBranchSyncDetails(int branchId);
}
