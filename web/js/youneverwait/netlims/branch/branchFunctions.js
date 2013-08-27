function viewBranchList(branchListJson,tableObj) {
	var branchTable=setBranchTableStructure();
	$j('#tabs-1').html(branchTable.html());
	setCustomDataTable(tableObj);
	loadBranchPageToolBar();
	pgBranchList=fillBranchTable(branchListJson,tableObj);
	return pgBranchList;
}

function setBranchTableStructure() {
	//create the table structure for netlims Table
	var tblData = getRequestData('/youNeverWait/json/list/branchTable.json');
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

function fillBranchTable(branchListJson,tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	var branchResult=postdataToServer("/youNeverWait/ws/ui/superAdmin/branchList", branchListJson );
	//alert(JSON.stringify(branchResult));
	if(branchResult.branch) {
		if(branchResult.branch.length>0) {			
			$j(branchResult.branch).each(function (index, branch) {
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

function viewHealthmonitor(branchId)
{
	var healtMontr=getRequestData('/youNeverWait/ws/ui/superAdmin/viewBranchSystemInfo/'+branchId);
	//alert(JSON.stringify(healtMontr));
	if(healtMontr)
		$j("#newHealthForm #criticalCpuLevel").val(healtMontr.criticalCpuLevel);
	else
		$j("#newHealthForm #criticalCpuLevel").val('Nil');
	if(healtMontr)
		$j("#newHealthForm #criticalMemoryLevel").val(healtMontr.criticalMemoryLevel);
	else 
		$j("#newHealthForm #criticalMemoryLevel").val('Nil');
	if(healtMontr)
		$j("#newHealthForm #branchid").val(healtMontr.branchId);
	else 
		$j("#newHealthForm #branchid").val('Nil');
	if(healtMontr)
		$j("#newHealthForm #frequencyType").val(healtMontr.freqType);
	else 
		$j("#newHealthForm #frequencyType").val('Nil');
	if(healtMontr)
		$j("#newHealthForm #intervalTime").val(healtMontr.intervalTime);
	else 
		$j("#newHealthForm #intervalTime").val('Nil');	
	
	if(healtMontr)
		$j("#newHealthForm #criticalHardDiskSpaceLevel").val(healtMontr.criticalHardDiskSpaceLevel);
	else 
		$j("#newHealthForm #criticalHardDiskSpaceLevel").val('Nil');
}

function submitHealthmonitorInfo(){

	var resultJson = createSubmitJson();
	//alert(resultJson);
	var response = postdataToServer("/youNeverWait/ws/ui/superAdmin/updateLabBranchSystemInfo", resultJson );
	//alert(JSON.stringify(response));
	return response;

}

function createSubmitJson(){
	

	var submitdata;
	submitdata = '{'   +'"criticalCpuLevel"' + ':"'+$j('#newHealthForm #criticalCpuLevel').val() +'",';
	submitdata+='"branchId"' +':' + $j("#newHealthForm #branchid").val() +',';
	
	submitdata+='"criticalMemoryLevel"' +':"' + $j('#newHealthForm #criticalMemoryLevel').val() +'",';
	submitdata+='"criticalHardDiskSpaceLevel"' +':"' + $j('#newHealthForm #criticalHardDiskSpaceLevel').val() +'",';
	submitdata+='"freqType"' +':"' + $j('#newHealthForm #selectfrequencyType').val() +'",';
	
	submitdata+='"intervalTime"' +':"' + $j('#newHealthForm #intervalTime').val() +'",';
	submitdata+='"branchName"' +':"",';
	
	submitdata+='"healthMonitorList"' +':[]'+  '}' ;
	return submitdata;
}