function TestUidDTO(testUid, specimenUid,itemId,actionName) {
	if(arguments.length>0){
		this.testUid = testUid;
		this.specimenUid = specimenUid;
		this.itemId = itemId;
		this.actionName = actionName;
	}	
}
TestUidDTO.prototype.getTestUid=function() {
	return this.testUid;
}
TestUidDTO.prototype.setTestUid=function(testUid) {
	this.testUid=testUid;
}
TestUidDTO.prototype.getSpecimenUid=function() {
	return this.specimenUid;
}
TestUidDTO.prototype.setSpecimenUid=function(specimenUid) {
	this.specimenUid=specimenUid;
}
TestUidDTO.prototype.getItemId=function() {
	return this.itemId;
}
TestUidDTO.prototype.setItemId=function(itemId) {
	this.itemId=itemId;
}
TestUidDTO.prototype.getActionName=function() {
	return this.actionName;
}
TestUidDTO.prototype.setActionName=function(actionName) {
	return this.actionName=actionName;
}