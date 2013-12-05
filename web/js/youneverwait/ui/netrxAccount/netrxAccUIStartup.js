function netrxAccountUIStartup() {
	this.pgTableName = "#branchNetrxAcc";
	this.pgTableContainer="#branchNetrxAccTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#BRANCHNETRXPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#BRANCHNETRXPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#BRANCHNETRXPTBContainer #btn_delete_ptb_id');
	this.ptbSync=$j('#BRANCHNETRXPTBContainer #btn_change_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.netrxId=userdata.netrxId;
	this.pgTableRowClass = this.pgTableName + ' .branchIdCol';
	this.exp = new ExpressionListDTO();
	this.netrxAccService = new NetrxAccServiceImpl();
	this.listUrl = constants.NETRXACCBRANCHLISTURL;
	this.netrxAccTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.netrxAccService,this.exp);
	this.viewNetrxBranchUI = new ViewNetrxBranchUI(this);
}

netrxAccountUIStartup.prototype.setnetrxAccTableNavigator = function(netrxAccTableNavigator) {
	this.netrxAccTableNavigator = netrxAccTableNavigator;
}
netrxAccountUIStartup.prototype.getnetrxAccTableNavigator = function() {
	return this.netrxAccTableNavigator;
}
netrxAccountUIStartup.prototype.getViewNetrxBranchUI = function() {
	return this.viewNetrxBranchUI;
}
netrxAccountUIStartup.prototype.getBranchId = function() {
	return this.BranchId;
}
netrxAccountUIStartup.prototype.setBranchId = function(BranchId) {
	this.BranchId=BranchId;
}
netrxAccountUIStartup.prototype.getNetrxUIService = function() {
	return this.netrxAccService;
}


//Set the page title of the order ui page
netrxAccountUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

//Return the selected Order Id from the list table
netrxAccountUIStartup.prototype.getSelectedbranchId = function () {
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

netrxAccountUIStartup.prototype.init = function() {
	var self = this;
	var selName="netRxId";
	var selValue=self.netrxId;
	var selOperator = "eq";
	self.setPageTitle(constants.BRANCHLIST);
	var exp = new ExpressionListDTO();
	var expr = new ExpressionDTO(selName,selValue,selOperator);
	exp.add(expr);
	var netrxAccTableNavigator = self.getnetrxAccTableNavigator();
	netrxAccTableNavigator.setExp(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.create(constants.NETRXBRANCH, constants.NETRXACCPAGEBRANCHJSON); //Create the Page tool Bar for Order
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.NETRXACCBRANCHLISTJSON);//Create Table for Listing Order
	dataTableProcessor.setCustomTable(self.pgTableName);
	netrxAccTableNavigator.list();
	self.bindEvents();
	pageHandler.setActivePage(self);
}



netrxAccountUIStartup.prototype.createBranchModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETRXACCBRANCHJSON,constants.NEWNETRXBRANCHMODAL);		
	openModalBox(obj,constants.NEWNETRXBRANCHMODAL);
	var newBranchUI = new NewBranchUI(self);
	newBranchUI.init();
	return newBranchUI;
}

netrxAccountUIStartup.prototype.createSyncModal = function(obj,branchId) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NETRXACCSYNCJSON,constants.NETRXACCSYNCMODAL);		
	openModalBox(obj,constants.NETRXACCSYNCMODAL);
	var netrxAccSyncUI = new NetrxAccSyncUI(self,branchId);
	netrxAccSyncUI.init();
	return netrxAccSyncUI; 
}


netrxAccountUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		self.createBranchModal(obj);
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
				netrxAccTableNavigator.list();
			 
		}	
	}); 
	
	
	
	
}
netrxAccountUIStartup.prototype.bindEvents = function() {
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
	
	/* $j(parent.pgTableRowClass).die('click').live('click',function(){
	   var branchId= $j(this).parent().attr('id');
		if(branchId!="") {
			var viewNetrxBranchUI = parent.getViewNetrxBranchUI();
			viewNetrxBranchUI.init(branchId);
		}	
	});  */
}



