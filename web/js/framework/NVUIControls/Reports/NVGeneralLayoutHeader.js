function generalLayoutHeader(result,honorific) {
	this.result =	function(){ 
		var theadTag = $j('<thead/>');
		if(result.analysis){
			$j(result.analysis).each(function(index,analysis) {
				$j(analysis.resultContent).each(function(index1,rescont) {					
					var trTag=$j('<tr/>');		
					$j(rescont.resultParams).each(function(index2,resparam) {				
						var thTag = $j('<th style="width:40%" class="head1">INVESTIGATION</th>');
						trTag.append(thTag);	
						$j(resparam.values).each(function(index2,elemnt) {					
							if(honorific=="Animal" && elemnt.headervalue=="NormalRange") {
							}else{
								var thTag = $j('<th class="head1" class="middle"/>');
								if(elemnt.headerLabel){	
									thTag.append(elemnt.headerLabel);
								}
								trTag.append(thTag);	
							}
						});
						theadTag.append(trTag);
						return false;
					});
				});	
			});
		}	
		return theadTag;
	};	
}