function netlimsAccClassLoader() {
	this.load=function() {
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlimsAccount/netlimsAccUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert("netlimsAccUIStartup" + xhr + exception);
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlimsAccount/newBranchUI.js").done(function(script,textStatus){
		})
		//$j.getScript("/youNeverWait/js/youneverwait/ui/referral/ViewReferralUI.js").done(function(script,textStatus){
		//})
		//$j.getScript("/youNeverWait/js/youneverwait/ui/referral/ViewReferralPTB.js").done(function(script,textStatus){
		//})
		$j.getScript("/youNeverWait/js/validation/NetlimsBranchValidator.js").done(function(script,textStatus){
		}) 
	}
}