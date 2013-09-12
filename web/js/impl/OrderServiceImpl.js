function OrderServiceImpl () {
	
	this.setTableValues = function(tableObj, orderResult) {
		$j(tableObj).dataTable().fnClearTable();
		if(orderResult.order) {
			if(orderResult.order.length>0) {			
				$j(orderResult.order).each(function(index, order) {
					var ordid=order.uid;
					var id=ordid;
					var orderId=id;
					var status;
					if(order.status==constants.NEW)
						status='<img width="18" height="18" style="float:left;" src="/weblims/images/pr1.png">';
					else if(order.status==constants.READY)
						status='<img width="18" height="18" style="float:left;" src="/weblims/images/pr4.png">';
					else if(order.status==constants.HOLD)
						status='<img width="18" height="18" style="float:left;" src="/weblims/images/pr3.png">';
					else if(order.status==constants.DELIVERED)
						status='<img width="18" height="18" style="float:left;" src="/weblims/images/pr5.png">';
					else if(order.status==constants.INPROGRESS)
						status='<img width="18" height="18" style="float:left;" src="/weblims/images/pr6.png">';
					else if(order.status==constants.PARTIALRESULTS)
						status='<img width="18" height="18" style="float:left;" src="/weblims/images/pr7.png">';
					else
						status='<img width="18" height="18" style="float:left;" src="/weblims/images/pr2.png">';
					status += order.status;
					if(order.patient.honorifics)
						var patient=order.patient.honorifics +" "+order.patient.name;
					else
						var patient=order.patient.name;
					var createdOn=order.createdOn;
					var origin=order.origin;
					var total=order.total.toFixed(2);
					var paystatus=order.paymentStatus;
					var testDiscount=null;
					$j(order.test).each(function (index, rowTest) {
						testDiscount=rowTest.discount;
					});
					if(paystatus==constants.FULLYPAID)
						total+='<img width="12" height="12" style="float:left;" src="/weblims/images/complete-icon.png">';
					else if(paystatus==constants.PARTIALLYPAID)	
						total+='<img width="12" height="12" style="float:left;" src="/weblims/images/in-complete.png">';
					else
						total+='<img width="12" height="12" style="float:left;" src="/weblims/images/emergency.png">';
					if(testDiscount!=null) {
						total+='<img width="12" height="12" style="float:left;" src="/weblims/images/dis-icon.png">';
					}
					var ref;
					if(ordid.substring(0,2)==constants.JV) {
						ref='<img width="18" height="18" alt=" " src="/weblims/images/doctor.png">';
					} else if(ordid.substring(0,2)==constants.AG) {
						ref='<img width="18" height="18" alt=" " src="/weblims/images/agent.png">';
					} else { 
						ref='<img width="18" height="18" alt=" " src="/weblims/images/border.png">';
					}	
					var reference=ref;
					if(order.referralName)
						reference+= order.referralName;
					var specimenName="";
					$j(order.specimen).each(function (index, rowSpecimen) {
						specimenStatus=rowSpecimen.status;
						if(specimenName=="")
							specimenName = rowSpecimen.name;
						else{
							specimenName +=  rowSpecimen.name;
						}
						if(specimenStatus=='false')	{ 
							specimenName+='<img width="12" height="12" style="float:right;" src="/weblims/images/icon/no-icon.png">'+'<br/>';
						} else {
							specimenName+='<img width="12" height="12" style="float:right;" src="/weblims/images/icon/right-icon.png">'+'<br/>';
						}
					});			
					var testName="";
					var specialTestPkgId="";
					$j(order.test).each(function (index, rowTest) {
						if(rowTest.specialTestPkgUid!=null){
							if(specialTestPkgId==""){
								specialTestPkgId=rowTest.specialTestPkgUid;
								testid=specialTestPkgId;
							}
							else
								return;
						} else   					
							testid=rowTest.uid;
						var specId = "";
						if(rowTest.specimenUid!=0 || rowTest.specimenUid!="")
							specId = " (" + getSpecimenName(rowTest.specimenUid) + ")";
						if(testName=="")
							testName = getTestName(testid) + specId;
						else
							testName += '<br/>'+getTestName(testid) + specId;
					});				
					var rowData=$j(tableObj).dataTable().fnAddData([orderId, status, patient, createdOn, origin, reference, testName, specimenName, total]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).children("td:nth-child(9)").attr("class","column-2");
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","orderIdCol Ustyle");
				});
			}		
		}
	}
	
}

