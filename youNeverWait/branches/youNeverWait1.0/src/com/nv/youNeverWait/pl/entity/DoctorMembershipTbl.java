package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the doctor_membership_tbl database table.
 * 
 */
@Entity
@Table(name="doctor_membership_tbl")
public class DoctorMembershipTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=100)
	private String membership;

	//bi-directional many-to-one association to DoctorTbl
    @ManyToOne
	@JoinColumn(name="doctor_id", nullable=false)
	private DoctorTbl doctorTbl;

    public DoctorMembershipTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMembership() {
		return this.membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	public DoctorTbl getDoctorTbl() {
		return this.doctorTbl;
	}

	public void setDoctorTbl(DoctorTbl doctorTbl) {
		this.doctorTbl = doctorTbl;
	}
	
}