package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the netmd_doctor_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_doctor_tbl")
@NamedQuery(name="NetmdDoctorTbl.findAll", query="SELECT n FROM NetmdDoctorTbl n")
public class NetmdDoctorTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(length = 145)
	private String address;

	@Column(name="consultation_interval")
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

	//bi-directional many-to-one association to DoctorAchievementTbl
	@OneToMany(mappedBy="netmdDoctorTbl")
	private List<DoctorAchievementTbl> doctorAchievementTbls;

	//bi-directional many-to-one association to DoctorEducationalQualificationTbl
	@OneToMany(mappedBy="netmdDoctorTbl")
	private List<DoctorEducationalQualificationTbl> doctorEducationalQualificationTbls;

	//bi-directional many-to-one association to DoctorExpertiseTbl
	@OneToMany(mappedBy="netmdDoctorTbl")
	private List<DoctorExpertiseTbl> doctorExpertiseTbls;

	//bi-directional many-to-one association to DoctorMembershipTbl
	@OneToMany(mappedBy="netmdDoctorTbl")
	private List<DoctorMembershipTbl> doctorMembershipTbls;

	//bi-directional many-to-one association to DoctorPracticeExperienceTbl
	@OneToMany(mappedBy="netmdDoctorTbl")
	private List<DoctorPracticeExperienceTbl> doctorPracticeExperienceTbls;

	//bi-directional many-to-one association to DoctorScheduleTbl
	@OneToMany(mappedBy="netmdDoctorTbl")
	private List<DoctorScheduleTbl> doctorScheduleTbls;

	//bi-directional many-to-one association to MedicalRecordTbl
	@OneToMany(mappedBy="netmdDoctorTbl")
	private List<MedicalRecordTbl> medicalRecordTbls;

	//bi-directional many-to-one association to NetmdLoginTbl
	@ManyToOne
	@JoinColumn(name="login_id")
	private NetmdLoginTbl netmdLoginTbl;

	//bi-directional many-to-one association to NetmdBranchTbl
	@ManyToOne
	@JoinColumn(name="netmd_branch_id")
	private NetmdBranchTbl netmdBranchTbl;

	//bi-directional many-to-one association to NetmdPassphraseTbl
	@ManyToOne
	@JoinColumn(name="netmd_passphrase_id")
	private NetmdPassphraseTbl netmdPassphraseTbl;

	//bi-directional many-to-one association to PatientAppointmentTbl
	@OneToMany(mappedBy="netmdDoctorTbl")
	private List<PatientAppointmentTbl> patientAppointmentTbls;

	public NetmdDoctorTbl() {
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

	public String getSpecialization() {
		return this.specialization;
	}

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

	public List<DoctorAchievementTbl> getDoctorAchievementTbls() {
		return this.doctorAchievementTbls;
	}

	public void setDoctorAchievementTbls(List<DoctorAchievementTbl> doctorAchievementTbls) {
		this.doctorAchievementTbls = doctorAchievementTbls;
	}

	public DoctorAchievementTbl addDoctorAchievementTbl(DoctorAchievementTbl doctorAchievementTbl) {
		getDoctorAchievementTbls().add(doctorAchievementTbl);
		doctorAchievementTbl.setNetmdDoctorTbl(this);

		return doctorAchievementTbl;
	}

	public DoctorAchievementTbl removeDoctorAchievementTbl(DoctorAchievementTbl doctorAchievementTbl) {
		getDoctorAchievementTbls().remove(doctorAchievementTbl);
		doctorAchievementTbl.setNetmdDoctorTbl(null);

		return doctorAchievementTbl;
	}

	public List<DoctorEducationalQualificationTbl> getDoctorEducationalQualificationTbls() {
		return this.doctorEducationalQualificationTbls;
	}

	public void setDoctorEducationalQualificationTbls(List<DoctorEducationalQualificationTbl> doctorEducationalQualificationTbls) {
		this.doctorEducationalQualificationTbls = doctorEducationalQualificationTbls;
	}

	public DoctorEducationalQualificationTbl addDoctorEducationalQualificationTbl(DoctorEducationalQualificationTbl doctorEducationalQualificationTbl) {
		getDoctorEducationalQualificationTbls().add(doctorEducationalQualificationTbl);
		doctorEducationalQualificationTbl.setNetmdDoctorTbl(this);

		return doctorEducationalQualificationTbl;
	}

	public DoctorEducationalQualificationTbl removeDoctorEducationalQualificationTbl(DoctorEducationalQualificationTbl doctorEducationalQualificationTbl) {
		getDoctorEducationalQualificationTbls().remove(doctorEducationalQualificationTbl);
		doctorEducationalQualificationTbl.setNetmdDoctorTbl(null);

		return doctorEducationalQualificationTbl;
	}

	public List<DoctorExpertiseTbl> getDoctorExpertiseTbls() {
		return this.doctorExpertiseTbls;
	}

	public void setDoctorExpertiseTbls(List<DoctorExpertiseTbl> doctorExpertiseTbls) {
		this.doctorExpertiseTbls = doctorExpertiseTbls;
	}

	public DoctorExpertiseTbl addDoctorExpertiseTbl(DoctorExpertiseTbl doctorExpertiseTbl) {
		getDoctorExpertiseTbls().add(doctorExpertiseTbl);
		doctorExpertiseTbl.setNetmdDoctorTbl(this);

		return doctorExpertiseTbl;
	}

	public DoctorExpertiseTbl removeDoctorExpertiseTbl(DoctorExpertiseTbl doctorExpertiseTbl) {
		getDoctorExpertiseTbls().remove(doctorExpertiseTbl);
		doctorExpertiseTbl.setNetmdDoctorTbl(null);

		return doctorExpertiseTbl;
	}

	public List<DoctorMembershipTbl> getDoctorMembershipTbls() {
		return this.doctorMembershipTbls;
	}

	public void setDoctorMembershipTbls(List<DoctorMembershipTbl> doctorMembershipTbls) {
		this.doctorMembershipTbls = doctorMembershipTbls;
	}

	public DoctorMembershipTbl addDoctorMembershipTbl(DoctorMembershipTbl doctorMembershipTbl) {
		getDoctorMembershipTbls().add(doctorMembershipTbl);
		doctorMembershipTbl.setNetmdDoctorTbl(this);

		return doctorMembershipTbl;
	}

	public DoctorMembershipTbl removeDoctorMembershipTbl(DoctorMembershipTbl doctorMembershipTbl) {
		getDoctorMembershipTbls().remove(doctorMembershipTbl);
		doctorMembershipTbl.setNetmdDoctorTbl(null);

		return doctorMembershipTbl;
	}

	public List<DoctorPracticeExperienceTbl> getDoctorPracticeExperienceTbls() {
		return this.doctorPracticeExperienceTbls;
	}

	public void setDoctorPracticeExperienceTbls(List<DoctorPracticeExperienceTbl> doctorPracticeExperienceTbls) {
		this.doctorPracticeExperienceTbls = doctorPracticeExperienceTbls;
	}

	public DoctorPracticeExperienceTbl addDoctorPracticeExperienceTbl(DoctorPracticeExperienceTbl doctorPracticeExperienceTbl) {
		getDoctorPracticeExperienceTbls().add(doctorPracticeExperienceTbl);
		doctorPracticeExperienceTbl.setNetmdDoctorTbl(this);

		return doctorPracticeExperienceTbl;
	}

	public DoctorPracticeExperienceTbl removeDoctorPracticeExperienceTbl(DoctorPracticeExperienceTbl doctorPracticeExperienceTbl) {
		getDoctorPracticeExperienceTbls().remove(doctorPracticeExperienceTbl);
		doctorPracticeExperienceTbl.setNetmdDoctorTbl(null);

		return doctorPracticeExperienceTbl;
	}

	public List<DoctorScheduleTbl> getDoctorScheduleTbls() {
		return this.doctorScheduleTbls;
	}

	public void setDoctorScheduleTbls(List<DoctorScheduleTbl> doctorScheduleTbls) {
		this.doctorScheduleTbls = doctorScheduleTbls;
	}

	public DoctorScheduleTbl addDoctorScheduleTbl(DoctorScheduleTbl doctorScheduleTbl) {
		getDoctorScheduleTbls().add(doctorScheduleTbl);
		doctorScheduleTbl.setNetmdDoctorTbl(this);

		return doctorScheduleTbl;
	}

	public DoctorScheduleTbl removeDoctorScheduleTbl(DoctorScheduleTbl doctorScheduleTbl) {
		getDoctorScheduleTbls().remove(doctorScheduleTbl);
		doctorScheduleTbl.setNetmdDoctorTbl(null);

		return doctorScheduleTbl;
	}

	public List<MedicalRecordTbl> getMedicalRecordTbls() {
		return this.medicalRecordTbls;
	}

	public void setMedicalRecordTbls(List<MedicalRecordTbl> medicalRecordTbls) {
		this.medicalRecordTbls = medicalRecordTbls;
	}

	public MedicalRecordTbl addMedicalRecordTbl(MedicalRecordTbl medicalRecordTbl) {
		getMedicalRecordTbls().add(medicalRecordTbl);
		medicalRecordTbl.setNetmdDoctorTbl(this);

		return medicalRecordTbl;
	}

	public MedicalRecordTbl removeMedicalRecordTbl(MedicalRecordTbl medicalRecordTbl) {
		getMedicalRecordTbls().remove(medicalRecordTbl);
		medicalRecordTbl.setNetmdDoctorTbl(null);

		return medicalRecordTbl;
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

	public NetmdPassphraseTbl getNetmdPassphraseTbl() {
		return this.netmdPassphraseTbl;
	}

	public void setNetmdPassphraseTbl(NetmdPassphraseTbl netmdPassphraseTbl) {
		this.netmdPassphraseTbl = netmdPassphraseTbl;
	}

	public List<PatientAppointmentTbl> getPatientAppointmentTbls() {
		return this.patientAppointmentTbls;
	}

	public void setPatientAppointmentTbls(List<PatientAppointmentTbl> patientAppointmentTbls) {
		this.patientAppointmentTbls = patientAppointmentTbls;
	}

	public PatientAppointmentTbl addPatientAppointmentTbl(PatientAppointmentTbl patientAppointmentTbl) {
		getPatientAppointmentTbls().add(patientAppointmentTbl);
		patientAppointmentTbl.setNetmdDoctorTbl(this);

		return patientAppointmentTbl;
	}

	public PatientAppointmentTbl removePatientAppointmentTbl(PatientAppointmentTbl patientAppointmentTbl) {
		getPatientAppointmentTbls().remove(patientAppointmentTbl);
		patientAppointmentTbl.setNetmdDoctorTbl(null);

		return patientAppointmentTbl;
	}

}