/**
 * NetRxResource.java
 *
 * @Author Luciya Jos
 * May 8, 2013 
 */
package com.nv.youNeverWait.rs.ui;

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
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchDetail;
import com.nv.youNeverWait.rs.dto.NetRxBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxHeaderDTO;
import com.nv.youNeverWait.rs.dto.NetRxUserDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxUserListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxViewResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqResponseDTO;
import com.nv.youNeverWait.security.User;
import com.nv.youNeverWait.user.bl.service.LogService;
import com.nv.youNeverWait.user.bl.service.NetRxService;

/**
 * @author netvarth
 * 
 */
@Controller
@RequestMapping("ui/netRx/")
public class NetRxResource {
	private NetRxService netRxService;
	private LogService logService;

	/**
	 * 
	 * To show login page for netrx user
	 * 
	 * @return netRxLoginPage.html
	 */
	@RequestMapping(value = "startUp", method = RequestMethod.GET)
	public String check() {
		return "netRxIndex";
	}

	/**
	 * To show login page for netRx user/owner
	 * 
	 * @return netRxLoginPage.html
	 */
	@RequestMapping(value = "rForm", method = RequestMethod.GET)
	public String rForm() {
		return "netRxLoginPage";
	}

	/**
	 * To retrieve a list of netRx branches which satisfy all filter conditions
	 * 
	 * @param filter
	 * @return NetMdBranchListResponseDTO
	 */
	@RequestMapping(value = "netRxBranchList", method = RequestMethod.POST)
	@ResponseBody
	public NetRxBranchListResponseDTO getNetRxBranchList(
			@RequestBody FilterDTO filter) {
		NetRxBranchListResponseDTO response = new NetRxBranchListResponseDTO();
		try {
			response = netRxService.getNetRxBranchList(filter);
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
	 * To clear mac Id for the purpose of uninstalling netMd application from
	 * device
	 * 
	 * @param HeaderDTO
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "clearMacId", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO clearMacId(@RequestBody NetRxHeaderDTO header) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = netRxService.clearMacId(header);
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
			response = netRxService.changePassword(password);
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
	 * 
	 * View netRx account
	 * 
	 * @param netRxId
	 * @return NetRxViewResponseDTO
	 */
	@RequestMapping(value = "viewNetRx/{netRxId}", method = RequestMethod.GET)
	@ResponseBody
	public NetRxViewResponseDTO viewNetRx(@PathVariable int netRxId) {
		NetRxViewResponseDTO response = new NetRxViewResponseDTO();
		try {
			response = netRxService.viewNetRx(netRxId);
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
	@RequestMapping(value = "deleteNetRx/{netRxId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteNetRx(@PathVariable int netRxId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = netRxService.deleteNetRx(netRxId);
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
	@RequestMapping(value = "deleteNetRxBranch/{globalId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteBranch(@PathVariable int globalId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = netRxService.deleteBranch(globalId);
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
					ApplicationNameEnum.NetRx.getDisplayName(),
					Constants.DELETE_BRANCH);
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
	public ResponseDTO createBranch(@RequestBody NetRxBranchDetail branch) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = netRxService.createBranch(branch);
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
					ApplicationNameEnum.NetRx.getDisplayName(),
					Constants.CREATE_BRANCH);
		}
		return response;
	}

	/**
	 * 
	 * Create netrx user account
	 * 
	 * @param user
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "createNetRxUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createUser(@RequestBody NetRxUserDTO user) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = netRxService
					.createUser(user.getHeader(), user.getUser());
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
					ApplicationNameEnum.NetRx.getDisplayName(),
					Constants.USER_REGISTRATION);
		}
		return response;
	}

	/**
	 * Update netrx user account
	 * 
	 * @param user
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateNetRxUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateUser(@RequestBody NetRxUserDTO user) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = netRxService
					.updateUser(user.getHeader(), user.getUser());
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
					ApplicationNameEnum.NetRx.getDisplayName(),
					Constants.USER_UPDATION);
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
		req.getSession().setAttribute(Constants.USER, null);
		response.setSuccess(true);
		response.setError(null);
		return response;
	}

	/**
	 * View netrx branch details
	 * 
	 * @param globalId
	 * @return NetRxBranchResponseDTO
	 */
	@RequestMapping(value = "viewNetRxBranch/{netrxBranchId}", method = RequestMethod.GET)
	@ResponseBody
	public NetRxBranchResponseDTO viewBranch(@PathVariable int netrxBranchId) {
		NetRxBranchResponseDTO response = new NetRxBranchResponseDTO();
		try {
			response = netRxService.viewBranch(netrxBranchId);
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
					ApplicationNameEnum.NetRx.getDisplayName(),
					Constants.VIEW_BRANCH);
		}
		return response;
	}

	/**
	 * View netrx user
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "viewUser/{globalId}", method = RequestMethod.POST)
	@ResponseBody
	public NetRxUserDTO viewUser(@PathVariable int globalId) {
		NetRxUserDTO response = new NetRxUserDTO();
		try {
			response = netRxService.viewUser(globalId);
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
	 * To retrieve the netRx User list
	 * 
	 * @param filter
	 * @return response
	 */
	@RequestMapping(value = "listNetRxUser", method = RequestMethod.POST)
	@ResponseBody
	public NetRxUserListResponseDTO listNetRxUser(@RequestBody FilterDTO filter) {
		NetRxUserListResponseDTO response = new NetRxUserListResponseDTO();
		try {
			response = netRxService.listNetRxUser(filter);
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
	public ResponseDTO updateBranch(@RequestBody NetRxBranchDetail branch) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = netRxService.updateNetRxBranch(branch);
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
					ApplicationNameEnum.NetRx.getDisplayName(),
					Constants.UPDATE_BRANCH);
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
			response = netRxService.forgotPassword(login);
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
				ApplicationNameEnum.NetRx.getDisplayName(),
				Constants.RESET_PSWD);

		return response;
	}

