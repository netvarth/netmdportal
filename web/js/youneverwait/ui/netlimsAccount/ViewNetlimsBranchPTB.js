function ViewNetlimsBranchPTB(viewNetlimsUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewNetlimsUI = viewNetlimsUI;
	
	this.getViewNetlimsUI = function() {
		return this.viewNetlimsUI;
	}
		
	this.getNetlimsUIStartup = function() {
		var viewNetlimsUI = this.getViewNetlimsUI();
		return viewNetlimsUI.getNetlimsUIStartup();
	}
	
	this.getnetlimsAccTableNavigator = function() {
		var NetlimsUIStartup = this.getNetlimsUIStartup();
		return NetlimsUIStartup.getnetlimsAccTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewNetlimsUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var netlimsUIStartup = self.getNetlimsUIStartup();
			netlimsUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var netlimsTableNavigator = self.getnetlimsAccTableNavigator();
			var netlimsInfo = source.getBranch();
			var prevId = self.getPrevId(netlimsInfo.branch.globalId,netlimsTableNavigator.getPgDataList());
			viewUI.viewNetlimsBranchDetails(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var netlimsTableNavigator = self.getnetlimsAccTableNavigator();
			var netlimsInfo = source.getBranch();
			var prevId = self.getNextId(netlimsInfo.branch.globalId,netlimsTableNavigator.getPgDataList());
			viewUI.viewNetlimsBranchDetails(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.NETLIMSACCBRCHGENERAL, constants.GENERALPTBURL);
		this.bindEvents(ptbParent,source);
	}
}