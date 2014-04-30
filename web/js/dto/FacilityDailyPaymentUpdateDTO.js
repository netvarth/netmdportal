function FacilityDailyPaymentUpdateDTO(uid,paidAmount,paidDate) {
	if(arguments.length>0) {
		this.uid = uid;
		this.paidAmount = paidAmount;
		this.paidDate = paidDate;
	}
}

FacilityDailyPaymentUpdateDTO.prototype.getUid = function() {
		return this.uid ;
}

FacilityDailyPaymentUpdateDTO.prototype.setUid=function(uid) {
	this.uid = uid;
}

FacilityDailyPaymentUpdateDTO.prototype.getPaidAmount=function() {
		return this.paidAmount;
}
FacilityDailyPaymentUpdateDTO.prototype.setPaidAmount=function(paidAmount) {
		this.paidAmount = paidAmount;
}
FacilityDailyPaymentUpdateDTO.prototype.getPaidDate=function() {
		return this.paidDate;
}

FacilityDailyPaymentUpdateDTO.prototype.setPaidDate=function(paidDate) {
		this.paidDate = paidDate;
}
	
	

