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
    private NetmdDoctorTbl netmdDoctorTbl;

    /**
     * 
     */
    public DoctorAchievementTbl() {
    }

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the achievement
	 */
	public String getAchievement() {
		return this.achievement;
	}

	/**
	 * @param achievement the achievement to set
	 */
	public void setAchievement(String achievement) {
		this.achievement = achievement;
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