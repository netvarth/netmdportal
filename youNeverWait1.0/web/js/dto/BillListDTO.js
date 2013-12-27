function BillListDTO(){
	
}

BillListDTO.prototype.setfromDate = function(fromDate) {
	this.fromDate=fromDate;
}
BillListDTO.prototype.getfromDate = function() {
	return this.fromDate;
}
BillListDTO.prototype.setToDate = function(toDate) {
	this.toDate=toDate;
}
BillListDTO.prototype.getToDate = function() {
	return this.toDate;
}
BillListDTO.prototype.setnetmdId = function(netmdId) {
	this.netmdId=netmdId;
}
BillListDTO.prototype.getnetmdId = function() {
	return this.netmdId;
}
BillListDTO.prototype.setnetmdBranchId = function(netmdBranchId) {
	this.netmdBranchId=netmdBranchId;
}
BillListDTO.prototype.getlabnetmdBranchId = function() {
	return this.netmdBranchId;
}