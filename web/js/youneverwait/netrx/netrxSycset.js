fillTimeList("#NetrxSyncTime");
validateNumber("#newsetNetrxSyncForm #NetrxSyncInterval");
var netRxId="";

function getNetrxId(netrxId){
	netRxId=netrxId;
	var getSyncdetails=getRequestData('/youNeverWait/ws/ui/netRx/getNetrxSyncDetails/'+netRxId);
	var radioStatus=getSyncdetails.enableSync;
	
	if(radioStatus==false)
		{$j('#NetrxDisable').attr('checked', true);}
	else{$j('#NetrxEnable').attr('checked', true);}


	if(getSyncdetails.syncFreqType)
		$j("#newsetNetrxSyncForm #NetrxSyncTime ").val(getSyncdetails.syncFreqType);
	else
		$j("#newsetNetrxSyncForm #NetrxSyncTime ").val("Nil");
	if(getSyncdetails.syncTime)
		$j("#newsetNetrxSyncForm #NetrxSyncInterval ").val(getSyncdetails.syncTime);
	else
		$j("#newsetNetrxSyncForm #NetrxSyncInterval ").val();	
	
	var flag=$j('#newsetNetrxSyncForm input[type=radio]:checked').val();	
	if(flag!="disable"){
		$j('#syctxtNetrxEnTime').show();
		$j('#syctxtNetrxEnInr').show();
	}
}

$j('#NetrxEnable').die('click').live("click",function() {
		$j('#syctxtNetrxEnTime').show();
		$j('#syctxtNetrxEnInr').show();
	});

$j('#NetrxDisable').die('click').live("click",function() {
		$j('#syctxtNetrxEnTime').hide();
		$j('#syctxtNetrxEnInr').hide();
		
	});
	
	
$j("#newsetNetrxSyncForm #btnnetrxSubmit").die('click').live('click',function(){
	//alert("click");
	removeErrors();	
	if(validateNetrxSync())
		{
	var response = submitNetrxSyncDetailsInfo();
	if(response.success==true){
			var messg=response.msg;
		if(response.msg==null){
			showTip("Set Synchronisation Successfully");
			}else{
			updateTipsNew(messg,$j('#newsetNetrxSyncForm #errorDivNewNetrxBranchData'),$j('#newsetNetrxSyncForm #errorDivHeader'));
			} 
		}
	else {
			updateTipsNew(getErrorName(response.error),$j('#newsetNetrxSyncForm #errorDivNewNetrxBranchData'),$j('#newsetNetrxSyncForm #errorDivHeader'));
		}
		}
	});	
	
function submitNetrxSyncDetailsInfo(){
	var resultJson = createNetrxSyncDetailsJson();
	//alert(resultJson);
	var response = postdataToServer(constant_newnetRxSync_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}	

function createNetrxSyncDetailsJson()
	{
	var rsd=$j('#newsetNetrxSyncForm input[type=radio]:checked').val();
	//alert(rsd);
	 if($j('#newsetNetrxSyncForm input[type=radio]:checked').val()=="enable"){
		 
		var syncDetails = '{"enableSync":'+"true"+',';
			syncDetails += '"syncTime":'+$j("#newsetNetrxSyncForm #NetrxSyncInterval").val()+',';
			syncDetails += '"netrxId":'+netRxId+',';
			syncDetails +='"syncFreqType":"' +$j("#newsetNetrxSyncForm #NetrxSyncTime ").val() + '"}';
		return syncDetails;
		
	}
	else{
		var syncDetails = '{"enableSync":'+"false"+',';
			syncDetails += '"netrxId":'+netRxId+ '}';
			return syncDetails;
		
		}   
	
	}

function validateNetrxSync(){
	var syncInterval=$j('#newsetNetrxSyncForm #NetrxSyncInterval');
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