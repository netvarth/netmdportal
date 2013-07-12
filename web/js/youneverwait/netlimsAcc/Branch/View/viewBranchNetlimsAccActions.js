
//up button click action
	$j("#GeneralPTBContainer #btn_up_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curBranchId = $j("#branchNetlimsAccViewForm #branchid").val();
		var branchIdforView = getpreviousBranchId(curBranchId,pgBranchList);
		viewNetlimaAccBranchInfo(branchIdforView);
	});
//down button click action
	$j("#GeneralPTBContainer #btn_down_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curBranchId = $j("#branchNetlimsAccViewForm #branchid").val();
		var branchIdforView = getnextBranchId(curBranchId,pgBranchList);
		viewNetlimaAccBranchInfo(branchIdforView);	
	}); 
	
//back button click action
	$j("#GeneralPTBContainer #btn_back_ptb_id").die('click').live('click',function() {
		//var netlimsId= $j("#branchNetlimsAccViewForm #labid").val();
		//branchlist(netlimsId);
		removeErrors();
		$j.cachedScript(constant_NetLimsAccEntry_Url).done(function(script, textStatus) {
		})
	}); 

	$j('#branchNetlimsAccViewForm #branchvwbtnCreate').die('click').live('click',function(){
		$j('#pageTitle').html("Edit Branch Details");
		validateNumber("#branchNetlimsAccViewForm #branchPhone");
		validateNumber("#branchNetlimsAccViewForm #branchMobile");
		removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').hide();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').hide();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').hide();
			
		$j('#branchNetlimsAccViewForm #branchvwbtnCreate').hide();
		$j('#branchNetlimsAccViewForm #branchvwbtnCancel').show();
		$j('#branchNetlimsAccViewForm #branchvwbtnDone').show();
			
		$j('#viewNetlimsAccBranchHeader input[type=text],#viewNetlimsAccBranchHeader textarea').removeClass('newBox'); // make box 
		$j('#viewNetlimsAccBranchHeader input[type=text],#viewNetlimsAccBranchHeader textarea').removeAttr('readonly');
		$j('#viewNetlimsAccBranchHeader #branchid').addClass('newBox');
		$j('#viewNetlimsAccBranchHeader #branchid').attr('readonly','readonly');
		$j('#viewNetlimsAccBranchHeader #labid').addClass('newBox');
		$j('#viewNetlimsAccBranchHeader #labid').attr('readonly','readonly');
		$j('#viewNetlimsAccBranchHeader #branchStatus').addClass('newBox');
		$j('#viewNetlimsAccBranchHeader #branchStatus').attr('readonly','readonly');
		$j('#viewNetlimsAccBranchHeader #passPhrase').addClass('newBox');
		$j('#viewNetlimsAccBranchHeader #passPhrase').attr('readonly','readonly');
		$j('#viewNetlimsAccBranchHeader #macId').addClass('newBox');
		$j('#viewNetlimsAccBranchHeader #macId').attr('readonly','readonly');
		clearNilFields(branchNetlimsAccViewForm);
		if($j('#branchNetlimsAccViewForm #macId').val()=='')
		$j('#branchNetlimsAccViewForm #macId').val("Nil")
		
	});

//loading previous details while canceling update
	$j('#branchNetlimsAccViewForm #branchvwbtnCancel').die('click').live('click',function(){
	removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').show();
		var branchid = $j("#branchNetlimsAccViewForm #branchid").val();
		viewNetlimaAccBranchInfo(branchid);
		$j('#viewNetlimsAccBranchHeader input[type=text],#viewNetlimsAccBranchHeader textarea').addClass('newBox');
		$j('#viewNetlimsAccBranchHeader input[type=text],#viewNetlimsAccBranchHeader textarea').attr('readonly','readonly');	

		$j('#branchNetlimsAccViewForm #branchvwbtnDone').hide();
		$j('#branchNetlimsAccViewForm #branchvwbtnCancel').hide();
		$j('#branchNetlimsAccViewForm #branchvwbtnCreate').show();
	
	});	


//saving edited info
	$j("#branchNetlimsAccViewForm #branchvwbtnDone").die('click').live('click',function(){
		removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').show();
		var branchId = $j("#branchNetlimsAccViewForm #branchid").val();
		if(validateviewnetlimsaccbranch())
		{
			var response = submitNetlimsAccBranchInfo();
			if(response.success==true) {
				branchInfo=setNetlimsAccBranchInfo();
				viewNetlimaAccBranchInfo(branchId);
				$j('#viewNetlimsAccBranchHeader input[type=text],#viewNetlimsAccBranchHeader textarea').addClass('newBox');
				$j('#viewNetlimsAccBranchHeader input[type=text],#viewNetlimsAccBranchHeader textarea').attr('readonly','readonly');	
				$j('#branchNetlimsAccViewForm #branchvwbtnDone').hide();
				$j('#branchNetlimsAccViewForm #branchvwbtnCancel').hide();
				$j('#branchNetlimsAccViewForm #branchvwbtnCreate').show();
				showTip("Branch updated successfully");					
			}	
		else
			//alert(JSON.stringify(response.error));
			updateTipsNew(getErrorName(response.error),$j('#errorDivvwNetlimsAccData'),$j('#errorDivHeader'));
		}
	});
	$j("#branchNetlimsAccViewForm #btnShowOrders").die('click').live('click',function(){
		removeErrors();
		var obj=$j(this);
		
		var branchorder=BrachOrderslist();
		fillNetlimsEachBranchorderTable(branchorder,obj)
	});





	
