function ClassLoader() {
	this.load = function () {
		
		
		$j.getScript("/youNeverWait/js/resource/ServerUrlProcessor.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/resource/PageHandler.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/resource/Constants.js").done(function(script, textStatus){
		})
		$j.getScript("/youNeverWait/js/resource/DataTableProcessor.js").done(function(script, textStatus){
		})
		$j.getScript("/youNeverWait/js/resource/CommonMethodInvoker.js").done(function(script, textStatus){
		})
		$j.getScript("/youNeverWait/js/resource/DataTableNavigator.js").done(function(script, textStatus){
		})	
		$j.getScript("/youNeverWait/js/resource/Validator.js").done(function(script, textStatus){
		})
		$j.getScript("/youNeverWait/js/resource/GlobalToolBarProcessor.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/resource/SettingsToolBarProcessor.js").done(function(script,textStatus){
		})
		 $j.getScript("/youNeverWait/js/resource/PageToolBarProcessor.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/resource/FilterToolBarProcessor.js").done(function(script,textStatus){
		})
		
		
		 
	    $j.getScript("/youNeverWait/js/ClassInitializer.js").done(function(script, textStatus) {
		})
		
$j.getScript("/youNeverWait/js/framework/RequestHandler.js").done(function(script, textStatus) {
}) 
$j.getScript("/youNeverWait/js/framework/general.js").done(function(script, textStatus) {
})
$j.getScript("/youNeverWait/js/youneverwait/common/common.js").done(function(script, textStatus) {
})
$j.getScript("/youNeverWait/js/framework/invokeUIControls.js").done(function(script, textStatus) {
})
$j.getScript("/youNeverWait/js/framework/createModal.js").done(function(script, textStatus) {
})
$j.getScript("/youNeverWait/js/framework/validations.js").done(function(script, textStatus) {
})
$j.getScript("/youNeverWait/js/framework/notifier.js").done(function(script, textStatus) {
})
$j.getScript("/youNeverWait/js/framework/logout.js").done(function(script, textStatus) {
})
$j.getScript("/youNeverWait/js/framework/login.js").done(function(script, textStatus) {
})

		$j.getScript("/youNeverWait/js/dto/SpecimenListDTO.js").done(function(script, textStatus) {
		})
		$j.getScript("/youNeverWait/js/dto/SpecimenCollectStatusDTO.js").done(function(script, textStatus) {
		})
		$j.getScript("/youNeverWait/js/dto/ErrorDTO.js").done(function(script, textStatus){
		})
		$j.getScript("/youNeverWait/js/dto/ErrorMessageDTO.js").done(function(script, textStatus){
		})
		$j.getScript("/youNeverWait/js/dto/ExpressionDTO.js").done(function(script, textStatus){
		})
		$j.getScript("/youNeverWait/js/dto/ExpressionListDTO.js").done(function(script, textStatus){
		})
		$j.getScript("/youNeverWait/js/dto/FilterDTO.js").done(function(script, textStatus){
		})
		$j.getScript("/youNeverWait/js/dto/SpecimenDTO.js").done(function(script, textStatus){
		})
		$j.getScript("/youNeverWait/js/dto/SpecimenListResponseDTO.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/dto/TestSpecimenDTO.js").done(function(script,textStatus){
		}) 
		$j.getScript("/youNeverWait/js/dto/BranchNetlimsDTO.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/dto/CreateBranchDTO.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/dto/ChangePasswrdDTO.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/impl/GlobalServiceImpl.js").done(function(script, textStatus) {
		})	
		/*--------------------------------*/
		/* Specimen UI Pages */
		$j.getScript("/youNeverWait/js/youneverwait/netlims/Specimen/SpecimenUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert(xhr + exception);
		})
		$j.getScript("/youNeverWait/js/youneverwait/netlims/Specimen/NewSpecimenUI.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert(xhr + exception);
		})
		$j.getScript("/youNeverWait/js/youneverwait/netlims/Specimen/ViewSpecimenUI.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert(xhr + exception);
		})
		$j.getScript("/youNeverWait/js/youneverwait/netlims/Specimen/ViewSpecimenPTB.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert(xhr + exception);
		})
		/* Specimen Validator */
		$j.getScript("/youNeverWait/js/framework/SpecimenValidator.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert(xhr + exception);
		})
		/* Specimen Service JS */
		$j.getScript("/youNeverWait/js/impl/SpecimenServiceImpl.js").done(function(script, textStatus){
		}).fail(function(xhr,status,exception) {
			alert("SpecimenServiceImpl" + xhr + exception);
		})
		$j.getScript("/youNeverWait/js/impl/NetlimsAccServiceImpl.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/ui/netlimsAccount/netlimsAccClassLoader.js").done(function(script,textStatus){
		})
		$j.getScript("/youNeverWait/js/youneverwait/settings/netlimsAccSettings/NetlimsAccSettingsClassLoader.js").done(function(script,textStatus){
		})
	}
}