/**
 * DoctorRegistrationEmailPerformHandler.java
 *
 * Mar 27, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.msg;

import com.nv.platform.email.sendmsg.SendMsgResultNotifier;
import com.nv.platform.email.sendmsg.SendMsgStatus;
import com.nv.platform.email.sendmsg.email.SendMailMsgObj;

public class DoctorRegistrationEmailPerformHandler  implements
SendMsgResultNotifier<SendMailMsgObj> {

	@Override
	public void sendMsgPerformed(SendMailMsgObj obj) {
		// Perform Success and Failure action for each request
				if (obj.getStatus().equals(SendMsgStatus.SUCCESS)) {
					System.out.println("Successfully send  doctor registration email  - CALLBACK FUNCTION");
				} else if (obj.getStatus().equals(SendMsgStatus.FAILED)) {
					System.out.println("Failed to send doctor registration email  - CALLBACK FUNCTION");
				}
	}

}
