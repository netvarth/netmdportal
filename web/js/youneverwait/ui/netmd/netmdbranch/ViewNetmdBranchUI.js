 function ViewNetmdBranchUI(netmdUIStartup){
	this.viewNetmdAccBrchPage = "#viewNetmdBranchHeader";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.pageTitle = $j('#pageTitle');
	this.fromDate="#netmdbranchViewForm #BillfromDate";
	this.toDate="#netmdbranchViewForm #BilltoDate";
	this.billButton="#netmdbranchViewForm #btnBill";
	this.primaryTable="#passphrasePrimaryViewTable";
	this.secondaryTable="#passphraseViewTable"
	this.clearMacid="#netmdbranchViewForm .clearMacid";
	this.makePrim="#netmdbranchViewForm .chngprimdevice";
	this.clearMacidYes="#macidClearForm #btnClearMacYes";
	this.clearMacidNo="#macidClearForm #btnClearMacNo";
	this.billListTable="#accBillDetailsViewTable";
	this.billListTableCont="#viewBranchHeader";
	this.updateButton = this.viewNetmdAccBrchPage + " #netmdbranchvwbtnDone";
	this.editButton = this.viewNetmdAccBrchPage + " #netmdbranchvwbtnEdit";
	this.cancelButton = this.viewNetmdAccBrchPage + " #netmdbranchvwbtnCancel";  
	this.ptbBack = '#netmdaccbrachPTBContainer #btn_back_ptb_id';
	this.ptbUp = '#netmdaccbrachPTBContainer #btn_up_ptb_id';
	this.ptbDown = '#netmdaccbrachPTBContainer #btn_down_ptb_id';  
	this.id = this.viewNetmdAccBrchPage + " #netmdid ";
	this.brachId=this.viewNetmdAccBrchPage + " #branchid";
	this.branchStatus=this.viewNetmdAccBrchPage + " #branchStatus";
	this.inputFields = this.viewNetmdAccBrchPage + " :input[type=text]";
	this.name=this.viewNetmdAccBrchPage + " #organizationName";
	this.address=this.viewNetmdAccBrchPage + " #address";
	this.phone = this.viewNetmdAccBrchPage + " #branchPhone";
	this.mobile=this.viewNetmdAccBrchPage + " #branchMobile";
	this.email = this.viewNetmdAccBrchPage + " #email";
	this.deviceNo= this.viewNetmdAccBrchPage + " #devicesno";
	this.netmdUIStartup=netmdUIStartup;
	this.exp = new ExpressionListDTO();
	this.netmdAccService = new NetmdbranchServiceImpl();
	this.listUrl = constants.GETNETMDBILLLISTURL;
	this.netmdBillTableNavigator = new DataTableNavigator(this.billListTable,this.listUrl,this.billListTableCont,this.netmdAccService,this.exp);
	this.viewNetmdBranchPTB = new ViewNetmdBranchPTB(this);
}
ViewNetmdBranchUI.prototype.getNetmdUIService = function() {
	var netmdUIStartup = this.getNetmdUIStartup();
	return netmdUIStartup.getNetmdUIService();
}
ViewNetmdBranchUI.prototype.getNetmdUIStartup = function() {
	return this.netmdUIStartup;
}
ViewNetmdBranchUI.prototype.getnetmdAccTableNavigator = function() {
	var netmdAccBrchUI = this.getNetmdUIStartup();
	return netmdAccBrchUI.getnetmdAccTableNavigator();
}
ViewNetmdBranchUI.prototype.setBranchId= function(branchId) {
	var netmdAccBrchUI = this.getNetmdUIStartup();
	return netmdAccBrchUI.setBranchId(branchId);
}
ViewNetmdBranchUI.prototype.getBranch = function() {
	return this.branch;
}
ViewNetmdBranchUI.prototype.setBranch = function(branch) {
	this.branch=branch;
}
ViewNetmdBranchUI.prototype.getnetmdAccBrchTableNavigator = function() {
	var netmdAccBrchUI = this.getNetmdUIStartup();
	return netmdAccBrchUI.getnetmdAccBrchTableNavigator();
}
ViewNetmdBranchUI.prototype.getviewNetmdBranchPTB = function() {
	return this.viewNetmdBranchPTB;
}

