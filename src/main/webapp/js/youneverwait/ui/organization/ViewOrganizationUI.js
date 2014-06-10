
function ViewOrganizationUI(orgUIStartup){

	
	this.viewOrgPage = "#viewOrgnzHeader";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.pageTitle = $j('#pageTitle');
	this.updateButton = this.viewOrgPage + " #orgvwbtnDone";
	this.editButton = this.viewOrgPage + " #orgvwbtnEdit";
	this.cancelButton = this.viewOrgPage + " #orgvwbtnCancel";  
	this.ptbBack = '#orgnPTBContainer #btn_back_ptb_id';
	this.ptbUp = '#orgnPTBContainer #btn_up_ptb_id';
	this.ptbDown = '#orgnPTBContainer #btn_down_ptb_id';  
	this.netmdid = this.viewOrgPage + " #netmdid ";
	this.inputFields = this.viewOrgPage + " :input[type=text]";
	this.username=this.viewOrgPage + " #usernameNetmd";
	this.organizationname=this.viewOrgPage + " #organizationName";
	this.ownerfirstname = this.viewOrgPage + " #ownerFirstName";
	this.ownerlastname=this.viewOrgPage + " #ownerLastName";
	this.owneremail = this.viewOrgPage + " #ownerEmail";
	this.ownerphone = this.viewOrgPage + " #ownerPhone";
	this.ownermobile = this.viewOrgPage + " #ownerMobile";
	this.departmentType = this.viewOrgPage + " #headOfficeName";
	this.headofficephone = this.viewOrgPage + " #headOfficePhone";
	this.headofficemobile = this.viewOrgPage + " #headOfficeMobile";
	this.headofficeemail = this.viewOrgPage + " #headOfficeEmail";
	this.owneraddress = this.viewOrgPage + " #ownerAddress";
	this.headofficeaddress = this.viewOrgPage + " #headOfficeAddress";
	this.orgUIStartup=orgUIStartup;
	this.viewOrgnzPTB = new ViewOrgnzPTB(this);
}

ViewOrganizationUI.prototype.getOrgnUIService = function() {
	var orgUIStartup = this.getOrgUIStartup();
	return orgUIStartup.getOrgnUIService();
}
ViewOrganizationUI.prototype.getOrgUIStartup = function() {
	return this.orgUIStartup;
}
ViewOrganizationUI.prototype.getorgznTableNavigator = function() {
	var orgnzUI = this.getOrgUIStartup();
	return orgnzUI.getorgznTableNavigator();
}
ViewOrganizationUI.prototype.setOrgznId= function(orgId) {
	var orgnzUI = this.getOrgUIStartup();
	return orgnzUI.setOrgznId(orgId);
}
ViewOrganizationUI.prototype.getOrgnz = function() {
	return this.branch;
}
ViewOrganizationUI.prototype.setOrgnz = function(branch) {
	this.branch=branch;
}
ViewOrganizationUI.prototype.getorgAdminTableNavigator = function() {
	var orgnzUI = this.getOrgUIStartup();
	return orgnzUI.getorgAdminTableNavigator();
}
ViewOrganizationUI.prototype.getviewOrgnzPTB = function() {
	return this.viewOrgnzPTB;
}

