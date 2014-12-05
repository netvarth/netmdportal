package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the order_tbl database table.
 * 
 */
@Entity
@Table(name="order_tbl")
public class OrderTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="local_order_uid", nullable=false, length=45)
	private String localOrderUid;

	@Column(nullable=false)
	private String result;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	//bi-directional many-to-one association to LabBranchTbl
    @ManyToOne
	@JoinColumn(name="source_branch_id", nullable=false)
	private LabBranchTbl sourceBranchTbl;

	//bi-directional many-to-one association to LabBranchTbl
    @ManyToOne
	@JoinColumn(name="destination_branch_id", nullable=false)
	private LabBranchTbl destinationBranchTbl;

	//bi-directional many-to-one association to LabTbl
    @ManyToOne
	@JoinColumn(name="destination_lab_id", nullable=false)
	private LabTbl destinationLabTbl;

	//bi-directional many-to-one association to LabTbl
    @ManyToOne
	@JoinColumn(name="source_lab_id", nullable=false)
	private LabTbl sourceLabTbl;

    public OrderTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLocalOrderUid() {
		return this.localOrderUid;
	}

	public void setLocalOrderUid(String localOrderUid) {
		this.localOrderUid = localOrderUid;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the destinationBranchTbl
	 */
	public LabBranchTbl getDestinationBranchTbl() {
		return destinationBranchTbl;
	}

	/**
	 * @param destinationBranchTbl the destinationBranchTbl to set
	 */
	public void setDestinationBranchTbl(LabBranchTbl destinationBranchTbl) {
		this.destinationBranchTbl = destinationBranchTbl;
	}

	/**
	 * @return the destinationLabTbl
	 */
	public LabTbl getDestinationLabTbl() {
		return destinationLabTbl;
	}

	/**
	 * @param destinationLabTbl the destinationLabTbl to set
	 */
	public void setDestinationLabTbl(LabTbl destinationLabTbl) {
		this.destinationLabTbl = destinationLabTbl;
	}

	/**
	 * @return the sourceLabTbl
	 */
	public LabTbl getSourceLabTbl() {
		return sourceLabTbl;
	}

	/**
	 * @param sourceLabTbl the sourceLabTbl to set
	 */
	public void setSourceLabTbl(LabTbl sourceLabTbl) {
		this.sourceLabTbl = sourceLabTbl;
	}

	/**
	 * @return the sourceBranchTbl
	 */
	public LabBranchTbl getSourceBranchTbl() {
		return sourceBranchTbl;
	}

	/**
	 * @param sourceBranchTbl the sourceBranchTbl to set
	 */
	public void setSourceBranchTbl(LabBranchTbl sourceBranchTbl) {
		this.sourceBranchTbl = sourceBranchTbl;
	}
	
}