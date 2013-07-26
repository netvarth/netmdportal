/**
 * CaseResponseDTO.java
 */
package com.nv.youNeverWait.rs.dto;
import java.util.ArrayList;
import java.util.List;

public class CaseResponseDTO {
	private int id;
	private int patientId;
	private String caseName;
	private String caseStatus;
	private String shortDesc;
	private String longDesc;
	private String date;
	private String actionName;
	private ErrorDTO error;
	private boolean success;
	private List<MedicalRecordDTO> medicalRecord=new ArrayList<MedicalRecordDTO>();
	public CaseResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getLongDesc() {
		return longDesc;
	}
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public ErrorDTO getError() {
		return error;
	}
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<MedicalRecordDTO> getMedicalRecord() {
		return medicalRecord;
	}
	public void setMedicalRecord(List<MedicalRecordDTO> medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
}
