function netlimsUIStartup() {
	this.pgTableName = "#netlims";
	this.pgTableContainer="#netlimsTableCont";
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#NETLIMSPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#NETLIMSPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#NETLIMSPTBContainer #btn_delete_ptb_id');
	this.ptbOrderCount=$j('#NETLIMSPTBContainer #btn_orderCount_ptb_id');
	this.ptbSync=$j('#NETLIMSPTBContainer #btn_change_ptb_id');
	this.brachlist=$j('#NETLIMSPTBContainer #btn_brannchlist_ptb_id');
	this.newbranch=$j('#NETLIMSPTBContainer #btn_newbranch_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.netlimsId=userdata.labId;
	this.pgTableRowClass = this.pgTableName + ' .netlimsIdCol ';	
	this.exp = new ExpressionListDTO();
	this.netlimsService = new NetlimsServiceImpl();
	this.listUrl = constants.NETLIMSBRANCHLISTURL;
	this.netlimsTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.netlimsService,this.exp);
	this.viewNetlimsUI = new ViewNetlimsUI(this);
}

netlimsUIStartup.prototype.setnetlimsAccTableNavigator = function(netlimsTableNavigator) {
	this.netlimsTableNavigator = netlimsTableNavigator;
}
netlimsUIStartup.prototype.getnetlimsTableNavigator = function() {
	return this.netlimsTableNavigator;
}
netlimsUIStartup.prototype.getViewNetlimsUI = function() {
	return this.viewNetlimsUI;
}
netlimsUIStartup.prototype.getNetlimsId = function() {
	return this.netlimsId;
}
netlimsUIStartup.prototype.setNetlimsId = function(netlimsId) {
	this.netlimsId=netlimsId;
}
netlimsUIStartup.prototype.getNetlimsUIService = function() {
	return this.netlimsService;
}


//Set the page title of the order ui page
netlimsUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

//Return the selected Order Id from the list table
netlimsUIStartup.prototype.getSelectedbranchId = function () {
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

netlimsUIStartup.prototype.init = function() {
	var self = this;
	self.setPageTitle(constants.NETLIMSLIST);
	var exp = new ExpressionListDTO();
	var netlimsTableNavigator = self.getnetlimsTableNavigator();
	netlimsTableNavigator.setExp(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.create(constants.NETLIMS, constants.NETLIMSPAGEJSON); //Create the Page tool Bar for Order
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.NETLIMSLISTJSON);//Create Table for Listing Order
	dataTableProcessor.setCustomTable(self.pgTableName);
	netlimsTableNavigator.list("netlimslist");
	self.bindEvents();
	pageHandler.setActivePage(self);
}



netlimsUIStartup.prototype.createNetlimsModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETLIMSJSON,constants.NEWNETLIMSMODAL);		
	openModalBox(obj,constants.NEWNETLIMSMODAL);
	var newNetlimsUI = new NewNetlimsUI(self);
	newNetlimsUI.init();
	return newNetlimsUI;
}

netlimsUIStartup.prototype.createOrderCountModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.ORDERCOUNTJSON,constants.NEWORDERCOUNTMODAL);		
	openModalBox(obj,constants.NEWORDERCOUNTMODAL);
	$j('#'+constants.NEWORDERCOUNTMODAL + ' #orderCount').addClass("newBox");
	$j('#'+constants.NEWORDERCOUNTMODAL + ' #orderCount').attr("readonly",true);
	$j('#'+constants.NEWORDERCOUNTMODAL + ' #orderCount').val(0);
	self.bindOrderCountEvents();
	return self;
}
netlimsUIStartup.prototype.bindOrderCountEvents = function(){

	var self=this;
	var serviceHandler = self.getNetlimsUIService();
	$j('#'+constants.NEWORDERCOUNTMODAL + ' #fromDate').datepicker();
	$j('#'+constants.NEWORDERCOUNTMODAL + ' #toDate').datepicker();
	$j('#'+constants.NEWORDERCOUNTMODAL + ' #btnCancel').die('click').live('click',function() {
		$j('#' + constants.NEWORDERCOUNTMODAL).trigger('reveal:close');
	});
	$j('#'+constants.NEWORDERCOUNTMODAL + ' #btnSearch').die('click').live('click',function() {
		var input = new OrderCountInput();
		input.setBranch(self.getSelectedbranchId(self.pgTableName));
		input.setFromDate($j('#'+constants.NEWORDERCOUNTMODAL + ' #fromDate').val());
		input.setToDate($j('#'+constants.NEWORDERCOUNTMODAL + ' #toDate').val());
		var result = serviceHandler.findOrderCountForLab(input);
		$j('#'+constants.NEWORDERCOUNTMODAL + ' #orderCount').val(result);
	});
	
}

