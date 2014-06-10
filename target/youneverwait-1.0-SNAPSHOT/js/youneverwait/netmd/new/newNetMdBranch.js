function createbranch(netmdId){
	$j('#newNetMdBranchForm #organizationname').focus();


	$j('#newNetMdBranchForm #btnNewBranchCreate').die('click').live("click",function() {	
		removeErrors();	
		if(validateNewNetmdBranch())
			{
				var response = submitNetmdBranchInfo(netmdId);
				if(response.success==true){
					showTip("NetMD Branch Created Successfully");
					$j("#newNetMdBranchForm input[type=text], textarea").val("");
					$j("#newNetMdBranchForm  input[type=text],#newNetMdBranchForm input[type=password]").val("");
					//$j.cachedScript(constant_NetLimsEntry_Url).done(function(script, textStatus) {
					//})
				}
				else {
				updateTipsNew(getErrorName(response.error),$j('#netmdbranchModal #errorDivNewNetmdBranchData'),$j('#netmdbranchModal #errorDivHeader'));


				}
			}
	});
	 
		
	$j('#newNetMdBranchForm #btnNewBranchCancel').die('click').live("click",function() {
		removeErrors();
		$j("#newNetMdBranchForm input[type=text], textarea").val("");
		$j("#newNetMdBranchForm  input[type=text],#newNetMdBranchForm input[type=password]").val("");
	});
	 
}


function submitNetmdBranchInfo(netmdId){
	var resultJson = createSubmitJson(netmdId);
	//alert(resultJson);
	var response = postdataToServer(constant_newNetMdBranch_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}


function createSubmitJson(netmdId){
	var branchname;
	branchname=$j('#newNetMdBranchForm #organizationname').val()  ;
	branchname=branchname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
    var netmdDetails = '{"name":"'+branchname+'",';
		netmdDetails += '"address":"'+ nl2br($j('#newNetMdBranchForm #organizationaddress').val()) +'",';
		netmdDetails += '"email":"'+ $j('#newNetMdBranchForm #Email').val() +'",';
		netmdDetails +='"phone":"' + $j('#newNetMdBranchForm #phone').val() + '",';
	
		netmdDetails +='"netMdId":' +netmdId+ ',';
		netmdDetails +='"numberOfDevices":' +$j('#newNetMdBranchForm #numberOfDevices').val() + ',';
		netmdDetails +='"mobile":"' + $j('#newNetMdBranchForm #mobile').val() + '"}';
	return netmdDetails;
}

	
function validateNewNetmdBranch(){
	var organizationName=$j('#newNetMdBranchForm #organizationname');
	var organizationAddress=$j('#newNetMdBranchForm #organizationaddress');
	var phone=$j('#newNetMdBranchForm #phone');
	var mobile=$j('#newNetMdBranchForm #mobile');
	var email=$j('#newNetMdBranchForm #Email');
	var homeBranch=$j('#newNetMdBranchForm #HomeBranch');
	var numberOfDevices=$j('#newNetMdBranchForm #numberOfDevices');

	
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
	OrgNregValid =  checkRegexp(organizationName, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/, constants_organizationnameInvalidMessage ,$j('#errorDivNewNetmdBranchData'));
	OrgNregValid=OrgNregValid&&NoDevices;
	
	if((isEmpty(phone)))
	OrgPhregValid= checkRegexp(phone,/^(0?[1-9]{1}[0-9]{9})$/,constants_phoneInvalid,$j('#errorDivNewNetmdBranchData'));
	OrgPhregValid=OrgPhregValid&&OrgNregValid;
	
	if((isEmpty(mobile)))
	OrgMobregValid= checkRegexp(mobile,/^[0-9]\d{9}$/,constants_headofficemobileInvalidMessage,$j('#errorDivNewNetmdBranchData'));
	OrgMobregValid=OrgMobregValid&&OrgPhregValid;
	
	if((isEmpty(email)))
	OrgEmailregValid = checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, constants_headofficeemailInvalidMessage,$j('#errorDivNewNetmdBranchData') );
	OrgEmailregValid=OrgEmailregValid&&OrgMobregValid;
	
	bValid=bValid && OrgEmailregValid;
	return bValid;
}	