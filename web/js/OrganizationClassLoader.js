function ClassLoader() {
	this.load = function () {
		$j.cachedScript("/youNeverWait/js/resource/ServerUrlProcessor.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/youneverwait/ui/organization/organizationStartUp").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/youneverwait/ui/impl/OrganizationServiceImpl").done(function(script,textStatus){
		})
	}
}