//up button click action
	$j("#GeneralPTBContainer #btn_up_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curBranchId = $j("#branchViewForm #branchid").val();
		var branchIdforView = getpreviousBranchId(curBranchId,pgBranchList);
		viewbranchInfo(branchIdforView,'#passphrasenetlimsViewTable');
	});
//down button click action
	$j("#GeneralPTBContainer #btn_down_ptb_id").die('click').live('click',function() {
		removeErrors();
		var curBranchId = $j("#branchViewForm #branchid").val();
		var branchIdforView = getnextBranchId(curBranchId,pgBranchList);
		viewbranchInfo(branchIdforView,'#passphrasenetlimsViewTable');	
	}); 
	
//back button click action
	$j("#GeneralPTBContainer #btn_back_ptb_id").die('click').live('click',function() {
		var netlimsId= $j("#branchViewForm #labid").val();
		branchlist(netlimsId);
		removeErrors();
		$j.cachedScript(constant_NetLimsBranchEntry_Url).done(function(script, textStatus) {
		})
	}); 

	$j('#branchViewForm #branchvwbtnCreate').die('click').live('click',function(){
		$j('#pageTitle').html("Edit Branch Details");
		validateNumber("#branchViewForm #branchPhone");
		validateNumber("#branchViewForm #branchMobile");
		removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').hide();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').hide();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').hide();
			
		$j('#branchViewForm #branchvwbtnCreate').hide();
		$j('#branchViewForm #branchvwbtnCancel').show();
		$j('#branchViewForm #branchvwbtnDone').show();
			
		$j('#viewBranchHeader input[type=text],#viewBranchHeader textarea').removeClass('newBox'); // make box 
		$j('#viewBranchHeader input[type=text],#viewBranchHeader textarea').removeAttr('readonly');
		$j('#viewBranchHeader #branchid').addClass('newBox');
		$j('#viewBranchHeader #branchid').attr('readonly','readonly');
		$j('#viewBranchHeader #labid').addClass('newBox');
		$j('#viewBranchHeader #labid').attr('readonly','readonly');
		$j('#viewBranchHeader #branchStatus').addClass('newBox');
		$j('#viewBranchHeader #branchStatus').attr('readonly','readonly');
		$j('#viewBranchHeader #passPhrase').addClass('newBox');
		$j('#viewBranchHeader #passPhrase').attr('readonly','readonly');
		clearNilFields(branchViewForm);
	});

//loading previous details while canceling update
	$j('#branchViewForm #branchvwbtnCancel').die('click').live('click',function(){
	removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').show();
		var branchid = $j("#branchViewForm #branchid").val();
		viewbranchInfo(branchid,'#passphrasenetlimsViewTable');
		$j('#pageTitle').html(constant_BranchView_Msg);
		$j('#viewBranchHeader input[type=text],#viewBranchHeader textarea').addClass('newBox');
		$j('#viewBranchHeader input[type=text],#viewBranchHeader textarea').attr('readonly','readonly');	

		$j('#branchViewForm #branchvwbtnDone').hide();
		$j('#branchViewForm #branchvwbtnCancel').hide();
		$j('#branchViewForm #branchvwbtnCreate').show();
	
	});	


