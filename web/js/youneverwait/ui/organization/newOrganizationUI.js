function NewOrganizationUI(OrganzUIStartup) {
	this.orgModal = '#organizationModal';
	this.neworgPage = this.orgModal + " #newOrgForm";
	this.createButton = $j(this.neworgPage + " #btnNewOrgCreate");
	this.cancelButton = $j(this.neworgPage + " #btnNewOrgCancel");
	this.errorHeader = $j(this.neworgPage + " #errorDivHeader");
	this.errorData = $j(this.neworgPage + " #errorDivNewNetmdData");
	this.departmentType=$j(this.neworgPage + " #headofficename");;
	this.inputFields = this.neworgPage + " :input[type=text]";
	this.organizationname=this.neworgPage + " #organizationname";
	this.headofficephone=this.neworgPage + " #headofficephone";
	this.headofficemobile=this.neworgPage + " #headofficemobile";
	this.headofficeaddress=this.neworgPage + " #headofficeaddress";
	this.headofficeemail=this.neworgPage + " #headofficeemail";
	this.ownerfirstname=this.neworgPage + " #ownerfirstname";
	this.ownerlastname=this.neworgPage + " #ownerlastname";
	this.owneraddress=this.neworgPage + " #owneraddress";
	this.ownerphone = this.neworgPage + " #ownerphone";
	this.ownermobile=this.neworgPage + " #ownermobile";
	this.owneremail = this.neworgPage + " #owneremail";
	this.password=this.neworgPage + " #password";
	this.rePassword=this.neworgPage + " #rePassword";
	this.username=this.neworgPage + " #username";
	this.orgUIStartup = OrganzUIStartup;
	this.orgCreationStatus= false;
}

NewOrganizationUI.prototype.getOrgnzCreationStatus = function() {
	return this.orgCreationStatus;
}
NewOrganizationUI.prototype.setOrganzCreationStatus = function(status) {
	this.orgCreationStatus=status;
}
NewOrganizationUI.prototype.getBranchUIStartup = function() {
	return this.orgUIStartup;
}
NewOrganizationUI.prototype.getorgznTableNavigator = function() {
	var orgUIStartup = this.getBranchUIStartup();
	return orgUIStartup.getorgznTableNavigator();
}
NewOrganizationUI.prototype.getOrgnUIService = function() {
	var orgUIStartup = this.getBranchUIStartup();
	return orgUIStartup.getOrgnUIService();
}

NewOrganizationUI.prototype.init = function() {
	self =this;
	self.fillDepartmentList(this.departmentType);
	self.bindEvents();
}

NewOrganizationUI.prototype.clearFields = function() {
	$j(this.neworgPage + " input[type=text]").val("");
	$j(this.neworgPage + " input[type=password]").val("");
	$j(this.neworgPage + " textarea").val("");
}
NewOrganizationUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.inputFields);
	
}
NewOrganizationUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
NewOrganizationUI.prototype.cancel = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	$j(self.orgModal + self.neworgPage + " input[type=text]").val("");	
	$j(self.orgModal).trigger('reveal:close');
	$j(self.orgModal).remove();
	if(self.getOrgnzCreationStatus() == true){
	var exp = new ExpressionListDTO();
	var orgTableNavigator = self.getorgznTableNavigator();
	orgTableNavigator.setExp(exp);
	orgTableNavigator.list("orglist");
		
	}
	self.setOrganzCreationStatus(false);
	self=pageHandler.getActivePage();
}
NewOrganizationUI.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var orgnzion = self.getOrg();
	var orgnzValidator = new OrgValidator();
	var error  = orgnzValidator.validate(orgnzion,self);
	//alert("Error"+error);
	if(error.errorStatus==false) {
		var orgService = self.getOrgnUIService();
		var orgResponse =orgService.createOrgnz(orgnzion);
		//alert(JSON.stringify(orgResponse));
		if(orgResponse.error==null) {
			self.setOrganzCreationStatus(true);
			showTip(constants.ORGANZTNCREATESUCCESS);//For showing the global Tip			
			self.clearFields();
		} else 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(orgResponse.error));
	} else 
		self.createError(error); 
}
NewOrganizationUI.prototype.getOrg = function() {
	var self=this;
	var Organization = new OrganzDTO();
	var netmdname;
	netmdname=$j(self.organizationname).val();
	netmdname=netmdname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerfirstname;
	ownerfirstname=$j(self.ownerfirstname).val();
	ownerfirstname=ownerfirstname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerlastname;
	ownerlastname=$j(self.ownerlastname).val();
	ownerlastname=ownerlastname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	
	
	Organization.setName(netmdname);
	Organization.setownerFirstName(ownerfirstname);
	Organization.setownerLastName(ownerlastname);
	Organization.setownerEmail($j(self.owneremail).val());
	Organization.setownerPhone($j(self.ownerphone).val());
	Organization.setownerMobile($j(self.ownermobile).val());
	Organization.setownerAddress(commonMethodInvoker.nl2br($j(self.owneraddress).val()));
	Organization.setheadOfficeAddress(commonMethodInvoker.nl2br($j(self.headofficeaddress).val()));
	Organization.setheadOfficeName();
	Organization.setheadOfficeEmail($j(self.headofficeemail).val());
	Organization.setheadOfficePhone($j(self.headofficephone).val());
	Organization.setheadOfficeMobile($j(self.headofficemobile).val());
	Organization.setdepartmentType($j(self.departmentType).val());
	Organization.setuserName($j(self.username).val());
	Organization.setpassword($j(self.password).val());
	return Organization;
}

NewOrganizationUI.prototype.fillDepartmentList = function(controlobj) {
	var departmentList=[];
	ajaxProcessor.setUrl(constants.DEPARTMENTLISTURL);
	var enumList=ajaxProcessor.get();
	$j(enumList.enumListDTO).each(function (index, list) {
		if(list.key=="DepartmentTypeEnum") {
			$j(list.enumValues).each(function (enumvalueIndex, enumvalue) {
				departmentList.push(enumvalue);
			});
		}
	});
	$j(departmentList).each(function (Index, List) {
			var freqlist=List;
			$j(controlobj).append('<option  value="'+freqlist+'">'+freqlist+'</option>');
	}); 
}


NewOrganizationUI.prototype.bindEvents = function() {
self = this;	
	self.removecolors(self.neworgPage + " :input");
	//prevent from entering characters
	commonMethodInvoker.validateNumber(self.headofficephone);
	commonMethodInvoker.validateNumber(self.headofficemobile);
	commonMethodInvoker.validateNumber(self.ownerphone);
	commonMethodInvoker.validateNumber(self.ownermobile);

$j(self.orgModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});	
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}	