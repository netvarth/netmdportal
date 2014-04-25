package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the answer_set_tbl database table.
 * 
 */
@Entity
@Table(name="answer_set_tbl")

public class AnswerSetTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="local_answer_set")
	private int localAnswerSet;

	@Column(name="questionnaire_id")
	private int questionnaireId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="s_date")
	private Date sDate;

	//bi-directional many-to-one association to OrganisationTbl
	@ManyToOne
	@JoinColumn(name="branch_id")
	private OrganisationTbl organisationTbl;

	public AnswerSetTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLocalAnswerSet() {
		return this.localAnswerSet;
	}

	public void setLocalAnswerSet(int localAnswerSet) {
		this.localAnswerSet = localAnswerSet;
	}

	public int getQuestionnaireId() {
		return this.questionnaireId;
	}

	public void setQuestionnaireId(int questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public Date getSDate() {
		return this.sDate;
	}

	public void setSDate(Date sDate) {
		this.sDate = sDate;
	}

	public OrganisationTbl getOrganisationTbl() {
		return this.organisationTbl;
	}

	public void setOrganisationTbl(OrganisationTbl organisationTbl) {
		this.organisationTbl = organisationTbl;
	}

}