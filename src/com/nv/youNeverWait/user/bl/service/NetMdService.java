/**
 * NetMdService.java
 *
 * Jan 3, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.bl.service;


import java.util.Date;
import java.util.List;

import com.nv.youNeverWait.rs.dto.BillSummaryDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.NetMdActivationResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDetail;
import com.nv.youNeverWait.rs.dto.NetMdViewResponseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalUserResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveNetmdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveNetmdListResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;


public interface NetMdService {
	public ResponseDTO  createNetMd(NetMdDTO netMd);
	public ResponseDTO  updateNetMd(NetMdDTO netMd);
	public ResponseDTO  deleteNetMd(int netMdId);
	public NetMdViewResponseDTO  viewNetMd(int netMdId);
	public ResponseDTO createBranch(NetMdBranchDTO branch);
	public ResponseDTO  updateBranch(NetMdBranchDTO branch);
	public NetMdBranchResponseDTO viewBranch(int globalId);
	public ResponseDTO  deleteBranch(int globalId);
	public NetMdResponseDTO  getMacStatus(String passPhrase);
	public NetMdActivationResponseDTO activateNetMd(HeaderDTO header);
	public NetMdBranchListResponseDTO getBranchList(FilterDTO filterDTO);
	public ResponseDTO createUser(HeaderDTO header,NetMdUserDetail user);
	public RetrieveNetmdListResponseDTO retrieveNetmdList(String syncTime, Date currentTime);
	public RetrieveNetmdBranchListResponseDTO retrieveNetmdBranchList(String syncTime, Date currentTime);
	public NetMdListResponseDTO getNetMdList(FilterDTO filterDTO);
	public ResponseDTO updateUser(HeaderDTO header,NetMdUserDetail user);
	public ResponseDTO forgotPassword(LoginDTO login);
	public ResponseDTO resetPassword(LoginDTO login);
	public ResponseDTO changePassword(PasswordDTO password);
	public ResponseDTO deleteUser(int globalId);
	public NetMdUserDTO viewUser(int globalId);
	public RetrievalUserResponseDTO retrieveUserList(String lastSyncTime,String passPhrase, int netmdBranchId,Date currentSyncTime);
	public ResponseDTO clearMacId(HeaderDTO header);
	public ResponseDTO makePrimary(HeaderDTO header);
	/**
	 * @param newBill
	 * @param header
	 * @return
	 */
	public ResponseDTO createBill(BillSummaryDTO newBill, HeaderDTO header);
	/**
	 * @param updatedBill
	 * @param header
	 * @return
	 */
	public ResponseDTO updateBills(BillSummaryDTO updatedBill, HeaderDTO header);

	/**
	 * @param sync
	 * @return
	 */
	public ResponseDTO setNetMdSync(SyncFreqDTO sync);
	/**
	 * @param sync
	 * @return
	 */
	public ResponseDTO setNetMdBranchSync(SyncFreqDTO sync);
//	/**
//	 * @param sync
//	 * @return
//	 */
//	public ResponseDTO enableBranchSync(SyncFreqDTO sync);
//	/**
//	 * @param sync
//	 * @return
//	 */
//	public ResponseDTO enableSync(SyncFreqDTO sync);
	
	
}
