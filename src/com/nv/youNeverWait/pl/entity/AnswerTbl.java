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

	@Column(name="answer_set_id")
	private int answerSetId;

	@Column(name="netmd_branch_id")
	private int netmdBranchId;

	//bi-directional many-to-one association to QuestionTbl
	@ManyToOne
	@JoinColumn(name="quest_id")
	private QuestionTbl questionTbl;

	//bi-directional many-to-one association to QuestionnaireTbl
	@ManyToOne
	@JoinColumn(name="questionnare_id")
	private QuestionnaireTbl questionnaireTbl;

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

	public int getAnswerSetId() {
		return this.answerSetId;
	}

	public void setAnswerSetId(int answerSetId) {
		this.answerSetId = answerSetId;
	}

	public int getNetmdBranchId() {
		return this.netmdBranchId;
	}

	public void setNetmdBranchId(int netmdBranchId) {
		this.netmdBranchId = netmdBranchId;
	}

	public QuestionTbl getQuestionTbl() {
		return this.questionTbl;
	}

	public void setQuestionTbl(QuestionTbl questionTbl) {
		this.questionTbl = questionTbl;
	}

	public QuestionnaireTbl getQuestionnaireTbl() {
		return this.questionnaireTbl;
	}

	public void setQuestionnaireTbl(QuestionnaireTbl questionnaireTbl) {
		this.questionnaireTbl = questionnaireTbl;
	}

}