	/**
	 * To show a page from which NetRX Application can be downloaded
	 * 
	 * @return netRxDownload.html
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
					ApplicationNameEnum.NetRx.getDisplayName(),
					Constants.NETRX_DOWNLOAD);
		}
		return "netRxDownload";
	}

	/**
	 * To reset password of netRx user/owner
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO resetPassword(@RequestBody LoginDTO login) {

		ResponseDTO response = new ResponseDTO();
		try {
			response = netRxService.resetPassword(login);
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
				ApplicationNameEnum.NetRx.getDisplayName(),
				Constants.RESET_PSWD);
		return response;
	}

	
	/**
	 * To set synchronization frequency for a NetRx branch
	 * 
	 * @param sync
	 * @return SyncFreqResponseDTO
	 */
	@RequestMapping(value = "setNetRxBranchSync", method = RequestMethod.POST)
	
	@ResponseBody
	public SyncFreqResponseDTO setNetRxBranchSync(@RequestBody SyncFreqDTO sync) {

		SyncFreqResponseDTO response = new SyncFreqResponseDTO();
		try {
			response = netRxService.setNetRxBranchSync(sync);
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
	 * To get synchronization frequency details of a netrx 
	 * 
	 * @param netmdId
	 * @return SyncFreqDTO
	 */
	@RequestMapping(value = "getNetrxSyncDetails/{netrxId}", method = RequestMethod.GET)
	@ResponseBody
	public SyncFreqDTO getNetrxSyncDetails(@PathVariable int netrxId) {

		SyncFreqDTO response = new SyncFreqDTO();
		try {
			response = netRxService.getNetrxSyncDetails(netrxId);
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
	 * To get synchronization frequency of a netrx branch 
	 * 
	 * @param branchId
	 * @return SyncFreqDTO
	 */
	@RequestMapping(value = "getBranchSyncDetails/{branchId}", method = RequestMethod.GET)
	@ResponseBody
	public SyncFreqDTO getBranchSyncDetails(@PathVariable int branchId) {

		SyncFreqDTO response = new SyncFreqDTO();
		try {
			response = netRxService.getBranchSyncDetails(branchId);
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
	 * @return the netRxService
	 */
	public NetRxService getNetRxService() {
		return netRxService;
	}

	/**
	 * @param netRxService
	 *            the netRxService to set
	 */
	public void setNetRxService(NetRxService netRxService) {
		this.netRxService = netRxService;
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
