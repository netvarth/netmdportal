
	var a = window.location.toString();
	var name = a.substring(a.indexOf("=")+1);
	
	
$j = jQuery.noConflict();/* avoid conflicts of js--*/
$j.ajaxSetup({async:false});

var serverPath = "";

$j(document).ready(function() {
	
	$j("#newPasswordNetlims").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#newPasswordNetlims").val()=="")
				return false;
			$j("#confirmPaaswordNetlims").focus();
		}		
	});
	$j("#confirmPaaswordNetlims").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#confirmPaaswordNetlims").val()=="")
				return false;
			$j("#resetPasswordNetlims").trigger('click');
		}		
	});

	$j('#resetPasswordNetlims').die('click').live("click",function(){
		if(validate()) {
			var  newPwd=$j('#newPasswordNetlims').val();
			var confirmPwd=$j('#confirmPaaswordNetlims').val();
			var loginData='{'+'"password"'+':"'+newPwd+'"'+','+'"userName"'+':"' + name +'"'+'}';
			var resetResult=postdataToServer("/youNeverWait/ws/ui/lab/resetPassword", loginData );
			if(resetResult.success==true)
			{
			$j('#newPasswordNetlims').val("");
			$j('#confirmPaaswordNetlims').val("");
			successTip("Password reset successful",'',$j('#loginError'));
			}

			
			/*$j.ajax({
				type: "POST",
				url: serverPath + "/weblims/ws/ui/auth/login",
				data: loginData,
				contentType: "application/json",
				dataType: "json",
				async: false,
				success:loginSuccess,
				error: loginError						
			});	*/	
	}
	});
	
	function loginSuccess(data) {
		if(data.success==true)
			location.reload();
		else
			errorTips("Incorrect username/password",'',$j('#loginError'));	
	}

	function loginError() {
		errorTips("Incorrect username/password",'',$j('#loginError'));

	}
	
	function validate() {
		var newPwd = $j('#newPasswordNetlims');
		var ConfirmPwd = $j('#confirmPaaswordNetlims'); 
		var bValid=true,NewPwdValid=true,ConfirmPwdValid=true,PwdMatch=true;
		//bValid = bValid && checkNull( newPwd, "New Password", $j('#loginError'));
		//bValid = bValid && checkNull( ConfirmPwd, "Confirm Password", $j('#loginError'));
		ConfirmPwdValid = checkNull( ConfirmPwd, "confirm password", $j('#loginError'));
		NewPwdValid = checkNull( newPwd, "new password", $j('#loginError'));
		NewPwdValid=NewPwdValid&&ConfirmPwdValid; 
		if($j('#confirmPaaswordNetlims').val()!=''&&$j('#newPasswordNetlims').val()!='')
		{
		
		if($j('#confirmPaaswordNetlims').val()!=$j('#newPasswordNetlims').val())
		{
		errorTips("*"+"password mismatch",'',$j('#loginError'));
		PwdMatch=false;
		}
		}
		PwdMatch=PwdMatch&&ConfirmPwdValid;
		bValid=bValid && PwdMatch;

		return bValid;
	}
	function checkNull(o,n, errorDiv){
		if(o.val().length=='0'){
			o.addClass("error");
			errorTips("*"+"Please enter"+" " + n,o ,errorDiv);
			return false;	
		} 
		else 
			return true;
	}
});	



	
	function postdataToServer(posturl, requestData ) {
	var responseData;
	jQuery.ajax({
		type: "POST",
		url: posturl,
		data: requestData,
		contentType: "application/json",
		dataType: "json",
		async: false,
		success:function(response) {
			if(response==null){
				//window.location.href="/weblims/ws/ui/html/startUp";
				location.reload();
				return false;
			}	
			else{
				responseData = response;
			}	
		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError);
			responseData = false;	
			//window.location.href="/weblims/ws/ui/html/startUp";
			location.reload();  
		}
	});	
	return responseData;
}

		function  errorTips(t,obj,errorDiv) {
		errorDiv.attr('style','color:#ff0000');
		errorDiv.text(t);
		setTimeout(function() {
			if(obj!='') {
				obj.focus();
				obj.removeClass('error');
				obj.removeAttr('style');
			}
			errorDiv.text("");
		}, 5000 );
	}
function showTip(message) {
	$j('#BeeperBox .UIBeep_Title').html(message);
	$j("#BeeperBox").show();
    timerId = setTimeout(function () {
        $j("#BeeperBox").hide();
    }, 5000);
}
	function  successTip(t,obj,errorDiv) {
		errorDiv.attr('style','color:#008000');
		errorDiv.text(t);
		setTimeout(function() {
			if(obj!='') {
				obj.focus();
				obj.removeClass('error');
				obj.removeAttr('style');
			}
			errorDiv.text("");
		}, 10000 );
	}