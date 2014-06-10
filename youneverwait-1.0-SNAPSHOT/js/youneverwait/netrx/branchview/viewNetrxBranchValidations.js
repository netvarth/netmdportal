function validateviewnetrxbranch(){
	var organizationName=$j('#organizationName');
	var branchPhone=$j('#branchPhone');
	var branchMobile=$j('#branchMobile');
	var address=$j('#address');
	var email=$j('#email');
	
	
	var bValid=true,OrgNameValid=true,BRPhoneValid=true,BRMobileValid=true,BREmailValid=true,OrgNregValid=true,BRPhoneregValid=true,BRMobregValid=true,BREmailregValid=true;
	
	OrgNameValid = checkNull( organizationName,constants_organizationNameRequired);
	BRPhoneValid = checkNull( branchPhone,constants_phoneRequired);
	BRPhoneValid=BRPhoneValid&&OrgNameValid;
	BRMobileValid = checkNull( branchMobile,constants_mobileRequired);
	BRMobileValid=BRMobileValid&&BRPhoneValid;
	//BREmailValid = checkNull( email,constants_emailInvalid);
	//BREmailValid=BREmailValid&&BRMobileValid;
	
	
	if((isEmpty(organizationName)))
	OrgNregValid =  checkRegexp(organizationName, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/, constants_organizationnameInvalidMessage ,$j('#errorDivvwnetrxBranchData'));
	OrgNregValid=OrgNregValid&&BRMobileValid;
	if((isEmpty(branchPhone)))
	BRPhoneregValid= checkRegexp(branchPhone,/^(0?[1-9]{1}[0-9]{9})$/,constants_headofficephoneInvalidMessage,$j('#errorDivvwnetrxBranchData'));
	BRPhoneregValid=BRPhoneregValid&&OrgNregValid;
	if((isEmpty(branchMobile)))
	BRMobregValid= checkRegexp(branchMobile,/^[0-9]\d{9}$/,constants_headofficemobileInvalidMessage,$j('#errorDivvwnetrxBranchData'));
	BRMobregValid=BRMobregValid&&BRPhoneregValid;
	if((isEmpty(email)))
	BREmailregValid = checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, constants_headofficeemailInvalidMessage,$j('#errorDivvwnetrxBranchData') );
	BREmailregValid=BREmailregValid&&BRMobregValid;

	bValid=bValid && BREmailregValid;
	return bValid;
	
}