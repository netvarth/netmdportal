package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the test_specimen_tbl database table.
 * 
 */
@Entity
@Table(name="test_specimen_tbl")
public class TestSpecimenTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private float quantity;

	//bi-directional many-to-one association to SpecimenTbl
	@ManyToOne
	@JoinColumn(name="specimen_uid", nullable=false)
	private SpecimenTbl specimenTbl;

	//bi-directional many-to-one association to TestTbl
	@ManyToOne
	@JoinColumn(name="test_uid", nullable=false)
	private TestTbl testTbl;

	public TestSpecimenTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getQuantity() {
		return this.quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public SpecimenTbl getSpecimenTbl() {
		return this.specimenTbl;
	}

	public void setSpecimenTbl(SpecimenTbl specimenTbl) {
		this.specimenTbl = specimenTbl;
	}

	public TestTbl getTestTbl() {
		return this.testTbl;
	}

	public void setTestTbl(TestTbl testTbl) {
		this.testTbl = testTbl;
	}

}