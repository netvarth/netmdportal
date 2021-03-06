 function ViewNetlimsBranchUI(netlimsbrchUIStartup){
	this.viewNetlimsAccBrchPage = "#viewBranchHeader";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.errorHeaderMacId = $j('#errorDivclearMacidHeader');
	this.errorDataMacId = $j('#errorDivNetlimsclearMacidData');
	this.pageTitle = $j('#pageTitle');
	this.fromDate="#branchViewForm #fromDate";
	this.toDate="#branchViewForm #toDate";
	this.orderButton="#branchViewForm #btnShowOrders";
	this.orderListTable="#ordersnetlimsaccViewTable";
	this.orderListTableCont="#viewBranchHeader";
	this.primaryTable="#passphrasenetlimsViewTable";
	this.clearMacid="#branchViewForm .clearMacid";
	this.clearMacidYes="#macidClearForm #btnClearMacYes";
	this.clearMacidNo="#macidClearForm #btnClearMacNo";
	this.updateButton = this.viewNetlimsAccBrchPage + " #branchvwbtnDone";
	this.editButton = this.viewNetlimsAccBrchPage + " #branchvwbtnCreate";
	this.cancelButton = this.viewNetlimsAccBrchPage + " #branchvwbtnCancel";  
	this.ptbBack = '#netlimsaccbrachPTBContainer #btn_back_ptb_id';
	this.ptbUp = '#netlimsaccbrachPTBContainer #btn_up_ptb_id';
	this.ptbDown = '#netlimsaccbrachPTBContainer #btn_down_ptb_id';  
	this.id = this.viewNetlimsAccBrchPage + " #labid ";
	this.brachId=this.viewNetlimsAccBrchPage + " #branchid";
	this.branchStatus=this.viewNetlimsAccBrchPage + " #branchStatus";
	this.passPhrase=this.viewNetlimsAccBrchPage + " #passPhrase";
	this.macId=this.viewNetlimsAccBrchPage + " #macId";
	this.inputFields = this.viewNetlimsAccBrchPage + " :input[type=text]";
	this.name=this.viewNetlimsAccBrchPage + " #organizationName";
	this.address=this.viewNetlimsAccBrchPage + " #address";
	this.phone = this.viewNetlimsAccBrchPage + " #branchPhone";
	this.mobile=this.viewNetlimsAccBrchPage + " #branchMobile";
	this.email = this.viewNetlimsAccBrchPage + " #email";
	this.exp = new ExpressionListDTO();
	this.netlimsAccService = new NetlimsbranchServiceImpl();
	this.listUrl=constants.GETNETLIMSBRANCHORDERLISTURL;
	this.netlimsOrderTableNavigator = new DataTableNavigator(this.orderListTable,this.listUrl,this.orderListTableCont,this.netlimsAccService,this.exp);
	this.netlimsUIStartup=netlimsbrchUIStartup;
	this.viewNetlimsBranchPTB = new ViewNetlimsBranchPTB(this);
}
ViewNetlimsBranchUI.prototype.getNetlimsUIService = function() {
	var netlimsUIStartup = this.getNetlimsUIStartup();
	return netlimsUIStartup.getNetlimsUIService();
}
ViewNetlimsBranchUI.prototype.getNetlimsUIStartup = function() {
	return this.netlimsUIStartup;
}
ViewNetlimsBranchUI.prototype.getnetlimsAccTableNavigator = function() {
	var netlimsAccBrchUI = this.getNetlimsUIStartup();
	return netlimsAccBrchUI.getnetlimsAccTableNavigator();
}
ViewNetlimsBranchUI.prototype.setBranchId= function(branchId) {
	var netlimsAccBrchUI = this.getNetlimsUIStartup();
	return netlimsAccBrchUI.setBranchId(branchId);
}
ViewNetlimsBranchUI.prototype.getBranch = function() {
	return this.branch;
}
ViewNetlimsBranchUI.prototype.setBranch = function(branch) {
	this.branch=branch;
}
ViewNetlimsBranchUI.prototype.getnetlimsAccBrchTableNavigator = function() {
	var netlimsAccBrchUI = this.getNetlimsUIStartup();
	return netlimsAccBrchUI.getnetlimsAccBrchTableNavigator();
}
ViewNetlimsBranchUI.prototype.getviewNetlimsBranchPTB = function() {
	return this.viewNetlimsBranchPTB;
}

