/**
 * OrganisationResource.java
 * January 10, 2013
 */
package com.nv.youNeverWait.rs.ui;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
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
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.user.bl.service.LogService;
import com.nv.youNeverWait.user.bl.service.OrganisationService;
import com.nv.youNeverWait.user.pl.dao.OrganisationDao;

/**
 * @author Luciya Jose
 *
 */
@Controller
@RequestMapping("ui/orgn/")
public class OrganisationResource {
	private LogService logService;
	private OrganisationService organisationService;

	/**
	 * To show login page for organisation user/owner
	 * 
	 * @return organisationLoginPage.html
	 */
	@RequestMapping(value = "startUp", method = RequestMethod.GET)
	public String startUp() {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		logService.saveUserDetails(request.getRemoteAddr(), null,
						LogUserTypeEnum.Nil.getDisplayName(), null, null,
						ApplicationNameEnum.Organisation.getDisplayName(),
						Constants.STARTUP);

		return "organisationIndex";
	}
	
	/**
	 * To show login page for lab user/owner
	 * 
	 * @return netLimsLoginPage.html
	 */
	@RequestMapping(value = "oForm", method = RequestMethod.GET)
	public String lForm() {
		return "organisationLoginPage";
	}
	
	/**
	 * To show login page for organisation user/owner
	 * 
	 * @return organisationLoginPage.html
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home() {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		logService.saveUserDetails(request.getRemoteAddr(), null,
						LogUserTypeEnum.Nil.getDisplayName(), null, null,
						ApplicationNameEnum.Organisation.getDisplayName(),
						Constants.HOME);

		return "organisationHome";
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
	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
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
		logService.saveUserDetails(request.getRemoteAddr(),
				null, login.getUserType(), null, null,
				ApplicationNameEnum.Organisation.getDisplayName(),
				Constants.RESET_PSWD);
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
