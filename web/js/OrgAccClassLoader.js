function ClassLoader() {
	this.load = function () {
		
		$j.getScript("/youNeverWait/js/resource/ServerUrlProcessor.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/resource/PageHandler.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/resource/Constants.js").done(function(script, textStatus){
		})
		//$j.getScript("/youNeverWait/js/resource/DataTableProcessor.js").done(function(script, textStatus){
		//})
		//$j.getScript("/youNeverWait/js/resource/CommonMethodInvoker.js").done(function(script, textStatus){
		//})
		//$j.getScript("/youNeverWait/js/resource/DataTableNavigator.js").done(function(script, textStatus){
		//})	
		$j.getScript("/youNeverWait/js/resource/Validator.js").done(function(script, textStatus){
		})
		//$j.getScript("/youNeverWait/js/resource/GlobalToolBarProcessor.js").done(function(script,textStatus){
		//})
		//$j.getScript("/youNeverWait/js/resource/PageToolBarProcessor.js").done(function(script,textStatus){
		//})
		//$j.getScript("/youNeverWait/js/resource/FilterToolBarProcessor.js").done(function(script,textStatus){
		//})
		//$j.getScript("/youNeverWait/js/resource/SettingsToolBarProcessor.js").done(function(script,textStatus){
		//})
	    $j.getScript("/youNeverWait/js/ClassInitializer.js").done(function(script, textStatus) {
		})
		$j.getScript("/youNeverWait/js/framework/general.js").done(function(script, textStatus) {
		})
		//$j.getScript("/youNeverWait/js/framework/NVUIControls/NVGlobalToolBar.js").done(function(script, textStatus) {
		//})
		//$j.getScript("/youNeverWait/js/framework/NVUIControls/NVAdminToolBar.js").done(function(script, textStatus) {
		//})
		//$j.getScript("/youNeverWait/js/framework/NVUIControls/NVLeftpaneToolBar.js").done(function(script, textStatus) {
		//})
		$j.getScript("/youNeverWait/js/framework/validations.js").done(function(script, textStatus) {
		})
		$j.getScript("/youNeverWait/js/framework/notifier.js").done(function(script, textStatus) {
		})
		$j.getScript("/youNeverWait/js/framework/logout.js").done(function(script, textStatus) {
		})
		$j.getScript("/youNeverWait/js/dto/ErrorDTO.js").done(function(script, textStatus){
		})
		$j.getScript("/youNeverWait/js/dto/ErrorMessageDTO.js").done(function(script, textStatus){
		})
		$j.getScript("/youNeverWait/js/dto/ExpressionDTO.js").done(function(script, textStatus){
		})
		$j.getScript("/youNeverWait/js/dto/ExpressionListDTO.js").done(function(script, textStatus){
		})
		$j.getScript("/youNeverWait/js/dto/FilterDTO.js").done(function(script, textStatus){
		})
		//$j.getScript("/youNeverWait/js/dto/CreateBranchDTO.js").done(function(script,textStatus){
		//})
		//$j.getScript("/youNeverWait/js/dto/ChangePasswrdDTO.js").done(function(script,textStatus){
		//})
		//$j.getScript("/youNeverWait/js/dto/BranchSyncDTO.js").done(function(script,textStatus){
		//}) 
		//$j.getScript("/youNeverWait/js/impl/GlobalServiceImpl.js").done(function(script, textStatus) {
		//})
		//$j.getScript("/youNeverWait/js/youneverwait/ui/netlimsAccount/netlimsAccClassLoader.js").done(function(script,textStatus){
		//})
		//$j.getScript("/youNeverWait/js/youneverwait/ui/netmdAccount/netmdAccClassLoader.js").done(function(script,textStatus){
		//})
		//$j.getScript("/youNeverWait/js/youneverwait/ui/netrxAccount/netrxAccClassLoader.js").done(function(script,textStatus){
		//})
		$j.getScript("/youNeverWait/js/youneverwait/ui/organizationAccount/organztnAccClassLoader.js").done(function(script,textStatus){
		})
	}
}