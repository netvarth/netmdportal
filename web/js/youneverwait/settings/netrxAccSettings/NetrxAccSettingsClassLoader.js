function NetrxAccSettingsClassLoader() {
	 this.load=function() {
		$j.getScript("/youNeverWait/js/youneverwait/settings/netrxAccSettings/NetrxAccChgpaswrdUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("netlimsAccUIStartup" + xhr + exception);
		})
		$j.getScript("/youNeverWait/js/validation/ChangePasswrdValidator.js").done(function(script,textStatus){
		})
	} 
}