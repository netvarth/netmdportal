var userdata =getRequestData('/youNeverWait/ynw/auth/user');
netrxId=userdata.netrxId;


	$j('#newNetRxBranchAccForm #btnNewBranchCreate').die('click').live("click",function() {	
	removeErrors();	
		if(validateNewNetrxAccBranch())
		{
		var response = submitNetrxAccBranchInfo();
		//alert(JSON.stringify(response));
		if(response.success==true){
			//alert("success");
			showTip("Netrx Branch Created Successfully");
			$j("#newNetRxBranchAccForm input[type=text], textarea").val("");
			$j("#newNetRxBranchAccForm  input[type=text],#newNetRxBranchAccForm input[type=password]").val("");
			$j.cachedScript(constant_NetRxAccEntry_Url).done(function(script, textStatus) {
			})
		}
		else {
		updateTipsNew(getErrorName(response.error),$j('#netrxAccBranchModal #errorDivNewNetrxAccBranchData'),$j('#netrxAccBranchModal #errorDivHeader'));
		//alert(JSON.stringify(response.error));
		}
		}
	});
	 
	$j('#newNetRxBranchAccForm #btnNewBranchCancel').die('click').live("click",function() {
	removeErrors();
		 $j("#newNetRxBranchAccForm input[type=text], textarea").val("");
		 $j("#newNetRxBranchAccForm  input[type=text],#newNetRxBranchAccForm input[type=password]").val("");
	});
	
	
	
function submitNetrxAccBranchInfo(){
	var resultJson = createSubmitJson();
	//alert(resultJson);
	var response = postdataToServer(constant_newNetrxAccBranch_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}

function createSubmitJson(){
	var netRxbranchname;
	netRxbranchname=$j('#newNetRxBranchAccForm #organizationname').val() ;
	netRxbranchname=netRxbranchname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
    var netmdDetails = '{"name":"'+netRxbranchname+'",';
		netmdDetails += '"address":"'+ nl2br($j('#newNetRxBranchAccForm #organizationaddress').val()) +'",';
		netmdDetails += '"email":"'+ $j('#newNetRxBranchAccForm #Email').val() +'",';
		netmdDetails +='"phone":"' + $j('#newNetRxBranchAccForm #phone').val() + '",';
		netmdDetails +='"netRxId":' +netrxId+ ',';
		netmdDetails +='"numberOfDevices":' +$j('#newNetRxBranchAccForm #numberOfDevices').val() + ',';
		netmdDetails +='"mobile":"' + $j('#newNetRxBranchAccForm #mobile').val() + '"}';
	return netmdDetails;
}




function validateNewNetrxAccBranch(){
	var organizationName=$j('#newNetRxBranchAccForm #organizationname');
	var organizationAddress=$j('#newNetRxBranchAccForm #organizationaddress');
	var phone=$j('#newNetRxBranchAccForm #phone');
	var mobile=$j('#newNetRxBranchAccForm #mobile');
	var email=$j('#newNetRxBranchAccForm #Email');
	var numberOfDevices=$j('#newNetRxBranchAccForm #numberOfDevices');

	
	var bValid=true,OrgNameValid=true,OrgEmailValid=true,PhoneValid=true,MobValid=true,OrgNregValid=true,OrgPhregValid=true,OrgMobregValid=true,OrgEmailregValid=true;
	OrgNameValid = checkNull( organizationName,constants_branchNameRequired);
	//OrgEmailValid = checkNull( email,constants_emailRequired);
	//OrgEmailValid=OrgEmailValid&&OrgNameValid;
	PhoneValid = checkNull( phone,constants_phoneRequired);
	PhoneValid=PhoneValid&&OrgNameValid;
	MobValid = checkNull( mobile,constants_mobileRequired);
	MobValid=MobValid&&PhoneValid;
	NoDevices=checkNull( numberOfDevices,constants_nodevicesRequired);
	NoDevices=NoDevices&&MobValid;
	
	if((isEmpty(organizationName)))
	OrgNregValid =  checkRegexp(organizationName, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/, constants_organizationnameInvalidMessage ,$j('#errorDivNewNetmdData'));
	OrgNregValid=OrgNregValid&&NoDevices;
	
	if((isEmpty(phone)))
	OrgPhregValid= checkRegexp(phone,/^[0-10]\d{10}$/,constants_phoneInvalid,$j('#errorDivNewNetmdData'));
	OrgPhregValid=OrgPhregValid&&OrgNregValid;
	
	if((isEmpty(mobile)))
	OrgMobregValid= checkRegexp(mobile,/^(0?[1-9]{1}[0-9]{9})$/,constants_headofficemobileInvalidMessage,$j('#errorDivNewNetmdData'));
	OrgMobregValid=OrgMobregValid&&OrgPhregValid;
	
	if((isEmpty(email)))
	OrgEmailregValid = checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, constants_headofficeemailInvalidMessage,$j('#errorDivNewNetmdAccBranchData') );
	OrgEmailregValid=OrgEmailregValid&&OrgMobregValid;
	
	bValid=bValid && OrgEmailregValid;
	return bValid;
}