function OrderGeneralInfoDTO() {
	this.getOrderUid = funtion() {
		return this.orderUid;
	}
	this.setOrderUid=function(orderUid) {
		this.orderUid = orderUid;
	}
	
	this.getReferralUid = funtion() {
		return this.referralUid;
	}
	this.setReferralUid=function(referralUid) {
		this.referralUid = referralUid;
	}
	
	this.getNote =function(){
		return this.note;
	}
	this.setNote =function(note){
		this.note=note;
	}
	
	this.getCollectedAt = function() {
		return this.collectedAt;
	}
	this.setCollectedAt = function(collectedAt) {
		this.collectedAt = collectedAt;
	}
}