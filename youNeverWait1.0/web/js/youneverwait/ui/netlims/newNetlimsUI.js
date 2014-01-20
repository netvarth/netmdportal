function NewNetlimsUI(NetlimsUIStartup) {
this.netlimsBranchModal = '#netlimsModal';
	this.newnetlimsBranchPage = this.netlimsBranchModal + " #newNetlimsForm";
	this.createButton = $j(this.newnetlimsBranchPage + " #btnNewNetLimsCreate");
	this.cancelButton = $j(this.newnetlimsBranchPage + " #btnNewNetLimsCancel");
	this.errorHeader = $j(this.newnetlimsBranchPage + " #errorDivHeader");
	this.errorData = $j(this.newnetlimsBranchPage + " #errorDivNewNetlimsData");
	this.headofficename=$j(this.newnetlimsBranchPage + " #headofficename");;
	this.inputFields = this.newnetlimsBranchPage + " :input[type=text]";
	this.organizationname=this.newnetlimsBranchPage + " #organizationname";
	this.headofficephone=this.newnetlimsBranchPage + " #headofficephone";
	this.headofficemobile=this.newnetlimsBranchPage + " #headofficemobile";
	this.headofficeaddress=this.newnetlimsBranchPage + " #headofficeaddress";
	this.headofficeemail=this.newnetlimsBranchPage + " #headofficeemail";
	this.ownerfirstname=this.newnetlimsBranchPage + " #ownerfirstname";
	this.ownerlastname=this.newnetlimsBranchPage + " #ownerlastname";
	this.owneraddress=this.newnetlimsBranchPage + " #owneraddress";
	this.ownerphone = this.newnetlimsBranchPage + " #ownerphone";
	this.ownermobile=this.newnetlimsBranchPage + " #ownermobile";
	this.owneremail = this.newnetlimsBranchPage + " #owneremail";
	this.password=this.newnetlimsBranchPage + " #password";
	this.rePassword=this.newnetlimsBranchPage + " #rePassword";
	this.username=this.newnetlimsBranchPage + " #username";
	this.netlimsUIStartup = NetlimsUIStartup;
	this.netlimsCreationStatus= false;
}

NewNetlimsUI.prototype.getNetlimsCreationStatus = function() {
	return this.netlimsCreationStatus;
}
NewNetlimsUI.prototype.setNetlimsCreationStatus = function(status) {
	this.netlimsCreationStatus=status;
}
NewNetlimsUI.prototype.getBranchUIStartup = function() {
	return this.netlimsUIStartup;
}
NewNetlimsUI.prototype.getnetlimsTableNavigator = function() {
	var netlimsUIStartup = this.getBranchUIStartup();
	return netlimsUIStartup.getnetlimsTableNavigator();
}
NewNetlimsUI.prototype.getNetlimsUIService = function() {
	var netlimsUIStartup = this.getBranchUIStartup();
	return netlimsUIStartup.getNetlimsUIService();
}

NewNetlimsUI.prototype.init = function() {
	self =this;
	self.bindEvents();
}

NewNetlimsUI.prototype.clearFields = function() {
	$j(this.newnetlimsBranchPage + " input[type=text]").val("");
	$j(this.newnetlimsBranchPage + " textarea").val("");
}
NewNetlimsUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.inputFields);
	
}
NewNetlimsUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
NewNetlimsUI.prototype.cancel = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	$j(self.netlimsBranchModal + self.newnetlimsBranchPage + " input[type=text]").val("");	
	$j(self.netlimsBranchModal).trigger('reveal:close');
	$j(self.netlimsBranchModal).remove();
	if(self.getNetlimsCreationStatus() == true){
	var exp = new ExpressionListDTO();
	var netlimsTableNavigator = self.getnetlimsTableNavigator();
	netlimsTableNavigator.setExp(exp);
	netlimsTableNavigator.list();
		
	}
	self.setNetlimsCreationStatus(false);
	self=pageHandler.getActivePage();
}
NewNetlimsUI.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var netlims = self.getNetlims();
	var netlimsValidator = new NetlimsValidator();
	var error  = netlimsValidator.validate(netlims,self);
	//alert("Error"+error);
	if(error.errorStatus==false) {
		var netlimsService = self.getNetlimsUIService();
		var netlimsResponse =netlimsService.createNetlims(netlims);
		//alert(JSON.stringify(netlimsResponse));
		if(netlimsResponse.error==null) {
			self.setNetlimsCreationStatus(true);
			showTip(constants.NETLIMSCREATESUCCESS);//For showing the global Tip			
			self.clearFields();
		} else 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netlimsResponse.error));
	} else 
		self.createError(error); 
}
NewNetlimsUI.prototype.getNetlims = function() {
	var self=this;
	var netlims = new NetlimsDTO();
	var netlimsname;
	netlimsname=$j(self.organizationname).val();
	netlimsname=netlimsname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
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
	
	netlims.setName(netlimsname);
	netlims.setownerFirstName(ownerfirstname);
	netlims.setownerLastName(ownerlastname);
	netlims.setownerEmail($j(self.owneremail).val());
	netlims.setownerPhone($j(self.ownerphone).val());
	netlims.setownerMobile($j(self.ownermobile).val());
	netlims.setownerAddress(commonMethodInvoker.nl2br($j(self.owneraddress).val()));
	netlims.setheadOfficeAddress(commonMethodInvoker.nl2br($j(self.headofficeaddress).val()));
	netlims.setheadOfficeName(headofficename);
	netlims.setheadOfficeEmail($j(self.headofficeemail).val());
	netlims.setheadOfficePhone($j(self.headofficephone).val());
	netlims.setheadOfficeMobile($j(self.headofficemobile).val());
	netlims.setuserName($j(self.username).val());
	netlims.setpassword($j(self.password).val());
	return netlims;
}

NewNetlimsUI.prototype.bindEvents = function() {
self = this;	
	self.removecolors(self.newnetlimsBranchPage + " :input");
	//prevent from entering characters
	commonMethodInvoker.validateNumber(self.headofficephone);
	commonMethodInvoker.validateNumber(self.headofficemobile);
	commonMethodInvoker.validateNumber(self.ownerphone);
	commonMethodInvoker.validateNumber(self.ownermobile);

$j(self.netlimsBranchModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});	
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}	