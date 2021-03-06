package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the netmd_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_tbl")
public class NetmdTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_date_time", nullable=false)
	private Date createDateTime;

	@Column(name="enable_sync", nullable=false)
	private boolean enableSync;

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

	@Lob
	private String logo;

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

	@Column(name="sync_freq_type", length=45)
	private String syncFreqType;

	@Column(name="sync_time")
	private int syncTime;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_date_time", nullable=false)
	private Date updateDateTime;

	//bi-directional many-to-one association to NetmdBillTbl
	@OneToMany(mappedBy="netmdTbl")
	private List<NetmdBillTbl> netmdBillTbls;

	//bi-directional many-to-one association to NetmdBranchTbl
	@OneToMany(mappedBy="netmdTbl")
	private List<NetmdBranchTbl> netmdBranchTbls;

	//bi-directional many-to-one association to LoginTbl
	@ManyToOne
	@JoinColumn(name="login_id")
	private LoginTbl loginTbl;

	//bi-directional many-to-one association to ResultTbl
	@OneToMany(mappedBy="netmdTbl")
	private List<ResultTbl> resultTbls;

	public NetmdTbl() {
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

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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

	public List<NetmdBillTbl> getNetmdBillTbls() {
		return this.netmdBillTbls;
	}

	public void setNetmdBillTbls(List<NetmdBillTbl> netmdBillTbls) {
		this.netmdBillTbls = netmdBillTbls;
	}

	public NetmdBillTbl addNetmdBillTbl(NetmdBillTbl netmdBillTbl) {
		getNetmdBillTbls().add(netmdBillTbl);
		netmdBillTbl.setNetmdTbl(this);

		return netmdBillTbl;
	}

	public NetmdBillTbl removeNetmdBillTbl(NetmdBillTbl netmdBillTbl) {
		getNetmdBillTbls().remove(netmdBillTbl);
		netmdBillTbl.setNetmdTbl(null);

		return netmdBillTbl;
	}

	public List<NetmdBranchTbl> getNetmdBranchTbls() {
		return this.netmdBranchTbls;
	}

	public void setNetmdBranchTbls(List<NetmdBranchTbl> netmdBranchTbls) {
		this.netmdBranchTbls = netmdBranchTbls;
	}

	public NetmdBranchTbl addNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		getNetmdBranchTbls().add(netmdBranchTbl);
		netmdBranchTbl.setNetmdTbl(this);

		return netmdBranchTbl;
	}

	public NetmdBranchTbl removeNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		getNetmdBranchTbls().remove(netmdBranchTbl);
		netmdBranchTbl.setNetmdTbl(null);

		return netmdBranchTbl;
	}

	public LoginTbl getLoginTbl() {
		return this.loginTbl;
	}

	public void setLoginTbl(LoginTbl loginTbl) {
		this.loginTbl = loginTbl;
	}

	public List<ResultTbl> getResultTbls() {
		return this.resultTbls;
	}

	public void setResultTbls(List<ResultTbl> resultTbls) {
		this.resultTbls = resultTbls;
	}

	public ResultTbl addResultTbl(ResultTbl resultTbl) {
		getResultTbls().add(resultTbl);
		resultTbl.setNetmdTbl(this);

		return resultTbl;
	}

	public ResultTbl removeResultTbl(ResultTbl resultTbl) {
		getResultTbls().remove(resultTbl);
		resultTbl.setNetmdTbl(null);

		return resultTbl;
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