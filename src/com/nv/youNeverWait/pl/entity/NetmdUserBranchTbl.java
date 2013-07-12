package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the netmd_user_branch_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_user_branch_tbl")
public class NetmdUserBranchTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to NetmdUserTbl
    @ManyToOne
	@JoinColumn(name="netmd_user_id", nullable=false)
	private NetmdUserTbl netmdUserTbl;

	//bi-directional many-to-one association to NetmdBranchTbl
    @ManyToOne
	@JoinColumn(name="netmd_branch_id", nullable=false)
	private NetmdBranchTbl netmdBranchTbl;

    public NetmdUserBranchTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NetmdUserTbl getNetmdUserTbl() {
		return this.netmdUserTbl;
	}

	public void setNetmdUserTbl(NetmdUserTbl netmdUserTbl) {
		this.netmdUserTbl = netmdUserTbl;
	}
	
	public NetmdBranchTbl getNetmdBranchTbl() {
		return this.netmdBranchTbl;
	}

	public void setNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		this.netmdBranchTbl = netmdBranchTbl;
	}
	
}