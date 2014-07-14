package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the netmd_login_tbl database table.
 * 
 */
@Entity
@Table(name="login_tbl")
public class LoginTbl implements Serializable {
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
	@OneToMany(mappedBy="loginTbl")
	private List<NetmdDoctorTbl> netmdDoctorTbls;

	//bi-directional many-to-one association to NetmdPatientTbl
	@OneToMany(mappedBy="loginTbl")
	private List<NetmdPatientTbl> netmdPatientTbls;

	//bi-directional many-to-one association to NetmdTbl
	@OneToMany(mappedBy="loginTbl")
	private List<NetmdTbl> netmdTbls;

	//bi-directional many-to-one association to NetmdUserTbl
	@OneToMany(mappedBy="loginTbl")
	private List<NetmdUserTbl> netmdUserTbls;

	//bi-directional many-to-one association to LabFacilityTbl
	@OneToMany(mappedBy="loginTbl")
	private List<LabFacilityTbl> labFacilityTbls;

	//bi-directional many-to-one association to LabTbl
	@OneToMany(mappedBy="loginTbl")
	private List<LabTbl> labTbls;

	//bi-directional many-to-one association to LabUserTbl
	@OneToMany(mappedBy="loginTbl")
	private List<LabUserTbl> labUserTbls;

	/**
	 * 
	 */
	public LoginTbl() {
	}


	/**
	 * @param netmdDoctorTbl
	 * @return NetmdDoctorTbl
	 */ 
	public NetmdDoctorTbl addNetmdDoctorTbl(NetmdDoctorTbl netmdDoctorTbl) {
		getNetmdDoctorTbls().add(netmdDoctorTbl);
		netmdDoctorTbl.setLoginTbl(this);

		return netmdDoctorTbl;
	}

	/**
	 * @param netmdDoctorTbl
	 * @return NetmdDoctorTbl
	 */
	public NetmdDoctorTbl removeNetmdDoctorTbl(NetmdDoctorTbl netmdDoctorTbl) {
		getNetmdDoctorTbls().remove(netmdDoctorTbl);
		netmdDoctorTbl.setLoginTbl(null);

		return netmdDoctorTbl;
	}

	/**
	 * @return List<NetmdPatientTbl>
	 */
	public List<NetmdPatientTbl> getNetmdPatientTbls() {
		return this.netmdPatientTbls;
	}

	/**
	 * @param netmdPatientTbls
	 */
	public void setNetmdPatientTbls(List<NetmdPatientTbl> netmdPatientTbls) {
		this.netmdPatientTbls = netmdPatientTbls;
	}

	/**
	 * @param netmdPatientTbl
	 * @return NetmdPatientTbl
	 */
	public NetmdPatientTbl addNetmdPatientTbl(NetmdPatientTbl netmdPatientTbl) {
		getNetmdPatientTbls().add(netmdPatientTbl);
		netmdPatientTbl.setLoginTbl(this);

		return netmdPatientTbl;
	}

	/**
	 * @param netmdPatientTbl
	 * @return NetmdPatientTbl
	 */
	public NetmdPatientTbl removeNetmdPatientTbl(NetmdPatientTbl netmdPatientTbl) {
		getNetmdPatientTbls().remove(netmdPatientTbl);
		netmdPatientTbl.setLoginTbl(null);

		return netmdPatientTbl;
	}

	/**
	 * @return List<NetmdTbl>
	 */
	public List<NetmdTbl> getNetmdTbls() {
		return this.netmdTbls;
	}

	/**
	 * @param netmdTbls
	 */
	public void setNetmdTbls(List<NetmdTbl> netmdTbls) {
		this.netmdTbls = netmdTbls;
	}

	/**
	 * @param netmdTbl
	 * @return NetmdTbl
	 */
	public NetmdTbl addNetmdTbl(NetmdTbl netmdTbl) {
		getNetmdTbls().add(netmdTbl);
		netmdTbl.setLoginTbl(this);

		return netmdTbl;
	}

	/**
	 * @param netmdTbl
	 * @return NetmdTbl
	 */
	public NetmdTbl removeNetmdTbl(NetmdTbl netmdTbl) {
		getNetmdTbls().remove(netmdTbl);
		netmdTbl.setLoginTbl(null);

		return netmdTbl;
	}

	/**
	 * @return List<NetmdUserTbl>
	 */
	public List<NetmdUserTbl> getNetmdUserTbls() {
		return this.netmdUserTbls;
	}

	/**
	 * @param netmdUserTbls
	 */
	public void setNetmdUserTbls(List<NetmdUserTbl> netmdUserTbls) {
		this.netmdUserTbls = netmdUserTbls;
	}

	/**
	 * @param netmdUserTbl
	 * @return netmdUserTbl
	 */
	public NetmdUserTbl addNetmdUserTbl(NetmdUserTbl netmdUserTbl) {
		getNetmdUserTbls().add(netmdUserTbl);
		netmdUserTbl.setLoginTbl(this);

		return netmdUserTbl;
	}

	/**
	 * @param netmdUserTbl
	 * @return netmdUserTbl
	 */
	public NetmdUserTbl removeNetmdUserTbl(NetmdUserTbl netmdUserTbl) {
		getNetmdUserTbls().remove(netmdUserTbl);
		netmdUserTbl.setLoginTbl(null);

		return netmdUserTbl;
	}
	/**
	 * @param labFacilityTbl
	 * @return labFacilityTbl
	 */
	public LabFacilityTbl addLabFacilityTbl(LabFacilityTbl labFacilityTbl) {
		getLabFacilityTbls().add(labFacilityTbl);
		labFacilityTbl.setLoginTbl(this);

		return labFacilityTbl;
	}

	/**
	 * @param labFacilityTbl
	 * @return labFacilityTbl
	 */
	public LabFacilityTbl removeLabFacilityTbl(LabFacilityTbl labFacilityTbl) {
		getLabFacilityTbls().remove(labFacilityTbl);
		labFacilityTbl.setLoginTbl(null);

		return labFacilityTbl;
	}

	/**
	 * @return the labFacilityTbls
	 */
	public List<LabFacilityTbl> getLabFacilityTbls() {
		return labFacilityTbls;
	}

	/**
	 * @param labFacilityTbls the labFacilityTbls to set
	 */
	public void setLabFacilityTbls(List<LabFacilityTbl> labFacilityTbls) {
		this.labFacilityTbls = labFacilityTbls;
	}


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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}


	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}


	/**
	 * @return the netmdDoctorTbls
	 */
	public List<NetmdDoctorTbl> getNetmdDoctorTbls() {
		return netmdDoctorTbls;
	}


	/**
	 * @param netmdDoctorTbls the netmdDoctorTbls to set
	 */
	public void setNetmdDoctorTbls(List<NetmdDoctorTbl> netmdDoctorTbls) {
		this.netmdDoctorTbls = netmdDoctorTbls;
	}


	/**
	 * @return the labTbls
	 */
	public List<LabTbl> getLabTbls() {
		return labTbls;
	}


	/**
	 * @param labTbls the labTbls to set
	 */
	public void setLabTbls(List<LabTbl> labTbls) {
		this.labTbls = labTbls;
	}


	/**
	 * @return the labUserTbls
	 */
	public List<LabUserTbl> getLabUserTbls() {
		return labUserTbls;
	}


	/**
	 * @param labUserTbls the labUserTbls to set
	 */
	public void setLabUserTbls(List<LabUserTbl> labUserTbls) {
		this.labUserTbls = labUserTbls;
	}

}