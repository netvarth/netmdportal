fillTimeList("#NetmdBranchSyncTime");
validateNumber("#newnetmdBranchsetSyncForm #NetmdBranchSyncInterval");
var netMdBrchId="";

function getNetmdBrchId(netmdbrId){
	netMdBrchId=netmdbrId;
	var getSyncdetails=getRequestData('/youNeverWait/ws/ui/netMd/getBranchSyncDetails/'+netMdBrchId);
	var radioStatus=getSyncdetails.enableSync;
	
	if(radioStatus==false)
		{$j('#netmdbrchDisable').attr('checked', true);}
	else{$j('#netmdbrchEnable').attr('checked', true);}


	if(getSyncdetails.syncFreqType)
		$j("#newnetmdBranchsetSyncForm #NetmdBranchSyncTime ").val(getSyncdetails.syncFreqType);
	else
		$j("#newnetmdBranchsetSyncForm #NetmdBranchSyncTime ").val("Nil");
	if(getSyncdetails.syncTime)
		$j("#newnetmdBranchsetSyncForm #NetmdBranchSyncInterval ").val(getSyncdetails.syncTime);
	else
		$j("#newnetmdBranchsetSyncForm #NetmdBranchSyncInterval ").val();	
	
	var flag=$j('#newnetmdBranchsetSyncForm input[type=radio]:checked').val();	
	if(flag!="disable"){
		$j('#syctxtnetmdBranchEnTime').show();
		$j('#syctxtnetmdBranchEnInr').show();
	}
}


$j('#netmdbrchEnable').die('click').live("click",function() {
		$j('#syctxtnetmdBranchEnTime').show();
		$j('#syctxtnetmdBranchEnInr').show();
	});

$j('#netmdbrchDisable').die('click').live("click",function() {
		$j('#syctxtnetmdBranchEnTime').hide();
		$j('#syctxtnetmdBranchEnInr').hide();
		
	});
	
$j("#newnetmdBranchsetSyncForm #btnnetmdbrchSubmit").die('click').live('click',function(){
	
	removeErrors();	
	if(validateNetmdBrchSync())
		{
	var response = submitNetmdBrchSyncDetailsInfo();
	if(response.success==true){
			var messg=response.msg;
		if(response.msg==null){
			showTip("Set Synchronisation Successfully");
			}else{
			updateTipsNew(messg,$j('#newnetmdBranchsetSyncForm #errorDivNewNetmdBranchData'),$j('#newnetmdBranchsetSyncForm #errorDivHeader'));
			} 
		}
	else {
			updateTipsNew(getErrorName(response.error),$j('#newnetmdBranchsetSyncForm #errorDivNewNetmdBranchData'),$j('#newnetmdBranchsetSyncForm #errorDivHeader'));
		}
		}
	});	

function submitNetmdBrchSyncDetailsInfo(){
	var resultJson = createNetmdBrchSyncDetailsJson();
	var response = postdataToServer(constant_newnetmdBrchSync_Create_Url, resultJson );	
	return response;
}	

function createNetmdBrchSyncDetailsJson()
	{
	var rsd=$j('#newnetmdBranchsetSyncForm input[type=radio]:checked').val();
	//alert(rsd);
	 if($j('#newnetmdBranchsetSyncForm  input[type=radio]:checked').val()=="enable"){
		 
		var syncDetails = '{"enableSync":'+"true"+',';
			syncDetails += '"syncTime":'+$j("#newnetmdBranchsetSyncForm #NetmdBranchSyncInterval").val()+',';
			syncDetails += '"netmdBranchId":'+netMdBrchId+',';
			syncDetails +='"syncFreqType":"' +$j("#newnetmdBranchsetSyncForm #NetmdBranchSyncTime ").val() + '"}';
		return syncDetails;
		
	}
	else{
		var syncDetails = '{"enableSync":'+"false"+',';
			syncDetails += '"netmdBranchId":'+netMdBrchId+ '}';
			return syncDetails;
		
		}   
	
	}	
function validateNetmdBrchSync(){
	var syncInterval=$j('#newnetmdBranchsetSyncForm #NetmdBranchSyncInterval');
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