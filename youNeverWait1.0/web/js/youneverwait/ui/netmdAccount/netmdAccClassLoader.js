function netmdAccClassLoader() {
	this.load=function() {
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmdAccount/netMdloader.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmdAccount/netmdAccUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("netlimsAccUIStartup" + xhr + exception);
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmdAccount/newBranchUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmdAccount/ViewNetmdBranchUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmdAccount/ViewNetmdBranchPTB.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/validation/NetmdBranchValidator.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmdAccount/netmdAccSyncUI.js").done(function(script,textStatus){
		})
	}
}