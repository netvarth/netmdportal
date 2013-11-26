
function NetlimsAccSyncUI(BranchUIStartup,branchId) {
	this.netlimsAccSycSetModal = " #netlimsAccSycSetModal";
	this.netlimsAccSycSetPage =  " #newAccBranchsetSyncForm";
	this.radioCheck=this.netlimsAccSycSetPage + " input[type=radio]:checked ";
	this.createButton = $j(this.netlimsAccSycSetPage + " #btnnetlimsAccBranchSubmit");
	this.enableRadio=$j(this.netlimsAccSycSetPage + " #netlimsAccBrchEnable");
	this.disableRadio=$j(this.netlimsAccSycSetPage + " #netlimsAccBrchDisable");
	this.selectHeader=$j(this.netlimsAccSycSetPage + " #syctxtAccBranchEnTime");
	this.select=$j(this.netlimsAccSycSetPage + " #NetlimsAccBranchSyncTime");
	this.intervalHead=$j(this.netlimsAccSycSetPage + " #syctxtAccBranchEnInr");
	this.interval=$j(this.netlimsAccSycSetPage + " #NetlimsAccBranchSyncInterval");
	this.errorHeader = $j(this.netlimsAccSycSetPage + " #errorDivHeader");
	this.errorData = $j(this.netlimsAccSycSetPage + " #errorDivNewNetlimsAccBranchData");
	this.branchId=branchId;
	this.branchUIStartup = BranchUIStartup; 
	
}


 NetlimsAccSyncUI.prototype.getBranchUIStartup = function() {
	return this.branchUIStartup;
}

NetlimsAccSyncUI.prototype.getNetlimsUIService = function() {
	var branchUIStartup = this.getBranchUIStartup();
	return branchUIStartup.getNetlimsUIService();
}

NetlimsAccSyncUI.prototype.init = function() {
	self =this;
	fillTimeList(self.select);
	commonMethodInvoker.validateNumber(self.interval);
	var syncdetails = self.getNetlimsUIService();
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
NetlimsAccSyncUI.prototype.bindEvents = function() {
self = this;	
	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}

NetlimsAccSyncUI.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var syncData = self.getSyncDetail();
	if(validateNetlimsAccBrchSync()) {
		var syncService = self.getNetlimsUIService();
		var syncServiceResponse =syncService.syncBranchNetlims(syncData);
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
NetlimsAccSyncUI.prototype.getSyncDetail = function() {
	var self=this;
	var intr=self.interval.val();
	var status=false;
	var radioselStatus=$j(self.radioCheck).val();
	if(radioselStatus=="enable"){status=true;}
	var BrchSync = new NetlimsAccBrchSyncDTO();
	BrchSync.setenableSync(status);
	BrchSync.setsyncTime(parseInt(self.interval.val()));
	BrchSync.setlabBranchId(parseInt(self.branchId));
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
function validateNetlimsAccBrchSync(){
	var syncInterval=$j('#newAccBranchsetSyncForm #NetlimsAccBranchSyncInterval');
	var OrgNameValid;
	OrgNameValid = checkNull(syncInterval,constants.SYNCINTERVALREQUIRED);
	return OrgNameValid;
} 	