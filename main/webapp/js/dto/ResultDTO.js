function ResultDTO() {
	this.getOrderUid = function() {
		return this.orderUid;
	}
	this.setOrderUid =function(orderUid) {
		this.orderUid=orderUid;
	}	
	this.getResult = function() {
		return this.result;
	}
	this.setResult = function(result) {
		this.result = result;
	}
	this.getResultHtml = function() {
		return this.resultHtml;
	}
	this.setResultHtml = function(resultHtml) {
		this.resultHtml = resultHtml;
	}
}