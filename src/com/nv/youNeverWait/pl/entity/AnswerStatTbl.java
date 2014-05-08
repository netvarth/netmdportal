package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="answer_stat_tbl")
@NamedQuery(name="AnswerStatTbl.findAll", query="SELECT a FROM AnswerStatTbl a")
public class AnswerStatTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private float average;

	private BigDecimal compute1;

	private BigDecimal compute10;

	private BigDecimal compute11;

	private BigDecimal compute12;

	private BigDecimal compute13;

	private BigDecimal compute14;

	private BigDecimal compute15;

	private BigDecimal compute16;

	private BigDecimal compute17;

	private BigDecimal compute18;

	private BigDecimal compute19;

	private BigDecimal compute2;

	private BigDecimal compute20;

	private BigDecimal compute21;

	private BigDecimal compute22;

	private BigDecimal compute23;

	private BigDecimal compute24;

	private BigDecimal compute25;

	private BigDecimal compute3;

	private BigDecimal compute4;

	private BigDecimal compute5;

	private BigDecimal compute6;

	private BigDecimal compute7;

	private BigDecimal compute8;

	private BigDecimal compute9;

	private int count;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private float maximum;

	private float minimum;

	private int question1;

	private int question2;

	private int question3;

	private int question4;

	private int question5;

	//bi-directional many-to-one association to DataPointTbl
	@ManyToOne
	@JoinColumn(name="data_point")
	private DataPointTbl dataPointTbl;

	//bi-directional many-to-one association to HealthCareOrganisationTbl
	@ManyToOne
	@JoinColumn(name="hospital")
	private HealthCareOrganisationTbl healthCareOrganisationTbl;

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

	public BigDecimal getCompute1() {
		return this.compute1;
	}

	public void setCompute1(BigDecimal compute1) {
		this.compute1 = compute1;
	}

	public BigDecimal getCompute10() {
		return this.compute10;
	}

	public void setCompute10(BigDecimal compute10) {
		this.compute10 = compute10;
	}

	public BigDecimal getCompute11() {
		return this.compute11;
	}

	public void setCompute11(BigDecimal compute11) {
		this.compute11 = compute11;
	}

	public BigDecimal getCompute12() {
		return this.compute12;
	}

	public void setCompute12(BigDecimal compute12) {
		this.compute12 = compute12;
	}

	public BigDecimal getCompute13() {
		return this.compute13;
	}

	public void setCompute13(BigDecimal compute13) {
		this.compute13 = compute13;
	}

	public BigDecimal getCompute14() {
		return this.compute14;
	}

	public void setCompute14(BigDecimal compute14) {
		this.compute14 = compute14;
	}

	public BigDecimal getCompute15() {
		return this.compute15;
	}

	public void setCompute15(BigDecimal compute15) {
		this.compute15 = compute15;
	}

	public BigDecimal getCompute16() {
		return this.compute16;
	}

	public void setCompute16(BigDecimal compute16) {
		this.compute16 = compute16;
	}

	public BigDecimal getCompute17() {
		return this.compute17;
	}

	public void setCompute17(BigDecimal compute17) {
		this.compute17 = compute17;
	}

	public BigDecimal getCompute18() {
		return this.compute18;
	}

	public void setCompute18(BigDecimal compute18) {
		this.compute18 = compute18;
	}

	public BigDecimal getCompute19() {
		return this.compute19;
	}

	public void setCompute19(BigDecimal compute19) {
		this.compute19 = compute19;
	}

	public BigDecimal getCompute2() {
		return this.compute2;
	}

	public void setCompute2(BigDecimal compute2) {
		this.compute2 = compute2;
	}

	public BigDecimal getCompute20() {
		return this.compute20;
	}

	public void setCompute20(BigDecimal compute20) {
		this.compute20 = compute20;
	}

	public BigDecimal getCompute21() {
		return this.compute21;
	}

	public void setCompute21(BigDecimal compute21) {
		this.compute21 = compute21;
	}

	public BigDecimal getCompute22() {
		return this.compute22;
	}

	public void setCompute22(BigDecimal compute22) {
		this.compute22 = compute22;
	}

	public BigDecimal getCompute23() {
		return this.compute23;
	}

	public void setCompute23(BigDecimal compute23) {
		this.compute23 = compute23;
	}

	public BigDecimal getCompute24() {
		return this.compute24;
	}

	public void setCompute24(BigDecimal compute24) {
		this.compute24 = compute24;
	}

	public BigDecimal getCompute25() {
		return this.compute25;
	}

	public void setCompute25(BigDecimal compute25) {
		this.compute25 = compute25;
	}

	public BigDecimal getCompute3() {
		return this.compute3;
	}

	public void setCompute3(BigDecimal compute3) {
		this.compute3 = compute3;
	}

	public BigDecimal getCompute4() {
		return this.compute4;
	}

	public void setCompute4(BigDecimal compute4) {
		this.compute4 = compute4;
	}

	public BigDecimal getCompute5() {
		return this.compute5;
	}

	public void setCompute5(BigDecimal compute5) {
		this.compute5 = compute5;
	}

	public BigDecimal getCompute6() {
		return this.compute6;
	}

	public void setCompute6(BigDecimal compute6) {
		this.compute6 = compute6;
	}

	public BigDecimal getCompute7() {
		return this.compute7;
	}

	public void setCompute7(BigDecimal compute7) {
		this.compute7 = compute7;
	}

	public BigDecimal getCompute8() {
		return this.compute8;
	}

	public void setCompute8(BigDecimal compute8) {
		this.compute8 = compute8;
	}

	public BigDecimal getCompute9() {
		return this.compute9;
	}

	public void setCompute9(BigDecimal compute9) {
		this.compute9 = compute9;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
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

	public DataPointTbl getDataPointTbl() {
		return this.dataPointTbl;
	}

	public void setDataPointTbl(DataPointTbl dataPointTbl) {
		this.dataPointTbl = dataPointTbl;
	}

	public HealthCareOrganisationTbl getHealthCareOrganisationTbl() {
		return this.healthCareOrganisationTbl;
	}

	public void setHealthCareOrganisationTbl(HealthCareOrganisationTbl healthCareOrganisationTbl) {
		this.healthCareOrganisationTbl = healthCareOrganisationTbl;
	}

}