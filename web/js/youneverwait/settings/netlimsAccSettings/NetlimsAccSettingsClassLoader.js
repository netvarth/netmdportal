function NetlimsAccSettingsClassLoader() {
	 this.load=function() {
		$j.getScript("/youNeverWait/js/youneverwait/settings/netlimsAccSettings/NetlimsAccChgpaswrdUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("netlimsAccUIStartup" + xhr + exception);
		})
		$j.getScript("/youNeverWait/js/validation/ChangePasswrdValidator.js").done(function(script,textStatus){
		}) 
		/* $j.getScript("/youNeverWait/js/youneverwait/settings/netlimsAccSettings/newBranchUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/settings/netlimsAccSettings/ViewNetlimsBranchUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/settings/netlimsAccSettings/ViewNetlimsBranchPTB.js").done(function(script,textStatus){
		})
		 */
	} 
}