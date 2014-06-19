function PatientDTO (patient) {
	if(arguments.length==1) {
		this.honorifics = patient.honorifics;
		this.name = patient.name;
		this.phone = patient.phone;
		this.mobile = patient.mobile;
		this.age = patient.age;
		this.email = patient.email;
		this.gender = patient.gender;
	}	
}
PatientDTO.prototype.setGender = function(gender) {
	this.gender=gender;
}
PatientDTO.prototype.getGender = function() {
	return this.gender;
}

PatientDTO.prototype.setPhone = function(phone) {
	this.phone=phone;
}
PatientDTO.prototype.getPhone = function() {
	return this.phone;
}

PatientDTO.prototype.setMobile = function(mobile) {
	this.mobile=mobile;
}
PatientDTO.prototype.getMobile = function() {
	return this.mobile;
}

PatientDTO.prototype.setAge = function(age) {
	this.age=age;
}
PatientDTO.prototype.getAge = function() {
	return this.age;
}

PatientDTO.prototype.setHonorifics = function(honorifics) {
	this.honorifics=honorifics;
}
PatientDTO.prototype.getHonorifics = function() {
	return this.honorifics;
}

PatientDTO.prototype.setName = function(name) {
	this.name=name;
}
PatientDTO.prototype.getName = function() {
	return this.name;
}
PatientDTO.prototype.setEmail = function(email) {
	this.email=email;
}
PatientDTO.prototype.getEmail = function() {
	return this.email;
}