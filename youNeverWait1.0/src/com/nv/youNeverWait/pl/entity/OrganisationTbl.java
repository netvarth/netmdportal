package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the organisation_tbl database table.
 * 
 */
@Entity
@Table(name="organisation_tbl")
public class OrganisationTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date_time")
	private Date createDateTime;

	@Column(name="department_type")
	private String departmentType;

	@Column(name="enable_sync")
	private boolean enableSync;

	@Column(name="head_office_address")
	private String headOfficeAddress;

	@Column(name="head_office_email")
	private String headOfficeEmail;

	@Column(name="head_office_mobile")
	private String headOfficeMobile;

	@Column(name="head_office_name")
	private String headOfficeName;

	@Column(name="head_office_phone")
	private String headOfficePhone;

	@Column(name="name")
	private String name;

	@Column(name="owner_address")
	private String ownerAddress;

	@Column(name="owner_email")
	private String ownerEmail;

	@Column(name="owner_first_name")
	private String ownerFirstName;

	@Column(name="owner_last_name")
	private String ownerLastName;

	@Column(name="owner_mobile")
	private String ownerMobile;

	@Column(name="owner_phone")
	private String ownerPhone;

	private String status;

	@Column(name="sync_freq_type")
	private String syncFreqType;

	@Column(name="sync_time")
	private int syncTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date_time")
	private Date updateDateTime;

	//bi-directional many-to-one association to OrganisationLoginTbl
	@ManyToOne
	@JoinColumn(name="login_id")
	private OrganisationLoginTbl organisationLoginTbl;

	public OrganisationTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getDepartmentType() {
		return this.departmentType;
	}

	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

	

	public String getHeadOfficeAddress() {
		return this.headOfficeAddress;
	}

	public void setHeadOfficeAddress(String headOfficeAddress) {
		this.headOfficeAddress = headOfficeAddress;
	}

	public String getHeadOfficeEmail() {
		return this.headOfficeEmail;
	}

	public void setHeadOfficeEmail(String headOfficeEmail) {
		this.headOfficeEmail = headOfficeEmail;
	}

	public String getHeadOfficeMobile() {
		return this.headOfficeMobile;
	}

	public void setHeadOfficeMobile(String headOfficeMobile) {
		this.headOfficeMobile = headOfficeMobile;
	}

	public String getHeadOfficeName() {
		return this.headOfficeName;
	}

	public void setHeadOfficeName(String headOfficeName) {
		this.headOfficeName = headOfficeName;
	}

	public String getHeadOfficePhone() {
		return this.headOfficePhone;
	}

	public void setHeadOfficePhone(String headOfficePhone) {
		this.headOfficePhone = headOfficePhone;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerAddress() {
		return this.ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	public String getOwnerEmail() {
		return this.ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getOwnerFirstName() {
		return this.ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerLastName() {
		return this.ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getOwnerMobile() {
		return this.ownerMobile;
	}

	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}

	public String getOwnerPhone() {
		return this.ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSyncFreqType() {
		return this.syncFreqType;
	}

	public void setSyncFreqType(String syncFreqType) {
		this.syncFreqType = syncFreqType;
	}

	public int getSyncTime() {
		return this.syncTime;
	}

	public void setSyncTime(int syncTime) {
		this.syncTime = syncTime;
	}

	public Date getUpdateDateTime() {
		return this.updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public OrganisationLoginTbl getOrganisationLoginTbl() {
		return this.organisationLoginTbl;
	}

	public void setOrganisationLoginTbl(OrganisationLoginTbl organisationLoginTbl) {
		this.organisationLoginTbl = organisationLoginTbl;
	}

	/**
	 * @return the enableSync
	 */
	public boolean isEnableSync() {
		return enableSync;
	}

	/**
	 * @param enableSync the enableSync to set
	 */
	public void setEnableSync(boolean enableSync) {
		this.enableSync = enableSync;
	}

}