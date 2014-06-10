
//up button click action
	$j("#netmdGeneralPTBContainer #btn_up_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curNetmdId = $j("#netmdViewForm #netmdid").val();
		var netmdIdforView = getpreviousNetmdId(curNetmdId,pgNetMdList);
		viewnetmdInfo(netmdIdforView);
	});
//down button click action
	$j("#netmdGeneralPTBContainer #btn_down_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curNetmdId = $j("#netmdViewForm #netmdid").val();
		var netmdIdforView = getnextNetmdId(curNetmdId,pgNetMdList);
		viewnetmdInfo(netmdIdforView);	
	}); 
//back button click action
	$j("#netmdGeneralPTBContainer #btn_back_ptb_id").die('click').live('click',function() {
		removeErrors();
		$j.cachedScript(constant_NetMdEntry_Url).done(function(script, textStatus) {
		})
	}); 	

//Netmd Info  Details Edit Click
	$j('#netmdViewForm #netmdvwbtnEdit').die('click').live('click',function(){
		$j('#pageTitle').html("Edit Netmd Details");
		validateNumber("#netmdViewForm #ownerPhone");
		validateNumber("#netmdViewForm #ownerMobile");
		validateNumber("#netmdViewForm #headOfficePhone");
		validateNumber("#netmdViewForm #headOfficeMobile");
		removeErrors();
		$j('#netmdGeneralPTBContainer #btn_up_ptb_id ').hide();
		$j('#netmdGeneralPTBContainer #btn_down_ptb_id ').hide();
		$j('#netmdGeneralPTBContainer #btn_back_ptb_id ').hide();
			
		$j('#netmdViewForm #netmdvwbtnEdit').hide();
		$j('#netmdViewForm #netmdvwbtnCancel').show();
		$j('#netmdViewForm #netmdvwbtnDone').show();
			
		$j('#viewNetmdHeader input[type=text],#viewNetmdHeader textarea').removeClass('newBox'); // make box 
		$j('#viewNetmdHeader input[type=text],#viewNetmdHeader textarea').removeAttr('readonly');
		$j('#viewNetmdHeader #netmdid').addClass('newBox');
		$j('#viewNetmdHeader #netmdid').attr('readonly','readonly');
		$j('#viewNetmdHeader #usernameNetmd').addClass('newBox');
		$j('#viewNetmdHeader #usernameNetmd').attr('readonly','readonly');
		clearNilFields(netmdViewForm);
	});

//loading previous details while canceling update
	$j('#netmdViewForm #netmdvwbtnCancel').die('click').live('click',function(){
	removeErrors();
		$j('#netmdGeneralPTBContainer #btn_up_ptb_id ').show();
		$j('#netmdGeneralPTBContainer #btn_down_ptb_id ').show();
		$j('#netmdGeneralPTBContainer #btn_back_ptb_id ').show();
		var netmdId = $j("#netmdViewForm #netmdid").val();
		viewnetmdInfo(netmdId);
		$j('#pageTitle').html(constant_NetMdView_Msg);
		$j('#viewNetmdHeader input[type=text],#viewNetmdHeader textarea').addClass('newBox');
		$j('#viewNetmdHeader input[type=text],#viewNetmdHeader textarea').attr('readonly','readonly');	

		$j('#netmdViewForm #netmdvwbtnDone').hide();
		$j('#netmdViewForm #netmdvwbtnCancel').hide();
		$j('#netmdViewForm #netmdvwbtnEdit').show();
	
	});	
	
//saving edited info
$j("#netmdViewForm #netmdvwbtnDone").die('click').live('click',function(){
	removeErrors();
	$j('#netmdGeneralPTBContainer #btn_up_ptb_id ').show();
	$j('#netmdGeneralPTBContainer #btn_down_ptb_id ').show();
	$j('#netmdGeneralPTBContainer #btn_back_ptb_id ').show();
	var netmdId = $j("#netmdViewForm #netmdid").val();
	if(validateviewnetmd())
	{
	var response = submitNetmdInfo();
		if(response.success==true) {
		netmdInfo=setNetmdInfo();
		viewnetmdInfo(netmdId);
		$j('#viewNetmdHeader input[type=text],#viewNetmdHeader textarea').addClass('newBox');
		$j('#viewNetmdHeader input[type=text],#viewNetmdHeader textarea').attr('readonly','readonly');	
		$j('#netmdViewForm #netmdvwbtnDone').hide();
		$j('#netmdViewForm #netmdvwbtnCancel').hide();
		$j('#netmdViewForm #netmdvwbtnEdit').show();
		$j('#pageTitle').html(constant_NetMdView_Msg);
		showTip("Netmd updated successfully");					
	}	
	else
		updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
	}
});

function setNetmdInfo() {
	var netmdId = $j("#netmdViewForm #netmdid").val();
	return getNetMdData(netmdId)
}	