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
function TestSpecimenDTO(testUid, specimenUid) {
	this.testUid = testId;
	this.specimenUid = specimenUid;
}
function ResultViewResponseDTO() {
	this.getOrderUid = function() {return this.orderUid;}
	this.setOrderUid = function(orderUid) {this.orderUid=orderUid;}
	this.getResult = function() {return this.result;}
	this.setResult = function(result) {this.result=result;}
	this.getPrintResult = function() {return this.printResult;}
	this.setPrintResult = function(printResult) {this.printResult=printResult;}
	this.getReportDate = function() {return this.reportDate;}
	this.setReportDate = function(reportDate) {	this.reportDate=reportDate;	}
	this.getReportTime = function() {return this.reportTime;}
	this.setReportTime = function(reportTime) {	this.reportTime=reportTime;}
	this.isPrinted = function() {return this.printed;}
	this.setPrinted = function(printed) {this.printed=printed;}
	this.getTestList= function() {return this.testList;}
	this.setTestList= function(testList) {this.testList=testList;}
	this.getError= function() {return this.error;}
	this.setError= function(error) {this.error=error;}
	this.getSuccess= function() {return this.success;}
	this.setSuccess= function(success) {this.success=success;}
}
function FinalJSONDTO() {
	this.setResultHeader= function(resultHeader) {this.resultHeader= resultHeader;}
	this.getResultHeader= function() {return this.resultHeader;	}
	this.setTestList= function(testList) {this.testList= testList;}
	this.getTestList= function() {	return this.testList;}
}
function AFBLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var table=$('<table width="100%"/>');
		var thead=$('<thead></thead>');
		var theadRow=$('<tr/>');
		var tdTag=$('<td colspan=2 align="center";/>');
		var breakTag=$('<br/>');
		if(mode=="print") {
			var lblTag = $('<label />');
			lblTag.html(result.testTitle);
			tdTag.append(lblTag);
			tdTag.append(breakTag);
		}else {
			var header=$(' <input type="text" class="Title" style="width:100%;font-weight:bold;"  id="AFB_Title" value="'+result.testTitle+'"/>');
			tdTag.append(header);
			tdTag.append(breakTag);
		}
		theadRow.append(tdTag);
		thead.append(theadRow);
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each(function(index,data){
			var row=$('<tr/>');
			var rowNew=$('<tr><td colspan=2>&nbsp</td></tr>');
			var columnOne=$('<td style="width:25%" align="left"></td>');
			var columnTwo=$('<td style="width:75%" align="left"></td>');
			spanTagOne=$('<span />');
			spanTagTwo=$('<span />');
			breakTag=$('<br/>');
			if(mode=="print") {
			if(data.fieldval1.trim()!=""){  
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval1);
				spanTagOne.append(lblTag);
			}	
			if(data.fieldval2.trim()!=""){
				var lblTag = $('<label/>');
				lblTag.html(":"+data.fieldval2);
				spanTagTwo.append(lblTag);
			}
			} else {
				var inputTag=$('<input type="text" style="width:100%" id="AFB_' + index +'_1" />');
				inputTag.attr('value',data.fieldval1);
				if(data.classNameOne)
					inputTag.attr('class',data.classNameOne);
				spanTagOne.append(inputTag);
				var inputTagTwo=$('<input type="text" style="width:100%" id="AFB_' + index +'_2" />');
				inputTagTwo.attr('value',data.fieldval2);
				if(data.classNameTwo)
					inputTagTwo.attr('class',data.classNameTwo);
				spanTagTwo.append(inputTagTwo);
				}
			columnOne.append(spanTagOne);	
			columnTwo.append(spanTagTwo);
			row.append(columnOne);
			row.append(columnTwo);
			if(data.fieldval1.trim()!=""){
			  tbody.append(rowNew);
			  }
			tbody.append(row);	
		});
		table.append(tbody);
		return table;
	};
}
function aminoacidLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<div/>');
		var fstDiv = $('<div align="center" class="resultTitle">'+result.testName+'</div>');
		content.append(fstDiv);
	    var table=$('<table id="realTable" style="width:90%"/>');
		var thead=$('<thead/>');
		var headrow=$('<tr>');
		$(result.Resultheaders).each(function(index,headervalue){
		  var headcol=$('<th>'+headervalue.headername+'</th>');
		  headrow.append(headcol);
		});
		thead.append(headrow);
      	table.append(thead);
		var tbody=$('<tbody/>');
		$(result.Resultbody).each(function(index,resultbody){
		var newRow=$('<tr/>');
		var newColumns=$('<td>'+resultbody.headerOneval+'</td>');
		newRow.append(newColumns);
		newColumns=$('<td>');
		if(mode=="print"){
			var lblTag = $('<label/>');
			lblTag.html(resultbody.value);
			newColumns.addClass('middle');
			newColumns.html(lblTag);
		}
		else{
			var inputTag=$('<select type="text" class="Amino tiny" id="Aminoacid' + index +'_1" tabIndex="'+(index+1)+'" value="' + resultbody.value + '"/>');
			newColumns.append(inputTag);
		}	
			newRow.append(newColumns);
			tbody.append(newRow);
		});
		
		table.append(tbody);
		content.append(table);
		var footerDiv = $('<div style="float:left;;margin-left:2%;margin-right:2%; width:100%;"/>');
		var count = 0;
		if(result.footer) {
			$(result.footer.values).each(function (footerIndex, footerdata) {
				if(count%3==0 && count!=0)
					footerDiv.append($('<br/>'));
				var spanTag =$('<div style="margin:0px 10px 0 10px; float:left; min-width:30%" />');	
				if(footerdata.name.trim()=="") 
					return;					
				if(mode=="print") {
					labelTag = $('<label>'+footerdata.value+'</label>');
					spanTag.html(labelTag);
				} else{
					inputTag=$('<input type="text" style="width:100%" class="large"/>');
					inputTag.attr('id',footerdata.name);
					inputTag.attr('value',footerdata.value);
					spanTag.html(inputTag);
				}
				footerDiv.append(spanTag);			
				count++;
			});
		}		
		content.append(footerDiv);
		return content;
		return content;
		};
}
function anaIfa(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div/>');
		var titleSection =$('<div class="resultTitle middle">'+result.testName + '</div>');
		content.append(titleSection);
		var table=$('<div width="100%" id="anaIfa" />');
		$(result.values).each (function(index,paramData){
			if(mode=='print') {
					var container = $('<div class="one"/>');
					var contentTag = $('<div class="one_first_column normal_height columnleftalign" />');
					contentTag.append(paramData.name);
					container.append(contentTag);
					contentTag = $('<div class="one_second_column normal_height columnleftalign"/>');
					contentTag.append(' : ');
					container.append(contentTag);
					container.append(contentTag);
					contentTag = $('<div class="one_third_column normal_height columnleftalign"/>');
					if(paramData.value.trim()=="")
						contentTag.append('&nbsp;');
					else
						contentTag.append(paramData.value);
					container.append(contentTag);
					table.append(container);
			} else {
				var container = $('<div class="one"/>');
				var contentTag = $('<div class="one_first_column normal_height columnleftalign"/>');
				contentTag.append(paramData.name);
				container.append(contentTag);
				contentTag = $('<div class="one_second_column normal_height columnleftalign"/>');
				contentTag.append(' : ');
				container.append(contentTag);
				contentTag = $('<div class="one_third_column normal_height columnleftalign"/>');
				elementTag = $('<input type="text" class="observed" />');
				if(paramData.className)
					elementTag.attr('class',paramData.className);
				if(paramData.value)
					elementTag.attr('value',paramData.value);
				$(elementTag).attr('id',paramData.id);
				contentTag.append(elementTag);
				container.append(contentTag);
				table.append(container);
			}		
		});
		var container = $('<div class="one column_height">&nbsp;</div>');
		table.append(container);
		var container = $('<div class="one middle column_height">INTERPRETATION GUIDELINES (Dilution- 1 : 100)</div>');
		table.append(container);
		var container = $('<div class="one"/>');
		var contentTag = $('<div class="one_first_column normal_height columnleftalign" />');
		contentTag.append('Negative');
		container.append(contentTag);
		contentTag = $('<div class="one_second_column normal_height columnleftalign"/>');
		contentTag.append(' : ');
		container.append(contentTag);			
		contentTag = $('<div class="one_third_column normal_height columnleftalign"/>');
		contentTag.append('No Immunoflourescence');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one"/>');
		var contentTag = $('<div class="one_first_column normal_height columnleftalign" />');
		contentTag.append('+');
		container.append(contentTag);
		contentTag = $('<div class="one_second_column normal_height columnleftalign"/>');
		contentTag.append(' : ');
		container.append(contentTag);
		contentTag = $('<div class="one_third_column normal_height columnleftalign"/>');
		contentTag.append('Weak Positive(1 : 100)');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one"/>');
		var contentTag = $('<div class="one_first_column normal_height columnleftalign" />');
		contentTag.append('++');
		container.append(contentTag);
		contentTag = $('<div class="one_second_column normal_height columnleftalign"/>');
		contentTag.append(' : ');
		container.append(contentTag);				
		container.append(contentTag);
		contentTag = $('<div class="one_third_column normal_height columnleftalign"/>');
		contentTag.append('Moderate Positive( 1 : 320)');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one"/>');
		var contentTag = $('<div class="one_first_column normal_height columnleftalign" />');
		contentTag.append('+++');
		container.append(contentTag);
		contentTag = $('<div class="one_second_column normal_height columnleftalign"/>');
		contentTag.append(' : ');
		container.append(contentTag);				
		container.append(contentTag);
		contentTag = $('<div class="one_third_column normal_height columnleftalign"/>');
		contentTag.append('Moderate Positive( 1 : 320)');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column normal_height columnleftalign" />');
		contentTag.append('++++');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column normal_height columnleftalign" />');
		contentTag.append(' : ');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column normal_height columnleftalign" />');
		contentTag.append('Very Strong Positive(1 :3200');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one middle column_height">TEST DESCRIPTION</div>');
		table.append(container);
		var container = $('<div class="one normal_height">Antinuclear antibodies (ANAs) are unusual antibodies, Detectable in the blood, that have the capacity of binding to certain structues within the nucleus of the cells. ANAs indicate the possible presence of autoimmunity and provide, therefore, an indication of autoimmune illness. Fluorescence techniques are frequently used to detect the anibodies in the cells. Thus ANA testing is sometimes referred to as fluorescent antinuclear antibody test (FANA). The ANA test is a sensitive screening test used to detect autoimmune diseases.</div>');
		table.append(container);
		content.append(table);
		return content;
	};	
}
function ANALayout(honorific,result,testId,mode) {
	this.result =	function(){ 
	var content =$('<div align="center" style="font-weight:bold">'+result.testName+'</div>');
		var table=$('<table id="SpHgtTable" />');
		var thead=$('<thead><th>ANTIGEN</th><th>CLASS</th><th>RESULTS</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			var column=$('<td></td>');
			if(mode=="print"){
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval1);
				column.html(lblTag);
			}
			else{
				var inputTag=$('<input type="text" id="ana_' + index +'_1" class="large" value="' + data.fieldval1 + '"/>');
				if(data.className1)
					inputTag.attr('class',data.className1);	
				column.append(inputTag);
			}
			row.append(column);
			if(mode=="print") {
				if(data.fieldval1.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
			column=$('<td></td>');
			if(mode=="print"){
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval2);
				column.addClass('middle');
				column.html(lblTag);
			}
			else {
				var inputTag=$('<input type="text" id="ana_' + index +'_2" class="large" value="' + data.fieldval2 + '"/>');
				if(data.className2)
					inputTag.attr('class',data.className2);
				column.append(inputTag);
			}
			row.append(column);
				if(mode=="print") {
				if(data.fieldval2.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
			column=$('<td></td>');
		    if(mode=="print"){
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval3);
				column.addClass('middle');
				column.html(lblTag);
			}
			else{
				var inputTag=$('<input type="text" id="ana_' + index +'_3" class="large" value="' + data.fieldval3 + '"/>');
				if(data.className3)
					inputTag.attr('class',data.className3);
				column.append(inputTag);
			}
			row.append(column);
			if(mode=="print") {
				if(data.fieldval3.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
		  });
		  table.append(tbody);
		  content.append(table);
		  return content;
		};
}
function APPTLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var tbody=$('<tbody/>');
		var trow = $('<tr><td style="font-size:100%;font-weight:bold;">INVESTIGATION</td><td style="font-size:100%;font-weight:bold;">OBSERVED VALUE</td><td style="font-size:100%;font-weight:bold;">REFERENCE RANGE&UNIT</td></tr>');
		tbody.append(trow);
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			if(data.FieldOneName){
				if(data.FieldOneName!=""){
					var column=$('<td style="width:39%;padding-left:5px;">'+data.FieldOneName+'</td>');
					row.append(column);
				}
			}
			if(index==1){
		        var column=$('<td>&nbsp;</td>');
				 row.append(column);
			}
			var column=$('<td style="width:28%;padding-left:5px;"/>');
			var spanTag=$('<div style="width:32%;float:left;">'+data.FieldTwoName+'</div>');
			column.append(spanTag);
			spanTag=$('<div style="width:5%;float:left;">:</div>');
		    column.append(spanTag);
			spanTag=$('<div style="width:32%;float:left;"/>');
		   if(mode=="print") {
				var lblTag = $('<label />');
				lblTag.html(data.FieldVal +" "+data.unitVal );
				spanTag.append(lblTag);
			} else{
				var inputTag=$('<input type="text"  id="APPT_' + index +'_1" class="large" value="' + data.FieldVal + '"/>');
				spanTag.append(inputTag);
			}
		    column.append(spanTag);
			spanTag=$('<div style="width:20%;float:left;"></div>');
			if(data.time){
				 if(mode=="print") {
					var lblTag = $('<label />');
					lblTag.html(data.UnitType);
					spanTag.append(lblTag);
				} else{
					var inputTag=$('<input type="text" class="'+data.time+'" value="'+data.unitVal+'" id="APPT_'+index+'_2"/>');
					spanTag.append(inputTag);
					if(data.UnitType)
						spanTag.html(data.UnitType);
				}	
			}			
		   	column.append(spanTag);
		   	row.append(column);
		  	if(data.FieldThreeName){
			   	if(data.FieldThreeName!=""){
					var column=$('<td style="width:33%;padding-left:5px;">'+data.FieldThreeName+'</td>');
					row.append(column);
			   } 
		    } 
			if(index==1){
				var column=$('<td>&nbsp;</td>');
				row.append(column);
			}
		  	tbody.append(row);
		});
		return tbody;
	};
}
function CDLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div align="center" style="font-weight:bold">'+result.testName+'</div>');
		var table=$('<table width="100%" id="SpHgtTable" />');
		var thead=$('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th><th>REFERENCE RANGE&UNIT</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			var column=$('<td></td>');
			if(mode=="print"){
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval1);
				column.html(lblTag);
			}
			else {
				var inputTag=$('<input type="text" style="width:100%" id="CD_' + index +'_1" class="large" value="' + data.fieldval1 + '"/>');
				if(data.className1)
					inputTag.attr('class',data.className1);
				column.append(inputTag);
			}
			row.append(column);
			if(mode=="print") {
				if(data.fieldval1.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
			column=$('<td></td>');
			if(mode=="print"){
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval2);
				column.addClass('middle');
				column.html(lblTag);
			}
			else {
				var inputTag=$('<input type="text" style="width:100%" id="CD_' + index +'_2" class="large" value="' + data.fieldval2 + '"/>');
				if(data.className2)
					inputTag.attr('class',data.className2);
				column.append(inputTag);
			}
			row.append(column);
			if(mode=="print") {
				if(data.fieldval2.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
			column=$('<td></td>');
			if(mode=="print"){
				var lblTag = $('<label/>');
				column.addClass('middle');
				lblTag.html(data.fieldval3);
				column.html(lblTag);
			}
			else {
				var inputTag=$('<input type="text" style="width:100%" id="CD_' + index +'_3" class="large" value="' + data.fieldval3 + '"/>');
			    if(data.className3)
					inputTag.attr('class',data.className3);
			    column.append(inputTag);
			}
			row.append(column);
			if(mode=="print") {
				if(data.fieldval3.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
		  });
		table.append(tbody);
		content.append(table);
		return content;
		};
          
}
function cultureLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<tbody class="noBorder"/>');
		var trRootTag = $('<tr/>');
		var tdRootTag = $('<td />');
		var headerTable = $('<table width="100%" class="cultureTable_Head">');	
		var theadParentTag = $('<thead>');
		var trParentTag = $('<tr/>');
		var thParentTag = $('<th colspan="4">');
		thParentTag.html(result.analysis.resultTitle);
		trParentTag.html(thParentTag);
		theadParentTag.append(trParentTag);
		headerTable.append(theadParentTag);
		var tbodyParentTag = $('<tbody>');
		var trParentTag = $('<tr/>');
		var tdParentTag = $('<td colspan="2">');
		var labelTag = $('<label/>');
		labelTag.html(result.analysis.organismGrownLabel);
		tdParentTag.append(labelTag);
		if(mode=='print') {
			var lblTag = $('<label/>');
			if(result.analysis.organismGrownValue)
				lblTag.html(result.analysis.organismGrownValue);
			tdParentTag.append(lblTag);
		} else {
			var inputPTag=$('<input type="text" class="cultureList large" id="organismValue" style="width:86%"/>');
			if(result.analysis.organismGrownValue)
				inputPTag.attr('value',result.analysis.organismGrownValue);
			tdParentTag.append(inputPTag);
		}		
		trParentTag.html(tdParentTag);	
		tbodyParentTag.append(trParentTag);
		trParentTag = $('<tr/>');
		tdParentTag = $('<td style="width:50%"/>');
		spanPTag = $('<span>1). </span>');
		tdParentTag.append(spanPTag);
		spanPTag = $('<span/>');
		if(mode=='print') {
			var lblTag = $('<label/>');
			if(result.analysis.columnValue1)
				lblTag.html(result.analysis.columnValue1+result.analysis.columnValue1Sub);
			spanPTag.html(lblTag);
			tdParentTag.append(spanPTag);
		} else {
			inputPTag=$('<input type="text" class="antibiotics" id="columnValue1" style="width:70%"/>');
			 if(result.analysis.columnValue1)
				inputPTag.attr('value',result.analysis.columnValue1); 
			spanPTag.append(inputPTag);
			var inputPTag2=$('<select class="raise2"  align="right;" id="columnValue1Sub" style="width:20%"/>');
			 if(result.analysis.columnValue1Sub)
				inputPTag2.attr('value',result.analysis.columnValue1Sub);
				spanPTag.append(inputPTag2);
			   tdParentTag.append(spanPTag);
		}	
		trParentTag.append(tdParentTag);	
		tdParentTag = $('<td style="width:50%"/>');
		spanPTag = $('<span>2). </span>');
		tdParentTag.append(spanPTag);
		spanPTag = $('<span/>');
		if(mode=='print') {
			var lblTag = $('<label/>');
			if(result.analysis.columnValue2)
				lblTag.html(result.analysis.columnValue2+result.analysis.columnValue2Sub); 
			spanPTag.html(lblTag);
			tdParentTag.append(spanPTag);
		} else {
			inputPTag=$('<input type="text" class="antibiotics " id="columnValue2" style="width:70%"/>');
			 if(result.analysis.columnValue2)
				inputPTag.attr('value',result.analysis.columnValue2);
			spanPTag.append(inputPTag);
			var inputPTag2=$('<select class="raise2"  align="right;"id="columnValue2Sub" style="width:20%"/>');
			 if(result.analysis.columnValue2Sub)
				inputPTag2.attr('value',result.analysis.columnValue2Sub);
				spanPTag.append(inputPTag2);
			   tdParentTag.append(spanPTag);
		}	
		trParentTag.append(tdParentTag);
		tbodyParentTag.append(trParentTag);
		headerTable.append(tbodyParentTag);
		tdRootTag.append(headerTable);
		var tblDiv = $('<div/>');
		var resultTable = $('<table id="cultureresultTable" style="float:left; margin-left:40px; width:90%" />');
		var theadTag = $('<thead/>');
		var trTag=$('<tr />');	
		for(i=0;i<2;i++) {
			$(result.analysis.columns).each(function(index, column) {
				var thTag = $('<th />');
				thTag.append(column);
				trTag.append(thTag);
			});
		}	
		theadTag.append(trTag);	
		resultTable.append(theadTag);
		var tbodyTag = $('<tbody/>');
		var length=result.analysis.data.length;
		var trTag;
		var tabIndex=0;
		var tabIndex1=0; 
		var tabIndex2=0; 
		var tabIndex3=0;
		$(result.analysis.data).each(function(index,data) {
			if(index%2==0){
				trTag=$('<tr />');		
					tabIndex1++;
					tabIndex=tabIndex1;
			}else {
				tabIndex2=tabIndex1 + 20;
				tabIndex=tabIndex2;
			}
			var tdTag;
			if(mode=="print") 
				tdTag = $('<td style="width:34%"><label>'+data.name+'</label></td>');
			else
			tdTag = $('<td style="width:34%"><input type="text"  value="'+data.name+'" id="' + data.idProperty + '_0" tabIndex=' + tabIndex + '></td>');
			//tdTag.append(data.name);
			trTag.append(tdTag);
			tdTag = $('<td style="width:8%;text-align:center;"/>');
			if(mode=="print") 
				inputTag = $('<label class="middle">'+data.value1+'</label>');
			else{
				inputTag=$('<select class="number" tabIndex=' + (tabIndex+40)+ '/>');
				inputTag.attr('id',data.idProperty+"_1");
				inputTag.addClass('middle');
				inputTag.attr('value',data.value1);
			}
			tdTag.append(inputTag);
			trTag.append(tdTag);			
			tdTag = $('<td style="width:8%;text-align:center;"/>');
			if(mode=="print") 
				inputTag = $('<label class="middle">'+data.value2+'</label>');
			else{
				inputTag=$('<select class="number" tabIndex=' + ((tabIndex+40)*2) +'/>');
				inputTag.attr('id',data.idProperty+"_2");
				inputTag.addClass('middle');
				inputTag.attr('value',data.value2);
			}
			tdTag.append(inputTag);
			trTag.append(tdTag);
				
			if((index+1)%2==0 || length==(index+1))
				tbodyTag.append(trTag);
		});
		resultTable.append(tbodyTag);
		tblDiv.append(resultTable);		
		tdRootTag.append(tblDiv);			
		var footerDiv = $('<div style="float:left;;margin-left:2%;margin-right:2%; width:100%;"/>');
		var count = 0;
		if(result.analysis.footer) {
			$(result.analysis.footer.values).each(function (footerIndex, footerdata) {
				if(count%3==0 && count!=0)
					footerDiv.append($('<br/>'));
				var spanTag =$('<div style="margin:0px 10px 0 10px; float:left; min-width:30%" />');	
				if(footerdata.name.trim()=="") 
					return;					
				if(mode=="print") {
					labelTag = $('<label>'+footerdata.value+'</label>');
					spanTag.html(labelTag);
				} else{
					inputTag=$('<input type="text" style="width:100%" class="large"/>');
					inputTag.attr('id',footerdata.name);
					inputTag.attr('value',footerdata.value);
					spanTag.html(inputTag);
				}
				footerDiv.append(spanTag);			
				count++;
			});
		}		
		tdRootTag.append(footerDiv);
		trRootTag.append(tdRootTag);
		content.append(trRootTag);
		return content;
	};	
}
function DCLayout(result, testId, mode,honorific) {
	this.result =	function(){ 
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			var column=$('<td ></td>');
			if(mode=="print") {
				var lblTag = $('<label/>');
				lblTag.html(data.key);
				column.html(lblTag);
			} else {	
				var inputTag=$('<input type="text" id="DC_' + index +'_1"  value="' + data.key + '"/>');
				if(data.className1)
					inputTag.attr('class',data.className1);
				column.append(inputTag);
			}	
			row.append(column);
			if(mode=="print") {
				if(data.key.trim()!="")
					tbody.append(row);
			}	
			else
				tbody.append(row);			
				column=$('<td class="middle"></td>');
				if(mode=="print") {
					var lblTag = $('<label/>');
					lblTag.html(data.fieldvaltwo + " " + data.unit);
					column.html(lblTag);
				} else {	
					var inputTag=$('<input type="text" class="observed" style="float:left;" id="DC_' + index +'_2" tabindex="' + (index +1)+'" value="' + data.fieldvaltwo + '"/>');
					var label=$('<label >' + data.unit + '</label>');					
					column.append(inputTag);
					column.append(label);
				}	
				row.append(column);		
				if(mode=="print") {
					if(data.key.trim()!="")
						tbody.append(row);
				}	
				else
					tbody.append(row);
				if(honorific!='Animal') {
					column=$('<td class="middle"></td>');
					if(mode=="print") {
						var lblTag = $('<label/>');
						lblTag.html(data.fieldvalthree+" " + data.unit);
						column.html(lblTag);
					} else {	
						var inputTag=$('<input type="text"  style="float:left;" id="DC_' + index +'_3"  value="' + data.fieldvalthree + '"/>');
						var label=$('<label >' + data.unit + '</label>');
						if(data.className3)
							inputTag.attr('class',data.className3);
						column.append(inputTag);
						column.append(label);
					}	
					row.append(column);
					if(mode=="print") {
						if(data.key.trim()!=""){
							tbody.append(row);
						}
					}	
					else
						tbody.append(row);	
				}		
		}); 
		return tbody;
	};
}
function DCLayoutHeader(honorific) {
	this.result =function(){
		var headTag='<thead><tr><th style="width:40%" class="head1" >INVESTIGATION</th><th class="head1" >OBSERVED VALUE</th>';
		if(honorific!='Animal')
			headTag+='<th class="head1" >REFERENCE RANGE&amp;UNIT</th>';
		headTag+='</tr></thead>';
		var theadTag =$(headTag);
		return theadTag;
	};	
}
function DCLayoutMed(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div/>');
		var titleSection =$('<div class="one singleheight bold underline">'+result.testName + '</div>');
		content.append(titleSection);
		var table=$('<div width="100%" id="dcTable" />');
		$(result.values).each (function(index,paramData){
			if(mode=='print') {
				if(paramData.value.trim()!="") {
					var container = $('<div class="one singleheight">&nbsp;</div>');
					var contentTag = $('<div class="onefirst">&nbsp;</div>');
					contentTag.empty().html(paramData.name);
					container.empty().html(contentTag);
					contentTag = $('<div class="onesecond">&nbsp;</div>');
					if(paramData.value!="")
						contentTag.empty().html(paramData.value);
					if(paramData.unit){
						var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
						spanTag.append(paramData.unit);
						contentTag.append(spanTag);	
					}
					container.append(contentTag);
					contentTag = $('<div class="onesecond">&nbsp;</div>');
					if(paramData.normal!="")
						contentTag.empty().html(paramData.normal);
					container.append(contentTag);
					table.append(container);
				}
			} else {
				var container = $('<div class="one singleheight">&nbsp;</div>');
				var contentTag = $('<div class="onefirst">&nbsp;</div>');
				contentTag.append(paramData.name);
				container.append(contentTag);
				contentTag = $('<div class="onesecond">&nbsp;</div>');
				elementTag = $('<input type="text" class="observed" />');
				if(paramData.className)
					elementTag.attr('class',paramData.className);
				if(paramData.value)
					elementTag.attr('value',paramData.value);
				$(elementTag).attr('id',paramData.id);
				contentTag.append(elementTag);
				if(paramData.unit)
					contentTag.append(paramData.unit);	
				container.append(contentTag);
				contentTag = $('<div class="onesecond">&nbsp;</div>');
				if(paramData.normal!="")
					contentTag.empty().html(paramData.normal);
				container.append(contentTag);
				table.append(container);
			}	
		});
		content.append(table);
		return content;
	};
}
function enaProfile(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<div/>');
		var fstDiv = $('<div  align="center" class="resultTitle">'+result.testName+'</div>');
		content.append(fstDiv);
		var SecDiv = $('<div class="five_sixth"/>');
	    var table=$('<table id="realTable"/>');
		var thead=$('<thead/>');
		var headrow=$('<tr>');
		$(result.Resultheaders).each(function(index,headervalue){
		  var headcol=$('<th>'+headervalue.headername+'</th>');
		  headrow.append(headcol);
		});
		thead.append(headrow);
      	table.append(thead);
		var tbody=$('<tbody/>');
		$(result.Resultbody).each(function(index,resultbody){
		var newRow=$('<tr/>');
		var newColumns=$('<td>'+resultbody.headerOneval+'</td><td> '+resultbody.headerTwoval+'</td>');
		newRow.append(newColumns);
		var newColumns=$('<td/>');
		var inputTag=$('<input type="text" id="Ena_' + index + '_1" value="'+resultbody.headerThreeval+'"/>');
		 if(mode=="print") {
						inputTag.addClass('readonlyStyle');
						inputTag.attr('readonly','readonly');
						inputTag.removeClass('middle');
						inputTag.addClass('column-2');
					}	
		newColumns.append(inputTag);
		newRow.append(newColumns);
		tbody.append(newRow);
		});	
		table.append(tbody);
		SecDiv.append(table);
		content.append(SecDiv);
		return content;	
		};
}
function foodAllergy(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div/>');
		var titleSection =$('<div class="resultTitle middle">'+result.testName + '</div>');
		content.append(titleSection);
		var table=$('<div width="100%" id="foodAllergy" />');
		var container = $('<div class="one smallheight" />');
		var contentTag = $('<div class="onethird foodallergy_onethird_border middle bold" />');
		contentTag.append('&nbsp;');
		container.append(contentTag);
		var contentTag = $('<div class="onethird foodallergy_onethird_border middle bold" />');
		contentTag.append('CONCENTRATION');
		container.append(contentTag);
		var contentTag = $('<div class="onethird foodallergy_onethird_border middle bold" />');
		contentTag.append('CLASS');
		container.append(contentTag);
		table.append(container);
		$(result.values).each (function(index,paramData){
			if(mode=='print') {
				if(paramData.value.trim()!="") {
					var container = $('<div class="one smallheight"/>');
					var contentTag = $('<div class="onethird foodallergy_onethird_border" />');
					contentTag.append(paramData.name);
					container.append(contentTag);
					contentTag = $('<div class="onethird foodallergy_onethird_border"/>');
					contentTag.addClass('middle');
					contentTag.append(paramData.value);
					if(paramData.unit){
						var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
						spanTag.append(paramData.unit);
						contentTag.append(spanTag);	
					}
					container.append(contentTag);
					contentTag = $('<div class="onethird foodallergy_onethird_border"/>');
					contentTag.addClass('middle');
					contentTag.append(paramData.classvalue);
					container.append(contentTag);
					table.append(container);
				}
			} else {
				var container = $('<div class="one"/>');
				var contentTag = $('<div class="onethird foodallergy_onethird_border"/>');
				contentTag.append(paramData.name);
				container.append(contentTag);
				contentTag = $('<div class="onethird foodallergy_onethird_border"/>');
				elementTag = $('<input type="text" class="observed" />');
				if(paramData.className)
					elementTag.attr('class',paramData.className);
				if(paramData.value)
					elementTag.attr('value',paramData.value);
				$(elementTag).attr('id',paramData.id);
				contentTag.append(elementTag);
				if(paramData.unit)
					contentTag.append(paramData.unit);	
				container.append(contentTag);
				contentTag = $('<div class="onethird foodallergy_onethird_border"/>');
				elementTag = $('<input type="text" class="observed" />');
				if(paramData.classvalue)
					elementTag.attr('value',paramData.classvalue);
				$(elementTag).attr('id',paramData.classid);
				contentTag.append(elementTag);
				container.append(contentTag);
				table.append(container);
			}	
		});
		var container = $('<div class="one smallheight">&nbsp;</div>');
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('Concentration [k/U/I]');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('Class');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('Explanation');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('< 0.35');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('0');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border columnleftalign" />');
		contentTag.append('No specific antibodies detected');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('0.35 - 0.7');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('1');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border columnleftalign" />');
		contentTag.append('Very weak antibody detection, often no clinical symptoms with an existing sensitization');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('0.7 - 3.5');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('2');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border columnleftalign" />');
		contentTag.append('Weak antibody detection, existing sensitization, often with clinical symptoms in upper levels');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('3.5 - 17.5');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('3');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border columnleftalign" />');
		contentTag.append('Definite antibody detection, clinical symptoms are often present');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('17.5 - 50');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('4');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border columnleftalign" />');
		contentTag.append('Strong antibody detection, almost always with existing symptoms');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('50 - 100');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('5');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border columnleftalign" />');
		contentTag.append('Very high antibody titer');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('>100');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('6');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border columnleftalign" />');
		contentTag.append('Very high antibody titer');
		container.append(contentTag);
		table.append(container);
		content.append(table);
		return content;
	};	
}
function NVGCTLayout(result, testId, mode,honorific) {
	this.result =	function(){ 
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			var column=$('<td style="vertical-align:middle"></td>');
			if(mode=="print") {
				if(data.name=="")
					return;
				var lblTag = $('<label/>');
				lblTag.html(data.name);
				column.html(lblTag);
			} else {	
				var inputTag=$('<input type="text" id="GCT_' + index +'_1"  value="' + data.name + '"/>');
				if(data.className)
					inputTag.attr('class',data.className);
				column.append(inputTag);
			}	
			row.append(column);
			if(mode=="print") {
				if(data.name.trim()!="")
					tbody.append(row);
			}	
			else
				tbody.append(row);			
				column=$('<td class="middle"></td>');
				if(mode=="print") {
					var lblTag = $('<label/>');
					lblTag.html(data.current + " " + data.unit);
					column.html(lblTag);
				} else {	
					var inputTag=$('<input type="text" class="observed" style="float:left;" id="GCT_' + index +'_2" tabindex="' + (index +1)+'" value="' + data.current + '"/>');
					var label=$('<label >' + data.unit + '</label>');					
					column.append(inputTag);
					column.append(label);
				}	
				row.append(column);
				
				if(mode=="print") {
					if(data.name.trim()!="")
						tbody.append(row);
				}	
				else
					tbody.append(row);
				if(honorific!='Animal') {
					column=$('<td class="middle"></td>');
					if(mode=="print") {
						var lblTag = $('<label/>');
						lblTag.html(data.normal+" " + data.unit);
						column.html(lblTag);
					} else {	
						var inputTag=$('<input type="text"  style="float:left;" id="GCT_' + index +'_3"  value="' + data.normal + '"/>');
						var label=$('<label >' + data.unit + '</label>');
						if(data.className3)
							inputTag.attr('class',data.className3);
						column.append(inputTag);
						column.append(label);
					}	
					row.append(column);
					if(mode=="print") {
						if(data.name.trim()!=""){
							tbody.append(row);
						}
					}	
					else
						tbody.append(row);	
				}		
		}); 
		return tbody;
	};
}
function generalLayout(testResult, testId, mode,honorific,order) {
	this.result =	function(){ 
		if(testId==null || testId=="")
			testId=0;
		var response = $('<tbody/>');
		response.attr('name','tbody_'+testId);	
		var status=true;	
		if(honorific!='Animal') {
			$(testResult.analysis).each(function(analysisIndex,analysis) {
				$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
					$(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
						if(resultparam.key){
							if(status==true) {
								var trTag = $('<tr/>');
								var tdTag = $('<td style="vertical-align:middle"/>');
								tdTag.attr('colspan',resultparam.values.length+1);
								tdTag.html(testResult.testName);	
								trTag.append(tdTag);
								response.append(trTag);
								status=false;
							}						
						}
						var trTag = $('<tr/>');
						trTagId = testId + '_' + analysisIndex + "_" + resultcontentIndex + "_" + resultparamIndex;
						trTag.attr('id',trTagId);
						var tdTag= $('<td style="width:40%;vertical-align:middle"/>');
						if(resultparam.key)
							tdTag.html(resultparam.key);
						else {					
							tdTag.html(testResult.testName);
						}	
						trTag.append(tdTag);
						$(resultparam.values).each(function(paramvaluesIndex,paramValues) {
							$(paramValues.values).each(function(paramdataIndex,paramData) {
								var tdTag= $('<td style="vertical-align:middle" class="middle"/>');
								if(paramValues.headervalue=='NormalRange') 					
										tdTag.attr('style',"width:35%");
								var tblTag = $('<table width="100%"/>');
								if(paramData.values) {				
									$(paramData.values).each(function(paramdataValueIndex,paramDataValues){
										var newrow = $('<tr/>');
										if(paramDataValues.normalRangeAttribute) {	
											var colTag = $('<td style="vertical-align:middle;"  class="middle"/>');
											colTag.append(paramDataValues.normalRangeAttribute);	
											newrow.append(colTag);								
										}
										if(paramDataValues.values) {
											var colTag = $('<td class="middle"/>');
											var innerTblTag = $('<table width="100%"/>');
											$(paramDataValues.values).each(function(paramdataValueInnerIndex,paramDataValuesInner){
												var newrowInner = $('<tr/>');
												if(paramDataValuesInner.normalRangeAttribute) {	
													var colTag = $('<td class="middle"/>');
													colTag.append(paramDataValuesInner.normalRangeAttribute);	
													newrowInner.append(colTag);								
												}
												if(paramDataValuesInner.value) {
													var colTag = $('<td class="middle"/>');
													colTag.html(paramDataValuesInner.value);
													if(paramDataValuesInner.unit){
														var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
														spanTag.append(paramDataValuesInner.unit);
														colTag.append(spanTag);	
													}
													newrowInner.append(colTag);	
												}
												innerTblTag.append(newrowInner);
											});				
											colTag.append(innerTblTag);
											newrow.append(colTag);
										} else {
											if(paramDataValues.value) {
												var colTag = $('<td class="middle"/>');
												colTag.html(commonMethodInvoker.nl2br(paramDataValues.value));
												if(paramDataValues.unit){
													var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
													spanTag.append(paramDataValues.unit);
													colTag.append(spanTag);	
												}
												newrow.append(colTag);	
											}	
										}
										tblTag.append(newrow);
									});
								} else {
									var newrow = $('<tr/>');
									if(paramData.normalRangeAttribute) {	
										var colTag = $('<td />');
										colTag.append(paramData.normalRangeAttribute);	
										newrow.append(colTag);								
									}
									if(paramData.displayType) {	
										var colTag = $('<td />');
										if(paramData.headervalue=='NormalRange') {
											tdTag.attr('style',"max-width:38%;min-width:35%");
										}
										if(paramData.displayType=='text') {
											var elementTag;
											var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
											if(mode=='print') {
												colTag.addClass('middle');
												elementTag = $('<label />')
												if(paramData.value)
													elementTag.text(paramData.value);
											} else {
												elementTag = $('<input type="text" class="observed" style="float:left;"/>');
												if(paramData.className)
													elementTag.attr('class',paramData.className);
												if(paramData.value)
													elementTag.attr('value',paramData.value);
												colTag.attr('style',"padding:0px");	
											}	
											$(elementTag).attr('id',txtid);					
											colTag.append(elementTag);
											if(paramData.unit){
												var spanTag = $('<span style="padding:0px 0px 0px 5px;"/>');
												spanTag.append(paramData.unit);
												colTag.append(spanTag);	
											}
										}
										newrow.append(colTag);
									} else {	
										var curNormalRange = methodInvoker.getCurrentNormalRange($.parseJSON(order.headerData), testResult);
										if(curNormalRange!=""){
												var colTag = $('<td />');
												if(curNormalRange.length>60)
													colTag.addClass('leftalign');
												else
													colTag.addClass('middle');
												colTag.html(commonMethodInvoker.nl2br(curNormalRange));				
												newrow.append(colTag);
										} else{
											if(paramData.value) {			
												var colTag = $('<td />');
												if(paramData.value.length>60)
													colTag.addClass('leftalign');
												else
													colTag.addClass('middle');
												var normalValue = commonMethodInvoker.nl2br(paramData.value);
												normalValue  = normalValue.replaceAll(" ", "&nbsp;");
												colTag.html(normalValue);
												if(paramData.unit){
													var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
													spanTag.append(paramData.unit);
													colTag.append(spanTag);	
												}										
												newrow.append(colTag);
											}
										}
									}	
									tblTag.append(newrow);
								}														
								tdTag.append(tblTag);
								trTag.append(tdTag);							
							});
						});
						response.append(trTag);
					});
				});
			});
		} else {
			$(testResult.analysis).each(function(analysisIndex,analysis) {
				$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
					$(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
						var trTag = $('<tr/>');
						trTagId = testId + '_' + analysisIndex + "_" + resultcontentIndex + "_" + resultparamIndex;
						trTag.attr('id',trTagId);
						var tdTag= $('<td style="width:40%;vertical-align:middle"/>');
						tdTag.html(testResult.testName);	
						trTag.append(tdTag);
						$(resultparam.values).each(function(paramvaluesIndex,paramValues) {
							$(paramValues.values).each(function(paramdataIndex,paramData) {
								var tdTag= $('<td style="vertical-align:middle" class="middle"/>');
								if(paramValues.headervalue!='NormalRange') { 					
									var tblTag = $('<table width="100%"/>');
									if(paramData.values) {				
										$(paramData.values).each(function(paramdataValueIndex,paramDataValues){
											var newrow = $('<tr/>');
											if(paramDataValues.normalRangeAttribute) {	
												var colTag = $('<td style="vertical-align:middle;"  class="middle"/>');
												colTag.append(paramDataValues.normalRangeAttribute);	
												newrow.append(colTag);								
											}
											if(paramDataValues.values) {
												var colTag = $('<td class="middle"/>');
												var innerTblTag = $('<table width="100%"/>');
												$(paramDataValues.values).each(function(paramdataValueInnerIndex,paramDataValuesInner){
													var newrowInner = $('<tr/>');
													if(paramDataValuesInner.normalRangeAttribute) {	
														var colTag = $('<td class="middle"/>');
														colTag.append(paramDataValuesInner.normalRangeAttribute);	
														newrowInner.append(colTag);								
													}
													if(paramDataValuesInner.value) {
														var colTag = $('<td class="middle"/>');
														colTag.html(paramDataValuesInner.value);
														if(paramDataValuesInner.unit){
															var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
															spanTag.append(paramDataValuesInner.unit);
															colTag.append(spanTag);	
														}
														newrowInner.append(colTag);	
													}
													innerTblTag.append(newrowInner);
												});				
												colTag.append(innerTblTag);
												newrow.append(colTag);
											} else {
												if(paramDataValues.value) {
													var colTag = $('<td class="middle"/>');
													colTag.html(commonMethodInvoker.nl2br(paramDataValues.value));
													if(paramDataValues.unit){
														var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
														spanTag.append(paramDataValues.unit);
														colTag.append(spanTag);	
													}
													newrow.append(colTag);	
												}	
											}
											tblTag.append(newrow);
										});
									} else {
										var newrow = $('<tr/>');
										if(paramData.normalRangeAttribute) {	
											var colTag = $('<td />');
											colTag.append(paramData.normalRangeAttribute);	
											newrow.append(colTag);								
										}
										if(paramData.displayType) {	
											var colTag = $('<td />');									
											if(paramData.displayType=='text') {
												var elementTag;
												var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
												if(mode=='print') {
													colTag.addClass('middle');
													elementTag = $('<label />')
													if(paramData.value)
														elementTag.text(paramData.value);
												} else {
													elementTag = $('<input type="text" class="observed" style="float:left;"/>');
													if(paramData.className)
														elementTag.attr('class',paramData.className);
													if(paramData.value)
														elementTag.attr('value',paramData.value);
													colTag.attr('style',"padding:0px");	
												}	
												$(elementTag).attr('id',txtid);					
												colTag.append(elementTag);
												if(paramData.unit){
													var spanTag = $('<span style="padding:0px 0px 0px 5px;"/>');
													spanTag.append(paramData.unit);
													colTag.append(spanTag);	
												}
											}
											newrow.append(colTag);
										} else {				
											if(paramData.value) {			
												var colTag = $('<td />');
												if(paramData.value.length>60)
													colTag.addClass('leftalign');
												else
													colTag.addClass('middle');
												var normalValue = commonMethodInvoker.nl2br(paramData.value);
												normalValue  = normalValue.replaceAll(" ", "&nbsp;");
												colTag.html(normalValue);
												if(paramData.unit){
													var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
													spanTag.append(paramData.unit);
													colTag.append(spanTag);	
												}										
												newrow.append(colTag);
											}
										}	
										tblTag.append(newrow);
									}														
									tdTag.append(tblTag);
									trTag.append(tdTag);	
								}		
							});
						});
						response.append(trTag);		
					});
				});
			});		
		}		
		return response;
	};
}
function generalLayoutHeader(result,honorific) {
	this.result =	function(){ 
		var theadTag = $('<thead/>');
		if(result.analysis){
			$(result.analysis).each(function(index,analysis) {
				$(analysis.resultContent).each(function(index1,rescont) {					
					var trTag=$('<tr/>');		
					$(rescont.resultParams).each(function(index2,resparam) {				
						var thTag = $('<th style="width:40%" class="head1">INVESTIGATION</th>');
						trTag.append(thTag);	
						$(resparam.values).each(function(index2,elemnt) {					
							if(honorific=="Animal" && elemnt.headervalue=="NormalRange") {
							}else{
								var thTag = $('<th class="head1" class="middle"/>');
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
function generalLayoutMed(testResult, testId, mode, honorific, order) {
	this.result =	function(){ 
		if(testId==null || testId=="")
			testId=0;
		var response = $('<div/>');
		response.attr('name','tbody_'+testId);
		var status=true;
		$(testResult.analysis).each(function(analysisIndex,analysis) {
			$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
				$(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
					var trTag = $('<div class="one">&nbsp;</div>');
					trTagId = testId + '_' + analysisIndex + "_" + resultcontentIndex + "_" + resultparamIndex;
					trTag.attr('id',trTagId);
					var tdTag = $('<div class="onefirst">&nbsp;</div>');
					tdTag.empty().html(testResult.testName);	
					trTag.empty().html(tdTag);	
					$(resultparam.values).each(function(paramvaluesIndex,paramValues) {
						$(paramValues.values).each(function(paramdataIndex,paramData) {
							var tdTag= $('<div>&nbsp;</div>');
							if(paramData.displayType=='text') {
								var elementTag;
								var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
								if(mode=='print') {
									trTag.addClass('singleheight');
									elementTag = $('<label>&nbsp;</label>');
									if(paramData.value)
										elementTag.text(paramData.value);
								} else {
									elementTag = $('<input type="text" class="observed" />');
									if(paramData.className)
										elementTag.attr('class',paramData.className);
									if(paramData.value)
										elementTag.attr('value',paramData.value);
									$(elementTag).attr('id',txtid);
								}
								tdTag.append(elementTag);
								if(paramData.unit){
									var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
									spanTag.append(paramData.unit);
									tdTag.append(spanTag);	
								}
								tdTag.addClass('onesecond');
								trTag.append(tdTag);
							} else if(paramData.displayType=='textarea'){
								tdTag.addClass('onesecond');
								var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
								if(mode=='print'){
									if(paramData.value){
										var normalValue = commonMethodInvoker.nl2br(paramData.value);
										normalValue  = normalValue.replaceAll(" ", "&nbsp;");
										tdTag.html(normalValue);
									}
									trTag.append(tdTag);
								}else {
									var textarea = $('<textarea class="valueTextArea" />');
									textarea.attr('id',txtid);
									var curNormalRange = methodInvoker.getCurrentNormalRange(order, testResult);
									if(curNormalRange!="")
										textarea.html(commonMethodInvoker.br2nl(curNormalRange));
									else
										textarea.html(commonMethodInvoker.br2nl(paramData.value));
									tdTag.append(textarea);
									trTag.append(tdTag);
								}
							} else {
								if(paramData.value) {
									if(paramData.value.trim()!="")
									tdTag.html(paramData.value);
									if(paramData.unit){
										var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
										spanTag.append(paramData.unit);
										tdTag.append(spanTag);	
									}			
								} 
								tdTag.addClass('onesecond');
								trTag.append(tdTag);
							}	
						});
					});
					response.append(trTag);	
				});	
			});
		});		
		return response.html();
	};
}
function generalOneLayout(testResult,testId,mode,honorific) {
	this.result =	function(){
		var resultTBody = $('<tbody/>');
		resultTBody.attr('name','tbody_'+testId);		
		if(honorific!="Animal"){
			$(testResult.analysis).each(function(analysisIndex,analysis) {
			$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
			$(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
				var trTag = $('<tr />');
				trTagId = testId + '_' + analysisIndex + "_" + resultcontentIndex + "_" + resultparamIndex;
				trTag.attr('id',trTagId);
				tdTag= $('<td style="width:40%;vertical-align:middle" />'); // First column for investigation
				tdTag.html(testResult.testName);
				//tdTag.attr('style','font-weight:bold;');
				trTag.append(tdTag); // Added Investigation Name to first column
				//Iterating through contents
				$(resultparam.values).each(function(paramvaluesIndex,paramValues) {
					//Creating the next columns like observed value, normal range etc...
					tdTag= $('<td class="middle" style="vertical-align:middle"/>');					
					if(paramValues.values) {
						$(paramValues.values).each(function(paramdataIndex,paramDataValues){
							if(paramValues.headervalue=='NormalRange') {
								tdTag.removeClass('middle');
								tdTag.attr('style',"width:35%");
							}
							if(mode=='print') {
								if(paramValues.headervalue!='NormalRange') {
									elementTag = $('<label />')
									if(paramDataValues.value)
										elementTag.text(commonMethodInvoker.nl2br(paramDataValues.value));
									tdTag.html(elementTag);
									if(paramDataValues.unit){
										var spanTag = $('<span style="padding:0px 0px 0px 5px"/>');
										spanTag.append(paramDataValues.unit);
										tdTag.append(spanTag);	
									}
								} else {
									var normalValue = commonMethodInvoker.nl2br(paramDataValues.value);
									normalValue  = normalValue.replaceAll(" ", "&nbsp;");
									tdTag.html(normalValue);
								}
							} else {
								var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
								if(paramValues.headervalue=='NormalRange') {
									var innerTblTag = $('<table width="100%"/>');
									var newrowInner = $('<tr/>');
									var columnTag = $('<td class="middle"/>');
									selectTag=$('<select class="select normalRange" style="width:100%;" >');
									columnTag.append(selectTag);
									newrowInner.append(columnTag);
									innerTblTag.append(newrowInner);
									newrowInner = $('<tr/>');
									columnTag = $('<td class="middle"/>');
									var textarea = $('<textarea class="normalVal" />');
									textarea.attr('id',txtid);
									textarea.html(commonMethodInvoker.br2nl(paramDataValues.value));
									columnTag.append(textarea);
									newrowInner.append(columnTag);
									innerTblTag.append(newrowInner);
									tdTag.html(innerTblTag);
								} else {
									var elementTag = $('<input type="text" style="float:left;" class="observed"/>');
									if(paramDataValues.className)
										elementTag.addClass(paramDataValues.className);
									elementTag.attr('id',txtid);
									elementTag.attr('value',paramDataValues.value);
									tdTag.html(elementTag);								
									if(paramDataValues.unit) {
										var lblTag = $('<label padding:0px 0px 0px 5px;"/>');
										lblTag.html(paramDataValues.unit);
										tdTag.append(lblTag);
									}									
								}
							}						
						});
					}
					trTag.append(tdTag);
				});
				resultTBody.append(trTag);
			});
			});
			});
		}
		else{
			$(testResult.analysis).each(function(analysisIndex,analysis) {
			$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
			$(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
				var trTag = $('<tr />'); // Create the first row
				trTagId = testId + '_' + analysisIndex + "_" + resultcontentIndex + "_" + resultparamIndex;
				trTag.attr('id',trTagId); // set id for the row
				tdTag= $('<td style="width:40%;vertical-align:middle" />'); // First column for investigation
				tdTag.html(testResult.testName);
				trTag.append(tdTag); // Added Investigation Name to first column
				//Iterating through contents
				$(resultparam.values).each(function(paramvaluesIndex,paramValues) {
					//Creating the next columns like observed value, normal range etc...
                    var tdTag;					
					if(paramValues.values) {
						$(paramValues.values).each(function(paramdataIndex,paramDataValues){
							if(mode=='print') {
								if(paramValues.headervalue!='NormalRange') {
								   tdTag= $('<td class="middle" style="vertical-align:middle"/>');
									elementTag = $('<label />')
									if(paramDataValues.value)
										elementTag.text(commonMethodInvoker.nl2br(paramDataValues.value));
									tdTag.html(elementTag);
									if(paramDataValues.unit){
										var spanTag = $('<span style="padding:0px 0px 0px 5px"/>');
										spanTag.append(paramDataValues.unit);
										tdTag.append(spanTag);	
									}
								} 
							} else {
								var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
								if(paramValues.headervalue!='NormalRange') {
								    tdTag= $('<td class="middle" style="vertical-align:middle"/>');
									var elementTag = $('<input type="text" style="float:left;" class="observed"/>');
									elementTag.attr('id',txtid);
									elementTag.attr('value',paramDataValues.value);
									tdTag.html(elementTag);								
									if(paramDataValues.unit) {
										var lblTag = $('<label padding:0px 0px 0px 5px;"/>');
										lblTag.html(paramDataValues.unit);
										tdTag.append(lblTag);
									}			
								
								} 
							}						
						});
					}
					trTag.append(tdTag);
				});
				resultTBody.append(trTag);
			});
			});
			});	 
		}	  
	return resultTBody; // return the result table body
	};
}
function GTTLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		if(mode=="print") {
			var content =$('<div align="center" />');
			var titleSection = $('<div align="center" style="font-size:18px; line-height:40px;">'+result.testName+'(<label style="margin-right:5px">'+result.testType+'</label>Oral Glucose)</div>');
			content.append(titleSection);
		} else
			var content =$('<div align="center" >'+result.testName+'(<input type="text" id="GTT_heading" class="heading micro" value="'+result.testType+'"/>Oral Glucose)</div>');
		var table=$('<table width="100%" id="realTable" />');
		var thead=$('<thead><th>INVESTIGATION</th><th>BLOOD SUGAR</th><th>URINE SUGAR</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			var column=$('<td />');
			if(mode=="print") {
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval1);
				column.html(lblTag);
			}
			else {
				var inputTag=$('<input type="text"  id="GTT_' + index +'_1" value="' + data.fieldval1 + '"/>');
				if(data.className1)
					inputTag.attr('class',data.className1 );
				column.append(inputTag);
			  }
			row.append(column);
			if(mode=="print") {
				if(data.fieldval1.trim()!=""){
					tbody.append(row);
				}
			}	
			else
				tbody.append(row); 
			column=$('<td>'  +data.BldUnit+'</td>');
			if(mode=="print") {
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval2+data.BldUnit);
				column.html(lblTag);
			}
			else{
				var inputTag=$('<input type="text"  id="GTT_' + index +'_2" class="medium" value="' + data.fieldval2 + '"/>');
				if(data.className2)
					inputTag.attr('class',data.className2 );	
				column.prepend(inputTag);
			  }
			row.append(column);
			if(mode=="print") {
				if(data.fieldval2.trim()!=""){
					tbody.append(row);
				}
			}	
			else
				tbody.append(row); 
			column=$('<td></td>');
			if(mode=="print") {
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval3);
				column.html(lblTag);
			}
			else{
				var inputTag=$('<input type="text"  id="GTT_' + index +'_3" class="medium" value="' + data.fieldval3 + '"/>');
				if(data.className3)
					inputTag.attr('class',data.className3 );
				column.append(inputTag);
			}
			row.append(column);
			if(mode=="print") {
				if(data.fieldval3.trim()!=""){
					tbody.append(row);
				}
			}	
			else
				tbody.append(row); 
		});
		table.append(tbody);
		content.append(table);
		return content;
	};         
}
function haemogramLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<div/>');
		//div for report title starts here
		var fstDiv = $('<div align="center" class="resultTitle"> '+result.testName+'</div>');
		content.append(fstDiv);
		var SecDiv = $('<div class="five_sixth"/>');
		var table;
		if(honorific=="Animal"){
			table=$('<table id="realTable"/>');
			var mainrow=$('<thead><tr><th rowspan="2" ></th> <th colspan="4" align="center">PATIENT\'S VALUE  </th> </tr></thead>');
			table.append(mainrow);
			var tableheading=$('<thead ><tr><th style="width:29%">          </th><th style="width:19%">ABSOLUTE VALUE</th><th style="width:14%;">%VALUE</th></tr></thead>');
			table.append(tableheading);
			var tablebody=$('<tbody/>')
			$(result.values).each(function(index,tblVal){
				var row=$('<tr/>');
				var rowColumns=$('<td >'+tblVal.testnameval+'</td>');
				if(tblVal.testnameval=='ESR')
					rowColumns.attr('style','font-weight:bold;');
				row.append(rowColumns);
				if(mode=="print") {
					rowColumns=$('<td class="middle"/>');
					rowColumns.html(tblVal.absoluteValue + " " + tblVal.aVUnit);
				} else {	
					rowColumns=$('<td >'+tblVal.aVUnit+'</td>');
					var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_1" value="' + tblVal.absoluteValue + '"/>');
					rowColumns.prepend(inputTag);
				}
				row.append(rowColumns);
				if(tblVal.VUnit.trim()!=""){
					if(mode=="print") {
						rowColumns=$('<td class="middle"/>');
						rowColumns.html(tblVal.value  + " " + tblVal.VUnit);
					} else {
						rowColumns=$('<td>'+ tblVal.VUnit + '</td>');
						var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_2" value="' + tblVal.value + '"/>');
						rowColumns.prepend(inputTag);
					}
				 row.append(rowColumns);
				 }
				else
				rowColumns=$('<td></td>');
				row.append(rowColumns);
				tablebody.append(row);
			  });
			  table.append(tablebody);	 
		}
		else {
		 	table=$('<table id="realTable"/>');
			var mainrow=$('<thead><tr><th rowspan="2" ></th> <th colspan="4" align="center">PATIENT\'S VALUE  </th> </tr></thead>');
			table.append(mainrow);
			var tableheading=$('<thead ><tr><th style="width:29%">          </th><th style="width:19%">ABSOLUTE VALUE</th><th style="width:24%">NORMAL RANGE</th><th style="width:14%;">%VALUE</th><th style="width:14%">NORMAL RANGE</th></tr></thead>');
			table.append(tableheading);
			var tablebody=$('<tbody/>')
			$(result.values).each(function(index,tblVal){
				var row=$('<tr/>');
				var rowColumns=$('<td >'+tblVal.testnameval+'</td>');
				if(tblVal.testnameval=='ESR')
					rowColumns.attr('style','font-weight:bold;');
				row.append(rowColumns);
				if(mode=="print") {
					rowColumns=$('<td class="middle"/>');
					rowColumns.html(tblVal.absoluteValue + " " + tblVal.aVUnit);
				} else {	
					rowColumns=$('<td >'+tblVal.aVUnit+'</td>');
					var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_1" value="' + tblVal.absoluteValue + '"/>');
					rowColumns.prepend(inputTag);
				}
				row.append(rowColumns);
				var rowColumns= $('<td  class="middle">'+tblVal.normalRange+'</td>');
				row.append(rowColumns);
				
				if(tblVal.VUnit.trim()!=""){
					if(mode=="print") {
						rowColumns=$('<td class="middle"/>');
						rowColumns.html(tblVal.value  + " " + tblVal.VUnit);
					} else {
						rowColumns=$('<td>'+ tblVal.VUnit + '</td>');
						var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_2" value="' + tblVal.value + '"/>');
						rowColumns.prepend(inputTag);
					}
				 row.append(rowColumns);
				 }
				else
					rowColumns=$('<td></td>');
				row.append(rowColumns);
				rowColumns=$('<td >'+tblVal.NR+'</td>');
				row.append(rowColumns);
				tablebody.append(row);
			  });
			  table.append(tablebody);			  
	   } 
		SecDiv.append(table);
		content.append(SecDiv);
		return content;
	};			
}
function haemogramESRLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<div/>');
		//div for report title starts here
		var fstDiv = $('<div align="center" class="resultTitle"> '+result.testName+'</div>');
		content.append(fstDiv);
		var SecDiv = $('<div class="five_sixth"/>');
		var table;
		if(honorific=="Animal"){
			table=$('<table id="realTable"/>');
			var mainrow=$('<thead><tr><th rowspan="2" ></th> <th colspan="4" align="center">PATIENT\'S VALUE  </th> </tr></thead>');
			table.append(mainrow);
			var tableheading=$('<thead ><tr><th style="width:29%">          </th><th style="width:19%">ABSOLUTE VALUE</th><th style="width:14%;">%VALUE</th></tr></thead>');
			table.append(tableheading);
			var tablebody=$('<tbody/>')
			$(result.values).each(function(index,tblVal){
				var row=$('<tr/>');
				var rowColumns=$('<td >'+tblVal.testnameval+'</td>');
				if(tblVal.testnameval=='ESR')
					rowColumns.attr('style','font-weight:bold;');
				row.append(rowColumns);
				if(mode=="print") {
					rowColumns=$('<td class="middle"/>');
					rowColumns.html(tblVal.absoluteValue + " " + tblVal.aVUnit);
				} else {	
					rowColumns=$('<td >'+tblVal.aVUnit+'</td>');
					var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_1" value="' + tblVal.absoluteValue + '"/>');
					rowColumns.prepend(inputTag);
				}
				row.append(rowColumns);
				if(tblVal.VUnit.trim()!=""){
					if(mode=="print") {
						rowColumns=$('<td class="middle"/>');
						rowColumns.html(tblVal.value  + " " + tblVal.VUnit);
					} else {
						rowColumns=$('<td>'+ tblVal.VUnit + '</td>');
						var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_2" value="' + tblVal.value + '"/>');
						rowColumns.prepend(inputTag);
					}
				 row.append(rowColumns);
				 }
				else
				rowColumns=$('<td></td>');
				row.append(rowColumns);
				tablebody.append(row);
			  });
			  table.append(tablebody); 
		}
		else {
		 	table=$('<table id="realTable"/>');
			var mainrow=$('<thead><tr><th rowspan="2" ></th> <th colspan="4" align="center">PATIENT\'S VALUE  </th> </tr></thead>');
			table.append(mainrow);
			var tableheading=$('<thead ><tr><th style="width:29%">          </th><th style="width:19%">ABSOLUTE VALUE</th><th style="width:24%">NORMAL RANGE</th><th style="width:14%;">%VALUE</th><th style="width:14%">NORMAL RANGE</th></tr></thead>');
			table.append(tableheading);
			var tablebody=$('<tbody/>')
			$(result.values).each(function(index,tblVal){
				var row=$('<tr/>');
				var rowColumns=$('<td >'+tblVal.testnameval+'</td>');
				if(tblVal.testnameval=='ESR')
					rowColumns.attr('style','font-weight:bold;');
				row.append(rowColumns);
				if(mode=="print") {
					rowColumns=$('<td class="middle"/>');
					rowColumns.html(tblVal.absoluteValue + " " + tblVal.aVUnit);
				} else {	
					rowColumns=$('<td >'+tblVal.aVUnit+'</td>');
					var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_1" value="' + tblVal.absoluteValue + '"/>');
					rowColumns.prepend(inputTag);
				}
				row.append(rowColumns);
				var rowColumns= $('<td  class="middle">'+tblVal.normalRange+'</td>');
				row.append(rowColumns);		
				if(tblVal.VUnit.trim()!=""){
					if(mode=="print") {
						rowColumns=$('<td class="middle"/>');
						rowColumns.html(tblVal.value  + " " + tblVal.VUnit);
					} else {
						rowColumns=$('<td>'+ tblVal.VUnit + '</td>');
						var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_2" value="' + tblVal.value + '"/>');
						rowColumns.prepend(inputTag);
					}
				 row.append(rowColumns);
				 }
				else
					rowColumns=$('<td></td>');
				row.append(rowColumns);
				rowColumns=$('<td >'+tblVal.NR+'</td>');
				row.append(rowColumns);
				tablebody.append(row);
			  });
			  table.append(tablebody);			  
	   } 
		SecDiv.append(table);
		content.append(SecDiv);
		return content;
	};		
}
function haemogramLayoutMed(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div/>');
		var tableHead = $('<div width="100%" class="one singleheight bold" />');
		var contentTag = $('<div class="onesecond"/>');
		contentTag.append("Investigation");
		tableHead.append(contentTag);
		contentTag = $('<div class="onefive"/>');
		contentTag.append("Absolute Value");
		tableHead.append(contentTag);
		contentTag = $('<div class="onefive"/>');
		contentTag.append("Normal Range");
		tableHead.append(contentTag);
		contentTag = $('<div class="onefourth"/>');
		contentTag.append("% Value");
		tableHead.append(contentTag);
		contentTag = $('<div class="onefourth"/>');
		contentTag.append("Normal Range");
		tableHead.append(contentTag);
		content.append(tableHead);
		var table=$('<div width="100%" id="haemogramTable" />');
		$(result.values).each (function(index,paramData){
			if(mode=='print') {
				if(paramData.value.trim()!="") {
					var container = $('<div class="one singleheight"/>');
					//container.attr('min-height','25px');
					var contentTag = $('<div class="onesecond"/>');
					contentTag.append(paramData.name);
					container.append(contentTag);
					contentTag = $('<div class="onefive"/>');
					contentTag.append(paramData.value);
					if(paramData.unit){
						var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
						spanTag.append(paramData.unit);
						contentTag.append(spanTag);	
					}
					container.append(contentTag);
					contentTag = $('<div class="onefive"/>');
					contentTag.append(paramData.normal);
					container.append(contentTag);
					if(paramData.perc_id) {
						contentTag = $('<div class="onefourth"/>');
						contentTag.empty().html(paramData.perc_value);
						if(paramData.perc_unit){
							var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
							spanTag.append(paramData.perc_unit);
							contentTag.append(spanTag);	
						}
						container.append(contentTag);
						contentTag = $('<div class="onefourth">&nbsp;</div>');
						contentTag.empty().html(paramData.perc_normal);
						container.append(contentTag);
					}
					table.append(container);
				}
			} else {
				var container = $('<div class="one singleheight" />');
				var contentTag = $('<div class="onesecond"/>');
				contentTag.append(paramData.name);
				container.append(contentTag);
				contentTag = $('<div class="onefive"/>');
				elementTag = $('<input type="text" class="observed" />');
				if(paramData.className)
					elementTag.attr('class',paramData.className);
				if(paramData.value)
					elementTag.attr('value',paramData.value);
				$(elementTag).attr('id',paramData.id);
				contentTag.append(elementTag);
				if(paramData.unit)
					contentTag.append(paramData.unit);	
				container.append(contentTag);
				contentTag = $('<div class="onefive"/>');
				contentTag.append(paramData.normal);
				container.append(contentTag);
				contentTag = $('<div class="onefourth">&nbsp;</div>');
				if(paramData.perc_id) {
					elementTag = $('<input type="text" class="observed" />');
					if(paramData.className)
						elementTag.attr('class',paramData.className);
					if(paramData.value)
						elementTag.attr('value',paramData.value);
					$(elementTag).attr('id',paramData.perc_id);
					contentTag.append(elementTag);
					if(paramData.perc_unit){
						var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
						spanTag.append(paramData.perc_unit);
						contentTag.append(spanTag);	
					}
				}	
				container.append(contentTag);
				contentTag = $('<div class="onefourth">&nbsp;</div>');
				if(paramData.perc_id) 
					contentTag.empty().html(paramData.perc_normal);
				container.append(contentTag);
				table.append(container);
			}			
		});
		content.append(table);
		return content;
	};
}
function HistoPathLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div align="center" style="font-weight:bold">'+result.testName+'</div>');
		var table=$('<table width="100%" height="500px" id="realTable" />');
		var thead=$('<thead></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		var row=$('<tr/>');
		var tdTag = $('<td class="topAlign"/>');
		$(result.values).each (function(index,data){
			if(mode=="print"){
				var lblTag = $('<label/>');
				lblTag.html(commonMethodInvoker.nl2br(data.fieldval));
				tdTag.append(lblTag);	
			}
			else{
				var textarea=$('<textarea  style="width:100%; height:500px;" id="HistoPath_' + index +'_1" >'+ data.fieldval +'</textarea>');
				tdTag.append(textarea);					
				}	
		});
		row.append(tdTag);
		tbody.append(row);
		table.append(tbody);
		content.append(table);
		return content;
	};         
}
function layout1(honorific,testResult, testId, mode) {
	this.result =	function(){ 
		var response = $('<table />');
		$(testResult.analysis).each(function(analysisIndex,analysis) {
			var thead = $('<thead class="noBorder">');
			thead.attr('id','thead_'+analysisIndex);
			var headRow = $('<tr/>');
			var headCol = $('<th style="border:none;"/>');
			headCol.html(analysis.analysisType);
			headRow.html(headCol);
			thead.html(headRow);
			response.append(thead);
			var tbody=$('<tbody/>');
			var trTag = $('<tr />');
			var tdTag = $('<td style="border:none;"/>');
			$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
				var tblTag = $('<table style="width:90%"/>');
				$(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
					$(resultparam.values).each(function(paramvaluesIndex,paramValues) {						
						$(paramValues.values).each(function(paramdataIndex,paramData) {							
							if(paramData.values) {	
								var newrow = $('<tr/>');
								if(resultparam.headerLabel) {
									var colhead=$('<td>');
									colhead.html(resultparam.headerLabel);
									newrow.append(colhead);
								};	
								$(paramData.values).each(function(paramdataValueIndex,paramDataValue) {
									if(paramDataValue.normalRangeAttribute) {	
										var colTag = $('<td />');
										colTag.append(paramDataValue.normalRangeAttribute);	
										newrow.append(colTag);								
									}
									if(paramDataValue.displayType) {	
										var colTag = $('<td />');
										var spanTag=$('<span/>');
										if(paramDataValue.displayType=='text') {
											elementTag = $('<input type="text"/>');
											if(mode=='print') {
												elementTag.attr('class','readonlybox');
												elementTag.attr('readonly',true);
											}
											var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex+"_"+paramdataValueIndex;
											$(elementTag).attr('id',txtid);
											if(paramDataValue.className){
											elementTag.attr('class',paramDataValue.className)
											}
											if(paramDataValue.style){
											elementTag.attr('style',paramDataValue.style);
											}
											if(paramDataValue.value) {
											 elementTag.attr('value',paramDataValue.value);
											}
											colTag.append(elementTag);
												if(paramDataValue.unit) {
												colTag.append(paramDataValue.unit);
											}
										}
										newrow.append(colTag);
									} else {
										if(paramDataValue.value) {
											var colTag = $('<td />');
											colTag.html(paramDataValue.value);
											if(paramDataValue.unit)
												colTag.prepend(paramDataValue.unit);
											newrow.append(colTag);	
										}
									}
									tblTag.append(newrow);									
								});								
							} 																															
						});	
						var newrow = $('<tr/>');
						if(resultparam.headerLabel) {
							var colhead=$('<td>');
							newrow.append(colhead);
						};	
						$(paramValues.values).each(function(paramdataIndex,paramData) {															
							if(paramData.normalRangeAttribute) {	
								var colTag = $('<td  style="width:60%;padding-left:25%;text-align:left"/>');
								colTag.append(paramData.normalRangeAttribute);	
								newrow.append(colTag);								
							}
							var colTag = $('<td  style="text-align:center"/>');
							colTag.append("&nbsp; &nbsp;&nbsp;: &nbsp;&nbsp; ");	
							newrow.append(colTag);	
							if(paramData.displayType) {	
								var colTag = $('<td style="text-align:left"/>');
								if(paramData.displayType=='text') {
									elementTag = $('<input type="text" style="text-align:left"/>');
									if(mode=='print') {
										elementTag.attr('class','readonlybox');
										elementTag.attr('readonly',true);
									}
									var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
									$(elementTag).attr('id',txtid);
									if(paramData.className){
											elementTag.attr('class',paramData.className);
											}
										if(paramData.style){
										elementTag.attr('style',paramData.style);
											}
									if(paramData.readonly){
											elementTag.attr('readonly',true);
											}
									if(paramData.value) {
										elementTag.attr('value',paramData.value);
										elementTag.attr('style','font-size:100%;');
									}
									colTag.append(elementTag);
									if(paramData.unit) {
										colTag.append(paramData.unit);
									}
									 }
								if(paramData.displayType=="select") {
										elementTag = $('<select/>');
										if(paramData.list) {
											$(paramData.list).each(function(indexval,val) {
												var optTag = $('<option/>')
												if(val.value)optTag.attr("value",val.value);
												if(val.selected)optTag.attr("selected",val.selected);
												optTag.html(val.value);
												elementTag.append(optTag);
											});
										}
										if(paramData.id){
										elementTag.attr('id',paramData.id)
										}
										colTag.append(elementTag);																							
									}
								
								newrow.append(colTag);
							} else {
								if(paramData.value) {
									var colTag = $('<td />');
									colTag.html(paramData.value);
									if(paramData.unit)
										colTag.append(paramData.unit);
									newrow.append(colTag);	
								}
							}
						});
						tblTag.append(newrow);	
					});	
				});
				tdTag.append(tblTag);
				trTag.append(tdTag);
				tbody.append(trTag);
				response.append(tbody);
			});
			
		});	
		return response.html();
	};	
}
function LipidLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
	var content =$('<div align="center" />');
	var titleSection =$('<div align="center" style="font-size:18px; line-height:40px;">'+result.testName+'</div>');
	var table;
	if(honorific=="Animal"){
		if(mode!="print"){
			var spanTag=$('<span style="float:right; margin-right:10px;font-size:14px; line-height:6px;">Manual calculation</span>');
			 var inputTag=$('<input id="manualCalc" type="checkbox" />')
			 if(result.checked==true)
			   inputTag.attr('checked','true');
			spanTag.append(inputTag);
			titleSection.append(spanTag);
		}
		content.append(titleSection);
		var table=$('<table width="100%" id="SpHgtTable" />');
		var thead=$('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			if(mode=="print" && data.id=="LDL"){
			  if(result.checked==true )
					var column=$('<td style="width:40%">'+data.name+' (Direct)</td>');
				else
					var column=$('<td style="width:40%">'+data.name+'</td>');			
			}
			else
			var column=$('<td style="width:40%">'+data.name+'</td>');
			row.append(column);
			var testId=getTestId(data.name);
			var testInfo = getTestData(testId);
			var resultInfo=$.parseJSON(testInfo.result);
			column=$('<td class="middle"/>');
			if(mode=="print") {
				elementTag = $('<label />')
				elementTag.text(data.fieldval);
				column.append(elementTag);
			} else {
				var inputTag=$('<input type="text" class="'+data.className+'" id="LPT' + index +'_1"  value="' + data.fieldval + '"/>');
				  column.append(inputTag);
				
			}		
			var spanTag=$('<span>'+resultInfo.testUnit+'</span>');
			column.append(spanTag);
			row.append(column);
			  if(index==4 && result.checked==true ){
				 return;
			  }
			  else
			   tbody.append(row);
			  });
			  table.append(tbody);
	}
	else {
	     	if(mode!="print"){
			var spanTag=$('<span style="float:right; margin-right:10px;font-size:14px; line-height:6px;">Manual calculation</span>');
			 var inputTag=$('<input id="manualCalc" type="checkbox" />')
			 if(result.checked==true)
			   inputTag.attr('checked','true');
			spanTag.append(inputTag);
			titleSection.append(spanTag);
		}
		content.append(titleSection);
		var table=$('<table width="100%" id="SpHgtTable" />');
		var thead=$('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th><th>REFERENCE RANGE&UNIT</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			if(mode=="print" && data.id=="LDL"){
			  if(result.checked==true )
					var column=$('<td style="width:40%">'+data.name+' (Direct)</td>');
				else
					var column=$('<td style="width:40%">'+data.name+'</td>');			
			}
			else
			var column=$('<td style="width:40%">'+data.name+'</td>');
			row.append(column);
			var testId=getTestId(data.name);
			var testInfo = getTestData(testId);
			var resultInfo=$.parseJSON(testInfo.result);
			column=$('<td class="middle"/>');
			if(mode=="print") {
				elementTag = $('<label />')
				elementTag.text(data.fieldval);
				column.append(elementTag);
			} else {
				var inputTag=$('<input type="text" class="'+data.className+'" id="LPT' + index +'_1"  value="' + data.fieldval + '"/>');
				  column.append(inputTag);
				
			}		
			var spanTag=$('<span>'+resultInfo.testUnit+'</span>');
			column.append(spanTag);
			row.append(column);
			var newcolumn=$('<td style="width:35%" />');
			if(index==4){
				if(mode=="print") {
					elementTag = $('<label />')
					elementTag.text(data.fieldvalue);
					newcolumn.append(elementTag);
				}
				else{
				var inputTagNew=$('<input type="text" id="LPT4_2" value="'+data.fieldvalue+'"/>');
				newcolumn.append(inputTagNew);
				}
			}
			else{
				var spanTag=$('<span/>');
				spanTag.append(testInfo.normalRange);
				newcolumn.append(spanTag);
				}
			  row.append(newcolumn);
			  if(index==4 && result.checked==true ){
				 return;
			  }
			  else
			   tbody.append(row);
			  });
			  table.append(tbody);
		 
	}		 
	content.append(table);
	return content;
		};
}
function lipidLayoutMed(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div/>');
		var titleSection =$('<div class="one bold singleheight underline">'+result.testName+'</div>');
		content.append(titleSection);
		var table=$('<div width="100%" id="lipidTable" />');
		$(result.values).each (function(index,paramData){
			if(mode=='print') {
				if(paramData.value.trim()!="") {
					var container = $('<div class="one singleheight"/>');
					//container.attr('min-height','25px');
					var contentTag = $('<div class="onefirst"/>');
					contentTag.append(paramData.name);
					container.append(contentTag);

					contentTag = $('<div class="onesecond"/>');
					contentTag.append(paramData.value);
					if(paramData.unit){
						var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
						spanTag.append(paramData.unit);
						contentTag.append(spanTag);	
					}
					container.append(contentTag);
					contentTag = $('<div class="onesecond"/>');
					contentTag.append(paramData.normal);
					container.append(contentTag);
					table.append(container);
				}
			} else {
				var container = $('<div class="one singleheight" />');
				var contentTag = $('<div class="onefirst"/>');
				contentTag.append(paramData.name);
				container.append(contentTag);
				contentTag = $('<div class="onesecond"/>');
				elementTag = $('<input type="text" class="observed" />');
				if(paramData.className)
					elementTag.attr('class',paramData.className);
				if(paramData.value)
					elementTag.attr('value',paramData.value);
				$(elementTag).attr('id',paramData.id);
				contentTag.append(elementTag);
				if(paramData.unit)
					contentTag.append(paramData.unit);	
				container.append(contentTag);
				contentTag = $('<div class="onesecond"/>');
				contentTag.append(paramData.normal);
				container.append(contentTag);
				table.append(container);
			}
			
		});
		content.append(table);
		return content;
	};
}
function LiverLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
	var content =$('<div align="center"/>');
	var titleSection=$('<div align="center" class="resultTitle" >'+result.testName+'</div>');
	content.append(titleSection);
	var table;
	if(honorific=="Animal"){
		table=$('<table width="100%" id="SpHgtTable" />');
		var thead=$('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			var column=$('<td style="width:50%">'+data.name+'</td>');
			row.append(column);
			var testId=getTestId(data.name);
			var testInfo = getTestData(testId);
			var resultInfo=$.parseJSON(testInfo.result);
			column=$('<td style="width:50%"></td>');
			if(mode=="print") {
				elementTag = $('<label />')
				elementTag.text(data.fieldval);
				column.append(elementTag);
			}
			else{	
				var inputTag=$('<input type="text" class="'+data.className+'" id="LFT' + index +'_1"  value="' + data.fieldval + '" tabindex=' + (index+1) + '/>');
				 if(data.readonly)	
				 inputTag.attr("readonly","readonly");
				column.append(inputTag);
			}
			var spanTag=$('<span style="font-weight:normal">'+'&nbsp;&nbsp;'+resultInfo.testUnit+'</span>');
			column.append(spanTag);
			row.append(column);
			
			  tbody.append(row);
			  });
			  table.append(tbody);
	} 
	else{
		table=$('<table width="100%" id="SpHgtTable" />');
		var thead=$('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th><th>REFERENCE RANGE&UNIT</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			var column=$('<td style="width:30%">'+data.name+'</td>');
			row.append(column);
			var testId=getTestId(data.name);
			var testInfo = getTestData(testId);
			var resultInfo=$.parseJSON(testInfo.result);
			column=$('<td></td>');
			if(mode=="print") {
				elementTag = $('<label />')
				elementTag.text(data.fieldval);
				column.append(elementTag);
			}
			else{	
				var inputTag=$('<input type="text" class="'+data.className+'" id="LFT' + index +'_1"  value="' + data.fieldval + '" tabindex=' + (index+1) + '/>');
				 if(data.readonly)	
				 inputTag.attr("readonly","readonly");
				column.append(inputTag);
			}
			var spanTag=$('<span style="font-weight:normal">'+'&nbsp;&nbsp;'+resultInfo.testUnit+'</span>');
			column.append(spanTag);
			row.append(column);
			var newcolumn=$('<td style="width:40%;font-weight:normal"></td>');
			if(mode=="print") {
				newcolumn.append(commonMethodInvoker.space2tab(commonMethodInvoker.nl2br(data.OBSRDVal)));
			}
			else{
				if(testId=="T184"){
					var textarea=$('<textarea id="LFT' + index +'_2" />');
					textarea.html(commonMethodInvoker.br2nl(testInfo.normalRange));
					newcolumn.append(textarea);
				}
				else{
					var inputTag=$('<input type="text" id="LFT' + index +'_2"  value="' + commonMethodInvoker.br2nl(testInfo.normalRange) + '"/>');		
					newcolumn.append(inputTag);
				}
			}	
			  row.append(newcolumn);
			  tbody.append(row);
			  });
			  table.append(tbody);				
	}		
		  content.append(table);
		  return content;
		};         
}
function liverLayoutMed(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div/>');
		var titleSection =$('<div class="one bold singleheight underline">'+result.testName+'</div>');
		content.append(titleSection);
		var table=$('<div width="100%" id="LFT_Table" />');
		$(result.values).each (function(index,paramData){
			if(mode=='print') {
				if(paramData.value.trim()!="") {
					var container = $('<div class="one singleheight"/>');
					//container.attr('min-height','25px');
					var contentTag = $('<div class="onefirst"/>');
					contentTag.append(paramData.name);
					container.append(contentTag);
					contentTag = $('<div class="onesecond"/>');
					contentTag.append(paramData.value);
					if(paramData.unit){
						var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
						spanTag.append(paramData.unit);
						contentTag.append(spanTag);	
					}
					container.append(contentTag);
					contentTag = $('<div class="onesecond"/>');
					contentTag.append(paramData.normal);
					container.append(contentTag);
					table.append(container);
				}
			} else {
				var container = $('<div class="one singleheight" />');
				var contentTag = $('<div class="onefirst"/>');
				contentTag.append(paramData.name);
				container.append(contentTag);
				contentTag = $('<div class="onesecond"/>');
				elementTag = $('<input type="text" class="observed" />');
				if(paramData.className)
					elementTag.attr('class',paramData.className);
				if(paramData.value)
					elementTag.attr('value',paramData.value);
				$(elementTag).attr('id',paramData.id);
				contentTag.append(elementTag);
				if(paramData.unit)
					contentTag.append(paramData.unit);	
				container.append(contentTag);
				contentTag = $('<div class="onesecond"/>');
				elementTag = $('<input type="text" />');
				elementTag.attr('value',paramData.normal);
				contentTag.append(elementTag);
				container.append(contentTag);
				table.append(container);
			}			
		});
		content.append(table);
		return content;
	};
}
function osmoticLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<div/>');
		//div for report title starts here
		var fstDiv = $('<div  align="center" class="resultTitle">'+result.testName+'</div>');
		content.append(fstDiv);
	    var table=$('<table id="SpHgtTable"/>');
		var thead=$('<thead/>');
		var headrow=$('<tr>');
		$(result.Resultheaders).each(function(index,headervalue){
		  var headcol=$('<th style="width:33%">'+headervalue.headername+'</th>');
		  headrow.append(headcol);
		});
		thead.append(headrow);
      	table.append(thead);
		var tbody=$('<tbody/>');
		$(result.Resultbody).each(function(index,headerbody){
		var newRow=$('<tr/>');
		var newColumns=$('<td />');
		if(mode=="print"){
			var lblTag = $('<label/>');
			lblTag.html(headerbody.headerOneval);
			newColumns.addClass('middle');
			newColumns.html(lblTag);
		}
		else {
			var inputTag=$('<input type=text  align="center" id="Osmotic_' + index +'_1" value="' + headerbody.headerOneval +'"/> ');
			newColumns.append(inputTag);
		}
		newRow.append(newColumns);
		var newColumns=$('<td/>');
		if(mode=="print"){
			var lblTag = $('<label/>');
			lblTag.html(headerbody.headerTwoval);
			newColumns.addClass('middle');
			newColumns.html(lblTag);
		}
		else{
			var inputTag=$('<input type=text  align="center" id="Osmotic_' + index +'_2" tabIndex="'+(index+1)+'" value="' + headerbody.headerTwoval +'"/>');
			newColumns.append(inputTag);
		}
		newRow.append(newColumns);
		var newColumns=$('<td/>');
		if(mode=="print"){
			var lblTag = $('<label/>');
			lblTag.html(headerbody.headerThreeVal);
			newColumns.addClass('middle');
			newColumns.html(lblTag);
		}
		else{
			var inputTag=$('<input type=text align="center" id="Osmotic_' + index +'_3" value="' + headerbody.headerThreeVal +'"/>');
			newColumns.append(inputTag);
		}
		newRow.append(newColumns);
		if(mode=="print") {
				if(headerbody.headerOneval.trim()!=""){
					tbody.append(newRow);
				}	
		}	
		else
			tbody.append(newRow);
		});
		table.append(tbody);
		content.append(table);
		return content;		
		};
}
function PeripheralLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
	var content =$('<div align="center" style="font-weight:bold">'+result.testName+'</div>');
		var table=$('<table width="100%" id="realTable" />');
		var thead=$('<thead></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
		  var row=$('<tr/>');
		  var column=$('<td></td>');
		  if(mode=="print"){
			var lblTag = $('<label/>');
			lblTag.html(data.fieldval);
			column.html(lblTag);
		  }
		  else{
				var inputTag=$('<input type="text"  style="width:100%;" id="Peripheral_' + index +'_1" value="' + data.fieldval + '"/>');
				column.append(inputTag);
			}	
		  row.append(column);
		  if(mode=="print") {
				if(data.fieldval.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
		  });
		  table.append(tbody);
		  content.append(table);
		  return content;
		};         
}
function PTLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var tbody=$('<tbody/>');
		var trow = $('<tr><td style="font-size:100%;font-weight:bold;">INVESTIGATION</td><td style="font-size:100%;font-weight:bold;">OBSERVED VALUE</td><td style="font-size:100%;font-weight:bold;">REFERENCE RANGE&UNIT</td></tr>');
		tbody.append(trow);
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			if(data.FieldOneName){
				if(data.FieldOneName!=""){
					var column=$('<td style="width:33%;padding-left:5px;">'+data.FieldOneName+'</td>');
					row.append(column);
				}
			}
			if(index==1){
		        var column=$('<td rowspan="3"></td>');
				 row.append(column);
			}
			var column=$('<td style="width:33%;padding-left:5px;"/>');
			var spanTag=$('<div style="width:35%;float:left;">'+data.FieldTwoName+'</div>');
			column.append(spanTag);
			var spanTag=$('<div style="width:5%;float:left;">:</div>');
		    column.append(spanTag);
			var spanTag=$('<div style="width:35%;float:left;"/>');
		   	if(mode=="print") {
				var lblTag = $('<label />');
				lblTag.html(data.FieldVal +" "+data.unitVal);
				spanTag.append(lblTag);
			} else {
				var inputTag=$('<input type="text"  id="PT_' + index +'_1" class="large" value="' + data.FieldVal + '"/>');
				spanTag.append(inputTag);
			}
		    column.append(spanTag);
			spanTag=$('<div style="width:20%;float:left;"></div>');
			if(data.time){
				if(mode=="print") {
					var lblTag = $('<label />');
					lblTag.html(data.UnitType);
					spanTag.append(lblTag);
				} else {
					var inputTag=$('<input type="text" class="'+data.time+'" value="'+data.unitVal+'" id="PT_'+index+'_2"/>');
					spanTag.append(inputTag);
					if(data.UnitType)
						spanTag.html(data.UnitType);
				}
			}		
		    column.append(spanTag);
		   	row.append(column);
		  	if(data.FieldThreeName){
			   	if(data.FieldThreeName!=""){
					var column=$('<td style="width:33%;padding-left:5px;">'+data.FieldThreeName+'</td>');
				   	row.append(column);
			   	} 
		    } 
			if(index==1){
				var column=$('<td rowspan="3"></td>');
				row.append(column);
			}
		  	tbody.append(row);
		});
		return tbody;
	};
}
function getContent(result) {
	this.result = function () {
		//Fill Data values
		var tmpTable = $('<table/>');		
		$(result.analysis).each(function(index,analysis) {
			var tmpHead=result.testName + "-";
			$(analysis.resultContent).each(function(index1,rescont) {
				var tbodyTag = $('<tbody/>');
				$(rescont.resultParams).each(function(index2,resparam) {
					var trTag=$('<tr/>');
					var tdTag = $('<td/>');
					if(resparam.key) {
						tmpHead +=resparam.key;
						tdTag.append(tmpHead);
						tmpHead="";
					}	
					else
						tdTag.append(result.testName);						
					trTag.append(tdTag);
					$(resparam.values).each(function(index2,elemnt) {
						tdTag = $('<td/>');						
						if(elemnt.values){
							$(elemnt.values).each(function(index2,elem) {
								var elemContainerTag=$('<div/>');
								if(elem.normalRangeLabel) {
									elemContainerTag.append(elem.normalRangeLabel);
								}
								if(elem.displayType){																	
									if(elem.displayType=="text") {
										elmnTag = $('<input type="text"/>');
										if(elemnt.value)
											elmnTag.attr('value',elemnt.value);
										elemContainerTag.append(elmnTag);
									}
									if(elem.displayType=="select") {
										elmnTag = $('<select/>');
										if(elem.list) {
											$(elem.list).each(function(indexval,val) {
												var optTag = $('<option/>')
												optTag.html(val.value);
												elmnTag.append(optTag);
											});
										}
										if(elemnt.value)
											elmnTag.attr('value',elemnt.value);
										elemContainerTag.append(elmnTag);
									}
								}else {
									elmnTag = $('<label/>');
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
							var elemntContainerTag=$('<div width:30%; style="float:left;"/>');
							if(elemnt.displayType=="text") {
								elmnTag = $('<input type="text"/>');
								if(elemnt.value)
									elmnTag.attr('value',elemnt.value);
								elemntContainerTag.append(elmnTag);								
								tdTag.append(elemntContainerTag);
							}
							if(elemnt.displayType=="select") {
								elmnTag = $('<select/>');
								if(elemnt.list) {
									$(elemnt.list).each(function(indexval,val) {
										var optTag = $('<option/>')
										optTag.html(val.value);
										elmnTag.append(optTag);
									});
								}
								if(elemnt.value)
									elmnTag.attr('value',elemnt.value);
								elemntContainerTag.append(elmnTag);
								tdTag.append(elemntContainerTag);
							}							
							var divUnitTag = $('<div/>');
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
function SemenLayout(honorific,result,testId, mode) {
	this.result =function(){ 
		var azospermia = false;
		var content =$('<div align="center" class="resultTitle">'+result.testName+'</div>');
		var semenTable=$('<table width="100%" id="SemenTable" style="font-size:15px"/>');
		var tbodyTag;
		$(result.analysis).each(function(ResultContentIndex,analysis){
			if(analysis.analysisType){
				var theadTag = $('<thead />');
				var trTag=$('<tr></tr>');
				var tdTag = $('<th colspan="2"></th>');
				tdTag.html(analysis.analysisType);
				trTag.append(tdTag);	
				theadTag.html(trTag);
				semenTable.append(theadTag);
			}	
			tbodyTag=$('<tbody/>');
			if(analysis.analysisType=="MICROSCOPIC EXAMINATION"){
				var trTag = $('<tr/>');
				var spanTag;
				$(analysis.resultContent).each(function(index,resultcontent) {
					if(resultcontent.value.trim()=="Azospermia")
						azospermia = true;
					if(index!=2) {					
						tdTag = $('<td style="width:50%"/>');	
						spanTag = $('<span />');
					} else {
						spanTag = $('<span />');
					}					
					if(mode=='print') {
						var lblTag = $('<label/>');
						lblTag.html(resultcontent.value);
						spanTag.append(lblTag);
					} else {
						var inputTag=$('<input type="text" id="' + resultcontent.id +'" value="' + resultcontent.value + '" />');
						if(resultcontent.className)
							inputTag.attr('class',resultcontent.className);
						spanTag.append(inputTag);
					}
					tdTag.append(spanTag);
					if(index!=1)
						trTag.append(tdTag);
				});	
				tbodyTag.append(trTag);
			}else if(analysis.analysisType=="SPERM MORPHOLOGY"){//Sperm Morphology Section 
				var trParentTag = $('<tr />');
				var tdTag = $('<td colspan="2"/>');
				var innerTable = $('<table width="100%" style="font-size:15px"/>');
				var status =true;
				$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
					var trTag=$('<tr/>');
					$(resultcontent.values).each(function(valuesIndex,values){	
						//abnormal Heading
						if(resultcontentIndex==0) {
							if(status==true) {
								var tdchildTag=$('<td >' + resultcontent.headerLabel +'</td>');
								trTag.append(tdchildTag);
								status=false;
							}	
							var tdchildTag = $('<td style="width:27%"/>');
							var spanTag = $('<span>' + values.normalRangeAttribute + '</span>');
							tdchildTag.append(spanTag);		
							spanTag = $('<span/>');
							if(mode=='print') {	
								var lblTag = $('<label/>');
								if(values.value=="")
									lblTag.html("--");
								else
									lblTag.html(values.value);							
								spanTag.html(lblTag);
								tdchildTag.append(spanTag);
							} else {
								var inputTag=$('<input type="text" id="'+values.id + '" value="' + values.value + '"/>');
								if(values.className)
									inputTag.attr('class',values.className);
								spanTag.html(inputTag);
								tdchildTag.append(spanTag);
							}
							if(values.unit) {
								spanTag = $('<span/>');
								if(azospermia==false && values.value.toUpperCase()!="NIL" && values.value.trim()!="" && values.value.trim()!="--") 
									spanTag.html(values.unit);
								tdchildTag.append(spanTag);
							}							
							trTag.append(tdchildTag);
						} else {
							var tdchildTag=$('<td colspan="2"/>');
							var spanTag = $('<span>' + values.normalRangeAttribute + '</span>');
							tdchildTag.append(spanTag);		
							spanTag = $('<span/>');
							if(mode=='print') {	
								var lblTag = $('<label/>');
								lblTag.html(values.value);							
								spanTag.html(lblTag);
								tdchildTag.append(spanTag);
							} else {
								var inputTag=$('<input type="text" id="'+values.id + '" value="' + values.value + '"/>');
								if(values.className)
									inputTag.attr('class',values.className);
								spanTag.html(inputTag);
								tdchildTag.append(spanTag);
							}							
							if(values.unit) {
								spanTag = $('<span/>');
								if(azospermia==false && values.value.toUpperCase()!="NIL" && values.value.trim()!=" "&& values.value.trim()!="--") 
									spanTag.html(values.unit);
								tdchildTag.append(spanTag);
							}
							trTag.append(tdchildTag);							
						}
					});						
					innerTable.append(trTag);
				});
				tdTag.append(innerTable);
				trParentTag.append(tdTag);
				tbodyTag.append(trParentTag);
			} else {		//Other Sections
				$(analysis.resultContent).each(function(index,resultcontent) {
					var trTag = $('<tr/>');
					if(resultcontent.normalRangeAttribute=="Liquefaction") {
						var tdTag = $('<td style="width:50%"/>');
						var spanTag = $('<span />');
						spanTag.html(resultcontent.normalRangeAttribute);
						tdTag.html(spanTag);
						spanTag = $('<span />');
						if(mode=='print') {
							var lblTag = $('<label style="float:right;"/>');
							lblTag.html(resultcontent.value);
							tdTag.append(lblTag);
						} else {
							var inputTag=$('<input style="float:right;" type="text" align="right" id="'+ resultcontent.id + '" value="' + resultcontent.value + '"/>');
							if(resultcontent.className1)
								inputTag.attr('class',resultcontent.className1);
							tdTag.append(inputTag); 
						}						
						trTag.append(tdTag);
						
						tdTag = $('<td style="width:50%"/>');
						if(mode=='print') {
							var lblTag = $('<label/>');
							lblTag.html(resultcontent.value1);
							tdTag.append(lblTag);
						} else {
							var inputTag=$('<input type="text" id="'+ resultcontent.id + '_1' +'" value="' + resultcontent.value1 + '"/>');
							if(resultcontent.className)
								inputTag.attr('class',resultcontent.className);
							tdTag.append(inputTag); 
						}	
						if(resultcontent.unit) {
							spanTag = $('<span/>');
							spanTag.html(resultcontent.unit);
							tdTag.append(spanTag);
						}
						trTag.append(tdTag);
						tbodyTag.append(trTag);
					} else {
						var tdTag = $('<td style="width:50%"/>');
						tdTag.html(resultcontent.normalRangeAttribute); // add first column commonly caption
						trTag.append(tdTag);
						tdTag = $('<td style="width:50%" align="left"/>');
						if(mode=='print') {
							if(analysis.analysisType=="SPERM MOTILITY")
								var lblTag = $('<div style="float:left;text-align:right;padding-right:5px;min-width:10%" />');
							else
								var lblTag = $('<div style="float:left;padding-right:5px;" />');
								if(resultcontent.value=="")
									lblTag.html("--");
								else
									lblTag.html(resultcontent.value);
							tdTag.html(lblTag);
						} else {
							var inputTag=$('<input type="text" id="'+resultcontent.id +'" value="' + resultcontent.value + '"/>');
							if(resultcontent.className)
								inputTag.attr('class',resultcontent.className);
							tdTag.html(inputTag); 
						}
						if(resultcontent.unit) {
							spanTag = $('<span/>');
							if(analysis.analysisType=="SPERM MOTILITY"){
								if(azospermia==false && resultcontent.value.toUpperCase()!="NIL" && resultcontent.value!="--" && resultcontent.value!="-" && resultcontent.value!="") 
									spanTag.html(resultcontent.unit);
								else
                                    spanTag.html("");								
							}else
							 spanTag.html(resultcontent.unit);
							tdTag.append(spanTag);
						}
						trTag.append(tdTag);
						tbodyTag.append(trTag);
					}
				});
			}	
			semenTable.append(tbodyTag);			
		});	
		content.append(semenTable);
		return content; 
	};
}	
function stone(honorific,result,testId,mode) {
this.result =function(){ 
		var content =$('<div/>');
		var table=$('<table width="100%" />');
		var thead=$('<thead><th colspan="2">'+result.analysisType+'</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.resultContent).each(function(ResultContentIndex,resultContentVal){
			if(resultContentVal.title){
				var newrow=$('<tr><td>'+resultContentVal.title+'</td></tr>');
				tbody.append(newrow);
			}
			newrow=$('<tr><td></td></tr>');
			newtd=$('<td/>');
			var newtable=$('<table id="StoneTable"/>');
			var newtablebody=$('<tbody/>');
			$(resultContentVal.resultParams).each(function(index,tblVal){
				 var tablerow=$('<tr/>');
				 var tablecolumns=$('<td colspan="2">'+tblVal.Attribute+'<td>');
				 tablerow.append(tablecolumns);
				 if(tblVal.colon){
					tablecolumns=$('<td >'+tblVal.colon+'<td>');
					tablerow.append(tablecolumns);
				}
				tablecolumns=$('<td width="100%"/>');
				if(tblVal.displayType=="text"){
					var spanTag=$('<span />');
					if(mode=="print") {
						var lblTag = $('<label/>');
						lblTag.html(tblVal.value);
						spanTag.append(lblTag);
					}
					else{		
						var inputTag=$('<input type="text" class="'+tblVal.className+'" id="Stone_' + index +'_1" value="' + tblVal.value + '"/>');
						spanTag.append(inputTag)			
			          }
					tablecolumns.append(spanTag);
					tablerow.append(tablecolumns);
					newtablebody.append(tablerow);
			    }
				else if(tblVal.displayType=="textarea"){
					var spanTag=$('<span />');
					if(mode=="print") {
						var lblTag = $('<label/>');
						lblTag.html(tblVal.value);
						spanTag.append(lblTag);
					}														
					else{		
						 var textarea=$('<textarea class="large" id="Stone_3_1" style="width:100%" >'+tblVal.value+'</textarea>');	
						spanTag.append(textarea);						 
						
					}
					tablecolumns.append(spanTag);
					tablerow.append(tablecolumns);
					newtablebody.append(tablerow);
			    } 
			});
			newtable.append(newtablebody);
			newtd.append(newtable)
			newrow.append(newtd);
			tbody.append(newrow);   
			 
		 });
		 table.append(tbody);
		 content.append(table);
		return content; 
	};
}	
function stoolLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<div/>');
		var fstDiv = $('<div  height="20px;" style="font-size:18px; line-height:40px;" align="center"> STOOL ANALYSIS</div>');
		content.append(fstDiv);
		var SecDiv = $('<div class="five_sixth"/>');
	    var table=$('<table id="SpHgtTable" style="width:550px;"/>');
		var tablebody=$('<tbody/>')
		var count;
		$(result.values).each(function(index,tblVal){
		    var row=$('<tr/>');
			var rowColumns=$('<td width="35%" >'+tblVal.testExamine+'</td>');
			row.append(rowColumns);
			 var rowColumns=$('<td/>');
			 var spanTag=$('<span />');
			 if(mode=="print") {
				var lblTag = $('<label/>');
				lblTag.html(tblVal.value);
				spanTag.append(lblTag);
			}
			else{
				 var inputTag=$('<input type="text" class="'+tblVal.className+'"  style="font-size:15px;"id="Stvalue_' + index +'_1" value="' + tblVal.value + '"/>');
				 spanTag.append(inputTag);
			}
            rowColumns.append(spanTag);			
			row.append(rowColumns);
			tablebody.append(row);			
		});
		var newrow=$('<tr><td colspan="2" align="center" style="font-size:18px;" class="microscopy">Microscopy</td></tr>');
		tablebody.append(newrow);
		$(result.testMicrosopy).each(function(index,tblVal){
			var row=$('<tr/>');	
			if(mode=="print") {
			    var column=$('<td width="35%" ></td>');
				var spanTag=$('<span />');
				var lblTag = $('<label/>');
				lblTag.html(tblVal.MSresult1);
				spanTag.append(lblTag);
				column.append(spanTag);
				row.append(column);
				column=$('<td></td>');
				spanTag=$('<span />');		
				lblTag = $('<label/>');
				lblTag.html(tblVal.MSresult2);
				spanTag.append(lblTag);
				column.append(spanTag);
				row.append(column);	
				}
			else{
				var column=$('<td width="35%" ></td>');
				var spanTag=$('<span />');				
				var inputTag=$('<input type="text" id="StMicro_' + index +'_1" class="'+tblVal.className+'"  value="' + tblVal.MSresult1 + '"/>');
				spanTag.append(inputTag);
				column.append(spanTag);
				row.append(column);
				column=$('<td/>');
				spanTag=$('<span />');
				inputTag=$('<input type="text" id="StMicro_' + index +'_2" class="'+tblVal.className+'"  value="' + tblVal.MSresult2 + '"/>');
				spanTag.append(inputTag);
				column.append(spanTag);
				row.append(column);
				}
					
			if(mode=="print") {
				if(tblVal.MSresult1.trim()!="") 
					tablebody.append(row); 
				
			} else
				tablebody.append(row);
		});
		var lastrow=$('<tr><td> Occultblood</td></tr>');
		var newRow=$('<td/>');
		var spanTag=$('<span />');
		if(mode=="print") {
			var lblTag = $('<label/>');
			lblTag.html(result.Occubld);
			spanTag.append(lblTag);
		}else{
			var inputTag=$('<input type="text" id="StOccubld_0_1" style="font-size:15px;" class="medium common" value="' + result.Occubld + '"/>');
			spanTag.append(inputTag);
		}
		newRow.append(spanTag);
		lastrow.append(newRow);				
		tablebody.append(lastrow);
		table.append(tablebody);
		SecDiv.append(table);
		content.append(SecDiv);
		return content;
	};
}
function UrineLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div align="center"/>');
		var titleSection = $('<div align="center" class="resultTitle">'+result.testName + '</div>');
		content.append(titleSection);			
		var tableTag=$('<table id="SpHgtTable" width="100%"/>');
		var tbodyTag=$('<tbody/>');		
		$(result.values).each(function(index,values){
			var trTag=$('<tr/>');
			var tdTag=$('<td style="width:20%">'+values.testnameval+'</td>'); 
			trTag.append(tdTag);
			tdTag=$('<td style="width:30%"></td>'); 			
			if(mode=='print') {
				var lblTag = $('<label/>');
				lblTag.html(values.value1);
				tdTag.append(lblTag);
			} else {
				if(values.displayType1=="text")
					var inputTag=$('<input type="text" id="US_'+ index +'_11" name="US_'+ index +'_1" value="'+values.value1+'" tabIndex=' +values.tabIndex1 + '/>');
				if(values.displayType1=="select")
					var inputTag=$('<select id="US_'+ index +'_11" name="US_'+ index +'_1" value="'+values.value1+'" tabIndex=' +values.tabIndex1 +'/>');	
				if(values.className1)
					inputTag.attr('class',values.className1);
				tdTag.append(inputTag);				
			}
			trTag.append(tdTag);			
			if(index=='0'){
				tdTag=$('<td colspan="2" style="text-align:center; width:50%">'+values.testnameval2+'</td>');
				trTag.append(tdTag);
			} else { // index !=0
				if(values.testnameval2==""){
					tdTag=$('<td style="width:20%"/>');					
					if(mode=='print') {
						var lblTag = $('<label/>');
						lblTag.html(values.editField);
						tdTag.append(lblTag);
					} else {
						var inputTag=$('<input type="text" id="US_'+ index +'_02" value="'+values.editField+'"/>');
						tdTag.append(inputTag);
					} 					 
				} else
					tdTag=$('<td style="width:20%">'+values.testnameval2+'</td>');				
				trTag.append(tdTag);				
				tdTag=$('<td style="width:30%"/>');			
				if(mode=='print') {
					var lblTag = $('<label/>');
					lblTag.html(values.value2);
					tdTag.append(lblTag);
				} else {
					if(values.displayType2=="text")
						var inputTag=$('<input type="text" id="US_'+ index +'_12" value="'+values.value2+'" tabIndex=' +values.tabIndex2 +'/>');
				    if(values.displayType2=="select")
						var inputTag=$('<select id="US_'+ index +'_12" value="'+values.value2+'" tabIndex=' +values.tabIndex2 +'/>');
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
function urineLayoutMed(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content=$('<div class="urineLayoutMed"/>');
		$(result.group).each(function(index, group){
			if(group.groupName) {
				var urineGroupRow = $('<div class="one singleheight">&nbsp;</div>');
				var urineRow =  $('<div class="onefirst bold underline">&nbsp;</div>');
				urineRow.empty().html(group.groupName);
				if(group.valueRequired==true) {
					if(mode=="print")
						urineRow.append(group.value);
					else {
						var elementTag = $('<input type="text"/>');
						elementTag.attr('id',group.id);
						elementTag.html(group.value);
						urineRow.append(elementTag);
					}
				}
				urineGroupRow.append(urineRow);
				content.append(urineGroupRow);
			}
			$(group.params).each(function(index, param){
				var urineRow = $('<div class="one singleheight">&nbsp;</div>');
				var urineKey = $('<div class="onefirst">&nbsp;</div>');
				urineKey.empty().html(param.key);
				urineRow.empty().html(urineKey);
				var urineVal = $('<div class="onesecond">&nbsp;</div>');
				if(mode=="print"){
					urineVal.append(param.value);
				}else {
					var elementTag = $('<input type="text"/>');
					elementTag.attr('id',param.id);
					elementTag.html(param.value);
					if(param.className)
						elementTag.addClass(param.className);
					urineVal.append(elementTag);
				}
				urineRow.append(urineVal);
				var urineRef = $('<div class="onesecond">&nbsp;</div>');
				urineRef.append(param.normalvalue);
				urineRow.append(urineRef);
				content.append(urineRow);
			});
		});
		return content;
	};	
}
function waterCultureLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<div/>');
		//div for report title starts here
		var headerDiv = $('<div style="text-align:middle;"/>');
		var headerContent=$('<h3 align="center" style="padding:10px 0 10px 0px"/>');
		headerContent.html(result.analysis.resultTitle);
		headerDiv.append(headerContent);
		content.append(headerDiv);
		//div for report title ends here
		var tblDiv = $('<div/>');
		var resultTable = $('<table id="cultureresultTable" style="float:left; margin-left:40px; margin-bottom:10px; width:90%;font-size:92%" />');
		var theadTag = $('<thead/>');
		var trTag=$('<tr />');	
		$(result.analysis.columns).each(function(index, column) {
			var thTag = $('<th />');
			thTag.append(column);
			trTag.append(thTag);
		});
		theadTag.append(trTag);	
		resultTable.append(theadTag);
		var tbodyTag = $('<tbody/>');
		 var	trTag=$('<tr />');		
		$(result.analysis.data).each(function(index,data) {			
			var tdTag = $('<td style="vertical-align:middle; padding-left:5px; line-height:50px;"/>');
			var inputTag;
			if(data.display) {
				if(data.display=='text') {					
					if(mode=="print") {
						tdTag.append(data.value + data.unit);
					} else {
						inputTag=$('<input type="text" style="width:50px;"/>');
						inputTag.attr('id',"txtWaterCSResult");
						inputTag.addClass('middle');
						inputTag.val(data.value);
						tdTag.append(inputTag);
						spanTag=$('<span style="margin-left:10px;"/>');
						spanTag.append(data.unit);
						tdTag.append(spanTag);
					}					
					tdTag.append($('<br/>'));
					tdTag.append($('<br/>'));
					spanTag=$('<span style="margin-left:10px;">Water comes under </span>');
					tdTag.append(spanTag);					
					if(mode=="print") {
						tdTag.append(data.result);
					} else {
						inputTag=$('<input type="text" id="lblwatercsResult"/>');
						inputTag.val(data.result);
						tdTag.append(inputTag);
					}					
				} else if(data.display=="textarea"){
					if(mode=="print") {
						tdTag.append(commonMethodInvoker.nl2br(data.value));
					} else {
						inputTag=$('<textarea style="min-height:150px"/>');
						inputTag.attr('id',"txt"+index);
						inputTag.text(data.value);
						tdTag.append(inputTag);
					}										
				} else {
					tdTag.append(data.value);
				}
			}	
			trTag.append(tdTag);
			tbodyTag.append(trTag);
		});
		resultTable.append(tbodyTag);
		tblDiv.append(resultTable);	
		content.append(tblDiv);		
		return content;
	};	
}
function widal(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content=$('<div />');
		var widaltitle = $('<div class="one bold underline singleheight">&nbsp;</div>');
		widaltitle.append(result.testTitle);
		content.append(widaltitle);
		$(result.values).each(function(index, value){
			var widalRow = $('<div class="one singleheight">&nbsp;</div>');
			var widalKey = $('<div class="onefirst">&nbsp;</div>');
			widalKey.empty().html(value.key);
			widalRow.empty().html(widalKey);
			var widalVal = $('<div class="onesecond">&nbsp;</div>');
			if(mode=="print"){
				if(value.value!="")
					widalVal.empty().html(value.value);
			}else {
				var elementTag = $('<input type="text"/>');
				elementTag.attr('id',value.id);
				elementTag.html(value.value);
				widalVal.empty().html(elementTag);
			}
			widalRow.append(widalVal);
			var widalRef = $('<div class="onesecond">&nbsp;</div>');
			if(value.normalvalue!="")
				widalRef.empty().html(value.normalvalue);
			widalRow.append(widalRef);
			content.append(widalRow);
		});
		return content;
	};	
}
function ResultPrintResponseDTO() {
	this.getOrderUid = function() {return this.orderUid;}
	this.setOrderUid = function(orderUid) {this.orderUid=orderUid;}
	this.getResult = function() {return this.result;}
	this.setResult = function(result) {this.result=result;}
	this.getReportDate = function() {return this.reportingDate;}
	this.setReportDate = function(reportingDate) {this.reportingDate=reportingDate;}
	this.getReportTime = function() {return this.reportingTime;	}
	this.setReportTime = function(reportingTime) {this.reportingTime=reportingTime;}
	this.isPrinted = function() {return this.printed;}
	this.setPrinted = function(printed) {this.printed=printed;}
	this.getTestList= function() {return this.testList;	}
	this.setTestList= function(testList) {this.testList=testList;}
}
function TestListJsonDTO() {
	this.setTestName= function(testName) {this.testName= testName;}
	this.getTestName= function() {return this.testName;}
	this.setReportingDate= function(reportingDate) {this.reportingDate= reportingDate;}
	this.getReportingDate= function() {	return this.reportingDate;	}
	this.setReportingTime= function(reportingTime) {this.reportingTime= reportingTime;}
	this.getReportingTime= function() {	return this.reportingTime;	}
	this.setSpecimens= function(specimens) {this.specimens= specimens;}
	this.getSpecimens= function() {	return this.specimens;	}
	this.setResult= function(result) {	this.result= result;}
	this.getResult= function() {return this.result;	}	
}