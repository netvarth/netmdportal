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

	@Lob
	private String questionnaire;

	private String uid;

	//bi-directional many-to-one association to AnswerSetTbl
	@OneToMany(mappedBy="questionnaireTbl")
	private List<AnswerSetTbl> answerSetTbls;

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

	/**
	 * 
	 */
	public QuestionnaireTbl() {
	}


	/**
	 * @param answerSetTbl
	 * @return AnswerSetTbl
	 */
	public AnswerSetTbl addAnswerSetTbl(AnswerSetTbl answerSetTbl) {
		getAnswerSetTbls().add(answerSetTbl);
		answerSetTbl.setQuestionnaireTbl(this);

		return answerSetTbl;
	}

	/**
	 * @param answerSetTbl
	 * @return AnswerSetTbl
	 */
	public AnswerSetTbl removeAnswerSetTbl(AnswerSetTbl answerSetTbl) {
		getAnswerSetTbls().remove(answerSetTbl);
		answerSetTbl.setQuestionnaireTbl(null);

		return answerSetTbl;
	}

	/**
	 * @param questionTbl
	 * @return QuestionTbl
	 */
	public QuestionTbl addQuestionTbl(QuestionTbl questionTbl) {
		getQuestionTbls().add(questionTbl);
		questionTbl.setQuestionnaireTbl(this);

		return questionTbl;
	}

	/**
	 * @param questionTbl
	 * @return QuestionTbl
	 */
	public QuestionTbl removeQuestionTbl(QuestionTbl questionTbl) {
		getQuestionTbls().remove(questionTbl);
		questionTbl.setQuestionnaireTbl(null);

		return questionTbl;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the dataPoints
	 */
	public String getDataPoints() {
		return dataPoints;
	}


	/**
	 * @param dataPoints the dataPoints to set
	 */
	public void setDataPoints(String dataPoints) {
		this.dataPoints = dataPoints;
	}


	/**
	 * @return the questionnaire
	 */
	public String getQuestionnaire() {
		return questionnaire;
	}


	/**
	 * @param questionnaire the questionnaire to set
	 */
	public void setQuestionnaire(String questionnaire) {
		this.questionnaire = questionnaire;
	}


	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}


	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}


	/**
	 * @return the answerSetTbls
	 */
	public List<AnswerSetTbl> getAnswerSetTbls() {
		return answerSetTbls;
	}


	/**
	 * @param answerSetTbls the answerSetTbls to set
	 */
	public void setAnswerSetTbls(List<AnswerSetTbl> answerSetTbls) {
		this.answerSetTbls = answerSetTbls;
	}


	/**
	 * @return the questionTbls
	 */
	public List<QuestionTbl> getQuestionTbls() {
		return questionTbls;
	}


	/**
	 * @param questionTbls the questionTbls to set
	 */
	public void setQuestionTbls(List<QuestionTbl> questionTbls) {
		this.questionTbls = questionTbls;
	}


	/**
	 * @return the departmentTbl
	 */
	public DepartmentTbl getDepartmentTbl() {
		return departmentTbl;
	}


	/**
	 * @param departmentTbl the departmentTbl to set
	 */
	public void setDepartmentTbl(DepartmentTbl departmentTbl) {
		this.departmentTbl = departmentTbl;
	}


	/**
	 * @return the organisationTbl
	 */
	public OrganisationTbl getOrganisationTbl() {
		return organisationTbl;
	}


	/**
	 * @param organisationTbl the organisationTbl to set
	 */
	public void setOrganisationTbl(OrganisationTbl organisationTbl) {
		this.organisationTbl = organisationTbl;
	}

}