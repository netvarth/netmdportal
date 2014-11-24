function ServerUrlProcessor() {
	this.setUrl=function(url) {this.url = url;}
	this.post = function(param) {
		var postResponse;
		$("#dvLoading").show();
		jQuery.ajax({
			type: "POST",
			url: this.url,
			data: JSON.stringify(param),
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			async: false,
			success:function(response) {
				if(response==null){
					postResponse = new Error(true, "No response from server");	
				}else
					postResponse = response;
			},
			error: function(xhr, ajaxOptions, thrownError) {
				postResponse = new Error(true, xhr.responseText);	
			}
		});	
		$("#dvLoading").hide();
		return postResponse;	
	}
	this.getHtml = function(){
		var response;
		$.ajax({
			type: 'GET',
			url: this.url,
			dataType: 'html',
			contentType: 'text/html',
			async: false,
			success: function (html) {
				response=html;
			},
			error: function(xhr, ajaxOptions, thrownError) {
				response = new Error(true, xhr.responseText);	
			}
		});
		return response;
	}
	this.get = function() {
		var postResponse;
		$("#dvLoading").show();
		jQuery.ajax({
			type: "GET",
			url: this.url,
			dataType: "json",
			async: false,
			success:function(response) {
				if(response==null || response==undefined)
					postResponse = new Error(true, "No response from server");	
				else
					postResponse = response;
			},
			error: function(xhr, ajaxOptions, thrownError) {
				postResponse = new Error(true, xhr.responseText);	
			}
		});	
		$("#dvLoading").hide();
		return postResponse;	
	}
	this.delete = function() {
		var postResponse;
		$("#dvLoading").show();
		jQuery.ajax({
			type: "DELETE",
			url: this.url,
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			async: false,
			success:function(response) {
				if(response==null)
					postResponse = new Error(true, "No response from server");	
				else 
					postResponse = response;
			},
			error: function(xhr, ajaxOptions, thrownError) {
				postResponse = new Error(true, xhr.responseText);	
			}
		});	
		$("#dvLoading").hide();
		return postResponse;	
	}
	this.put = function(param) {
		var postResponse;
		$("#dvLoading").show();
		jQuery.ajax({
			type: "PUT",
			url: this.url,
			data: JSON.stringify(param),
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			async: false,
			success:function(response) {
				if(response==null){	
					postResponse = new Error(true, "No response from server");	
				}	
				else{
					postResponse = response;
				}
			},
			error: function(xhr, ajaxOptions, thrownError) {
				postResponse =  new Error(true, xhr.responseText);	
			}
		});	
		$("#dvLoading").hide();
		return postResponse;	
	}
	this.postForm=function(param){
		var postResponse;
		$("#dvLoading").show();
		jQuery.ajax({
			url: this.url,
			data: param,
			processData: false,
			contentType:false,
			type: "POST",
			dataType: "json",
			success:function(response) {
				if(response==null)
					postResponse = new Error(true, "No response from server");	
				else
					postResponse = response;
			},
			error: function(xhr, ajaxOptions, thrownError) {
				postResponse =  new Error(true, xhr.responseText);	
			}
		});	
		$("#dvLoading").hide();
		return postResponse;	
	}
}	
function DataTableProcessor() {
	this.create = function(tableName , tableUrl, parent) {
		ajaxProcessor.setUrl(tableUrl);
		var tblData = ajaxProcessor.get();
		var boxDiv = $('<div/>');
		$(tblData.data).each(function(index,subgroup) {
			var cont = new Container(subgroup);
			boxDiv.append(cont.result);
		});
		$(parent).empty().html(boxDiv.html());
		//this.setCustomTable(tableName);
	}
	this.setCustomTable = function(tableName) {
		tableObj = jQuery(tableName);
		jQuery(tableObj).dataTable( {
			"sPaginationType": "newPagination",
			"bRetrieve":true,
			"bFilter":false,
			"bInfo":false,
			"bLengthChange":false,
			"bSort":false,
			"sDom": '<"top"Hip>'
		});
	}
	this.setCustomTableWithoutNavigator = function(tableName) {
		tableObj = jQuery(tableName);
		jQuery(tableObj).dataTable( {
			"sPaginationType": "full_numbers",
			"bFilter":false,
			"bInfo":false,
			"bPaginate":false,
			"bSort": false,
			"bRetrieve":true,
			"bAutoWidth":false,
			"sDom": '<"top"Hip>'
		});
	}
}
function PageHandler() {
	this.create = function(url) {
		ajaxProcessor.setUrl(url);
		var pageContent = ajaxProcessor.get();
		var page = new form(pageContent);
		$('#tabs-1').empty().html(page.result);
	}
	
	this.buttons = function(url, parent) {
		ajaxProcessor.setUrl(url);
		var response = ajaxProcessor.get();
		var buttonsDiv = new buttonsContainer(response);
		$(parent).empty().html(buttonsDiv.result);
	}
	
	this.createSection = function(url) {
		ajaxProcessor.setUrl(url);
		var pageContent = ajaxProcessor.get();
		var page= new Container(pageContent);
		return page.result;
	}
	this.getHomePage = function() {
		return this.homePage;
	}
	this.setHomePage = function(homePage) {
		this.homePage = homePage;
	}
	this.getActivePage = function() {
		return this.activePage;
	}
	this.setActivePage = function(activePage) {
		this.activePage = activePage;
	}
	this.openPageAsModal=function(obj,modalName) {
		obj.attr('data-reveal-id',modalName);
		obj.attr('data-animation','fade');
		var modalLocation = obj.attr('data-reveal-id');
		$('#'+modalLocation).reveal(obj.data());
		$('#'+modalName).trigger('reveal:open',"veryfast");
		obj.removeAttr('data-reveal-id');
		obj.removeAttr('data-animation');
	}
	this.generateModalPage = function(urlPath, modalName) {
		var modalobj;
		ajaxProcessor.setUrl(urlPath);
		var dataToCreateModal=ajaxProcessor.get();
		modalobj = new Modal(dataToCreateModal);
		var obj='#' + modalName;
		if($(obj).length>0) 
			$(obj).html(modalobj.result);
		else{
			var newDiv = $('<div class="reveal-modal">');
			if(dataToCreateModal.style)
				newDiv.attr('style',dataToCreateModal.style);
			newDiv.attr('id',modalName);
			newDiv.html(modalobj.result);
			$('body').append(newDiv);
		}		
	}
}
function password(button){
	this.result=function(){var pTag=$('<p/>');var spanTag=$('<span/>');spanTag.html(button.title);var brTag=$('<br/>');pTag.append(spanTag);if(button.required){spanTag=$('<span class="req_astrisk"> *</span>');pTag.append(spanTag)}pTag.append(brTag);var inputTag=$('<input/>');inputTag.attr('type',button.type);if(button.name)inputTag.attr('name',button.name);if(button.id)inputTag.attr('id',button.id);if(button.className)inputTag.attr('class',button.className);if(button.style)inputTag.attr('style',button.style);if(button.readonly)inputTag.attr('readonly',button.readonly);if(button.tabIndex)inputTag.attr('tabIndex',button.tabIndex);pTag.append(inputTag);return pTag}
}
function text(button) {
	this.result = function () {
		var pTag = $('<p/>'); // Create a  ptag [outer-1]
		var spanTag = $('<span/>'); // creating a span tag inside the ptag		
		var title = button.title;
		if(button.image)
			title += ' (<img src="' + button.image + '"/>)'; 
		spanTag.html(title); //Add data inside the span Tag
		var brTag = $('<br/>'); // Create a br for new Line
		if(button.idforInner) {
			var innerSpanTag = $('<span/>'); // creating a span tag inside the ptag
			innerSpanTag.attr('id',button.idforInner);
			spanTag.append(innerSpanTag);
		}	
		pTag.append(spanTag); //Add the span tag inside the P tag
		if(button.required) {
			spanTag = $('<span class="req_astrisk">*</span>');
			if(button.spanId)spanTag.attr('id',button.spanId);
			pTag.append(spanTag);
		}
		pTag.append(brTag); // Add the br line tag inside the P tag
		var inputTag = $('<input/>');
		inputTag.attr('type',button.type);
		if (button.name) inputTag.attr('name',button.name);
		if(button.id) inputTag.attr('id',button.id);
		if(button.className) inputTag.attr('class',button.className);
		if(button.style) inputTag.attr('style',button.style);
		if(button.readonly) inputTag.attr('readonly',button.readonly);
		if(button.tabIndex) inputTag.attr('tabIndex',button.tabIndex);
		pTag.append(inputTag);
		return pTag;
	}
}
function form(formdata) { //formdata is a json for a single form 
	this.result = function() {
		var myForm = $('<form/>');
		if(formdata.name) myForm.attr('name',formdata.name);
		if(formdata.id) myForm.attr('id',formdata.id);
		if(formdata.style) myForm.attr('style',formdata.style);
		$(formdata.content).each(function (index, group) {	//iterating through the maindivs of form
			var sepDiv = $('<div/>');
			if (group.className) sepDiv.attr('class',group.className);
			if (group.id) sepDiv.attr('id',group.id);
			if (group.style) sepDiv.attr('style',group.style);
			var boxDiv = $('<div/>');
			boxDiv.attr('class','box');
			var  boxcontentDiv = $('<div/>');
			boxcontentDiv.attr('class','box-content');
			$(group.data).each(function(index,subgroup) {
				var cont = new Container(subgroup);
				boxcontentDiv.append(cont.result);
			});
			boxDiv.append(boxcontentDiv);
			sepDiv.append(boxDiv);
			myForm.append(sepDiv);
			//boxcontentDiv.append(sepDiv);
		});
		return myForm;
	};
}
function radio(button) {
	this.result = function () {
		var pTag = $('<p/>'); // Create a  ptag [outer-1]
		if(!button.style) pTag.attr('style','margin:0 0 5px 0;');
		var spanTag = $('<span/>'); // creating a span tag inside the ptag
		spanTag.html(button.title); //Add data inside the span Tag
		var brTag = $('<br/>'); // Create a br for new Line
		pTag.append(spanTag); //Add the span tag inside the P tag
		if(button.required) {
			spanTag = $('<span class="req_astrisk"> *</span>');
			pTag.append(spanTag);
		}
		pTag.append(brTag); // Add the br line tag inside the P tag
		$(button.value).each(function(index,item) {
			var inputTag = $('<input/>');
			inputTag.attr('type',button.type);
			if(button.name) inputTag.attr('name',button.name);
			if(button.id) inputTag.attr('id',button.name + index);
			if(item.checked) inputTag.attr('checked',item.checked);
			if(item.value)inputTag.attr('value',item.value);
			if(item.id)inputTag.attr('id',item.id);
			if(item.tabIndex) inputTag.attr('tabIndex',item.tabIndex);
			if(button.className) inputTag.attr('class',button.className);
			pTag.append(inputTag);
			var labelTag=$('<label/>');
			labelTag.attr('for',button.name + index);
			labelTag.attr('style','margin:0 8px 0 3px;');
			
			labelTag.html(item.title);
			pTag.append(labelTag);
		});
		return pTag;
	};
}
function label(button) {
	this.result = function () {
		var pTag = $('<p/>');	
		var spanTag;
		if(button.title) {
			spanTag = $('<span />');
			spanTag.html(button.title);
			}
			if(button.spanstyle)
			spanTag.attr('style',button.spanstyle);
			if(button.idforInner) {
				var innerSpanTag = $('<span/>'); // creating a span tag inside the ptag
				innerSpanTag.attr('id',button.idforInner);
				spanTag.append(innerSpanTag);
			}	
			pTag.append(spanTag);
		spanTag = $('<span />');
		if(button.name) spanTag.attr('name',button.name);
		if(button.id) spanTag.attr('id',button.id);
		if(button.className) spanTag.attr('class',button.className);
		if(button.style) spanTag.attr('style',button.style);
		pTag.append(spanTag);
		return pTag;
	};
}
function checkbox(button) {
	this.result = function () {
		var pTag = $('<p/>'); // Create a  ptag [outer-1]
		if(button.title) {
			var spanTag = $('<span/>'); // creating a span tag inside the ptag
			spanTag.html(button.title); //Add data inside the span Tag
			pTag.append(spanTag);
			pTag.append($('<br/>'));
		}
		$(button.value).each(function(index,item) {
			var labelTag=$('<label/>');
			labelTag.attr('for',item.title);
			labelTag.attr('style','margin:0 10px 0 3px;');
			labelTag.html(item.title);
			var inputTag = $('<input/>');
			inputTag.attr('type',button.type);	
			if(item.checked) inputTag.attr('checked',item.checked);
			if(item.value)inputTag.attr('value',item.value);
			if(item.id) inputTag.attr('id',item.id);
			if(item.tabIndex) inputTag.attr('tabIndex',item.tabIndex);
			pTag.append(inputTag);
			pTag.append(labelTag);
		});
		return pTag;
	};
}
function button(btn) {
	this.result = function() {
		var btnTag = $('<input/>'); 
		btnTag.attr('value',btn.title);
		btnTag.attr('type',btn.type);
		if(btn.className)  btnTag.attr('class',btn.className);
		if(btn.name) {
			btnTag.attr('name',btn.name);
			btnTag.attr('id',btn.name);
		}	
		if(btn.className) btnTag.attr('class',btn.className);
		if(btn.style) btnTag.attr('style',btn.style);
		if(btn.tabIndex) btnTag.attr('tabIndex',btn.tabIndex);
		return btnTag;
	};
}
function Table (jsondata) {
	this.result = function () {
		if(jsondata!=null) {
			var tbl = $('<table></table>');
			if(jsondata.name) $(tbl).attr('name',jsondata.name);
			if (jsondata.idProperty)	$(tbl).attr('id',jsondata.idProperty);
			if (jsondata.className)	$(tbl).attr('class',jsondata.className); 
		     if(jsondata.style)$(tbl).attr('style',jsondata.style);
			/* Create Header Part */
			if(jsondata.colHeaders) { 
				var theader = $('<thead></thead>');
				var row = $('<tr></tr>');		
				$(jsondata.colHeaders).each(function(index,header) {
					var colHead = $('<th></th>');
					if(header.className) colHead.attr('class',header.className);
					if(header.style) colHead.attr('style',header.style);
					if(header.id) colHead.attr('id',header.id);
					var data;
					if(header.name)	data=header.name;
					if(header.image){
					 data+= ' (<img src ="' + header.image + '" />)';
					}
					var innerdata="";
					if(header.content) {
						$(header.content).each(function(index,headercontent) {
							innerdata = new datalabel(headercontent).result;
						});
					}
					colHead.html(data);
					colHead.append(innerdata);
					row.append(colHead);
				});
				theader.append(row);
				tbl.append(theader);
				/* Header Part Created*/
			}
		}
		return tbl;
	};
}

