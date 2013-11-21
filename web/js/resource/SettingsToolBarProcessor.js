function SettingsToolBarProcessor() {
	this.globalService = new GlobalServiceImpl();
	this.changePasswrdButton ="#btnNetlimsChangePwd";
	this.orderTypeButton="#btnNetlimsOrdertype";
	this.pageTitle = $j('#pageTitle');
	/* this.testButton="#btnTest";
	this.testpackageButton="#btnTestPkg";
	this.referralButton="#btnDoctors";
	this.facilityButton="#btnFacilities";
	this.agentButton= "#btnAgents";
	this.discountButton = "#btnDiscounts";
	this.backupButton = "#btnBackUP";
	this.configButton = "#btnConfig";
	this.areaButton = "#btnArea";
	this.specimenButton = "#btnSpecimens";
	this.departmentButton="#btnDepartments";
	this.settingsButton="#btnSettings";
	this.btnConfigSave="#btnConfigSave";
	this.filter = $j('#filter');
	this.filterBench=$j('#filterWorkBench');
	this.ftbContainer=$j('#filterToolBar-Container'); */
	this.ptbContainer=$j('#pageToolBar-Container');
}
SettingsToolBarProcessor.prototype.getGlobalService = function() {
	return this.globalService;
}
//Set the page title of the agent ui page
SettingsToolBarProcessor.prototype.setPageTitle = function(value) {
	this.pageTitle.empty().html(value);
}
SettingsToolBarProcessor.prototype.init = function() {
	var self=this;
	var globalService = self.getGlobalService();
	var adminTbInfo = globalService.getAdminTBData();
	self.setPageTitle(constants.SETTINGTITLE);
	self.createAdminTB(adminTbInfo);
	self.bindAdminTBEvents();
}
SettingsToolBarProcessor.prototype.createAdminTB=function(globalTbInfo) {
	var adminTB = new AdminToolBar(globalTbInfo);
	$j("#tabs-1").html(adminTB.result);		
}

SettingsToolBarProcessor.prototype.createChangePasswrdModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETLIMSACCPASWRDCHGJSON,constants.NEWNETLIMSACCPASWRDCHGMODAL);		
	openModalBox(obj,constants.NEWNETLIMSACCPASWRDCHGMODAL);
	var newNetlimsAccCngPswdUI = new NetlimsAccChgpaswrdUIStartup();
	newNetlimsAccCngPswdUI.init();
	return newNetlimsAccCngPswdUI; 
}
/* SettingsToolBarProcessor.prototype.reset = function() {
	var self=this;
	self.filter.hide();
	self.filterBench.hide();
	self.ftbContainer.empty().html("");
	self.ptbContainer.empty().html("");
} */
SettingsToolBarProcessor.prototype.bindAdminTBEvents=function() {
	var self=this;
	$j(self.changePasswrdButton).die('click').click(function(){
		var obj=$j(this);
		if(pageHandler.isnetlimsAccSettingsClassLoaded()!=true){
				var NetlimsAccSettingsClass = new NetlimsAccSettingsClassLoader();
				NetlimsAccSettingsClass.load();
				pageHandler.setnetlimsAccSettingsClassLoaded(true);
			}
		self.createChangePasswrdModal(obj);
	});
	
	
	
	
	
}
