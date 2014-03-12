function BranchNetrxDTO(name) {
	if(arguments.length>0){
		this.name=name;
	}	
}
BranchNetrxDTO.prototype.setglobalId= function(globalId) {
	this.globalId=globalId;
}
BranchNetrxDTO.prototype.getglobalId = function() {
	return this.globalId;
}
BranchNetrxDTO.prototype.setnetRxId= function(netRxId) {
	this.netRxId=netRxId;
}
BranchNetrxDTO.prototype.getnetRxId = function() {
	return this.netRxId;
}
BranchNetrxDTO.prototype.setName= function(name) {
	this.name=name;
}
BranchNetrxDTO.prototype.getName = function() {
	return this.name;
}
BranchNetrxDTO.prototype.setEmail = function(email) {
	this.email=email;
}
BranchNetrxDTO.prototype.getEmail = function() {
	return this.email;
}
BranchNetrxDTO.prototype.setMobile= function(mobile) {
	this.mobile=mobile;
}
BranchNetrxDTO.prototype.getMobile = function() {
	return this.mobile;
}
BranchNetrxDTO.prototype.setPhone = function(phone) {
	this.phone=phone;
}
BranchNetrxDTO.prototype.getPhone = function() {
	return this.phone;
}
BranchNetrxDTO.prototype.setAddress = function(address) {
	this.address=address;
}
BranchNetrxDTO.prototype.getAddress = function() {
	return this.address;
}
BranchNetrxDTO.prototype.setnumberOfDevices = function(numberOfDevices) {
	this.numberOfDevices=numberOfDevices;
}
BranchNetrxDTO.prototype.getnumberOfDevices = function() {
	return this.numberOfDevices;
}