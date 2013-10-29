fillTimeList("#NetrxBranchSyncTime");
validateNumber("#newnetrxBranchsetSyncForm #NetrxBranchSyncInterval");
var netRxBrchId="";

function getNetrxBrchId(netrxbrId){
	netRxBrchId=netrxbrId;
	var getSyncdetails=getRequestData('/youNeverWait/ws/ui/netRx/getBranchSyncDetails/'+netRxBrchId);
	var radioStatus=getSyncdetails.enableSync;
	
	if(radioStatus==false)
		{$j('#netrxbrchDisable').attr('checked', true);}
	else{$j('#netrxbrchEnable').attr('checked', true);}


	if(getSyncdetails.syncFreqType)
		$j("#newnetrxBranchsetSyncForm #NetrxBranchSyncTime ").val(getSyncdetails.syncFreqType);
	else
		$j("#newnetrxBranchsetSyncForm #NetrxBranchSyncTime ").val("Nil");
	if(getSyncdetails.syncTime)
		$j("#newnetrxBranchsetSyncForm #NetrxBranchSyncInterval ").val(getSyncdetails.syncTime);
	else
		$j("#newnetrxBranchsetSyncForm #NetrxBranchSyncInterval ").val();	
	
	var flag=$j('#newnetrxBranchsetSyncForm input[type=radio]:checked').val();	
	if(flag!="disable"){
		$j('#syctxtnetrxBranchEnTime').show();
		$j('#syctxtnetrxBranchEnInr').show();
	}
}


$j('#netrxbrchEnable').die('click').live("click",function() {
		$j('#syctxtnetrxBranchEnTime').show();
		$j('#syctxtnetrxBranchEnInr').show();
	});

$j('#netrxbrchDisable').die('click').live("click",function() {
		$j('#syctxtnetrxBranchEnTime').hide();
		$j('#syctxtnetrxBranchEnInr').hide();
		
	});
	
$j("#newnetrxBranchsetSyncForm #btnnetrxbrchSubmit").die('click').live('click',function(){
	
	removeErrors();	
	if(validateNetrxBrchSync())
		{
	var response = submitNetrxBrchSyncDetailsInfo();
	if(response.success==true){
			showTip("Set Synchronisation Successfully");
		}
	else {
			updateTipsNew(getErrorName(response.error),$j('#newnetrxBranchsetSyncForm #errorDivNewNetrxBranchData'),$j('#newnetrxBranchsetSyncForm #errorDivHeader'));
		}
		}
	});	

function submitNetrxBrchSyncDetailsInfo(){
	var resultJson = createNetrxBrchSyncDetailsJson();
	var response = postdataToServer(constant_newnetRxBrchSync_Create_Url, resultJson );	
	return response;
}	

function createNetrxBrchSyncDetailsJson()
	{
	var rsd=$j('#newnetrxBranchsetSyncForm input[type=radio]:checked').val();
	//alert(rsd);
	 if($j('#newnetrxBranchsetSyncForm  input[type=radio]:checked').val()=="enable"){
		 
		var syncDetails = '{"enableSync":'+"true"+',';
			syncDetails += '"syncTime":'+$j("#newnetrxBranchsetSyncForm #NetrxBranchSyncInterval").val()+',';
			syncDetails += '"netrxBranchId":'+netRxBrchId+',';
			syncDetails +='"syncFreqType":"' +$j("#newnetrxBranchsetSyncForm #NetrxBranchSyncTime ").val() + '"}';
		return syncDetails;
		
	}
	else{
		var syncDetails = '{"enableSync":'+"false"+',';
			syncDetails += '"netrxBranchId":'+netRxBrchId+ '}';
			return syncDetails;
		
		}   
	
	}	
function validateNetrxBrchSync(){
	var syncInterval=$j('#newnetrxBranchsetSyncForm #NetrxBranchSyncInterval');
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