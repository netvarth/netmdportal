function layout2(result) {

this.result =	function(){ 
	var content = $j('<div>');
	var headerDiv = $j('<div style="text-align:middle;"/>');
	var headerContent=$j('<h3 align="center" style="text-decoration:underline;padding:10px 0 10px 0px"/>');
	headerContent.html(result.testName);
	headerDiv.append(headerContent);
	content.append(headerDiv);
	var resultTable = $j('<table class="stdtable"/>');
	if(result.analysis){
		$j(result.analysis).each(function(index,analysis) {
			$j(analysis.resultContent).each(function(index1,rescont) {
				var theadTag = $j('<thead/>');
				var trTag=$j('<tr/>');		
				$j(rescont.resultParams).each(function(index2,resparam) {				
					var thTag = $j('<th class="head1"/>');
					if(resparam.headerLabel)
						thTag.append(resparam.headerLabel);
					trTag.append(thTag);
					
					$j(resparam.values).each(function(index2,elemnt) {
						var thTag = $j('<th class="head1"/>');
						if(elemnt.headerLabel){	
							thTag.append(elemnt.headerLabel);
						}
						trTag.append(thTag);	
					});
					theadTag.append(trTag);
					resultTable.append(theadTag);
					return false;
				});
			});	
		});
		
		//Fill Data values
	/*	$j(result.analysis).each(function(index,analysis) {
			var testName = result.testName + "-";
			$j(analysis.resultContent).each(function(index1,rescont) {
				var tbodyTag = $j('<tbody/>');
				
				$j(rescont.resultParams).each(function(index2,resparam) {
					var trTag=$j('<tr/>');
					var tdTag = $j('<td/>');
					if(resparam.key){
						tdTag.append( testName +  resparam.key);
						testName ="";
					}	
					else
						tdTag.append(result.testName);
						
					trTag.append(tdTag);
					$j(resparam.values).each(function(index2,elemnt) {
						tdTag = $j('<td/>');
						
						if(elemnt.values){
							$j(elemnt.values).each(function(index2,elem) {
								var elemContainerTag=$j('<div/>');
								
								if(elem.normalRangeLabel) {
									elemContainerTag.append(elem.normalRangeLabel);
								}
								if(elem.displayType){																	
									if(elem.displayType=="text") {
										elmnTag = $j('<input type="text"/>');
										elemContainerTag.append(elmnTag);
									}
									if(elem.displayType=="select") {
										elmnTag = $j('<select/>');
										if(elem.list) {
											$j(elem.list).each(function(indexval,val) {
												var optTag = $j('<option/>')
												optTag.html(val.value);
												elmnTag.append(optTag);
											});
										}
										elemContainerTag.append(elmnTag);																							
									}
								}else {
									elmnTag = $j('<label/>');
									if(elem.value)
										elmnTag.html(elem.value);
									else
										elmnTag.html('&nbsp;');
									elemContainerTag.append(elmnTag);
								}								
								if(elem.unit){									
									elemContainerTag.append(elem.unit);
								}else {
									elemContainerTag.append("&nbsp;");
								}
								tdTag.append(elemContainerTag);								
							});													
						}else {	
							var elemntContainerTag=$j('<div width:30%; style="float:left;"/>');
							if(elemnt.displayType=="text") {
								elmnTag = $j('<input type="text"/>');
								elemntContainerTag.append(elmnTag);
								tdTag.append(elemntContainerTag);
							}
							if(elemnt.displayType=="select") {
								elmnTag = $j('<select/>');
								if(elemnt.list) {
									$j(elemnt.list).each(function(indexval,val) {
										var optTag = $j('<option/>')
										optTag.html(val.value);
										elmnTag.append(optTag);
									});
								}
								elemntContainerTag.append(elmnTag);
								tdTag.append(elemntContainerTag);
							}
							var divUnitTag = $j('<div/>');
							if(elemnt.unit){									
								divUnitTag.html(elemnt.unit);
							}else {
								divUnitTag.html("&nbsp;");
							}								
							tdTag.append(divUnitTag);
						}	
						trTag.append(tdTag);						
					});	
					tbodyTag.append(trTag);
					resultTable.append(tbodyTag);
				});
			});	
		});*/

	//	content.append(resultTable);
	}
	$j.getScript("/weblims/js/NVUIControls/Reports/NVResultProcessor.js").done(function(script, textStatus) {			
			var cont = new getContent(result);
			resultTable.append(cont.result);
	});
	content.append(resultTable);	
	return content;
};	
}