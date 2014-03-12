package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the netmd_question_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_question_tbl")
public class NetmdQuestionTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String question;

	@Column(name="question_key", nullable=false, length=45)
	private String questionKey;

	//bi-directional many-to-one association to NetmdAnswerTbl
	@OneToMany(mappedBy="netmdQuestionTbl")
	private Set<NetmdAnswerTbl> netmdAnswerTbls;

	public NetmdQuestionTbl() {
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

	public Set<NetmdAnswerTbl> getNetmdAnswerTbls() {
		return this.netmdAnswerTbls;
	}

	public void setNetmdAnswerTbls(Set<NetmdAnswerTbl> netmdAnswerTbls) {
		this.netmdAnswerTbls = netmdAnswerTbls;
	}

//	public NetmdAnswerTbl addNetmdAnswerTbl(NetmdAnswerTbl netmdAnswerTbl) {
//		getNetmdAnswerTbls().add(netmdAnswerTbl);
//		netmdAnswerTbl.setNetmdQuestionTbl(this);
//
//		return netmdAnswerTbl;
//	}
//
//	public NetmdAnswerTbl removeNetmdAnswerTbl(NetmdAnswerTbl netmdAnswerTbl) {
//		getNetmdAnswerTbls().remove(netmdAnswerTbl);
//		netmdAnswerTbl.setNetmdQuestionTbl(null);
//
//		return netmdAnswerTbl;
//	}

}