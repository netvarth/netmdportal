package com.nv.NetlimsPortal.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the lab_branch_tbl database table.
 * 
 */
@Entity
@Table(name="lab_branch_tbl")
public class LabBranchTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	
	@Column(length=145)
	private String address;

	@Column(length=45)
	private String mobile;

	@Column(nullable=false, length=45)
	private String name;

	@Column(length=45)
	private String phone;
	
	@Column(length=45)
	private String email;

//	@Column(name="branch_code", nullable=false, length=45)
//	private String branchCode;
	
	//bi-directional many-to-one association to LabTbl
    @ManyToOne
	@JoinColumn(name="lab_id", nullable=false)
	private LabTbl labTbl;

	//bi-directional many-to-one association to LabPassphraseTbl
	@OneToMany(mappedBy="labBranchTbl")
	private Set<LabPassphraseTbl> labPassphraseTbls;

	//bi-directional many-to-one association to LabUserBranchTbl
	@OneToMany(mappedBy="labBranchTbl")
	private Set<LabUserBranchTbl> labUserBranchTbls;

	@Column(nullable=false, length=45)
	private String status;


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_date_time", nullable=false)
	private Date updateDateTime;
	
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_date_time", nullable=false)
	private Date createDateTime;

    @Column(name="sync_freq_type", length=45)
	private String syncFreqType;

	@Column(name="sync_time")
	private int syncTime;
    
	@Column(name="enable_sync", nullable=false)
	private boolean enableSync;
	
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

	public String getAddress() {
		return this.address;
	}
	
    public LabBranchTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	

	public void setAddress(String address) {
		this.address = address;
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

	public LabTbl getLabTbl() {
		return this.labTbl;
	}

	public void setLabTbl(LabTbl labTbl) {
		this.labTbl = labTbl;
	}
	
	public Set<LabPassphraseTbl> getLabPassphraseTbls() {
		return this.labPassphraseTbls;
	}

	public void setLabPassphraseTbls(Set<LabPassphraseTbl> labPassphraseTbls) {
		this.labPassphraseTbls = labPassphraseTbls;
	}
	
	public Set<LabUserBranchTbl> getLabUserBranchTbls() {
		return this.labUserBranchTbls;
	}

	public void setLabUserBranchTbls(Set<LabUserBranchTbl> labUserBranchTbls) {
		this.labUserBranchTbls = labUserBranchTbls;
	}

//	public String getBranchCode() {
//		return this.branchCode;
//	}
//
//	public void setBranchCode(String branchCode) {
//		this.branchCode = branchCode;
//	}
	
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
	public boolean getEnableSync() {
		return this.enableSync;
	}

	public void setEnableSync(boolean enableSync) {
		this.enableSync = enableSync;
	}

}