function organizationBranchUIStartup(netmdId) {
	this.pgTableName = "#branchOrgnzAcc";
	this.pgTableContainer="#branchOrgnzAccTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#BRANCHORGPTBContainer #btn_new_ptb_id');
	this.ptbOrglist=$j('#BRANCHORGPTBContainer #btn_back_ptb_id');
	this.ptbView=$j('#BRANCHORGPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#BRANCHORGPTBContainer #btn_delete_ptb_id');
	this.ptbSync=$j('#BRANCHORGPTBContainer #btn_change_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.orgnzId=netmdId;
	this.pgTableRowClass = this.pgTableName + ' .userOrgAccIdCol';
	this.exp = new ExpressionListDTO();
	this.orgzAccService = new OrganizationUserServiceImpl();
	this.listUrl = constants.ORGAZSUPERUSERLISTURL;
	this.orgzAccTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.orgzAccService,this.exp);
	this.viewOrgzBranchUI = new ViewOrganizationBranchUI(this);
}

organizationBranchUIStartup.prototype.setorgAccTableNavigator = function(orgzAccTableNavigator) {
	this.orgzAccTableNavigator = orgzAccTableNavigator;
}
organizationBranchUIStartup.prototype.getorgnAccTableNavigator = function() {
	return this.orgzAccTableNavigator;
}
organizationBranchUIStartup.prototype.getViewOrgzBranchUI = function() {
	return this.viewOrgzBranchUI;
}
organizationBranchUIStartup.prototype.getBranchId = function() {
	return this.BranchId;
}
organizationBranchUIStartup.prototype.setBranchId = function(BranchId) {
	this.BranchId=BranchId;
}
organizationBranchUIStartup.prototype.getOrgnzId = function() {
	return this.orgnzId;
}
organizationBranchUIStartup.prototype.getOrgnzUIService = function() {
	return this.orgzAccService;
}


//Set the page title of the order ui page
organizationBranchUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

//Return the selected Order Id from the list table
organizationBranchUIStartup.prototype.getSelectedbranchId = function () {
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

organizationBranchUIStartup.prototype.init = function(orgnzId) {
	var self = this;
	var selName="organisationId";
	var selValue=self.orgnzId;
	var selOperator = "eq";
	self.setPageTitle(constants.USERLIST);
	var exp = new ExpressionListDTO();
	var expr = new ExpressionDTO(selName,selValue,selOperator);
	exp.add(expr);
	var orgAccntTableNavigator = self.getorgnAccTableNavigator();
	orgAccntTableNavigator.setExp(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.create(constants.ORGNBRANCH, constants.ORGNPAGEBRANCHJSON); //Create the Page tool Bar for Order
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.ORGNACCBRANCHLISTJSON);//Create Table for Listing Order
	dataTableProcessor.setCustomTable(self.pgTableName);
	orgAccntTableNavigator.list("userlist");
	self.bindEvents();
	pageHandler.setActivePage(self);
}



organizationBranchUIStartup.prototype.createBranchModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWORGACCUSERJSON,constants.NEWORGACCUSERMODAL);		
	openModalBox(obj,constants.NEWORGACCUSERMODAL);
	var newBranchUI = new NewBranchUI(self);
	newBranchUI.init();
	return newBranchUI;
}

 organizationBranchUIStartup.prototype.createSyncModal = function(obj,branchId) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NETMDACCSYNCJSON,constants.NETMDACCSYNCMODAL);		
	openModalBox(obj,constants.NETMDACCSYNCMODAL);
	var netmdAccSyncUI = new NetmdAccSyncUI(self,branchId);
	netmdAccSyncUI.init();
	return netmdAccSyncUI; 
} 


organizationBranchUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	 self.ptbOrglist.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
			if(pageHandler.isorganizationLoaded()!=true){
				var netmdClass = new organizationClassLoader();
				netmdClass.load();
				pageHandler.setorganizationClassLoaded(true);
			}
			var orgUI = new organizationUIStartup();	
			orgUI.init();
	});	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var branchId=self.getSelectedbranchId(self.pgTableName);
		if(branchId!="") {
			var viewOrgzBranchUI = self.getViewOrgzBranchUI();
			viewOrgzBranchUI.init(branchId);
		}	
	});
	/* self.ptbSync.die('click').live('click',function() {
		removeErrors();
		var branchId=self.getSelectedbranchId(self.pgTableName);
		if(branchId!="") {
			var obj=$j(this);
			self.createSyncModal(obj,branchId);
		}	
	});  */
	self.ptbDelete.die('click').live('click',function() {
		removeErrors();
		var branchId=self.getSelectedbranchId(self.pgTableName);
		if(branchId!="") {
			var orgUIService = self.getOrgnzUIService();
				 var orgDelResponse = orgUIService.deleteOrgBranch(branchId);
				//alert(JSON.stringify(orgDelResponse));	
				if(orgDelResponse.success==true) {
					showTip(constants.ORGUSERDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(orgDelResponse.error));
				}
				var orgzAccTableNavigator = self.getorgnAccTableNavigator();
				orgzAccTableNavigator.list("userlist");
			 
		}	
	});
	
	
	
	
}
organizationBranchUIStartup.prototype.bindEvents = function() {
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
			var viewOrgzBranchUI = parent.getViewOrgzBranchUI();
			viewOrgzBranchUI.init(branchId);
		}	
	});  
}



