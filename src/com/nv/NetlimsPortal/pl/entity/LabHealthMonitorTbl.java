package com.nv.NetlimsPortal.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the lab_health_monitor_tbl database table.
 * 
 */
@Entity
@Table(name="lab_health_monitor_tbl")
public class LabHealthMonitorTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date_time", nullable=false)
	private Date createdDateTime;

	@Column(name="free_cpu_space", nullable=false)
	private float freeCpuSpace;

	@Column(name="free_hard_disk_space", nullable=false)
	private float freeHardDiskSpace;

	@Column(name="free_memory_space", nullable=false)
	private float freeMemorySpace;

	@Column(name="total_cpu", nullable=false)
	private float totalCpu;

	@Column(name="total_hard_disk", nullable=false)
	private float totalHardDisk;

	@Column(name="total_memory", nullable=false)
	private float totalMemory;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date_time", nullable=false)
	private Date updatedDateTime;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="lab_branch_id", nullable=false)
	private LabBranchTbl labBranchTbl;

	public LabHealthMonitorTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public float getFreeCpuSpace() {
		return this.freeCpuSpace;
	}

	public void setFreeCpuSpace(float freeCpuSpace) {
		this.freeCpuSpace = freeCpuSpace;
	}

	public float getFreeHardDiskSpace() {
		return this.freeHardDiskSpace;
	}

	public void setFreeHardDiskSpace(float freeHardDiskSpace) {
		this.freeHardDiskSpace = freeHardDiskSpace;
	}

	public float getFreeMemorySpace() {
		return this.freeMemorySpace;
	}

	public void setFreeMemorySpace(float freeMemorySpace) {
		this.freeMemorySpace = freeMemorySpace;
	}

	public float getTotalCpu() {
		return this.totalCpu;
	}

	public void setTotalCpu(float totalCpu) {
		this.totalCpu = totalCpu;
	}

	public float getTotalHardDisk() {
		return this.totalHardDisk;
	}

	public void setTotalHardDisk(float totalHardDisk) {
		this.totalHardDisk = totalHardDisk;
	}

	public float getTotalMemory() {
		return this.totalMemory;
	}

	public void setTotalMemory(float totalMemory) {
		this.totalMemory = totalMemory;
	}

	public Date getUpdatedDateTime() {
		return this.updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public LabBranchTbl getLabBranchTbl() {
		return this.labBranchTbl;
	}

	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}

}