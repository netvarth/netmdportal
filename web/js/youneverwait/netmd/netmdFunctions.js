function viewNetMdList(netmdListJson,tableObj) {
	var netmdTable=setNetMdTableStructure();
	$j('#tabs-1').html(netmdTable.html());
	setCustomDataTable(tableObj);
	loadNetMdPageToolBar();
	pgNetMdList=fillNetMdTable(netmdListJson,tableObj);
	return pgNetMdList;
}

function setNetMdTableStructure() {
	//create the table structure for doctor Table
	var tblData = getRequestData('/youNeverWait/json/list/netmdTable.json');
	var boxDiv = $j('<div/>');
		$j(tblData.data).each(function(index,subgroup) {
			var cont = new Container(subgroup);
			boxDiv.append(cont.result);   
		});
	return 	boxDiv;
}

function loadNetMdPageToolBar() {
	//Creating Page Tool Bar
	var ptbdata =getRequestData('/youNeverWait/json/toolbars/netmdPageToolBar.json');
	var ptbContainer = $j('<div id="netmdPTBContainer"/>');
	var ptb = new PageToolBar(ptbdata);
	//alert(JSON.stringify(ptb));
	$j(ptbContainer).append(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
}

function fillNetMdTable(netmdListJson,tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	var netmdListJson=postdataToServer("/youNeverWait/ws/ui/superAdmin/netmdList", netmdListJson );
	if(netmdListJson.netMd) {
		if(netmdListJson.netMd.length>0) {			
			$j(netmdListJson.netMd).each(function (index, netMd) {
				var id=netMd.globalId;
				var rowData=$j(tableObj).dataTable().fnAddData([id,netMd.name,netMd.ownerEmail,netMd.status]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				$j(row).children("td:nth-child(1)").attr("class","netmdIdCol Ustyle");
				});	
		}
	}		
	return netmdListJson;
	
	
}

/* function to get the  previous netmdId from the netmdlist json */
function getpreviousNetmdId(netmdId, netmdResult) {
	var ntmdId;
	$j(netmdResult.netMd).each(function (index, rowNetmd) {
		if(netmdId==rowNetmd.globalId)	{
			var arrayLength=(netmdResult.netMd).length;
			var comp=arrayLength-1;
			if(index==0)
				ntmdId = netmdId;
			else
				ntmdId=netmdResult.netMd[index-1].globalId;
		}
	});
	return ntmdId;	
}

/* function to get the  next netmdId from the netmdlist json */
function getnextNetmdId(netmdId, netmdResult) {
	var ntmdId;
	$j(netmdResult.netMd).each(function (index, rowNetmd) {
		if(netmdId==rowNetmd.globalId)	{
			var arrayLength=(netmdResult.netMd).length;
			var comp=arrayLength-1;
			if(index==comp){
				ntmdId = netmdId;
			}
			else{
				ntmdId=netmdResult.netMd[index+1].globalId;
			}	
		}
	});	
	return ntmdId;	
}