function textarea(button) {
	this.result = function() {
		var pTag = $('<p/>'); // Create a  ptag [outer-1]
		var spanTag = $('<span/>'); // creating a span tag inside the ptag
		spanTag.html(button.title); //Add data inside the span Tag
		var brTag = $('<br/>'); // Create a br for new Line
		pTag.append(spanTag); //Add the span tag inside the P tag
		pTag.append(brTag); // Add the br line tag inside the P tag	
		var textareaTag = $('<textarea/>');
		if(button.name) textareaTag.attr('name',button.name);
		if(button.id) textareaTag.attr('id',button.id);
		if(button.className) textareaTag.attr('class',button.className);
		if(button.tabIndex) textareaTag.attr('tabIndex',button.tabIndex);
		if(button.style) textareaTag.attr('style',button.style)
		pTag.append(textareaTag);
		return pTag;
	};
}
function select(button) {
	this.result = function() {
		var resTag=$('<div/>');
		var pTag = $('<p/>'); // Create a  ptag [outer-1]
		if(button.title) {
			var spanTag = $('<span/>'); // creating a span tag inside the ptag
			spanTag.html(button.title); //Add data inside the span Tag
			var brTag = $('<br/>'); // Create a br for new Line			
			pTag.append(spanTag); //Add the span tag inside the P tag
			if(button.required) {
				spanTag = $('<span class="req_astrisk"> *</span>');
				pTag.append(spanTag);
			}
			pTag.append(brTag); // Add the br line tag inside the P tag
		}
		var  selectTag= $('<select/>');
		if(button.name) selectTag.attr('name',button.name);
		if(button.id) selectTag.attr('id',button.id);
		if(button.className) selectTag.attr('class',button.className);
		if(button.style) selectTag.attr('style',button.style);	
		if(button.tabIndex) selectTag.attr('tabIndex',button.tabIndex);	
		$(button.value).each(function(index,item) {
			var optionTag = $('<option />');
			optionTag.html(item);
			selectTag.append(optionTag);
		});
		pTag.append(selectTag);
		return pTag;
	};
}
function Container(contentgroup) {
	this.result =	function(){ 
		var content = $('<div>');
		content.attr('class',contentgroup.className);
		if(contentgroup.id) content.attr('id',contentgroup.id);
		if(contentgroup.style) content.attr('style',contentgroup.style);
		$(contentgroup.elements).each(function(index, buttonelement) {
			if(buttonelement.type == "radio") 
				content.append((new radio(buttonelement).result));	
			if(buttonelement.type == "checkbox") 
				content.append((new checkbox(buttonelement).result));
			if(buttonelement.type=="text") 					
				content.append((new text(buttonelement).result));	
			if(buttonelement.type=="password") 					
				content.append((new password(buttonelement).result));
			if(buttonelement.type=="label") 
				content.append((new label(buttonelement).result));			
			if(buttonelement.type=="textarea") 
				content.append((new textarea(buttonelement).result));				
			if(buttonelement.type=="select")
				content.append((new select(buttonelement).result));
			if(buttonelement.type=="table")
				content.append((new Table(buttonelement.content).result));
			if(buttonelement.type=="button") {
				$(buttonelement.content.buttons).each(function(index,btn) {											
					content.append((new button(btn).result));
				});
			}
			if(buttonelement.data){
				$(buttonelement.data).each(function (index, group) {										
					var contain = new Container(group);						
					content.append(contain.result);	
				});
			}																			
		});
		$(contentgroup.data).each(function (index, group) {	
			var contain = new Container(group);
			content.append(contain.result);										
		});
		return content;
	};
}
function DataTableNavigator(sourceTable,url, sourceContainer, tableObj, exp) {
	this.curPage=1;
	this.maxPage=0;
	this.startValue=0;
	this.interval=10;
	this.firstButton = "#first";
	this.lastButton = "#last";
	this.nextButton = "#next";
	this.prevButton = "#previous";
	this.exp = exp;
	this.filterDTO = new FilterDTO(this.exp.getExpressionList(),this.startValue,this.interval,false);
	this.sourceTable = sourceTable;
	this.sourceContainer = sourceContainer;
	this.url = url;
	this.tableObj = tableObj;
	this.bindNavigateEvents(sourceContainer);//bind events to next,prev etc.
}
DataTableNavigator.prototype.getKey=function() {
	return this.key;
}
DataTableNavigator.prototype.setKey=function(key) {
	this.key=key;
}
DataTableNavigator.prototype.getPgDataList=function() {
	return this.pgDataList;
}
DataTableNavigator.prototype.getStartValue=function() {
	return this.startValue;
}
DataTableNavigator.prototype.setStartValue=function(startValue) {
	this.startValue=startValue;
}
DataTableNavigator.prototype.setExp=function(exp) {
	this.exp=exp;
}
DataTableNavigator.prototype.setFilterDTO=function() {
	this.filterDTO=new FilterDTO(this.exp.getExpressionList(),this.startValue,this.interval,false);
}
DataTableNavigator.prototype.getFilterDTO=function() {
	return this.filterDTO;
}
DataTableNavigator.prototype.getExp=function() {
	return this.exp;
}
DataTableNavigator.prototype.getMaxPage=function() {
	return this.maxPage;
}
DataTableNavigator.prototype.setMaxPage=function(maxRecords) {
	if(maxRecords%this.interval!=0)
		this.maxPage = parseInt(maxRecords/this.interval) + 1;
	else
		this.maxPage = parseInt(maxRecords/this.interval);	
}
DataTableNavigator.prototype.getCurPage=function() {
	return this.curPage;
}
DataTableNavigator.prototype.setCurPage=function(curPage) {
	this.curPage=curPage;
}
DataTableNavigator.prototype.getInterval=function() {
	return this.interval;
}
DataTableNavigator.prototype.setInterval=function(interval) {
	this.interval=interval;
}
DataTableNavigator.prototype.next = function() {
	var self = this;
	if(self.curPage!=self.maxPage && self.curPage<self.maxPage) {
		self.setStartValue(self.interval * (self.curPage));
		self.setCurPage(self.curPage+1);
		self.filterDTO = new FilterDTO(self.exp.getExpressionList(),self.startValue,self.interval,false);
		self.list(self.getKey());
		self.setPaginationFields(self.sourceContainer);
	}	
}
DataTableNavigator.prototype.prev= function() {
	var self = this;
	if(self.curPage!=1) {
		self.curPage-=1;
		self.startValue = self.interval*(self.curPage-1);
		self.filterDTO = new FilterDTO(self.exp.getExpressionList(),self.startValue,self.interval,false);
		self.list(self.getKey());	
		self.setPaginationFields(self.sourceContainer);		
	}
}
DataTableNavigator.prototype.first=function() {
	var self = this;
	if(self.curPage!=1) {
		self.curPage=1;
		self.startValue = 0;
		self.filterDTO = new FilterDTO(self.exp.getExpressionList(),self.startValue,self.interval,false);
		self.list(self.getKey());
		self.setPaginationFields(self.sourceContainer);
	}	
}
DataTableNavigator.prototype.last= function() {
	var self = this;
	if(self.curPage!=self.maxPage && self.curPage<self.maxPage) {
		self.curPage =self.maxPage;
		self.startValue = (self.curPage-1)*self.interval;
		self.filterDTO = new FilterDTO(self.exp.getExpressionList(),self.startValue,self.interval,false);
		self.list(self.getKey());
		self.setPaginationFields(self.sourceContainer);
	}
}
DataTableNavigator.prototype.list = function(key) {
	var self=this;
	self.setKey(key);
	ajaxProcessor.setUrl(self.url);
	self.setFilterDTO();
	self.pgDataList = ajaxProcessor.post(self.filterDTO);
	self.setMaxPage(self.pgDataList.count);
	self.setPaginationFields(self.sourceContainer);
	self.tableObj.setTableValues(self.sourceTable,self.pgDataList, self.getKey());
}
DataTableNavigator.prototype.get = function() {
	var self=this;
	ajaxProcessor.setUrl(self.url);
	self.pgDataList = ajaxProcessor.get();
	self.setMaxPage(self.pgDataList.count);
	self.setPaginationFields(self.sourceContainer);
	self.tableObj.setTableValues(self.sourceTable,self.pgDataList);
}
DataTableNavigator.prototype.setPaginationFields= function(tableObj) {
	var self = this;
	$(tableObj + ' #first').removeClass('paginate_button_disabled');
	$(tableObj + ' #next').removeClass('paginate_button_disabled');
	$(tableObj + ' #previous').removeClass('paginate_button_disabled');
	$(tableObj + ' #last').removeClass('paginate_button_disabled');
	$(tableObj + ' #first').addClass('paginate_button');
	$(tableObj + ' #next').addClass('paginate_button');
	$(tableObj + ' #previous').addClass('paginate_button');
	$(tableObj + ' #last').addClass('paginate_button');
	$(tableObj + ' .paginate_active').empty().html(self.curPage);
	if(self.curPage==1 && self.maxPage<=1) {
		$(tableObj + ' #first').removeClass('paginate_button');
		$(tableObj + ' #next').removeClass('paginate_button');
		$(tableObj + ' #previous').removeClass('paginate_button');
		$(tableObj + ' #last').removeClass('paginate_button');
		$(tableObj + ' #first').addClass('paginate_button_disabled');
		$(tableObj + ' #next').addClass('paginate_button_disabled');
		$(tableObj + ' #previous').addClass('paginate_button_disabled');
		$(tableObj + ' #last').addClass('paginate_button_disabled');
	}else if(self.curPage==1) {
		$(tableObj + ' #previous').removeClass('paginate_button');
		$(tableObj + ' #previous').addClass('paginate_button_disabled');
		$(tableObj + ' #first').removeClass('paginate_button');
		$(tableObj + ' #first').addClass('paginate_button_disabled');
	}else if(self.curPage==self.maxPage) {
		$(tableObj + ' #first').removeClass('paginate_button_disabled');
		$(tableObj + ' #first').addClass('paginate_button');
		$(tableObj + ' #previous').removeClass('paginate_button_disabled');
		$(tableObj + ' #previous').addClass('paginate_button');
		
		$(tableObj + ' #last').removeClass('paginate_button');
		$(tableObj + ' #last').addClass('paginate_button_disabled');
		$(tableObj + ' #next').removeClass('paginate_button');
		$(tableObj + ' #next').addClass('paginate_button_disabled');
	} 
}
DataTableNavigator.prototype.bindNavigateEvents = function(sourceObj) {
	var self=this;
	$(sourceObj + " " + this.nextButton).die('click').live('click',function() {
		self.next();
	});
	$(sourceObj + " " + this.lastButton).die('click').live('click',function() {
		self.last();
	});
	$(sourceObj + " " + this.prevButton).die('click').live('click',function() {
		self.prev();
	});
	$(sourceObj + " " + this.firstButton).die('click').live('click',function() {
		self.first();
	});
}
function PageToolBarProcessor() {
	this.create = function(category, url) {
		ajaxProcessor.setUrl(url);
		var ptbdata =ajaxProcessor.get();
		var ptbContainer = $('<div id="' + category + 'PTBContainer"/>');
		var ptb = new PageToolBar(ptbdata);
		$(ptbContainer).append(ptb.result);
		$('#pageToolBar-Container').empty().html(ptbContainer);
		return ('"#' + category + 'PTBContainer"');
	}
}
function PageToolBar(jsondata) {
	var toolBar=$("<div></div>");
	if(jsondata!=null)
	{	
		$(jsondata.buttons).each(function(index,button) {	
			var headTag = $('<h2/>');
			var aTag = $('<a href="#" rel="tooltip" />');
			aTag.attr('class','btn btn3 btn_'+button.name);
			aTag.attr('title',button.title);
			aTag.attr('id','btn_'+button.name+'_ptb_id');
			if(button.datarevealid){
				aTag.attr('data-reveal-id',button.datarevealid);
				aTag.attr('data-animation','fade');
			}
			headTag.append(aTag);
			toolBar.append(headTag);
		});
	}
	this.result = toolBar.html();
}
function FilterToolBarProcessor() {
	this.create = function(parent, category, url) {
		ajaxProcessor.setUrl(url);
		var response =ajaxProcessor.get();
		var ftbdata=response.buttons;
		/*$(response.enumListDTO).each(function (index, enumList) {
			if(category==enumList.key) {
				ftbdata = enumList.enumValues;
				return false;
			}
		});*/
		var ftb = this.generate(ftbdata);
		var filterCont=$('<div id="' + parent + '-filter-cont"/>');
		var filterTB = $('<div id="' + parent + '-filter-toolbar" class="box-content"/>');
		filterTB.append(ftb); 
		filterCont.append(filterTB);
		var filterWB = $('<div id="' + parent + '-filter-wb" style="display:none;padding:0 0 0 0px"/>');
		var filterWBInner = $('<div id="' + parent + '-filter-wb-inner" style="float:left; width:40%"/>');
		filterWB.append(filterWBInner);
		var filterSubBtn = $('<input type="button" value="Go", id="' + parent + 'btnfltrSubmit"/>');
		filterWB.append(filterSubBtn);
		filterCont.append(filterWB);
		$('#filterToolBar-Container').empty().html(filterCont);
		$('#filter').show();
		$('#filterWorkBench').hide();	
	}	
	this.generate = function(filterList) {
		var self = this;
		var toolBar=$('<div/>');
		if(filterList!=null)
		{	
			/* Creation of ToolBar */	
			$(filterList).each(function(index,button) {
				var toolbutton= $('<a href="#" class="anchorbutton remMarginRight genMarginLeft' + '" name="'+ button.displayName +'" id="btn_'+ button.displayName +'_filter_id"><span>'+ button.parameterName +'</span></a>');
				toolBar.append(toolbutton);
				type=button.type;
				/*if(type!=null){
					toolBar.append(self.setFilterOperators(button,type));}*/
				var inputTag=$('<input type="text">');
				inputTag.attr('id','txt'+button.displayName);
				inputTag.addClass('genTextHeight');
				inputTag.attr('style','display:none');
				toolBar.append(inputTag);
			});
		}
		var inputTag=$('<input type="button" value=" Go " class="genMarginLeft genTextHeight stdbtn btn_black">');
		inputTag.attr('id','btnGo');
		toolBar.append(inputTag);	
		return toolBar.html();
	}
	this.setFilterOperators=function(button,type) {
		var selectTag=$('<select/>');
		selectTag.attr('id','lst'+ button.displayName);
		selectTag.addClass('genTextHeight');
		selectTag.addClass('genTextwidth');
		selectTag.attr('style','display:none');
		var option = $('<option value="eq">eq</option>');
		selectTag.append(option);
		var option = $('<option value="neq">neq</option>');
		selectTag.append(option);
		if(type=='number'){
			var option = $('<option value="gt">gt</option>');
			selectTag.append(option);
			var option = $('<option value="lt">lt</option>');
			selectTag.append(option);
			var option = $('<option value="ge">ge</option>');
			selectTag.append(option);
			var option = $('<option value="le">le</option>');
			selectTag.append(option);
		}else if(type=='text'){
			var option = $('<option value="like">like</option>');
			selectTag.append(option);
		}
		return selectTag;
	}
	this.createFromHtml=function(parent, htmlData) {
		var filterCont=$('<div id="' + parent + '-filter-cont"/>');
		var filterTB = $('<div id="' + parent + '-filter-toolbar" class="box-content"/>');
		filterTB.append(htmlData); 
		filterCont.append(filterTB);
		var filterWB = $('<div id="' + parent + '-filter-wb" style="display:none;padding:0 0 0 0px"/>');
		var filterWBInner = $('<div id="' + parent + '-filter-wb-inner" style="float:left; width:40%"/>');
		filterWB.append(filterWBInner);
		filterCont.append(filterWB);
		$('#filterToolBar-Container').empty().html(filterCont);
		$('#filter').show();
		$('#filterWorkBench').hide();	
	}
}
function Modal(modaldata,model) {
	this.result = function() {
		var mainDiv = $('<div/>');
		var modalTitleDiv = $('<div class="reveal-modal-head"/>'); // Create the  header div for the Modal(title)
		modalTitleDiv.html('<h1>' + modaldata.title + '</h1>'); // give value to the title
		mainDiv.append(modalTitleDiv); // Add the div inside the form Container
		var modalContentDiv = $('<div class="reveal-modal-content"/>'); // Create the  Content div for the Modal
		$(modaldata.data).each(function(index,myform) { //iterate for each form
			var contentForm = new form(myform,model);
			modalContentDiv.append(contentForm.result);
		});
		mainDiv.append(modalContentDiv);
		var CloseModalTag = $('<a class="close-reveal-modal">&#215;</a>');
		mainDiv.append(CloseModalTag); // Appending the a tag for closing the modal box
		return mainDiv;
	};
}
function createResultModal(data, modaldivName, testName, width) {
	var modalobj;
	modalobj = new resultModal(data,testName);
	var obj='#' + modaldivName;
	if($(obj).length>0) {
		$(obj).html(modalobj.result);
	} else{
		var newDiv = $('<div class="reveal-modal">');
		widthstyle='width:' + width;
		widthstyle+=";max-height:100%";
		widthstyle+=";overflow:auto;";
		newDiv.attr('style',widthstyle);
		newDiv.attr('id',modaldivName);
		newDiv.html(modalobj.result);
		$('body').append(newDiv);
	}	
}
function resultModal(modaldata,testName) {
	this.result = function() {
		var mainDiv = $('<div/>');
		var modalTitleDiv = $('<div class="reveal-modal-head"/>'); // Create the  header div for the Modal(title)
		var headtitle = '<h1>Result -' + testName + '</h1>';
		modalTitleDiv.html(headtitle); // give value to the title
		mainDiv.append(modalTitleDiv); // Add the div inside the form Container
		var modalContentDiv = $('<div class="reveal-modal-content"/>'); // Create the  Content div for the Modal	
		var height = $(window).height()-50;
		modalContentDiv.css("max-height",height);
		var contentForm = new resultform(modaldata);
		modalContentDiv.append(contentForm.result);
		mainDiv.append(modalContentDiv);
		var CloseModalTag = $('<a class="close-reveal-modal">&#215;</a>');
		mainDiv.append(CloseModalTag); // Appending the a tag for closing the modal box
		return mainDiv;
	};
}
function resultform(formdata) { //formdata is a json for a single form 
	this.result = function() {
		var mydiv =$('<div class="box"/>');
		var myForm = $('<form/>');
		myForm.attr('id','resultForm');
		myForm.html(formdata);
		mydiv.html(myForm);
		return mydiv;
	};
}
function openModalBox(obj,modalName) {
	obj.attr('data-reveal-id',modalName);
	obj.attr('data-animation','fade');
	var modalLocation = obj.attr('data-reveal-id');
	$('#'+modalLocation).reveal(obj.data());
	$('#'+modalName).trigger('reveal:open',"veryfast");
	obj.removeAttr('data-reveal-id');
	obj.removeAttr('data-animation');
}
function ErrorDTO() {
	this.errorStatus=false;
	this.errorMsgs=[];
}
ErrorDTO.prototype.setErrorMsgs = function(errorMsgs) {
	this.errorMsgs=errorMsgs;
}
ErrorDTO.prototype.setErrorStatus = function(errorStatus) {
	this.errorStatus=errorStatus;
}
function CommonMethodInvoker() {
	this.getUserDateFromSystemDate=function(curDate) {
		var month = curDate.getMonth()+1;
		if(month<10)
			month='0' + month;
		var day = curDate.getDate();
		if (day<10)
			day='0' + day;
		var year = curDate.getFullYear();
		var today = "" + day + '-' + month + '-' + year + "";
		return today;
	}
	this.getUserTimeFromSystemDate=function(curDate) {
		var hour = curDate.getHours();
		var ampm = 'AM';
		if (hour==0) {
			hour=12;
			ampm = 'AM';
		}else if((hour-12)<0){
			ampm='AM';
			if(hour<10)
				hour = '0' + hour;
		} else if((hour-12)==0){
			ampm='PM';
		} else if((hour-12)<12){
			hour = hour-12;
			ampm = 'PM';
			if(hour<10)
				hour = '0' + hour;
		} else {
			hour=12;
			ampm='AM';
		}
		var minute = curDate.getMinutes();
		if(minute<10)
			minute = '0' + minute;
		var time = hour + ":" + minute + " " + ampm;
		return time;
	}
	this.br2nl = function(str) {
		return str.replace(/<br\s*\/?>/mg,"\n");
	}
	this.nl2br = function(str) {
		return str.replace(/[\r\n]/g, "<br/>");
	}
	this.tab2space=function(str) {
		return str.replace(/\t/g, '&nbsp;&nbsp;&nbsp;&nbsp;');
	}
	this.space2tab=function(str) {
		return str.replace(/&nbsp;/g, ' ');
	}
	this.validateNumber = function(p)	{
		$(p).keydown(function (e) {
			if (e.shiftKey || e.ctrlKey || e.altKey) { // if shift, ctrl or alt keys held down
				e.preventDefault();         // Prevent character input
			} else {
				var n = e.keyCode;
				if (!((n == 8) 				// backspace
					|| (n == 46)                // delete
					|| (n >= 35 && n <= 40)     // arrow keys/home/end
					|| (n >= 48 && n <= 57)     // numbers on keyboard
					|| (n >= 96 && n <= 105)   // number on keypad
					|| (n==109 || n==189)	//minus sign
					|| (n==107 )			//plus sign
					|| (n==187 )			//plus sign
					||	(n==32 )				//Space
					||(n==13 || n==9))      // enter and tab	
				) {
					e.preventDefault();     // Prevent character input
				}
			}
		});
	}
	this.validateNumberDot = function(p)	{
		$(p).keydown(function (e) {
            if (e.shiftKey || e.ctrlKey || e.altKey) { // if shift, ctrl or alt keys held down
                e.preventDefault();         // Prevent character input
            } else {
                var n = e.keyCode;
                if (!((n == 8) 				// backspace
                    || (n == 46)                // delete
                    || (n >= 35 && n <= 40)     // arrow keys/home/end
                    || (n >= 48 && n <= 57)     // numbers on keyboard
                    || (n >= 96 && n <= 105)   // number on keypad
					|| (n==190|| n==110)			//dot
					||(n==13 || n==9))      // enter and tab
                ) {
                    e.preventDefault();     // Prevent character input
				} else{
					if(n==190||n==110) {
						var total =$(p).val();//fixed to 2 decimal points
						(total).toFixed(2); 
					}
				}
			}
        });
	}
	this.setViewTable = function(tableName) {
		tableObj = jQuery(tableName);
		jQuery(tableObj).dataTable( {
			"sPaginationType": "full_numbers",
			"bFilter":false,
			"bInfo":false,
			"bPaginate":false,
			"bAutoWidth":false,
			"bSort": false,
			"bRetrieve":true,
			"sDom": '<"top"Hip>'
		});
	}
	this.setDateFormat = function(date){
		newDate = date.split('-');
		newDate=newDate[2]+'-'+newDate[1]+'-'+newDate[0];
		return newDate;
	}
	this.getCustomDate = function(date) {
		var month = date.getMonth()+1;
		if(month<10)
			month='0' + month;
		var day = date.getDate();
		if (day<10)
			day='0' + day;
		var year = date.getFullYear();
		var curdate = "" + day + '-' + month + '-' + year + "";
		return curdate;
	}
	this.makeautoComplete=function(controlName,sourceData){
		sourceData.sort();
		$(controlName).autocomplete({
			source: function( request, response ) {
				var matches = $.map( sourceData, function(tag) {
					if (tag.toUpperCase().indexOf(request.term.toUpperCase()) === 0 ) {
						return tag;
					}
				});
				response(matches);
			}			
		});		
		return;
	}	
	this.makeautoCompleteDefault=function(className,sourceData,parent){
		var target = "."+className;
		if(parent!=undefined)
			target = parent + " ."+className;
		$(target).autocomplete({
			source: sourceData			
		});		
		return;
	}	
	this.isIndexExists=function(source, index) {
		var foundStatus = false;
		for(ind = 0; ind<source.length;ind++){
			if(source[ind]==index) {
				foundStatus=true;
				return foundStatus;
			}	
		}	
		return foundStatus;
	}
	this.isEqual=function(value1,value2){
		var stat = false;
		if(value1==value2)
			stat = true;
		return stat;
	}
	this.checkRowIdDuplication=function(tableObj, rowId) {
		var status=false;
		if($(tableObj).dataTable().fnGetData().length>0) {
			var curTable = $(tableObj + " tr:gt(0)"); //this will not include the header row
			curTable.each(function() {
				var curId=$(this).attr('id');
				if(rowId==curId) {
					status=true;
					return false;
				}
			});	
		}	
		return status;
	}
	this.getmmFromPixel=function(pixels, dpi) {
		mm = (pixels * 25.4) / dpi;
		return mm;
	}
}
function FilterDTO(exp, from, count, asc) {
	this.from = from;
	this.count= count;
	this.asc=asc;
	this.exp= exp;
}
FilterDTO.setFrom = function(from) {
	this.from=from;
}
FilterDTO.setCount = function(count) {
	this.count=count;
}
FilterDTO.setAsc= function(asc) {
	this.asc=asc;
}
FilterDTO.setExp= function(exp) {
	this.exp=exp;
}
function ExpressionDTO(name,value,operator) {
	if(arguments.length>0) {
		this.name = name;
		this.value= value;
		this.operator=operator;
	}
}
ExpressionDTO.setValue = function(value) {
	this.id=id;
}
ExpressionDTO.setName = function(name) {
	this.name=name;
}
ExpressionDTO.setOperator= function(operator) {
	this.operator=operator;
}
function ExpressionListDTO(expressionDTO) {
	this.expressionList=[];
	this.init = function() {
		this.expressionList=[];
	}
	this.add = function(expressionDTO) {
		this.expressionList.push(expressionDTO);
	}
	this.remove = function(expressionName) {
	}
	this.getExpressionList = function() {
		return this.expressionList;
	}
}
function Error(errorStatus, errorMessage) {
	this.errorStatus=false;
	this.errorMessage=errorMessage;
}
Error.prototype.getErrorMessage = function() {
	return this.errorMessage;
}
function PageToolBarProcessor() {
	this.create = function(category, url) {
		ajaxProcessor.setUrl(url);
		var ptbdata =ajaxProcessor.get();
		var ptbContainer = $('<div id="' + category + 'PTBContainer"/>');
		var ptb = new PageToolBar(ptbdata);
		$(ptbContainer).append(ptb.result);
		$('#pageToolBar-Container').empty().html(ptbContainer);
		return ('"#' + category + 'PTBContainer"');
	}
}
function PageToolBar(jsondata) {
	var toolBar=$("<div></div>");
	if(jsondata!=null)
	{	
		$(jsondata.buttons).each(function(index,button) {	
			var headTag = $('<h2/>');
			var aTag = $('<a href="#" rel="tooltip" />');
			aTag.attr('class','btn btn3 btn_'+button.name);
			aTag.attr('title',button.title);
			aTag.attr('id','btn_'+button.name+'_ptb_id');
			if(button.datarevealid){
				aTag.attr('data-reveal-id',button.datarevealid);
				aTag.attr('data-animation','fade');
			}
			headTag.append(aTag);
			toolBar.append(headTag);
		});
	}
	this.result = toolBar.html();
}
function FilterToolBarProcessor() {

	this.create = function(parent, category, url) {
		ajaxProcessor.setUrl(url);
		var response =ajaxProcessor.get();
		var ftbdata=response.buttons;
		/*$(response.enumListDTO).each(function (index, enumList) {
			if(category==enumList.key) {
				ftbdata = enumList.enumValues;
				return false;
			}
		});*/
		var ftb = this.generate(ftbdata);
		var filterCont=$('<div id="' + parent + '-filter-cont"/>');
		var filterTB = $('<div id="' + parent + '-filter-toolbar" class="box-content"/>');
		filterTB.append(ftb); 
		filterCont.append(filterTB);
		var filterWB = $('<div id="' + parent + '-filter-wb" style="display:none;padding:0 0 0 0px"/>');
		var filterWBInner = $('<div id="' + parent + '-filter-wb-inner" style="float:left; width:40%"/>');
		filterWB.append(filterWBInner);
		var filterSubBtn = $('<input type="button" value="Go", id="' + parent + 'btnfltrSubmit"/>');
		filterWB.append(filterSubBtn);
		filterCont.append(filterWB);
		$('#filterToolBar-Container').empty().html(filterCont);
		$('#filter').show();
		$('#filterWorkBench').hide();	
	}	
	this.generate = function(filterList) {
		var self = this;
		var toolBar=$('<div/>');
		if(filterList!=null)
		{	
			/* Creation of ToolBar */	
			$(filterList).each(function(index,button) {
				var toolbutton= $('<a href="#" class="anchorbutton remMarginRight genMarginLeft' + '" name="'+ button.displayName +'" id="btn_'+ button.displayName +'_filter_id"><span>'+ button.parameterName +'</span></a>');
				toolBar.append(toolbutton);
				type=button.type;
				/*if(type!=null){
					toolBar.append(self.setFilterOperators(button,type));}*/
				var inputTag=$('<input type="text">');
				inputTag.attr('id','txt'+button.displayName);
				inputTag.addClass('genTextHeight');
				inputTag.attr('style','display:none');
				toolBar.append(inputTag);
			});
		}
		var inputTag=$('<input type="button" value=" Go " class="genMarginLeft genTextHeight stdbtn btn_black">');
		inputTag.attr('id','btnGo');
		toolBar.append(inputTag);	
		return toolBar.html();
	}
	this.setFilterOperators=function(button,type) {
		var selectTag=$('<select/>');
		selectTag.attr('id','lst'+ button.displayName);
		selectTag.addClass('genTextHeight');
		selectTag.addClass('genTextwidth');
		selectTag.attr('style','display:none');
		var option = $('<option value="eq">eq</option>');
		selectTag.append(option);
		var option = $('<option value="neq">neq</option>');
		selectTag.append(option);
		if(type=='number'){
			var option = $('<option value="gt">gt</option>');
			selectTag.append(option);
			var option = $('<option value="lt">lt</option>');
			selectTag.append(option);
			var option = $('<option value="ge">ge</option>');
			selectTag.append(option);
			var option = $('<option value="le">le</option>');
			selectTag.append(option);
		}else if(type=='text'){
			var option = $('<option value="like">like</option>');
			selectTag.append(option);
		}
		return selectTag;
	}
	this.createFromHtml=function(parent, htmlData) {
		var filterCont=$('<div id="' + parent + '-filter-cont"/>');
		var filterTB = $('<div id="' + parent + '-filter-toolbar" class="box-content"/>');
		filterTB.append(htmlData); 
		filterCont.append(filterTB);
		var filterWB = $('<div id="' + parent + '-filter-wb" style="display:none;padding:0 0 0 0px"/>');
		var filterWBInner = $('<div id="' + parent + '-filter-wb-inner" style="float:left; width:40%"/>');
		filterWB.append(filterWBInner);
		filterCont.append(filterWB);
		$('#filterToolBar-Container').empty().html(filterCont);
		$('#filter').show();
		$('#filterWorkBench').hide();	
	}
}
String.prototype.startsWith = function(s) {
   if( this.indexOf(s) == 0 ) return true;
   return false;
}

