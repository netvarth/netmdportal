
function ViewNetmdUI(netmdUIStartup){
	this.viewNetmdPage = "#viewNetmdHeader";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.pageTitle = $j('#pageTitle');
	this.updateButton = this.viewNetmdPage + " #netmdvwbtnDone";
	this.editButton = this.viewNetmdPage + " #netmdvwbtnEdit";
	this.cancelButton = this.viewNetmdPage + " #netmdvwbtnCancel";  
	this.ptbBack = '#netmdPTBContainer #btn_back_ptb_id';
	this.ptbUp = '#netmdPTBContainer #btn_up_ptb_id';
	this.ptbDown = '#netmdPTBContainer #btn_down_ptb_id';  
	this.netmdid = this.viewNetmdPage + " #netmdid ";
	this.inputFields = this.viewNetmdPage + " :input[type=text]";
	this.username=this.viewNetmdPage + " #usernameNetmd";
	this.organizationname=this.viewNetmdPage + " #organizationName";
	this.ownerfirstname = this.viewNetmdPage + " #ownerFirstName";
	this.ownerlastname=this.viewNetmdPage + " #ownerLastName";
	this.owneremail = this.viewNetmdPage + " #ownerEmail";
	this.ownerphone = this.viewNetmdPage + " #ownerPhone";
	this.ownermobile = this.viewNetmdPage + " #ownerMobile";
	this.headofficename = this.viewNetmdPage + " #headOfficeName";
	this.headofficephone = this.viewNetmdPage + " #headOfficePhone";
	this.headofficemobile = this.viewNetmdPage + " #headOfficeMobile";
	this.headofficeemail = this.viewNetmdPage + " #headOfficeEmail";
	this.owneraddress = this.viewNetmdPage + " #ownerAddress";
	this.headofficeaddress = this.viewNetmdPage + " #headOfficeAddress";
	this.netmdUIStartup=netmdUIStartup;
	this.logoDiv=this.viewNetmdPage + " #logo";
	this.viewNetmdPTB = new ViewNetmdPTB(this);
	this.browseImage=this.viewNetmdPage + " #file";
	this.selectedImage="#netmdlogo";
}

ViewNetmdUI.prototype.getNetmdUIService = function() {
	var netmdUIStartup = this.getNetmdUIStartup();
	return netmdUIStartup.getNetmdUIService();
}
ViewNetmdUI.prototype.getNetmdUIStartup = function() {
	return this.netmdUIStartup;
}
ViewNetmdUI.prototype.getnetmdTableNavigator = function() {
	var netmdUI = this.getNetmdUIStartup();
	return netmdUI.getnetmdTableNavigator();
}
ViewNetmdUI.prototype.setNetmdId= function(netmdId) {
	var netmdUI = this.getNetmdUIStartup();
	return netmdUI.setNetmdId(netmdId);
}
ViewNetmdUI.prototype.getNetmd = function() {
	return this.branch;
}
ViewNetmdUI.prototype.setNetmd = function(branch) {
	this.branch=branch;
}
ViewNetmdUI.prototype.getnetmdAdminTableNavigator = function() {
	var netmdUI = this.getNetmdUIStartup();
	return netmdUI.getnetmdAdminTableNavigator();
}
ViewNetmdUI.prototype.getviewNetmdPTB = function() {
	return this.viewNetmdPTB;
}

ViewNetmdUI.prototype.setPageTitle = function() {
	this.pageTitle.empty().html("View NetMD ");
}
ViewNetmdUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.inputFields);
	
}
ViewNetmdUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
ViewNetmdUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	commonMethodInvoker.validateNumber(self.headofficephone);
	commonMethodInvoker.validateNumber(self.headofficemobile);
	commonMethodInvoker.validateNumber(self.ownerphone);
	commonMethodInvoker.validateNumber(self.ownermobile);
	$j(self.viewNetmdPage + " input[type=text]").removeAttr('readonly');
	$j(self.viewNetmdPage + " input[type=text]").removeClass('newBox');
	$j(self.viewNetmdPage + " textarea").removeClass('newBox');
	$j(self.viewNetmdPage + " textarea").removeAttr('readonly');
		$j(self.netmdid).addClass('newBox');
		$j(self.netmdid).attr('readonly','readonly');
		$j(self.username).addClass('newBox');
		$j(self.username).attr('readonly','readonly');
		
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
}
ViewNetmdUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewNetmdPage + " input[type=text]").attr('readonly',true);
	$j(self.viewNetmdPage + " input[type=text]").addClass('newBox');
	$j(self.viewNetmdPage + " textarea").attr('readonly',true);
	$j(self.viewNetmdPage + " textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
}
ViewNetmdUI.prototype.init = function(netmdId) {
	var self = this;
	var viewNetmdPTB = self.getviewNetmdPTB();
	viewNetmdPTB.init(self);
	pageHandler.create(constants.VIEWNETMDDETAILPAGEURL);
	self.readable();
	self.viewNetmdDetails(netmdId);
	pageHandler.setActivePage(self);
	self.bindEvents();
	
}
ViewNetmdUI.prototype.editLogo=function(){

var fullClass = $j('<div />');
	var fileTag = $j('<input type="file" id="file"/>');
	$j('#logosection').append(fileTag);
	var rightDiv = $j('<div class="column-right"/>');
	var fulldiv = $j('<div class="five_sixth"/>');
	var Notes = $j('<div class="panel-heading"><h1 class="panel-title">upload Tips</h1></div><div ><ul type="circle"><li> The maximum file size for uploads is 25KB .</li><li>Only image files (JPG, GIF, PNG) are allowed </li></ul></div>');
	fulldiv.append(Notes);
	rightDiv.append(fulldiv);
	fullClass.append(rightDiv);
	$j(self.logoDiv).append(fullClass);
}
ViewNetmdUI.prototype.bindEvents = function() {
	self = this;
	parent = self;
	$j(self.editButton).die('click').live('click',function(){
 		self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self.writable();
		self.editLogo();
		self.fileEvents();
	});
	$j(self.cancelButton).die('click').live('click',function(){
		self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j('div #logo').html("");
		var netmdInfo = self.getNetmd();
		var netmdId = netmdInfo.netMd.globalId;
		self.viewNetmdDetails(netmdId);
		self.readable();
	});
	$j(self.updateButton).die('click').live('click',function(){
		 self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var netmd = self.getNetmdRequest();
		var netmdValidator = new NetmdViewValidator();
		var error  = netmdValidator.validate(netmd,self);
		if(error.errorStatus==false) {
			var netmdUIService = self.getNetmdUIService();
			var netmdResponse = netmdUIService.updateNetmd(netmd);
				if(netmdResponse.error==null) {
					showTip(constants.NETMDUPDATESUCCESS);//For showing the global Tip
					var netmdInfo = self.getNetmd();
					$j(self.logoDiv).html("");
					self.viewNetmdDetails(netmdInfo.netMd.globalId);
					self.readable();
				} else
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netmdResponse.error));
			} else
			self.createError(error);
	}); 
	 
}

