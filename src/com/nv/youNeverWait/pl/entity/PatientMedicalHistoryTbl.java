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
    private NetmdPatientTbl netmdPatientTbl;

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
	 * @return the diagonisedAge
	 */
	public String getDiagonisedAge() {
		return diagonisedAge;
	}

	/**
	 * @param diagonisedAge the diagonisedAge to set
	 */
	public void setDiagonisedAge(String diagonisedAge) {
		this.diagonisedAge = diagonisedAge;
	}

	/**
	 * @return the isCured
	 */
	public byte getIsCured() {
		return isCured;
	}

	/**
	 * @param isCured the isCured to set
	 */
	public void setIsCured(byte isCured) {
		this.isCured = isCured;
	}

	/**
	 * @return the medicalIssue
	 */
	public String getMedicalIssue() {
		return medicalIssue;
	}

	/**
	 * @param medicalIssue the medicalIssue to set
	 */
	public void setMedicalIssue(String medicalIssue) {
		this.medicalIssue = medicalIssue;
	}

	/**
	 * @return the medication
	 */
	public String getMedication() {
		return medication;
	}

	/**
	 * @param medication the medication to set
	 */
	public void setMedication(String medication) {
		this.medication = medication;
	}

	/**
	 * @return the surgery
	 */
	public String getSurgery() {
		return surgery;
	}

	/**
	 * @param surgery the surgery to set
	 */
	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}

	/**
	 * @return the tenure
	 */
	public String getTenure() {
		return tenure;
	}

	/**
	 * @param tenure the tenure to set
	 */
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	/**
	 * @return the treatment
	 */
	public String getTreatment() {
		return treatment;
	}

	/**
	 * @param treatment the treatment to set
	 */
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	/**
	 * @return the netmdPatientTbl
	 */
	public NetmdPatientTbl getNetmdPatientTbl() {
		return netmdPatientTbl;
	}

	/**
	 * @param netmdPatientTbl the netmdPatientTbl to set
	 */
	public void setNetmdPatientTbl(NetmdPatientTbl netmdPatientTbl) {
		this.netmdPatientTbl = netmdPatientTbl;
	}

   
}