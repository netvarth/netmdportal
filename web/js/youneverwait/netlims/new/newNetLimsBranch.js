function createbranch(netlimsId){

	$j('#newBranchForm #organizationname').focus();

	$j('#newBranchForm #btnNewBranchCreate').die('click').live("click",function() {	
	removeErrors();	
		//alert("save");
		//var response = submitNetlimsBranchInfo();
		if(validateNewNetlimsBranch())
		{
		var response = submitNetlimsBranchInfo(netlimsId);
		//alert(JSON.stringify(response));
		if(response.success==true){
			//alert("success");
			showTip("Netlims Branch Created Successfully");
			$j("#newBranchForm input[type=text], textarea").val("");
			$j("#newBranchForm  input[type=text],#newBranchForm input[type=password]").val("");
			$j.cachedScript(constant_NetLimsEntry_Url).done(function(script, textStatus) {
			})
		}
		else {
		updateTipsNew(getErrorName(response.error),$j('#netlimsBranchModal #errorDivNewNetlimsBranchData'),$j('#netlimsBranchModal #errorDivHeader'));
			//alert(JSON.stringify(response.error));
			
		}
		}
	});
	 
	$j('#newBranchForm #btnNewBranchCancel').die('click').live("click",function() {
	removeErrors();
		 $j("#newBranchForm input[type=text], textarea").val("");
		 $j("#newBranchForm  input[type=text],#newBranchForm input[type=password]").val("");
	});
	 
	}


function submitNetlimsBranchInfo(netlimsId){
	var resultJson = createSubmitJson(netlimsId);
	//alert(resultJson);
	//validateNewNetlimsBranch();
	var response = postdataToServer(constant_newNetLimsBranch_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}

function createSubmitJson(netlimsId){
	var branchname;
	branchname=$j('#newBranchForm #organizationname').val();
	branchname=branchname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
    var netlimsDetails = '{"name":"'+branchname+'",';
		netlimsDetails += '"address":"'+ nl2br($j('#newBranchForm #organizationaddress').val()) +'",';
		netlimsDetails += '"email":"'+ $j('#newBranchForm #Email').val() +'",';
		netlimsDetails +='"phone":"' + $j('#newBranchForm #phone').val() + '",';
		//netlimsDetails += '"branchCode":"'+ $j('#newBranchForm #organizationbrcode').val() +'",';
		netlimsDetails +='"labId":' +netlimsId+ ',';
		netlimsDetails +='"mobile":"' + $j('#newBranchForm #mobile').val() + '"}';
	return netlimsDetails;
}

function validateNewNetlimsBranch(){
	var organizationName=$j('#newBranchForm #organizationname');
	var organizationAddress=$j('#newBranchForm #organizationaddress');
	//var organizationbrchcode=$j('#newBranchForm #organizationbrcode');
	var phone=$j('#newBranchForm #phone');
	var mobile=$j('#newBranchForm #mobile');
	var email=$j('#newBranchForm #Email');
	var homeBranch=$j('#newBranchForm #HomeBranch');

	
	var bValid=true,OrgNameValid=true,OrgEmailValid=true,PhoneValid=true,MobValid=true,orgbrachcodevalid=true,OrgNregValid=true,OrgPhregValid=true,OrgMobregValid=true,OrgEmailregValid=true;
	OrgNameValid = checkNull( organizationName,constants_branchNameRequired);
	//orgbrachcodevalid=checkNull(organizationbrchcode,constants_branchcodeRequired);
	//OrgNameValid=orgbrachcodevalid&&OrgNameValid;
	//OrgEmailValid = checkNull( email,constants_emailRequired);
	//OrgEmailValid=OrgEmailValid&&OrgNameValid;
	PhoneValid = checkNull( phone,constants_phoneRequired);
	PhoneValid=PhoneValid&&OrgNameValid;
	MobValid = checkNull( mobile,constants_mobileRequired);
	MobValid=MobValid&&PhoneValid;
	
	if((isEmpty(organizationName)))
	OrgNregValid =  checkRegexp(organizationName, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/, constants_organizationnameInvalidMessage ,$j('#errorDivNewNetlimsData'));
	OrgNregValid=OrgNregValid&&MobValid;
	
	if((isEmpty(phone)))
	OrgPhregValid= checkRegexp(phone,/^(0?[1-9]{1}[0-9]{9})$/,constants_phoneInvalid,$j('#errorDivNewNetlimsData'));
	OrgPhregValid=OrgPhregValid&&OrgNregValid;
	
	if((isEmpty(mobile)))
	OrgMobregValid= checkRegexp(mobile,/^[0-9]\d{9}$/,constants_headofficemobileInvalidMessage,$j('#errorDivNewNetlimsData'));
	OrgMobregValid=OrgMobregValid&&OrgPhregValid;
	
	if((isEmpty(email)))
	OrgEmailregValid = checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, constants_headofficeemailInvalidMessage,$j('#errorDivNewNetlimsData') );
	OrgEmailregValid=OrgEmailregValid&&OrgMobregValid;
	
	bValid=bValid && OrgEmailregValid;
	return bValid;
}