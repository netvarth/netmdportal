function SettingDTO(name,id,settingList) {
	if(arguments.length>0){
		this.name = name;
		this.id=id;
		this.settingList=settingList;
	}		
}
SettingDTO.prototype.setName = function(name) {
	this.name=name;
}
SettingDTO.prototype.getName=function() {
	return this.name;
}
SettingDTO.prototype.setId = function(id) {
	this.id=id;
}
SettingDTO.prototype.getId=function() {
	return this.id;
}
SettingDTO.prototype.setSettingList= function(settingList) {
	this.settingList= settingList;
}
SettingDTO.prototype.getSettingList=function() {
	return this.settingList;
}
