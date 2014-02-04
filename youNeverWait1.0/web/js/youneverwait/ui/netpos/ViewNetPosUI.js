function ViewNetPosUI(NetPosStartup) {
	
	this.viewNetposPage = "#viewNetposHeader";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.pageTitle = $j('#pageTitle');
	this.ordersnetposListTable="#ordersnetposViewTable";
	this.updateButton = this.viewNetposPage + " #netposvwbtnDone";
	this.editButton = this.viewNetposPage + " #netposvwbtnCreate";
	this.cancelButton = this.viewNetposPage + " #netposvwbtnCancel";  
	this.ptbBack = '#netposPTBContainer #btn_back_ptb_id';
	this.ptbUp = '#netposPTBContainer #btn_up_ptb_id';
	this.ptbDown = '#netposPTBContainer #btn_down_ptb_id';  
	this.netposid = this.viewNetposPage + " #netposid ";
	this.inputFields = this.viewNetposPage + " :input[type=text]";
	this.username=this.viewNetposPage + " #username";
	this.organizationname=this.viewNetposPage + " #organizationName";
	this.ownerfirstname = this.viewNetposPage + " #ownerFirstName";
	this.ownerlastname=this.viewNetposPage + " #ownerLastName";
	this.owneremail = this.viewNetposPage + " #ownerEmail";
	this.ownerphone = this.viewNetposPage + " #ownerPhone";
	this.ownermobile = this.viewNetposPage + " #ownerMobile";
	this.headofficename = this.viewNetposPage + " #headOfficeName";
	this.headofficephone = this.viewNetposPage + " #headOfficePhone";
	this.headofficemobile = this.viewNetposPage + " #headOfficeMobile";
	this.headofficeemail = this.viewNetposPage + " #headOfficeEmail";
	this.owneraddress = this.viewNetposPage + " #ownerAddress";
	this.headofficeaddress = this.viewNetposPage + " #headOfficeAddress";
	this.netposUIStartup=NetPosStartup;
	this.viewNetposPTB = new ViewNetPosPTB(this);
}

ViewNetPosUI.prototype.getNetposUIService = function() {
	var netposUIStartup = this.getNetposUIStartup();
	return netposUIStartup.getNetPosService();
}
ViewNetPosUI.prototype.getNetposUIStartup = function() {
	return this.netposUIStartup;
}
/* ViewNetPosUI.prototype.getnetlimsTableNavigator = function() {
	var netposUI = this.getNetposUIStartup();
	return netposUI.getnetlimsTableNavigator();
} */
ViewNetPosUI.prototype.setNetposId= function(netposId) {
	var netposUI = this.getNetposUIStartup();
	return netposUI.setNetposId(netposId);
}
ViewNetPosUI.prototype.getNetpos = function() {
	return this.branch;
}
ViewNetPosUI.prototype.setNetpos = function(branch) {
	this.branch=branch;
 }
 /*
ViewNetPosUI.prototype.getnetlimsAdminTableNavigator = function() {
	var netposUI = this.getNetposUIStartup();
	return netposUI.getnetlimsAdminTableNavigator();
} */
ViewNetPosUI.prototype.getviewNetposPTB = function() {
	return this.viewNetposPTB;
}

