function ViewOrgnzPTB(vieworgUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewOrgnzUI = vieworgUI;
	
	this.getViewOrgnzUI = function() {
		return this.viewOrgnzUI;
	}
		
	this.getOrgUIStartup = function() {
		var viewOrgnzUI = this.getViewOrgnzUI();
		return viewOrgnzUI.getOrgUIStartup();
	}
	
	this.getorgznTableNavigator = function() {
		var orgUIStartup = this.getOrgUIStartup();
		return orgUIStartup.getorgznTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewOrgnzUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var orgUIStartup = self.getOrgUIStartup();
			orgUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var orgTableNavigator = self.getorgznTableNavigator();
			var orgInfo = source.getOrgnz();
			var prevId = self.getPrevId(orgInfo.organization.globalId,orgTableNavigator.getPgDataList());
			viewUI.viewOrganzDetails(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var orgTableNavigator = self.getorgznTableNavigator();
			var orgInfo = source.getOrgnz();
			var prevId = self.getNextId(orgInfo.organization.globalId,orgTableNavigator.getPgDataList());
			viewUI.viewOrganzDetails(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.ORGNGENERAL, constants.GENERALPTBURL);
		this.bindEvents(ptbParent,source);
	}
}