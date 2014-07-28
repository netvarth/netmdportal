package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the netlims_result_tbl database table.
 * 
 */
@Entity
@Table(name="netlims_result_tbl")
public class NetlimsResultTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Lob
	private String result;

	@Column(name="test_uid")
	private String testUid;
	
	@Column(name="test_name")
	private String testName;
	
	@Column(name="specimen")
	private String specimen;

	@Column(name="item_id")
	private int itemId;
	
	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	//bi-directional many-to-one association to NetlimsOrderTbl
	@ManyToOne
	@JoinColumn(name="portal_order_id")
	private NetlimsOrderTbl netlimsOrderTbl;

	/**
	 * 
	 */
	public NetlimsResultTbl() {
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
	 * @return the testUid
	 */
	public String getTestUid() {
		return testUid;
	}

	/**
	 * @param testUid the testUid to set
	 */
	public void setTestUid(String testUid) {
		this.testUid = testUid;
	}

	/**
	 * @return the netlimsOrderTbl
	 */
	public NetlimsOrderTbl getNetlimsOrderTbl() {
		return netlimsOrderTbl;
	}

	/**
	 * @param netlimsOrderTbl the netlimsOrderTbl to set
	 */
	public void setNetlimsOrderTbl(NetlimsOrderTbl netlimsOrderTbl) {
		this.netlimsOrderTbl = netlimsOrderTbl;
	}

	/**
	 * @return the testName
	 */
	public String getTestName() {
		return testName;
	}

	/**
	 * @param testName the testName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * @return the specimen
	 */
	public String getSpecimen() {
		return specimen;
	}

	/**
	 * @param specimen the specimen to set
	 */
	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}

}