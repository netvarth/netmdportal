//up button click action
	$j("#GeneralPTBContainer #btn_up_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curBranchId = $j("#netrxAccbranchViewForm #branchid").val();
		var branchIdforView = getpreviousBranchId(curBranchId,pgBranchList);
		viewNetrxAccBranchInfo(branchIdforView,'#passphraseViewTableAcc','#passphrasePrimaryViewTableAcc');
		//viewbranchInfo(branchIdforView);
	});
//down button click action
	$j("#GeneralPTBContainer #btn_down_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curBranchId = $j("#netrxAccbranchViewForm #branchid").val();
		var branchIdforView = getnextBranchId(curBranchId,pgBranchList);
		viewNetrxAccBranchInfo(branchIdforView,'#passphraseViewTableAcc','#passphrasePrimaryViewTableAcc');
		//viewbranchInfo(branchIdforView);	
	}); 


//back button click action
	$j("#GeneralPTBContainer #btn_back_ptb_id").die('click').live('click',function() {
		removeErrors();
		$j.cachedScript(constant_NetRxAccEntry_Url).done(function(script, textStatus) {
		})
	}); 
	
	$j('#netrxAccbranchViewForm #netrxAccbranchvwbtnEdit').die('click').live('click',function(){
		$j('#pageTitle').html("Edit Branch Details");
		validateNumber("#netrxAccbranchViewForm #branchPhone");
		validateNumber("#netrxAccbranchViewForm #branchMobile");
		removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').hide();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').hide();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').hide();
			
		$j('#netrxAccbranchViewForm #netrxAccbranchvwbtnEdit').hide();
		$j('#netrxAccbranchViewForm #netrxAccbranchvwbtnCancel').show();
		$j('#netrxAccbranchViewForm #netrxAccbranchvwbtnDone').show();
			
		$j('#viewNetrxAccBranchHeader input[type=text],#viewNetrxAccBranchHeader textarea').removeClass('newBox'); // make box 
		$j('#viewNetrxAccBranchHeader input[type=text],#viewNetrxAccBranchHeader textarea').removeAttr('readonly');
		$j('#viewNetrxAccBranchHeader #branchid').addClass('newBox');
		$j('#viewNetrxAccBranchHeader #branchid').attr('readonly','readonly');
		$j('#viewNetrxAccBranchHeader #netrxid').addClass('newBox');
		$j('#viewNetrxAccBranchHeader #netrxid').attr('readonly','readonly');
		$j('#viewNetrxAccBranchHeader #branchStatus').addClass('newBox');
		$j('#viewNetrxAccBranchHeader #branchStatus').attr('readonly','readonly');
		$j('#viewNetrxAccBranchHeader #devicesno').addClass('newBox');
		$j('#viewNetrxAccBranchHeader #devicesno').attr('readonly','readonly');
		clearNilFields(netrxAccbranchViewForm);
	});	
	
	//loading previous details while canceling update
	$j('#netrxAccbranchViewForm #netrxAccbranchvwbtnCancel').die('click').live('click',function(){
	removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').show();
		var branchid = $j("#netrxAccbranchViewForm #branchid").val();
		viewNetrxAccBranchInfo(branchid,'#passphraseViewTableAcc','#passphrasePrimaryViewTableAcc');
		$j('#viewNetrxAccBranchHeader input[type=text],#viewNetrxAccBranchHeader textarea').addClass('newBox');
		$j('#viewNetrxAccBranchHeader input[type=text],#viewNetrxAccBranchHeader textarea').attr('readonly','readonly');	

		$j('#netrxAccbranchViewForm #netrxAccbranchvwbtnDone').hide();
		$j('#netrxAccbranchViewForm #netrxAccbranchvwbtnCancel').hide();
		$j('#netrxAccbranchViewForm #netrxAccbranchvwbtnEdit').show();
	
	});	
	
	//saving edited info
	$j("#netrxAccbranchViewForm #netrxAccbranchvwbtnDone").die('click').live('click',function(){
		removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').show();
		var branchId = $j("#netrxAccbranchViewForm #branchid").val();
		if(validateviewnetrxAccbranch())
		{
			var response = submitNetrxAccBranchInfo();
			if(response.success==true) {
				viewNetrxAccBranchInfo(branchId,'#passphraseViewTableAcc','#passphrasePrimaryViewTableAcc');
				$j('#viewNetrxAccBranchHeader input[type=text],#viewNetrxAccBranchHeader textarea').addClass('newBox');
				$j('#viewNetrxAccBranchHeader input[type=text],#viewNetrxAccBranchHeader textarea').attr('readonly','readonly');	
				$j('#netrxAccbranchViewForm #netrxAccbranchvwbtnDone').hide();
				$j('#netrxAccbranchViewForm #netrxAccbranchvwbtnCancel').hide();
				$j('#netrxAccbranchViewForm #netrxAccbranchvwbtnEdit').show();
				showTip("Branch updated successfully");					
			}	
		else
			//alert(JSON.stringify(response.error));
			updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
		}
	});
