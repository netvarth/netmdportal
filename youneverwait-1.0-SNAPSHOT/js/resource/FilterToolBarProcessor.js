function FilterToolBarProcessor() {

	this.create = function(parent, category, url) {
		//Creating Fiter Tool Bar
		ajaxProcessor.setUrl(url);
		var response =ajaxProcessor.get();
		var ftbdata;
		$j(response.enumListDTO).each(function (index, enumList) {
			if(category==enumList.key) {
				ftbdata = enumList.enumValues;
				return false;
			}
		});
		var ftb = this.generate(ftbdata);
		var filterCont=$j('<div id="' + parent + '-filter-cont"/>');
		var filterTB = $j('<div id="' + parent + '-filter-toolbar" class="box-content"/>');
		filterTB.append(ftb); // Add the filter tool bar to Div
		filterCont.append(filterTB);
		var filterWB = $j('<div id="' + parent + '-filter-wb" style="display:none;padding:0 0 0 0px"/>');
		var filterWBInner = $j('<div id="' + parent + '-filter-wb-inner" style="float:left; width:40%"/>');
		filterWB.append(filterWBInner);
		var filterSubBtn = $j('<input type="button" value="Go", id="' + parent + 'btnfltrSubmit"/>');
		filterWB.append(filterSubBtn);
		filterCont.append(filterWB);
		$j('#filterToolBar-Container').html(filterCont);
		$j('#filter').show();
		$j('#filterWorkBench').hide();	
	}	
	this.generate = function(filterList) {
		var self = this;
		var toolBar=$j('<div/>');
		if(filterList!=null)
		{	
			/* Creation of ToolBar */	
			$j(filterList).each(function(index,button) {
				var toolbutton= $j('<a href="#" class="anchorbutton remMarginRight genMarginLeft' + '" name="'+ button +'" id="btn_'+ button +'_filter_id"><span>'+ button +'</span></a>');
				toolBar.append(toolbutton);
				var type=methodInvoker.getFilterParameterType(button);
				if(type!=null)
					toolBar.append(self.setFilterOperators(button,type));
				var inputTag=$j('<input type="text">');
				inputTag.attr('id','txt'+button);
				inputTag.addClass('genTextHeight');
				inputTag.attr('style','display:none');
				toolBar.append(inputTag);
			});
		}
		var inputTag=$j('<input type="button" value=" Go " class="genMarginLeft genTextHeight stdbtn btn_black">');
		inputTag.attr('id','btnGo');
		toolBar.append(inputTag);	
		return toolBar.html();
	}
	this.setFilterOperators=function(button,type) {
		var selectTag=$j('<select/>');
		selectTag.attr('id','lst'+ button);
		selectTag.addClass('genTextHeight');
		selectTag.addClass('genTextwidth');
		selectTag.attr('style','display:none');
		var option = $j('<option value="eq">eq</option>');
		selectTag.append(option);
		var option = $j('<option value="neq">neq</option>');
		selectTag.append(option);
		if(type=='number'){
			var option = $j('<option value="gt">gt</option>');
			selectTag.append(option);
			var option = $j('<option value="lt">lt</option>');
			selectTag.append(option);
			var option = $j('<option value="ge">ge</option>');
			selectTag.append(option);
			var option = $j('<option value="le">le</option>');
			selectTag.append(option);
		}else if(type=='text'){
			var option = $j('<option value="like">like</option>');
			selectTag.append(option);
		}
		return selectTag;
	}
}