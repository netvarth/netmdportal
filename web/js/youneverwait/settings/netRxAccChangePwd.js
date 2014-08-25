$j('#pageTitle').html("Settings");
//create the table structure for order Table
response = getRequestData('/youNeverWait/json/toolbars/netRxAccChangePwd.json');
var adminTB = new AdminToolBar(response);
$j('#tabs-1').html(adminTB.result);	
	
	$j('#btnNetRxChangePwd').die('click').live("click",function() {
	var obj=$j(this);
		createModal(constants_netRxAccChangePwdJson,'changepwdModalNetrx');	
		openModalBox(obj,'changepwdModalNetrx');
	});

	$j('#changePasswordFormNetRxAcc #btnChangePwdSubmit').die('click').live('click',function(){
	removeErrors();	
		if(validateNetRxAccChangePassword())
		{
			var response = submitChangePasswordInfo();
				if(response.success==true){
				showTip("Password Changed Successfully");
				$j('#errorDivChangePwdData').hide();
				$j("#changePasswordFormNetRxAcc input[type=text],#changePasswordFormNetRxAcc input[type=password]").val("");

			}
			else {
				updateTipsNew(getErrorName(response.error),$j('#changepwdModalNetrx #errorDivChangePwdData'),$j('#changepwdModalNetrx #errorDivHeader'));
			}
		}	
	});
	
function submitChangePasswordInfo(){
	var resultJson = createSubmitJson();
	var response = postdataToServer(constant_netRxAccChangePassword_Url, resultJson );	
	return response;
}

function createSubmitJson(){
	var userdata =getRequestData('/youNeverWait/ws/ui/auth/user');
    var changepwdDetails = '{"oldPassword":"'+$j('#changePasswordFormNetRxAcc #oldpassword').val()  +'",';
		changepwdDetails += '"username":"'+ userdata.userName +'",';
		changepwdDetails +='"newPassword":"' + $j('#changePasswordFormNetRxAcc #newpassword').val() + '"}';
	return changepwdDetails;
}


function validateNetRxAccChangePassword(){
	//var userName=$j('#changePasswordFormNetRxAcc #username');
	var oldPassword=$j('#changePasswordFormNetRxAcc #oldpassword');
	var newPassword=$j('#changePasswordFormNetRxAcc #newpassword');
	var confirmPassword=$j('#changePasswordFormNetRxAcc #confirmpassword');
	var newPassword1=$j('#changePasswordFormNetRxAcc #newpassword').val();
	var confirmPassword1=$j('#changePasswordFormNetRxAcc #confirmpassword').val();

	
	var bValid=true,userNameValid=true,oldPasswordValid=true,newPasswordValid=true,confirmPasswordValid=true,userNameregValid=true,PwdCompValid=true;
	//userNameValid = checkNull( userName,constants_userNameRequired);
	oldPasswordValid = checkNull( oldPassword,constants_oldPasswordRequired);
	oldPasswordValid=oldPasswordValid&&userNameValid;
	newPasswordValid = checkNull( newPassword,constants_newPasswordRequired);
	newPasswordValid=newPasswordValid&&oldPasswordValid;
	confirmPassword = checkNull( confirmPassword,constants_confirmPasswordRequired);
	confirmPassword=confirmPassword&&newPasswordValid;
	
	/*if((isEmpty(userName)))
	userNameregValid =  checkRegexp(userName, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/, constants_userNameInvalidMessage ,$j('#errorDivNewNetlimsData'));
	userNameregValid=userNameregValid&&confirmPassword;*/
	if((newPassword1!="")&&(confirmPassword1!="")){
	if(newPassword1!=confirmPassword1)
	{
		createError(constants_passwordMismatch,newPassword);
		PwdCompValid=false;
	}}
	PwdCompValid=PwdCompValid&&confirmPassword;
	
	bValid=bValid && PwdCompValid;
	return bValid;
}
	
	
$j('#changePasswordFormNetRxAcc #btnChangePwdCancel').die('click').live('click',function(){
	removeErrors();
	$j("#changePasswordFormNetRxAcc input[type=text],#changePasswordFormNetRxAcc input[type=password]").val("");
});
	