ViewNetlimsBranchUI.prototype.setPageTitle = function() {
	this.pageTitle.empty().html("View NetLims Branch");
}
ViewNetlimsBranchUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
	commonMethodInvoker.removeErrorColor(self.phone);
	commonMethodInvoker.removeErrorColor(self.mobile);
	commonMethodInvoker.removeErrorColor(self.email);
}
ViewNetlimsBranchUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
ViewNetlimsBranchUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewNetlimsAccBrchPage + " input[type=text]").removeAttr('readonly');
	$j(self.viewNetlimsAccBrchPage + " input[type=text]").removeClass('newBox');
	$j(self.viewNetlimsAccBrchPage + " textarea").removeClass('newBox');
	$j(self.viewNetlimsAccBrchPage + " textarea").removeAttr('readonly');
		$j(self.brachId).addClass('newBox');
		$j(self.brachId).attr('readonly','readonly');
		$j(self.id).addClass('newBox');
		$j(self.id).attr('readonly','readonly');
		$j(self.branchStatus).addClass('newBox');
		$j(self.branchStatus).attr('readonly','readonly');
		$j(self.passPhrase).addClass('newBox');
		$j(self.passPhrase).attr('readonly','readonly');
		$j(self.macId).addClass('newBox');
		$j(self.macId).attr('readonly','readonly');
		//clearNilFields(branchViewForm);
		//if($j(self.macId).val()=='')
		//$j(self.macId).val("Nil")
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
}
ViewNetlimsBranchUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewNetlimsAccBrchPage + " input[type=text]").attr('readonly',true);
	$j(self.viewNetlimsAccBrchPage + " input[type=text]").addClass('newBox');
	$j(self.viewNetlimsAccBrchPage + " textarea").attr('readonly',true);
	$j(self.viewNetlimsAccBrchPage + " textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
}
ViewNetlimsBranchUI.prototype.init = function(branchId) {
	var self = this;
	var viewNetlimsBranchPTB = self.getviewNetlimsBranchPTB();
	viewNetlimsBranchPTB.init(self);
	pageHandler.create(constants.VIEWNETLIMSBRCHPAGEURL);
	self.readable();
	self.viewNetlimsBranchDetails(branchId);
	pageHandler.setActivePage(self);
	self.bindEvents();
	$j(self.fromDate).datepicker();
	$j(self.toDate).datepicker();
}
ViewNetlimsBranchUI.prototype.bindEvents = function() {
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
		self.viewNetlimsBranchDetails(branchId);
		self.readable();
	});
	 $j(self.updateButton).die('click').live('click',function(){
		self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var branchPass = self.getBranchRequest();
		var branchValidator = new NetlimsBranchValidator();
		var error  = branchValidator.validate(branchPass, self);
		if(error.errorStatus==false) {
			var netlimsUIService = self.getNetlimsUIService();
			var netlimsResponse = netlimsUIService.updateAccBranchNetlims(branchPass);
			//alert(JSON.stringify(netlimsResponse));
			if(netlimsResponse.error==null) {
				showTip(constants.BRANCHUPDATESUCCESS);//For showing the global Tip
				var branchInfo = self.getBranch();
				//alert(JSON.stringify(branchInfo));
				self.viewNetlimsBranchDetails(branchInfo.branch.globalId);
				self.readable();
			} else
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netlimsResponse.error));
		} else
			self.createError(error);	
	}); 
	
	$j(self.clearMacid).die('click').live('click',function(){
 		self=parent;
		self.errorHeader.hide();
		var obj=$j(this);
		commonMethodInvoker.removeErrors();
		self.createClearmacidModal(obj);
	});
	
	$j(self.orderButton).die('click').live('click',function(){
 		self=parent;
		self.errorHeader.hide();
		var obj=$j(this);
		commonMethodInvoker.removeErrors();
		
		var selName="labId";
		var selValue=$j(self.id).val();
		var selOperator = "eq";
		
		var selBrchName="labBranchId";
		var selBrchValue=$j(self.brachId).val();
		var selBrchOperator = "eq";
		
		var date="orderDate";
		var fromValue=$j(self.fromDate).val();
		var fromOperator = "ge";
		
		var toValue=$j(self.toDate).val();
		var toOperator = "le";
		
		var exp = new ExpressionListDTO();
		var expr = new ExpressionDTO(selName,selValue,selOperator);
		var expr1 = new ExpressionDTO(selBrchName,selBrchValue,selBrchOperator);
		var expr2 = new ExpressionDTO(date,fromValue,fromOperator);
		var expr3 = new ExpressionDTO(date,toValue,toOperator);
		exp.add(expr);
		exp.add(expr1);
		exp.add(expr2);
		exp.add(expr3);
		
		createModal(constants.BRANCHNETLIMSORDERLIST,constants.SHOWORDERMODAL);		
		openModalBox(obj,constants.SHOWORDERMODAL);
		dataTableProcessor.setCustomTable(self.orderListTable);
		
		var netlimsAccntTableNavigator = self.netlimsOrderTableNavigator;
		netlimsAccntTableNavigator.setExp(exp);
		netlimsAccntTableNavigator.list("orderlist");
		});
}

ViewNetlimsBranchUI.prototype.createClearmacidModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETLIMSBRANCHMACIDCLEARJSON,constants.NEWMACIDCLEARMODAL);		
	openModalBox(obj,constants.NEWMACIDCLEARMODAL);
	self.macidClearevents();
}

