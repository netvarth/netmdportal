function organztnUIStartup() {
	this.organztnTabCreator = new organztnTABCreator();
	this.organztnReportCreator = new organztnReportCreator();
	this.organztnHomeCreator=new organztnHomeCreator();
}

organztnUIStartup.prototype.getorgnztnTabCreator = function() {
	return this.organztnTabCreator;
}

organztnUIStartup.prototype.init = function(userdata) {
	self = this;
	ajaxProcessor.setUrl(constants.ORGNZTNREPORTFILTERNETMDLISTURL + userdata.organisationId);
	var netMdlistdata =ajaxProcessor.get();
	var orgnztnTab = self.organztnTabCreator;
	var orgnztnHomeTab=self.organztnHomeCreator;
	var orgnztnReportTab=self.organztnReportCreator;
	orgnztnTab.create(constants.ORGNZTNTABCREATEVIEWURL,constants.ORGTABOBJ);
	orgnztnHomeTab.viewHome(constants.ORGNZTNHOMETABVIEWURL,constants.ORGHOMETABOBJ);
	orgnztnReportTab.viewReport(constants.ORGNZTNREPORTTABVIEWURL,constants.ORGREPORTTABOBJ,netMdlistdata);
}