function FilterDTO(exp, from, count, asc) {
	this.from = from;
	this.count= count;
	this.asc=asc;
	this.exp= exp;
}
	
FilterDTO.setFrom = function(from) {
	this.from=from;
}

FilterDTO.setCount = function(count) {
	this.count=count;
}

FilterDTO.setAsc= function(asc) {
	this.asc=asc;
}

FilterDTO.setExp= function(exp) {
	this.exp=exp;
}