
function ViewNetrxUI(netrxUIStartup){

	
	this.viewNetrxPage = "#viewNetrxHeader";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.pageTitle = $j('#pageTitle');
	this.updateButton = this.viewNetrxPage + " #netrxvwbtnDone";
	this.editButton = this.viewNetrxPage + " #netrxvwbtnEdit";
	this.cancelButton = this.viewNetrxPage + " #netrxvwbtnCancel";  
	this.ptbBack = '#netrxPTBContainer #btn_back_ptb_id';
	this.ptbUp = '#netrxPTBContainer #btn_up_ptb_id';
	this.ptbDown = '#netrxPTBContainer #btn_down_ptb_id';  
	this.netrxid = this.viewNetrxPage + " #netrxid ";
	this.inputFields = this.viewNetrxPage + " :input[type=text]";
	this.username=this.viewNetrxPage + " #usernameNetrx";
	this.organizationname=this.viewNetrxPage + " #organizationName";
	this.ownerfirstname = this.viewNetrxPage + " #ownerFirstName";
	this.ownerlastname=this.viewNetrxPage + " #ownerLastName";
	this.owneremail = this.viewNetrxPage + " #ownerEmail";
	this.ownerphone = this.viewNetrxPage + " #ownerPhone";
	this.ownermobile = this.viewNetrxPage + " #ownerMobile";
	this.headofficename = this.viewNetrxPage + " #headOfficeName";
	this.headofficephone = this.viewNetrxPage + " #headOfficePhone";
	this.headofficemobile = this.viewNetrxPage + " #headOfficeMobile";
	this.headofficeemail = this.viewNetrxPage + " #headOfficeEmail";
	this.owneraddress = this.viewNetrxPage + " #ownerAddress";
	this.headofficeaddress = this.viewNetrxPage + " #headOfficeAddress";
	this.netrxUIStartup=netrxUIStartup;
	this.viewNetrxPTB = new ViewNetrxPTB(this);
}

ViewNetrxUI.prototype.getNetrxUIService = function() {
	var netrxUIStartup = this.getNetrxUIStartup();
	return netrxUIStartup.getNetrxUIService();
}
ViewNetrxUI.prototype.getNetrxUIStartup = function() {
	return this.netrxUIStartup;
}
ViewNetrxUI.prototype.getnetrxTableNavigator = function() {
	var netrxUI = this.getNetrxUIStartup();
	return netrxUI.getnetrxTableNavigator();
}
ViewNetrxUI.prototype.setNetrxId= function(netrxId) {
	var netrxUI = this.getNetrxUIStartup();
	return netrxUI.setNetrxId(netrxId);
}
ViewNetrxUI.prototype.getNetrx = function() {
	return this.branch;
}
ViewNetrxUI.prototype.setNetrx = function(branch) {
	this.branch=branch;
}
ViewNetrxUI.prototype.getnetrxAdminTableNavigator = function() {
	var netrxUI = this.getNetrxUIStartup();
	return netrxUI.getnetrxAdminTableNavigator();
}
ViewNetrxUI.prototype.getviewNetrxPTB = function() {
	return this.viewNetrxPTB;
}

