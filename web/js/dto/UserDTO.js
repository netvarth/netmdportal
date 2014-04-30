function UserDTO() {
	this.getId = function() {
		return this.uid;
	}
	this.setId =function(id) {
		this.uid=id;
	}	
	this.getName = function() {
		return this.name;
	}
	this.setName = function(name) {
		this.name = name;
	}
	this.getAddress = function() {
		return this.address;
	}
	this.setAddress = function(address) {
		this.address = address;
	}
	this.getPhone = function() {
		return this.phone;
	}
	this.setPhone = function(phone) {
		this.phone = phone;
	}
	this.getEmail = function() {
		return this.email;
	}
	this.setEmail = function(email) {
		this.email = email;
	}
	this.getRoleId = function() {
		return this.roleUid;
	}
	this.setRoleId = function(roleUid) {
		this.roleUid = roleUid;
	}
	this.getLoginId = function() {
		return this.loginId;
	}
	this.setLoginId = function(loginId) {
		this.loginId = loginId;
	}	
	this.getPassword = function() {
		return this.password;
	}
	this.setPassword = function(password) {
		this.password = password;
	}
	this.getActive= function() {
		return this.active;
	}
	this.setActive = function(active) {
		this.active = active;
	}
	this.getDesignation = function() {
		return this.designation;
	}
	this.setDesignation = function(designation) {
		this.designation = designation;
	}
}