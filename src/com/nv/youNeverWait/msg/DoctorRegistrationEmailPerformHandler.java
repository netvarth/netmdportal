/**
 * DoctorRegistrationEmailPerformHandler.java
 *
 * Mar 27, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.msg;

import com.nv.framework.sendmsg.SendMsgResultNotifier;
import com.nv.framework.sendmsg.SendMsgStatus;
import com.nv.framework.sendmsg.email.SendMailMsgObj;

public class DoctorRegistrationEmailPerformHandler  implements
SendMsgResultNotifier<SendMailMsgObj> {

	@Override
	public void sendMsgPerformed(SendMailMsgObj obj) {
		// Perform Success and Failure action for each request
				if (obj.getStatus().equals(SendMsgStatus.SUCCESS)) {
					System.out.println("Successfully send  doctor registration email  - CALLBACK FUNCTION");
				} else if (obj.getStatus().equals(SendMsgStatus.FAILED)) {
					System.out.pri ntln("Failed to send doctor registration email  - CALLBACK FUNCTION");
				}
	}

}
