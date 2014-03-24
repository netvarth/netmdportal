function NewInstallerUI(installerUIStartup) {
	this.createButton = $j("#newInstaller #btnNewVersionSave");
	this.cancelButton = $j('#newInstaller #btnNewVersionCancel');
	this.newBlockPage = "#newInstaller";
	this.installerModal = '#installerModal';
	this.errorHeader = $j('#installerModal #errorDivHeader');
	this.errorData = $j('#installerModal #errorDivNewInstallerData');
	this.application="#newInstaller #applicationName";
	this.versionNo="#newInstaller #versionNo";
	this.war="#newInstaller #warUpload";
	this.query="#newInstaller #queryUpload"
	this.inputFields = ":input";
	this.installerUIStartup = installerUIStartup;

}

NewInstallerUI.prototype.getInstallerUIStartup = function() {
	return this.installerUIStartup;
}

NewInstallerUI.prototype.getInstallerTableNavigator = function() {
	var installerUIStartup = this.getInstallerUIStartup();
	return installerUIStartup.getInstallerTableNavigator();
}

NewInstallerUI.prototype.getInstallerService = function() {
	var installerUIStartup = this.getInstallerUIStartup();
	return installerUIStartup.getInstallerService();
}

NewInstallerUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}

NewInstallerUI.prototype.init = function() {
	self =this;
	$j(self.war).html(self.getWarData());
	$j(self.query).html(self.getQueryData());
	self.bindEvents();
}
NewInstallerUI.prototype.getWarData = function() {
var pTag=$j('<p></p>');
var spanTag=$j('<span> Select War File</span>')
pTag.append(spanTag);
	var fileTag = $j('<input type="file"  name="file[]"  id="warfile"/>');
	pTag.append(fileTag);
	return pTag;
}

 
NewInstallerUI.prototype.getQueryData = function() {
var pTag=$j('<p></p>');
var spanTag=$j('<span> Select query File</span>')
pTag.append(spanTag);
	var fileTag = $j('<input type="file" name="file[]" id="queryfile"/>');
	pTag.append(fileTag);
	return pTag;
}
NewInstallerUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newInstallerPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
NewInstallerUI.prototype.clearFields = function() {
	self = this;
	$j(self.newInstallerPage + " input[type=text],textarea").val("");
	$j(self.name ).focus();
}

NewInstallerUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newInstallerPage + " " + self.inputFields);
	$j(self.newInstallerPage + " input[type=text] ").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
   $j(self.installerModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.installerModal + self.newInstallerPage + " input[type=text]").val("");	
		$j(self.installerModal).trigger('reveal:close');
		$j(self.installerModal).remove();
		self=self.getInstallerUIStartup();
	});
	
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.installerModal + self.newInstallerPage + " input[type=text]").val("");	
		$j(self.installerModal).trigger('reveal:close');
		$j(self.installerModal).remove();
		self=self.getInstallerUIStartup();
	});	

	   self.createButton.die('click').live('click',function(){
        $j.ajax({
            url: '/youNeverWait/ws/ui/installer/uploadNewVersion',
            type: 'POST',
            contentType:false,
            processData: false,
			enctype:'multipart/form-data',
			multiple:true,
            data: function(){
				var ajaxData = new FormData();
					$j.each($j("input[type=file]"), function(x, obj) {
						$j.each(obj.files,function(i,file){
							ajaxData.append('file['+x+']', file);
							
						})
				});
				 ajaxData.append('applicationName', "netmd");
				 ajaxData.append('versionNo', "1.2");
                return ajaxData;
            }(),
                success: function(result) {
                alert(result);
                },
				error: function(xhr, result, errorThrown){
				alert('Request failed.');
            }
            });

    }); 

	
}
