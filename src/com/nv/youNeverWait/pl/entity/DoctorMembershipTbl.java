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
    private NetmdDoctorTbl netmdDoctorTbl;

    /**
     * 
     */
    public DoctorMembershipTbl() {
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
	 * @return the membership
	 */
	public String getMembership() {
		return membership;
	}

	/**
	 * @param membership the membership to set
	 */
	public void setMembership(String membership) {
		this.membership = membership;
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