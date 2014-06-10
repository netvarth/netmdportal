package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the doctor_educational_qualification_tbl database table.
 * 
 */
@Entity
@Table(name="doctor_educational_qualification_tbl")
public class DoctorEducationalQualificationTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="educational_degree", nullable=false, length=145)
	private String educationalDegree;


	private String institution;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="passed_out_date")
	private Date passedOutDate;

	//bi-directional many-to-one association to DoctorTbl
    @ManyToOne
	@JoinColumn(name="doctor_id", nullable=false)
	private DoctorTbl doctorTbl;

    public DoctorEducationalQualificationTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEducationalDegree() {
		return this.educationalDegree;
	}

	public void setEducationalDegree(String educationalDegree) {
		this.educationalDegree = educationalDegree;
	}

	public String getInstitution() {
		return this.institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public Date getPassedOutDate() {
		return this.passedOutDate;
	}

	public void setPassedOutDate(Date passedOutDate) {
		this.passedOutDate = passedOutDate;
	}

	public DoctorTbl getDoctorTbl() {
		return this.doctorTbl;
	}

	public void setDoctorTbl(DoctorTbl doctorTbl) {
		this.doctorTbl = doctorTbl;
	}
	
}