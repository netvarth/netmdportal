package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the department_tbl database table.
 * 
 */
@Entity
@Table(name="department_tbl")
public class DepartmentTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int id;

	private String description;

	private String name;

	private String status;

	//bi-directional many-to-one association to AnswerTbl
	@OneToMany(mappedBy="departmentTbl")
	private List<AnswerTbl> answerTbls;

	//bi-directional many-to-one association to QuestionTbl
	@OneToMany(mappedBy="departmentTbl")
	private List<QuestionTbl> questionTbls;

	public DepartmentTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AnswerTbl> getAnswerTbls() {
		return this.answerTbls;
	}

	public void setAnswerTbls(List<AnswerTbl> answerTbls) {
		this.answerTbls = answerTbls;
	}

	public AnswerTbl addAnswerTbl(AnswerTbl answerTbl) {
		getAnswerTbls().add(answerTbl);
		

		return answerTbl;
	}

	public AnswerTbl removeAnswerTbl(AnswerTbl answerTbl) {
		getAnswerTbls().remove(answerTbl);
		

		return answerTbl;
	}

	public List<QuestionTbl> getQuestionTbls() {
		return this.questionTbls;
	}

	public void setQuestionTbls(List<QuestionTbl> questionTbls) {
		this.questionTbls = questionTbls;
	}

	public QuestionTbl addQuestionTbl(QuestionTbl questionTbl) {
		getQuestionTbls().add(questionTbl);
		questionTbl.setDepartmentTbl(this);

		return questionTbl;
	}

	public QuestionTbl removeQuestionTbl(QuestionTbl questionTbl) {
		getQuestionTbls().remove(questionTbl);
		questionTbl.setDepartmentTbl(null);

		return questionTbl;
	}

}