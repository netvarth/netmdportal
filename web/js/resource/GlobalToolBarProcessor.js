function GlobalToolBarProcessor() {
	this.globalService = new GlobalServiceImpl();
	this.netlimsAccbranchlist="#leftPaneNetlimsBranch";
	this.netlimsAccbranchorders="#leftPaneNetlimsOrders";
	this.netlimsAccbranchsettings="#leftPaneNetlimsSettings";
	this.newBranch="#ribbonNewNetlimsBranch";
	this.netlimsdownLoad="#ribbonNetlimsDownload";
	/* this.referralButton ="#leftPaneDoctor";
	this.dashboardButton="#leftPaneDashBoard";
	this.orderButton="#leftPaneAllOrders";
	this.facilityButton="#leftPaneFacilities";
	this.agentButton="#leftPaneAgents";
	this.blanketOrderButton="#leftPaneBlanketOrder";
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
	if($j(self.newBranch)) {
		$j(self.newBranch).die('click').live("click",function() {		
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
	
	
	
	/*if($j(self.editRateButton)) {
		$j(self.editRateButton).die('click').live("click",function() {	
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			if(pageHandler.isTestPackageClassLoaded()!=true){
				var testPackageClass = new TestPackageClassLoader();
				testPackageClass.load();
				pageHandler.setTestPackageClassLoaded(true);
			}
			var testPackageUI = new TestPackageUIStartup();
			testPackageUI.createRateModal(obj);
		});
	}
	 */
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
	
	
	/*if($j(self.dashboardButton)){
		$j(self.dashboardButton).die('click').live("click",function() {	
			commonMethodInvoker.removeErrors();
			pageHandler.setHomePage(constants.DASHBOARDPAGE);
			self.reset();
			var dashboardUI = new DashboardUIStartup();
			dashboardUI.init();
		});
	}
	
	if($j(self.facilityButton))	{
		$j(self.facilityButton).die('click').live("click",function() {
			commonMethodInvoker.removeErrors();
			pageHandler.setHomePage(constants.FACILITYPAGE);
			self.reset();
			if(pageHandler.isFacilityClassLoaded()!=true){
				var facilityClass = new FacilityClassLoader();
				facilityClass.load();
				pageHandler.setFacilityClassLoaded(true);
			}
			var facilityUI = new FacilityUIStartup();			
			facilityUI.init();
		});
	}
	if($j(self.orderButton)){
		$j(self.orderButton).die('click').live("click",function() {
			commonMethodInvoker.removeErrors();
			pageHandler.setHomePage(constants.ORDERPAGE);
			self.reset();
			if(pageHandler.isOrderClassLoaded()!=true){
				var orderClass = new OrderClassLoader();
				orderClass.load();
				pageHandler.setOrderClassLoaded(true);
			}
			var orderUI = new OrderUIStartup();			
			orderUI.init();
		});
	}	
	
	 */
}