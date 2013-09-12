function SpecimenCollectStatusDTO() {
		this.setOrderId = function(orderUid) {
			this.orderUid = orderUid;
		}
		
		this.setSuccess = function(success){
			this.success=success;
		}

		this.setSpecimenList = function(specimenList){
			this.specimenList = specimenList;
		}
		
		this.setError = function(error){
			this.error = error;
		}
}
