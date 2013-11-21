/**
 * LabResource.java
 * 
 * @Author Asha Chandran
 *
 * Dec 5, 2012
 */
package com.nv.youNeverWait.rs.ui;

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
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ApplicationNameEnum;
import com.nv.youNeverWait.pl.entity.LogUserTypeEnum;
import com.nv.youNeverWait.rs.dto.BranchOrderDTO;
import com.nv.youNeverWait.rs.dto.BranchOrdersResponseDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.HealthMonitorResponse;
import com.nv.youNeverWait.rs.dto.LabBranchDTO;
import com.nv.youNeverWait.rs.dto.BranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.LabActivationResponseDTO;
import com.nv.youNeverWait.rs.dto.LabBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.LabListResponseDTO;
import com.nv.youNeverWait.rs.dto.LabUserDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.MacStatusResponseDTO;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
import com.nv.youNeverWait.rs.dto.OrderTransferResponse;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultTransferDTO;
import com.nv.youNeverWait.rs.dto.ResultTransferResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqResponseDTO;
import com.nv.youNeverWait.rs.dto.SystemHealthDetails;
import com.nv.youNeverWait.rs.dto.TransferNetMdResultDTO;
import com.nv.youNeverWait.security.User;
import com.nv.youNeverWait.user.bl.service.LabService;
import com.nv.youNeverWait.user.bl.service.LogService;
import com.nv.youNeverWait.user.bl.service.OrderService;

@Controller
@RequestMapping("ui/lab/")
public class LabResource {
	private LabService labService;
	private LogService logService;

	/**
	 * Shows NetLims Detail page
	 * 
	 * @return aboutNetLims html
	 */
	@RequestMapping(value = "aboutNetLims", method = RequestMethod.GET)
	public String aboutNetLims() {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		logService.saveUserDetails(request.getRemoteAddr(), null,
				LogUserTypeEnum.Nil.getDisplayName(), null, null,
				ApplicationNameEnum.NetLims.getDisplayName(),
				Constants.ABOUT_NETLIMS);

		return "aboutNetLims";
	}

	/**
	 * Shows NetLims Contact Details
	 * 
	 * @return contactUs html
	 */
	@RequestMapping(value = "contactUs", method = RequestMethod.GET)
	public String contactUs() {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		logService.saveUserDetails(request.getRemoteAddr(), null,
				LogUserTypeEnum.Nil.getDisplayName(), null, null,
				ApplicationNameEnum.NetLims.getDisplayName(),
				Constants.CONTACT_US);

		return "contactUs";
	}

	/**
	 * Shows Pricing Details
	 * 
	 * @return pricing html
	 */
	@RequestMapping(value = "pricing", method = RequestMethod.GET)
	public String pricing() {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		logService
				.saveUserDetails(request.getRemoteAddr(), null,
						LogUserTypeEnum.Nil.getDisplayName(), null, null,
						ApplicationNameEnum.NetLims.getDisplayName(),
						Constants.PRICING);

		return "pricing";
	}

	/**
	 * Shows Privacy and policy details
	 * 
	 * @return pricing html
	 */
	@RequestMapping(value = "privacyPolicy", method = RequestMethod.GET)
	public String privacyPolicy() {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		logService.saveUserDetails(request.getRemoteAddr(), null,
				LogUserTypeEnum.Nil.getDisplayName(), null, null,
				ApplicationNameEnum.NetLims.getDisplayName(), Constants.POLICY);

		return "privacyPolicy";
	}

	/**
	 * Method performed for session logout
	 * 
	 * @return LoginResponseDTO
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	@ResponseBody
	public LoginResponseDTO logout() {

		LoginResponseDTO response = new LoginResponseDTO();
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user = (User) req.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(req.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), new Date(),
					ApplicationNameEnum.NetLims.getDisplayName(),
					Constants.LOGOUT);
		}
		req.getSession().setAttribute(Constants.USER, null);
		response.setSuccess(true);
		response.setError(null);
		return response;
	}

	/**
	 * To reset password of lab user/owner
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO resetPassword(@RequestBody LoginDTO login) {

		ResponseDTO response = new ResponseDTO();
		try {
			response = labService.resetPassword(login);
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
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		logService.saveUserDetails(request.getRemoteAddr(),
				null, login.getUserType(), null, null,
				ApplicationNameEnum.NetLims.getDisplayName(),
				Constants.RESET_PSWD);
		return response;
	}

	/**
	 * To show login page for lab user/owner
	 * 
	 * @return netLimsLoginPage.html
	 */
	@RequestMapping(value = "startUp", method = RequestMethod.GET)
	public String check() {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		logService
				.saveUserDetails(request.getRemoteAddr(), null,
						LogUserTypeEnum.Nil.getDisplayName(), null, null,
						ApplicationNameEnum.NetLims.getDisplayName(),
						Constants.STARTUP);

		return "netlimsIndex";
	}

