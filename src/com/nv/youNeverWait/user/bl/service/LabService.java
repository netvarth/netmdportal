/**
 * LabService.java
 *
 * Jan 3, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.bl.service;

import java.util.Date;

import com.nv.security.youNeverWait.User;
import com.nv.youNeverWait.rs.dto.BranchFacilityListResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderCountResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderDetail;
import com.nv.youNeverWait.rs.dto.BranchOrdersResponseDTO;
import com.nv.youNeverWait.rs.dto.FacilityListResponseDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.HealthMonitorResponse;
import com.nv.youNeverWait.rs.dto.LabBranchDTO;
import com.nv.youNeverWait.rs.dto.LabBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchSystemInfoDetails;
import com.nv.youNeverWait.rs.dto.LabActivationResponseDTO;
import com.nv.youNeverWait.rs.dto.LabOrderHeaderDTO;
import com.nv.youNeverWait.rs.dto.LabResultHeaderDTO;
import com.nv.youNeverWait.rs.dto.LabUserDTO;
import com.nv.youNeverWait.rs.dto.ListResponse;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.MacStatusResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.LabBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.LabDTO;
import com.nv.youNeverWait.rs.dto.LabListResponseDTO;
import com.nv.youNeverWait.rs.dto.LabResponseDTO;
import com.nv.youNeverWait.rs.dto.MailTransferInfo;
import com.nv.youNeverWait.rs.dto.OrderDetails;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
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
import com.nv.youNeverWait.rs.dto.SyncUser;
import com.nv.youNeverWait.rs.dto.SystemHealthDetails;
import com.nv.youNeverWait.rs.dto.TransferNetMdResultDTO;
import com.nv.youNeverWait.rs.dto.TransferredDetails;
import com.nv.youNeverWait.rs.dto.TransferredResultDetails;
import com.nv.youNeverWait.rs.dto.UserInfo;




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
	public ResultTransferResponseDTO transferResultToNetMd(TransferNetMdResultDTO resultTranfer);
	public RetrieveNetmdListResponseDTO retrieveNetmdList(HeaderDTO header, String lastSyncTime,Date currentTime);
	public RetrieveNetmdBranchListResponseDTO retrieveNetmdBranchList(HeaderDTO header, String lastSyncTime,Date currentTime);
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
	public ResponseDTO sendMail(MailTransferInfo mailResult);
	public int processUser(SyncUser user, Integer source_branch_id);
	public UserInfo getUserByReferredUid(int referredUid, int branchId);
	public ListResponse getFacilityByFilter(
			FilterDTO filterDTO, User user);
	
}
