function NetlimsAccChgpaswrdUIStartup(){
	this.netlimsAccChgpaswrdModal='#changepwdModalNetLims';
	this.netlimsAccChgpaswrdPage='#changePasswordFormNetLimsAcc';
	this.createButton = $j(this.netlimsAccChgpaswrdPage + " #btnChangePwdSubmit");
	this.cancelButton = $j(this.netlimsAccChgpaswrdPage + " #btnChangePwdCancel");
	this.errorHeader = $j(this.netlimsAccChgpaswrdPage + " #errorDivHeader");
	this.errorData = $j(this.netlimsAccChgpaswrdPage + " #errorDivChangePwdData");
	this.name=userdata.userName;
	this.netlimsAccService = new NetlimsAccServiceImpl();
	this.inputFields = this.netlimsAccChgpaswrdPage + " :input[type=text]";
	this.oldpassword=this.netlimsAccChgpaswrdPage + " #oldpassword";
	this.newpassword=this.netlimsAccChgpaswrdPage + " #newpassword";
	this.confirmpassword = this.netlimsAccChgpaswrdPage + " #confirmpassword";
}
NetlimsAccChgpaswrdUIStartup.prototype.getNetlimsUIService = function() {
	return this.netlimsAccService;
}

NetlimsAccChgpaswrdUIStartup.prototype.init = function() {
	self =this;
	self.bindEvents();
}
 NetlimsAccChgpaswrdUIStartup.prototype.clearFields = function() {
	$j(this.netlimsAccChgpaswrdModal  + " input[type=password]").val("");
}
NetlimsAccChgpaswrdUIStartup.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.oldpassword);
	commonMethodInvoker.removeErrorColor(self.newpassword);
	commonMethodInvoker.removeErrorColor(self.confirmpassword);
}
NetlimsAccChgpaswrdUIStartup.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}

NetlimsAccChgpaswrdUIStartup.prototype.cancel = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	$j(self.netlimsAccChgpaswrdModal + " input[type=password]").val("");	
	$j(self.netlimsAccChgpaswrdModal).trigger('reveal:close');
	$j(self.netlimsAccChgpaswrdModal).remove();
	
}

NetlimsAccChgpaswrdUIStartup.prototype.create = function() {
	//alert("create function");
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var changePasswrd = self.getChangepaswrdDetail();
	var changePasswrdValidator = new ChangePasswrdValidator();
	var error  = changePasswrdValidator.validate(self);
	//alert("Error"+error);
	if(error.errorStatus==false) {
		var changePasswrdService = self.getNetlimsUIService();
		var changePasswrdResponse =changePasswrdService.changePasswrdNetlims(changePasswrd);
		//alert(JSON.stringify(branchResponse));
		if(changePasswrdResponse.error==null) {
			showTip(constants.PASSWRDCHANGDSUCCES);//For showing the global Tip			
			self.clearFields();
		} else 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(changePasswrdResponse.error));
	} else 
		self.createError(error); 
}

NetlimsAccChgpaswrdUIStartup.prototype.getChangepaswrdDetail = function() {
	var self=this;
	var ChangePasswrd = new ChangePasswrdDTO();
	ChangePasswrd.setUsername(self.name);
	ChangePasswrd.setNewPassword($j(self.newpassword).val());
	ChangePasswrd.setOldPassword($j(self.oldpassword).val());
	return ChangePasswrd;
}
 
NetlimsAccChgpaswrdUIStartup.prototype.bindEvents = function() {
self = this;	
	self.removecolors(self.netlimsAccChgpaswrdPage + " :input");
	

$j(self.netlimsAccChgpaswrdModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});	
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});	
	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}	