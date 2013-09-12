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
		self.list();
		self.setPaginationFields(self.sourceContainer);
	}	
}

DataTableNavigator.prototype.prev= function() {
	var self = this;
	if(self.curPage!=1) {
		self.curPage-=1;
		self.startValue = self.interval*(self.curPage-1);
		self.filterDTO = new FilterDTO(self.exp.getExpressionList(),self.startValue,self.interval,false);
		self.list();	
		self.setPaginationFields(self.sourceContainer);		
	}
}

DataTableNavigator.prototype.first=function() {
	var self = this;
	if(self.curPage!=1) {
		self.curPage=1;
		self.startValue = 0;
		self.filterDTO = new FilterDTO(self.exp.getExpressionList(),self.startValue,self.interval,false);
		self.list();
		self.setPaginationFields(self.sourceContainer);
	}	
}

DataTableNavigator.prototype.last= function() {
	var self = this;
	if(self.curPage!=self.maxPage && self.curPage<self.maxPage) {
		self.curPage =self.maxPage;
		self.startValue = (self.curPage-1)*self.interval;
		self.filterDTO = new FilterDTO(self.exp.getExpressionList(),self.startValue,self.interval,false);
		self.list();
		self.setPaginationFields(self.sourceContainer);
	}
}
DataTableNavigator.prototype.list = function() {
	var self=this;
	//alert("fgfg"+self.url);
	ajaxProcessor.setUrl(self.url);
	//alert(self.url);
	self.setFilterDTO();
	self.pgDataList = ajaxProcessor.post(self.filterDTO);
	self.setMaxPage(self.pgDataList.count);
	self.setPaginationFields(self.sourceContainer);
	self.tableObj.setTableValues(self.sourceTable,self.pgDataList);
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
	$j(tableObj + ' #first').removeClass('paginate_button_disabled');
	$j(tableObj + ' #next').removeClass('paginate_button_disabled');
	$j(tableObj + ' #previous').removeClass('paginate_button_disabled');
	$j(tableObj + ' #last').removeClass('paginate_button_disabled');
	$j(tableObj + ' #first').addClass('paginate_button');
	$j(tableObj + ' #next').addClass('paginate_button');
	$j(tableObj + ' #previous').addClass('paginate_button');
	$j(tableObj + ' #last').addClass('paginate_button');
	$j(tableObj + ' .paginate_active').html(self.curPage);
	if(self.curPage==1 && self.maxPage<=1) {
		$j(tableObj + ' #first').removeClass('paginate_button');
		$j(tableObj + ' #next').removeClass('paginate_button');
		$j(tableObj + ' #previous').removeClass('paginate_button');
		$j(tableObj + ' #last').removeClass('paginate_button');
		$j(tableObj + ' #first').addClass('paginate_button_disabled');
		$j(tableObj + ' #next').addClass('paginate_button_disabled');
		$j(tableObj + ' #previous').addClass('paginate_button_disabled');
		$j(tableObj + ' #last').addClass('paginate_button_disabled');
	}else if(self.curPage==1) {
		$j(tableObj + ' #previous').removeClass('paginate_button');
		$j(tableObj + ' #previous').addClass('paginate_button_disabled');
		$j(tableObj + ' #first').removeClass('paginate_button');
		$j(tableObj + ' #first').addClass('paginate_button_disabled');
	}else if(self.curPage==self.maxPage) {
		$j(tableObj + ' #first').removeClass('paginate_button_disabled');
		$j(tableObj + ' #first').addClass('paginate_button');
		$j(tableObj + ' #previous').removeClass('paginate_button_disabled');
		$j(tableObj + ' #previous').addClass('paginate_button');
		
		$j(tableObj + ' #last').removeClass('paginate_button');
		$j(tableObj + ' #last').addClass('paginate_button_disabled');
		$j(tableObj + ' #next').removeClass('paginate_button');
		$j(tableObj + ' #next').addClass('paginate_button_disabled');
	} 
}

DataTableNavigator.prototype.bindNavigateEvents = function(sourceObj) {
	var self=this;
	$j(sourceObj + " " + this.nextButton).die('click').live('click',function() {
		self.next();
	});
	$j(sourceObj + " " + this.lastButton).die('click').live('click',function() {
		self.last();
	});
	$j(sourceObj + " " + this.prevButton).die('click').live('click',function() {
		self.prev();
	});
	$j(sourceObj + " " + this.firstButton).die('click').live('click',function() {
		self.first();
	});
	
	
	
}