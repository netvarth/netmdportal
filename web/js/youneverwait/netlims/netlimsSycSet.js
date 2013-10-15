fillTimeList("#NetlimsSyncTime");
validateNumber("#newsetSyncForm #NetlimsSyncInterval");
var netlmsId="";
function getNetlimsId(netlimsId){
	netlmsId=netlimsId;
	var getSyncdetails=getRequestData('/youNeverWait/ws/ui/lab/getLabSyncDetails/'+netlmsId);
	//alert(JSON.stringify(getSyncdetails));
	var radioStatus=getSyncdetails.enableSync;
	
	if(radioStatus==false)
		{$j('#netlimsDisable').attr('checked', true);}
	else{$j('#netlimsEnable').attr('checked', true);}


	if(getSyncdetails.syncFreqType)
		$j("#newsetSyncForm #NetlimsSyncTime ").val(getSyncdetails.syncFreqType);
	else
		$j("#newsetSyncForm #NetlimsSyncTime ").val("Nil");
	if(getSyncdetails.syncTime)
		$j("#newsetSyncForm #NetlimsSyncInterval ").val(getSyncdetails.syncTime);
	else
		$j("#newsetSyncForm #NetlimsSyncInterval ").val();	
	
	var flag=$j('#newsetSyncForm input[type=radio]:checked').val();	
	//alert(flag);
	if(flag!="disable"){
		$j('#syctxtnetlimsEnTime').show();
		$j('#syctxtnetlimsEnInr').show();
	}
	
}



$j('#netlimsEnable').die('click').live("click",function() {
		$j('#syctxtnetlimsEnTime').show();
		$j('#syctxtnetlimsEnInr').show();
	});

$j('#netlimsDisable').die('click').live("click",function() {
		$j('#syctxtnetlimsEnTime').hide();
		$j('#syctxtnetlimsEnInr').hide();
		
	});
	
	
$j("#newsetSyncForm #btnnetlimsSubmit").die('click').live('click',function(){
	//alert("click");
	removeErrors();	
	if(validateNetlimsSync())
		{
	var response = submitNetlimsSyncDetailsInfo();
	if(response.success==true){
			showTip("Set Synchronisation Successfully");
		}
	else {
			updateTipsNew(getErrorName(response.error),$j('#newsetSyncForm #errorDivNewNetlimsBranchData'),$j('#newsetSyncForm #errorDivHeader'));
		}
		}
	});	

function submitNetlimsSyncDetailsInfo(){
	var resultJson = createNetlimsSyncDetailsJson();
	//alert(resultJson);
	var response = postdataToServer(constant_newnetlimsSync_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}

function createNetlimsSyncDetailsJson()
	{
	var rsd=$j('#newsetSyncForm input[type=radio]:checked').val();
	//alert(rsd);
	 if($j('#newsetSyncForm input[type=radio]:checked').val()=="enable"){
		 
		var syncDetails = '{"enableSync":'+"true"+',';
			syncDetails += '"syncTime":'+$j("#newsetSyncForm #NetlimsSyncInterval ").val()+',';
			syncDetails += '"labId":'+netlmsId+',';
			syncDetails +='"syncFreqType":"' +$j("#newsetSyncForm #NetlimsSyncTime ").val() + '"}';
		return syncDetails;
		
	}
	else{
		var syncDetails = '{"enableSync":'+"false"+',';
			syncDetails += '"labId":'+netlmsId+ '}';
			return syncDetails;
		
		}   
	
	}
	
function validateNetlimsSync(){
	var syncInterval=$j('#newsetSyncForm #NetlimsSyncInterval');
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