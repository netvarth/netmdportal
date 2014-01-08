function createModal (urlPath,modaldivName,model) {
	var modalobj;
		ajaxProcessor.setUrl(urlPath);
		var dataToCreateModal = ajaxProcessor.get();
	//alert(JSON.stringify(dataToCreateModal));
	modalobj = new Modal(dataToCreateModal,model);
	
	var obj='#' + modaldivName;
	if($j(obj).length>0) 
		$j(obj).html(modalobj.result);
	else{
		var newDiv = $j('<div class="reveal-modal">');
		if(dataToCreateModal.style)
			newDiv.attr('style',dataToCreateModal.style);
		newDiv.attr('id',modaldivName);
		newDiv.html(modalobj.result);
		$j('body').append(newDiv);
	}		
}

function createResultModal(data, modaldivName, testName, width) {
	var modalobj;
	modalobj = new resultModal(data,testName);
	var obj='#' + modaldivName;
	if($j(obj).length>0) {
		$j(obj).html(modalobj.result);
	} else{
		var newDiv = $j('<div class="reveal-modal">');
		widthstyle='width:' + width;
		newDiv.attr('style',widthstyle);
		newDiv.attr('id',modaldivName);
		newDiv.html(modalobj.result);
		$j('body').append(newDiv);
	}	
}

function createGeneralModal(data, modalName, title, width, height){
	var obj='#' + modalName;
	var marginperc=0;
	if(width)
		marginperc= (100- parseInt(width || 0))/2;
	var mainDiv = $j('<div/>');
	var modalTitleDiv = $j('<div class="reveal-modal-head"/>'); // Create the  header div for the Modal(title)
	var headtitle = '<h1>' + title + '</h1>';
	modalTitleDiv.html(headtitle); // give value to the title
	mainDiv.append(modalTitleDiv); // Add the div inside the form Container
		
	var modalContentDiv = $j('<div class="reveal-modal-content"/>'); // Create the  Content div for the Modal			
	var boxDiv = $j('<div/>');
	boxDiv.attr('class','box');
	var  boxcontentDiv = $j('<div/>');
	boxcontentDiv.attr('class','box-content');
	boxcontentDiv.append(data);
	boxDiv.append(boxcontentDiv);
	modalContentDiv.append(boxDiv);
		
	mainDiv.append(modalContentDiv);
		
	var CloseModalTag = $j('<a class="close-reveal-modal">&#215;</a>');
	mainDiv.append(CloseModalTag); // Appending the a tag for closing the modal box
	
	
	if($j(obj).length>0) {
		$j(obj).html(mainDiv);
	} else {
		var newDiv = $j('<div class="reveal-modal" >');
		widthstyle='width:' + width + ';' ;
		if(marginperc!=0)
			widthstyle += 'margin:0 ' + marginperc + '% 0 ' + marginperc + '%';
		//+ ';height:' + height;
		newDiv.attr('style',widthstyle);
		newDiv.attr('id',modalName);
		newDiv.html(mainDiv);
		$j('body').append(newDiv);
	}
}

function createReportModal(data,modalName,title, width,height) {
	var modalobj;
	modalobj = new reportModal(data,title);
	var savediv = $j('<div style="width:98%; margin-left:10px; padding:0px 0px 5px 0px"/>'); // create div for store the save button
	var savebutton = $j('<input type="button" class="stdbtn" value="Print" id="btnPrintReport"/>');
	savediv.html(savebutton);	
	var obj='#' + modalName;
	if($j(obj).length>0) {
		$j(obj).html(modalobj.result);
		$j(obj).append(savediv);
	} else {
		var newDiv = $j('<div class="reveal-modal" >');
		widthstyle='width:' + width ;
		//+ ';height:' + height;
		newDiv.attr('style',widthstyle);
		newDiv.attr('id',modalName);
		newDiv.html(modalobj.result);
		newDiv.append(savediv);
		$j('body').append(newDiv);
	}
}