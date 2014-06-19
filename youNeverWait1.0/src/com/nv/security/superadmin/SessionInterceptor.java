/**
 * SessionInterceptor.java
 * @author Luciya Jos
 */
package com.nv.security.superadmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (request.getSession(true).getAttribute("user") == null) {

			
				//String container = request.getRequestURI();
				//String adminContent = "youNeverWait/sadmin/ui/superAdmin/";

				//if (container.toUpperCase()
						//.contains(adminContent.toUpperCase())) {

					if (!request.getRequestURI().equals(
							"/youNeverWait/superadmin/auth/")
							&& !request.getRequestURI().equals(
									"/youNeverWait/superadmin/ui/superAdmin/login")
							&& !request
									.getRequestURI()
									.equals("/youNeverWait/superadmin/auth/resetPassword")
							&& !request
									.getRequestURI()
									.equals("/youNeverWait/superadmin/auth/forgotPassword")
							&& !request.getRequestURI().equals(
									"/youNeverWait/superadmin/auth/getErrorCodes")
							&& !request.getRequestURI().equals(
									"/youNeverWait/superadmin/auth/getEnumsList")
							&& !request.getRequestURI().equals(
									"/youNeverWait/superadmin/auth/getCaptcha")
							&& !request.getRequestURI().equals(
									"/youNeverWait/superadmin/auth/verifyCaptcha")) {

						request.getRequestDispatcher("/superadmin/auth/")
								.forward(request, response);
						return false;

					}}
				
		return true;
	}

}
