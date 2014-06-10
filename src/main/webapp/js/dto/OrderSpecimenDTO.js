function OrderSpecimenDTO(uid,name,quantity,status,collectedAt,actionName) {
	if(arguments.length>0){
		this.uid=uid;
		this.name=name;
		this.quantity=quantity;
		this.status=status;
		this.collectedAt=collectedAt;
		this.actionName=actionName;
	}	
}

OrderSpecimenDTO.prototype.setUid = function(uid) {
	this.uid=uid;
}
OrderSpecimenDTO.prototype.getUid = function() {
	return this.uid;
}

OrderSpecimenDTO.prototype.setName = function(name) {
	this.name=name;
}
OrderSpecimenDTO.prototype.getName = function() {
	return this.name;
}

OrderSpecimenDTO.prototype.setQuantity = function(quantity) {
	this.quantity=quantity;
}
OrderSpecimenDTO.prototype.getQuantity = function() {
	return this.quantity;
}

OrderSpecimenDTO.prototype.setStatus = function(status) {
	this.status=status;
}
OrderSpecimenDTO.prototype.getStatus = function() {
	return this.status;
}

OrderSpecimenDTO.prototype.setCollectedAt = function(collectedAt) {
	this.collectedAt=collectedAt;
}
OrderSpecimenDTO.prototype.getCollectedAt = function() {
	return this.collectedAt;
}

OrderSpecimenDTO.prototype.setActionName = function(actionName) {
	this.actionName=actionName;
}
OrderSpecimenDTO.prototype.getActionName = function() {
	return this.actionName;
}