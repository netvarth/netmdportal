
	var a = window.location.toString();
	var name = a.substring(a.indexOf("=")+1);
	
	
$j = jQuery.noConflict();/* avoid conflicts of js--*/
$j.ajaxSetup({async:false});

var serverPath = "";

$j(document).ready(function() {
	
	$j("#newPwdNetlims").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#newPwdNetlims").val()=="")
				return false;
			$j("#confirmPwdNetlims").focus();
		}		
	});
	$j("#confirmPwdNetlims").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#confirmPwdNetlims").val()=="")
				return false;
			$j("#resetPwdNetlims").trigger('click');
		}		
	});

	$j('#resetPwdNetlims').die('click').live("click",function(){
		if(validate()) {
			var  newPwd=$j('#newPwdNetlims').val();
			var confirmPwd=$j('#confirmPwdNetlims').val();
			var loginData='{'+'"password"'+':"'+newPwd+'"'+','+'"username"'+':"' + name +'"'+','+'"confirmPassword"'+':"' + confirmPwd +'"'+'}';
			var resetResult=postdataToServer("/youNeverWait/ynw/ui/patient/createPassword", loginData );
			if(resetResult.success==true)
			{
			$j('#newPwdNetlims').val("");
			$j('#confirmPwdNetlims').val("");
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
		var newPwd = $j('#newPwdNetlims');
		var ConfirmPwd = $j('#confirmPwdNetlims'); 
		var bValid=true,NewPwdValid=true,ConfirmPwdValid=true,PwdMatch=true;
		//bValid = bValid && checkNull( newPwd, "New Password", $j('#loginError'));
		//bValid = bValid && checkNull( ConfirmPwd, "Confirm Password", $j('#loginError'));
		ConfirmPwdValid = checkNull( ConfirmPwd, "confirm password", $j('#loginError'));
		NewPwdValid = checkNull( newPwd, "new password", $j('#loginError'));
		NewPwdValid=NewPwdValid&&ConfirmPwdValid; 
		if($j('#confirmPwdNetlims').val()!=''&&$j('#newPwdNetlims').val()!='')
		{
		
		if($j('#confirmPwdNetlims').val()!=$j('#newPwdNetlims').val())
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