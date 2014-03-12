

function validateviewnetmd(){
var organizationName=$j('#organizationName');
	var headOfficeAddress=$j('#headOfficeAddress');
	var headOfficeEmail=$j('#headOfficeEmail');
	var headOfficeName=$j('#headOfficeName');
	var headOfficePhone=$j('#headOfficePhone');
	var headOfficeMobile=$j('#headOfficeMobile');
	var ownerName=$j('#ownerFirstName');
	var ownerLastName=$j('#ownerLastName');
	var ownerEmail=$j('#ownerEmail');
	var ownerMobile=$j('#ownerMobile');
	var ownerPhone=$j('#ownerPhone');
	var username=$j('#userName');
	
	var bValid=true,OrgNameValid=true,HONameValid=true,HOEmailValid=true,OwnNameValid=true,OwnLNregValid=true,OwnEmailValid=true,UserNameValid=true,PasswordValid=true,OrgNregValid=true,HONregValid=true,HOPhoneregValid=true,HOMobregValid=true,HOEmailregValid=true,OwnNregValid=true,OwnMobregValid=true,OwnPhoneregValid=true,PwdCompValid=true,OwnEmailregValid=true;
	
	OrgNameValid = checkNull( organizationName,constants_organizationNameRequired);
	HONameValid = checkNull( headOfficeName,constants_headofficeNameRequired);
	HONameValid=HONameValid&&OrgNameValid;
	HOEmailValid = checkNull( headOfficeEmail,constants_headofficeEmailRequired);
	HOEmailValid=HOEmailValid&&HONameValid;
	OwnNameValid = checkNull( ownerName,constants_ownerNameRequired);
	OwnNameValid=OwnNameValid&&HOEmailValid;
	OwnEmailValid = checkNull( ownerEmail,constants_ownerEmailRequired);
	OwnEmailValid=OwnEmailValid&&OwnNameValid;
	//UserNameValid = checkNull( username,constants_userNameRequired);
	//UserNameValid=UserNameValid&&OwnEmailValid;
	
	if((isEmpty(organizationName)))
	OrgNregValid =  checkRegexp(organizationName, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/, constants_organizationnameInvalidMessage ,$j('#errorDivNewNetlimsData'));
	OrgNregValid=OrgNregValid&&OwnEmailValid;
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
	if((isEmpty(ownerName)))
	OwnNregValid =  checkRegexp(ownerName, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/, constants_ownernameInvalidMessage ,$j('#errorDivNewNetlimsData'));
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
	//OwnPhoneregValid= checkRegexp(ownerPhone,/\(?([0-9]{4})\)?([ .-]?)([0-9])\d{6}$/,constants_ownerphoneInvalidMessage,$j('#errorDivNewNetlimsData'));
	OwnPhoneregValid=OwnPhoneregValid&&OwnMobregValid;
	bValid=bValid && OwnPhoneregValid;
	return bValid;
	
}