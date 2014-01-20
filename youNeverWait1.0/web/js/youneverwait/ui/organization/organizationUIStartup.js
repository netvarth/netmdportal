function organizationUIStartup() {
	this.pgTableName = "#organization";
	this.pgTableContainer="#organizationTableCont";
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#ORGANZTNPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#ORGANZTNPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#ORGANZTNPTBContainer #btn_delete_ptb_id');
	this.ptbSync=$j('#ORGANZTNPTBContainer #btn_change_ptb_id');
	this.brachlist=$j('#ORGANZTNPTBContainer #btn_brannchlist_ptb_id');
	this.newbranch=$j('#ORGANZTNPTBContainer #btn_newbranch_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.orgzId=userdata.netmdId;
	this.pgTableRowClass = this.pgTableName + ' .netmdIdCol ';
	this.exp = new ExpressionListDTO();
	this.organzinService = new OrganizationServiceImpl();
	this.listUrl = constants.ORGANZTNBRANCHLISTURL;
	this.orgnznTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.organzinService,this.exp);
	this.viewOrgnzUI = new ViewOrganizationUI(this);
}

organizationUIStartup.prototype.setorgnznAccTableNavigator = function(orgnznTableNavigator) {
	this.orgnznTableNavigator = orgnznTableNavigator;
}
organizationUIStartup.prototype.getorgznTableNavigator = function() {
	return this.orgnznTableNavigator;
}
organizationUIStartup.prototype.getViewOrgnzUI = function() {
	return this.viewOrgnzUI;
}
organizationUIStartup.prototype.getOrgnzId = function() {
	return this.orgzId;
}
organizationUIStartup.prototype.setOrgznId = function(orgzId) {
	this.orgzId=orgzId;
}
organizationUIStartup.prototype.getOrgnUIService = function() {
	return this.organzinService;
}


//Set the page title of the order ui page
organizationUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

//Return the selected Order Id from the list table
organizationUIStartup.prototype.getSelectedbranchId = function () {
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

organizationUIStartup.prototype.init = function() {
	var self = this;
	self.setPageTitle(constants.ORGANZTNLIST);
	var exp = new ExpressionListDTO();
	var orgnznTableNavigator = self.getorgznTableNavigator();
	orgnznTableNavigator.setExp(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.create(constants.ORGANZTN, constants.ORGANZTNPAGEJSON); //Create the Page tool Bar for Order
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.ORGANZTNLISTJSON);//Create Table for Listing Order
	dataTableProcessor.setCustomTable(self.pgTableName);
	orgnznTableNavigator.list("orglist");
	self.bindEvents();
	pageHandler.setActivePage(self);
}



organizationUIStartup.prototype.createOrganizationModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWORGANZTNJSON,constants.NEWORGANZTNMODAL);		
	openModalBox(obj,constants.NEWORGANZTNMODAL);
	var newOrgnznUI = new NewOrganizationUI(self);
	newOrgnznUI.init();
	return newOrgnznUI;
}

organizationUIStartup.prototype.createSyncModal = function(obj,netmdId) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NETLIMSACCSYNCJSON,constants.NETMDSYNCMODAL);		
	openModalBox(obj,constants.NETMDSYNCMODAL);
	var netmdSyncUI = new NetmdSyncUI(self,netmdId);
	netmdSyncUI.init();
	return netmdSyncUI; 
}



organizationUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		self.createOrganizationModal(obj);
	});	
	 self.ptbView.die('click').live('click',function() {
		removeErrors();
		var orgnzId=self.getSelectedbranchId(self.pgTableName);
		if(orgnzId!="") {
			var viewOrgnzUI = self.getViewOrgnzUI();
			viewOrgnzUI.init(orgnzId);
		}	
	});
	 self.ptbSync.die('click').live('click',function() {
		removeErrors();
		var orgnzId=self.getSelectedbranchId(self.pgTableName);
		if(orgnzId!="") {
			var obj=$j(this);
			self.createSyncModal(obj,orgnzId);
		}	
	});
	
	self.newbranch.die('click').live('click',function() {
		var obj=$j(this);
		var orgnzId=self.getSelectedbranchId(self.pgTableName);
		if(orgnzId!="") {
			commonMethodInvoker.removeErrors();
			var orgnzAccClass = new organizationbranchClassLoader();
			orgnzAccClass.load();
			var orgnzAccUI = new organizationBranchUIStartup(orgnzId);	
			orgnzAccUI.createBranchModal(obj);
		}	
	});
	
	self.brachlist.die('click').live('click',function() {
		var obj=$j(this);
		var orgnzId=self.getSelectedbranchId(self.pgTableName);
		if(orgnzId!="") {
			commonMethodInvoker.removeErrors();
			var orgnzAccClass = new organizationbranchClassLoader();
			orgnzAccClass.load();
			var orgnzAccUI = new organizationBranchUIStartup(orgnzId);	
			orgnzAccUI.init(orgnzId);
		}	
	});
	 
	
	
	
	self.ptbDelete.die('click').live('click',function() {
		removeErrors();
		var orgnzId=self.getSelectedbranchId(self.pgTableName);
		if(orgnzId!="") {
			var organzUIService = self.getOrgnUIService();
				  var organzDelResponse = organzUIService.deleteOrganz(orgnzId);
				//alert(JSON.stringify(organzDelResponse));	
				if(organzDelResponse.success==true) {
					showTip(constants.ORGANZTNDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(organzDelResponse.error));
				}
				var orgnznTableNavigator = self.getorgznTableNavigator();
				orgnznTableNavigator.list("orglist");
			
			}	
	});
	
	 
	
}
organizationUIStartup.prototype.bindEvents = function() {
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
	   var orgnzId= $j(this).parent().attr('id');
		if(orgnzId!="") {
			var viewOrgnzUI = parent.getViewOrgnzUI();
			viewOrgnzUI.init(orgnzId);
		}	
	}); 
}



