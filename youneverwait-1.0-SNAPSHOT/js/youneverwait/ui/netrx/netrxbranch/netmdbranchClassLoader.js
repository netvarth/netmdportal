function netmdbranchClassLoader() {
	this.load=function() {
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmd/netmdbranch/netmdBranchUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("netlimsAccUIStartup" + xhr + exception);
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmd/netmdbranch/newBranchUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmd/netmdbranch/ViewNetmdBranchUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmd/netmdbranch/ViewNetmdBranchPTB.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/validation/NetmdBranchValidator.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmd/netmdbranch/netmdAccSyncUI.js").done(function(script,textStatus){
		})
		
	}
}