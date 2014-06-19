function OrderDiscountDetailDTO(){

}
OrderDiscountDetailDTO.prototype.setOrderUid = function(uid) {
	this.orderUid = uid;
}
OrderDiscountDetailDTO.prototype.setNote = function(note) {
	this.note = note;
}
OrderDiscountDetailDTO.prototype.setDiscountDetail = function(discountDetail) {
	this.discountDetail = discountDetail;
}
OrderDiscountDetailDTO.prototype.getDiscountDetail = function() {
	return this.discountDetail;
}
OrderDiscountDetailDTO.prototype.getOrderUid = function() {
	return this.orderUid;
}
OrderDiscountDetailDTO.prototype.getNote = function() {
	return this.note;
}