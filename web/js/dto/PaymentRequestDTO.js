function PaymentRequestDTO(){

}
PaymentRequestDTO.prototype.setUid = function(uid) {
	this.uid = uid;
}
PaymentRequestDTO.prototype.setPaidAmount = function(paidAmount) {
	this.paidAmount = paidAmount;
}
PaymentRequestDTO.prototype.setNote = function(note) {
	this.note = note;
}
PaymentRequestDTO.prototype.getUid = function() {
	return this.uid;
}
PaymentRequestDTO.prototype.getPaidAmount = function() {
	return this.paidAmount;
}
PaymentRequestDTO.prototype.getNote = function() {
	return this.note;
}