/**
 * NetMdListResponseDTO.java
 * 
 * @author Asha Chandran
 *
 * Mar 11, 2013
 */
package com.nv.youNeverWait.rs.dto;

import com.nv.youNeverWait.pl.entity.NetmdBranchTbl;

public class NetMdBranchDetail {
	public int globalId;
	private String name;
	private String email;
	private String phone;
	private String mobile;
	private String address;
	private String status;
	private String netmdId;
	

	public NetMdBranchDetail(NetmdBranchTbl netmdBranchTbl) {
		this.setAddress(netmdBranchTbl.getAddress());
		this.setEmail(netmdBranchTbl.getEmail());
		this.setGlobalId(netmdBranchTbl.getId());
		this.setMobile(netmdBranchTbl.getMobile());
		this.setName(netmdBranchTbl.getName());
		this.setPhone(netmdBranchTbl.getPhone());
		this.setStatus(netmdBranchTbl.getStatus());
		this.setNetmdId(String.valueOf(netmdBranchTbl.getNetmdTbl().getId()));
		
	}

	/**
	 * @return the globalId
	 */
	public int getGlobalId() {
		return globalId;
	}

	/**
	 * @param globalId
	 *            the globalId to set
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
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
	 * @param mobile
	 *            the mobile to set
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
	 * @param address
	 *            the address to set
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
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getNetmdId() {
		return netmdId;
	}

	public void setNetmdId(String netmdId) {
		this.netmdId = netmdId;
	}

	

}
