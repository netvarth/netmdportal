

 
function NetPosUIStartup() {

	  this.pgTableName = "#netpos";
	this.pgTableContainer="#netposTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#netPosPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#netPosPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#netPosPTBContainer #btn_delete_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.netPosIdCol';
	this.exp = new ExpressionListDTO();
	this.netPosService = new NetPosServiceImpl();
	this.listUrl = constants.NETPOSLISTURL;
	this.netPosTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.netPosService,this.exp);
	this.ftbToolBar = '#netpos-filter-toolbar';
	this.filter = '#filter';
	this.netPosId=0;
	this.viewNetPosUI = new ViewNetPosUI(this);  
}




NetPosUIStartup.prototype.setNetPosTableNavigator = function(netPosTableNavigator) {
	this.netPosTableNavigator = netPosTableNavigator;
}

NetPosUIStartup.prototype.getNetPosTableNavigator = function() {
	return this.netPosTableNavigator;
}

NetPosUIStartup.prototype.getNetPosService = function() {

	return this.netPosService;
}

NetPosUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

  NetPosUIStartup.prototype.getViewNetPosUI = function() {
	return this.viewNetPosUI;
}   
NetPosUIStartup.prototype.getNetposId = function() {
	return this.netPosId;
}
NetPosUIStartup.prototype.setNetposId = function(netPosId) {
	this.netPosId=netPosId;
}
 NetPosUIStartup.prototype.init = function() {
	var self = this;
	self.setPageTitle(constants.NETPOSLIST);
	var exp = new ExpressionListDTO();
	var netposTableNavigator = self.getNetPosTableNavigator();
	netposTableNavigator.setExp(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.create(constants.NETPOS, constants.NETPOSPAGEJSON); //Create the Page tool Bar for Order
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.NETPOSLISTJSON);
	//Create Table for Listing Order
	dataTableProcessor.setCustomTable(self.pgTableName);
	netposTableNavigator.list();
	self.bindEvents();
	//pageHandler.setActivePage(self); 
}



NetPosUIStartup.prototype.createNetPosModal= function(obj) {
	  var self = this;
 commonMethodInvoker.removeErrors();
	createModal(constants.CREATENETPOSUI,constants.NETPOSMODEL);		
	openModalBox(obj,constants.NETPOSMODEL);
		var newNetPosUI = new NewNetPosUI(self);
	newNetPosUI.init();
	return newNetPosUI;
} 

NetPosUIStartup.prototype.bindToolBarEvents = function() {
var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(self);
		self.createNetPosModal(obj);
	});
	
	
	self.ptbView.die('click').live('click',function() {	
		removeErrors();
		var netPosId=self.getSelectedNetPosId(self.pgTableName);
		if(netPosId!="") {
			var viewNetposUI = self.getViewNetPosUI();
			viewNetposUI.init(netPosId);
			}
	});
	
	
	/*
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var netPosId=self.getSelectedNetPosId(self.pgTableName);
		if(netPosId!="") {
			var netPosService = self.getNetPosService();
			var netPosResponse = netPosService.viewNetPos(netPosId);
			var confirmStatus = confirm(constants.NETPOSDELETECONFIRM + netPosResponse.netPosName);
			if(confirmStatus==true) {				
				var netPosResponse = netPosService.deleteNetPos(netPosId);
				if(netPosResponse.success==true) {
					showTip(constants.NETPOSDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netPosResponse.error));
				}
				var netPosTableNavigator = self.getNetPosTableNavigator();
				netPosTableNavigator.list();
				methodInvoker.setDefaultData();
				defaultData = methodInvoker.getDefaultData();
			}
		}	
	});
	

*/
}
NetPosUIStartup.prototype.getSelectedNetPosId = function () {
	var netPosId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selNetPos = $j(this.pgTableName + ' tbody tr[selected]');
		if(selNetPos.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTNETPOS);
		} else if(selNetPos.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTNETPOSONLY);
		else
			netPosId=selNetPos.attr('id');
	}
	return netPosId;
}
NetPosUIStartup.prototype.bindEvents = function() {
	self = this;
	$j(self.pgTableName + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style',constants.SELECTEDROWCOLOR);
		}	
		removeErrors();
	});	
	
	$j(this.pgTableRowClass).die('click').live('click',function(){
	   var netPosId= $j(this).parent().attr('id');
	   $j('#' + constants.NETPOSTITLE + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(netPosId!="") {
			$j('#netPos-filter-wb').hide();
			var viewNetPosUI = self.getViewNetPosUI();
			viewNetPosUI.init(netPosId);
		}	
	});
  
  }