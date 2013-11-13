function GlobalServiceImpl () {
	this.getRibbonTBData=function () {
		ajaxProcessor.setUrl(constants.NETLIMSACCRIBBONURL);
		return ajaxProcessor.get();
	}
	this.getLeftPaneTBData = function() {
		ajaxProcessor.setUrl(constants.NETLIMSACCLEFTPANEURL);
		return ajaxProcessor.get();
	}
	
}