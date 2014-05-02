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

	@Column(name="data_points")
	private String dataPoints;

	private String questionnaire;

	private String uid;

	//bi-directional many-to-one association to AnswerSetTbl
	@OneToMany(mappedBy="questionnaireTbl")
	private List<AnswerSetTbl> answerSetTbls;

	//bi-directional many-to-one association to AnswerStatTbl
	@OneToMany(mappedBy="questionnaireTbl")
	private List<AnswerStatTbl> answerStatTbls;

	//bi-directional many-to-one association to DataPointTbl
	@OneToMany(mappedBy="questionnaireTbl")
	private List<DataPointTbl> dataPointTbls;

	//bi-directional many-to-one association to QuestionTbl
	@OneToMany(mappedBy="questionnaireTbl")
	private List<QuestionTbl> questionTbls;

	//bi-directional many-to-one association to DepartmentTbl
	@ManyToOne
	@JoinColumn(name="dept_id")
	private DepartmentTbl departmentTbl;

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

	public List<AnswerSetTbl> getAnswerSetTbls() {
		return this.answerSetTbls;
	}

	public void setAnswerSetTbls(List<AnswerSetTbl> answerSetTbls) {
		this.answerSetTbls = answerSetTbls;
	}

	public AnswerSetTbl addAnswerSetTbl(AnswerSetTbl answerSetTbl) {
		getAnswerSetTbls().add(answerSetTbl);
		answerSetTbl.setQuestionnaireTbl(this);

		return answerSetTbl;
	}

	public AnswerSetTbl removeAnswerSetTbl(AnswerSetTbl answerSetTbl) {
		getAnswerSetTbls().remove(answerSetTbl);
		answerSetTbl.setQuestionnaireTbl(null);

		return answerSetTbl;
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

	public List<DataPointTbl> getDataPointTbls() {
		return this.dataPointTbls;
	}

	public void setDataPointTbls(List<DataPointTbl> dataPointTbls) {
		this.dataPointTbls = dataPointTbls;
	}

	public DataPointTbl addDataPointTbl(DataPointTbl dataPointTbl) {
		getDataPointTbls().add(dataPointTbl);
		

		return dataPointTbl;
	}

	public DataPointTbl removeDataPointTbl(DataPointTbl dataPointTbl) {
		getDataPointTbls().remove(dataPointTbl);
		

		return dataPointTbl;
	}

	public List<QuestionTbl> getQuestionTbls() {
		return this.questionTbls;
	}

	public void setQuestionTbls(List<QuestionTbl> questionTbls) {
		this.questionTbls = questionTbls;
	}

	public QuestionTbl addQuestionTbl(QuestionTbl questionTbl) {
		getQuestionTbls().add(questionTbl);
		questionTbl.setQuestionnaireTbl(this);

		return questionTbl;
	}

	public QuestionTbl removeQuestionTbl(QuestionTbl questionTbl) {
		getQuestionTbls().remove(questionTbl);
		questionTbl.setQuestionnaireTbl(null);

		return questionTbl;
	}

	public DepartmentTbl getDepartmentTbl() {
		return this.departmentTbl;
	}

	public void setDepartmentTbl(DepartmentTbl departmentTbl) {
		this.departmentTbl = departmentTbl;
	}

	public OrganisationTbl getOrganisationTbl() {
		return this.organisationTbl;
	}

	public void setOrganisationTbl(OrganisationTbl organisationTbl) {
		this.organisationTbl = organisationTbl;
	}

}