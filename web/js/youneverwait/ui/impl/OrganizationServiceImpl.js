function OrganizationServiceImpl () {
	

}

OrganizationServiceImpl.prototype.createBlock=function (blockObj) {
	ajaxProcessor.setUrl(constants.CREATEBLOCKURL);
	return ajaxProcessor.post(blockObj);
}
OrganizationServiceImpl.prototype.updateBlock=function(blockObj) {
	ajaxProcessor.setUrl(constants.UPDATEBLOCKURL);
	return ajaxProcessor.post(blockObj);
}
OrganizationServiceImpl.prototype.deleteBlock=function(blockId) {
	ajaxProcessor.setUrl(constants.DELETEBLOCKURL + blockId);
	return ajaxProcessor.get();
}
OrganizationServiceImpl.prototype.viewBlock=function(blockId) {
	ajaxProcessor.setUrl(constants.VIEWBLOCKURL + blockId);
	return ajaxProcessor.get();
}