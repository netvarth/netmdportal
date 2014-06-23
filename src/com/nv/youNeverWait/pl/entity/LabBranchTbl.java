package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the lab_branch_tbl database table.
 * 
 */
@Entity
@Table(name="lab_branch_tbl")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class LabBranchTbl extends HealthCareOrganisationTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date_time", nullable=false)
	private Date createDateTime;


	@Column(name="enable_sync", nullable=false)
	private boolean enableSync;

	@Column(name="sync_freq_type", length=45)
	private String syncFreqType;

	@Column(name="sync_time")
	private int syncTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date_time", nullable=false)
	private Date updateDateTime;

	//bi-directional many-to-one association to LabBranchSystemInfoTbl
	@OneToMany(mappedBy="labBranchTbl")
	private List<LabBranchSystemInfoTbl> labBranchSystemInfoTbls;

	//bi-directional many-to-one association to LabTbl
	@ManyToOne
	@JoinColumn(name="lab_id")
	private LabTbl labTbl;

	//bi-directional many-to-one association to LabHealthMonitorTbl
	@OneToMany(mappedBy="labBranchTbl")
	private List<LabHealthMonitorTbl> labHealthMonitorTbls;

	//bi-directional many-to-one association to LabPassphraseTbl
	@OneToMany(mappedBy="labBranchTbl")
	private List<LabPassphraseTbl> labPassphraseTbls;

	//bi-directional many-to-one association to LabUserBranchTbl
	@OneToMany(mappedBy="labBranchTbl")
	private List<LabUserBranchTbl> labUserBranchTbls;

	//bi-directional many-to-one association to NetlimsPatientTbl
	@OneToMany(mappedBy="labBranchTbl")
	private List<NetlimsPatientTbl> netlimsPatientTbls;

	//bi-directional many-to-one association to NetlimsReferralTbl
	@OneToMany(mappedBy="labBranchTbl")
	private List<NetlimsReferralTbl> netlimsReferralTbls;

	//bi-directional many-to-one association to OrderAmountTbl
	@OneToMany(mappedBy="labBranchTbl")
	private List<OrderAmountTbl> orderAmountTbls;

	//bi-directional many-to-one association to OrderBranchTbl
	@OneToMany(mappedBy="labBranchTbl")
	private List<OrderBranchTbl> orderBranchTbls;

	//bi-directional many-to-one association to OrderResultTbl
	@OneToMany(mappedBy="labBranchTbl")
	private List<OrderResultTbl> orderResultTbls1;

	//bi-directional many-to-one association to OrderResultTbl
	@OneToMany(mappedBy="ownerLabBranchTbl")
	private List<OrderResultTbl> orderResultTbls2;

	//bi-directional many-to-one association to OrderTbl
	@OneToMany(mappedBy="sourceBranchTbl")
	private List<OrderTbl> sourceBranchTbls;

	//bi-directional many-to-one association to OrderTbl
	@OneToMany(mappedBy="destinationBranchTbl")
	private List<OrderTbl> destinationBranchTbls;

	//bi-directional many-to-one association to OrderTransferTbl
	@OneToMany(mappedBy="labBranchTbl")
	private List<OrderTransferTbl> orderTransferTbls;

	//bi-directional many-to-one association to ResultTbl
	@OneToMany(mappedBy="labBranchTbl")
	private List<ResultTbl> resultTbls;

	public LabBranchTbl() {
	}

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getSyncFreqType() {
		return this.syncFreqType;
	}

	public void setSyncFreqType(String syncFreqType) {
		this.syncFreqType = syncFreqType;
	}

	public int getSyncTime() {
		return this.syncTime;
	}

	public void setSyncTime(int syncTime) {
		this.syncTime = syncTime;
	}

	public Date getUpdateDateTime() {
		return this.updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public List<LabBranchSystemInfoTbl> getLabBranchSystemInfoTbls() {
		return this.labBranchSystemInfoTbls;
	}

	public void setLabBranchSystemInfoTbls(List<LabBranchSystemInfoTbl> labBranchSystemInfoTbls) {
		this.labBranchSystemInfoTbls = labBranchSystemInfoTbls;
	}

	public LabBranchSystemInfoTbl addLabBranchSystemInfoTbl(LabBranchSystemInfoTbl labBranchSystemInfoTbl) {
		getLabBranchSystemInfoTbls().add(labBranchSystemInfoTbl);
		labBranchSystemInfoTbl.setLabBranchTbl(this);

		return labBranchSystemInfoTbl;
	}

	public LabBranchSystemInfoTbl removeLabBranchSystemInfoTbl(LabBranchSystemInfoTbl labBranchSystemInfoTbl) {
		getLabBranchSystemInfoTbls().remove(labBranchSystemInfoTbl);
		labBranchSystemInfoTbl.setLabBranchTbl(null);

		return labBranchSystemInfoTbl;
	}

	public LabTbl getLabTbl() {
		return this.labTbl;
	}

	public void setLabTbl(LabTbl labTbl) {
		this.labTbl = labTbl;
	}

	public List<LabHealthMonitorTbl> getLabHealthMonitorTbls() {
		return this.labHealthMonitorTbls;
	}

	public void setLabHealthMonitorTbls(List<LabHealthMonitorTbl> labHealthMonitorTbls) {
		this.labHealthMonitorTbls = labHealthMonitorTbls;
	}

	public LabHealthMonitorTbl addLabHealthMonitorTbl(LabHealthMonitorTbl labHealthMonitorTbl) {
		getLabHealthMonitorTbls().add(labHealthMonitorTbl);
		labHealthMonitorTbl.setLabBranchTbl(this);

		return labHealthMonitorTbl;
	}

	public LabHealthMonitorTbl removeLabHealthMonitorTbl(LabHealthMonitorTbl labHealthMonitorTbl) {
		getLabHealthMonitorTbls().remove(labHealthMonitorTbl);
		labHealthMonitorTbl.setLabBranchTbl(null);

		return labHealthMonitorTbl;
	}

	public List<LabPassphraseTbl> getLabPassphraseTbls() {
		return this.labPassphraseTbls;
	}

	public void setLabPassphraseTbls(List<LabPassphraseTbl> labPassphraseTbls) {
		this.labPassphraseTbls = labPassphraseTbls;
	}

	public LabPassphraseTbl addLabPassphraseTbl(LabPassphraseTbl labPassphraseTbl) {
		getLabPassphraseTbls().add(labPassphraseTbl);
		labPassphraseTbl.setLabBranchTbl(this);

		return labPassphraseTbl;
	}

	public LabPassphraseTbl removeLabPassphraseTbl(LabPassphraseTbl labPassphraseTbl) {
		getLabPassphraseTbls().remove(labPassphraseTbl);
		labPassphraseTbl.setLabBranchTbl(null);

		return labPassphraseTbl;
	}

	public List<LabUserBranchTbl> getLabUserBranchTbls() {
		return this.labUserBranchTbls;
	}

	public void setLabUserBranchTbls(List<LabUserBranchTbl> labUserBranchTbls) {
		this.labUserBranchTbls = labUserBranchTbls;
	}

	public LabUserBranchTbl addLabUserBranchTbl(LabUserBranchTbl labUserBranchTbl) {
		getLabUserBranchTbls().add(labUserBranchTbl);
		labUserBranchTbl.setLabBranchTbl(this);

		return labUserBranchTbl;
	}

	public LabUserBranchTbl removeLabUserBranchTbl(LabUserBranchTbl labUserBranchTbl) {
		getLabUserBranchTbls().remove(labUserBranchTbl);
		labUserBranchTbl.setLabBranchTbl(null);

		return labUserBranchTbl;
	}

	public List<NetlimsPatientTbl> getNetlimsPatientTbls() {
		return this.netlimsPatientTbls;
	}

	public void setNetlimsPatientTbls(List<NetlimsPatientTbl> netlimsPatientTbls) {
		this.netlimsPatientTbls = netlimsPatientTbls;
	}

	public NetlimsPatientTbl addNetlimsPatientTbl(NetlimsPatientTbl netlimsPatientTbl) {
		getNetlimsPatientTbls().add(netlimsPatientTbl);
		netlimsPatientTbl.setLabBranchTbl(this);

		return netlimsPatientTbl;
	}

	public NetlimsPatientTbl removeNetlimsPatientTbl(NetlimsPatientTbl netlimsPatientTbl) {
		getNetlimsPatientTbls().remove(netlimsPatientTbl);
		netlimsPatientTbl.setLabBranchTbl(null);

		return netlimsPatientTbl;
	}

	public List<NetlimsReferralTbl> getNetlimsReferralTbls() {
		return this.netlimsReferralTbls;
	}

	public void setNetlimsReferralTbls(List<NetlimsReferralTbl> netlimsReferralTbls) {
		this.netlimsReferralTbls = netlimsReferralTbls;
	}

	public NetlimsReferralTbl addNetlimsReferralTbl(NetlimsReferralTbl netlimsReferralTbl) {
		getNetlimsReferralTbls().add(netlimsReferralTbl);
		netlimsReferralTbl.setLabBranchTbl(this);

		return netlimsReferralTbl;
	}

	public NetlimsReferralTbl removeNetlimsReferralTbl(NetlimsReferralTbl netlimsReferralTbl) {
		getNetlimsReferralTbls().remove(netlimsReferralTbl);
		netlimsReferralTbl.setLabBranchTbl(null);

		return netlimsReferralTbl;
	}

	public List<OrderAmountTbl> getOrderAmountTbls() {
		return this.orderAmountTbls;
	}

	public void setOrderAmountTbls(List<OrderAmountTbl> orderAmountTbls) {
		this.orderAmountTbls = orderAmountTbls;
	}

	public OrderAmountTbl addOrderAmountTbl(OrderAmountTbl orderAmountTbl) {
		getOrderAmountTbls().add(orderAmountTbl);
		orderAmountTbl.setLabBranchTbl(this);

		return orderAmountTbl;
	}

	public OrderAmountTbl removeOrderAmountTbl(OrderAmountTbl orderAmountTbl) {
		getOrderAmountTbls().remove(orderAmountTbl);
		orderAmountTbl.setLabBranchTbl(null);

		return orderAmountTbl;
	}

	public List<OrderBranchTbl> getOrderBranchTbls() {
		return this.orderBranchTbls;
	}

	public void setOrderBranchTbls(List<OrderBranchTbl> orderBranchTbls) {
		this.orderBranchTbls = orderBranchTbls;
	}

	public OrderBranchTbl addOrderBranchTbl(OrderBranchTbl orderBranchTbl) {
		getOrderBranchTbls().add(orderBranchTbl);
		orderBranchTbl.setLabBranchTbl(this);

		return orderBranchTbl;
	}

	public OrderBranchTbl removeOrderBranchTbl(OrderBranchTbl orderBranchTbl) {
		getOrderBranchTbls().remove(orderBranchTbl);
		orderBranchTbl.setLabBranchTbl(null);

		return orderBranchTbl;
	}

	public List<OrderResultTbl> getOrderResultTbls1() {
		return this.orderResultTbls1;
	}

	public void setOrderResultTbls1(List<OrderResultTbl> orderResultTbls1) {
		this.orderResultTbls1 = orderResultTbls1;
	}

	public OrderResultTbl addOrderResultTbls1(OrderResultTbl orderResultTbls1) {
		getOrderResultTbls1().add(orderResultTbls1);
		orderResultTbls1.setLabBranchTbl(this);

		return orderResultTbls1;
	}

	public OrderResultTbl removeOrderResultTbls1(OrderResultTbl orderResultTbls1) {
		getOrderResultTbls1().remove(orderResultTbls1);
		orderResultTbls1.setLabBranchTbl(null);

		return orderResultTbls1;
	}

	public List<OrderResultTbl> getOrderResultTbls2() {
		return this.orderResultTbls2;
	}

	public void setOrderResultTbls2(List<OrderResultTbl> orderResultTbls2) {
		this.orderResultTbls2 = orderResultTbls2;
	}

	public OrderResultTbl addOrderResultTbls2(OrderResultTbl orderResultTbls2) {
		getOrderResultTbls2().add(orderResultTbls2);
		orderResultTbls2.setOwnerLabBranchTbl(this);

		return orderResultTbls2;
	}

	public OrderResultTbl removeOrderResultTbls2(OrderResultTbl orderResultTbls2) {
		getOrderResultTbls2().remove(orderResultTbls2);
		orderResultTbls2.setOwnerLabBranchTbl(null);

		return orderResultTbls2;
	}
	public List<OrderTransferTbl> getOrderTransferTbls() {
		return this.orderTransferTbls;
	}

	public void setOrderTransferTbls(List<OrderTransferTbl> orderTransferTbls) {
		this.orderTransferTbls = orderTransferTbls;
	}

	public OrderTransferTbl addOrderTransferTbl(OrderTransferTbl orderTransferTbl) {
		getOrderTransferTbls().add(orderTransferTbl);
		orderTransferTbl.setLabBranchTbl(this);

		return orderTransferTbl;
	}

	public OrderTransferTbl removeOrderTransferTbl(OrderTransferTbl orderTransferTbl) {
		getOrderTransferTbls().remove(orderTransferTbl);
		orderTransferTbl.setLabBranchTbl(null);

		return orderTransferTbl;
	}

	public List<ResultTbl> getResultTbls() {
		return this.resultTbls;
	}

	public void setResultTbls(List<ResultTbl> resultTbls) {
		this.resultTbls = resultTbls;
	}

	public ResultTbl addResultTbl(ResultTbl resultTbl) {
		getResultTbls().add(resultTbl);
		resultTbl.setLabBranchTbl(this);

		return resultTbl;
	}

	public ResultTbl removeResultTbl(ResultTbl resultTbl) {
		getResultTbls().remove(resultTbl);
		resultTbl.setLabBranchTbl(null);

		return resultTbl;
	}

	/**
	 * @return the enableSync
	 */
	public boolean isEnableSync() {
		return enableSync;
	}

	/**
	 * @param enableSync the enableSync to set
	 */
	public void setEnableSync(boolean enableSync) {
		this.enableSync = enableSync;
	}

	/**
	 * @return the sourceBranchTbls
	 */
	public List<OrderTbl> getSourceBranchTbls() {
		return sourceBranchTbls;
	}

	/**
	 * @param sourceBranchTbls the sourceBranchTbls to set
	 */
	public void setSourceBranchTbls(List<OrderTbl> sourceBranchTbls) {
		this.sourceBranchTbls = sourceBranchTbls;
	}

	/**
	 * @return the destinationBranchTbls
	 */
	public List<OrderTbl> getDestinationBranchTbls() {
		return destinationBranchTbls;
	}

	/**
	 * @param destinationBranchTbls the destinationBranchTbls to set
	 */
	public void setDestinationBranchTbls(List<OrderTbl> destinationBranchTbls) {
		this.destinationBranchTbls = destinationBranchTbls;
	}

}