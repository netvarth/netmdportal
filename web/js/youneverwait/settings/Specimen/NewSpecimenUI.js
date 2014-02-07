function NewSpecimenUI(specimenUIStartup) {
	this.createButton = $j("#specimenModal #btnSpecimenSave");
	this.cancelButton = $j('#specimenModal #btnSpecimenCancel');
	this.newSpecimenPage = "#newSpecimen";
	this.specimenModal = '#specimenModal';
	this.errorHeader = $j('#specimenModal #errorDivHeader');
	this.errorData = $j('#specimenModal #errorDivNewSpecimenData');
	this.name="#name";
	this.unit="#unit";
	this.inputFields = ":input";
	this.specimenUIStartup = specimenUIStartup;
}

NewSpecimenUI.prototype.getSpecimenUIStartup = function() {
	return this.specimenUIStartup;
}

NewSpecimenUI.prototype.getSpecTableNavigator = function() {
	var specimenUIStartup = this.getSpecimenUIStartup();
	return specimenUIStartup.getSpecTableNavigator();
}

NewSpecimenUI.prototype.getSpecimenService = function() {
	var specimenUIStartup = this.getSpecimenUIStartup();
	return specimenUIStartup.getSpecimenService();
}

NewSpecimenUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewSpecimenUI.prototype.getSpecimen = function() {
	var name = $j(this.newSpecimenPage + " " + this.name).val();
	var unit = $j(this.newSpecimenPage + " " + this.unit).val();	
	var uid=0;
	var specimen = new SpecimenDTO();
	specimen.setName(name);
	specimen.setUnit(unit);
	//specimen.setId(uid);
	return specimen;
}

NewSpecimenUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newSpecimenPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}

NewSpecimenUI.prototype.clearFields = function() {
	$j(this.newSpecimenPage + " input[type=text]").val("");
}

NewSpecimenUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newSpecimenPage + " " + self.inputFields);
	
	$j(self.newSpecimenPage).bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	
	$j(self.specimenModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.specimenModal + self.newSpecimenPage + " input[type=text]").val("");	
		$j(self.specimenModal).trigger('reveal:close');
		$j(self.specimenModal).remove();
		self=self.getSpecimenUIStartup();
	});
	
	
	
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.specimenModal + self.newSpecimenPage + " input[type=text]").val("");	
		$j(self.specimenModal).trigger('reveal:close');
		$j(self.specimenModal).remove();
		self=self.getSpecimenUIStartup();
	});
	
	self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var specimen = self.getSpecimen();
		var specValidator = new SpecimenValidator();
		var error  = specValidator.validate(specimen);
		if(error.errorStatus==false) {
			var specimenService = self.getSpecimenService();
			var specimenResponse = specimenService.createSpecimen(specimen);
			if(specimenResponse.success==true) {
				showTip(constants.SPECIMENCREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var specTableNavigator = self.getSpecTableNavigator();
				specTableNavigator.list();
				/* global config value set function*/ 
				//methodInvoker.setConfigData();
				//configData = methodInvoker.getConfigData();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(specimenResponse.error));
			}
		} else {
			self.createError(error);
		}		
	});
	
}