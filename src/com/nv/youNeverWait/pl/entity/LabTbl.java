package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the lab_tbl database table.
 * 
 */
@Entity
@Table(name="lab_tbl")
@NamedQuery(name="LabTbl.findAll", query="SELECT l FROM LabTbl l")
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

	@Lob
	@Column(name="order_type_code", nullable=false)
	private String orderTypeCode;

	@Column(name="enable_sync", nullable=false)
	private boolean enableSync;

	@Column(name="sync_freq_type", length=45)
	private String syncFreqType;

	@Column(name="sync_time")
	private int syncTime;

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
	//bi-directional many-to-one association to LabBranchTbl
	@OneToMany(mappedBy="labTbl")
	private List<LabBranchTbl> labBranchTbls;

	//bi-directional many-to-one association to LoginTbl
	@ManyToOne
	@JoinColumn(name="login_id")
	private LoginTbl loginTbl;

	//bi-directional many-to-one association to OrderAmountTbl
	@OneToMany(mappedBy="labTbl")
	private List<OrderAmountTbl> orderAmountTbls;

	//bi-directional many-to-one association to OrderBranchTbl
	@OneToMany(mappedBy="labTbl")
	private List<OrderBranchTbl> orderBranchTbls;

	//bi-directional many-to-one association to OrderResultTbl
	@OneToMany(mappedBy="labTbl")
	private List<OrderResultTbl> orderResultTbls;

	//bi-directional many-to-one association to OrderTbl
	@OneToMany(mappedBy="sourceLabTbl")
	private List<OrderTbl> sourceLabTbls;

	//bi-directional many-to-one association to OrderTbl
	@OneToMany(mappedBy="destinationLabTbl")
	private List<OrderTbl> destinationLabTbls;

	//bi-directional many-to-one association to OrderTransferTbl
	@OneToMany(mappedBy="labTbl")
	private List<OrderTransferTbl> orderTransferTbls;

	//bi-directional many-to-one association to ResultTbl
	@OneToMany(mappedBy="labTbl")
	private List<ResultTbl> resultTbls;

	public LabTbl() {
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

	public String getOrderTypeCode() {
		return this.orderTypeCode;
	}

	public void setOrderTypeCode(String orderTypeCode) {
		this.orderTypeCode = orderTypeCode;
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

	public List<LabBranchTbl> getLabBranchTbls() {
		return this.labBranchTbls;
	}

	public void setLabBranchTbls(List<LabBranchTbl> labBranchTbls) {
		this.labBranchTbls = labBranchTbls;
	}

	public LabBranchTbl addLabBranchTbl(LabBranchTbl labBranchTbl) {
		getLabBranchTbls().add(labBranchTbl);
		labBranchTbl.setLabTbl(this);

		return labBranchTbl;
	}

	public LabBranchTbl removeLabBranchTbl(LabBranchTbl labBranchTbl) {
		getLabBranchTbls().remove(labBranchTbl);
		labBranchTbl.setLabTbl(null);

		return labBranchTbl;
	}

	public List<OrderAmountTbl> getOrderAmountTbls() {
		return this.orderAmountTbls;
	}

	public void setOrderAmountTbls(List<OrderAmountTbl> orderAmountTbls) {
		this.orderAmountTbls = orderAmountTbls;
	}

	public OrderAmountTbl addOrderAmountTbl(OrderAmountTbl orderAmountTbl) {
		getOrderAmountTbls().add(orderAmountTbl);
		orderAmountTbl.setLabTbl(this);

		return orderAmountTbl;
	}

	public OrderAmountTbl removeOrderAmountTbl(OrderAmountTbl orderAmountTbl) {
		getOrderAmountTbls().remove(orderAmountTbl);
		orderAmountTbl.setLabTbl(null);

		return orderAmountTbl;
	}

	public List<OrderBranchTbl> getOrderBranchTbls() {
		return this.orderBranchTbls;
	}

	public void setOrderBranchTbls(List<OrderBranchTbl> orderBranchTbls) {
		this.orderBranchTbls = orderBranchTbls;
	}

	public OrderBranchTbl addOrderBranchTbl(OrderBranchTbl orderBranchTbl) {
		getOrderBranchTbls().add(orderBranchTbl);
		orderBranchTbl.setLabTbl(this);

		return orderBranchTbl;
	}

	public OrderBranchTbl removeOrderBranchTbl(OrderBranchTbl orderBranchTbl) {
		getOrderBranchTbls().remove(orderBranchTbl);
		orderBranchTbl.setLabTbl(null);

		return orderBranchTbl;
	}

	public List<OrderResultTbl> getOrderResultTbls() {
		return this.orderResultTbls;
	}

	public void setOrderResultTbls(List<OrderResultTbl> orderResultTbls) {
		this.orderResultTbls = orderResultTbls;
	}

	public OrderResultTbl addOrderResultTbl(OrderResultTbl orderResultTbl) {
		getOrderResultTbls().add(orderResultTbl);
		orderResultTbl.setLabTbl(this);

		return orderResultTbl;
	}

	public OrderResultTbl removeOrderResultTbl(OrderResultTbl orderResultTbl) {
		getOrderResultTbls().remove(orderResultTbl);
		orderResultTbl.setLabTbl(null);

		return orderResultTbl;
	}



	public List<OrderTransferTbl> getOrderTransferTbls() {
		return this.orderTransferTbls;
	}

	public void setOrderTransferTbls(List<OrderTransferTbl> orderTransferTbls) {
		this.orderTransferTbls = orderTransferTbls;
	}

	public OrderTransferTbl addOrderTransferTbl(OrderTransferTbl orderTransferTbl) {
		getOrderTransferTbls().add(orderTransferTbl);
		orderTransferTbl.setLabTbl(this);

		return orderTransferTbl;
	}

	public OrderTransferTbl removeOrderTransferTbl(OrderTransferTbl orderTransferTbl) {
		getOrderTransferTbls().remove(orderTransferTbl);
		orderTransferTbl.setLabTbl(null);

		return orderTransferTbl;
	}

	public List<ResultTbl> getResultTbls() {
		return this.resultTbls;
	}

	public void setResultTbls(List<ResultTbl> resultTbls) {
		this.resultTbls = resultTbls;
	}

	public ResultTbl addResultTbl(ResultTbl resultTbl) {
		getResultTbls().add(resultTbl);
		resultTbl.setLabTbl(this);

		return resultTbl;
	}

	public ResultTbl removeResultTbl(ResultTbl resultTbl) {
		getResultTbls().remove(resultTbl);
		resultTbl.setLabTbl(null);

		return resultTbl;
	}

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

	/**
	 * @return the sourceLabTbls
	 */
	public List<OrderTbl> getSourceLabTbls() {
		return sourceLabTbls;
	}

	/**
	 * @param sourceLabTbls the sourceLabTbls to set
	 */
	public void setSourceLabTbls(List<OrderTbl> sourceLabTbls) {
		this.sourceLabTbls = sourceLabTbls;
	}

	/**
	 * @return the destinationLabTbls
	 */
	public List<OrderTbl> getDestinationLabTbls() {
		return destinationLabTbls;
	}

	/**
	 * @param destinationLabTbls the destinationLabTbls to set
	 */
	public void setDestinationLabTbls(List<OrderTbl> destinationLabTbls) {
		this.destinationLabTbls = destinationLabTbls;
	}

	/**
	 * @return the loginTbl
	 */
	public LoginTbl getLoginTbl() {
		return loginTbl;
	}

	/**
	 * @param loginTbl the loginTbl to set
	 */
	public void setLoginTbl(LoginTbl loginTbl) {
		this.loginTbl = loginTbl;
	}

}