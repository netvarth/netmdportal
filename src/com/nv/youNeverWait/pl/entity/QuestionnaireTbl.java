package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the questionnaire_tbl database table.
 * 
 */
@Entity
@Table(name="questionnaire_tbl")
@NamedQuery(name="QuestionnaireTbl.findAll", query="SELECT q FROM QuestionnaireTbl q")
public class QuestionnaireTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Lob
	@Column(name="data_points")
	private String dataPoints;

	@Column(name="dept_id")
	private int deptId;

	@Lob
	private String questionnaire;

	private String uid;

	//bi-directional many-to-one association to AnswerStatTbl
	@OneToMany(mappedBy="questionnaireTbl")
	private List<AnswerStatTbl> answerStatTbls;

	//bi-directional many-to-one association to AnswerTbl
	@OneToMany(mappedBy="questionnaireTbl")
	private List<AnswerTbl> answerTbls;

	//bi-directional many-to-one association to OrganisationTbl
	@ManyToOne
	@JoinColumn(name="organisation_id")
	private OrganisationTbl organisationTbl;

	public QuestionnaireTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDataPoints() {
		return this.dataPoints;
	}

	public void setDataPoints(String dataPoints) {
		this.dataPoints = dataPoints;
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

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<AnswerStatTbl> getAnswerStatTbls() {
		return this.answerStatTbls;
	}

	public void setAnswerStatTbls(List<AnswerStatTbl> answerStatTbls) {
		this.answerStatTbls = answerStatTbls;
	}

	public AnswerStatTbl addAnswerStatTbl(AnswerStatTbl answerStatTbl) {
		getAnswerStatTbls().add(answerStatTbl);
		answerStatTbl.setQuestionnaireTbl(this);

		return answerStatTbl;
	}

	public AnswerStatTbl removeAnswerStatTbl(AnswerStatTbl answerStatTbl) {
		getAnswerStatTbls().remove(answerStatTbl);
		answerStatTbl.setQuestionnaireTbl(null);

		return answerStatTbl;
	}

	public List<AnswerTbl> getAnswerTbls() {
		return this.answerTbls;
	}

	public void setAnswerTbls(List<AnswerTbl> answerTbls) {
		this.answerTbls = answerTbls;
	}

	public AnswerTbl addAnswerTbl(AnswerTbl answerTbl) {
		getAnswerTbls().add(answerTbl);
		answerTbl.setQuestionnaireTbl(this);

		return answerTbl;
	}

	public AnswerTbl removeAnswerTbl(AnswerTbl answerTbl) {
		getAnswerTbls().remove(answerTbl);
		answerTbl.setQuestionnaireTbl(null);

		return answerTbl;
	}

	public OrganisationTbl getOrganisationTbl() {
		return this.organisationTbl;
	}

	public void setOrganisationTbl(OrganisationTbl organisationTbl) {
		this.organisationTbl = organisationTbl;
	}

}