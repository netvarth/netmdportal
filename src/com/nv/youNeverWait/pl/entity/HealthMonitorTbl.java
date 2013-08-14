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

	@Column(name = "freq_period", nullable = false)
	private String freqPeriod;

	@Column(name = "hard_disk_used", nullable = false)
	private int hardDiskUsed;

	@Column(name = "interval_time", nullable = false)
	private int intervalTime;

	// bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name = "lab_branch_id", nullable = false)
	private LabBranchTbl labBranchTbl;

	@Column(name = "memory_used", nullable = false)
	private int memoryUsed;

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

	public String getFreqPeriod() {
		return this.freqPeriod;
	}

	public void setFreqPeriod(String freqPeriod) {
		this.freqPeriod = freqPeriod;
	}

	public int getHardDiskUsed() {
		return this.hardDiskUsed;
	}

	public void setHardDiskUsed(int hardDiskUsed) {
		this.hardDiskUsed = hardDiskUsed;
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

	public int getMemoryUsed() {
		return this.memoryUsed;
	}

	public void setMemoryUsed(int memoryUsed) {
		this.memoryUsed = memoryUsed;
	}

}