OrderServiceImpl.prototype.createOrder=function (orderObj) {
	ajaxProcessor.setUrl(constants.CREATEORDERURL);
	return ajaxProcessor.post(orderObj);
}
OrderServiceImpl.prototype.updateOrder=function(orderObj) {
	ajaxProcessor.setUrl(constants.UPDATEORDERURL);
	return ajaxProcessor.post(orderObj);
}
OrderServiceImpl.prototype.deleteOrder=function(orderId) {
	ajaxProcessor.setUrl(constants.DELETEORDERURL + orderId);
	return ajaxProcessor.get();
}
OrderServiceImpl.prototype.viewOrder=function(orderId) {
	ajaxProcessor.setUrl(constants.VIEWORDERURL + orderId);
	return ajaxProcessor.get();
}
OrderServiceImpl.prototype.getTestResultStatus = function(orderId) {
	ajaxProcessor.setUrl(constants.GETTESTRESULTSTATUS + orderId);
	return ajaxProcessor.post();
}
OrderServiceImpl.prototype.updatePayment = function(paymentRequest) {
	ajaxProcessor.setUrl(constants.UPDATEPAYMENTURL);
	return ajaxProcessor.post(paymentRequest);
}
OrderServiceImpl.prototype.updatePatient = function(patientRequest) {
	ajaxProcessor.setUrl(constants.UPDATEPATIENTURL);
	return ajaxProcessor.post(patientRequest);
}
OrderServiceImpl.prototype.updateOrderReferral = function(referralRequest) {
	ajaxProcessor.setUrl(constants.UPDATEREFERRALURL);
	return ajaxProcessor.post(referralRequest);
}
OrderServiceImpl.prototype.updateOrderDiscount = function(discountRequest) {
	ajaxProcessor.setUrl(constants.UPDATEORDERDISCOUNT);
	return ajaxProcessor.post(discountRequest);
}
OrderServiceImpl.prototype.updateOrderSpecimen = function(specimenRequest) {
	ajaxProcessor.setUrl(constants.UPDATESPECIMENSTATUS);
	return ajaxProcessor.post(specimenRequest);
}
OrderServiceImpl.prototype.updateOrderTest = function(testRequest) {
	ajaxProcessor.setUrl(constants.UPDATEORDERTEST);
	return ajaxProcessor.post(testRequest);
}
OrderServiceImpl.prototype.updateOrderStatus = function(statusRequest) {
	ajaxProcessor.setUrl(constants.UPDATEORDERSTATUS);
	return ajaxProcessor.post(statusRequest);
}
OrderServiceImpl.prototype.getOrderHistory = function(orderId) {
	ajaxProcessor.setUrl(constants.GETORDERHISTORY + orderId);
	return ajaxProcessor.get();
}
OrderServiceImpl.prototype.updateCollectionCharge = function(collectionRequest) {
	ajaxProcessor.setUrl(constants.UPDATECOLLECTIONCHARGE);
	return ajaxProcessor.post(collectionRequest);
}
OrderServiceImpl.prototype.isSpecimenCollected = function(specimenRequest) {
	ajaxProcessor.setUrl(constants.ISSPECIMENCOLLECTED);
	return ajaxProcessor.post(specimenRequest);
}
OrderServiceImpl.prototype.viewTestResult = function(specimenRequest) {
	ajaxProcessor.setUrl(constants.VIEWTESTRESULT);
	return ajaxProcessor.post(specimenRequest);
}
OrderServiceImpl.prototype.updateTestResult = function(resultRequest) {
	ajaxProcessor.setUrl(constants.UPDATETESTRESULT);
	return ajaxProcessor.post(resultRequest);
}
OrderServiceImpl.prototype.saveOrderResult = function(resultRequest) {
	ajaxProcessor.setUrl(constants.SAVEORDERRESULT);
	return ajaxProcessor.post(resultRequest);
}
OrderServiceImpl.prototype.getOrdeIdFromBarcodeNo=function(barcodeNo) {
	ajaxProcessor.setUrl(constants.GETORDERIDFROMBARCODE + barcodeNo);
	return ajaxProcessor.get();
}
OrderServiceImpl.prototype.updateTestSequence=function(testIds) {
	ajaxProcessor.setUrl(constants.UPDATESEQUENCEURL);
	return ajaxProcessor.post(testIds);
}
OrderServiceImpl.prototype.getOrderResult=function(orderId) {
	ajaxProcessor.setUrl(constants.GETORDERRESULTURL + orderId);
	return ajaxProcessor.get();
}
OrderServiceImpl.prototype.emailOrderResult=function(mailRequest) {
	ajaxProcessor.setUrl(constants.GETORDERRESULTMAILURL);
	return ajaxProcessor.post(mailRequest);
}
OrderServiceImpl.prototype.getBillValue=function(value) {
	var responseValue="";
	ajaxProcessor.setUrl(constants.VIEWSETTINGBYNAMEURL + value);
	var response = ajaxProcessor.get();
	if(response.success==true) {
		if(response.setting.settingList){
			$j(response.setting.settingList).each(function(index,settingDetail){
				responseValue = settingDetail.value;
				return false;
			});
		}	
	} 
	return responseValue;
}
