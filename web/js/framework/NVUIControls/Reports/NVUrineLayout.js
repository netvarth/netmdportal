function UrineLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		//div for report title starts here
		var content =$j('<div align="center"/>');
		var titleSection = $j('<div align="center" class="resultTitle">'+result.testName + '</div>');
		content.append(titleSection);
			
		var tableTag=$j('<table id="SpHgtTable" width="100%"/>');
		var tbodyTag=$j('<tbody/>');
		
		$j(result.values).each(function(index,values){
			var trTag=$j('<tr/>');
			var tdTag=$j('<td style="width:30%">'+values.testnameval+'</td>'); //first Column
			trTag.append(tdTag);
			tdTag=$j('<td></td>'); 
			
			if(mode=='print') {
				var lblTag = $j('<label/>');
				lblTag.html(values.value1);
				tdTag.append(lblTag);
			} else {
				if(values.displayType1=="text")
					var inputTag=$j('<input type="text" id="US_'+ index +'_11" name="US_'+ index +'_1" value="'+values.value1+'" tabIndex=' +values.tabIndex1 + '/>');
				if(values.displayType1=="select")
					var inputTag=$j('<select id="US_'+ index +'_11" name="US_'+ index +'_1" value="'+values.value1+'" tabIndex=' +values.tabIndex1 +'/>');	
				if(values.className1)
					inputTag.attr('class',values.className1);
				tdTag.append(inputTag);
				
			}
			trTag.append(tdTag);
			
			if(index=='0'){
				tdTag=$j('<td colspan="2" style="text-align:center;">'+values.testnameval2+'</td>');
				trTag.append(tdTag);
			} else { // index !=0
				if(values.testnameval2==""){
					tdTag=$j('<td style="width:20%"/>');
					
					if(mode=='print') {
						var lblTag = $j('<label/>');
						lblTag.html(values.editField);
						tdTag.append(lblTag);
					} else {
						var inputTag=$j('<input type="text" id="US_'+ index +'_02" value="'+values.editField+'"/>');
						tdTag.append(inputTag);
					} 
					 
				} else
					tdTag=$j('<td style="width:30%">'+values.testnameval2+'</td>');
				
				trTag.append(tdTag);
				
				tdTag=$j('<td style="width:20%"/>');
				
				if(mode=='print') {
					var lblTag = $j('<label/>');
					lblTag.html(values.value2);
					tdTag.append(lblTag);
				} else {
					if(values.displayType2=="text")
						var inputTag=$j('<input type="text" id="US_'+ index +'_12" value="'+values.value2+'" tabIndex=' +values.tabIndex2 +'/>');
				    if(values.displayType2=="select")
						var inputTag=$j('<select id="US_'+ index +'_12" value="'+values.value2+'" tabIndex=' +values.tabIndex2 +'/>');
						if(values.className2)
							inputTag.attr('class',values.className2);
						tdTag.append(inputTag);
							
				}
				trTag.append(tdTag);	
			}
			tbodyTag.append(trTag);
		});
		tableTag.append(tbodyTag);
		content.append(tableTag);
		return content;
	};
}