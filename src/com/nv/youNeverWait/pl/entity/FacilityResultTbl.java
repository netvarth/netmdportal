package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the facility_result_tbl database table.
 * 
 */
@Entity
@Table(name="facility_result_tbl")
public class FacilityResultTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	//bi-directional many-to-one association to FacilityTbl
	@ManyToOne
	@JoinColumn(name="facility_id")
	private LabFacilityTbl labFacilityTbl;

	//bi-directional many-to-one association to NetlimsOrderTbl
	@ManyToOne
	@JoinColumn(name="portal_order_id")
	private NetlimsOrderTbl netlimsOrderTbl;

	/**
	 * 
	 */
	public FacilityResultTbl() {
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
	 * @return the labFacilityTbl
	 */
	public LabFacilityTbl getLabFacilityTbl() {
		return labFacilityTbl;
	}

	/**
	 * @param labFacilityTbl the labFacilityTbl to set
	 */
	public void setLabFacilityTbl(LabFacilityTbl labFacilityTbl) {
		this.labFacilityTbl = labFacilityTbl;
	}

	/**
	 * @return the netlimsOrderTbl
	 */
	public NetlimsOrderTbl getNetlimsOrderTbl() {
		return netlimsOrderTbl;
	}

	/**
	 * @param netlimsOrderTbl the netlimsOrderTbl to set
	 */
	public void setNetlimsOrderTbl(NetlimsOrderTbl netlimsOrderTbl) {
		this.netlimsOrderTbl = netlimsOrderTbl;
	}

}