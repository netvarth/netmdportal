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
	 @Column(name="appointment_date",nullable=false)
	private Date appointmentDate;

	@Temporal(TemporalType.TIMESTAMP)
	 @Column(name="appointment_time",nullable=false)
	private Date appointmentTime;
	
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
	private NetmdPatientTbl netmdPatientTbl;

	// bi-directional many-to-one association to DoctorTbl
	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable=false)
	private NetmdDoctorTbl netmdDoctorTbl;

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
	
	/**
	 * 
	 */
	public PatientAppointmentTbl() {
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

	
	/**
	 * @return the appointmentTime
	 */
	public Date getAppointmentTime() {
		return appointmentTime;
	}

	/**
	 * @param appointmentTime the appointmentTime to set
	 */
	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	/**
	 * @return the appointmentDate
	 */
	public Date getAppointmentDate() {
		return appointmentDate;
	}

	/**
	 * @param appointmentDate the appointmentDate to set
	 */
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	/**
	 * @return the startingTime
	 */
	public Date getStartingTime() {
		return startingTime;
	}

	/**
	 * @param startingTime the startingTime to set
	 */
	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the netmdPassphraseTbl
	 */
	public NetmdPassphraseTbl getNetmdPassphraseTbl() {
		return netmdPassphraseTbl;
	}

	/**
	 * @param netmdPassphraseTbl the netmdPassphraseTbl to set
	 */
	public void setNetmdPassphraseTbl(NetmdPassphraseTbl netmdPassphraseTbl) {
		this.netmdPassphraseTbl = netmdPassphraseTbl;
	}

	/**
	 * @return the netmdPatientTbl
	 */
	public NetmdPatientTbl getNetmdPatientTbl() {
		return netmdPatientTbl;
	}

	/**
	 * @param netmdPatientTbl the netmdPatientTbl to set
	 */
	public void setNetmdPatientTbl(NetmdPatientTbl netmdPatientTbl) {
		this.netmdPatientTbl = netmdPatientTbl;
	}

	/**
	 * @return the netmdDoctorTbl
	 */
	public NetmdDoctorTbl getNetmdDoctorTbl() {
		return netmdDoctorTbl;
	}

	/**
	 * @param netmdDoctorTbl the netmdDoctorTbl to set
	 */
	public void setNetmdDoctorTbl(NetmdDoctorTbl netmdDoctorTbl) {
		this.netmdDoctorTbl = netmdDoctorTbl;
	}

	/**
	 * @return the doctorScheduleTbl
	 */
	public DoctorScheduleTbl getDoctorScheduleTbl() {
		return doctorScheduleTbl;
	}

	/**
	 * @param doctorScheduleTbl the doctorScheduleTbl to set
	 */
	public void setDoctorScheduleTbl(DoctorScheduleTbl doctorScheduleTbl) {
		this.doctorScheduleTbl = doctorScheduleTbl;
	}

	/**
	 * @return the netmdBranchTbl
	 */
	public NetmdBranchTbl getNetmdBranchTbl() {
		return netmdBranchTbl;
	}

	/**
	 * @param netmdBranchTbl the netmdBranchTbl to set
	 */
	public void setNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		this.netmdBranchTbl = netmdBranchTbl;
	}

	/**
	 * @return the appointmentStatus
	 */
	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	/**
	 * @param appointmentStatus the appointmentStatus to set
	 */
	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @return the updateDateTime
	 */
	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	/**
	 * @param updateDateTime the updateDateTime to set
	 */
	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}



}