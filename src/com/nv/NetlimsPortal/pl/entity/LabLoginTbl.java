package com.nv.NetlimsPortal.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the lab_login_tbl database table.
 * 
 */
@Entity
@Table(name="lab_login_tbl")
public class LabLoginTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String password;

	@Column(name="user_name", nullable=false, length=45)
	private String userName;

	@Column(name="user_type", nullable=false, length=1)
	private String userType;

	//bi-directional many-to-one association to LabTbl
	@OneToMany(mappedBy="labLoginTbl")
	private Set<LabTbl> labTbls;

	//bi-directional many-to-one association to LabUserTbl
	@OneToMany(mappedBy="labLoginTbl")
	private Set<LabUserTbl> labUserTbls;

    public LabLoginTbl() {
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

	public Set<LabTbl> getLabTbls() {
		return this.labTbls;
	}

	public void setLabTbls(Set<LabTbl> labTbls) {
		this.labTbls = labTbls;
	}
	
	public Set<LabUserTbl> getLabUserTbls() {
		return this.labUserTbls;
	}

	public void setLabUserTbls(Set<LabUserTbl> labUserTbls) {
		this.labUserTbls = labUserTbls;
	}
	
}