function ViewSpecimenUI(specimenUIStartup) {
	this.viewSpecimenPage = "#viewSpecimen";
	this.errorHeader= $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#id label";
	this.name = "#name";
	this.unit = "#unit";
	this.updateButton = '#viewSpecimen #btnSpecimenSave';
	this.editButton = '#viewSpecimen #btnSpecimenEdit';
	this.cancelButton = '#viewSpecimen #btnSpecimenCancel'; 
	this.ptbBack = "#specimenGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#specimenGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#specimenGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.specimenUIStartup=specimenUIStartup;
	this.viewSpecimenPTB = new ViewSpecimenPTB(this);
}

ViewSpecimenUI.prototype.getSpecimenUIStartup = function() {
	return this.specimenUIStartup;
}

ViewSpecimenUI.prototype.getViewSpecimenPTB = function() {
	return this.viewSpecimenPTB;
}

ViewSpecimenUI.prototype.getSpecTableNavigator = function() {
	var specimenUIStartup = this.getSpecimenUIStartup();
	return specimenUIStartup.getSpecTableNavigator();
}

ViewSpecimenUI.prototype.getSpecimenService = function() {
	var specimenUIStartup = this.getSpecimenUIStartup();
	return specimenUIStartup.getSpecimenService();
}
//Set the page title of the specimen ui page
ViewSpecimenUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewSpecimenUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}

ViewSpecimenUI.prototype.init = function(specimenId) {
	var self = this;
	var viewSpecimenPTB = self.getViewSpecimenPTB();
	viewSpecimenPTB.init(self);
	pageHandler.create(constants.VIEWSPECIMENPAGEURL);
	pageHandler.buttons(constants.SPECIMENVIEWINFOBUTTONS, '#' + constants.SPECIMENBUTTONSCONTAINER);
	this.bindEvents();
	this.viewSpecimen(specimenId);
}

ViewSpecimenUI.prototype.viewSpecimen = function(specimenId) {
	self=this;
	var header = constants.SPECIMENVIEWINFO;
	var specimenService = self.getSpecimenService();
	var specimenResponse = specimenService.viewSpecimen(specimenId);
	//alert(JSON.stringify(specimenResponse));
	if(specimenResponse.success==true) {
		var specimen = new SpecimenDTO();
		specimen.setName(specimenResponse.specimenName);
		header+=specimenResponse.specimenName;
		specimen.setUnit(specimenResponse.unit);
		specimen.setId(specimenResponse.uid);
		self.setSpecimen(specimen);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(specimenResponse.error));
	}
	self.setPageTitle(header);
}

ViewSpecimenUI.prototype.setSpecimen = function(specimen) {
	$j(this.viewSpecimenPage + " " + this.id).text(specimen.uid);
	$j(this.viewSpecimenPage + " " + this.name).val(specimen.specimenName);
	if(specimen.unit=="null"||specimen.unit=="")
		$j(this.viewSpecimenPage + " " + this.unit).val('Nil');
	else
		$j(this.viewSpecimenPage + " " + this.unit).val(specimen.unit);
	
}

ViewSpecimenUI.prototype.getSpecimen = function() {
	var id = parseInt($j(this.viewSpecimenPage + " " + this.id).text());
	var name = $j(this.viewSpecimenPage + " " + this.name).val();
	var unit = $j(this.viewSpecimenPage + " " + this.unit).val();	
	var specimen = new SpecimenDTO();
	specimen.setId(id);
	specimen.setName(name);
	specimen.setUnit(unit);
	return specimen;
}

ViewSpecimenUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewSpecimenPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}

ViewSpecimenUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewSpecimenPage + " " + self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewSpecimenPage + " input[type=text]").removeAttr('readonly');
	$j(self.viewSpecimenPage + " input[type=text]").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewSpecimenPage + " input[type=text]").removeAttr('disabled');
}

ViewSpecimenUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewSpecimenPage + " input[type=text]").attr('readonly',true);
	$j(self.viewSpecimenPage + " input[type=text]").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewSpecimenPage + " input[type=text]").attr('disabled',true);
}

ViewSpecimenUI.prototype.getPrevId = function(curId,specimenResult) {
	var prevId;
	//alert(JSON.stringify(specimenResult))
	$j(specimenResult.specimenList).each(function (index, rowSpecimen) {
		if(curId==rowSpecimen.uid)	{
			var arrayLength=(specimenResult.specimenList).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=specimenResult.specimenList[index-1].uid;
		}
	});
	return prevId;	
}
	
ViewSpecimenUI.prototype.getNextId = function(curId,specimenResult) {
	var nextId;
	$j(specimenResult.specimenList).each(function (index, rowspecimen) {
		if(curId==rowspecimen.uid)	{
			var arrayLength=(specimenResult.specimenList).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=specimenResult.specimenList[index+1].uid;
			}	
		}
	});	
	return nextId;	
}

ViewSpecimenUI.prototype.bindEvents = function() {
	self = this;
	
	$j(self.viewSpecimenPage).bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	
	$j(self.cancelButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var specimen = self.getSpecimen();
		self.viewSpecimen(specimen.uid);
		self.readable();
	});
	
	//Update Specimen Button Click Event
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var specimen = self.getSpecimen();
		var specValidator = new SpecimenValidator();
		var error  = specValidator.validate(specimen);
		if(error.errorStatus==false) {
			var specimenService = self.getSpecimenService();
			var specimenResponse = specimenService.updateSpecimen(specimen);
			if(specimenResponse.success==true) {
				showTip(constants.SPECIMENUPDATESUCCESS);//For showing the global Tip
				var specimen = self.getSpecimen();
				self.viewSpecimen(specimen.uid);
				self.readable();
				
				/* global config value set function*/ 
				//configData = methodInvoker.getConfigData();
				
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(specimenResponse.error));
			}
		} else {
			self.createError(error);
		}		
	});
	
	//Edit Specimen Button Click Event
	$j(self.editButton).die('click').live('click',function(){
		clearNilFields(viewSpecimen);
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self.writable();
	});
	
}