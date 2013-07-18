//up button click action
	$j("#GeneralPTBContainer #btn_up_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curBranchId = $j("#netmdAccbranchViewForm #branchid").val();
		var branchIdforView = getpreviousBranchId(curBranchId,pgBranchList);
		viewNetmdAccBranchInfo(branchIdforView);
		//viewbranchInfo(branchIdforView);
	});
//down button click action
	$j("#GeneralPTBContainer #btn_down_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curBranchId = $j("#netmdAccbranchViewForm #branchid").val();
		var branchIdforView = getnextBranchId(curBranchId,pgBranchList);
		viewNetmdAccBranchInfo(branchIdforView);
		//viewbranchInfo(branchIdforView);	
	}); 


//back button click action
	$j("#GeneralPTBContainer #btn_back_ptb_id").die('click').live('click',function() {
		removeErrors();
		$j.cachedScript(constant_NetMdAccEntry_Url).done(function(script, textStatus) {
		})
	}); 
	
	$j('#netmdAccbranchViewForm #netmdAccbranchvwbtnEdit').die('click').live('click',function(){
		$j('#pageTitle').html("Edit Branch Details");
		validateNumber("#netmdAccbranchViewForm #branchPhone");
		validateNumber("#netmdAccbranchViewForm #branchMobile");
		removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').hide();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').hide();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').hide();
			
		$j('#netmdAccbranchViewForm #netmdAccbranchvwbtnEdit').hide();
		$j('#netmdAccbranchViewForm #netmdAccbranchvwbtnCancel').show();
		$j('#netmdAccbranchViewForm #netmdAccbranchvwbtnDone').show();
			
		$j('#viewNetmdAccBranchHeader input[type=text],#viewNetmdAccBranchHeader textarea').removeClass('newBox'); // make box 
		$j('#viewNetmdAccBranchHeader input[type=text],#viewNetmdAccBranchHeader textarea').removeAttr('readonly');
		$j('#viewNetmdAccBranchHeader #branchid').addClass('newBox');
		$j('#viewNetmdAccBranchHeader #branchid').attr('readonly','readonly');
		$j('#viewNetmdAccBranchHeader #netmdid').addClass('newBox');
		$j('#viewNetmdAccBranchHeader #netmdid').attr('readonly','readonly');
		$j('#viewNetmdAccBranchHeader #branchStatus').addClass('newBox');
		$j('#viewNetmdAccBranchHeader #branchStatus').attr('readonly','readonly');
		$j('#viewNetmdAccBranchHeader #devicesno').addClass('newBox');
		$j('#viewNetmdAccBranchHeader #devicesno').attr('readonly','readonly');
		clearNilFields(netmdAccbranchViewForm);
	});	
	
	//loading previous details while canceling update
	$j('#netmdAccbranchViewForm #netmdAccbranchvwbtnCancel').die('click').live('click',function(){
	removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').show();
		var branchid = $j("#netmdAccbranchViewForm #branchid").val();
		viewNetmdAccBranchInfo(branchid);
		$j('#viewNetmdAccBranchHeader input[type=text],#viewNetmdAccBranchHeader textarea').addClass('newBox');
		$j('#viewNetmdAccBranchHeader input[type=text],#viewNetmdAccBranchHeader textarea').attr('readonly','readonly');	

		$j('#netmdAccbranchViewForm #netmdAccbranchvwbtnDone').hide();
		$j('#netmdAccbranchViewForm #netmdAccbranchvwbtnCancel').hide();
		$j('#netmdAccbranchViewForm #netmdAccbranchvwbtnEdit').show();
	
	});	
	
	//saving edited info
	$j("#netmdAccbranchViewForm #netmdAccbranchvwbtnDone").die('click').live('click',function(){
		removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').show();
		var branchId = $j("#netmdAccbranchViewForm #branchid").val();
		if(validateviewnetmdAccbranch())
		{
			var response = submitNetmdAccBranchInfo();
			if(response.success==true) {
				viewNetmdAccBranchInfo(branchId);
				$j('#viewNetmdAccBranchHeader input[type=text],#viewNetmdAccBranchHeader textarea').addClass('newBox');
				$j('#viewNetmdAccBranchHeader input[type=text],#viewNetmdAccBranchHeader textarea').attr('readonly','readonly');	
				$j('#netmdAccbranchViewForm #netmdAccbranchvwbtnDone').hide();
				$j('#netmdAccbranchViewForm #netmdAccbranchvwbtnCancel').hide();
				$j('#netmdAccbranchViewForm #netmdAccbranchvwbtnEdit').show();
				showTip("Branch updated successfully");					
			}	
		else
			//alert(JSON.stringify(response.error));
			updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
		}
	});
