function organizationbranchClassLoader() {
	this.load=function() {
		$j.getScript("/youNeverWait/js/youneverwait/ui/organization/organizationbranch/organizationBranchUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("netlimsAccUIStartup" + xhr + exception);
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/organization/organizationbranch/newBranchUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/organization/organizationbranch/VieworganizationBranchUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/organization/organizationbranch/VieworganizationBranchPTB.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/validation/OrganizationUserValidator.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/organization/organizationbranch/organizationAccSyncUI.js").done(function(script,textStatus){
		})
		
	}
}