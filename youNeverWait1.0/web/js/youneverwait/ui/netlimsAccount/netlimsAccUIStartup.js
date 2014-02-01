function netlimsAccountUIStartup() {
	this.pgTableName = "#branchNetlimsAcc";
	this.pgTableContainer="#branchNetlimsAccTableCont"; 
	this.transfrOrderpgTableContainer="#branchtransfrOrderNetlimsCont";
	this.transfrResultpgTableContainer="#branchtrnsfrresultNetlimsCont"; 	
	this.pgTableNameorderList="#branchOrderNetlimsAcc";
	this.pgTableNameTransfrorderList="#branchtransfrOrderNetlimsAcc";
	this.pgTableNameTransfresultList="#branchtrnsfrresultNetlimsAcc";
	this.transfrOrderListUrl=constants.NETLIMSACCTRANSFEREDORDERLISTURL;
	this.transfrresultListUrl=constants.NETLIMSACCTRANSFEREDRESULTLISTURL;
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#BRANCHPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#BRANCHPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#BRANCHPTBContainer #btn_delete_ptb_id');
	this.ptbSync=$j('#BRANCHPTBContainer #btn_change_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.netlimsId=userdata.labId;
	this.pgTableRowClass = this.pgTableName + ' .branchNetlimsAccIdCol';
	this.exp = new ExpressionListDTO();
	this.netlimsAccService = new NetlimsAccServiceImpl();
	this.listUrl = constants.NETLIMSACCBRANCHLISTURL;
	this.netlimsAccTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.netlimsAccService,this.exp);
	this.netlimsTranfrTableNavigator = new DataTableNavigator(this.pgTableNameTransfrorderList,this.transfrOrderListUrl,this.transfrOrderpgTableContainer,this.netlimsAccService,this.exp);
	this.netlimsTranfrResultTableNavigator = new DataTableNavigator(this.pgTableNameTransfresultList,this.transfrresultListUrl,this.transfrResultpgTableContainer,this.netlimsAccService,this.exp);
	this.viewNetlimsBranchUI = new ViewNetlimsBranchUI(this);
}

netlimsAccountUIStartup.prototype.setnetlimsAccTableNavigator = function(netlimsAccTableNavigator) {
	this.netlimsAccTableNavigator = netlimsAccTableNavigator;
}
netlimsAccountUIStartup.prototype.getnetlimsAccTableNavigator = function() {
	return this.netlimsAccTableNavigator;
}
netlimsAccountUIStartup.prototype.getViewNetlimsBranchUI = function() {
	return this.viewNetlimsBranchUI;
}
netlimsAccountUIStartup.prototype.getBranchId = function() {
	return this.BranchId;
}
netlimsAccountUIStartup.prototype.setBranchId = function(BranchId) {
	this.BranchId=BranchId;
}
netlimsAccountUIStartup.prototype.getNetlimsUIService = function() {
	return this.netlimsAccService;
}


//Set the page title of the order ui page
netlimsAccountUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

//Return the selected Order Id from the list table
netlimsAccountUIStartup.prototype.getSelectedbranchId = function () {
	var self =this;
	var branchId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selbranch = $j(self.pgTableName + ' tbody tr[selected]');
		if(selbranch.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEBRANCH);
		} else if(selbranch.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEBRANCHONLY);
		else
			branchId=selbranch.attr('id');
	}
	return branchId;
}

netlimsAccountUIStartup.prototype.init = function() {
	var self = this;
	var selName="labId";
	var selValue=self.netlimsId;
	var selOperator = "eq";
	self.setPageTitle(constants.BRANCHLIST);
	var exp = new ExpressionListDTO();
	var expr = new ExpressionDTO(selName,selValue,selOperator);
	exp.add(expr);
	var netlimsAccTableNavigator = self.getnetlimsAccTableNavigator();
	netlimsAccTableNavigator.setExp(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.create(constants.NETLIMSBRANCH, constants.NETLIMSACCPAGEBRANCHJSON); //Create the Page tool Bar for Order
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.NETLIMSACCBRANCHLISTJSON);//Create Table for Listing Order
	dataTableProcessor.setCustomTable(self.pgTableName);
	netlimsAccTableNavigator.list("branchlist");
	self.bindEvents();
	pageHandler.setActivePage(self);
}



netlimsAccountUIStartup.prototype.createBranchModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETLIMSACCBRANCHJSON,constants.NEWBRANCHMODAL);		
	openModalBox(obj,constants.NEWBRANCHMODAL);
	var newBranchUI = new NewBranchUI(self);
	newBranchUI.init();
	return newBranchUI;
}

netlimsAccountUIStartup.prototype.createSyncModal = function(obj,branchId) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NETLIMSACCSYNCJSON,constants.NETLIMSACCSYNCMODAL);		
	openModalBox(obj,constants.NETLIMSACCSYNCMODAL);
	var netlimsAccSyncUI = new NetlimsAccSyncUI(self,branchId);
	netlimsAccSyncUI.init();
	return netlimsAccSyncUI; 
}

