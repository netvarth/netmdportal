package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the netmd_user_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_user_tbl")
public class NetmdUserTbl implements Serializable {
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

    @Column(nullable=false, length=45)
	private String status;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_date_time")
	private Date updateDateTime;

    @Column(name="user_type", nullable=false, length=45)
	private String userType;

	//bi-directional many-to-one association to NetmdPassphraseTbl
    @ManyToOne
	@JoinColumn(name="netmd_passphrase_id")
	private NetmdPassphraseTbl netmdPassphraseTbl;

	//bi-directional many-to-one association to NetmdBranchTbl
    @ManyToOne
    @JoinColumn(name="netmd_branch_id", nullable=false)
	private NetmdBranchTbl netmdBranchTbl;

	//bi-directional many-to-one association to LoginTbl
    @ManyToOne
    @JoinColumn(name="login_id", nullable=false)
	private LoginTbl loginTbl;

    public NetmdUserTbl() {
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

	public NetmdPassphraseTbl getNetmdPassphraseTbl() {
		return this.netmdPassphraseTbl;
	}

	public void setNetmdPassphraseTbl(NetmdPassphraseTbl netmdPassphraseTbl) {
		this.netmdPassphraseTbl = netmdPassphraseTbl;
	}
	
	public NetmdBranchTbl getNetmdBranchTbl() {
		return this.netmdBranchTbl;
	}

	public void setNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		this.netmdBranchTbl = netmdBranchTbl;
	}
	
	public LoginTbl getLoginTbl() {
		return this.loginTbl;
	}

	public void setLoginTbl(LoginTbl loginTbl) {
		this.loginTbl = loginTbl;
	}
	
}