function ReferralDTO(name,designation) {
	if(arguments.length>0){
		this.name=name;
		this.designation =designation;
	}	
}

ReferralDTO.prototype.setName= function(name) {
	this.name=name;
}
ReferralDTO.prototype.getName = function() {
	return this.name;
}
ReferralDTO.prototype.setEmail = function(email) {
	this.email=email;
}
ReferralDTO.prototype.getEmail = function() {
	return this.email;
}
ReferralDTO.prototype.setMobile= function(mobile) {
	this.mobile=mobile;
}
ReferralDTO.prototype.getMobile = function() {
	return this.mobile;
}
ReferralDTO.prototype.setPrimaryAddress = function(primaryAddress) {
	this.primaryAddress=primaryAddress;
}
ReferralDTO.prototype.getPrimaryAddress = function() {
	return this.primaryAddress;
}
ReferralDTO.prototype.setSecondaryAddress = function(secondaryAddress) {
	this.secondaryAddress=secondaryAddress;
}
ReferralDTO.prototype.getSecondaryAddress = function() {
	return this.secondaryAddress;
}
ReferralDTO.prototype.setFax = function(fax) {
	this.fax=fax;
}
ReferralDTO.prototype.getFax = function() {
	return this.fax;
}
ReferralDTO.prototype.setDescription = function(description) {
	this.description=description;
}
ReferralDTO.prototype.getDescription = function() {
	return this.description;
}
ReferralDTO.prototype.setPhone = function(phone) {
	this.phone=phone;
}
ReferralDTO.prototype.getPhone = function() {
	return this.phone;
}
ReferralDTO.prototype.setDesignation = function(designation) {
	this.designation=designation;
}
ReferralDTO.prototype.getDesignation = function() {
	return this.designation;
}
ReferralDTO.prototype.setAddress = function(address) {
	this.address=address;
}
ReferralDTO.prototype.getAddress = function() {
	return this.address;
}