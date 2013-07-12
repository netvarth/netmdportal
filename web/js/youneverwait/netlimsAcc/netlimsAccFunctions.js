function viewNetlimsAccBranchList(branchListNetlimsAccJson,tableObj) {
	var branchTable=setBranchTableStructure();
	$j('#tabs-1').html(branchTable.html());
	setCustomDataTable(tableObj);
	loadBranchPageToolBar();
	pgBranchList=fillNetlimsAccBranchTable(branchListNetlimsAccJson,tableObj);
	return pgBranchList;
}

function setBranchTableStructure() {
	//create the table structure for netlims Table
	var tblData = getRequestData('/youNeverWait/json/list/netlimsAccBranchTable.json');
	var boxDiv = $j('<div/>');
		$j(tblData.data).each(function(index,subgroup) {
			var cont = new Container(subgroup);
			boxDiv.append(cont.result);   
		});
	return 	boxDiv;
}


function loadBranchPageToolBar() {
	//Creating Page Tool Bar
	var ptbdata =getRequestData('/youNeverWait/json/toolbars/netlimsAccBranchPageToolBar.json');
	var ptbContainer = $j('<div id="branchNetlimsAccPTBContainer"/>');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).append(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
}

function fillNetlimsAccBranchTable(branchListNetlimsAccJson,tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	var branchResult=postdataToServer("/youNeverWait/ws/ui/lab/branchList", branchListNetlimsAccJson );
	//alert(JSON.stringify(branchResult));
	if(branchResult.branch) {
		if(branchResult.branch.length>0) {			
			$j(branchResult.branch).each(function (index, branch) {
				var id=branch.globalId;
				var rowData=$j(tableObj).dataTable().fnAddData([id,branch.name,branch.mobile,branch.status]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				$j(row).children("td:nth-child(1)").attr("class","branchNetlimsAccIdCol Ustyle");
				});	
		}
	}		
	return branchResult;
}