ViewNetrxUI.prototype.setPageTitle = function() {
	this.pageTitle.empty().html("View Netrx ");
}
ViewNetrxUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.inputFields);
	
}
ViewNetrxUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
ViewNetrxUI.prototype.writable = function() {
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
	$j(self.viewNetrxPage + " input[type=text]").removeAttr('readonly');
	$j(self.viewNetrxPage + " input[type=text]").removeClass('newBox');
	$j(self.viewNetrxPage + " textarea").removeClass('newBox');
	$j(self.viewNetrxPage + " textarea").removeAttr('readonly');
		$j(self.netrxid).addClass('newBox');
		$j(self.netrxid).attr('readonly','readonly');
		$j(self.username).addClass('newBox');
		$j(self.username).attr('readonly','readonly');
		
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
}
ViewNetrxUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewNetrxPage + " input[type=text]").attr('readonly',true);
	$j(self.viewNetrxPage + " input[type=text]").addClass('newBox');
	$j(self.viewNetrxPage + " textarea").attr('readonly',true);
	$j(self.viewNetrxPage + " textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
}
ViewNetrxUI.prototype.init = function(netrxId) {
	var self = this;
	var viewNetrxPTB = self.getviewNetrxPTB();
	viewNetrxPTB.init(self);
	pageHandler.create(constants.VIEWNETRXDETAILPAGEURL);
	self.readable();
	self.viewNetmdDetails(netrxId);
	pageHandler.setActivePage(self);
	self.bindEvents();
	
}
ViewNetrxUI.prototype.bindEvents = function() {
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
		var netmdInfo = self.getNetrx();
		var netmdId = netmdInfo.netMd.globalId;
		self.viewNetmdDetails(netmdId);
		self.readable();
	});
	$j(self.updateButton).die('click').live('click',function(){
		 self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var netmd = self.getNetmdRequest();
		//alert(JSON.stringify(netmd));
		var netmdValidator = new NetmdViewValidator();
		var error  = netmdValidator.validate(netmd,self);
		if(error.errorStatus==false) {
			var netmdUIService = self.getNetrxUIService();
			var netmdResponse = netmdUIService.updateNetmd(netmd);
			//alert(JSON.stringify(netmdResponse));
				if(netmdResponse.error==null) {
					showTip(constants.NETMDUPDATESUCCESS);//For showing the global Tip
					var netmdInfo = self.getNetrx();
					//alert(JSON.stringify(branchInfo));
					self.viewNetmdDetails(netmdInfo.netMd.globalId);
					self.readable();
				} else
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netmdResponse.error));
			} else
			self.createError(error);
	}); 
	 
}
ViewNetrxUI.prototype.viewNetmdDetails = function(netmdId) {
	self=this;
	self.setNetrxId(netmdId);
	var NetmdUIService = self.getNetrxUIService();
	var netmdInfo = NetmdUIService.viewNetmdDetails(netmdId);
	//alert(JSON.stringify(netmdInfo));
	if(!netmdInfo.errorMessage) {
		self.setNetrx(netmdInfo);
		$j(self.netmdid).val(netmdInfo.netMd.globalId);
		$j(self.organizationname).val(netmdInfo.netMd.name);
		$j(self.headofficeemail).val(netmdInfo.netMd.headOfficeEmail);
		$j(self.headofficephone).val(netmdInfo.netMd.headOfficePhone);
		$j(self.ownerfirstname).val(netmdInfo.netMd.ownerFirstName);
		$j(self.ownerlastname).val(netmdInfo.netMd.ownerLastName);
		$j(self.owneremail).val(netmdInfo.netMd.ownerEmail);
		$j(self.ownerphone).val(netmdInfo.netMd.ownerPhone);
		$j(self.owneraddress).val(commonMethodInvoker.br2nl(netmdInfo.netMd.ownerAddress));
		$j(self.headofficeaddress).val(commonMethodInvoker.br2nl(netmdInfo.netMd.headOfficeAddress));
		$j(self.ownermobile).val(netmdInfo.netMd.ownerMobile);
		$j(self.headofficename).val(netmdInfo.netMd.headOfficeName);
		$j(self.headofficemobile).val(netmdInfo.netMd.headOfficeMobile);
		$j(self.username).val(netmdInfo.netMd.userName);
				
	} else 
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData,netmdInfo.errorMessage);
	self.setPageTitle("View Netmd ");
}

ViewNetrxUI.prototype.getNetmdRequest = function() {
	var self=this;
	var netmd = new NetmdDTO();
	var netmdname;
	netmdname=$j(self.organizationname).val();
	netmdname=netmdname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
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
	var headofficename;
	headofficename= $j(self.headofficename).val();
	headofficename=headofficename.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	
	netmd.setName(netmdname);
	netmd.setglobalId(parseInt($j(self.netmdid).val()));
	netmd.setownerFirstName(ownerfirstname);
	netmd.setownerLastName(ownerlastname);
	netmd.setownerEmail($j(self.owneremail).val());
	netmd.setownerPhone($j(self.ownerphone).val());
	netmd.setownerMobile($j(self.ownermobile).val());
	netmd.setownerAddress(commonMethodInvoker.nl2br($j(self.owneraddress).val()));
	netmd.setheadOfficeAddress(commonMethodInvoker.nl2br($j(self.headofficeaddress).val()));
	netmd.setheadOfficeName(headofficename);
	netmd.setheadOfficeEmail($j(self.headofficeemail).val());
	netmd.setheadOfficePhone($j(self.headofficephone).val());
	netmd.setheadOfficeMobile($j(self.headofficemobile).val());
	//netmd.setuserName($j(self.username).val());
	//netmd.setpassword($j(self.password).val());
	return netmd;
}

ViewNetrxUI.prototype.getPrevId = function(curId,branchResult) {
	var prevId;
	$j(branchResult.netMd).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.netMd).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=branchResult.netMd[index-1].globalId;
		}
	});
	return prevId;	
}
ViewNetrxUI.prototype.getNextId = function(curId,branchResult) {
	var nextId;
	$j(branchResult.netMd).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.netMd).length;
			var comp=arrayLength-1;
			if(index==comp)
				nextId = curId;
			else
				nextId=branchResult.netMd[index+1].globalId;	
		}
	});	
	return nextId;	
}  