/**
 * LabRegistrationEmailPerformHandler.java
 *
 * Mar 27, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.msg;

import com.nv.platform.email.sendmsg.SendMsgResultNotifier;
import com.nv.platform.email.sendmsg.SendMsgStatus;
import com.nv.platform.email.sendmsg.email.SendMailMsgObj;

public class LabRegistrationEmailPerformHandler implements
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
			System.out.println("Successfully send  lab registration email  - CALLBACK FUNCTION");
		} else if (obj.getStatus().equals(SendMsgStatus.FAILED)) {
			System.out.println("Failed to send  lab registration email  - CALLBACK FUNCTION");
		}
	}
}
