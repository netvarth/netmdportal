package com.nv.portal.rs.corporateusers;

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
import com.nv.youNeverWait.user.bl.service.NetMdService;
import com.nv.youNeverWait.user.bl.service.OrganisationService;

@Controller
@RequestMapping("auth")

public class OrgAuthentication {
	private AuthenticationService authenticationService;
	private LogService logService;
	private OrganisationService organisationService;
	
	/**
	 * To show login page for netmd
	 * 
	 * @return netMdLogin.html
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String pForm() {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		if(request.getSession().getAttribute("user")==null)
			return "organisationLoginPage";
		else
			return "organisationIndex";
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
			response = organisationService.forgotPassword(login);
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
				ApplicationNameEnum.Organisation.getDisplayName(),
				Constants.FORGOT_PWD);

		return response;
	}

	/**
	 * To reset password of organisation user/owner
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO resetPassword(@RequestBody LoginDTO login) {

		ResponseDTO response = new ResponseDTO();
		try {
			response = organisationService.resetPassword(login);
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
				login.getUserType(), null, null,
				ApplicationNameEnum.Organisation.getDisplayName(),
				Constants.RESET_PSWD);
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
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public LogService getLogService() {
		return logService;
	}

	public OrganisationService getOrganisationService() {
		return organisationService;
	}

	public void setOrganisationService(OrganisationService organisationService) {
		this.organisationService = organisationService;
	}


}
