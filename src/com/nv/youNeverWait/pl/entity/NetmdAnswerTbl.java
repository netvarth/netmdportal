package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the netmd_answer_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_answer_tbl")
public class NetmdAnswerTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=45)
	private String answer;

	//bi-directional many-to-one association to NetmdBranchTbl
	@ManyToOne
	@JoinColumn(name="netmd_branch_id", nullable=false)
	private NetmdBranchTbl netmdBranchTbl;

	//bi-directional many-to-one association to NetmdQuestionTbl
	@ManyToOne
	@JoinColumn(name="question_id", nullable=false)
	private NetmdQuestionTbl netmdQuestionTbl;

	//bi-directional many-to-one association to NetmdQuestionnaireTbl
	@ManyToOne
	@JoinColumn(name="questionnaire_id", nullable=false)
	private NetmdQuestionnaireTbl netmdQuestionnaireTbl;

	public NetmdAnswerTbl() {
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

	public NetmdBranchTbl getNetmdBranchTbl() {
		return this.netmdBranchTbl;
	}

	public void setNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		this.netmdBranchTbl = netmdBranchTbl;
	}

	public NetmdQuestionTbl getNetmdQuestionTbl() {
		return this.netmdQuestionTbl;
	}

	public void setNetmdQuestionTbl(NetmdQuestionTbl netmdQuestionTbl) {
		this.netmdQuestionTbl = netmdQuestionTbl;
	}

	public NetmdQuestionnaireTbl getNetmdQuestionnaireTbl() {
		return this.netmdQuestionnaireTbl;
	}

	public void setNetmdQuestionnaireTbl(NetmdQuestionnaireTbl netmdQuestionnaireTbl) {
		this.netmdQuestionnaireTbl = netmdQuestionnaireTbl;
	}

}