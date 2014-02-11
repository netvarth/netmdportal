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
	this.superAdminSyncdata=function() {
		ajaxProcessor.setUrl(constants.SYNCDATASUPERADMINURL);
		return ajaxProcessor.get();
	}
	this.syncSuperadmin=function(syncData) {
		ajaxProcessor.setUrl(constants.SETSUPERADMINSYCURL);
		return ajaxProcessor.post(syncData);
	}
	this.changePasswrdSuperadmin=function(changePasswrd) {
		ajaxProcessor.setUrl(constants.SUPERADMINCHGPASWRDURL);
		return ajaxProcessor.post(changePasswrd);
	}
}