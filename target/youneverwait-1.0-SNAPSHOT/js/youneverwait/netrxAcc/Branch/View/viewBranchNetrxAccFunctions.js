	
	function viewNetrxAccBranch(branchId,tableObj,tableObj1)
	 {
		$j('#pageTitle').html(constant_BranchNetrxAccView_Msg);
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
		var viewdata = getRequestData('/youNeverWait/json/view/viewNetrxAccBranchDetails.json');
		var contentForm = new form(viewdata);
		$j('#tabs-1').html(contentForm.result);	
		viewNetrxAccBranchInfo(branchId,tableObj,tableObj1);
	 }
	 
	function viewNetrxAccBranchInfo(netrxbranchId,tableObj,tableObj1) {
		
		var branchInfo = getNetRxBranchAccData(netrxbranchId);
		//alert(JSON.stringify(branchInfo));
		if(branchInfo.branch.globalId)
			$j("#netrxAccbranchViewForm #branchid ").val(branchInfo.branch.globalId);
		else
			$j("#netrxAccbranchViewForm #branchid ").val("Nil");
		if(branchInfo.branch.netRxId)
			$j("#netrxAccbranchViewForm #netrxid ").val(branchInfo.branch.netRxId);
		else
			$j("#netrxAccbranchViewForm #netrxid ").val("Nil");	
		if(branchInfo.branch.name)
			$j("#netrxAccbranchViewForm #organizationName").val(branchInfo.branch.name);
		else
			$j("#netrxAccbranchViewForm #organizationName").val('Nil');
		if(branchInfo.branch.phone)
			$j("#netrxAccbranchViewForm #branchPhone").val(branchInfo.branch.phone);
		else
			$j("#netrxAccbranchViewForm #branchPhone").val('Nil');
		if(branchInfo.branch.mobile)
			$j("#netrxAccbranchViewForm #branchMobile").val(branchInfo.branch.mobile);
		else
			$j("#netrxAccbranchViewForm #branchMobile").val('Nil');
		if(branchInfo.branch.address)
			$j("#netrxAccbranchViewForm #address").val(br2nl(branchInfo.branch.address));
		else 
			$j("#netrxAccbranchViewForm #address").val('Nil');
		if(branchInfo.branch.email)
			$j("#netrxAccbranchViewForm #email").val(branchInfo.branch.email);
		else 
			$j("#netrxAccbranchViewForm #email").val('Nil');	
		if(branchInfo.branch.status)
			$j("#netrxAccbranchViewForm #branchStatus").val(branchInfo.branch.status);
		else 
			$j("#netrxAccbranchViewForm #branchStatus").val('Nil');	
		if(branchInfo.branch.numberOfDevices)
			$j("#netrxAccbranchViewForm #devicesno").val(branchInfo.branch.numberOfDevices);
		else 
			$j("#netrxAccbranchViewForm #devicesno").val('Nil');	
		if(branchInfo.branch.email)
			$j("#netrxAccbranchViewForm #email").val(branchInfo.branch.email);
		else 
			$j("#netrxAccbranchViewForm #email").val('Nil');	
		
		/*if(branchInfo.branch.macId)
			$j("#netmdAccbranchViewForm #macId").val(branchInfo.branch.macId);
		else 
			$j("#netmdAccbranchViewForm #macId").val('Nil');	*/	
		makeDataTable('#passphraseViewTableAcc'); 
		makeDataTable('#passphrasePrimaryViewTableAcc'); 
		viewPassPhraseTableAcc(branchInfo,tableObj,tableObj1);		

	}
	
	
	function submitNetrxAccBranchInfo(){
		var resultJson = createSubmitJson();
		//alert(resultJson);
		var response = postdataToServer("/youNeverWait/ws/ui/netRx/updateNetRxBranch", resultJson );
		//alert(JSON.stringify(response));
		return response;
	}
	
	function createSubmitJson(){
		var submitdata;
		submitdata = '{'+'"name"' + ':"'+$j('#netrxAccbranchViewForm #organizationName').val() +'",';
		submitdata+='"globalId"' +':' + $j('#netrxAccbranchViewForm #branchid').val() +',';
		submitdata+='"phone"' +':"' + $j('#netrxAccbranchViewForm #branchPhone').val() +'",';
		submitdata+='"mobile"' +':"' + $j('#netrxAccbranchViewForm #branchMobile').val() +'",';
		submitdata+='"address"' +':"' + nl2br($j('#netrxAccbranchViewForm #address').val()) +'",';
		submitdata +='"netRxId":' + $j('#netrxAccbranchViewForm #netrxid').val() + ',';
		submitdata +='"numberOfDevices":' + $j('#netrxAccbranchViewForm #devicesno').val() + ',';
		submitdata +='"status":"' + $j('#netrxAccbranchViewForm #branchStatus').val() + '",';

		submitdata +='"email":"' + $j('#netrxAccbranchViewForm #email').val() + '"}';
		
		return submitdata;
	}

	function viewPassPhraseTableAcc(branchInfo,tableObj,tableObj1) {
		$j(tableObj).dataTable().fnClearTable();
		$j(tableObj1).dataTable().fnClearTable();
		//alert(JSON.stringify(branchInfo));
		$j(branchInfo.branch).each(function (index,branchInfo) {
		
		//var myData='<a href="#"><img width="60" height="25" style="margin:0 0 0 5px;" class="delete tohide" src="/youNeverWait/images/clear.jpeg"></a>';
			$j(branchInfo.passPhrase).each(function (index,passPhrase) {
			if(passPhrase.primary==true)
			{
			if(passPhrase.macId==null)
			{		var rowData=$j(tableObj1).dataTable().fnAddData([passPhrase.passPhrase,'Nil']);
				var row=$j(tableObj1).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).children("td:nth-child(2)").attr("class","nilstyle");
			}
			else
			{
			var rowData=$j(tableObj1).dataTable().fnAddData([passPhrase.passPhrase,passPhrase.macId]);
			var row=$j(tableObj1).dataTable().fnSettings().aoData[rowData].nTr;
			$j(row).children("td:nth-child(2)").attr("class","nilstyle");
			}
			}
			// var button=$j('<button name="save"/>');
			else
			{
			if(passPhrase.macId==null)
			{		
			var rowData=$j(tableObj).dataTable().fnAddData([passPhrase.passPhrase,'Nil']);
			var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr
			$j(row).children("td:nth-child(2)").attr("class","nilstyle");
			}
			else
			{
			var rowData=$j(tableObj).dataTable().fnAddData([passPhrase.passPhrase,passPhrase.macId]);
			var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr; 
			$j(row).children("td:nth-child(2)").attr("class","nilstyle");
			}
			}
				
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
/* function to get the  previous netrxid from the netrxlist json */
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

/* function to get the  next netrxid from the netrxlist json */
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
