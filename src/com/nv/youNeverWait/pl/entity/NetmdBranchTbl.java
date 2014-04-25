package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the netmd_branch_tbl database table.
 * 
 */
@Entity
public class NetmdBranchTbl extends HealthCareOrganisationTbl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date_time")
	private Date createDateTime;

	@Column(name="enable_sync")
	private boolean enableSync;

	@Column(name="sync_freq_type")
	private String syncFreqType;

	@Column(name="sync_time")
	private int syncTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date_time")
	private Date updateDateTime;

	//bi-directional many-to-one association to DoctorScheduleTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private List<DoctorScheduleTbl> doctorScheduleTbls;

	//bi-directional many-to-one association to DoctorTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private List<DoctorTbl> doctorTbls;

	//bi-directional many-to-one association to NetmdBillTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private List<NetmdBillTbl> netmdBillTbls;

	//bi-directional many-to-one association to NetmdBranchSystemInfoTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private List<NetmdBranchSystemInfoTbl> netmdBranchSystemInfoTbls;

	//bi-directional one-to-one association to HealthCareOrganisationTbl
	@OneToOne
	@JoinColumn(name="id")
	private HealthCareOrganisationTbl healthCareOrganisationTbl;

	//bi-directional many-to-one association to NetmdTbl
	@ManyToOne
	@JoinColumn(name="netmd_id")
	private NetmdTbl netmdTbl;

	//bi-directional many-to-one association to NetmdPassphraseTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private List<NetmdPassphraseTbl> netmdPassphraseTbls;

	//bi-directional many-to-one association to NetmdUserTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private List<NetmdUserTbl> netmdUserTbls;

	//bi-directional many-to-one association to OrganisationNetmdTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private List<OrganisationNetmdTbl> organisationNetmdTbls;

	//bi-directional many-to-one association to PatientAppointmentTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private List<PatientAppointmentTbl> patientAppointmentTbls;

	//bi-directional many-to-one association to PatientTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private List<PatientTbl> patientTbls;

	//bi-directional many-to-one association to ResultTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private List<ResultTbl> resultTbls;

	public NetmdBranchTbl() {
	}


	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	

	public boolean isEnableSync() {
		return enableSync;
	}


	public void setEnableSync(boolean enableSync) {
		this.enableSync = enableSync;
	}


	public String getSyncFreqType() {
		return this.syncFreqType;
	}

	public void setSyncFreqType(String syncFreqType) {
		this.syncFreqType = syncFreqType;
	}

	public int getSyncTime() {
		return this.syncTime;
	}

	public void setSyncTime(int syncTime) {
		this.syncTime = syncTime;
	}

	public Date getUpdateDateTime() {
		return this.updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public List<DoctorScheduleTbl> getDoctorScheduleTbls() {
		return this.doctorScheduleTbls;
	}

	public void setDoctorScheduleTbls(List<DoctorScheduleTbl> doctorScheduleTbls) {
		this.doctorScheduleTbls = doctorScheduleTbls;
	}

	public DoctorScheduleTbl addDoctorScheduleTbl(DoctorScheduleTbl doctorScheduleTbl) {
		getDoctorScheduleTbls().add(doctorScheduleTbl);
		doctorScheduleTbl.setNetmdBranchTbl(this);

		return doctorScheduleTbl;
	}

	public DoctorScheduleTbl removeDoctorScheduleTbl(DoctorScheduleTbl doctorScheduleTbl) {
		getDoctorScheduleTbls().remove(doctorScheduleTbl);
		doctorScheduleTbl.setNetmdBranchTbl(null);

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
		doctorTbl.setNetmdBranchTbl(this);

		return doctorTbl;
	}

	public DoctorTbl removeDoctorTbl(DoctorTbl doctorTbl) {
		getDoctorTbls().remove(doctorTbl);
		doctorTbl.setNetmdBranchTbl(null);

		return doctorTbl;
	}

	public List<NetmdBillTbl> getNetmdBillTbls() {
		return this.netmdBillTbls;
	}

	public void setNetmdBillTbls(List<NetmdBillTbl> netmdBillTbls) {
		this.netmdBillTbls = netmdBillTbls;
	}

	public NetmdBillTbl addNetmdBillTbl(NetmdBillTbl netmdBillTbl) {
		getNetmdBillTbls().add(netmdBillTbl);
		netmdBillTbl.setNetmdBranchTbl(this);

		return netmdBillTbl;
	}

	public NetmdBillTbl removeNetmdBillTbl(NetmdBillTbl netmdBillTbl) {
		getNetmdBillTbls().remove(netmdBillTbl);
		netmdBillTbl.setNetmdBranchTbl(null);

		return netmdBillTbl;
	}

	public List<NetmdBranchSystemInfoTbl> getNetmdBranchSystemInfoTbls() {
		return this.netmdBranchSystemInfoTbls;
	}

	public void setNetmdBranchSystemInfoTbls(List<NetmdBranchSystemInfoTbl> netmdBranchSystemInfoTbls) {
		this.netmdBranchSystemInfoTbls = netmdBranchSystemInfoTbls;
	}

	public NetmdBranchSystemInfoTbl addNetmdBranchSystemInfoTbl(NetmdBranchSystemInfoTbl netmdBranchSystemInfoTbl) {
		getNetmdBranchSystemInfoTbls().add(netmdBranchSystemInfoTbl);
		netmdBranchSystemInfoTbl.setNetmdBranchTbl(this);

		return netmdBranchSystemInfoTbl;
	}

	public NetmdBranchSystemInfoTbl removeNetmdBranchSystemInfoTbl(NetmdBranchSystemInfoTbl netmdBranchSystemInfoTbl) {
		getNetmdBranchSystemInfoTbls().remove(netmdBranchSystemInfoTbl);
		netmdBranchSystemInfoTbl.setNetmdBranchTbl(null);

		return netmdBranchSystemInfoTbl;
	}

	public HealthCareOrganisationTbl getHealthCareOrganisationTbl() {
		return this.healthCareOrganisationTbl;
	}

	public void setHealthCareOrganisationTbl(HealthCareOrganisationTbl healthCareOrganisationTbl) {
		this.healthCareOrganisationTbl = healthCareOrganisationTbl;
	}

	public NetmdTbl getNetmdTbl() {
		return this.netmdTbl;
	}

	public void setNetmdTbl(NetmdTbl netmdTbl) {
		this.netmdTbl = netmdTbl;
	}

	public List<NetmdPassphraseTbl> getNetmdPassphraseTbls() {
		return this.netmdPassphraseTbls;
	}

	public void setNetmdPassphraseTbls(List<NetmdPassphraseTbl> netmdPassphraseTbls) {
		this.netmdPassphraseTbls = netmdPassphraseTbls;
	}

	public NetmdPassphraseTbl addNetmdPassphraseTbl(NetmdPassphraseTbl netmdPassphraseTbl) {
		getNetmdPassphraseTbls().add(netmdPassphraseTbl);
		netmdPassphraseTbl.setNetmdBranchTbl(this);

		return netmdPassphraseTbl;
	}

	public NetmdPassphraseTbl removeNetmdPassphraseTbl(NetmdPassphraseTbl netmdPassphraseTbl) {
		getNetmdPassphraseTbls().remove(netmdPassphraseTbl);
		netmdPassphraseTbl.setNetmdBranchTbl(null);

		return netmdPassphraseTbl;
	}

	public List<NetmdUserTbl> getNetmdUserTbls() {
		return this.netmdUserTbls;
	}

	public void setNetmdUserTbls(List<NetmdUserTbl> netmdUserTbls) {
		this.netmdUserTbls = netmdUserTbls;
	}

	public NetmdUserTbl addNetmdUserTbl(NetmdUserTbl netmdUserTbl) {
		getNetmdUserTbls().add(netmdUserTbl);
		netmdUserTbl.setNetmdBranchTbl(this);

		return netmdUserTbl;
	}

	public NetmdUserTbl removeNetmdUserTbl(NetmdUserTbl netmdUserTbl) {
		getNetmdUserTbls().remove(netmdUserTbl);
		netmdUserTbl.setNetmdBranchTbl(null);

		return netmdUserTbl;
	}

	public List<OrganisationNetmdTbl> getOrganisationNetmdTbls() {
		return this.organisationNetmdTbls;
	}

	public void setOrganisationNetmdTbls(List<OrganisationNetmdTbl> organisationNetmdTbls) {
		this.organisationNetmdTbls = organisationNetmdTbls;
	}

	public OrganisationNetmdTbl addOrganisationNetmdTbl(OrganisationNetmdTbl organisationNetmdTbl) {
		getOrganisationNetmdTbls().add(organisationNetmdTbl);
		organisationNetmdTbl.setNetmdBranchTbl(this);

		return organisationNetmdTbl;
	}

	public OrganisationNetmdTbl removeOrganisationNetmdTbl(OrganisationNetmdTbl organisationNetmdTbl) {
		getOrganisationNetmdTbls().remove(organisationNetmdTbl);
		organisationNetmdTbl.setNetmdBranchTbl(null);

		return organisationNetmdTbl;
	}

	public List<PatientAppointmentTbl> getPatientAppointmentTbls() {
		return this.patientAppointmentTbls;
	}

	public void setPatientAppointmentTbls(List<PatientAppointmentTbl> patientAppointmentTbls) {
		this.patientAppointmentTbls = patientAppointmentTbls;
	}

	public PatientAppointmentTbl addPatientAppointmentTbl(PatientAppointmentTbl patientAppointmentTbl) {
		getPatientAppointmentTbls().add(patientAppointmentTbl);
		patientAppointmentTbl.setNetmdBranchTbl(this);

		return patientAppointmentTbl;
	}

	public PatientAppointmentTbl removePatientAppointmentTbl(PatientAppointmentTbl patientAppointmentTbl) {
		getPatientAppointmentTbls().remove(patientAppointmentTbl);
		patientAppointmentTbl.setNetmdBranchTbl(null);

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
		patientTbl.setNetmdBranchTbl(this);

		return patientTbl;
	}

	public PatientTbl removePatientTbl(PatientTbl patientTbl) {
		getPatientTbls().remove(patientTbl);
		patientTbl.setNetmdBranchTbl(null);

		return patientTbl;
	}

	public List<ResultTbl> getResultTbls() {
		return this.resultTbls;
	}

	public void setResultTbls(List<ResultTbl> resultTbls) {
		this.resultTbls = resultTbls;
	}

	public ResultTbl addResultTbl(ResultTbl resultTbl) {
		getResultTbls().add(resultTbl);
		resultTbl.setNetmdBranchTbl(this);

		return resultTbl;
	}

	public ResultTbl removeResultTbl(ResultTbl resultTbl) {
		getResultTbls().remove(resultTbl);
		resultTbl.setNetmdBranchTbl(null);

		return resultTbl;
	}

}