package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the lab_user_tbl database table.
 * 
 */
@Entity
@Table(name="lab_user_tbl")
public class LabUserTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="ref_uid")
	private int refUid;
	
	//bi-directional many-to-one association to LoginTbl
	@ManyToOne
	@JoinColumn(name="login_id")
	private LoginTbl loginTbl;
	
	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="lab_branch_id", nullable=false)
	private LabBranchTbl labBranchTbl;

	//bi-directional many-to-one association to UserTbl
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserTbl userTbl;

	@Column(nullable=false, length=45)
	private String status;

	/**
	 * 
	 */
	public LabUserTbl() {
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

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the labBranchTbl
	 */
	public LabBranchTbl getLabBranchTbl() {
		return labBranchTbl;
	}

	/**
	 * @param labBranchTbl the labBranchTbl to set
	 */
	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}

	/**
	 * @return the userTbl
	 */
	public UserTbl getUserTbl() {
		return userTbl;
	}

	/**
	 * @param userTbl the userTbl to set
	 */
	public void setUserTbl(UserTbl userTbl) {
		this.userTbl = userTbl;
	}

	/**
	 * @return the refUid
	 */
	public int getRefUid() {
		return refUid;
	}

	/**
	 * @param refUid the refUid to set
	 */
	public void setRefUid(int refUid) {
		this.refUid = refUid;
	}

	/**
	 * @return the loginTbl
	 */
	public LoginTbl getLoginTbl() {
		return loginTbl;
	}

	/**
	 * @param loginTbl the loginTbl to set
	 */
	public void setLoginTbl(LoginTbl loginTbl) {
		this.loginTbl = loginTbl;
	}

}