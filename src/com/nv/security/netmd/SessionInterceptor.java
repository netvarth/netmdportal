/**
 * SessionInterceptor.java
 * @author Luciya Jos
 */
package com.nv.security.netmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (request.getSession(true).getAttribute("user") == null) {
			System.out.println(request.getRequestURI());
			if (!request.getRequestURI().equals(
							"/youNeverWait/netmd/auth/")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netmd/auth/netmdLogin")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netmd/auth/getCaptcha")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netmd/auth/verifyCaptcha")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netmd/auth/resetPassword")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netmd/ui/doctor/resetPassword")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netmd/ui/netMd/forgotPassword")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netmd/ui/netMd/activateNetMd")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netmd/ui/sync/syncData")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netmd/auth/getErrorCodes")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netmd/auth/getEnumsList")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netmd/ui/netMd/privacyPolicy")) {
						request.getRequestDispatcher("/netmd/auth/")
								.forward(request, response);
						return false;

					}
				}

			//}// end of netmd if loop

		//}
		return true;
	}

}
