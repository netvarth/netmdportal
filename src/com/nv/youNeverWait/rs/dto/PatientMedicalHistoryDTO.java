package com.nv.youNeverWait.rs.dto;

public class PatientMedicalHistoryDTO {
	private int id;
	private int patientId;
	private String medicalIssue;
	private int diagonisedAge;
	private String tenure;
	private String treatment;
	private String surgery;
	private String medication;
	private String isCured;
	private String actionName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getMedicalIssue() {
		return medicalIssue;
	}
	public void setMedicalIssue(String medicalIssue) {
		this.medicalIssue = medicalIssue;
	}
	public int getDiagonisedAge() {
		return diagonisedAge;
	}
	public void setDiagonisedAge(int diagonisedAge) {
		this.diagonisedAge = diagonisedAge;
	}
	public String getTenure() {
		return tenure;
	}
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	public String getSurgery() {
		return surgery;
	}
	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}
	public String getMedication() {
		return medication;
	}
	public void setMedication(String medication) {
		this.medication = medication;
	}
	public String getIsCured() {
		return isCured;
	}
	public void setIsCured(String isCured) {
		this.isCured = isCured;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
}
