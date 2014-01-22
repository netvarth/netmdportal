 function ViewOrganizationBranchUI(orgUIStartup){
	this.viewOrgnzAccBrchPage = "#viewOrgnBranchHeader";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.pageTitle = $j('#pageTitle');
	this.updateButton = this.viewOrgnzAccBrchPage + " #orgzuservwbtnDone";
	this.editButton = this.viewOrgnzAccBrchPage + " #orgzuservwbtnEdit";
	this.cancelButton = this.viewOrgnzAccBrchPage + " #orgzuservwbtnCancel";  
	this.ptbBack = '#orgaccbrachPTBContainer #btn_back_ptb_id';
	this.ptbUp = '#orgaccbrachPTBContainer #btn_up_ptb_id';
	this.ptbDown = '#orgaccbrachPTBContainer #btn_down_ptb_id';  
	this.id = this.viewOrgnzAccBrchPage + " #orgid ";
	this.brachId=this.viewOrgnzAccBrchPage + " #userid";
	this.branchStatus=this.viewOrgnzAccBrchPage + " #branchStatus";
	this.usertype=this.viewOrgnzAccBrchPage + " #usertype";
	this.usertypelabel=this.viewOrgnzAccBrchPage + " #usertypelabel";
	this.usertypediv=this.viewOrgnzAccBrchPage + " #usertypediv";
	this.usertypesel=this.viewOrgnzAccBrchPage + " #usertypesel";
	this.inputFields = this.viewOrgnzAccBrchPage + " :input[type=text]";
	this.firstName=this.viewOrgnzAccBrchPage + " #firstName";
	this.lastName=this.viewOrgnzAccBrchPage + " #lastName";
	this.address=this.viewOrgnzAccBrchPage + " #address";
	this.phone = this.viewOrgnzAccBrchPage + " #branchPhone";
	this.mobile=this.viewOrgnzAccBrchPage + " #branchMobile";
	this.email = this.viewOrgnzAccBrchPage + " #email";
	this.username= this.viewOrgnzAccBrchPage + " #username";
	this.orgUIStartup=orgUIStartup;
	this.ViewOrgBranchPTB = new ViewOrgBranchPTB(this);
}
ViewOrganizationBranchUI.prototype.getOrgnzUIService = function() {
	var orgUIStartup = this.getOrgUIStartup();
	return orgUIStartup.getOrgnzUIService();
}
ViewOrganizationBranchUI.prototype.getOrgUIStartup = function() {
	return this.orgUIStartup;
}
ViewOrganizationBranchUI.prototype.getorgnAccTableNavigator = function() {
	var orgAccBrchUI = this.getOrgUIStartup();
	return orgAccBrchUI.getorgnAccTableNavigator();
}
ViewOrganizationBranchUI.prototype.setBranchId= function(branchId) {
	var orgAccBrchUI = this.getOrgUIStartup();
	return orgAccBrchUI.setBranchId(branchId);
}
ViewOrganizationBranchUI.prototype.getBranch = function() {
	return this.branch;
}
ViewOrganizationBranchUI.prototype.setBranch = function(branch) {
	this.branch=branch;
}
ViewOrganizationBranchUI.prototype.getorgAccBrchTableNavigator = function() {
	var orgAccBrchUI = this.getOrgUIStartup();
	return orgAccBrchUI.getorgAccBrchTableNavigator();
}
ViewOrganizationBranchUI.prototype.getvieworgBranchPTB = function() {
	return this.ViewOrgBranchPTB;
}

