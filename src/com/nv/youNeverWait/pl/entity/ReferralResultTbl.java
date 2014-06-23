package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the referral_result_tbl database table.
 * 
 */
@Entity
@Table(name="referral_result_tbl")
@NamedQuery(name="ReferralResultTbl.findAll", query="SELECT r FROM ReferralResultTbl r")
public class ReferralResultTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	//bi-directional many-to-one association to NetlimsReferralTbl
	@ManyToOne
	@JoinColumn(name="referral_id")
	private NetlimsReferralTbl netlimsReferralTbl;

	//bi-directional many-to-one association to NetlimsOrderTbl
	@ManyToOne
	@JoinColumn(name="portal_order_id")
	private NetlimsOrderTbl netlimsOrderTbl;

	public ReferralResultTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NetlimsReferralTbl getNetlimsReferralTbl() {
		return this.netlimsReferralTbl;
	}

	public void setNetlimsReferralTbl(NetlimsReferralTbl netlimsReferralTbl) {
		this.netlimsReferralTbl = netlimsReferralTbl;
	}

	public NetlimsOrderTbl getNetlimsOrderTbl() {
		return this.netlimsOrderTbl;
	}

	public void setNetlimsOrderTbl(NetlimsOrderTbl netlimsOrderTbl) {
		this.netlimsOrderTbl = netlimsOrderTbl;
	}

}