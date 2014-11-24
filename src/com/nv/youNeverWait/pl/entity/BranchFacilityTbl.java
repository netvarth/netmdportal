package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the branch_facility_tbl database table.
 * 
 */
@Entity
@Table(name="branch_facility_tbl")
public class BranchFacilityTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	//bi-directional many-to-one association to LabFacilityTbl
	@ManyToOne
	@JoinColumn(name="facility")
	private LabFacilityTbl labFacilityTbl;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="branch")
	private LabBranchTbl labBranchTbl;

	public BranchFacilityTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LabFacilityTbl getLabFacilityTbl() {
		return this.labFacilityTbl;
	}

	public void setLabFacilityTbl(LabFacilityTbl labFacilityTbl) {
		this.labFacilityTbl = labFacilityTbl;
	}

	public LabBranchTbl getLabBranchTbl() {
		return this.labBranchTbl;
	}

	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}

}