package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the netpos_login_tbl database table.
 * 
 */
@Entity
@Table(name="netpos_login_tbl")
public class NetposLoginTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String password;

	@Column(name="user_name", nullable=false, length=45)
	private String userName;

	@Column(name="user_type", nullable=false, length=1)
	private String userType;

	//bi-directional many-to-one association to NetposTbl
	@OneToMany(mappedBy="netposLoginTbl")
	private List<NetposTbl> netposTbls;

	//bi-directional many-to-one association to NetposUserTbl
	@OneToMany(mappedBy="netposLoginTbl")
	private List<NetposUserTbl> netposUserTbls;

	public NetposLoginTbl() {
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

	public List<NetposTbl> getNetposTbls() {
		return this.netposTbls;
	}

	public void setNetposTbls(List<NetposTbl> netposTbls) {
		this.netposTbls = netposTbls;
	}

	public NetposTbl addNetposTbl(NetposTbl netposTbl) {
		getNetposTbls().add(netposTbl);
		netposTbl.setNetposLoginTbl(this);

		return netposTbl;
	}

	public NetposTbl removeNetposTbl(NetposTbl netposTbl) {
		getNetposTbls().remove(netposTbl);
		netposTbl.setNetposLoginTbl(null);

		return netposTbl;
	}

	public List<NetposUserTbl> getNetposUserTbls() {
		return this.netposUserTbls;
	}

	public void setNetposUserTbls(List<NetposUserTbl> netposUserTbls) {
		this.netposUserTbls = netposUserTbls;
	}

	public NetposUserTbl addNetposUserTbl(NetposUserTbl netposUserTbl) {
		getNetposUserTbls().add(netposUserTbl);
		netposUserTbl.setNetposLoginTbl(this);

		return netposUserTbl;
	}

	public NetposUserTbl removeNetposUserTbl(NetposUserTbl netposUserTbl) {
		getNetposUserTbls().remove(netposUserTbl);
		netposUserTbl.setNetposLoginTbl(null);

		return netposUserTbl;
	}

}