function ResultPreviewProcessor(orderReference) {
	this.orderReference = orderReference;
	this.getOrderUI = function(){return this.orderReference;}
	//Function creating the Preview of Results
	this.preview = function( honorific,combinedResult,orderId, order,printStatus) {
		self = this;
		var layoutStatus = methodInvoker.getPageSettingByKey('general_layout');
		newLayouts=layoutStatus.visible;
		var reportRoot = $('<div id="resultRoot"/>');
		if(combinedResult.resultList)
			resultList=combinedResult.resultList;
		else	
			resultList=combinedResult;
		$(resultList).each(function (index, result) {
			if(newLayouts==false)
				reportRoot.append(self.getResult(honorific,result,combinedResult,orderId,order,printStatus));
			else
				reportRoot.append(self.generate(honorific,result,combinedResult,orderId,order));
		});	
		return reportRoot;
	}	
	this.getResult = function(honorific,result,combinedResult,orderId,order,printStatus) {
		var orderUI = this.getOrderUI();
		var ReportPageRoot = $('<div class="pageRoot" />');	
		if(printStatus!=true){
			var wrapper = $('<div style="background-color:#D6FCFF;" class="groupResultTable" />');
		}else{
			var wrapper = $('<div class="groupResultTable" />');
		}
		var resultContent = $('<div class="pageResultContent"  />');
		var resultParent = result;
		var parentTable = $('<table  style="width:90%; margin:auto; position:relative;"  />');	
		var remarksDiv = $('<div class="remarks"/>');
		var remarksHeaderTag = $('<p><b>Remarks</b></p>');
		var remarksTag = $('<p/>');	
		//Array used for holding the test/testpackage ids already listed
		var displayedIndex =[];
		var tpCount = 0;
		while((displayedIndex.length+tpCount)<result.testResult.length) {
			var trTag= $('<tr/>');
			var tdTag=$('<td>');
			var tblObj = $('<table width="95%" class="printresultTable"/>');
			var status=true;
			var headerData="";	
			// Iterating through tests and packages			
			$(result.testResult).each(function (resultIndex, singleResult) {
				//check whether the result already displayed
				isExistStatus =commonMethodInvoker.isIndexExists(displayedIndex, resultIndex);	
				if(isExistStatus==false) {
					if(singleResult.IsTstPkgTitle!=undefined) {
						if(singleResult.IsTstPkgTitle==false) {							
							$(singleResult.testResult).each(function (resultIndex1, tpTestResult) {
								if(headerData=="") {											
									if(tpTestResult.testLayout==constants.LAYOUTGENERAL || tpTestResult.testLayout==constants.LAYOUTGENERALMED || tpTestResult.testLayout==constants.LAYOUTGENERALONE || tpTestResult.testLayout==constants.LAYOUTDC) {
										if(status==true) {
											if(tpTestResult.testLayout==constants.LAYOUTDC)
												headerData = new DCLayoutHeader(honorific);
											else
												headerData = new generalLayoutHeader(tpTestResult, honorific);
											tblObj.append(headerData.result);
											status=false;
										}
										if(tpTestResult.testLayout==constants.LAYOUTGENERAL)
											var modalData = new generalLayout(tpTestResult, null, constants.MODEPRINT,honorific, order);	
										else if(tpTestResult.testLayout==constants.LAYOUTGENERALMED)
											var modalData = new generalLayoutMed(tpTestResult, null, constants.MODEPRINT,honorific);	
										else if(tpTestResult.testLayout==constants.LAYOUTDC)
											var modalData = new DCLayout(tpTestResult, null, constants.MODEPRINT,honorific);	
										else
											var modalData = new generalOneLayout(tpTestResult, null, constants.MODEPRINT,honorific);	
										tblObj.append(modalData.result);
									} 	
								} else {
									if(tpTestResult.testLayout==constants.LAYOUTGENERALONE) {						 
										var tmpheaderData = new generalLayoutHeader(tpTestResult, honorific);
										var header1= $('<div/>');
										header1.append(headerData.result);	
										var header2= $('<div/>');
										header2.append(tmpheaderData.result);						
										findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
										if(findHeaderstat==true) {							
											var modalData = new generalOneLayout(tpTestResult, null, constants.MODEPRINT,honorific);
											tblObj.append(modalData.result);
										}
									} else if(tpTestResult.testLayout==constants.LAYOUTGENERAL) {					
										var tmpheaderData = new generalLayoutHeader(tpTestResult,honorific);
										var header1= $('<div/>');
										header1.append(headerData.result);	
										var header2= $('<div/>');
										header2.append(tmpheaderData.result);		
										//findHeaderstat = isEqual(header1.html(),header2.html());
										//if(findHeaderstat==true) {							
										var modalData = new generalLayout(tpTestResult, null, constants.MODEPRINT,honorific, order);
										tblObj.append(modalData.result);
										//}
									}else if(tpTestResult.testLayout==constants.LAYOUTGENERALMED) {						
										var modalData = new generalLayoutMed(tpTestResult, null, constants.MODEPRINT,honorific);
										tblObj.append(modalData.result);
									}else if(tpTestResult.testLayout==constants.LAYOUTDC) {					
										var tmpheaderData = new DCLayoutHeader(honorific);
										var header1= $('<div/>');
										header1.append(headerData.result);	
										var header2= $('<div/>');
										header2.append(tmpheaderData.result);
										findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
										if(findHeaderstat==true) {	
											var modalData = new DCLayout(tpTestResult, null, constants.MODEPRINT,honorific);
											tblObj.append(modalData.result);
										}
									}	
								}
							});
							displayedIndex.push(resultIndex);
						} else 
							tpCount++;
					} else {
						if(headerData=="") {
							if(singleResult.testLayout==constants.LAYOUTGENERAL || singleResult.testLayout==constants.LAYOUTGENERALONE || singleResult.testLayout==constants.LAYOUTDC) {
								if(status==true) {
									if(singleResult.testLayout==constants.LAYOUTDC)
										headerData = new DCLayoutHeader(honorific);
									else
										headerData = new generalLayoutHeader(singleResult, honorific);
									tblObj.append(headerData.result);
									displayedIndex.push(resultIndex);
									status=false;
								}
								var modalData;
								if(singleResult.testLayout==constants.LAYOUTGENERAL)
									modalData = new generalLayout(singleResult, null, constants.MODEPRINT,honorific,order);	
								else if(singleResult.testLayout==constants.LAYOUTDC)
									modalData = new DCLayout(singleResult, null, constants.MODEPRINT,honorific);	
								else if(singleResult.testLayout==constants.LAYOUTGENERALMED){
									modalData = new generalLayoutMed(singleResult, null,constants.MODEPRINT,honorific);		
								} else
									var modalData = new generalOneLayout(singleResult, null, constants.MODEPRINT,honorific);	
								tblObj.append(modalData.result);
							} else if(singleResult.testLayout==constants.LAYOUTGENERALMED){
								var modalData = new generalLayoutMed(singleResult, null, constants.MODEPRINT,honorific);	
								tblObj.append(modalData.result);
								headerData = modalData.result;
								displayedIndex.push(resultIndex);		
							} else if(singleResult.testLayout==constants.LAYOUTCULTURE || singleResult.testLayout=="CultureReport" ) {
								var modalData = new cultureLayout(honorific,singleResult,null, constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTWATERCULTURE){
								var modalData = new waterCultureLayout(honorific,singleResult,null, constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTHAEMOGRAM){
								var modalData = new haemogramLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTHAEMOGRAMESR){
								var modalData = new haemogramESRLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTAMINOACIDOGRAM){
								var modalData = new aminoacidLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTOSMOTIC){
								var modalData = new osmoticLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTSTOOLANALYSIS  || singleResult.testLayout=="StoolAnalysis"){
								var modalData = new stoolLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTENA){
								var modalData = new enaProfile(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							}  else if(singleResult.testLayout==constants.LAYOUTSTONEANALYSIS  || singleResult.testLayout=="StoneAnalysis"){
								var modalData = new stone(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTURINE){
								var modalData = new UrineLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTAFB){
								var modalData = new AFBLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTCD){
								var modalData = new CDLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTGTT){
								var modalData = new GTTLayout(honorific,singleResult, null, constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTPERIPHERAL){
								var modalData = new PeripheralLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTPT){
								var modalData = new PTLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTAPPT){
								var modalData = new APPTLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTHISTOPATH){
								var modalData = new HistoPathLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTLIPID){
								var modalData = new LipidLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTLFT){
								var modalData = new LiverLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTDC){
								var modalData = new DCLayout(singleResult,null, constants.MODEPRINT,honorific);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTANA){
								var modalData = new ANALayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTBETAHCG) {	
								tblObj.attr('style',"border:none;");
								var modalData = new generalLayout(singleResult, null, constants.MODEPRINT,honorific,order);
								tblObj.append(modalData.result);
								displayedIndex.push(resultIndex);
								tblObj.append('<br/>');
								headerData = "";									
								return false;
							} else if(singleResult.testLayout==constants.LAYOUTSEMEN || singleResult.testLayout=="SemenLayout"){
								var modalData = new SemenLayout(honorific,singleResult, null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTGCT){
								headerData = new DCLayoutHeader(honorific);
								tblObj.append(headerData.result);
								var modalData = new NVGCTLayout(singleResult, null,constants.MODEPRINT,honorific);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="WidalLayoutMed"){
								var modalData = new widal(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="UrineLayoutMed") {
								var modalData = new urineLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							}  else if(singleResult.testLayout=="FoodAllergy") {
								var modalData = new foodAllergy(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							}  else if(singleResult.testLayout=="ANAIFALayout") {
								var modalData = new anaIfa(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="DCLayoutMed") {
								var modalData = new DCLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="LipidLayoutMed"){
								var modalData = new lipidLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="LFTLayoutMed") {
								var modalData = new liverLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="HaemogramLayoutMed") {
								var modalData = new haemogramLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else {
								var modalData = new layout1(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;
							}	
						} else {
							if(singleResult.testLayout==constants.LAYOUTGENERALONE) {						 
								var tmpheaderData = new generalLayoutHeader(singleResult,honorific);
								var header1= $('<div/>');
								header1.append(headerData.result);	
								var header2= $('<div/>');
								header2.append(tmpheaderData.result);						
								findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
								if(findHeaderstat==true) {							
									var modalData = new generalOneLayout(singleResult, null, constants.MODEPRINT,honorific);
									tblObj.append(modalData.result);
									displayedIndex.push(resultIndex);
								}
							} else if(singleResult.testLayout==constants.LAYOUTGENERAL) {					
								var tmpheaderData = new generalLayoutHeader(singleResult,honorific);
								var header1= $('<div/>');
								header1.append(headerData.result);	
								var header2= $('<div/>');
								header2.append(tmpheaderData.result);						
								findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
								if(findHeaderstat==true) {							
									var modalData = new generalLayout(singleResult, null, constants.MODEPRINT,honorific,order);
									tblObj.append(modalData.result);
									displayedIndex.push(resultIndex);
								}
							} else if(singleResult.testLayout==constants.LAYOUTDC) {					
								var tmpheaderData = new DCLayoutHeader(honorific);
								var header1= $('<div/>');
								header1.append(headerData.result);	
								var header2= $('<div/>');
								header2.append(tmpheaderData.result);
								findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
								if(findHeaderstat==true) {	
									var modalData = new DCLayout(singleResult, null, constants.MODEPRINT,honorific);
									tblObj.append(modalData.result);
									displayedIndex.push(resultIndex);
								}
							} else if(singleResult.testLayout==constants.LAYOUTGENERALMED){
								var modalData = new generalLayoutMed(singleResult, null, constants.MODEPRINT,honorific);									
								tblObj.append(modalData.result);
								displayedIndex.push(resultIndex);
							}	
						}
					}	
				}		
			});	
			tdTag.append(tblObj);
			trTag.append(tdTag);	
			parentTable.append(trTag);	
		}
		if(tpCount>0) {
			$(result.testResult).each(function (resultIndex, singleResult) {
				if(singleResult.IsTstPkgTitle!=undefined) {
					if(singleResult.IsTstPkgTitle==true) {
						trTag= $('<tr/>');
						tdTag=$('<td class="titleClass" />');
						tdTag.html(singleResult.testName);
						trTag.append(tdTag);
						parentTable.append(trTag);
						trTag= $('<tr/>');
						tdTag=$('<td />');					
						var tblObj = $('<table width="100%" class="printresultTable"/>');
						var status=true;
						headerData = "";
						$(singleResult.testResult).each(function (resultIndex, tpTestResult) {
							if(headerData=="") {											
								if(tpTestResult.testLayout==constants.LAYOUTGENERAL || tpTestResult.testLayout==constants.LAYOUTGENERALMED || tpTestResult.testLayout==constants.LAYOUTGENERALONE || tpTestResult.testLayout==constants.LAYOUTDC) {

									if(status==true) {
										if(tpTestResult.testLayout==constants.LAYOUTDC)
											headerData = new DCLayoutHeader(honorific);
										else
											headerData = new generalLayoutHeader(tpTestResult,honorific);
										tblObj.append(headerData.result);
										status=false;
									}
									if(tpTestResult.testLayout==constants.LAYOUTGENERAL)
										var modalData = new generalLayout(tpTestResult, null, constants.MODEPRINT,honorific,order);	
									else if(tpTestResult.testLayout==constants.LAYOUTGENERALMED)
										var modalData = new generalLayoutMed(tpTestResult, null, constants.MODEPRINT,honorific);				
									else if(tpTestResult.testLayout==constants.LAYOUTDC)
										var modalData = new DCLayout(tpTestResult, null, constants.MODEPRINT,honorific);	
									else
										var modalData = new generalOneLayout(tpTestResult, null, constants.MODEPRINT,honorific);	
									tblObj.append(modalData.result);
								} 	
							} else {
								if(tpTestResult.testLayout==constants.LAYOUTGENERALONE) {						 
									var tmpheaderData = new generalLayoutHeader(tpTestResult,honorific);
									var header1= $('<div/>');
									header1.append(headerData.result);	
									var header2= $('<div/>');
									header2.append(tmpheaderData.result);						
									findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
									if(findHeaderstat==true) {							
										var modalData = new generalOneLayout(tpTestResult, null, constants.MODEPRINT,honorific);
										tblObj.append(modalData.result); 
									}
								} else if(tpTestResult.testLayout==constants.LAYOUTGENERAL) {					
									var tmpheaderData = new generalLayoutHeader(tpTestResult,honorific);
									var header1= $('<div/>');
									header1.append(headerData.result);	
									var header2= $('<div/>');
									header2.append(tmpheaderData.result);						
									findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
									if(findHeaderstat==true) {							
										var modalData = new generalLayout(tpTestResult, null, constants.MODEPRINT,honorific,order);
										tblObj.append(modalData.result);
									}
								}else if(tpTestResult.testLayout==constants.LAYOUTGENERALMED) {					
									var modalData = new generalLayoutMed(tpTestResult, null, constants.MODEPRINT,honorific);
									tblObj.append(modalData.result);
								}else if(tpTestResult.testLayout==constants.LAYOUTDC) {					
									var tmpheaderData = new DCLayoutHeader(honorific);
									var header1= $('<div/>');
									header1.append(headerData.result);	
									var header2= $('<div/>');
									header2.append(tmpheaderData.result);
									findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
									if(findHeaderstat==true) {	
										var modalData = new DCLayout(tpTestResult, null, constants.MODEPRINT,honorific);
										tblObj.append(modalData.result);
									}
								}	
							}
						});
						tdTag.append(tblObj);
						trTag.append(tdTag);	
						parentTable.append(trTag);	
					}
				}
			});
		}
		//remarks section
		if(!result.testName) {				
			$(result.testResult).each(function (resultIndex, singleResult) {
				var remark=singleResult.remarks;
				if(remark!=null && remark.trim()!="") {
					var pTag = $('<p/>');	
					var remarkInfo = commonMethodInvoker.nl2br(remark);
					remarkInfo  = remarkInfo.replaceAll(" ", "&nbsp;");
					pTag.append(remarkInfo);
					remarksTag.append(pTag);
				}
			});
		} else {
			var remark=result.remarks;
			if(remark!=null && remark.trim()!="") {
				var pTag = $('<p/>');	
				var remarkInfo = commonMethodInvoker.nl2br(remark);
				remarkInfo  = remarkInfo.replaceAll(" ", "&nbsp;");
				pTag.append(remarkInfo);
				remarksTag.append(pTag);
			}
		}
		if(remarksTag.html().trim().length!=0) {
			remarksDiv.append(remarksHeaderTag);
			remarksDiv.append(remarksTag.html());
		}
		resultContent.append(parentTable);
		resultContent.append(remarksDiv);
		var header = new ResultHeaderProcessor(orderUI);
		var repHeader = header.make(resultParent,combinedResult,orderId,honorific,printStatus);
		wrapper.append(repHeader);
		var contentset =methodInvoker.getPageSettingByKey('content');
		var originalHeight = parseInt(contentset.height,10) || 0;
		var marginHeight = parseInt(contentset.marginTop,10) || 0;
		var totalHeight = originalHeight-marginHeight;
		resultContent.css("height",totalHeight + "mm");
		resultContent.css("width",contentset.width+"mm");
		resultContent.css("margin-top",contentset.marginTop+"mm");
		resultContent.css("font-size",contentset.fontSize + "px");
		wrapper.append(resultContent);
		var headerset =methodInvoker.getPageSettingByKey('header');
		var footerset =methodInvoker.getPageSettingByKey('footer');
		var footer = new ResultFooterProcessor(orderUI);
		var repFooter = footer.create(resultParent,combinedResult,orderId);
		wrapper.append(repFooter);
		totalHeight=parseInt(headerset.height) + parseInt(footerset.height) +parseInt(contentset.height);
		wrapper.css("height","297mm");
		//wrapper.css("height",totalHeight+"mm");
		wrapper.css("width",contentset.width+"mm");
		ReportPageRoot.append(wrapper);
		return ReportPageRoot;
	}
	this.generate = function(honorific,result,combinedResult,orderId,order) {
		var orderUI = this.getOrderUI();
		ReportPageRoot = $('<div class="pageRoot"/>');		
		var wrapper = $('<div class="groupResultTable" />');
		var resultContent = $('<div class="pageResultContent"  />');
		var resultParent = result;
		var parentTable = $('<div  style="width:100%;"  />');	
		var remarksDiv = $('<div class="remarks"/>');
		var remarksHeaderTag = $('<p class="nomargin"><b>Remarks</b></p>');
		var remarksTag = $('<p/>');	
		remarksTag.addClass('nomargin');
		var tblObj = $('<div class="printresultTable" style="float:left;width:100%"/>');
		var departmentList = [];
		$(result.testResult).each(function (resultIndex, singleResult) {
			departmentName=null;
			if(singleResult.departmentName)
				departmentName = singleResult.departmentName;
			else
				departmentName=" ";
			if(!commonMethodInvoker.isIndexExists(departmentList,departmentName)){
				departmentList.push(departmentName);
				if(departmentName!=null){
					var tbody=$('<div class="one singleheight "/>');
					var tdTag = $('<div class="onefirst">&nbsp;</div>');
					tbody.empty().html(tdTag);
					tdTag = $('<div class="onesecond bold underline">&nbsp;</div>');
					tdTag.empty().html(singleResult.departmentName);
					tbody.append(tdTag);
					tdTag = $('<div class="onesecond">&nbsp;</div>');
					tbody.append(tdTag);
					tblObj.append(tbody);
					parentTable.append(tblObj);
				}	
			}
			if(singleResult.testResult) {
				if(singleResult.IsTstPkgTitle==true){
					var tbody=$('<div class="one underline bold"/>');
					tbody.append(singleResult.testName);
					tblObj.append(tbody);
				}
				$(singleResult.testResult).each(function (resultIndex1, subResult) {
					var modalData = self.generateLayoutData(subResult, honorific, order);
					tblObj.append(modalData.result);
				});
			} else {
				var modalData = self.generateLayoutData(singleResult, honorific, order);
				tblObj.append(modalData.result);
			}
		});	
		//remarks section
		if(!result.testName) {			
			$(result.testResult).each(function (resultIndex, singleResult) {
				var remark=singleResult.remarks;
				if(remark!=null && remark.trim()!="") {
					var pTag = $('<p />');	
					pTag.addClass('nomargin');
					var remarkInfo = commonMethodInvoker.nl2br(remark);
					remarkInfo  = remarkInfo.replaceAll(" ", "&nbsp;");
					pTag.append(remarkInfo);
					remarksTag.append(pTag);
				}
			});
		} else {
			var remark=result.remarks;
			if(remark!=null && remark.trim()!="") {
				var pTag = $('<p/>');	
				pTag.addClass('nomargin');
				var remarkInfo = commonMethodInvoker.nl2br(remark);
				remarkInfo  = remarkInfo.replaceAll(" ", "&nbsp;");
				pTag.append(remarkInfo);
				remarksTag.append(pTag);
			}
		}
		if(remarksTag.html().trim().length!=0) {
			remarksDiv.append(remarksHeaderTag);
			remarksDiv.append(remarksTag.html());
		}
		remarksDiv.addClass("one");
		remarksDiv.addClass("columnleftalign");
		tblObj.append(remarksDiv);
		parentTable.append(tblObj);
		resultContent.append(parentTable);
		var header = new ResultHeaderProcessor(orderUI);
		var repHeader = header.make(resultParent,combinedResult,orderId,honorific,printStatus);
		var repHeader = header.generate(resultParent,combinedResult,orderId,honorific);
		wrapper.append(repHeader);
		var contentset =methodInvoker.getPageSettingByKey('content');
		var headerset =methodInvoker.getPageSettingByKey('header');
		var footerset =methodInvoker.getPageSettingByKey('footer');
		var originalHeight = parseInt(contentset.height,10) || 0;
		var marginHeight = parseInt(contentset.marginTop,10) || 0;
		var totalHeight = originalHeight-marginHeight;
		resultContent.css("height",totalHeight + "mm");
		resultContent.css("width",contentset.width+"mm");
		resultContent.css("margin-top",contentset.marginTop+"mm");
		resultContent.css("font-size",contentset.fontSize + "px");
		resultContent.css("margin-left",headerset.marginLeft + "mm");
		wrapper.append(resultContent);
		var footer = new ResultFooterProcessor(orderUI);
		var repFooter = footer.generalFooter();
		wrapper.append(repFooter);
		totalHeight=parseInt(headerset.height) + parseInt(footerset.height) +parseInt(contentset.height);
		wrapper.css("height",totalHeight+"mm");
		//wrapper.css("width",contentset.width+"mm");
		ReportPageRoot.append(wrapper);
		return ReportPageRoot;
	}
	this.generateLayoutData =function(singleResult,honorific,order) {
		if(singleResult.testLayout==constants.LAYOUTGENERAL)
			var modalData = new generalLayout(singleResult, null, constants.MODEPRINT,honorific,order);	
		else if(singleResult.testLayout==constants.LAYOUTDC)
			var modalData = new DCLayout(singleResult, null, constants.MODEPRINT,honorific);	
		else if(singleResult.testLayout==constants.LAYOUTGENERALONE)
			var modalData = new generalOneLayout(singleResult, null, constants.MODEPRINT,honorific);	
		else if(singleResult.testLayout==constants.LAYOUTGENERALMED)
			var modalData = new generalLayoutMed(singleResult, null, constants.MODEPRINT,honorific);									
		else if(singleResult.testLayout==constants.LAYOUTCULTURE || singleResult.testLayout=="CultureReport" )
			var modalData = new cultureLayout(honorific,singleResult,null, constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTWATERCULTURE)
			var modalData = new waterCultureLayout(honorific,singleResult,null, constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTHAEMOGRAM)
			var modalData = new haemogramLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTHAEMOGRAMESR)
			var modalData = new haemogramESRLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTAMINOACIDOGRAM)
			var modalData = new aminoacidLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTOSMOTIC)
			var modalData = new osmoticLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTSTOOLANALYSIS  || singleResult.testLayout=="StoolAnalysis")
			var modalData = new stoolLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTENA)
			var modalData = new enaProfile(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTSTONEANALYSIS  || singleResult.testLayout=="StoneAnalysis")
			var modalData = new stone(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTURINE)
			var modalData = new UrineLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTAFB)
			var modalData = new AFBLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTCD)
			var modalData = new CDLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTGTT)
			var modalData = new GTTLayout(honorific,singleResult, null, constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTPERIPHERAL)
			var modalData = new PeripheralLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTPT)
			var modalData = new PTLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTAPPT)
			var modalData = new APPTLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTHISTOPATH)
			var modalData = new HistoPathLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTLIPID)
			var modalData = new LipidLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTLFT)
			var modalData = new LiverLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTDC)
			var modalData = new DCLayout(singleResult,null, constants.MODEPRINT,honorific);
		else if(singleResult.testLayout==constants.LAYOUTANA)
			var modalData = new ANALayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTBETAHCG)
			var modalData = new generalLayout(singleResult, null, constants.MODEPRINT,honorific,order);
		else if(singleResult.testLayout==constants.LAYOUTSEMEN || singleResult.testLayout=="SemenLayout")
			var modalData = new SemenLayout(honorific,singleResult, null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTGCT)
			var modalData = new NVGCTLayout(singleResult, null,constants.MODEPRINT,honorific);
		else if(singleResult.testLayout=="WidalLayoutMed")
			var modalData = new widal(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="UrineLayoutMed")
			var modalData = new urineLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="LipidLayoutMed")
			var modalData = new lipidLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="DCLayoutMed")
			var modalData = new DCLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="FoodAllergy") 
			var modalData = new foodAllergy(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="ANAIFALayout") 
			var modalData = new anaIfa(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="LFTLayoutMed") 
			var modalData = new liverLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="HaemogramLayoutMed") 
			var modalData = new haemogramLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
		else 
			var modalData = new layout1(honorific,singleResult,null,constants.MODEPRINT);
		return modalData;
	}
}
function ResultDataGenerator() {
	this.arrangeByDeparment=function(testResult){
		var testResult = [];
		var departmentList = [];
		/*find departments*/
		$(testResult).each(function(index, result) {
			var currentResult = $.parseJSON(result.result);
			departmentName="";
			if(currentResult.departmentName)
				departmentName=currentResult.departmentName;
			if(!commonMethodInvoker.isIndexExists(departmentList, departmentName))
				departmentList.push(departmentName);
		});
		$(departmentList).each(function(index, department) {
			$(testResult).each(function(index, result) {
				var currentResult = $.parseJSON(result.result);
				departmentName="";
				if(currentResult.departmentName)
					departmentName=currentResult.departmentName;
				if(commonMethodInvoker.isEqual(departmentName, department))
					testResult.push(result);
			});
		});
		return testResult;
	}
	this.createFinalJson = function(orderReference , testResult, reportDate, reportTime) {
		var finalJson = new FinalJSONDTO();
		var testList = [];
		var resultHeader = orderReference.getOrderHeader();
		finalJson.setResultHeader(resultHeader);	
		$(testResult).each(function(index, result) {
			var testDetails = new TestListJsonDTO();
			var specimens=[];
			testDetails.setSpecimens(specimens);
			if(result.reportingDate)
				testDetails.setReportingDate(result.reportingDate);
			else
				testDetails.setReportingDate(reportDate);
			if(result.reportingTime)
				testDetails.setReportingTime(result.reportingTime);
			else
				testDetails.setReportingTime(reportTime);
			testDetails.setResult(result);
			testList.push(testDetails);
		});
		finalJson.setTestList(testList);
		return finalJson;
	}
	this.generateResultData =function(orderReference ,testArray, testResult) {
		var self = this;
		var orderHeader = orderReference.getOrderHeader();
		var resultViewResponse = new ResultViewResponseDTO();//response to return	
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		var curdate = new Date();
		var reportDate = commonMethodInvoker.getUserDateFromSystemDate(curdate);
		var reportTime = commonMethodInvoker.getUserTimeFromSystemDate(curdate);
		var finaljson = self.createFinalJson(orderReference, testResult, reportDate, reportTime);
		resultViewResponse.setResult(finaljson);
		var verifiedTestIdsList = [];
		var testSpecimenList = [];
		var resultToPrint = this.generatePrintJson(orderReference, testArray, testResult,reportDate,reportTime);
		resultViewResponse.setPrintResult(resultToPrint);
		resultViewResponse.setTestList(testSpecimenList);
		resultViewResponse.setReportDate(reportDate);
		resultViewResponse.setReportTime(reportTime);
		resultViewResponse.setSuccess(true);	
		resultViewResponse.setPrinted(true);
		resultViewResponse.setOrderUid(orderHeader.uid);
		return resultViewResponse;
	}
	this.generatedDeliveredResult = function(orderReference, testArray, testResult) {
		var orderHeader = orderReference.getOrderHeader();
		var self=this;
		var resultHtml;
		var pageResult = [];
		var selectedTests = [];
		var finalResult = [];
		var orderResultResponseCopy = testResult;
		var status=true;
		$(orderResultResponseCopy).each(function(index, result) {
			var testId=result.testUid;
			if(result.specimenUid)
				testId = testId + "_" + result.specimenUid;
			if(!commonMethodInvoker.isIndexExists(testArray, testId))
				return;
			var reportingDate = result.reportingDate;
			var reportingTime = result.reportingTime;
			$(testResult).each(function(index1, result1) {
				testId=result1.testUid;
				if(result1.specimenUid)
					testId = testId + "_" + result1.specimenUid;
				if(!commonMethodInvoker.isIndexExists(testArray, testId))
					return;
				if(result1.reportingDate==reportingDate && result1.reportingTime==reportingTime) {
					if(!commonMethodInvoker.isIndexExists(selectedTests, testId)) {
						pageResult.push(result1);
						selectedTests.push(testId);
					}	
				}
			});
			if(pageResult.length>0)
				finalResult.push(pageResult);
			pageResult=[];
		});
		var verifiedUserProvider = new VerifiedUserProvider(); //For getting verified users
		var resultPrintArray = [];
		var contentset =methodInvoker.getPageSettingByKey('content');
		var originalHeight = parseInt(contentset.height,10) || 0;
		var marginHeight = parseInt(contentset.marginTop,10) || 0;
		var totalHeight = originalHeight-marginHeight;
		$(finalResult).each(function(parentIndex,singleResult) {
			var resultArray = []; //storing tests info
			var resultTestPackageArray=[]; //For storing tests which should be in separate pages and testpackages
			var resultSet =[]; //
			var resultHeight=0;//for calculating the height of tests to include in one page
			var curReportDate;
			var curReportTime;
			$(singleResult).each(function (index,result ) {
				curReportDate = result.reportingDate;
				curReportTime = result.reportingTime;
				var testId = result.testUid;
				var tstId=testId;
				if(result.specimenUid)
					tstId = testId + "_" + result.specimenUid;		
				var resultVar=result;					
				if(resultVar.testLayout=="General" || resultVar.testLayout=="GeneralOne" || resultVar.testLayout=="DC" || resultVar.testLayout=='GTT' || resultVar.testLayout=='Urine' || resultVar.testLayout=='LipidLayout' || resultVar.testLayout=='PT' || resultVar.testLayout==constants.LAYOUTGENERALMED
						|| resultVar.testLayout=="WidalLayoutMed" || resultVar.testLayout=="UrineLayoutMed" || resultVar.testLayout=="LipidLayoutMed" || resultVar.testLayout=="DCLayoutMed") {
					if(resultVar.layoutHeight)
						resultHeight+=resultVar.layoutHeight;	
					var heightmm = commonMethodInvoker.getmmFromPixel(resultHeight, methodInvoker.getDPI());			
					if(heightmm>totalHeight) {
						var specimens="";
						//var specimens = resultSpecimenProcessor.getSpecimenList('{"testResult":[' + resultArray + ']}',orderHeader.header.uid);
						var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(resultArray) + '}',"");
						resultSet.push('{"specimens":"' + specimens + '","testResult":' + JSON.stringify(resultArray) + ',"verifiedUsers":'+JSON.stringify(verifiedUserIds) + ',"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
						resultArray=[];
						resultHeight=resultVar.layoutHeight+25;
					}	
					resultArray.push(result.result);
				} else
					resultTestPackageArray.push(result.result);
			});	
			if(resultArray.length>0){
				var specimens="";
				//	var specimens = resultSpecimenProcessor.getSpecimenList('{"testResult":[' + resultArray + ']}',orderInfo.header.uid);
				var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(resultArray) + '}',"");
				resultSet.push('{"specimens":"' + specimens + '","testResult":' + JSON.stringify(resultArray) + ',"verifiedUsers":['+verifiedUserIds + '],"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
			}	
			$(resultTestPackageArray).each(function (index, testResult) {
				var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(testResult) + '}',"");
				resultSet.push('{"testResult":' + JSON.stringify(testResult) + ',"verifiedUsers":['+verifiedUserIds + '],"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
			});
			var resultHeader = orderReference.getOrderHeader();
			resultToPrint = '{"resultHeader":' + JSON.stringify(resultHeader) + ',"resultList":[' + resultSet + ']}';
			resultPrintArray.push(resultToPrint);
		});
		return resultPrintArray;
	}	
}
function VerifiedUserProvider() {
	this.getVerifiedUsers = function(result,orderId) {
		var userArray = [];
		$($.parseJSON(result).testResult).each(function (index,testInfo) {
			if(testInfo.userId) {
				var userId=testInfo.userId;
				if(!commonMethodInvoker.isIndexExists(userArray,userId))
					userArray.push(userId);
			}
		});
		var userList=[];
		$(userArray).each(function (index,id) {
			$($.parseJSON(result).testResult).each(function (index1,testInfo) {
				if(id==testInfo.userId){
					var user = new VerifierDTO();
					user.setUserId(id);
					user.setName(testInfo.userName);
					user.setDesignation(testInfo.userDesignation);
					userList.push(user);
					user=null;
					return false;
				}	
			});
		});
		return userList;
	}
	this.getTestPackageTitleArea = function() {
		var testpkgTitleDiv=$('<div class="box-content column-right">');
		var pTag = $('<p>');
		pTag.append($('<br/>'));
		var spanTag = $('<span />');
		var checkboxBtn=$('<input type="checkbox" name="title" value="title" id="testpkgTitle">');
		spanTag.append(checkboxBtn);
		pTag.append(spanTag);
		spanTag = $('<span />');
		spanTag.append(constants_testPkgTitle);
		pTag.append(spanTag);
		testpkgTitleDiv.append(pTag);
		return testpkgTitleDiv;
	}
}	
ResultDataGenerator.prototype.generatePrintJson = function(orderReference,selectedTestArray, testResult,reportDate,reportTime) {
	var self=this;
	var curReportDate = reportDate;
	var curReportTime = reportTime;
	var resultHeight=0;//for calculating the height of tests to include in one page
	var verifiedUserProvider = new VerifiedUserProvider(); //For getting verified users
	var resultSpecimenProcessor = new ResultSpecimenProcessor(); //specimen process handler	
	var resultArray = []; //storing tests info
	var resultTestPackageArray=[]; //For storing tests which should be in separate pages and testpackages
	var resultSet =[]; //
	var contentset =methodInvoker.getPageSettingByKey('content');
	var originalHeight = parseInt(contentset.height,10) || 0;
	var marginHeight = parseInt(contentset.marginTop,10) || 0;
	var totalHeight = originalHeight-marginHeight;
	$(testResult).each(function(index, result) {
		var resultVar=result;			
		var layoutStatus = methodInvoker.getPageSettingByKey('general_layout');
		newLayouts=layoutStatus.visible;
		if(newLayouts==false){				
			if(resultVar.testLayout=="General" || resultVar.testLayout=="GeneralOne" || resultVar.testLayout=="DC" || resultVar.testLayout=='GTT' || resultVar.testLayout=='Urine' || resultVar.testLayout=='LipidLayout' || resultVar.testLayout=='PT' 
				|| resultVar.testLayout==constants.LAYOUTGENERALMED || resultVar.testLayout=="WidalLayoutMed" || resultVar.testLayout=="UrineLayoutMed" || resultVar.testLayout=="LipidLayoutMed" || resultVar.testLayout=="DCLayoutMed") {
				if(resultVar.layoutHeight)
					resultHeight+=resultVar.layoutHeight;	
				var heightmm = commonMethodInvoker.getmmFromPixel(resultHeight,methodInvoker.getDPI());		
				if(heightmm>totalHeight) {
					//var specimens="";
					var specimens = resultSpecimenProcessor.getSpecimenList('{"testResult":' + JSON.stringify(resultArray)  + '}',"");
					var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(resultArray) + '}',"");
					resultSet.push('{"specimens":"' + specimens + '","testResult":' + JSON.stringify(resultArray) + ',"verifiedUsers":'+JSON.stringify(verifiedUserIds) + ',"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
					resultArray=[];
					resultHeight=resultVar.layoutHeight+25;
				}	
				resultArray.push(result);
			} else
				resultTestPackageArray.push(result);
		}else {
			resultArray.push(result);
		}
	});
	if(resultArray.length>0){
		var specimens="";
		var specimens = resultSpecimenProcessor.getSpecimenList('{"testResult":' + JSON.stringify(resultArray) + '}',"");
		var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(resultArray) + '}',"");
		resultSet.push('{"specimens":"' + specimens + '","testResult":' + JSON.stringify(resultArray) + ',"verifiedUsers":'+JSON.stringify(verifiedUserIds) + ',"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
	}	
	$(resultTestPackageArray).each(function (index, testResult) {
		var specimens="";
		var specimens = resultSpecimenProcessor.getSpecimenList('{"testResult":' + JSON.stringify(testResult) + '}',"");
		var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(testResult) + '}',"");
		resultSet.push('{"specimens":"' + specimens + '","testResult":[' + JSON.stringify(testResult) + '],"verifiedUsers":'+JSON.stringify(verifiedUserIds) + ',"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
	});
	var resultHeader = orderReference.getOrderHeader();
	resultToPrint = '{"resultHeader":' + JSON.stringify(resultHeader) + ',"resultList":[' + resultSet + ']}';
	return resultToPrint;
}
function ResultSpecimenProcessor() {
	this.getSpecimenList = function(result,orderId) {
		result = $.parseJSON(result);
		var specimenArray = [];
		$(result.testResult).each(function (index,testInfo) {
			$(testInfo.specimen).each(function(index1, specimen){
				if(!commonMethodInvoker.isIndexExists(specimenArray,specimen))
					specimenArray.push(specimen);
				return false;
			});	
		});
		return specimenArray;
	}
}
function ResultHeaderProcessor(orderUI) {
	this.orderUI = orderUI;	
	this.getOrderUI = function(){return this.orderUI;}
	this.make = function(result,combinedResult,orderId,honorific,status){
		var self=this;
		var header = $('<div id="resultHeader"/>');
		header.addClass('result_header');

		var headerSetting =methodInvoker.getPageSettingByKey('header');

		header.css('width', (headerSetting.width - headerSetting.marginLeft)+ "mm");
		header.css('height', (headerSetting.height-headerSetting.marginTop)+ "mm");
		header.css('margin-left',headerSetting.marginLeft+ "mm");
		if(parseInt(headerSetting.fontSize)!=0)
			header.css('font-size',headerSetting.fontSize + "px")
			var columnSymbol = $('<div>:</div>');
		columnSymbol.addClass('column_symbol');
		var row = $('<div/>');
		row.addClass('five_sixth');
		row.addClass('column_top');
		var nameCaption = $('<div>Name</div>');
		nameCaption.addClass('column_small');
		row.append(nameCaption);
		row.append(columnSymbol.clone());
		var name = $('<div/>');	
		name.addClass('column_extralarge');
		name.empty().html(self.getValue(result, combinedResult, orderId, honorific,"name"));
		row.append(name);
		var ageCaption=$('<div>Age</div>');
		ageCaption.addClass('column_very_small');
		row.append(ageCaption);
		row.append(columnSymbol.clone());
		var age = $('<div/>');	
		age.addClass('column_medium');
		age.empty().html(self.getValue(result, combinedResult, orderId, honorific,"age"));
		row.append(age);
		header.append(row);
		row = $('<div/>');
		row.addClass('five_sixth');
		row.addClass('column_top');
		var refNoCaption = $('<div>Ref. No</div>');
		refNoCaption.addClass('column_small');
		row.append(refNoCaption);
		row.append(columnSymbol.clone().clone().clone());
		var refNo = $('<div/>');	
		refNo.addClass('column_extralarge');
		refNo.empty().html(self.getValue(result, combinedResult, orderId, honorific,"orderId"));
		row.append(refNo);
		var sexCaption=$('<div>Sex</div>');
		sexCaption.addClass('column_very_small');
		row.append(sexCaption);
		row.append(columnSymbol.clone().clone());
		var sex = $('<div/>');	
		sex.addClass('column_medium');
		sex.empty().html(self.getValue(result, combinedResult, orderId, honorific,"sex"));
		row.append(sex);
		header.append(row);
		var row = $('<div/>');
		row.addClass('five_sixth');
		row.addClass('column_top');
		var refNoCaption = $('<div>Ref. By</div>');
		refNoCaption.addClass('column_small');
		row.append(refNoCaption);
		row.append(columnSymbol.clone());
		var refBy = $('<div/>');
		refBy.addClass('column_very_extralarge');
		refName = self.getValue(result, combinedResult, orderId, honorific,"referredBy");
		if(refName=="")
			refName="&nbsp;";
		refBy.empty().html(refName);
		row.append(refBy);
		header.append(row);
		var row = $('<div/>');
		row.addClass('five_sixth');
		row.addClass('column_top');
		var collectedAtCaption = $('<div>Collected At</div>');
		collectedAtCaption.addClass('column_small');
		row.append(collectedAtCaption);
		row.append(columnSymbol.clone());
		var collectedAt = $('<div/>');	
		collectedAt.addClass('column_large');
		collectedAt.empty().html(self.getValue(result, combinedResult, orderId, honorific,"collectedAt"));
		row.append(collectedAt);
		var collectDateCaption=$('<div>Collection Date</div>');
		collectDateCaption.addClass('column_small_medium');
		row.append(collectDateCaption);
		row.append(columnSymbol.clone());
		var collectDate = $('<div/>');
		collectDate.addClass('column_medium');
		collectDate.empty().html(self.getValue(result, combinedResult, orderId, honorific,"collectionDate"));
		row.append(collectDate);
		var collectTimeCaption=$('<div>Time</div>');
		collectTimeCaption.addClass('column_very_small');
		row.append(collectTimeCaption);
		row.append(columnSymbol.clone());
		var collectionTime = $('<div/>');	
		collectionTime.addClass('column_medium');
		collectionTime.empty().html(self.getValue(result, combinedResult, orderId, honorific,"collectionTime"));
		row.append(collectionTime);
		header.append(row);
		var row = $('<div/>');
		row.addClass('five_sixth');
		row.addClass('column_top');
		var specimenCaption = $('<div>Specimen</div>');
		specimenCaption.addClass('column_small');
		row.append(specimenCaption);
		row.append(columnSymbol.clone());
		var specimen = $('<div/>');	
		specimen.addClass('column_large');
		specimen.empty().html(self.getValue(result, combinedResult, orderId, honorific,"specimen"));
		row.append(specimen);
		var reportDateCaption=$('<div>Reporting Date</div>');
		reportDateCaption.addClass('column_small_medium');
		row.append(reportDateCaption);
		row.append(columnSymbol.clone());
		var reportDate = $('<div/>');	
		reportDate.addClass('column_medium');
		reportDate.empty().html(self.getValue(result, combinedResult, orderId, honorific,"reportingDate"));
		row.append(reportDate);
		var reportTimeCaption=$('<div>Time</div>');
		reportTimeCaption.addClass('column_very_small');
		row.append(reportTimeCaption);
		row.append(columnSymbol);
		var reportTime = $('<div/>');	
		reportTime.addClass('column_medium');
		reportTime.empty().html(self.getValue(result, combinedResult, orderId, honorific,"reportingTime"));
		row.append(reportTime);
		header.append(row);
		return header;
	}
	this.create = function(result,combinedResult,orderId,honorific,status) {
		/*Set the Header Properties*/
		var self=this;
		var headerSetting =methodInvoker.getPageSettingByKey('header');
		var header = $("<div class='resultHeader' />");
		header.css('width', (headerSetting.width - headerSetting.marginLeft)+ "mm");
		header.css('height', headerSetting.height+ "mm");
		header.css('margin-left',headerSetting.marginLeft+ "mm");
		if(parseInt(headerSetting.fontSize)!=0)
			header.css('font-size',headerSetting.fontSize + "px")
			if(parseInt(headerSetting.marginTop)!=0){
				var newDiv = $("<div id='header'/>");
				if(status!=true)
				{
					var imgTag=$('<img src="'+headerSetting.bgImage+'"  style="height:30mm; width:216mm;">');
					newDiv.append(imgTag);
				}
				newDiv.css("height",headerSetting.marginTop+"mm");
				newDiv.css("width",headerSetting.width+"mm");
				header.append(newDiv);
			}
		/*Create the Header Sections*/
		var headerTable = $("<table cellpadding=0 cellspacing=0/>");
		//headerTable.css("height",(headerSetting.height - headerSetting.marginTop));
		var l1c = methodInvoker.getPageSettingByKey('L1C');
		var l1v = methodInvoker.getPageSettingByKey('L1V');
		var m1c = methodInvoker.getPageSettingByKey('M1C');
		var m1v = methodInvoker.getPageSettingByKey('M1V');
		var r1c = methodInvoker.getPageSettingByKey('R1C');
		var r1v = methodInvoker.getPageSettingByKey('R1V');
		if(l1v.label!="select" || m1v.label!="select" || r1v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l1c,l1v,m1c,m1v,r1c,r1v));	
		}
		var l2c = methodInvoker.getPageSettingByKey('L2C');
		var l2v = methodInvoker.getPageSettingByKey('L2V');
		var m2c = methodInvoker.getPageSettingByKey('M2C');
		var m2v = methodInvoker.getPageSettingByKey('M2V');
		var r2c = methodInvoker.getPageSettingByKey('R2C');
		var r2v = methodInvoker.getPageSettingByKey('R2V');
		if(l2v.label!="select" || m2v.label!="select" || r2v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l2c,l2v,m2c,m2v,r2c,r2v));	
		}
		var l3c = methodInvoker.getPageSettingByKey('L3C');
		var l3v = methodInvoker.getPageSettingByKey('L3V');
		var m3c = methodInvoker.getPageSettingByKey('M3C');
		var m3v = methodInvoker.getPageSettingByKey('M3V');
		var r3c = methodInvoker.getPageSettingByKey('R3C');
		var r3v = methodInvoker.getPageSettingByKey('R3V');
		if(l3v.label!="select" || m3v.label!="select" || r3v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l3c,l3v,m3c,m3v,r3c,r3v));	
		}
		var l4c = methodInvoker.getPageSettingByKey('L4C');
		var l4v = methodInvoker.getPageSettingByKey('L4V');
		var m4c = methodInvoker.getPageSettingByKey('M4C');
		var m4v = methodInvoker.getPageSettingByKey('M4V');
		var r4c = methodInvoker.getPageSettingByKey('R4C');
		var r4v = methodInvoker.getPageSettingByKey('R4V');
		if(l4v.label!="select" || m4v.label!="select" || r4v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l4c,l4v,m4c,m4v,r4c,r4v));	
		}
		var l5c = methodInvoker.getPageSettingByKey('L5C');
		var l5v = methodInvoker.getPageSettingByKey('L5V');
		var m5c = methodInvoker.getPageSettingByKey('M5C');
		var m5v = methodInvoker.getPageSettingByKey('M5V');
		var r5c = methodInvoker.getPageSettingByKey('R5C');
		var r5v = methodInvoker.getPageSettingByKey('R5V');
		if(l5v.label!="select" || m5v.label!="select" || r5v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l5c,l5v,m5c,m5v,r5c,r5v));	
		}
		var l6c = methodInvoker.getPageSettingByKey('L6C');
		var l6v = methodInvoker.getPageSettingByKey('L6V');
		var m6c = methodInvoker.getPageSettingByKey('M6C');
		var m6v = methodInvoker.getPageSettingByKey('M6V');
		var r6c = methodInvoker.getPageSettingByKey('R6C');
		var r6v = methodInvoker.getPageSettingByKey('R6V');
		if(l6v.label!="select" || m6v.label!="select" || r6v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l6c,l6v,m6c,m6v,r6c,r6v));	
		}
		header.append(headerTable);
		return header;
	}
	this.setStyles=function(column, settings){
		if(settings.bold==true)
			column.css("font-weight","bold");
		if(settings.italics==true)
			column.css("font-style","italics");
		if(settings.underline==true)
			column.css("text-decoration","underline");
		if(settings.fontSize!="")
			column.css("font-size",settings.fontSize+"px");
		if(settings.width!="")
			column.css("width",settings.width+"mm");
		if(settings.height!="")
			column.css("height",settings.height+"mm");
	}
	this.setStylesWithoutMeasure=function(column, settings){
		if(settings.bold==true)
			column.css("font-weight","bold");
		if(settings.italics==true)
			column.css("font-style","italics");
		if(settings.underline==true)
			column.css("text-decoration","underline");
		if(settings.fontSize!="")
			column.css("font-size",settings.fontSize+"px");
	}
	this.generateRow = function(result, combinedResult, orderId, honorific, lnc, lnv, mnc, mnv, rnc, rnv){
		var self=this;
		var row = $("<tr/>");
		var columnSpan=1;
		if(mnc.label=='' && mnv.label=='blank')
			columnSpan+=2;
		if(columnSpan!=1){
			var lnccolumn = $("<td>&nbsp;</td>");
			self.setStyles(lnccolumn, lnc);
			//if(lnc.visible!=false && lnc.label!="") 
			//	lnccolumn.empty().html(lnc.label);
			lnccolumn.empty().html(lnc.label);
			row.append(lnccolumn);
			var lnvcolumn = $("<td colspan='3'/>");
			self.setStyles(lnvcolumn, lnv);
			self.setStylesWithoutMeasure(lnvcolumn, lnv);
			if(lnv.visible!=false && (lnv.label!="blank" || lnv.label!="select" )){
				var refName = self.getValue(result, combinedResult, orderId, honorific,lnv.label);
				if(refName!=undefined)
					if(refName.length>50)
						lnvcolumn.css('font-size','13px');
				lnvcolumn.empty().html(refName);
			}
			row.append(lnvcolumn);
		} else {
			var lnccolumn = $("<td>&nbsp;</td>");
			self.setStyles(lnccolumn, lnc);
			//if(lnc.visible!=false && lnc.label!="") 
			lnccolumn.empty().html(lnc.label);
			row.append(lnccolumn);
			var lnvcolumn = $("<td>&nbsp;</td>");
			self.setStyles(lnvcolumn, lnv);
			if(lnv.visible!=false && (lnv.label!="blank" || lnv.label!="select" ))
				lnvcolumn.empty().html(self.getValue(result, combinedResult, orderId, honorific,lnv.label));
			row.append(lnvcolumn);
			var mnccolumn = $("<td>&nbsp;</td>");
			self.setStyles(mnccolumn, mnc);
			//if(mnc.visible!=false && mnc.label!="") 
			mnccolumn.empty().html(mnc.label);
			row.append(mnccolumn);
			var mnvcolumn = $("<td>&nbsp;</td>");
			self.setStyles(mnvcolumn, mnv);
			if(mnv.visible!=false && (mnv.label!="blank" || mnv.label!="select" ))
				mnvcolumn.empty().html(self.getValue(result, combinedResult, orderId, honorific,mnv.label));
			row.append(mnvcolumn);
		}
		var rnccolumn = $("<td>&nbsp;</td>");
		self.setStyles(rnccolumn, rnc);
		//if(rnc.visible!=false && rnc.label!="") 
		rnccolumn.empty().html(rnc.label);
		row.append(rnccolumn);
		var rnvcolumn = $("<td>&nbsp;</td>");
		self.setStyles(rnvcolumn, rnv);
		if(rnv.visible!=false && (rnv.label!="blank" || rnv.label!="select" ))
			rnvcolumn.empty().html(self.getValue(result, combinedResult, orderId, honorific,rnv.label));
		row.append(rnvcolumn);
		return row;	
	}
	this.getValue = function(result, combinedResult, orderId, honorific, key ) {
		if(key=='name')
			return combinedResult.resultHeader.patientName;
		if(key=='age') {
			curAge = "";
			if(combinedResult.resultHeader.age) {
				var age = combinedResult.resultHeader.age;
				var tempAge = age.split("-");
				if(tempAge.length>1){
					curYear = parseFloat(tempAge[0],10)||0;
					curMonth = parseFloat(tempAge[1],10)||0;
					curDay = parseFloat(tempAge[2],10)||0;
					if(curYear!=0)
						curAge = curYear + "Y ";
					if(curMonth!=0)
						curAge = curAge + curMonth + "M ";
					if(curDay!=0)
						curAge = curAge + curDay + "D";	
				} else
					curAge = tempAge[0];
			}	
			return curAge;	
		}	
		if(key=='orderId')
			return combinedResult.resultHeader.orderId;
		if(key=='referredBy')
			return combinedResult.resultHeader.referral;
		if(key=='sex')
			return combinedResult.resultHeader.gender;
		if(key=='collectedAt'){
			if(combinedResult.resultHeader.collectedAt) {
				if(combinedResult.resultHeader.collectedAt!=null && combinedResult.resultHeader.collectedAt!='null')
					return combinedResult.resultHeader.collectedAt;
			}
			return "";
		}
		if(key=='collectionDate'){
			if(combinedResult.resultHeader.date)
				return combinedResult.resultHeader.date;
			return "";
		}
		if(key=='collectionTime'){
			if(combinedResult.resultHeader.time)
				return combinedResult.resultHeader.time;
			return "";
		}
		if(key=='reportingDate') {
			if(result!=null)
				if(result.reportDate)
					return result.reportDate;
			return "";
		}
		if(key=='reportingTime') {
			if(result!=null)
				if(result.reportTime)
					return result.reportTime;
			return "";
		}
		if(key=='specimen'){
			//var resultSpecimenProcessor = new ResultSpecimenProcessor();
			if(result!=null){	
				if(result.specimens)
					return (' ' + result.specimens + ' ');
				else {
					//var specimenArray=resultSpecimenProcessor.getSpecimenList(JSON.stringify(result),orderId);
					var specimenArray=[];
					if(specimenArray.length==0)
						return "";
					else {
						specimenName="";
						$(specimenArray).each(function(index,specimen) {
							if(specimenName=="")
								specimenName=specimen;
							else
								specimenName+=','+ specimen;
						});
						return (' ' + specimenName + ' ');
					}	
				}
			}
		}
	}	
	this.generate = function(result,combinedResult,orderId,honorific) {
		var self=this;
		var headerSetting =methodInvoker.getPageSettingByKey('header');
		var header = $("<div class='resultHeader' />");
		//header.css('width', (headerSetting.width - headerSetting.marginLeft)+ "mm");
		header.css('width', headerSetting.width+"mm");
		header.css('height', headerSetting.height+ "mm");
		header.css('margin-left',headerSetting.marginLeft+ "mm");
		if(parseInt(headerSetting.fontSize)!=0)
			header.css('font-size',headerSetting.fontSize + "px")
			if(parseInt(headerSetting.marginTop)!=0){
				var newDiv = $("<div>&nbsp;</div>");
				newDiv.append(" ");
				newDiv.css("height",headerSetting.marginTop+"mm");
				newDiv.css("width",headerSetting.width+"mm");
				header.append(newDiv);
			}
		var l1c = methodInvoker.getPageSettingByKey('L1C');
		var l1v = methodInvoker.getPageSettingByKey('L1V');
		var m1c = methodInvoker.getPageSettingByKey('M1C');
		var m1v = methodInvoker.getPageSettingByKey('M1V');
		var r1c = methodInvoker.getPageSettingByKey('R1C');
		var r1v = methodInvoker.getPageSettingByKey('R1V');
		header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l1c,l1v,m1c,m1v,r1c,r1v));
		var l2c = methodInvoker.getPageSettingByKey('L2C');
		var l2v = methodInvoker.getPageSettingByKey('L2V');
		var m2c = methodInvoker.getPageSettingByKey('M2C');
		var m2v = methodInvoker.getPageSettingByKey('M2V');
		var r2c = methodInvoker.getPageSettingByKey('R2C');
		var r2v = methodInvoker.getPageSettingByKey('R2V');
		if(l2v.label!="select" || m2v.label!="select" || r2v.label!="select"){
			header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l2c,l2v,m2c,m2v,r2c,r2v));	
		}
		var l3c = methodInvoker.getPageSettingByKey('L3C');
		var l3v = methodInvoker.getPageSettingByKey('L3V');
		var m3c = methodInvoker.getPageSettingByKey('M3C');
		var m3v = methodInvoker.getPageSettingByKey('M3V');
		var r3c = methodInvoker.getPageSettingByKey('R3C');
		var r3v = methodInvoker.getPageSettingByKey('R3V');
		if(l3v.label!="select" || m3v.label!="select" || r3v.label!="select"){
			header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l3c,l3v,m3c,m3v,r3c,r3v));	
		}
		var l4c = methodInvoker.getPageSettingByKey('L4C');
		var l4v = methodInvoker.getPageSettingByKey('L4V');
		var m4c = methodInvoker.getPageSettingByKey('M4C');
		var m4v = methodInvoker.getPageSettingByKey('M4V');
		var r4c = methodInvoker.getPageSettingByKey('R4C');
		var r4v = methodInvoker.getPageSettingByKey('R4V');
		if(l4v.label!="select" || m4v.label!="select" || r4v.label!="select"){
			header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l4c,l4v,m4c,m4v,r4c,r4v));	
		}
		var l5c = methodInvoker.getPageSettingByKey('L5C');
		var l5v = methodInvoker.getPageSettingByKey('L5V');
		var m5c = methodInvoker.getPageSettingByKey('M5C');
		var m5v = methodInvoker.getPageSettingByKey('M5V');
		var r5c = methodInvoker.getPageSettingByKey('R5C');
		var r5v = methodInvoker.getPageSettingByKey('R5V');
		if(l5v.label!="select" || m5v.label!="select" || r5v.label!="select"){
			header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l5c,l5v,m5c,m5v,r5c,r5v));	
		}
		var l6c = methodInvoker.getPageSettingByKey('L6C');
		var l6v = methodInvoker.getPageSettingByKey('L6V');
		var m6c = methodInvoker.getPageSettingByKey('M6C');
		var m6v = methodInvoker.getPageSettingByKey('M6V');
		var r6c = methodInvoker.getPageSettingByKey('R6C');
		var r6v = methodInvoker.getPageSettingByKey('R6V');
		if(l6v.label!="select" || m6v.label!="select" || r6v.label!="select"){
			header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l6c,l6v,m6c,m6v,r6c,r6v));	
		}

		return header;
	}
	this.generateRowNew = function(result, combinedResult, orderId, honorific, lnc, lnv, mnc, mnv, rnc, rnv){
		var self=this;
		var row="";
		var curwidth=0;
		var curcol = "";
		if(lnv.label!='blank' || mnv.label!="blank" || rnv.label!="blank" || 
				lnc.label.trim()!="" || mnc.label.trim()!="" || rnc.label.trim()!=""){
			row=$('<div class="oneHeader" >&nbsp;</div>');
			var column = $('<div class="columnleftalign">&nbsp;</div>');
			column.append("Name");
			self.setStyles(column,lnc);
			curwidth = parseInt(lnc.width),10||0;
			curcol = column;
			row.append(column);
			if(lnv.visible!=false){
				var column = $('<div class="columnleftalign">&nbsp;</div>');
				column.append(self.getValue(result, combinedResult, orderId, honorific, lnv.label));
				self.setStyles(column,lnv);
				curwidth = parseInt(lnv.width);
				curcol=column;
				row.append(column);
			} else {
				curwidth+=parseInt(lnv.width);
				curcol.css('width',curwidth+"mm");
			}
			if(mnc.visible!=false){
				var column = $('<div class="columnleftalign">&nbsp;</div>');
				column.append(mnc.label);
				self.setStyles(column,mnc);
				curwidth = parseInt(mnc.width);
				curcol=column;
				row.append(column);
			} else {
				curwidth+=parseInt(mnc.width);
				curcol.css('width',curwidth+"mm");
			}
			if(mnv.visible!=false){
				var column = $('<div class="columnleftalign">&nbsp;</div>');
				column.append(self.getValue(result, combinedResult, orderId, honorific, mnv.label));
				self.setStyles(column,mnv);
				curwidth = parseInt(mnv.width);
				curcol=column;
				row.append(column);
			} else {
				curwidth+=parseInt(mnv.width);
				curcol.css('width',curwidth+"mm");
			}
			if(rnc.visible!=false){
				var column = $('<div class="columnleftalign">&nbsp;</div>');
				column.append(rnc.label);
				self.setStyles(column,rnc);
				curwidth = parseInt(rnc.width);
				curcol=column;
				row.append(column);
			} else {
				curwidth+=parseInt(rnc.width);
				curcol.css('width',curwidth+"mm");
			}
			if(rnv.visible!=false){
				var column = $('<div class="columnleftalign">&nbsp;</div>');
				column.append(self.getValue(result, combinedResult, orderId, honorific, rnv.label));
				self.setStyles(column,rnv);
				curwidth = parseInt(rnv.width);
				curcol=column;
				row.append(column);
			} else {
				curwidth+=parseInt(rnv.width);
				curcol.css('width',curwidth+"mm");
			}
		}
		return row;
	}
}
function ResultFooterProcessor(orderUI) {
	this.create = function(result,combinedResult,orderId) {
		var self=this;
		var footer = $('<div class="printReportFooter " />');
		var headerSetting =methodInvoker.getPageSettingByKey('footer');
		var headerHeight = methodInvoker.getPageSettingByKey('header').marginTop;
		var signSetting = methodInvoker.getPageSettingByKey('USIGN');
		footer.css('width', headerSetting.width+ "mm");
		footer.css('height',(parseInt(headerSetting.height)+parseInt(headerHeight))+ "mm");
		var linehgt = headerSetting.height/5;
		if(headerSetting.visible==false) {
			footer.append('&nbsp;');
		} else {
			var tblFooter = $('<table style="width:100%; height:100%;vertical-align:bottom; float:left;"/>');
			trTag = $('<tr/>');
			tdTag = $('<td style="vertical-align:top"/>');
			var noOfUsers = result.verifiedUsers.length; 
			$(result.verifiedUsers).each(function(index, user) {	
				var userInfo=query.viewUser(user.userId, orderUI.getBranchId());
				if(noOfUsers==2) {
					if(index==0){
						divTag = $('<div style="width:46%;float:left;margin-left:2%" />');
					}else{
						divTag = $('<div style="width:46%;float:right;margin-right:2%;text-align:right" />');
					}
					var signTag=$('<div>&nbsp;</div>');
					if(userInfo.signature && signSetting.visible==true) {
						signTag.html('<img src=' + userInfo.signature + ' width="200" height="40" style="margin-left:5%;"/>');
					}	
					divTag.append(signTag);
					var childTag = $('<div/>');
					childTag.html(user.name);
					divTag.append(childTag);
					if(user.designation) {
						childTag = $('<div/>');
						childTag.html(user.designation);
						divTag.append(childTag);
					}
					divTag.attr('height',(linehgt*3)+"mm");	
					tdTag.append(divTag);
				}else {
					if(index==0)
						divTag = $('<div style="min-width:31%;float:left;margin-left:1%;text-align:left" />')
						else if(index==1)
							divTag = $('<div style="min-width:31%;float:left;margin-left:1%;text-align:center" />')
							else
								divTag = $('<div style="min-width:31%;float:right;margin-right:1%;text-align:right" />')

								var childTag = $('<div/>');
					var signTag=$('<div>&nbsp;</div>');
					if(userInfo.signature  && signSetting.visible==true) {
						signTag.html('<img src=' + userInfo.signature + ' width="200" height="40" style="margin-left:5%;"/>');
					}
					divTag.append(signTag);
					childTag.html(user.name);
					divTag.append(childTag);
					if(user.designation) {
						childTag = $('<div/>');
						childTag.html(user.designation);
						divTag.append(childTag);
					}	
					divTag.attr('height',(linehgt*3)+"mm");
					tdTag.append(divTag);
					if(index==2)
						return false;
				}
			});
			var f1l = methodInvoker.getPageSettingByKey('F1L');
			var f1m = methodInvoker.getPageSettingByKey('F1M');
			var f1r = methodInvoker.getPageSettingByKey('F1R');
			var f2l = methodInvoker.getPageSettingByKey('F2L');
			var f2m = methodInvoker.getPageSettingByKey('F2M');
			var f2r = methodInvoker.getPageSettingByKey('F2R');

			if(f1l.label.trim()=="" && f1m.label.trim()=="" && f1r.label.trim()==""){

			} else {
				var f1Div  = $('<div class="left oneHeader" style="clear:both; margin-left:0.5%;text-align:left"/>');
				f1Div.attr('height',linehgt+"mm");
				var f1 = $('<div class="onethird">&nbsp;</div>')
				if(f1l.label.trim()!=""){
					f1.append(f1l.label);
					self.setStyles(f1, f1l);
				}
				f1Div.append(f1);
				var f2 = $('<div class="onethird">&nbsp;</div>')
				if(f1m.label.trim()!=""){
					f2.append(f1m.label);
					self.setStyles(f2, f1m);
				}
				f1Div.append(f2);
				var f3 = $('<div class="onethird ">&nbsp;</div>')
				if(f1r.label.trim()!=""){
					f3.append(f1r.label);
					self.setStyles(f3, f1r);
				}
				f1Div.append(f3);
				tdTag.append(f1Div);
			}
			if(f2l.label.trim()=="" && f2m.label.trim()=="" && f2r.label.trim()==""){

			} else {
				var f2Div  = $('<div class="left oneHeader" style="clear:both;margin-left:0.5%;text-align:left"/>');
				f2Div.attr('height',linehgt+"mm");
				var f1 = $('<div class="onethird">&nbsp;</div>')
				if(f2l.label.trim()!=""){
					f1.append(f2l.label);
					self.setStyles(f1, f2l);
				}
				f2Div.append(f1);
				var f2 = $('<div class="onethird">&nbsp;</div>')
				if(f2m.label.trim()!=""){
					f2.append(f2m.label);
					self.setStyles(f2, f2m);
				}
				f2Div.append(f2);
				var f3 = $('<div class="onethird ">&nbsp;</div>')
				if(f2r.label.trim()!=""){
					f3.append(f2r.label);
					self.setStyles(f3, f2r);
				}
				f2Div.append(f3);
				tdTag.append(f2Div);
			}
			trTag.append(tdTag);
			tblFooter.append(trTag);
			footer.append(tblFooter);
		}
		return footer;
	}
	this.setStyles=function(column, settings){
		if(settings.bold==true)
			column.css("font-weight","bold");
		if(settings.italics==true)
			column.css("font-style","italic");
		if(settings.underline==true)
			column.css("text-decoration","underline");
		if(settings.fontSize!="")
			column.css("font-size",settings.fontSize+"px");
		if(settings.width!="")
			column.css("width",settings.width+"mm");
		if(settings.height!="")
			column.css("height",settings.height+"mm");
	}
	this.generalFooter = function() {
		var self=this;
		var footer = $('<div class="printReportFooter " style="vertical-align:bottom;"/>');
		var headerSetting =methodInvoker.getPageSettingByKey('footer');
		footer.css('width', headerSetting.width+ "mm");
		footer.css('height',headerSetting.height+ "mm");
		var f1l = methodInvoker.getPageSettingByKey('F1L');
		var f1m = methodInvoker.getPageSettingByKey('F1M');
		var f1r = methodInvoker.getPageSettingByKey('F1R');
		var f2l = methodInvoker.getPageSettingByKey('F2L');
		var f2m = methodInvoker.getPageSettingByKey('F2M');
		var f2r = methodInvoker.getPageSettingByKey('F2R');
		var f1Div  = $('<div class="left"/>');
		if(f1l.label.trim()=="" && f1m.label.trim()=="" && f1r.label.trim()){

		} else {
			var f1 = $('<div class="onethird">&nbsp;</div>')
			if(f1l.label.trim()!=""){
				f1.append(f1l.label);
				self.setStyles(f1, f1l);
			}
			f1Div.append(f1);
			var f2 = $('<div class="onethird">&nbsp;</div>')
			if(f1m.label.trim()!=""){
				f2.append(f1m.label);
				self.setStyles(f2, f1m);
			}
			f1Div.append(f2);
			var f3 = $('<div class="onethird ">&nbsp;</div>')
			if(f1r.label.trim()!=""){
				f3.append(f1r.label);
				self.setStyles(f3, f1r);
			}
			f1Div.append(f3);
		}
		footer.append(f1Div);
		var f2Div  = $('<div class="left"/>');
		if(f2l.label.trim()=="" && f2m.label.trim()=="" && f2r.label.trim()){

		} else {
			var f1 = $('<div class="onethird">&nbsp;</div>')
			if(f2l.label.trim()!=""){
				f1.append(f2l.label);
				self.setStyles(f1, f2l);
			}
			f2Div.append(f1);
			var f2 = $('<div class="onethird">&nbsp;</div>')
			if(f2m.label.trim()!=""){
				f2.append(f2m.label);
				self.setStyles(f2, f2m);
			}
			f2Div.append(f2);
			var f3 = $('<div class="onethird ">&nbsp;</div>')
			if(f2r.label.trim()!=""){
				f3.append(f2r.label);
				self.setStyles(f3, f2r);
			}
			f2Div.append(f3);
		}
		footer.append(f2Div);
		return footer;
	}
}
function ResultPrintHandler() {
	this.generateHtmlString = function(result) {
		var strHtml = '<html><head>'; 
		strHtml += "<style>";
		strHtml += "<!--";
		strHtml += ".groupResultTable{font-family:'arial';page-break-after:always; padding:none; margin:none;} ";
		strHtml += ".page{font-family:'arial';page-break-after:always; padding:none; margin:none;} ";
		strHtml += ".one {width:100%;}";
		strHtml += ".nomargin{margin:0px}";
		strHtml += ".singleheight {height:6.5mm;}";
		strHtml += ".oneHeader{width:100%}";
		strHtml += ".onethird {width:33%;float:left}";
		strHtml += ".onesecond {width:30%;float:left}";
		strHtml += ".onefirst{width:39%;float:left}";
		strHtml += ".onefive {width:20%;float:left;}";
		strHtml += ".onefourth {width:15%;float:left;}";
		//strHtml += ".remarks { margin:auto; width:90%}";
		strHtml += ".column-2{text-align:right}";
		strHtml += ".middle{text-align:center}";
		strHtml += ".bold {font-weight:bold}";
		strHtml += ".underline{text-decoration:underline}";
		strHtml += ".columnleftalign{float:left;}";
		strHtml += ".one_first_column {width:17%}";
		strHtml += ".one_second_column{width:5%}";
		strHtml += ".one_third_column{width:77%}";
		strHtml += "#foodAllergy {font-size:12px}";
		strHtml += ".foodallergy_onethird_border {border:1px solid}";
		strHtml += "#foodAllergy .one_third_column{font-size:11px}";
		strHtml += ".smallheight {line-height:15px;}";
		strHtml += ".column_height {line-height:15mm;}";
		strHtml += ".normal_height{line-height:7mm}";
		strHtml += ".column_symbol {float:left; width:1%}";
		strHtml += ".column_very_small {float:left;width:5%}";
		strHtml += ".column_small {float:left;width:12%}";
		strHtml += ".column_small_medium {float:left;width:14%}";
		strHtml += ".column_medium {float:left;width:15%}";
		strHtml += ".column_medium_extra {float:left;width:20%}";
		strHtml += ".column_large {float:left;width:36%}";
		strHtml += ".result_header {font-size:15}";
		strHtml += ".column_extralarge{float:left;width:66%}";
		strHtml += ".column_very_extralarge{float:left;width:87%}";
		strHtml += ".column_top {padding-top:3%}";
		var generalLayoutset =methodInvoker.getPageSettingByKey('general_layout');
		strHtml += ".printresultTable {";
		if(generalLayoutset.border==true) {
			strHtml += "	border:1px solid #000;";
			strHtml += "	margin:auto;";
			strHtml += "	border-spacing:15px;";
			strHtml += "	border-collapse:collapse;";
		}
		//strHtml += "	line-height:25px;";
		//strHtml += "	font-size:16px;";
		strHtml += "}";
		if(generalLayoutset.border==true) {
			strHtml += ".printresultTable thead tr th {border:1px solid #000; line-height:25px;}";
			strHtml += ".printresultTable tbody tr td{border:1px solid #000;line-height:25px;}";
			strHtml += ".printresultTable table tbody tr td{border:none;}";
		}
		strHtml += "input.readonlyStyle {";
		strHtml += "	background-color:#FAFAFA;";
		strHtml += "	border-style:none;";
		strHtml += "	border-width:0px;";
		strHtml += "	color:#000;";
		strHtml += "}";
		strHtml += ".resultTitle{ font-weight:bold; font-size:17px;margin-bottom:5px;}";
		strHtml += "#cultureresultTable{";
		strHtml += "	border:1px solid #000;";
		strHtml += "	width:95%;";
		strHtml += "	margin:auto;";
		strHtml += "	border-spacing:15px;";
		strHtml += "	border-collapse:collapse;";
		strHtml += "	line-height:15px;";
		strHtml += "	font-size:90%";
		strHtml += "}";
		strHtml += "#cultureresultTable thead tr th {border:1px solid #000;}";
		strHtml += "#cultureresultTable tbody tr td{border:1px solid #000; line-height:19px;}";
		strHtml += "#cultureresultTable  table tbody tr td{border:1px solid #000;}";
		strHtml += "#cultureresultTable  td{padding:none;margin:none; line-height:19px;}";
		strHtml += ".cultureTable_Head thead tr th {border:0px solid #000; font-size:16px;}";
		strHtml += ".cultureTable_Head tbody tr td{border:0px solid #000;}";

		strHtml += "input.readonlybox {";
		strHtml += "	background:none;";
		strHtml += "	border:none;";
		strHtml += "	color:#000;";
		strHtml += "	padding:0px;";
		strHtml += "	margin:0px;";
		strHtml += "	width:50%;";
		strHtml += "	text-align:right;	";
		strHtml += "}";
		strHtml += "input.plainText {";
		strHtml += "	width:90%;";
		strHtml += "	background-color:#FAFAFA;";
		strHtml += "	border-style:none;";
		strHtml += "	border-width:0px;";
		strHtml += "	color:#000;";
		strHtml += "}";
		strHtml += "input.middle{";
		strHtml += "	text-align:center;";
		strHtml += "}";
		strHtml += "#realTable{";
		strHtml += "	border:1px solid #000;";
		strHtml += "	width:100%;";
		strHtml += "	margin:auto;";
		strHtml += "	border-spacing:15px;";
		strHtml += "	border-collapse:collapse;";
		strHtml += "	line-height:21px;font-size:90%;";
		strHtml += "}";
		strHtml += "#realTable thead tr th {border:1px solid #000;font-size:12px;}";
		strHtml += "#realTable tbody tr td{border:1px solid #000;}";
		strHtml += "#realTable  table tbody tr td{border:1px solid #000;}";
		strHtml += "#realTable  td{padding:none;margin:none;}";
		strHtml += "#realTable input.medium { width: 35%;}";

		strHtml += "#SpHgtTable{";
		strHtml += "	border:1px solid #000;";
		strHtml += "	width:100%;";
		strHtml += "	margin:auto;";
		strHtml += "	border-spacing:15px;";
		strHtml += "	border-collapse:collapse;";
		strHtml += "	line-height:25px;";
		strHtml += "}";
		strHtml += "#SpHgtTable thead tr th {border:1px solid #000;}";
		strHtml += "#SpHgtTable tbody tr td{border:1px solid #000;}";
		strHtml += "#SpHgtTable  table tbody tr td{border:1px solid #000;}";
		strHtml += "#SpHgtTable  td{padding-left:1%;margin:none;}";
		strHtml += "#SpHgtTable input.medium { width: 40%;}";
		strHtml += "#SpHgtTable input.part { width: 75%;}";
		strHtml += "#SpHgtTable input.large { width: 100%;}";
		strHtml += "#SpHgtTable td.microscopy {font-weight:bold;font-size:20px;}";
		strHtml += "#SpHgtTable input.mystyle{ width:100%;height:28px;font-size:15px;}";
		strHtml += "#StoneTable input.medium { width: 40%;}";
		strHtml += "#StoneTable input.part { width: 75%;}";
		strHtml += "#SemenTable{";
		strHtml += "	border:1px solid #000;";
		strHtml += "	width:90%;";
		strHtml += "	margin:auto;";
		strHtml += "	border-spacing:15px;";
		strHtml += "	border-collapse:collapse;";
		strHtml += "}";
		strHtml += "";
		strHtml += "#SemenTable thead tr th {border:1px solid #000;}";
		strHtml += "#SemenTable tbody tr td{border:1px ;}";
		strHtml += "#SemenTable  tbody tr {border:1px;}";
		strHtml += "#SemenTable  td{padding:none;margin:none;}";
		strHtml += "#SemenTable input.medium { width:30%;}";
		strHtml += "#SemenTable input.tiny { width:10%;}";
		strHtml += "#SemenTable span {padding-right:5px;}";
		strHtml += "#SemenTable label {padding-right:3px;}";
		strHtml += "";
		strHtml += "#SpHgtTable #NoBorderTable{";
		strHtml += "	border:none;";
		strHtml += "	width:90%;";
		strHtml += "	margin:auto;";
		strHtml += "	border-spacing:15px;";
		strHtml += "	border-collapse:collapse;";
		strHtml += "	line-height:25px;";
		strHtml += "	width:100%;";
		strHtml += "	font-weight:normal;";
		strHtml += "}";
		strHtml += "#SpHgtTable #NoBorderTable thead tr th {border:none;}";
		strHtml += "#SpHgtTable #NoBorderTable tbody tr td{border:none;font-weight:italic}";
		strHtml += "#SpHgtTable #NoBorderTable  tbody tr {border:none;}";
		strHtml += "#SpHgtTable #NoBorderTable  td{padding:none;margin:none;}";
		strHtml += "#SpHgtTable #NoBorderTable input.medium { width:30%;}";
		strHtml += "";
		strHtml += ".column-left { float: left; width: 49%;}";
		strHtml += ".column-first { float: left; width: 38%;}";
		strHtml += ".column-second { float: left; width: 30%;}";
		strHtml += ".column-right {";
		strHtml += "  float: right;";
		strHtml += "  width: 49%;";
		strHtml += "  padding:0 0 0 0px;";
		strHtml += "}";
		strHtml += "#SpHgtTable  table tbody tr td{font-size:16px;}";
		strHtml += "form .column-left, form .column-right { width: 47%;  padding:0 2% 0 0;}";
		strHtml += ".titleClass {";
		strHtml += "	font-size:16px;";
		strHtml += "	font-weight:bold;";
		strHtml += "	text-align:center;";
		strHtml += "	line-height:25px;";
		strHtml += "	text-decoration: underline; ";
		strHtml += "}";
		strHtml += ".topAlign {vertical-align:top;}";
		strHtml += "table .noBorder {border:none;}";
		strHtml += "table .noBorder tbody tr td{border:none;}";
		strHtml += ".remarks p { margin-top:0px;margin-bottom:0px;}";
		strHtml += "--></style>";
		strHtml+='</head><body><div id="printDiv">';
		strHtml+= result + '</div></body></html>';
		return strHtml;
	}	
	this.print=function(htmlString) {
		var WindowObject = window.open('', 'PrintWindow', 'width=1000,height=650,top=50,left=50,toolbars=no,scrollbars=yes,status=no,resizable=yes');
		var writestat = WindowObject.document.writeln(htmlString);
		WindowObject.focus();
		WindowObject.print();
		WindowObject.document.close();
		WindowObject.close();
		return;
	}
}