function PatientUIStartup() {
	this.patientTabCreator = new PatientTABCreator();
	this.patientAppointmentVw = new PatientAppointmentVw();
	this.patientResultVw = new PatientResultVw();
	this.patientClinicsVw = new PatientClinicsView();
}

PatientUIStartup.prototype.getPatientTabCreator = function() {
	return this.patientTabCreator;
}

PatientUIStartup.prototype.getPatientAppointmentVw = function() {
	return this.patientAppointmentVw;
}

PatientUIStartup.prototype.getPatientResultVw = function() {
	return this.patientResultVw;
}
PatientUIStartup.prototype.getpatientClinicsVw = function() {
	return this.patientClinicsVw;
}

PatientUIStartup.prototype.init = function() {
	self = this;
	var patientTab = self.getPatientTabCreator();
	var patientApptab = self.getPatientAppointmentVw();
	var patientResulttab = self.getPatientResultVw();
	var patientClinicstab = self.getpatientClinicsVw();
	patientTab.create(constant.PATIENTTABCREATEVIEWURL,constant.PARENTTABOBJ);
	patientApptab.viewappointment(constant.PATIENTAPOINTMENTVIEWURL,constant.PATIENTPASTAPOINTMENTVIEWURL,constant.PATIENTAPPOINTMENTOBJ);
	patientClinicstab.viewclinics(constant.PATIENTCLINICSOBJ);
	patientResulttab.viewresult(constant.PATIENTRESULTVIEWURL,constant.PATIENTRESULTOBJ);
	}
	
PatientUIStartup.prototype.initsel = function() {
	self = this;
	var patientApptab = self.getPatientAppointmentVw();
	var patientResulttab = self.getPatientResultVw();
	var patientClinicstab = self.getpatientClinicsVw();
	patientApptab.viewappointment(constant.PATIENTAPOINTMENTVIEWURL,constant.PATIENTPASTAPOINTMENTVIEWURL,constant.PATIENTAPPOINTMENTOBJ);
	patientClinicstab.viewclinics(constant.PATIENTCLINICSOBJ);
	patientResulttab.viewresult(constant.PATIENTRESULTVIEWURL,constant.PATIENTRESULTOBJ);
	}	