ViewNetPosUI.prototype.setPageTitle = function() {
	this.pageTitle.empty().html("View NetLims ");
}
ViewNetPosUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.inputFields);
	
}
ViewNetPosUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
ViewNetPosUI.prototype.writable = function() {
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
	$j(self.viewNetposPage + " input[type=text]").removeAttr('readonly');
	$j(self.viewNetposPage + " input[type=text]").removeClass('newBox');
	$j(self.viewNetposPage + " textarea").removeClass('newBox');
	$j(self.viewNetposPage + " textarea").removeAttr('readonly');
		$j(self.netposid).addClass('newBox');
		$j(self.netposid).attr('readonly','readonly');
		$j(self.username).addClass('newBox');
		$j(self.username).attr('readonly','readonly');
		
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
}
ViewNetPosUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewNetposPage + " input[type=text]").attr('readonly',true);
	$j(self.viewNetposPage + " input[type=text]").addClass('newBox');
	$j(self.viewNetposPage + " textarea").attr('readonly',true);
	$j(self.viewNetposPage + " textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
}
ViewNetPosUI.prototype.init = function(netposId) {
	var self = this;
	var viewNetposPTB = self.getviewNetposPTB();
	viewNetposPTB.init(self);
	pageHandler.create(constants.VIEWNETPOSDETAILPAGEURL);
	self.readable();
	self.viewNetlposDetails(netposId);
	pageHandler.setActivePage(self);
	self.bindEvents();
	
}
ViewNetPosUI.prototype.bindEvents = function() {
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
		var netlimsInfo = self.getNetpos();
		var netposId = netlimsInfo.netPos.globalId;
		self.viewNetlposDetails(netposId);
		self.readable();
	});
	$j(self.updateButton).die('click').live('click',function(){
	
		 self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var netpos = self.getNetposRequest();
		//alert(JSON.stringify(netpos));
		 var netposValidator = new editNetPosValidator();
		var error  = netposValidator.validate(netpos,self);
		if(error.errorStatus==false) {
			var netposUIService = self.getNetposUIService();
			var netposResponse = netposUIService.updateNetpos(netpos);
			//alert(JSON.stringify(netposResponse));
				if(netposResponse.error==null) {
					showTip(constants.UPDATESUCCESS);
					//For showing the global Tip
					var netPOSInfo = self.getNetpos();
					//alert(JSON.stringify(netPOSInfo));
					self.viewNetlposDetails(netPOSInfo.netPos.globalId);
					self.readable(); 
				} else
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netlimsResponse.error));
			} else
			self.createError(error);
	}); 
	 
}
ViewNetPosUI.prototype.viewNetlposDetails = function(netposId) {
	self=this;
	self.setNetposId(netposId);
	var NetposUIService = self.getNetposUIService();
	var netposInfo = NetposUIService.viewNetposDetails(netposId);
	//alert(JSON.stringify(netposInfo));
	if(!netposInfo.errorMessage) {
		self.setNetpos(netposInfo);
		$j(self.netposid).val(netposInfo.netPos.globalId);
		$j(self.organizationname).val(netposInfo.netPos.name);
		$j(self.headofficeemail).val(netposInfo.netPos.headOfficeEmail);
		$j(self.headofficephone).val(netposInfo.netPos.headOfficePhone);
		$j(self.ownerfirstname).val(netposInfo.netPos.ownerFirstName);
		$j(self.ownerlastname).val(netposInfo.netPos.ownerLastName);
		$j(self.owneremail).val(netposInfo.netPos.ownerEmail);
		$j(self.ownerphone).val(netposInfo.netPos.ownerPhone);
		$j(self.owneraddress).val(commonMethodInvoker.br2nl(netposInfo.netPos.ownerAddress));
		$j(self.headofficeaddress).val(commonMethodInvoker.br2nl(netposInfo.netPos.headOfficeAddress));
		$j(self.ownermobile).val(netposInfo.netPos.ownerMobile);
		$j(self.headofficename).val(netposInfo.netPos.headOfficeName);
		$j(self.headofficemobile).val(netposInfo.netPos.headOfficeMobile);
		$j(self.username).val(netposInfo.netPos.userName);
		
		
	} else 
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData,netposInfo.errorMessage);
	self.setPageTitle("View NetPos ");
}

ViewNetPosUI.prototype.getNetposRequest = function() {
	var self=this;
	var netpos = new NetPosDTO();
	var netposname;
	netposname=$j(self.organizationname).val();
	netposname=netposname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
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
	
	netpos.setName(netposname);
	netpos.setglobalId(parseInt($j(self.netposid).val()));
	netpos.setofName(ownerfirstname);
	netpos.setolName(ownerlastname);
	netpos.setoEmail($j(self.owneremail).val());
	netpos.setoPh($j(self.ownerphone).val());
	netpos.setoMob($j(self.ownermobile).val());
	netpos.setoAdd(commonMethodInvoker.nl2br($j(self.owneraddress).val()));
	netpos.sethoAdd(commonMethodInvoker.nl2br($j(self.headofficeaddress).val()));
	netpos.sethoName(headofficename);
	netpos.sethoEmail($j(self.headofficeemail).val());
	netpos.sethoPh($j(self.headofficephone).val());
	netpos.sethoMob($j(self.headofficemobile).val());
	netpos.setuName($j(self.username).val());
	netpos.setpass($j(self.password).val());
	return netpos;
}

