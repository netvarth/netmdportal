package com.nv.youNeverWait.pl.entity;




import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * The persistent class for the doctor_schedule_tbl database table.
 * 
 */
@Entity
@Table(name = "doctor_schedule_tbl")
public class DoctorScheduleTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(length = 45, nullable = false)
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date date;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ending_time", nullable = false)
	private Date endingTime;

	@Column(name = "schedule_status", nullable = false, length = 45)
	private String scheduleStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "starting_time", nullable = false)
	private Date startingTime;

	// bi-directional many-to-one association to NetmdPassphraseTbl
	@ManyToOne
	@JoinColumn(name = "netmd_passphrase_id")
	private NetmdPassphraseTbl netmdPassphraseTbl;

	// bi-directional many-to-one association to DoctorTbl
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private NetmdDoctorTbl netmdDoctorTbl;

	// bi-directional many-to-one association to SeriesTbl
	@ManyToOne
	@JoinColumn(name = "series_id", nullable = false)
	private SeriesTbl seriesTbl;

	// bi-directional many-to-one association to NetmdBranchTbl
	@ManyToOne
	@JoinColumn(name = "netmd_branch_id")
	private NetmdBranchTbl netmdBranchTbl;

	// bi-directional many-to-one association to PatientAppointmentTbl
	@OneToMany(mappedBy = "doctorScheduleTbl")
	private Set<PatientAppointmentTbl> patientAppointmentTbls;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date_time",nullable = false)
	private Date createDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date_time",nullable = false)
	private Date updateDateTime;

	/**
	 * 
	 */
	public DoctorScheduleTbl() {
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
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the endingTime
	 */
	public Date getEndingTime() {
		return endingTime;
	}

	/**
	 * @param endingTime the endingTime to set
	 */
	public void setEndingTime(Date endingTime) {
		this.endingTime = endingTime;
	}

	/**
	 * @return the scheduleStatus
	 */
	public String getScheduleStatus() {
		return scheduleStatus;
	}

	/**
	 * @param scheduleStatus the scheduleStatus to set
	 */
	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
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
	 * @return the seriesTbl
	 */
	public SeriesTbl getSeriesTbl() {
		return seriesTbl;
	}

	/**
	 * @param seriesTbl the seriesTbl to set
	 */
	public void setSeriesTbl(SeriesTbl seriesTbl) {
		this.seriesTbl = seriesTbl;
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
	 * @return the patientAppointmentTbls
	 */
	public Set<PatientAppointmentTbl> getPatientAppointmentTbls() {
		return patientAppointmentTbls;
	}

	/**
	 * @param patientAppointmentTbls the patientAppointmentTbls to set
	 */
	public void setPatientAppointmentTbls(
			Set<PatientAppointmentTbl> patientAppointmentTbls) {
		this.patientAppointmentTbls = patientAppointmentTbls;
	}



}