/**
 * Patient.java
 * @author Mani E.V 
 *
 * Version 1.0 11-Jun-2014
 *
 * Copyright (c) 2014 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

/**
 *
 *
 * @author Mani E.V
 */
public class Patient {
	private String honorifics;
	private String name;
	private String age;
	private String gender;
	private Address address;
	/**
	 * @return the honorifics
	 */
	public String getHonorifics() {
		return honorifics;
	}
	/**
	 * @param honorifics the honorifics to set
	 */
	public void setHonorifics(String honorifics) {
		this.honorifics = honorifics;
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
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
