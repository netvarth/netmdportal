/**
 * 
 */
package com.nv.youNeverWait.user.bl.validation;

/**
 * @author Mani E.V
 *
 */
public class LabFacilityValidator {
	
	private boolean isValidEmail(String Email) {
		if (Email
				.matches("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {
			return true;
		}
		return false;
	}
	
	private boolean isValidName(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}
		return false;
	}
	
}
