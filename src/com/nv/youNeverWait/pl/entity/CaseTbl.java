package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


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
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="case_name", length=45)
	private String caseName;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date date;

	@Column(name="long_desc", length=150)
	private String longDesc;

	@Column(name="short_desc", length=45)
	private String shortDesc;

	@Column(nullable=false, length=1)
	private String status;

	//bi-directional many-to-one association to PatientTbl
    @ManyToOne
	@JoinColumn(name="patient_id", nullable=false)
	private PatientTbl patientTbl;

	//bi-directional many-to-one association to MedicalRecordTbl
	@OneToMany(mappedBy="caseTbl")
	private Set<MedicalRecordTbl> medicalRecordTbls;

    public CaseTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCaseName() {
		return this.caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLongDesc() {
		return this.longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getShortDesc() {
		return this.shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PatientTbl getPatientTbl() {
		return this.patientTbl;
	}

	public void setPatientTbl(PatientTbl patientTbl) {
		this.patientTbl = patientTbl;
	}
	
	public Set<MedicalRecordTbl> getMedicalRecordTbls() {
		return this.medicalRecordTbls;
	}

	public void setMedicalRecordTbls(Set<MedicalRecordTbl> medicalRecordTbls) {
		this.medicalRecordTbls = medicalRecordTbls;
	}
	
}