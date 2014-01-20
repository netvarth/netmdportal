function NewNetmdUI(NetmdUIStartup) {
	this.netmdModal = '#netmdModal';
	this.newnetmdPage = this.netmdModal + " #newNetmdForm";
	this.createButton = $j(this.newnetmdPage + " #btnNewNetMDCreate");
	this.cancelButton = $j(this.newnetmdPage + " #btnNewNetMdCancel");
	this.errorHeader = $j(this.newnetmdPage + " #errorDivHeader");
	this.errorData = $j(this.newnetmdPage + " #errorDivNewNetmdData");
	this.headofficename=$j(this.newnetmdPage + " #headofficename");;
	this.inputFields = this.newnetmdPage + " :input[type=text]";
	this.organizationname=this.newnetmdPage + " #organizationname";
	this.headofficephone=this.newnetmdPage + " #headofficephone";
	this.headofficemobile=this.newnetmdPage + " #headofficemobile";
	this.headofficeaddress=this.newnetmdPage + " #headofficeaddress";
	this.headofficeemail=this.newnetmdPage + " #headofficeemail";
	this.ownerfirstname=this.newnetmdPage + " #ownerfirstname";
	this.ownerlastname=this.newnetmdPage + " #ownerlastname";
	this.owneraddress=this.newnetmdPage + " #owneraddress";
	this.ownerphone = this.newnetmdPage + " #ownerphone";
	this.ownermobile=this.newnetmdPage + " #ownermobile";
	this.owneremail = this.newnetmdPage + " #owneremail";
	this.password=this.newnetmdPage + " #password";
	this.rePassword=this.newnetmdPage + " #rePassword";
	this.username=this.newnetmdPage + " #username";
	this.netmdUIStartup = NetmdUIStartup;
	this.netmdCreationStatus= false;
}

NewNetmdUI.prototype.getNetmdCreationStatus = function() {
	return this.netmdCreationStatus;
}
NewNetmdUI.prototype.setNetmdCreationStatus = function(status) {
	this.netmdCreationStatus=status;
}
NewNetmdUI.prototype.getBranchUIStartup = function() {
	return this.netmdUIStartup;
}
NewNetmdUI.prototype.getnetmdTableNavigator = function() {
	var netmdUIStartup = this.getBranchUIStartup();
	return netmdUIStartup.getnetmdTableNavigator();
}
NewNetmdUI.prototype.getNetmdUIService = function() {
	var netmdUIStartup = this.getBranchUIStartup();
	return netmdUIStartup.getNetmdUIService();
}

NewNetmdUI.prototype.init = function() {
	self =this;
	self.bindEvents();
}

NewNetmdUI.prototype.clearFields = function() {
	$j(this.newnetmdPage + " input[type=text]").val("");
	$j(this.newnetmdPage + " input[type=password]").val("");
	$j(this.newnetmdPage + " textarea").val("");
}
NewNetmdUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.inputFields);
	
}
NewNetmdUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
NewNetmdUI.prototype.cancel = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	$j(self.netmdModal + self.newnetmdPage + " input[type=text]").val("");	
	$j(self.netmdModal).trigger('reveal:close');
	$j(self.netmdModal).remove();
	if(self.getNetmdCreationStatus() == true){
	var exp = new ExpressionListDTO();
	var netmdTableNavigator = self.getnetmdTableNavigator();
	netmdTableNavigator.setExp(exp);
	netmdTableNavigator.list();
		
	}
	self.setNetmdCreationStatus(false);
	self=pageHandler.getActivePage();
}
NewNetmdUI.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var netmd = self.getNetmd();
	var netmdValidator = new NetmdValidator();
	var error  = netmdValidator.validate(netmd,self);
	//alert("Error"+error);
	if(error.errorStatus==false) {
		var netmdService = self.getNetmdUIService();
		var netmdResponse =netmdService.createNetmd(netmd);
		//alert(JSON.stringify(netmdResponse));
		if(netmdResponse.error==null) {
			self.setNetmdCreationStatus(true);
			showTip(constants.NETMDCREATESUCCESS);//For showing the global Tip			
			self.clearFields();
		} else 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netmdResponse.error));
	} else 
		self.createError(error); 
}
NewNetmdUI.prototype.getNetmd = function() {
	var self=this;
	var netmd = new NetmdDTO();
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
	var headofficename;
	headofficename= $j(self.headofficename).val();
	headofficename=headofficename.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	
	netmd.setName(netmdname);
	netmd.setownerFirstName(ownerfirstname);
	netmd.setownerLastName(ownerlastname);
	netmd.setownerEmail($j(self.owneremail).val());
	netmd.setownerPhone($j(self.ownerphone).val());
	netmd.setownerMobile($j(self.ownermobile).val());
	netmd.setownerAddress(commonMethodInvoker.nl2br($j(self.owneraddress).val()));
	netmd.setheadOfficeAddress(commonMethodInvoker.nl2br($j(self.headofficeaddress).val()));
	netmd.setheadOfficeName(headofficename);
	netmd.setheadOfficeEmail($j(self.headofficeemail).val());
	netmd.setheadOfficePhone($j(self.headofficephone).val());
	netmd.setheadOfficeMobile($j(self.headofficemobile).val());
	netmd.setuserName($j(self.username).val());
	netmd.setpassword($j(self.password).val());
	return netmd;
}

NewNetmdUI.prototype.bindEvents = function() {
self = this;	
	self.removecolors(self.newnetmdPage + " :input");
	//prevent from entering characters
	commonMethodInvoker.validateNumber(self.headofficephone);
	commonMethodInvoker.validateNumber(self.headofficemobile);
	commonMethodInvoker.validateNumber(self.ownerphone);
	commonMethodInvoker.validateNumber(self.ownermobile);

$j(self.netmdModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});	
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}	