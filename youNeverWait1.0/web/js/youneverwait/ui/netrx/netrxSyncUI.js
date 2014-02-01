
function NetrxSyncUI(BranchUIStartup,netrxId) {
	this.netmdSycSetModal = " #netrxsyncmodal";
	this.netrxSycSetPage =  " #newAccBranchsetSyncForm";
	this.radioCheck=this.netrxSycSetPage + " input[type=radio]:checked ";
	this.createButton = $j(this.netrxSycSetPage + " #btnnetlimsAccBranchSubmit");
	this.enableRadio=$j(this.netrxSycSetPage + " #netlimsAccBrchEnable");
	this.disableRadio=$j(this.netrxSycSetPage + " #netlimsAccBrchDisable");
	this.selectHeader=$j(this.netrxSycSetPage + " #syctxtAccBranchEnTime");
	this.select=$j(this.netrxSycSetPage + " #NetlimsAccBranchSyncTime");
	this.intervalHead=$j(this.netrxSycSetPage + " #syctxtAccBranchEnInr");
	this.interval=$j(this.netrxSycSetPage + " #NetlimsAccBranchSyncInterval");
	this.errorHeader = $j(this.netrxSycSetPage + " #errorDivHeader");
	this.errorData = $j(this.netrxSycSetPage + " #errorDivNewNetlimsAccBranchData");
	this.netrxId=netrxId;
	this.branchUIStartup = BranchUIStartup; 
	
}


 NetrxSyncUI.prototype.getBranchUIStartup = function() {
	return this.branchUIStartup;
}

NetrxSyncUI.prototype.getNetrxUIService = function() {
	var branchUIStartup = this.getBranchUIStartup();
	return branchUIStartup.getNetrxUIService();
}

NetrxSyncUI.prototype.init = function() {
	self =this;
	fillTimeList(self.select);
	commonMethodInvoker.validateNumber(self.interval);
	var syncdetails = self.getNetrxUIService();
	var getSyncdetails=syncdetails.netrxSyncdata(self.netrxId);
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
NetrxSyncUI.prototype.bindEvents = function() {
self = this;	
	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}

NetrxSyncUI.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var syncData = self.getSyncDetail();
	if(validateNetmdSync()) {
		var syncService = self.getNetrxUIService();
		var syncServiceResponse =syncService.syncNetrx(syncData);
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
NetrxSyncUI.prototype.getSyncDetail = function() {
	var self=this;
	var intr=self.interval.val();
	var status=false;
	var radioselStatus=$j(self.radioCheck).val();
	if(radioselStatus=="enable"){status=true;}
	var BrchSync = new BranchSyncDTO();
	BrchSync.setenableSync(status);
	BrchSync.setsyncTime(parseInt(self.interval.val()));
	BrchSync.setnetrxId(parseInt(self.netrxId));
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
function validateNetmdSync(){
	var syncInterval=$j('#newAccBranchsetSyncForm #NetlimsAccBranchSyncInterval');
	var OrgNameValid;
	OrgNameValid = checkNull(syncInterval,constants.SYNCINTERVALREQUIRED);
	return OrgNameValid;
} 	