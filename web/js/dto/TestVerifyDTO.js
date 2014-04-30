function TestVerifyDTO() {
	this.setOrderUid = function(orderUid) {
		this.orderUid = orderUid;
	}
	this.getOrderUid = function() {
		return this.orderUid;
	}	
	this.setTestList = function(testList){
		this.testList=testList;
	}	
	this.getTestList = function() {
		return this.testList;
	}
}
