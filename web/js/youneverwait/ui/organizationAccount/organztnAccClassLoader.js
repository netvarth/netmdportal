function orgztnAccClassLoader() {
	this.load=function() {
		$j.getScript("/youNeverWait/js/youneverwait/ui/organizationAccount/orgztnLoader.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/organizationAccount/orgztnAccUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("netlimsAccUIStartup" + xhr + exception);
		})
		
		$j.getScript("/youNeverWait/js/youneverwait/ui/organizationAccount/orgztnTABCreator.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/organizationAccount/organztnHomDashbordCreator.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/organizationAccount/organztnREPORTCreator.js").done(function(script,textStatus){
		})
	}
}