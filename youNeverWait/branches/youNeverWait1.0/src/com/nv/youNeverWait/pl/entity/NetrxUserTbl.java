package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the netrx_user_tbl database table.
 * 
 */
@Entity
@Table(name="netrx_user_tbl")
public class NetrxUserTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=145)
	private String address;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_date_time")
	private Date createDateTime;

    @Column(length=45)
	private String email;

    @Column(name="first_name", nullable=false, length=45)
	private String firstName;

	@Column(name="last_name", length=45)
	private String lastName;

	@Column(length=45)
	private String mobile;

	@Column(length=45)
	private String phone;

	@Column( nullable=false, length=45)
	private String status;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_date_time")
	private Date updateDateTime;

	@Column(name="user_type", nullable=false, length=45)
	private String userType;

	//bi-directional many-to-one association to NetrxPassphraseTbl
    @ManyToOne
	@JoinColumn(name="netrx_passphrase_id")
	private NetrxPassphraseTbl netrxPassphraseTbl;

	//bi-directional many-to-one association to NetrxLoginTbl
    @ManyToOne
	@JoinColumn(name="login_id")
	private NetrxLoginTbl netrxLoginTbl;

	//bi-directional many-to-one association to NetrxBranchTbl
    @ManyToOne
	@JoinColumn(name="netrx_branch_id")
	private NetrxBranchTbl netrxBranchTbl;

    public NetrxUserTbl() {
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public NetrxPassphraseTbl getNetrxPassphraseTbl() {
		return this.netrxPassphraseTbl;
	}

	public void setNetrxPassphraseTbl(NetrxPassphraseTbl netrxPassphraseTbl) {
		this.netrxPassphraseTbl = netrxPassphraseTbl;
	}
	
	public NetrxLoginTbl getNetrxLoginTbl() {
		return this.netrxLoginTbl;
	}

	public void setNetrxLoginTbl(NetrxLoginTbl netrxLoginTbl) {
		this.netrxLoginTbl = netrxLoginTbl;
	}
	
	public NetrxBranchTbl getNetrxBranchTbl() {
		return this.netrxBranchTbl;
	}

	public void setNetrxBranchTbl(NetrxBranchTbl netrxBranchTbl) {
		this.netrxBranchTbl = netrxBranchTbl;
	}
	
}