package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the netmd_patient_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_patient_tbl")
@NamedQuery(name="NetmdPatientTbl.findAll", query="SELECT n FROM NetmdPatientTbl n")
public class NetmdPatientTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String address;

	private int age;

	private String ailment;

	private String allergies;

	@Column(name="blood_group")
	private String bloodGroup;

	@Column(name="chronic_disease")
	private String chronicDisease;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date_time")
	private Date createDateTime;

	@Lob
	private String diagnosis;

	private String email;

	@Column(name="emergency_no")
	private String emergencyNo;

	@Column(name="family_history")
	private String familyHistory;

	@Column(name="first_name")
	private String firstName;

	private String gender;

	private String height;

	@Column(name="last_name")
	private String lastName;

	private String mobile;

	private String phone;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date_time")
	private Date updateDateTime;

	private String weight;

	//bi-directional many-to-one association to CaseTbl
	@OneToMany(mappedBy="netmdPatientTbl")
	private List<CaseTbl> caseTbls;

	//bi-directional many-to-one association to MedicalRecordTbl
	@OneToMany(mappedBy="netmdPatientTbl")
	private List<MedicalRecordTbl> medicalRecordTbls;

	//bi-directional many-to-one association to NetmdBillTbl
	@OneToMany(mappedBy="netmdPatientTbl")
	private List<NetmdBillTbl> netmdBillTbls;

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
	@OneToMany(mappedBy="netmdPatientTbl")
	private List<PatientAppointmentTbl> patientAppointmentTbls;

	//bi-directional many-to-one association to PatientMedicalHistoryTbl
	@OneToMany(mappedBy="netmdPatientTbl")
	private List<PatientMedicalHistoryTbl> patientMedicalHistoryTbls;

	//bi-directional many-to-one association to ResultTbl
	@OneToMany(mappedBy="netmdPatientTbl")
	private List<ResultTbl> resultTbls;

	public NetmdPatientTbl() {
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

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
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

	public Date getUpdateDateTime() {
		return this.updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public List<CaseTbl> getCaseTbls() {
		return this.caseTbls;
	}

	public void setCaseTbls(List<CaseTbl> caseTbls) {
		this.caseTbls = caseTbls;
	}

	public CaseTbl addCaseTbl(CaseTbl caseTbl) {
		getCaseTbls().add(caseTbl);
		caseTbl.setNetmdPatientTbl(this);

		return caseTbl;
	}

	public CaseTbl removeCaseTbl(CaseTbl caseTbl) {
		getCaseTbls().remove(caseTbl);
		caseTbl.setNetmdPatientTbl(null);

		return caseTbl;
	}

	public List<MedicalRecordTbl> getMedicalRecordTbls() {
		return this.medicalRecordTbls;
	}

	public void setMedicalRecordTbls(List<MedicalRecordTbl> medicalRecordTbls) {
		this.medicalRecordTbls = medicalRecordTbls;
	}

	public MedicalRecordTbl addMedicalRecordTbl(MedicalRecordTbl medicalRecordTbl) {
		getMedicalRecordTbls().add(medicalRecordTbl);
		medicalRecordTbl.setNetmdPatientTbl(this);

		return medicalRecordTbl;
	}

	public MedicalRecordTbl removeMedicalRecordTbl(MedicalRecordTbl medicalRecordTbl) {
		getMedicalRecordTbls().remove(medicalRecordTbl);
		medicalRecordTbl.setNetmdPatientTbl(null);

		return medicalRecordTbl;
	}

	public List<NetmdBillTbl> getNetmdBillTbls() {
		return this.netmdBillTbls;
	}

	public void setNetmdBillTbls(List<NetmdBillTbl> netmdBillTbls) {
		this.netmdBillTbls = netmdBillTbls;
	}

	public NetmdBillTbl addNetmdBillTbl(NetmdBillTbl netmdBillTbl) {
		getNetmdBillTbls().add(netmdBillTbl);
		netmdBillTbl.setNetmdPatientTbl(this);

		return netmdBillTbl;
	}

	public NetmdBillTbl removeNetmdBillTbl(NetmdBillTbl netmdBillTbl) {
		getNetmdBillTbls().remove(netmdBillTbl);
		netmdBillTbl.setNetmdPatientTbl(null);

		return netmdBillTbl;
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
		patientAppointmentTbl.setNetmdPatientTbl(this);

		return patientAppointmentTbl;
	}

	public PatientAppointmentTbl removePatientAppointmentTbl(PatientAppointmentTbl patientAppointmentTbl) {
		getPatientAppointmentTbls().remove(patientAppointmentTbl);
		patientAppointmentTbl.setNetmdPatientTbl(null);

		return patientAppointmentTbl;
	}

	public List<PatientMedicalHistoryTbl> getPatientMedicalHistoryTbls() {
		return this.patientMedicalHistoryTbls;
	}

	public void setPatientMedicalHistoryTbls(List<PatientMedicalHistoryTbl> patientMedicalHistoryTbls) {
		this.patientMedicalHistoryTbls = patientMedicalHistoryTbls;
	}

	public PatientMedicalHistoryTbl addPatientMedicalHistoryTbl(PatientMedicalHistoryTbl patientMedicalHistoryTbl) {
		getPatientMedicalHistoryTbls().add(patientMedicalHistoryTbl);
		patientMedicalHistoryTbl.setNetmdPatientTbl(this);

		return patientMedicalHistoryTbl;
	}

	public PatientMedicalHistoryTbl removePatientMedicalHistoryTbl(PatientMedicalHistoryTbl patientMedicalHistoryTbl) {
		getPatientMedicalHistoryTbls().remove(patientMedicalHistoryTbl);
		patientMedicalHistoryTbl.setNetmdPatientTbl(null);

		return patientMedicalHistoryTbl;
	}

	public List<ResultTbl> getResultTbls() {
		return this.resultTbls;
	}

	public void setResultTbls(List<ResultTbl> resultTbls) {
		this.resultTbls = resultTbls;
	}

	public ResultTbl addResultTbl(ResultTbl resultTbl) {
		getResultTbls().add(resultTbl);
		resultTbl.setNetmdPatientTbl(this);

		return resultTbl;
	}

	public ResultTbl removeResultTbl(ResultTbl resultTbl) {
		getResultTbls().remove(resultTbl);
		resultTbl.setNetmdPatientTbl(null);

		return resultTbl;
	}

}