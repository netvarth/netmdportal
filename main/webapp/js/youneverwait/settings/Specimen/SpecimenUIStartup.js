function SpecimenUIStartup() {
	//alert("SpecimenUIStartup function");
	this.pgTableName = "#specimen";
	this.pgTableContainer="#specimenListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#specimenPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#specimenPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#specimenPTBContainer #btn_delete_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.specimenIdCol';
	this.exp = new ExpressionListDTO();
	this.specimenService = new SpecimenServiceImpl();
	this.listUrl = constants.SPECIMENLISTURL;
	this.specTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.specimenService,this.exp);
	this.ftbToolBar = '#specimen-filter-toolbar';
	this.filter = '#filter';
	this.viewSpecimenUI = new ViewSpecimenUI(this);
	this.filterActionButton = '#btnGo';
}

SpecimenUIStartup.prototype.getViewSpecimenUI = function() {
	return this.viewSpecimenUI;
}
SpecimenUIStartup.prototype.setSpecTableNavigator = function(specTableNavigator) {
	this.specTableNavigator = specTableNavigator;
}

SpecimenUIStartup.prototype.getSpecTableNavigator = function() {
	return this.specTableNavigator;
}

SpecimenUIStartup.prototype.getSpecimenService = function() {
	return this.specimenService;
}



//Set the page title of the specimen ui page
SpecimenUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

//Return the selected Specimen Id from the list table
SpecimenUIStartup.prototype.getSelectedSpecimenId = function () {
	var specimenId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selSpecimens = $j(this.pgTableName + ' tbody tr[selected]');
		if(selSpecimens.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONESPECIMEN);
		} else if(selSpecimens.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONESPECIMENONLY);
		else
			specimenId=selSpecimens.attr('id');
	}
	return specimenId;
}

SpecimenUIStartup.prototype.init = function() {
	//alert("init");
	var self = this;
	var specTableNavigator = self.getSpecTableNavigator();
	self.setPageTitle(constants.SPECIMENS);
	var exp = new ExpressionListDTO();
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.create(constants.SPECIMEN, constants.SPECIMENPAGETOOLBAR); //Create the Page tool Bar for Specimen
	//var ftbProcessor = new FilterToolBarProcessor();
	//ftbProcessor.create(constants.SPECIMEN,constants.SPECIMENFILTERKEY,constants.ENUMLISTURL);
	self.hideFilters();
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.SPECIMENLISTTABLEURL);//Create Table for Listing Specimen
	dataTableProcessor.setCustomTable(self.pgTableName);
	specTableNavigator.list();
	self.bindEvents();
	
}

SpecimenUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		commonMethodInvoker.removeErrors();
		createModal(constants.CREATESPECIMENUI,constants.SPECIMENMODAL);		
		openModalBox(obj,constants.SPECIMENMODAL);
		var newSpecimenUI = new NewSpecimenUI(self);
		newSpecimenUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		var specimenId=self.getSelectedSpecimenId(self.pgTableName);
		$j('#' + constants.SPECIMEN + '-filter-cont').hide();
		$j(self.filter).hide();
		if(specimenId!="") {
			var viewSpecimenUI = self.getViewSpecimenUI();
			viewSpecimenUI.init(specimenId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		var specimenId=self.getSelectedSpecimenId(self.pgTableName);
		if(specimenId!="") {
			var specimenService = self.getSpecimenService();
			var specimenResponse = specimenService.viewSpecimen(specimenId);
			//var confirmStatus = confirm(constants.SPECIMENDELETECONFIRM + specimenResponse.name);
			//if(confirmStatus==true) {				
				var specimenResponse = specimenService.deleteSpecimen(specimenId);
				if(specimenResponse.success==true) {
					showTip(constants.SPECIMENDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(specimenResponse.error));
				}
				var specTableNavigator = self.getSpecTableNavigator();
				specTableNavigator.list();
				
				/* global config value set function*/ 
				//configData = methodInvoker.getConfigData();
			//}
		}	
	});
	
	/*Page Tool Bar Events ends here*/
	
	/*Filter Tool Bar Events starts here*/
	$j(self.ftbToolBar + " a:not(:selected)").die('click').live('click',function() {
		var curObjName = $j(this).attr('name');
		$j(this).attr('selected','selected');
		$j(this).addClass('button_filter');
		$j('#lst'+curObjName).show();
		$j('#txt'+curObjName).show();
		$j('#txt'+curObjName).focus();
		methodInvoker.setReportFilterValues(curObjName);
	})
	
	$j(self.ftbToolBar + " a[selected]").die('click').live('click',function() {
		var curObjName = $j(this).attr('name');
		$j(this).removeAttr('selected');
		$j(this).removeClass('button_filter');
		$j('#lst'+curObjName).hide();
		$j('#txt'+curObjName).hide();
		$j(self.ftbToolBar + ' ' + self.filterActionButton).trigger('click');
	})
	
	$j(self.ftbToolBar + ' ' + self.filterActionButton).die('click').click(function(){
		removeErrors();
		var expList=new ExpressionListDTO();
		$j(self.ftbToolBar + " a[selected]").each(function(){
			var selName=$j(this).attr('name');
			var selTextValue=$j("#txt"+ selName).val();
			var selOperator = $j("#lst"+ selName).val();
			ExpressionListDTO
			var expr = new ExpressionDTO(selName,selTextValue,selOperator);
			expList.add(expr);
		});
		var specTableNavigator = self.getSpecTableNavigator();
		specTableNavigator.setExp(expList);
		specTableNavigator.list();
		
	});
	
	$j(self.ftbToolBar + ' input[type="text"]').die('keypress').live("keypress",function(e){
		if(e.keyCode==13)
			$j(self.ftbToolBar + ' ' + self.filterActionButton).trigger('click');
	});
	/*Filter Tool Bar Events ends here*/
	
}
SpecimenUIStartup.prototype.hideFilters = function() {
	var self=this;
	$j(self.ftbToolBar + " a[name='specimenUid']").hide();
}	
SpecimenUIStartup.prototype.bindEvents = function() {
	self = this;
	$j(self.pgTableName + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style',constants.SELECTEDROWCOLOR);
		}	
		removeErrors();
	});	
	
	$j(this.pgTableRowClass).die('click').live('click',function(){
	   var specimenId= $j(this).parent().attr('id');
	   $j('#' + constants.SPECIMEN + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(specimenId!="") {
			$j('#specimen-filter-wb').hide();
			var viewSpecimenUI = self.getViewSpecimenUI();
			viewSpecimenUI.init(specimenId);
		}	
	});
}