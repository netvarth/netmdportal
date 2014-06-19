package com.nv.NetlimsPortal.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lab_branch_system_info_tbl database table.
 * 
 */
@Entity
@Table(name="lab_branch_system_info_tbl")
public class LabBranchSystemInfoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="critical_cpu_level", nullable=false)
	private float criticalCpuLevel;

	@Column(name="critical_hard_disk_space_level", nullable=false)
	private float criticalHardDiskSpaceLevel;

	@Column(name="critical_memory_level", nullable=false)
	private float criticalMemoryLevel;

	@Column(name="freq_type", nullable=false, length=1)
	private String freqType;

	@Column(name="interval_time", nullable=false)
	private int intervalTime;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="branch_id", nullable=false)
	private LabBranchTbl labBranchTbl;

	public LabBranchSystemInfoTbl() {
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

	public float getCriticalHardDiskSpaceLevel() {
		return this.criticalHardDiskSpaceLevel;
	}

	public void setCriticalHardDiskSpaceLevel(float criticalHardDiskSpaceLevel) {
		this.criticalHardDiskSpaceLevel = criticalHardDiskSpaceLevel;
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

	public LabBranchTbl getLabBranchTbl() {
		return this.labBranchTbl;
	}

	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}

}