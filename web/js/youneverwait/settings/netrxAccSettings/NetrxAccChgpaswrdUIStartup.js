function NetrxAccChgpaswrdUIStartup(){
	this.netrxAccChgpaswrdModal='#changepwdModalNetrx';
	this.netrxAccChgpaswrdPage='#changePasswordFormNetRxAcc';
	this.createButton = $j(this.netrxAccChgpaswrdPage + " #btnChangePwdSubmit");
	this.cancelButton = $j(this.netrxAccChgpaswrdPage + " #btnChangePwdCancel");
	this.errorHeader = $j(this.netrxAccChgpaswrdPage + " #errorDivHeader");
	this.errorData = $j(this.netrxAccChgpaswrdPage + " #errorDivChangePwdData");
	this.name=userdata.userName;
	this.netrxAccService = new NetrxAccServiceImpl();
	this.inputFields = this.netrxAccChgpaswrdPage + " :input[type=text]";
	this.oldpassword=this.netrxAccChgpaswrdPage + " #oldpassword";
	this.newpassword=this.netrxAccChgpaswrdPage + " #newpassword";
	this.confirmpassword = this.netrxAccChgpaswrdPage + " #confirmpassword";
}
NetrxAccChgpaswrdUIStartup.prototype.getNetrxUIService = function() {
	return this.netrxAccService;
}

NetrxAccChgpaswrdUIStartup.prototype.init = function() {
	self =this;
	self.bindEvents();
}
 NetrxAccChgpaswrdUIStartup.prototype.clearFields = function() {
	$j(this.netrxAccChgpaswrdModal  +  " :input[type=password]").val("");
}
NetrxAccChgpaswrdUIStartup.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.oldpassword);
	commonMethodInvoker.removeErrorColor(self.newpassword);
	commonMethodInvoker.removeErrorColor(self.confirmpassword);
}
NetrxAccChgpaswrdUIStartup.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}

NetrxAccChgpaswrdUIStartup.prototype.cancel = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	$j(self.netrxAccChgpaswrdModal +  "  :input[type=password]").val("");	
	$j(self.netrxAccChgpaswrdModal).trigger('reveal:close');
	$j(self.netrxAccChgpaswrdModal).remove();
	
}

NetrxAccChgpaswrdUIStartup.prototype.create = function() {
	//alert("create function");
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var changePasswrd = self.getChangepaswrdDetail();
	var changePasswrdValidator = new ChangePasswrdValidator();
	var error  = changePasswrdValidator.validate(self);
	//alert("Error"+error);
	if(error.errorStatus==false) {
		var changePasswrdService = self.getNetrxUIService();
		var changePasswrdResponse =changePasswrdService.changePasswrdNetmd(changePasswrd);
		//alert(JSON.stringify(branchResponse));
		if(changePasswrdResponse.success==true) {
			showTip(constants.PASSWRDCHANGDSUCCES);//For showing the global Tip			
			self.clearFields();
		} else 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(changePasswrdResponse.error));
	} else 
		self.createError(error); 
}

NetrxAccChgpaswrdUIStartup.prototype.getChangepaswrdDetail = function() {
	var self=this;
	var ChangePasswrd = new ChangePasswrdDTO();
	ChangePasswrd.setUsername(self.name);
	ChangePasswrd.setNewPassword($j(self.newpassword).val());
	ChangePasswrd.setOldPassword($j(self.oldpassword).val());
	return ChangePasswrd;
}
 
NetrxAccChgpaswrdUIStartup.prototype.bindEvents = function() {
self = this;	
	self.removecolors(self.netrxAccChgpaswrdPage + " :input");
	

$j(self.netrxAccChgpaswrdModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});	
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});	
	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}	