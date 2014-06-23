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
    private NetmdDoctorTbl netmdDoctorTbl;

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
	 * @return the educationalDegree
	 */
	public String getEducationalDegree() {
		return educationalDegree;
	}

	/**
	 * @param educationalDegree the educationalDegree to set
	 */
	public void setEducationalDegree(String educationalDegree) {
		this.educationalDegree = educationalDegree;
	}

	/**
	 * @return the institution
	 */
	public String getInstitution() {
		return institution;
	}

	/**
	 * @param institution the institution to set
	 */
	public void setInstitution(String institution) {
		this.institution = institution;
	}

	/**
	 * @return the passedOutDate
	 */
	public Date getPassedOutDate() {
		return passedOutDate;
	}

	/**
	 * @param passedOutDate the passedOutDate to set
	 */
	public void setPassedOutDate(Date passedOutDate) {
		this.passedOutDate = passedOutDate;
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
	 * 
	 */
	public DoctorEducationalQualificationTbl() {
		super();
	}


	
	
}