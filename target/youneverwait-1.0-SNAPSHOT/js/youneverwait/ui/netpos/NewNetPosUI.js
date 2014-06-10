
function NewNetPosUI(NetPosUIStartup) {

	this.newNetPosModel = '#netPosmodel';
	this.newNetPosPage = this.newNetPosModel + " #newNetPos";
	this.createButton = $j(this.newNetPosPage + " #btnNetPosSave");
	this.cancelButton = $j(this.newNetPosPage + " #btnNetPosCancel");
	this.errorHeader = $j(this.newNetPosPage + " #errorDivHeader");
	this.errorData = $j(this.newNetPosPage + " #errorDivnewNetPosData");
	this.headofficename=$j(this.newNetPosPage + " #headofficeName");;
	this.inputFields = this.newNetPosPage + " :input[type=text]";
	this.organizationname=this.newNetPosPage + " #organizationName";
	this.headofficephone=this.newNetPosPage + " #headofficePhone";
	this.headofficemobile=this.newNetPosPage + " #headofficeMobile";
	this.headofficeaddress=this.newNetPosPage + " #headofficeAddress";
	this.headofficeemail=this.newNetPosPage + " #headofficeEmail";
	this.ownerfirstname=this.newNetPosPage + " #ownerfirstname";
	this.ownerlastname=this.newNetPosPage + " #ownerlastname";
	this.owneraddress=this.newNetPosPage + " #owneraddress";
	this.ownerphone = this.newNetPosPage + " #ownerphone";
	this.ownermobile=this.newNetPosPage + " #ownermobile";
	this.owneremail = this.newNetPosPage + " #owneremail";
	this.password=this.newNetPosPage + " #password";
	this.rePassword=this.newNetPosPage + " #rePassword";
	this.username=this.newNetPosPage + " #username";
	this.netposCreationStatus= false;
	this.netPosUIStartup = NetPosUIStartup; 
}

NewNetPosUI.prototype.getNetPosUIStartup = function() {

	return this.netPosUIStartup;
}

NewNetPosUI.prototype.getNetPosTableNavigator = function() {
	var netPosUIStartup = this.getNetPosUIStartup();
	return netPosUIStartup.getNetPosTableNavigator();
}

NewNetPosUI.prototype.getNetPosService = function() {

	var netPosUIStartup = this.getNetPosUIStartup();
	return netPosUIStartup.getNetPosService();
}

NewNetPosUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}

NewNetPosUI.prototype.getNetPos = function() {

	var self=this;
	var netPos = new NetPosDTO();
	
	netPos.setName($j(self.organizationname).val());
	 netPos.sethoName($j(self.headofficename).val());
	netPos.sethoPh($j(self.headofficephone).val());
	netPos.sethoAdd($j(self.headofficeaddress).val());
	netPos.sethoMob($j(self.headofficemobile).val());
	netPos.sethoEmail($j(self.headofficeemail).val());
	netPos.setofName($j(self.ownerfirstname).val());
	netPos.setolName($j(self.ownerlastname).val());
	netPos.setoAdd($j(self.owneraddress).val());
	netPos.setoPh($j(self.ownerphone).val());
	netPos.setoMob($j(self.ownermobile).val());
	netPos.setoEmail($j(self.owneremail).val());
	netPos.setpass($j(self.rePassword).val());
	netPos.setuName($j(self.username).val()); 
	
	return netPos;
}



 NewNetPosUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newNetPosPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}


NewNetPosUI.prototype.init = function() {
	self =this;
	self.bindEvents();
}
NewNetPosUI.prototype.clearFields = function() {
	$j(this.newNetPosPage + " input[type=text],textarea").val("");
}


NewNetPosUI.prototype.bindEvents = function() {
	self = this;	
	self.removecolors(self.newNetPosPage + " :input");
	//prevent from entering characters
	
	commonMethodInvoker.validateNumber(self.headofficephone);
	commonMethodInvoker.validateNumber(self.headofficemobile);
	commonMethodInvoker.validateNumber(self.ownerphone);
	commonMethodInvoker.validateNumber(self.ownermobile);

$j(self.newNetPosModel + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});	
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
	

} 

NewNetPosUI.prototype.create = function() {

	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var netPos = self.getNetPos();
	var netPosValidator = new NetPosValidator();
	var error  = netPosValidator.validate(netPos,self);
	if(error.errorStatus==false) {
	//alert(JSON.stringify(netPos));
		var netPosService = self.getNetPosService();
		var netPosResponse = netPosService.createNetPos(netPos);
		if(netPosResponse.success==true) {
		
			showTip(constants.NETPOSCREATESUCCESS);
			//For showing the global Tip
			self.clearFields();
			
			var netPosTableNavigator = self.getNetPosTableNavigator();
			netPosTableNavigator.list();
		} else {
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netPosResponse.error));
		} 
	} else {
		self.createError(error);
	}	

}

NewNetPosUI.prototype.cancel = function() {

self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.newNetPosModel + self.newNetPosPage + " input[type=text]").val("");	
		$j(self.newNetPosModel).trigger('reveal:close');
		$j(self.newNetPosModel).remove();
	

}


