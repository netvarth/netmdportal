package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

/**
 * The persistent class for the netmd_passphrase_tbl database table.
 * 
 */
@Entity
@Table(name = "netmd_passphrase_tbl")
public class NetmdPassphraseTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="mac_id", length=45)
	private String macId;

	@Column(name="pass_phrase", length=45)
	private String passPhrase;

	@Column(name="primary_device", nullable=false)
	private boolean primaryDevice;

	// bi-directional many-to-one association to DoctorScheduleTbl
	@OneToMany(mappedBy = "netmdPassphraseTbl")
	private Set<DoctorScheduleTbl> doctorScheduleTbls;

	// bi-directional many-to-one association to DoctorTbl
	@OneToMany(mappedBy = "netmdPassphraseTbl")
	private Set<DoctorTbl> doctorTbls;

	// bi-directional many-to-one association to NetmdBranchTbl
	@ManyToOne
	@JoinColumn(name = "netmd_branch_id", nullable=false)
	private NetmdBranchTbl netmdBranchTbl;

	// bi-directional many-to-one association to NetmdUserTbl
	@OneToMany(mappedBy = "netmdPassphraseTbl")
	private Set<NetmdUserTbl> netmdUserTbls;

	// bi-directional many-to-one association to PatientAppointmentTbl
	@OneToMany(mappedBy = "netmdPassphraseTbl")
	private Set<PatientAppointmentTbl> patientAppointmentTbls;

	// bi-directional many-to-one association to PatientTbl
	@OneToMany(mappedBy = "netmdPassphraseTbl")
	private Set<PatientTbl> patientTbls;

	public NetmdPassphraseTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMacId() {
		return this.macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getPassPhrase() {
		return this.passPhrase;
	}

	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}

	/**
	 * @return the primaryDevice
	 */
	public boolean isPrimaryDevice() {
		return primaryDevice;
	}

	/**
	 * @param primaryDevice the primaryDevice to set
	 */
	public void setPrimaryDevice(boolean primaryDevice) {
		this.primaryDevice = primaryDevice;
	}

	public Set<DoctorScheduleTbl> getDoctorScheduleTbls() {
		return this.doctorScheduleTbls;
	}

	public void setDoctorScheduleTbls(Set<DoctorScheduleTbl> doctorScheduleTbls) {
		this.doctorScheduleTbls = doctorScheduleTbls;
	}

	public Set<DoctorTbl> getDoctorTbls() {
		return this.doctorTbls;
	}

	public void setDoctorTbls(Set<DoctorTbl> doctorTbls) {
		this.doctorTbls = doctorTbls;
	}

	public NetmdBranchTbl getNetmdBranchTbl() {
		return this.netmdBranchTbl;
	}

	public void setNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		this.netmdBranchTbl = netmdBranchTbl;
	}

	public Set<NetmdUserTbl> getNetmdUserTbls() {
		return this.netmdUserTbls;
	}

	public void setNetmdUserTbls(Set<NetmdUserTbl> netmdUserTbls) {
		this.netmdUserTbls = netmdUserTbls;
	}

	public Set<PatientAppointmentTbl> getPatientAppointmentTbls() {
		return this.patientAppointmentTbls;
	}

	public void setPatientAppointmentTbls(
			Set<PatientAppointmentTbl> patientAppointmentTbls) {
		this.patientAppointmentTbls = patientAppointmentTbls;
	}

	public Set<PatientTbl> getPatientTbls() {
		return this.patientTbls;
	}

	public void setPatientTbls(Set<PatientTbl> patientTbls) {
		this.patientTbls = patientTbls;
	}

}