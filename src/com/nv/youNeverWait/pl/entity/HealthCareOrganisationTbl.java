package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the health_care_organisation_tbl database table.
 * 
 */
@Entity
@Table(name="health_care_organisation_tbl")
@Inheritance(strategy=InheritanceType.JOINED)
public class HealthCareOrganisationTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String address;

	private String email;

	private String mobile;

	private String name;

	private String phone;

	private String status;

	//bi-directional one-to-one association to NetmdBranchTbl
	@OneToOne(mappedBy="healthCareOrganisationTbl")
	private NetmdBranchTbl netmdBranchTbl;

	//bi-directional one-to-one association to OrganisationTbl
	@OneToOne(mappedBy="healthCareOrganisationTbl")
	private OrganisationTbl organisationTbl;

	public HealthCareOrganisationTbl() {
	}

	public int getId() {
		return this.id;
	}
	
	
	

	public void setId(int id) {n
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public NetmdBranchTbl getNetmdBranchTbl() {
		return this.netmdBranchTbl;
	}

	public void setNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		this.netmdBranchTbl = netmdBranchTbl;
	}

	public OrganisationTbl getOrganisationTbl() {
		return this.organisationTbl;
	}

	public void setOrganisationTbl(OrganisationTbl organisationTbl) {
		this.organisationTbl = organisationTbl;
	}

}