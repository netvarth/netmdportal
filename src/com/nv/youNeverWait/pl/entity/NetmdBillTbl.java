package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the netmd_bill_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_bill_tbl")
public class NetmdBillTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="amount_paid", nullable=false)
	private float amountPaid;

	@Column(name="bill_amount", nullable=false)
	private float billAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date_time", nullable=false)
	private Date createdDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="order_date", nullable=false)
	private Date orderDate;

	@Column(name="patient_name", nullable=false, length=45)
	private String patientName;

	@Column(name="pay_status", nullable=false, length=45)
	private String payStatus;

	@Column(nullable=false, length=45)
	private String uid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date_time", nullable=false)
	private Date updatedDateTime;

	//bi-directional many-to-one association to NetmdBranchTbl
	@ManyToOne
	@JoinColumn(name="netmd_branch_id", nullable=false)
	private NetmdBranchTbl netmdBranchTbl;

	//bi-directional many-to-one association to NetmdTbl
	@ManyToOne
	@JoinColumn(name="netmd_id", nullable=false)
	private NetmdTbl netmdTbl;

	//bi-directional many-to-one association to PatientTbl
	@ManyToOne
	@JoinColumn(name="patient_id", nullable=false)
	private NetmdPatientTbl netmdPatientTbl;

	/**
	 * 
	 */
	public NetmdBillTbl() {
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the amountPaid
	 */
	public float getAmountPaid() {
		return amountPaid;
	}

	/**
	 * @param amountPaid the amountPaid to set
	 */
	public void setAmountPaid(float amountPaid) {
		this.amountPaid = amountPaid;
	}

	/**
	 * @return the billAmount
	 */
	public float getBillAmount() {
		return billAmount;
	}

	/**
	 * @param billAmount the billAmount to set
	 */
	public void setBillAmount(float billAmount) {
		this.billAmount = billAmount;
	}

	/**
	 * @return the createdDateTime
	 */
	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	/**
	 * @param createdDateTime the createdDateTime to set
	 */
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @return the payStatus
	 */
	public String getPayStatus() {
		return payStatus;
	}

	/**
	 * @param payStatus the payStatus to set
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the updatedDateTime
	 */
	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	/**
	 * @param updatedDateTime the updatedDateTime to set
	 */
	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	/**
	 * @return the netmdBranchTbl
	 */
	public NetmdBranchTbl getNetmdBranchTbl() {
		return netmdBranchTbl;
	}

	/**
	 * @param netmdBranchTbl the netmdBranchTbl to set
	 */
	public void setNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		this.netmdBranchTbl = netmdBranchTbl;
	}

	/**
	 * @return the netmdTbl
	 */
	public NetmdTbl getNetmdTbl() {
		return netmdTbl;
	}

	/**
	 * @param netmdTbl the netmdTbl to set
	 */
	public void setNetmdTbl(NetmdTbl netmdTbl) {
		this.netmdTbl = netmdTbl;
	}

	/**
	 * @return the netmdPatientTbl
	 */
	public NetmdPatientTbl getNetmdPatientTbl() {
		return netmdPatientTbl;
	}

	/**
	 * @param netmdPatientTbl the netmdPatientTbl to set
	 */
	public void setNetmdPatientTbl(NetmdPatientTbl netmdPatientTbl) {
		this.netmdPatientTbl = netmdPatientTbl;
	}


}