$j('#pageTitle').html("Settings");
//create the table structure for order Table
response = getRequestData('/youNeverWait/json/toolbars/netlimsAccChangePwd.json');
var adminTB = new AdminToolBar(response);
$j('#tabs-1').html(adminTB.result);	
	
	$j('#btnNetlimsChangePwd').die('click').live("click",function() {
	var obj=$j(this);
		createModal(constants_netlimsAccChangePwdJson,'changepwdModalNetLims');	
		openModalBox(obj,'changepwdModalNetLims');
	});
	
	$j('#btnNetlimsOrdertype').die('click').live("click",function() {
	var obj=$j(this);
		createModal(constants_netlimsOrdertypeJson,'orderTypeModalNetLims');	
		openModalBox(obj,'orderTypeModalNetLims');
		$j("#agentorder").val("AO");
		$j("#blanketorder").val("BO");
		$j("#walkinorder").val("WO");
	});
	
	$j('#btnordertypeSubmit').die('click').live("click",function() {
			removeErrors();	
		if(validateNetlimsorderType())
		{
			var response = submitorderType();
				if(response.success==true){
				showTip("Order Type Successfully");
				$j('#errorDivChangePwdData').hide();
				$j("#changePasswordFormNetLimsAcc input[type=text]").val("");

			}
			else {
				updateTipsNew(getErrorName(response.error),$j('#changepwdModalNetLims #errorDivChangePwdData'),$j('#changepwdModalNetLims #errorDivHeader'));
			}
		}	
	});
	
	$j('#btnordertypeCancel').die('click').live("click",function() {
		$j("#ordertypeFormNetLimsAcc input[type=text]").val("");
	});
	
	$j('#changePasswordFormNetLimsAcc #btnChangePwdSubmit').die('click').live('click',function(){
	removeErrors();	
		if(validateNetlimsAccChangePassword())
		{
			var response = submitChangePasswordInfo();
				if(response.success==true){
				showTip("Password Changed Successfully");
				$j('#errorDivChangePwdData').hide();
				$j("#changePasswordFormNetLimsAcc input[type=text],#changePasswordFormNetLimsAcc input[type=password]").val("");

			}
			else {
				updateTipsNew(getErrorName(response.error),$j('#changepwdModalNetLims #errorDivChangePwdData'),$j('#changepwdModalNetLims #errorDivHeader'));
			}
		}	
	});
function submitorderType(){
	var resultJson = createSubmitOrdertypeJson();
	var response = postdataToServer(constant_netlimsAccChangePassword_Url, resultJson );	
	return response;
}
function createSubmitOrdertypeJson(){
	var userdata =getRequestData('/youNeverWait/ws/ui/auth/getUser');
    var orderTypeDetails = '{"agentorder":"'+$j('#changePasswordFormNetLimsAcc #oldpassword').val()  +'",';
		orderTypeDetails += '"blanketorder":"'+ userdata.userName +'",';
		orderTypeDetails +='"walkinorder":"' + $j('#changePasswordFormNetLimsAcc #newpassword').val() + '"}';
	return orderTypeDetails;
}
function submitChangePasswordInfo(){
	var resultJson = createSubmitJson();
	var response = postdataToServer(constant_netlimsAccChangePassword_Url, resultJson );	
	return response;
}

function createSubmitJson(){
	var userdata =getRequestData('/youNeverWait/ws/ui/auth/getUser');
    var changepwdDetails = '{"oldPassword":"'+$j('#changePasswordFormNetLimsAcc #oldpassword').val()  +'",';
		changepwdDetails += '"username":"'+ userdata.userName +'",';
		changepwdDetails +='"newPassword":"' + $j('#changePasswordFormNetLimsAcc #newpassword').val() + '"}';
	return changepwdDetails;
}


function validateNetlimsAccChangePassword(){
	//var userName=$j('#changePasswordFormNetLimsAcc #username');
	var oldPassword=$j('#changePasswordFormNetLimsAcc #oldpassword');
	var newPassword=$j('#changePasswordFormNetLimsAcc #newpassword');
	var confirmPassword=$j('#changePasswordFormNetLimsAcc #confirmpassword');
	var newPassword1=$j('#changePasswordFormNetLimsAcc #newpassword').val();
	var confirmPassword1=$j('#changePasswordFormNetLimsAcc #confirmpassword').val();

	
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
	
	
$j('#changePasswordFormNetLimsAcc #btnChangePwdCancel').die('click').live('click',function(){
	removeErrors();
	$j("#changePasswordFormNetLimsAcc input[type=text],#changePasswordFormNetLimsAcc input[type=password]").val("");
});
	