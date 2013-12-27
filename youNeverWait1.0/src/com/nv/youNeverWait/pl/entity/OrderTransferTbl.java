package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the order_transfer_tbl database table.
 * 
 */
@Entity
@Table(name="order_transfer_tbl")
public class OrderTransferTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date_time", nullable=false)
	private Date createdDateTime;

	@Lob
	@Column(name="order_details", nullable=false)
	private String orderDetails;


	@Column(name="order_uid", nullable=false, length=45)
	private String orderUid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date_time", nullable=false)
	private Date updatedDateTime;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="destination_branch_id", nullable=false)
	private LabBranchTbl destinationBranch;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="source_branch_id", nullable=false)
	private LabBranchTbl sourceBranch;

	//bi-directional many-to-one association to LabTbl
	@ManyToOne
	@JoinColumn(name="source_lab_id", nullable=false)
	private LabTbl sourceLab;

	//bi-directional many-to-one association to LabTbl
	@ManyToOne
	@JoinColumn(name="destination_lab_id", nullable=false)
	private LabTbl destinationLab;

	public OrderTransferTbl() {
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

	public String getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}

	
	/**
	 * @return the orderUid
	 */
	public String getOrderUid() {
		return orderUid;
	}

	/**
	 * @param orderUid the orderUid to set
	 */
	public void setOrderUid(String orderUid) {
		this.orderUid = orderUid;
	}

	public Date getUpdatedDateTime() {
		return this.updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	/**
	 * @return the destinationBranch
	 */
	public LabBranchTbl getDestinationBranch() {
		return destinationBranch;
	}

	/**
	 * @param destinationBranch the destinationBranch to set
	 */
	public void setDestinationBranch(LabBranchTbl destinationBranch) {
		this.destinationBranch = destinationBranch;
	}

	/**
	 * @return the sourceBranch
	 */
	public LabBranchTbl getSourceBranch() {
		return sourceBranch;
	}

	/**
	 * @param sourceBranch the sourceBranch to set
	 */
	public void setSourceBranch(LabBranchTbl sourceBranch) {
		this.sourceBranch = sourceBranch;
	}

	/**
	 * @return the sourceLab
	 */
	public LabTbl getSourceLab() {
		return sourceLab;
	}

	/**
	 * @param sourceLab the sourceLab to set
	 */
	public void setSourceLab(LabTbl sourceLab) {
		this.sourceLab = sourceLab;
	}

	/**
	 * @return the destinationLab
	 */
	public LabTbl getDestinationLab() {
		return destinationLab;
	}

	/**
	 * @param destinationLab the destinationLab to set
	 */
	public void setDestinationLab(LabTbl destinationLab) {
		this.destinationLab = destinationLab;
	}

	

	

}