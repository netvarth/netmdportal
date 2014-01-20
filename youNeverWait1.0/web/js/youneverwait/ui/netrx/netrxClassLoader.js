function netrxClassLoader() {
	this.load=function() {
		$j.getScript("/youNeverWait/js/youneverwait/ui/netrx/netrxUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("netlimsAccUIStartup" + xhr + exception);
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netrx/newNetrxUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netrx/ViewNetrxUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netrx/ViewNetrxPTB.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/validation/NetrxValidator.js").done(function(script,textStatus){
		})
		//$j.getScript("/youNeverWait/js/validation/NetrxViewValidator.js").done(function(script,textStatus){
		//})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netrx/netrxSyncUI.js").done(function(script,textStatus){
		})
		//$j.getScript("/youNeverWait/js/youneverwait/ui/netrx/netmdbranch/netmdbranchClassLoader.js").done(function(script,textStatus){
		//})
	}
}