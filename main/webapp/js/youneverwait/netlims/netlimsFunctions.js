
function viewNetLimsList(netlimsListJson,tableObj) {
	var netlimsTable=setNetLimsTableStructure();
	$j('#tabs-1').html(netlimsTable.html());
	setCustomDataTable(tableObj);
	loadNetLimsPageToolBar();
	pgNetLimsList=fillNetLimsTable(netlimsListJson,tableObj);
	return pgNetLimsList;
}

function setNetLimsTableStructure() {
	//create the table structure for netlims Table
	var tblData = getRequestData('/youNeverWait/json/list/netlimsTable.json');
	var boxDiv = $j('<div/>');
		$j(tblData.data).each(function(index,subgroup) {
			var cont = new Container(subgroup);
			boxDiv.append(cont.result);   
		});
	return 	boxDiv;
}

function loadNetLimsPageToolBar() {
	//Creating Page Tool Bar
	var ptbdata =getRequestData('/youNeverWait/json/toolbars/netlimsPageToolBar.json');
	var ptbContainer = $j('<div id="netlimsPTBContainer"/>');
	var ptb = new PageToolBar(ptbdata);
	//alert(JSON.stringify(ptb));
	$j(ptbContainer).append(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
}

function fillNetLimsTable(netlimsListJson,tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	var netlimsResult=postdataToServer("/youNeverWait/ws/ui/superAdmin/labList", netlimsListJson );
	if(netlimsResult.lab) {
		if(netlimsResult.lab.length>0) {			
			$j(netlimsResult.lab).each(function (index, lab) {
				var id=lab.globalId;
				var rowData=$j(tableObj).dataTable().fnAddData([id,lab.name,lab.ownerEmail,lab.status]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				$j(row).children("td:nth-child(1)").attr("class","netlimsIdCol Ustyle");
				});	
		}
	}		
	return netlimsResult;
}

/* function to get the  previous netlimsid from the netlimslist json */
function getpreviousNetlimsId(netlimsId, netlimsResult) {
	var ntlimsId;
	$j(netlimsResult.lab).each(function (index, rowNetlims) {
		if(netlimsId==rowNetlims.globalId)	{
			var arrayLength=(netlimsResult.lab).length;
			var comp=arrayLength-1;
			if(index==0)
				ntlimsId = netlimsId;
			else
				ntlimsId=netlimsResult.lab[index-1].globalId;
		}
	});
	return ntlimsId;	
}

/* function to get the  next netlimsid from the netlimslist json */
function getnextNetlimsId(netlimsId, netlimsResult) {
	var ntlimsId;
	$j(netlimsResult.lab).each(function (index, rowNetlims) {
		if(netlimsId==rowNetlims.globalId)	{
			var arrayLength=(netlimsResult.lab).length;
			var comp=arrayLength-1;
			if(index==comp){
				ntlimsId = netlimsId;
			}
			else{
				ntlimsId=netlimsResult.lab[index+1].globalId;
			}	
		}
	});	
	return ntlimsId;	
}
