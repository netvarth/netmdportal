$j = jQuery.noConflict();/* avoid conflicts of js--*/
$j.ajaxSetup({async:false});

var serverPath = "";
var secretcode="";
$j.getScript("/youNeverWait/js/framework/RequestHandler.js").done(function(script, textStatus) {
})
$j.getScript("/youNeverWait/js/youneverwait/common/common.js").done(function(script, textStatus) {
}) 
$j(document).ready(function() { 
	
	var response = postdataToServer("/youNeverWait/ws/ui/auth/getCaptcha");
	if(response.success==true){
	$j("#log").html('<img src="data:image/png;base64,'+response.image + '"/>');
	$j('#ancap').show();
	secretcode=response.secretCode;
	}
	else{errorTips(getErrorName(response.error),'',$j('#loginError'));}
	
	$j("#userName").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#userName").val()=="")
				return false;
			$j("#password").focus();
		}		
	});
	$j("#password").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#password").val()=="")
				return false;
			$j("#capchavalue").focus();
		}		
	});
	$j("#capchavalue").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#capchavalue").val()=="")
				return false;
			$j("#btnLogin").trigger('click');
		}		
	});
	
	$j('#captcha').die('click').live("click",function(){
		var response = postdataToServer("/youNeverWait/ws/ui/auth/getCaptcha");
		$j("#log").html('<img src="data:image/png;base64,'+response.image + '"/>');
		$j('#ancap').show();
		secretcode=response.secretCode;
		
	});

$j('#forgotpwd').die('click').live("click",function(){
	window.location.href = "/youNeverWait/html/Forgotpassword.html";
	});
	$j('#btnLogin').die('click').live("click",function(){
	var captcha=$j('#capchavalue').val();
	//var secretcode=response.secretCode;
	if(validate())
	{
		if(existCaptcha())
		{
			if(verifyCaptcha(captcha,secretcode)) {
				var  username=$j('#userName').val();
				var password=$j('#password').val();
				var loginData='{'+'"userName"'+':"'+username+'"'+','+'"password"'+':"' + password +'"'+'}';
				$j.ajax({
					type: "POST",
					url: serverPath + "/youNeverWait/ws/ui/superAdmin/login",
					data: loginData,
					contentType: "application/json",
					dataType: "json",
					async: false,
					success:loginSuccess,
					error: loginError						
				});		
			}
			else
			{
			errorTips("Sorry , Your entry was incorrect . Please type again",'',$j('#loginError'));
			$j('#capchavalue').val("");
			}
		}

	}
	});
	
	function loginSuccess(data) {
		if(data.success==true)
		//window.location.href = "/youNeverWait/html/index.html";
		location.reload();
		else
		errorTips("Incorrect username/password",'',$j('#loginError'));	
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
	function loginError() {
		errorTips("Incorrect username/password",'',$j('#loginError'));

	}
	
	function validate() {
		var userId = $j('#userName');
		var passwd = $j('#password'); 
		var bValid=true;
		bValid = bValid && checkNull( userId, "Please enter the username", $j('#loginError'));
		bValid = bValid && checkNull( passwd, "Please enter the password ", $j('#loginError'));
		return bValid;
	}

	function existCaptcha() {

		var captchacode = $j('#capchavalue'); 
		var bValid=true;

		bValid = bValid && checkNull( captchacode, "Please enter the characters", $j('#loginError'));
		return bValid;
	}
	function checkNull(o,n, errorDiv){
		if(o.val().length=='0'){
			o.addClass("error");
			//errorTips( "* " + n + " must not be null" ,o ,errorDiv);
			errorTips( "* " + n ,o ,errorDiv);
			return false;	
		} 
		else 
			return true;
	}
});


function verifyCaptcha(captcha,secretCode){

var json='{'+'"secretCode"'+':"'+secretCode+'"'+','+'"verificationCode"'+':"' + captcha +'"'+'}';
var response = postdataToServer("/youNeverWait/ws/ui/auth/verifyCaptcha", json );
var resp=response.valid;
return resp;

}