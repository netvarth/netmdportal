function DiscountDetailDTO(){

}
DiscountDetailDTO.prototype.setActionName = function(actionName) {
	this.actionName = actionName;
}
DiscountDetailDTO.prototype.setDiscount = function(discount) {
	this.discount = discount;
}
DiscountDetailDTO.prototype.getActionName = function() {
	return this.actionName;
}
DiscountDetailDTO.prototype.getDiscount = function() {
	return this.discount;
}