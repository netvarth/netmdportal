package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the case_tbl database table.
 * 
 */
@Entity
@Table(name="case_tbl")
public class CaseTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="admitted_date")
	private Date admittedDate;

	private float bmi;

	@Column(name="case_name")
	private String caseName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date_time")
	private Date createdDateTime;

	private String description;

	private float hb;

	private float height;

	@Column(name="local_answerset_id")
	private int localAnswersetId;

	@Column(name="local_case_id")
	private int localCaseId;

	@Column(name="patient_type")
	private String patientType;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date_time")
	private Date updatedDateTime;

	private float weight;

	//bi-directional many-to-one association to NetmdPatientTbl
	@ManyToOne
	@JoinColumn(name="patient_id")
	private NetmdPatientTbl netmdPatientTbl;

	//bi-directional many-to-one association to DepartmentTbl
	@ManyToOne
	@JoinColumn(name="department_id")
	private DepartmentTbl departmentTbl;

	//bi-directional many-to-one association to MedicalRecordTbl
	@OneToMany(mappedBy="caseTbl")
	private List<MedicalRecordTbl> medicalRecordTbls;

	public CaseTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAdmittedDate() {
		return this.admittedDate;
	}

	public void setAdmittedDate(Date admittedDate) {
		this.admittedDate = admittedDate;
	}

	public float getBmi() {
		return this.bmi;
	}

	public void setBmi(float bmi) {
		this.bmi = bmi;
	}

	public String getCaseName() {
		return this.caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public Date getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getHb() {
		return this.hb;
	}

	public void setHb(float hb) {
		this.hb = hb;
	}

	public float getHeight() {
		return this.height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getLocalAnswersetId() {
		return this.localAnswersetId;
	}

	public void setLocalAnswersetId(int localAnswersetId) {
		this.localAnswersetId = localAnswersetId;
	}

	public int getLocalCaseId() {
		return this.localCaseId;
	}

	public void setLocalCaseId(int localCaseId) {
		this.localCaseId = localCaseId;
	}

	public String getPatientType() {
		return this.patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedDateTime() {
		return this.updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public float getWeight() {
		return this.weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public NetmdPatientTbl getNetmdPatientTbl() {
		return this.netmdPatientTbl;
	}

	public void setNetmdPatientTbl(NetmdPatientTbl netmdPatientTbl) {
		this.netmdPatientTbl = netmdPatientTbl;
	}

	public DepartmentTbl getDepartmentTbl() {
		return this.departmentTbl;
	}

	public void setDepartmentTbl(DepartmentTbl departmentTbl) {
		this.departmentTbl = departmentTbl;
	}

	public List<MedicalRecordTbl> getMedicalRecordTbls() {
		return this.medicalRecordTbls;
	}

	public void setMedicalRecordTbls(List<MedicalRecordTbl> medicalRecordTbls) {
		this.medicalRecordTbls = medicalRecordTbls;
	}

	public MedicalRecordTbl addMedicalRecordTbl(MedicalRecordTbl medicalRecordTbl) {
		getMedicalRecordTbls().add(medicalRecordTbl);
		medicalRecordTbl.setCaseTbl(this);

		return medicalRecordTbl;
	}

	public MedicalRecordTbl removeMedicalRecordTbl(MedicalRecordTbl medicalRecordTbl) {
		getMedicalRecordTbls().remove(medicalRecordTbl);
		medicalRecordTbl.setCaseTbl(null);

		return medicalRecordTbl;
	}

}