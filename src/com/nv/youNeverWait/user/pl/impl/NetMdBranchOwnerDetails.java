/**
 * NetMdBranchOwnerDetails.java
 *
 * Mar 06, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.pl.impl;

import java.util.ArrayList;
import java.util.List;

public class NetMdBranchOwnerDetails {
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerEmail;
	private String branchName;
	private List<String> passPhrase = new ArrayList<String>();
	private String primaryPassPhrase;
	private String netMdName;

	/**
	 * @return the ownerFirstName
	 */
	public String getOwnerFirstName() {
		return ownerFirstName;
	}
	/**
	 * @param ownerFirstName the ownerFirstName to set
	 */
	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}
	/**
	 * @return the ownerLastName
	 */
	public String getOwnerLastName() {
		return ownerLastName;
	}
	/**
	 * @param ownerLastName the ownerLastName to set
	 */
	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}
	/**
	 * @return the ownerEmail
	 */
	public String getOwnerEmail() {
		return ownerEmail;
	}
	/**
	 * @param ownerEmail the ownerEmail to set
	 */
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	/**
	 * @return the netMdName
	 */
	public String getNetMdName() {
		return netMdName;
	}
	/**
	 * @param netMdName the netMdName to set
	 */
	public void setNetMdName(String netMdName) {
		this.netMdName = netMdName;
	}
	/**
	 * @return the passPhrase
	 */
	public List<String> getPassPhrase() {
		return passPhrase;
	}
	/**
	 * @param passPhrase the passPhrase to set
	 */
	public void setPassPhrase(List<String> passPhrase) {
		this.passPhrase = passPhrase;
	}
	/**
	 * @return the primaryPassPhrase
	 */
	public String getPrimaryPassPhrase() {
		return primaryPassPhrase;
	}
	/**
	 * @param primaryPassPhrase the primaryPassPhrase to set
	 */
	public void setPrimaryPassPhrase(String primaryPassPhrase) {
		this.primaryPassPhrase = primaryPassPhrase;
	}
	
}