ViewNetmdUI.prototype.viewNetmdDetails = function(netmdId) {
	self=this;
	self.setNetmdId(netmdId);
	var NetmdUIService = self.getNetmdUIService();
	var netmdInfo = NetmdUIService.viewNetmdDetails(netmdId);
	if(!netmdInfo.errorMessage) {
		self.setNetmd(netmdInfo);
		$j(self.netmdid).val(netmdInfo.netMd.globalId);
		$j(self.organizationname).val(netmdInfo.netMd.name);
		$j(self.headofficeemail).val(netmdInfo.netMd.headOfficeEmail);
		$j(self.headofficephone).val(netmdInfo.netMd.headOfficePhone);
		$j(self.ownerfirstname).val(netmdInfo.netMd.ownerFirstName);
		$j(self.ownerlastname).val(netmdInfo.netMd.ownerLastName);
		$j(self.owneremail).val(netmdInfo.netMd.ownerEmail);
		$j(self.ownerphone).val(netmdInfo.netMd.ownerPhone);
		$j(self.owneraddress).val(commonMethodInvoker.br2nl(netmdInfo.netMd.ownerAddress));
		$j(self.headofficeaddress).val(commonMethodInvoker.br2nl(netmdInfo.netMd.headOfficeAddress));
		$j(self.ownermobile).val(netmdInfo.netMd.ownerMobile);
		$j(self.headofficename).val(netmdInfo.netMd.headOfficeName);
		$j(self.headofficemobile).val(netmdInfo.netMd.headOfficeMobile);
		$j(self.username).val(netmdInfo.netMd.userName);
		self.displayLogo(netmdInfo.netMd.logo);
				
	} else 
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData,netmdInfo.errorMessage);
	self.setPageTitle("View NetMD ");
}

ViewNetmdUI.prototype.displayLogo = function(data) {
	var leftDiv = $j('<div class="column-left" id="logosection"/>');
	var pTag=$j('<p></p>');
	 pTag.empty();
	var titleTag=$j(' <span>Logo</span><br>');
	pTag.append(titleTag);
	var fileTag = $j('<img src="'+data +'" " id="netmdlogo" width="100px" height="60px" title="Logo"/>');
	pTag.append(fileTag);
	leftDiv.append(pTag);
	$j(self.logoDiv).append(leftDiv);
}
ViewNetmdUI.prototype.getNetmdRequest = function() {
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
	netmd.setglobalId(parseInt($j(self.netmdid).val()));
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
	netmd.setlogo($j(this.selectedImage).attr('src'));
	return netmd;
}

ViewNetmdUI.prototype.getPrevId = function(curId,branchResult) {
	var prevId;
	$j(branchResult.netMd).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.netMd).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=branchResult.netMd[index-1].globalId;
		}
	});
	return prevId;	
}
ViewNetmdUI.prototype.fileEvents=function(){

	$j('#viewNetmdHeader #file').change(function() {
		var reader = new FileReader();
    	var image  = new Image();
    	var file = this.files[0];
    	reader.readAsDataURL(file);  
    	reader.onload = function(_file) {
    		image.src="";
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
ViewNetmdUI.prototype.getNextId = function(curId,branchResult) {
	var nextId;
	$j(branchResult.netMd).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.netMd).length;
			var comp=arrayLength-1;
			if(index==comp)
				nextId = curId;
			else
				nextId=branchResult.netMd[index+1].globalId;	
		}
	});	
	return nextId;	
}  