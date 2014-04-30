function SpecimenUpdateRequestDTO(){

}
SpecimenUpdateRequestDTO.prototype.setOrderUid = function(uid) {
	this.orderUid = uid;
}
SpecimenUpdateRequestDTO.prototype.getOrderUid = function() {
	return this.orderUid;
}
SpecimenUpdateRequestDTO.prototype.setSpecimen = function(specimen) {
	this.specimen = specimen;
}
SpecimenUpdateRequestDTO.prototype.getSpecimen = function() {
	return this.specimen;
}