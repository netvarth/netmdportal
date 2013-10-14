package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * The persistent class for the health_monitor_tbl database table.
 * 
 */
@Entity
@Table(name="health_monitor_tbl")
public class HealthMonitorTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="cpu_usage", nullable=false)
	private float cpuUsage;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date_time", nullable=false)
	private Date createdDateTime;

	@Column(name="freq_type", nullable=false, length=45)
	private String freqType;

	@Column(name="hard_disk_usage", nullable=false)
	private float hardDiskUsage;

	@Column(name="interval_time", nullable=false)
	private int intervalTime;

	@Column(name="memory_usage", nullable=false)
	private float memoryUsage;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date_time", nullable=false)
	private Date updatedDateTime;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="lab_branch_id", nullable=false)
	private LabBranchTbl labBranchTbl;

	public HealthMonitorTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getCpuUsage() {
		return this.cpuUsage;
	}

	public void setCpuUsage(float cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public Date getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getFreqType() {
		return this.freqType;
	}

	public void setFreqType(String freqType) {
		this.freqType = freqType;
	}

	public float getHardDiskUsage() {
		return this.hardDiskUsage;
	}

	public void setHardDiskUsage(float hardDiskUsage) {
		this.hardDiskUsage = hardDiskUsage;
	}

	public int getIntervalTime() {
		return this.intervalTime;
	}

	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}

	public float getMemoryUsage() {
		return this.memoryUsage;
	}

	public void setMemoryUsage(float memoryUsage) {
		this.memoryUsage = memoryUsage;
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