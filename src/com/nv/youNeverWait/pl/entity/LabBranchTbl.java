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

	//bi-directional many-to-one association to LabUserTbl
	@OneToMany(mappedBy="labBranchTbl")
	private List<LabUserTbl> labUserTbls;

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

	//bi-directional many-to-one association to NetlimsOrderTbl
	@OneToMany(mappedBy="labBranchTbl")
	private List<NetlimsOrderTbl> netlimsOrderTbls;

	//bi-directional many-to-one association to OrderTransferCountTbl
		@OneToMany(mappedBy="labBranchTbl")
		private List<OrderTransferCountTbl> orderTransferCountTbls;
	/**
	 * 
	 */
	public LabBranchTbl() {
	}

	/**
	 * @param labBranchSystemInfoTbl
	 * @return LabBranchSystemInfoTbl
	 */
	public LabBranchSystemInfoTbl addLabBranchSystemInfoTbl(LabBranchSystemInfoTbl labBranchSystemInfoTbl) {
		getLabBranchSystemInfoTbls().add(labBranchSystemInfoTbl);
		labBranchSystemInfoTbl.setLabBranchTbl(this);

		return labBranchSystemInfoTbl;
	}

	/**
	 * @param labBranchSystemInfoTbl
	 * @return LabBranchSystemInfoTbl
	 */
	public LabBranchSystemInfoTbl removeLabBranchSystemInfoTbl(LabBranchSystemInfoTbl labBranchSystemInfoTbl) {
		getLabBranchSystemInfoTbls().remove(labBranchSystemInfoTbl);
		labBranchSystemInfoTbl.setLabBranchTbl(null);

		return labBranchSystemInfoTbl;
	}

	/**
	 * @param labHealthMonitorTbl
	 * @return LabHealthMonitorTbl
	 */
	public LabHealthMonitorTbl addLabHealthMonitorTbl(LabHealthMonitorTbl labHealthMonitorTbl) {
		getLabHealthMonitorTbls().add(labHealthMonitorTbl);
		labHealthMonitorTbl.setLabBranchTbl(this);

		return labHealthMonitorTbl;
	}

	/**
	 * @param labHealthMonitorTbl
	 * @return LabHealthMonitorTbl
	 */
	public LabHealthMonitorTbl removeLabHealthMonitorTbl(LabHealthMonitorTbl labHealthMonitorTbl) {
		getLabHealthMonitorTbls().remove(labHealthMonitorTbl);
		labHealthMonitorTbl.setLabBranchTbl(null);

		return labHealthMonitorTbl;
	}

	/**
	 * @param labPassphraseTbl
	 * @return LabPassphraseTbl
	 */
	public LabPassphraseTbl addLabPassphraseTbl(LabPassphraseTbl labPassphraseTbl) {
		getLabPassphraseTbls().add(labPassphraseTbl);
		labPassphraseTbl.setLabBranchTbl(this);

		return labPassphraseTbl;
	}

	/**
	 * @param labPassphraseTbl
	 * @return LabPassphraseTbl
	 */
	public LabPassphraseTbl removeLabPassphraseTbl(LabPassphraseTbl labPassphraseTbl) {
		getLabPassphraseTbls().remove(labPassphraseTbl);
		labPassphraseTbl.setLabBranchTbl(null);

		return labPassphraseTbl;
	}

	/**
	 * @param labUserTbl
	 * @return LabUserTbl
	 */
	public LabUserTbl addLabUserTbl(LabUserTbl labUserTbl) {
		getLabUserTbls().add(labUserTbl);
		labUserTbl.setLabBranchTbl(this);

		return labUserTbl;
	}

	/**
	 * @param labUserTbl
	 * @return LabUserTbl
	 */
	public LabUserTbl removeLabUserTbl(LabUserTbl labUserTbl) {
		getLabUserTbls().remove(labUserTbl);
		labUserTbl.setLabBranchTbl(null);

		return labUserTbl;
	}

	/**
	 * @param netlimsPatientTbl
	 * @return NetlimsPatientTbl
	 */
	public NetlimsPatientTbl addNetlimsPatientTbl(NetlimsPatientTbl netlimsPatientTbl) {
		getNetlimsPatientTbls().add(netlimsPatientTbl);
		netlimsPatientTbl.setLabBranchTbl(this);

		return netlimsPatientTbl;
	}

	/**
	 * @param netlimsPatientTbl
	 * @return NetlimsPatientTbl
	 */
	public NetlimsPatientTbl removeNetlimsPatientTbl(NetlimsPatientTbl netlimsPatientTbl) {
		getNetlimsPatientTbls().remove(netlimsPatientTbl);
		netlimsPatientTbl.setLabBranchTbl(null);

		return netlimsPatientTbl;
	}


	/**
	 * @param netlimsReferralTbl
	 * @return NetlimsReferralTbl
	 */
	public NetlimsReferralTbl addNetlimsReferralTbl(NetlimsReferralTbl netlimsReferralTbl) {
		getNetlimsReferralTbls().add(netlimsReferralTbl);
		netlimsReferralTbl.setLabBranchTbl(this);

		return netlimsReferralTbl;
	}

	/**
	 * @param netlimsReferralTbl
	 * @return NetlimsReferralTbl
	 */
	public NetlimsReferralTbl removeNetlimsReferralTbl(NetlimsReferralTbl netlimsReferralTbl) {
		getNetlimsReferralTbls().remove(netlimsReferralTbl);
		netlimsReferralTbl.setLabBranchTbl(null);

		return netlimsReferralTbl;
	}

	/**
	 * @param orderAmountTbl
	 * @return OrderAmountTbl
	 */
	public OrderAmountTbl addOrderAmountTbl(OrderAmountTbl orderAmountTbl) {
		getOrderAmountTbls().add(orderAmountTbl);
		orderAmountTbl.setLabBranchTbl(this);

		return orderAmountTbl;
	}

	/**
	 * @param orderAmountTbl
	 * @return OrderAmountTbl
	 */ 
	public OrderAmountTbl removeOrderAmountTbl(OrderAmountTbl orderAmountTbl) {
		getOrderAmountTbls().remove(orderAmountTbl);
		orderAmountTbl.setLabBranchTbl(null);

		return orderAmountTbl;
	}

	/**
	 * @param orderBranchTbl
	 * @return OrderBranchTbl
	 */
	public OrderBranchTbl addOrderBranchTbl(OrderBranchTbl orderBranchTbl) {
		getOrderBranchTbls().add(orderBranchTbl);
		orderBranchTbl.setLabBranchTbl(this);

		return orderBranchTbl;
	}

	/**
	 * @param orderBranchTbl
	 * @return OrderBranchTbl
	 */
	public OrderBranchTbl removeOrderBranchTbl(OrderBranchTbl orderBranchTbl) {
		getOrderBranchTbls().remove(orderBranchTbl);
		orderBranchTbl.setLabBranchTbl(null);

		return orderBranchTbl;
	}

	/**
	 * @param orderResultTbls1
	 * @return OrderResultTbl
	 */
	public OrderResultTbl addOrderResultTbls1(OrderResultTbl orderResultTbls1) {
		getOrderResultTbls1().add(orderResultTbls1);
		orderResultTbls1.setLabBranchTbl(this);

		return orderResultTbls1;
	}

	/**
	 * @param orderResultTbls1
	 * @return OrderResultTbl
	 */
	public OrderResultTbl removeOrderResultTbls1(OrderResultTbl orderResultTbls1) {
		getOrderResultTbls1().remove(orderResultTbls1);
		orderResultTbls1.setLabBranchTbl(null);

		return orderResultTbls1;
	}

	/**
	 * @param orderResultTbls2
	 * @return OrderResultTbl
	 */
	public OrderResultTbl addOrderResultTbls2(OrderResultTbl orderResultTbls2) {
		getOrderResultTbls2().add(orderResultTbls2);
		orderResultTbls2.setOwnerLabBranchTbl(this);

		return orderResultTbls2;
	}

	/**
	 * @param orderResultTbls2
	 * @return OrderResultTbl
	 */
	public OrderResultTbl removeOrderResultTbls2(OrderResultTbl orderResultTbls2) {
		getOrderResultTbls2().remove(orderResultTbls2);
		orderResultTbls2.setOwnerLabBranchTbl(null);

		return orderResultTbls2;
	}

	/**
	 * @param orderTransferTbl
	 * @return OrderTransferTbl
	 */
	public OrderTransferTbl addOrderTransferTbl(OrderTransferTbl orderTransferTbl) {
		getOrderTransferTbls().add(orderTransferTbl);
		orderTransferTbl.setLabBranchTbl(this);

		return orderTransferTbl;
	}

	/**
	 * @param orderTransferTbl
	 * @return OrderTransferTbl
	 */
	public OrderTransferTbl removeOrderTransferTbl(OrderTransferTbl orderTransferTbl) {
		getOrderTransferTbls().remove(orderTransferTbl);
		orderTransferTbl.setLabBranchTbl(null);

		return orderTransferTbl;
	}

	/**
	 * @param resultTbl
	 * @return ResultTbl
	 */
	public ResultTbl addResultTbl(ResultTbl resultTbl) {
		getResultTbls().add(resultTbl);
		resultTbl.setLabBranchTbl(this);

		return resultTbl;
	}

	/**
	 * @param resultTbl
	 * @return ResultTbl
	 */
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

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @return the syncFreqType
	 */
	public String getSyncFreqType() {
		return syncFreqType;
	}

	/**
	 * @param syncFreqType the syncFreqType to set
	 */
	public void setSyncFreqType(String syncFreqType) {
		this.syncFreqType = syncFreqType;
	}

	/**
	 * @return the syncTime
	 */
	public int getSyncTime() {
		return syncTime;
	}

	/**
	 * @param syncTime the syncTime to set
	 */
	public void setSyncTime(int syncTime) {
		this.syncTime = syncTime;
	}

	/**
	 * @return the updateDateTime
	 */
	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	/**
	 * @param updateDateTime the updateDateTime to set
	 */
	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	/**
	 * @return the labBranchSystemInfoTbls
	 */
	public List<LabBranchSystemInfoTbl> getLabBranchSystemInfoTbls() {
		return labBranchSystemInfoTbls;
	}

	/**
	 * @param labBranchSystemInfoTbls the labBranchSystemInfoTbls to set
	 */
	public void setLabBranchSystemInfoTbls(
			List<LabBranchSystemInfoTbl> labBranchSystemInfoTbls) {
		this.labBranchSystemInfoTbls = labBranchSystemInfoTbls;
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
	 * @return the labHealthMonitorTbls
	 */
	public List<LabHealthMonitorTbl> getLabHealthMonitorTbls() {
		return labHealthMonitorTbls;
	}

	/**
	 * @param labHealthMonitorTbls the labHealthMonitorTbls to set
	 */
	public void setLabHealthMonitorTbls(
			List<LabHealthMonitorTbl> labHealthMonitorTbls) {
		this.labHealthMonitorTbls = labHealthMonitorTbls;
	}

	/**
	 * @return the labPassphraseTbls
	 */
	public List<LabPassphraseTbl> getLabPassphraseTbls() {
		return labPassphraseTbls;
	}

	/**
	 * @param labPassphraseTbls the labPassphraseTbls to set
	 */
	public void setLabPassphraseTbls(List<LabPassphraseTbl> labPassphraseTbls) {
		this.labPassphraseTbls = labPassphraseTbls;
	}

	/**
	 * @return the labUserBranchTbls
	 */
	public List<LabUserTbl> getLabUserTbls() {
		return labUserTbls;
	}

	/**
	 * @param labUserTbls the labUserTbls to set
	 */
	public void setLabUserTbls(List<LabUserTbl> labUserTbls) {
		this.labUserTbls = labUserTbls;
	}

	/**
	 * @return the netlimsPatientTbls
	 */
	public List<NetlimsPatientTbl> getNetlimsPatientTbls() {
		return netlimsPatientTbls;
	}

	/**
	 * @param netlimsPatientTbls the netlimsPatientTbls to set
	 */
	public void setNetlimsPatientTbls(List<NetlimsPatientTbl> netlimsPatientTbls) {
		this.netlimsPatientTbls = netlimsPatientTbls;
	}

	/**
	 * @return the netlimsReferralTbls
	 */
	public List<NetlimsReferralTbl> getNetlimsReferralTbls() {
		return netlimsReferralTbls;
	}

	/**
	 * @param netlimsReferralTbls the netlimsReferralTbls to set
	 */
	public void setNetlimsReferralTbls(List<NetlimsReferralTbl> netlimsReferralTbls) {
		this.netlimsReferralTbls = netlimsReferralTbls;
	}

	/**
	 * @return the orderAmountTbls
	 */
	public List<OrderAmountTbl> getOrderAmountTbls() {
		return orderAmountTbls;
	}

	/**
	 * @param orderAmountTbls the orderAmountTbls to set
	 */
	public void setOrderAmountTbls(List<OrderAmountTbl> orderAmountTbls) {
		this.orderAmountTbls = orderAmountTbls;
	}

	/**
	 * @return the orderBranchTbls
	 */
	public List<OrderBranchTbl> getOrderBranchTbls() {
		return orderBranchTbls;
	}

	/**
	 * @param orderBranchTbls the orderBranchTbls to set
	 */
	public void setOrderBranchTbls(List<OrderBranchTbl> orderBranchTbls) {
		this.orderBranchTbls = orderBranchTbls;
	}

	/**
	 * @return the orderResultTbls1
	 */
	public List<OrderResultTbl> getOrderResultTbls1() {
		return orderResultTbls1;
	}

	/**
	 * @param orderResultTbls1 the orderResultTbls1 to set
	 */
	public void setOrderResultTbls1(List<OrderResultTbl> orderResultTbls1) {
		this.orderResultTbls1 = orderResultTbls1;
	}

	/**
	 * @return the orderResultTbls2
	 */
	public List<OrderResultTbl> getOrderResultTbls2() {
		return orderResultTbls2;
	}

	/**
	 * @param orderResultTbls2 the orderResultTbls2 to set
	 */
	public void setOrderResultTbls2(List<OrderResultTbl> orderResultTbls2) {
		this.orderResultTbls2 = orderResultTbls2;
	}

	/**
	 * @return the orderTransferTbls
	 */
	public List<OrderTransferTbl> getOrderTransferTbls() {
		return orderTransferTbls;
	}

	/**
	 * @param orderTransferTbls the orderTransferTbls to set
	 */
	public void setOrderTransferTbls(List<OrderTransferTbl> orderTransferTbls) {
		this.orderTransferTbls = orderTransferTbls;
	}

	/**
	 * @return the resultTbls
	 */
	public List<ResultTbl> getResultTbls() {
		return resultTbls;
	}

	/**
	 * @param resultTbls the resultTbls to set
	 */
	public void setResultTbls(List<ResultTbl> resultTbls) {
		this.resultTbls = resultTbls;
	}

	/**
	 * @return the netlimsOrderTbls
	 */
	public List<NetlimsOrderTbl> getNetlimsOrderTbls() {
		return netlimsOrderTbls;
	}

	/**
	 * @param netlimsOrderTbls the netlimsOrderTbls to set
	 */
	public void setNetlimsOrderTbls(List<NetlimsOrderTbl> netlimsOrderTbls) {
		this.netlimsOrderTbls = netlimsOrderTbls;
	}
	/**
	 * @param netlimsOrderTbl
	 * @return NetlimsOrderTbl
	 */
	public NetlimsOrderTbl addNetlimsOrderTbl(NetlimsOrderTbl netlimsOrderTbl) {
		getNetlimsOrderTbls().add(netlimsOrderTbl);
		netlimsOrderTbl.setLabBranchTbl(this);

		return netlimsOrderTbl;
	}

	/**
	 * @param netlimsOrderTbl
	 * @return NetlimsOrderTbl
	 */
	public NetlimsOrderTbl removeNetlimsOrderTbl(NetlimsOrderTbl netlimsOrderTbl) {
		getNetlimsOrderTbls().remove(netlimsOrderTbl);
		netlimsOrderTbl.setLabBranchTbl(null);

		return netlimsOrderTbl;
	}
	public List<OrderTransferCountTbl> getOrderTransferCountTbls() {
		return this.orderTransferCountTbls;
	}

	public void setOrderTransferCountTbls(List<OrderTransferCountTbl> orderTransferCountTbls) {
		this.orderTransferCountTbls = orderTransferCountTbls;
	}

	public OrderTransferCountTbl addOrderTransferCountTbl(OrderTransferCountTbl orderTransferCountTbl) {
		getOrderTransferCountTbls().add(orderTransferCountTbl);
		orderTransferCountTbl.setLabBranchTbl(this);

		return orderTransferCountTbl;
	}

	public OrderTransferCountTbl removeOrderTransferCountTbl(OrderTransferCountTbl orderTransferCountTbl) {
		getOrderTransferCountTbls().remove(orderTransferCountTbl);
		orderTransferCountTbl.setLabBranchTbl(null);

		return orderTransferCountTbl;
	}

}