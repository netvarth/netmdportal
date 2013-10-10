
fillTimeList("#syncTime");
validateNumber("#newBranchForm #syncInterval");
var getSyncdetails=getRequestData('/youNeverWait/ws/ui/superAdmin/getSyncDetails');
var radioStatus=getSyncdetails.enableSync;

if(radioStatus==false)
	{$j('#Disable').attr('checked', true);}
else{$j('#Enable').attr('checked', true);}


if(getSyncdetails.syncFreqType)
		$j("#newBranchForm #syncTime ").val(getSyncdetails.syncFreqType);
	else
		$j("#newBranchForm #syncTime ").val("Nil");
	if(getSyncdetails.syncTime)
		$j("#newBranchForm #syncInterval ").val(getSyncdetails.syncTime);
	else
		$j("#newBranchForm #syncInterval ").val();	

var flag=$j('input[type=radio]:checked').val();	
	if(flag!="disable"){
		$j('#syctxtEnTime').show();
		$j('#syctxtEnInr').show();
	}

$j('#Enable').die('click').live("click",function() {
		 $j('#syctxtEnTime').show();
		$j('#syctxtEnInr').show();
	});

$j('#Disable').die('click').live("click",function() {
		$j('#syctxtEnTime').hide();
		$j('#syctxtEnInr').hide();
		
	});

$j("#newBranchForm #btnSubmit").die('click').live('click',function(){
	removeErrors();	
	if(validateSync())
		{
	var response = submitSyncDetailsInfo();
	if(response.success==true){
			showTip("Set Synchronisation Successfully");
		}
	else {
			updateTipsNew(getErrorName(response.error),$j('#newBranchForm #errorDivNewNetlimsBranchData'),$j('#newBranchForm #errorDivHeader'));
		}
		}
	});	

 function submitSyncDetailsInfo(){
	var resultJson = createSyncDetailsJson();
	//alert(resultJson);
	var response = postdataToServer(constant_newSync_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}
	
function createSyncDetailsJson()
	{
	var rsd=$j('input[type=radio]:checked').val();
	//alert(rsd);
	 if($j('input[type=radio]:checked').val()=="enable"){
		 
		var syncDetails = '{"enableSync":'+"true"+',';
			syncDetails += '"syncTime":'+$j("#newBranchForm #syncInterval ").val()+',';
			syncDetails +='"syncFreqType":"' +$j("#newBranchForm #syncTime ").val() + '"}';
		return syncDetails;
		
		
	}
	 else{
	var syncDetails = '{"enableSync":'+"false"+'}';
	return syncDetails;
		}   
	
	}
	
function validateSync(){
	var syncInterval=$j('#newBranchForm #syncInterval');
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
