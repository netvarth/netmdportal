

function validateviewnetlimsaccbranch(){
	var organizationName=$j('#branchNetlimsAccViewForm #organizationName');
	var branchPhone=$j('#branchNetlimsAccViewForm #branchPhone');
	var branchMobile=$j('#branchNetlimsAccViewForm #branchMobile');
	var address=$j('#branchNetlimsAccViewForm #address');
	var email=$j('#branchNetlimsAccViewForm #email');
	//var branchaccode=$j('#branchNetlimsAccViewForm #accbranchcode');
	
	var bValid=true,OrgNameValid=true,BRPhoneValid=true,BRMobileValid=true,branchcodevalid=true,BREmailValid=true,OrgNregValid=true,BRPhoneregValid=true,BRMobregValid=true,BREmailregValid=true;
	
	OrgNameValid = checkNull( organizationName,constants_organizationNameRequired);
	//branchcodevalid=checkNull( branchaccode,constants_branchcodeRequired);
	//OrgNameValid=branchcodevalid&&OrgNameValid;
	BRPhoneValid = checkNull( branchPhone,constants_phoneRequired);
	BRPhoneValid=BRPhoneValid&&OrgNameValid;
	BRMobileValid = checkNull( branchMobile,constants_mobileRequired);
	BRMobileValid=BRMobileValid&&BRPhoneValid;
	//BREmailValid = checkNull( email,constants_emailInvalid);
	//BREmailValid=BREmailValid&&BRMobileValid;
	
	
	if((isEmpty(organizationName)))
	OrgNregValid =  checkRegexp(organizationName, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/, constants_organizationnameInvalidMessage ,$j('#errorDivNewNetlimsData'));
	OrgNregValid=OrgNregValid&&BRMobileValid;
	if((isEmpty(branchPhone)))
	BRPhoneregValid= checkRegexp(branchPhone,/^(0?[1-9]{1}[0-9]{9})$/,constants_headofficephoneInvalidMessage,$j('#errorDivNewNetlimsData'));
	BRPhoneregValid=BRPhoneregValid&&OrgNregValid;
	if((isEmpty(branchMobile)))
	BRMobregValid= checkRegexp(branchMobile,/^[0-9]\d{9}$/,constants_headofficemobileInvalidMessage,$j('#errorDivNewNetlimsData'));
	BRMobregValid=BRMobregValid&&BRPhoneregValid;
	if((isEmpty(email)))
	BREmailregValid = checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, constants_headofficeemailInvalidMessage,$j('#errorDivNewNetlimsData') );
	BREmailregValid=BREmailregValid&&BRMobregValid;

	bValid=bValid && BREmailregValid;
	return bValid;
	
}