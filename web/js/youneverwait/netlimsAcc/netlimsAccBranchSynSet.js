fillTimeList("#NetlimsAccBranchSyncTime");
validateNumber("#newAccBranchsetSyncForm #NetlimsAccBranchSyncInterval");
var netlmsBrchId="";

function getNetlimsAccBrchId(branchId){
	netlmsBrchId=branchId;
	var getSyncdetails=getRequestData('/youNeverWait/ws/ui/lab/getBranchSyncDetails/'+netlmsBrchId);
	var radioStatus=getSyncdetails.enableSync;
	
	if(radioStatus==false)
		{$j('#netlimsAccBrchDisable').attr('checked', true);}
	else{$j('#netlimsAccBrchEnable').attr('checked', true);}


	if(getSyncdetails.syncFreqType)
		$j("#newAccBranchsetSyncForm #NetlimsAccBranchSyncTime ").val(getSyncdetails.syncFreqType);
	else
		$j("#newAccBranchsetSyncForm #NetlimsAccBranchSyncTime ").val("Nil");
	if(getSyncdetails.syncTime)
		$j("#newAccBranchsetSyncForm #NetlimsAccBranchSyncInterval ").val(getSyncdetails.syncTime);
	else
		$j("#newAccBranchsetSyncForm #NetlimsAccBranchSyncInterval ").val();	
	
	var flag=$j('#newAccBranchsetSyncForm input[type=radio]:checked').val();	
	if(flag!="disable"){
		$j('#syctxtAccBranchEnTime').show();
		$j('#syctxtAccBranchEnInr').show();
	}
}

$j('#netlimsAccBrchEnable').die('click').live("click",function() {
		$j('#syctxtAccBranchEnTime').show();
		$j('#syctxtAccBranchEnInr').show();
	});

$j('#netlimsAccBrchDisable').die('click').live("click",function() {
		$j('#syctxtAccBranchEnTime').hide();
		$j('#syctxtAccBranchEnInr').hide();
		
	});
	
$j("#newAccBranchsetSyncForm #btnnetlimsAccBranchSubmit").die('click').live('click',function(){
	removeErrors();	
	 if(validateNetlimsAccBrchSync())
		{
	var response = submitNetlimsAccBrchSyncDetailsInfo();
	 if(response.success==true){
			var messge=response.msg;
		if(response.msg==null){
			showTip("Set Synchronisation Successfully");
			}else{
			updateTipsNew(messge,$j('#newAccBranchsetSyncForm #errorDivNewNetlimsAccBranchData'),$j('#newAccBranchsetSyncForm #errorDivHeader'));
			} 
		}
	else {
			updateTipsNew(getErrorName(response.error),$j('#newAccBranchsetSyncForm #errorDivNewNetlimsAccBranchData'),$j('#newAccBranchsetSyncForm #errorDivHeader'));
		} 
		} 
	});	

function submitNetlimsAccBrchSyncDetailsInfo(){
	var resultJson = createNetlimsAccBrchSyncDetailsJson();
	//alert(resultJson);
	var response = postdataToServer(constant_newnetlimsAccBrchSync_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}	

function createNetlimsAccBrchSyncDetailsJson()
	{
	var rsd=$j('#newAccBranchsetSyncForm  input[type=radio]:checked').val();
	//alert(rsd);
	 if($j('#newAccBranchsetSyncForm input[type=radio]:checked').val()=="enable"){
		 
		var syncDetails = '{"enableSync":'+"true"+',';
			syncDetails += '"syncTime":'+$j("#newAccBranchsetSyncForm #NetlimsAccBranchSyncInterval").val()+',';
			syncDetails += '"labBranchId":'+netlmsBrchId+',';
			syncDetails +='"syncFreqType":"' +$j("#newAccBranchsetSyncForm #NetlimsAccBranchSyncTime").val() + '"}';
		return syncDetails;
		
	}
	else{
		var syncDetails = '{"enableSync":'+"false"+',';
			syncDetails += '"labBranchId":'+netlmsBrchId+ '}';
			return syncDetails;
		
		}   
	
	}
	
function validateNetlimsAccBrchSync(){
	var syncInterval=$j('#newAccBranchsetSyncForm #NetlimsAccBranchSyncInterval');
	var OrgNameValid;
	OrgNameValid = checkNull(syncInterval,constants_SyncintervalRequired);
	return OrgNameValid;
} 	


	
function fillTimeList(controlobj)
	{
		var list=["daily","hourly","minutes"];
		$j(list).each(function (Index, List) {
			var freqlist=List;
			$j(controlobj).append('<option  value="'+freqlist+'">'+freqlist+'</option>');
		});

		
	} 