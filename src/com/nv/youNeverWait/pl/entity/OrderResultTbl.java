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
@NamedQuery(name="OrderResultTbl.findAll", query="SELECT o FROM OrderResultTbl o")
public class OrderResultTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date_time")
	private Date createdDateTime;

	@Lob
	private String result;

	@Column(name="sent")
	private boolean sent;

	@Column(name="test_uid", length=45)
	private String testUid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date_time")
	private Date updatedDateTime;

	//bi-directional many-to-one association to OrderBranchTbl
	@ManyToOne
	@JoinColumn(name="order_id", nullable=false)
	private OrderBranchTbl orderBranchTbl;

	//bi-directional many-to-one association to LabTbl
	@ManyToOne
	@JoinColumn(name="source_lab_id", nullable=false)
	private LabTbl labTbl;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="source_lab_branch_id", nullable=false)
	private LabBranchTbl labBranchTbl;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="owner_branch_id", nullable=false)
	private LabBranchTbl ownerLabBranchTbl;

	public OrderResultTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	
	public String getTestUid() {
		return this.testUid;
	}

	public void setTestUid(String testUid) {
		this.testUid = testUid;
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

	public LabTbl getLabTbl() {
		return this.labTbl;
	}

	public void setLabTbl(LabTbl labTbl) {
		this.labTbl = labTbl;
	}


	/**
	 * @return the ownerLabBranchTbl
	 */
	public LabBranchTbl getOwnerLabBranchTbl() {
		return ownerLabBranchTbl;
	}

	/**
	 * @param ownerLabBranchTbl the ownerLabBranchTbl to set
	 */
	public void setOwnerLabBranchTbl(LabBranchTbl ownerLabBranchTbl) {
		this.ownerLabBranchTbl = ownerLabBranchTbl;
	}

	/**
	 * @return the labBranchTbl
	 */
	public LabBranchTbl getLabBranchTbl() {
		return labBranchTbl;
	}

	/**
	 * @param labBranchTbl the labBranchTbl to set
	 */
	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}

	/**
	 * @return the sent
	 */
	public boolean isSent() {
		return sent;
	}

	/**
	 * @param sent the sent to set
	 */
	public void setSent(boolean sent) {
		this.sent = sent;
	}

	

}