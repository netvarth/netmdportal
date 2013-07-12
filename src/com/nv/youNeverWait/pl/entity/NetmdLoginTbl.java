package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the netmd_login_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_login_tbl")
public class NetmdLoginTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=45)
	private String password;

	@Column(name="user_name", nullable=false, length=45)
	private String userName;

	@Column(name="user_type", nullable=false, length=45)
	private String userType;

	//bi-directional many-to-one association to DoctorTbl
	@OneToMany(mappedBy="netmdLoginTbl")
	private Set<DoctorTbl> doctorTbls;

	//bi-directional many-to-one association to NetmdTbl
	@OneToMany(mappedBy="netmdLoginTbl")
	private Set<NetmdTbl> netmdTbls;

	//bi-directional many-to-one association to NetmdUserTbl
	@OneToMany(mappedBy="netmdLoginTbl")
	private Set<NetmdUserTbl> netmdUserTbls;

	//bi-directional many-to-one association to PatientTbl
	@OneToMany(mappedBy="netmdLoginTbl")
	private Set<PatientTbl> patientTbls;

    public NetmdLoginTbl() {
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

	public Set<DoctorTbl> getDoctorTbls() {
		return this.doctorTbls;
	}

	public void setDoctorTbls(Set<DoctorTbl> doctorTbls) {
		this.doctorTbls = doctorTbls;
	}
	
	public Set<NetmdTbl> getNetmdTbls() {
		return this.netmdTbls;
	}

	public void setNetmdTbls(Set<NetmdTbl> netmdTbls) {
		this.netmdTbls = netmdTbls;
	}
	
	public Set<NetmdUserTbl> getNetmdUserTbls() {
		return this.netmdUserTbls;
	}

	public void setNetmdUserTbls(Set<NetmdUserTbl> netmdUserTbls) {
		this.netmdUserTbls = netmdUserTbls;
	}
	
	public Set<PatientTbl> getPatientTbls() {
		return this.patientTbls;
	}

	public void setPatientTbls(Set<PatientTbl> patientTbls) {
		this.patientTbls = patientTbls;
	}
	
}