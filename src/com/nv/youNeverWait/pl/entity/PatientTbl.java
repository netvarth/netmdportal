package com.nv.youNeverWait.pl.entity;



import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * The persistent class for the patient_tbl database table.
 * 
 */
@Entity
@Table(name = "patient_tbl")
public class PatientTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(length = 145)
	private String address;

	private int age;

	@Column(length = 45)
	private String ailment;

	@Column(length = 75)
	private String allergies;

	@Column(name = "blood_group", length = 45)
	private String bloodGroup;

	@Column(name = "chronic_disease", length = 75)
	private String chronicDisease;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date_time",nullable = false)
	private Date createDateTime;

	@Lob()
	private String diagnosis;

	@Column(length = 45)
	private String email;

	@Column(name = "emergency_no", length = 25)
	private String emergencyNo;

	@Column(name = "family_history", length = 150)
	private String familyHistory;

	@Column(name = "first_name", length = 45)
	private String firstName;

	@Column(length = 1)
	private String gender;

	@Column(length = 45)
	private String height;

	@Column(name = "last_name", length = 45)
	private String lastName;

	@Column(length = 15)
	private String mobile;

	@Column(length = 15)
	private String phone;

	@Column(nullable = false, length = 45)
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date_time",nullable = false)
	private Date updateDateTime;

	@Column(length = 45)
	private String weight;

	// bi-directional many-to-one association to CaseTbl
	@OneToMany(mappedBy = "patientTbl")
	private Set<CaseTbl> caseTbls;

	// bi-directional many-to-one association to MedicalRecordTbl
	@OneToMany(mappedBy = "patientTbl")
	private Set<MedicalRecordTbl> medicalRecordTbls;

	// bi-directional many-to-one association to PatientAppointmentTbl
	@OneToMany(mappedBy = "patientTbl")
	private Set<PatientAppointmentTbl> patientAppointmentTbls;

	// bi-directional many-to-one association to PatientMedicalHistoryTbl
	@OneToMany(mappedBy = "patientTbl")
	private Set<PatientMedicalHistoryTbl> patientMedicalHistoryTbls;

	// bi-directional many-to-one association to NetmdPassphraseTbl
	@ManyToOne
	@JoinColumn(name = "netmd_passphrase_id")
	private NetmdPassphraseTbl netmdPassphraseTbl;

	// bi-directional many-to-one association to NetmdLoginTbl
	@ManyToOne
	@JoinColumn(name = "login_id")
	private NetmdLoginTbl netmdLoginTbl;

	// bi-directional many-to-one association to NetmdBranchTbl
	@ManyToOne
	@JoinColumn(name = "netmd_branch_id", nullable = false)
	private NetmdBranchTbl netmdBranchTbl;

	// bi-directional many-to-one association to ResultTbl
	@OneToMany(mappedBy = "patientTbl")
	private Set<ResultTbl> resultTbls;

	public PatientTbl() {
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

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAilment() {
		return this.ailment;
	}

	public void setAilment(String ailment) {
		this.ailment = ailment;
	}

	public String getAllergies() {
		return this.allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getChronicDisease() {
		return this.chronicDisease;
	}

	public void setChronicDisease(String chronicDisease) {
		this.chronicDisease = chronicDisease;
	}

	

	public String getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmergencyNo() {
		return this.emergencyNo;
	}

	public void setEmergencyNo(String emergencyNo) {
		this.emergencyNo = emergencyNo;
	}

	public String getFamilyHistory() {
		return this.familyHistory;
	}

	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
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

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Set<CaseTbl> getCaseTbls() {
		return this.caseTbls;
	}

	public void setCaseTbls(Set<CaseTbl> caseTbls) {
		this.caseTbls = caseTbls;
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

	public Set<PatientMedicalHistoryTbl> getPatientMedicalHistoryTbls() {
		return this.patientMedicalHistoryTbls;
	}

	public void setPatientMedicalHistoryTbls(
			Set<PatientMedicalHistoryTbl> patientMedicalHistoryTbls) {
		this.patientMedicalHistoryTbls = patientMedicalHistoryTbls;
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

	public Set<ResultTbl> getResultTbls() {
		return this.resultTbls;
	}

	public void setResultTbls(Set<ResultTbl> resultTbls) {
		this.resultTbls = resultTbls;
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

}