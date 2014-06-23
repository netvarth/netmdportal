package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the netlims_order_tbl database table.
 * 
 */
@Entity
@Table(name="netlims_order_tbl")
@NamedQuery(name="NetlimsOrderTbl.findAll", query="SELECT n FROM NetlimsOrderTbl n")
public class NetlimsOrderTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_sync_date")
	private Date lastSyncDate;

	@Column(name="order_id")
	private String orderId;

	@Column(name="order_status")
	private String orderStatus;

	@Column(name="source_branch_id")
	private int sourceBranchId;

	//bi-directional many-to-one association to FacilityResultTbl
	@OneToMany(mappedBy="netlimsOrderTbl")
	private List<FacilityResultTbl> facilityResultTbls;

	//bi-directional many-to-one association to NetlimsResultTbl
	@OneToMany(mappedBy="netlimsOrderTbl")
	private List<NetlimsResultTbl> netlimsResultTbls;

	//bi-directional many-to-one association to PatientResultTbl
	@OneToMany(mappedBy="netlimsOrderTbl")
	private List<PatientResultTbl> patientResultTbls;

	//bi-directional many-to-one association to ReferralResultTbl
	@OneToMany(mappedBy="netlimsOrderTbl")
	private List<ReferralResultTbl> referralResultTbls;

	public NetlimsOrderTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastSyncDate() {
		return this.lastSyncDate;
	}

	public void setLastSyncDate(Date lastSyncDate) {
		this.lastSyncDate = lastSyncDate;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getSourceBranchId() {
		return this.sourceBranchId;
	}

	public void setSourceBranchId(int sourceBranchId) {
		this.sourceBranchId = sourceBranchId;
	}

	public List<FacilityResultTbl> getFacilityResultTbls() {
		return this.facilityResultTbls;
	}

	public void setFacilityResultTbls(List<FacilityResultTbl> facilityResultTbls) {
		this.facilityResultTbls = facilityResultTbls;
	}

	public FacilityResultTbl addFacilityResultTbl(FacilityResultTbl facilityResultTbl) {
		getFacilityResultTbls().add(facilityResultTbl);
		facilityResultTbl.setNetlimsOrderTbl(this);

		return facilityResultTbl;
	}

	public FacilityResultTbl removeFacilityResultTbl(FacilityResultTbl facilityResultTbl) {
		getFacilityResultTbls().remove(facilityResultTbl);
		facilityResultTbl.setNetlimsOrderTbl(null);

		return facilityResultTbl;
	}

	public List<NetlimsResultTbl> getNetlimsResultTbls() {
		return this.netlimsResultTbls;
	}

	public void setNetlimsResultTbls(List<NetlimsResultTbl> netlimsResultTbls) {
		this.netlimsResultTbls = netlimsResultTbls;
	}

	public NetlimsResultTbl addNetlimsResultTbl(NetlimsResultTbl netlimsResultTbl) {
		getNetlimsResultTbls().add(netlimsResultTbl);
		netlimsResultTbl.setNetlimsOrderTbl(this);

		return netlimsResultTbl;
	}

	public NetlimsResultTbl removeNetlimsResultTbl(NetlimsResultTbl netlimsResultTbl) {
		getNetlimsResultTbls().remove(netlimsResultTbl);
		netlimsResultTbl.setNetlimsOrderTbl(null);

		return netlimsResultTbl;
	}

	public List<PatientResultTbl> getPatientResultTbls() {
		return this.patientResultTbls;
	}

	public void setPatientResultTbls(List<PatientResultTbl> patientResultTbls) {
		this.patientResultTbls = patientResultTbls;
	}

	public PatientResultTbl addPatientResultTbl(PatientResultTbl patientResultTbl) {
		getPatientResultTbls().add(patientResultTbl);
		patientResultTbl.setNetlimsOrderTbl(this);

		return patientResultTbl;
	}

	public PatientResultTbl removePatientResultTbl(PatientResultTbl patientResultTbl) {
		getPatientResultTbls().remove(patientResultTbl);
		patientResultTbl.setNetlimsOrderTbl(null);

		return patientResultTbl;
	}

	public List<ReferralResultTbl> getReferralResultTbls() {
		return this.referralResultTbls;
	}

	public void setReferralResultTbls(List<ReferralResultTbl> referralResultTbls) {
		this.referralResultTbls = referralResultTbls;
	}

	public ReferralResultTbl addReferralResultTbl(ReferralResultTbl referralResultTbl) {
		getReferralResultTbls().add(referralResultTbl);
		referralResultTbl.setNetlimsOrderTbl(this);

		return referralResultTbl;
	}

	public ReferralResultTbl removeReferralResultTbl(ReferralResultTbl referralResultTbl) {
		getReferralResultTbls().remove(referralResultTbl);
		referralResultTbl.setNetlimsOrderTbl(null);

		return referralResultTbl;
	}

}