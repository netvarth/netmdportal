function viewNetmdBranch(netmdbranchId,tableObj,tableObj1) {
	$j('#pageTitle').html(constant_NetmdBranchView_Msg);
	$j('#filter').hide();
	$j('#tabs-1').html("");
	$j('#filterToolBar-Container').html("");
	$j('#pageToolBar-Container').html("");
	//Creating Page Tool Bar
	var ptbContainer = $j('<div id="GeneralPTBContainer" />');
	var ptbdata =getRequestData('/youNeverWait/json/toolbars/viewBranchGeneralToolbar1.json');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).html(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
	var viewdata = getRequestData('/youNeverWait/json/view/viewNetmdBranchDetails.json');
	var contentForm = new form(viewdata);
	$j('#tabs-1').html(contentForm.result);	
	viewnetmdbranchInfo(netmdbranchId,tableObj,tableObj1);
}


function viewnetmdbranchInfo(netmdbranchId,tableObj,tableObj1) {
	var branchInfo = getNetMdBranchData(netmdbranchId);
	//alert(JSON.stringify(branchInfo));
	if(branchInfo.branch.globalId)
		$j("#netmdbranchViewForm #branchid ").val(branchInfo.branch.globalId);
	else
		$j("#netmdbranchViewForm #branchid ").val("Nil");
	if(branchInfo.branch.netMdId)
		$j("#netmdbranchViewForm #netmdid ").val(branchInfo.branch.netMdId);
	else
		$j("#netmdbranchViewForm #netmdid ").val("Nil");	
	if(branchInfo.branch.name)
		$j("#netmdbranchViewForm #organizationName").val(branchInfo.branch.name);
	else
		$j("#netmdbranchViewForm #organizationName").val('Nil');
	if(branchInfo.branch.phone)
		$j("#netmdbranchViewForm #branchPhone").val(branchInfo.branch.phone);
	else
		$j("#netmdbranchViewForm #branchPhone").val('Nil');
	if(branchInfo.branch.mobile)
		$j("#netmdbranchViewForm #branchMobile").val(branchInfo.branch.mobile);
	else
		$j("#netmdbranchViewForm #branchMobile").val('Nil');
	if(branchInfo.branch.address)
		$j("#netmdbranchViewForm #address").val(br2nl(branchInfo.branch.address));
	else 
		$j("#netmdbranchViewForm #address").val('Nil');
	if(branchInfo.branch.email)
		$j("#netmdbranchViewForm #email").val(branchInfo.branch.email);
	else 
		$j("#netmdbranchViewForm #email").val('Nil');	
	if(branchInfo.branch.status)
		$j("#netmdbranchViewForm #branchStatus").val(branchInfo.branch.status);
	else 
		$j("#netmdbranchViewForm #branchStatus").val('Nil');	
	if(branchInfo.branch.numberOfDevices)
		$j("#netmdbranchViewForm #devicesno").val(branchInfo.branch.numberOfDevices);
	else 
		$j("#netmdbranchViewForm #devicesno").val('Nil');	
	/*if(branchInfo.branch.macId)
		$j("#netmdbranchViewForm #macId").val(branchInfo.branch.macId);
	else 
		$j("#netmdbranchViewForm #macId").val('Nil');	*/	
	makeDataTable('#passphraseViewTable'); 
	makeDataTable('#passphrasePrimaryViewTable'); 
	viewPassPhraseTable(branchInfo,tableObj,tableObj1);		

}


function submitBranchInfo(){
	var resultJson = createSubmitJson();
	//alert(resultJson);
	var response = postdataToServer("/youNeverWait/ws/ui/superAdmin/updateNetMdBranch", resultJson );
	//alert(JSON.stringify(response));
	return response;
}



function createSubmitJson(){
	var branchname;
	branchname=$j('#netmdbranchViewForm #organizationName').val();
	branchname=branchname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var submitdata;
	submitdata = '{'+'"name"' + ':"'+branchname+'",';
	submitdata+='"globalId"' +':' + $j('#netmdbranchViewForm #branchid').val() +',';
	submitdata+='"phone"' +':"' + $j('#netmdbranchViewForm #branchPhone').val() +'",';
	submitdata+='"mobile"' +':"' + $j('#netmdbranchViewForm #branchMobile').val() +'",';
	submitdata+='"address"' +':"' + nl2br($j('#netmdbranchViewForm #address').val()) +'",';
	submitdata +='"netMdId":' + $j('#netmdbranchViewForm #netmdid').val() + ',';
	submitdata +='"numberOfDevices":' + $j('#netmdbranchViewForm #devicesno').val() + ',';
	submitdata +='"status":"' + $j('#netmdbranchViewForm #branchStatus').val() + '",';

	submitdata +='"email":"' + $j('#netmdbranchViewForm #email').val() + '"}';
	
	return submitdata;
}

function viewPassPhraseTable(branchInfo,tableObj,tableObj1) {
	$j(tableObj).dataTable().fnClearTable();
	$j(tableObj1).dataTable().fnClearTable();
	$j(branchInfo.branch).each(function (index,branchInfo) {
	var myData='<input type="button" value="clear" class="clearMacid stdbtn">';
	var makePrim='<input type="button" value="clear"  class="clearMacid stdbtn"><input type="button" value="make primary"  class="chngprimdevice stdbtn"  >';
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
	$j(branchResult.netmdBranch).each(function (index, rowBranch) {
		if(branchid==rowBranch.globalId)	{
			var arrayLength=(branchResult.netmdBranch).length;
			var comp=arrayLength-1;
			if(index==0)
				brnchId = branchid;
			else
				brnchId=branchResult.netmdBranch[index-1].globalId;
		}
	});
	return brnchId;	
}

/* function to get the  next netlimsid from the netlimslist json */
function getnextBranchId(branchid, branchResult) {
	var brnchId;
	$j(branchResult.netmdBranch).each(function (index, rowBranch) {
		if(branchid==rowBranch.globalId)	{
			var arrayLength=(branchResult.netmdBranch).length;
			var comp=arrayLength-1;
			if(index==comp){
				brnchId = branchid;
			}
			else{
				brnchId=branchResult.netmdBranch[index+1].globalId;
			}	
		}
	});	
	return brnchId;	
}	
