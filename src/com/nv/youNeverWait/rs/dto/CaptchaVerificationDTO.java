/**
 * CaptchaVerificationDTO.java 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Asha Chandran
 *
 */
public class CaptchaVerificationDTO {
private String secretCode;
private String verificationCode;
/**
 * @return the secretCode
 */
public String getSecretCode() {
	return secretCode;
}
/**
 * @param secretCode the secretCode to set
 */
public void setSecretCode(String secretCode) {
	this.secretCode = secretCode;
}
/**
 * @return the verificationCode
 */
public String getVerificationCode() {
	return verificationCode;
}
/**
 * @param verificationCode the verificationCode to set
 */
public void setVerificationCode(String verificationCode) {
	this.verificationCode = verificationCode;
}

}
