function InstallerUIStartup() {
	this.pgTableName = "#installer";
	this.pgTableContainer="installerListTableCont";
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#installerPTBContainer  #btn_new_ptb_id');
	this.ptbView=$j('#installerPTBContainer  #btn_view_ptb_id');
	this.ptbDelete=$j('#installerPTBContainer  #btn_delete_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = this.pgTableName + ' .netmdIdCol ';
	this.exp = new ExpressionListDTO();
	this.installerService = new InstallerServiceImpl();
	this.listUrl = constants.INSTALLERLISTURL;
	this.installerTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.installerService,this.exp);
	//this.viewOrgnzUI = new ViewOrganizationUI(this);
}

  InstallerUIStartup.prototype.setinstallerTableNavigator = function(installerTableNavigator) {
	this.installerTableNavigator = installerTableNavigator;
}
InstallerUIStartup.prototype.getinstallerTableNavigator = function() {
	return this.installerTableNavigator;
} 
 InstallerUIStartup.prototype.getViewOrgnzUI = function() {
	return this.viewOrgnzUI;
}
InstallerUIStartup.prototype.getOrgnzId = function() {
	return this.orgzId;
}
InstallerUIStartup.prototype.setOrgznId = function(orgzId) {
	this.orgzId=orgzId;
}
InstallerUIStartup.prototype.getOrgnUIService = function() {
	return this.organzinService;
}
 

 //Set the page title of the order ui page
InstallerUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

//Return the selected Order Id from the list table
InstallerUIStartup.prototype.getSelectedbranchId = function () {
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

InstallerUIStartup.prototype.init = function() {
	var self = this;
	self.setPageTitle(constants.INSTALLERTITLE);
	var exp = new ExpressionListDTO();
	var installerTableNavigator = self.getinstallerTableNavigator();
	installerTableNavigator.setExp(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.create(constants.INSTALLER,constants.INSTALLERPAGETOOLBARJSON); //Create the Page tool Bar for Order
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.INSTALLERLISTJSON);//Create Table for Listing Order
	dataTableProcessor.setCustomTable(self.pgTableName);
	installerTableNavigator.list();
	self.bindEvents();
} 

InstallerUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	$j('#installerPTBContainer  #btn_new_ptb_id').click(function() {
	var obj=$j(this);
	createModal(constants.CREATEINSTALLERUI,constants.INSTALLERMODAL);		
		openModalBox(obj,constants.INSTALLERMODAL);
		var newInstaller = new NewInstallerUI(self);
		newInstaller.init();
	});

	/* self.ptbView.die('click').live('click',function() {
		removeErrors();
		var orgnzId=self.getSelectedbranchId(self.pgTableName);
		if(orgnzId!="") {
			var viewOrgnzUI = self.getViewOrgnzUI();
			viewOrgnzUI.init(orgnzId);
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
	}); */
	
	 
	
}
InstallerUIStartup.prototype.bindEvents = function() {
	/*parent = this;
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
	});  */
}
 


