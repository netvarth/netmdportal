/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Asha Chandran
 *
 */
public class CaptchaResponseDTO {
	private byte[] image;
	private String secretCode;
	private ErrorDTO error;
	private boolean success;
	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}
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
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

}
