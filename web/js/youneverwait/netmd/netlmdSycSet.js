fillTimeList("#NetmdSyncTime");
validateNumber("#newsetNetmdSyncForm #NetmdSyncInterval");
var netMdId="";

function getNetmdId(netmdId){
	netMdId=netmdId;
	var getSyncdetails=getRequestData('/youNeverWait/ws/ui/netMd/getNetmdSyncDetails/'+netMdId);
	var radioStatus=getSyncdetails.enableSync;
	
	if(radioStatus==false)
		{$j('#NetmdDisable').attr('checked', true);}
	else{$j('#NetmdEnable').attr('checked', true);}


	if(getSyncdetails.syncFreqType)
		$j("#newsetNetmdSyncForm #NetmdSyncTime ").val(getSyncdetails.syncFreqType);
	else
		$j("#newsetNetmdSyncForm #NetmdSyncTime ").val("Nil");
	if(getSyncdetails.syncTime)
		$j("#newsetNetmdSyncForm #NetmdSyncInterval ").val(getSyncdetails.syncTime);
	else
		$j("#newsetNetmdSyncForm #NetmdSyncInterval ").val();	
	
	var flag=$j('input[type=radio]:checked').val();	
	if(flag!="disable"){
		$j('#syctxtNetmdEnTime').show();
		$j('#syctxtNetmdEnInr').show();
	}
}

$j('#NetmdEnable').die('click').live("click",function() {
		$j('#syctxtNetmdEnTime').show();
		$j('#syctxtNetmdEnInr').show();
	});

$j('#NetmdDisable').die('click').live("click",function() {
		$j('#syctxtNetmdEnTime').hide();
		$j('#syctxtNetmdEnInr').hide();
		
	});
	
	
$j("#newsetNetmdSyncForm #btnnetmdSubmit").die('click').live('click',function(){
	//alert("click");
	removeErrors();	
	if(validateNetmdSync())
		{
	var response = submitNetmdSyncDetailsInfo();
	if(response.success==true){
			showTip("Set Synchronisation Successfully");
		}
	else {
			updateTipsNew(getErrorName(response.error),$j('#newsetNetmdSyncForm #errorDivNewNetmdBranchData'),$j('#newBranchForm #errorDivHeader'));
		}
		}
	});	
	
function submitNetmdSyncDetailsInfo(){
	var resultJson = createNetmdSyncDetailsJson();
	//alert(resultJson);
	var response = postdataToServer(constant_newnetmdSync_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}	

function createNetmdSyncDetailsJson()
	{
	var rsd=$j('input[type=radio]:checked').val();
	//alert(rsd);
	 if($j('input[type=radio]:checked').val()=="enable"){
		 
		var syncDetails = '{"enableSync":'+"true"+',';
			syncDetails += '"syncTime":'+$j("#newsetNetmdSyncForm #NetmdSyncInterval").val()+',';
			syncDetails += '"netmdId":'+netMdId+',';
			syncDetails +='"syncFreqType":"' +$j("#newsetNetmdSyncForm #NetmdSyncTime ").val() + '"}';
		return syncDetails;
		
	}
	else{
		var syncDetails = '{"enableSync":'+"false"+',';
			syncDetails += '"netmdId":'+netMdId+ '}';
			return syncDetails;
		
		}   
	
	}

function validateNetmdSync(){
	var syncInterval=$j('#newsetNetmdSyncForm #NetmdSyncInterval');
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