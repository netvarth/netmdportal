package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * The persistent class for the doctor_tbl database table.
 * 
 */
@Entity
@Table(name = "doctor_tbl")
public class DoctorTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int id;
	
	@Column(length = 145)
	private String address;

	@Column(name = "consultation_interval")
	private int consultationInterval;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date_time", nullable = false)
	private Date createDateTime;

	@Column(name = "date_of_birth", length = 45)
	private String dateOfBirth;

	@Column(length = 145)
	private String designation;

	@Column(nullable = false, length = 45)
	private String email;

	@Column(name = "first_name", length = 45)
	private String firstName;

	@Column(length = 45)
	private String gender;

	@Column(name = "last_name", length = 45)
	private String lastName;

	@Column(length = 15)
	private String mobile;

	@Column(length = 15)
	private String phone;

	@Column(length = 145)
	private String specialization;

	@Column(length = 45, nullable = false)
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date_time", nullable = false)
	private Date updateDateTime;

	@Column(name = "work_history", length = 145)
	private String workHistory;

	@Column(name = "working_places", length = 145)
	private String workingPlaces;

	// bi-directional many-to-one association to DoctorAchievementTbl
	@OneToMany(mappedBy = "doctorTbl")
	private Set<DoctorAchievementTbl> doctorAchievementTbls;

	// bi-directional many-to-one association to
	// DoctorEducationalQualificationTbl
	@OneToMany(mappedBy = "doctorTbl")
	private Set<DoctorEducationalQualificationTbl> doctorEducationalQualificationTbls;

	// bi-directional many-to-one association to DoctorExpertiseTbl
	@OneToMany(mappedBy = "doctorTbl")
	private Set<DoctorExpertiseTbl> doctorExpertiseTbls;

	// bi-directional many-to-one association to DoctorMembershipTbl
	@OneToMany(mappedBy = "doctorTbl")
	private Set<DoctorMembershipTbl> doctorMembershipTbls;

	// bi-directional many-to-one association to DoctorPracticeExperienceTbl
	@OneToMany(mappedBy = "doctorTbl")
	private Set<DoctorPracticeExperienceTbl> doctorPracticeExperienceTbls;

	// bi-directional many-to-one association to DoctorScheduleTbl
	@OneToMany(mappedBy = "doctorTbl")
	private Set<DoctorScheduleTbl> doctorScheduleTbls;

	// bi-directional many-to-one association to NetmdPassphraseTbl
	@ManyToOne
	@JoinColumn(name = "netmd_passphrase_id")
	private NetmdPassphraseTbl netmdPassphraseTbl;

	// bi-directional many-to-one association to NetmdLoginTbl
	@ManyToOne
	@JoinColumn(name = "login_id", nullable = false)
	private NetmdLoginTbl netmdLoginTbl;

	// bi-directional many-to-one association to NetmdBranchTbl
	@ManyToOne
	@JoinColumn(name = "netmd_branch_id", nullable = false)
	private NetmdBranchTbl netmdBranchTbl;

	// bi-directional many-to-one association to MedicalRecordTbl
	@OneToMany(mappedBy = "doctorTbl")
	private Set<MedicalRecordTbl> medicalRecordTbls;

	// bi-directional many-to-one association to PatientAppointmentTbl
	@OneToMany(mappedBy = "doctorTbl")
	private Set<PatientAppointmentTbl> patientAppointmentTbls;

	public DoctorTbl() {
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

	public int getConsultationInterval() {
		return this.consultationInterval;
	}

	public void setConsultationInterval(int consultationInterval) {
		this.consultationInterval = consultationInterval;
	}

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	

	/**
	 * @return the specialization
	 */
	public String getSpecialization() {
		return specialization;
	}

	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
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

	public String getWorkHistory() {
		return this.workHistory;
	}

	public void setWorkHistory(String workHistory) {
		this.workHistory = workHistory;
	}

	public String getWorkingPlaces() {
		return this.workingPlaces;
	}

	public void setWorkingPlaces(String workingPlaces) {
		this.workingPlaces = workingPlaces;
	}

	public Set<DoctorAchievementTbl> getDoctorAchievementTbls() {
		return this.doctorAchievementTbls;
	}

	public void setDoctorAchievementTbls(
			Set<DoctorAchievementTbl> doctorAchievementTbls) {
		this.doctorAchievementTbls = doctorAchievementTbls;
	}

	public Set<DoctorEducationalQualificationTbl> getDoctorEducationalQualificationTbls() {
		return this.doctorEducationalQualificationTbls;
	}

	public void setDoctorEducationalQualificationTbls(
			Set<DoctorEducationalQualificationTbl> doctorEducationalQualificationTbls) {
		this.doctorEducationalQualificationTbls = doctorEducationalQualificationTbls;
	}

	public Set<DoctorExpertiseTbl> getDoctorExpertiseTbls() {
		return this.doctorExpertiseTbls;
	}

	public void setDoctorExpertiseTbls(
			Set<DoctorExpertiseTbl> doctorExpertiseTbls) {
		this.doctorExpertiseTbls = doctorExpertiseTbls;
	}

	public Set<DoctorMembershipTbl> getDoctorMembershipTbls() {
		return this.doctorMembershipTbls;
	}

	public void setDoctorMembershipTbls(
			Set<DoctorMembershipTbl> doctorMembershipTbls) {
		this.doctorMembershipTbls = doctorMembershipTbls;
	}

	public Set<DoctorPracticeExperienceTbl> getDoctorPracticeExperienceTbls() {
		return this.doctorPracticeExperienceTbls;
	}

	public void setDoctorPracticeExperienceTbls(
			Set<DoctorPracticeExperienceTbl> doctorPracticeExperienceTbls) {
		this.doctorPracticeExperienceTbls = doctorPracticeExperienceTbls;
	}

	public Set<DoctorScheduleTbl> getDoctorScheduleTbls() {
		return this.doctorScheduleTbls;
	}

	public void setDoctorScheduleTbls(Set<DoctorScheduleTbl> doctorScheduleTbls) {
		this.doctorScheduleTbls = doctorScheduleTbls;
	}

	public NetmdPassphraseTbl getNetmdPassphraseTbl() {
		return this.netmdPassphraseTbl;
	}

	public void setNetmdPassphraseTbl(NetmdPassphraseTbl netmdPassphraseTbl) {
		this.netmdPassphraseTbl = netmdPassphraseTbl;
	}

	public NetmdLoginTbl getNetmdLoginTbl() {
		return this.netmdLoginTbl;
	}

	public void setNetmdLoginTbl(NetmdLoginTbl netmdLoginTbl) {
		this.netmdLoginTbl = netmdLoginTbl;
	}

	public NetmdBranchTbl getNetmdBranchTbl() {
		return this.netmdBranchTbl;
	}

	public void setNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		this.netmdBranchTbl = netmdBranchTbl;
	}

	public Set<MedicalRecordTbl> getMedicalRecordTbls() {
		return this.medicalRecordTbls;
	}

	public void setMedicalRecordTbls(Set<MedicalRecordTbl> medicalRecordTbls) {
		this.medicalRecordTbls = medicalRecordTbls;
	}

	public Set<PatientAppointmentTbl> getPatientAppointmentTbls() {
		return this.patientAppointmentTbls;
	}

	public void setPatientAppointmentTbls(
			Set<PatientAppointmentTbl> patientAppointmentTbls) {
		this.patientAppointmentTbls = patientAppointmentTbls;
	}

}