function SpecimenDTO(uid, specimenName, unit) {
	
		this.uid=uid;
		this.specimenName=specimenName;
		this.unit=unit;
		
}
SpecimenDTO.prototype.setId = function(uid) {
	this.uid=uid;
}
SpecimenDTO.prototype.setName = function(specimenName) {
	this.specimenName=specimenName;
}
SpecimenDTO.prototype.setUnit= function(unit) {
	this.unit=unit;
}
SpecimenDTO.prototype.getId = function() {
	return this.uid;
}
SpecimenDTO.prototype.getName = function() {
	return this.specimenName;
}
SpecimenDTO.prototype.getUnit= function() {
	return this.unit;
}