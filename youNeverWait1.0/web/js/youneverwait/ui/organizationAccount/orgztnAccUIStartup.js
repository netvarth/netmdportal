function organztnUIStartup() {
	this.organztnTabCreator = new organztnTABCreator();
	this.organztnReportCreator = new organztnReportCreator();
	
}

organztnUIStartup.prototype.getorgnztnTabCreator = function() {
	return this.organztnTabCreator;
}

organztnUIStartup.prototype.init = function() {
	self = this;
	var orgnztnTab = self.organztnTabCreator;
	var orgnztnReportTab=self.organztnReportCreator;
	orgnztnTab.create(constants.ORGNZTNTABCREATEVIEWURL,constants.ORGTABOBJ);
	orgnztnReportTab.viewReport(constants.ORGNZTNREPORTTABVIEWURL,constants.ORGREPORTTABOBJ);
}