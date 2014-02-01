function netrxBranchUIStartup(netrxId) {
	this.pgTableName = "#branchNetrxAcc";
	this.pgTableContainer="#branchNetrxAccTableCont"; 
	this.pgTableNameorderList="#branchOrderNetlimsAcc";
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#BRANCHNETRXPTBContainer #btn_new_ptb_id');
	this.ptbNetrxlist=$j('#BRANCHNETRXPTBContainer #btn_back_ptb_id');
	this.ptbView=$j('#BRANCHNETRXPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#BRANCHNETRXPTBContainer #btn_delete_ptb_id');
	this.ptbSync=$j('#BRANCHNETRXPTBContainer #btn_change_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.netrxId=netrxId;
	this.pgTableRowClass = this.pgTableName + ' .branchNetrxAccIdCol';
	this.exp = new ExpressionListDTO();
	this.netmdAccService = new NetrxbranchServiceImpl();
	this.listUrl = constants.NETRXSUPERBRANCHLISTURL;
	this.netrxAccTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.netmdAccService,this.exp);
	this.viewNetrxBranchUI = new ViewNetrxBranchUI(this);
}

netrxBranchUIStartup.prototype.setnetrxAccTableNavigator = function(netrxAccTableNavigator) {
	this.netrxAccTableNavigator = netrxAccTableNavigator;
}
netrxBranchUIStartup.prototype.getnetrxAccTableNavigator = function() {
	return this.netrxAccTableNavigator;
}
netrxBranchUIStartup.prototype.getViewNetrxBranchUI = function() {
	return this.viewNetrxBranchUI;
}
netrxBranchUIStartup.prototype.getBranchId = function() {
	return this.BranchId;
}
netrxBranchUIStartup.prototype.setBranchId = function(BranchId) {
	this.BranchId=BranchId;
}
netrxBranchUIStartup.prototype.getnetrxId = function() {
	return this.netrxId;
}
netrxBranchUIStartup.prototype.getNetrxUIService = function() {
	return this.netmdAccService;
}


//Set the page title of the order ui page
netrxBranchUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

//Return the selected Order Id from the list table
netrxBranchUIStartup.prototype.getSelectedbranchId = function () {
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

netrxBranchUIStartup.prototype.init = function(netrxId) {
	var self = this;
	var selName="netRxId";
	var selValue=self.netrxId;
	var selOperator = "eq";
	self.setPageTitle(constants.BRANCHLIST);
	var exp = new ExpressionListDTO();
	var expr = new ExpressionDTO(selName,selValue,selOperator);
	exp.add(expr);
	var netrxAccntTableNavigator = self.getnetrxAccTableNavigator();
	netrxAccntTableNavigator.setExp(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.create(constants.NETRXBRANCH, constants.NETMDPAGEBRANCHJSON); //Create the Page tool Bar for Order
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.NETRXACCBRANCHLISTJSON);//Create Table for Listing Order
	dataTableProcessor.setCustomTable(self.pgTableName);
	netrxAccntTableNavigator.list("branchlist");
	self.bindEvents();
	pageHandler.setActivePage(self);
}



netrxBranchUIStartup.prototype.createBranchModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETRXACCBRANCHJSON,constants.NEWNETRXBRANCHMODAL);		
	openModalBox(obj,constants.NEWNETRXBRANCHMODAL);
	var newBranchUI = new NewBranchUI(self);
	newBranchUI.init();
	return newBranchUI;
}

 netrxBranchUIStartup.prototype.createSyncModal = function(obj,branchId) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NETRXACCSYNCJSON,constants.NETRXACCSYNCMODAL);		
	openModalBox(obj,constants.NETRXACCSYNCMODAL);
	var netrxAccSyncUI = new NetrxAccSyncUI(self,branchId);
	netrxAccSyncUI.init();
	return netrxAccSyncUI; 
} 


netrxBranchUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	 self.ptbNetrxlist.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
			if(pageHandler.isnetrxClassLoaded()!=true){
				var netrxClass = new netrxClassLoader();
				netrxClass.load();
				pageHandler.setnetrxClassLoaded(true);
			}
			var netrxUI = new netrxUIStartup();	
			netrxUI.init();
	});	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var branchId=self.getSelectedbranchId(self.pgTableName);
		if(branchId!="") {
			var viewNetrxBranchUI = self.getViewNetrxBranchUI();
			viewNetrxBranchUI.init(branchId);
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
			var netrxUIService = self.getNetrxUIService();
				 var netrxDelResponse = netrxUIService.deleteNetrxBranch(branchId);
				//alert(JSON.stringify(netrxDelResponse));	
				if(netrxDelResponse.success==true) {
					showTip(constants.NETMDBRANCHDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netrxDelResponse.error));
				}
				var netrxAccTableNavigator = self.getnetrxAccTableNavigator();
				netrxAccTableNavigator.list("branchlist");
			 
		}	
	});
	
	
	
	
}
netrxBranchUIStartup.prototype.bindEvents = function() {
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
			var viewNetrxBranchUI = parent.getViewNetrxBranchUI();
			viewNetrxBranchUI.init(branchId);
		}	
	});  
}