netlimsUIStartup.prototype.createSyncModal = function(obj,netlimsId) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NETLIMSACCSYNCJSON,constants.NETLIMSSYNCMODAL);		
	openModalBox(obj,constants.NETLIMSSYNCMODAL);
	var netlimsSyncUI = new NetlimsSyncUI(self,netlimsId);
	netlimsSyncUI.init();
	return netlimsSyncUI; 
}



netlimsUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		self.createNetlimsModal(obj);
	});	
	 self.ptbView.die('click').live('click',function() {
		removeErrors();
		var netlimsId=self.getSelectedbranchId(self.pgTableName);
		if(netlimsId!="") {
			var viewNetlimsUI = self.getViewNetlimsUI();
			viewNetlimsUI.init(netlimsId);
		}	
	});
	self.ptbSync.die('click').live('click',function() {
		removeErrors();
		var netlimsId=self.getSelectedbranchId(self.pgTableName);
		if(netlimsId!="") {
			var obj=$j(this);
			self.createSyncModal(obj,netlimsId);
		}	
	});
	
	self.newbranch.die('click').live('click',function() {
		var obj=$j(this);
		var netlimsId=self.getSelectedbranchId(self.pgTableName);
		if(netlimsId!="") {
			commonMethodInvoker.removeErrors();
			var netlimsAccClass = new netlimsbranchClassLoader();
			netlimsAccClass.load();
			var netlimsAccUI = new netlimsBranchUIStartup(netlimsId);	
			netlimsAccUI.createBranchModal(obj);
		}	
	});
	self.ptbOrderCount.die('click').live('click',function() {
		removeErrors();
		var netlimsId=self.getSelectedbranchId(self.pgTableName);
		if(netlimsId!="") {
			var obj=$j(this);
			self.createOrderCountModal(obj,netlimsId);
		}	
	});
	self.ptbDelete.die('click').live('click',function() {
		removeErrors();
		var netlimsId=self.getSelectedbranchId(self.pgTableName);
		if(netlimsId!="") {
			var netlimsUIService = self.getNetlimsUIService();
				  var netlimsDelResponse = netlimsUIService.deleteNetlims(netlimsId);
				//alert(JSON.stringify(netlimsDelResponse));	
				if(netlimsDelResponse.success==true) {
					showTip(constants.NETLIMSDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netlimsDelResponse.error));
				}
				var netlimsTableNavigator = self.getnetlimsTableNavigator();
				netlimsTableNavigator.list("netlimslist");
			
			}	
	});
	
	 self.brachlist.die('click').live('click',function() {
		var obj=$j(this);
		var labId=self.getSelectedbranchId(self.pgTableName);
		var sellab = $j(self.pgTableName + ' tbody tr[selected] td:eq(1)').text();
		if(labId!="") {
			commonMethodInvoker.removeErrors();
			var netlimsAccClass = new netlimsbranchClassLoader();
			netlimsAccClass.load();
			var netlimsAccUI = new netlimsBranchUIStartup(labId,sellab);	
			netlimsAccUI.init(labId,sellab);
		}	
	});
	
	
}
netlimsUIStartup.prototype.bindEvents = function() {
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
	   var netlimsId= $j(this).parent().attr('id');
		if(netlimsId!="") {
			var viewNetlimsUI = parent.getViewNetlimsUI();
			viewNetlimsUI.init(netlimsId);
		}	
	}); 
}

function OrderCountInput() {
	this.setBranch = function(branch) {
		this.branch = branch;
	}
	this.getBranch = function() {
		return this.branch;
	}
	this.setFromDate = function(fromDate) {
		this.fromDate = fromDate;
	}
	this.getFromDate = function() {
		return this.fromDate;
	}
	this.setToDate = function(toDate) {
		this.toDate = toDate;
	}
	this.getToDate=function() {
		return this.toDate;
	}
}

