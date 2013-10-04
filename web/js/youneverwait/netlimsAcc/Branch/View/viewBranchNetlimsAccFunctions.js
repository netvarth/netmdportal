
 function viewNetlimsAccBranch(branchId)
 {
 	$j('#pageTitle').html(constant_BranchNetlimsAccView_Msg);
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
	var viewdata = getRequestData('/youNeverWait/json/view/viewNetlimsAccBranchDetails.json');
	var contentForm = new form(viewdata);
	$j('#tabs-1').html(contentForm.result);	
	viewNetlimaAccBranchInfo(branchId);
	$j("#branchNetlimsAccViewForm #fromDate").datepicker();
	$j("#branchNetlimsAccViewForm #toDate").datepicker();
 }
 
 
 
function viewNetlimaAccBranchInfo(branchId) {
	var branchInfo = getBranchNetlimsAccData(branchId);
	//alert(JSON.stringify(branchInfo));
	if(branchInfo.branch.globalId)
		$j("#branchNetlimsAccViewForm #branchid ").val(branchInfo.branch.globalId);
	else
		$j("#branchNetlimsAccViewForm #branchid ").val("Nil");
	if(branchInfo.branch.labId)
		$j("#branchNetlimsAccViewForm #labid ").val(branchInfo.branch.labId);
	else
		$j("#branchNetlimsAccViewForm #labid ").val("Nil");	
	if(branchInfo.branch.name)
		$j("#branchNetlimsAccViewForm #organizationName").val(branchInfo.branch.name);
	else
		$j("#branchNetlimsAccViewForm #organizationName").val('Nil');
		if(branchInfo.branch.branchCode)
		$j("#branchNetlimsAccViewForm #accbranchcode").val(branchInfo.branch.branchCode);
	else
		$j("#branchNetlimsAccViewForm #accbranchcode").val('Nil');
	
	if(branchInfo.branch.phone)
		$j("#branchNetlimsAccViewForm #branchPhone").val(branchInfo.branch.phone);
	else
		$j("#branchNetlimsAccViewForm #branchPhone").val('Nil');
	if(branchInfo.branch.mobile)
		$j("#branchNetlimsAccViewForm #branchMobile").val(branchInfo.branch.mobile);
	else
		$j("#branchNetlimsAccViewForm #branchMobile").val('Nil');
	if(branchInfo.branch.address)
		$j("#branchNetlimsAccViewForm #address").val(br2nl(branchInfo.branch.address));
	else 
		$j("#branchNetlimsAccViewForm #address").val('Nil');
	if(branchInfo.branch.email)
		$j("#branchNetlimsAccViewForm #email").val(branchInfo.branch.email);
	else 
		$j("#branchNetlimsAccViewForm #email").val('Nil');	
	if(branchInfo.branch.status)
		$j("#branchNetlimsAccViewForm #branchStatus").val(branchInfo.branch.status);
	else 
		$j("#branchNetlimsAccViewForm #branchStatus").val('Nil');	
	if(branchInfo.branch.passPhrase)
		$j("#branchNetlimsAccViewForm #passPhrase").val(branchInfo.branch.passPhrase);
	else 
		$j("#branchNetlimsAccViewForm #passPhrase").val('Nil');	
	if(branchInfo.branch.macId)
		$j("#branchNetlimsAccViewForm #macId").val(branchInfo.branch.macId);
	else 
		$j("#branchNetlimsAccViewForm #macId").val('Nil');			

}
function BrachOrderslist() {
		
	var submitdata;
	submitdata = '{'+'"fromDate"' + ':"'+$j('#branchNetlimsAccViewForm #fromDate').val()+'",';
	submitdata+='"toDate"' +':"' +$j('#branchNetlimsAccViewForm #toDate').val() +'",';
	submitdata +='"labId":' + $j('#branchNetlimsAccViewForm #labid').val() + ',';
	submitdata +='"labBranchId":' + $j('#branchNetlimsAccViewForm #branchid').val() + '}';
	//var response = postdataToServer("/youNeverWait/ws/ui/lab/orderList",submitdata);
	//alert(JSON.stringify(response));
	return submitdata;
}

function fillNetlimsEachBranchorderTable(branchorder,obj) {
	var branchResult=postdataToServer("/youNeverWait/ws/ui/lab/orderList",branchorder);
	//alert(JSON.stringify(branchResult));
	if(branchResult.success==true) {
		createModal(constants_ShowOrdersModalJson,'ShowOrdersModal');	
		openModalBox(obj,'ShowOrdersModal');
		var tableObj="#ordersnetlimsaccViewTable";
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

function submitNetlimsAccBranchInfo(){
	var resultJson = createSubmitJson();
	//alert(resultJson);
	var response = postdataToServer("/youNeverWait/ws/ui/lab/updateBranch", resultJson );
	//alert(JSON.stringify(response));
	return response;
}



function createSubmitJson(){
	var branchname;
	branchname=$j('#branchNetlimsAccViewForm #organizationName').val();
	branchname=branchname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var submitdata;
	submitdata = '{'+'"name"' + ':"'+branchname+'",';
	submitdata+='"globalId"' +':' + $j('#branchNetlimsAccViewForm #branchid').val() +',';
	submitdata+='"phone"' +':"' + $j('#branchNetlimsAccViewForm #branchPhone').val() +'",';
	submitdata+='"mobile"' +':"' + $j('#branchNetlimsAccViewForm #branchMobile').val() +'",';
	//submitdata+='"branchCode"' +':"' + $j('#branchNetlimsAccViewForm #accbranchcode').val() +'",';
	submitdata+='"address"' +':"' +nl2br($j('#branchNetlimsAccViewForm #address').val()) +'",';
	submitdata +='"labId":' + $j('#branchNetlimsAccViewForm #labid').val() + ',';
	submitdata +='"status":"' + $j('#branchNetlimsAccViewForm #branchStatus').val() + '",';
	submitdata +='"passPhrase":"' + $j('#branchNetlimsAccViewForm #passPhrase').val() + '",';
	//submitdata +='"macId":' + $j('#branchNetlimsAccViewForm #macId').val() + ',';
	submitdata +='"macId":"'  + '",';
	submitdata +='"email":"' + $j('#branchNetlimsAccViewForm #email').val() + '"}';
	
	return submitdata;
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

function setNetlimsAccBranchInfo() {
	var branchId = $j("#branchNetlimsAccViewForm #branchid").val();
	return getBranchNetlimsAccData(branchId);
}
