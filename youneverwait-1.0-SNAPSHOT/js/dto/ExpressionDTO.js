function ExpressionDTO(name,value,operator) {
	if(arguments.length>0) {
		this.name = name;
		this.value= value;
		this.operator=operator;
	}
}

ExpressionDTO.setValue = function(value) {
	this.id=id;
}

ExpressionDTO.setName = function(name) {
	this.name=name;
}

ExpressionDTO.setOperator= function(operator) {
	this.operator=operator;
}