ViewNetlimsBranchUI.prototype.macidClearevents = function(obj) {
	var self = this;
	$j(self.clearMacidYes).die('click').live('click',function() {
	var branchId=$j(self.brachId).val();
	var resultMacJson=self.createMacJson();
	//alert(resultMacJson);
	var netlimsUIService = self.getNetlimsUIService();
	var response = netlimsUIService.clearMacNetlimsBranch(resultMacJson);	
		//alert(JSON.stringify(response));
		if(response.success==true) {
		var NetlimsUIService = self.getNetlimsUIService();
		var netlimsResponse = NetlimsUIService.viewNetlimsBranchDetails(branchId);
			self.viewPassPhraseTableAcc(netlimsResponse);
			$j('#macIdClearModal').trigger('reveal:close');
			showTip("MacId Cleared");
		}	
		else	
		commonMethodInvoker.createServerError(self.errorHeaderMacId,self.errorDataMacId, commonMethodInvoker.getErrorName(response.error));
	});
	$j(self.clearMacidNo).die('click').live('click',function() {
		$j('#macIdClearModal').trigger('reveal:close');
	});
}

ViewNetlimsBranchUI.prototype.createMacJson = function() {
	self=this;
	var branch = new BranchNetlimsDTO();
	branch.setlabId($j(self.id).val());
	branch.setglobalId($j(self.brachId).val());
	return branch;
	
}

ViewNetlimsBranchUI.prototype.viewNetlimsBranchDetails = function(branchId) {
	self=this;
	self.setBranchId(branchId);
	var NetlimsUIService = self.getNetlimsUIService();
	var netlimsResponse = NetlimsUIService.viewNetlimsBranchDetails(branchId);
	if(!netlimsResponse.errorMessage) {
		self.setBranch(netlimsResponse);
		$j(self.id).val(netlimsResponse.branch.labId);
		$j(self.brachId).val(netlimsResponse.branch.globalId);
		$j(self.branchStatus).val(netlimsResponse.branch.status);
		$j(self.passPhrase).val(netlimsResponse.branch.passPhrase);
		$j(self.macId).val(netlimsResponse.branch.macId);
		$j(self.name).val(netlimsResponse.branch.name);
		$j(self.phone).val(netlimsResponse.branch.phone);
		$j(self.email).val(netlimsResponse.branch.email);
		$j(self.address).val(commonMethodInvoker.br2nl(netlimsResponse.branch.address));
		$j(self.mobile).val(netlimsResponse.branch.mobile);
		dataTableProcessor.setCustomTableWithoutNavigator(self.primaryTable);
		self.viewPassPhraseTableAcc(netlimsResponse);
	} else 
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData,netlimsResponse.errorMessage);
	self.setPageTitle("View NetLims Branch");
}
ViewNetlimsBranchUI.prototype.viewPassPhraseTableAcc = function(netlimsResponse) {
	var self=this;
	$j(self.primaryTable).dataTable().fnClearTable();
	$j(netlimsResponse.branch).each(function (index,branchInfo) {
	var myData='<input type="button" value="clear" class="clearMacid stdbtn">';
	
		
		
		if(branchInfo.macId==null)
		{		var rowData=$j(self.primaryTable).dataTable().fnAddData([branchInfo.passPhrase,'Nil',myData]);
			var row=$j(self.primaryTable).dataTable().fnSettings().aoData[rowData].nTr;
			$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		else
		{
		var rowData=$j(self.primaryTable).dataTable().fnAddData([branchInfo.passPhrase,branchInfo.macId,myData]);
		var row=$j(self.primaryTable).dataTable().fnSettings().aoData[rowData].nTr;
		$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		
		
	});
}
ViewNetlimsBranchUI.prototype.getBranchRequest = function() {
	var self=this;
	var branchInfo = self.getBranch();
	var branch = new BranchNetlimsDTO();
	var name=$j(self.name).val();
	name= name.replace(/\b[a-z]/g, function(letter) {
		return letter.toUpperCase();
	});
	branch.setlabId($j(self.id).val());
	branch.setglobalId($j(self.brachId).val());
	branch.setName(name);
	branch.setEmail($j(self.email).val());
	branch.setAddress(commonMethodInvoker.nl2br($j(self.address).val()));
	branch.setPhone($j(self.phone).val());
	branch.setMobile($j(self.mobile).val());
	return branch;
}
ViewNetlimsBranchUI.prototype.getOrderDetail = function() {
	var self=this;
	var orderData=new OrderListDTO();
	orderData.setfromDate($j(self.fromDate).val());
	orderData.setToDate($j(self.toDate).val());
	orderData.setlabId($j(self.id).val());
	orderData.setlabBranchId($j(self.brachId).val());
	return orderData;
}
ViewNetlimsBranchUI.prototype.getPrevId = function(curId,branchResult) {
	var prevId;
	$j(branchResult.branch).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.branch).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=branchResult.branch[index-1].globalId;
		}
	});
	return prevId;	
}
ViewNetlimsBranchUI.prototype.getNextId = function(curId,branchResult) {
	var nextId;
	$j(branchResult.branch).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.branch).length;
			var comp=arrayLength-1;
			if(index==comp)
				nextId = curId;
			else
				nextId=branchResult.branch[index+1].globalId;	
		}
	});	
	return nextId;	
} 