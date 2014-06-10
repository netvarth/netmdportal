
function viewNetRx(netrxId) {
	$j('#pageTitle').html(constant_NetRxView_Msg);
	$j('#filter').hide();
	$j('#tabs-1').html("");
	$j('#filterToolBar-Container').html("");
	$j('#pageToolBar-Container').html("");
	//Creating Page Tool Bar
	var ptbContainer = $j('<div id="netrxGeneralPTBContainer" />');
	var ptbdata =getRequestData('/youNeverWait/json/toolbars/viewGeneralToolbar.json');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).html(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
	var viewdata = getRequestData('/youNeverWait/json/view/viewnetrxdetails.json');
	var contentForm = new form(viewdata);
	$j('#tabs-1').html(contentForm.result);	
	viewnetrxInfo(netrxId);
}


    
function viewnetrxInfo(netrxId) {
	var netrxInfo = getNetRxData(netrxId);
	if(netrxInfo.netRxDTO.globalId)
		$j("#netrxViewForm #netrxid ").val(netrxInfo.netRxDTO.globalId);
	else
		$j("#netrxViewForm #netrxid ").val("Nil");
	if(netrxInfo.netRxDTO.name)
		$j("#netrxViewForm #organizationName").val(netrxInfo.netRxDTO.name);
	else
		$j("#netrxViewForm #organizationName").val('Nil');	
		if(netrxInfo.netRxDTO.headOfficeEmail)
		$j("#netrxViewForm #headOfficeEmail").val(netrxInfo.netRxDTO.headOfficeEmail);
	else
		$j("#netrxViewForm #headOfficeEmail").val('Nil');
	if(netrxInfo.netRxDTO.headOfficePhone)
		$j("#netrxViewForm #headOfficePhone").val(netrxInfo.netRxDTO.headOfficePhone);
	else 
		$j("#netrxViewForm #headOfficePhone").val('Nil');

	if(netrxInfo.netRxDTO.ownerFirstName)
		$j("#netrxViewForm #ownerFirstName").val(netrxInfo.netRxDTO.ownerFirstName);
	else 
		$j("#netrxViewForm #ownerFirstName").val('Nil');
	if(netrxInfo.netRxDTO.ownerLastName)
		$j("#netrxViewForm #ownerLastName").val(netrxInfo.netRxDTO.ownerLastName);
	else 
		$j("#netrxViewForm #ownerLastName").val('Nil');	
		
	if(netrxInfo.netRxDTO.ownerEmail)
		$j("#netrxViewForm #ownerEmail").val(netrxInfo.netRxDTO.ownerEmail);
	else 
		$j("#netrxViewForm #ownerEmail").val('Nil');
	if(netrxInfo.netRxDTO.ownerPhone)
		$j("#netrxViewForm #ownerPhone").val(netrxInfo.netRxDTO.ownerPhone);
	else 
		$j("#netrxViewForm #ownerPhone").val('Nil');
	if(netrxInfo.netRxDTO.ownerMobile)
		$j("#netrxViewForm #ownerMobile").val(netrxInfo.netRxDTO.ownerMobile);
	else 
		$j("#netrxViewForm #ownerMobile").val('Nil');
	if(netrxInfo.netRxDTO.ownerAddress)
		$j("#netrxViewForm #ownerAddress").val(br2nl(netrxInfo.netRxDTO.ownerAddress));
	else 
		$j("#netrxViewForm #ownerAddress").val('Nil');	


if(netrxInfo.netRxDTO.headOfficeName)
		$j("#netrxViewForm #headOfficeName").val(netrxInfo.netRxDTO.headOfficeName);
	else 
		$j("#netrxViewForm #headOfficeName").val('Nil');
	if(netrxInfo.netRxDTO.headOfficeMobile)
		$j("#netrxViewForm #headOfficeMobile").val(netrxInfo.netRxDTO.headOfficeMobile);
	else 
		$j("#netrxViewForm #headOfficeMobile").val('Nil');
	if(netrxInfo.netRxDTO.headOfficeAddress)
		$j("#netrxViewForm #headOfficeAddress").val(br2nl(netrxInfo.netRxDTO.headOfficeAddress));
	else 
		$j("#netrxViewForm #headOfficeAddress").val('Nil');
	if(netrxInfo.netRxDTO.userName)
		$j("#netrxViewForm #usernameNetrx").val(netrxInfo.netRxDTO.userName);
	else 
		$j("#netrxViewForm #usernameNetrx").val('Nil');		
	
}	

function submitNetrxInfo(){
	var resultJson = createSubmitJson();
	//alert(resultJson);
	var response = postdataToServer("/youNeverWait/ws/ui/superAdmin/updateNetRx", resultJson );
	//alert(JSON.stringify(response));
	return response;
}

function createSubmitJson(){
	var netrxname;
	netrxname=$j('#netrxViewForm #organizationName').val() ;
	netrxname=netrxname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerfirstname;
	ownerfirstname= $j('#netrxViewForm #ownerFirstName').val();
	ownerfirstname=ownerfirstname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerlastname;
	ownerlastname= $j('#netrxViewForm #ownerLastName').val();
	ownerlastname=ownerlastname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var headofficename;
	headofficename= $j('#netrxViewForm #headOfficeName').val();
	headofficename=headofficename.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var submitdata;
	submitdata = '{'   +'"name"' + ':"'+netrxname+'",';
	submitdata+='"globalId"' +':' + $j('#netrxViewForm #netrxid').val() +',';
	submitdata+='"ownerFirstName"' +':"' +ownerfirstname+'",';
	submitdata+='"ownerLastName"' +':"' +ownerlastname+'",';
	submitdata+='"ownerEmail"' +':"' + $j('#netrxViewForm #ownerEmail').val() +'",';
	submitdata+='"ownerPhone"' +':"' + $j('#netrxViewForm #ownerPhone').val() +'",';
	submitdata+='"ownerMobile"' +':"' + $j('#netrxViewForm #ownerMobile').val() +'",';
	submitdata+='"ownerAddress"' +':"' +nl2br($j('#netrxViewForm #ownerAddress').val()) +'",';
	submitdata+='"headOfficeName"' +':"' +headofficename+'",';
	submitdata+='"headOfficeEmail"' +':"' + $j('#netrxViewForm #headOfficeEmail').val() +'",';
	submitdata+='"headOfficePhone"' +':"' + $j('#netrxViewForm #headOfficePhone').val() +'",';
	submitdata+='"headOfficeMobile"' +':"' + $j('#netrxViewForm #headOfficeMobile').val() +'",';
	submitdata+='"headOfficeAddress"' +':"' + nl2br($j('#netrxViewForm #headOfficeAddress').val()) +'",';
	//submitdata+='"userName"' +':"' + $j('#netrxViewForm #userName').val() +'",';
	submitdata+='"userName"' +':"",';
	submitdata+='"password"' +':""'+  '}' ;
	return submitdata;
}