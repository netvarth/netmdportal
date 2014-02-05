function NewBranchUI(BranchUIStartup) {
this.netlimsAccBranchModal = '#netlimsAccBranchModal';
	this.newnetlimsAccBranchPage = this.netlimsAccBranchModal + " #newBranchNetlimsAccForm";
	this.createButton = $j(this.newnetlimsAccBranchPage + " #btnNewBranchCreate");
	this.cancelButton = $j(this.newnetlimsAccBranchPage + " #btnNewBranchCancel");
	this.errorHeader = $j(this.newnetlimsAccBranchPage + " #errorDivHeader");
	this.errorData = $j(this.newnetlimsAccBranchPage + " #errorDivNewNetlimsAccBranchData");
	this.labId=userdata.labId;
	this.inputFields = this.newnetlimsAccBranchPage + " :input[type=text]";
	this.name=this.newnetlimsAccBranchPage + " #organizationname";
	this.address=this.newnetlimsAccBranchPage + " #organizationaddress";
	this.phone = this.newnetlimsAccBranchPage + " #phone";
	this.mobile=this.newnetlimsAccBranchPage + " #mobile";
	this.email = this.newnetlimsAccBranchPage + " #Email";
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
NewBranchUI.prototype.getnetlimsAccTableNavigator = function() {
	var branchUIStartup = this.getBranchUIStartup();
	return branchUIStartup.getnetlimsAccTableNavigator();
}
NewBranchUI.prototype.getNetlimsUIService = function() {
	var branchUIStartup = this.getBranchUIStartup();
	return branchUIStartup.getNetlimsUIService();
}

NewBranchUI.prototype.init = function() {
	self =this;
	self.bindEvents();
}

NewBranchUI.prototype.clearFields = function() {
	$j(this.newnetlimsAccBranchPage + " input[type=text]").val("");
	$j(this.newnetlimsAccBranchPage + " textarea").val("");
}
NewBranchUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
	commonMethodInvoker.removeErrorColor(self.phone);
	commonMethodInvoker.removeErrorColor(self.mobile);
	commonMethodInvoker.removeErrorColor(self.email);
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
	$j(self.netlimsAccBranchModal + self.newnetlimsAccBranchPage + " input[type=text]").val("");	
	$j(self.netlimsAccBranchModal).trigger('reveal:close');
	$j(self.netlimsAccBranchModal).remove();
	if(self.getBranchCreationStatus() == true){
			var selName="labId";
	var selValue=self.labId;
	var selOperator = "eq";
	var exp = new ExpressionListDTO();
	var expr = new ExpressionDTO(selName,selValue,selOperator);
	exp.add(expr);
	var netlimsAccTableNavigator = self.getnetlimsAccTableNavigator();
	netlimsAccTableNavigator.setExp(exp);
		
			//var branchTableNavigator = self.getnetlimsAccTableNavigator();
			netlimsAccTableNavigator.list();
		
	}
	self.setBranchCreationStatus(false);
	self=pageHandler.getActivePage();
}
NewBranchUI.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var branch = self.getBranch();
	var branchValidator = new NetlimsBranchValidator();
	var error  = branchValidator.validate(branch,self);
	//alert("Error"+error);
	if(error.errorStatus==false) {
		var branchService = self.getNetlimsUIService();
		var branchResponse =branchService.createBranchNetlims(branch);
		//alert(JSON.stringify(branchResponse));
		if(branchResponse.error==null) {
			//self.setBranchCreationStatus(true);
			showTip(constants.BRANCHCREATESUCCESS);//For showing the global Tip			
			self.clearFields();
		} else 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(branchResponse.error));
	} else 
		self.createError(error); 
}
NewBranchUI.prototype.getBranch = function() {
	var self=this;
	var branch = new BranchNetlimsDTO();
	var name=$j(self.name).val();
	name= name.replace(/\b[a-z]/g, function(letter) {
		return letter.toUpperCase();
	});
	
	branch.setlabId(self.labId);
	branch.setName(name);
	branch.setEmail($j(self.email).val());
	branch.setAddress(commonMethodInvoker.nl2br($j(self.address).val()));
	branch.setPhone($j(self.phone).val());
	branch.setMobile($j(self.mobile).val());
	return branch;
}

NewBranchUI.prototype.bindEvents = function() {
self = this;	
	self.removecolors(self.newnetlimsAccBranchPage + " :input");
	//prevent from entering characters
	commonMethodInvoker.validateNumber(self.phone);
	commonMethodInvoker.validateNumber(self.mobile);

$j(self.netlimsAccBranchModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});	
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}	