package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the netmd_branch_system_info_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_branch_system_info_tbl")
public class NetmdBranchSystemInfoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="critical_cpu_level", nullable=false)
	private float criticalCpuLevel;

	@Column(name="critical_hard_disk_level", nullable=false)
	private float criticalHardDiskLevel;

	@Column(name="critical_memory_level", nullable=false)
	private float criticalMemoryLevel;

	@Column(name="freq_type", length=1)
	private String freqType;

	@Column(name="interval_time")
	private int intervalTime;

	//bi-directional many-to-one association to NetmdPassphraseTbl
	@ManyToOne
	@JoinColumn(name="passphrase_id", nullable=false)
	private NetmdPassphraseTbl netmdPassphraseTbl;

	//bi-directional many-to-one association to NetmdBranchTbl
	@ManyToOne
	@JoinColumn(name="branch_id", nullable=false)
	private NetmdBranchTbl netmdBranchTbl;

	public NetmdBranchSystemInfoTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getCriticalCpuLevel() {
		return this.criticalCpuLevel;
	}

	public void setCriticalCpuLevel(float criticalCpuLevel) {
		this.criticalCpuLevel = criticalCpuLevel;
	}

	public float getCriticalHardDiskLevel() {
		return this.criticalHardDiskLevel;
	}

	public void setCriticalHardDiskLevel(float criticalHardDiskLevel) {
		this.criticalHardDiskLevel = criticalHardDiskLevel;
	}

	public float getCriticalMemoryLevel() {
		return this.criticalMemoryLevel;
	}

	public void setCriticalMemoryLevel(float criticalMemoryLevel) {
		this.criticalMemoryLevel = criticalMemoryLevel;
	}

	public String getFreqType() {
		return this.freqType;
	}

	public void setFreqType(String freqType) {
		this.freqType = freqType;
	}

	public int getIntervalTime() {
		return this.intervalTime;
	}

	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}

	public NetmdPassphraseTbl getNetmdPassphraseTbl() {
		return this.netmdPassphraseTbl;
	}

	public void setNetmdPassphraseTbl(NetmdPassphraseTbl netmdPassphraseTbl) {
		this.netmdPassphraseTbl = netmdPassphraseTbl;
	}

	public NetmdBranchTbl getNetmdBranchTbl() {
		return this.netmdBranchTbl;
	}

	public void setNetmdBranchTbl(NetmdBranchTbl netmdBranchTbl) {
		this.netmdBranchTbl = netmdBranchTbl;
	}

}