/**
 * ReplaceAll by Fagner Brack (MIT Licensed)
 * Replaces all occurrences of a substring in a string
 */
String.prototype.replaceAll = function(token, newToken, ignoreCase) {
    var str, i = -1, _token;
    if((str = this.toString()) && typeof token === "string") {
        _token = ignoreCase === true? token.toLowerCase() : undefined;
        while((i = (
            _token !== undefined? 
                str.toLowerCase().indexOf(
                            _token, 
                            i >= 0? i + newToken.length : 0
                ) : str.indexOf(
                            token,
                            i >= 0? i + newToken.length : 0
                )
        )) !== -1 ) {
            str = str.substring(0, i)
                    .concat(newToken)
                    .concat(str.substring(i + token.length));
        }
    }
return str;
};

$.fn.serializeObject = function()
{	
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
				o[this.name] = this.value || '';
		}
	});
	return o;
};
function GlobalToolBar(jsondata) {
	this.result = function() {
		var toolBar=$('<ul/>');
		if(jsondata!=null)
		{
			$(jsondata.buttons).each(function(index,button) {
				var liTag = $('<li/>');
				var aTag = $('<a/>');
				aTag.attr('href','#');
				if (button.className){ aTag.attr('class',button.className);		}		
				if(button.name) { 
					aTag.attr('id',button.name);
					aTag.attr('name',button.name);
				}
				if(button.image) {
					imgTag = $('<img/>');
					imgTag.attr('src',button.image);
					aTag.append(imgTag);
				}
				if(button.title){
					var spanTag = $('<span/>');
					spanTag.html(button.title);
					aTag.append(spanTag);
				}
				liTag.append(aTag);
				toolBar.append(liTag);
			});
		}
		return toolBar;
	}
}
function leftpaneToolBar(buttons) {
	this.result = function() {
		var toolBar=$('<ul/>');
		if(buttons!=null)
		{
			$(buttons).each(function(index,button) {	
				var liTag = $('<li/>');
				var aTag = $('<a/>');
				aTag.attr('href','#');
				if (button.className) {aTag.attr('class',button.className);}
				if(button.name){ aTag.attr('id',button.name);}
				if(button.title){
					var spanTag = $('<span/>');
					spanTag.html(button.title);
					aTag.append(spanTag);   
				}
				liTag.append(aTag);
				if(button.buttons) {
					var sublist;
					var	sublist =new leftpaneToolBar(button.buttons);
					liTag.append(sublist.result);
				}
				toolBar.append(liTag);
			});
		}
		return toolBar;
	}
}
function Validator() {
	this.isEmpty = function(value) {
		if(value.trim().length==0){
			return true;
		}else{
			return false;}
	}	
	this.isZero = function(value) {
		if(parseFloat(value)==0 || parseFloat(value==NaN)){
			return true;
		}else{
			return false;}
	}	
	this.isEmptyList = function(list) {
		if(list.length==0){
			return true;
		}else{
			return false;}
	}	
	this.validateName = function(name) {
		var regexp = /^[0-9A-Za-z_-]'?[ 0-9A-Za-z#$%=@!{},`~&*()'<>?.:;_|^ +\t\r\n\[\]"-]*/;
		if(regexp.test(name.trim())){
			return true;
		}else{
			return false;}
	}	
	this.validateEmail = function (email) { 
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
		if(email.match(mailformat))  {
			return true; } 
		return false;
	}	
	this.validatePhone = function(phone){
		var phoneFormat = /\(?([0-9]{4})\)?([ .-]?)([0-9])\d{6}$/;  
		if(phone.match(phoneFormat)){  
			return true;  }
		return false;
	}
	this.validateMobile = function(mobile){
		var phoneFormat = /^[0-9]\d{9}$/;  
		if(mobile.match(phoneFormat))  {
			return true;  }
		return false;
	}
	this.validateFax = function(fax){
		var faxFormat = /\(?([0-9]{4})\)?([ .-]?)([0-9])\d{6}$/;  
		if(fax.match(faxFormat))  {
			return true;}  
		return false;
	}
	this.validatePin = function(pin){
		var pinFormat = /^([0-9]{6})?$/;  
		if(pin.match(pinFormat))  {
			return true; } 
		return false;
	}
	this.validateAmount=function(amount){
		var amountFormat = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/
				if(amount.toString().match(amountFormat)){
					return true;}
		return false;
	}
}
function Notifier() {
	//function to display the beeper and hide it 
	this.showTip =function(message) {
		$('#BeeperBox .UIBeep_Title').html(message);
		$("#BeeperBox").show();
	    timerId = setTimeout(function () {
	        $("#BeeperBox").hide();
	    }, 5000);
	}
	//function to destroy the timeout
	this.stopHide = function() {
		clearTimeout(timerId);
	}
	//function to hide the beeper
	this.startHide = function() {
		timerId = setTimeout(function () {
			$("#BeeperBox").hide();
    	}, 5000);
	}
}
function ErrorHandler() {
	this.updateTipsNew=function(t,errorDiv, errorDivParent) {
		$(errorDivParent).show();
		errorDiv.attr('class',"errorHeaderStyle");
		errorDiv.text(t);		
	}
	this.generateErrorFromList=function(error){
		var self=this;
		$(error.errorMsgs).each(function(index, errormsg) {
			self.createError($(errormsg.errorField), errormsg.errorMessage);
		});
	}
	this.createError = function(obj,msg) {
		var errorDiv=$('<div/>');
		errorDiv.attr('class',"errorStyle");
		errorDiv.text(msg);
		obj.closest('p').append(errorDiv);
	}
	//Error Which Coming from the server
	this.createServerError=function(objParent, obj, msg) {
		objParent.show();
		obj.attr('class',"errorHeaderStyle");
		obj.text(msg);
	}
	this.drawBorder_Error=function(obj){
		obj.addClass("error");
	}
	this.addErrorMessage=function(obj,message){
		$(obj).show();
		$(obj).addClass("errormessage");
		$(obj).text(message);
	}
	this.removeBorder_Error =function(col,errorInfo){
		$(col).click(function() {
			$(errorInfo).hide();
			$(col).removeClass('error');
		});
	}
	this.removeErrors=function () {
		$('.errorStyle').remove();
		$('.error').removeClass('error');
		$('#errorDivHeader').hide();
		$('#errorDiv_Spec_Header').hide();
		$('#errorDiv_PayDisc_Header').hide();
	}
	//function for removing error color while clicking in that field
	this.removeErrorColor = function (col){
		$(col).click(function() {
			$(this).closest('p').children('div').hide().end();
			$(col).removeClass('error');
		});
	}
}
function ErrorMessageDTO(errorField,errorMessage) {
	this.errorField=errorField;
	this.errorMessage=errorMessage;
}
function PasswordInfo() {
	this.setOldPassword = function(oldPassword) {this.oldPassword = oldPassword;}
	this.getOldPassword = function(){return this.oldPassword;}
	this.setUsername = function(name){this.username = name;}
	this.getUsername = function() {return this.username;}
	this.getNewPassword = function(){return this.newPassword;}
	this.setNewPassword = function(newPassword){this.newPassword = newPassword;	}
}
function LayoutUpdater() {
	this.generateGeneral = function(input) {
		var test = new Test_Template();
		var values = new Values_Template();
		test.setTestName(input.testName);
		test.setTestId(input.testId);
		values.setId(input.testName);
		values.setKey(input.testName);
		//values.setUnit(input.testUnit);
		var normal = "";
		var value = "";
		$(input.analysis).each(function(analysisIndex,analysis) {
			$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
				$(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
					$(resultparam.values).each(function(paramvaluesIndex,paramValues) {
						if(paramValues.headervalue=='PatientValue' || paramValues.headervalue=='Result'){
							$(paramValues.values).each(function(paramdataIndex,paramData) {
								value = paramData.value;
								values.setUnit(paramData.unit);
							});	
						}
						if(paramValues.headervalue=='NormalRange'){
							$(paramValues.values).each(function(paramdataIndex,paramData) {
								normal = paramData.value;
							});	
						}
					});
				});
			});
		});
		values.setValue(value);
		values.setNormal(normal);
		test.setValues(values);
		if(input.departmentName)
			test.setDepartmentName(input.departmentName);
		if(input.userId)
			test.setUserId(input.userId);
		if(input.userName)
			test.setUserName(input.userName);
		if(input.userDesignation)
			test.setUserDesignation(input.userDesignation);
		if(input.specimen)
			test.setSpecimen(input.specimen);
		return test;
	}
	this.generateUrine = function(input) {
		var test = new Test_Template();
		test.setTestName(input.testName);
		test.setTestId(input.testId);
		var valuesList = [];
		//values.setUnit(input.testUnit);
		var normal = "";
		var value = "";
		$(input.values).each(function(index, key){
			if(key.testnameval=="Colour"){
				var values = new Values_Template();
				values.setId('colour');
				values.setKey(key.testnameval);
				values.setValue(key.value1);
				valuesList.push(values);
			}
			if(key.testnameval=="Reaction"){
				var values = new Values_Template();
				values.setId('reaction');
				values.setKey(key.testnameval);
				values.setValue(key.value1);
				valuesList.push(values);
			}
			if(key.testnameval=="Specific Gravity"){
				var values = new Values_Template();
				values.setId('sgravity');
				values.setKey(key.testnameval);
				values.setValue(key.value1);
				valuesList.push(values);	
			}
			if(key.testnameval=="Albumin"){
				var values = new Values_Template();
				values.setId('albumin');
				values.setKey(key.testnameval);
				values.setValue(key.value1);
				valuesList.push(values);
			}
			if(key.testnameval=="Sugar"){
				var values = new Values_Template();
				values.setId('sugar');
				values.setKey(key.testnameval);
				values.setValue(key.value1);
				valuesList.push(values);
			}
			if(key.testnameval=="Acetone"){
				var values = new Values_Template();
				values.setId('acetone');
				values.setKey(key.testnameval);
				values.setValue(key.value1);
				valuesList.push(values);
			}
			if(key.testnameval=="Bile Pigments"){
				var values = new Values_Template();
				values.setId('bilepig');
				values.setKey(key.testnameval);
				values.setValue(key.value1);
				valuesList.push(values);
			}
			if(key.testnameval=="Bile salt"){
				var values = new Values_Template();
				values.setId('bilesalt');
				values.setKey(key.testnameval);
				values.setValue(key.value1);
				valuesList.push(values);
			}
			if(key.testnameval=="Urobilinogen"){
				var values = new Values_Template();
				values.setId('urobi');
				values.setKey(key.testnameval);
				values.setValue(key.value1);
				valuesList.push(values);
			}
			if(key.testnameval=="Porphobilinogen"){
				var values = new Values_Template();
				values.setId('porph');
				values.setKey(key.testnameval);
				values.setValue(key.value1);
				valuesList.push(values);
			}
			if(key.testnameval2=="Red Blood Cells"){
				var values = new Values_Template();
				values.setId('rbc');
				values.setKey(key.testnameval2);
				values.setValue(key.value2);
				valuesList.push(values);
			}
			if(key.testnameval2=="Pus Cells"){
				var values = new Values_Template();
				values.setId('pus');
				values.setKey(key.testnameval2);
				values.setValue(key.value2);
				valuesList.push(values);
			}
			if(key.testnameval2=="Epithelial Cells"){
				var values = new Values_Template();
				values.setId('epitcell');
				values.setKey(key.testnameval2);
				values.setValue(key.value2);
				valuesList.push(values);
			}
			if(key.testnameval2=="Cast"){
				var values = new Values_Template();
				values.setId('cast');
				values.setKey(key.testnameval2);
				values.setValue(key.value2);
				valuesList.push(values);
			}
			if(key.testnameval2=="Crystals"){
				var values = new Values_Template();
				values.setId('cryst');
				values.setKey(key.testnameval2);
				values.setValue(key.value2);
				valuesList.push(values);
			}
			if(key.testnameval2=="Bacteria"){
				var values = new Values_Template();
				values.setId('bacteria');
				values.setKey(key.testnameval2);
				values.setValue(key.value2);
				valuesList.push(values);
			}
			if(key.testnameval2=="Ammor.Urates"){
				var values = new Values_Template();
				values.setId('ammor');
				values.setKey(key.testnameval2);
				values.setValue(key.value2);
				valuesList.push(values);
			}
		});
		test.setValues(valuesList);
		if(input.departmentName)
			test.setDepartmentName(input.departmentName);
		if(input.userId)
			test.setUserId(input.userId);
		if(input.userName)
			test.setUserName(input.userName);
		if(input.userDesignation)
			test.setUserDesignation(input.userDesignation);
		if(input.specimen)
			test.setSpecimen(input.specimen);
		return test;
	}
	this.generateSemen=function(input) {
		var test = new Test_Template();
		test.setTestName(input.testName);
		test.setTestId(input.testId);
		var normal = "";
		var values = [];
		$(input.analysis).each(function(analysisIndex,analysis) {
			if(analysis.analysisType && analysis.analysisType!=""){
				var value = new Values_Template();
				value.setId("head" + (analysisIndex+1));
				value.setKey(analysis.analysisType);
				value.setValue("");
				value.setUnit("");
				value.setNormal("");
				values.push(value);
			}
			$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
				if(resultcontent.headerLabel) {
					var value = new Values_Template();
					value.setId("subHead"+(resultcontentIndex+1));
					value.setKey(resultcontent.headerLabel);
					value.setValue("");
					value.setUnit("");
					value.setNormal("");
					values.push(value);
					$(resultcontent.values).each(function(valueIndex, content){
						var value = new Values_Template();
						value.setId(content.id);
						value.setKey(content.normalRangeAttribute);
						var contentValue = content.value;
						if(contentValue.trim()=="")
							contentValue="--";
						if(content.value1)
							contentValue = contentValue + " " + content.value1;
						value.setValue(contentValue);
						if(content.unit)
							value.setUnit(content.unit);
						else
							value.setUnit("");
						if(content.normal)
							value.setNormal(content.normal);
						else
							value.setNormal("");
						values.push(value);
					});
				} else {
					if(resultcontent.values){
						$(resultcontent.values).each(function(valueIndex, content){
							var value = new Values_Template();
							value.setId(content.id);
							value.setKey(content.normalRangeAttribute);
							var contentValue = content.value;
							if(contentValue.trim()=="")
								contentValue="--";
							if(content.value1)
								contentValue = contentValue + " " + content.value1;
							value.setValue(contentValue);
							if(content.unit)
								value.setUnit(content.unit);
							else
								value.setUnit("");
							if(content.normal)
								value.setNormal(content.normal);
							else
								value.setNormal("");
							values.push(value);
						});
					} else {
						var value = new Values_Template();
						value.setId(resultcontent.id);
						value.setKey(resultcontent.normalRangeAttribute);
						var contentValue = resultcontent.value;
						if(contentValue.trim()=="")
							contentValue="--";
						if(resultcontent.value1)
							contentValue = contentValue + " " + resultcontent.value1;
						value.setValue(contentValue);
						if(resultcontent.unit)
							value.setUnit(resultcontent.unit);
						else
							value.setUnit("");
						if(resultcontent.normal)
							value.setNormal(resultcontent.normal);
						else
							value.setNormal("");
						values.push(value);
					}
				}
			});
		});
		test.setValues(values);
		if(input.departmentName)
			test.setDepartmentName(input.departmentName);
		if(input.userId)
			test.setUserId(input.userId);
		if(input.userName)
			test.setUserName(input.userName);
		if(input.userDesignation)
			test.setUserDesignation(input.userDesignation);
		if(input.specimen)
			test.setSpecimen(input.specimen);
		if(input.remarks)
			test.setRemarks(input.remarks);
		return test;
	}
	this.generateWidal = function(input) {
		var test = new Test_Template();
		test.setTestName(input.testName);
		test.setTestId(input.testId);
		var normal = "";
		var values = [];
		$(input.analysis).each(function(analysisIndex,analysis) {
			$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
				$(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
					$(resultparam.values).each(function(paramvaluesIndex,paramValues) {
						$(paramValues.values).each(function(paramdataIndex,paramData) {
							var value = new Values_Template();
							value.setId("index" + (paramdataIndex+1));
							value.setKey(paramData.normalRangeAttribute);
							value.setValue(paramData.value);
							value.setUnit("");
							value.setNormal("");
							values.push(value);
						});	
					});
				});
			});
		});
		test.setValues(values);
		if(input.departmentName)
			test.setDepartmentName(input.departmentName);
		if(input.userId)
			test.setUserId(input.userId);
		if(input.userName)
			test.setUserName(input.userName);
		if(input.userDesignation)
			test.setUserDesignation(input.userDesignation);
		if(input.specimen)
			test.setSpecimen(input.specimen);
		return test;
	}
}
function LayoutJson() {
	this.setResultHeader = function(resultHeader){this.resultHeader = resultHeader;}
	this.getResultHeader = function(){return this.resultHeader;}
	this.setLayouts = function(layouts){this.layouts=layouts;}
	this.getLayouts = function() {return this.layouts;}
	this.setResultFooter=function(resultFooter){this.resultFooter=resultFooter;}
	this.getResultFooter = function(){return this.resultFooter;}
}
function Layouts_Template() {
	this.setTestLayout = function(layout) {	this.testLayout = layout;}
	this.getTestLayout = function() {return this.testLayout;}
	this.setTests = function(tests) {this.tests = tests;}
	this.getTests =function(){return this.tests;}
}
function Values_Template() {
	this.setId = function(id) {this.id = id;}
	this.getId = function() {return this.id;}
	this.setKey = function(key) {this.key = key;}
	this.getKey = function() {return this.key;}
	this.setNormal = function(normal) {this.normal = normal;}
	this.getNormal = function(){return this.normal;}
	this.setUnit = function(unit) {this.unit = unit;}
	this.getUnit = function(){return this.unit;}
	this.setValue = function(value){this.value = value;}
	this.getValue = function(){return this.value;}
}
function Test_Template() {
	this.setTestName = function(testName){this.testName = testName;}
	this.getTestName = function(){return this.testName;}
	this.setTestId = function(testId) {this.testId = testId;}
	this.getTestId = function(){return this.testId;}
	this.setValues = function(values) {this.values = values;}
	this.getValues = function(){return this.values;}
	this.getDepartmentName = function() {return this.departmentName;}
	this.setDepartmentName=function(departmentName){this.departmentName = departmentName;}
	this.getRemarks =function() {return this.remarks;}
	this.setRemarks =function(remarks){this.remarks = remarks;}
	this.getUserId = function() {return this.userId;}
	this.setUserId = function(userId) {this.userId=userId;}
	this.getUserName = function() {	return this.userName;}
	this.setUserName = function(userName) {	this.userName = userName;}
	this.getUserDesignation = function() {return this.userDesignation;}
	this.setUserDesignation = function(userDesignation) {this.userDesignation = userDesignation;}
	this.getSpecimen = function(){return this.specimen;	}
	this.setSpecimen = function(specimen){this.specimen = specimen;}
}
function VerifierDTO() {
	this.setUserId =function(userId) {this.userId=userId;}	
	this.setName = function(name) {this.name = name;}
	this.setDesignation = function(designation) {this.designation = designation;}
	this.getUserId = function() {return this.userId;}
	this.getName = function() {return this.name;}
	this.getDesignation = function() {return this.designation;}
	this.getSignature = function(){return this.signature;}
	this.setSignature = function(signature){this.signature=signature;}
	this.setIndex = function(index){this.index = index;}
	this.getIndex=function(){return this.index;}
}
function Login() {
	this.setUserName=function(userName){this.userName = userName;}
	this.getUserName = function() {return this.userName;}
	this.setPassword = function(password) {this.password = password;}
	this.getPassword =function() {return this.password;}
	this.setUserType = function(userType){this.userType=userType;}
	this.getUserType=function(){return this.userType;}
}
function CaptchaInfo(){
	this.setSecretCode = function(secretCode){this.secretCode = secretCode;}
	this.getSecretCode = function(){return this.secretCode;}
	this.setVerificationCode = function(verificationCode){this.verificationCode = verificationCode;	}
	this.getVerificationCode = function(){return this.verificationCode;}
}
$.fn.dataTableExt.oPagination.newPagination = {
    "fnInit": function ( oSettings, nPaging, fnCallbackDraw )
    {
        nFirst = document.createElement( 'span' );
		$(nFirst).attr('id','first');
        nPrevious = document.createElement( 'span' );
		$(nPrevious).attr('id','previous');
		nActive = document.createElement( 'span' );
		$(nActive).attr('id','active');
        nNext = document.createElement( 'span' );
		$(nNext).attr('id','next');
        nLast = document.createElement( 'span' );   
		$(nLast).attr('id','last');		
        nFirst.appendChild( document.createTextNode( oSettings.oLanguage.oPaginate.sFirst ) );
        nPrevious.appendChild( document.createTextNode( oSettings.oLanguage.oPaginate.sPrevious ) );
		nActive.appendChild( document.createTextNode("1" ) );
        nNext.appendChild( document.createTextNode( oSettings.oLanguage.oPaginate.sNext ) );
        nLast.appendChild( document.createTextNode( oSettings.oLanguage.oPaginate.sLast ) );
        nFirst.className = "paginate_button first";
        nPrevious.className = "paginate_button previous";
        nNext.className="paginate_button next";
        nLast.className = "paginate_button last";
        nActive.className = "paginate_active"; 
        nPaging.appendChild( nFirst );
        nPaging.appendChild( nPrevious );
		nPaging.appendChild( nActive );
        nPaging.appendChild( nNext );
        nPaging.appendChild( nLast ); 
        /* Disallow text selection */
        $(nFirst).bind( 'selectstart', function () { return false; } );
        $(nPrevious).bind( 'selectstart', function () { return false; } );
        $(nNext).bind( 'selectstart', function () { return false; } );
        $(nLast).bind( 'selectstart', function () { return false; } );

    },
    "fnUpdate": function ( oSettings, fnCallbackDraw )
    {
        if ( !oSettings.aanFeatures.p )
        {
            return;
        }
    }
};
function Branch(name) {
	if(arguments.length>0){
		this.name=name;
	}
	this.setglobalId= function(globalId) {this.globalId=globalId;}
	this.getglobalId = function() {return this.globalId;}
	this.setlabId= function(labId) {this.labId=labId;}
	this.getlabId = function() {return this.labId;}
	this.setName= function(name) {this.name=name;}
	this.getName = function() {return this.name;}
	this.setEmail = function(email) {this.email=email;}
	this.getEmail = function() {return this.email;}
	this.setMobile= function(mobile) {this.mobile=mobile;}
	this.getMobile = function() {return this.mobile;}
	this.setPhone = function(phone) {this.phone=phone;}
	this.getPhone = function() {return this.phone;}
	this.setAddress = function(address) {this.address=address;}
	this.getAddress = function() {return this.address;}	
}
function OrderCountInput() {
	this.setFacility = function(facility) {this.facility = facility;}
	this.getFacility = function() {return this.facility;}
	this.setBranch = function(branch) {this.branch = branch;}
	this.getBranch = function() {return this.branch;}
	this.setFromDate = function(fromDate) {this.fromDate = fromDate;}
	this.getFromDate = function() {return this.fromDate;}
	this.setToDate = function(toDate) {this.toDate = toDate;}
	this.getToDate=function() {return this.toDate;}
}
function BranchSyncInput(){
	this.setenableSync = function(enableSync) {this.enableSync=enableSync;}
	this.getlabId = function() {return this.enableSync;}
	this.setsyncTime = function(syncTime) {this.syncTime=syncTime;}
	this.getsyncTime = function() {return this.syncTime;}
	this.setsyncFreqType = function(syncFreqType) {this.syncFreqType=syncFreqType;}
	this.getsyncFreqType = function() {return this.syncFreqType;}
	this.setlabId = function(labId) {this.labId=labId;}
	this.getlabId = function() {return this.labId;}
	this.setnetmdId = function(netmdId) {this.netmdId=netmdId;}
	this.getnetmdId = function() {return this.netmdId;}
	this.setnetrxId = function(netrxId) {this.netrxId=netrxId;}
	this.getnetrxId = function() {return this.netrxId;}
	this.setlabBranchId = function(labBranchId) {this.labBranchId=labBranchId;}
	this.getlabBranchId = function() {return this.labBranchId;}
	this.setnetmdBranchId = function(netmdBranchId) {this.netmdBranchId=netmdBranchId;}
	this.getnetmdBranchId = function() {return this.netmdBranchId;}
	this.setnetrxBranchId = function(netrxBranchId) {this.netrxBranchId=netrxBranchId;}
	this.getnetrxBranchId = function() {return this.netrxBranchId;}
}
function AdminToolBar(jsondata){this.result=function(){var toolBar=$('<ul class="shortcuts"/>');if(jsondata!=null){$(jsondata.buttons).each(function(index,button){var liTag=$('<li/>');var aTag=$('<a/>');if(button.className)aTag.attr('class',button.className);if(button.name){aTag.attr('name',button.name);aTag.attr('id',button.name)}if(button.title){var spanTag=$('<span/>');spanTag.html(button.title);aTag.append(spanTag)}liTag.append(aTag);toolBar.append(liTag)})}return toolBar}}
function OrderType() {
	this.setAgentOrder= function(agentorder) {this.agentorder=agentorder;}
	this.getAgentOrder = function() {return this.agentorder;}
	this.setBlanketOrder= function(blanketorder) {this.blanketorder=blanketorder;}
	this.getBlanketOrder = function() {return this.blanketorder;}
	this.setWalkinOrder= function(walkinorder) {this.walkinorder=walkinorder;}
	this.getWalkinOrder = function() {return this.walkinorder;}
}
function OrderTypeInput(orderType,userlabId) {
	if(arguments.length>=1) {
		this.labId=userlabId;
		this.orderTypeCodes=JSON.stringify(orderType);
	}	
	this.setlabId= function(labId) {this.labId=labId;}
	this.getlabId = function() {return this.labId;}
	this.setOrderTypeCodes= function(orderTypeCodes){this.orderTypeCodes=orderTypeCodes;}
	this.getOrderTypeCodes = function() {return this.orderTypeCodes;}
}