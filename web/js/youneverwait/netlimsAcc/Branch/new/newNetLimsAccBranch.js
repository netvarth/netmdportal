var userdata =getRequestData('/youNeverWait/ws/ui/auth/getUser');
netlimsId=userdata.labId;


	$j('#newBranchNetlimsAccForm #btnNewBranchCreate').die('click').live("click",function() {	
	removeErrors();	
		if(validateNewNetlimsAccBranch())
		{
		var response = submitNetlimsAccBranchInfo();
		//alert(JSON.stringify(response));
		if(response.success==true){
			//alert("success");
			showTip("Netlims Branch Created Successfully");
			$j("#newBranchNetlimsAccForm input[type=text], textarea").val("");
			$j("#newBranchNetlimsAccForm  input[type=text],#newBranchNetlimsAccForm input[type=password]").val("");
			$j.cachedScript(constant_NetLimsAccEntry_Url).done(function(script, textStatus) {
			})
		}
		else {
		updateTipsNew(getErrorName(response.error),$j('#netlimsAccBranchModal #errorDivNewNetlimsAccBranchData'),$j('#netlimsAccBranchModal #errorDivHeader'));
		//alert(JSON.stringify(response.error));
		}
		}
	});
	 
	$j('#newBranchNetlimsAccForm #btnNewBranchCancel').die('click').live("click",function() {
	removeErrors();
		 $j("#newBranchNetlimsAccForm input[type=text], textarea").val("");
		 $j("#newBranchNetlimsAccForm  input[type=text],#newBranchNetlimsAccForm input[type=password]").val("");
	});
	
	
	
function submitNetlimsAccBranchInfo(){
	var resultJson = createSubmitJson();
	//alert(resultJson);
	var response = postdataToServer(constant_newNetLimsAccBranch_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}

function createSubmitJson(){
	var branchname;
	branchname=$j('#newBranchNetlimsAccForm #organizationname').val() ;
	branchname=branchname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
    var netlimsDetails = '{"name":"'+branchname+'",';
		netlimsDetails += '"address":"'+ nl2br($j('#newBranchNetlimsAccForm #organizationaddress').val()) +'",';
		netlimsDetails += '"email":"'+ $j('#newBranchNetlimsAccForm #Email').val() +'",';
		netlimsDetails +='"phone":"' + $j('#newBranchNetlimsAccForm #phone').val() + '",';
		netlimsDetails +='"branchCode":"' + $j('#newBranchNetlimsAccForm #organizationaccbrcode').val() + '",';
		netlimsDetails +='"labId":' +netlimsId+ ',';
		netlimsDetails +='"mobile":"' + $j('#newBranchNetlimsAccForm #mobile').val() + '"}';
	return netlimsDetails;
}

function validateNewNetlimsAccBranch(){
	var organizationName=$j('#newBranchNetlimsAccForm #organizationname');
	var organizationAddress=$j('#newBranchNetlimsAccForm #organizationaddress');
	var phone=$j('#newBranchNetlimsAccForm #phone');
	var mobile=$j('#newBranchNetlimsAccForm #mobile');
	var email=$j('#newBranchNetlimsAccForm #Email');
	var brachcode=$j('#newBranchNetlimsAccForm #organizationaccbrcode');

	
	var bValid=true,OrgNameValid=true,OrgEmailValid=true,PhoneValid=true,MobValid=true,orgbcodevalid=true,OrgNregValid=true,OrgPhregValid=true,OrgMobregValid=true,OrgEmailregValid=true;
	OrgNameValid = checkNull( organizationName,constants_branchNameRequired);
	orgbcodevalid=checkNull(brachcode,constants_branchcodeRequired);
	OrgNameValid=orgbcodevalid&&OrgNameValid;
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