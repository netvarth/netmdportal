/**
 * NetmdBranchRegistrationEmailPerformHandler.java
 *
 * Mar 27, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.msg;

import com.nv.framework.sendmsg.SendMsgResultNotifier;
import com.nv.framework.sendmsg.SendMsgStatus;
import com.nv.framework.sendmsg.email.SendMailMsgObj;

public class NetmdBranchRegistrationEmailPerformHandler implements
		SendMsgResultNotifier<SendMailMsgObj> {

	/**
	 * Callback function which does the responding back of the request made to
	 * the application.
	 * 
	 * @param obj
	 *            the obj
	 */
	@Override
	public void sendMsgPerformed(SendMailMsgObj obj) {
		// Perform Success and Failure action for each request
		if (obj.getStatus().equals(SendMsgStatus.SUCCESS)) {
			System.out.println("Successfully send  netmd branch registration email  - CALLBACK FUNCTION");
		} else if (obj.getStatus().equals(SendMsgStatus.FAILED)) {
			System.out.println("Failed to send netmd branch registration email  - CALLBACK FUNCTION");
		}
	}
}
