function netlimsBranchUIStartup(labId) {
	this.pgTableName = "#branchNetlimsAcc";
	this.pgTableContainer="#branchNetlimsAccTableCont"; 
	this.pgTableNameorderList="#branchOrderNetlimsAcc";
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#BRANCHPTBContainer #btn_new_ptb_id');
	this.ptbNetlimslist=$j('#BRANCHPTBContainer #btn_back_ptb_id');
	this.ptbView=$j('#BRANCHPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#BRANCHPTBContainer #btn_delete_ptb_id');
	this.branchhealth=$j('#BRANCHPTBContainer #btn_health_ptb_id');
	this.ptbSync=$j('#BRANCHPTBContainer #btn_change_ptb_id');
	this.healthForm='#newHealthForm';
	this.branchid=this.healthForm +  ' #branchid';
	this.criticalCpuLevel=this.healthForm +  '  #criticalCpuLevel';
	this.frequencyType=this.healthForm +  ' #frequencyType';
	this.selectfrequencyType=this.healthForm +  ' #selectfrequencyType';
	this.selectfreqtypeDiv=this.healthForm +  ' #selfrequencyType';
	this.texfrequencyTypeDiv=this.healthForm +  ' #texfrequencyType';
	this.intervalTime=this.healthForm +  ' #intervalTime';
	this.criticalMemoryLevel=this.healthForm +  ' #criticalMemoryLevel';
	this.criticalHardDiskSpaceLevel=this.healthForm +  '  #criticalHardDiskSpaceLevel';
	this.inputFields=this.healthForm  +  "  :input[type=text]";
	this.healthbtnedit=this.healthForm  +  '  #healthbtnedit';
	this.healthbtnDone=this.healthForm  +  ' #healthbtnDone';
	this.healthbtnCancel=this.healthForm  +  '  #healthbtnCancel';
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.netlimsId=labId;
	this.pgTableRowClass = this.pgTableName + ' .branchNetlimsAccIdCol';
	this.exp = new ExpressionListDTO();
	this.netlimsAccService = new NetlimsbranchServiceImpl();
	this.listUrl = constants.NETLIMSLABBRANCHLISTURL;
	this.netlimsAccTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.netlimsAccService,this.exp);
	this.viewNetlimsBranchUI = new ViewNetlimsBranchUI(this);
}

netlimsBranchUIStartup.prototype.setnetlimsAccTableNavigator = function(netlimsAccTableNavigator) {
	this.netlimsAccTableNavigator = netlimsAccTableNavigator;
}
netlimsBranchUIStartup.prototype.getnetlimsAccTableNavigator = function() {
	return this.netlimsAccTableNavigator;
}
netlimsBranchUIStartup.prototype.getViewNetlimsBranchUI = function() {
	return this.viewNetlimsBranchUI;
}
netlimsBranchUIStartup.prototype.getBranchId = function() {
	return this.BranchId;
}
netlimsBranchUIStartup.prototype.getlabId = function() {
	return this.netlimsId;
}
netlimsBranchUIStartup.prototype.setBranchId = function(BranchId) {
	this.BranchId=BranchId;
}
netlimsBranchUIStartup.prototype.getNetlimsUIService = function() {
	return this.netlimsAccService;
}


//Set the page title of the order ui page
netlimsBranchUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

//Return the selected Order Id from the list table
netlimsBranchUIStartup.prototype.getSelectedbranchId = function () {
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

netlimsBranchUIStartup.prototype.init = function(netlimsId) {
	var self = this;
	var selName="labId";
	var selValue=self.netlimsId;
	var selOperator = "eq";
	self.setPageTitle(constants.BRANCHLIST);
	var exp = new ExpressionListDTO();
	var expr = new ExpressionDTO(selName,selValue,selOperator);
	exp.add(expr);
	var netlimsAccTableNavigator = self.getnetlimsAccTableNavigator();
	netlimsAccTableNavigator.setExp(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.create(constants.NETLIMSBRANCH, constants.NETLIMSBRNCHPAGEBRANCHJSON); //Create the Page tool Bar for Order
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.NETLIMSACCBRANCHLISTJSON);//Create Table for Listing Order
	dataTableProcessor.setCustomTable(self.pgTableName);
	netlimsAccTableNavigator.list();
	self.bindEvents();
	pageHandler.setActivePage(self);
}



netlimsBranchUIStartup.prototype.createBranchModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETLIMSACCBRANCHJSON,constants.NEWBRANCHMODAL);		
	openModalBox(obj,constants.NEWBRANCHMODAL);
	var newBranchUI = new NewBranchUI(self);
	newBranchUI.init();
	return newBranchUI;
}

