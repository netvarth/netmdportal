function NetOrderDiscountDTO(uid,discValue,amount) {
	if(arguments.length>0){
		this.uid=uid;
		this.discValue=discValue;
		this.amount=amount;
	}	
}
NetOrderDiscountDTO.prototype.setUid = function(uid) {
	this.uid=uid;
}
NetOrderDiscountDTO.prototype.getUid = function() {
	return this.uid;
}
NetOrderDiscountDTO.prototype.setDiscValue = function(discValue) {
	this.discValue=discValue;
}
NetOrderDiscountDTO.prototype.getDiscValue = function() {
	return this.discValue;
}
NetOrderDiscountDTO.prototype.setAmount = function(amount) {
	this.amount=amount;
}
NetOrderDiscountDTO.prototype.getAmount = function() {
	return this.amount;
}