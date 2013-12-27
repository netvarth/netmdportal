function FacilityDailyPaymentDTO(areaUid,agent,collectedAt) {
	if(arguments.length>0) {
		this.areaUid = areaUid;
		this.agent = agent;
		this.collectedAt = collectedAt;
	}
}

FacilityDailyPaymentDTO.prototype.getArea = function() {
		return this.areaUid ;
}

FacilityDailyPaymentDTO.prototype.setArea=function(areaUid) {
	this.areaUid = areaUid;
}

FacilityDailyPaymentDTO.prototype.getAgent=function() {
		return this.agent;
}
FacilityDailyPaymentDTO.prototype.setAgent=function(agent) {
		this.agent = agent;
}

FacilityDailyPaymentDTO.prototype.getCollectedAt=function() {
		return this.collectedAt;
}

FacilityDailyPaymentDTO.prototype.setCollectedAt=function(collectedAt) {
		this.collectedAt = collectedAt;
}
	
	

