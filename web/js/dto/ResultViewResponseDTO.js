function ResultViewResponseDTO() {
	
}

ResultViewResponseDTO.prototype.getOrderUid = function() {
	return this.orderUid;
}

ResultViewResponseDTO.prototype.setOrderUid = function(orderUid) {
	this.orderUid=orderUid;
}

ResultViewResponseDTO.prototype.getResult = function() {
	return this.result;
}

ResultViewResponseDTO.prototype.setResult = function(result) {
	this.result=result;
}

ResultViewResponseDTO.prototype.getPrintResult = function() {
	return this.printResult;
}

ResultViewResponseDTO.prototype.setPrintResult = function(printResult) {
	this.printResult=printResult;
}

ResultViewResponseDTO.prototype.getReportDate = function() {
	return this.reportDate;
}

ResultViewResponseDTO.prototype.setReportDate = function(reportDate) {
	this.reportDate=reportDate;
}

ResultViewResponseDTO.prototype.getReportTime = function() {
	return this.reportTime;
}

ResultViewResponseDTO.prototype.setReportTime = function(reportTime) {
	this.reportTime=reportTime;
}

ResultViewResponseDTO.prototype.isPrinted = function() {
	return this.printed;
}

ResultViewResponseDTO.prototype.setPrinted = function(printed) {
	this.printed=printed;
}

ResultViewResponseDTO.prototype.getTestList= function() {
	return this.testList;
}

ResultViewResponseDTO.prototype.setTestList= function(testList) {
	this.testList=testList;
}

ResultViewResponseDTO.prototype.getError= function() {
	return this.error;
}

ResultViewResponseDTO.prototype.setError= function(error) {
	this.error=error;
}

ResultViewResponseDTO.prototype.getSuccess= function() {
	return this.success;
}

ResultViewResponseDTO.prototype.setSuccess= function(success) {
	this.success=success;
}