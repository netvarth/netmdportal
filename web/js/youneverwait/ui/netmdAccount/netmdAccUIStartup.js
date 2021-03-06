function netmdAccountUIStartup() {
	this.pgTableName = "#branchNetmdAcc";
	this.pgTableContainer="#branchNetmdAccTableCont"; 
	//this.pgTableNameorderList="#branchOrderNetlimsAcc";
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#BRANCHNETMDPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#BRANCHNETMDPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#BRANCHNETMDPTBContainer #btn_delete_ptb_id');
	this.ptbSync=$j('#BRANCHNETMDPTBContainer #btn_change_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.netmdId=userdata.netmdId;
	this.pgTableRowClass = this.pgTableName + ' .branchNetlimsAccIdCol';
	this.exp = new ExpressionListDTO();
	this.netmdAccService = new NetmdAccServiceImpl();
	this.listUrl = constants.NETMDACCBRANCHLISTURL;
	this.netmdAccTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.netmdAccService,this.exp);
	this.viewNetmdBranchUI = new ViewNetmdBranchUI(this);
}

netmdAccountUIStartup.prototype.setnetmdAccTableNavigator = function(netmdAccTableNavigator) {
	this.netmdAccTableNavigator = netmdAccTableNavigator;
}
netmdAccountUIStartup.prototype.getnetmdAccTableNavigator = function() {
	return this.netmdAccTableNavigator;
}
netmdAccountUIStartup.prototype.getViewNetmdBranchUI = function() {
	return this.viewNetmdBranchUI;
}
netmdAccountUIStartup.prototype.getBranchId = function() {
	return this.BranchId;
}
netmdAccountUIStartup.prototype.setBranchId = function(BranchId) {
	this.BranchId=BranchId;
}
netmdAccountUIStartup.prototype.getNetmdUIService = function() {
	return this.netmdAccService;
}
netmdAccountUIStartup.prototype.organizationlist = function() {
	ajaxProcessor.setUrl(constants.GETORGANIZATIONLIST);
	return ajaxProcessor.get();
}

//Set the page title of the order ui page
netmdAccountUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

//Return the selected Order Id from the list table
netmdAccountUIStartup.prototype.getSelectedbranchId = function () {
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

netmdAccountUIStartup.prototype.init = function() {
	var self = this;
	var selName="netMdId";
	var selValue=self.netmdId;
	var selOperator = "eq";
	self.setPageTitle(constants.BRANCHLIST);
	var exp = new ExpressionListDTO();
	var expr = new ExpressionDTO(selName,selValue,selOperator);
	exp.add(expr);
	var netmdAccntTableNavigator = self.getnetmdAccTableNavigator();
	netmdAccntTableNavigator.setExp(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.create(constants.NETMDBRANCH, constants.NETMDACCPAGEBRANCHJSON); //Create the Page tool Bar for Order
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.NETMDACCBRANCHLISTJSON);//Create Table for Listing Order
	dataTableProcessor.setCustomTable(self.pgTableName);
	netmdAccntTableNavigator.list("branchlist");
	self.bindEvents();
	pageHandler.setActivePage(self);
}



netmdAccountUIStartup.prototype.createBranchModal = function(obj) {
	var self = this;
	if(pageHandler.isnetmdControlClassLoaded()!=true){
		ajaxProcessor.setUrl("/youNeverWait/js/youneverwait/ui/netmdAccount/netMdloader.js");
		ajaxProcessor.getJavaScript();
		pageHandler.setnetmdControlClassLoaded(true);
			}
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETMDACCBRANCHJSON,constants.NEWNETMDACCBRANCHMODAL);		
	openModalBox(obj,constants.NEWNETMDACCBRANCHMODAL);
	var newBranchUI = new NewBranchUI(self);
	newBranchUI.init();
	return newBranchUI;
}

 netmdAccountUIStartup.prototype.createSyncModal = function(obj,branchId) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NETMDACCSYNCJSON,constants.NETMDACCSYNCMODAL);		
	openModalBox(obj,constants.NETMDACCSYNCMODAL);
	var netmdAccSyncUI = new NetmdAccSyncUI(self,branchId);
	netmdAccSyncUI.init();
	return netmdAccSyncUI; 
} 


netmdAccountUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	
	 self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		self.createBranchModal(obj);
	});	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		if(pageHandler.isnetmdControlClassLoaded()!=true){
		ajaxProcessor.setUrl("/youNeverWait/js/youneverwait/ui/netmdAccount/netMdloader.js");
		ajaxProcessor.getJavaScript();
		pageHandler.setnetmdControlClassLoaded(true);
			}
		var branchId=self.getSelectedbranchId(self.pgTableName);
		if(branchId!="") {
			var viewNetmdBranchUI = self.getViewNetmdBranchUI();
			viewNetmdBranchUI.init(branchId);
		}	
	});
	self.ptbSync.die('click').live('click',function() {
		removeErrors();
		if(pageHandler.isnetmdControlClassLoaded()!=true){
		ajaxProcessor.setUrl("/youNeverWait/js/youneverwait/ui/netmdAccount/netMdloader.js");
		ajaxProcessor.getJavaScript();
		pageHandler.setnetmdControlClassLoaded(true);
			}
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
			var netmdUIService = self.getNetmdUIService();
				 var netmdDelResponse = netmdUIService.deleteNetmdBranch(branchId);
				//alert(JSON.stringify(netmdDelResponse));	
				if(netmdDelResponse.success==true) {
					showTip(constants.NETMDBRANCHDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netmdDelResponse.error));
				}
				var netmdAccTableNavigator = self.getnetmdAccTableNavigator();
				netmdAccTableNavigator.list("branchlist");
			 
		}	
	});
	
	
	
	
}
netmdAccountUIStartup.prototype.bindEvents = function() {
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
			if(pageHandler.isnetmdControlClassLoaded()!=true){
		ajaxProcessor.setUrl("/youNeverWait/js/youneverwait/ui/netmdAccount/netMdloader.js");
		ajaxProcessor.getJavaScript();
		pageHandler.setnetmdControlClassLoaded(true);
			}
			var viewNetmdBranchUI = parent.getViewNetmdBranchUI();
			viewNetmdBranchUI.init(branchId);
		}	
	});  
}



