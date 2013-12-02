function GlobalToolBarProcessor() {
	this.globalService = new GlobalServiceImpl();
	
	this.netlimsAccbranchlist="#leftPaneNetlimsBranch";
	this.netlimsAccbranchorders="#leftPaneNetlimsOrders";
	this.netlimsAccbranchsettings="#leftPaneNetlimsSettings";
	this.newnetlimsBranch="#ribbonNewNetlimsBranch";
	this.netlimsdownLoad="#ribbonNetlimsDownload";
	
	this.netmdAccbranchlist="#leftPaneNetMdBranch";
	this.netmdAccbranchsettings="#leftPaneNetMdSettings";
	this.newnetmdBranch="#ribbonNewNetMdBranch";
	this.netmddownLoad="#ribbonDownload";
	
	/*this.blanketOrderButton="#leftPaneBlanketOrder";
	this.reportButton= "#leftPaneReports";
	this.adminButton = "#leftPaneAdmin";
	this.filter = $j('#filter');
	this.filterBench=$j('#filterWorkBench');
	this.ftbContainer=$j('#filterToolBar-Container');
	this.ptbContainer=$j('#pageToolBar-Container');
	this.newReferralButton = "#ribbonNewDoctor";
	this.newFacilityButton = "#ribbonNewFacility";
	this.newAgentButton = "#ribbonNewAgent";
	this.newOrderButton = "#ribbonNewOrder";
	this.newTestpackageButton="#ribbonNewTestPackage";
	this.resultReadyButton="#ribbonResultReady";
	this.editRateButton = "#ribbonEditRate";
	this.printerSetupButton="#ribbonPrintSetUp";
	this.todaysReportButton='#ribbonTodayReport'; */
}
GlobalToolBarProcessor.prototype.getGlobalService = function() {
	return this.globalService;
}
GlobalToolBarProcessor.prototype.init = function() {
	var self=this;
	var globalService = self.getGlobalService();
	var globalTbInfo = globalService.getRibbonTBData();
	self.createGlobalTB(globalTbInfo);
	var leftTBInfo=globalService.getLeftPaneTBData();
	self.createLeftPaneTB(leftTBInfo);
	self.bindLeftPaneEvents();
	self.bindRibbonTBEvents();
	
}
GlobalToolBarProcessor.prototype.netMdAccinit = function() {
	var self=this;
	var globalService = self.getGlobalService();
	var globalTbInfo = globalService.getnetMdAccRibbonTBData();
	self.createGlobalTB(globalTbInfo);
	var leftTBInfo=globalService.getnetMdAccLeftPaneTBData();
	self.createLeftPaneTB(leftTBInfo);
	self.bindnetMdAccLeftPaneEvents();
	self.bindnetMdAccRibbonTBEvents();
	
}
GlobalToolBarProcessor.prototype.createGlobalTB=function(globalTbInfo) {
	var globalTB = new GlobalToolBar(globalTbInfo);
	$j('.global-bar-tab .controlB').html(globalTB.result);		
}
GlobalToolBarProcessor.prototype.createLeftPaneTB=function(leftTBInfo) {
	var leftpaneTB = new leftpaneToolBar(leftTBInfo.buttons);
	$j('.leftmenu').html(leftpaneTB.result);		
}

GlobalToolBarProcessor.prototype.bindRibbonTBEvents=function() {
	var self=this;
	if($j(self.newnetlimsBranch)) {
		$j(self.newnetlimsBranch).die('click').live("click",function() {		
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			if(pageHandler.isnetlimsAccClassLoaded()!=true){
				var netlimsAccClass = new netlimsAccClassLoader();
				netlimsAccClass.load();
				pageHandler.setnetlimsAccClassLoaded(true);
			}
			var netlimsAccUI = new netlimsAccountUIStartup();			
			netlimsAccUI.createBranchModal(obj);
		});
	}
	
	if($j(self.netlimsdownLoad)) {
		$j(self.netlimsdownLoad).die('click').live("click",function() {		
			$j.ajax({
			type: 'GET',
			url: "/youNeverWait/ws/ui/lab/download",
			dataType: 'html',
			contentType: 'text/html',
			success: function (html) {
			var w = window.open();
			w.document.writeln(html);
			//w.location.reload();
			}
			});
		});
	}
	
	
}
GlobalToolBarProcessor.prototype.bindnetMdAccRibbonTBEvents=function() {
	var self=this;
	if($j(self.newnetmdBranch)) {
		$j(self.newnetmdBranch).die('click').live("click",function() {		
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			if(pageHandler.isnetmdAccClassLoaded()!=true){
				var netmdAccClass = new netmdAccClassLoader();
				netmdAccClass.load();
				pageHandler.setnetmdAccClassLoaded(true);
			}
			var netmdAccUI = new netmdAccountUIStartup();			
			netmdAccUI.createBranchModal(obj);
		});
	}
	
	if($j(self.netmddownLoad)) {
		$j(self.netmddownLoad).die('click').live("click",function() {		
			$j.ajax({
			type: 'GET',
			url: "/youNeverWait/ws/ui/netMd/download",
			dataType: 'html',
			contentType: 'text/html',
			success: function (html) {
			var w = window.open();
			w.document.writeln(html);
			//w.location.reload();
			}
			});
		});
	}
	
	
}
GlobalToolBarProcessor.prototype.bindLeftPaneEvents=function() {
	var self=this;
	
	if($j(self.netlimsAccbranchlist)) {
		$j(self.netlimsAccbranchlist).die('click').live("click",function() {	
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			if(pageHandler.isnetlimsAccClassLoaded()!=true){
				var netlimsAccClass = new netlimsAccClassLoader();
				netlimsAccClass.load();
				pageHandler.setnetlimsAccClassLoaded(true);
			}
			var netlimsAccUI = new netlimsAccountUIStartup();	
			netlimsAccUI.init();
		});
	}	
	
	if($j(self.netlimsAccbranchorders)) {
		$j(self.netlimsAccbranchorders).die('click').live("click",function() {
			 var obj=$j(this);
			commonMethodInvoker.removeErrors();
			$j('#pageToolBar-Container').html("")
			if(pageHandler.isnetlimsAccClassLoaded()!=true){
				var netlimsAccClass = new netlimsAccClassLoader();
				netlimsAccClass.load();
				pageHandler.setnetlimsAccClassLoaded(true);
			}
			var netlimsAccUI = new netlimsAccountUIStartup();	
			netlimsAccUI.branchorderlist(); 
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
GlobalToolBarProcessor.prototype.bindnetMdAccLeftPaneEvents=function() {
	var self=this;
	
	if($j(self.netmdAccbranchlist)) {
		$j(self.netmdAccbranchlist).die('click').live("click",function() {	
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			if(pageHandler.isnetmdAccClassLoaded()!=true){
				var netmdAccClass = new netmdAccClassLoader();
				netmdAccClass.load();
				pageHandler.setnetmdAccClassLoaded(true);
			}
			var netmdAccUI = new netmdAccountUIStartup();			
			netmdAccUI.init();
		});
	}	
	
	
	
}