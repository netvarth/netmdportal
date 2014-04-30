function organizationClassLoader() {
	this.load=function() {
		$j.getScript("/youNeverWait/js/youneverwait/ui/organization/organizationUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("organizationUIStartup" + xhr + exception);
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/organization/newOrganizationUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/organization/ViewOrganizationUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/organization/ViewOrganizationPTB.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/validation/OrganizationValidator.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/validation/OrganizationViewValidator.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/organization/organizationSyncUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/organization/organizationbranch/organizationbranchClassLoader.js").done(function(script,textStatus){
		})
	}
}