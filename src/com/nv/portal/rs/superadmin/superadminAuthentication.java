package com.nv.portal.rs.superadmin;

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
import com.nv.youNeverWait.rs.dto.CaptchaResponseDTO;
import com.nv.youNeverWait.rs.dto.CaptchaVerificationDTO;
import com.nv.youNeverWait.rs.dto.CaptchaVerificationResponseDTO;
import com.nv.youNeverWait.rs.dto.CreatePasswordDTO;
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
import com.nv.youNeverWait.user.bl.service.LogService;
import com.nv.youNeverWait.user.bl.service.SuperAdminService;

@Controller
@RequestMapping("auth")
public class superadminAuthentication {
	private AuthenticationService authenticationService;
	private SuperAdminService service;
	private LogService logService;
	
	
	/**
	 * To show login page for superadminloginpage
	 * 
	 * @return superadminLogin.html
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String pForm() {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		if(request.getSession().getAttribute("user")==null)
			return "superadminLoginPage";
		else
			return "index";
	}
	/**
	 * To reset password of superAdmin
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO resetPassword(@RequestBody LoginDTO login) {

		ResponseDTO response = new ResponseDTO();
		try {
			response = service.resetPassword(login);
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
	 * To reset password if superAdmin forgot his password
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO forgotPassword(@RequestBody LoginDTO login) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.forgotPassword(login);
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
	 * Method which performs password changing
	 * 
	 * @param passwords
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO changePassword(@RequestBody PasswordDTO passwords) 

{
		ResponseDTO response = new ResponseDTO();
		ServletRequestAttributes t = (ServletRequestAttributes) 

RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		logService.saveUserDetails(request.getRemoteAddr(), null,
				LogUserTypeEnum.Nil.getDisplayName(), null, 

null,
				

ApplicationNameEnum.SuperAdmin.getDisplayName(),
				Constants.SUPER_ADMIN_CHANGE_PASSWORD);
		try {
			response = service.changePassword(passwords);
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
	 * Get current user in the session
	 * 
	 * @return String
	 */
	@RequestMapping(value = "user", method = RequestMethod.GET)
	@ResponseBody
	public User getUser() {

		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user = (User) req.getSession().getAttribute("user");
		return user;
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
	 * Method performed for session logout
	 * 
	 * @return LoginResponseDTO
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public LoginResponseDTO logout() {

		LoginResponseDTO response = new LoginResponseDTO();
		ServletRequestAttributes t = (ServletRequestAttributes) 

RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		logService.saveUserDetails(req.getRemoteAddr(), 

null,LogUserTypeEnum.Nil.getDisplayName(), 

null,null,ApplicationNameEnum.SuperAdmin.getDisplayName(),Constants.SUPER_ADMIN_LOGOUT);
		req.getSession().setAttribute(Constants.USER, null);
		response.setSuccess(true);
		response.setError(null);

		return response;
	}
	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	public SuperAdminService getService() {
		return service;
	}
	public void setService(SuperAdminService service) {
		this.service = service;
	}
	public LogService getLogService() {
		return logService;
	}
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
