function SpecimenListResponseDTO(specimenListResp) {
	this.specimens=specimens;
	this.count=specimenListResp.count;
	this.error=specimenListResp.error;
	this.success=specimenListResp.success;
}

SpecimenDTO.getSpecimens = function() {
	return this.specimens;
}

SpecimenDTO.getCount = function() {
	return this.count;
}

SpecimenDTO.getError= function() {
	return this.error;
}

SpecimenDTO.getSuccess= function() {
	return this.success;
}