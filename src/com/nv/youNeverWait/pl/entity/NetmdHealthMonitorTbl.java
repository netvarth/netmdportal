package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the netmd_health_monitor_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_health_monitor_tbl")
public class NetmdHealthMonitorTbl implements Serializable {
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

	@Column(name="total_cpu_space", nullable=false)
	private float totalCpuSpace;

	@Column(name="total_hard_disk_space", nullable=false)
	private float totalHardDiskSpace;

	@Column(name="total_memory_space", nullable=false)
	private float totalMemorySpace;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date_time", nullable=false)
	private Date updateDateTime;

	//bi-directional many-to-one association to NetmdPassphraseTbl
	@ManyToOne
	@JoinColumn(name="netmd_passphrase_id", nullable=false)
	private NetmdPassphraseTbl netmdPassphraseTbl;

	public NetmdHealthMonitorTbl() {
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

	public float getTotalCpuSpace() {
		return this.totalCpuSpace;
	}

	public void setTotalCpuSpace(float totalCpuSpace) {
		this.totalCpuSpace = totalCpuSpace;
	}

	public float getTotalHardDiskSpace() {
		return this.totalHardDiskSpace;
	}

	public void setTotalHardDiskSpace(float totalHardDiskSpace) {
		this.totalHardDiskSpace = totalHardDiskSpace;
	}

	public float getTotalMemorySpace() {
		return this.totalMemorySpace;
	}

	public void setTotalMemorySpace(float totalMemorySpace) {
		this.totalMemorySpace = totalMemorySpace;
	}

	public Date getUpdateDateTime() {
		return this.updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public NetmdPassphraseTbl getNetmdPassphraseTbl() {
		return this.netmdPassphraseTbl;
	}

	public void setNetmdPassphraseTbl(NetmdPassphraseTbl netmdPassphraseTbl) {
		this.netmdPassphraseTbl = netmdPassphraseTbl;
	}

}