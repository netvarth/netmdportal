package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the facility_result_tbl database table.
 * 
 */
@Entity
@Table(name="facility_result_tbl")
@NamedQuery(name="FacilityResultTbl.findAll", query="SELECT f FROM FacilityResultTbl f")
public class FacilityResultTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	//bi-directional many-to-one association to FacilityTbl
	@ManyToOne
	@JoinColumn(name="facility_id")
	private FacilityTbl facilityTbl;

	//bi-directional many-to-one association to NetlimsOrderTbl
	@ManyToOne
	@JoinColumn(name="portal_order_id")
	private NetlimsOrderTbl netlimsOrderTbl;

	public FacilityResultTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FacilityTbl getFacilityTbl() {
		return this.facilityTbl;
	}

	public void setFacilityTbl(FacilityTbl facilityTbl) {
		this.facilityTbl = facilityTbl;
	}

	public NetlimsOrderTbl getNetlimsOrderTbl() {
		return this.netlimsOrderTbl;
	}

	public void setNetlimsOrderTbl(NetlimsOrderTbl netlimsOrderTbl) {
		this.netlimsOrderTbl = netlimsOrderTbl;
	}

}