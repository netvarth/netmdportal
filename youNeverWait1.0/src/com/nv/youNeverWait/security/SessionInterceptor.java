/**
 * SessionInterceptor.java
 * @author Luciya Jos
 */
package com.nv.youNeverWait.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (request.getSession(true).getAttribute("user") == null) {

			/* youneverwait domain */
			if (request.getServerName().equals("www.youneverwait.com")) {

				/* Patient login */
				if (!request.getRequestURI().equals(
						"/youNeverWait/ws/ui/patient/pForm")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ws/ui/auth/patientLogin")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ws/ui/patient/createPassword")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ws/ui/patient/forgotPassword")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ws/ui/patient/resetPassword")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ws/ui/auth/getErrorCodes")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ws/ui/auth/getEnumsList")) {
					request.getRequestDispatcher("/ws/ui/patient/pForm")
							.forward(request, response);
					return false;

				}
			} // end of if loop of youNeverWait domain

			/* netLims domain */
			if (request.getServerName().equals("www.netlims.in")) {

				String container = request.getRequestURI();
				String adminContent = "youNeverWait/ws/ui/superAdmin/";

				/* Super admin login */
				if (container.toUpperCase()
						.contains(adminContent.toUpperCase())) {

					if (!request.getRequestURI().equals(
							"/youNeverWait/ws/ui/superAdmin/sForm")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/superAdmin/login")
							&& !request
									.getRequestURI()
									.equals("/youNeverWait/ws/ui/superAdmin/resetPassword")
							&& !request
									.getRequestURI()
									.equals("/youNeverWait/ws/ui/superAdmin/forgotPassword")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/getErrorCodes")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/getEnumsList")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/getCaptcha")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/verifyCaptcha")) {

						request.getRequestDispatcher("/ws/ui/superAdmin/sForm")
								.forward(request, response);
						return false;

					}
				}
				/* netLims login */
				else {

					if (!request.getRequestURI().equals(
							"/youNeverWait/ws/ui/lab/lForm")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/netlimsLogin")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/getCaptcha")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/verifyCaptcha")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/lab/resetPassword")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/lab/forgotPassword")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/lab/activateLab")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/getErrorCodes")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/getEnumsList")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/lab/aboutNetLims")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/lab/contactUs")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/lab/pricing")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/lab/privacyPolicy")
							&& !request
									.getRequestURI()
									.equals("/youNeverWait/ws/ui/lab/checkSystemHealth")
							&& !request
									.getRequestURI()
									.equals("/youNeverWait/ws/ui/lab/retrieveBranchOrders")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/lab/orderTransfer")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/lab/resultTransfer")
							&& !request.getRequestURI().equals
							("/youNeverWait/ws/ui/lab/mailResult")) {
						request.getRequestDispatcher("/ws/ui/lab/lForm")
								.forward(request, response);
						return false;

					}
				}
			} // end of netlims if loop

			/* netRx domain */
			if (request.getServerName().equals("www.netrx.in")) {

				if (!request.getRequestURI().equals(
						"/youNeverWait/ws/ui/netRx/rForm")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ws/ui/auth/netrxLogin")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ws/ui/auth/getCaptcha")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ws/ui/auth/verifyCaptcha")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ws/ui/auth/getErrorCodes")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ws/ui/auth/getEnumsList")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ws/ui/netRx/resetPassword")
						&& !request.getRequestURI().equals(
								"/youNeverWait/ws/ui/netRx/forgotPassword")) {
					request.getRequestDispatcher("/ws/ui/netRx/rForm").forward(
							request, response);
					return false;

				}

			} // end of netrx if loop

			/* netMd domain */
			if (request.getServerName().equals("www.netmd.co.in")) {
				String containr = request.getRequestURI();
				String orgnContent = "youNeverWait/ws/ui/orgn/";
				/* Organization domain */
				if (containr.toUpperCase().contains(orgnContent.toUpperCase())) {
					if (!request.getRequestURI().equals(
							"/youNeverWait/ws/ui/orgn/oForm")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/orgn/login")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/orgn/resetPassword")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/orgn/forgotPassword")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/getErrorCodes")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/getEnumsList")) {
						request.getRequestDispatcher("/ws/ui/orgn/oForm")
								.forward(request, response);
						return false;

					}
				} else {
					if (!request.getRequestURI().equals(
							"/youNeverWait/ws/ui/netMd/mForm")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/netmdLogin")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/getCaptcha")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/verifyCaptcha")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/netMd/resetPassword")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/doctor/resetPassword")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/netMd/forgotPassword")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/netMd/activateNetMd")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/sync/syncData")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/getErrorCodes")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/auth/getEnumsList")
							&& !request.getRequestURI().equals(
									"/youNeverWait/ws/ui/netMd/privacyPolicy")) {
						request.getRequestDispatcher("/ws/ui/netMd/mForm")
								.forward(request, response);
						return false;

					}
				}

			}// end of netmd if loop

		}
		return true;
	}

}
