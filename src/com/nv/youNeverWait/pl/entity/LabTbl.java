package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the lab_tbl database table.
 * 
 */
@Entity
@Table(name="lab_tbl")
public class LabTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	

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

	@Column(name="owner_email", nullable=false, length=45)
	private String ownerEmail;

	@Column(name="owner_first_name", nullable=false, length=45)
	private String ownerFirstName;

	@Column(name="owner_last_name", length=45)
	private String ownerLastName;

	@Column(name="owner_mobile", length=45)
	private String ownerMobile;

	@Column(name="owner_phone", length=45)
	private String ownerPhone;

//	@Column(name="lab_code", nullable=false, length=45)
//	private String labCode;
	
	@Lob
	@Column(name="order_type_code", nullable=false)
	private String orderTypeCode;
	
	//bi-directional many-to-one association to LabBranchTbl
	@OneToMany(mappedBy="labTbl")
	private Set<LabBranchTbl> labBranchTbls;

	//bi-directional many-to-one association to NetlimsLoginTbl
    @ManyToOne
	@JoinColumn(name="login_id", nullable=false)
	private LabLoginTbl labLoginTbl;

	//bi-directional many-to-one association to OrderTbl
	@OneToMany(mappedBy="sourceLabTbl")
	private Set<OrderTbl> sourceLabTbls;

	//bi-directional many-to-one association to OrderTbl
	@OneToMany(mappedBy="destinationLabTbl")
	private Set<OrderTbl> destinationLabTbls;

	@Column(name="auth_to_sent_result")
	private boolean authToSentResult;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_date_time", nullable=false)
	private Date createDateTime;


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_date_time", nullable=false)
	private Date updateDateTime;
    
    @Column(nullable=false, length=45)
	private String status;

    
    public LabTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public String getLabCode() {
//		return this.labCode;
//	}
//
//	public void setLabCode(String labCode) {
//		this.labCode = labCode;
//	}

	/**
	 * @return the authToSentResult
	 */
	public boolean isAuthToSentResult() {
		return authToSentResult;
	}

	/**
	 * @param authToSentResult the authToSentResult to set
	 */
	public void setAuthToSentResult(boolean authToSentResult) {
		this.authToSentResult = authToSentResult;
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

	public Set<LabBranchTbl> getLabBranchTbls() {
		return this.labBranchTbls;
	}

	public void setLabBranchTbls(Set<LabBranchTbl> labBranchTbls) {
		this.labBranchTbls = labBranchTbls;
	}
	
	public String getOrderTypeCode() {
		return this.orderTypeCode;
	}

	public void setOrderTypeCode(String orderTypeCode) {
		this.orderTypeCode = orderTypeCode;
	}


	/**
	 * @return the labLoginTbl
	 */
	public LabLoginTbl getLabLoginTbl() {
		return labLoginTbl;
	}

	/**
	 * @param labLoginTbl the labLoginTbl to set
	 */
	public void setLabLoginTbl(LabLoginTbl labLoginTbl) {
		this.labLoginTbl = labLoginTbl;
	}

	/**
	 * @return the sourceLabTbls
	 */
	public Set<OrderTbl> getSourceLabTbls() {
		return sourceLabTbls;
	}

	/**
	 * @param sourceLabTbls the sourceLabTbls to set
	 */
	public void setSourceLabTbls(Set<OrderTbl> sourceLabTbls) {
		this.sourceLabTbls = sourceLabTbls;
	}

	/**
	 * @return the destinationLabTbls
	 */
	public Set<OrderTbl> getDestinationLabTbls() {
		return destinationLabTbls;
	}

	/**
	 * @param destinationLabTbls the destinationLabTbls to set
	 */
	public void setDestinationLabTbls(Set<OrderTbl> destinationLabTbls) {
		this.destinationLabTbls = destinationLabTbls;
	}
	
	
	
}