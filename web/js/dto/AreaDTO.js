function AreaDTO() {

}

function AreaDTO(id, name) {
	this.uid=id;
	this.name=name;
}

AreaDTO.prototype.setId = function(id) {
	this.uid=id;
}

AreaDTO.prototype.setName = function(name) {
	this.name=name;
}

AreaDTO.prototype.getId = function() {
	return this.uid;
}

AreaDTO.prototype.getName = function() {
	return this.name;
}