netlimsBranchUIStartup.prototype.createSyncModal = function(obj,branchId) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NETLIMSACCSYNCJSON,constants.NETLIMSACCSYNCMODAL);		
	openModalBox(obj,constants.NETLIMSACCSYNCMODAL);
	var netlimsAccSyncUI = new NetlimsAccSyncUI(self,branchId);
	netlimsAccSyncUI.init();
	return netlimsAccSyncUI; 
}

netlimsBranchUIStartup.prototype.createHealthmonitorModal = function(obj,branchId) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.GRAPHHEALTHMONITORJSON,constants.HEALTHMONITORMODAL);		
	openModalBox(obj,constants.HEALTHMONITORMODAL);
	
	$j.getScript(constants.HEALTHMONITORPATH).done(function(script, textStatus) {
	})
	self.viewHealthmonitorDetails(branchId);
	self.healthMonitorButtonEvents();
	//fillFrequencyList("#selectfrequencyType");
}

netlimsBranchUIStartup.prototype.viewHealthmonitorDetails = function(branchId) {
	var self = this;
	commonMethodInvoker.removeErrors();
	var NetlimsUIService = self.getNetlimsUIService();
	var healtMontr = NetlimsUIService.viewHealthmonitorDetails(branchId);
	if(!healtMontr.errorMessage) {
		$j(self.criticalCpuLevel).val(healtMontr.criticalCpuLevel);
		$j(self.criticalMemoryLevel).val(healtMontr.criticalMemoryLevel);
		$j(self.branchid).val(healtMontr.branchId);
		$j(self.frequencyType).val(healtMontr.freqType);
		$j(self.intervalTime).val(healtMontr.intervalTime);
		$j(self.criticalHardDiskSpaceLevel).val(healtMontr.criticalHardDiskSpaceLevel);
	} else 
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData,healtMontr.errorMessage);
	
}

