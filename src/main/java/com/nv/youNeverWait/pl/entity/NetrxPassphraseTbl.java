package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the netrx_passphrase_tbl database table.
 * 
 */
@Entity
@Table(name="netrx_passphrase_tbl")
public class NetrxPassphraseTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="mac_id", length=45)
	private String macId;

	@Column(name="pass_phrase", length=45)
	private String passPhrase;

	@Column(name="primary_device", nullable=false)
	private boolean primaryDevice;

	//bi-directional many-to-one association to NetrxBranchTbl
    @ManyToOne
	@JoinColumn(name="netrx_branch_id", nullable=false)
	private NetrxBranchTbl netrxBranchTbl;

	//bi-directional many-to-one association to NetrxUserTbl
	@OneToMany(mappedBy="netrxPassphraseTbl")
	private Set<NetrxUserTbl> netrxUserTbls;

    public NetrxPassphraseTbl() {
    }

    
	/**
	 * @return the primaryDevice
	 */
	public boolean isPrimaryDevice() {
		return primaryDevice;
	}


	/**
	 * @param primaryDevice the primaryDevice to set
	 */
	public void setPrimaryDevice(boolean primaryDevice) {
		this.primaryDevice = primaryDevice;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMacId() {
		return this.macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getPassPhrase() {
		return this.passPhrase;
	}

	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}

	

	public NetrxBranchTbl getNetrxBranchTbl() {
		return this.netrxBranchTbl;
	}

	public void setNetrxBranchTbl(NetrxBranchTbl netrxBranchTbl) {
		this.netrxBranchTbl = netrxBranchTbl;
	}
	
	public Set<NetrxUserTbl> getNetrxUserTbls() {
		return this.netrxUserTbls;
	}

	public void setNetrxUserTbls(Set<NetrxUserTbl> netrxUserTbls) {
		this.netrxUserTbls = netrxUserTbls;
	}
	
}