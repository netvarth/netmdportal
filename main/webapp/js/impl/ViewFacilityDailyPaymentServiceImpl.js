function ViewFacilityDailyPaymentServiceImpl () {
	this.setTableValues = function(tableObj, paymentResponse) { 	
		$j(tableObj).dataTable().fnClearTable();
		if(paymentResponse.paymentList.length>0) {			
			$j(paymentResponse.paymentList).each(function(index,element) {
				var id=element.orderUid;
				var orderDate=element.orderDate;
				var name=element.patientName;
				var	collectedAt=element.collectedAt;
				var	totalAmount=element.grandTotal;
				var due=element.balanceDue;
				var paid=element.prevPaidAmount;
				var	paidDate;
				if(element.prevPaidDate==null)
					paidDate = getUserDateFromSystemDate(new Date());
				else 
					paidDate=element.prevPaidDate; 
				var rowData=$j(tableObj).dataTable().fnAddData([id,orderDate,name,collectedAt,totalAmount,due,paid,paidDate]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);
				$j(row).children("td:nth-child(7)").attr('name',paid); 					
				$j(row).children("td:nth-child(8)").attr("class","datepicker");
			});
		}	 			
	}
}

ViewFacilityDailyPaymentServiceImpl.prototype.getFacilityDailyPaymentList=function(facilityObj) {
	ajaxProcessor.setUrl(constants.GETFACILITYPAYMENTLISTURL);
	return ajaxProcessor.post(facilityObj);
}
ViewFacilityDailyPaymentServiceImpl.prototype.getFacilityDailyPaymentJSON=function() {
	ajaxProcessor.setUrl(constants.FACILITYDAILYPAYMENTLISTURL);
	return ajaxProcessor.get();
}
ViewFacilityDailyPaymentServiceImpl.prototype.getFacilityByArea=function(areaObj) {
	ajaxProcessor.setUrl(constants.GETFACILITYBYAREA+areaObj);
	return ajaxProcessor.get();
}
ViewFacilityDailyPaymentServiceImpl.prototype.updatePayments=function(paymentsList) {
	ajaxProcessor.setUrl(constants.UPDATEPAYMENTLIST);
	return ajaxProcessor.post(paymentsList);
}