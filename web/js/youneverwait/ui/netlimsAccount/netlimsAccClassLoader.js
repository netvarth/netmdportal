function netlimsAccClassLoader() {
	this.load=function() {
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlimsAccount/netlimsAccUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("netlimsAccUIStartup" + xhr + exception);
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlimsAccount/newBranchUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlimsAccount/ViewNetlimsBranchUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlimsAccount/ViewNetlimsBranchPTB.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/validation/NetlimsBranchValidator.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlimsAccount/netlimsAccSyncUI.js").done(function(script,textStatus){
		})
	}
}