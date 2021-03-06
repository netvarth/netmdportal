/**
 * SuperAdminResource.java
 *
 * Jan 2, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.portal.rs.superadmin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nv.security.youNeverWait.User;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ApplicationNameEnum;
import com.nv.youNeverWait.pl.entity.LogUserTypeEnum;
import com.nv.youNeverWait.rs.dto.BranchBillListResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchOrdersResponseDTO;
import com.nv.youNeverWait.rs.dto.EnableLogStatusResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LabBranchDTO;
import com.nv.youNeverWait.rs.dto.LabBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchSystemInfoDetails;
import com.nv.youNeverWait.rs.dto.LabListResponseDTO;
import com.nv.youNeverWait.rs.dto.LogDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.LabDTO;
import com.nv.youNeverWait.rs.dto.LabResponseDTO;
import com.nv.youNeverWait.rs.dto.MailInfo;
import com.nv.youNeverWait.rs.dto.NetMdBranchDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdDTO;
import com.nv.youNeverWait.rs.dto.NetMdListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdViewResponseDTO;
import com.nv.youNeverWait.rs.dto.NetPosDTO;
import com.nv.youNeverWait.rs.dto.NetPosListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetPosViewResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchDetail;
import com.nv.youNeverWait.rs.dto.NetRxBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxDTO;
import com.nv.youNeverWait.rs.dto.NetRxListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxViewResponseDTO;
import com.nv.youNeverWait.rs.dto.Organisation;
import com.nv.youNeverWait.rs.dto.OrganisationListResponseDTO;
import com.nv.youNeverWait.rs.dto.OrganisationUserDetail;
import com.nv.youNeverWait.rs.dto.OrganisationUsersList;
import com.nv.youNeverWait.rs.dto.OrganizationViewResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.SpecimenListResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncLogListResponseDTO;
import com.nv.youNeverWait.rs.dto.TestListResponseDTO;
import com.nv.youNeverWait.rs.dto.UserDetails;
import com.nv.youNeverWait.rs.dto.UserLogListResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncLogDTO;
import com.nv.youNeverWait.rs.dto.ViewOrganisationUser;
import com.nv.youNeverWait.user.bl.service.LogService;
import com.nv.youNeverWait.user.bl.service.SuperAdminService;

@Controller
@RequestMapping("ui/superAdmin/")
public class SuperAdminResource {
	private SuperAdminService service;
	private LogService logService;
	
	/*@RequestMapping(value = "sForm", method = RequestMethod.GET)
	public String sForm() {
		return "superadminLoginPage";
	}*/

	@RequestMapping(value = "startUp", method = RequestMethod.GET)
	public String check() {
		return "index";
	}

	/**
	 * To enable/disable log process
	 * 
	 * @param log
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "enableLog", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO enableLog(@RequestBody LogDTO log) {

		ResponseDTO response = new ResponseDTO();
		ServletRequestAttributes t = 

(ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		try {
			response = service.enableLog(log, request);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * To get the status of user enable log
	 * 
	 */
	@RequestMapping(value = "enableLogStatus", method = RequestMethod.GET)
	@ResponseBody
	public EnableLogStatusResponseDTO enableLogStatus() {
		EnableLogStatusResponseDTO response= new EnableLogStatusResponseDTO();
		try{
			response=service.enableLogStatus();
		}catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		return response;
	}


	/**
	 * To get the status of user enable log
	 * 
	 */
	@RequestMapping(value = "getSyncLogStatus", method = 

RequestMethod.GET)
	@ResponseBody
	public EnableLogStatusResponseDTO getSyncLogStatus() {
		EnableLogStatusResponseDTO response= new EnableLogStatusResponseDTO();
		try{
			response=service.getSyncLogStatus();
		}catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		return response;
	}

	/**
	 * To show all the total orders and its related details of each branch 

in the lab
	 * 
	 * @param globalId
	 * @return BranchOrdersResponseDTO
	 */
	@RequestMapping(value = "viewBranchOrders/{globalId}", method = RequestMethod.GET)
	@ResponseBody
	public BranchOrdersResponseDTO viewBranchOrders(@PathVariable int globalId) {
		BranchOrdersResponseDTO response= new BranchOrdersResponseDTO();
		try{
			response=service.viewBranchOrders(globalId);
		}catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		return response;
	}

	/**
	 * List all records of Report Tbl for given From and To date
	 * @param report
	 * @return ReportResponseDTO
	 */
	@RequestMapping(value = "orderList", method = RequestMethod.POST)
	@ResponseBody
	public BranchOrdersResponseDTO orderList(@RequestBody FilterDTO 

filterDTO){
		BranchOrdersResponseDTO response=	new BranchOrdersResponseDTO();	
		try{
			response=service.orderList(filterDTO);	
		}
		catch(ServiceException e){
			List<Parameter> parameters =e.getParamList();
			ErrorDTO error=new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * Updates netmd branch details
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateNetMdBranch", method = 

RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateNetMdBranch(@RequestBody NetMdBranchDTO branch) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.updateNetMdBranch(branch);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		return response;
	}

	/**
	 * Updates netRx branch details
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateNetRxBranch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateNetRxBranch(@RequestBody NetRxBranchDetail branch) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.updateNetRxBranch(branch);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		
		return response;
	}
	
	
	/**
	 * To reset password of superAdmin
	 * 
	 * @param header
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "makePrimary", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO makePrimary(@RequestBody HeaderDTO header) {

		ResponseDTO response = new ResponseDTO();
		try {
			response = service.makePrimary(header);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	
	
	/**
	 * Creates a NetMd account
	 * @param netMd
	 * @return
	 */
	@RequestMapping(value = "createNetMd", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createNetMd(@RequestBody NetMdDTO netMd) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.createNetMd(netMd);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}
		

		

	/**
	 * Create netRx account
	 * @param netRx
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "createNetRx", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createNetRx(@RequestBody NetRxDTO netRx) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.createNetRx(netRx);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;

	}
	/**
	 * Update netRx account
	 * @param netRx
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateNetRx", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateNetRx(@RequestBody NetRxDTO netRx) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.updateNetRx(netRx);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;

	}
	
	/**
	 * Deletes a netRx 
	 * 
	 * @param netRxId
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "deleteNetRx/{netRxId}", method = 

RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteNetRx(@PathVariable int netRxId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.deleteNetRx(netRxId);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * To retrieve a list of netRx branches which satisfy all filter 

conditions
	 * 
	 * @param filter
	 * @return NetMdBranchListResponseDTO
	 */
	@RequestMapping(value = "netRxBranchList", method = 

RequestMethod.POST)
	@ResponseBody
	public NetRxBranchListResponseDTO getNetRxBranchList(
			@RequestBody FilterDTO filter) {
		NetRxBranchListResponseDTO response = new 

NetRxBranchListResponseDTO();
		try {
			response = service.getNetRxBranchList(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		
		return response;
	}
	
	/**
	 * To retrieve a list of netmd which satisfy all filter conditions
	 * 
	 * @param filter
	 * @return NetMdListResponseDTO
	 */
	@RequestMapping(value = "netrxList", method = RequestMethod.POST)
	@ResponseBody
	public NetRxListResponseDTO getNetRxList(@RequestBody FilterDTO 

filter) {
		NetRxListResponseDTO response = new NetRxListResponseDTO();
		try {
			response = service.getNetRxList(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	/**
	 * Creates a netRx branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "createNetRxBranch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createNetRxBranch(@RequestBody NetRxBranchDetail branch) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.createNetRxBranch(branch);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		return response;
	}
	
	/**
	 * Create Lab
	 * 
	 * @param lab
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "createLab", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createLab(@RequestBody LabDTO lab) {

		ResponseDTO response = new ResponseDTO();
		try {
			response = service.createLab(lab);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;

	}

	/**
	 * Update Lab
	 * 
	 * @param lab
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateLab", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateLab(@RequestBody LabDTO lab) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.updateLab(lab);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;

	}

	/**
	 * Delete lab
	 * 
	 * @param labId
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "deleteLab/{labId}", method = 

RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteLab(@PathVariable int labId) {
		System.out.println("deletelab....");
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.deleteLab(labId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;

	}
	/**
	 * Deletes a netRx branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "deleteNetRxBranch/{globalId}", method = 

RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteNetRxBranch(@PathVariable int globalId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.deleteNetRxBranch(globalId);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		return response;
	}


	/**

	 * View netRx account
	 * 
	 * @param netRxId
	 * @return NetRxViewResponseDTO
	 */
	@RequestMapping(value = "viewNetRx/{netRxId}", method = 

RequestMethod.GET)
	@ResponseBody
	public NetRxViewResponseDTO viewNetRx(@PathVariable int netRxId) {
		NetRxViewResponseDTO response = new NetRxViewResponseDTO();
		try {
			response = service.viewNetRx(netRxId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	
	/**
	 * View netrx branch details
	 * 
	 * @param globalId
	 * @return NetRxBranchResponseDTO
	 */
	@RequestMapping(value = "viewNetRxBranch/{netrxBranchId}", method = 

RequestMethod.GET)
	@ResponseBody
	public NetRxBranchResponseDTO viewNetRxBranch(@PathVariable int 

netrxBranchId) {
		NetRxBranchResponseDTO response = new 

NetRxBranchResponseDTO();
		try {
			response = service.viewNetRxBranch(netrxBranchId);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		
		return response;
	}


	/**
	 * View lab details
	 * 
	 * @param labId
	 * @return LabResponseDTO
	 */
	@RequestMapping(value = "viewLab/{labId}", method = RequestMethod.GET)
	@ResponseBody
	public LabResponseDTO viewLab(@PathVariable int labId) {
		System.out.println("viewLab....");
		LabResponseDTO response = new LabResponseDTO();
		try {
			response = service.viewLab(labId);

		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;

	}

	/**
	 * Checks for the authentication of super admin
	 * 
	 * @param login
	 * @return LoginResponseDTO
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponseDTO login(@RequestBody LoginDTO login) {
		LoginResponseDTO response = new LoginResponseDTO();
		try {

			response = service.login(login);
			if (response.isSuccess()) {
				ServletRequestAttributes t = (ServletRequestAttributes) 

RequestContextHolder.currentRequestAttributes();
				HttpServletRequest req = t.getRequest();
				logService.saveUserDetails(req.getRemoteAddr(), 

null,LogUserTypeEnum.Nil.getDisplayName(), null, 

null,ApplicationNameEnum.SuperAdmin.getDisplayName(),Constants.SUPER_ADMIN_LOGIN);
				User user = new User();
				UserDetails userDetail = service.getUser(login.getUserName());
				if (userDetail != null) {
					user.setLoginTime(new Date());
					user.setName(userDetail.getName());
					user.setUserName(login.getUserName().trim());
					user.setId(userDetail.getId());
				}
				req.getSession().setAttribute(Constants.USER, user);
			}
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * To retrieve a list of netmd which satisfy all filter conditions
	 * 
	 * @param filter
	 * @return NetMdListResponseDTO
	 */
	@RequestMapping(value = "netmdList", method = RequestMethod.POST)
	@ResponseBody
	public NetMdListResponseDTO getNetMdList(@RequestBody FilterDTO filter) {
		NetMdListResponseDTO response = new NetMdListResponseDTO();
		try {
			response = service.getNetMdList(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * To retrieve a list of clinics which satisfy all filter conditions
	 * 
	 * @param filter
	 * @return NetMdBranchListResponseDTO
	 */
	@RequestMapping(value = "netmdBranchList", method = RequestMethod.POST)
	@ResponseBody
	public NetMdBranchListResponseDTO getNetmdBranchList(
			@RequestBody FilterDTO filter) {
		NetMdBranchListResponseDTO response = new NetMdBranchListResponseDTO();
		try {
			response = service.getBranchList(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * Shows a list of all labs
	 * 
	 * @param filter
	 * @return LabListResponseDTO
	 */
	@RequestMapping(value = "labList", method = RequestMethod.POST)
	@ResponseBody
	public LabListResponseDTO labList(@RequestBody FilterDTO filter) {
		LabListResponseDTO response = new LabListResponseDTO();
		try {
			response = service.labList(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * Creates a branch for a lab
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "createBranch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createBranch(@RequestBody LabBranchDTO branch) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.createBranch(branch);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * View details of a lab branch
	 * 
	 * @param globalId
	 * @return BranchResponseDTO
	 */
	@RequestMapping(value = "viewBranch/{globalId}", method = 

RequestMethod.GET)
	@ResponseBody
	public LabBranchResponseDTO viewBranch(@PathVariable int globalId) {
		LabBranchResponseDTO response = new LabBranchResponseDTO();
		try {
			response = service.viewBranch(globalId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * Deletes a branch of a lab
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "deleteBranch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO deleteBranch(@RequestBody LabBranchDTO branch) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.deleteBranch(branch);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}

	/**
	 * Updates details of a lab branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateBranch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateBranch(@RequestBody LabBranchDTO branch) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.updateBranch(branch);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * Shows a list of all lab branches
	 * 
	 * @param filter
	 * @return BranchListResponseDTO
	 */
	@RequestMapping(value = "branchList", method = RequestMethod.POST)
	@ResponseBody
	public BranchListResponseDTO branchList(@RequestBody FilterDTO filter) 

{
		BranchListResponseDTO response = new BranchListResponseDTO();
		try {
			response = service.branchList(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * Filter method for getting list of bills in netmd
	 * @param filter
	 * @return BranchBillListResponseDTO
	 */
	@RequestMapping(value = "billList", method = RequestMethod.POST)
	@ResponseBody
	public BranchBillListResponseDTO billList(@RequestBody FilterDTO 

filter) {

		BranchBillListResponseDTO response=	new 

BranchBillListResponseDTO();	
		try{
			response=service.billList(filter);
		}
		catch(ServiceException e){
			List<Parameter> parameters =e.getParamList();
			ErrorDTO error=new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * Update netmd account
	 * 
	 * @param netMd
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateNetMd", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateNetMd(@RequestBody NetMdDTO netMd) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.updateNetMd(netMd);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	
	

	/**
	 * View netmd account
	 * 
	 * @param netMdId
	 * @return NetMdViewResponseDTO
	 */
	@RequestMapping(value = "viewNetMd/{netMdId}", method = RequestMethod.GET)
	@ResponseBody
	public NetMdViewResponseDTO viewNetMd(@PathVariable int netMdId) {
		NetMdViewResponseDTO response = new NetMdViewResponseDTO();
		try {
			response = service.viewNetMd(netMdId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * Creates a netmd branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "createNetMdBranch", method = 

RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createBranch(@RequestBody NetMdBranchDTO branch) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.createNetMdBranch(branch);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * View netmd branch details
	 * 
	 * @param globalId
	 * @return BranchResponseDTO
	 */
	@RequestMapping(value = "viewNetMdBranch/{netmdBranchId}", method = 

RequestMethod.GET)
	@ResponseBody
	public NetMdBranchResponseDTO viewNetMdBranch(
			@PathVariable int netmdBranchId) {
		NetMdBranchResponseDTO response = new 

NetMdBranchResponseDTO();
		try {
			response = service.viewNetMdBranch(netmdBranchId);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	
	
	/**
	 * Deletes a netmd branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "deleteNetMdBranch/{netmdBranchId}", method = 

RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteBranch(@PathVariable int netmdBranchId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.deleteNetMdBranch(netmdBranchId);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * Deletes a netmd
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "deleteNetMd/{globalId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteNetMd(@PathVariable int globalId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.deleteNetMd(globalId);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	
	
	
	
	/**
	 * To clear mac Id for the purpose of uninstalling netMd application 

from
	 * device
	 * 
	 * @param HeaderDTO
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "clearNetMdMacId", method = 

RequestMethod.POST)
	@ResponseBody
	public ResponseDTO clearNetMdMacId(@RequestBody HeaderDTO header) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.clearNetMdMacId(header);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	/**
	 * To clear mac Id for the purpose of uninstalling netlims application 

from
	 * device
	 * 
	 * @param LabBranchDTO
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "clearNetlimsMacId", method = 

RequestMethod.POST)
	@ResponseBody
	public ResponseDTO clearNetlimsMacId(@RequestBody LabBranchDTO branch) 

{
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.clearNetlimsMacId(branch);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * Method for viewing branch default system details
	 * @param branchId
	 * @return
	 */
	@RequestMapping (value="viewBranchSystemInfo/{branchId}", 

method=RequestMethod.GET)
	@ResponseBody
	public BranchSystemInfoDetails viewBranchSystemInfo(@PathVariable int branchId){
		BranchSystemInfoDetails details= new BranchSystemInfoDetails();
		try{
			details= service.viewBranchSystemInfo(branchId);
		}
		catch(ServiceException e){
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			details.setError(error);
			details.setSuccess(false);
		}
		return details;
		
	}
	
	/**
	 * Method for viewing branch default system details
	 * @param branchId
	 * @return
	 */
	@RequestMapping (value="viewNetMdBranchSystemInfo/{passphrase}", 

method=RequestMethod.GET)
	@ResponseBody
	public BranchSystemInfoDetails viewNetMdBranchSystemInfo(@PathVariable String 

passphrase){
		BranchSystemInfoDetails details= new BranchSystemInfoDetails();
		try{
			details= service.viewNetMdBranchSystemInfo(passphrase);
		}
		catch(ServiceException e){
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			details.setError(error);
			details.setSuccess(false);
		}
		return details;
		
	}

	/**
 	 * Method for updating the branch default system details of a lab
	 * @param details
 	 * @return
     */
	@RequestMapping(value = "updateLabBranchSystemInfo", method = 

RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateLabBranchSystemInfo(@RequestBody 

BranchSystemInfoDetails details) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.updateLabBranchSystemInfo(details);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		return response;
	}

	/**
 	 * Method for updating the branch default system details of a netmd
	 * @param details
 	 * @return
     */
	@RequestMapping(value = "updateNetmdBranchSystemInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateNetmdBranchSystemInfo(@RequestBody BranchSystemInfoDetails 

systemCriticalDetails) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.updateNetmdBranchSystemInfo(systemCriticalDetails);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		return response;
	}

	
	/**
	 * Shows a list of all user logs
	 * 
	 * @param filter
	 * @return UserLogListResponseDTO
	 */
	@RequestMapping(value = "userLogList", method = RequestMethod.POST)
	@ResponseBody
	public UserLogListResponseDTO userLogList(@RequestBody FilterDTO filter) {
		UserLogListResponseDTO response = new UserLogListResponseDTO();
		try {
			response = service.userLogList(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * Shows a list of all sync logs
	 * 
	 * @param filter
	 * @return SyncLogListResponseDTO
	 */
	@RequestMapping(value = "syncLogList", method = RequestMethod.POST)
	@ResponseBody
	public SyncLogListResponseDTO syncLogList(@RequestBody FilterDTO filter) {
		SyncLogListResponseDTO response = new SyncLogListResponseDTO();
		try {
			response = service.syncLogList(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * Shows a list of all tests
	 * 
	 * @param filter
	 * @return TestListResponseDTO
	 */
	@RequestMapping(value = "testList", method = RequestMethod.POST)
	@ResponseBody
	public TestListResponseDTO testList(@RequestBody FilterDTO filter) {
		TestListResponseDTO response = new TestListResponseDTO();
		try {
			response = service.testList(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	/**
	 * Shows a list of all test specimens
	 * 
	 * @param filter
	 * @return SpecimenListResponseDTO
	 */
	@RequestMapping(value = "testSpecimenList", method = 

RequestMethod.POST)
	@ResponseBody
	public SpecimenListResponseDTO testSpecimenList(@RequestBody FilterDTO filter) {
		SpecimenListResponseDTO response = new SpecimenListResponseDTO();
		try {
			response = service.testSpecimenList(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * To enable/disable  sync log process
	 * 
	 * @param log
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "enableSyncLog", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO enableSyncLog(@RequestBody SyncLogDTO syncLog) {

		ResponseDTO response = new ResponseDTO();
		ServletRequestAttributes t = (ServletRequestAttributes) 

RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		try {
			response = service.enableSyncLog(syncLog, request);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
//	
	
	/**
	 * To enable/disable  sync process
	 * 
	 * @param log
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "setSync", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO setSync(@RequestBody SyncFreqDTO sync) {

		ResponseDTO response = new ResponseDTO();
		try {
			response = service.setSync(sync);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * To set synchronization frequency for a lab 
	 * 
	 * @param sync
	 * @return SyncFreqResponseDTO
	 */
	@RequestMapping(value = "setLabSync", method = RequestMethod.POST)
	@ResponseBody
	public SyncFreqResponseDTO setLabSync(@RequestBody SyncFreqDTO sync) {

		SyncFreqResponseDTO response = new SyncFreqResponseDTO();
		try {
			response = service.setLabSync(sync);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * To set synchronization frequency for a lab branch
	 * 
	 * @param sync
	 * @return SyncFreqResponseDTO
	 */
	@RequestMapping(value = "setBranchSync", method = RequestMethod.POST)
	@ResponseBody
	public SyncFreqResponseDTO setBranchSync(@RequestBody SyncFreqDTO sync) {

		SyncFreqResponseDTO response = new SyncFreqResponseDTO();
		try {
			response = service.setBranchSync(sync);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * To set synchronization frequency for a NetMd 
	 * 
	 * @param sync
	 * @return SyncFreqResponseDTO
	 */
	@RequestMapping(value = "setNetMdSync", method = RequestMethod.POST)
	
	@ResponseBody
	public SyncFreqResponseDTO setNetMdSync(@RequestBody SyncFreqDTO sync) 

{

		SyncFreqResponseDTO response = new SyncFreqResponseDTO();
		try {
			response = service.setNetMdSync(sync);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * To set synchronization frequency for a NetMd branch
	 * 
	 * @param sync
	 * @return SyncFreqResponseDTO
	 */
	@RequestMapping(value = "setNetMdBranchSync", method = 

RequestMethod.POST)
	
	@ResponseBody
	public SyncFreqResponseDTO setNetMdBranchSync(@RequestBody SyncFreqDTO 

sync) {

		SyncFreqResponseDTO response = new SyncFreqResponseDTO();
		try {
			response = service.setNetMdBranchSync(sync);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	/**
	 * To set synchronization frequency for a NetRx 
	 * 
	 * @param sync
	 * @return SyncFreqResponseDTO
	 */
	@RequestMapping(value = "setNetRxSync", method = RequestMethod.POST)
	
	@ResponseBody
	public SyncFreqResponseDTO setNetRxSync(@RequestBody SyncFreqDTO sync) 

{

		SyncFreqResponseDTO response = new SyncFreqResponseDTO();
		try {
			response = service.setNetRxSync(sync);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	/**
	 * To set synchronization frequency for a NetRx branch
	 * 
	 * @param sync
	 * @return SyncFreqResponseDTO
	 */
	@RequestMapping(value = "setNetRxBranchSync", method = 

RequestMethod.POST)
	
	@ResponseBody
	public SyncFreqResponseDTO setNetRxBranchSync(@RequestBody SyncFreqDTO 

sync) {

		SyncFreqResponseDTO response = new SyncFreqResponseDTO();
		try {
			response = service.setNetRxBranchSync(sync);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	
	/**
	 * To get synchronization frequency of a lab 
	 * 
	 * @param log
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "getSyncDetails", method = RequestMethod.GET)
	@ResponseBody
	public SyncFreqDTO getSyncDetails() {

		SyncFreqDTO response = new SyncFreqDTO();
		try {
			response = service.getSyncDetails();
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	
	
	
	/**
	 * Creates a NetPos account
	 * @param netPos
	 * @return
	 */
	@RequestMapping(value = "createNetPos", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createNetPos(@RequestBody NetPosDTO netPos) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.createNetPos(netPos);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	
	
	}
	
	/**
	 * Update netPos account
	 * @param netPos
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateNetPos", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateNetPos(@RequestBody NetPosDTO netPos) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.updateNetPos(netPos);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;

	}
	
	/**
	 * Delete netPos account
	 * @param netPos
	 * @return ResponseDTO
	 */
	
	@RequestMapping(value = "deleteNetPos/{globalId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteNetPos(@PathVariable int globalId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.deleteNetPos(globalId);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	
	/**
	 * View netpos account
	 * 
	 * @param netPos
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "viewNetPos/{netPosId}", method = 

RequestMethod.GET)
	@ResponseBody
	public NetPosViewResponseDTO viewNetPos(@PathVariable int netPosId) {
		NetPosViewResponseDTO response = new NetPosViewResponseDTO();
		try {
			response = service.viewNetPos(netPosId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	
	/**
	 * Creates a Organisation account
	 * @param organztion
	 * @return
	 */
	@RequestMapping(value = "createOrganisation", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createOrganisation(@RequestBody Organisation organztion) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.createOrganisation(organztion);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}
		
	/**
	 * Update organization details
	 * 
	 * @param organztion
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateOrganisation", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateOrganisation(@RequestBody Organisation organztion) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.updateOrganisation(organztion);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * View Organization details
	 * 
	 * @param globalId
	 * @return OrganizationViewResponseDTO
	 */
	@RequestMapping(value = "viewOrganisation/{globalId}", method = RequestMethod.GET)
	@ResponseBody
	public OrganizationViewResponseDTO viewOrganisation(@PathVariable int globalId) {
		OrganizationViewResponseDTO response = new OrganizationViewResponseDTO();
		try {
			response = service.viewOrganisation(globalId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * Deletes a organization
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "deleteOrganisation/{globalId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteOrganisation(@PathVariable int globalId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.deleteOrganisation(globalId);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	
	/**
	 * To retrieve a list of organisation which satisfy all filter conditions
	 * 
	 * @param filter
	 * @return OrganisationListResponseDTO
	 */
	@RequestMapping(value = "getOrganisationList", method = RequestMethod.POST)
	@ResponseBody
	public OrganisationListResponseDTO getOrganisationList(@RequestBody FilterDTO filter) {
		OrganisationListResponseDTO response = new OrganisationListResponseDTO();
		try {
			response = service.getOrganisationList(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	

	/**
	 * Creates a Organisation account
	 * @param organztion
	 * @return
	 */
	@RequestMapping(value = "createOrganisationUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createOrganisationUser(@RequestBody OrganisationUserDetail organztionUser) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.createOrganisationUser(organztionUser);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}
		
	/**
	 * Update organization details
	 * 
	 * @param organztion
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateOrganisationUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateOrganisationUser(@RequestBody OrganisationUserDetail organztionUser) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.updateOrganisationUser(organztionUser);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * View Organization details
	 * 
	 * @param globalId
	 * @return OrganizationViewResponseDTO
	 */
	@RequestMapping(value = "viewOrganisationUser/{globalId}", method = RequestMethod.GET)
	@ResponseBody
	public ViewOrganisationUser viewOrganisationUser(@PathVariable int globalId) {
		ViewOrganisationUser response = new ViewOrganisationUser();
		try {
			response = service.viewOrganisationUser(globalId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * Deletes a organization
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "deleteOrganisationUser/{globalId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteOrganisationUser(@PathVariable int globalId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.deleteOrganisationUser(globalId);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	
	/**
	 * To retrieve a list of organisation users which satisfy all filter conditions
	 * 
	 * @param filter
	 * @return NetMdListResponseDTO
	 */
	@RequestMapping(value = "getOrganisationUserList", method = RequestMethod.POST)
	@ResponseBody
	public OrganisationUsersList getOrganisationUserList(@RequestBody FilterDTO filter) {
		OrganisationUsersList response = new OrganisationUsersList();
		try {
			response = service.getOrganisationUserList(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	

	
	/**
	 * To retrieve a list of netmd which satisfy all filter conditions
	 * 
	 * @param filter
	 * @return NetMdListResponseDTO
	 */
	@RequestMapping(value = "netPosList", method = RequestMethod.POST)
	@ResponseBody
	public NetPosListResponseDTO getNetPosList(@RequestBody FilterDTO filter) {
		NetPosListResponseDTO response = new NetPosListResponseDTO();
		try {
			response = service.getNetPosList(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	@RequestMapping(value="contactus/mail", method=RequestMethod.POST)
	@ResponseBody
	public boolean sendMail(@RequestBody MailInfo mailInfo){
		return service.sendMail(mailInfo);
	}
	/**
	 * @return the service
	 */
	public SuperAdminService getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to setb 
	 */
	public void setService(SuperAdminService service) {
		this.service = service;
	}

	/**
	 * @return the logService
	 */
	public LogService getLogService() {
		return logService;
	}

	/**
	 * @param logService the logService to set
	 */
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	
}
