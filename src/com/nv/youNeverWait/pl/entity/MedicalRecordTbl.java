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


	@Column(name="medical_record", length=200)
	private String medicalRecord;

	@Column(name="type",length=200)
	private String type;

	//bi-directional many-to-one association to CaseTbl
    @ManyToOne
	@JoinColumn(name="case_id", nullable=false)
	private CaseTbl caseTbl;

	//bi-directional many-to-one association to DoctorTbl
    @ManyToOne
	@JoinColumn(name="doctor_id", nullable=false)
    private NetmdDoctorTbl netmdDoctorTbl;

	//bi-directional many-to-one association to PatientTbl
    @ManyToOne
	@JoinColumn(name="patient_id", nullable=false)
    private NetmdPatientTbl netmdPatientTbl;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date_time")
	private Date updateDateTime;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date_time")
	private Date createdDateTime;
   
	@Column(name="status", length=200)
	private String status;
	
    /**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 */
	public MedicalRecordTbl() {
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
	 * @return the medicalRecord
	 */
	public String getMedicalRecord() {
		return medicalRecord;
	}

	/**
	 * @param medicalRecord the medicalRecord to set
	 */
	public void setMedicalRecord(String medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the caseTbl
	 */
	public CaseTbl getCaseTbl() {
		return caseTbl;
	}

	/**
	 * @param caseTbl the caseTbl to set
	 */
	public void setCaseTbl(CaseTbl caseTbl) {
		this.caseTbl = caseTbl;
	}

	/**
	 * @return the netmdDoctorTbl
	 */
	public NetmdDoctorTbl getNetmdDoctorTbl() {
		return netmdDoctorTbl;
	}

	/**
	 * @param netmdDoctorTbl the netmdDoctorTbl to set
	 */
	public void setNetmdDoctorTbl(NetmdDoctorTbl netmdDoctorTbl) {
		this.netmdDoctorTbl = netmdDoctorTbl;
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

	/**
	 * @return the updateDateTime
	 */
	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	/**
	 * @param updateDateTime the updateDateTime to set
	 */
	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	/**
	 * @return the createdDateTime
	 */
	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	/**
	 * @param createdDateTime the createdDateTime to set
	 */
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	
}