function TestPackageDTO(uid,name,description,category,active,testUids,testPkgUids,stdRate) {
	if(arguments.length>0){
		this.uid=uid;
		this.name = name;
		this.description = description;
		this.category = category;
		this.active = active;
		this.testUids=testUids;
		this.testPkgUids=testPkgUids;
		this.stdRate=stdRate;
	}	
}
TestPackageDTO.prototype.setUid = function(uid) {
	this.uid=uid;
}
TestPackageDTO.prototype.getUid=function() {
	return this.uid;
}
TestPackageDTO.prototype.setName = function(name) {
	this.name=name;
}
TestPackageDTO.prototype.getName=function() {
	return this.name;
}
TestPackageDTO.prototype.setDescription= function(description) {
	this.description = description;
}
TestPackageDTO.prototype.getDescription=function(){
	return this.description;
}
TestPackageDTO.prototype.setCategory=function(category){
	this.category=category;
}
TestPackageDTO.prototype.getCategory=function(){
	return this.category;
}
TestPackageDTO.prototype.setActive = function(active) {
	this.active=active;
}
TestPackageDTO.prototype.getActive=function() {
	return this.active;
}

TestPackageDTO.prototype.setTestUids= function(testUids) {
	this.testUids= testUids;
	
}
TestPackageDTO.prototype.getTestUids=function() {
	return this.testUids;
}
TestPackageDTO.prototype.setTestPkgUids = function(testPkgUids) {
	this.testPkgUids= testPkgUids;
	
}
TestPackageDTO.prototype.getTestPkgUids=function() {
	return this.testPkgUids;
}

TestPackageDTO.prototype.setStdRate = function(stdRate) {
	this.stdRate= stdRate;
	
}
TestPackageDTO.prototype.getStdRate=function() {
	return this.stdRate;
}
