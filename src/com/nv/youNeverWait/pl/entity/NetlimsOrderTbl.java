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

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="source_branch_id", nullable=false)
	private LabBranchTbl labBranchTbl;

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
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}



	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	/**
	 * @return the lastSyncDate
	 */
	public Date getLastSyncDate() {
		return lastSyncDate;
	}



	/**
	 * @param lastSyncDate the lastSyncDate to set
	 */
	public void setLastSyncDate(Date lastSyncDate) {
		this.lastSyncDate = lastSyncDate;
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
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}



	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * @return the facilityResultTbls
	 */
	public List<FacilityResultTbl> getFacilityResultTbls() {
		return facilityResultTbls;
	}



	/**
	 * @param facilityResultTbls the facilityResultTbls to set
	 */
	public void setFacilityResultTbls(List<FacilityResultTbl> facilityResultTbls) {
		this.facilityResultTbls = facilityResultTbls;
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

}