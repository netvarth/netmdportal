function HealthMonitorDTO() {
	if(arguments.length>0){
		this.healthMonitorList=[];
	}	
}
HealthMonitorDTO.prototype.setbranchId= function(branchId) {
	this.branchId=branchId;
}
HealthMonitorDTO.prototype.getbranchId = function() {
	return this.branchId;
}
HealthMonitorDTO.prototype.setcriticalCpuLevel= function(criticalCpuLevel) {
	this.criticalCpuLevel=criticalCpuLevel;
}
HealthMonitorDTO.prototype.getcriticalCpuLevel = function() {
	return this.criticalCpuLevel;
}
HealthMonitorDTO.prototype.setcriticalMemoryLevel= function(criticalMemoryLevel) {
	this.criticalMemoryLevel=criticalMemoryLevel;
}
HealthMonitorDTO.prototype.getcriticalMemoryLevel = function() {
	return this.criticalMemoryLevel;
}
HealthMonitorDTO.prototype.setcriticalHardDiskSpaceLevel = function(criticalHardDiskSpaceLevel) {
	this.criticalHardDiskSpaceLevel=criticalHardDiskSpaceLevel;
}
HealthMonitorDTO.prototype.getcriticalHardDiskSpaceLevel = function() {
	return this.criticalHardDiskSpaceLevel;
}
HealthMonitorDTO.prototype.setfreqType= function(freqType) {
	this.freqType=freqType;
}
HealthMonitorDTO.prototype.getfreqType = function() {
	return this.freqType;
}
HealthMonitorDTO.prototype.setintervalTime = function(intervalTime) {
	this.intervalTime=intervalTime;
}
HealthMonitorDTO.prototype.getintervalTime = function() {
	return this.intervalTime;
}
HealthMonitorDTO.prototype.setbranchName = function(branchName) {
	this.branchName=branchName;
}
HealthMonitorDTO.prototype.getbranchName = function() {
	return this.branchName;
}
HealthMonitorDTO.prototype.sethealthMonitorList= function(healthMonitorList) {
	this.healthMonitorList=healthMonitorList;
}
HealthMonitorDTO.prototype.gethealthMonitorList = function() {
	return this.healthMonitorList;
}