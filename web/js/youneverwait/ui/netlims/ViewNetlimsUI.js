
function ViewNetlimsUI(netlimsUIStartup){

	
	this.viewNetlimsPage = "#viewNetlimsHeader";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.pageTitle = $j('#pageTitle');
	this.ordersnetlimsListTable="#ordersnetlimsViewTable";
	this.updateButton = this.viewNetlimsPage + " #netlimsvwbtnDone";
	this.editButton = this.viewNetlimsPage + " #netlimsvwbtnCreate";
	this.cancelButton = this.viewNetlimsPage + " #netlimsvwbtnCancel";  
	this.ptbBack = '#netlimsPTBContainer #btn_back_ptb_id';
	this.ptbUp = '#netlimsPTBContainer #btn_up_ptb_id';
	this.ptbDown = '#netlimsPTBContainer #btn_down_ptb_id';  
	this.netlimsid = this.viewNetlimsPage + " #netlimsid ";
	this.inputFields = this.viewNetlimsPage + " :input[type=text]";
	this.username=this.viewNetlimsPage + " #username";
	this.organizationname=this.viewNetlimsPage + " #organizationName";
	this.ownerfirstname = this.viewNetlimsPage + " #ownerFirstName";
	this.ownerlastname=this.viewNetlimsPage + " #ownerLastName";
	this.owneremail = this.viewNetlimsPage + " #ownerEmail";
	this.ownerphone = this.viewNetlimsPage + " #ownerPhone";
	this.ownermobile = this.viewNetlimsPage + " #ownerMobile";
	this.headofficename = this.viewNetlimsPage + " #headOfficeName";
	this.headofficephone = this.viewNetlimsPage + " #headOfficePhone";
	this.headofficemobile = this.viewNetlimsPage + " #headOfficeMobile";
	this.headofficeemail = this.viewNetlimsPage + " #headOfficeEmail";
	this.owneraddress = this.viewNetlimsPage + " #ownerAddress";
	this.headofficeaddress = this.viewNetlimsPage + " #headOfficeAddress";
	this.netlimsUIStartup=netlimsUIStartup;
	this.viewNetlimsPTB = new ViewNetlimsPTB(this);
}

ViewNetlimsUI.prototype.getNetlimsUIService = function() {
	var netlimsUIStartup = this.getNetlimsUIStartup();
	return netlimsUIStartup.getNetlimsUIService();
}
ViewNetlimsUI.prototype.getNetlimsUIStartup = function() {
	return this.netlimsUIStartup;
}
ViewNetlimsUI.prototype.getnetlimsTableNavigator = function() {
	var netlimsBrchUI = this.getNetlimsUIStartup();
	return netlimsBrchUI.getnetlimsTableNavigator();
}
ViewNetlimsUI.prototype.setNetlimsId= function(netlimsId) {
	var netlimsBrchUI = this.getNetlimsUIStartup();
	return netlimsBrchUI.setNetlimsId(netlimsId);
}
ViewNetlimsUI.prototype.getNetlims = function() {
	return this.branch;
}
ViewNetlimsUI.prototype.setNetlims = function(branch) {
	this.branch=branch;
}
ViewNetlimsUI.prototype.getnetlimsAdminTableNavigator = function() {
	var netlimsBrchUI = this.getNetlimsUIStartup();
	return netlimsBrchUI.getnetlimsAdminTableNavigator();
}
ViewNetlimsUI.prototype.getviewNetlimsPTB = function() {
	return this.viewNetlimsPTB;
}

