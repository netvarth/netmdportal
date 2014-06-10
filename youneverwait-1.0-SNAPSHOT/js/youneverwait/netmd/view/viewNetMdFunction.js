
function viewNetMd(netmdId) {
	$j('#pageTitle').html(constant_NetMdView_Msg);
	$j('#filter').hide();
	$j('#tabs-1').html("");
	$j('#filterToolBar-Container').html("");
	$j('#pageToolBar-Container').html("");
	//Creating Page Tool Bar
	var ptbContainer = $j('<div id="netmdGeneralPTBContainer" />');
	var ptbdata =getRequestData('/youNeverWait/json/toolbars/viewGeneralToolbar.json');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).html(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
	var viewdata = getRequestData('/youNeverWait/json/view/viewnetmddetails.json');
	var contentForm = new form(viewdata);
	$j('#tabs-1').html(contentForm.result);	
	
	viewnetmdInfo(netmdId);
}


    
function viewnetmdInfo(netmdId) {
	var netmdInfo = getNetMdData(netmdId);
	//alert(JSON.stringify(netmdInfo));
	if(netmdInfo.netMd.globalId)
		$j("#netmdViewForm #netmdid ").val(netmdInfo.netMd.globalId);
	else
		$j("#netmdViewForm #netmdid ").val("Nil");
	if(netmdInfo.netMd.name)
		$j("#netmdViewForm #organizationName").val(netmdInfo.netMd.name);
	else
		$j("#netmdViewForm #organizationName").val('Nil');	
		if(netmdInfo.netMd.headOfficeEmail)
		$j("#netmdViewForm #headOfficeEmail").val(netmdInfo.netMd.headOfficeEmail);
	else
		$j("#netmdViewForm #headOfficeEmail").val('Nil');
	if(netmdInfo.netMd.headOfficePhone)
		$j("#netmdViewForm #headOfficePhone").val(netmdInfo.netMd.headOfficePhone);
	else 
		$j("#netmdViewForm #headOfficePhone").val('Nil');

	if(netmdInfo.netMd.ownerFirstName)
		$j("#netmdViewForm #ownerFirstName").val(netmdInfo.netMd.ownerFirstName);
	else 
		$j("#netmdViewForm #ownerFirstName").val('Nil');
	if(netmdInfo.netMd.ownerLastName)
		$j("#netmdViewForm #ownerLastName").val(netmdInfo.netMd.ownerLastName);
	else 
		$j("#netmdViewForm #ownerLastName").val('Nil');	
		
	if(netmdInfo.netMd.ownerEmail)
		$j("#netmdViewForm #ownerEmail").val(netmdInfo.netMd.ownerEmail);
	else 
		$j("#netmdViewForm #ownerEmail").val('Nil');
	if(netmdInfo.netMd.ownerPhone)
		$j("#netmdViewForm #ownerPhone").val(netmdInfo.netMd.ownerPhone);
	else 
		$j("#netmdViewForm #ownerPhone").val('Nil');
	if(netmdInfo.netMd.ownerMobile)
		$j("#netmdViewForm #ownerMobile").val(netmdInfo.netMd.ownerMobile);
	else 
		$j("#netmdViewForm #ownerMobile").val('Nil');
	if(netmdInfo.netMd.ownerAddress)
		$j("#netmdViewForm #ownerAddress").val(br2nl(netmdInfo.netMd.ownerAddress));
	else 
		$j("#netmdViewForm #ownerAddress").val('Nil');	


if(netmdInfo.netMd.headOfficeName)
		$j("#netmdViewForm #headOfficeName").val(netmdInfo.netMd.headOfficeName);
	else 
		$j("#netmdViewForm #headOfficeName").val('Nil');
	if(netmdInfo.netMd.headOfficeMobile)
		$j("#netmdViewForm #headOfficeMobile").val(netmdInfo.netMd.headOfficeMobile);
	else 
		$j("#netmdViewForm #headOfficeMobile").val('Nil');
	if(netmdInfo.netMd.headOfficeAddress)
		$j("#netmdViewForm #headOfficeAddress").val(br2nl(netmdInfo.netMd.headOfficeAddress));
	else 
		$j("#netmdViewForm #headOfficeAddress").val('Nil');
	if(netmdInfo.netMd.userName)
		$j("#netmdViewForm #usernameNetmd").val(netmdInfo.netMd.userName);
	else 
		$j("#netmdViewForm #usernameNetmd").val('Nil');		
	
}	

function submitNetmdInfo(){
	var resultJson = createSubmitJson();
	//alert(resultJson);
	var response = postdataToServer("/youNeverWait/ws/ui/superAdmin/updateNetMd", resultJson );
	//alert(JSON.stringify(response));
	return response;
}

function createSubmitJson(){
	var netmdname;
	netmdname=$j('#netmdViewForm #organizationName').val() ;
	netmdname=netmdname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerfirstname;
	ownerfirstname= $j('#netmdViewForm #ownerFirstName').val();
	ownerfirstname=ownerfirstname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerlastname;
	ownerlastname= $j('#netmdViewForm #ownerLastName').val();
	ownerlastname=ownerlastname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var headofficename;
	headofficename= $j('#netmdViewForm #headOfficeName').val();
	headofficename=headofficename.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var submitdata;
	submitdata = '{'   +'"name"' + ':"'+netmdname+'",';
	submitdata+='"globalId"' +':' + $j('#netmdViewForm #netmdid').val() +',';
	submitdata+='"ownerFirstName"' +':"' +ownerfirstname+'",';
	submitdata+='"ownerLastName"' +':"' +ownerlastname+'",';
	submitdata+='"ownerEmail"' +':"' + $j('#netmdViewForm #ownerEmail').val() +'",';
	submitdata+='"ownerPhone"' +':"' + $j('#netmdViewForm #ownerPhone').val() +'",';
	submitdata+='"ownerMobile"' +':"' + $j('#netmdViewForm #ownerMobile').val() +'",';
	submitdata+='"ownerAddress"' +':"' +nl2br($j('#netmdViewForm #ownerAddress').val()) +'",';
	submitdata+='"headOfficeName"' +':"' +headofficename+'",';
	submitdata+='"headOfficeEmail"' +':"' + $j('#netmdViewForm #headOfficeEmail').val() +'",';
	submitdata+='"headOfficePhone"' +':"' + $j('#netmdViewForm #headOfficePhone').val() +'",';
	submitdata+='"headOfficeMobile"' +':"' + $j('#netmdViewForm #headOfficeMobile').val() +'",';
	submitdata+='"headOfficeAddress"' +':"' + nl2br($j('#netmdViewForm #headOfficeAddress').val()) +'",';
	//submitdata+='"userName"' +':"' + $j('#netmdViewForm #userName').val() +'",';
	submitdata+='"userName"' +':"",';
	submitdata+='"password"' +':""'+  '}' ;
	return submitdata;
}