package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the doctor_achievement_tbl database table.
 * 
 */
@Entity
@Table(name="doctor_achievement_tbl")
public class DoctorAchievementTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=100)
	private String achievement;

	//bi-directional many-to-one association to DoctorTbl
    @ManyToOne
	@JoinColumn(name="doctor_id", nullable=false)
	private DoctorTbl doctorTbl;

    public DoctorAchievementTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAchievement() {
		return this.achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public DoctorTbl getDoctorTbl() {
		return this.doctorTbl;
	}

	public void setDoctorTbl(DoctorTbl doctorTbl) {
		this.doctorTbl = doctorTbl;
	}
	
}