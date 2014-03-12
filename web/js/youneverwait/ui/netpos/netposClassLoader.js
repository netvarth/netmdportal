function netposClassLoader() {
	this.load=function() {
	
	$j.getScript("/youNeverWait/js/youneverwait/ui/netpos/NetPosUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("NetPosUIStartup" + xhr + exception);
		})

		$j.getScript("/youNeverWait/js/youneverwait/ui/netpos/NewNetPosUI.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("NewNetPosUI" + xhr + exception);
		
		})
		
		$j.getScript("/youNeverWait/js/youneverwait/ui/netpos/ViewNetPosPTB.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("ViewNetPosPTB" + xhr + exception);
		})
		
		$j.getScript("/youNeverWait/js/youneverwait/ui/netpos/ViewNetPosUI.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("ViewNetPosUI" + xhr + exception);
		})
		
		$j.getScript("/youNeverWait/js/validation/NetPosValidator.js").done(function(script,textStatus){
		})
		
	}
	}
	