	/**
	 * To show login page for lab user/owner
	 * 
	 * @return netLimsLoginPage.html
	 */
	@RequestMapping(value = "lForm", method = RequestMethod.GET)
	public String lForm() {
		return "netLimsLoginPage";
	}

	/**
	 * To show a page from which Netlims Application can be downloaded
	 * 
	 * @return netLimsDownload.html
	 */
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public String download() {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.NetLims.getDisplayName(),
					Constants.LAB_DOWNLOAD);
		}
		return "netLimsDownload";
	}

	/**
	 * Create user in Lab
	 * 
	 * @param user
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createUser(@RequestBody LabUserDTO user) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = labService.createUser(user);
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
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User userObj = (User) request.getSession().getAttribute("user");
		if (userObj != null) {
			logService.saveUserDetails(request.getRemoteAddr(),
					userObj.getName(), userObj.getUserType(),
					userObj.getLoginTime(), null,
					ApplicationNameEnum.NetLims.getDisplayName(),
					Constants.USER_REGISTRATION);
		}
		return response;
	}

	/**
	 * Update lab user
	 * 
	 * @param user
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateUser(@RequestBody LabUserDTO user) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = labService.updateUser(user);
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
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User userObj = (User) request.getSession().getAttribute("user");
		if (userObj != null) {
			logService.saveUserDetails(request.getRemoteAddr(),
					userObj.getName(), userObj.getUserType(),
					userObj.getLoginTime(), null,
					ApplicationNameEnum.NetLims.getDisplayName(),
					Constants.USER_UPDATION);
		}
		return response;
	}

	/**
	 * View lab user details
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "viewUser/{globalId}", method = RequestMethod.GET)
	@ResponseBody
	public LabUserDTO viewUser(@PathVariable int globalId) {
		LabUserDTO response = new LabUserDTO();
		try {
			response = labService.viewUser(globalId);
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
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User userObj = (User) request.getSession().getAttribute("user");
		if (userObj != null) {
			logService.saveUserDetails(request.getRemoteAddr(),
					userObj.getName(), userObj.getUserType(),
					userObj.getLoginTime(), null,
					ApplicationNameEnum.NetLims.getDisplayName(),
					Constants.USER_VIEW);
		}
		return response;
	}

	/**
	 * Delete lab user
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "deleteUser/{globalId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteUser(@PathVariable int globalId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = labService.deleteUser(globalId);
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
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User userObj = (User) request.getSession().getAttribute("user");
		if (userObj != null) {
			logService.saveUserDetails(request.getRemoteAddr(),
					userObj.getName(), userObj.getUserType(),
					userObj.getLoginTime(), null,
					ApplicationNameEnum.NetLims.getDisplayName(),
					Constants.USER_DELETION);
		}
		return response;
	}

	/**
	 * View lab user details
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "viewBranchOrders/{globalId}", method = RequestMethod.GET)
	@ResponseBody
	public BranchOrdersResponseDTO viewBranchOrders(@PathVariable int globalId) {
		BranchOrdersResponseDTO response = new BranchOrdersResponseDTO();
		try {
			response = labService.viewBranchOrders(globalId);
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
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User userObj = (User) request.getSession().getAttribute("user");
		if (userObj != null) {
			logService.saveUserDetails(request.getRemoteAddr(),
					userObj.getName(), userObj.getUserType(),
					userObj.getLoginTime(), null,
					ApplicationNameEnum.NetLims.getDisplayName(),
					Constants.USER_VIEW);
		}
		return response;
	}

	/**
	 * Method which performs password changing
	 * 
	 * @param passwords
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO changePassword(@RequestBody PasswordDTO passwords) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = labService.changePassword(passwords);
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
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User userObj = (User) request.getSession().getAttribute("user");
		if (userObj != null) {
			logService.saveUserDetails(request.getRemoteAddr(),
					userObj.getName(), userObj.getUserType(),
					userObj.getLoginTime(), null,
					ApplicationNameEnum.NetLims.getDisplayName(),
					Constants.CHANGE_PASSWORD);
		}
		return response;
	}

	/**
	 * To reset password if user forgot his password
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "forgotPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO forgotPassword(@RequestBody LoginDTO login) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = labService.forgotPassword(login);
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
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		logService.saveUserDetails(request.getRemoteAddr(), null,
				LogUserTypeEnum.Nil.getDisplayName(), null, null,
				ApplicationNameEnum.NetLims.getDisplayName(),
				Constants.FORGOT_PWD);

		return response;
	}

	/**
	 * To clear mac Id for the purpose of uninstalling netlims application from
	 * device
	 * 
	 * @param LabBranchDTO
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "clearMacId", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO clearMacId(@RequestBody LabBranchDTO branch) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = labService.clearMacId(branch);
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
	 * To check netlims is already installed on particular device, if so mac id
	 * exists on portal for given passPhrase for that device.
	 * 
	 * @param passPhrase
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "getMacStatus/{passPhrase}", method = RequestMethod.GET)
	@ResponseBody
	public MacStatusResponseDTO getMacStatus(@PathVariable String passPhrase) {
		MacStatusResponseDTO response = new MacStatusResponseDTO();
		try {
			response = labService.getMacStatus(passPhrase);
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
	 * Retrieves lab branch details And set Mac Id if not exists
	 * 
	 * @param header
	 * @return LabActivationResponseDTO
	 */
	@RequestMapping(value = "activateLab", method = RequestMethod.POST)
	@ResponseBody
	public LabActivationResponseDTO activateLab(@RequestBody HeaderDTO header) {
		LabActivationResponseDTO response = new LabActivationResponseDTO();
		try {
			response = labService.activateLab(header);
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
			response = labService.createBranch(branch);
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
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User userObj = (User) request.getSession().getAttribute("user");
		if (userObj != null) {
			logService.saveUserDetails(request.getRemoteAddr(),
					userObj.getUserName(), userObj.getUserType(),
					userObj.getLoginTime(), null,
					ApplicationNameEnum.NetLims.getDisplayName(),
					Constants.CREATE_BRANCH);
		}
		return response;
	}

	/**
	 * Shows a list of all labs
	 * 
	 * @param filter
	 * @return LabListResponseDTO
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public LabListResponseDTO list(@RequestBody FilterDTO filter) {
		LabListResponseDTO response = new LabListResponseDTO();
		try {
			response = labService.list(filter);
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
	public BranchListResponseDTO branchList(@RequestBody FilterDTO filter) {
		BranchListResponseDTO response = new BranchListResponseDTO();
		try {
			response = labService.branchList(filter);
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
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.NetLims.getDisplayName(),
					Constants.BRANCH_LIST);
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
			response = labService.updateBranch(branch);
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
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.NetLims.getDisplayName(),
					Constants.UPDATE_BRANCH);
		}
		return response;
	}

	/**
	 * View details of a lab branch
	 * 
	 * @param globalId
	 * @return BranchResponseDTO
	 */
	@RequestMapping(value = "viewBranch/{globalId}", method = RequestMethod.GET)
	@ResponseBody
	public LabBranchResponseDTO viewBranch(@PathVariable int globalId) {
		LabBranchResponseDTO response = new LabBranchResponseDTO();
		try {
			response = labService.viewBranch(globalId);
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
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.NetLims.getDisplayName(),
					Constants.VIEW_BRANCH);
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
			response = labService.deleteBranch(branch);
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
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.NetLims.getDisplayName(),
					Constants.DELETE_BRANCH);
		}
		return response;
	}

	/**
	 * Transfer result to NetMd
	 * 
	 * @param resultTranfer
	 * @return ResultTransferResponseDTO
	 */
	@RequestMapping(value = "transferResultToNetMd", method = RequestMethod.POST)
	@ResponseBody
	public ResultTransferResponseDTO transferResultToNetMd(
			@RequestBody TransferNetMdResultDTO resultTranfer) {
		ResultTransferResponseDTO response = new ResultTransferResponseDTO();
		try {
			response = labService.transferResultToNetMd(resultTranfer);
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
	 * Transfer result from one lab branch to other
	 * 
	 * @param ResultTransferDTO
	 * @return ResultTransferResponseDTO
	 */
	@RequestMapping(value = "transferResult", method = RequestMethod.POST)
	@ResponseBody
	public ResultTransferResponseDTO transferResult(
			@RequestBody ResultTransferDTO resultTranferDto) {
		ResultTransferResponseDTO response = new ResultTransferResponseDTO();
		try {
			response = labService.transferResult(resultTranferDto);
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
	 * List all recorde of Report Tbl for given From and To date
	 * 
	 * @param report
	 * @return ReportResponseDTO
	 */
	@RequestMapping(value = "orderList", method = RequestMethod.POST)
	@ResponseBody
	public BranchOrdersResponseDTO orderList(
			@RequestBody BranchOrderDTO orderDTO) {

		BranchOrdersResponseDTO response = new BranchOrdersResponseDTO();
		try {
			response = labService.orderList(orderDTO);
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
	 * Method performed for system health monitor
	 * 
	 * @return HealthMonitorResponse
	 */
	@RequestMapping(value = "checkSystemHealth", method = RequestMethod.POST)
	@ResponseBody
	public HealthMonitorResponse checkSystemHealth(
			@RequestBody SystemHealthDetails systemHealthDetails) {

		HealthMonitorResponse response = new HealthMonitorResponse();
		try {
			response = labService.checkSystemHealth(systemHealthDetails);
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

	@RequestMapping(value = "orderTransfer", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO orderTransfer(
			@RequestBody OrderTransfer orderTranfer) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = labService.transferOrder(orderTranfer);
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

//	/**
//	 * To enable/disable  sync process
//	 * 
//	 * @param log
//	 * @return ResponseDTO
//	 */
//	@RequestMapping(value = "enableLabSync", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseDTO enableSync(@RequestBody SyncFreqDTO sync) {
//
//		ResponseDTO response = new ResponseDTO();
//		try {
//			response = labService.enableLabSync(sync);
//		} catch (ServiceException e) {
//			List<Parameter> parameters = e.getParamList();
//			ErrorDTO error = new ErrorDTO();
//			error.setErrCode(e.getError().getErrCode());
//			error.setParams(parameters);
//			error.setDisplayErrMsg(e.isDisplayErrMsg());
//			response.setError(error);
//			response.setSuccess(false);
//		}
//		return response;
//	}
	
//	/**
//	 * To enable/disable  sync process
//	 * 
//	 * @param log
//	 * @return ResponseDTO
//	 */
//	@RequestMapping(value = "enableBranchSync", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseDTO enableBranchSync(@RequestBody SyncFreqDTO sync) {
//
//		ResponseDTO response = new ResponseDTO();
//		try {
//			response = labService.enableBranchSync(sync);
//		} catch (ServiceException e) {
//			List<Parameter> parameters = e.getParamList();
//			ErrorDTO error = new ErrorDTO();
//			error.setErrCode(e.getError().getErrCode());
//			error.setParams(parameters);
//			error.setDisplayErrMsg(e.isDisplayErrMsg());
//			response.setError(error);
//			response.setSuccess(false);
//		}
//		return response;
//	}
//	
	
	
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
			response = labService.setBranchSync(sync);
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
	 * @param labId
	 * @return SyncFreqDTO
	 */
	@RequestMapping(value = "getLabSyncDetails/{labId}", method = RequestMethod.GET)
	@ResponseBody
	public SyncFreqDTO getLabSyncDetails(@PathVariable int labId) {

		SyncFreqDTO response = new SyncFreqDTO();
		try {
			response = labService.getLabSyncDetails(labId);
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
	 * @param branchId
	 * @return SyncFreqDTO
	 */
	@RequestMapping(value = "getBranchSyncDetails/{branchId}", method = RequestMethod.GET)
	@ResponseBody
	public SyncFreqDTO getBranchSyncDetails(@PathVariable int branchId) {

		SyncFreqDTO response = new SyncFreqDTO();
		try {
			response = labService.getBranchSyncDetails(branchId);
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
	 * @return the labService
	 */
	public LabService getLabService() {
		return labService;
	}

	/**
	 * @param labService
	 *            the labService to set
	 */
	public void setLabService(LabService labService) {
		this.labService = labService;
	}

	/**
	 * @return the logService
	 */
	public LogService getLogService() {
		return logService;
	}

	/**
	 * @param logService
	 *            the logService to set
	 */
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

}
