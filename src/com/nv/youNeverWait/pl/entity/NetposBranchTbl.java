package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the netpos_branch_tbl database table.
 * 
 */
@Entity
@Table(name="netpos_branch_tbl")
public class NetposBranchTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=145)
	private String address;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date_time")
	private Date createDateTime;

	@Column(length=45)
	private String email;

	@Column(name="enable_sync", nullable=false)
	private byte enableSync;

	@Column(length=45)
	private String mobile;

	@Column(length=45)
	private String name;

	@Column(length=45)
	private String phone;

	@Column(nullable=false, length=1)
	private String status;

	@Column(name="sync_freq_type", length=1)
	private String syncFreqType;

	@Column(name="sync_time")
	private int syncTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date_time")
	private Date updateDateTime;

	//bi-directional many-to-one association to NetposTbl
	@ManyToOne
	@JoinColumn(name="netpos_id", nullable=false)
	private NetposTbl netposTbl;

	//bi-directional many-to-one association to NetposPassphraseTbl
	@OneToMany(mappedBy="netposBranchTbl")
	private List<NetposPassphraseTbl> netposPassphraseTbls;

	//bi-directional many-to-one association to NetposUserTbl
	@OneToMany(mappedBy="netposBranchTbl")
	private List<NetposUserTbl> netposUserTbls;

	public NetposBranchTbl() {
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

	public byte getEnableSync() {
		return this.enableSync;
	}

	public void setEnableSync(byte enableSync) {
		this.enableSync = enableSync;
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

	public NetposTbl getNetposTbl() {
		return this.netposTbl;
	}

	public void setNetposTbl(NetposTbl netposTbl) {
		this.netposTbl = netposTbl;
	}

	public List<NetposPassphraseTbl> getNetposPassphraseTbls() {
		return this.netposPassphraseTbls;
	}

	public void setNetposPassphraseTbls(List<NetposPassphraseTbl> netposPassphraseTbls) {
		this.netposPassphraseTbls = netposPassphraseTbls;
	}

	public NetposPassphraseTbl addNetposPassphraseTbl(NetposPassphraseTbl netposPassphraseTbl) {
		getNetposPassphraseTbls().add(netposPassphraseTbl);
		netposPassphraseTbl.setNetposBranchTbl(this);

		return netposPassphraseTbl;
	}

	public NetposPassphraseTbl removeNetposPassphraseTbl(NetposPassphraseTbl netposPassphraseTbl) {
		getNetposPassphraseTbls().remove(netposPassphraseTbl);
		netposPassphraseTbl.setNetposBranchTbl(null);

		return netposPassphraseTbl;
	}

	public List<NetposUserTbl> getNetposUserTbls() {
		return this.netposUserTbls;
	}

	public void setNetposUserTbls(List<NetposUserTbl> netposUserTbls) {
		this.netposUserTbls = netposUserTbls;
	}

	public NetposUserTbl addNetposUserTbl(NetposUserTbl netposUserTbl) {
		getNetposUserTbls().add(netposUserTbl);
		netposUserTbl.setNetposBranchTbl(this);

		return netposUserTbl;
	}

	public NetposUserTbl removeNetposUserTbl(NetposUserTbl netposUserTbl) {
		getNetposUserTbls().remove(netposUserTbl);
		netposUserTbl.setNetposBranchTbl(null);

		return netposUserTbl;
	}

}