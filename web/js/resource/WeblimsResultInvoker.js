function WeblimsResultInvoker() {
	this.getListByName = function(key) {
		ajaxProcessor.setUrl(constants.VIEWSETTINGBYNAMEURL + key);
		var response = ajaxProcessor.get();
		var list=[];
		if(response.success==true) {
			$j(response.setting.settingList).each(function(index, setting){
				list.push(setting.value + " ");
			});
		}
		return list;
	}
	this.FillListToControl = function(key,layout,parent) {
		var autoCompleteArray = this.getListByName(key);
		commonMethodInvoker.makeautoCompleteDefault(key,autoCompleteArray,parent);
	}
	this.FillnumberlistToControl = function(key,layout,parent) {
		var autoCompleteArray = this.getListByName(key);
		var selectfield = "."+key;
		if(parent!=undefined)
			selectfield = parent + " ."+key;
		var integerArray=["1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40"];
    	$j(autoCompleteArray).each(function(index,test){  
			$j(selectfield).append($j("<option />").val(test).text(test));    	     
		});
		$j.each(integerArray,function(i,value){
			$j(selectfield).append($j("<option />").val(value).text(value));  
		});
		return;
	}
	this.FillListToSelectBox = function(key,layout,parent) {
		var selectfield = "."+key;
		if(parent!=undefined)
			selectfield = parent + " ."+key;
		var list =this.getListByName(key);
	    $j(list).each(function(index,test){ 
			$j(selectfield).append($j("<option />").val(test).text(test));     
		});
		return;
	}
}