function netlimsClassLoader() {
	this.load=function() {
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlims/netlimsUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("netlimsAccUIStartup" + xhr + exception);
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlims/newNetlimsUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlims/ViewNetlimsUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlims/ViewNetlimsPTB.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/validation/NetlimsValidator.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/validation/NetlimsViewValidator.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlims/netlimsSyncUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlims/netlimsbranch/netlimsbranchClassLoader.js").done(function(script,textStatus){
			})
	}
}