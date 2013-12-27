function ViewSpecimenPTB(viewSpecimenUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewSpecimenUI = viewSpecimenUI;

	this.getViewSpecimenUI = function() {
		return this.viewSpecimenUI;
	}
		
	this.getSpecimenUIStartup = function() {
		var viewSpecimenUI = this.getViewSpecimenUI();
		return viewSpecimenUI.getSpecimenUIStartup();
	}
	
	this.getSpecTableNavigator = function() {
		var specimenUIStartup = this.getSpecimenUIStartup();
		return specimenUIStartup.getSpecTableNavigator();
	}
	
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewSpecimenUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var specimenUIStartup = self.getSpecimenUIStartup();
			specimenUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var specTableNavigator = self.getSpecTableNavigator();
			var specimen = source.getSpecimen();
			var prevId = self.getPrevId(specimen.getId(),specTableNavigator.getPgDataList());
			viewUI.viewSpecimen(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var specTableNavigator = self.getSpecTableNavigator();
			var specimen = source.getSpecimen();			
			var nextId = self.getNextId(specimen.getId(),specTableNavigator.getPgDataList());
			viewUI.viewSpecimen(nextId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.SPECIMENGENERAL, constants.SPECIMENGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	}
	
}
