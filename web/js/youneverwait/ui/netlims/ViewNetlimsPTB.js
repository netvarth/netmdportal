function ViewNetlimsPTB(viewNetlimsUI) {
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
	
	this.getnetlimsTableNavigator = function() {
		var NetlimsUIStartup = this.getNetlimsUIStartup();
		return NetlimsUIStartup.getnetlimsTableNavigator();
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
			var netlimsTableNavigator = self.getnetlimsTableNavigator();
			var netlimsInfo = source.getNetlims();
			var prevId = self.getPrevId(netlimsInfo.lab.globalId,netlimsTableNavigator.getPgDataList());
			viewUI.viewNetlimsDetails(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var netlimsTableNavigator = self.getnetlimsTableNavigator();
			var netlimsInfo = source.getNetlims();
			var prevId = self.getNextId(netlimsInfo.lab.globalId,netlimsTableNavigator.getPgDataList());
			viewUI.viewNetlimsDetails(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.NETLIMSGENERAL, constants.GENERALPTBURL);
		this.bindEvents(ptbParent,source);
	}
}