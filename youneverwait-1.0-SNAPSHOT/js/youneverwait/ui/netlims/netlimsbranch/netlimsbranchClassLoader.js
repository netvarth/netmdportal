function netlimsbranchClassLoader() {
	this.load=function() {
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlims/netlimsbranch/netlimsBranchUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("netlimsAccUIStartup" + xhr + exception);
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlims/netlimsbranch/newBranchUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlims/netlimsbranch/ViewNetlimsBranchUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlims/netlimsbranch/ViewNetlimsBranchPTB.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/validation/NetlimsBranchValidator.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlims/netlimsbranch/netlimsAccSyncUI.js").done(function(script,textStatus){
		})
		
	}
}