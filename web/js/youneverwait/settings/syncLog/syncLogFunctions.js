

function viewLogList(synclogListJson,tableObj) {
	var syncLogTable=setSyncLogTableStructure();
	$j('#tabs-1').html(syncLogTable.html());
	setCustomDataTable(tableObj);
	loadSyncLogPageToolBar();
	loadSyncLogFilterToolBar();
	pgSyncLogList=fillSyncLogTable(synclogListJson,tableObj);
	return pgSyncLogList;
}

function setSyncLogTableStructure() {
	//create the table structure for sync Table
	var tblData = getRequestData('/youNeverWait/json/list/syncLogTable.json');
	var boxDiv = $j('<div/>');
		$j(tblData.data).each(function(index,subgroup) {
			var cont = new Container(subgroup);
			boxDiv.append(cont.result);   
		});
	return 	boxDiv;
}

function loadSyncLogPageToolBar() {
	//Creating Page Tool Bar
	var ptbdata =getRequestData('/youNeverWait/json/toolbars/syncLogPageToolBar.json');
	var ptbContainer = $j('<div id="syncLogPTBContainer"/>');
	var ptb = new PageToolBar(ptbdata);
	//alert(JSON.stringify(ptb));
	$j(ptbContainer).append(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
}

function loadSyncLogFilterToolBar(){
	var ftbdata =getRequestData('/youNeverWait/json/toolbars/syncLogFilterToolBar.json');
	var ftb = new FilterToolBar(ftbdata);
	var syncFilterCont=$j('<div id="sync-filter-cont">');
	var syncFilterTB = $j('<div id="sync-filter-toolbar" class="box-content"/>');
	syncFilterTB.append(ftb.result); // Add the filter tool bar to Div
	syncFilterCont.append(syncFilterTB);
	var syncFilterWB = $j('<div id="sync-filter-wb" style="display:none;padding:0 0 0 0px"/>');
	var syncFilterWBInner = $j('<div id="sync-filter-wb-inner" style="float:left; width:40%"/>');
	syncFilterWB.append(syncFilterWBInner);
	var syncFilterSubBtn = $j('<input type="button" value="Go", id="sync_btnfltrSubmit">');
	syncFilterWB.append(syncFilterSubBtn);
	syncFilterCont.append(syncFilterWB);
	$j('#filterToolBar-Container').html(syncFilterCont);
	$j('#filter').show();
	$j('#filterWorkBench').hide();	
}

function fillSyncLogTable(synclogListJson,tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	var syncLogListJson=postdataToServer("/youNeverWait/superadmin/ui/superAdmin/syncLogList", synclogListJson );
	//alert(JSON.stringify(syncLogListJson));
	 if(syncLogListJson.syncLog) {
		if(syncLogListJson.syncLog.length>0) {			
			$j(syncLogListJson.syncLog).each(function (index, syncLog) {
				var id=syncLog.id;
				var rowData=$j(tableObj).dataTable().fnAddData([id,syncLog.applicationName,syncLog.branchName,syncLog.headOfficeName,syncLog.ipAddress,syncLog.lastSyncTime]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				$j(row).children("td:nth-child(1)").attr("class","logIdCol Ustyle");
				});	
		}
	}		 
	return syncLogListJson;
	
}