function SuperAdminGlobalToolBarProcessor() {
	this.globalService = new SuperAdminGlobalServiceImpl();
	this.netlimslist="#leftPaneNetLims";
	this.netmdlist="#leftPaneNetMd";
	this.netrxlist="#leftPaneNetRx";
	this.organizationlist="#leftPaneOrganization";
	this.settings="#leftPaneSettings";
	this.newnetlims="#ribbonNewNetLims";
	this.newnetmd="#ribbonNewNetMd";
	this.newnetrx="#ribbonNewNetRx";
	this.neworganization="#ribbonNewOrgzn";
	this.newnetpos="#ribbonNewNetPos";
	this.netposlist="#leftPaneNetPos";
	this.setsync="#ribbonSetSync";
	
	/*
	this.filter = $j('#filter');
	this.filterBench=$j('#filterWorkBench');
	this.ftbContainer=$j('#filterToolBar-Container');
	this.ptbContainer=$j('#pageToolBar-Container');
	 */
}
SuperAdminGlobalToolBarProcessor.prototype.getGlobalService = function() {
	return this.globalService;
}
SuperAdminGlobalToolBarProcessor.prototype.init = function() {
	var self=this;
	var globalService = self.getGlobalService();
	var globalTbInfo = globalService.getRibbonTBData();
	self.createGlobalTB(globalTbInfo);
	var leftTBInfo=globalService.getLeftPaneTBData();
	self.createLeftPaneTB(leftTBInfo);
	self.bindLeftPaneEvents();
	self.bindRibbonTBEvents();
	
}

SuperAdminGlobalToolBarProcessor.prototype.createGlobalTB=function(globalTbInfo) {
	var globalTB = new GlobalToolBar(globalTbInfo);
	$j('.global-bar-tab .controlB').html(globalTB.result);		
}
SuperAdminGlobalToolBarProcessor.prototype.createLeftPaneTB=function(leftTBInfo) {
	var leftpaneTB = new leftpaneToolBar(leftTBInfo.buttons);
	$j('.leftmenu').html(leftpaneTB.result);		
}

SuperAdminGlobalToolBarProcessor.prototype.bindRibbonTBEvents=function() {
	var self=this;
	if($j(self.newnetlims)) {
		$j(self.newnetlims).die('click').live("click",function() {		
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			if(pageHandler.isnetlimsClassLoaded()!=true){
				var netlimsClass = new netlimsClassLoader();
				netlimsClass.load();
				pageHandler.setnetlimsClassLoaded(true);
			}
	var netlimsUI;
	if(pageHandler.getHomePage()==constants.NETLIMSPAGE)
		{
			netlimsUI = pageHandler.getActivePage();
		}
	else
		{
			netlimsUI = new netlimsUIStartup();	
		}
			netlimsUI.createNetlimsModal(obj);
		});
	}
	if($j(self.newnetmd)) {
		$j(self.newnetmd).die('click').live("click",function() {		
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			if(pageHandler.isnetmdClassLoaded()!=true){
				var netmdClass = new netmdClassLoader();
				netmdClass.load();
				pageHandler.setnetmdClassLoaded(true);
			}
	var netmdUI;
	if(pageHandler.getHomePage()==constants.NETMDPAGE)
			{
				netmdUI = pageHandler.getActivePage();
			}
	else
			{
				netmdUI = new netmdUIStartup();	
			}
				netmdUI.createNetmdModal(obj);
		});
	}
	
	if($j(self.newnetrx)) {
		$j(self.newnetrx).die('click').live("click",function() {		
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			if(pageHandler.isnetrxClassLoaded()!=true){
				var netrxClass = new netrxClassLoader();
				netrxClass.load();
				pageHandler.setnetrxClassLoaded(true);
			}
		var netrxUI;
		if(pageHandler.getHomePage()==constants.NETRXPAGE)
			{
				netrxUI = pageHandler.getActivePage();
			}
		else
			{
				netrxUI = new netmdUIStartup();	
			}
				netrxUI.createNetrxModal(obj);
		});
	}
	if($j(self.newnetpos)) {
		$j(self.newnetpos).die('click').live("click",function() {		
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			if(pageHandler.isnetposClassLoaded()!=true){
				var netposClass = new netposClassLoader();
				netposClass.load();
				pageHandler.setnetposClassLoaded(true);
			}
		var netposUI;
		if(pageHandler.getHomePage()==constants.NETPOSPAGE)
			{
				netposUI = pageHandler.getActivePage();
			}
		else
			{
				netposUI = new NetPosUIStartup();	
			}	
				netposUI.createNetPosModal(obj);
		});
	}
	if($j(self.neworganization)) {
		$j(self.neworganization).die('click').live("click",function() {		
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			if(pageHandler.isorganizationLoaded()!=true){
				var orgClass = new organizationClassLoader();
				orgClass.load();
				pageHandler.setorganizationClassLoaded(true);
			}
		var orgnUI;
		if(pageHandler.getHomePage()==constants.ORGNPAGE)
			{
				orgnUI = pageHandler.getActivePage();
			}
		else
			{
				orgnUI = new organizationUIStartup();	
			}	
				orgnUI.createOrganizationModal(obj);
		});
	}
	if($j(self.setsync)) {
		$j(self.setsync).die('click').live("click",function() {		
			var obj=$j(this);
			self.createSyncModal(obj);
		});
	}
	
	
}

