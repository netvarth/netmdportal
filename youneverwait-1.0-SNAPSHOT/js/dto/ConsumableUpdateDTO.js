function ConsumableUpdateDTO() {
	this.getOrderUid = function() {
		return this.orderUid;
	}
	this.setOrderUid =function(orderUid) {
		this.orderUid=orderUid;
	}	
	
	this.getNote = function() {
		return this.note;
	}
	this.setNote = function(note) {
		this.note = note;
	}
	
	this.getConsumables = function() {
		return this.consumables;
	}
	this.setConsumables = function(consumables) {
		this.consumables = consumables;
	}	
}