ViewOrganizationBranchUI.prototype.setPageTitle = function() {
	this.pageTitle.empty().html("View Organization User");
}
ViewOrganizationBranchUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
	commonMethodInvoker.removeErrorColor(self.phone);
	commonMethodInvoker.removeErrorColor(self.mobile);
	commonMethodInvoker.removeErrorColor(self.email);
}
ViewOrganizationBranchUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
ViewOrganizationBranchUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.usertypediv).show();
	$j(self.usertypelabel).hide();
	self.fillUsertypeList(this.usertypesel);
	$j(self.viewOrgnzAccBrchPage + " input[type=text]").removeAttr('readonly');
	$j(self.viewOrgnzAccBrchPage + " input[type=text]").removeClass('newBox');
	$j(self.viewOrgnzAccBrchPage + " textarea").removeClass('newBox');
	$j(self.viewOrgnzAccBrchPage + " textarea").removeAttr('readonly');
		$j(self.brachId).addClass('newBox');
		$j(self.brachId).attr('readonly','readonly');
		$j(self.id).addClass('newBox');
		$j(self.id).attr('readonly','readonly');
		$j(self.branchStatus).addClass('newBox');
		$j(self.branchStatus).attr('readonly','readonly');
		$j(self.username).addClass('newBox');
		$j(self.username).attr('readonly','readonly');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
}
ViewOrganizationBranchUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.usertypediv).hide();
	$j(self.usertypelabel).show();
	$j(self.viewOrgnzAccBrchPage + " input[type=text]").attr('readonly',true);
	$j(self.viewOrgnzAccBrchPage + " input[type=text]").addClass('newBox');
	$j(self.viewOrgnzAccBrchPage + " textarea").attr('readonly',true);
	$j(self.viewOrgnzAccBrchPage + " textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
}
ViewOrganizationBranchUI.prototype.init = function(branchId) {
	var self = this;
	var ViewOrgBranchPTB = self.getvieworgBranchPTB();
	ViewOrgBranchPTB.init(self);
	pageHandler.create(constants.VIEWORGBRCHPAGEURL);
	self.readable();
	self.viewOrgBranchDetails(branchId);
	pageHandler.setActivePage(self);
	self.bindEvents();
	
}
ViewOrganizationBranchUI.prototype.bindEvents = function() {
	self = this;
	parent = self;
	$j(self.editButton).die('click').live('click',function(){
 		self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self.writable();
	});
	$j(self.cancelButton).die('click').live('click',function(){
		self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var branchInfo = self.getBranch();
		var branchId = branchInfo.userDetails.globalId;
		self.viewOrgBranchDetails(branchId);
		self.readable();
	});
	 $j(self.updateButton).die('click').live('click',function(){
		self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var branchPass = self.getBranchRequest();
		var branchValidator = new OrganizationUserValidator();
		var error  = branchValidator.validate(branchPass, self);
		if(error.errorStatus==false) {
			var netmdUIService = self.getOrgnzUIService();
			var orgzResponse = netmdUIService.updateAccBranchOrg(branchPass);
			//alert(JSON.stringify(orgzResponse));
			if(orgzResponse.success==true) {
				showTip(constants.USERUPDATESUCCESS);//For showing the global Tip
				var branchInfo = self.getBranch();
				//alert(JSON.stringify(branchInfo));
				self.viewOrgBranchDetails(branchInfo.userDetails.globalId);
				self.readable();
			} else
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(orgzResponse.error));
		} else
			self.createError(error);	
	}); 
	
	
}



ViewOrganizationBranchUI.prototype.viewOrgBranchDetails = function(branchId) {
	self=this;
	self.setBranchId(branchId);
	var NetorgUIService = self.getOrgnzUIService();
	var orgzResponse = NetorgUIService.viewOrgBranchDetails(branchId);
	//alert(JSON.stringify(orgzResponse));
	 if(!orgzResponse.errorMessage) {
		self.setBranch(orgzResponse);
		$j(self.id).val(orgzResponse.userDetails.organisationId);
		$j(self.brachId).val(orgzResponse.userDetails.globalId);
		$j(self.branchStatus).val(orgzResponse.userDetails.status);
		$j(self.firstName).val(orgzResponse.userDetails.firstName);
		$j(self.phone).val(orgzResponse.userDetails.phone);
		$j(self.usertype).val(orgzResponse.userDetails.userType);
		$j(self.usertypesel).val(orgzResponse.userDetails.userType);
		$j(self.email).val(orgzResponse.userDetails.email);
		$j(self.address).val(commonMethodInvoker.br2nl(orgzResponse.userDetails.address));
		$j(self.mobile).val(orgzResponse.userDetails.mobile);
		$j(self.lastName).val(orgzResponse.userDetails.lastName);
		$j(self.username).val(orgzResponse.userDetails.userName);
		
	} else 
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData,orgzResponse.errorMessage); 
	self.setPageTitle("View User");
}
ViewOrganizationBranchUI.prototype.fillUsertypeList = function(controlobj) {
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


ViewOrganizationBranchUI.prototype.getBranchRequest = function() {
	var self=this;
	var userDetails = new organizationUserDTO();
	var name=$j(self.firstName).val();
	name= name.replace(/\b[a-z]/g, function(letter) {
		return letter.toUpperCase();
	});
	
	userDetails.setglobalId($j(self.brachId).val());
	userDetails.setOrganisationId($j(self.id).val());
	userDetails.setfirstName(name);
	userDetails.setlastName($j(self.lastName).val());
	userDetails.setaddress(commonMethodInvoker.nl2br($j(self.address).val()));
	userDetails.setemail($j(self.email).val());
	userDetails.setphone($j(self.phone).val());
	userDetails.setmobile($j(self.mobile).val());
	userDetails.setuserType($j(self.usertypesel).val());
	userDetails.setuserName($j(self.username).val());
	//userDetails.setpassword($j(self.password).val());
	
	return userDetails;
}

ViewOrganizationBranchUI.prototype.getPrevId = function(curId,branchResult) {
	var prevId;
	$j(branchResult.organisationUsers).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.organisationUsers).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=branchResult.organisationUsers[index-1].globalId;
		}
	});
	return prevId;	
}
ViewOrganizationBranchUI.prototype.getNextId = function(curId,branchResult) {
	var nextId;
	$j(branchResult.organisationUsers).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.organisationUsers).length;
			var comp=arrayLength-1;
			if(index==comp)
				nextId = curId;
			else
				nextId=branchResult.organisationUsers[index+1].globalId;	
		}
	});	
	return nextId;	
} 