ViewNetmdBranchUI.prototype.setPageTitle = function() {
	this.pageTitle.empty().html("View NetMD Branch");
}
ViewNetmdBranchUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
	commonMethodInvoker.removeErrorColor(self.phone);
	commonMethodInvoker.removeErrorColor(self.mobile);
	commonMethodInvoker.removeErrorColor(self.email);
}
ViewNetmdBranchUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
ViewNetmdBranchUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewNetmdAccBrchPage + " input[type=text]").removeAttr('readonly');
	$j(self.viewNetmdAccBrchPage + " input[type=text]").removeClass('newBox');
	$j(self.viewNetmdAccBrchPage + " textarea").removeClass('newBox');
	$j(self.viewNetmdAccBrchPage + " textarea").removeAttr('readonly');
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
ViewNetmdBranchUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewNetmdAccBrchPage + " input[type=text]").attr('readonly',true);
	$j(self.viewNetmdAccBrchPage + " input[type=text]").addClass('newBox');
	$j(self.viewNetmdAccBrchPage + " textarea").attr('readonly',true);
	$j(self.viewNetmdAccBrchPage + " textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
}
ViewNetmdBranchUI.prototype.init = function(branchId) {
	var self = this;
	var viewNetmdBranchPTB = self.getviewNetmdBranchPTB();
	viewNetmdBranchPTB.init(self);
	pageHandler.create(constants.VIEWNETMDBRCHPAGEURL);
	self.readable();
	self.viewNetmdBranchDetails(branchId);
	pageHandler.setActivePage(self);
	self.bindEvents();
	$j(self.fromDate).datepicker();
	$j(self.toDate).datepicker();
}
ViewNetmdBranchUI.prototype.bindEvents = function() {
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
		self.viewNetmdBranchDetails(branchId);
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
			var netmdUIService = self.getNetmdUIService();
			var netmdResponse = netmdUIService.updateAccBranchNetmd(branchPass);
			//alert(JSON.stringify(netmdResponse));
			if(netmdResponse.success==true) {
				showTip(constants.BRANCHUPDATESUCCESS);//For showing the global Tip
				var branchInfo = self.getBranch();
				//alert(JSON.stringify(branchInfo));
				self.viewNetmdBranchDetails(branchInfo.branch.globalId);
				self.readable();
			} else
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netmdResponse.error));
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
	
	$j(self.makePrim).die('click').live('click',function(){
 		self=parent;
		self.errorHeader.hide();
		var obj=$j(this);
		var branchId=$j(self.brachId).val();
		var passPhrase=obj.closest('tr').children('td:nth-child(1)').text();
		var macid=obj.closest('tr').children('td:nth-child(2)').text();
		var resultMacJson=self.createMacJson(passPhrase,macid);
		//alert(resultMacJson);
		var netmdUIService = self.getNetmdUIService();
		var response = netmdUIService.changePrimaryNetmdBranch(resultMacJson);	
			//alert(JSON.stringify(response));
			if(response.success==true) {
			var netmdUIService = self.getNetmdUIService();
			var netmdResponse = netmdUIService.viewNetmdBranchDetails(branchId);
				self.viewPassPhraseTableAcc(netmdResponse);
				showTip("Primary Device Changed");
			}	
			else	
			commonMethodInvoker.createServerError(self.errorHeaderMacId,self.errorDataMacId, commonMethodInvoker.getErrorName(response.error));
	});
	
	 $j(self.billButton).die('click').live('click',function(){
 		self=parent;
		self.errorHeader.hide();
		var obj=$j(this);
		commonMethodInvoker.removeErrors();
		
		var selName="netmdId";
		var selValue=$j(self.id).val();
		var selOperator = "eq";
		
		var selBrchName="netmdBranchId";
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
		createModal(constants.BRANCHNETMDBILLLISTJSON,constants.SHOWBILLLISTMODAL);		
		openModalBox(obj,constants.SHOWBILLLISTMODAL);
		dataTableProcessor.setCustomTable(self.billListTable);
		
		var netmdAccntTableNavigator = self.netmdBillTableNavigator;
		netmdAccntTableNavigator.setExp(exp);
		netmdAccntTableNavigator.list("billList");
	}); 
}

