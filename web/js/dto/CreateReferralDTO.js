function CreateReferralDTO() {

}
CreateReferralDTO.prototype.setReferral= function(referral) {
	this.referral=referral;
}
CreateReferralDTO.prototype.getReferral = function() {
	return this.referral;
}
CreateReferralDTO.prototype.setTestDiscount = function(testDiscount) {
	this.testDiscount=testDiscount;
}
CreateReferralDTO.prototype.gettestDiscount = function() {
	return this.testDiscount;
}