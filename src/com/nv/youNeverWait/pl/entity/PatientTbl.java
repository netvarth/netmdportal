package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the patient_tbl database table.
 * 
 */
@Entity
@Table(name="patient_tbl")
@NamedQuery(name="PatientTbl.findAll", query="SELECT p FROM PatientTbl p")
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
	//bi-directional many-to-one association to NetlimsPatientTbl
	@OneToMany(mappedBy="patientTbl")
	private List<NetlimsPatientTbl> netlimsPatientTbls;

	/**
	 * 
	 */
	public PatientTbl() {
	}

	/**
	 * @param netlimsPatientTbl
	 * @return NetlimsPatientTbl
	 */
	public NetlimsPatientTbl addNetlimsPatientTbl(NetlimsPatientTbl netlimsPatientTbl) {
		getNetlimsPatientTbls().add(netlimsPatientTbl);
		netlimsPatientTbl.setPatientTbl(this);

		return netlimsPatientTbl;
	}

	/**
	 * @param netlimsPatientTbl
	 * @return NetlimsPatientTbl
	 */
	public NetlimsPatientTbl removeNetlimsPatientTbl(NetlimsPatientTbl netlimsPatientTbl) {
		getNetlimsPatientTbls().remove(netlimsPatientTbl);
		netlimsPatientTbl.setPatientTbl(null);

		return netlimsPatientTbl;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}



	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}



	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}



	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}



	/**
	 * @return the ailment
	 */
	public String getAilment() {
		return ailment;
	}



	/**
	 * @param ailment the ailment to set
	 */
	public void setAilment(String ailment) {
		this.ailment = ailment;
	}



	/**
	 * @return the allergies
	 */
	public String getAllergies() {
		return allergies;
	}



	/**
	 * @param allergies the allergies to set
	 */
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}



	/**
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}



	/**
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}



	/**
	 * @return the chronicDisease
	 */
	public String getChronicDisease() {
		return chronicDisease;
	}



	/**
	 * @param chronicDisease the chronicDisease to set
	 */
	public void setChronicDisease(String chronicDisease) {
		this.chronicDisease = chronicDisease;
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
	 * @return the diagnosis
	 */
	public String getDiagnosis() {
		return diagnosis;
	}



	/**
	 * @param diagnosis the diagnosis to set
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @return the emergencyNo
	 */
	public String getEmergencyNo() {
		return emergencyNo;
	}



	/**
	 * @param emergencyNo the emergencyNo to set
	 */
	public void setEmergencyNo(String emergencyNo) {
		this.emergencyNo = emergencyNo;
	}



	/**
	 * @return the familyHistory
	 */
	public String getFamilyHistory() {
		return familyHistory;
	}



	/**
	 * @param familyHistory the familyHistory to set
	 */
	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}



	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}



	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}



	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}



	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}



	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}



	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}



	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}



	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}



	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}



	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}



	/**
	 * @return the netlimsPatientTbls
	 */
	public List<NetlimsPatientTbl> getNetlimsPatientTbls() {
		return netlimsPatientTbls;
	}



	/**
	 * @param netlimsPatientTbls the netlimsPatientTbls to set
	 */
	public void setNetlimsPatientTbls(List<NetlimsPatientTbl> netlimsPatientTbls) {
		this.netlimsPatientTbls = netlimsPatientTbls;
	}

}