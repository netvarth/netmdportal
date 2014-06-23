package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the netmd_login_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_login_tbl")
@NamedQuery(name="NetmdLoginTbl.findAll", query="SELECT n FROM NetmdLoginTbl n")
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

	//bi-directional many-to-one association to NetmdDoctorTbl
	@OneToMany(mappedBy="netmdLoginTbl")
	private List<NetmdDoctorTbl> netmdDoctorTbls;

	//bi-directional many-to-one association to NetmdPatientTbl
	@OneToMany(mappedBy="netmdLoginTbl")
	private List<NetmdPatientTbl> netmdPatientTbls;

	//bi-directional many-to-one association to NetmdTbl
	@OneToMany(mappedBy="netmdLoginTbl")
	private List<NetmdTbl> netmdTbls;

	//bi-directional many-to-one association to NetmdUserTbl
	@OneToMany(mappedBy="netmdLoginTbl")
	private List<NetmdUserTbl> netmdUserTbls;

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

	public List<NetmdDoctorTbl> getNetmdDoctorTbls() {
		return this.netmdDoctorTbls;
	}

	public void setNetmdDoctorTbls(List<NetmdDoctorTbl> netmdDoctorTbls) {
		this.netmdDoctorTbls = netmdDoctorTbls;
	}

	public NetmdDoctorTbl addNetmdDoctorTbl(NetmdDoctorTbl netmdDoctorTbl) {
		getNetmdDoctorTbls().add(netmdDoctorTbl);
		netmdDoctorTbl.setNetmdLoginTbl(this);

		return netmdDoctorTbl;
	}

	public NetmdDoctorTbl removeNetmdDoctorTbl(NetmdDoctorTbl netmdDoctorTbl) {
		getNetmdDoctorTbls().remove(netmdDoctorTbl);
		netmdDoctorTbl.setNetmdLoginTbl(null);

		return netmdDoctorTbl;
	}

	public List<NetmdPatientTbl> getNetmdPatientTbls() {
		return this.netmdPatientTbls;
	}

	public void setNetmdPatientTbls(List<NetmdPatientTbl> netmdPatientTbls) {
		this.netmdPatientTbls = netmdPatientTbls;
	}

	public NetmdPatientTbl addNetmdPatientTbl(NetmdPatientTbl netmdPatientTbl) {
		getNetmdPatientTbls().add(netmdPatientTbl);
		netmdPatientTbl.setNetmdLoginTbl(this);

		return netmdPatientTbl;
	}

	public NetmdPatientTbl removeNetmdPatientTbl(NetmdPatientTbl netmdPatientTbl) {
		getNetmdPatientTbls().remove(netmdPatientTbl);
		netmdPatientTbl.setNetmdLoginTbl(null);

		return netmdPatientTbl;
	}

	public List<NetmdTbl> getNetmdTbls() {
		return this.netmdTbls;
	}

	public void setNetmdTbls(List<NetmdTbl> netmdTbls) {
		this.netmdTbls = netmdTbls;
	}

	public NetmdTbl addNetmdTbl(NetmdTbl netmdTbl) {
		getNetmdTbls().add(netmdTbl);
		netmdTbl.setNetmdLoginTbl(this);

		return netmdTbl;
	}

	public NetmdTbl removeNetmdTbl(NetmdTbl netmdTbl) {
		getNetmdTbls().remove(netmdTbl);
		netmdTbl.setNetmdLoginTbl(null);

		return netmdTbl;
	}

	public List<NetmdUserTbl> getNetmdUserTbls() {
		return this.netmdUserTbls;
	}

	public void setNetmdUserTbls(List<NetmdUserTbl> netmdUserTbls) {
		this.netmdUserTbls = netmdUserTbls;
	}

	public NetmdUserTbl addNetmdUserTbl(NetmdUserTbl netmdUserTbl) {
		getNetmdUserTbls().add(netmdUserTbl);
		netmdUserTbl.setNetmdLoginTbl(this);

		return netmdUserTbl;
	}

	public NetmdUserTbl removeNetmdUserTbl(NetmdUserTbl netmdUserTbl) {
		getNetmdUserTbls().remove(netmdUserTbl);
		netmdUserTbl.setNetmdLoginTbl(null);

		return netmdUserTbl;
	}

}