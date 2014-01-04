function SuperAdminGlobalServiceImpl () {
	this.getRibbonTBData=function () {
		ajaxProcessor.setUrl(constants.SUPERADMINRIBBONURL);
		return ajaxProcessor.get();
	}
	this.getLeftPaneTBData = function() {
		ajaxProcessor.setUrl(constants.SUPERADMINLEFTPANEURL);
		return ajaxProcessor.get();
	}
	this.getAdminTBData = function() {
		ajaxProcessor.setUrl(constants.SUPERADMINTOOLBARJSON);
		return ajaxProcessor.get();
	}
	
	
}