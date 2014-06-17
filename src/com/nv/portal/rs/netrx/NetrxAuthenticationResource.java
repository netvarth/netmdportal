package com.nv.portal.rs.netrx;

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
import com.nv.youNeverWait.rs.dto.EnumListResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorCodeListResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.UserDetails;
import com.nv.youNeverWait.security.bl.service.AuthenticationService;
import com.nv.youNeverWait.user.bl.service.LogService;

@Controller
@RequestMapping("ui/auth/")

public class NetrxAuthenticationResource {
	
	private AuthenticationService authenticationService;
	private LogService logService;

	/**
	 * Method performed for netrx login
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 */
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
