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
	private DoctorTbl doctorTbl;

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


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getEndingTime() {
		return this.endingTime;
	}

	public void setEndingTime(Date endingTime) {
		this.endingTime = endingTime;
	}

	public String getScheduleStatus() {
		return this.scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public Date getStartingTime() {
		return this.startingTime;
	}

	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}

	public NetmdPassphraseTbl getNetmdPassphraseTbl() {
		return this.netmdPassphraseTbl;
	}

	public void setNetmdPassphraseTbl(NetmdPassphraseTbl netmdPassphraseTbl) {
		this.netmdPassphraseTbl = netmdPassphraseTbl;
	}

	public DoctorTbl getDoctorTbl() {
		return this.doctorTbl;
	}

	public void setDoctorTbl(DoctorTbl doctorTbl) {
		this.doctorTbl = doctorTbl;
	}

	public SeriesTbl getSeriesTbl() {
		return this.seriesTbl;
	}

	public void setSeriesTbl(SeriesTbl seriesTbl) {
		this.seriesTbl = seriesTbl;
	}

	public NetmdBranchTbl getNetmdBranchTbl() {
		return this.netmdBranchTbl;
	}

	public void setNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		this.netmdBranchTbl = netmdBranchTbl;
	}

	public Set<PatientAppointmentTbl> getPatientAppointmentTbls() {
		return this.patientAppointmentTbls;
	}

	public void setPatientAppointmentTbls(
			Set<PatientAppointmentTbl> patientAppointmentTbls) {
		this.patientAppointmentTbls = patientAppointmentTbls;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}