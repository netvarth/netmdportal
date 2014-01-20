function NewBranchUI(BranchUIStartup) {
this.netmdAccBranchModal = '#netmdAccBranchModal';
	this.newnetmdAccBranchPage = this.netmdAccBranchModal + " #newNetMdAccBranchForm";
	this.createButton = $j(this.newnetmdAccBranchPage + " #btnNewBranchCreate");
	this.cancelButton = $j(this.newnetmdAccBranchPage + " #btnNewBranchCancel");
	this.errorHeader = $j(this.newnetmdAccBranchPage + " #errorDivHeader");
	this.errorData = $j(this.newnetmdAccBranchPage + " #errorDivNewNetmdAccBranchData");
	//this.netmdId=userdata.netmdId;
	this.netmdId=BranchUIStartup.getnetmdId();
	this.inputFields = this.newnetmdAccBranchPage + " :input[type=text]";
	this.name=this.newnetmdAccBranchPage + " #organizationname";
	this.address=this.newnetmdAccBranchPage + " #organizationaddress";
	this.phone = this.newnetmdAccBranchPage + " #phone";
	this.mobile=this.newnetmdAccBranchPage + " #mobile";
	this.email = this.newnetmdAccBranchPage + " #Email";
	this.numberOfDevices = this.newnetmdAccBranchPage + " #numberOfDevices";
	this.branchUIStartup = BranchUIStartup;
	this.branchCreationStatus= false;
}

NewBranchUI.prototype.getBranchCreationStatus = function() {
	return this.branchCreationStatus;
}
NewBranchUI.prototype.setBranchCreationStatus = function(status) {
	this.branchCreationStatus=status;
}
NewBranchUI.prototype.getBranchUIStartup = function() {
	return this.branchUIStartup;
}
NewBranchUI.prototype.getnetmdAccTableNavigator = function() {
	var branchUIStartup = this.getBranchUIStartup();
	return branchUIStartup.getnetmdAccTableNavigator();
}
NewBranchUI.prototype.getNetmdUIService = function() {
	var branchUIStartup = this.getBranchUIStartup();
	return branchUIStartup.getNetmdUIService();
}

NewBranchUI.prototype.init = function() {
	self =this;
	self.bindEvents();
}

NewBranchUI.prototype.clearFields = function() {
	$j(this.newnetmdAccBranchPage + " input[type=text]").val("");
	$j(this.newnetmdAccBranchPage + " textarea").val("");
}
NewBranchUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
	commonMethodInvoker.removeErrorColor(self.phone);
	commonMethodInvoker.removeErrorColor(self.mobile);
	commonMethodInvoker.removeErrorColor(self.email);
	commonMethodInvoker.removeErrorColor(self.numberOfDevices);
}
NewBranchUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
NewBranchUI.prototype.cancel = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	$j(self.netmdAccBranchModal + self.newnetmdAccBranchPage + " input[type=text]").val("");	
	$j(self.netmdAccBranchModal).trigger('reveal:close');
	$j(self.netmdAccBranchModal).remove();
	if(self.getBranchCreationStatus() == true){
			var selName="netMdId";
	var selValue=self.netmdId;
	var selOperator = "eq";
	var exp = new ExpressionListDTO();
	var expr = new ExpressionDTO(selName,selValue,selOperator);
	exp.add(expr);
	var netmdAccTableNavigator = self.getnetmdAccTableNavigator();
	netmdAccTableNavigator.setExp(exp);
		
			//var branchTableNavigator = self.getnetmdAccTableNavigator();
			netmdAccTableNavigator.list();
		
	}
	self.setBranchCreationStatus(false);
	self=pageHandler.getActivePage();
}
NewBranchUI.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var branch = self.getBranch();
	var branchValidator = new NetmdBranchValidator();
	var error  = branchValidator.validate(branch,self);
	//alert("Error"+error);
	if(error.errorStatus==false) {
		var branchService = self.getNetmdUIService();
		var branchResponse =branchService.createBranchNetmd(branch);
		//alert(JSON.stringify(branchResponse));
		if(branchResponse.success==true) {
			self.setBranchCreationStatus(true);
			showTip(constants.BRANCHCREATESUCCESS);//For showing the global Tip			
			self.clearFields();
		} else 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(branchResponse.error));
	} else 
		self.createError(error); 
}
NewBranchUI.prototype.getBranch = function() {
	var self=this;
	var branch = new BranchNetmdDTO();
	var device=$j(self.numberOfDevices).val();
	if(device==""){device=0;}
	var name=$j(self.name).val();
	name= name.replace(/\b[a-z]/g, function(letter) {
		return letter.toUpperCase();
	});
	
	branch.setnetMdId(self.netmdId);
	branch.setName(name);
	branch.setEmail($j(self.email).val());
	branch.setAddress(commonMethodInvoker.nl2br($j(self.address).val()));
	branch.setPhone($j(self.phone).val());
	branch.setMobile($j(self.mobile).val());
	branch.setnumberOfDevices(parseInt(device));
	return branch;
}

NewBranchUI.prototype.bindEvents = function() {
self = this;
	self.removecolors(self.newnetmdAccBranchPage + " :input");
	commonMethodInvoker.validateNumber(self.phone);
	commonMethodInvoker.validateNumber(self.mobile);
	commonMethodInvoker.validateNumber(self.numberOfDevices);

	$j(self.netmdAccBranchModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});	
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}	