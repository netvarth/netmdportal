package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the answer_tbl database table.
 * 
 */
@Entity
@Table(name="answer_tbl")
@NamedQuery(name="AnswerTbl.findAll", query="SELECT a FROM AnswerTbl a")
public class AnswerTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String answer;

	//bi-directional many-to-one association to AnswerSetTbl
	@ManyToOne
	@JoinColumn(name="answer_set_id")
	private AnswerSetTbl answerSetTbl;

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

	public AnswerSetTbl getAnswerSetTbl() {
		return this.answerSetTbl;
	}

	public void setAnswerSetTbl(AnswerSetTbl answerSetTbl) {
		this.answerSetTbl = answerSetTbl;
	}

	public QuestionTbl getQuestionTbl() {
		return this.questionTbl;
	}

	public void setQuestionTbl(QuestionTbl questionTbl) {
		this.questionTbl = questionTbl;
	}

}