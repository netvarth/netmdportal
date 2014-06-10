package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the netpos_passphrase_tbl database table.
 * 
 */
@Entity
@Table(name="netpos_passphrase_tbl")
public class NetposPassphraseTbl implements Serializable {
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
	private byte primaryDevice;

	//bi-directional many-to-one association to NetposBranchTbl
	@ManyToOne
	@JoinColumn(name="netpos_branch_id", nullable=false)
	private NetposBranchTbl netposBranchTbl;

	//bi-directional many-to-one association to NetposUserTbl
	@OneToMany(mappedBy="netposPassphraseTbl")
	private List<NetposUserTbl> netposUserTbls;

	public NetposPassphraseTbl() {
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

	public byte getPrimaryDevice() {
		return this.primaryDevice;
	}

	public void setPrimaryDevice(byte primaryDevice) {
		this.primaryDevice = primaryDevice;
	}

	public NetposBranchTbl getNetposBranchTbl() {
		return this.netposBranchTbl;
	}

	public void setNetposBranchTbl(NetposBranchTbl netposBranchTbl) {
		this.netposBranchTbl = netposBranchTbl;
	}

	public List<NetposUserTbl> getNetposUserTbls() {
		return this.netposUserTbls;
	}

	public void setNetposUserTbls(List<NetposUserTbl> netposUserTbls) {
		this.netposUserTbls = netposUserTbls;
	}

	public NetposUserTbl addNetposUserTbl(NetposUserTbl netposUserTbl) {
		getNetposUserTbls().add(netposUserTbl);
		netposUserTbl.setNetposPassphraseTbl(this);

		return netposUserTbl;
	}

	public NetposUserTbl removeNetposUserTbl(NetposUserTbl netposUserTbl) {
		getNetposUserTbls().remove(netposUserTbl);
		netposUserTbl.setNetposPassphraseTbl(null);

		return netposUserTbl;
	}

}