function Container(contents) {
this.result =	function(){ 
	var contentgroup=contents.result;
	var content = $j('<div>');
		if(contentgroup.resultName){
			var headTag = $j('<h1/>');
			headTag.html(contentgroup.resultName);
			content.append(headTag);
		}
		if(contentgroup.analysis){
			var contentanalysis=$j('<div/>');
			$j(contentgroup.analysis).each(function(index,analysis) {
				if(analysis.type) {
					var headTag=$j('<h2/>');
					headTag.html(analysis.type);
					contentanalysis.append(headTag);
				}
				if(analysis.name){
					$j(analysis.name).each(function(index1,analysisname) {
						if(analysisname.groupName){
							var pTag = $j('<p/>');
							pTag.html(analysisname.groupName);
							contentanalysis.append(pTag);
						}
						if(analysisname.element) {
							$j(analysisname.element).each(function(index2,elemnt) {
								var elementTag=$j('<p/>');
								labelTag = $j('<label/>');
								labelTag.attr('for',elemnt.key);
								labelTag.html(elemnt.key);
								elementTag.append(labelTag);
								if(elemnt.displayType=="text") {
									elmnTag = $j('<input type="text"/>');
									elementTag.append(elmnTag);
								}
								if(elemnt.displayType=="select") {
									elmnTag = $j('<select/>');
									if(elemnt.values) {
										$j(elemnt.values).each(function(indexval,val) {
											var optTag = $j('<option/>')
											optTag.html(val.value);
											elmnTag.append(optTag);
										});
									}
									elementTag.append(elmnTag);
								}
								if(elemnt.unit){
									var spanTag = $j('<span/>');
									spanTag.html(elemnt.unit);
									elementTag.append(spanTag);
								}
								contentanalysis.append(elementTag);
							});
						}
					});
				}
			});
			content.append(contentanalysis);
		}
		return content;
	};
}

