function ViewOrgBranchPTB(viewOrgUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewOrgUI = viewOrgUI;
	
	this.getViewOrgUI = function() {
		return this.viewOrgUI;
	}
		
	this.getOrgUIStartup = function() {
		var viewOrgUI = this.getViewOrgUI();
		return viewOrgUI.getOrgUIStartup();
	}
	
	this.getorgnAccTableNavigator = function() {
		var OrgUIStartup = this.getOrgUIStartup();
		return OrgUIStartup.getorgnAccTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewOrgUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var orgUIStartup = self.getOrgUIStartup();
			orgUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var orgTableNavigator = self.getorgnAccTableNavigator();
			var orgInfo = source.getBranch();
			var prevId = self.getPrevId(orgInfo.userDetails.globalId,orgTableNavigator.getPgDataList());
			viewUI.viewOrgBranchDetails(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var orgTableNavigator = self.getorgnAccTableNavigator();
			var orgInfo = source.getBranch();
			var prevId = self.getNextId(orgInfo.userDetails.globalId,orgTableNavigator.getPgDataList());
			viewUI.viewOrgBranchDetails(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.ORGBRCHGENERAL, constants.GENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}