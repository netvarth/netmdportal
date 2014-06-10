package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lab_user_branch_tbl database table.
 * 
 */
@Entity
@Table(name="lab_user_branch_tbl")
public class LabUserBranchTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to LabBranchTbl
    @ManyToOne
	@JoinColumn(name="lab_branch_id", nullable=false)
	private LabBranchTbl labBranchTbl;

	//bi-directional many-to-one association to LabUserTbl
    @ManyToOne
	@JoinColumn(name="lab_user_id", nullable=false)
	private LabUserTbl labUserTbl;

    @Column(nullable=false, length=45)
	private String status;
    
    public LabUserBranchTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LabBranchTbl getLabBranchTbl() {
		return this.labBranchTbl;
	}

	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}
	
	public LabUserTbl getLabUserTbl() {
		return this.labUserTbl;
	}

	public void setLabUserTbl(LabUserTbl labUserTbl) {
		this.labUserTbl = labUserTbl;
	}

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
	
}