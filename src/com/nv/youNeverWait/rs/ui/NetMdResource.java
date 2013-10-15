/**
 * NetMdResource.java
 * 
 * @Author Asha Chandran
 *
 * Jan 3, 2013
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
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdActivationResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDTO;
import com.nv.youNeverWait.rs.dto.NetMdViewResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqResponseDTO;
import com.nv.youNeverWait.security.User;
import com.nv.youNeverWait.user.bl.service.LogService;
import com.nv.youNeverWait.user.bl.service.NetMdService;

@Controller
@RequestMapping("ui/netMd/")
public class NetMdResource {
	private NetMdService netMdService;
	private LogService logService;

	/**
	 * Shows Privacy and policy details
	 * @return pricing html
	 */
	@RequestMapping(value = "privacyPolicy", method = RequestMethod.GET)
	public String privacyPolicy() {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		logService
				.saveUserDetails(request.getRemoteAddr(), null,
						LogUserTypeEnum.Nil.getDisplayName(), null, null,
						ApplicationNameEnum.NetMd.getDisplayName(),
						Constants.NETMD_POLICY);

		return "privacyPolicynetMd";
	}
	
	/**
	 * To show login page for netmd user
	 * 
	 * @return netMdLoginPage.html
	 */
	@RequestMapping(value = "startUp", method = RequestMethod.GET)
	public String check() {
		return "netMdIndex";
	}
	
	/**
	 * To show login page for netMd user/owner
	 * 
	 * @return netMdLoginPage.html
	 */
	@RequestMapping(value = "mForm", method = RequestMethod.GET)
	public String mForm() {
		return "netMdLoginPage";
	}
	
	
	/**
	 * To show a page from which NetMD Application can be downloaded
	 * 
	 * @return netMdDownload.html
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
					ApplicationNameEnum.NetMd.getDisplayName(),
					Constants.NETMD_DOWNLOAD);
		}
		return "netMdDownload";
	}
	/**
	 * To reset password of netmd user/owner
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO resetPassword(@RequestBody LoginDTO login) {

		ResponseDTO response = new ResponseDTO();
		try {
			response = netMdService.resetPassword(login);
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
				login.getUserName(), login.getUserType(), null, null,
				ApplicationNameEnum.NetMd.getDisplayName(),
				Constants.RESET_PSWD);
		return response;
	}

	/**
	 * To reset password of netmd user/owner
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO changePassword(@RequestBody PasswordDTO password) {

		ResponseDTO response = new ResponseDTO();
		try {
			response = netMdService.changePassword(password);
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
	 * Create netmd account
	 * 
	 * @param netMd
	 * @return
	 */
	@RequestMapping(value = "createNetMd", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createNetMd(@RequestBody NetMdDTO netMd) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = netMdService.createNetMd(netMd);
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
			response = netMdService.updateNetMd(netMd);
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
	 * @param netMd
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "viewNetMd/{netMdId}", method = RequestMethod.GET)
	@ResponseBody
	public NetMdViewResponseDTO view(@PathVariable int netMdId) {
		NetMdViewResponseDTO response = new NetMdViewResponseDTO();
		try {
			response = netMdService.viewNetMd(netMdId);
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
			response = netMdService.deleteNetMd(globalId);
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
	@RequestMapping(value = "createNetMdBranch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createBranch(@RequestBody NetMdBranchDTO branch) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = netMdService.createBranch(branch);
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
					ApplicationNameEnum.NetMd.getDisplayName(),
					Constants.CREATE_BRANCH);
		}
		return response;
	}

	/**
	 * Updates netmd branch details
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateNetMdBranch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateBranch(@RequestBody NetMdBranchDTO branch) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = netMdService.updateBranch(branch);
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
					ApplicationNameEnum.NetMd.getDisplayName(),
					Constants.UPDATE_BRANCH);
		}
		return response;
	}

	/**
	 * To check netmd is already installed on particular device, if so mac id
	 * exists on portal for given passPhrase for that device.
	 * 
	 * @param passPhrase
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "getMacStatus/{passPhrase}", method = RequestMethod.GET)
	@ResponseBody
	public NetMdResponseDTO getMacStatus(@PathVariable String passPhrase) {
		NetMdResponseDTO response = new NetMdResponseDTO();
		try {
			response = netMdService.getMacStatus(passPhrase);
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
	 * Activate netmd after installing netmd on a device. activation process
	 * means verifying netmd installed on correct device and also sending back
	 * all necessary data related to that netmd .
	 * 
	 * @param header
	 * @return
	 */
	@RequestMapping(value = "activateNetMd", method = RequestMethod.POST)
	@ResponseBody
	public NetMdActivationResponseDTO activateNetMd(
			@RequestBody HeaderDTO header) {
		NetMdActivationResponseDTO response = new NetMdActivationResponseDTO();
		try {
			response = netMdService.activateNetMd(header);
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
	@RequestMapping(value = "viewNetMdBranch/{netmdBranchId}", method = RequestMethod.GET)
	@ResponseBody
	public NetMdBranchResponseDTO viewBranch(@PathVariable int netmdBranchId) {
		NetMdBranchResponseDTO response = new NetMdBranchResponseDTO();
		try {
			response = netMdService.viewBranch(netmdBranchId);
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
					ApplicationNameEnum.NetMd.getDisplayName(),
					Constants.VIEW_BRANCH);
		}
		return response;
	}

	/**
	 * Deletes a netmd branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "deleteNetMdBranch/{globalId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteBranch(@PathVariable int globalId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = netMdService.deleteBranch(globalId);
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
					ApplicationNameEnum.NetMd.getDisplayName(),
					Constants.DELETE_BRANCH);
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
			response = netMdService.getBranchList(filter);
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
					ApplicationNameEnum.NetMd.getDisplayName(),
					Constants.BRANCH_LIST);
		}
		return response;
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
					ApplicationNameEnum.NetMd.getDisplayName(),
					Constants.LOGOUT);
		}
		req.getSession().setAttribute(Constants.USER, null);
		response.setSuccess(true);
		response.setError(null);
		return response;
	}
	/**
	 * Create netmd user account
	 * 
	 * @param netMdUser
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "createNetMdUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createUser(@RequestBody NetMdUserDTO user) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = netMdService.createUser(user.getHeader(),user.getUser());
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
					ApplicationNameEnum.NetMd.getDisplayName(),
					Constants.USER_REGISTRATION);
		}
		return response;
	}
	/**
	 * Update netmd user account
	 * 
	 * @param netMdUser
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateNetMdUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateUser(@RequestBody NetMdUserDTO user) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = netMdService.updateUser(user.getHeader(),user.getUser());
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
					ApplicationNameEnum.NetMd.getDisplayName(),
					Constants.USER_UPDATION);
		}
		return response;
	}
	/**
	 * Delete netmd user account
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "deleteUser/{globalId}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO deleteUser(@PathVariable int globalId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = netMdService.deleteUser(globalId);
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
					ApplicationNameEnum.NetMd.getDisplayName(),
					Constants.USER_DELETION);
		}
		return response;
	}
	/**
	 * View netmd user 
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "viewUser/{globalId}", method = RequestMethod.POST)
	@ResponseBody
	public  NetMdUserDTO viewUser(@PathVariable int globalId) {
		NetMdUserDTO response = new NetMdUserDTO();
		try {
			response = netMdService.viewUser(globalId);
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
					ApplicationNameEnum.NetMd.getDisplayName(),
					Constants.USER_VIEW);
		}
		return response;
	}
	/**
	 * To reset password if user forgot his password
	 * @param login
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "forgotPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO forgotPassword(@RequestBody LoginDTO login)
	{
		ResponseDTO response=new ResponseDTO();
		try{
			response=netMdService.forgotPassword(login);
		}
		catch (ServiceException e) {
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
				ApplicationNameEnum.NetMd.getDisplayName(),
				Constants.RESET_PSWD);

		return response;
	}
	
	/**
	 * To clear mac Id for the purpose of uninstalling netMd application from
	 * device
	 * 
	 * @param HeaderDTO
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "clearMacId", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO clearMacId(@RequestBody HeaderDTO header) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = netMdService.clearMacId(header);
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
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "setNetMdSync", method = RequestMethod.POST)
	
	@ResponseBody
	public SyncFreqResponseDTO setNetMdSync(@RequestBody SyncFreqDTO sync) {

		SyncFreqResponseDTO response = new SyncFreqResponseDTO();
		try {
			response = netMdService.setNetMdSync(sync);
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
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "setNetMdBranchSync", method = RequestMethod.POST)
	
	@ResponseBody
	public SyncFreqResponseDTO setNetMdBranchSync(@RequestBody SyncFreqDTO sync) {

		SyncFreqResponseDTO response = new SyncFreqResponseDTO();
		try {
			response = netMdService.setNetMdBranchSync(sync);
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
	 * To get synchronization frequency details of a netmd 
	 * 
	 * @param log
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "getNetmdSyncDetails/{netmdId}", method = RequestMethod.GET)
	@ResponseBody
	public SyncFreqDTO getNetmdSyncDetails(@PathVariable int netmdId) {

		SyncFreqDTO response = new SyncFreqDTO();
		try {
			response = netMdService.getNetmdSyncDetails(netmdId);
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
	 * To get synchronization frequency of a netmd branch 
	 * 
	 * @param log
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "getBranchSyncDetails/{branchId}", method = RequestMethod.GET)
	@ResponseBody
	public SyncFreqDTO getBranchSyncDetails(@PathVariable int branchId) {

		SyncFreqDTO response = new SyncFreqDTO();
		try {
			response = netMdService.getBranchSyncDetails(branchId);
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
//	@RequestMapping(value = "enableSync", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseDTO enableSync(@RequestBody SyncFreqDTO sync) {
//
//		ResponseDTO response = new ResponseDTO();
//		try {
//			response = netMdService.enableSync(sync);
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
//			response = netMdService.enableBranchSync(sync);
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
//	

	/**
	 * @return the netMdService
	 */
	public NetMdService getNetMdService() {
		return netMdService;
	}

	/**
	 * @param netMdService
	 *            the netMdService to set
	 */
	public void setNetMdService(NetMdService netMdService) {
		this.netMdService = netMdService;
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
