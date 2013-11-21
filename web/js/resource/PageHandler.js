function PageHandler() {
	this.getHomePageUrl=function() {
		return "youNeverWait/ws/ui/lab/startUp";
	}
	
	this.create = function(url) {
		ajaxProcessor.setUrl(url);
		var pageContent = ajaxProcessor.get();
		var page = new form(pageContent);
		$j('#tabs-1').empty().html(page.result);
	}
	
	this.buttons = function(url, parent) {
		ajaxProcessor.setUrl(url);
		var response = ajaxProcessor.get();
		var buttonsDiv = new buttonsContainer(response);
		$j(parent).empty().html(buttonsDiv.result);
	}
	
	this.createSection = function(url) {
		ajaxProcessor.setUrl(url);
		var pageContent = ajaxProcessor.get();
		var page= new Container(pageContent);
		return page.result;
	}
	this.getHomePage = function() {
		return this.homePage;
	}
	this.setHomePage = function(homePage) {
		this.homePage = homePage;
	}
	this.getActivePage = function() {
		return this.activePage;
	}
	this.setActivePage = function(activePage) {
		this.activePage = activePage;
	}
	this.isnetlimsAccClassLoaded=function(){
		return this.netlimsAccClassLoaded;
	}
	this.setnetlimsAccClassLoaded=function(status){
		this.netlimsAccClassLoaded=status;
	}
	this.isnetlimsAccSettingsClassLoaded=function(){
		return this.netlimsAccSettingsClassLoaded;
	}
	this.setnetlimsAccSettingsClassLoaded=function(status){
		this.netlimsAccSettingsClassLoaded=status;
	}
	/* this.isOrderClassLoaded=function(){
		return this.orderClassLoaded;
	}
	this.setOrderClassLoaded=function(status){
		this.orderClassLoaded=status;
	}
	this.isAgentClassLoaded=function(){
		return this.agentClassLoaded;
	}
	this.setAgentClassLoaded=function(status){
		this.agentClassLoaded=status;
	}
	this.isAreaClassLoaded=function(){
		return this.areaClassLoaded;
	}
	this.setAreaClassLoaded=function(status){
		this.areaClassLoaded=status;
	}
	this.isBlanketClassLoaded=function(){
		return this.blanketClassLoaded;
	}
	this.setBlanketClassLoaded=function(status){
		this.blanketClassLoaded=status;
	}
	this.isDepartmentClassLoaded=function(){
		return this.departmentClassLoaded;
	}
	this.setDepartmentClassLoaded=function(status){
		this.departmentClassLoaded=status;
	}
	this.isDiscountClassLoaded=function(){
		return this.discountClassLoaded;
	}
	this.setDiscountClassLoaded=function(status){
		this.discountClassLoaded=status;
	}
	this.isFacilityClassLoaded=function(){
		return this.facilityClassLoaded;
	}
	this.setFacilityClassLoaded=function(status){
		this.facilityClassLoaded=status;
	}
	this.isReferralClassLoaded=function(){
		return this.referralClassLoaded;
	}
	this.setReferralClassLoaded=function(status){
		this.referralClassLoaded=status;
	}
	this.isRoleClassLoaded=function(){
		return this.roleClassLoaded;
	}
	this.setRoleClassLoaded=function(status){
		this.roleClassLoaded=status;
	}
	this.isSettingClassLoaded=function(){
		return this.settingClassLoaded;
	}
	this.setSettingClassLoaded=function(status){
		this.settingClassLoaded=status;
	}
	this.isSpecimenClassLoaded=function(){
		return this.specimenClassLoaded;
	}
	this.setSpecimenClassLoaded=function(status){
		this.specimenClassLoaded=status;
	}
	this.isTestClassLoaded=function(){
		return this.testClassLoaded;
	}
	this.setTestClassLoaded=function(status){
		this.testClassLoaded=status;
	}
	this.isTestPackageClassLoaded=function(){
		return this.testPackageClassLoaded;
	}
	this.setTestPackageClassLoaded=function(status){
		this.testPackageClassLoaded=status;
	}
	this.isUserClassLoaded=function(){
		return this.userClassLoaded;
	}
	this.setUserClassLoaded=function(status){
		this.userClassLoaded=status;
	} */
}