	
	function viewNetmdAccBranch(branchId,tableObj,tableObj1)
	 {
		$j('#pageTitle').html(constant_BranchNetmdAccView_Msg);
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
		var viewdata = getRequestData('/youNeverWait/json/view/viewNetmdAccBranchDetails.json');
		var contentForm = new form(viewdata);
		$j('#tabs-1').html(contentForm.result);	
		viewNetmdAccBranchInfo(branchId,tableObj,tableObj1);
	 }
	 
	function viewNetmdAccBranchInfo(netmdbranchId,tableObj,tableObj1) {
		
		var branchInfo = getNetMdBranchAccData(netmdbranchId);
		//alert(JSON.stringify(branchInfo));
		if(branchInfo.branch.globalId)
			$j("#netmdAccbranchViewForm #branchid ").val(branchInfo.branch.globalId);
		else
			$j("#netmdAccbranchViewForm #branchid ").val("Nil");
		if(branchInfo.branch.netMdId)
			$j("#netmdAccbranchViewForm #netmdid ").val(branchInfo.branch.netMdId);
		else
			$j("#netmdAccbranchViewForm #netmdid ").val("Nil");	
		if(branchInfo.branch.name)
			$j("#netmdAccbranchViewForm #organizationName").val(branchInfo.branch.name);
		else
			$j("#netmdAccbranchViewForm #organizationName").val('Nil');
		if(branchInfo.branch.phone)
			$j("#netmdAccbranchViewForm #branchPhone").val(branchInfo.branch.phone);
		else
			$j("#netmdAccbranchViewForm #branchPhone").val('Nil');
		if(branchInfo.branch.mobile)
			$j("#netmdAccbranchViewForm #branchMobile").val(branchInfo.branch.mobile);
		else
			$j("#netmdAccbranchViewForm #branchMobile").val('Nil');
		if(branchInfo.branch.address)
			$j("#netmdAccbranchViewForm #address").val(br2nl(branchInfo.branch.address));
		else 
			$j("#netmdAccbranchViewForm #address").val('Nil');
		if(branchInfo.branch.email)
			$j("#netmdAccbranchViewForm #email").val(branchInfo.branch.email);
		else 
			$j("#netmdAccbranchViewForm #email").val('Nil');	
		if(branchInfo.branch.status)
			$j("#netmdAccbranchViewForm #branchStatus").val(branchInfo.branch.status);
		else 
			$j("#netmdAccbranchViewForm #branchStatus").val('Nil');	
		if(branchInfo.branch.numberOfDevices)
			$j("#netmdAccbranchViewForm #devicesno").val(branchInfo.branch.numberOfDevices);
		else 
			$j("#netmdAccbranchViewForm #devicesno").val('Nil');	
		if(branchInfo.branch.email)
			$j("#netmdAccbranchViewForm #email").val(branchInfo.branch.email);
		else 
			$j("#netmdAccbranchViewForm #email").val('Nil');	
		
		/*if(branchInfo.branch.macId)
			$j("#netmdAccbranchViewForm #macId").val(branchInfo.branch.macId);
		else 
			$j("#netmdAccbranchViewForm #macId").val('Nil');	*/	
		makeDataTable('#passphraseViewTableAcc'); 
		makeDataTable('#passphrasePrimaryViewTableAcc'); 
		viewPassPhraseTableAcc(branchInfo,tableObj,tableObj1);		

	}
	
	
	function submitNetmdAccBranchInfo(){
		var resultJson = createSubmitJson();
		//alert(resultJson);
		var response = postdataToServer("/youNeverWait/ws/ui/netMd/updateNetMdBranch", resultJson );
		//alert(JSON.stringify(response));
		return response;
	}
	
	function createSubmitJson(){
		var submitdata;
		submitdata = '{'+'"name"' + ':"'+$j('#netmdAccbranchViewForm #organizationName').val() +'",';
		submitdata+='"globalId"' +':' + $j('#netmdAccbranchViewForm #branchid').val() +',';
		submitdata+='"phone"' +':"' + $j('#netmdAccbranchViewForm #branchPhone').val() +'",';
		submitdata+='"mobile"' +':"' + $j('#netmdAccbranchViewForm #branchMobile').val() +'",';
		submitdata+='"address"' +':"' + nl2br($j('#netmdAccbranchViewForm #address').val()) +'",';
		submitdata +='"netMdId":' + $j('#netmdAccbranchViewForm #netmdid').val() + ',';
		submitdata +='"numberOfDevices":' + $j('#netmdAccbranchViewForm #devicesno').val() + ',';
		submitdata +='"status":"' + $j('#netmdAccbranchViewForm #branchStatus').val() + '",';

		submitdata +='"email":"' + $j('#netmdAccbranchViewForm #email').val() + '"}';
		
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
/* function to get the  previous netmdid from the netmdlist json */
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

/* function to get the  next netmdid from the netmdlist json */
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
