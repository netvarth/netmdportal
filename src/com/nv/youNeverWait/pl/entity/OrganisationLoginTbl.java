package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the organisation_login_tbl database table.
 * 
 */
@Entity
@Table(name="organisation_login_tbl")
public class OrganisationLoginTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int id;
	
	@Column(name="password")
	private String password;

	@Column(name="user_name")
	private String userName;

	@Column(name="user_type")
	private String userType;

	//bi-directional many-to-one association to OrganisationTbl
	@OneToMany(mappedBy="organisationLoginTbl")
	private List<OrganisationTbl> organisationTbls;

	//bi-directional many-to-one association to OrganisationUserTbl
	@OneToMany(mappedBy="organisationLoginTbl")
	private List<OrganisationUserTbl> organisationUserTbls;

	public OrganisationLoginTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<OrganisationTbl> getOrganisationTbls() {
		return this.organisationTbls;
	}

	public void setOrganisationTbls(List<OrganisationTbl> organisationTbls) {
		this.organisationTbls = organisationTbls;
	}

	public OrganisationTbl addOrganisationTbl(OrganisationTbl organisationTbl) {
		getOrganisationTbls().add(organisationTbl);
		organisationTbl.setOrganisationLoginTbl(this);

		return organisationTbl;
	}

	public OrganisationTbl removeOrganisationTbl(OrganisationTbl organisationTbl) {
		getOrganisationTbls().remove(organisationTbl);
		organisationTbl.setOrganisationLoginTbl(null);

		return organisationTbl;
	}

	public List<OrganisationUserTbl> getOrganisationUserTbls() {
		return this.organisationUserTbls;
	}

	public void setOrganisationUserTbls(List<OrganisationUserTbl> organisationUserTbls) {
		this.organisationUserTbls = organisationUserTbls;
	}

	public OrganisationUserTbl addOrganisationUserTbl(OrganisationUserTbl organisationUserTbl) {
		getOrganisationUserTbls().add(organisationUserTbl);
		organisationUserTbl.setOrganisationLoginTbl(this);

		return organisationUserTbl;
	}

	public OrganisationUserTbl removeOrganisationUserTbl(OrganisationUserTbl organisationUserTbl) {
		getOrganisationUserTbls().remove(organisationUserTbl);
		organisationUserTbl.setOrganisationLoginTbl(null);

		return organisationUserTbl;
	}

}