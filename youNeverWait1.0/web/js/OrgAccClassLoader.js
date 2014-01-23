function ClassLoader() {
	this.load = function () {
		
		$j.cachedScript("/youNeverWait/js/resource/ServerUrlProcessor.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/resource/PageHandler.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/resource/Constants.js").done(function(script, textStatus){
		})
		$j.cachedScript("/youNeverWait/js/resource/CommonMethodInvoker.js").done(function(script, textStatus){
		})	
		$j.cachedScript("/youNeverWait/js/resource/Validator.js").done(function(script, textStatus){
		})
	    $j.cachedScript("/youNeverWait/js/ClassInitializer.js").done(function(script, textStatus) {
		})
		$j.cachedScript("/youNeverWait/js/framework/general.js").done(function(script, textStatus) {
		})
		$j.cachedScript("/youNeverWait/js/framework/validations.js").done(function(script, textStatus) {
		})
		$j.cachedScript("/youNeverWait/js/framework/notifier.js").done(function(script, textStatus) {
		})
		$j.cachedScript("/youNeverWait/js/framework/logout.js").done(function(script, textStatus) {
		})
		$j.cachedScript("/youNeverWait/js/dto/ErrorDTO.js").done(function(script, textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/ErrorMessageDTO.js").done(function(script, textStatus){
		})
		$j.cachedScript("/youNeverWait/js/youneverwait/ui/organizationAccount/organztnAccClassLoader.js").done(function(script,textStatus){
		})
	}
}