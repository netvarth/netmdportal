function GlobalServiceImpl () {
	this.getRibbonTBData=function () {
		ajaxProcessor.setUrl(constants.NETLIMSACCRIBBONURL);
		return ajaxProcessor.get();
	}
	this.getLeftPaneTBData = function() {
		ajaxProcessor.setUrl(constants.NETLIMSACCLEFTPANEURL);
		return ajaxProcessor.get();
	}
	this.getAdminTBData = function() {
		ajaxProcessor.setUrl(constants.ADMINTOOLBARJSON);
		return ajaxProcessor.get();
	}
	this.getnetMdAccRibbonTBData=function () {
		ajaxProcessor.setUrl(constants.NETMDACCRIBBONURL);
		return ajaxProcessor.get();
	}
	this.getnetMdAccLeftPaneTBData = function() {
		ajaxProcessor.setUrl(constants.NETMDACCLEFTPANEURL);
		return ajaxProcessor.get();
	}
	this.getAdminTBnetmdACcData = function() {
		ajaxProcessor.setUrl(constants.ADMINNETMDACCTOOLBARJSON);
		return ajaxProcessor.get();
	}
	this.getnetRxAccRibbonTBData=function () {
		ajaxProcessor.setUrl(constants.NETRXACCRIBBONURL);
		return ajaxProcessor.get();
	}
	this.getnetRxAccLeftPaneTBData = function() {
		ajaxProcessor.setUrl(constants.NETRXACCLEFTPANEURL);
		return ajaxProcessor.get();
	}
	this.getAdminTBnetrxACcData = function() {
		ajaxProcessor.setUrl(constants.ADMINNETMDACCTOOLBARJSON);
		return ajaxProcessor.get();
	}
}