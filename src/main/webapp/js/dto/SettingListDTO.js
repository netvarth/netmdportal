function SettingListDTO(value,actionName,itemId) {
	if(arguments.length>0){
		this.value = value;
		this.actionName = actionName;
		this.itemId = itemId;
	}	
}
SettingListDTO.prototype.getValue = function() {
	return this.value ;
}
SettingListDTO.prototype.setValue=function(value) {
	this.value = value;
}
SettingListDTO.prototype.getActionName=function() {
	return this.actionName;
}
SettingListDTO.prototype.setActionName=function(actionName) {
	this.actionName = actionName;
}
SettingListDTO.prototype.getItemId=function() {
	return this.itemId;
}
SettingListDTO.prototype.setItemId=function(itemId) {
	this.itemId = itemId;
}
	
	

