/**
 * SessionInterceptor.java
 * @author Luciya Jos
 */
package com.nv.security.netlims;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Luciya Jos
 *
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (request.getSession(true).getAttribute("user") == null) {

			
				 /*netLims login */ 
				
					System.out.println(request.getRequestURI());
					
					if (!request.getRequestURI().equals(
							"/youNeverWait/netlims/auth/")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/auth/netlimsLogin")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/auth/getCaptcha")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/auth/verifyCaptcha")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/ui/lab/resetPassword")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/ui/lab/forgotPassword")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/ui/lab/activateLab")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/ui/auth/getErrorCodes")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/ui/auth/getEnumsList")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/ui/lab/aboutNetLims")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/ui/lab/contactUs")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/ui/lab/pricing")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/ui/lab/privacyPolicy")
							&& !request
									.getRequestURI()
									.equals("/youNeverWait/netlims/ui/lab/checkSystemHealth")
							&& !request
									.getRequestURI()
									.equals("/youNeverWait/netlims/ui/lab/retrieveBranchOrders")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/ui/lab/orderTransfer")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/ui/lab/resultTransfer")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/ui/sync/order")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/ui/sync/facility")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/ui/sync/referral")
							&& !request.getRequestURI().equals(
									"/youNeverWait/netlims/ui/sync/user")
							&& !request.getRequestURI().equals
							("/youNeverWait/netlims/ui/lab/mailResult")) {
						request.getRequestDispatcher("/netlims/auth/")
								.forward(request, response);
						return false;

					}
				}
		return true;
	}

}
