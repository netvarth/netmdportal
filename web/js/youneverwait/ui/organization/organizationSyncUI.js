
function NetmdSyncUI(BranchUIStartup,netmdId) {
	this.netmdSycSetModal = " #netmdsyncmodal";
	this.netmdSycSetPage =  " #newAccBranchsetSyncForm";
	this.radioCheck=this.netmdSycSetPage + " input[type=radio]:checked ";
	this.createButton = $j(this.netmdSycSetPage + " #btnnetlimsAccBranchSubmit");
	this.enableRadio=$j(this.netmdSycSetPage + " #netlimsAccBrchEnable");
	this.disableRadio=$j(this.netmdSycSetPage + " #netlimsAccBrchDisable");
	this.selectHeader=$j(this.netmdSycSetPage + " #syctxtAccBranchEnTime");
	this.select=$j(this.netmdSycSetPage + " #NetlimsAccBranchSyncTime");
	this.intervalHead=$j(this.netmdSycSetPage + " #syctxtAccBranchEnInr");
	this.interval=$j(this.netmdSycSetPage + " #NetlimsAccBranchSyncInterval");
	this.errorHeader = $j(this.netmdSycSetPage + " #errorDivHeader");
	this.errorData = $j(this.netmdSycSetPage + " #errorDivNewNetlimsAccBranchData");
	this.netmdId=netmdId;
	this.branchUIStartup = BranchUIStartup; 
	
}


 NetmdSyncUI.prototype.getBranchUIStartup = function() {
	return this.branchUIStartup;
}

NetmdSyncUI.prototype.getNetmdUIService = function() {
	var branchUIStartup = this.getBranchUIStartup();
	return branchUIStartup.getNetmdUIService();
}

NetmdSyncUI.prototype.init = function() {
	self =this;
	fillTimeList(self.select);
	commonMethodInvoker.validateNumber(self.interval);
	var syncdetails = self.getNetmdUIService();
	var getSyncdetails=syncdetails.netmdSyncdata(self.netmdId);
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
NetmdSyncUI.prototype.bindEvents = function() {
self = this;	
	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}

NetmdSyncUI.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var syncData = self.getSyncDetail();
	if(validateNetmdSync()) {
		var syncService = self.getNetmdUIService();
		var syncServiceResponse =syncService.syncNetmd(syncData);
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
NetmdSyncUI.prototype.getSyncDetail = function() {
	var self=this;
	var intr=self.interval.val();
	var status=false;
	var radioselStatus=$j(self.radioCheck).val();
	if(radioselStatus=="enable"){status=true;}
	var BrchSync = new BranchSyncDTO();
	BrchSync.setenableSync(status);
	BrchSync.setsyncTime(parseInt(self.interval.val()));
	BrchSync.setnetmdId(parseInt(self.netmdId));
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