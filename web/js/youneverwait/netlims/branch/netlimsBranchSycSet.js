fillTimeList("#NetlimsBranchSyncTime");
validateNumber("#newBranchsetSyncForm #NetlimsBranchSyncInterval");
var netlmsBrchId="";

function getNetlimsBrchId(branchId){
	netlmsBrchId=branchId;
	var getSyncdetails=getRequestData('/youNeverWait/ws/ui/lab/getBranchSyncDetails/'+netlmsBrchId);
	var radioStatus=getSyncdetails.enableSync;
	
	if(radioStatus==false)
		{$j('#netlimsBrchDisable').attr('checked', true);}
	else{$j('#netlimsBrchEnable').attr('checked', true);}


	if(getSyncdetails.syncFreqType)
		$j("#newBranchsetSyncForm #NetlimsBranchSyncTime ").val(getSyncdetails.syncFreqType);
	else
		$j("#newBranchsetSyncForm #NetlimsBranchSyncTime ").val("Nil");
	if(getSyncdetails.syncTime)
		$j("#newBranchsetSyncForm #NetlimsBranchSyncInterval ").val(getSyncdetails.syncTime);
	else
		$j("#newBranchsetSyncForm #NetlimsBranchSyncInterval ").val();	
	
	var flag=$j('input[type=radio]:checked').val();	
	if(flag!="disable"){
		$j('#syctxtBranchEnTime').show();
		$j('#syctxtBranchEnInr').show();
	}
}

$j('#netlimsBrchEnable').die('click').live("click",function() {
		$j('#syctxtBranchEnTime').show();
		$j('#syctxtBranchEnInr').show();
	});

$j('#netlimsBrchDisable').die('click').live("click",function() {
		$j('#syctxtBranchEnTime').hide();
		$j('#syctxtBranchEnInr').hide();
		
	});
	
$j("#newBranchsetSyncForm #btnnetlimsBranchSubmit").die('click').live('click',function(){
	//alert("click");
	removeErrors();	
	if(validateNetlimsBrchSync())
		{
	var response = submitNetlimsBrchSyncDetailsInfo();
	if(response.success==true){
			showTip("Set Synchronisation Successfully");
		}
	else {
			updateTipsNew(getErrorName(response.error),$j('#newBranchsetSyncForm #errorDivNewNetlimsBranchData'),$j('#newBranchsetSyncForm #errorDivHeader'));
		}
		}
	});	

function submitNetlimsBrchSyncDetailsInfo(){
	var resultJson = createNetlimsBrchSyncDetailsJson();
	//alert(resultJson);
	var response = postdataToServer(constant_newnetlimsBrchSync_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}	

function createNetlimsBrchSyncDetailsJson()
	{
	var rsd=$j('input[type=radio]:checked').val();
	//alert(rsd);
	 if($j('input[type=radio]:checked').val()=="enable"){
		 
		var syncDetails = '{"enableSync":'+"true"+',';
			syncDetails += '"syncTime":'+$j("#newBranchsetSyncForm #NetlimsBranchSyncInterval").val()+',';
			syncDetails += '"labBranchId":'+netlmsBrchId+',';
			syncDetails +='"syncFreqType":"' +$j("#newBranchsetSyncForm #NetlimsBranchSyncTime").val() + '"}';
		return syncDetails;
		
	}
	else{
		var syncDetails = '{"enableSync":'+"false"+',';
			syncDetails += '"labBranchId":'+netlmsBrchId+ '}';
			return syncDetails;
		
		}   
	
	}
	
function validateNetlimsBrchSync(){
	var syncInterval=$j('#newBranchsetSyncForm #NetlimsBranchSyncInterval');
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