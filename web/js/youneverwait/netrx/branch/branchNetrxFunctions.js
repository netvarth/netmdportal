function viewNetRxBranchList(branchNetrxListJson,tableObj) {
	var branchTable=setBranchTableStructure();
	$j('#tabs-1').html(branchTable.html());
	setCustomDataTable(tableObj);
	loadBranchPageToolBar();
	pgBranchList=fillNetRxBranchTable(branchNetrxListJson,tableObj);
	return pgBranchList;
}

function setBranchTableStructure() {
	//create the table structure for netrx Table
	var tblData = getRequestData('/youNeverWait/json/list/netrxBranchTable.json');
	var boxDiv = $j('<div/>');
		$j(tblData.data).each(function(index,subgroup) {
			var cont = new Container(subgroup);
			boxDiv.append(cont.result);   
		});
	return 	boxDiv;
}


function loadBranchPageToolBar() {
	//Creating Page Tool Bar
	var ptbdata =getRequestData('/youNeverWait/json/toolbars/branchPageToolBar.json');
	var ptbContainer = $j('<div id="branchNetrxPTBContainer"/>');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).append(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
}

function fillNetRxBranchTable(branchNetrxListJson,tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	var branchNetRxResult=postdataToServer("/youNeverWait/ws/ui/superAdmin/netRxBranchList", branchNetrxListJson );
	if(branchNetRxResult.netRxBranch) {
		if(branchNetRxResult.netRxBranch.length>0) {			
			$j(branchNetRxResult.netRxBranch).each(function (index, netrxBranch) {
				var id=netrxBranch.globalId;
				var rowData=$j(tableObj).dataTable().fnAddData([id,netrxBranch.name,netrxBranch.mobile,netrxBranch.status]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				$j(row).children("td:nth-child(1)").attr("class","netrxbranchIdCol Ustyle");
				});	
		}
	}		
	return branchNetRxResult;
}
