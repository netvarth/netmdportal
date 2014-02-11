

function SASyncStatusProcessor(syncStatusProcessor) {
	this.superAdminSycSetModal = " #superadminsyncmodal";
	this.superAdminSycSetPage =  " #newBranchForm";
	this.radioCheck=this.superAdminSycSetPage + " input[type=radio]:checked ";
	this.createButton = $j(this.superAdminSycSetPage + " #btnSubmit");
	this.enableRadio=$j(this.superAdminSycSetPage + " #Enable");
	this.disableRadio=$j(this.superAdminSycSetPage + " #Disable");
	this.selectHeader=$j(this.superAdminSycSetPage + " #syctxtEnTime");
	this.select=$j(this.superAdminSycSetPage + " #syncTime");
	this.intervalHead=$j(this.superAdminSycSetPage + " #syctxtEnInr");
	this.interval=$j(this.superAdminSycSetPage + " #syncInterval");
	this.errorHeader = $j(this.superAdminSycSetPage + " #errorDivHeader");
	this.errorData = $j(this.superAdminSycSetPage + " #errorDivNewNetlimsBranchData");
	this.superadminUIStartup = syncStatusProcessor; 
	
}

 SASyncStatusProcessor.prototype.getBranchUIStartup = function() {
	return this.superadminUIStartup;
}

SASyncStatusProcessor.prototype.getGlobalService = function() {
	var superadminUIStartup = this.getBranchUIStartup();
	return superadminUIStartup.getGlobalService();
}
 
SASyncStatusProcessor.prototype.init = function() {
	 self =this;
	fillTimeList(self.select);
	commonMethodInvoker.validateNumber(self.interval);
	var syncdetails = self.getGlobalService();
	var getSyncdetails=syncdetails.superAdminSyncdata();
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
SASyncStatusProcessor.prototype.bindEvents = function() {
self = this;	
	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}

SASyncStatusProcessor.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var syncData = self.getSyncDetail();
	if(validateSuperadminSync()) {
		var syncService = self.getGlobalService();
		var syncServiceResponse =syncService.syncSuperadmin(syncData);
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
SASyncStatusProcessor.prototype.getSyncDetail = function() {
	var self=this;
	var intr=self.interval.val();
	var status=false;
	var radioselStatus=$j(self.radioCheck).val();
	if(radioselStatus=="enable"){status=true;}
	var BrchSync = new BranchSyncDTO();
	BrchSync.setenableSync(status);
	BrchSync.setsyncTime(parseInt(self.interval.val()));
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
function validateSuperadminSync(){
	var syncInterval=$j('#newBranchForm #syncInterval');
	var OrgNameValid;
	OrgNameValid = checkNull(syncInterval,constants.SYNCINTERVALREQUIRED);
	return OrgNameValid;
} 	 