function ResultHeaderDTO() {
	this.getOrderId = function() {
		return this.orderId;
	}
	this.setOrderId =function(orderId) {
		this.orderId=orderId;
	}	
	this.getPatientName = function() {
		return this.patientName;
	}
	this.setPatientName = function(patientName) {
		this.patientName = patientName;
	}
	this.getAge = function() {
		return this.age;
	}
	this.setAge = function(age) {
		this.age = age;
	}
	this.getReferral = function() {
		return this.referral;
	}
	this.setReferral = function(referral) {
		this.referral = referral;
	}
	this.getSpecimen = function() {
		return this.specimen;
	}
	this.setSpecimen = function(specimen) {
		this.specimen = specimen;
	}
	this.getGender = function() {
		return this.gender;
	}
	this.setGender = function(gender) {
		this.gender = gender;
	}
	this.getDate = function() {
		return this.date;
	}
	this.setDate = function(date) {
		this.date = date;
	}
	this.getTime = function() {
		return this.time;
	}
	this.setTime = function(time) {
		this.time = time;
	}
	this.getCollectedAt = function() {
		return this.collectedAt;
	}
	this.setCollectedAt = function(collectedAt) {
		this.collectedAt = collectedAt;
	}
}