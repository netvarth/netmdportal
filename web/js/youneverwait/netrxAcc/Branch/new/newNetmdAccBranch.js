var userdata =getRequestData('/youNeverWait/netmd/auth/user');
netmdId=userdata.netmdId;


	$j('#newNetMdAccBranchForm #btnNewBranchCreate').die('click').live("click",function() {	
	removeErrors();	
		if(validateNewNetmdAccBranch())
		{
		var response = submitNetmdAccBranchInfo();
		//alert(JSON.stringify(response));
		if(response.success==true){
			//alert("success");
			showTip("Netmd Branch Created Successfully");
			$j("#newNetMdAccBranchForm input[type=text], textarea").val("");
			$j("#newNetMdAccBranchForm  input[type=text],#newNetMdAccBranchForm input[type=password]").val("");
			$j.cachedScript(constant_NetMdAccEntry_Url).done(function(script, textStatus) {
			})
		}
		else {
		updateTipsNew(getErrorName(response.error),$j('#netmdAccBranchModal #errorDivNewNetmdAccBranchData'),$j('#netmdAccBranchModal #errorDivHeader'));
		//alert(JSON.stringify(response.error));
		}
		}
	});
	 
	$j('#newNetMdAccBranchForm #btnNewBranchCancel').die('click').live("click",function() {
	removeErrors();
		 $j("#newNetMdAccBranchForm input[type=text], textarea").val("");
		 $j("#newNetMdAccBranchForm  input[type=text],#newNetMdAccBranchForm input[type=password]").val("");
	});
	
	
	
function submitNetmdAccBranchInfo(){
	var resultJson = createSubmitJson();
	//alert(resultJson);
	var response = postdataToServer(constant_newNetmdAccBranch_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}

function createSubmitJson(){
	var netMdbranchname;
	netMdbranchname=$j('#newNetMdAccBranchForm #organizationname').val() ;
	netMdbranchname=netMdbranchname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
    var netmdDetails = '{"name":"'+netMdbranchname+'",';
		netmdDetails += '"address":"'+ nl2br($j('#newNetMdAccBranchForm #organizationaddress').val()) +'",';
		netmdDetails += '"email":"'+ $j('#newNetMdAccBranchForm #Email').val() +'",';
		netmdDetails +='"phone":"' + $j('#newNetMdAccBranchForm #phone').val() + '",';
		netmdDetails +='"netMdId":' +netmdId+ ',';
		netmdDetails +='"numberOfDevices":' +$j('#newNetMdAccBranchForm #numberOfDevices').val() + ',';
		netmdDetails +='"mobile":"' + $j('#newNetMdAccBranchForm #mobile').val() + '"}';
	return netmdDetails;
}




function validateNewNetmdAccBranch(){
	var organizationName=$j('#newNetMdAccBranchForm #organizationname');
	var organizationAddress=$j('#newNetMdAccBranchForm #organizationaddress');
	var phone=$j('#newNetMdAccBranchForm #phone');
	var mobile=$j('#newNetMdAccBranchForm #mobile');
	var email=$j('#newNetMdAccBranchForm #Email');
	var numberOfDevices=$j('#newNetMdAccBranchForm #numberOfDevices');

	
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