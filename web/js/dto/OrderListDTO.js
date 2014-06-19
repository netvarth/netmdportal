function OrderListDTO(){
	
}

OrderListDTO.prototype.setfromDate = function(fromDate) {
	this.fromDate=fromDate;
}
OrderListDTO.prototype.getfromDate = function() {
	return this.fromDate;
}
OrderListDTO.prototype.setToDate = function(toDate) {
	this.toDate=toDate;
}
OrderListDTO.prototype.getToDate = function() {
	return this.toDate;
}
OrderListDTO.prototype.setlabId = function(labId) {
	this.labId=labId;
}
OrderListDTO.prototype.getlabId = function() {
	return this.labId;
}
OrderListDTO.prototype.setlabBranchId = function(labBranchId) {
	this.labBranchId=labBranchId;
}
OrderListDTO.prototype.getlabBranchId = function() {
	return this.labBranchId;
}