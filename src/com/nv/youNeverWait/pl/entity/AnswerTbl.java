package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the answer_tbl database table.
 * 
 */
@Entity
@Table(name="answer_tbl")
public class AnswerTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String answer;

	//bi-directional many-to-one association to CaseTbl
	@ManyToOne
	@JoinColumn(name="case_id")
	private CaseTbl caseTbl;

	//bi-directional many-to-one association to DepartmentTbl
	@ManyToOne
	@JoinColumn(name="dept_id")
	private DepartmentTbl departmentTbl;

	//bi-directional many-to-one association to QuestionTbl
	@ManyToOne
	@JoinColumn(name="quest_id")
	private QuestionTbl questionTbl;

	public AnswerTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public CaseTbl getCaseTbl() {
		return this.caseTbl;
	}

	public void setCaseTbl(CaseTbl caseTbl) {
		this.caseTbl = caseTbl;
	}

	public DepartmentTbl getDepartmentTbl() {
		return this.departmentTbl;
	}

	public void setDepartmentTbl(DepartmentTbl departmentTbl) {
		this.departmentTbl = departmentTbl;
	}

	public QuestionTbl getQuestionTbl() {
		return this.questionTbl;
	}

	public void setQuestionTbl(QuestionTbl questionTbl) {
		this.questionTbl = questionTbl;
	}

}