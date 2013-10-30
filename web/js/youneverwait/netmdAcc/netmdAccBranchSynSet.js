fillTimeList("#NetmdAccBranchSyncTime");
validateNumber("#newnetmdAccBranchsetSyncForm #NetmdAccBranchSyncInterval");
var netMdBrchId="";

function getNetmdAccBrchId(netmdbrId){
	netMdBrchId=netmdbrId;
	var getSyncdetails=getRequestData('/youNeverWait/ws/ui/netMd/getBranchSyncDetails/'+netMdBrchId);
	var radioStatus=getSyncdetails.enableSync;
	
	if(radioStatus==false)
		{$j('#netmdAccbrchDisable').attr('checked', true);}
	else{$j('#netmdAccbrchEnable').attr('checked', true);}


	if(getSyncdetails.syncFreqType)
		$j("#newnetmdAccBranchsetSyncForm #NetmdAccBranchSyncTime ").val(getSyncdetails.syncFreqType);
	else
		$j("#newnetmdAccBranchsetSyncForm #NetmdAccBranchSyncTime ").val("Nil");
	if(getSyncdetails.syncTime)
		$j("#newnetmdAccBranchsetSyncForm #NetmdAccBranchSyncInterval ").val(getSyncdetails.syncTime);
	else
		$j("#newnetmdAccBranchsetSyncForm #NetmdAccBranchSyncInterval ").val();	
	
	var flag=$j('#newnetmdAccBranchsetSyncForm input[type=radio]:checked').val();	
	if(flag!="disable"){
		$j('#syctxtnetmdAccBranchEnTime').show();
		$j('#syctxtnetmdAccBranchEnInr').show();
	}
}


$j('#netmdAccbrchEnable').die('click').live("click",function() {
		$j('#syctxtnetmdAccBranchEnTime').show();
		$j('#syctxtnetmdAccBranchEnInr').show();
	});

$j('#netmdAccbrchDisable').die('click').live("click",function() {
		$j('#syctxtnetmdAccBranchEnTime').hide();
		$j('#syctxtnetmdAccBranchEnInr').hide();
		
	});
	
$j("#newnetmdAccBranchsetSyncForm #btnnetmdAccbrchSubmit").die('click').live('click',function(){
	
	removeErrors();	
	if(validateNetmdAccBrchSync())
		{
	var response = submitNetmdAccBrchSyncDetailsInfo();
	if(response.success==true){
			var messg=response.msg;
		if(response.msg==null){
			showTip("Set Synchronisation Successfully");
			}else{
			updateTipsNew(messg,$j('#newnetmdAccBranchsetSyncForm #errorDivNewNetmdAccBranchData'),$j('#newnetmdAccBranchsetSyncForm #errorDivHeader'));
			} 
		}
	else {
			updateTipsNew(getErrorName(response.error),$j('#newnetmdAccBranchsetSyncForm #errorDivNewNetmdAccBranchData'),$j('#newnetmdAccBranchsetSyncForm #errorDivHeader'));
		}
		}
	});	

function submitNetmdAccBrchSyncDetailsInfo(){
	var resultJson = createNetmdAccBrchSyncDetailsJson();
	var response = postdataToServer(constant_newnetmdAccBrchSync_Create_Url, resultJson );	
	return response;
}	

function createNetmdAccBrchSyncDetailsJson()
	{
	var rsd=$j('#newnetmdAccBranchsetSyncForm input[type=radio]:checked').val();
	//alert(rsd);
	 if($j('#newnetmdAccBranchsetSyncForm  input[type=radio]:checked').val()=="enable"){
		 
		var syncDetails = '{"enableSync":'+"true"+',';
			syncDetails += '"syncTime":'+$j("#newnetmdAccBranchsetSyncForm #NetmdAccBranchSyncInterval").val()+',';
			syncDetails += '"netmdBranchId":'+netMdBrchId+',';
			syncDetails +='"syncFreqType":"' +$j("#newnetmdAccBranchsetSyncForm #NetmdAccBranchSyncTime ").val() + '"}';
		return syncDetails;
		
	}
	else{
		var syncDetails = '{"enableSync":'+"false"+',';
			syncDetails += '"netmdBranchId":'+netMdBrchId+ '}';
			return syncDetails;
		
		}   
	
	}	
function validateNetmdAccBrchSync(){
	var syncInterval=$j('#newnetmdAccBranchsetSyncForm #NetmdAccBranchSyncInterval');
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