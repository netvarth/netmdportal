function ConsumableDTO() {

}

function ConsumableDTO(id, name, rate) {
	this.uid=id;
	this.name=name;
	this.rate=rate;
}

ConsumableDTO.prototype.setId = function(id) {
	this.uid=id;
}
ConsumableDTO.prototype.getId = function() {
	return this.uid;
}

ConsumableDTO.prototype.setName = function(name) {
	this.name=name;
}
ConsumableDTO.prototype.getName = function() {
	return this.name;
}

ConsumableDTO.prototype.setRate= function(rate) {
	this.rate=rate;
}
ConsumableDTO.prototype.getRate= function() {
	return this.rate;
}