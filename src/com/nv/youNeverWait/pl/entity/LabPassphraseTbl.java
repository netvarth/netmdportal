package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lab_passphrase_tbl database table.
 * 
 */
@Entity
@Table(name="lab_passphrase_tbl")
public class LabPassphraseTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="mac_id", length=45)
	private String macId;

	@Column(name="pass_phrase", nullable=false, length=45)
	private String passPhrase;

	//bi-directional many-to-one association to LabBranchTbl
    @ManyToOne
	@JoinColumn(name="lab_branch_id", nullable=false)
	private LabBranchTbl labBranchTbl;

    public LabPassphraseTbl() {
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

	public LabBranchTbl getLabBranchTbl() {
		return this.labBranchTbl;
	}

	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}
	
}