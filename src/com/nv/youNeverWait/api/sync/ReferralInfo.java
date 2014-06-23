package com.nv.youNeverWait.api.sync;

import com.nv.youNeverWait.rs.dto.Address;

public class ReferralInfo {

	private String uid;
	private String name;
	private Address address;
	
	
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
