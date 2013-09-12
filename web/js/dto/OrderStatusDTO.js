function OrderStatusDTO(uid, status) {
	this.uid=uid;
	this.status=status;
}
OrderStatusDTO.prototype.setUid = function(uid) {
	this.uid=uid;
}
OrderStatusDTO.prototype.getUid = function() {
	return this.uid;
}
OrderStatusDTO.prototype.setStatus = function(status) {
	this.status=status;
}
OrderStatusDTO.prototype.getStatus = function() {
	return this.status;
}