function NetmdAccChgpaswrdUIStartup(){
	this.netmdAccChgpaswrdModal='#changepwdModalNetMd';
	this.netmdAccChgpaswrdPage='#changePasswordFormNetMdAcc';
	this.createButton = $j(this.netmdAccChgpaswrdPage + " #btnChangePwdSubmit");
	this.cancelButton = $j(this.netmdAccChgpaswrdPage + " #btnChangePwdCancel");
	this.errorHeader = $j(this.netmdAccChgpaswrdPage + " #errorDivHeader");
	this.errorData = $j(this.netmdAccChgpaswrdPage + " #errorDivChangePwdData");
	this.name=userdata.userName;
	this.netmdAccService = new NetmdAccServiceImpl();
	this.inputFields = this.netmdAccChgpaswrdPage + " :input[type=text]";
	this.oldpassword=this.netmdAccChgpaswrdPage + " #oldpassword";
	this.newpassword=this.netmdAccChgpaswrdPage + " #newpassword";
	this.confirmpassword = this.netmdAccChgpaswrdPage + " #confirmpassword";
}
NetmdAccChgpaswrdUIStartup.prototype.getNetmdUIService = function() {
	return this.netmdAccService;
}

NetmdAccChgpaswrdUIStartup.prototype.init = function() {
	self =this;
	self.bindEvents();
}
 NetmdAccChgpaswrdUIStartup.prototype.clearFields = function() {
	$j(this.netmdAccChgpaswrdModal  +  " :input[type=password]").val("");
}
NetmdAccChgpaswrdUIStartup.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.oldpassword);
	commonMethodInvoker.removeErrorColor(self.newpassword);
	commonMethodInvoker.removeErrorColor(self.confirmpassword);
}
NetmdAccChgpaswrdUIStartup.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}

NetmdAccChgpaswrdUIStartup.prototype.cancel = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	$j(self.netmdAccChgpaswrdModal +  "  :input[type=password]").val("");	
	$j(self.netmdAccChgpaswrdModal).trigger('reveal:close');
	$j(self.netmdAccChgpaswrdModal).remove();
	
}

NetmdAccChgpaswrdUIStartup.prototype.create = function() {
	//alert("create function");
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var changePasswrd = self.getChangepaswrdDetail();
	var changePasswrdValidator = new ChangePasswrdValidator();
	var error  = changePasswrdValidator.validate(self);
	//alert("Error"+error);
	if(error.errorStatus==false) {
		var changePasswrdService = self.getNetmdUIService();
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

NetmdAccChgpaswrdUIStartup.prototype.getChangepaswrdDetail = function() {
	var self=this;
	var ChangePasswrd = new ChangePasswrdDTO();
	ChangePasswrd.setUsername(self.name);
	ChangePasswrd.setNewPassword($j(self.newpassword).val());
	ChangePasswrd.setOldPassword($j(self.oldpassword).val());
	return ChangePasswrd;
}
 
NetmdAccChgpaswrdUIStartup.prototype.bindEvents = function() {
self = this;	
	self.removecolors(self.netmdAccChgpaswrdPage + " :input");
	

$j(self.netmdAccChgpaswrdModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});	
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});	
	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}	