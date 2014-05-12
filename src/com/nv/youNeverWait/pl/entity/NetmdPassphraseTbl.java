package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the netmd_passphrase_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_passphrase_tbl")
public class NetmdPassphraseTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="mac_id")
	private String macId;

	@Column(name="pass_phrase")
	private String passPhrase;

	@Column(name="primary_device", nullable=false)
	private boolean primaryDevice;

	//bi-directional many-to-one association to DoctorScheduleTbl
	@OneToMany(mappedBy="netmdPassphraseTbl")
	private List<DoctorScheduleTbl> doctorScheduleTbls;

	//bi-directional many-to-one association to DoctorTbl
	@OneToMany(mappedBy="netmdPassphraseTbl")
	private List<DoctorTbl> doctorTbls;

	//bi-directional many-to-one association to NetmdBranchSystemInfoTbl
	@OneToMany(mappedBy="netmdPassphraseTbl")
	private List<NetmdBranchSystemInfoTbl> netmdBranchSystemInfoTbls;

	//bi-directional many-to-one association to NetmdHealthMonitorTbl
	@OneToMany(mappedBy="netmdPassphraseTbl")
	private List<NetmdHealthMonitorTbl> netmdHealthMonitorTbls;

	//bi-directional many-to-one association to NetmdBranchTbl
	@ManyToOne
	@JoinColumn(name="netmd_branch_id")
	private NetmdBranchTbl netmdBranchTbl;

	//bi-directional many-to-one association to NetmdUserTbl
	@OneToMany(mappedBy="netmdPassphraseTbl")
	private List<NetmdUserTbl> netmdUserTbls;

	//bi-directional many-to-one association to PatientAppointmentTbl
	@OneToMany(mappedBy="netmdPassphraseTbl")
	private List<PatientAppointmentTbl> patientAppointmentTbls;

	//bi-directional many-to-one association to PatientTbl
	@OneToMany(mappedBy="netmdPassphraseTbl")
	private List<PatientTbl> patientTbls;

	//bi-directional many-to-one association to SeriesTbl
	@OneToMany(mappedBy="netmdPassphraseTbl")
	private List<SeriesTbl> seriesTbls;

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

	public List<DoctorScheduleTbl> getDoctorScheduleTbls() {
		return this.doctorScheduleTbls;
	}

	public void setDoctorScheduleTbls(List<DoctorScheduleTbl> doctorScheduleTbls) {
		this.doctorScheduleTbls = doctorScheduleTbls;
	}

	public DoctorScheduleTbl addDoctorScheduleTbl(DoctorScheduleTbl doctorScheduleTbl) {
		getDoctorScheduleTbls().add(doctorScheduleTbl);
		doctorScheduleTbl.setNetmdPassphraseTbl(this);

		return doctorScheduleTbl;
	}

	public DoctorScheduleTbl removeDoctorScheduleTbl(DoctorScheduleTbl doctorScheduleTbl) {
		getDoctorScheduleTbls().remove(doctorScheduleTbl);
		doctorScheduleTbl.setNetmdPassphraseTbl(null);

		return doctorScheduleTbl;
	}

	public List<DoctorTbl> getDoctorTbls() {
		return this.doctorTbls;
	}

	public void setDoctorTbls(List<DoctorTbl> doctorTbls) {
		this.doctorTbls = doctorTbls;
	}

	public DoctorTbl addDoctorTbl(DoctorTbl doctorTbl) {
		getDoctorTbls().add(doctorTbl);
		doctorTbl.setNetmdPassphraseTbl(this);

		return doctorTbl;
	}

	public DoctorTbl removeDoctorTbl(DoctorTbl doctorTbl) {
		getDoctorTbls().remove(doctorTbl);
		doctorTbl.setNetmdPassphraseTbl(null);

		return doctorTbl;
	}

	public List<NetmdBranchSystemInfoTbl> getNetmdBranchSystemInfoTbls() {
		return this.netmdBranchSystemInfoTbls;
	}

	public void setNetmdBranchSystemInfoTbls(List<NetmdBranchSystemInfoTbl> netmdBranchSystemInfoTbls) {
		this.netmdBranchSystemInfoTbls = netmdBranchSystemInfoTbls;
	}

	public NetmdBranchSystemInfoTbl addNetmdBranchSystemInfoTbl(NetmdBranchSystemInfoTbl netmdBranchSystemInfoTbl) {
		getNetmdBranchSystemInfoTbls().add(netmdBranchSystemInfoTbl);
		netmdBranchSystemInfoTbl.setNetmdPassphraseTbl(this);

		return netmdBranchSystemInfoTbl;
	}

	public NetmdBranchSystemInfoTbl removeNetmdBranchSystemInfoTbl(NetmdBranchSystemInfoTbl netmdBranchSystemInfoTbl) {
		getNetmdBranchSystemInfoTbls().remove(netmdBranchSystemInfoTbl);
		netmdBranchSystemInfoTbl.setNetmdPassphraseTbl(null);

		return netmdBranchSystemInfoTbl;
	}

	public List<NetmdHealthMonitorTbl> getNetmdHealthMonitorTbls() {
		return this.netmdHealthMonitorTbls;
	}

	public void setNetmdHealthMonitorTbls(List<NetmdHealthMonitorTbl> netmdHealthMonitorTbls) {
		this.netmdHealthMonitorTbls = netmdHealthMonitorTbls;
	}

	public NetmdHealthMonitorTbl addNetmdHealthMonitorTbl(NetmdHealthMonitorTbl netmdHealthMonitorTbl) {
		getNetmdHealthMonitorTbls().add(netmdHealthMonitorTbl);
		netmdHealthMonitorTbl.setNetmdPassphraseTbl(this);

		return netmdHealthMonitorTbl;
	}

	public NetmdHealthMonitorTbl removeNetmdHealthMonitorTbl(NetmdHealthMonitorTbl netmdHealthMonitorTbl) {
		getNetmdHealthMonitorTbls().remove(netmdHealthMonitorTbl);
		netmdHealthMonitorTbl.setNetmdPassphraseTbl(null);

		return netmdHealthMonitorTbl;
	}

	public NetmdBranchTbl getNetmdBranchTbl() {
		return this.netmdBranchTbl;
	}

	public void setNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		this.netmdBranchTbl = netmdBranchTbl;
	}

	public List<NetmdUserTbl> getNetmdUserTbls() {
		return this.netmdUserTbls;
	}

	public void setNetmdUserTbls(List<NetmdUserTbl> netmdUserTbls) {
		this.netmdUserTbls = netmdUserTbls;
	}

	public NetmdUserTbl addNetmdUserTbl(NetmdUserTbl netmdUserTbl) {
		getNetmdUserTbls().add(netmdUserTbl);
		netmdUserTbl.setNetmdPassphraseTbl(this);

		return netmdUserTbl;
	}

	public NetmdUserTbl removeNetmdUserTbl(NetmdUserTbl netmdUserTbl) {
		getNetmdUserTbls().remove(netmdUserTbl);
		netmdUserTbl.setNetmdPassphraseTbl(null);

		return netmdUserTbl;
	}

	public List<PatientAppointmentTbl> getPatientAppointmentTbls() {
		return this.patientAppointmentTbls;
	}

	public void setPatientAppointmentTbls(List<PatientAppointmentTbl> patientAppointmentTbls) {
		this.patientAppointmentTbls = patientAppointmentTbls;
	}

	public PatientAppointmentTbl addPatientAppointmentTbl(PatientAppointmentTbl patientAppointmentTbl) {
		getPatientAppointmentTbls().add(patientAppointmentTbl);
		patientAppointmentTbl.setNetmdPassphraseTbl(this);

		return patientAppointmentTbl;
	}

	public PatientAppointmentTbl removePatientAppointmentTbl(PatientAppointmentTbl patientAppointmentTbl) {
		getPatientAppointmentTbls().remove(patientAppointmentTbl);
		patientAppointmentTbl.setNetmdPassphraseTbl(null);

		return patientAppointmentTbl;
	}

	public List<PatientTbl> getPatientTbls() {
		return this.patientTbls;
	}

	public void setPatientTbls(List<PatientTbl> patientTbls) {
		this.patientTbls = patientTbls;
	}

	public PatientTbl addPatientTbl(PatientTbl patientTbl) {
		getPatientTbls().add(patientTbl);
		patientTbl.setNetmdPassphraseTbl(this);

		return patientTbl;
	}

	public PatientTbl removePatientTbl(PatientTbl patientTbl) {
		getPatientTbls().remove(patientTbl);
		patientTbl.setNetmdPassphraseTbl(null);

		return patientTbl;
	}

	public List<SeriesTbl> getSeriesTbls() {
		return this.seriesTbls;
	}

	public void setSeriesTbls(List<SeriesTbl> seriesTbls) {
		this.seriesTbls = seriesTbls;
	}

	public SeriesTbl addSeriesTbl(SeriesTbl seriesTbl) {
		getSeriesTbls().add(seriesTbl);
		seriesTbl.setNetmdPassphraseTbl(this);

		return seriesTbl;
	}

	public SeriesTbl removeSeriesTbl(SeriesTbl seriesTbl) {
		getSeriesTbls().remove(seriesTbl);
		seriesTbl.setNetmdPassphraseTbl(null);

		return seriesTbl;
	}

}