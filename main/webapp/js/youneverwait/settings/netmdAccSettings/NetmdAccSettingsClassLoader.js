function NetmdAccSettingsClassLoader() {
	 this.load=function() {
		$j.getScript("/youNeverWait/js/youneverwait/settings/netmdAccSettings/NetmdAccChgpaswrdUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("netlimsAccUIStartup" + xhr + exception);
		})
		$j.getScript("/youNeverWait/js/validation/ChangePasswrdValidator.js").done(function(script,textStatus){
		})
	} 
}