package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the netrx_tbl database table.
 * 
 */
@Entity
@Table(name="netrx_tbl")
public class NetrxTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_date_time", nullable=false)
	private Date createDateTime;

	@Column(name="head_office_address", length=145)
	private String headOfficeAddress;

	@Column(name="head_office_email", length=45)
	private String headOfficeEmail;

	@Column(name="head_office_mobile", length=45)
	private String headOfficeMobile;

	@Column(name="head_office_name", length=45)
	private String headOfficeName;

	@Column(name="head_office_phone", length=45)
	private String headOfficePhone;
	
	@Column(length=45)
	private String name;

	@Column(name="owner_address", length=145)
	private String ownerAddress;

	@Column(name="owner_email", length=45)
	private String ownerEmail;

	@Column(name="owner_first_name", length=45)
	private String ownerFirstName;

	@Column(name="owner_last_name", length=45)
	private String ownerLastName;

	@Column(name="owner_mobile", length=45)
	private String ownerMobile;

	@Column(name="owner_phone", length=45)
	private String ownerPhone;
	
	@Column(nullable=false, length=45)
	private String status;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_date_time", nullable=false)
	private Date updateDateTime;
  
    //bi-directional many-to-one association to NetrxBranchTbl
  	@OneToMany(mappedBy="netrxTbl")
  	private Set<NetrxBranchTbl> netrxBranchTbls;
	
  	//bi-directional many-to-one association to NetrxLoginTbl
    @ManyToOne
	@JoinColumn(name="login_id")
	private NetrxLoginTbl netrxLoginTbl;

    public NetrxTbl() {
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

	public Date getUpdateDateTime() {
		return this.updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public NetrxLoginTbl getNetrxLoginTbl() {
		return this.netrxLoginTbl;
	}

	public void setNetrxLoginTbl(NetrxLoginTbl netrxLoginTbl) {
		this.netrxLoginTbl = netrxLoginTbl;
	}

	/**
	 * @return the netrxBranchTbls
	 */
	public Set<NetrxBranchTbl> getNetrxBranchTbls() {
		return netrxBranchTbls;
	}

	/**
	 * @param netrxBranchTbls the netrxBranchTbls to set
	 */
	public void setNetrxBranchTbls(Set<NetrxBranchTbl> netrxBranchTbls) {
		this.netrxBranchTbls = netrxBranchTbls;
	}
	
}