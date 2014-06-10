function BranchSyncDTO(){
	/* this.enableSync;
	this. syncTime;
	this.syncFreqType;
	this.labId;
	this.labBranchId;
	this.netmdId;
	this.netmdBranchId;
	this.netrxId;
	this.netrxBranchId; */
}
BranchSyncDTO.prototype.setenableSync = function(enableSync) {
	this.enableSync=enableSync;
}
BranchSyncDTO.prototype.getlabId = function() {
	return this.enableSync;
}
BranchSyncDTO.prototype.setsyncTime = function(syncTime) {
	this.syncTime=syncTime;
}
BranchSyncDTO.prototype.getsyncTime = function() {
	return this.syncTime;
}
BranchSyncDTO.prototype.setsyncFreqType = function(syncFreqType) {
	this.syncFreqType=syncFreqType;
}
BranchSyncDTO.prototype.getsyncFreqType = function() {
	return this.syncFreqType;
}
BranchSyncDTO.prototype.setlabId = function(labId) {
	this.labId=labId;
}
BranchSyncDTO.prototype.getlabId = function() {
	return this.labId;
}
BranchSyncDTO.prototype.setnetmdId = function(netmdId) {
	this.netmdId=netmdId;
}
BranchSyncDTO.prototype.getnetmdId = function() {
	return this.netmdId;
}

BranchSyncDTO.prototype.setnetrxId = function(netrxId) {
	this.netrxId=netrxId;
}
BranchSyncDTO.prototype.getnetrxId = function() {
	return this.netrxId;
}
BranchSyncDTO.prototype.setlabBranchId = function(labBranchId) {
	this.labBranchId=labBranchId;
}
BranchSyncDTO.prototype.getlabBranchId = function() {
	return this.labBranchId;
}

BranchSyncDTO.prototype.setnetmdBranchId = function(netmdBranchId) {
	this.netmdBranchId=netmdBranchId;
}
BranchSyncDTO.prototype.getnetmdBranchId = function() {
	return this.netmdBranchId;
}
BranchSyncDTO.prototype.setnetrxBranchId = function(netrxBranchId) {
	this.netrxBranchId=netrxBranchId;
}
BranchSyncDTO.prototype.getnetrxBranchId = function() {
	return this.netrxBranchId;
}