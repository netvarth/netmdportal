package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the doctor_expertise_tbl database table.
 * 
 */
@Entity
@Table(name="doctor_expertise_tbl")
public class DoctorExpertiseTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=100)
	private String expertise;

	//bi-directional many-to-one association to DoctorTbl
    @ManyToOne
	@JoinColumn(name="doctor_id", nullable=false)
    private NetmdDoctorTbl netmdDoctorTbl;

    /**
     * 
     */
    public DoctorExpertiseTbl() {
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
	 * @return the expertise
	 */
	public String getExpertise() {
		return expertise;
	}

	/**
	 * @param expertise the expertise to set
	 */
	public void setExpertise(String expertise) {
		this.expertise = expertise;
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


	
}