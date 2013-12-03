
function NetmdAccSyncUI(BranchUIStartup,branchId) {
	this.netmdAccSycSetModal = " #netMdAccSycSetModal";
	this.netmdAccSycSetPage =  " #newnetmdAccBranchsetSyncForm";
	this.radioCheck=this.netmdAccSycSetPage + " input[type=radio]:checked ";
	this.createButton = $j(this.netmdAccSycSetPage + " #btnnetmdAccbrchSubmit");
	this.enableRadio=$j(this.netmdAccSycSetPage + " #netmdAccbrchEnable");
	this.disableRadio=$j(this.netmdAccSycSetPage + " #netmdAccbrchDisable");
	this.selectHeader=$j(this.netmdAccSycSetPage + " #syctxtnetmdAccBranchEnTime");
	this.select=$j(this.netmdAccSycSetPage + " #NetmdAccBranchSyncTime");
	this.intervalHead=$j(this.netmdAccSycSetPage + " #syctxtnetmdAccBranchEnInr");
	this.interval=$j(this.netmdAccSycSetPage + " #NetmdAccBranchSyncInterval");
	this.errorHeader = $j(this.netmdAccSycSetPage + " #errorDivHeader");
	this.errorData = $j(this.netmdAccSycSetPage + " #errorDivNewNetmdAccBranchData");
	this.branchId=branchId;
	this.branchUIStartup = BranchUIStartup; 
	
}


 NetmdAccSyncUI.prototype.getBranchUIStartup = function() {
	return this.branchUIStartup;
}

NetmdAccSyncUI.prototype.getNetmdUIService = function() {
	var branchUIStartup = this.getBranchUIStartup();
	return branchUIStartup.getNetmdUIService();
}

NetmdAccSyncUI.prototype.init = function() {
	self =this;
	fillTimeList(self.select);
	commonMethodInvoker.validateNumber(self.interval);
	var syncdetails = self.getNetmdUIService();
	var getSyncdetails=syncdetails.syncdata(self.branchId);
	//alert(JSON.stringify(getSyncdetails));
	var radioStatus=getSyncdetails.enableSync;
	
	if(radioStatus==false)
		{self.disableRadio.attr('checked', true);}
	else{self.enableRadio.attr('checked', true);}


	if(getSyncdetails.syncFreqType)
		self.select.val(getSyncdetails.syncFreqType);
	else
		self.select.val();
	if(getSyncdetails.syncTime)
		self.interval.val(getSyncdetails.syncTime);
	else
		self.interval.val();	
	
	
	var flag=$j(self.radioCheck).val();
	if(flag!="disable"){
		self.selectHeader.show();
		self.intervalHead.show();
	}
	
	
	self.enableRadio.die('click').live('click',function(){
		self.selectHeader.show();
		self.intervalHead.show();
	});	
	
	self.disableRadio.die('click').live('click',function(){
		self.selectHeader.hide();
		self.intervalHead.hide();
	});	
	self.bindEvents();
} 
NetmdAccSyncUI.prototype.bindEvents = function() {
self = this;	
	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}

NetmdAccSyncUI.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var syncData = self.getSyncDetail();
	if(validateNetmdAccBrchSync()) {
		var syncService = self.getNetmdUIService();
		var syncServiceResponse =syncService.syncBranchNetmd(syncData);
		//alert(JSON.stringify(syncServiceResponse));
		if(syncServiceResponse.success==true){
			var messge=syncServiceResponse.msg;
		if(syncServiceResponse.msg==null){
			showTip("Set Synchronisation Successfully");
			}else{
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, messge);
			} 
		}
	else {
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(syncServiceResponse.error));
		} 
	} 
}
NetmdAccSyncUI.prototype.getSyncDetail = function() {
	var self=this;
	var intr=self.interval.val();
	var status=false;
	var radioselStatus=$j(self.radioCheck).val();
	if(radioselStatus=="enable"){status=true;}
	var BrchSync = new BranchSyncDTO();
	BrchSync.setenableSync(status);
	BrchSync.setsyncTime(parseInt(self.interval.val()));
	BrchSync.setnetmdBranchId(parseInt(self.branchId));
	BrchSync.setsyncFreqType(self.select.val());
	return BrchSync;
}	
function fillTimeList(controlobj)
	{
		var list=["daily","hourly","minutes"];
		$j(list).each(function (Index, List) {
			var freqlist=List;
			$j(controlobj).append('<option  value="'+freqlist+'">'+freqlist+'</option>');
		});

		
	} 
function validateNetmdAccBrchSync(){
	var syncInterval=$j('#newnetmdAccBranchsetSyncForm #NetmdAccBranchSyncInterval');
	var OrgNameValid;
	OrgNameValid = checkNull(syncInterval,constants.SYNCINTERVALREQUIRED);
	return OrgNameValid;
} 	