function NewBranchUI(BranchUIStartup) {
this.orgAccBranchModal = '#organizationusermodal';
	this.neworgAccBranchPage = this.orgAccBranchModal + " #newOrgnzAccBranchForm";
	this.createButton = $j(this.neworgAccBranchPage + " #btnNewuserCreate");
	this.cancelButton = $j(this.neworgAccBranchPage + " #btnNewUserCancel");
	this.errorHeader = $j(this.neworgAccBranchPage + " #errorDivHeader");
	this.errorData = $j(this.neworgAccBranchPage + " #errorDivNewOrgAccBranchData");
	//this.netmdId=userdata.netmdId;
	this.orgzId=BranchUIStartup.getOrgnzId();
	this.inputFields = this.neworgAccBranchPage + " :input[type=text]";
	this.firstname=this.neworgAccBranchPage + " #organizationfirstname";
	this.lastname=this.neworgAccBranchPage + " #organizationlastname";
	this.usertype=this.neworgAccBranchPage + " #usertype";
	this.address=this.neworgAccBranchPage + " #organizationaddress";
	this.phone = this.neworgAccBranchPage + " #organizationphone";
	this.mobile=this.neworgAccBranchPage + " #organizationmobile";
	this.email = this.neworgAccBranchPage + " #organizationemail";
	this.password = this.neworgAccBranchPage + " #password";
	this.rePassword = this.neworgAccBranchPage + " #rePassword";
	this.username = this.neworgAccBranchPage + " #username";
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
NewBranchUI.prototype.getorgnAccTableNavigator = function() {
	var branchUIStartup = this.getBranchUIStartup();
	return branchUIStartup.getorgnAccTableNavigator();
}
NewBranchUI.prototype.getOrgnzUIService = function() {
	var branchUIStartup = this.getBranchUIStartup();
	return branchUIStartup.getOrgnzUIService();
}

NewBranchUI.prototype.init = function() {
	self =this;
	self.fillUsertypeList(this.usertype);
	self.bindEvents();
}

NewBranchUI.prototype.clearFields = function() {
	$j(this.neworgAccBranchPage + " input[type=text]").val("");
	$j(this.neworgAccBranchPage + " textarea").val("");
}
NewBranchUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.firstname);
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
	$j(self.orgAccBranchModal + self.neworgAccBranchPage + " input[type=text]").val("");	
	$j(self.orgAccBranchModal).trigger('reveal:close');
	$j(self.orgAccBranchModal).remove();
	if(self.getBranchCreationStatus() == true){
			var selName="organisationId";
	var selValue=self.orgzId;
	var selOperator = "eq";
	var exp = new ExpressionListDTO();
	var expr = new ExpressionDTO(selName,selValue,selOperator);
	exp.add(expr);
	var orgAccTableNavigator = self.getorgnAccTableNavigator();
	orgAccTableNavigator.setExp(exp);
		
			//var branchTableNavigator = self.getorgnAccTableNavigator();
			orgAccTableNavigator.list("orglist");
		
	}
	self.setBranchCreationStatus(false);
	self=pageHandler.getActivePage();
}
NewBranchUI.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var branch = self.getUser();
	var branchValidator = new OrganizationUserValidator();
	var error  = branchValidator.validate(branch,self);
	//alert("Error"+error);
	if(error.errorStatus==false) {
		var branchService = self.getOrgnzUIService();
		var branchResponse =branchService.createBranchOrg(branch);
		//alert(JSON.stringify(branchResponse));
		if(branchResponse.success==true) {
			self.setBranchCreationStatus(true);
			showTip(constants.USERCREATESUCCESS);//For showing the global Tip			
			self.clearFields();
		} else 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(branchResponse.error));
	} else 
		self.createError(error); 
}
NewBranchUI.prototype.getUser = function() {
	var self=this;
	var branch = new organizationUserDTO();
	var name=$j(self.firstname).val();
	name= name.replace(/\b[a-z]/g, function(letter) {
		return letter.toUpperCase();
	});
	
	branch.setOrganisationId(parseInt(self.orgzId));
	branch.setfirstName(name);
	branch.setlastName($j(self.lastname).val());
	branch.setaddress(commonMethodInvoker.nl2br($j(self.address).val()));
	branch.setemail($j(self.email).val());
	branch.setphone($j(self.phone).val());
	branch.setmobile($j(self.mobile).val());
	branch.setuserType($j(self.usertype).val());
	branch.setuserName($j(self.username).val());
	branch.setpassword($j(self.password).val());
	
	return branch;
}

NewBranchUI.prototype.fillUsertypeList = function(controlobj) {
	var usertypeList=[];
	ajaxProcessor.setUrl(constants.DEPARTMENTLISTURL);
	var enumList=ajaxProcessor.get();
	$j(enumList.enumListDTO).each(function (index, list) {
		if(list.key=="UserTypeEnum") {
			$j(list.enumValues).each(function (enumvalueIndex, enumvalue) {
				usertypeList.push(enumvalue);
			});
		}
	});
	$j(usertypeList).each(function (Index, List) {
			var freqlist=List;
			$j(controlobj).append('<option  value="'+freqlist+'">'+freqlist+'</option>');
	}); 
}

NewBranchUI.prototype.bindEvents = function() {
self = this;
	self.removecolors(self.neworgAccBranchPage + " :input");
	commonMethodInvoker.validateNumber(self.phone);
	commonMethodInvoker.validateNumber(self.mobile);
	commonMethodInvoker.validateNumber(self.numberOfDevices);

	$j(self.orgAccBranchModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});	
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}	