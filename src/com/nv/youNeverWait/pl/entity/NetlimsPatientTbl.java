package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the netlims_patient_tbl database table.
 * 
 */
@Entity
@Table(name="netlims_patient_tbl")
@NamedQuery(name="NetlimsPatientTbl.findAll", query="SELECT n FROM NetlimsPatientTbl n")
public class NetlimsPatientTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	//bi-directional many-to-one association to PatientTbl
	@ManyToOne
	@JoinColumn(name="patient_id")
	private PatientTbl patientTbl;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="branch_id")
	private LabBranchTbl labBranchTbl;

	//bi-directional many-to-one association to PatientResultTbl
	@OneToMany(mappedBy="netlimsPatientTbl")
	private List<PatientResultTbl> patientResultTbls;

	public NetlimsPatientTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PatientTbl getPatientTbl() {
		return this.patientTbl;
	}

	public void setPatientTbl(PatientTbl patientTbl) {
		this.patientTbl = patientTbl;
	}

	public LabBranchTbl getLabBranchTbl() {
		return this.labBranchTbl;
	}

	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}

	public List<PatientResultTbl> getPatientResultTbls() {
		return this.patientResultTbls;
	}

	public void setPatientResultTbls(List<PatientResultTbl> patientResultTbls) {
		this.patientResultTbls = patientResultTbls;
	}

	public PatientResultTbl addPatientResultTbl(PatientResultTbl patientResultTbl) {
		getPatientResultTbls().add(patientResultTbl);
		patientResultTbl.setNetlimsPatientTbl(this);

		return patientResultTbl;
	}

	public PatientResultTbl removePatientResultTbl(PatientResultTbl patientResultTbl) {
		getPatientResultTbls().remove(patientResultTbl);
		patientResultTbl.setNetlimsPatientTbl(null);

		return patientResultTbl;
	}

}