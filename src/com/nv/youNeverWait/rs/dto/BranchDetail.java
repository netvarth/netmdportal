/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

import com.nv.youNeverWait.pl.entity.LabBranchTbl;

/**
 * @author Luciya Jose
 *
 */
public class BranchDetail {
	public int globalId;
	public String status;
	private String name;
	private String phone;
	private String mobile;
	private String address;
	/**
	 * @param branchTbl
	 */
	public BranchDetail(LabBranchTbl branchTbl) {
		this.globalId = branchTbl.getId();
		this.name = branchTbl.getName();
		this.phone = branchTbl.getPhone();
		this.mobile = branchTbl.getMobile();
		this.address = branchTbl.getAddress();
		this.status = branchTbl.getStatus();
	}
	
	/**
	 * @return the globalId
	 */
	public int getGlobalId() {
		return globalId;
	}
	/**
	 * @param globalId the globalId to set
	 */
	public void setGlobalId(int globalId) {
		this.globalId = globalId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
