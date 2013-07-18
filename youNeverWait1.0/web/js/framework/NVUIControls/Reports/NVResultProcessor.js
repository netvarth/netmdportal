function getContent(result) {
	this.result = function () {
		//Fill Data values
		var tmpTable = $j('<table/>');
		
		$j(result.analysis).each(function(index,analysis) {
			var tmpHead=result.testName + "-";
			$j(analysis.resultContent).each(function(index1,rescont) {
				var tbodyTag = $j('<tbody/>');
				$j(rescont.resultParams).each(function(index2,resparam) {
					var trTag=$j('<tr/>');
					var tdTag = $j('<td/>');
					if(resparam.key) {
						tmpHead +=resparam.key;
						tdTag.append(tmpHead);
						tmpHead="";
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
										if(elemnt.value)
											elmnTag.attr('value',elemnt.value);
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
										if(elemnt.value)
											elmnTag.attr('value',elemnt.value);
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
									elemContainerTag.append("/"+elem.unit);
								}else {
									elemContainerTag.append("&nbsp;");
								}
								tdTag.append(elemContainerTag);								
							});													
						}else {	
							var elemntContainerTag=$j('<div width:30%; style="float:left;"/>');
							if(elemnt.displayType=="text") {
								elmnTag = $j('<input type="text"/>');
								if(elemnt.value)
									elmnTag.attr('value',elemnt.value);
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
								if(elemnt.value)
									elmnTag.attr('value',elemnt.value);
								elemntContainerTag.append(elmnTag);
								tdTag.append(elemntContainerTag);
							}							
							var divUnitTag = $j('<div/>');
							if(elemnt.unit){									
								divUnitTag.html("/"+elemnt.unit);
							}else {
								divUnitTag.html("&nbsp;");
							}								
							tdTag.append(divUnitTag);
						}	
						trTag.append(tdTag);						
					});	
					tbodyTag.append(trTag);
					tmpTable.append(tbodyTag);
				});
			});	
		});
		return tmpTable.html();
	};


}