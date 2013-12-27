package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dept_json_tbl database table.
 * 
 */
@Entity
@Table(name="dept_json_tbl")
public class DeptJsonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Lob
	@Column(nullable=false)
	private String questionnaire;

	//bi-directional many-to-one association to DeptTbl
	@ManyToOne
	@JoinColumn(name="dept_id", nullable=false)
	private DeptTbl deptTbl;

	public DeptJsonTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestionnaire() {
		return this.questionnaire;
	}

	public void setQuestionnaire(String questionnaire) {
		this.questionnaire = questionnaire;
	}

	public DeptTbl getDeptTbl() {
		return this.deptTbl;
	}

	public void setDeptTbl(DeptTbl deptTbl) {
		this.deptTbl = deptTbl;
	}

}