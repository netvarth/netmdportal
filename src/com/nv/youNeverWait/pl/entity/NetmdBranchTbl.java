package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the netmd_branch_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_branch_tbl")
public class NetmdBranchTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=145)
	private String address;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_date_time")
	private Date createDateTime;

	@Column(length=45)
	private String email;

	@Column(length=45)
	private String mobile;

	@Column(length=45)
	private String name;

	@Column(length=45)
	private String phone;

	@Column(nullable=false, length=45)
	private String status;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_date_time")
	private Date updateDateTime;

	//bi-directional many-to-one association to DoctorScheduleTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private Set<DoctorScheduleTbl> doctorScheduleTbls;

	//bi-directional many-to-one association to DoctorTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private Set<DoctorTbl> doctorTbls;

	//bi-directional many-to-one association to NetmdTbl
    @ManyToOne
	@JoinColumn(name="netmd_id", nullable=false)
	private NetmdTbl netmdTbl;

	//bi-directional many-to-one association to NetmdPassphraseTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private Set<NetmdPassphraseTbl> netmdPassphraseTbls;

	//bi-directional many-to-one association to NetmdUserTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private Set<NetmdUserTbl> netmdUserTbls;

	//bi-directional many-to-one association to PatientAppointmentTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private Set<PatientAppointmentTbl> patientAppointmentTbls;

	//bi-directional many-to-one association to PatientTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private Set<PatientTbl> patientTbls;

	//bi-directional many-to-one association to ResultTbl
	@OneToMany(mappedBy="netmdBranchTbl")
	private Set<ResultTbl> resultTbls;

	@Column(name="enable_sync", nullable=false)
	private boolean enableSync;
	
	@Column(name="sync_freq_type", length=45)
	private String syncFreqType;

	@Column(name="sync_time")
	private int syncTime;
	
	//bi-directional many-to-one association to OrganisationNetmdTbl
		@OneToMany(mappedBy="netmdBranchTbl")
		private Set<OrganisationNetmdTbl> organisationNetmdTbls;
		
    public NetmdBranchTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
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

	public Date getUpdateDateTime() {
		return this.updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
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
	
	public NetmdTbl getNetmdTbl() {
		return this.netmdTbl;
	}

	public void setNetmdTbl(NetmdTbl netmdTbl) {
		this.netmdTbl = netmdTbl;
	}
	
	public Set<NetmdPassphraseTbl> getNetmdPassphraseTbls() {
		return this.netmdPassphraseTbls;
	}

	public void setNetmdPassphraseTbls(Set<NetmdPassphraseTbl> netmdPassphraseTbls) {
		this.netmdPassphraseTbls = netmdPassphraseTbls;
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

	public void setPatientAppointmentTbls(Set<PatientAppointmentTbl> patientAppointmentTbls) {
		this.patientAppointmentTbls = patientAppointmentTbls;
	}
	
	public Set<PatientTbl> getPatientTbls() {
		return this.patientTbls;
	}

	public void setPatientTbls(Set<PatientTbl> patientTbls) {
		this.patientTbls = patientTbls;
	}
	
	public Set<ResultTbl> getResultTbls() {
		return this.resultTbls;
	}

	public void setResultTbls(Set<ResultTbl> resultTbls) {
		this.resultTbls = resultTbls;
	}
	public boolean getEnableSync() {
		return this.enableSync;
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
	public Set<OrganisationNetmdTbl> getOrganisationNetmdTbls() {
		return this.organisationNetmdTbls;
	}

	public void setOrganisationNetmdTbls(Set<OrganisationNetmdTbl> organisationNetmdTbls) {
		this.organisationNetmdTbls = organisationNetmdTbls;
	}
}