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
@Table(name="facility_tbl")
@NamedQuery(name="FacilityTbl.findAll", query="SELECT f FROM FacilityTbl f")
public class FacilityTbl implements Serializable {
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
	@OneToMany(mappedBy="facilityTbl")
	private List<FacilityResultTbl> facilityResultTbls;

	public FacilityTbl() {
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

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<FacilityResultTbl> getFacilityResultTbls() {
		return this.facilityResultTbls;
	}

	public void setFacilityResultTbls(List<FacilityResultTbl> facilityResultTbls) {
		this.facilityResultTbls = facilityResultTbls;
	}

	public FacilityResultTbl addFacilityResultTbl(FacilityResultTbl facilityResultTbl) {
		getFacilityResultTbls().add(facilityResultTbl);
		facilityResultTbl.setFacilityTbl(this);

		return facilityResultTbl;
	}

	public FacilityResultTbl removeFacilityResultTbl(FacilityResultTbl facilityResultTbl) {
		getFacilityResultTbls().remove(facilityResultTbl);
		facilityResultTbl.setFacilityTbl(null);

		return facilityResultTbl;
	}

}