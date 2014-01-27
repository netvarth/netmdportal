function ClassLoader() {
	this.load = function () {
		
		
		$j.cachedScript("/youNeverWait/js/resource/ServerUrlProcessor.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/resource/PageHandler.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/resource/Constants.js").done(function(script, textStatus){
		})
		$j.cachedScript("/youNeverWait/js/resource/DataTableProcessor.js").done(function(script, textStatus){
		})
		$j.cachedScript("/youNeverWait/js/resource/CommonMethodInvoker.js").done(function(script, textStatus){
		})
		$j.cachedScript("/youNeverWait/js/resource/DataTableNavigator.js").done(function(script, textStatus){
		})	
		$j.cachedScript("/youNeverWait/js/resource/Validator.js").done(function(script, textStatus){
		})
		$j.cachedScript("/youNeverWait/js/resource/GlobalToolBarProcessor.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/resource/SuperAdminGlobalToolBarProcessor.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/resource/PageToolBarProcessor.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/resource/FilterToolBarProcessor.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/resource/SettingsToolBarProcessor.js").done(function(script,textStatus){
		})
	    $j.cachedScript("/youNeverWait/js/ClassInitializer.js").done(function(script, textStatus) {
		})
		
$j.cachedScript("/youNeverWait/js/framework/RequestHandler.js").done(function(script, textStatus) {
}) 
$j.cachedScript("/youNeverWait/js/framework/general.js").done(function(script, textStatus) {
})
$j.cachedScript("/youNeverWait/js/youneverwait/common/common.js").done(function(script, textStatus) {
})
$j.cachedScript("/youNeverWait/js/framework/invokeUIControls.js").done(function(script, textStatus) {
})
$j.cachedScript("/youNeverWait/js/framework/createModal.js").done(function(script, textStatus) {
})
$j.cachedScript("/youNeverWait/js/framework/validations.js").done(function(script, textStatus) {
})
$j.cachedScript("/youNeverWait/js/framework/notifier.js").done(function(script, textStatus) {
})
$j.cachedScript("/youNeverWait/js/framework/logout.js").done(function(script, textStatus) {
})

		$j.cachedScript("/youNeverWait/js/dto/SpecimenListDTO.js").done(function(script, textStatus) {
		})
		$j.cachedScript("/youNeverWait/js/dto/SpecimenCollectStatusDTO.js").done(function(script, textStatus) {
		})
		$j.cachedScript("/youNeverWait/js/dto/ErrorDTO.js").done(function(script, textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/ErrorMessageDTO.js").done(function(script, textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/ExpressionDTO.js").done(function(script, textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/ExpressionListDTO.js").done(function(script, textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/FilterDTO.js").done(function(script, textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/SpecimenDTO.js").done(function(script, textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/SpecimenListResponseDTO.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/TestSpecimenDTO.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/netlimsDTO.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/netmdDTO.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/netrxDTO.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/organizationDTO.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/BranchNetlimsDTO.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/BranchNetmdDTO.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/BranchNetrxDTO.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/CreateBranchDTO.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/ChangePasswrdDTO.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/NetmdMacPassphrseDTO.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/OrderTypeDTO.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/OrderTypePassDTO.js").done(function(script,textStatus){
		}) 
		$j.cachedScript("/youNeverWait/js/dto/BranchSyncDTO.js").done(function(script,textStatus){
		}) 
		$j.cachedScript("/youNeverWait/js/dto/OrderListDTO.js").done(function(script,textStatus){
		}) 
		$j.cachedScript("/youNeverWait/js/dto/BillListDTO.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/dto/HealthMonitorDTO.js").done(function(script,textStatus){
		}) 
		$j.cachedScript("/youNeverWait/js/dto/organizationUserDTO.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/impl/SuperAdminGlobalServiceImpl.js").done(function(script, textStatus) {
		})
		/*--------------------------------*/
		/* Specimen UI Pages */
		$j.cachedScript("/youNeverWait/js/youneverwait/netlims/Specimen/SpecimenUIStartup.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert(xhr + exception);
		})
		$j.cachedScript("/youNeverWait/js/youneverwait/netlims/Specimen/NewSpecimenUI.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert(xhr + exception);
		})
		$j.cachedScript("/youNeverWait/js/youneverwait/netlims/Specimen/ViewSpecimenUI.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert(xhr + exception);
		})
		$j.cachedScript("/youNeverWait/js/youneverwait/netlims/Specimen/ViewSpecimenPTB.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert(xhr + exception);
		})
		/* Specimen Validator */
		$j.cachedScript("/youNeverWait/js/framework/SpecimenValidator.js").done(function(script,textStatus){
		}).fail(function(xhr,status,exception) {
			alert(xhr + exception);
		})
		/* Specimen Service JS */
		$j.cachedScript("/youNeverWait/js/impl/SpecimenServiceImpl.js").done(function(script, textStatus){
		}).fail(function(xhr,status,exception) {
			alert("SpecimenServiceImpl" + xhr + exception);
		})
		
		$j.cachedScript("/youNeverWait/js/impl/NetlimsServiceImpl.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/impl/NetmdServiceImpl.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/impl/NetrxServiceImpl.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/impl/OrganizationServiceImpl.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/impl/NetlimsbranchServiceImpl.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/impl/NetmdbranchServiceImpl.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/impl/NetrxbranchServiceImpl.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/impl/OrganizationUserServiceImpl.js").done(function(script,textStatus){
		})
		
		$j.cachedScript("/youNeverWait/js/youneverwait/ui/netlims/netlimsClassLoader.js").done(function(script,textStatus){
		}) 
		$j.cachedScript("/youNeverWait/js/youneverwait/ui/netmd/netmdClassLoader.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/youneverwait/ui/organization/organizationClassLoader.js").done(function(script,textStatus){
		})
		$j.cachedScript("/youNeverWait/js/youneverwait/ui/netrx/netrxClassLoader.js").done(function(script,textStatus){
		})
		
	}
}