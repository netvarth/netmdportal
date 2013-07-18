/**
 * NetRxBranchDetail.java
 *
 * @Author Luciya Jos
 * May 14, 2013 
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

import com.nv.youNeverWait.pl.entity.NetrxBranchTbl;

/**
 * @author netvarth
 *
 */
public class NetRxBranchDetail {
	private int id;
	public int globalId;
	private String name;
	private String email;
	private String phone;
	private String mobile;
	private String address;
	private String status;
	private int netRxId;
	private List<PassPhraseDTO> passPhrase = new ArrayList<PassPhraseDTO>();
	private int numberOfDevices;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the passPhrase
	 */
	public List<PassPhraseDTO> getPassPhrase() {
		return passPhrase;
	}
	/**
	 * @param passPhrase the passPhrase to set
	 */
	public void setPassPhrase(List<PassPhraseDTO> passPhrase) {
		this.passPhrase = passPhrase;
	}
	/**
	 * @return the numberOfDevices
	 */
	public int getNumberOfDevices() {
		return numberOfDevices;
	}
	/**
	 * @param numberOfDevices the numberOfDevices to set
	 */
	public void setNumberOfDevices(int numberOfDevices) {
		this.numberOfDevices = numberOfDevices;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
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
	
	
	/**
	 * @return the netRxId
	 */
	public int getNetRxId() {
		return netRxId;
	}
	/**
	 * @param netRxId the netRxId to set
	 */
	public void setNetRxId(int netRxId) {
		this.netRxId = netRxId;
	}
	/**
	 * @param globalId
	 * @param name
	 * @param email
	 * @param phone
	 * @param mobile
	 * @param address
	 * @param status
	 * @param netRxId
	 */
	public NetRxBranchDetail(NetrxBranchTbl netRxBranchTbl) {
		this.globalId = netRxBranchTbl.getId();
		this.name = netRxBranchTbl.getName();
		this.email = netRxBranchTbl.getEmail();
		this.phone = netRxBranchTbl.getPhone();
		this.mobile = netRxBranchTbl.getMobile();
		this.address = netRxBranchTbl.getAddress();
		this.status = netRxBranchTbl.getStatus();
		this.netRxId = netRxBranchTbl.getNetrxTbl().getId();
	}
	/**
	 * 
	 */
	public NetRxBranchDetail() {
		
	}
	

}
