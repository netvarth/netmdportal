$j = jQuery.noConflict();/* avoid conflicts of js--*/
$j.ajaxSetup({async:false});

var serverPath = "";
 

$j(document).ready(function() {
	
	/*$j('.Home').die('click').live("click",function(){
		
		$j.get('/youNeverWait/ws/ui/lab/startUp', function(data){
		$j('html').html(data);
		});

	});
	
	 $j('.AboutNetlims').die('click').live("click",function(){
		
		$j.get('/youNeverWait/ws/ui/lab/aboutNetLims', function(data){
		$j('html').html(data);
		
		});
	
	}); 
	
	$j('.ContactUs').die('click').live("click",function(){
		
		$j.get('/youNeverWait/ws/ui/lab/contactUs', function(data){
		$j('html').html(data);
		});
	});
	
	
	$j('.Pricing').die('click').live("click",function(){
	
		$j.get('/youNeverWait/ws/ui/lab/pricing', function(data){
		$j('html').html(data);
		});
	
	});
	
	$j('.PrivacyPolicy').die('click').live("click",function(){
		$j.get('/youNeverWait/ws/ui/lab/privacyPolicy', function(data){
		$j('html').html(data);
		});
	
	});*/
	
	$j("#userNameNetlims").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#userNameNetlims").val()=="")
				return false;
			$j("#passwordNetlims").focus();
		}		
	});
	
	$j("#passwordNetlims").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#passwordNetlims").val()=="")
				return false;
			$j("#btnLoginNetlims").trigger('click');
		}		
	});
	
	
	$j('#forgotpwdNetlims').die('click').live("click",function(){
	window.location.href = "/youNeverWait/html/netlimsForgotPassword.html";
	});

	$j('#btnLoginNetlims').die('click').live("click",function(){
	
		if(validate()){
			
			
					var  username=$j('#userNameNetlims').val();
					var password=$j('#passwordNetlims').val();
					var userType=$j('#userTypeNetlims').val();
					var loginData='{'+'"userName"'+':"'+username+'"'+','+'"password"'+':"' + password +'","userType"'+':"' + userType +'"'+'}';
					$j.ajax({
						type: "POST",
						url: serverPath + "/youNeverWait/netlims/auth/netlimsLogin",
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
		//window.location.href = "/youNeverWait/html/netlimsIndex.html";
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
		var userId = $j('#userNameNetlims');
		var passwd = $j('#passwordNetlims'); 
		var bValid=true;
		bValid = bValid && checkNull( userId, "Please enter the username", $j('#loginError'));
		bValid = bValid && checkNull( passwd, "Please enter the password ", $j('#loginError'));
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