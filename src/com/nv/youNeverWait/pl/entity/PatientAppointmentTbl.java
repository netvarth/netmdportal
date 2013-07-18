package com.nv.youNeverWait.pl.entity;





import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the patient_appointment_tbl database table.
 * 
 */
@Entity
@Table(name = "patient_appointment_tbl")
public class PatientAppointmentTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="appointment_status", nullable=false, length=45)
	private String appointmentStatus;

	@Temporal(TemporalType.TIMESTAMP)
	 @Column(nullable=false)
	private Date date;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="starting_time", nullable=false)
	private Date startingTime;

	@Column(nullable=false, length=45)
	private String status;

	// bi-directional many-to-one association to NetmdPassphraseTbl
	@ManyToOne
	@JoinColumn(name = "netmd_passphrase_id")
	private NetmdPassphraseTbl netmdPassphraseTbl;

	// bi-directional many-to-one association to PatientTbl
	@ManyToOne
	@JoinColumn(name = "patient_id", nullable=false)
	private PatientTbl patientTbl;

	// bi-directional many-to-one association to DoctorTbl
	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable=false)
	private DoctorTbl doctorTbl;

	// bi-directional many-to-one association to DoctorScheduleTbl
	@ManyToOne
	@JoinColumn(name = "schedule_id", nullable=false)
	private DoctorScheduleTbl doctorScheduleTbl;

	// bi-directional many-to-one association to NetmdBranchTbl
	@ManyToOne
	@JoinColumn(name = "netmd_branch_id")
	private NetmdBranchTbl netmdBranchTbl;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_date_time",nullable = false)
	private Date createDateTime;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_date_time",nullable = false)
	private Date updateDateTime;
	
	@Column(name="appointmnet_level",nullable=false)
	private boolean appointmnetLevel;
	
	public PatientAppointmentTbl() {
	}

	public int getId() {
		return this.id;
	}

	

	/**
	 * @return the appointmnetLevel
	 */
	public boolean isAppointmnetLevel() {
		return appointmnetLevel;
	}

	/**
	 * @param appointmnetLevel the appointmnetLevel to set
	 */
	public void setAppointmnetLevel(boolean appointmnetLevel) {
		this.appointmnetLevel = appointmnetLevel;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppointmentStatus() {
		return this.appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getUpdateDateTime() {
		return this.updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getStartingTime() {
		return this.startingTime;
	}

	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public NetmdPassphraseTbl getNetmdPassphraseTbl() {
		return this.netmdPassphraseTbl;
	}

	public void setNetmdPassphraseTbl(NetmdPassphraseTbl netmdPassphraseTbl) {
		this.netmdPassphraseTbl = netmdPassphraseTbl;
	}

	public PatientTbl getPatientTbl() {
		return this.patientTbl;
	}

	public void setPatientTbl(PatientTbl patientTbl) {
		this.patientTbl = patientTbl;
	}

	public DoctorTbl getDoctorTbl() {
		return this.doctorTbl;
	}

	public void setDoctorTbl(DoctorTbl doctorTbl) {
		this.doctorTbl = doctorTbl;
	}

	public DoctorScheduleTbl getDoctorScheduleTbl() {
		return this.doctorScheduleTbl;
	}

	public void setDoctorScheduleTbl(DoctorScheduleTbl doctorScheduleTbl) {
		this.doctorScheduleTbl = doctorScheduleTbl;
	}

	public NetmdBranchTbl getNetmdBranchTbl() {
		return this.netmdBranchTbl;
	}

	public void setNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		this.netmdBranchTbl = netmdBranchTbl;
	}

}