function SettingsToolBarProcessor() {
	this.globalService = new GlobalServiceImpl();
	this.changePasswrdButton ="#btnNetlimsChangePwd";
	this.orderTypeButton="#btnNetlimsOrdertype";
	this.changePasswrdnetMdbutton="#btnNetMdChangePwd";
	this.changePasswrdnetRxbutton="#btnNetRxChangePwd";
	this.pageTitle = $j('#pageTitle');
	/* this.testButton="#btnTest";
	this.discountButton = "#btnDiscounts";
	this.backupButton = "#btnBackUP";
	this.configButton = "#btnConfig";
	this.areaButton = "#btnArea";
	this.specimenButton = "#btnSpecimens";
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
SettingsToolBarProcessor.prototype.netmdAccinit = function() {
	var self=this;
	var globalService = self.getGlobalService();
	var adminTbInfo = globalService.getAdminTBnetmdACcData();
	self.setPageTitle(constants.SETTINGTITLE);
	self.createAdminTB(adminTbInfo);
	self.bindAdminTBEvents();
}
SettingsToolBarProcessor.prototype.netrxAccinit = function() {
	var self=this;
	var globalService = self.getGlobalService();
	var adminTbInfo = globalService.getAdminTBnetrxACcData();
	self.setPageTitle(constants.SETTINGTITLE);
	self.createAdminTB(adminTbInfo);
	self.bindAdminTBEvents();
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
SettingsToolBarProcessor.prototype.createorderTypeModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETLIMSORDERTYPEJSON,constants.ORDERTYPEMODAL);		
	openModalBox(obj,constants.ORDERTYPEMODAL);
	var netlimsOrderTypeUI = new NetlimsOrderTypeUIStartup();
	netlimsOrderTypeUI.init();
	return netlimsOrderTypeUI; 
}
SettingsToolBarProcessor.prototype.createChangePasswrdnetMdModal = function(obj) {
	var self = this;
	if(pageHandler.isnetmdControlClassLoaded()!=true){
		ajaxProcessor.setUrl("/youNeverWait/js/youneverwait/ui/netmdAccount/netMdloader.js");
		ajaxProcessor.getJavaScript();
		pageHandler.setnetmdControlClassLoaded(true);
			}
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETMDACCPASWRDCHGJSON,constants.NEWNETMDACCPASWRDCHGMODAL);		
	openModalBox(obj,constants.NEWNETMDACCPASWRDCHGMODAL);
	var newNetmdAccCngPswdUI = new NetmdAccChgpaswrdUIStartup();
	newNetmdAccCngPswdUI.init();
	return newNetmdAccCngPswdUI; 
}
SettingsToolBarProcessor.prototype.createChangePasswrdnetrxModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETRXACCPASWRDCHGJSON,constants.NEWNETRXACCPASWRDCHGMODAL);		
	openModalBox(obj,constants.NEWNETRXACCPASWRDCHGMODAL);
	var newNetrxAccCngPswdUI = new NetrxAccChgpaswrdUIStartup();
	newNetrxAccCngPswdUI.init();
	return newNetrxAccCngPswdUI; 
}
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
	
	$j(self.orderTypeButton).die('click').click(function(){
		var obj=$j(this);
		if(pageHandler.isnetlimsAccSettingsClassLoaded()!=true){
				var NetlimsAccSettingsClass = new NetlimsAccSettingsClassLoader();
				NetlimsAccSettingsClass.load();
				pageHandler.setnetlimsAccSettingsClassLoaded(true);
			} 
		self.createorderTypeModal(obj);
	});
	$j(self.changePasswrdnetMdbutton).die('click').click(function(){
		var obj=$j(this);
		if(pageHandler.isnetmdAccSettingsClassLoaded()!=true){
				var NetmdAccSettingsClass = new NetmdAccSettingsClassLoader();
				NetmdAccSettingsClass.load();
				pageHandler.setnetmdAccSettingsClassLoaded(true);
			}
		self.createChangePasswrdnetMdModal(obj);
	});
	$j(self.changePasswrdnetRxbutton).die('click').click(function(){
		var obj=$j(this);
		if(pageHandler.isnetrxAccSettingsClassLoaded()!=true){
				var NetrxAccSettingsClass = new NetrxAccSettingsClassLoader();
				NetrxAccSettingsClass.load();
				pageHandler.setnetrxAccSettingsClassLoaded(true);
			}
		self.createChangePasswrdnetrxModal(obj);
	});
	
	
}
