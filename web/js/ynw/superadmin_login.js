var ajaxProcessor=new ServerUrlProcessor();
validator = new Validator();
constants = new Constants();
query = new Query();
var errorHandler = new ErrorHandler();
$(document).ready(function() {
	var superadminLogin = new SuperadminLogin();
	superadminLogin.init();
});	
function Constants() {
	this.FORGOTPASSWORDURL = "/youNeverWait/html/Forgotpassword.html";
	this.LOGINURL = "/youNeverWait/superadmin/ui/superAdmin/login";
	this.FIELDREQUIRED = "Field required";
	this.INVALIDUSERNAMEPASSWORD = 	"Invalid UserId/Password";
	this.CAPTCHAURL="/youNeverWait/superadmin/auth/getCaptcha";
	this.CAPTCHAVERIFYURL="/youNeverWait/superadmin/auth/verifyCaptcha";
	this.INVALIDCAPTCHAIMAGE="Retype the text correctly";
	this.CAPCHALOADERROR="Problem while loading image";
}
function Query(){
	this.login = function(loginInfo) {
		ajaxProcessor.setUrl(constants.LOGINURL);
		return ajaxProcessor.post(loginInfo);
	}
	this.getCaptcha=function(){
		ajaxProcessor.setUrl(constants.CAPTCHAURL);
		return ajaxProcessor.get();
	}
	this.verifyCaptcha =function(captchaInfo){
		ajaxProcessor.setUrl(constants.CAPTCHAVERIFYURL);
		return ajaxProcessor.post(captchaInfo);
	}
}
function SuperadminLogin(){
	this.userName = "#userName";
	this.password = "#password";
	this.loginButton = "#btnLogin";
	this.forgotPassword = "#forgotpwd";
	this.captchaInput = "#capchavalue";
	this.errorInfo = "#loginError";
	this.captchaImage = "#captchaImage";
	this.captchaRefresh = "#captcha";
	this.secretCode = "";

	this.setSecretCode = function(secretCode){
		this.secretCode = secretCode;
	}
	this.getSecretCode = function(){
		return this.secretCode;
	}
	this.init=function() {
		var captchaReq = query.getCaptcha();
		if(captchaReq.success==true){
			$(this.captchaImage).empty().html('<img src="data:image/png;base64,'+captchaReq.image + '"/>');
			this.setSecretCode(captchaReq.secretCode);
		} else {
			errorHandler.addErrorMessage(self.errorInfo, constants.CAPCHALOADERROR);
			errorHandler.drawBorder_Error($(self.captchaInput));
		}
		this.bindEvents();
	}	
	this.bindEvents = function() {
		var self=this;
		errorHandler.removeErrorColor(this.userName);
		errorHandler.removeErrorColor(this.password);
		errorHandler.removeErrorColor(this.captchaInput);
		errorHandler.removeBorder_Error(this.userName, this.errorInfo);
		errorHandler.removeBorder_Error(this.password, this.errorInfo);
		errorHandler.removeBorder_Error(this.captchaInput, this.errorInfo);
		$(self.userName).bind("keypress", function (e) {
			if (e.keyCode == 13) {
				if($(self.userName).val()=="")
					return false;
				$(self.password).focus();
			}		
		});
		$(self.password).bind("keypress", function (e) {
			if (e.keyCode == 13) {
				if($(self.password).val()=="")
					return false;
				$(self.captchaInput).focus();
			}		
		});
		$(self.captchaInput).bind("keypress", function (e) {
			if (e.keyCode == 13) {
				if($(self.captchaInput).val()=="")
					return false;
				$(self.loginButton).trigger('click');
			}		
		});
		$(self.captchaRefresh).die('click').live("click",function(){
			var captchaReq = query.getCaptcha();
			if(captchaReq.success==true){
				$(self.captchaImage).empty().html('<img src="data:image/png;base64,'+captchaReq.image + '"/>');
				self.setSecretCode(captchaReq.secretCode);
			} else {
				errorHandler.addErrorMessage(self.errorInfo, constants.CAPCHALOADERROR);
				errorHandler.drawBorder_Error($(self.captchaInput));
			}
		});
		$(self.forgotPassword).die('click').live("click",function(){
			window.location.href = constants.FORGOTPASSWORDURL;
		});
		$(self.loginButton).die('click').live("click",function(){
			errorHandler.removeErrors();
			var login = new Login();
			login.setUserName($(self.userName).val());
			login.setPassword($(self.password).val());	
			var captcha = $(self.captchaInput).val();
			var error = self.validate();		
			if(error.errorStatus==false){
				var captchaInfo = new CaptchaInfo();
				captchaInfo.setSecretCode(self.getSecretCode());
				captchaInfo.setVerificationCode(captcha);
				var captchaVerifyReq = query.verifyCaptcha(captchaInfo);
				if(captchaVerifyReq.valid){
					var response = query.login(login);
					if(response.success==true)
						location.reload();
					else {
						errorHandler.addErrorMessage(self.errorInfo, constants.INVALIDUSERNAMEPASSWORD);
						return false;
					}
				} else {
					errorHandler.addErrorMessage(self.errorInfo, constants.INVALIDCAPTCHAIMAGE);
					errorHandler.drawBorder_Error($(self.captchaInput));
					$(self.captchaInput).val("");
					return false;
				}
			} else{
				self.createError(error);
				return false;
			}
		});
	}
	this.createError = function(error) {
		var self=this;
		$(error.errorMsgs).each(function(index, errormsg) {
			errorHandler.createError($(errormsg.errorField), errormsg.errorMessage);
			errorHandler.drawBorder_Error($(errormsg.errorField));
		});
	}
	this.validate = function() {
		var error = new ErrorDTO();
		var errorMsgs = [];
		if(validator.isEmpty($(this.userName).val())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(this.userName,constants.FIELDREQUIRED);
			errorMsgs.push(errorMessage);
		}
		if(validator.isEmpty($(this.password).val())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(this.password,constants.FIELDREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		if(validator.isEmpty($(this.captchaInput).val())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(this.captchaInput,constants.FIELDREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		error.setErrorMsgs(errorMsgs);
		return error;
	}
}