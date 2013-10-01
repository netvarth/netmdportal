function viewNetLims(netlimsId) {
	$j('#pageTitle').html(constant_NetLimsView_Msg);
	$j('#filter').hide();
	$j('#tabs-1').html("");
	$j('#filterToolBar-Container').html("");
	$j('#pageToolBar-Container').html("");
	//Creating Page Tool Bar
	var ptbContainer = $j('<div id="GeneralPTBContainer" />');
	var ptbdata =getRequestData('/youNeverWait/json/toolbars/viewGeneralToolbar.json');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).html(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
	var viewdata = getRequestData('/youNeverWait/json/view/viewnetlimsdetails.json');
	var contentForm = new form(viewdata);
	$j('#tabs-1').html(contentForm.result);	
	viewnetlimsInfo(netlimsId);
}
function viewnetlimsInfo(netlimsId) {
	var netlimsInfo = getNetLimsData(netlimsId);
	var netlabId=netlimsInfo.lab.globalId;
	var tableObj='#ordersnetlimsViewTable';
	//alert(JSON.stringify(netlimsInfo));
	if(netlimsInfo.lab.globalId)
		$j("#netlimsViewForm #netlimsid ").val(netlimsInfo.lab.globalId);
	else
		$j("#netlimsViewForm #netlimsid ").val("Nil");
	if(netlimsInfo.lab.name)
		$j("#netlimsViewForm #organizationName").val(netlimsInfo.lab.name);
	else
		$j("#netlimsViewForm #organizationName").val('Nil');
	
	if(netlimsInfo.lab.labCode)
		$j("#netlimsViewForm #ownerFirstcode").val(netlimsInfo.lab.labCode);
	else
		$j("#netlimsViewForm #ownerFirstcode").val('Nil');
	if(netlimsInfo.lab.headOfficeEmail)
		$j("#netlimsViewForm #headOfficeEmail").val(netlimsInfo.lab.headOfficeEmail);
	else
		$j("#netlimsViewForm #headOfficeEmail").val('Nil');
	if(netlimsInfo.lab.headOfficePhone)
		$j("#netlimsViewForm #headOfficePhone").val(netlimsInfo.lab.headOfficePhone);
	else 
		$j("#netlimsViewForm #headOfficePhone").val('Nil');
	if(netlimsInfo.lab.ownerFirstName)
		$j("#netlimsViewForm #ownerFirstName").val(netlimsInfo.lab.ownerFirstName);
	else 
		$j("#netlimsViewForm #ownerFirstName").val('Nil');
	if(netlimsInfo.lab.ownerLastName)
		$j("#netlimsViewForm #ownerLastName").val(netlimsInfo.lab.ownerLastName);
	else 
		$j("#netlimsViewForm #ownerLastName").val('Nil');	
	if(netlimsInfo.lab.ownerEmail)
		$j("#netlimsViewForm #ownerEmail").val(netlimsInfo.lab.ownerEmail);
	else 
		$j("#netlimsViewForm #ownerEmail").val('Nil');
	if(netlimsInfo.lab.ownerPhone)
		$j("#netlimsViewForm #ownerPhone").val(netlimsInfo.lab.ownerPhone);
	else 
		$j("#netlimsViewForm #ownerPhone").val('Nil');
	if(netlimsInfo.lab.ownerMobile)
		$j("#netlimsViewForm #ownerMobile").val(netlimsInfo.lab.ownerMobile);
	else 
		$j("#netlimsViewForm #ownerMobile").val('Nil');
	if(netlimsInfo.lab.ownerAddress)
		$j("#netlimsViewForm #ownerAddress").val(br2nl(netlimsInfo.lab.ownerAddress));
	else 
		$j("#netlimsViewForm #ownerAddress").val('Nil');
	if(netlimsInfo.lab.headOfficeName)
		$j("#netlimsViewForm #headOfficeName").val(netlimsInfo.lab.headOfficeName);
	else 
		$j("#netlimsViewForm #headOfficeName").val('Nil');
	if(netlimsInfo.lab.headOfficeMobile)
		$j("#netlimsViewForm #headOfficeMobile").val(netlimsInfo.lab.headOfficeMobile);
	else 
		$j("#netlimsViewForm #headOfficeMobile").val('Nil');
	if(netlimsInfo.lab.headOfficeAddress)
		$j("#netlimsViewForm #headOfficeAddress").val(br2nl(netlimsInfo.lab.headOfficeAddress));
	else 
		$j("#netlimsViewForm #headOfficeAddress").val('Nil');
	$j(netlimsInfo.lab).each(function (index,netlimsInfo) {
		$j(netlimsInfo.login).each(function (index,login) {
		if(login.userName)
			$j("#netlimsViewForm #username").val(login.userName);
		else 
			$j("#netlimsViewForm #username").val('Nil');
		});	
	});		
	makeDataTable('#ordersnetlimsViewTable');
	fillordersnetlimsView(netlabId,tableObj);		
}

