fillTimeList("#NetrxAccBranchSyncTime");
validateNumber("#newnetrxAccBranchsetSyncForm #NetrxAccBranchSyncInterval");
var netRxBrchId="";

function getNetrxBrchId(netrxbrId){
	netRxBrchId=netrxbrId;
	var getSyncdetails=getRequestData('/youNeverWait/ws/ui/netRx/getBranchSyncDetails/'+netRxBrchId);
	var radioStatus=getSyncdetails.enableSync;
	
	if(radioStatus==false)
		{$j('#netrxAccbrchDisable').attr('checked', true);}
	else{$j('#netrxAccbrchEnable').attr('checked', true);}


	if(getSyncdetails.syncFreqType)
		$j("#newnetrxAccBranchsetSyncForm #NetrxAccBranchSyncTime ").val(getSyncdetails.syncFreqType);
	else
		$j("#newnetrxAccBranchsetSyncForm #NetrxAccBranchSyncTime ").val("Nil");
	if(getSyncdetails.syncTime)
		$j("#newnetrxAccBranchsetSyncForm #NetrxAccBranchSyncInterval ").val(getSyncdetails.syncTime);
	else
		$j("#newnetrxAccBranchsetSyncForm #NetrxAccBranchSyncInterval ").val();	
	
	var flag=$j('#newnetrxAccBranchsetSyncForm input[type=radio]:checked').val();	
	if(flag!="disable"){
		$j('#syctxtnetrxAccBranchEnTime').show();
		$j('#syctxtnetrxAccBranchEnInr').show();
	}
}


$j('#netrxAccbrchEnable').die('click').live("click",function() {
		$j('#syctxtnetrxAccBranchEnTime').show();
		$j('#syctxtnetrxAccBranchEnInr').show();
	});

$j('#netrxAccbrchDisable').die('click').live("click",function() {
		$j('#syctxtnetrxAccBranchEnTime').hide();
		$j('#syctxtnetrxAccBranchEnInr').hide();
		
	});
	
$j("#newnetrxAccBranchsetSyncForm #btnnetrxAccbrchSubmit").die('click').live('click',function(){
	
	removeErrors();	
	if(validateNetrxAccBrchSync())
		{
	var response = submitNetrxAccBrchSyncDetailsInfo();
	if(response.success==true){
			var messg=response.msg;
		if(response.msg==null){
			showTip("Set Synchronisation Successfully");
			}else{
			updateTipsNew(messg,$j('#newnetrxAccBranchsetSyncForm #errorDivNewNetrxAccBranchData'),$j('#newnetrxAccBranchsetSyncForm #errorDivHeader'));
			} 
		}
	else {
			updateTipsNew(getErrorName(response.error),$j('#newnetrxAccBranchsetSyncForm #errorDivNewNetrxAccBranchData'),$j('#newnetrxAccBranchsetSyncForm #errorDivHeader'));
		}
		}
	});	

function submitNetrxAccBrchSyncDetailsInfo(){
	var resultJson = createNetrxAccBrchSyncDetailsJson();
	var response = postdataToServer(constant_newnetRxAccBrchSync_Create_Url, resultJson );	
	return response;
}	

function createNetrxAccBrchSyncDetailsJson()
	{
	var rsd=$j('#newnetrxAccBranchsetSyncForm input[type=radio]:checked').val();
	//alert(rsd);
	 if($j('#newnetrxAccBranchsetSyncForm  input[type=radio]:checked').val()=="enable"){
		 
		var syncDetails = '{"enableSync":'+"true"+',';
			syncDetails += '"syncTime":'+$j("#newnetrxAccBranchsetSyncForm #NetrxAccBranchSyncInterval").val()+',';
			syncDetails += '"netrxBranchId":'+netRxBrchId+',';
			syncDetails +='"syncFreqType":"' +$j("#newnetrxAccBranchsetSyncForm #NetrxAccBranchSyncTime ").val() + '"}';
		return syncDetails;
		
	}
	else{
		var syncDetails = '{"enableSync":'+"false"+',';
			syncDetails += '"netrxBranchId":'+netRxBrchId+ '}';
			return syncDetails;
		
		}   
	
	}	
function validateNetrxAccBrchSync(){
	var syncInterval=$j('#newnetrxAccBranchsetSyncForm #NetrxAccBranchSyncInterval');
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