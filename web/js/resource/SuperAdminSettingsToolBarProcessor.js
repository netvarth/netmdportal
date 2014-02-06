function SuperAdminSettingsToolBarProcessor() {
	this.globalService = new SuperAdminGlobalServiceImpl();
	this.changePasswrdButton ="#btnChangePwd";
	this.btnUserLogList="#btnUserLogList";
	this.btnSyncLogList="#btnSyncLogList";
	this.btnTestList="#btnTestList";
	this.btnSpecimenList="#btnSpecimenList";
	this.pageTitle = $j('#pageTitle');
	
}
SuperAdminSettingsToolBarProcessor.prototype.getGlobalService = function() {
	return this.globalService;
}
//Set the page title of the agent ui page
SuperAdminSettingsToolBarProcessor.prototype.setPageTitle = function(value) {
	this.pageTitle.empty().html(value);
}
SuperAdminSettingsToolBarProcessor.prototype.init = function() {
	var self=this;
	var globalService = self.getGlobalService();
	var adminTbInfo = globalService.getAdminTBData();
	self.setPageTitle(constants.SETTINGTITLE);
	self.createAdminTB(adminTbInfo);
	self.bindAdminTBEvents();
}
SuperAdminSettingsToolBarProcessor.prototype.createAdminTB=function(globalTbInfo) {
	var adminTB = new AdminToolBar(globalTbInfo);
	$j("#tabs-1").html(adminTB.result);		
}

SuperAdminSettingsToolBarProcessor.prototype.createChangePasswrdModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWSUPERADMINPASWRDCHGJSON,constants.NEWSUPERADMINPASWRDCHGMODAL);		
	openModalBox(obj,constants.NEWSUPERADMINPASWRDCHGMODAL);
	var newSuperAdminCngPswdUI = new SuperAdminChgpaswrdUIStartup();
	newSuperAdminCngPswdUI.init();
	return newSuperAdminCngPswdUI; 
}


SuperAdminSettingsToolBarProcessor.prototype.bindAdminTBEvents=function() {
	var self=this;
	$j(self.changePasswrdButton).die('click').click(function(){
		var obj=$j(this);
		if(pageHandler.issuperadminSettingsClassLoaded()!=true){
				var SuperadminSettingsClass = new SuperAdminSettingsClassLoader();
				SuperadminSettingsClass.load();
				pageHandler.setsuperadminSettingsClassLoaded(true);
			} 
		self.createChangePasswrdModal(obj);
	});
	
	$j(self.orderTypeButton).die('click').click(function(){
		var obj=$j(this);
		
		if(pageHandler.isnetlimsAccSettingsClassLoaded()!=true){
				var NetlimsAccSettingsClass = new NetlimsAccSettingsClassLoader();
				NetlimsAccSettingsClass.load();
				pageHandler.setnetlimsAccSettingsClassLoaded(true);
			} 
		self.createorderTypeModal(obj);
	});
	
	
	
	
}
