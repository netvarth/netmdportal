$j(document).ready(function(){
	validateNumber("#newNetrxForm #ownerPhone");
	validateNumber("#newNetrxForm #ownerMobile");
	validateNumber("#newNetrxForm #headofficephone");
	validateNumber("#newNetrxForm #headOfficeMobile");
    $j('#newNetrxForm #organizationname').focus();
	
	$j('#newNetrxForm #btnNewNetRXCreate').die('click').live("click",function() {
	removeErrors();	
		if(validateNewNetrx())
		{
		var response = submitNetrxInfo();
		if(response.success==true){
			showTip("NetRx Created Successfully");
			$j('#errorDivNewNetrxData').hide();
			$j("#newNetrxForm input[type=text], textarea").val("");
			$j("#newNetrxForm  input[type=text],#newNetrxForm input[type=password]").val("");
			$j.cachedScript(constant_NetRxEntry_Url).done(function(script, textStatus) {
			})
		}
		else {
			updateTipsNew(getErrorName(response.error),$j('#netrxModal #errorDivNewNetrxData'),$j('#netrxModal #errorDivHeader'));
		}
		}
	});
	 
	$j('#newNetrxForm #btnNewNetRXCancel').die('click').live("click",function() {
	removeErrors();
		 $j("#newNetrxForm input[type=text], textarea").val("");
		 $j("#newNetrxForm  input[type=text],#newNetrxForm input[type=password]").val("");
	});
	 
	
});	

function submitNetrxInfo(){
	var resultJson = createSubmitJson();
	//alert(resultJson);
	var response = postdataToServer(constant_newNetRx_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}

function createSubmitJson(){
	var netmdname;
	netmdname=$j('#newNetrxForm #organizationname').val();
	netmdname=netmdname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerfirstname;
	ownerfirstname=$j('#newNetrxForm #ownerfirstname').val();
	ownerfirstname=ownerfirstname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerlastname;
	ownerlastname=$j('#newNetrxForm #ownerlastname').val();
	ownerlastname=ownerlastname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var headofficename;
	headofficename= $j('#newNetrxForm #headofficename').val();
	headofficename=headofficename.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
    var netrxDetails = '{"name":"'+netmdname+'",';
		netrxDetails += '"ownerFirstName":"'+ownerfirstname+'",';
		netrxDetails += '"ownerLastName":"'+ownerlastname+'",';
		netrxDetails += '"ownerEmail":"'+ $j('#newNetrxForm #owneremail').val() +'",';
		netrxDetails +='"ownerPhone":"' + $j('#newNetrxForm #ownerphone').val() + '",';
		netrxDetails +='"ownerMobile":"' + $j('#newNetrxForm #ownermobile').val() + '",';
		netrxDetails +='"ownerAddress":"' + nl2br($j('#newNetrxForm #owneraddress').val()) + '",';
		netrxDetails +='"headOfficeName":"' +headofficename+ '",';
		netrxDetails +='"headOfficeEmail":"' + $j('#newNetrxForm #headofficeemail').val() + '",';
		netrxDetails +='"headOfficePhone":"' + $j('#newNetrxForm #headofficephone').val() + '",';
		netrxDetails +='"headOfficeMobile":"' + $j('#newNetrxForm #headofficemobile').val() + '",';
		netrxDetails +='"userName":"' + $j('#newNetrxForm #username').val() + '",';
		netrxDetails +='"password":"' + $j('#newNetrxForm #password').val() + '",';
		netrxDetails +='"headOfficeAddress":"' +nl2br($j('#newNetrxForm #headofficeaddress').val()) + '"}';
	return netrxDetails;
}

function validateNewNetrx(){
	var organizationName=$j('#newNetrxForm #organizationname');
	var headOfficeAddress=$j('#newNetrxForm #headofficeaddress');
	var headOfficeEmail=$j('#newNetrxForm #headofficeemail');
	var headOfficeName=$j('#newNetrxForm #headofficename');
	var headOfficePhone=$j('#newNetrxForm #headofficephone');
	var headOfficeMobile=$j('#newNetrxForm #headofficemobile');
	var ownerFirstName=$j('#newNetrxForm #ownerfirstname');
	var ownerLastName=$j('#newNetrxForm #ownerlastname');
	var ownerEmail=$j('#newNetrxForm #owneremail');
	var ownerMobile=$j('#newNetrxForm #ownermobile');
	var ownerPhone=$j('#newNetrxForm #ownerphone');
	var username=$j('#newNetrxForm #username');
	var password=$j('#newNetrxForm #password').val();
	var password1=$j('#newNetrxForm #password');
	var confirmPassword=$j("#newNetrxForm #rePassword").val();
	var confirmPassword1=$j("#newNetrxForm #rePassword");

	
	var bValid=true,OrgNameValid=true,HONameValid=true,HOEmailValid=true,OwnNameValid=true,OwnEmailValid=true,UserNameValid=true,PasswordValid=true,OrgNregValid=true,HONregValid=true,HOPhoneregValid=true,HOMobregValid=true,HOEmailregValid=true,OwnNregValid=true,OwnLNregValid=true,OwnMobregValid=true,OwnPhoneregValid=true,PwdCompValid=true,OwnEmailregValid=true,ConfirmPasswordValid=true;
	
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