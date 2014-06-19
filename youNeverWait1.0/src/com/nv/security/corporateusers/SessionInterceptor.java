/**
 * SessionInterceptor.java
 * @author Luciya Jos
 */
package com.nv.security.corporateusers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println(request.getRequestURI());
		if (request.getSession().getAttribute("user") == null) {

			/*Organisation */
			System.out.println(request.getRequestURI());
			
			
			
				if (!request.getRequestURI().equals(
						"/youNeverWait/orgn/auth/")
						&& !request.getRequestURI().equals(
								"/youNeverWait/orgn/ui/orgn/login")
						&& !request.getRequestURI().equals(
								"/youNeverWait/orgn/auth/resetPassword")
						&& !request.getRequestURI().equals(
								"/youNeverWait/orgn/auth/forgotPassword")
						&& !request.getRequestURI().equals(
								"/youNeverWait/orgn/auth/getErrorCodes")
						&& !request.getRequestURI().equals(
								"/youNeverWait/orgn/auth/getEnumsList")) {
					request.getRequestDispatcher("/orgn/auth/")
							.forward(request, response);
					return false;
				}
				}
		return true;
	}
	}



