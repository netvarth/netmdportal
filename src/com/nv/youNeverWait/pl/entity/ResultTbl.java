package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the result_tbl database table.
 * 
 */
@Entity
@Table(name="result_tbl")
public class ResultTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_date_time", nullable=false)
	private Date createdDateTime;

	@Column(name="order_id", nullable=false, length=45)
	private String orderId;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="order_date", nullable=false)
	private Date orderDate;
	
	//@Column(nullable=false, length=945)
	@Lob()
	private String result;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_date_time", nullable=false)
	private Date updatedDateTime;

	//bi-directional many-to-one association to MessageTbl
	@OneToMany(mappedBy="resultTbl")
	private Set<MessageTbl> messageTbls;

	//bi-directional many-to-one association to LabBranchTbl
    @ManyToOne
	@JoinColumn(name="source_lab_branch_id", nullable=false)
	private LabBranchTbl labBranchTbl;

	//bi-directional many-to-one association to LabTbl
    @ManyToOne
	@JoinColumn(name="source_lab_id", nullable=false)
	private LabTbl labTbl;

	//bi-directional many-to-one association to NetmdBranchTbl
    @ManyToOne
	@JoinColumn(name="destination_netmd_branch_id", nullable=false)
	private NetmdBranchTbl netmdBranchTbl;

	//bi-directional many-to-one association to NetmdTbl
    @ManyToOne
	@JoinColumn(name="destination_netmd_id", nullable=false)
	private NetmdTbl netmdTbl;

	//bi-directional many-to-one association to PatientTbl
    @ManyToOne
	@JoinColumn(name="patient_id", nullable=false)
    private NetmdPatientTbl netmdPatientTbl;

    /**
     * 
     */
    public ResultTbl() {
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
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
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
	 * @return the messageTbls
	 */
	public Set<MessageTbl> getMessageTbls() {
		return messageTbls;
	}

	/**
	 * @param messageTbls the messageTbls to set
	 */
	public void setMessageTbls(Set<MessageTbl> messageTbls) {
		this.messageTbls = messageTbls;
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
	 * @return the labTbl
	 */
	public LabTbl getLabTbl() {
		return labTbl;
	}

	/**
	 * @param labTbl the labTbl to set
	 */
	public void setLabTbl(LabTbl labTbl) {
		this.labTbl = labTbl;
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