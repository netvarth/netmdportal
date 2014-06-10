function ViewTestResultResponseDTO() {
	this.setResult = function(result) {
		this.result = result;
	}
	this.getResult = function() {
		return this.result;
	}	
	this.setRemarks = function(remarks){
		this.remarks=remarks;
	}	
	this.getRemarks = function(){
		return this.remarks;
	}
	this.setUserUid = function(userId){
		this.userId = userId;
	}
	this.getUserUid = function(){
		return this.userId;
	}
	this.isCompleted = function(){
		return this.completed;
	}
	this.setCompleted = function(completed){
		this.completed=completed;
	}
	this.isVerified = function(){
		return this.verified;
	}
	this.setVerified = function(verified){
		this.verified=verified;
	}	
	this.setTestSpecimenId = function(testSpecimenId){
		this.testSpecimenId = testSpecimenId;
	}
	this.getTestSpecimenId = function(){
		return this.testSpecimenId;
	}
	this.setTestUid = function(testId){
		this.testId = testId;
	}
	this.getTestUid = function(){
		return this.testId;
	}
	this.setSpecimenUid = function(specimenId){
		this.specimenId = specimenId;
	}
	this.getSpecimenUid = function(){
		return this.specimenId;
	}
}

