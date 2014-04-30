function viewNetrxBranch(netrxbranchId,tableObj,tableObj1) {
	$j('#pageTitle').html(constant_NetrxBranchView_Msg);
	$j('#filter').hide();
	$j('#tabs-1').html("");
	$j('#filterToolBar-Container').html("");
	$j('#pageToolBar-Container').html("");
	//Creating Page Tool Bar
	var ptbContainer = $j('<div id="GeneralnetrxPTBContainer" />');
	var ptbdata =getRequestData('/youNeverWait/json/toolbars/viewBranchGeneralToolbar1.json');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).html(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
	var viewdata = getRequestData('/youNeverWait/json/view/viewNetrxBranchDetails.json');
	var contentForm = new form(viewdata);
	$j('#tabs-1').html(contentForm.result);	
	viewnetrxbranchInfo(netrxbranchId,tableObj,tableObj1);
}


function viewnetrxbranchInfo(netrxbranchId,tableObj,tableObj1) {
	var branchInfo = getNetRxBranchData(netrxbranchId);
	//alert(JSON.stringify(branchInfo));
	if(branchInfo.branch.globalId)
		$j("#netrxbranchViewForm #branchid ").val(branchInfo.branch.globalId);
	else
		$j("#netrxbranchViewForm #branchid ").val("Nil");
	if(branchInfo.branch.netRxId)
		$j("#netrxbranchViewForm #netrxid ").val(branchInfo.branch.netRxId);
	else
		$j("#netrxbranchViewForm #netrxid ").val("Nil");	
	if(branchInfo.branch.name)
		$j("#netrxbranchViewForm #organizationName").val(branchInfo.branch.name);
	else
		$j("#netrxbranchViewForm #organizationName").val('Nil');
	if(branchInfo.branch.phone)
		$j("#netrxbranchViewForm #branchPhone").val(branchInfo.branch.phone);
	else
		$j("#netrxbranchViewForm #branchPhone").val('Nil');
	if(branchInfo.branch.mobile)
		$j("#netrxbranchViewForm #branchMobile").val(branchInfo.branch.mobile);
	else
		$j("#netrxbranchViewForm #branchMobile").val('Nil');
	if(branchInfo.branch.address)
		$j("#netrxbranchViewForm #address").val(br2nl(branchInfo.branch.address));
	else 
		$j("#netrxbranchViewForm #address").val('Nil');
	if(branchInfo.branch.email)
		$j("#netrxbranchViewForm #email").val(branchInfo.branch.email);
	else 
		$j("#netrxbranchViewForm #email").val('Nil');	
	if(branchInfo.branch.status)
		$j("#netrxbranchViewForm #branchStatus").val(branchInfo.branch.status);
	else 
		$j("#netrxbranchViewForm #branchStatus").val('Nil');	
	if(branchInfo.branch.numberOfDevices)
		$j("#netrxbranchViewForm #devicesno").val(branchInfo.branch.numberOfDevices);
	else 
		$j("#netrxbranchViewForm #devicesno").val('Nil');	
	/*if(branchInfo.branch.macId)
		$j("#netrxbranchViewForm #macId").val(branchInfo.branch.macId);
	else 
		$j("#netrxbranchViewForm #macId").val('Nil');	*/	
	makeDataTable('#passphraseViewTable'); 
	makeDataTable('#passphrasePrimaryViewTable'); 
	viewPassPhraseTable(branchInfo,tableObj,tableObj1);		

}


function submitBranchInfo(){
	var resultJson = createSubmitJson();
	//alert(resultJson);
	var response = postdataToServer("/youNeverWait/ws/ui/superAdmin/updateNetRxBranch", resultJson );
	//alert(JSON.stringify(response));
	return response;
}



function createSubmitJson(){
	var branchname;
	branchname=$j('#netrxbranchViewForm #organizationName').val();
	branchname=branchname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var submitdata;
	submitdata = '{'+'"name"' + ':"'+branchname+'",';
	submitdata+='"globalId"' +':' + $j('#netrxbranchViewForm #branchid').val() +',';
	submitdata+='"phone"' +':"' + $j('#netrxbranchViewForm #branchPhone').val() +'",';
	submitdata+='"mobile"' +':"' + $j('#netrxbranchViewForm #branchMobile').val() +'",';
	submitdata+='"address"' +':"' + nl2br($j('#netrxbranchViewForm #address').val()) +'",';
	submitdata +='"netRxId":' + $j('#netrxbranchViewForm #netrxid').val() + ',';
	submitdata +='"numberOfDevices":' + $j('#netrxbranchViewForm #devicesno').val() + ',';
	submitdata +='"status":"' + $j('#netrxbranchViewForm #branchStatus').val() + '",';

	submitdata +='"email":"' + $j('#netrxbranchViewForm #email').val() + '"}';
	
	return submitdata;
}

function viewPassPhraseTable(branchInfo,tableObj,tableObj1) {
	$j(tableObj).dataTable().fnClearTable();
	$j(tableObj1).dataTable().fnClearTable();
	$j(branchInfo.branch).each(function (index,branchInfo) {
	var myData='<button  class="clearMacid stdbtn">clear</button>';
	var makePrim='<button  class="clearMacid stdbtn">clear</button><button  class="chngprimdevice stdbtn"  >make primary</button>';
		$j(branchInfo.passPhrase).each(function (index,passPhrase) {
		if(passPhrase.primary==true)
		{
		if(passPhrase.macId==null)
		{		var rowData=$j(tableObj1).dataTable().fnAddData([passPhrase.passPhrase,'Nil',myData]);
			var row=$j(tableObj1).dataTable().fnSettings().aoData[rowData].nTr;
			$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		else
		{
		var rowData=$j(tableObj1).dataTable().fnAddData([passPhrase.passPhrase,passPhrase.macId,myData]);
		var row=$j(tableObj1).dataTable().fnSettings().aoData[rowData].nTr;
		$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		}
		// var button=$j('<button name="save"/>');
		else
		{
		if(passPhrase.macId==null)
		{		
		var rowData=$j(tableObj).dataTable().fnAddData([passPhrase.passPhrase,'Nil',makePrim]);
		var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr
		$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		else
		{
		var rowData=$j(tableObj).dataTable().fnAddData([passPhrase.passPhrase,passPhrase.macId,makePrim]);
		var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr; 
		$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		}
		//$j(row).attr('id',id);	
			//fillViewTestDiscountTable(passPhrase.passPhrase,passPhrase.macId,"");			
		});	
	});
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


/* function to get the  previous netlimsid from the netlimslist json */
function getpreviousBranchId(branchid, branchResult) {
	var brnchId;
	$j(branchResult.netRxBranch).each(function (index, rowBranch) {
		if(branchid==rowBranch.globalId)	{
			var arrayLength=(branchResult.netRxBranch).length;
			var comp=arrayLength-1;
			if(index==0)
				brnchId = branchid;
			else
				brnchId=branchResult.netRxBranch[index-1].globalId;
		}
	});
	return brnchId;	
}

/* function to get the  next netlimsid from the netlimslist json */
function getnextBranchId(branchid, branchResult) {
	var brnchId;
	$j(branchResult.netRxBranch).each(function (index, rowBranch) {
		if(branchid==rowBranch.globalId)	{
			var arrayLength=(branchResult.netRxBranch).length;
			var comp=arrayLength-1;
			if(index==comp){
				brnchId = branchid;
			}
			else{
				brnchId=branchResult.netRxBranch[index+1].globalId;
			}	
		}
	});	
	return brnchId;	
}	
