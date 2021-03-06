/**
 * OrganisationResource.java
 * January 10, 2013
 */
package com.nv.portal.rs.corporateusers;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.nv.security.youNeverWait.User;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ApplicationNameEnum;
import com.nv.youNeverWait.pl.entity.LogUserTypeEnum;
import com.nv.youNeverWait.report.PDFReportView;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;

import com.nv.youNeverWait.rs.dto.OrganisationListResponseDTO;
import com.nv.youNeverWait.rs.dto.OrganizationViewResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ReportListFilterDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.UserDetails;
import com.nv.youNeverWait.user.bl.service.LogService;
import com.nv.youNeverWait.user.bl.service.OrganisationService;

/**
 * @author Luciya Jose
 * 
 */
@Controller
@RequestMapping("ui/orgn/")
public class OrganisationResource   {
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
					user.setAccountName(userDetail.getAccountName());
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
	ServletContext context =	request.getSession().getServletContext();	
    
	Map<String, Object> model = new HashMap<String, Object>();
		
    model.put("reportName", request.getParameter("reportName"));
	model.put("startMonth",request.getParameter("startMonth"));
	model.put("startYear", request.getParameter("startYear"));
	model.put("endMonth", request.getParameter("endMonth"));
	model.put("endYear", request.getParameter("endYear"));
	
	model.put("paramList", request.getParameter("paramList"));
    
    JasperPrint jPrint =organisationService.createReport(model, context);
    model.put("jPrint",jPrint );		
	return new ModelAndView(view, model);
	}

	/**
	 * To get Organization list
	 * 
	 * @return OrganizationViewResponseDTO
	 */
	@RequestMapping(value = "getOrganisationList", method = RequestMethod.GET)
	@ResponseBody
	public OrganisationListResponseDTO getOrganisationList() {

		OrganisationListResponseDTO response = new OrganisationListResponseDTO();
		try {
			response = organisationService.getOrganisationList();
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
	 * To get Organization FilterList
	 * 
	 * @return OrganizationViewResponseDTO
	 */
	@RequestMapping(value = "getReportFilterList", method = RequestMethod.GET)
	@ResponseBody
	public ReportListFilterDTO getReportFilterList() {

		ReportListFilterDTO response = new ReportListFilterDTO();
		try {
			response = organisationService.getFilterList();
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
	
	@RequestMapping(value ="getGraphics",method=RequestMethod.GET)
	public String check(){
		return "samplehtml";
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
