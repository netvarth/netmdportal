function viewNetrxAccBranchList(branchListNetrxAccJson,tableObj) {
	var branchTable=setBranchTableStructure();
	$j('#tabs-1').html(branchTable.html());
	setCustomDataTable(tableObj);
	loadBranchPageToolBar();
	pgBranchList=fillNetrxAccBranchTable(branchListNetrxAccJson,tableObj);
	return pgBranchList;
}

function setBranchTableStructure() {
	//create the table structure for netlims Table
	var tblData = getRequestData('/youNeverWait/json/list/netrxAccBranchTable.json');
	var boxDiv = $j('<div/>');
		$j(tblData.data).each(function(index,subgroup) {
			var cont = new Container(subgroup);
			boxDiv.append(cont.result);   
		});
	return 	boxDiv;
}


function loadBranchPageToolBar() {
	//Creating Page Tool Bar
	var ptbdata =getRequestData('/youNeverWait/json/toolbars/netrxAccBranchPageToolBar.json');
	var ptbContainer = $j('<div id="branchPTBContainer"/>');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).append(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
}

function fillNetrxAccBranchTable(branchListNetrxAccJson,tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	var branchResult=postdataToServer("/youNeverWait/ws/ui/netRx/netRxBranchList", branchListNetrxAccJson );
	//alert(JSON.stringify(branchResult));
	if(branchResult.netRxBranch) {
		if(branchResult.netRxBranch.length>0) {			
			$j(branchResult.netRxBranch).each(function (index, branch) {
				var id=branch.globalId;
				var rowData=$j(tableObj).dataTable().fnAddData([id,branch.name,branch.mobile,branch.status]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				$j(row).children("td:nth-child(1)").attr("class","branchIdCol Ustyle");
				});	
		}
	}		
	return branchResult;
}
