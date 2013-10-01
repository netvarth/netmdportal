/**
 * SuperAdminService.java
 *
 * Jan 2, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.bl.service;

import javax.servlet.http.HttpServletRequest;

import com.nv.youNeverWait.rs.dto.BranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderDTO;
import com.nv.youNeverWait.rs.dto.BranchOrdersResponseDTO;

import com.nv.youNeverWait.rs.dto.EnableLogStatusResponseDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LabBranchDTO;
import com.nv.youNeverWait.rs.dto.LabBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.LabBranchSystemInfoDetails;
import com.nv.youNeverWait.rs.dto.LabListResponseDTO;
import com.nv.youNeverWait.rs.dto.LogDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.LabDTO;
import com.nv.youNeverWait.rs.dto.LabResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdDTO;
import com.nv.youNeverWait.rs.dto.NetMdListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDTO;
import com.nv.youNeverWait.rs.dto.NetMdViewResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchDetail;
import com.nv.youNeverWait.rs.dto.NetRxBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxDTO;
import com.nv.youNeverWait.rs.dto.NetRxListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxViewResponseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.SpecimenListResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncLogDTO;
import com.nv.youNeverWait.rs.dto.TestListResponseDTO;
import com.nv.youNeverWait.rs.dto.UserDetails;
import com.nv.youNeverWait.rs.dto.UserLogListResponseDTO;

/**
 * 
 */
public interface SuperAdminService {

	public ResponseDTO  createNetMd(NetMdDTO netMd);
	public ResponseDTO  updateNetMd(NetMdDTO netMd);
	public ResponseDTO  deleteNetMd(int netMdId);
	public NetMdViewResponseDTO  viewNetMd(int netMdId);
	public ResponseDTO  createLab(LabDTO lab);
	public ResponseDTO  updateLab(LabDTO lab);
	public ResponseDTO  deleteLab(int labId);
	public LabResponseDTO viewLab(int labId);
	public LoginResponseDTO login(LoginDTO login);
	public UserDetails getUser(String userName);
	public ResponseDTO changePassword(PasswordDTO passwords);
	public ResponseDTO forgotPassword(LoginDTO login);
	public ResponseDTO resetPassword(LoginDTO login);
	public ResponseDTO makePrimary(HeaderDTO header);
	public LabListResponseDTO labList(FilterDTO filter);
	public ResponseDTO  createBranch(LabBranchDTO branch);
	public LabBranchResponseDTO viewBranch(int globalId);
	public ResponseDTO  deleteBranch(LabBranchDTO branch);
	public ResponseDTO  updateBranch(LabBranchDTO branch);
	public BranchListResponseDTO branchList(FilterDTO filter);
	public NetMdListResponseDTO getNetMdList(FilterDTO filter);
	public NetMdBranchListResponseDTO getBranchList(FilterDTO filter);
	public NetMdBranchResponseDTO viewNetMdBranch(int netMdBranchId);
	public ResponseDTO deleteNetMdBranch(int netMdBranchId);
	public ResponseDTO createNetMdBranch(NetMdBranchDTO branch);
	public ResponseDTO updateNetMdBranch(NetMdBranchDTO branch);
	public ResponseDTO updateNetMdUser(NetMdUserDTO netMdUser);
	public ResponseDTO enableLog(LogDTO log, HttpServletRequest request);
	public UserLogListResponseDTO userLogList(FilterDTO filter);
	public ResponseDTO clearNetMdMacId(HeaderDTO header);
	public ResponseDTO clearNetlimsMacId(LabBranchDTO branch);
	public ResponseDTO createNetRx(NetRxDTO netRx);
	public ResponseDTO updateNetRx(NetRxDTO netRx);
	public NetRxListResponseDTO getNetRxList(FilterDTO filter);
	public ResponseDTO  deleteNetRx(int netRxId);
	public NetRxBranchListResponseDTO getNetRxBranchList(FilterDTO filter);
	public ResponseDTO createNetRxBranch(NetRxBranchDetail branch);
	public ResponseDTO deleteNetRxBranch(int globalId);
	public NetRxViewResponseDTO viewNetRx(int netRxId);
	public NetRxBranchResponseDTO viewNetRxBranch(int netrxBranchId);
	public ResponseDTO updateNetRxBranch(NetRxBranchDetail branch);
	public EnableLogStatusResponseDTO enableLogStatus();
	public BranchOrdersResponseDTO viewBranchOrders(int globalId);
	public BranchOrdersResponseDTO orderList(BranchOrderDTO orderDTO);
	public LabBranchSystemInfoDetails viewBranchSystemInfo(int branchId);
	public ResponseDTO updateLabBranchSystemInfo(
			LabBranchSystemInfoDetails details);
	
	public TestListResponseDTO testList(FilterDTO filter);
	/**
	 * @param filter
	 * @return
	 */
	public SpecimenListResponseDTO testSpecimenList(FilterDTO filter);
	/**
	 * @param syncLog
	 * @param request
	 * @return
	 */
	public ResponseDTO enableSyncLog(SyncLogDTO syncLog, HttpServletRequest request);
	

}
