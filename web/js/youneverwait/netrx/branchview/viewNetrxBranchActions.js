//up button click action
	$j("#GeneralnetrxPTBContainer #btn_up_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curBranchId = $j("#netrxbranchViewForm #branchid").val();
		var branchIdforView = getpreviousBranchId(curBranchId,pgBranchList);
		viewnetrxbranchInfo(branchIdforView,'#passphraseViewTable','#passphrasePrimaryViewTable');
		//viewbranchInfo(branchIdforView);
	});
//down button click action
	$j("#GeneralnetrxPTBContainer #btn_down_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curBranchId = $j("#netrxbranchViewForm #branchid").val();
		var branchIdforView = getnextBranchId(curBranchId,pgBranchList);
		viewnetrxbranchInfo(branchIdforView,'#passphraseViewTable','#passphrasePrimaryViewTable');
		//viewbranchInfo(branchIdforView);	
	}); 


//back button click action
	$j("#GeneralnetrxPTBContainer #btn_back_ptb_id").die('click').live('click',function() {
		var netrxId= $j("#netrxbranchViewForm #netrxid").val();
		branchlist(netrxId);
		removeErrors();
	}); 
	
	$j('#netrxbranchViewForm #netrxbranchvwbtnEdit').die('click').live('click',function(){
		$j('#pageTitle').html("Edit Branch Details");
		validateNumber("#netrxbranchViewForm #branchPhone");
		validateNumber("#netrxbranchViewForm #branchMobile");
		removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').hide();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').hide();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').hide();
			
		$j('#netrxbranchViewForm #netrxbranchvwbtnEdit').hide();
		$j('#netrxbranchViewForm #netrxbranchvwbtnCancel').show();
		$j('#netrxbranchViewForm #netrxbranchvwbtnDone').show();
			
		$j('#viewNetrxBranchHeader input[type=text],#viewNetrxBranchHeader textarea').removeClass('newBox'); // make box 
		$j('#viewNetrxBranchHeader input[type=text],#viewNetrxBranchHeader textarea').removeAttr('readonly');
		$j('#viewNetrxBranchHeader #branchid').addClass('newBox');
		$j('#viewNetrxBranchHeader #branchid').attr('readonly','readonly');
		$j('#viewNetrxBranchHeader #netrxid').addClass('newBox');
		$j('#viewNetrxBranchHeader #netrxid').attr('readonly','readonly');
		$j('#viewNetrxBranchHeader #branchStatus').addClass('newBox');
		$j('#viewNetrxBranchHeader #branchStatus').attr('readonly','readonly');
		$j('#viewNetrxBranchHeader #devicesno').addClass('newBox');
		$j('#viewNetrxBranchHeader #devicesno').attr('readonly','readonly');
		clearNilFields(netrxbranchViewForm);
	});	
	
	//loading previous details while canceling update
	$j('#netrxbranchViewForm #netrxbranchvwbtnCancel').die('click').live('click',function(){
	removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').show();
		var branchid = $j("#netrxbranchViewForm #branchid").val();
		viewnetrxbranchInfo(branchid,'#passphraseViewTable','#passphrasePrimaryViewTable');
		$j('#pageTitle').html(constant_NetrxBranchView_Msg);
		$j('#viewNetrxBranchHeader input[type=text],#viewNetrxBranchHeader textarea').addClass('newBox');
		$j('#viewNetrxBranchHeader input[type=text],#viewNetrxBranchHeader textarea').attr('readonly','readonly');	

		$j('#netrxbranchViewForm #netrxbranchvwbtnDone').hide();
		$j('#netrxbranchViewForm #netrxbranchvwbtnCancel').hide();
		$j('#netrxbranchViewForm #netrxbranchvwbtnEdit').show();
	
	});	
	
	//saving edited info
	$j("#netrxbranchViewForm #netrxbranchvwbtnDone").die('click').live('click',function(){
		removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').show();
		var branchId = $j("#netrxbranchViewForm #branchid").val();
		if(validateviewnetrxbranch())
		{
			var response = submitBranchInfo();
			if(response.success==true) {
				viewnetrxbranchInfo(branchId,'#passphraseViewTable','#passphrasePrimaryViewTable');
				$j('#viewNetrxBranchHeader input[type=text],#viewNetrxBranchHeader textarea').addClass('newBox');
				$j('#viewNetrxBranchHeader input[type=text],#viewNetrxBranchHeader textarea').attr('readonly','readonly');	
				$j('#netrxbranchViewForm #netrxbranchvwbtnDone').hide();
				$j('#netrxbranchViewForm #netrxbranchvwbtnCancel').hide();
				$j('#netrxbranchViewForm #netrxbranchvwbtnEdit').show();
				$j('#pageTitle').html(constant_NetrxBranchView_Msg);
				showTip("Branch updated successfully");					
			}	
		else
			//alert(JSON.stringify(response.error));
			updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
		}
	});

	/* $j(".clearMacid").die('click').live('click',function(){
		//alert("hii");
		var passPhrase=$j(this).closest('tr').children('td:nth-child(1)').text();
		var macid=$j(this).closest('tr').children('td:nth-child(2)').text();
		var	resultMacJson=createMacJson(passPhrase,macid);
			//alert(resultMacJson);
				var response= postdataToServer("/youNeverWait/ws/ui/superAdmin/clearNetMdMacId", resultMacJson );
				if(response.success==true) {
					var branchid = $j("#netrxbranchViewForm #branchid").val();
					viewnetrxbranchInfo(branchid,'#passphraseViewTable','#passphrasePrimaryViewTable');
					showTip("MacId Cleared");
				}	
				else
					updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
				
	}); */
	
	/* $j(".chngprimdevice").die('click').live('click',function(){
		//alert("hii");
		var passPhrase=$j(this).closest('tr').children('td:nth-child(1)').text();
		var macid=$j(this).closest('tr').children('td:nth-child(2)').text();
		var	resultMacJson=createMacJson(passPhrase,macid);
			//alert(resultMacJson);
				var response= postdataToServer("/youNeverWait/ws/ui/superAdmin/makePrimary", resultMacJson );
				//alert(JSON.stringify(response));
				if(response.success==true) {
					var branchid = $j("#netrxbranchViewForm #branchid").val();
					viewnetrxbranchInfo(branchid,'#passphraseViewTable','#passphrasePrimaryViewTable');
					showTip("Primary Device Changed");
				}	
				else
					updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
				
	}); */
	
	
	/* function createMacJson(passPhrase,macid){
	
	var submitdata;
	var branchId =$j('#netrxbranchViewForm #branchid').val();
	var netrxId= $j('#netrxbranchViewForm #netrxid').val();
	
	submitdata = '{'+'"netMdBranchId"' + ':'+branchId+',';
	submitdata +='"netRxId":' +netrxId+',';
	submitdata +='"passPhrase":"' +passPhrase+'",';
	submitdata +='"macId":"' + macid+ '"}';
	
	return submitdata;
	}  */