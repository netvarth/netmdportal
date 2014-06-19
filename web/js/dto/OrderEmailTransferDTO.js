function OrderEmailTransferDTO(to, subject, messagebody, result) {
	if(arguments.length>0){
		this.to = to;
		this.subject = subject;
		this.messagebody = messagebody;
		this.result = result;
	}
}
OrderEmailTransferDTO.prototype.getTo = function() {
	return this.to;
}
OrderEmailTransferDTO.prototype.getSubject = function() {
	return this.subject;
}
OrderEmailTransferDTO.prototype.getResult = function() {
	return this.result;
}
OrderEmailTransferDTO.prototype.getMessagebody = function() {
	return this.messagebody;
}
OrderEmailTransferDTO.prototype.setMessagebody = function(messagebody) {
	this.messagebody=messagebody;
}
OrderEmailTransferDTO.prototype.setTo = function(to) {
	this.to=to;
}
OrderEmailTransferDTO.prototype.setSubject = function(subject) {
	this.subject=subject;
}
OrderEmailTransferDTO.prototype.setResult = function(result) {
	this.result=result;
}
