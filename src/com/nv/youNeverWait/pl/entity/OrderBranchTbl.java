package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the order_branch_tbl database table.
 * 
 */
@Entity
@Table(name="order_branch_tbl")
@NamedQuery(name="OrderBranchTbl.findAll", query="SELECT o FROM OrderBranchTbl o")
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

	//bi-directional many-to-one association to LabTbl
	@ManyToOne
	@JoinColumn(name="source_lab_id")
	private LabTbl labTbl;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="source_branch_id")
	private LabBranchTbl labBranchTbl;

	//bi-directional many-to-one association to OrderResultTbl
	@OneToMany(mappedBy="orderBranchTbl")
	private List<OrderResultTbl> orderResultTbls;

	//bi-directional many-to-one association to OrderTransferTbl
	@OneToMany(mappedBy="orderBranchTbl")
	private List<OrderTransferTbl> orderTransferTbls;

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

	public LabTbl getLabTbl() {
		return this.labTbl;
	}

	public void setLabTbl(LabTbl labTbl) {
		this.labTbl = labTbl;
	}

	public LabBranchTbl getLabBranchTbl() {
		return this.labBranchTbl;
	}

	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}

	public List<OrderResultTbl> getOrderResultTbls() {
		return this.orderResultTbls;
	}

	public void setOrderResultTbls(List<OrderResultTbl> orderResultTbls) {
		this.orderResultTbls = orderResultTbls;
	}

	public OrderResultTbl addOrderResultTbl(OrderResultTbl orderResultTbl) {
		getOrderResultTbls().add(orderResultTbl);
		orderResultTbl.setOrderBranchTbl(this);

		return orderResultTbl;
	}

	public OrderResultTbl removeOrderResultTbl(OrderResultTbl orderResultTbl) {
		getOrderResultTbls().remove(orderResultTbl);
		orderResultTbl.setOrderBranchTbl(null);

		return orderResultTbl;
	}

	public List<OrderTransferTbl> getOrderTransferTbls() {
		return this.orderTransferTbls;
	}

	public void setOrderTransferTbls(List<OrderTransferTbl> orderTransferTbls) {
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