package com.nv.youNeverWait.rs.dto;

public class HealthCareDetailDTO {
	
	private String branchId;
	private String branchName;
	private String publicKey;
	private String address;
	private String phoneNumber;
	private String mobilNumber;
	private String email;
	private ErrorDTO errorDTO;
	private boolean success;
	
	public String getBranchId() {
		return branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public String getAddress() {
		return address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getMobilNumber() {
		return mobilNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setMobilNumber(String mobilNumber) {
		this.mobilNumber = mobilNumber;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ErrorDTO getErrorDTO() {
		return errorDTO;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setErrorDTO(ErrorDTO errorDTO) {
		this.errorDTO = errorDTO;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