function fillordersnetlimsView(netlabId,tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	var netlimsorderResult=getRequestData("/youNeverWait/ws/ui/superAdmin/viewBranchOrders/"+ netlabId );
	//alert(JSON.stringify(netlimsorderResult));
		if(netlimsorderResult.branchOrders.length>0) {			
			$j(netlimsorderResult.branchOrders).each(function (index, lab) {
				var id=lab.branchId;
				var rowData=$j(tableObj).dataTable().fnAddData([id,lab.branchName,lab.orderDate,lab.totalOrders,lab.netAmount,lab.paidAmount,lab.lastOrderdTime]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				//$j(row).children("td:nth-child(1)").attr("class","netlimsIdCol Ustyle");
				});	
		}
	//}	 
	return netlimsorderResult;
}

function submitNetlimsInfo(){
	var resultJson = createSubmitJson();
	//alert(resultJson);
	var response = postdataToServer("/youNeverWait/ws/ui/superAdmin/updateLab", resultJson );
	//alert(JSON.stringify(response));
	return response;
}

function createSubmitJson(){
	var netmdname;
	netmdname=$j('#netlimsViewForm #organizationName').val();
	netmdname=netmdname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerfirstname;
	ownerfirstname= $j('#netlimsViewForm #ownerFirstName').val();
	ownerfirstname=ownerfirstname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerlastname;
	ownerlastname= $j('#netlimsViewForm #ownerLastName').val() ;
	ownerlastname=ownerlastname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var headofficename;
	headofficename=$j('#netlimsViewForm #headOfficeName').val();
	headofficename=headofficename.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});

	var submitdata;
	submitdata = '{'   +'"name"' + ':"'+netmdname+'",';
	submitdata+='"globalId"' +':' + $j('#netlimsViewForm #netlimsid').val() +',';
	submitdata+='"ownerFirstName"' +':"' +ownerfirstname+'",';
	submitdata+='"ownerLastName"' +':"' +ownerlastname+'",';
	submitdata+='"ownerEmail"' +':"' + $j('#netlimsViewForm #ownerEmail').val() +'",';
	submitdata+='"ownerPhone"' +':"' + $j('#netlimsViewForm #ownerPhone').val() +'",';
	submitdata+='"ownerMobile"' +':"' + $j('#netlimsViewForm #ownerMobile').val() +'",';
	submitdata+='"ownerAddress"' +':"' + nl2br($j('#netlimsViewForm #ownerAddress').val()) +'",';
	submitdata+='"headOfficeName"' +':"' +headofficename+'",';
	submitdata+='"headOfficeEmail"' +':"' + $j('#netlimsViewForm #headOfficeEmail').val() +'",';
	submitdata+='"headOfficePhone"' +':"' + $j('#netlimsViewForm #headOfficePhone').val() +'",';
	submitdata+='"headOfficeMobile"' +':"' + $j('#netlimsViewForm #headOfficeMobile').val() +'",';
	submitdata+='"headOfficeAddress"' +':"' + nl2br($j('#netlimsViewForm #headOfficeAddress').val()) +'",';
	//submitdata+='"userName"' +':"' + $j('#netlimsViewForm #userName').val() +'",';
	submitdata+='"userName"' +':"",';
	submitdata+='"password"' +':""'+  '}' ;
	return submitdata;
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