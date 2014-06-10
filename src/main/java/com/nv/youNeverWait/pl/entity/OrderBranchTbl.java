package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the order_branch_tbl database table.
 * 
 */
@Entity
@Table(name="order_branch_tbl")
public class OrderBranchTbl implements Serializable {
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
	@JoinColumn(name="source_branch_id", nullable=false)
	private LabBranchTbl labBranchTbl;

	//bi-directional many-to-one association to LabTbl
	@ManyToOne
	@JoinColumn(name="source_lab_id", nullable=false)
	private LabTbl labTbl;

	//bi-directional many-to-one association to OrderTransferTbl
	@OneToMany(mappedBy="orderBranchTbl")
	private Set<OrderTransferTbl> orderTransferTbls;

	public OrderBranchTbl() {
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

	public String getOrderUid() {
		return this.orderUid;
	}

	public void setOrderUid(String orderUid) {
		this.orderUid = orderUid;
	}

	public Date getUpdatedDateTime() {
		return this.updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public LabBranchTbl getLabBranchTbl() {
		return this.labBranchTbl;
	}

	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}

	public LabTbl getLabTbl() {
		return this.labTbl;
	}

	public void setLabTbl(LabTbl labTbl) {
		this.labTbl = labTbl;
	}

	public Set<OrderTransferTbl> getOrderTransferTbls() {
		return this.orderTransferTbls;
	}

	public void setOrderTransferTbls(Set<OrderTransferTbl> orderTransferTbls) {
		this.orderTransferTbls = orderTransferTbls;
	}

	public OrderTransferTbl addOrderTransferTbl(OrderTransferTbl orderTransferTbl) {
		getOrderTransferTbls().add(orderTransferTbl);
		orderTransferTbl.setOrderBranchTbl(this);

		return orderTransferTbl;
	}

	public OrderTransferTbl removeOrderTransferTbl(OrderTransferTbl orderTransferTbl) {
		getOrderTransferTbls().remove(orderTransferTbl);
		orderTransferTbl.setOrderBranchTbl(null);

		return orderTransferTbl;
	}

}