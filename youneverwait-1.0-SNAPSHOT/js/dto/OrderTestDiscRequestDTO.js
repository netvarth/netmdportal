function OrderTestDiscRequestDTO(){
	
}

OrderTestDiscRequestDTO.prototype.setTestUid = function(testUid) {
	this.testUid = testUid;
}
OrderTestDiscRequestDTO.prototype.setBlanketOrderUid = function(blanketOrderUid) {
	this.blanketOrderUid = blanketOrderUid;
}
OrderTestDiscRequestDTO.prototype.setTestPkg = function(testPkg) {
	this.testPkg = testPkg;
}
OrderTestDiscRequestDTO.prototype.setFacilityUid = function(facilityUid) {
	this.facilityUid = facilityUid;
}
OrderTestDiscRequestDTO.prototype.setBranchUid = function(branchUid) {
	this.branchUid = branchUid;
}
OrderTestDiscRequestDTO.prototype.setReferralUid = function(referralUid) {
	this.referralUid = referralUid;
}
OrderTestDiscRequestDTO.prototype.setCollectedAt = function(collectedAt) {
	this.collectedAt=collectedAt;
}
OrderTestDiscRequestDTO.prototype.setCollectedAtDiscount = function(collectedAtDiscount) {
	this.collectedAtDiscount =collectedAtDiscount
}