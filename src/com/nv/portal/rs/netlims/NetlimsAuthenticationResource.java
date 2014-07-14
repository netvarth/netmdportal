package com.nv.portal.rs.netlims;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
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
import com.nv.youNeverWait.pl.entity.NetmdUserTypeEnum;
import com.nv.youNeverWait.rs.dto.CaptchaResponseDTO;
import com.nv.youNeverWait.rs.dto.CaptchaVerificationDTO;
import com.nv.youNeverWait.rs.dto.CaptchaVerificationResponseDTO;
import com.nv.youNeverWait.rs.dto.EnumListResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorCodeListResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.UserDetails;
import com.nv.youNeverWait.security.bl.service.AuthenticationService;
import com.nv.youNeverWait.user.bl.service.LabService;
import com.nv.youNeverWait.user.bl.service.LogService;

/**
 * @author Sruthy
 *
 */
@Controller
@RequestMapping("auth")

public class NetlimsAuthenticationResource {


	private AuthenticationService authenticationService;
	private LogService logService;
	private LabService labService;

	/**
	 * To show login page for netlims
	 * 
	 * @return netlimsLogin.html
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String pForm() {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if(user==null)
			return "netLimsLoginPage";
		else {
			if(user.getUserType().equals(NetmdUserTypeEnum.Facility.getDisplayName()))
				return "netlimsFacilityIndex";
		}
		return "netlimsIndex";
	}
	/**
	 * Method performed for NetLims login
	 * @param login 
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 */
	@RequestMapping(value = "/netlimsLogin", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponseDTO netlimsLogin(@RequestBody LoginDTO login) {
		User user = new User();
		LoginResponseDTO response = new LoginResponseDTO();
		try {
			response = authenticationService.netlimsLogin(login);
			if (response.isSuccess()) {
				ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes();
				HttpServletRequest req = t.getRequest();
				UserDetails userDetail =null;
				if(!login.getUserType().equals(NetmdUserTypeEnum.Facility.getDisplayName()))
					userDetail = authenticationService.getNetlimsUser(login.getUserName());
				else
					userDetail = authenticationService.getFacilityUserInfo(login.getUserName(), login.getUserType());
			
				
				if (userDetail != null) {
					user.setLoginTime(new Date());
					user.setName(userDetail.getName());
					user.setUserName(login.getUserName().trim());
					user.setId(userDetail.getId());
					user.setOrganisationId(userDetail.getOrganisationId());
					user.setLabId(userDetail.getLabId());
					user.setUserType(userDetail.getUserType());
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
			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		logService.saveUserDetails(request.getRemoteAddr(),
				user.getName(), user.getUserType(), user.getLoginTime(), null,
				ApplicationNameEnum.NetLims.getDisplayName(),
				Constants.LOGIN);
		return response;
	}

	/**
	 * @return true if success
	 * 
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	@ResponseBody
	public boolean logout(){
		
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		req.getSession().setAttribute(Constants.USER, null);
		return true;
	}
	/**
	 * To reset password of lab user/owner
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
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
	 * Method which performs password changing
	 * 
	 * @param passwords
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
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
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
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
	 * Get current user in the session
	 * 
	 * @return String
	 */
	@RequestMapping(value = "getUser", method = RequestMethod.GET)
	@ResponseBody
	public User getUser() {

		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user = (User) req.getSession().getAttribute("user");
		return user;
	}

	/**
	 * Method performed to get captcha
	 * 
	 * @return CaptchaResponseDTO
	 */
	@RequestMapping(value = "/getCaptcha", method = RequestMethod.GET)
	@ResponseBody
	public CaptchaResponseDTO getCaptcha() {

		CaptchaResponseDTO response = new CaptchaResponseDTO();
		try {
			response = authenticationService.getCaptcha();
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
	 * Method which verify captcha
	 * @param captcha 
	 * 
	 * @return CaptchaResponseDTO
	 */
	@RequestMapping(value = "/verifyCaptcha", method = RequestMethod.POST)
	@ResponseBody
	public CaptchaVerificationResponseDTO verifyCaptcha(
			@RequestBody CaptchaVerificationDTO captcha) {

		CaptchaVerificationResponseDTO response = new CaptchaVerificationResponseDTO();
		try {
			response = authenticationService.verifyCaptcha(captcha);
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
	 * Retrieves list of error messages
	 * 
	 * @return ErrorCodeListResponseDTO
	 */
	@RequestMapping(value = "/getErrorCodes", method = RequestMethod.GET)
	@ResponseBody
	public ErrorCodeListResponseDTO getErrorCodes() {

		ErrorCodeListResponseDTO response = authenticationService
				.getErrorCodes();
		return response;
	}

	/**
	 * Retrieves list of Enumerators
	 * 
	 * @return EnumListResponseDTO
	 */
	@RequestMapping(value = "/getEnumsList", method = RequestMethod.GET)
	@ResponseBody
	public EnumListResponseDTO getEnumsList() {

		EnumListResponseDTO response = authenticationService.getEnumsList();
		return response;
	}

	/**
	 * @return the authenticationService
	 */
	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	/**
	 * @param authenticationService
	 *            the authenticationService to set
	 */
	public void setAuthenticationService(
			AuthenticationService authenticationService) {

		this.authenticationService = authenticationService;
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

	/**
	 * @return labService
	 */
	public LabService getLabService() {
		return labService;
	}
	/**
	 * @param labService
	 */
	public void setLabService(LabService labService) {
		this.labService = labService;
	}

}
