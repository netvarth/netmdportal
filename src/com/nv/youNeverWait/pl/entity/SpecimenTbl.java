package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the specimen_tbl database table.
 * 
 */
@Entity
@Table(name="specimen_tbl")
public class SpecimenTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String name;

	@Column(unique=true, nullable=false)
	private int uid;

	@Column(nullable=false, length=45)
	private String unit;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date_time", nullable=false)
	private Date createdDateTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date_time", nullable=false)
	private Date updatedDateTime;
	
	//bi-directional many-to-one association to TestSpecimenTbl
	@OneToMany(mappedBy="specimenTbl")
	private List<TestSpecimenTbl> testSpecimenTbls;

	public SpecimenTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<TestSpecimenTbl> getTestSpecimenTbls() {
		return this.testSpecimenTbls;
	}

	public void setTestSpecimenTbls(List<TestSpecimenTbl> testSpecimenTbls) {
		this.testSpecimenTbls = testSpecimenTbls;
	}

	public Date getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public Date getUpdatedDateTime() {
		return this.updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	
	public TestSpecimenTbl addTestSpecimenTbl(TestSpecimenTbl testSpecimenTbl) {
		getTestSpecimenTbls().add(testSpecimenTbl);
		testSpecimenTbl.setSpecimenTbl(this);

		return testSpecimenTbl;
	}

	public TestSpecimenTbl removeTestSpecimenTbl(TestSpecimenTbl testSpecimenTbl) {
		getTestSpecimenTbls().remove(testSpecimenTbl);
		testSpecimenTbl.setSpecimenTbl(null);

		return testSpecimenTbl;
	}

}