$j(document).ready(function(){
	validateNumber("#newNetlimsForm #ownerPhone");
	validateNumber("#newNetlimsForm #ownerMobile");
	validateNumber("#newNetlimsForm #headofficephone");
	validateNumber("#newNetlimsForm #headOfficeMobile");
	$j("#newNetlimsForm #organizationname").focus();
	
	$j('#newNetlimsForm #btnNewNetLimsCreate').die('click').live("click",function() {
	removeErrors();	
		if(validateNewNetlims())
		{
		var response = submitNetlimsInfo();
		if(response.success==true){
			showTip("Netlims Created Successfully");
			$j('#errorDivNewNetlimsData').hide();
			$j("#newNetlimsForm input[type=text], textarea").val("");
			$j("#newNetlimsForm  input[type=text],#newNetlimsForm input[type=password]").val("");
			$j.cachedScript(constant_NetLimsEntry_Url).done(function(script, textStatus) {
			})
		}
		else {
			updateTipsNew(getErrorName(response.error),$j('#netlimsModal #errorDivNewNetlimsData'),$j('#netlimsModal #errorDivHeader'));
		}
		}
	});
	 
	$j('#newNetlimsForm #btnNewNetLimsCancel').die('click').live("click",function() {
	removeErrors();
		 $j("#newNetlimsForm input[type=text], textarea").val("");
		 $j("#newNetlimsForm  input[type=text],#newNetlimsForm input[type=password]").val("");
	});
	 
	
});	

function submitNetlimsInfo(){
	var resultJson = createSubmitJson();
	//alert(resultJson);
	//validateNewNetlims();
	var response = postdataToServer(constant_newNetLims_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}

function createSubmitJson(){
	var netmdname;
	netmdname=$j('#newNetlimsForm #organizationname').val();
	netmdname=netmdname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerfirstname;
	ownerfirstname=$j('#newNetlimsForm #ownerfirstname').val();
	ownerfirstname=ownerfirstname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerlastname;
	ownerlastname=$j('#newNetlimsForm #ownerlastname').val();
	ownerlastname=ownerlastname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var headofficename;
	headofficename= $j('#newNetlimsForm #headofficename').val();
	headofficename=headofficename.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
    var netlimsDetails = '{"name":"'+netmdname+'",';
		netlimsDetails += '"ownerFirstName":"'+ownerfirstname+'",';
		netlimsDetails += '"ownerLastName":"'+ownerlastname+'",';
		netlimsDetails += '"ownerEmail":"'+ $j('#newNetlimsForm #owneremail').val() +'",';
		netlimsDetails +='"ownerPhone":"' + $j('#newNetlimsForm #ownerphone').val() + '",';
		netlimsDetails +='"ownerMobile":"' + $j('#newNetlimsForm #ownermobile').val() + '",';
		netlimsDetails +='"ownerAddress":"' + nl2br($j('#newNetlimsForm #owneraddress').val()) + '",';
		netlimsDetails +='"headOfficeName":"' +headofficename+ '",';
		netlimsDetails +='"headOfficeEmail":"' + $j('#newNetlimsForm #headofficeemail').val() + '",';
		netlimsDetails +='"headOfficePhone":"' + $j('#newNetlimsForm #headofficephone').val() + '",';
		netlimsDetails +='"headOfficeMobile":"' + $j('#newNetlimsForm #headofficemobile').val() + '",';
		netlimsDetails +='"userName":"' + $j('#newNetlimsForm #username').val() + '",';
		netlimsDetails +='"password":"' + $j('#newNetlimsForm #password').val() + '",';
		netlimsDetails +='"headOfficeAddress":"' +nl2br($j('#newNetlimsForm #headofficeaddress').val()) + '"}';
	return netlimsDetails;
}

function validateNewNetlims(){
	var organizationName=$j('#newNetlimsForm #organizationname');
	var headOfficeAddress=$j('#newNetlimsForm #headofficeaddress');
	var headOfficeEmail=$j('#newNetlimsForm #headofficeemail');
	var headOfficeName=$j('#newNetlimsForm #headofficename');
	var headOfficePhone=$j('#newNetlimsForm #headofficephone');
	var headOfficeMobile=$j('#newNetlimsForm #headofficemobile');
	var ownerFirstName=$j('#newNetlimsForm #ownerfirstname');
	var ownerLastName=$j('#newNetlimsForm #ownerlastname');
	var ownerEmail=$j('#newNetlimsForm #owneremail');
	var ownerMobile=$j('#newNetlimsForm #ownermobile');
	var ownerPhone=$j('#newNetlimsForm #ownerphone');
	var username=$j('#newNetlimsForm #username');
	var password=$j('#newNetlimsForm #password').val();
	var password1=$j('#newNetlimsForm #password');
	var confirmPassword=$j("#newNetlimsForm #rePassword").val();
	var confirmPassword1=$j("#newNetlimsForm #rePassword");

	
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