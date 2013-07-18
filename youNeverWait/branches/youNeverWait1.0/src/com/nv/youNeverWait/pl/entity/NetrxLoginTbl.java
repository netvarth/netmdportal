package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the netrx_login_tbl database table.
 * 
 */
@Entity
@Table(name="netrx_login_tbl")
public class NetrxLoginTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;
	
	@Column(nullable=false,length=45)
	private String password;

	@Column(name="user_name", nullable=false, length=45)
	private String userName;

	@Column(name="user_type", nullable=false, length=45)
	private String userType;

	//bi-directional many-to-one association to NetrxTbl
	@OneToMany(mappedBy="netrxLoginTbl")
	private Set<NetrxTbl> netrxTbls;

	//bi-directional many-to-one association to NetrxUserTbl
	@OneToMany(mappedBy="netrxLoginTbl")
	private Set<NetrxUserTbl> netrxUserTbls;

    public NetrxLoginTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Set<NetrxTbl> getNetrxTbls() {
		return this.netrxTbls;
	}

	public void setNetrxTbls(Set<NetrxTbl> netrxTbls) {
		this.netrxTbls = netrxTbls;
	}
	
	public Set<NetrxUserTbl> getNetrxUserTbls() {
		return this.netrxUserTbls;
	}

	public void setNetrxUserTbls(Set<NetrxUserTbl> netrxUserTbls) {
		this.netrxUserTbls = netrxUserTbls;
	}
	
}