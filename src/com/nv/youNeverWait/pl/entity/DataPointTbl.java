package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the data_point_tbl database table.
 * 
 */
@Entity
@Table(name="data_point_tbl")
@NamedQuery(name="DataPointTbl.findAll", query="SELECT d FROM DataPointTbl d")
public class DataPointTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="cluster_xml")
	private String clusterXml;

	private String name;

	private int order;

	private int questionnaire;

	private String uid;

	//bi-directional many-to-one association to AnswerStatTbl
	@OneToMany(mappedBy="dataPointTbl")
	private List<AnswerStatTbl> answerStatTbls;

	/**
	 * 
	 */
	public DataPointTbl() {
	}


	/**
	 * @param answerStatTbl
	 * @return AnswerStatTbl
	 */
	public AnswerStatTbl addAnswerStatTbl(AnswerStatTbl answerStatTbl) {
		getAnswerStatTbls().add(answerStatTbl);
		answerStatTbl.setDataPointTbl(this);

		return answerStatTbl;
	}

	/**
	 * @param answerStatTbl
	 * @return AnswerStatTbl
	 */
	public AnswerStatTbl removeAnswerStatTbl(AnswerStatTbl answerStatTbl) {
		getAnswerStatTbls().remove(answerStatTbl);
		answerStatTbl.setDataPointTbl(null);

		return answerStatTbl;
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
	 * @return the clusterXml
	 */
	public String getClusterXml() {
		return clusterXml;
	}



	/**
	 * @param clusterXml the clusterXml to set
	 */
	public void setClusterXml(String clusterXml) {
		this.clusterXml = clusterXml;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}



	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}



	/**
	 * @return the questionnaire
	 */
	public int getQuestionnaire() {
		return questionnaire;
	}



	/**
	 * @param questionnaire the questionnaire to set
	 */
	public void setQuestionnaire(int questionnaire) {
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
	 * @return the answerStatTbls
	 */
	public List<AnswerStatTbl> getAnswerStatTbls() {
		return answerStatTbls;
	}



	/**
	 * @param answerStatTbls the answerStatTbls to set
	 */
	public void setAnswerStatTbls(List<AnswerStatTbl> answerStatTbls) {
		this.answerStatTbls = answerStatTbls;
	}

}