function viewNetRxList(netrxListJson,tableObj) {
	var netrxTable=setNetRxTableStructure();
	$j('#tabs-1').html(netrxTable.html());
	setCustomDataTable(tableObj);
	loadNetRxPageToolBar();
	pgNetRxList=fillNetRxTable(netrxListJson,tableObj);
	return pgNetRxList;
}

function setNetRxTableStructure() {
	//create the table structure for doctor Table
	var tblData = getRequestData('/youNeverWait/json/list/netrxTable.json');
	var boxDiv = $j('<div/>');
		$j(tblData.data).each(function(index,subgroup) {
			var cont = new Container(subgroup);
			boxDiv.append(cont.result);   
		});
	return 	boxDiv;
}

function loadNetRxPageToolBar() {
	//Creating Page Tool Bar
	var ptbdata =getRequestData('/youNeverWait/json/toolbars/netrxPageToolBar.json');
	var ptbContainer = $j('<div id="netrxPTBContainer"/>');
	var ptb = new PageToolBar(ptbdata);
	//alert(JSON.stringify(ptb));
	$j(ptbContainer).append(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
}

function fillNetRxTable(netrxListJson,tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	var netrxListJson=postdataToServer("/youNeverWait/ws/ui/superAdmin/netrxList", netrxListJson );
	if(netrxListJson.netRx) {
		if(netrxListJson.netRx.length>0) {			
			$j(netrxListJson.netRx).each(function (index, netRx) {
				var id=netRx.globalId;
				var rowData=$j(tableObj).dataTable().fnAddData([id,netRx.name,netRx.ownerEmail,netRx.status]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				$j(row).children("td:nth-child(1)").attr("class","netrxIdCol Ustyle");
				});	
		}
	}		
	return netrxListJson;
	
	
}

/* function to get the  previous netrxId from the netmdlist json */
function getpreviousNetrxId(netrxId, netrxResult) {
	var ntrxId;
	$j(netrxResult.netRx).each(function (index, rowNetrx) {
		if(netrxId==rowNetrx.globalId)	{
			var arrayLength=(netrxResult.netRx).length;
			var comp=arrayLength-1;
			if(index==0)
				ntrxId = netrxId;
			else
				ntrxId=netrxResult.netRx[index-1].globalId;
		}
	});
	return ntrxId;	
}

/* function to get the  next netrxId from the netmdlist json */
function getnextNetrxId(netrxId, netrxResult) {
	var ntrxId;
	$j(netrxResult.netRx).each(function (index, rowNetrx) {
		if(netrxId==rowNetrx.globalId)	{
			var arrayLength=(netrxResult.netRx).length;
			var comp=arrayLength-1;
			if(index==comp){
				ntrxId = netrxId;
			}
			else{
				ntrxId=netrxResult.netRx[index+1].globalId;
			}	
		}
	});	
	return ntrxId;	
}
