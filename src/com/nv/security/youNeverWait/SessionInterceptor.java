package com.nv.security.youNeverWait;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println(request.getRequestURI());
		if (request.getSession().getAttribute("user") == null) {

			/* Patient login */
			System.out.println(request.getRequestURI());
			
				if (!request.getRequestURI().equals(
						"/youNeverWait/ynw/auth/")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ynw/auth/patientLogin")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ynw/auth/createPassword")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ynw/auth/forgotPassword")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ynw/auth/resetPassword")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ynw/auth/getErrorCodes")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ynw/auth/getEnumsList")) {
					request.getRequestDispatcher("/ynw/auth/")
							.forward(request, response);
					return false;

				}
				}
		return true;
	}
	

}
