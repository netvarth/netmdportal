function ResultPrintResponseDTO() {
	
}

ResultPrintResponseDTO.prototype.getOrderUid = function() {
	return this.orderUid;
}

ResultPrintResponseDTO.prototype.setOrderUid = function(orderUid) {
	this.orderUid=orderUid;
}

ResultPrintResponseDTO.prototype.getResult = function() {
	return this.result;
}

ResultPrintResponseDTO.prototype.setResult = function(result) {
	this.result=result;
}

ResultPrintResponseDTO.prototype.getReportDate = function() {
	return this.reportingDate;
}

ResultPrintResponseDTO.prototype.setReportDate = function(reportingDate) {
	this.reportingDate=reportingDate;
}

ResultPrintResponseDTO.prototype.getReportTime = function() {
	return this.reportingTime;
}

ResultPrintResponseDTO.prototype.setReportTime = function(reportingTime) {
	this.reportingTime=reportingTime;
}

ResultPrintResponseDTO.prototype.isPrinted = function() {
	return this.printed;
}

ResultPrintResponseDTO.prototype.setPrinted = function(printed) {
	this.printed=printed;
}

ResultPrintResponseDTO.prototype.getTestList= function() {
	return this.testList;
}

ResultPrintResponseDTO.prototype.setTestList= function(testList) {
	this.testList=testList;
}