SuperAdminGlobalToolBarProcessor.prototype.createSyncModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.SUPERADMINSYNCJSON,constants.SUPERADMINSYNCMODAL);		
	openModalBox(obj,constants.SUPERADMINSYNCMODAL);
	var syncStatusProcessor = new SASyncStatusProcessor(self);
	syncStatusProcessor.init();
	return syncStatusProcessor; 
}

SuperAdminGlobalToolBarProcessor.prototype.bindLeftPaneEvents=function() {
	var self=this;
	
	if($j(self.netlimslist)) {
		$j(self.netlimslist).die('click').live("click",function() {	
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			pageHandler.setHomePage(constants.NETLIMSPAGE);
			if(pageHandler.isnetlimsClassLoaded()!=true){
				var netlimsClass = new netlimsClassLoader();
				netlimsClass.load();
				pageHandler.setnetlimsClassLoaded(true);
			}
			var netlimsUI = new netlimsUIStartup();	
			netlimsUI.init();
		});
	}	
	
	if($j(self.netmdlist)) {
		$j(self.netmdlist).die('click').live("click",function() {	
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			pageHandler.setHomePage(constants.NETMDPAGE);
			if(pageHandler.isnetmdClassLoaded()!=true){
				var netmdClass = new netmdClassLoader();
				netmdClass.load();
				pageHandler.setnetmdClassLoaded(true);
			}
			var netmdUI = new netmdUIStartup();	
			netmdUI.init();
		});
	}	
	
	if($j(self.netrxlist)) {
		$j(self.netrxlist).die('click').live("click",function() {	
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			pageHandler.setHomePage(constants.NETRXPAGE);
			if(pageHandler.isnetrxClassLoaded()!=true){
				var netrxClass = new netrxClassLoader();
				netrxClass.load();
				pageHandler.setnetrxClassLoaded(true);
			}
			var netrxUI = new netrxUIStartup();	
			netrxUI.init();
		});
	}	
	
	if($j(self.netposlist)) {
		$j(self.netposlist).die('click').live("click",function() {	
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			pageHandler.setHomePage(constants.NETPOSPAGE);
			if(pageHandler.isnetposClassLoaded()!=true){
				var netposClass = new netposClassLoader();
				netposClass.load();
				pageHandler.setnetposClassLoaded(true);
			}
			var netposUI = new NetPosUIStartup();	
			netposUI.init();
		});
	
	}
	
	if($j(self.organizationlist)) {
		$j(self.organizationlist).die('click').live("click",function() {	
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			pageHandler.setHomePage(constants.ORGNPAGE);
			if(pageHandler.isorganizationLoaded()!=true){
				var netmdClass = new organizationClassLoader();
				netmdClass.load();
				pageHandler.setorganizationClassLoaded(true);
			}
			var organizationUI = new organizationUIStartup();	
			organizationUI.init();
		});
	}	
	
	if($j(self.settings)) {
		$j(self.settings).die('click').live("click",function() {
			  var obj=$j(this);
			commonMethodInvoker.removeErrors();
			$j('#pageToolBar-Container').html("");
			var adminUI = new SuperAdminSettingsToolBarProcessor();
			adminUI.init();  
		});
	}	
	
}
