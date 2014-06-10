function ReferralUpdateRequestDTO(){

}
ReferralUpdateRequestDTO.prototype.setOrderUid = function(uid) {
	this.orderUid = uid;
}
ReferralUpdateRequestDTO.prototype.setNote = function(note) {
	this.note = note;
}
ReferralUpdateRequestDTO.prototype.setReferralUid = function(referralUid) {
	this.referralUid = referralUid;
}
ReferralUpdateRequestDTO.prototype.setCollectedAt = function(collectedAt) {
	this.collectedAt = collectedAt;
}
ReferralUpdateRequestDTO.prototype.getCollectedAt = function() {
	return this.collectedAt;
}
ReferralUpdateRequestDTO.prototype.getOrderUid = function() {
	return this.orderUid;
}
ReferralUpdateRequestDTO.prototype.getNote = function() {
	return this.note;
}
ReferralUpdateRequestDTO.prototype.getReferralUid = function() {
	return this.referralUid;
}