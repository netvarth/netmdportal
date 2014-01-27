function netrxUIStartup() {
	this.pgTableName = "#netrx";
	this.pgTableContainer="#netrxTableCont";
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#NETRXPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#NETRXPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#NETRXPTBContainer #btn_delete_ptb_id');
	this.ptbSync=$j('#NETRXPTBContainer #btn_change_ptb_id');
	this.brachlist=$j('#NETRXPTBContainer #btn_brannchlist_ptb_id');
	this.newbranch=$j('#NETRXPTBContainer #btn_newbranch_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.netrxId=userdata.netrxId;
	this.pgTableRowClass = this.pgTableName + ' .netrxIdCol ';
	this.exp = new ExpressionListDTO();
	this.netrxService = new NetrxServiceImpl();
	this.listUrl = constants.NETRXBRANCHLISTURL;
	this.netrxTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.netrxService,this.exp);
	this.viewNetrxUI = new ViewNetrxUI(this);
}

netrxUIStartup.prototype.setnetrxAccTableNavigator = function(netrxTableNavigator) {
	this.netrxTableNavigator = netrxTableNavigator;
}
netrxUIStartup.prototype.getnetrxTableNavigator = function() {
	return this.netrxTableNavigator;
}
netrxUIStartup.prototype.getViewNetrxUI = function() {
	return this.viewNetrxUI;
}
netrxUIStartup.prototype.getNetrxId = function() {
	return this.netrxId;
}
netrxUIStartup.prototype.setNetrxId = function(netrxId) {
	this.netrxId=netrxId;
}
netrxUIStartup.prototype.getNetrxUIService = function() {
	return this.netrxService;
}


//Set the page title of the order ui page
netrxUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

//Return the selected Order Id from the list table
netrxUIStartup.prototype.getSelectedbranchId = function () {
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

netrxUIStartup.prototype.init = function() {
	var self = this;
	self.setPageTitle(constants.NETRXLIST);
	var exp = new ExpressionListDTO();
	var netrxTableNavigator = self.getnetrxTableNavigator();
	netrxTableNavigator.setExp(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.create(constants.NETRX, constants.NETRXPAGEJSON); //Create the Page tool Bar for Order
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.NETRXLISTJSON);//Create Table for Listing Order
	dataTableProcessor.setCustomTable(self.pgTableName);
	netrxTableNavigator.list();
	self.bindEvents();
	pageHandler.setActivePage(self);
}



netrxUIStartup.prototype.createNetrxModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETRXJSON,constants.NEWNETRXMODAL);		
	openModalBox(obj,constants.NEWNETRXMODAL);
	var newNetrxUI = new NewNetrxUI(self);
	newNetrxUI.init();
	return newNetrxUI;
}

netrxUIStartup.prototype.createSyncModal = function(obj,netrxId) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NETLIMSACCSYNCJSON,constants.NETRXSYNCMODAL);		
	openModalBox(obj,constants.NETRXSYNCMODAL);
	var netrxSyncUI = new NetrxSyncUI(self,netrxId);
	netrxSyncUI.init();
	return netrxSyncUI; 
}



netrxUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		self.createNetrxModal(obj);
	});	
	 self.ptbView.die('click').live('click',function() {
		removeErrors();
		var netrxId=self.getSelectedbranchId(self.pgTableName);
		if(netrxId!="") {
			var viewNetrxUI = self.getViewNetrxUI();
			viewNetrxUI.init(netrxId);
		}	
	});
	 self.ptbSync.die('click').live('click',function() {
		removeErrors();
		var netrxId=self.getSelectedbranchId(self.pgTableName);
		if(netrxId!="") {
			var obj=$j(this);
			self.createSyncModal(obj,netrxId);
		}	
	});
	
	self.newbranch.die('click').live('click',function() {
		var obj=$j(this);
		var netrxId=self.getSelectedbranchId(self.pgTableName);
		if(netrxId!="") {
			commonMethodInvoker.removeErrors();
			var netrxAccClass = new netrxbranchClassLoader();
			netrxAccClass.load();
			var netrxAccUI = new netrxBranchUIStartup(netrxId);	
			netrxAccUI.createBranchModal(obj);
		}	
	});
	
	self.brachlist.die('click').live('click',function() {
		var obj=$j(this);
		var netrxId=self.getSelectedbranchId(self.pgTableName);
		if(netrxId!="") {
			commonMethodInvoker.removeErrors();
			var netrxAccClass = new netrxbranchClassLoader();
			netrxAccClass.load();
			var netrxAccUI = new netrxBranchUIStartup(netrxId);	
			netrxAccUI.init(netrxId);
		}	
	});
	 
	
	
	
	self.ptbDelete.die('click').live('click',function() {
		removeErrors();
		var netrxId=self.getSelectedbranchId(self.pgTableName);
		if(netrxId!="") {
			var netrxUIService = self.getNetrxUIService();
				  var netrxDelResponse = netrxUIService.deleteNetrx(netrxId);
				//alert(JSON.stringify(netrxDelResponse));	
				if(netrxDelResponse.success==true) {
					showTip(constants.NETRXDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netrxDelResponse.error));
				}
				var netrxTableNavigator = self.getnetrxTableNavigator();
				netrxTableNavigator.list();
			
			}	
	});
	
	 
	
}
netrxUIStartup.prototype.bindEvents = function() {
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
	   var netrxId= $j(this).parent().attr('id');
		if(netrxId!="") {
			var viewNetrxUI = parent.getViewNetrxUI();
			viewNetrxUI.init(netrxId);
		}	
	}); 
}



