function ConsumableListDTO() {
	this.getUid = function() {
		return this.uid;
	}
	this.setUid =function(uid) {
		this.uid=uid;
	}	
	
	this.getRate = function() {
		return this.rate;
	}
	this.setRate = function(rate) {
		this.rate = rate;
	}
	
	this.getAction= function() {
		return this.action;
	}
	this.setAction = function(action) {
		this.action = action;
	}
}