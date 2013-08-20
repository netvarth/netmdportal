package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lab_branch_system_tbl database table.
 * 
 */
@Entity
@Table(name="lab_branch_system_tbl")
public class LabBranchSystemTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="critica_hard_disk_space_level",nullable=false)
	private int criticaHardDiskSpaceLevel;

	@Column(name="critical_cpu_level",nullable=false)
	private int criticalCpuLevel;

	@Column(name="critical_memory_level",nullable=false)
	private int criticalMemoryLevel;

	@Column(name="freq_type",nullable=false)
	private String freqType;

	@Column(name="interval_time",nullable=false)
	private int intervalTime;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="branch_id",nullable=false)
	private LabBranchTbl labBranchTbl;

	public LabBranchSystemTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCriticaHardDiskSpaceLevel() {
		return this.criticaHardDiskSpaceLevel;
	}

	public void setCriticaHardDiskSpaceLevel(int criticaHardDiskSpaceLevel) {
		this.criticaHardDiskSpaceLevel = criticaHardDiskSpaceLevel;
	}

	public int getCriticalCpuLevel() {
		return this.criticalCpuLevel;
	}

	public void setCriticalCpuLevel(int criticalCpuLevel) {
		this.criticalCpuLevel = criticalCpuLevel;
	}

	public int getCriticalMemoryLevel() {
		return this.criticalMemoryLevel;
	}

	public void setCriticalMemoryLevel(int criticalMemoryLevel) {
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