ViewNetmdBranchUI.prototype.createClearmacidModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETLIMSBRANCHMACIDCLEARJSON,constants.NEWNETMDMACIDCLEARMODAL);		
	openModalBox(obj,constants.NEWNETMDMACIDCLEARMODAL);
	self.macidClearevents(obj);
}

ViewNetmdBranchUI.prototype.macidClearevents = function(obj) {
	var self = this;
	$j(self.clearMacidYes).die('click').live('click',function() {
	var branchId=$j(self.brachId).val();
	var passPhrase=obj.closest('tr').children('td:nth-child(1)').text();
	var macid=obj.closest('tr').children('td:nth-child(2)').text();
	var resultMacJson=self.createMacJson(passPhrase,macid);
	//alert(resultMacJson);
	var netmdUIService = self.getNetmdUIService();
	var response = netmdUIService.clearMacNetmdBranch(resultMacJson);	
		//alert(JSON.stringify(response));
		if(response.success==true) {
		var netmdUIService = self.getNetmdUIService();
		var netmdResponse = netmdUIService.viewNetmdBranchDetails(branchId);
			self.viewPassPhraseTableAcc(netmdResponse);
			$j('#netmdmacIdClearModal').trigger('reveal:close');
			showTip("MacId Cleared");
		}	
		else	
		commonMethodInvoker.createServerError(self.errorHeaderMacId,self.errorDataMacId, commonMethodInvoker.getErrorName(response.error));
	});
	$j(self.clearMacidNo).die('click').live('click',function() {
		$j('#netmdmacIdClearModal').trigger('reveal:close');
	});
}

ViewNetmdBranchUI.prototype.createMacJson = function(passPhrase,macid) {
	self=this;
	var branch = new NetmdMacPassphrseDTO();
	branch.setheadOfficeId($j(self.id).val());
	branch.setbranchId($j(self.brachId).val());
	branch.setpassPhrase(passPhrase);
	branch.setmacId(macid);
	return branch;
	
}

ViewNetmdBranchUI.prototype.viewNetmdBranchDetails = function(branchId) {
	self=this;
	self.setBranchId(branchId);
	var NetmdUIService = self.getNetmdUIService();
	var netmdResponse = NetmdUIService.viewNetmdBranchDetails(branchId);
	//alert(JSON.stringify(netmdResponse));
	if(!netmdResponse.errorMessage) {
		self.setBranch(netmdResponse);
		$j(self.id).val(netmdResponse.branch.netMdId);
		$j(self.brachId).val(netmdResponse.branch.globalId);
		$j(self.branchStatus).val(netmdResponse.branch.status);
		$j(self.name).val(netmdResponse.branch.name);
		$j(self.phone).val(netmdResponse.branch.phone);
		$j(self.deviceNo).val(netmdResponse.branch.numberOfDevices);
		$j(self.email).val(netmdResponse.branch.email);
		$j(self.address).val(commonMethodInvoker.br2nl(netmdResponse.branch.address));
		$j(self.mobile).val(netmdResponse.branch.mobile);
		dataTableProcessor.setCustomTableWithoutNavigator(self.primaryTable);
		dataTableProcessor.setCustomTableWithoutNavigator(self.secondaryTable);
		self.viewPassPhraseTableAcc(netmdResponse);
	} else 
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData,netmdResponse.errorMessage);
	self.setPageTitle("View NetMD Branch");
}

ViewNetmdBranchUI.prototype.viewPassPhraseTableAcc = function(netmdResponse) {
	self=this;
	$j(self.secondaryTable).dataTable().fnClearTable();
	$j(self.primaryTable).dataTable().fnClearTable();
		$j(netmdResponse.branch).each(function (index,branchInfo) {
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

ViewNetmdBranchUI.prototype.getBranchRequest = function() {
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
ViewNetmdBranchUI.prototype.getBillDetail = function() {
	var self=this;
	var billData=new BillListDTO();
	billData.setfromDate($j(self.fromDate).val());
	billData.setToDate($j(self.toDate).val());
	billData.setnetmdId($j(self.id).val());
	billData.setnetmdBranchId($j(self.brachId).val());
	return billData;
}
ViewNetmdBranchUI.prototype.getPrevId = function(curId,branchResult) {
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
ViewNetmdBranchUI.prototype.getNextId = function(curId,branchResult) {
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