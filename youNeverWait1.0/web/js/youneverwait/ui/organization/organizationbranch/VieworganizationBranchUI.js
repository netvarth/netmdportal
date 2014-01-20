 function ViewOrganizationBranchUI(orgUIStartup){
	this.viewOrgnzAccBrchPage = "#viewOrgnBranchHeader";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.pageTitle = $j('#pageTitle');
	this.updateButton = this.viewOrgnzAccBrchPage + " #orgzuservwbtnDone";
	this.editButton = this.viewOrgnzAccBrchPage + " #orgzuservwbtnEdit";
	this.cancelButton = this.viewOrgnzAccBrchPage + " #orgzuservwbtnCancel";  
	this.ptbBack = '#GeneralPTBContainer #btn_back_ptb_id';
	this.ptbUp = '#GeneralPTBContainer #btn_up_ptb_id';
	this.ptbDown = '#GeneralPTBContainer #btn_down_ptb_id';  
	this.id = this.viewOrgnzAccBrchPage + " #orgid ";
	this.brachId=this.viewOrgnzAccBrchPage + " #userid";
	this.branchStatus=this.viewOrgnzAccBrchPage + " #branchStatus";
	this.usertype=this.viewOrgnzAccBrchPage + " #usertype";
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
ViewOrganizationBranchUI.prototype.getorgAccTableNavigator = function() {
	var orgAccBrchUI = this.getOrgUIStartup();
	return orgAccBrchUI.getorgAccTableNavigator();
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
		$j(self.deviceNo).addClass('newBox');
		$j(self.deviceNo).attr('readonly','readonly');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
}
ViewOrganizationBranchUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
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
		var branchId = branchInfo.branch.globalId;
		self.viewOrgBranchDetails(branchId);
		self.readable();
	});
	 $j(self.updateButton).die('click').live('click',function(){
		self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var branchPass = self.getBranchRequest();
		var branchValidator = new NetmdBranchValidator();
		var error  = branchValidator.validate(branchPass, self);
		if(error.errorStatus==false) {
			var netmdUIService = self.getOrgnzUIService();
			var orgzResponse = netmdUIService.updateAccBranchOrg(branchPass);
			//alert(JSON.stringify(orgzResponse));
			if(orgzResponse.success==true) {
				showTip(constants.BRANCHUPDATESUCCESS);//For showing the global Tip
				var branchInfo = self.getBranch();
				//alert(JSON.stringify(branchInfo));
				self.viewOrgBranchDetails(branchInfo.branch.globalId);
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
	alert(JSON.stringify(orgzResponse));
	/* if(!orgzResponse.errorMessage) {
		self.setBranch(orgzResponse);
		$j(self.id).val(orgzResponse.userDetails.netMdId);
		$j(self.brachId).val(orgzResponse.userDetails.globalId);
		$j(self.branchStatus).val(orgzResponse.userDetails.status);
		$j(self.firstName).val(orgzResponse.userDetails.name);
		$j(self.phone).val(orgzResponse.userDetails.phone);
		$j(self.usertype).val(orgzResponse.userDetails.numberOfDevices);
		$j(self.email).val(orgzResponse.userDetails.email);
		$j(self.address).val(commonMethodInvoker.br2nl(orgzResponse.userDetails.address));
		$j(self.mobile).val(orgzResponse.userDetails.mobile);
		$j(self.lastName).val(orgzResponse.userDetails.email);
		$j(self.username).val(orgzResponse.userDetails.email);
		
	} else 
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData,orgzResponse.errorMessage); */
	self.setPageTitle("View User");
}

ViewOrganizationBranchUI.prototype.viewPassPhraseTableAcc = function(orgzResponse) {
	self=this;
	$j(self.secondaryTable).dataTable().fnClearTable();
	$j(self.primaryTable).dataTable().fnClearTable();
		$j(orgzResponse.branch).each(function (index,branchInfo) {
	var myData='<input type="button" value="clear" class="clearMacid stdbtn">';
	var makePrim='<input type="button" value="clear"  class="clearMacid stdbtn"><input type="button" value="make primary"  class="chngprimdevice stdbtn"  >';
		$j(branchInfo.passPhrase).each(function (index,passPhrase) {
		if(passPhrase.primary==true)
		{
		if(passPhrase.macId==null)
		{		var rowData=$j(self.primaryTable).dataTable().fnAddData([passPhrase.passPhrase,'Nil',myData]);
			var row=$j(self.primaryTable).dataTable().fnSettings().aoData[rowData].nTr;
			$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		else
		{
		var rowData=$j(self.primaryTable).dataTable().fnAddData([passPhrase.passPhrase,passPhrase.macId,myData]);
		var row=$j(self.primaryTable).dataTable().fnSettings().aoData[rowData].nTr;
		$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		}
		// var button=$j('<button name="save"/>');
		else
		{
		if(passPhrase.macId==null)
		{		
		var rowData=$j(self.secondaryTable).dataTable().fnAddData([passPhrase.passPhrase,'Nil',makePrim]);
		var row=$j(self.secondaryTable).dataTable().fnSettings().aoData[rowData].nTr
		$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		else
		{
		var rowData=$j(self.secondaryTable).dataTable().fnAddData([passPhrase.passPhrase,passPhrase.macId,makePrim]);
		var row=$j(self.secondaryTable).dataTable().fnSettings().aoData[rowData].nTr; 
		$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		}
				
		});	
	});

}

ViewOrganizationBranchUI.prototype.getBranchRequest = function() {
	var self=this;
	var branch = new BranchNetmdDTO();
	var device=$j(self.deviceNo).val();
	if(device==""){device=0;}
	var name=$j(self.name).val();
	name= name.replace(/\b[a-z]/g, function(letter) {
		return letter.toUpperCase();
	});
	
	branch.setglobalId($j(self.brachId).val());
	branch.setnetMdId($j(self.id).val());
	branch.setName(name);
	branch.setEmail($j(self.email).val());
	branch.setAddress(commonMethodInvoker.nl2br($j(self.address).val()));
	branch.setPhone($j(self.phone).val());
	branch.setMobile($j(self.mobile).val());
	branch.setnumberOfDevices(parseInt(device));
	return branch;
}

ViewOrganizationBranchUI.prototype.getPrevId = function(curId,branchResult) {
	var prevId;
	$j(branchResult.netmdBranch).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.netmdBranch).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=branchResult.netmdBranch[index-1].globalId;
		}
	});
	return prevId;	
}
ViewOrganizationBranchUI.prototype.getNextId = function(curId,branchResult) {
	var nextId;
	$j(branchResult.netmdBranch).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.netmdBranch).length;
			var comp=arrayLength-1;
			if(index==comp)
				nextId = curId;
			else
				nextId=branchResult.netmdBranch[index+1].globalId;	
		}
	});	
	return nextId;	
} 