
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
	self.viewNetrxDetails(netrxId);
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
		var netrxInfo = self.getNetrx();
		var netmdId = netrxInfo.netRxDTO.globalId;
		self.viewNetrxDetails(netmdId);
		self.readable();
	});
	$j(self.updateButton).die('click').live('click',function(){
		 self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var netrx = self.getNetrxRequest();
		//alert(JSON.stringify(netrx));
		var netrxValidator = new NetrxViewValidator();
		var error  = netrxValidator.validate(netrx,self);
		if(error.errorStatus==false) {
			var netrxUIService = self.getNetrxUIService();
			var netrxResponse = netrxUIService.updateNetrx(netrx);
			//alert(JSON.stringify(netrxResponse));
				if(netrxResponse.error==null) {
					showTip(constants.NETRXUPDATESUCCESS);//For showing the global Tip
					var netrxInfo = self.getNetrx();
					//alert(JSON.stringify(netrxInfo));
					self.viewNetrxDetails(netrxInfo.netRxDTO.globalId);
					self.readable();
				} else
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netrxResponse.error));
			} else
			self.createError(error);
	}); 
	 
}
ViewNetrxUI.prototype.viewNetrxDetails = function(netrxId) {
	self=this;
	self.setNetrxId(netrxId);
	var NetrxUIService = self.getNetrxUIService();
	var netrxInfo = NetrxUIService.viewNetrxDetails(netrxId);
	
	 if(!netrxInfo.errorMessage) {
		self.setNetrx(netrxInfo);
		$j(self.netrxid).val(netrxInfo.netRxDTO.globalId);
		$j(self.organizationname).val(netrxInfo.netRxDTO.name);
		$j(self.headofficeemail).val(netrxInfo.netRxDTO.headOfficeEmail);
		$j(self.headofficephone).val(netrxInfo.netRxDTO.headOfficePhone);
		$j(self.ownerfirstname).val(netrxInfo.netRxDTO.ownerFirstName);
		$j(self.ownerlastname).val(netrxInfo.netRxDTO.ownerLastName);
		$j(self.owneremail).val(netrxInfo.netRxDTO.ownerEmail);
		$j(self.ownerphone).val(netrxInfo.netRxDTO.ownerPhone);
		$j(self.owneraddress).val(commonMethodInvoker.br2nl(netrxInfo.netRxDTO.ownerAddress));
		$j(self.headofficeaddress).val(commonMethodInvoker.br2nl(netrxInfo.netRxDTO.headOfficeAddress));
		$j(self.ownermobile).val(netrxInfo.netRxDTO.ownerMobile);
		$j(self.headofficename).val(netrxInfo.netRxDTO.headOfficeName);
		$j(self.headofficemobile).val(netrxInfo.netRxDTO.headOfficeMobile);
		$j(self.username).val(netrxInfo.netRxDTO.userName);
				
	} else 
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData,netrxInfo.errorMessage);
	self.setPageTitle("View Netrx"); 
}

ViewNetrxUI.prototype.getNetrxRequest = function() {
	var self=this;
	var netrx = new NetrxDTO();
	var netrxname;
	netrxname=$j(self.organizationname).val();
	netrxname=netrxname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
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
	
	netrx.setName(netrxname);
	netrx.setglobalId(parseInt($j(self.netrxid).val()));
	netrx.setownerFirstName(ownerfirstname);
	netrx.setownerLastName(ownerlastname);
	netrx.setownerEmail($j(self.owneremail).val());
	netrx.setownerPhone($j(self.ownerphone).val());
	netrx.setownerMobile($j(self.ownermobile).val());
	netrx.setownerAddress(commonMethodInvoker.nl2br($j(self.owneraddress).val()));
	netrx.setheadOfficeAddress(commonMethodInvoker.nl2br($j(self.headofficeaddress).val()));
	netrx.setheadOfficeName(headofficename);
	netrx.setheadOfficeEmail($j(self.headofficeemail).val());
	netrx.setheadOfficePhone($j(self.headofficephone).val());
	netrx.setheadOfficeMobile($j(self.headofficemobile).val());
	//netrx.setuserName($j(self.username).val());
	//netrx.setpassword($j(self.password).val());
	return netrx;
}

ViewNetrxUI.prototype.getPrevId = function(curId,branchResult) {
	var prevId;
	$j(branchResult.netRx).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.netRx).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=branchResult.netRx[index-1].globalId;
		}
	});
	return prevId;	
}
ViewNetrxUI.prototype.getNextId = function(curId,branchResult) {
	var nextId;
	$j(branchResult.netRx).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.netRx).length;
			var comp=arrayLength-1;
			if(index==comp)
				nextId = curId;
			else
				nextId=branchResult.netRx[index+1].globalId;	
		}
	});	
	return nextId;	
}  