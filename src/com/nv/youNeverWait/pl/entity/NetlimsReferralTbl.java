package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the netlims_referral_tbl database table.
 * 
 */
@Entity
@Table(name="netlims_referral_tbl")
@NamedQuery(name="NetlimsReferralTbl.findAll", query="SELECT n FROM NetlimsReferralTbl n")
public class NetlimsReferralTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	//bi-directional many-to-one association to DoctorTbl
	@ManyToOne
	@JoinColumn(name="referral_id")
	private DoctorTbl doctorTbl;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="branch_id")
	private LabBranchTbl labBranchTbl;

	//bi-directional many-to-one association to ReferralResultTbl
	@OneToMany(mappedBy="netlimsReferralTbl")
	private List<ReferralResultTbl> referralResultTbls;

	public NetlimsReferralTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DoctorTbl getDoctorTbl() {
		return this.doctorTbl;
	}

	public void setDoctorTbl(DoctorTbl doctorTbl) {
		this.doctorTbl = doctorTbl;
	}

	public LabBranchTbl getLabBranchTbl() {
		return this.labBranchTbl;
	}

	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}

	public List<ReferralResultTbl> getReferralResultTbls() {
		return this.referralResultTbls;
	}

	public void setReferralResultTbls(List<ReferralResultTbl> referralResultTbls) {
		this.referralResultTbls = referralResultTbls;
	}

	public ReferralResultTbl addReferralResultTbl(ReferralResultTbl referralResultTbl) {
		getReferralResultTbls().add(referralResultTbl);
		referralResultTbl.setNetlimsReferralTbl(this);

		return referralResultTbl;
	}

	public ReferralResultTbl removeReferralResultTbl(ReferralResultTbl referralResultTbl) {
		getReferralResultTbls().remove(referralResultTbl);
		referralResultTbl.setNetlimsReferralTbl(null);

		return referralResultTbl;
	}

}