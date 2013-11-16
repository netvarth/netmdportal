function BranchNetlimsDTO(name) {
	if(arguments.length>0){
		this.name=name;
	}	
}
BranchNetlimsDTO.prototype.setglobalId= function(globalId) {
	this.globalId=globalId;
}
BranchNetlimsDTO.prototype.getglobalId = function() {
	return this.globalId;
}
BranchNetlimsDTO.prototype.setlabId= function(labId) {
	this.labId=labId;
}
BranchNetlimsDTO.prototype.getlabId = function() {
	return this.labId;
}
BranchNetlimsDTO.prototype.setName= function(name) {
	this.name=name;
}
BranchNetlimsDTO.prototype.getName = function() {
	return this.name;
}
BranchNetlimsDTO.prototype.setEmail = function(email) {
	this.email=email;
}
BranchNetlimsDTO.prototype.getEmail = function() {
	return this.email;
}
BranchNetlimsDTO.prototype.setMobile= function(mobile) {
	this.mobile=mobile;
}
BranchNetlimsDTO.prototype.getMobile = function() {
	return this.mobile;
}
BranchNetlimsDTO.prototype.setPhone = function(phone) {
	this.phone=phone;
}
BranchNetlimsDTO.prototype.getPhone = function() {
	return this.phone;
}
BranchNetlimsDTO.prototype.setAddress = function(address) {
	this.address=address;
}
BranchNetlimsDTO.prototype.getAddress = function() {
	return this.address;
}