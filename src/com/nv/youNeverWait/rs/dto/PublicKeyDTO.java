package com.nv.youNeverWait.rs.dto;

public class PublicKeyDTO {
	
	private String publicKey;
	private ErrorDTO errorDTO;
	private boolean success;
	
	
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public ErrorDTO getErrorDTO() {
		return errorDTO;
	}
	public void setErrorDTO(ErrorDTO errorDTO) {
		this.errorDTO = errorDTO;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

}
