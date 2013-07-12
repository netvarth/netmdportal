$j('#pageTitle').html(constant_NetLimsAccBrachorder_Msg);
//$j.cachedScript(constant_NetLimsAccFunctions_Url).done(function(script, textStatus) {
//})

$j(document).ready(function() {
	//var userdata =getRequestData('/youNeverWait/ws/ui/auth/getUser');
	var netlimsId=userdata.labId;
	var tableObj="#branchOrderNetlimsAcc";
	viewNetlimsAccBranchList(netlimsId,tableObj);

function viewNetlimsAccBranchList(netlimsId,tableObj) {
	var branchTable=getRequestData('/youNeverWait/json/list/netlimsAccBranchOrdersTable.json');
	var contentForm = new form(branchTable);
	$j('#tabs-1').html(contentForm.result);
	makeDataTable(tableObj);
	var pgBranchList=fillNetlimsAccBranchorderTable(netlimsId,tableObj);
	return pgBranchList;
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
function fillNetlimsAccBranchorderTable(netlimsId,tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	var branchResult=getRequestData("/youNeverWait/ws/ui/lab/viewBranchOrders/"+ netlimsId );
	//alert(JSON.stringify(branchResult));
	//if(branchResult.branch) {
		if(branchResult.branchOrders.length>0) {			
			$j(branchResult.branchOrders).each(function (index, lab) {
				var id=lab.branchId;
				var rowData=$j(tableObj).dataTable().fnAddData([id,lab.branchName,lab.orderDate,lab.totalOrders,lab.netAmount,lab.paidAmount,lab.lastOrderdTime]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				//$j(row).children("td:nth-child(1)").attr("class","netlimsIdCol Ustyle");
				});	
		}
	//}		
	return branchResult;
}

});	//document.ready ends here	*/