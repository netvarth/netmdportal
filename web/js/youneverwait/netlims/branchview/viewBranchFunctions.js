function viewBranch(branchId,tableObj) {
	$j('#pageTitle').html(constant_BranchView_Msg);
	$j('#filter').hide();
	$j('#tabs-1').html("");
	$j('#filterToolBar-Container').html("");
	$j('#pageToolBar-Container').html("");
	//Creating Page Tool Bar
	var ptbContainer = $j('<div id="GeneralPTBContainer" />');
	var ptbdata =getRequestData('/youNeverWait/json/toolbars/viewBranchGeneralToolbar1.json');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).html(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
	var viewdata = getRequestData('/youNeverWait/json/view/viewbranchdetails.json');
	var contentForm = new form(viewdata);
	$j('#tabs-1').html(contentForm.result);	
	viewbranchInfo(branchId,tableObj);
	$j("#branchViewForm #fromDate").datepicker();
	$j("#branchViewForm #toDate").datepicker();
}


function viewbranchInfo(branchId,tableObj) {
	var branchInfo = getBranchData(branchId);
	//alert(JSON.stringify(branchInfo));
	if(branchInfo.branch.globalId)
		$j("#branchViewForm #branchid ").val(branchInfo.branch.globalId);
	else
		$j("#branchViewForm #branchid ").val("Nil");
	if(branchInfo.branch.labId)
		$j("#branchViewForm #labid ").val(branchInfo.branch.labId);
	else
		$j("#branchViewForm #labid ").val("Nil");	
	if(branchInfo.branch.name)
		$j("#branchViewForm #organizationName").val(branchInfo.branch.name);
	else
		$j("#branchViewForm #organizationName").val('Nil');
	if(branchInfo.branch.branchCode)
		$j("#branchViewForm #branchcode").val(branchInfo.branch.branchCode);
	else
		$j("#branchViewForm #branchcode").val('Nil');
	if(branchInfo.branch.phone)
		$j("#branchViewForm #branchPhone").val(branchInfo.branch.phone);
	else
		$j("#branchViewForm #branchPhone").val('Nil');
	if(branchInfo.branch.mobile)
		$j("#branchViewForm #branchMobile").val(branchInfo.branch.mobile);
	else
		$j("#branchViewForm #branchMobile").val('Nil');
	if(branchInfo.branch.address)
		$j("#branchViewForm #address").val(br2nl(branchInfo.branch.address));
	else 
		$j("#branchViewForm #address").val('Nil');
	if(branchInfo.branch.email)
		$j("#branchViewForm #email").val(branchInfo.branch.email);
	else 
		$j("#branchViewForm #email").val('Nil');	
	if(branchInfo.branch.status)
		$j("#branchViewForm #branchStatus").val(branchInfo.branch.status);
	else 
		$j("#branchViewForm #branchStatus").val('Nil');	
		makeDataTable('#passphrasenetlimsViewTable');
		viewPassPhraseTablenetlims(branchInfo,tableObj);	
	/*if(branchInfo.branch.passPhrase)
		$j("#branchViewForm #passPhrase").val(branchInfo.branch.passPhrase);
	else 
		$j("#branchViewForm #passPhrase").val('Nil');	
	 if(branchInfo.branch.macId)
		$j("#branchViewForm #macId").val(branchInfo.branch.macId);
	else 
		$j("#branchViewForm #macId").val('Nil'); */			

}

function superBrachOrderslist() {
		
	var submitdata;
	submitdata = '{'+'"fromDate"' + ':"'+$j('#branchViewForm #fromDate').val()+'",';
	submitdata+='"toDate"' +':"' +$j('#branchViewForm #toDate').val() +'",';
	submitdata +='"labId":' + $j('#branchViewForm #labid').val() + ',';
	submitdata +='"labBranchId":' + $j('#branchViewForm #branchid').val() + '}';
	//var response = postdataToServer("/youNeverWait/ws/ui/lab/orderList",submitdata);
	//alert(JSON.stringify(response));
	return submitdata;
}