ViewNetlimsUI.prototype.setPageTitle = function() {
	this.pageTitle.empty().html("View NetLims ");
}
ViewNetlimsUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.inputFields);
	
}
ViewNetlimsUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
ViewNetlimsUI.prototype.writable = function() {
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
	$j(self.viewNetlimsPage + " input[type=text]").removeAttr('readonly');
	$j(self.viewNetlimsPage + " input[type=text]").removeClass('newBox');
	$j(self.viewNetlimsPage + " textarea").removeClass('newBox');
	$j(self.viewNetlimsPage + " textarea").removeAttr('readonly');
		$j(self.netlimsid).addClass('newBox');
		$j(self.netlimsid).attr('readonly','readonly');
		$j(self.username).addClass('newBox');
		$j(self.username).attr('readonly','readonly');
		
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
}
ViewNetlimsUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewNetlimsPage + " input[type=text]").attr('readonly',true);
	$j(self.viewNetlimsPage + " input[type=text]").addClass('newBox');
	$j(self.viewNetlimsPage + " textarea").attr('readonly',true);
	$j(self.viewNetlimsPage + " textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
}
ViewNetlimsUI.prototype.init = function(netlimsId) {
	var self = this;
	var viewNetlimsPTB = self.getviewNetlimsPTB();
	viewNetlimsPTB.init(self);
	pageHandler.create(constants.VIEWNETLIMSDETAILPAGEURL);
	self.readable();
	self.viewNetlimsDetails(netlimsId);
	pageHandler.setActivePage(self);
	self.bindEvents();
	
}
ViewNetlimsUI.prototype.bindEvents = function() {
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
		var netlimsInfo = self.getNetlims();
		var netlimsId = netlimsInfo.lab.globalId;
		self.viewNetlimsDetails(netlimsId);
		self.readable();
	});
	$j(self.updateButton).die('click').live('click',function(){
		 self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var netlims = self.getNetlimsRequest();
		//alert(JSON.stringify(netlims));
		var netlimsValidator = new NetlimsViewValidator();
		var error  = netlimsValidator.validate(netlims,self);
		if(error.errorStatus==false) {
			var netlimsUIService = self.getNetlimsUIService();
			var netlimsResponse = netlimsUIService.updateNetlims(netlims);
			//alert(JSON.stringify(netlimsResponse));
				if(netlimsResponse.error==null) {
					showTip(constants.BRANCHUPDATESUCCESS);//For showing the global Tip
					var netlimsInfo = self.getNetlims();
					//alert(JSON.stringify(branchInfo));
					self.viewNetlimsDetails(netlimsInfo.lab.globalId);
					self.readable();
				} else
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netlimsResponse.error));
			} else
			self.createError(error);
	}); 
	 /*  $j(self.orderButton).die('click').live('click',function(){
 		self=parent;
		self.errorHeader.hide();
		var obj=$j(this);
		commonMethodInvoker.removeErrors();
		var orderlistData=self.getOrderDetail();
		var netlimsUIService = self.getNetlimsUIService();
		var netlimsOrderListResponse = netlimsUIService.BranchOrderlistNetlims(orderlistData);
		//alert(JSON.stringify(netlimsOrderListResponse));
		if(netlimsOrderListResponse.success==true) {
		createModal(constants.BRANCHNETLIMSORDERLIST,constants.SHOWORDERMODAL);		
		openModalBox(obj,constants.SHOWORDERMODAL);
		dataTableProcessor.setCustomTable(self.orderListTable);
		//netlimsUIService.setTableValueBranchOrderList(self.orderListTable,netlimsOrderListResponse);
		}else
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netlimsOrderListResponse.error));
	});   */
}
ViewNetlimsUI.prototype.viewNetlimsDetails = function(netlimsId) {
	self=this;
	self.setNetlimsId(netlimsId);
	var NetlimsUIService = self.getNetlimsUIService();
	var netlimsInfo = NetlimsUIService.viewNetlimsDetails(netlimsId);
	//alert(JSON.stringify(netlimsInfo));
	if(!netlimsInfo.errorMessage) {
		self.setNetlims(netlimsInfo);
		$j(self.netlimsid).val(netlimsInfo.lab.globalId);
		$j(self.organizationname).val(netlimsInfo.lab.name);
		$j(self.headofficeemail).val(netlimsInfo.lab.headOfficeEmail);
		$j(self.headofficephone).val(netlimsInfo.lab.headOfficePhone);
		$j(self.ownerfirstname).val(netlimsInfo.lab.ownerFirstName);
		$j(self.ownerlastname).val(netlimsInfo.lab.ownerLastName);
		$j(self.owneremail).val(netlimsInfo.lab.ownerEmail);
		$j(self.ownerphone).val(netlimsInfo.lab.ownerPhone);
		$j(self.owneraddress).val(commonMethodInvoker.br2nl(netlimsInfo.lab.ownerAddress));
		$j(self.headofficeaddress).val(commonMethodInvoker.br2nl(netlimsInfo.lab.headOfficeAddress));
		$j(self.ownermobile).val(netlimsInfo.lab.ownerMobile);
		$j(self.headofficename).val(netlimsInfo.lab.headOfficeName);
		$j(self.headofficemobile).val(netlimsInfo.lab.headOfficeMobile);
		$j(netlimsInfo.lab).each(function (index,netlimsInfo) {
		$j(netlimsInfo.login).each(function (index,login) {
			if(login.userName)
				$j(self.username).val(login.userName);
			});	
		});	
		var orderdata=NetlimsUIService.viewordersnetlimsList(netlimsId);
		//alert(JSON.stringify(orderdata));
		dataTableProcessor.setCustomTableWithoutNavigator(self.ordersnetlimsListTable);
		NetlimsUIService.setviewordersnetlimsListTable(self.ordersnetlimsListTable,orderdata);
		
	} else 
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData,netlimsInfo.errorMessage);
	self.setPageTitle("View NetLims ");
}

