
function NetrxAccSyncUI(BranchUIStartup,branchId) {
	this.netrxAccSycSetModal = " #netrxAccBranchSycSetModal";
	this.netrxAccSycSetPage =  " #newnetrxAccBranchsetSyncForm";
	this.radioCheck=this.netrxAccSycSetPage + " input[type=radio]:checked ";
	this.createButton = $j(this.netrxAccSycSetPage + " #btnnetrxAccbrchSubmit");
	this.enableRadio=$j(this.netrxAccSycSetPage + " #netrxAccbrchEnable");
	this.disableRadio=$j(this.netrxAccSycSetPage + " #netrxAccbrchDisable");
	this.selectHeader=$j(this.netrxAccSycSetPage + " #syctxtnetrxAccBranchEnTime");
	this.select=$j(this.netrxAccSycSetPage + " #NetrxAccBranchSyncTime");
	this.intervalHead=$j(this.netrxAccSycSetPage + " #syctxtnetrxAccBranchEnInr");
	this.interval=$j(this.netrxAccSycSetPage + " #NetrxAccBranchSyncInterval");
	this.errorHeader = $j(this.netrxAccSycSetPage + " #errorDivHeader");
	this.errorData = $j(this.netrxAccSycSetPage + " #errorDivNewNetrxAccBranchData");
	this.branchId=branchId;
	this.branchUIStartup = BranchUIStartup; 
	
}


 NetrxAccSyncUI.prototype.getBranchUIStartup = function() {
	return this.branchUIStartup;
}

NetrxAccSyncUI.prototype.getNetrxUIService = function() {
	var branchUIStartup = this.getBranchUIStartup();
	return branchUIStartup.getNetrxUIService();
}

NetrxAccSyncUI.prototype.init = function() {
	self =this;
	fillTimeList(self.select);
	commonMethodInvoker.validateNumber(self.interval);
	var syncdetails = self.getNetrxUIService();
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
NetrxAccSyncUI.prototype.bindEvents = function() {
self = this;	
	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}

NetrxAccSyncUI.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var syncData = self.getSyncDetail();
	if(validateNetmdAccBrchSync()) {
		var syncService = self.getNetrxUIService();
		var syncServiceResponse =syncService.syncBranchNetrx(syncData);
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
NetrxAccSyncUI.prototype.getSyncDetail = function() {
	var self=this;
	var intr=self.interval.val();
	var status=false;
	var radioselStatus=$j(self.radioCheck).val();
	if(radioselStatus=="enable"){status=true;}
	var BrchSync = new BranchSyncDTO();
	BrchSync.setenableSync(status);
	BrchSync.setsyncTime(parseInt(self.interval.val()));
	BrchSync.setnetrxBranchId(parseInt(self.branchId));
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
	var syncInterval=$j('#newnetrxAccBranchsetSyncForm #NetrxAccBranchSyncInterval');
	var OrgNameValid;
	OrgNameValid = checkNull(syncInterval,constants.SYNCINTERVALREQUIRED);
	return OrgNameValid;
} 	