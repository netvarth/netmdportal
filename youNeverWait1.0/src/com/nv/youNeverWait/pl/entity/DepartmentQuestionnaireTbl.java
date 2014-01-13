package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the department_questionnaire_tbl database table.
 * 
 */
@Entity
@Table(name="department_questionnaire_tbl")
public class DepartmentQuestionnaireTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(name="dept_id")
	private int deptId;

	@Lob
	private String questionnaire;

	public DepartmentQuestionnaireTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeptId() {
		return this.deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getQuestionnaire() {
		return this.questionnaire;
	}

	public void setQuestionnaire(String questionnaire) {
		this.questionnaire = questionnaire;
	}

}