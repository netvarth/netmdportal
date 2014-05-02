package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the answer_stat_tbl database table.
 * 
 */
@Entity
@Table(name="answer_stat_tbl")
@NamedQuery(name="AnswerStatTbl.findAll", query="SELECT a FROM AnswerStatTbl a")
public class AnswerStatTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private float average;

	private float compute1;

	private float compute10;

	private float compute11;

	private float compute12;

	private float compute13;

	private float compute14;

	private float compute15;

	private float compute16;

	private float compute17;

	private float compute18;

	private float compute19;

	private float compute2;

	private float compute20;

	private float compute21;

	private float compute22;

	private float compute23;

	private float compute24;

	private float compute25;

	private float compute3;

	private float compute4;

	private float compute5;

	private float compute6;

	private float compute7;

	private float compute8;

	private float compute9;

	private int count;

	private String date;

	private float maximum;

	private float minimum;

	private int question1;

	private int question2;

	private int question3;

	private int question4;

	private int question5;

	//bi-directional many-to-one association to QuestionnaireTbl
	@ManyToOne
	@JoinColumn(name="questionnaire_id")
	private QuestionnaireTbl questionnaireTbl;

	//bi-directional many-to-one association to DataPointTbl
	@ManyToOne
	@JoinColumn(name="data_point")
	private DataPointTbl dataPointTbl;

	public AnswerStatTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAverage() {
		return this.average;
	}

	public void setAverage(float average) {
		this.average = average;
	}

	public float getCompute1() {
		return this.compute1;
	}

	public void setCompute1(float compute1) {
		this.compute1 = compute1;
	}

	public float getCompute10() {
		return this.compute10;
	}

	public void setCompute10(float compute10) {
		this.compute10 = compute10;
	}

	public float getCompute11() {
		return this.compute11;
	}

	public void setCompute11(float compute11) {
		this.compute11 = compute11;
	}

	public float getCompute12() {
		return this.compute12;
	}

	public void setCompute12(float compute12) {
		this.compute12 = compute12;
	}

	public float getCompute13() {
		return this.compute13;
	}

	public void setCompute13(float compute13) {
		this.compute13 = compute13;
	}

	public float getCompute14() {
		return this.compute14;
	}

	public void setCompute14(float compute14) {
		this.compute14 = compute14;
	}

	public float getCompute15() {
		return this.compute15;
	}

	public void setCompute15(float compute15) {
		this.compute15 = compute15;
	}

	public float getCompute16() {
		return this.compute16;
	}

	public void setCompute16(float compute16) {
		this.compute16 = compute16;
	}

	public float getCompute17() {
		return this.compute17;
	}

	public void setCompute17(float compute17) {
		this.compute17 = compute17;
	}

	public float getCompute18() {
		return this.compute18;
	}

	public void setCompute18(float compute18) {
		this.compute18 = compute18;
	}

	public float getCompute19() {
		return this.compute19;
	}

	public void setCompute19(float compute19) {
		this.compute19 = compute19;
	}

	public float getCompute2() {
		return this.compute2;
	}

	public void setCompute2(float compute2) {
		this.compute2 = compute2;
	}

	public float getCompute20() {
		return this.compute20;
	}

	public void setCompute20(float compute20) {
		this.compute20 = compute20;
	}

	public float getCompute21() {
		return this.compute21;
	}

	public void setCompute21(float compute21) {
		this.compute21 = compute21;
	}

	public float getCompute22() {
		return this.compute22;
	}

	public void setCompute22(float compute22) {
		this.compute22 = compute22;
	}

	public float getCompute23() {
		return this.compute23;
	}

	public void setCompute23(float compute23) {
		this.compute23 = compute23;
	}

	public float getCompute24() {
		return this.compute24;
	}

	public void setCompute24(float compute24) {
		this.compute24 = compute24;
	}

	public float getCompute25() {
		return this.compute25;
	}

	public void setCompute25(float compute25) {
		this.compute25 = compute25;
	}

	public float getCompute3() {
		return this.compute3;
	}

	public void setCompute3(float compute3) {
		this.compute3 = compute3;
	}

	public float getCompute4() {
		return this.compute4;
	}

	public void setCompute4(float compute4) {
		this.compute4 = compute4;
	}

	public float getCompute5() {
		return this.compute5;
	}

	public void setCompute5(float compute5) {
		this.compute5 = compute5;
	}

	public float getCompute6() {
		return this.compute6;
	}

	public void setCompute6(float compute6) {
		this.compute6 = compute6;
	}

	public float getCompute7() {
		return this.compute7;
	}

	public void setCompute7(float compute7) {
		this.compute7 = compute7;
	}

	public float getCompute8() {
		return this.compute8;
	}

	public void setCompute8(float compute8) {
		this.compute8 = compute8;
	}

	public float getCompute9() {
		return this.compute9;
	}

	public void setCompute9(float compute9) {
		this.compute9 = compute9;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getMaximum() {
		return this.maximum;
	}

	public void setMaximum(float maximum) {
		this.maximum = maximum;
	}

	public float getMinimum() {
		return this.minimum;
	}

	public void setMinimum(float minimum) {
		this.minimum = minimum;
	}

	public int getQuestion1() {
		return this.question1;
	}

	public void setQuestion1(int question1) {
		this.question1 = question1;
	}

	public int getQuestion2() {
		return this.question2;
	}

	public void setQuestion2(int question2) {
		this.question2 = question2;
	}

	public int getQuestion3() {
		return this.question3;
	}

	public void setQuestion3(int question3) {
		this.question3 = question3;
	}

	public int getQuestion4() {
		return this.question4;
	}

	public void setQuestion4(int question4) {
		this.question4 = question4;
	}

	public int getQuestion5() {
		return this.question5;
	}

	public void setQuestion5(int question5) {
		this.question5 = question5;
	}

	public QuestionnaireTbl getQuestionnaireTbl() {
		return this.questionnaireTbl;
	}

	public void setQuestionnaireTbl(QuestionnaireTbl questionnaireTbl) {
		this.questionnaireTbl = questionnaireTbl;
	}

	public DataPointTbl getDataPointTbl() {
		return this.dataPointTbl;
	}

	public void setDataPointTbl(DataPointTbl dataPointTbl) {
		this.dataPointTbl = dataPointTbl;
	}

}