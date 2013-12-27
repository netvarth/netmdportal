package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the question_tbl database table.
 * 
 */
@Entity
@Table(name="question_tbl")
public class QuestionTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Lob
	@Column(nullable=false)
	private String question;

	@Column(name="question_key", nullable=false, length=45)
	private String questionKey;

	//bi-directional many-to-one association to DeptTbl
	@ManyToOne
	@JoinColumn(name="dept_id", nullable=false)
	private DeptTbl deptTbl;

	//bi-directional many-to-one association to QusAnsTbl
	@OneToMany(mappedBy="questionTbl")
	private List<QusAnsTbl> qusAnsTbls;

	public QuestionTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestionKey() {
		return this.questionKey;
	}

	public void setQuestionKey(String questionKey) {
		this.questionKey = questionKey;
	}

	public DeptTbl getDeptTbl() {
		return this.deptTbl;
	}

	public void setDeptTbl(DeptTbl deptTbl) {
		this.deptTbl = deptTbl;
	}

	public List<QusAnsTbl> getQusAnsTbls() {
		return this.qusAnsTbls;
	}

	public void setQusAnsTbls(List<QusAnsTbl> qusAnsTbls) {
		this.qusAnsTbls = qusAnsTbls;
	}

	public QusAnsTbl addQusAnsTbl(QusAnsTbl qusAnsTbl) {
		getQusAnsTbls().add(qusAnsTbl);
		qusAnsTbl.setQuestionTbl(this);

		return qusAnsTbl;
	}

	public QusAnsTbl removeQusAnsTbl(QusAnsTbl qusAnsTbl) {
		getQusAnsTbls().remove(qusAnsTbl);
		qusAnsTbl.setQuestionTbl(null);

		return qusAnsTbl;
	}

}