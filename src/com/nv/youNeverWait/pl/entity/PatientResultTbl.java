package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the patient_result_tbl database table.
 * 
 */
@Entity
@Table(name="patient_result_tbl")
@NamedQuery(name="PatientResultTbl.findAll", query="SELECT p FROM PatientResultTbl p")
public class PatientResultTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	//bi-directional many-to-one association to NetlimsPatientTbl
	@ManyToOne
	@JoinColumn(name="patient_id")
	private NetlimsPatientTbl netlimsPatientTbl;

	//bi-directional many-to-one association to NetlimsOrderTbl
	@ManyToOne
	@JoinColumn(name="portal_order_id")
	private NetlimsOrderTbl netlimsOrderTbl;

	public PatientResultTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NetlimsPatientTbl getNetlimsPatientTbl() {
		return this.netlimsPatientTbl;
	}

	public void setNetlimsPatientTbl(NetlimsPatientTbl netlimsPatientTbl) {
		this.netlimsPatientTbl = netlimsPatientTbl;
	}

	public NetlimsOrderTbl getNetlimsOrderTbl() {
		return this.netlimsOrderTbl;
	}

	public void setNetlimsOrderTbl(NetlimsOrderTbl netlimsOrderTbl) {
		this.netlimsOrderTbl = netlimsOrderTbl;
	}

}