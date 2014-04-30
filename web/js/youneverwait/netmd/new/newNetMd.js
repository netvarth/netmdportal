

$j(document).ready(function(){
	validateNumber("#newNetmdForm #ownerPhone");
	validateNumber("#newNetmdForm #ownerMobile");
	validateNumber("#newNetmdForm #headofficephone");
	validateNumber("#newNetmdForm #headOfficeMobile");
	$j('#newNetmdForm #organizationname').focus();
	
	$j('#newNetmdForm #btnNewNetMDCreate').die('click').live("click",function() {	
	removeErrors();	
		//alert("save");
		if(validateNewNetmd())
		{
		var response = submitNetmdInfo();
		//alert(JSON.stringify(response));
		if(response.success==true){
			//alert("success");
			showTip("NetMD Created Successfully");
			$j('#errorDivNewNetmdData').hide();
			$j("#newNetmdForm input[type=text], textarea").val("");
			$j("#newNetmdForm  input[type=text],#newNetmdForm input[type=password]").val("");
			$j.cachedScript(constant_NetMdEntry_Url).done(function(script, textStatus) {
			})
		}
		else {
			//alert(JSON.stringify(response.error));
			updateTipsNew(getErrorName(response.error),$j('#netmdModal #errorDivNewNetmdData'),$j('#netmdModal #errorDivHeader'));
		}
		}
	});
	 
	$j('#newNetmdForm #btnNewNetMDCancel').die('click').live("click",function() {
	removeErrors();
		 $j("#newNetmdForm input[type=text], textarea").val("");
		 $j("#newNetmdForm  input[type=text],#newNetmdForm input[type=password]").val("");
	});
	 
	
});	

