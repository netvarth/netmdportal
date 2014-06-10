function NewNetrxUI(NetrxUIStartup) {
	this.netrxModal = '#netrxmodal';
	this.newnetrxPage = this.netrxModal + " #newNetrxForm";
	this.createButton = $j(this.newnetrxPage + " #btnNewNetRXCreate");
	this.cancelButton = $j(this.newnetrxPage + " #btnNewNetRXCancel");
	this.errorHeader = $j(this.newnetrxPage + " #errorDivHeader");
	this.errorData = $j(this.newnetrxPage + " #errorDivNewNetrxData");
	this.headofficename=$j(this.newnetrxPage + " #headofficename");;
	this.inputFields = this.newnetrxPage + " :input[type=text]";
	this.organizationname=this.newnetrxPage + " #organizationname";
	this.headofficephone=this.newnetrxPage + " #headofficephone";
	this.headofficemobile=this.newnetrxPage + " #headofficemobile";
	this.headofficeaddress=this.newnetrxPage + " #headofficeaddress";
	this.headofficeemail=this.newnetrxPage + " #headofficeemail";
	this.ownerfirstname=this.newnetrxPage + " #ownerfirstname";
	this.ownerlastname=this.newnetrxPage + " #ownerlastname";
	this.owneraddress=this.newnetrxPage + " #owneraddress";
	this.ownerphone = this.newnetrxPage + " #ownerphone";
	this.ownermobile=this.newnetrxPage + " #ownermobile";
	this.owneremail = this.newnetrxPage + " #owneremail";
	this.password=this.newnetrxPage + " #password";
	this.rePassword=this.newnetrxPage + " #rePassword";
	this.username=this.newnetrxPage + " #username";
	this.netrxUIStartup = NetrxUIStartup;
	this.netrxCreationStatus= false;
}

NewNetrxUI.prototype.getNetrxCreationStatus = function() {
	return this.netrxCreationStatus;
}
NewNetrxUI.prototype.setNetrxCreationStatus = function(status) {
	this.netrxCreationStatus=status;
}
NewNetrxUI.prototype.getBranchUIStartup = function() {
	return this.netrxUIStartup;
}
NewNetrxUI.prototype.getnetrxTableNavigator = function() {
	var netrxUIStartup = this.getBranchUIStartup();
	return netrxUIStartup.getnetrxTableNavigator();
}
NewNetrxUI.prototype.getNetrxUIService = function() {
	var netrxUIStartup = this.getBranchUIStartup();
	return netrxUIStartup.getNetrxUIService();
}

NewNetrxUI.prototype.init = function() {
	self =this;
	self.bindEvents();
}

NewNetrxUI.prototype.clearFields = function() {
	$j(this.newnetrxPage + " input[type=text]").val("");
	$j(this.newnetrxPage + " input[type=password]").val("");
	$j(this.newnetrxPage + " textarea").val("");
}
NewNetrxUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.inputFields);
	
}
NewNetrxUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
NewNetrxUI.prototype.cancel = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	$j(self.netrxModal + self.newnetrxPage + " input[type=text]").val("");	
	$j(self.netrxModal).trigger('reveal:close');
	$j(self.netrxModal).remove();
	if(self.getNetrxCreationStatus() == true){
	var exp = new ExpressionListDTO();
	var netrxTableNavigator = self.getnetrxTableNavigator();
	netrxTableNavigator.setExp(exp);
	netrxTableNavigator.list();
		
	}
	self.setNetrxCreationStatus(false);
	self=pageHandler.getActivePage();
}
NewNetrxUI.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var netrx = self.getNetrx();
	var netrxValidator = new NetrxValidator();
	var error  = netrxValidator.validate(netrx,self);
	//alert("Error"+error);
	if(error.errorStatus==false) {
		var netrxService = self.getNetrxUIService();
		var netrxResponse =netrxService.createNetrx(netrx);
		//alert(JSON.stringify(netrxResponse));
		if(netrxResponse.error==null) {
			self.setNetrxCreationStatus(true);
			showTip(constants.NETRXCREATESUCCESS);//For showing the global Tip			
			self.clearFields();
		} else 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netrxResponse.error));
	} else 
		self.createError(error); 
}
NewNetrxUI.prototype.getNetrx = function() {
	var self=this;
	var netrx = new NetrxDTO();
	var netrxname;
	netrxname=$j(self.organizationname).val();
	netrxname=netrxname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
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
	var headofficename;
	headofficename= $j(self.headofficename).val();
	headofficename=headofficename.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	
	netrx.setName(netrxname);
	netrx.setownerFirstName(ownerfirstname);
	netrx.setownerLastName(ownerlastname);
	netrx.setownerEmail($j(self.owneremail).val());
	netrx.setownerPhone($j(self.ownerphone).val());
	netrx.setownerMobile($j(self.ownermobile).val());
	netrx.setownerAddress(commonMethodInvoker.nl2br($j(self.owneraddress).val()));
	netrx.setheadOfficeAddress(commonMethodInvoker.nl2br($j(self.headofficeaddress).val()));
	netrx.setheadOfficeName(headofficename);
	netrx.setheadOfficeEmail($j(self.headofficeemail).val());
	netrx.setheadOfficePhone($j(self.headofficephone).val());
	netrx.setheadOfficeMobile($j(self.headofficemobile).val());
	netrx.setuserName($j(self.username).val());
	netrx.setpassword($j(self.password).val());
	return netrx;
}

NewNetrxUI.prototype.bindEvents = function() {
self = this;	
	self.removecolors(self.newnetrxPage + " :input");
	//prevent from entering characters
	commonMethodInvoker.validateNumber(self.headofficephone);
	commonMethodInvoker.validateNumber(self.headofficemobile);
	commonMethodInvoker.validateNumber(self.ownerphone);
	commonMethodInvoker.validateNumber(self.ownermobile);

$j(self.netrxModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});	
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}	