netlimsBranchUIStartup.prototype.healthMonitorButtonEvents = function() {
	var self = this;
	commonMethodInvoker.removeErrors();
	
	$j(self.healthbtnDone).die('click').live('click',function(){		
		var healthmonitorPass=self.getHealthmonitorRequest();
		var netlimsUIService = self.getNetlimsUIService();
		var response = netlimsUIService.updateHealthmonitorDetails(healthmonitorPass);
		//alert(JSON.stringify(response));
		if(response.success==true) {
		$j(self.inputFields).addClass('newBox');
		$j(self.inputFields).attr('readonly','readonly');	
		$j(self.healthbtnDone).hide();
		$j(self.healthbtnCancel).hide();
		$j(self.healthbtnedit).show();
		$j(self.texfrequencyTypeDiv).show();
		$j(self.selectfreqtypeDiv).hide();
		var branchId = $j(self.branchid).val();
		self.viewHealthmonitorDetails(branchId);
		showTip("Healthmonitor updated successfully");					
	}	
	else
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(response.error));
		
		
	});
	$j(self.healthbtnedit).die('click').live('click',function(){
		commonMethodInvoker.validateNumber(self.criticalCpuLevel);
		commonMethodInvoker.validateNumber(self.criticalMemoryLevel);
		commonMethodInvoker.validateNumber(self.criticalHardDiskSpaceLevel);
		commonMethodInvoker.validateNumber(self.intervalTime);
		$j(self.healthbtnedit).hide();
		$j(self.healthbtnDone).show();
		$j(self.healthbtnCancel).show();
		$j(self.inputFields).removeClass('newBox'); // make box 
		$j(self.inputFields).removeAttr('readonly');
		$j(self.branchid).addClass('newBox');
		$j(self.branchid).attr('readonly','readonly');
		$j(self.texfrequencyTypeDiv).hide();
		$j(self.selectfreqtypeDiv).show();
		$j(self.selectfrequencyType).empty();
		var fillfreq=self.fillFrequencyList(self.selectfrequencyType);
	});
	$j(self.healthbtnCancel).die('click').live('click',function(){
		$j(self.inputFields).addClass('newBox');
		$j(self.inputFields).attr('readonly','readonly');	
		$j(self.texfrequencyTypeDiv).show();
		$j(self.selectfreqtypeDiv).hide();
		$j(self.healthbtnDone).hide();
		$j(self.healthbtnCancel).hide();
		$j(self.healthbtnedit).show();
		var branchId=$j(self.branchid).val();
		 self.viewHealthmonitorDetails(branchId);
	});
}
netlimsBranchUIStartup.prototype.fillFrequencyList = function(controlobj) {
	var self = this;
	var list=["daily","hourly","minutes"];
		$j(list).each(function (Index, List) {
			var freqlist=List;
			$j(controlobj).append('<option  value="'+freqlist+'">'+freqlist+'</option>');
		});
}
netlimsBranchUIStartup.prototype.getHealthmonitorRequest = function() {
	var self=this;
	var health = new HealthMonitorDTO();
	health.setbranchId($j(self.branchid).val());
	health.setcriticalCpuLevel($j(self.criticalCpuLevel).val());
	health.setcriticalMemoryLevel($j(self.criticalMemoryLevel).val());
	health.setcriticalHardDiskSpaceLevel($j(self.criticalHardDiskSpaceLevel).val());
	health.setfreqType($j(self.selectfrequencyType).val());
	health.setintervalTime($j(self.intervalTime).val());
	health.setbranchName();
	health.sethealthMonitorList();
	return health;
}
netlimsBranchUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbNetlimslist.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
			if(pageHandler.isnetlimsClassLoaded()!=true){
				var netlimsClass = new netlimsClassLoader();
				netlimsClass.load();
				pageHandler.setnetlimsClassLoaded(true);
			}
			var netlimsUI = new netlimsUIStartup();	
			netlimsUI.init();
	});	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var branchId=self.getSelectedbranchId(self.pgTableName);
		if(branchId!="") {
			var viewNetlimsBranchUI = self.getViewNetlimsBranchUI();
			viewNetlimsBranchUI.init(branchId);
		}	
	});
	self.ptbSync.die('click').live('click',function() {
		removeErrors();
		var branchId=self.getSelectedbranchId(self.pgTableName);
		if(branchId!="") {
			var obj=$j(this);
			self.createSyncModal(obj,branchId);
		}	
	});
	self.branchhealth.die('click').live('click',function() {
		removeErrors();
		var branchId=self.getSelectedbranchId(self.pgTableName);
		if(branchId!="") {
			var obj=$j(this);
			self.createHealthmonitorModal(obj,branchId);
		}	
	});
	self.ptbDelete.die('click').live('click',function() {
		removeErrors();
		var branchId=self.getSelectedbranchId(self.pgTableName);
		if(branchId!="") {
			var netlimsUIService = self.getNetlimsUIService();
			var netlimsResponse = netlimsUIService.viewNetlimsBranchDetails(branchId);
				var netlimsBranchDel=netlimsResponse.branch;	
				 var netlimsDelResponse = netlimsUIService.deleteNetlimsBranch(netlimsBranchDel);
				//alert(JSON.stringify(netlimsDelResponse));	
				if(netlimsDelResponse.error==null) {
					showTip(constants.NETLIMSBRANCHDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netlimsDelResponse.error));
				}
				var netlimsAccTableNavigator = self.getnetlimsAccTableNavigator();
				netlimsAccTableNavigator.list();
			 
		}	
	});
	
	
	
	
}
netlimsBranchUIStartup.prototype.bindEvents = function() {
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
			var viewNetlimsBranchUI = parent.getViewNetlimsBranchUI();
			viewNetlimsBranchUI.init(branchId);
		}	
	}); 
}



