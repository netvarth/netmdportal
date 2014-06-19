function PatientUpdateRequestDTO(){

}
PatientUpdateRequestDTO.prototype.setOrderUid = function(uid) {
	this.orderUid = uid;
}
PatientUpdateRequestDTO.prototype.setNote = function(note) {
	this.note = note;
}
PatientUpdateRequestDTO.prototype.setPatient = function(patient) {
	this.patient = patient;
}
PatientUpdateRequestDTO.prototype.getOrderUid = function() {
	return this.orderUid;
}
PatientUpdateRequestDTO.prototype.getNote = function() {
	return this.note;
}
PatientUpdateRequestDTO.prototype.getPatient = function() {
	return this.patient;
}