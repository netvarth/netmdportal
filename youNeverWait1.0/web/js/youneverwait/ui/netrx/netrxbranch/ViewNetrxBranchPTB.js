function ViewNetrxBranchPTB(viewNetrxUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewNetrxUI = viewNetrxUI;
	
	this.getViewNetrxUI = function() {
		return this.viewNetrxUI;
	}
		
	this.getNetrxUIStartup = function() {
		var viewNetrxUI = this.getViewNetrxUI();
		return viewNetrxUI.getNetrxUIStartup();
	}
	
	this.getnetrxAccTableNavigator = function() {
		var NetrxUIStartup = this.getNetrxUIStartup();
		return NetrxUIStartup.getnetrxAccTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewNetrxUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var netrxUIStartup = self.getNetrxUIStartup();
			netrxUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var netrxTableNavigator = self.getnetrxAccTableNavigator();
			var netrxInfo = source.getBranch();
			var prevId = self.getPrevId(netrxInfo.branch.globalId,netrxTableNavigator.getPgDataList());
			viewUI.viewNetrxBranchDetails(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var netrxTableNavigator = self.getnetrxAccTableNavigator();
			var netrxInfo = source.getBranch();
			var prevId = self.getNextId(netrxInfo.branch.globalId,netrxTableNavigator.getPgDataList());
			viewUI.viewNetrxBranchDetails(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.NETRXACCBRCHGENERAL, constants.GENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}