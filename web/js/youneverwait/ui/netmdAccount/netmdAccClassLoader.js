function netmdAccClassLoader() {
	this.load=function() {
		ajaxProcessor.setUrl("/youNeverWait/js/youneverwait/ui/netmdAccount/netMdtoolbarloader.js");
		ajaxProcessor.getJavaScript();
		

		//$j.cachedScript("/youNeverWait/js/youneverwait/ui/netmdAccount/netMdtoolbarloader.js").done(function(script,textStatus){
		//})
		$j.cachedScript("/youNeverWait/js/youneverwait/ui/netmdAccount/netmdAccUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("netlimsAccUIStartup" + xhr + exception);
		})
		$j.cachedScript("/youNeverWait/js/youneverwait/ui/netmdAccount/newBranchUI.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/youneverwait/ui/netmdAccount/ViewNetmdBranchUI.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/youneverwait/ui/netmdAccount/ViewNetmdBranchPTB.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/validation/NetmdBranchValidator.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/youneverwait/ui/netmdAccount/netmdAccSyncUI.js").done(function(script,textStatus){
		})
	}
}