function viewNetMdBranchList(branchNetmdListJson,tableObj) {
	var branchTable=setBranchTableStructure();
	$j('#tabs-1').html(branchTable.html());
	setCustomDataTable(tableObj);
	loadBranchPageToolBar();
	pgBranchList=fillNetMdBranchTable(branchNetmdListJson,tableObj);
	return pgBranchList;
}

function setBranchTableStructure() {
	//create the table structure for netlims Table
	var tblData = getRequestData('/youNeverWait/json/list/netmdBranchTable.json');
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
	var ptbContainer = $j('<div id="branchPTBContainer"/>');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).append(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
}

function fillNetMdBranchTable(branchNetmdListJson,tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	var branchNetMdResult=postdataToServer("/youNeverWait/ws/ui/superAdmin/netmdBranchList", branchNetmdListJson );
	//alert(JSON.stringify(branchNetMdResult));
	if(branchNetMdResult.netmdBranch) {
		if(branchNetMdResult.netmdBranch.length>0) {			
			$j(branchNetMdResult.netmdBranch).each(function (index, netmdBranch) {
				var id=netmdBranch.globalId;
				var rowData=$j(tableObj).dataTable().fnAddData([id,netmdBranch.name,netmdBranch.mobile,netmdBranch.status]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				$j(row).children("td:nth-child(1)").attr("class","netmdbranchIdCol Ustyle");
				});	
		}
	}		
	return branchNetMdResult;
}