function fillSuperNetlimsEachBranchorderTable(branchorder,obj) {
	
	var branchResult=postdataToServer("/youNeverWait/ws/ui/superAdmin/orderList",branchorder);
	//alert(JSON.stringify(branchResult));
	if(branchResult.success==true) {
		createModal(constants_SuperadShowOrdersModalJson,'SuperadShowOrdersModal');	
		openModalBox(obj,'SuperadShowOrdersModal');
		var tableObj="#orderssupernetlimsaccViewTable";
		makeDataTable(tableObj);
		$j(tableObj).dataTable().fnClearTable();
			if(branchResult.branchOrders.length>0) {			
				$j(branchResult.branchOrders).each(function (index, lab) {
					var id=lab.branchId;
					var rowData=$j(tableObj).dataTable().fnAddData([lab.orderDate,lab.totalOrders,lab.netAmount,lab.paidAmount,lab.lastOrderdTime]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);	
					//$j(row).children("td:nth-child(1)").attr("class","netlimsIdCol Ustyle");
					});	
			}
	}
	else
		updateTipsNew(getErrorName(branchResult.error),$j('#errorDivData'),$j('#errorDivHeader'));
	
	return branchResult;
}

function makeDataTable(tableObj) {
	$j(tableObj).dataTable( {
		"sPaginationType": "full_numbers",
		"bFilter":false,
		"bInfo":false,
		"bPaginate":false,
		"bSort": false,
		"bDestroy": true,
		"bAutoWidth": false,
		"sDom": '<"top"Hip>'
	});
}	



function submitBranchInfo(){
	var resultJson = createSubmitJson();
	//alert(resultJson);
	var response = postdataToServer("/youNeverWait/ws/ui/superAdmin/updateBranch", resultJson );
	//alert(JSON.stringify(response));
	return response;
}



function createSubmitJson(){
	var branchname;
	branchname=$j('#branchViewForm #organizationName').val();
	branchname=branchname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var submitdata;
	submitdata = '{'+'"name"' + ':"'+branchname+'",';
	submitdata+='"globalId"' +':' + $j('#branchViewForm #branchid').val() +',';
	submitdata+='"phone"' +':"' + $j('#branchViewForm #branchPhone').val() +'",';
	submitdata+='"mobile"' +':"' + $j('#branchViewForm #branchMobile').val() +'",';
	submitdata+='"address"' +':"' +nl2br($j('#branchViewForm #address').val()) +'",';
	//submitdata +='"branchCode":"' + $j('#branchViewForm #branchcode').val() + '",';
	submitdata +='"labId":' + $j('#branchViewForm #labid').val() + ',';
	submitdata +='"status":"' + $j('#branchViewForm #branchStatus').val() + '",';
	submitdata +='"passPhrase":"' + $j('#branchViewForm #passPhrase').val() + '",';
	//submitdata +='"macId":' + $j('#branchViewForm #macId').val() + ',';
	submitdata +='"macId":"'  + '",';
	submitdata +='"email":"' + $j('#branchViewForm #email').val() + '"}';
	
	return submitdata;
}

function viewPassPhraseTablenetlims(branchInfo,tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	$j(branchInfo.branch).each(function (index,branchInfo) {
	var myData='<input type="button" value="clear" class="clearMacid stdbtn">';
	
		
		
		if(branchInfo.macId==null)
		{		var rowData=$j(tableObj).dataTable().fnAddData([branchInfo.passPhrase,'Nil',myData]);
			var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
			$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		else
		{
		var rowData=$j(tableObj).dataTable().fnAddData([branchInfo.passPhrase,branchInfo.macId,myData]);
		var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
		$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		
		
	});
}

function makeDataTable(tableObj) {
	$j(tableObj).dataTable( {
		"sPaginationType": "full_numbers",
		"bFilter":false,
		"bInfo":false,
		"bPaginate":false,
		"bSort": false,
		"bDestroy": true,
		"bAutoWidth": false,
		"sDom": '<"top"Hip>'
	});
}

/* function to get the  previous netlimsid from the netlimslist json */
function getpreviousBranchId(branchid, branchResult) {
	var brnchId;
	$j(branchResult.branch).each(function (index, rowBranch) {
		if(branchid==rowBranch.globalId)	{
			var arrayLength=(branchResult.branch).length;
			var comp=arrayLength-1;
			if(index==0)
				brnchId = branchid;
			else
				brnchId=branchResult.branch[index-1].globalId;
		}
	});
	return brnchId;	
}


/* function to get the  next netlimsid from the netlimslist json */
function getnextBranchId(branchid, branchResult) {
	var brnchId;
	$j(branchResult.branch).each(function (index, rowBranch) {
		if(branchid==rowBranch.globalId)	{
			var arrayLength=(branchResult.branch).length;
			var comp=arrayLength-1;
			if(index==comp){
				brnchId = branchid;
			}
			else{
				brnchId=branchResult.branch[index+1].globalId;
			}	
		}
	});	
	return brnchId;	
}	

function setBranchInfo() {
	var branchId = $j("#branchViewForm #branchid").val();
	return getBranchData(branchId);
}

