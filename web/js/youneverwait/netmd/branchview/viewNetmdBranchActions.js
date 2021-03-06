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
		createModal(constants_SuperadBillListModalJson,'SuperadBillListModal');	
		openModalBox(obj,'SuperadBillListModal');
		
		var pgNetMdList;
		var filterExp=[]; //For storing the filter expressions
		var pgTableName = "#superBillDetailsViewTable"; // Table showing netlims list
		pgTableContainer = "#viewBranchHeader"; // Parent container of the netlims list table
		setCustomDataTable(pgTableName);
		var maxRecords=0; // Total number of records
		var maxPages = 0; // Total number of pages
		var interval = 8;// Interval between pages
		var curPage = 1;//Current selected page	
		
		var netMddata;
		netMddata = '{'+'"name"' + ':"'+"netmdId"+'",';
		netMddata+='"value"' +':"' +$j('#netmdbranchViewForm #netmdid').val() +'",';
		netMddata +='"operator":"' + "eq" +'"}';
		
		var netMdBrchdata;
		netMdBrchdata = '{'+'"name"' + ':"'+"netmdBranchId"+'",';
		netMdBrchdata+='"value"' +':"' +$j('#netmdbranchViewForm #branchid').val()+'",';
		netMdBrchdata +='"operator":"' + "eq" +'"}';
		
		var fromData;
		fromData = '{'+'"name"' + ':"'+"orderDate"+'",';
		fromData+='"value"' +':"' +$j('#netmdbranchViewForm #BillfromDate').val()+'",';
		fromData +='"operator":"' + "ge" +'"}';
		
		var toData;
		toData = '{'+'"name"' + ':"'+"orderDate"+'",';
		toData+='"value"' +':"' +$j('#netmdbranchViewForm #BilltoDate').val()+'",';
		toData +='"operator":"' + "le" +'"}';
		filterExp=netMddata+","+netMdBrchdata+","+fromData+","+toData;
		
		
		var netmdListJson= getFilterlistUrl(filterExp,(curPage-1),interval);
		pgNetmdList = fillSuperNetmdEachBranchBillDetailTable(netmdListJson,pgTableName);
		if(pgNetmdList.count)
			maxRecords = pgNetmdList.count;	
		if(maxRecords%interval!=0)
			maxPages = parseInt(maxRecords/interval) + 1;
		else
			maxPages = parseInt(maxRecords/interval);	
		setPaginationFields(curPage, maxPages, pgTableContainer); 
		
		
		$j(pgTableContainer +' #next').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			netmdListJson=getFilterlistUrl(filterExp,startValue,interval);
			pgNetmdList=fillSuperNetmdEachBranchBillDetailTable(netmdListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			netmdListJson=getFilterlistUrl(filterExp,startValue,interval);
			pgNetmdList=fillSuperNetmdEachBranchBillDetailTable(netmdListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			netmdListJson= getFilterlistUrl(filterExp,startValue,interval);
			pgNetmdList=fillSuperNetmdEachBranchBillDetailTable(netmdListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			netmdListJson=getFilterlistUrl(filterExp,startValue,interval);
			pgNetmdList=fillSuperNetmdEachBranchBillDetailTable(netmdListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});	
		
	});
	
	function createMacJson(passPhrase,macid){
	
	var submitdata;
	var branchId =$j('#netmdbranchViewForm #branchid').val();
	var netmdId= $j('#netmdbranchViewForm #netmdid').val();
	
	submitdata = '{'+'"branchId"' + ':'+branchId+',';
	submitdata +='"headOfficeId":' +netmdId+',';
	submitdata +='"passPhrase":"' +passPhrase+'",';
	submitdata +='"macId":"' + macid+ '"}';
	
	return submitdata;
	} 