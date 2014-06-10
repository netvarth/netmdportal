function OrderTypeDTO() {
		
}
OrderTypeDTO.prototype.setAgentOrder= function(agentorder) {
	this.agentorder=agentorder;
}
OrderTypeDTO.prototype.getAgentOrder = function() {
	return this.agentorder;
}
OrderTypeDTO.prototype.setBlanketOrder= function(blanketorder) {
	this.blanketorder=blanketorder;
}
OrderTypeDTO.prototype.getBlanketOrder = function() {
	return this.blanketorder;
}
OrderTypeDTO.prototype.setWalkinOrder= function(walkinorder) {
	this.walkinorder=walkinorder;
}
OrderTypeDTO.prototype.getWalkinOrder = function() {
	return this.walkinorder;
}