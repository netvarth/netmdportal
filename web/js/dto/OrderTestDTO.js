function OrderTestDTO() {

}

OrderTestDTO.prototype.setUid = function(uid) {
	this.uid=uid;
}
OrderTestDTO.prototype.getUid = function() {
	return this.uid;
}

OrderTestDTO.prototype.setSpecimenUid = function(specimenUid) {
	this.specimenUid=specimenUid;
}
OrderTestDTO.prototype.getSpecimenUid = function() {
	return this.specimenUid;
}

OrderTestDTO.prototype.setItemId = function(itemId) {
	this.itemId=itemId;
}
OrderTestDTO.prototype.getItemId = function() {
	return this.itemId;
}

OrderTestDTO.prototype.setSpecialTestPkgUid = function(specialTestPkgUid) {
	this.specialTestPkgUid=specialTestPkgUid;
}
OrderTestDTO.prototype.getSpecialTestPkgUid = function() {
	return this.specialTestPkgUid;
}

OrderTestDTO.prototype.setStdRate = function(stdRate) {
	this.stdRate=stdRate;
}
OrderTestDTO.prototype.getStdRate = function() {
	return this.stdRate;
}

OrderTestDTO.prototype.setNetRate = function(netRate) {
	this.netRate=netRate;
}
OrderTestDTO.prototype.getNetRate = function() {
	return this.netRate;
}

OrderTestDTO.prototype.setFixedDiscount = function(fixedDiscount) {
	this.fixedDiscount=fixedDiscount;
}
OrderTestDTO.prototype.getFixedDiscount = function() {
	return this.fixedDiscount;
}

OrderTestDTO.prototype.setDiscount = function(discountDTO) {
	this.discount=discountDTO;
}
OrderTestDTO.prototype.getDiscount = function() {
	return this.discount;
}

OrderTestDTO.prototype.setTestPkg = function(testPkg) {
	this.testPkg=testPkg;
}
OrderTestDTO.prototype.getTestPkg = function() {
	return this.testPkg;
}

OrderTestDTO.prototype.setResult = function(result) {
	this.result=result;
}
OrderTestDTO.prototype.getResult= function() {
	return this.result;
}

OrderTestDTO.prototype.setActionName = function(actionName) {
	this.actionName=actionName;
}
OrderTestDTO.prototype.getActionName= function() {
	return this.actionName;
}