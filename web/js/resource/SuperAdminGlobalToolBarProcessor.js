function SuperAdminGlobalToolBarProcessor() {
	this.globalService = new SuperAdminGlobalServiceImpl();
	
	this.netlimslist="#leftPaneNetLims";
	this.netmdlist="#leftPaneNetMd";
	this.netrxlist="#leftPaneNetRx";
	this.settings="#leftPaneSettings";
	this.newnetlims="#ribbonNewNetLims";
	this.newnetmd="#ribbonNewNetMd";
	this.newnetrx="#ribbonNewNetRx";
	this.setsync="#ribbonSetSync";
	this.newnetpos="#ribbonNewNetPos";
	this.netposlist="#leftPaneNetPos";
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
			var netlimsUI = new netlimsUIStartup();	
			netlimsUI.createNetlimsModal(obj);
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
			var netposUI = new NetPosUIStartup();	
			netposUI.createNetPosModal(obj);
		});
	}
	
	
	
}


SuperAdminGlobalToolBarProcessor.prototype.bindLeftPaneEvents=function() {
	var self=this;
	
	if($j(self.netlimslist)) {
		$j(self.netlimslist).die('click').live("click",function() {	
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			if(pageHandler.isnetlimsClassLoaded()!=true){
				var netlimsClass = new netlimsClassLoader();
				netlimsClass.load();
				pageHandler.setnetlimsClassLoaded(true);
			}
			var netlimsUI = new netlimsUIStartup();	
			netlimsUI.init();
		});
	}	
	
	if($j(self.netposlist)) {
		$j(self.netposlist).die('click').live("click",function() {	
			var obj=$j(this);
		
			commonMethodInvoker.removeErrors();
			if(pageHandler.isnetposClassLoaded()!=true){
				var netposClass = new netposClassLoader();
				netposClass.load();
				pageHandler.setnetposClassLoaded(true);
			}
			var netposUI = new NetPosUIStartup();	
			netposUI.init();
		});
	
	}
	
	
	if($j(self.netlimsAccbranchsettings)) {
		$j(self.netlimsAccbranchsettings).die('click').live("click",function() {
			 var obj=$j(this);
			commonMethodInvoker.removeErrors();
			$j('#pageToolBar-Container').html("");
			var adminUI = new SettingsToolBarProcessor();
			adminUI.init(); 
		});
	}	
	
}
