package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the doctor_tbl database table.
 * 
 */
@Entity
@Table(name="doctor_tbl")
@NamedQuery(name="DoctorTbl.findAll", query="SELECT d FROM DoctorTbl d")
public class DoctorTbl implements Serializable {
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

	@Column(name="login_id")
	private int loginId;

	@Column(length = 15)
	private String mobile;

	@Column(length = 15)
	private String phone;

	@Column(length = 145)
	private String specialization;

	@Column(length = 45, nullable = false)
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date_time")
	private Date updateDateTime;

	@Column(name="work_history")
	private String workHistory;

	@Column(name="working_places")
	private String workingPlaces;

	//bi-directional many-to-one association to NetlimsReferralTbl
	@OneToMany(mappedBy="doctorTbl")
	private List<NetlimsReferralTbl> netlimsReferralTbls;

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

	public int getLoginId() {
		return this.loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
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

	public List<NetlimsReferralTbl> getNetlimsReferralTbls() {
		return this.netlimsReferralTbls;
	}

	public void setNetlimsReferralTbls(List<NetlimsReferralTbl> netlimsReferralTbls) {
		this.netlimsReferralTbls = netlimsReferralTbls;
	}

	public NetlimsReferralTbl addNetlimsReferralTbl(NetlimsReferralTbl netlimsReferralTbl) {
		getNetlimsReferralTbls().add(netlimsReferralTbl);
		netlimsReferralTbl.setDoctorTbl(this);

		return netlimsReferralTbl;
	}

	public NetlimsReferralTbl removeNetlimsReferralTbl(NetlimsReferralTbl netlimsReferralTbl) {
		getNetlimsReferralTbls().remove(netlimsReferralTbl);
		netlimsReferralTbl.setDoctorTbl(null);

		return netlimsReferralTbl;
	}

}