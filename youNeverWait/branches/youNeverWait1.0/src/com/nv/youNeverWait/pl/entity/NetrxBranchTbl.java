package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the netrx_branch_tbl database table.
 * 
 */
@Entity
@Table(name="netrx_branch_tbl")
public class NetrxBranchTbl implements Serializable {
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

    @Column(length=45)
	private String mobile;

    @Column(length=45)
	private String name;

    @Column(length=45)
	private String phone;

    @Column(nullable=false,length=45)
	private String status;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_date_time")
	private Date updateDateTime;
 
    //bi-directional many-to-one association to NetrxTbl
    @ManyToOne
	@JoinColumn(name="netrx_id", nullable=false)
	private NetrxTbl netrxTbl;
    
	//bi-directional many-to-one association to NetrxPassphraseTbl
	@OneToMany(mappedBy="netrxBranchTbl")
	private Set<NetrxPassphraseTbl> netrxPassphraseTbls;

	//bi-directional many-to-one association to NetrxUserTbl
	@OneToMany(mappedBy="netrxBranchTbl")
	private Set<NetrxUserTbl> netrxUserTbls;

    public NetrxBranchTbl() {
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

	public Date getUpdateDateTime() {
		return this.updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public Set<NetrxPassphraseTbl> getNetrxPassphraseTbls() {
		return this.netrxPassphraseTbls;
	}

	public void setNetrxPassphraseTbls(Set<NetrxPassphraseTbl> netrxPassphraseTbls) {
		this.netrxPassphraseTbls = netrxPassphraseTbls;
	}
	
	public Set<NetrxUserTbl> getNetrxUserTbls() {
		return this.netrxUserTbls;
	}

	public void setNetrxUserTbls(Set<NetrxUserTbl> netrxUserTbls) {
		this.netrxUserTbls = netrxUserTbls;
	}

	/**
	 * @return the netrxTbl
	 */
	public NetrxTbl getNetrxTbl() {
		return netrxTbl;
	}

	/**
	 * @param netrxTbl the netrxTbl to set
	 */
	public void setNetrxTbl(NetrxTbl netrxTbl) {
		this.netrxTbl = netrxTbl;
	}
	
}