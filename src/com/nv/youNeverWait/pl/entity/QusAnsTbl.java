package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the qus_ans_tbl database table.
 * 
 */
@Entity
@Table(name="qus_ans_tbl")
public class QusAnsTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Lob
	@Column(nullable=false)
	private String answer;

	//bi-directional many-to-one association to CaseTbl
	@ManyToOne
	@JoinColumn(name="case_id", nullable=false)
	private CaseTbl caseTbl;

	//bi-directional many-to-one association to DeptTbl
	@ManyToOne
	@JoinColumn(name="dept_id", nullable=false)
	private DeptTbl deptTbl;

	//bi-directional many-to-one association to QuestionTbl
	@ManyToOne
	@JoinColumn(name="quest_id", nullable=false)
	private QuestionTbl questionTbl;

	public QusAnsTbl() {
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

	public DeptTbl getDeptTbl() {
		return this.deptTbl;
	}

	public void setDeptTbl(DeptTbl deptTbl) {
		this.deptTbl = deptTbl;
	}

	public QuestionTbl getQuestionTbl() {
		return this.questionTbl;
	}

	public void setQuestionTbl(QuestionTbl questionTbl) {
		this.questionTbl = questionTbl;
	}

}