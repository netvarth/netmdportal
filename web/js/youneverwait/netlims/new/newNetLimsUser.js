alert("new netlims user");

//$j(document).ready(function(){
function createUser(branchid){
	$j('#newUserForm #btnNewBUserCreate').die('click').live("click",function() {	
	removeErrors();	
	alert("save");
	//validateNewNetlimsUser();
		if(validateNewNetlimsUser())
		{
		var response = submitNetlimsUserInfo(branchid);
		alert(JSON.stringify(response));
		/*if(response.success==true){
			//alert("success");
			showTip("Netlims Branch Created Successfully");
			$j("#newUserForm input[type=text], textarea").val("");
			$j("#newUserForm  input[type=text],#newUserForm input[type=password]").val("");
			$j.cachedScript(constant_NetLimsEntry_Url).done(function(script, textStatus) {
			})
		}
		else {
			alert(JSON.stringify(response.error));
			
		}*/
		}
	});
	 
	$j('#newUserForm #btnNewBranchCancel').die('click').live("click",function() {
	removeErrors();
		 $j("#newUserForm input[type=text], textarea").val("");
		 $j("#newUserForm  input[type=text],#newUserForm input[type=password]").val("");
	});
	 
	}
//});	

function submitNetlimsUserInfo(branchid){
	var resultJson = createSubmitJson(branchid);
	//alert(resultJson);
	//validateNewNetlimsBranch();
	var response = postdataToServer(constant_newNetLimsBranch_Create_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}

function createSubmitJson(branchid){
    var netlimsDetails = '{"name":"'+$j('#organizationname').val()  +'",';
		netlimsDetails += '"address":"'+ $j('#organizationaddress').val() +'",';
		netlimsDetails += '"email":"'+ $j('#Email').val() +'",';
		netlimsDetails +='"phone":"' + $j('#phone').val() + '",';
	
		netlimsDetails +='"labId":' +branchid+ ',';
		netlimsDetails +='"mobile":"' + $j('#mobile').val() + '"}';
	return netlimsDetails;
}

function validateNewNetlimsUser(){
	var userName=$j('#username');
	var organizationAddress=$j('#organizationaddress');
	var phone=$j('#phone');
	var mobile=$j('#mobile');
	var email=$j('#Email');
	var homeBranch=$j('#HomeBranch');

	
	var bValid=true,UserNameValid=true,UserEmailValid=true,PhoneValid=true,MobValid=true,UserNregValid=true,UserPhregValid=true,UserMobregValid=true,UserEmailregValid=true;
	UserNameValid = checkNull( userName,constants_userNameRequired);
	//UserEmailValid = checkNull( email,constants_emailRequired);
	//UserEmailValid=UserEmailValid&&UserNameValid;
	PhoneValid = checkNull( phone,constants_phoneRequired);
	PhoneValid=PhoneValid&&UserNameValid;
	MobValid = checkNull( mobile,constants_mobileRequired);
	MobValid=MobValid&&PhoneValid;
	
	if((isEmpty(userName)))
	UserNregValid =  checkRegexp(userName, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/, constants_usernameInvalidMessage ,$j('#errorDivNewNetlimsData'));
	UserNregValid=UserNregValid&&MobValid;
	
	if((isEmpty(phone)))
	UserPhregValid= checkRegexp(phone,/^(0?[1-9]{1}[0-9]{9})$/,constants_phoneInvalid,$j('#errorDivNewNetlimsData'));
	UserPhregValid=UserPhregValid&&UserNregValid;
	
	if((isEmpty(mobile)))
	UserMobregValid= checkRegexp(mobile,/^[0-9]\d{9}$/,constants_headofficemobileInvalidMessage,$j('#errorDivNewNetlimsData'));
	UserMobregValid=UserMobregValid&&UserPhregValid;
	
	if((isEmpty(email)))
	UserEmailregValid = checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, constants_headofficeemailInvalidMessage,$j('#errorDivNewNetlimsData') );
	UserEmailregValid=UserEmailregValid&&UserMobregValid;
	
	bValid=bValid && UserEmailregValid;
	return bValid;
}