ViewNetlimsUI.prototype.getNetlimsRequest = function() {
	var self=this;
	var netlims = new NetlimsDTO();
	var netlimsname;
	netlimsname=$j(self.organizationname).val();
	netlimsname=netlimsname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
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
	
	netlims.setName(netlimsname);
	netlims.setglobalId(parseInt($j(self.netlimsid).val()));
	netlims.setownerFirstName(ownerfirstname);
	netlims.setownerLastName(ownerlastname);
	netlims.setownerEmail($j(self.owneremail).val());
	netlims.setownerPhone($j(self.ownerphone).val());
	netlims.setownerMobile($j(self.ownermobile).val());
	netlims.setownerAddress(commonMethodInvoker.nl2br($j(self.owneraddress).val()));
	netlims.setheadOfficeAddress(commonMethodInvoker.nl2br($j(self.headofficeaddress).val()));
	netlims.setheadOfficeName(headofficename);
	netlims.setheadOfficeEmail($j(self.headofficeemail).val());
	netlims.setheadOfficePhone($j(self.headofficephone).val());
	netlims.setheadOfficeMobile($j(self.headofficemobile).val());
	//netlims.setuserName($j(self.username).val());
	//netlims.setpassword($j(self.password).val());
	return netlims;
}
ViewNetlimsUI.prototype.getOrderDetail = function() {
	var self=this;
	var orderData=new OrderListDTO();
	orderData.setfromDate($j(self.fromDate).val());
	orderData.setToDate($j(self.toDate).val());
	orderData.setlabId($j(self.id).val());
	orderData.setlabBranchId($j(self.brachId).val());
	return orderData;
}
ViewNetlimsUI.prototype.getPrevId = function(curId,branchResult) {
	var prevId;
	$j(branchResult.lab).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.lab).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=branchResult.lab[index-1].globalId;
		}
	});
	return prevId;	
}
ViewNetlimsUI.prototype.getNextId = function(curId,branchResult) {
	var nextId;
	$j(branchResult.lab).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.lab).length;
			var comp=arrayLength-1;
			if(index==comp)
				nextId = curId;
			else
				nextId=branchResult.lab[index+1].globalId;	
		}
	});	
	return nextId;	
}  