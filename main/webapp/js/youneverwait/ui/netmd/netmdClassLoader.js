function netmdClassLoader() {
	this.load=function() {
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmd/netmdUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("netlimsAccUIStartup" + xhr + exception);
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmd/newNetmdUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmd/ViewNetmdUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmd/ViewNetmdPTB.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/validation/NetmdValidator.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/validation/NetmdViewValidator.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmd/netmdSyncUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netmd/netmdbranch/netmdbranchClassLoader.js").done(function(script,textStatus){
		})
	}
}