netlimsAccountUIStartup.prototype.branchorderlist = function() {
	var self = this;
	commonMethodInvoker.removeErrors();
	self.setPageTitle(constants.TODAYSORDERLIST);
	ajaxProcessor.setUrl(constants.NETLIMSACCTODAYSORDERLISTURL);
	var branchTable=ajaxProcessor.get();
	var contentForm = new form(branchTable);
	$j('#tabs-1').html(contentForm.result);	
	dataTableProcessor.setCustomTable(self.pgTableNameorderList);
	ajaxProcessor.setUrl(constants.NETLIMSACCTODAYSORDERLISTTABLEURL + self.netlimsId);
	var pgDataList = ajaxProcessor.get();
	self.netlimsAccService.setTableValuesOrderList(self.pgTableNameorderList,pgDataList);
	 
	
}
netlimsAccountUIStartup.prototype.transferorderlist = function() {
	var self = this;
	commonMethodInvoker.removeErrors();
	self.setPageTitle(constants.TRANSFEREDORDERLIST);
		
		var selName="labId";
		var selValue=self.netlimsId;
		var selOperator = "eq";
		
		var exp = new ExpressionListDTO();
		var expr = new ExpressionDTO(selName,selValue,selOperator);
		exp.add(expr);
		
	ajaxProcessor.setUrl(constants.NETLIMSACCTRANSFEREDORDERLISTJSON);
	var branchTable=ajaxProcessor.get();
	var contentForm = new form(branchTable);
	$j('#tabs-1').html(contentForm.result);	
	dataTableProcessor.setCustomTable(self.pgTableNameTransfrorderList);
	
	var netlimsAccntTableNavigator = self.netlimsTranfrTableNavigator;
		netlimsAccntTableNavigator.setExp(exp);
		netlimsAccntTableNavigator.list("transferorder");
	
}
netlimsAccountUIStartup.prototype.transferresultlist = function() {
	var self = this;
	commonMethodInvoker.removeErrors();
	self.setPageTitle(constants.TRANSFEREDRESULTLIST);
		
		var selName="labId";
		var selValue=self.netlimsId;
		var selOperator = "eq";
		
		var exp = new ExpressionListDTO();
		var expr = new ExpressionDTO(selName,selValue,selOperator);
		exp.add(expr);
	ajaxProcessor.setUrl(constants.NETLIMSACCTRANSFEREDRESULTLISTJSON);
	var branchTable=ajaxProcessor.get();
	var contentForm = new form(branchTable);
	$j('#tabs-1').html(contentForm.result);	
	dataTableProcessor.setCustomTable(self.pgTableNameTransfresultList);
	
	var netlimsAccntTableNavigator = self.netlimsTranfrResultTableNavigator;
		netlimsAccntTableNavigator.setExp(exp);
		netlimsAccntTableNavigator.list("transferresult");
		
	//ajaxProcessor.setUrl(constants.NETLIMSACCTODAYSORDERLISTTABLEURL + self.netlimsId);
	//var pgDataList = ajaxProcessor.get();
	//self.netlimsAccService.setTableValues(self.pgTableNameTransfresultList,pgDataList,"transferresult");
	 
	
}

netlimsAccountUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		self.createBranchModal(obj);
	});	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var branchId=self.getSelectedbranchId(self.pgTableName);
		if(branchId!="") {
			var viewNetlimsBranchUI = self.getViewNetlimsBranchUI();
			viewNetlimsBranchUI.init(branchId);
		}	
	});
	self.ptbSync.die('click').live('click',function() {
		removeErrors();
		var branchId=self.getSelectedbranchId(self.pgTableName);
		if(branchId!="") {
			var obj=$j(this);
			self.createSyncModal(obj,branchId);
		}	
	});
	self.ptbDelete.die('click').live('click',function() {
		removeErrors();
		var branchId=self.getSelectedbranchId(self.pgTableName);
		if(branchId!="") {
			var netlimsUIService = self.getNetlimsUIService();
			var netlimsResponse = netlimsUIService.viewNetlimsBranchDetails(branchId);
				var netlimsBranchDel=netlimsResponse.branch;	
				 var netlimsDelResponse = netlimsUIService.deleteNetlimsBranch(netlimsBranchDel);
				//alert(JSON.stringify(netlimsDelResponse));	
				if(netlimsDelResponse.error==null) {
					showTip(constants.NETLIMSBRANCHDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netlimsDelResponse.error));
				}
				var netlimsAccTableNavigator = self.getnetlimsAccTableNavigator();
				netlimsAccTableNavigator.list("branchlist");
			 
		}	
	});
	
	
	
	
}
netlimsAccountUIStartup.prototype.bindEvents = function() {
	parent = this;
	$j(parent.pgTableName + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}	
		removeErrors();
	});	
	
	$j(parent.pgTableRowClass).die('click').live('click',function(){
	   var branchId= $j(this).parent().attr('id');
		if(branchId!="") {
			var viewNetlimsBranchUI = parent.getViewNetlimsBranchUI();
			viewNetlimsBranchUI.init(branchId);
		}	
	}); 
}



