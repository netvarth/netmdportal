function createbranch(netrxId){

$j('#newNetRxBranchForm #organizationname').focus();
	$j('#newNetRxBranchForm #btnNewBranchCreate').die('click').live("click",function() {	
		removeErrors();	
		if(validateNewNetrxBranch())
			{
				var response = submitNetrxBranchInfo(netrxId);
				if(response.success==true){
					showTip("NetRX Branch Created Successfully");
					$j("#newNetRxBranchForm input[type=text], textarea").val("");
					$j("#newNetRxBranchForm  input[type=text],#newNetRxBranchForm input[type=password]").val("");
					//$j.cachedScript(constant_NetLimsEntry_Url).done(function(script, textStatus) {
					//})
				}
				else {
				updateTipsNew(getErrorName(response.error),$j('#netrxbranchModal #errorDivNewNetrxBranchData'),$j('#netrxbranchModal #errorDivHeader'));


				}
			}
	});
	 
		
	$j('#newNetRxBranchForm #btnNewBranchCancel').die('click').live("click",function() {
		removeErrors();
		$j("#newNetRxBranchForm input[type=text], textarea").val("");
		$j("#newNetRxBranchForm  input[type=text],#newNetRxBranchForm input[type=password]").val("");
	});
	 
}


function submitNetrxBranchInfo(netrxId){
	var resultJson = createSubmitJson(netrxId);
	//alert(resultJson);
	var response = postdataToServer(constant_newNetRxBranch_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}


function createSubmitJson(netrxId){
	var branchname;
	branchname=$j('#newNetRxBranchForm #organizationname').val()  ;
	branchname=branchname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
    var netmdDetails = '{"name":"'+branchname+'",';
		netmdDetails += '"address":"'+ nl2br($j('#newNetRxBranchForm #organizationaddress').val()) +'",';
		netmdDetails += '"email":"'+ $j('#newNetRxBranchForm #Email').val() +'",';
		netmdDetails +='"phone":"' + $j('#newNetRxBranchForm #phone').val() + '",';
	
		netmdDetails +='"netRxId":' +netrxId+ ',';
		netmdDetails +='"numberOfDevices":' +$j('#newNetRxBranchForm #numberOfDevices').val() + ',';
		netmdDetails +='"mobile":"' + $j('#newNetRxBranchForm #mobile').val() + '"}';
	return netmdDetails;
}

	
function validateNewNetrxBranch(){
	var organizationName=$j('#newNetRxBranchForm #organizationname');
	var organizationAddress=$j('#newNetRxBranchForm #organizationaddress');
	var phone=$j('#newNetRxBranchForm #phone');
	var mobile=$j('#newNetRxBranchForm #mobile');
	var email=$j('#newNetRxBranchForm #Email');
	var homeBranch=$j('#newNetRxBranchForm #HomeBranch');
	var numberOfDevices=$j('#newNetRxBranchForm #numberOfDevices');

	
	var bValid=true,OrgNameValid=true,OrgEmailValid=true,PhoneValid=true,NoDevices=true,MobValid=true,OrgNregValid=true,OrgPhregValid=true,OrgMobregValid=true,OrgEmailregValid=true;
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
	OrgNregValid =  checkRegexp(organizationName, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/, constants_organizationnameInvalidMessage ,$j('#errorDivNewNetrxBranchData'));
	OrgNregValid=OrgNregValid&&NoDevices;
	
	if((isEmpty(phone)))
	OrgPhregValid= checkRegexp(phone,/^(0?[1-9]{1}[0-9]{9})$/,constants_phoneInvalid,$j('#errorDivNewNetrxBranchData'));
	OrgPhregValid=OrgPhregValid&&OrgNregValid;
	
	if((isEmpty(mobile)))
	OrgMobregValid= checkRegexp(mobile,/^[0-9]\d{9}$/,constants_headofficemobileInvalidMessage,$j('#errorDivNewNetrxBranchData'));
	OrgMobregValid=OrgMobregValid&&OrgPhregValid;
	
	if((isEmpty(email)))
	OrgEmailregValid = checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, constants_headofficeemailInvalidMessage,$j('#errorDivNewNetrxBranchData') );
	OrgEmailregValid=OrgEmailregValid&&OrgMobregValid;
	
	bValid=bValid && OrgEmailregValid;
	return bValid;
}	