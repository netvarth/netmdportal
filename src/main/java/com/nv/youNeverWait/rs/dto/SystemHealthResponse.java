/**
 * SystemHealthResponse.java
 * @author netvarth
 *
 * Version 1.0 Oct 29, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

/**
 *
 *
 * @author Luciya Jose
 */
public class SystemHealthResponse {
private HealthMonitorResponse systemHealth = new HealthMonitorResponse();
long freeHardDiskSpaceInPercent;
long freeMemorySpaceInPercent;
long freeCpuSpaceInPercent;
boolean success;

/**
 * @return the success
 */
public boolean isSuccess() {
	return success;
}
/**
 * @param success the success to set
 */
public void setSuccess(boolean success) {
	this.success = success;
}
/**
 * @return the systemHealth
 */
public HealthMonitorResponse getSystemHealth() {
	return systemHealth;
}
/**
 * @param systemHealth the systemHealth to set
 */
public void setSystemHealth(HealthMonitorResponse systemHealth) {
	this.systemHealth = systemHealth;
}
/**
 * @return the freeHardDiskSpaceInPercent
 */
public long getFreeHardDiskSpaceInPercent() {
	return freeHardDiskSpaceInPercent;
}
/**
 * @param freeHardDiskSpaceInPercent the freeHardDiskSpaceInPercent to set
 */
public void setFreeHardDiskSpaceInPercent(long freeHardDiskSpaceInPercent) {
	this.freeHardDiskSpaceInPercent = freeHardDiskSpaceInPercent;
}
/**
 * @return the freeMemorySpaceInPercent
 */
public long getFreeMemorySpaceInPercent() {
	return freeMemorySpaceInPercent;
}
/**
 * @param freeMemorySpaceInPercent the freeMemorySpaceInPercent to set
 */
public void setFreeMemorySpaceInPercent(long freeMemorySpaceInPercent) {
	this.freeMemorySpaceInPercent = freeMemorySpaceInPercent;
}
/**
 * @return the freeCpuSpaceInPercent
 */
public long getFreeCpuSpaceInPercent() {
	return freeCpuSpaceInPercent;
}
/**
 * @param freeCpuSpaceInPercent the freeCpuSpaceInPercent to set
 */
public void setFreeCpuSpaceInPercent(long freeCpuSpaceInPercent) {
	this.freeCpuSpaceInPercent = freeCpuSpaceInPercent;
}

}
