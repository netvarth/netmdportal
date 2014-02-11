function BranchNetmdDTO(name) {
	if(arguments.length>0){
		this.name=name;
	}	
}
BranchNetmdDTO.prototype.setglobalId= function(globalId) {
	this.globalId=globalId;
}
BranchNetmdDTO.prototype.getglobalId = function() {
	return this.globalId;
}
BranchNetmdDTO.prototype.setnetMdId= function(netMdId) {
	this.netMdId=netMdId;
}
BranchNetmdDTO.prototype.getnetMdId = function() {
	return this.netMdId;
}
BranchNetmdDTO.prototype.setName= function(name) {
	this.name=name;
}
BranchNetmdDTO.prototype.getName = function() {
	return this.name;
}
BranchNetmdDTO.prototype.setEmail = function(email) {
	this.email=email;
}
BranchNetmdDTO.prototype.getEmail = function() {
	return this.email;
}
BranchNetmdDTO.prototype.setMobile= function(mobile) {
	this.mobile=mobile;
}
BranchNetmdDTO.prototype.getMobile = function() {
	return this.mobile;
}
BranchNetmdDTO.prototype.setPhone = function(phone) {
	this.phone=phone;
}
BranchNetmdDTO.prototype.getPhone = function() {
	return this.phone;
}
BranchNetmdDTO.prototype.setAddress = function(address) {
	this.address=address;
}
BranchNetmdDTO.prototype.getAddress = function() {
	return this.address;
}
BranchNetmdDTO.prototype.setOrganisationName = function(organisationName) {
	this.organisationName=organisationName;
}
BranchNetmdDTO.prototype.getOrganisationName = function() {
	return this.organisationName;
}
BranchNetmdDTO.prototype.setnumberOfDevices = function(numberOfDevices) {
	this.numberOfDevices=numberOfDevices;
}
BranchNetmdDTO.prototype.getnumberOfDevices = function() {
	return this.numberOfDevices;
}