//up button click action
	$j("#GeneralPTBContainer #btn_up_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curBranchId = $j("#netmdbranchViewForm #branchid").val();
		var branchIdforView = getpreviousBranchId(curBranchId,pgBranchList);
		viewnetmdbranchInfo(branchIdforView,'#passphraseViewTable','#passphrasePrimaryViewTable');
		//viewbranchInfo(branchIdforView);
	});
//down button click action
	$j("#GeneralPTBContainer #btn_down_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curBranchId = $j("#netmdbranchViewForm #branchid").val();
		var branchIdforView = getnextBranchId(curBranchId,pgBranchList);
		viewnetmdbranchInfo(branchIdforView,'#passphraseViewTable','#passphrasePrimaryViewTable');
		//viewbranchInfo(branchIdforView);	
	}); 


//back button click action
	$j("#GeneralPTBContainer #btn_back_ptb_id").die('click').live('click',function() {
		var netmdId= $j("#netmdbranchViewForm #netmdid").val();
		branchlist(netmdId);
		removeErrors();
	}); 
	
	$j('#netmdbranchViewForm #netmdbranchvwbtnEdit').die('click').live('click',function(){
		$j('#pageTitle').html("Edit Branch Details");
		validateNumber("#netmdbranchViewForm #branchPhone");
		validateNumber("#netmdbranchViewForm #branchMobile");
		removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').hide();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').hide();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').hide();
			
		$j('#netmdbranchViewForm #netmdbranchvwbtnEdit').hide();
		$j('#netmdbranchViewForm #netmdbranchvwbtnCancel').show();
		$j('#netmdbranchViewForm #netmdbranchvwbtnDone').show();
			
		$j('#viewNetmdBranchHeader input[type=text],#viewNetmdBranchHeader textarea').removeClass('newBox'); // make box 
		$j('#viewNetmdBranchHeader input[type=text],#viewNetmdBranchHeader textarea').removeAttr('readonly');
		$j('#viewNetmdBranchHeader #branchid').addClass('newBox');
		$j('#viewNetmdBranchHeader #branchid').attr('readonly','readonly');
		$j('#viewNetmdBranchHeader #netmdid').addClass('newBox');
		$j('#viewNetmdBranchHeader #netmdid').attr('readonly','readonly');
		$j('#viewNetmdBranchHeader #branchStatus').addClass('newBox');
		$j('#viewNetmdBranchHeader #branchStatus').attr('readonly','readonly');
		$j('#viewNetmdBranchHeader #devicesno').addClass('newBox');
		$j('#viewNetmdBranchHeader #devicesno').attr('readonly','readonly');
		clearNilFields(netmdbranchViewForm);
	});	
	
	//loading previous details while canceling update
	$j('#netmdbranchViewForm #netmdbranchvwbtnCancel').die('click').live('click',function(){
	removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').show();
		var branchid = $j("#netmdbranchViewForm #branchid").val();
		viewnetmdbranchInfo(branchid,'#passphraseViewTable','#passphrasePrimaryViewTable');
		$j('#pageTitle').html(constant_NetmdBranchView_Msg);
		$j('#viewNetmdBranchHeader input[type=text],#viewNetmdBranchHeader textarea').addClass('newBox');
		$j('#viewNetmdBranchHeader input[type=text],#viewNetmdBranchHeader textarea').attr('readonly','readonly');	

		$j('#netmdbranchViewForm #netmdbranchvwbtnDone').hide();
		$j('#netmdbranchViewForm #netmdbranchvwbtnCancel').hide();
		$j('#netmdbranchViewForm #netmdbranchvwbtnEdit').show();
	
	});	
	
	//saving edited info
	$j("#netmdbranchViewForm #netmdbranchvwbtnDone").die('click').live('click',function(){
		removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').show();
		var branchId = $j("#netmdbranchViewForm #branchid").val();
		if(validateviewnetmdbranch())
		{
			var response = submitBranchInfo();
			if(response.success==true) {
				viewnetmdbranchInfo(branchId,'#passphraseViewTable','#passphrasePrimaryViewTable');
				$j('#viewNetmdBranchHeader input[type=text],#viewNetmdBranchHeader textarea').addClass('newBox');
				$j('#viewNetmdBranchHeader input[type=text],#viewNetmdBranchHeader textarea').attr('readonly','readonly');	
				$j('#netmdbranchViewForm #netmdbranchvwbtnDone').hide();
				$j('#netmdbranchViewForm #netmdbranchvwbtnCancel').hide();
				$j('#netmdbranchViewForm #netmdbranchvwbtnEdit').show();
				$j('#pageTitle').html(constant_NetmdBranchView_Msg);
				showTip("Branch updated successfully");					
			}	
		else
			//alert(JSON.stringify(response.error));
			updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
		}
	});

	$j(".clearMacid").die('click').live('click',function(){
		removeErrors();
		var obj=$j(this);
		createModal(constants_netmdmacIdConfirmJson,'netmdmacIdClearModal');	
		openModalBox(obj,'netmdmacIdClearModal');
		$j("#netmdmacidClearForm #btnClearMacYes").die('click').live('click',function(){
		 var passPhrase=obj.closest('tr').children('td:nth-child(1)').text();
		var macid=obj.closest('tr').children('td:nth-child(2)').text();
		var	resultMacJson=createMacJson(passPhrase,macid);
			//alert(resultMacJson);
				var response= postdataToServer("/youNeverWait/ws/ui/superAdmin/clearNetMdMacId", resultMacJson );
				if(response.success==true) {
					var branchid = $j("#netmdbranchViewForm #branchid").val();
					viewnetmdbranchInfo(branchid,'#passphraseViewTable','#passphrasePrimaryViewTable');
					showTip("MacId Cleared");
					$j('#netmdmacIdClearModal').trigger('reveal:close');
				}	
				else
					updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
				 
			 });
			 
		$j("#netmdmacidClearForm #btnClearMacNo").die('click').live('click',function(){
			$j('#netmdmacIdClearModal').trigger('reveal:close');
		});
	});
	
	$j(".chngprimdevice").die('click').live('click',function(){
		//alert("hii");
		var passPhrase=$j(this).closest('tr').children('td:nth-child(1)').text();
		var macid=$j(this).closest('tr').children('td:nth-child(2)').text();
		var	resultMacJson=createMacJson(passPhrase,macid);
			//alert(resultMacJson);
				var response= postdataToServer("/youNeverWait/ws/ui/superAdmin/makePrimary", resultMacJson );
				//alert(JSON.stringify(response));
				if(response.success==true) {
					var branchid = $j("#netmdbranchViewForm #branchid").val();
					viewnetmdbranchInfo(branchid,'#passphraseViewTable','#passphrasePrimaryViewTable');
					showTip("Primary Device Changed");
				}	
				else
					updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
				
	});
	
	$j("#netmdbranchViewForm #btnBill").die('click').live('click',function(){
		removeErrors();
		var obj=$j(this);
		var branchBill=superBillDetaillist();
		//alert(branchBill);
		fillSuperNetmdEachBranchBillDetailTable(branchBill,obj)
	});
	
	function createMacJson(passPhrase,macid){
	
	var submitdata;
	var branchId =$j('#netmdbranchViewForm #branchid').val();
	var netmdId= $j('#netmdbranchViewForm #netmdid').val();
	
	submitdata = '{'+'"netMdBranchId"' + ':'+branchId+',';
	submitdata +='"netMdId":' +netmdId+',';
	submitdata +='"passPhrase":"' +passPhrase+'",';
	submitdata +='"macId":"' + macid+ '"}';
	
	return submitdata;
	} 