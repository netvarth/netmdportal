function NewBranchUI(BranchUIStartup) {
	this.netrxAccBranchModal = '#netrxAccBranchModal';
	this.newnetrxAccBranchPage = this.netrxAccBranchModal + " #newNetRxBranchAccForm";
	this.createButton = $j(this.newnetrxAccBranchPage + " #btnNewBranchCreate");
	this.cancelButton = $j(this.newnetrxAccBranchPage + " #btnNewBranchCancel");
	this.errorHeader = $j(this.newnetrxAccBranchPage + " #errorDivHeader");
	this.errorData = $j(this.newnetrxAccBranchPage + " #errorDivNewNetrxAccBranchData");
	this.netrxId=userdata.netrxId;
	this.inputFields = this.newnetrxAccBranchPage + " :input[type=text]";
	this.name=this.newnetrxAccBranchPage + " #organizationname";
	this.address=this.newnetrxAccBranchPage + " #organizationaddress";
	this.phone = this.newnetrxAccBranchPage + " #phone";
	this.mobile=this.newnetrxAccBranchPage + " #mobile";
	this.email = this.newnetrxAccBranchPage + " #Email";
	this.numberOfDevices = this.newnetrxAccBranchPage + " #numberOfDevices";
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
NewBranchUI.prototype.getnetrxAccTableNavigator = function() {
	var branchUIStartup = this.getBranchUIStartup();
	return branchUIStartup.getnetrxAccTableNavigator();
}
NewBranchUI.prototype.getNetrxUIService = function() {
	var branchUIStartup = this.getBranchUIStartup();
	return branchUIStartup.getNetrxUIService();
}

NewBranchUI.prototype.init = function() {
	self =this;
	self.bindEvents();
}

NewBranchUI.prototype.clearFields = function() {
	$j(this.newnetrxAccBranchPage + " input[type=text]").val("");
	$j(this.newnetrxAccBranchPage + " textarea").val("");
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
	$j(self.netrxAccBranchModal + " input[type=text]").val("");	
	$j(self.netrxAccBranchModal).trigger('reveal:close');
	$j(self.netrxAccBranchModal).remove();
	if(self.getBranchCreationStatus() == true){
			var selName="netRxId";
	var selValue=self.netrxId;
	var selOperator = "eq";
	var exp = new ExpressionListDTO();
	var expr = new ExpressionDTO(selName,selValue,selOperator);
	exp.add(expr);
	var netrxAccTableNavigator = self.getnetrxAccTableNavigator();
	netrxAccTableNavigator.setExp(exp);
		
			//var branchTableNavigator = self.getnetrxAccTableNavigator();
			netrxAccTableNavigator.list("branchlist");
		
	}
	self.setBranchCreationStatus(false);
	self=pageHandler.getActivePage();
}
NewBranchUI.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var branch = self.getBranch();
	var branchValidator = new NetrxBranchValidator();
	var error  = branchValidator.validate(branch,self);
	//alert("Error"+error);
	if(error.errorStatus==false) {
		var branchService = self.getNetrxUIService();
		var branchResponse =branchService.createBranchNetrx(branch);
		//alert(JSON.stringify(branchResponse));
		if(branchResponse.error==null) {
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
	var branch = new BranchNetrxDTO();
	var device=$j(self.numberOfDevices).val();
	if(device==""){device=0;}
	var name=$j(self.name).val();
	name= name.replace(/\b[a-z]/g, function(letter) {
		return letter.toUpperCase();
	});
	
	branch.setnetRxId(self.netrxId);
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
	self.removecolors(self.newnetrxAccBranchPage + " :input");
	//prevent from entering characters
	commonMethodInvoker.validateNumber(self.phone);
	commonMethodInvoker.validateNumber(self.mobile);
	commonMethodInvoker.validateNumber(self.numberOfDevices);

	$j(self.netrxAccBranchModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});	
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}	