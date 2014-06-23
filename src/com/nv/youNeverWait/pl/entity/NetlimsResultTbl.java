package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the netlims_result_tbl database table.
 * 
 */
@Entity
@Table(name="netlims_result_tbl")
@NamedQuery(name="NetlimsResultTbl.findAll", query="SELECT n FROM NetlimsResultTbl n")
public class NetlimsResultTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Lob
	private String result;

	@Column(name="test_uid")
	private String testUid;

	//bi-directional many-to-one association to NetlimsOrderTbl
	@ManyToOne
	@JoinColumn(name="portal_order_id")
	private NetlimsOrderTbl netlimsOrderTbl;

	public NetlimsResultTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTestUid() {
		return this.testUid;
	}

	public void setTestUid(String testUid) {
		this.testUid = testUid;
	}

	public NetlimsOrderTbl getNetlimsOrderTbl() {
		return this.netlimsOrderTbl;
	}

	public void setNetlimsOrderTbl(NetlimsOrderTbl netlimsOrderTbl) {
		this.netlimsOrderTbl = netlimsOrderTbl;
	}

}