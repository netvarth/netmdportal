package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the test_tbl database table.
 * 
 */
@Entity
@Table(name="test_tbl")
public class TestTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;
	
	@Column(nullable=false, length=45)
	private String abbreviation;

	@Column(nullable=false)
	private boolean active;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date_time", nullable=false)
	private Date createdDateTime;

	@Column(name="generic_name", length=45)
	private String genericName;

	@Column(name="informatic_values", length=75)
	private String informaticValues;

	@Column(name="machineentry_status")
	private boolean machineentryStatus;

	@Column(name="min_time_required", length=45)
	private String minTimeRequired;

	@Lob
	@Column(name="normal_range")
	private String normalRange;

	@Lob
	private String remarks;

	@Lob
	private String result;

	@Column(name="result_type", length=75)
	private String resultType;

	@Column(length=45)
	private String schedule;

	@Column(name="specimenentry_status")
	private boolean specimenentryStatus;

	@Column(name="test_name", nullable=false, length=45)
	private String testName;

	@Column(unique=true, nullable=false)
	private int uid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date_time", nullable=false)
	private Date updatedDateTime;

	@Column(name="upload_status", nullable=false)
	private boolean uploadStatus;

	//bi-directional many-to-one association to TestSpecimenTbl
	@OneToMany(mappedBy="testTbl")
	private List<TestSpecimenTbl> testSpecimenTbls;

	public TestTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Date getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getGenericName() {
		return this.genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public String getInformaticValues() {
		return this.informaticValues;
	}

	public void setInformaticValues(String informaticValues) {
		this.informaticValues = informaticValues;
	}

	public String getMinTimeRequired() {
		return this.minTimeRequired;
	}

	public void setMinTimeRequired(String minTimeRequired) {
		this.minTimeRequired = minTimeRequired;
	}

	public String getNormalRange() {
		return this.normalRange;
	}

	public void setNormalRange(String normalRange) {
		this.normalRange = normalRange;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultType() {
		return this.resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getSchedule() {
		return this.schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getTestName() {
		return this.testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Date getUpdatedDateTime() {
		return this.updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	public List<TestSpecimenTbl> getTestSpecimenTbls() {
		return this.testSpecimenTbls;
	}

	public void setTestSpecimenTbls(List<TestSpecimenTbl> testSpecimenTbls) {
		this.testSpecimenTbls = testSpecimenTbls;
	}

	public TestSpecimenTbl addTestSpecimenTbl(TestSpecimenTbl testSpecimenTbl) {
		getTestSpecimenTbls().add(testSpecimenTbl);
		testSpecimenTbl.setTestTbl(this);

		return testSpecimenTbl;
	}

	public TestSpecimenTbl removeTestSpecimenTbl(TestSpecimenTbl testSpecimenTbl) {
		getTestSpecimenTbls().remove(testSpecimenTbl);
		testSpecimenTbl.setTestTbl(null);

		return testSpecimenTbl;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the specimenentryStatus
	 */
	public boolean isSpecimenentryStatus() {
		return specimenentryStatus;
	}

	/**
	 * @param specimenentryStatus the specimenentryStatus to set
	 */
	public void setSpecimenentryStatus(boolean specimenentryStatus) {
		this.specimenentryStatus = specimenentryStatus;
	}

	/**
	 * @param uploadStatus the uploadStatus to set
	 */
	public void setUploadStatus(boolean uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	/**
	 * @return the machineentryStatus
	 */
	public boolean isMachineentryStatus() {
		return machineentryStatus;
	}

	/**
	 * @param machineentryStatus the machineentryStatus to set
	 */
	public void setMachineentryStatus(boolean machineentryStatus) {
		this.machineentryStatus = machineentryStatus;
	}

	/**
	 * @return the uploadStatus
	 */
	public boolean isUploadStatus() {
		return uploadStatus;
	}

}