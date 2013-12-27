package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dept_tbl database table.
 * 
 */
@Entity
@Table(name="dept_tbl")
public class DeptTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="dept_name", nullable=false, length=45)
	private String deptName;

	//bi-directional many-to-one association to DeptJsonTbl
	@OneToMany(mappedBy="deptTbl")
	private List<DeptJsonTbl> deptJsonTbls;

	//bi-directional many-to-one association to QuestionTbl
	@OneToMany(mappedBy="deptTbl")
	private List<QuestionTbl> questionTbls;

	//bi-directional many-to-one association to QusAnsTbl
	@OneToMany(mappedBy="deptTbl")
	private List<QusAnsTbl> qusAnsTbls;

	public DeptTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<DeptJsonTbl> getDeptJsonTbls() {
		return this.deptJsonTbls;
	}

	public void setDeptJsonTbls(List<DeptJsonTbl> deptJsonTbls) {
		this.deptJsonTbls = deptJsonTbls;
	}

	public DeptJsonTbl addDeptJsonTbl(DeptJsonTbl deptJsonTbl) {
		getDeptJsonTbls().add(deptJsonTbl);
		deptJsonTbl.setDeptTbl(this);

		return deptJsonTbl;
	}

	public DeptJsonTbl removeDeptJsonTbl(DeptJsonTbl deptJsonTbl) {
		getDeptJsonTbls().remove(deptJsonTbl);
		deptJsonTbl.setDeptTbl(null);

		return deptJsonTbl;
	}

	public List<QuestionTbl> getQuestionTbls() {
		return this.questionTbls;
	}

	public void setQuestionTbls(List<QuestionTbl> questionTbls) {
		this.questionTbls = questionTbls;
	}

	public QuestionTbl addQuestionTbl(QuestionTbl questionTbl) {
		getQuestionTbls().add(questionTbl);
		questionTbl.setDeptTbl(this);

		return questionTbl;
	}

	public QuestionTbl removeQuestionTbl(QuestionTbl questionTbl) {
		getQuestionTbls().remove(questionTbl);
		questionTbl.setDeptTbl(null);

		return questionTbl;
	}

	public List<QusAnsTbl> getQusAnsTbls() {
		return this.qusAnsTbls;
	}

	public void setQusAnsTbls(List<QusAnsTbl> qusAnsTbls) {
		this.qusAnsTbls = qusAnsTbls;
	}

	public QusAnsTbl addQusAnsTbl(QusAnsTbl qusAnsTbl) {
		getQusAnsTbls().add(qusAnsTbl);
		qusAnsTbl.setDeptTbl(this);

		return qusAnsTbl;
	}

	public QusAnsTbl removeQusAnsTbl(QusAnsTbl qusAnsTbl) {
		getQusAnsTbls().remove(qusAnsTbl);
		qusAnsTbl.setDeptTbl(null);

		return qusAnsTbl;
	}

}