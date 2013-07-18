package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the patient_medical_history_tbl database table.
 * 
 */
@Entity
@Table(name="patient_medical_history_tbl")
public class PatientMedicalHistoryTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="diagonised_age", nullable=false, length=3)
	private String diagonisedAge;

	private byte isCured;

	@Column(name="medical_issue", nullable=false, length=245)
	private String medicalIssue;

	@Column(length=245)
	private String medication;

	@Column(nullable=false, length=245)
	private String surgery;

	@Column(nullable=false, length=45)
	private String tenure;

	@Column(nullable=false, length=245)
	private String treatment;

	//bi-directional many-to-one association to PatientTbl
    @ManyToOne
	@JoinColumn(name="patient_id", nullable=false)
	private PatientTbl patientTbl;

    public PatientMedicalHistoryTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiagonisedAge() {
		return this.diagonisedAge;
	}

	public void setDiagonisedAge(String diagonisedAge) {
		this.diagonisedAge = diagonisedAge;
	}

	public byte getIsCured() {
		return this.isCured;
	}

	public void setIsCured(byte isCured) {
		this.isCured = isCured;
	}

	public String getMedicalIssue() {
		return this.medicalIssue;
	}

	public void setMedicalIssue(String medicalIssue) {
		this.medicalIssue = medicalIssue;
	}

	public String getMedication() {
		return this.medication;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}

	public String getSurgery() {
		return this.surgery;
	}

	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}

	public String getTenure() {
		return this.tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getTreatment() {
		return this.treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public PatientTbl getPatientTbl() {
		return this.patientTbl;
	}

	public void setPatientTbl(PatientTbl patientTbl) {
		this.patientTbl = patientTbl;
	}
	
}