/**
 * AuthenticationResource.java
 * 
 * @Author Asha Chandran
 *
 * Dec 5, 2012
 */
package com.nv.portal.rs.youNeverWait;

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

@Controller
@RequestMapping("auth")
public class AuthenticationResource {
	private AuthenticationService authenticationService;
	private LogService logService;

	
	
	/**
	 * To show login page for patient
	 * 
	 * @return patientLogin.html
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String pForm() {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		if(request.getSession().getAttribute("user")==null)
			return "patientLogin";
		else
			return "patientindex";
	}
	
	
	/**
	 * Method performed for patient login
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 */
	@RequestMapping(value = "/patientLogin", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponseDTO patientLogin(@RequestBody LoginDTO login) {

		LoginResponseDTO response = new LoginResponseDTO();
		User user = new User();
		
		try {
			response = authenticationService.patientLogin(login);
			if (response.isSuccess()) {
				ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes();
				HttpServletRequest req = t.getRequest();
				UserDetails userDetail = authenticationService.getPatient(login.getUserName());
				System.out.println("patient user type is !!!!!!!!!!!!!!!!!!!!   "+userDetail.getUserType());
				user.setLoginTime(new Date());
				user.setUserName(login.getUserName().trim());
				user.setName(login.getUserName().trim());
				user.setUserType(userDetail.getUserType());
				user.setNetmdId(userDetail.getNetmdId());
				req.getSession(true).setAttribute(Constants.USER, user);
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
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user = (User) req.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(req.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), new Date(),
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.LOGOUT);
		}
		req.getSession().setAttribute(Constants.USER, null);
		response.setSuccess(true);
		response.setError(null);
		return response;
	}

	
	/**
	 * Change password
	 * @param passwords
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO changePassword(@RequestBody PasswordDTO passwords)
	{
		ResponseDTO response=new ResponseDTO();
		try{
			response=authenticationService.changePassword(passwords);
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
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.CHANGE_PASSWORD);
		}
		return response;
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
			response = authenticationService.resetPassword(login);
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
				ApplicationNameEnum.Patient.getDisplayName(),
				Constants.RESET_PSWD);
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
			response=authenticationService.forgotPassword(login);
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
		logService.saveUserDetails(request.getRemoteAddr(),
				login.getUserName(), login.getUserType(), null, null,
				ApplicationNameEnum.Patient.getDisplayName(),
				Constants.RESET_PSWD);
		return response;
	}

	
	/**
	 * Create password
	 * @param passwords
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "createPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createPassword(@RequestBody CreatePasswordDTO passwords)
	{
		ResponseDTO response=new ResponseDTO();
		try{
			response=authenticationService.createPassword(passwords);
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
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.CREATE_PASSWORD);
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
	 * Method performed for NetLims login
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 
	@RequestMapping(value = "netlimsLogin", method = RequestMethod.POST)
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
				
				UserDetails userDetail = authenticationService
						.getNetlimsUser(login.getUserName());
				if (userDetail != null) {
					user.setLoginTime(new Date());
					user.setName(userDetail.getName());
					user.setUserName(login.getUserName().trim());
					user.setId(userDetail.getId());
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
	 * Method performed for NetMd login
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 
	@RequestMapping(value = "netmdLogin", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponseDTO netmdLogin(@RequestBody LoginDTO login) {

		LoginResponseDTO response = new LoginResponseDTO();
		User user = new User();
		
		try {
			response = authenticationService.netmdLogin(login);
			if (response.isSuccess()) {
				ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes();
				HttpServletRequest req = t.getRequest();
				UserDetails userDetail = authenticationService
						.getNetmdUser(login.getUserName());
				if (userDetail != null) {
					user.setLoginTime(new Date());
					user.setId(userDetail.getId());
					user.setName(userDetail.getName());
					user.setUserName(login.getUserName().trim());
					user.setUserType(userDetail.getUserType());
					user.setNetmdId(userDetail.getNetmdId());
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
				ApplicationNameEnum.NetMd.getDisplayName(),
				Constants.LOGIN);
		return response;
	}
	/**
	 * Method performed for netrx login
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 
	@RequestMapping(value = "netrxLogin", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponseDTO netRxLogin(@RequestBody LoginDTO login) {

		LoginResponseDTO response = new LoginResponseDTO();
		User user = new User();
		
		try {
			response = authenticationService.netrxLogin(login);
			if (response.isSuccess()) {
				ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes();
				HttpServletRequest req = t.getRequest();
				UserDetails userDetail = authenticationService
						.getNetrxUser(login.getUserName());
				if (userDetail != null) {
					user.setLoginTime(new Date());
					user.setId(userDetail.getId());
					user.setName(userDetail.getName());
					user.setUserName(login.getUserName().trim());
					user.setUserType(userDetail.getUserType());
					user.setNetrxId(userDetail.getNetrxId());
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
		return response;
	}
	
	/**
	 * Get current user in the session
	 * 
	 * @return String
	 */
	@RequestMapping(value = "getCurrentUser", method = RequestMethod.GET)
	@ResponseBody
	public String getCurrentUser() {

		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user = (User) req.getSession().getAttribute("user");
		return user.getUserName();
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

}
