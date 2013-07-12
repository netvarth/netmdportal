
//up button click action
	$j("#netrxGeneralPTBContainer #btn_up_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curNetrxId = $j("#netrxViewForm #netrxid").val();
		var netrxIdforView = getpreviousNetrxId(curNetrxId,pgNetRxList);
		viewnetrxInfo(netrxIdforView);
	});
//down button click action
	$j("#netrxGeneralPTBContainer #btn_down_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curNetrxId = $j("#netrxViewForm #netrxid").val();
		var netrxIdforView = getnextNetrxId(curNetrxId,pgNetRxList);
		viewnetrxInfo(netrxIdforView);	
	}); 
//back button click action
	$j("#netrxGeneralPTBContainer #btn_back_ptb_id").die('click').live('click',function() {
		removeErrors();
		$j.cachedScript(constant_NetRxEntry_Url).done(function(script, textStatus) {
		})
	}); 	

//Netmd Info  Details Edit Click
	$j('#netrxViewForm #netrxvwbtnEdit').die('click').live('click',function(){
		$j('#pageTitle').html("Edit Netrx Details");
		validateNumber("#netrxViewForm #ownerPhone");
		validateNumber("#netrxViewForm #ownerMobile");
		validateNumber("#netrxViewForm #headOfficePhone");
		validateNumber("#netrxViewForm #headOfficeMobile");
		removeErrors();
		$j('#netrxGeneralPTBContainer #btn_up_ptb_id ').hide();
		$j('#netrxGeneralPTBContainer #btn_down_ptb_id ').hide();
		$j('#netrxGeneralPTBContainer #btn_back_ptb_id ').hide();
			
		$j('#netrxViewForm #netrxvwbtnEdit').hide();
		$j('#netrxViewForm #netrxvwbtnCancel').show();
		$j('#netrxViewForm #netrxvwbtnDone').show();
			
		$j('#viewNetrxHeader input[type=text],#viewNetrxHeader textarea').removeClass('newBox'); // make box 
		$j('#viewNetrxHeader input[type=text],#viewNetrxHeader textarea').removeAttr('readonly');
		$j('#viewNetrxHeader #netrxid').addClass('newBox');
		$j('#viewNetrxHeader #netrxid').attr('readonly','readonly');
		$j('#viewNetrxHeader #usernameNetrx').addClass('newBox');
		$j('#viewNetrxHeader #usernameNetrx').attr('readonly','readonly');
		clearNilFields(netrxViewForm);
	});

//loading previous details while canceling update
	$j('#netrxViewForm #netrxvwbtnCancel').die('click').live('click',function(){
	removeErrors();
		$j('#netrxGeneralPTBContainer #btn_up_ptb_id ').show();
		$j('#netrxGeneralPTBContainer #btn_down_ptb_id ').show();
		$j('#netrxGeneralPTBContainer #btn_back_ptb_id ').show();
		var netrxId = $j("#netrxViewForm #netrxid").val();
		viewnetrxInfo(netrxId);
		$j('#pageTitle').html(constant_NetRxView_Msg);
		$j('#viewNetrxHeader input[type=text],#viewNetrxHeader textarea').addClass('newBox');
		$j('#viewNetrxHeader input[type=text],#viewNetrxHeader textarea').attr('readonly','readonly');	

		$j('#netrxViewForm #netrxvwbtnDone').hide();
		$j('#netrxViewForm #netrxvwbtnCancel').hide();
		$j('#netrxViewForm #netrxvwbtnEdit').show();
	
	});	
	
//saving edited info
$j("#netrxViewForm #netrxvwbtnDone").die('click').live('click',function(){
	removeErrors();
	$j('#netrxGeneralPTBContainer #btn_up_ptb_id ').show();
	$j('#netrxGeneralPTBContainer #btn_down_ptb_id ').show();
	$j('#netrxGeneralPTBContainer #btn_back_ptb_id ').show();
	var netrxId = $j("#netrxViewForm #netrxid").val();
	if(validateviewnetrx())
	{
	var response = submitNetrxInfo();
		if(response.success==true) {
		netrxInfo=setNetrxInfo();
		viewnetrxInfo(netrxId);
		$j('#viewNetrxHeader input[type=text],#viewNetrxHeader textarea').addClass('newBox');
		$j('#viewNetrxHeader input[type=text],#viewNetrxHeader textarea').attr('readonly','readonly');	
		$j('#netrxViewForm #netrxvwbtnDone').hide();
		$j('#netrxViewForm #netrxvwbtnCancel').hide();
		$j('#netrxViewForm #netrxvwbtnEdit').show();
		$j('#pageTitle').html(constant_NetRxView_Msg);
		showTip("Netrx updated successfully");					
	}	
	else
		updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
	}
});

function setNetrxInfo() {
	var netrxId = $j("#netrxViewForm #netrxid").val();
	return getNetRxData(netrxId)
}	