function BillPrintProcessor(source) {
	this.source = source;
	this.getSource = function(){
		return this.source;
	}
	this.getOrderService = function() {
		var parent = this.getSource();
		return parent.getOrderService();
	}
	this.generateHtml = function(documentContainer) {
		var strHtml = '<html><head>';
		strHtml += "<style>";
		strHtml += "<!--";
		strHtml += "#printDiv {page-break-inside:avoid;page-break-after:avoid;page-break-before:avoid;}";
		strHtml += "#printDiv {width:100%;font-size:80%;font-family: arial;height:100%}";
		strHtml += "#printDiv .header{margin-bottom:10px;}";
		strHtml += "#printDiv .firstSection{float:left;margin-left:1%; margin-right:4%; width: 47%}";
		strHtml += "#printDiv .secondSection{float:right; width: 47%}";
		strHtml += "#printDiv .title{font-weight:normal;font-size:25px;}";
		strHtml += "#printDiv .subtitle{font-weight:normal;font-size:16px;}";
		strHtml += "#printDiv .subParentContents{margin-top:0;margin-bottom:0;}";
		strHtml += "#printDiv .middle{text-align:center;}";
		strHtml += "#printDiv .left{float:left;}";
		strHtml += "#printDiv .right{float:right;}";
		strHtml += "#printDiv .half{width:50%}";
		strHtml += "#printDiv .value{margin-left:1%}";
		strHtml += "#printDiv .full{width:100%}";
		strHtml += "#printDiv .bottomMargin{margin-bottom:5px;}";
		strHtml += "#printDiv .underline{text-decoration:underline;}";
		strHtml += "#printDiv .rowclass{width:100%;float:left;margin-bottom:3px;}";
		strHtml += "#printDiv .dataspan{margin-left:1%;}";
		strHtml += "#printDiv .dataleft{margin-left:10%; width:55%;float:left;}";
		strHtml += "#printDiv .dataleft1{margin-right:10%;}";
		strHtml += "#printDiv .dataright{margin-right:15%; float:right;text-align:right;}";
		strHtml += ".makeBold {font-weight:bold;}";
		strHtml += ".leftAlign {text-align:left}";
		strHtml += ".rightAlign {text-align:right}";
		strHtml += ".column-2 {text-align:right}";
		strHtml += ".rowDiv {width:100%}";
		strHtml += "--></style>";
		strHtml+='</head><body><div id="printDiv">';
		strHtml+= documentContainer.html() + '</div></body></html>';
		return strHtml;
	}
	
	this.print = function(htmlString) {
		var WindowObject = window.open('', 'PrintWindow', 'width=624,top=50,left=50,toolbars=yes,scrollbars=yes,status=no,resizable=yes,target=_new');
		var writestat = WindowObject.document.writeln(htmlString);
		WindowObject.focus();
		WindowObject.print();
		WindowObject.document.close();
		self = this.getSource();
	}

	this.generatePrintableSection = function(data) {
		self=this;
		var data1=data;
		var parent = $j('<div/>');
		var firstDiv = $j('<div class="firstSection"/>');
		firstDiv.html(data.html());
		parent.append(firstDiv);
		var secondDiv = $j('<div class="secondSection"/>');
		secondDiv.html(data1.html());
		parent.append(secondDiv);
		return parent;
	}
	
	this.createPrintArea = function(orderInfo) {
		self=this;
		var orderService = self.getOrderService();
		var otherCharges=orderInfo.otherCharges;
		var total=0;
		var printForm = $j('<div id="printForm" />');
		/*Header Section Starts Here*/
		var headerDiv=$j('<div class="header rowclass"/>');
		var headerSecDiv = $j('<div class="leftalign left" style="width:70%;float:left;"/>');
		var billTopHeader = $j('<div class="title rowclass"/>');
		var mainHeader = orderService.getBillValue(constants.BILLHEADER1);
		billTopHeader.html(mainHeader); 
		headerSecDiv.append(billTopHeader);
		billTopHeader = $j('<div class="subtitle rowclass"/>');
		var mainsubHeader = orderService.getBillValue(constants.BILLHEADER2);
		billTopHeader.html(mainsubHeader);
		headerSecDiv.append(billTopHeader);
		headerDiv.append(headerSecDiv);
		ajaxProcessor.setUrl(constants.GETBARCODE + orderInfo.uid + "/0");
		var barcode = ajaxProcessor.get();
		var barcodeSec = $j('<div id="barcodeContainer" class="leftalign right" style="width:30%"/>');
		barcodeSec.html(code128(barcode.object, 'C',orderInfo.uid));
		headerDiv.append(barcodeSec);
		printForm.append(headerDiv);
		/*Header Section Ends Here*/
	
		/*Name & Ref No*/
		headerDiv = $j('<div class="rowclass makeBold"/>');
		var nameDiv = $j('<div class="left half "/>');
		var nameKeyDiv = $j('<div class="key left" />');
		nameKeyDiv.append('Name :');
		nameDiv.append(nameKeyDiv);
		var nameValueDiv = $j('<div class="value" id="printPatientName"/>');
		var honorific=orderInfo.patient.honorifics;
		if(honorific=="Animal")
			nameValueDiv.append(orderInfo.patient.name);
		else
			nameValueDiv.append(orderInfo.patient.honorifics+" "+orderInfo.patient.name);
		nameDiv.append(nameValueDiv);
		headerDiv.append(nameDiv);
		
		var refSpaceDiv = $j('<div class="full"/>');
		var refDiv = $j('<div class="right half"/>');
		var refValueDiv = $j('<div class="value right" id="printrefNo"/>');
		refValueDiv.append(orderInfo.uid);
		
		refDiv.append(refValueDiv);
		var refKeyDiv = $j('<div class="key right" />');
		refKeyDiv.append('Ref. No :');
		refDiv.append(refKeyDiv);
		refSpaceDiv.append(refDiv);
		headerDiv.append(refSpaceDiv);
		printForm.append(headerDiv);
		/*Name & Ref No Ends*/
	
		/*Age, Sex, Date & Time */
		headerDiv = $j('<div class="rowclass"/>');
		var agesexDiv = $j('<div class="left half"/>');
		var ageDiv = $j('<div class="left half"/>');
		var ageKeyDiv = $j('<div class="key left makeBold" />');
		ageKeyDiv.append('Age :');
		ageDiv.append(ageKeyDiv);
		var ageValueDiv = $j('<div class="value" id="printPatientAge"/>');
		if(orderInfo.patient.age!=0)
			ageValueDiv.append(orderInfo.patient.age);
		else
			ageValueDiv.append(" ");
		ageDiv.append(ageValueDiv);
		agesexDiv.append(ageDiv);
		if(honorific!="Animal"){
			var sexDiv = $j('<div class="left half"/>');
			var sexKeyDiv = $j('<div class="key left makeBold" />');
			sexKeyDiv.append('Sex :');
			sexDiv.append(sexKeyDiv);
			var sexValueDiv = $j('<div class="value left" id="printPatientSex"/>');
			sexValueDiv.append(orderInfo.patient.gender);
			sexDiv.append(sexValueDiv);
			agesexDiv.append(sexDiv);
		}
		headerDiv.append(agesexDiv);	
		var datetimeDiv = $j('<div class="right half"/>');
		var dateDiv = $j('<div class="left half"/>');
		var dateKeyDiv = $j('<div class="key left makeBold" />');
		dateKeyDiv.append('Date :');
		dateDiv.append(dateKeyDiv);
		var dateValueDiv = $j('<div class="value" id="printorderDate"/>');
		if(orderInfo.date){
			var orderDate= setDateFormat(orderInfo.date);
			dateValueDiv.append(orderDate);
		}
		dateDiv.append(dateValueDiv);
		datetimeDiv.append(dateDiv);	
		var timeDiv = $j('<div class="left half"/>');
		var timeValueDiv = $j('<div class="value right" id="printorderTime"/>');
		timeValueDiv.append(orderInfo.time);
		timeDiv.append(timeValueDiv);
		var timeKeyDiv = $j('<div class="key right makeBold" />');
		timeKeyDiv.append('Time :');
		timeDiv.append(timeKeyDiv);
		datetimeDiv.append(timeDiv);
		headerDiv.append(datetimeDiv);	
		printForm.append(headerDiv);
		/*Age, Sex, Date & Time Ends*/
		/*Specimen & Collected At*/
		headerDiv = $j('<div class="rowclass"/>');
		var specimenDiv = $j('<div class="left half"/>');
		var specimenKeyDiv = $j('<div class="key left makeBold" />');
		specimenKeyDiv.append('Specimen :');
		specimenDiv.append(specimenKeyDiv);
		var specimenValueDiv = $j('<div class="value" id="printSpecimen"/>');
		//filling specimen field
		var specimenName="";
		$j(orderInfo.specimen).each(function (index,specimen) {
			if(specimenName=="")		
				specimenName = specimen.name;
			else
				specimenName = specimenName + ',' + specimen.name;	
		});
		specimenValueDiv.append(specimenName);
		specimenDiv.append(specimenValueDiv);
		headerDiv.append(specimenDiv);	
		var collectDiv = $j('<div class="right half"/>');		
		var collectKeyDiv = $j('<div class="key left makeBold" />');
		collectKeyDiv.append('Collected At :');
		collectDiv.append(collectKeyDiv);
		var collectValueDiv = $j('<div class="value " id="printCollectedAt"/>');
		collectValueDiv.append(orderInfo.collectedAt);
		collectDiv.append(collectValueDiv);
		headerDiv.append(collectDiv);
		printForm.append(headerDiv);
		/*Specimen & Collected At*/
		/*Ref By*/
		headerDiv = $j('<div class="rowclass"/>');
		refDiv = $j('<div class="full makeBold bottomMargin"/>');
		var refKeyDiv = $j('<div class="key left" />');
		refKeyDiv.append('Ref. By :');
		refDiv.append(refKeyDiv);
		var refValueDiv = $j('<div class="value" id="printrefBy"/>');
		if(orderInfo.referralUid) {
			var response=getDoctorData(orderInfo.referralUid);
			var ref="";
			if(response.referral.designation=="Nurse")
				ref="Nurse ";	
			else	
				ref="Dr. ";	
			ref+=response.referral.name;
			refValueDiv.append(ref);
		}
		refDiv.append(refValueDiv);
		headerDiv.append(refDiv);
		printForm.append(headerDiv);
		/*Ref By*/	
		/*Investigation & Rate Title*/
		headerDiv = $j('<div class="rowclass"/>');
		var investDiv = $j('<div class="dataleft underline"/>');
		investDiv.append('Investigation');
		headerDiv.append(investDiv);
		var rateDiv = $j('<div class="dataright underline rightAlign"/>');
		rateDiv.append('Rate ( ' + '<img src="/weblims/images/rupee-symbel.png"/> )');
		headerDiv.append(rateDiv);
		printForm.append(headerDiv);
		/*Investigation & Rate Ends*/
		/*Investigation & Rate Values*/
		var specialPkgTest="";
		$j(orderInfo.orderTest).each(function (index,test) {
			if(test.specialTestPkgUid!=null){	
				if (specialPkgTest==""){
					specialPkgTest=getTestName(test.specialTestPkgUid);
					var specialPkgRate=orderInfo.specialPkgRate.toFixed(2);
					var itemValues = self.fillFields(specialPkgTest, specialPkgRate);
					printForm.append(itemValues);
				}			 
			} else {
				var name=getTestName(test.uid);
				if(test.specimenUid!=0 || test.specimenUid!="")
					name+= " (" + getSpecimenName(test.specimenUid)+ ")";
				var netRate=test.netRate.toFixed(2);
				var itemValues = self.fillFields(name, netRate);
				printForm.append(itemValues);
			}
		});
		/*Investigation & Rate Values Ends*/
		var netTotalAmount = orderInfo.grandTotal;
		var totalDiscount = orderInfo.orderDiscountValue;
		var otherChargeTotal = orderInfo.otherCharges;
		var amountPaid = orderInfo.paidAmount;
		var grandTotal = netTotalAmount+totalDiscount-otherChargeTotal;
		var balanceAmt = netTotalAmount - amountPaid;
		//GrandTotal Calculation	
		itemValues=self.fillFields("&nbsp;", "&nbsp;");
		printForm.append(itemValues);
		itemValues=self.fillFields("&nbsp;", "&nbsp;");
		printForm.append(itemValues);	
		if(orderInfo.currency) {
			var res = self.fillOtherChargeFields(orderInfo.currency," ");
			printForm.append(res);
		}	
		//GrandTotal Display
		if(totalDiscount!=0 || otherChargeTotal!=0) {
			var res = self.fillTotalFields("Grand Total", grandTotal.toFixed(2));
			printForm.append(res);
		}
		//Discount Total Display
		if(totalDiscount!=0){
			var res = self.fillTotalFields("Discount Amount", totalDiscount.toFixed(2));
			printForm.append(res);
		}
		//Consumables......
		if(orderInfo.consumables.length>0){
			$j(orderInfo.consumables).each(function (index, consume) {
				if(consume.name=='Collection Charge'){
					var res=self.fillTotalFields("Collection Charge", consume.rate.toFixed(2)) ;
					printForm.append(res);
				} else {
					var res=self.fillTotalFields("Doctor Fee", consume.rate.toFixed(2)) ;
					printForm.append(res);
				}
			});
		}
		//GrandTotal Display
		var res = self.fillTotalFields("Net Total", netTotalAmount.toFixed(2),"makeBold");
		printForm.append(res);
		//Showing the balance div
		if(orderInfo.payStatus!='Fully Paid') { 
			var res = self.fillTotalFields("Amount Paid",amountPaid.toFixed(2),"makeBold");
			printForm.append(res);
			balance = total - orderInfo.paidAmount;
			var res = self.fillTotalFields("Balance",balanceAmt.toFixed(2),"makeBold");
			printForm.append(res);
		}
		res=self.fillFields("&nbsp;", "&nbsp;") ;
		printForm.append(res);
		res=self.fillFields("&nbsp;", "&nbsp;") ;
		printForm.append(res);
		headerDiv=$j('<div class="footer rowclass subtitle"/>');
		var billTopHeader = $j('<div class="middle rowclass"/>');
		var footer1 = orderService.getBillValue(constants.BILLFOOTER1);
		billTopHeader.append(footer1);
		billTopHeader.append('<br/>');
		var footer2 = orderService.getBillValue(constants.BILLFOOTER2);
		billTopHeader.append(footer2);
		headerDiv.append(billTopHeader);
		printForm.append(headerDiv);
		self = self.getSource();
		return printForm;
	}
	
	this.fillFields = function(name,value) {
		headerDiv = $j('<div class="rowclass"/>');
		var investDiv = $j('<div class="dataleft"/>');
		investDiv.append(name);
		headerDiv.append(investDiv);
		var rateDiv = $j('<div class="dataright rightAlign"/>');
		rateDiv.append(value);
		headerDiv.append(rateDiv);
		return headerDiv;
	}
	
	this.fillTotalFields=function(name,value,styleTag) {
		headerDiv = $j('<div class="rowclass"/>');
		var investDiv = $j('<div class="dataleft rightAlign underline"/>');
		investDiv.append(name);
		if(styleTag!=undefined)
			investDiv.addClass(styleTag);
		headerDiv.append(investDiv);
		var rateDiv = $j('<div class="dataright rightAlign"/>');
		rateDiv.append(value);
		headerDiv.append(rateDiv);
		return headerDiv;
	}
	
	this.fillOtherChargeFields=function(name,value) {
		headerDiv = $j('<div class="rowclass"/>');
		var investDiv = $j('<div class="dataleft middle"/>');
		investDiv.append(name);
		headerDiv.append(investDiv);
		var rateDiv = $j('<div class="dataright rightAlign"/>');
		rateDiv.append(value);
		headerDiv.append(rateDiv);
		return headerDiv;
	}
	this.bindEvents = function(source) {
		
	}
	
}