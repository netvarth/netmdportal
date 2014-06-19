function ChangePasswrdDTO() {
		
}
ChangePasswrdDTO.prototype.setOldPassword= function(oldPassword) {
	this.oldPassword=oldPassword;
}
ChangePasswrdDTO.prototype.getOldPassword = function() {
	return this.oldPassword;
}
ChangePasswrdDTO.prototype.setNewPassword= function(newPassword) {
	this.newPassword=newPassword;
}
ChangePasswrdDTO.prototype.getNewPassword = function() {
	return this.newPassword;
}
ChangePasswrdDTO.prototype.setUsername= function(username) {
	this.username=username;
}
ChangePasswrdDTO.prototype.getUsername = function() {
	return this.username;
}