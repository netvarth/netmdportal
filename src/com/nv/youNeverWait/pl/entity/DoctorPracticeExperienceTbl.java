package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the doctor_practice_experience_tbl database table.
 * 
 */
@Entity
@Table(name="doctor_practice_experience_tbl")
public class DoctorPracticeExperienceTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=145)
	private String designation;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="from_date")
	private Date fromDate;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="to_date")
	private Date toDate;

	@Column(name="worked_at", length=145)
	private String workedAt;

	//bi-directional many-to-one association to DoctorTbl
    @ManyToOne
	@JoinColumn(name="doctor_id", nullable=false)
	private DoctorTbl doctorTbl;

    public DoctorPracticeExperienceTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getWorkedAt() {
		return this.workedAt;
	}

	public void setWorkedAt(String workedAt) {
		this.workedAt = workedAt;
	}

	public DoctorTbl getDoctorTbl() {
		return this.doctorTbl;
	}

	public void setDoctorTbl(DoctorTbl doctorTbl) {
		this.doctorTbl = doctorTbl;
	}
	
}