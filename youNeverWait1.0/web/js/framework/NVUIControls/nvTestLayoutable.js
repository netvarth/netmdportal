
function TestNameTable(jsondata,resultId) {
//alert(JSON.stringify(jsondata));
  this.result = function () {
	var table=$j('<table id=TestNameTable/>');
	table.attr('class','stdtable');
		if(jsondata!=null) {
			$j(jsondata).each(function(index,element) {
			//alert("Nithesh"+element.testName);
				var thead = $j('<thead>');
				thead.attr('id','thead_'+index);
				var headRow = $j('<tr/>');
				headRow.attr('class','accordion');
				var str = element.testName;
				var headCol = $j('<th style="text-align:left;" colspan="4"/>');
				var headCheck = $j('<img class=expand_collapse name="expand" align="center" src="/youNeverWait/images/expand.jpg">&nbsp&nbsp<label>'+str+'</label>');
				headCol.append(headCheck);
				headRow.html(headCol);
				thead.html(headRow);
				table.append(thead);
				var tbody=$j('<tbody/>');
				tbody.attr('class',element.testName);
				tbody.attr('style','display:none');
				//tbody.html(element.result); 				
				
				 var response = postdataToServer("/youNeverWait/ws/ui/patient/patientTestResult/",resultId);  
			    //alert(JSON.stringify(response));
				if(response.success==true)
			    var sample=$j.parseJSON(response.testResult);
				//alert(sample);
				var patName = sample.resultHeader.patientName;
				honorific = patName.substring(0, patName.indexOf(' '));
				//alert(honorific);
				var sample=$j.parseJSON(element.result);
				//alert(JSON.stringify(sample));
				var xsample = sample.testResult;
				//alert(JSON.stringify(xsample));		
				var headerData="";
				var status=true;
					if(xsample!=undefined) {
							$j(xsample).each(function (Index, ElementEx) {
							//alert("inn");
								if(headerData=="") {
									//alert("header null");
									if(ElementEx.testLayout=="General"||ElementEx.testLayout=="GeneralOne" || ElementEx.testLayout=="DC") {
										if(status==true) {
											if(ElementEx.testLayout=="DC")
												headerData = new DCLayoutHeader(honorific);
											else
												headerData = new generalLayoutHeader(ElementEx, honorific);
											tbody.append(headerData.result);
												status=false;
										}
										if(ElementEx.testLayout=="General")
											var modalData = new generalLayout(ElementEx, null, 'print',honorific);	
										else if(ElementEx.testLayout=="DC")
											var modalData = new DCLayout(ElementEx, null, 'print',honorific);	
										else
											var modalData = new generalOneLayout(ElementEx, null, 'print',honorific);	
										tbody.append(modalData.result);
									} 	
								} else {
									if(ElementEx.testLayout=="GeneralOne") {						 
										var tmpheaderData = new generalLayoutHeader(ElementEx, honorific);
										var header1= $j('<div/>');
										header1.append(headerData.result);	
										var header2= $j('<div/>');
										header2.append(tmpheaderData.result);						
										findHeaderstat = isEqual(header1.html(),header2.html());
										if(findHeaderstat==true) {							
											var modalData = new generalOneLayout(ElementEx, null, 'print',honorific);
											tbody.append(modalData.result);
										}
									} else if(ElementEx.testLayout=="General") {					
										var tmpheaderData = new generalLayoutHeader(ElementEx,honorific);
										var header1= $j('<div/>');
										header1.append(headerData.result);	
										var header2= $j('<div/>');
										header2.append(tmpheaderData.result);						
										findHeaderstat = isEqual(header1.html(),header2.html());
										if(findHeaderstat==true) {							
											var modalData = new generalLayout(ElementEx, null, 'print',honorific);
											tbody.append(modalData.result);
										}
									}else if(ElementEx.testLayout=="DC") {					
										var tmpheaderData = new DCLayoutHeader(honorific);
										var header1= $j('<div/>');
										header1.append(headerData.result);	
										var header2= $j('<div/>');
										header2.append(tmpheaderData.result);
										findHeaderstat = isEqual(header1.html(),header2.html());
										if(findHeaderstat==true) {	
											var modalData = new DCLayout(ElementEx, null, 'print',honorific);
											tbody.append(modalData.result);
										}
									}	
								}
							});
							}
						
				else{
				 $j(sample).each(function(sampleIndex,newElement) {
							if(newElement.testLayout=="General"||newElement.testLayout=="GeneralOne" || newElement.testLayout=="DC") {
								//alert(JSON.stringify(newElement.testLayout));
								 if(status==true) {
									if(newElement.testLayout=="DC")
										headerData = new DCLayoutHeader(honorific);
									else
										headerData = new generalLayoutHeader(newElement,honorific);
									tbody.append(headerData.result);
									//displayedIndex.push(sampleIndex);
									status=false;
								} 
								if(newElement.testLayout=="General")
									var modalData = new generalLayout(newElement,null, 'print',honorific);
								else if(newElement.testLayout=="DC")
									var modalData = new DCLayout(newElement,null, 'print',honorific);	
								else
									var modalData = new generalOneLayout(newElement,null, 'print',honorific);	
								tbody.append(modalData.result);
							} else if(newElement.testLayout=="Culture Report") {
								var modalData = new cultureLayout(newElement);
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(newElement.testLayout=="WaterCultureReport"){
								var modalData = new waterCultureLayout(honorific,newElement,null, "print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							} else if(newElement.testLayout=="Haemogram"){
								var modalData = new haemogramLayout(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	

							}
							else if(newElement.testLayout=="HaemogramESR"){
								var modalData = new haemogramESRLayout(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	

							}
							
							else if(newElement.testLayout=="Aminoacidogram"){
								var modalData = new aminoacidLayout(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							} 
							else if(newElement.testLayout=="Osmotic"){
								var modalData = new osmoticLayout(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							}
							else if(newElement.testLayout=="Stool Analysis"){
								var modalData = new stoolLayout(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							} else if(newElement.testLayout=="ENA"){
								var modalData = new enaProfile(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							}  else if(newElement.testLayout=="Stone Analysis"){
								var modalData = new stone(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							}
							else if(newElement.testLayout=="Urine"){
								var modalData = new UrineLayout(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							}
							else if(newElement.testLayout=="AFB"){
								var modalData = new AFBLayout(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							}
							else if(newElement.testLayout=="CD"){
								var modalData = new CDLayout(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							}
							else if(newElement.testLayout=="GTT"){
								var modalData = new GTTLayout(honorific,newElement, null, "print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							}
							else if(newElement.testLayout=="Peripheral"){
								var modalData = new PeripheralLayout(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							}
							else if(newElement.testLayout=="PT"){
								var modalData = new PTLayout(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							}
								else if(newElement.testLayout=="APPT"){
								var modalData = new APPTLayout(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							}
							else if(newElement.testLayout=="HistoPath"){
								var modalData = new HistoPathLayout(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							}
							else if(newElement.testLayout=="LipidLayout"){
								var modalData = new LipidLayout(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							}
							else if(newElement.testLayout=="LFT"){
								var modalData = new LiverLayout(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							}
							else if(newElement.testLayout=="DC"){
								var modalData = new DCLayout(newElement,null, "print",honorific);
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							}
							else if(newElement.testLayout=="ANA"){
								var modalData = new ANALayout(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							}else if(newElement.testLayout=="BetaHCGLayout") {	
								tbody.attr('style',"border:none;");
								var modalData = new generalLayout(newElement, null, 'print',honorific);
								tbody.append(modalData.result);
								//displayedIndex.push(sampleIndex);
								tbody.append('<br/>');
								headerData = "";									
								return false;
							} else if(newElement.testLayout=="Semen Layout"){
								var modalData = new SemenLayout(honorific,newElement, null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;	
							} 
							else if(newElement.testLayout=="GeneralOne") {						 
								var tmpheaderData = new generalLayoutHeader(newElement,honorific);
								var header1= $j('<div/>');
								header1.append(headerData.result);	
								var header2= $j('<div/>');
								header2.append(tmpheaderData.result);						
								findHeaderstat = isEqual(header1.html(),header2.html());
								if(findHeaderstat==true) {							
									var modalData = new generalOneLayout(newElement, null, 'print',honorific);
									tbody.append(modalData.result);
									//displayedIndex.push(sampleIndex);
								}
							}
							else if(newElement.testLayout=="General") {					
								var tmpheaderData = new generalLayoutHeader(newElement,honorific);
								var header1= $j('<div/>');
								header1.append(headerData.result);	
								var header2= $j('<div/>');
								header2.append(tmpheaderData.result);						
								findHeaderstat = isEqual(header1.html(),header2.html());
								if(findHeaderstat==true) {							
									var modalData = new generalLayout(newElement, null, 'print',honorific);
									tbody.append(modalData.result);
									//displayedIndex.push(sampleIndex);
								}
							}
							else if(newElement.testLayout=="DC") {					
								var tmpheaderData = new DCLayoutHeader(honorific);
								var header1= $j('<div/>');
								header1.append(headerData.result);	
								var header2= $j('<div/>');
								header2.append(tmpheaderData.result);
								findHeaderstat = isEqual(header1.html(),header2.html());
								if(findHeaderstat==true) {	
									var modalData = new DCLayout(newElement, null, 'print',honorific);
									tbody.append(modalData.result);
									//displayedIndex.push(sampleIndex);
								} 
							}	


							else {
								var modalData = new layout1(honorific,newElement,null,"print");
								//displayedIndex.push(sampleIndex);
								tbody.append(modalData.result);
								tbody.append('<br/>');
								headerData = "";									
								return false;
							}
							
			});	
	}
				table.append(tbody);	   
			});
	    }
		return table;
	}; 
	}

