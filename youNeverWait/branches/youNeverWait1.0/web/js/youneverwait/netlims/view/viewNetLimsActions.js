//up button click action
	$j("#GeneralPTBContainer #btn_up_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curNetlimsId = $j("#netlimsViewForm #netlimsid").val();
		var netlimsIdforView = getpreviousNetlimsId(curNetlimsId,pgNetLimsList);
		viewnetlimsInfo(netlimsIdforView);
	});
//down button click action
	$j("#GeneralPTBContainer #btn_down_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curNetlimsId = $j("#netlimsViewForm #netlimsid").val();
		var netlimsIdforView = getnextNetlimsId(curNetlimsId,pgNetLimsList);
		viewnetlimsInfo(netlimsIdforView);	
	}); 
	
//back button click action
	$j("#GeneralPTBContainer #btn_back_ptb_id").die('click').live('click',function() {
		removeErrors();
		$j.cachedScript(constant_NetLimsEntry_Url).done(function(script, textStatus) {
		})
	}); 


//Netlims Info  Details Edit Click
	$j('#netlimsViewForm #netlimsvwbtnCreate').die('click').live('click',function(){
		$j('#pageTitle').html("Edit Netlims Details");
		validateNumber("#netlimsViewForm #ownerPhone");
		validateNumber("#netlimsViewForm #ownerMobile");
		validateNumber("#netlimsViewForm #headOfficePhone");
		validateNumber("#netlimsViewForm #headOfficeMobile");
		removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').hide();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').hide();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').hide();
			
		$j('#netlimsViewForm #netlimsvwbtnCreate').hide();
		$j('#netlimsViewForm #netlimsvwbtnCancel').show();
		$j('#netlimsViewForm #netlimsvwbtnDone').show();
			
		$j('#viewNetlimsHeader input[type=text],#viewNetlimsHeader textarea').removeClass('newBox'); // make box 
		$j('#viewNetlimsHeader input[type=text],#viewNetlimsHeader textarea').removeAttr('readonly');
		$j('#viewNetlimsHeader #netlimsid').addClass('newBox');
		$j('#viewNetlimsHeader #netlimsid').attr('readonly','readonly');
		$j('#viewNetlimsHeader #username').addClass('newBox');
		$j('#viewNetlimsHeader #username').attr('readonly','readonly');
		clearNilFields(netlimsViewForm);
	});



//loading previous details while canceling update
	$j('#netlimsViewForm #netlimsvwbtnCancel').die('click').live('click',function(){
	removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').show();
		var netlimsId = $j("#netlimsViewForm #netlimsid").val();
		viewnetlimsInfo(netlimsId);
		$j('#pageTitle').html(constant_NetLimsView_Msg);
		$j('#viewNetlimsHeader input[type=text],#viewNetlimsHeader textarea').addClass('newBox');
		$j('#viewNetlimsHeader input[type=text],#viewNetlimsHeader textarea').attr('readonly','readonly');	

		$j('#netlimsViewForm #netlimsvwbtnDone').hide();
		$j('#netlimsViewForm #netlimsvwbtnCancel').hide();
		$j('#netlimsViewForm #netlimsvwbtnCreate').show();
	
	});

//saving edited info
$j("#netlimsViewForm #netlimsvwbtnDone").die('click').live('click',function(){
	removeErrors();
	$j('#GeneralPTBContainer #btn_up_ptb_id ').show();
	$j('#GeneralPTBContainer #btn_down_ptb_id ').show();
	$j('#GeneralPTBContainer #btn_back_ptb_id ').show();
	var netlimsId = $j("#netlimsViewForm #netlimsid").val();
	if(validateviewnetlims())
	{
	var response = submitNetlimsInfo();
		if(response.success==true) {
		netlimsInfo=setNetlimsInfo();
		viewnetlimsInfo(netlimsId);
		$j('#viewNetlimsHeader input[type=text],#viewNetlimsHeader textarea').addClass('newBox');
		$j('#viewNetlimsHeader input[type=text],#viewNetlimsHeader textarea').attr('readonly','readonly');	
		$j('#netlimsViewForm #netlimsvwbtnDone').hide();
		$j('#netlimsViewForm #netlimsvwbtnCancel').hide();
		$j('#netlimsViewForm #netlimsvwbtnCreate').show();
		$j('#pageTitle').html(constant_NetLimsView_Msg);
		showTip("Netlims updated successfully");					
	}	
	else
		updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
	}
});

function setNetlimsInfo() {
	var netlimsId = $j("#netlimsViewForm #netlimsid").val();
	return getNetLimsData(netlimsId);
}
