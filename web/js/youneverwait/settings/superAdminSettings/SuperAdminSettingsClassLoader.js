function SuperAdminSettingsClassLoader() {
	 this.load=function() {
		$j.getScript("/youNeverWait/js/youneverwait/settings/superAdminSettings/SuperAdminAccChgpaswrdUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("NetlimsAccChgpaswrdUIStartup" + xhr + exception);
		})
		$j.getScript("/youNeverWait/js/validation/ChangePasswrdValidator.js").done(function(script,textStatus){
		}) 
		/*$j.getScript("/youNeverWait/js/youneverwait/settings/superAdminSettings/NetlimsOrderTypeUIStartup.js").done(function(script,textStatus){
		})
		 $j.getScript("/youNeverWait/js/youneverwait/settings/superAdminSettings/ViewNetlimsBranchUI.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/settings/superAdminSettings/ViewNetlimsBranchPTB.js").done(function(script,textStatus){
		})
		 */
	} 
}