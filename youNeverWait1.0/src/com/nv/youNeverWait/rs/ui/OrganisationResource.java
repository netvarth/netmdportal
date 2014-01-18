/**
 * OrganisationResource.java
 * January 10, 2013
 */
package com.nv.youNeverWait.rs.ui;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ApplicationNameEnum;
import com.nv.youNeverWait.pl.entity.LogUserTypeEnum;
import com.nv.youNeverWait.report.PDFReportView;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.UserDetails;
import com.nv.youNeverWait.security.User;
import com.nv.youNeverWait.user.bl.service.LogService;
import com.nv.youNeverWait.user.bl.service.OrganisationService;

/**
 * @author Luciya Jose
 * 
 */
@Controller
@RequestMapping("ui/orgn/")
public class OrganisationResource {
	private LogService logService;
	private OrganisationService organisationService;
	private PDFReportView view;

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
		logService.saveUserDetails(request.getRemoteAddr(), null,
				login.getUserType(), null, null,
				ApplicationNameEnum.Organisation.getDisplayName(),
				Constants.RESET_PSWD);
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
					ApplicationNameEnum.Organisation.getDisplayName(),
					Constants.LOGOUT);
		}
		req.getSession().setAttribute(Constants.USER, null);
		response.setSuccess(true);
		response.setError(null);
		return response;
	}

	/**
	 * Method performed for organisation login
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponseDTO organisationLogin(@RequestBody LoginDTO login) {
		User user = new User();
		LoginResponseDTO response = new LoginResponseDTO();
		try {
			response = organisationService.organisationLogin(login);
			if (response.isSuccess()) {
				ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes();
				HttpServletRequest req = t.getRequest();

				UserDetails userDetail = organisationService
						.getOrganisationUser(login.getUserName());
				if (userDetail != null) {
					user.setLoginTime(new Date());
					user.setName(userDetail.getName());
					user.setUserName(login.getUserName().trim());
					user.setId(userDetail.getId());
					user.setOrganisationId(userDetail.getOrganisationId());
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
		logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
				user.getUserType(), user.getLoginTime(), null,
				ApplicationNameEnum.Organisation.getDisplayName(),
				Constants.LOGIN);
		return response;
	}

	/**
	 * generates a report in pdf view
	 * 
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/report/generate", method = RequestMethod.POST)
	public ModelAndView generate(HttpServletRequest request) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("JRXML_URL", organisationService.getJRXmlPath(request
				.getParameter("reportName")));
		model.put("REPORT_CONNECTION", organisationService.getConnection());
		// model.put("reportTitle",adminService.getReportTitle());
		
			model.put("startMonth",request.getParameter("startMonth"));
			model.put("startYear", request.getParameter("startYear"));
			model.put("endMonth", request.getParameter("endMonth"));
			model.put("endYear", request.getParameter("endYear"));
		
			
		model.put("paramList", request.getParameter("paramList"));
		// model.put("maskingList", request.getParameter("maskingList"));
		return new ModelAndView(view, model);
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

	public PDFReportView getView() {
		return view;
	}

	public void setView(PDFReportView view) {
		this.view = view;
	}

	
}
