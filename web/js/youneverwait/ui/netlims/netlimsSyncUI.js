
function NetlimsSyncUI(BranchUIStartup,netlimsId) {
	this.netlimsSycSetModal = " #netlimssyncmodal";
	this.netlimsSycSetPage =  " #newAccBranchsetSyncForm";
	this.radioCheck=this.netlimsSycSetPage + " input[type=radio]:checked ";
	this.createButton = $j(this.netlimsSycSetPage + " #btnnetlimsAccBranchSubmit");
	this.enableRadio=$j(this.netlimsSycSetPage + " #netlimsAccBrchEnable");
	this.disableRadio=$j(this.netlimsSycSetPage + " #netlimsAccBrchDisable");
	this.selectHeader=$j(this.netlimsSycSetPage + " #syctxtAccBranchEnTime");
	this.select=$j(this.netlimsSycSetPage + " #NetlimsAccBranchSyncTime");
	this.intervalHead=$j(this.netlimsSycSetPage + " #syctxtAccBranchEnInr");
	this.interval=$j(this.netlimsSycSetPage + " #NetlimsAccBranchSyncInterval");
	this.errorHeader = $j(this.netlimsSycSetPage + " #errorDivHeader");
	this.errorData = $j(this.netlimsSycSetPage + " #errorDivNewNetlimsAccBranchData");
	this.netlimsId=netlimsId;
	this.branchUIStartup = BranchUIStartup; 
	
}


 NetlimsSyncUI.prototype.getBranchUIStartup = function() {
	return this.branchUIStartup;
}

NetlimsSyncUI.prototype.getNetlimsUIService = function() {
	var branchUIStartup = this.getBranchUIStartup();
	return branchUIStartup.getNetlimsUIService();
}

NetlimsSyncUI.prototype.init = function() {
	self =this;
	fillTimeList(self.select);
	commonMethodInvoker.validateNumber(self.interval);
	var syncdetails = self.getNetlimsUIService();
	var getSyncdetails=syncdetails.netlimsSyncdata(self.netlimsId);
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
NetlimsSyncUI.prototype.bindEvents = function() {
self = this;	
	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}

NetlimsSyncUI.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var syncData = self.getSyncDetail();
	if(validateNetlimsSync()) {
		var syncService = self.getNetlimsUIService();
		var syncServiceResponse =syncService.syncNetlims(syncData);
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
NetlimsSyncUI.prototype.getSyncDetail = function() {
	var self=this;
	var intr=self.interval.val();
	var status=false;
	var radioselStatus=$j(self.radioCheck).val();
	if(radioselStatus=="enable"){status=true;}
	var BrchSync = new BranchSyncDTO();
	BrchSync.setenableSync(status);
	BrchSync.setsyncTime(parseInt(self.interval.val()));
	BrchSync.setlabId(parseInt(self.netlimsId));
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
function validateNetlimsSync(){
	var syncInterval=$j('#newAccBranchsetSyncForm #NetlimsAccBranchSyncInterval');
	var OrgNameValid;
	OrgNameValid = checkNull(syncInterval,constants.SYNCINTERVALREQUIRED);
	return OrgNameValid;
} 	