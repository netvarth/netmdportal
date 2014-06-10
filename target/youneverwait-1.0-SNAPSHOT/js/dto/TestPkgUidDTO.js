function TestPkgUidDTO(testPkgUid,actionName,itemId) {
	if(arguments.length>0){
		this.testPkgUid = testPkgUid;
		this.actionName = actionName;
		this.itemId = itemId;
	}	
}
TestPkgUidDTO.prototype.getTestPkgUid = function() {
	return this.testPkgUid ;
}
TestPkgUidDTO.prototype.setTestPkgUid=function(testPkgUid) {
	this.testPkgUid = testPkgUid;
}
TestPkgUidDTO.prototype.getActionName=function() {
	return this.actionName;
}
TestPkgUidDTO.prototype.setActionName=function(actionName) {
	this.actionName = actionName;
}
TestPkgUidDTO.prototype.getItemId=function() {
	return this.itemId;
}
TestPkgUidDTO.prototype.setItemId=function(itemId) {
	this.itemId = itemId;
}
	
	

