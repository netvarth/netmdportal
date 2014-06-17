$j = jQuery.noConflict();/* avoid conflicts of js--*/
$j.ajaxSetup({async:false});

var serverPath = "";
$j.getScript("/youNeverWait/js/framework/RequestHandler.js").done(function(script, textStatus) {
}) 

$j(document).ready(function() {
	$j('.Home').die('click').live("click",function(){
		
		$j.get('/youNeverWait/ws/ui/netMd/startUp', function(data){
		$j('html').html(data);
		});

	});
	
	$j('.PrivacyPolicy').die('click').live("click",function(){
		$j.get('/youNeverWait/ws/ui/netMd/privacyPolicy', function(data){
		$j('html').html(data);
		});
	
	});
	
	$j("#userNameNetmd").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#userNameNetmd").val()=="")
				return false;
			$j("#passwordNetmd").focus();
		}		
	});
	
	$j("#passwordNetmd").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#passwordNetmd").val()=="")
				return false;
			$j("#btnLoginNetmd").trigger('click');
		}		
	});
	
	$j('#forgotpwdNetmd').die('click').live("click",function(){
	window.location.href = "/youNeverWait/html/netmdForgotPassword.html";
	});

	$j('#btnLoginNetmd').die('click').live("click",function(){
		
		if(validate()){
			
					var  username=$j('#userNameNetmd').val();
					var password=$j('#passwordNetmd').val();
					var loginData='{'+'"userName"'+':"'+username+'"'+','+'"password"'+':"' + password +'"'+'}';
					$j.ajax({
						type: "POST",
						url: serverPath + "/youNeverWait/netmd/auth/netmdLogin",
						data: loginData,
						contentType: "application/json",
						dataType: "json",
						async: false,
						success:loginSuccess,
						error: loginError						
					});	
				

		}
	});
	
	function loginSuccess(data) {
		if(data.success==true)
		//window.location.href = "/youNeverWait/html/netMdIndex.html";
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
		var userId = $j('#userNameNetmd');
		var passwd = $j('#passwordNetmd'); 
		var bValid=true;
		bValid = bValid && checkNull( userId, "Please enter the username", $j('#loginError'));
		bValid = bValid && checkNull( passwd, "Please enter the password ", $j('#loginError'));
		return bValid;
	}
	function checkNull(o,n, errorDiv){
		if(o.val().length=='0'){
			o.addClass("error");
			errorTips( "* " + n ,o ,errorDiv);
			return false;	
		} 
		else 
			return true;
	}

	
});