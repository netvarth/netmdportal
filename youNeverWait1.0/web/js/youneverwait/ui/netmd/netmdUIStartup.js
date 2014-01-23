function netmdUIStartup() {
	this.pgTableName = "#netmd";
	this.pgTableContainer="#netmdTableCont";
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#NETMDPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#NETMDPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#NETMDPTBContainer #btn_delete_ptb_id');
	this.ptbSync=$j('#NETMDPTBContainer #btn_change_ptb_id');
	this.brachlist=$j('#NETMDPTBContainer #btn_brannchlist_ptb_id');
	this.newbranch=$j('#NETMDPTBContainer #btn_newbranch_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.netmdId=userdata.netmdId;
	this.pgTableRowClass = this.pgTableName + ' .netmdIdCol ';
	this.exp = new ExpressionListDTO();
	this.netmdService = new NetmdServiceImpl();
	this.listUrl = constants.NETMDBRANCHLISTURL;
	this.netmdTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.netmdService,this.exp);
	this.viewNetmdUI = new ViewNetmdUI(this);
}

netmdUIStartup.prototype.setnetmdAccTableNavigator = function(netmdTableNavigator) {
	this.netmdTableNavigator = netmdTableNavigator;
}
netmdUIStartup.prototype.getnetmdTableNavigator = function() {
	return this.netmdTableNavigator;
}
netmdUIStartup.prototype.getViewNetmdUI = function() {
	return this.viewNetmdUI;
}
netmdUIStartup.prototype.getNetmdId = function() {
	return this.netmdId;
}
netmdUIStartup.prototype.setNetmdId = function(netmdId) {
	this.netmdId=netmdId;
}
netmdUIStartup.prototype.getNetmdUIService = function() {
	return this.netmdService;
}


//Set the page title of the order ui page
netmdUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

//Return the selected Order Id from the list table
netmdUIStartup.prototype.getSelectedbranchId = function () {
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

netmdUIStartup.prototype.init = function() {
	var self = this;
	self.setPageTitle(constants.NETMDLIST);
	var exp = new ExpressionListDTO();
	var netmdTableNavigator = self.getnetmdTableNavigator();
	netmdTableNavigator.setExp(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.create(constants.NETMD, constants.NETMDPAGEJSON); //Create the Page tool Bar for Order
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.NETMDLISTJSON);//Create Table for Listing Order
	dataTableProcessor.setCustomTable(self.pgTableName);
	netmdTableNavigator.list("netmdlist");
	self.bindEvents();
	pageHandler.setActivePage(self);
}



netmdUIStartup.prototype.createNetmdModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETMDJSON,constants.NEWNETMDMODAL);		
	openModalBox(obj,constants.NEWNETMDMODAL);
	var newNetmdUI = new NewNetmdUI(self);
	newNetmdUI.init();
	return newNetmdUI;
}

netmdUIStartup.prototype.createSyncModal = function(obj,netmdId) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NETLIMSACCSYNCJSON,constants.NETMDSYNCMODAL);		
	openModalBox(obj,constants.NETMDSYNCMODAL);
	var netmdSyncUI = new NetmdSyncUI(self,netmdId);
	netmdSyncUI.init();
	return netmdSyncUI; 
}



netmdUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		self.createNetmdModal(obj);
	});	
	 self.ptbView.die('click').live('click',function() {
		removeErrors();
		var netmdId=self.getSelectedbranchId(self.pgTableName);
		if(netmdId!="") {
			var viewNetmdUI = self.getViewNetmdUI();
			viewNetmdUI.init(netmdId);
		}	
	});
	 self.ptbSync.die('click').live('click',function() {
		removeErrors();
		var netmdId=self.getSelectedbranchId(self.pgTableName);
		if(netmdId!="") {
			var obj=$j(this);
			self.createSyncModal(obj,netmdId);
		}	
	});
	
	self.newbranch.die('click').live('click',function() {
		var obj=$j(this);
		var netmdId=self.getSelectedbranchId(self.pgTableName);
		if(netmdId!="") {
			commonMethodInvoker.removeErrors();
			var netmdAccClass = new netmdbranchClassLoader();
			netmdAccClass.load();
			var netmdAccUI = new netmdBranchUIStartup(netmdId);	
			netmdAccUI.createBranchModal(obj);
		}	
	});
	
	self.brachlist.die('click').live('click',function() {
		var obj=$j(this);
		var netmdId=self.getSelectedbranchId(self.pgTableName);
		if(netmdId!="") {
			commonMethodInvoker.removeErrors();
			var netmdAccClass = new netmdbranchClassLoader();
			netmdAccClass.load();
			var netmdAccUI = new netmdBranchUIStartup(netmdId);	
			netmdAccUI.init(netmdId);
		}	
	});
	 
	
	
	
	self.ptbDelete.die('click').live('click',function() {
		removeErrors();
		var netmdId=self.getSelectedbranchId(self.pgTableName);
		if(netmdId!="") {
			var netmdUIService = self.getNetmdUIService();
				  var netmdDelResponse = netmdUIService.deleteNetmd(netmdId);
				//alert(JSON.stringify(netmdDelResponse));	
				if(netmdDelResponse.success==true) {
					showTip(constants.NETMDDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netmdDelResponse.error));
				}
				var netmdTableNavigator = self.getnetmdTableNavigator();
				netmdTableNavigator.list("netmdlist");
			
			}	
	});
	
	 
	
}
netmdUIStartup.prototype.bindEvents = function() {
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
	   var netmdId= $j(this).parent().attr('id');
		if(netmdId!="") {
			var viewNetmdUI = parent.getViewNetmdUI();
			viewNetmdUI.init(netmdId);
		}	
	}); 
}



