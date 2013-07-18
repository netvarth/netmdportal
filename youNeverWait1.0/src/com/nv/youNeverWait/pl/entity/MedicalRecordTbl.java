package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the medical_record_tbl database table.
 * 
 */
@Entity
@Table(name="medical_record_tbl")
public class MedicalRecordTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date date;

	@Column(name="medical_record", length=200)
	private String medicalRecord;

	@Column(nullable=false, length=1)
	private String type;

	//bi-directional many-to-one association to CaseTbl
    @ManyToOne
	@JoinColumn(name="case_id", nullable=false)
	private CaseTbl caseTbl;

	//bi-directional many-to-one association to DoctorTbl
    @ManyToOne
	@JoinColumn(name="doctor_id", nullable=false)
	private DoctorTbl doctorTbl;

	//bi-directional many-to-one association to PatientTbl
    @ManyToOne
	@JoinColumn(name="patient_id", nullable=false)
	private PatientTbl patientTbl;

    public MedicalRecordTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMedicalRecord() {
		return this.medicalRecord;
	}

	public void setMedicalRecord(String medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CaseTbl getCaseTbl() {
		return this.caseTbl;
	}

	public void setCaseTbl(CaseTbl caseTbl) {
		this.caseTbl = caseTbl;
	}
	
	public DoctorTbl getDoctorTbl() {
		return this.doctorTbl;
	}

	public void setDoctorTbl(DoctorTbl doctorTbl) {
		this.doctorTbl = doctorTbl;
	}
	
	public PatientTbl getPatientTbl() {
		return this.patientTbl;
	}

	public void setPatientTbl(PatientTbl patientTbl) {
		this.patientTbl = patientTbl;
	}
	
}