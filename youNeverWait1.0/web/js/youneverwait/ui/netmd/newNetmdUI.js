function NewNetmdUI(NetmdUIStartup) {
	this.netmdModal = '#netmdModal';
	this.newnetmdPage = this.netmdModal + " #newNetmdForm";
	this.createButton = $j(this.newnetmdPage + " #btnNewNetMDCreate");
	this.cancelButton = $j(this.newnetmdPage + " #btnNewNetMdCancel");
	this.errorHeader = $j(this.newnetmdPage + " #errorDivHeader");
	this.errorData = $j(this.newnetmdPage + " #errorDivNewNetmdData");
	this.headofficename=$j(this.newnetmdPage + " #headofficename");;
	this.inputFields = this.newnetmdPage + " :input[type=text]";
	this.organizationname=this.newnetmdPage + " #organizationname";
	this.headofficephone=this.newnetmdPage + " #headofficephone";
	this.headofficemobile=this.newnetmdPage + " #headofficemobile";
	this.headofficeaddress=this.newnetmdPage + " #headofficeaddress";
	this.headofficeemail=this.newnetmdPage + " #headofficeemail";
	this.ownerfirstname=this.newnetmdPage + " #ownerfirstname";
	this.ownerlastname=this.newnetmdPage + " #ownerlastname";
	this.owneraddress=this.newnetmdPage + " #owneraddress";
	this.ownerphone = this.newnetmdPage + " #ownerphone";
	this.ownermobile=this.newnetmdPage + " #ownermobile";
	this.owneremail = this.newnetmdPage + " #owneremail";
	this.password=this.newnetmdPage + " #password";
	this.rePassword=this.newnetmdPage + " #rePassword";
	this.username=this.newnetmdPage + " #username";
	this.netmdUIStartup = NetmdUIStartup;
	this.netmdCreationStatus= false;
	this.logo="#newNetmdForm #logosection";
	this.browseImage = this.newnetmdPage +" #file";
	this.selectedImage = this.newnetmdPage +" #logo";
	this.logoDiv=this.newnetmdPage+" #logoDiv";
}

NewNetmdUI.prototype.getNetmdCreationStatus = function() {
	return this.netmdCreationStatus;
}
NewNetmdUI.prototype.setNetmdCreationStatus = function(status) {
	this.netmdCreationStatus=status;
}
NewNetmdUI.prototype.getBranchUIStartup = function() {
	return this.netmdUIStartup;
}
NewNetmdUI.prototype.getnetmdTableNavigator = function() {
	var netmdUIStartup = this.getBranchUIStartup();
	return netmdUIStartup.getnetmdTableNavigator();
}
NewNetmdUI.prototype.getNetmdUIService = function() {
	var netmdUIStartup = this.getBranchUIStartup();
	return netmdUIStartup.getNetmdUIService();
}

NewNetmdUI.prototype.init = function() {
	self =this;
	self =this;
	$j(self.logo).html(self.getNetmdLogo());
	self.bindEvents();
}