function submitNetmdInfo(){
	var resultJson = createSubmitJson();
	//alert(resultJson);
	//validateNewNetmd();
	var response = postdataToServer(constant_newNetMd_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}

function createSubmitJson(){
	var netmdname;
	netmdname=$j('#newNetmdForm #organizationname').val();
	netmdname=netmdname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerfirstname;
	ownerfirstname=$j('#newNetmdForm #ownerfirstname').val();
	ownerfirstname=ownerfirstname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerlastname;
	ownerlastname=$j('#newNetmdForm #ownerlastname').val();
	ownerlastname=ownerlastname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var headofficename;
	headofficename=$j('#newNetmdForm #headofficename').val();
	headofficename=headofficename.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
    var netmdDetails = '{"name":"'+netmdname+'",';
		netmdDetails += '"ownerFirstName":"'+ownerfirstname+'",';
		netmdDetails += '"ownerLastName":"'+ownerlastname+'",';
		netmdDetails += '"ownerEmail":"'+ $j('#newNetmdForm #owneremail').val() +'",';
		netmdDetails +='"ownerPhone":"' + $j('#newNetmdForm #ownerphone').val() + '",';
		netmdDetails +='"ownerMobile":"' + $j('#newNetmdForm #ownermobile').val() + '",';
		netmdDetails +='"ownerAddress":"' + nl2br($j('#newNetmdForm #owneraddress').val()) + '",';
		netmdDetails +='"headOfficeName":"' +headofficename+ '",';
		netmdDetails +='"headOfficeEmail":"' + $j('#newNetmdForm #headofficeemail').val() + '",';
		netmdDetails +='"headOfficePhone":"' + $j('#newNetmdForm #headofficephone').val() + '",';
		netmdDetails +='"headOfficeMobile":"' + $j('#newNetmdForm #headofficemobile').val() + '",';
		netmdDetails +='"userName":"' + $j('#newNetmdForm #username').val() + '",';
		netmdDetails +='"password":"' + $j('#newNetmdForm #password').val() + '",';
		netmdDetails +='"userType":"'  + '",';
		netmdDetails +='"headOfficeAddress":"' + nl2br($j('#newNetmdForm #headofficeaddress').val()) + '"}';
	return netmdDetails;
}

function validateNewNetmd(){
	var organizationName=$j('#newNetmdForm #organizationname');
	var headOfficeAddress=$j('#newNetmdForm #headofficeaddress');
	var headOfficeEmail=$j('#newNetmdForm #headofficeemail');
	var headOfficeName=$j('#newNetmdForm #headofficename');
	var headOfficePhone=$j('#newNetmdForm #headofficephone');
	var headOfficeMobile=$j('#newNetmdForm #headofficemobile');
	var ownerFirstName=$j('#newNetmdForm #ownerfirstname');
	var ownerLastName=$j('#newNetmdForm #ownerlastname');
	var ownerEmail=$j('#newNetmdForm #owneremail');
	var ownerMobile=$j('#newNetmdForm #ownermobile');
	var ownerPhone=$j('#newNetmdForm #ownerphone');
	var username=$j('#newNetmdForm #username');
	var password=$j('#newNetmdForm #password').val();
	var password1=$j('#newNetmdForm #password');
	var confirmPassword=$j("#newNetmdForm #rePassword").val();
	var confirmPassword1=$j("#newNetmdForm #rePassword");

	
	var bValid=true,OrgNameValid=true,HONameValid=true,HOEmailValid=true,OwnNameValid=true,OwnLNregValid=true,OwnEmailValid=true,UserNameValid=true,PasswordValid=true,OrgNregValid=true,HONregValid=true,HOPhoneregValid=true,HOMobregValid=true,HOEmailregValid=true,OwnNregValid=true,OwnMobregValid=true,OwnPhoneregValid=true,PwdCompValid=true,OwnEmailregValid=true,ConfirmPasswordValid=true;
	
	OrgNameValid = checkNull( organizationName,constants_organizationNameRequired);
	HONameValid = checkNull( headOfficeName,constants_headofficeNameRequired);
	HONameValid=HONameValid&&OrgNameValid;
	HOEmailValid = checkNull( headOfficeEmail,constants_headofficeEmailRequired);
	HOEmailValid=HOEmailValid&&HONameValid;
	OwnNameValid = checkNull( ownerFirstName,constants_ownerNameRequired);
	OwnNameValid=OwnNameValid&&HOEmailValid;
	OwnEmailValid = checkNull( ownerEmail,constants_ownerEmailRequired);
	OwnEmailValid=OwnEmailValid&&OwnNameValid;
	UserNameValid = checkNull( username,constants_userNameRequired);
	UserNameValid=UserNameValid&&OwnEmailValid;
	PasswordValid = checkNull( password1,constants_passwordRequired);
	PasswordValid=PasswordValid&&UserNameValid;
	ConfirmPasswordValid = checkNull( confirmPassword1,constants_confirmpasswordRequired);
	ConfirmPasswordValid=ConfirmPasswordValid&&PasswordValid;
	
	if((isEmpty(organizationName)))
	OrgNregValid =  checkRegexp(organizationName, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/, constants_organizationnameInvalidMessage ,$j('#errorDivNewNetlimsData'));
	OrgNregValid=OrgNregValid&&ConfirmPasswordValid;
	if((isEmpty(headOfficeName)))
	HONregValid =  checkRegexp(headOfficeName, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/, constants_headofficenameInvalidMessage ,$j('#errorDivNewNetlimsData'));
	HONregValid=HONregValid&&OrgNregValid;
	if((isEmpty(headOfficePhone)))
	HOPhoneregValid= checkRegexp(headOfficePhone,/^(0?[1-9]{1}[0-9]{9})$/,constants_headofficephoneInvalidMessage,$j('#errorDivNewNetlimsData'));
	HOPhoneregValid=HOPhoneregValid&&HONregValid;
	if((isEmpty(headOfficeMobile)))
	HOMobregValid= checkRegexp(headOfficeMobile,/^[0-9]\d{9}$/,constants_headofficemobileInvalidMessage,$j('#errorDivNewNetlimsData'));
	HOMobregValid=HOMobregValid&&HOPhoneregValid;
	if((isEmpty(headOfficeEmail)))
	HOEmailregValid = checkRegexp( headOfficeEmail, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, constants_headofficeemailInvalidMessage,$j('#errorDivNewNetlimsData') );
	HOEmailregValid=HOEmailregValid&&HOMobregValid;
	if((isEmpty(ownerFirstName)))
	OwnNregValid =  checkRegexp(ownerFirstName, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/, constants_ownernameInvalidMessage ,$j('#errorDivNewNetlimsData'));
	OwnNregValid=OwnNregValid&&HOEmailregValid;
	if((isEmpty(ownerLastName)))
	OwnLNregValid =  checkRegexp(ownerLastName, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/, constants_ownerlastnameInvalidMessage ,$j('#errorDivNewNetlimsData'));
	OwnLNregValid=OwnLNregValid&&OwnNregValid;
	if((isEmpty(ownerEmail)))
	OwnEmailregValid = checkRegexp( ownerEmail, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, constants_owneremailInvalidMessage,$j('#errorDivNewNetlimsData') );
	OwnEmailregValid=OwnEmailregValid&&OwnLNregValid;
	if((isEmpty(ownerMobile)))
	OwnMobregValid= checkRegexp(ownerMobile,/^[0-9]\d{9}$/,constants_ownermobileInvalidMessage,$j('#errorDivNewNetlimsData'));
	OwnMobregValid=OwnMobregValid&&OwnEmailregValid;
	if((isEmpty(ownerPhone)))
	OwnPhoneregValid= checkRegexp(ownerPhone,/^(0?[1-9]{1}[0-9]{9})$/,constants_ownerphoneInvalidMessage,$j('#errorDivNewNetlimsData'));
	OwnPhoneregValid=OwnPhoneregValid&&OwnMobregValid;

	if(password!=confirmPassword)
	{
		//alert("its  not same");
		createError(constants_passwordMismatch,password1);
		PwdCompValid=false;
	}
	PwdCompValid=PwdCompValid&&OwnPhoneregValid;
	bValid=bValid && PwdCompValid;
	return bValid;
}