function TestResultDTO() {
	this.setOrderUid = function(orderUid) {
		this.orderUid = orderUid;
	}
	this.getOrderUid = function() {
		return this.orderUid;
	}	
	this.isCompleted = function(){
		return this.completed;
	}
	this.setCompleted = function(completed){
		this.completed=completed;
	}
	this.setTestResultList = function(testResultList){
		this.testResultList=testResultList;
	}	
	this.getTestResultList = function() {
		return this.testResultList;
	}
	this.setTestRemarkStatus = function(testRemarkStatus){
		this.testRemarkStatus=testRemarkStatus;
	}	
	this.getTestRemarkStatus = function() {
		return this.testRemarkStatus;
	}
}