//saving edited info
	$j("#branchViewForm #branchvwbtnDone").die('click').live('click',function(){
		removeErrors();
		$j('#GeneralPTBContainer #btn_up_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_down_ptb_id ').show();
		$j('#GeneralPTBContainer #btn_back_ptb_id ').show();
		var branchId = $j("#branchViewForm #branchid").val();
		if(validateviewbranch())
		{
			var response = submitBranchInfo();
			if(response.success==true) {
				branchInfo=setBranchInfo();
				viewbranchInfo(branchId,'#passphrasenetlimsViewTable');
				$j('#viewBranchHeader input[type=text],#viewBranchHeader textarea').addClass('newBox');
				$j('#viewBranchHeader input[type=text],#viewBranchHeader textarea').attr('readonly','readonly');	
				$j('#branchViewForm #branchvwbtnDone').hide();
				$j('#branchViewForm #branchvwbtnCancel').hide();
				$j('#branchViewForm #branchvwbtnCreate').show();
				$j('#pageTitle').html(constant_BranchView_Msg);
				showTip("Branch updated successfully");					
			}	
		else
			//alert(JSON.stringify(response.error));
			updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
		}
	});


	$j("#branchViewForm #btnShowOrders").die('click').live('click',function(){
		removeErrors();
		var obj=$j(this);
		createModal(constants_SuperadShowOrdersModalJson,'SuperadShowOrdersModal');	
		openModalBox(obj,'SuperadShowOrdersModal');
		
		var pgNetLimsList;
		var filterExp=[]; //For storing the filter expressions
		var pgTableName = "#orderssupernetlimsaccViewTable"; // Table showing netlims list
		pgTableContainer = "#viewBranchHeader"; // Parent container of the netlims list table
		setCustomDataTable(pgTableName);
		var maxRecords=0; // Total number of records
		var maxPages = 0; // Total number of pages
		var interval = 8;// Interval between pages
		var curPage = 1;//Current selected page	
		
		var netLimsdata;
		netLimsdata = '{'+'"name"' + ':"'+"labId"+'",';
		netLimsdata+='"value"' +':"' +$j('#branchViewForm #labid').val() +'",';
		netLimsdata +='"operator":"' + "eq" +'"}';
		
		var netLimsBrchdata;
		netLimsBrchdata = '{'+'"name"' + ':"'+"labBranchId"+'",';
		netLimsBrchdata+='"value"' +':"' +$j('#branchViewForm #branchid').val()+'",';
		netLimsBrchdata +='"operator":"' + "eq" +'"}';
		
		var fromData;
		fromData = '{'+'"name"' + ':"'+"orderDate"+'",';
		fromData+='"value"' +':"' +$j('#branchViewForm #fromDate').val()+'",';
		fromData +='"operator":"' + "ge" +'"}';
		
		var toData;
		toData = '{'+'"name"' + ':"'+"orderDate"+'",';
		toData+='"value"' +':"' +$j('#branchViewForm #toDate').val()+'",';
		toData +='"operator":"' + "le" +'"}';
		filterExp=netLimsdata+","+netLimsBrchdata+","+fromData+","+toData;
		
		var netlimsListJson= getFilterlistUrl(filterExp,(curPage-1),interval);
		pgNetLimsList = fillSuperNetlimsEachBranchorderTable(netlimsListJson,pgTableName);
		if(pgNetLimsList.count)
			maxRecords = pgNetLimsList.count;	
		if(maxRecords%interval!=0)
			maxPages = parseInt(maxRecords/interval) + 1;
		else
			maxPages = parseInt(maxRecords/interval);	
		setPaginationFields(curPage, maxPages, pgTableContainer); 
		
		$j(pgTableContainer +' #next').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			netlimsListJson=getFilterlistUrl(filterExp,startValue,interval);
			pgNetLimsList=fillSuperNetlimsEachBranchorderTable(netlimsListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			netlimsListJson=getFilterlistUrl(filterExp,startValue,interval);
			pgNetLimsList=fillSuperNetlimsEachBranchorderTable(netlimsListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			netlimsListJson= getFilterlistUrl(filterExp,startValue,interval);
			pgNetLimsList=fillSuperNetlimsEachBranchorderTable(netlimsListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			netlimsListJson=getFilterlistUrl(filterExp,startValue,interval);
			pgNetLimsList=fillSuperNetlimsEachBranchorderTable(netlimsListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});	
		
	});




//MacId Clearing
	$j(".clearMacid").die('click').live('click',function(){
		removeErrors();
		var obj=$j(this);
		createModal(constants_macIdConfirmJson,'macIdClearModal');	
		openModalBox(obj,'macIdClearModal');
		
		$j("#macidClearForm #btnClearMacYes").die('click').live('click',function(){
			var branchId = $j("#branchViewForm #branchid").val();
			var labId= $j("#branchViewForm #labid").val();
			resultMacJson=createMacJson();
			
				var response= postdataToServer("/youNeverWait/ws/ui/superAdmin/clearNetlimsMacId", resultMacJson );
				if(response.success==true) {
					viewbranchInfo(branchId,'#passphrasenetlimsViewTable');
					$j('#macIdClearModal').trigger('reveal:close');
					showTip("MacId Cleared");
				}	
				else
					
				updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
			
		});
		
		$j("#macidClearForm #btnClearMacNo").die('click').live('click',function(){
			$j('#macIdClearModal').trigger('reveal:close');
		});
	});

function createMacJson(){
	var submitdata;
	submitdata = '{'+'"globalId"' + ':'+$j('#branchViewForm #branchid').val() +',';
	submitdata +='"labId":' + $j('#branchViewForm #labid').val() + '}';
	
	return submitdata;
}

	