ViewOrganizationUI.prototype.setPageTitle = function() {
	this.pageTitle.empty().html("View Organization ");
}
ViewOrganizationUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.inputFields);
	
}
ViewOrganizationUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
ViewOrganizationUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	commonMethodInvoker.validateNumber(self.headofficephone);
	commonMethodInvoker.validateNumber(self.headofficemobile);
	commonMethodInvoker.validateNumber(self.ownerphone);
	commonMethodInvoker.validateNumber(self.ownermobile);
	$j(self.viewOrgPage + " input[type=text]").removeAttr('readonly');
	$j(self.viewOrgPage + " input[type=text]").removeClass('newBox');
	$j(self.viewOrgPage + " textarea").removeClass('newBox');
	$j(self.viewOrgPage + " textarea").removeAttr('readonly');
		$j(self.netmdid).addClass('newBox');
		$j(self.netmdid).attr('readonly','readonly');
		$j(self.username).addClass('newBox');
		$j(self.username).attr('readonly','readonly');
		$j(self.departmentType).addClass('newBox');
		$j(self.departmentType).attr('readonly','readonly');
		
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
}
ViewOrganizationUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewOrgPage + " input[type=text]").attr('readonly',true);
	$j(self.viewOrgPage + " input[type=text]").addClass('newBox');
	$j(self.viewOrgPage + " textarea").attr('readonly',true);
	$j(self.viewOrgPage + " textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
}
ViewOrganizationUI.prototype.init = function(orgId) {
	var self = this;
	var viewOrgnzPTB = self.getviewOrgnzPTB();
	viewOrgnzPTB.init(self);
	pageHandler.create(constants.VIEWORGDETAILPAGEURL);
	self.readable();
	self.viewOrganzDetails(orgId);
	pageHandler.setActivePage(self);
	self.bindEvents();
	
}
ViewOrganizationUI.prototype.bindEvents = function() {
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
		var orgInfo = self.getOrgnz();
		var orgId = orgInfo.organization.globalId;
		self.viewOrganzDetails(orgId);
		self.readable();
	});
	$j(self.updateButton).die('click').live('click',function(){
		 self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var Organzn = self.getOrgRequest();
		//alert(JSON.stringify(Organzn));
		var orgValidator = new OrgzViewValidator();
		var error  = orgValidator.validate(Organzn,self);
		if(error.errorStatus==false) {
			var orgUIService = self.getOrgnUIService();
			var orgResponse = orgUIService.updateOrgn(Organzn);
			//alert(JSON.stringify(orgResponse));
				if(orgResponse.error==null) {
					showTip(constants.NETMDUPDATESUCCESS);//For showing the global Tip
					var orgInfo = self.getOrgnz();
					//alert(JSON.stringify(branchInfo));
					self.viewOrganzDetails(orgInfo.organization.globalId);
					self.readable();
				} else
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(orgResponse.error));
			} else
			self.createError(error);
	}); 
	 
}
ViewOrganizationUI.prototype.viewOrganzDetails = function(orgId) {
	self=this;
	self.setOrgznId(orgId);
	var orgUIService = self.getOrgnUIService();
	var orgInfo = orgUIService.viewOrganzDetails(orgId);
	//alert(JSON.stringify(orgInfo));
	if(!orgInfo.errorMessage) {
		self.setOrgnz(orgInfo);
		$j(self.netmdid).val(orgInfo.organization.globalId);
		$j(self.organizationname).val(orgInfo.organization.name);
		$j(self.headofficeemail).val(orgInfo.organization.headOfficeEmail);
		$j(self.headofficephone).val(orgInfo.organization.headOfficePhone);
		$j(self.ownerfirstname).val(orgInfo.organization.ownerFirstName);
		$j(self.ownerlastname).val(orgInfo.organization.ownerLastName);
		$j(self.owneremail).val(orgInfo.organization.ownerEmail);
		$j(self.ownerphone).val(orgInfo.organization.ownerPhone);
		$j(self.owneraddress).val(commonMethodInvoker.br2nl(orgInfo.organization.ownerAddress));
		$j(self.headofficeaddress).val(commonMethodInvoker.br2nl(orgInfo.organization.headOfficeAddress));
		$j(self.ownermobile).val(orgInfo.organization.ownerMobile);
		$j(self.departmentType).val(orgInfo.organization.departmentType);
		$j(self.headofficemobile).val(orgInfo.organization.headOfficeMobile);
		$j(self.username).val(orgInfo.organization.userName);
				
	} else 
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData,orgInfo.errorMessage);
	self.setPageTitle("View Organization ");
}

ViewOrganizationUI.prototype.getOrgRequest = function() {
	var self=this;
	var Organzn = new OrganzDTO();
	var orgname;
	orgname=$j(self.organizationname).val();
	orgname=orgname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerfirstname;
	ownerfirstname=$j(self.ownerfirstname).val();
	ownerfirstname=ownerfirstname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerlastname;
	ownerlastname=$j(self.ownerlastname).val();
	ownerlastname=ownerlastname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	
	
	Organzn.setName(orgname);
	Organzn.setglobalId(parseInt($j(self.netmdid).val()));
	Organzn.setownerFirstName(ownerfirstname);
	Organzn.setownerLastName(ownerlastname);
	Organzn.setownerEmail($j(self.owneremail).val());
	Organzn.setownerPhone($j(self.ownerphone).val());
	Organzn.setownerMobile($j(self.ownermobile).val());
	Organzn.setownerAddress(commonMethodInvoker.nl2br($j(self.owneraddress).val()));
	Organzn.setheadOfficeAddress(commonMethodInvoker.nl2br($j(self.headofficeaddress).val()));
	Organzn.setheadOfficeName();
	Organzn.setheadOfficeEmail($j(self.headofficeemail).val());
	Organzn.setheadOfficePhone($j(self.headofficephone).val());
	Organzn.setheadOfficeMobile($j(self.headofficemobile).val());
	Organzn.setdepartmentType($j(self.departmentType).val());
	//Organzn.setuserName($j(self.username).val());
	//Organzn.setpassword($j(self.password).val());
	return Organzn;
}

ViewOrganizationUI.prototype.getPrevId = function(curId,branchResult) {
	var prevId;
	$j(branchResult.organisation).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.organisation).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=branchResult.organisation[index-1].globalId;
		}
	});
	return prevId;	
}
ViewOrganizationUI.prototype.getNextId = function(curId,branchResult) {
	var nextId;
	$j(branchResult.organisation).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.organisation).length;
			var comp=arrayLength-1;
			if(index==comp)
				nextId = curId;
			else
				nextId=branchResult.organisation[index+1].globalId;	
		}
	});	
	return nextId;	
}  