function DiscountDTO(discount) {
	if(arguments.length==1) {
		this.uid=discount.discountDTO.uid;
		this.name=discount.discountDTO.name;
		this.description=discount.discountDTO.description;
		this.discType=discount.discountDTO.discType;
		this.discValue=discount.discountDTO.discValue;
		this.calculationType=discount.discountDTO.calculationType;
	}
	
}

DiscountDTO.prototype.setUid = function(uid) {
	this.uid=uid;
}
DiscountDTO.prototype.getUid = function() {
	return this.uid;
}

DiscountDTO.prototype.setName = function(name) {
	this.name=name;
}
DiscountDTO.prototype.getName = function() {
	return this.name;
}

DiscountDTO.prototype.setDescription = function(description) {
	this.description=description;
}
DiscountDTO.prototype.getDescription = function() {
	return this.description;
}

DiscountDTO.prototype.setDiscType = function(discType) {
	this.discType=discType;
}
DiscountDTO.prototype.getDiscType = function() {
	return this.discType;
}

DiscountDTO.prototype.setDiscValue = function(discValue) {
	this.discValue=discValue;
}
DiscountDTO.prototype.getDiscValue = function() {
	return this.discValue;
}

DiscountDTO.prototype.setCalculationType = function(calculationType) {
	this.calculationType=calculationType;
}
DiscountDTO.prototype.getCalculationType = function() {
	return this.calculationType;
}