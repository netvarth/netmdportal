package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the health_monitor_tbl database table.
 * 
 */
@Entity
@Table(name = "health_monitor_tbl")
public class HealthMonitorTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(name = "cpu_usage", nullable = false)
	private int cpuUsage;

	@Column(name = "freq_type", nullable = false)
	private String freqType;

	@Column(name = "hard_disk_usage", nullable = false)
	private int hardDiskUsage;

	@Column(name = "interval_time", nullable = false)
	private int intervalTime;

	// bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name = "lab_branch_id", nullable = false)
	private LabBranchTbl labBranchTbl;

	@Column(name = "memory_usage", nullable = false)
	private int memoryUsage;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_date_time", nullable=false)
	private Date createDateTime;


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_date_time", nullable=false)
	private Date updateDateTime;
    
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
	
	public HealthMonitorTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCpuUsage() {
		return this.cpuUsage;
	}

	public void setCpuUsage(int cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public String getFreqType() {
		return this.freqType;
	}

	public void setFreqType(String freqType) {
		this.freqType = freqType;
	}

	public int getHardDiskUsage() {
		return this.hardDiskUsage;
	}

	public void setHardDiskUsage(int hardDiskUsage) {
		this.hardDiskUsage = hardDiskUsage;
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

	public int getMemoryUsage() {
		return this.memoryUsage;
	}

	public void setMemoryUsage(int memoryUsage) {
		this.memoryUsage = memoryUsage;
	}

}