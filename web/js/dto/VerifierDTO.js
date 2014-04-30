function VerifierDTO() {
	this.setUserId =function(userId) {
		this.userId=userId;
	}	

	this.setName = function(name) {
		this.name = name;
	}
	
	this.setDesignation = function(designation) {
		this.designation = designation;
	}
	
	this.getUserId = function() {
		return this.userId;
	}
	
	this.getName = function() {
		return this.name;
	}
	
	this.getDesignation = function() {
		return this.designation;
	}
	
}