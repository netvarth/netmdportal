function ViewNetmdBranchPTB(viewNetmdUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewNetmdUI = viewNetmdUI;
	
	this.getViewNetmdUI = function() {
		return this.viewNetmdUI;
	}
		
	this.getNetmdUIStartup = function() {
		var viewNetmdUI = this.getViewNetmdUI();
		return viewNetmdUI.getNetmdUIStartup();
	}
	
	this.getnetmdAccTableNavigator = function() {
		var NetmdUIStartup = this.getNetmdUIStartup();
		return NetmdUIStartup.getnetmdAccTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewNetmdUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var netmdUIStartup = self.getNetmdUIStartup();
			netmdUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var netmdTableNavigator = self.getnetmdAccTableNavigator();
			var netmdInfo = source.getBranch();
			var prevId = self.getPrevId(netmdInfo.branch.globalId,netmdTableNavigator.getPgDataList());
			viewUI.viewNetmdBranchDetails(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var netmdTableNavigator = self.getnetmdAccTableNavigator();
			var netmdInfo = source.getBranch();
			var prevId = self.getNextId(netmdInfo.branch.globalId,netmdTableNavigator.getPgDataList());
			viewUI.viewNetmdBranchDetails(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.NETMDACCBRCHGENERAL, constants.GENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}