package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the order_result_tbl database table.
 * 
 */
@Entity
@Table(name="order_result_tbl")
public class OrderResultTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="`created-date_time`", nullable=false)
	private Date created_dateTime;

	@Lob
	@Column(nullable=false)
	private String result;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date_time", nullable=false)
	private Date updatedDateTime;

	//bi-directional many-to-one association to OrderBranchTbl
	@ManyToOne
	@JoinColumn(name="order_id", nullable=false)
	private OrderBranchTbl orderBranchTbl;

	//bi-directional many-to-one association to LabBranchTbl
		@ManyToOne
		@JoinColumn(name="source_lab_branch_id", nullable=false)
		private LabBranchTbl labBranchTbl;

		//bi-directional many-to-one association to LabTbl
		@ManyToOne
		@JoinColumn(name="source_lab_id", nullable=false)
		private LabTbl labTbl;

		//bi-directional many-to-one association to LabBranchTbl
		@ManyToOne
		@JoinColumn(name="owner_branch_id", nullable=false)
		private LabBranchTbl ownerLabBranchTbl;

		@Column(name="test_uid", length=45)
		private String testUid;

	public OrderResultTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated_dateTime() {
		return this.created_dateTime;
	}

	public void setCreated_dateTime(Date created_dateTime) {
		this.created_dateTime = created_dateTime;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getUpdatedDateTime() {
		return this.updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public OrderBranchTbl getOrderBranchTbl() {
		return this.orderBranchTbl;
	}

	public void setOrderBranchTbl(OrderBranchTbl orderBranchTbl) {
		this.orderBranchTbl = orderBranchTbl;
	}

	public String getTestUid() {
		return testUid;
	}

	public void setTestUid(String testUid) {
		this.testUid = testUid;
	}

	public LabBranchTbl getLabBranchTbl() {
		return labBranchTbl;
	}

	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}

	public LabTbl getLabTbl() {
		return labTbl;
	}

	public void setLabTbl(LabTbl labTbl) {
		this.labTbl = labTbl;
	}

	public LabBranchTbl getOwnerLabBranchTbl() {
		return ownerLabBranchTbl;
	}

	public void setOwnerLabBranchTbl(LabBranchTbl ownerLabBranchTbl) {
		this.ownerLabBranchTbl = ownerLabBranchTbl;
	}

	

	

}