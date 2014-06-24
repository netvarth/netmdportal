package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the facility_tbl database table.
 * 
 */
@Entity
@Table(name="lab_facility_tbl")
public class LabFacilityTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String address;

	private String city;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date_time")
	private Date createDateTime;

	private String email;

	@Column(name="login_id")
	private String loginId;

	private String mobile;

	private String name;

	private String phone;

	private String pin;

	private String state;

	//bi-directional many-to-one association to FacilityResultTbl
	@OneToMany(mappedBy="labFacilityTbl")
	private List<FacilityResultTbl> facilityResultTbls;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="branch_id", nullable=false)
	private LabBranchTbl labBranchTbl;

	/**
	 * 
	 */
	public LabFacilityTbl() {
	}


	/**
	 * @param facilityResultTbl
	 * @return FacilityResultTbl
	 */
	public FacilityResultTbl addFacilityResultTbl(FacilityResultTbl facilityResultTbl) {
		getFacilityResultTbls().add(facilityResultTbl);
		facilityResultTbl.setLabFacilityTbl(this);

		return facilityResultTbl;
	}

	/**
	 * @param facilityResultTbl
	 * @return FacilityResultTbl
	 */
	public FacilityResultTbl removeFacilityResultTbl(FacilityResultTbl facilityResultTbl) {
		getFacilityResultTbls().remove(facilityResultTbl);
		facilityResultTbl.setLabFacilityTbl(null);

		return facilityResultTbl;
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
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
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
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}


	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}


	/**
	 * @param pin the pin to set
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}


	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * @return the facilityResultTbls
	 */
	public List<FacilityResultTbl> getFacilityResultTbls() {
		return facilityResultTbls;
	}


	/**
	 * @param facilityResultTbls the facilityResultTbls to set
	 */
	public void setFacilityResultTbls(List<FacilityResultTbl> facilityResultTbls) {
		this.facilityResultTbls = facilityResultTbls;
	}


	/**
	 * @return the labBranchTbl
	 */
	public LabBranchTbl getLabBranchTbl() {
		return labBranchTbl;
	}


	/**
	 * @param labBranchTbl the labBranchTbl to set
	 */
	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}

}