NewNetmdUI.prototype.clearFields = function() {
	$j(this.newnetmdPage + " input[type=text]").val("");
	$j(this.newnetmdPage + " input[type=password]").val("");
	$j(this.newnetmdPage + " textarea").val("");
	$j(this.logoDiv).html('');
}
NewNetmdUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.inputFields);
	
}
NewNetmdUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
NewNetmdUI.prototype.getNetmdLogo = function() {
	var fullClass = $j('<div />');
	var leftcolumn=$j('<div class="column-left"/>');
	var leftDiv = $j('<div class="column-left"/>');
	var fileTag = $j('<input type="file" id="file"/>');
	leftDiv.append(fileTag);
	leftcolumn.append(leftDiv);
	var rightDiv = $j('<div class="column-right id=logodiv"/>');
	fileTag = $j('<img src="" id="logo" width="100px" height="60px"/>');
	rightDiv.append(fileTag);
	leftcolumn.append(rightDiv);
	fullClass.append(leftcolumn);
	
	var rightcolumn=$j('<div class="column-right"/>');
	var fulldiv = $j('<div class="five_sixth"/>');
	var Notes = $j('<div class="panel-heading"><h1 class="panel-title">upload Tips</h1></div><div ><ul type="circle"><li> The maximum file size for uploads is 25KB .</li><li>Only image files (JPG, GIF, PNG) are allowed </li></ul></div>');
	fulldiv.append(Notes);
	rightcolumn.append(fulldiv);
	fullClass.append(rightcolumn);
	return fullClass.html();
}
NewNetmdUI.prototype.cancel = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	$j(self.netmdModal + self.newnetmdPage + " input[type=text]").val("");	
	$j(self.netmdModal).trigger('reveal:close');
	$j(self.netmdModal).remove();
	if(self.getNetmdCreationStatus() == true){
	var exp = new ExpressionListDTO();
	var netmdTableNavigator = self.getnetmdTableNavigator();
	netmdTableNavigator.setExp(exp);
	netmdTableNavigator.list("netmdlist");
		
	}
	self.setNetmdCreationStatus(false);
	self=pageHandler.getActivePage();
}
NewNetmdUI.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var netmd = self.getNetmd();
	var netmdValidator = new NetmdValidator();
	var error  = netmdValidator.validate(netmd,self);
	//alert("Error"+error);
	if(error.errorStatus==false) {
		var netmdService = self.getNetmdUIService();
		var netmdResponse =netmdService.createNetmd(netmd);
		//alert(JSON.stringify(netmdResponse));
		if(netmdResponse.error==null) {
			self.setNetmdCreationStatus(true);
			showTip(constants.NETMDCREATESUCCESS);//For showing the global Tip			
			self.clearFields();
		} else 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netmdResponse.error));
	} else 
		self.createError(error); 
}
NewNetmdUI.prototype.getNetmd = function() {
	var self=this;
	var netmd = new NetmdDTO();
	var netmdname;
	netmdname=$j(self.organizationname).val();
	netmdname=netmdname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerfirstname;
	ownerfirstname=$j(self.ownerfirstname).val();
	ownerfirstname=ownerfirstname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var ownerlastname;
	ownerlastname=$j(self.ownerlastname).val();
	ownerlastname=ownerlastname.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	var headofficename;
	headofficename= $j(self.headofficename).val();
	headofficename=headofficename.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
	});
	
	netmd.setName(netmdname);
	netmd.setownerFirstName(ownerfirstname);
	netmd.setownerLastName(ownerlastname);
	netmd.setownerEmail($j(self.owneremail).val());
	netmd.setownerPhone($j(self.ownerphone).val());
	netmd.setownerMobile($j(self.ownermobile).val());
	netmd.setownerAddress(commonMethodInvoker.nl2br($j(self.owneraddress).val()));
	netmd.setheadOfficeAddress(commonMethodInvoker.nl2br($j(self.headofficeaddress).val()));
	netmd.setheadOfficeName(headofficename);
	netmd.setheadOfficeEmail($j(self.headofficeemail).val());
	netmd.setheadOfficePhone($j(self.headofficephone).val());
	netmd.setheadOfficeMobile($j(self.headofficemobile).val());
	netmd.setuserName($j(self.username).val());
	netmd.setpassword($j(self.password).val());
	netmd.setlogo($j(this.selectedImage).attr('src'));
	alert(JSON.stringify(netmd));
	return netmd;
}

NewNetmdUI.prototype.bindEvents = function() {
self = this;	
	self.removecolors(self.newnetmdPage + " :input");
	//prevent from entering characters
	commonMethodInvoker.validateNumber(self.headofficephone);
	commonMethodInvoker.validateNumber(self.headofficemobile);
	commonMethodInvoker.validateNumber(self.ownerphone);
	commonMethodInvoker.validateNumber(self.ownermobile);

$j(self.netmdModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});	
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
	
		$j(self.browseImage).change(function() {
		var reader = new FileReader();
    	var image  = new Image();
    	var file = this.files[0];
    	reader.readAsDataURL(file);  
    	reader.onload = function(_file) {
        	image.src    = _file.target.result;              // url.createObjectURL(file);
        	image.onload = function() {
	            var w = this.width,
	                h = this.height,
	                s = ~~(file.size/1024);
	            if(w!=150 && h!=60) {
	               alert("Image should be in 150x60 ");
	                $j(self.selectedImage).removeAttr('src');
	               return false;
	            }
	            if(s>=25) {
	               alert("Image size shouldn't exceed 25KB");
	                $j(self.selectedImage).removeAttr('src');
	               return false;
	            }
	            $j(self.selectedImage).attr('src', this.src);
	        };
        }
	});
}	