function ExpressionListDTO(expressionDTO) {
	
	this.expressionList=[];
	
	this.init = function() {
		this.expressionList=[];
	}
	
	this.add = function(expressionDTO) {
		this.expressionList.push(expressionDTO);
	}
	
	this.remove = function(expressionName) {
		
	}
	
	this.getExpressionList = function() {
		return this.expressionList;
	}
	
}

