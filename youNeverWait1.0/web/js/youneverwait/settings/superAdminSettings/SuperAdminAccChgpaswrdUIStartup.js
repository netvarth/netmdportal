function SuperAdminChgpaswrdUIStartup(){
	this.superAdminChgpaswrdModal='#superadminchgpaswrdModal';
	this.superAdminChgpaswrdPage='#changePasswordForm';
	this.createButton = $j(this.superAdminChgpaswrdPage + " #btnChangePwdSubmit");
	this.cancelButton = $j(this.superAdminChgpaswrdPage + " #btnChangePwdCancel");
	this.errorHeader = $j(this.superAdminChgpaswrdPage + " #errorDivHeader");
	this.errorData = $j(this.superAdminChgpaswrdPage + " #errorDivChangePwdAdminData");
	this.name=userdata.userName;
	this.superAdminService = new SuperAdminGlobalServiceImpl();
	this.inputFields = this.superAdminChgpaswrdPage + " :input[type=text]";
	this.oldpassword=this.superAdminChgpaswrdPage + " #oldpassword";
	this.newpassword=this.superAdminChgpaswrdPage + " #newpassword";
	this.confirmpassword = this.superAdminChgpaswrdPage + " #confirmpassword";
}
SuperAdminChgpaswrdUIStartup.prototype.getSuperadminUIService = function() {
	return this.superAdminService;
}

SuperAdminChgpaswrdUIStartup.prototype.init = function() {
	self =this;
	self.bindEvents();
}
 SuperAdminChgpaswrdUIStartup.prototype.clearFields = function() {
	$j(this.superAdminChgpaswrdModal  + " input[type=password]").val("");
}
SuperAdminChgpaswrdUIStartup.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.oldpassword);
	commonMethodInvoker.removeErrorColor(self.newpassword);
	commonMethodInvoker.removeErrorColor(self.confirmpassword);
}
SuperAdminChgpaswrdUIStartup.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}

SuperAdminChgpaswrdUIStartup.prototype.cancel = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	$j(self.superAdminChgpaswrdModal + " input[type=password]").val("");	
	$j(self.superAdminChgpaswrdModal).trigger('reveal:close');
	$j(self.superAdminChgpaswrdModal).remove();
	
}

SuperAdminChgpaswrdUIStartup.prototype.create = function() {
	//alert("create function");
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var changePasswrd = self.getChangepaswrdDetail();
	var changePasswrdValidator = new ChangePasswrdValidator();
	var error  = changePasswrdValidator.validate(self);
	//alert("Error"+error);
	if(error.errorStatus==false) {
		var changePasswrdService = self.getSuperadminUIService();
		var changePasswrdResponse =changePasswrdService.changePasswrdSuperadmin(changePasswrd);
		//alert(JSON.stringify(changePasswrdResponse));
		if(changePasswrdResponse.error==null) {
			showTip(constants.PASSWRDCHANGDSUCCES);//For showing the global Tip			
			self.clearFields();
		} else 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(changePasswrdResponse.error));
	} else 
		self.createError(error); 
}

SuperAdminChgpaswrdUIStartup.prototype.getChangepaswrdDetail = function() {
	var self=this;
	var ChangePasswrd = new ChangePasswrdDTO();
	ChangePasswrd.setUsername(self.name);
	ChangePasswrd.setNewPassword($j(self.newpassword).val());
	ChangePasswrd.setOldPassword($j(self.oldpassword).val());
	return ChangePasswrd;
}
 
SuperAdminChgpaswrdUIStartup.prototype.bindEvents = function() {
self = this;	
	self.removecolors(self.superAdminChgpaswrdPage + " :input");
	

$j(self.superAdminChgpaswrdModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});	
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});	
	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}	