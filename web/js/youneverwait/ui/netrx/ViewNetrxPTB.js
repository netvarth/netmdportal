function ViewNetmdPTB(viewNetmdUI) {
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
	
	this.getnetmdTableNavigator = function() {
		var netmdUIStartup = this.getNetmdUIStartup();
		return netmdUIStartup.getnetmdTableNavigator();
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
			var netmdTableNavigator = self.getnetmdTableNavigator();
			var netmdInfo = source.getNetmd();
			var prevId = self.getPrevId(netmdInfo.netMd.globalId,netmdTableNavigator.getPgDataList());
			viewUI.viewNetmdDetails(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var netmdTableNavigator = self.getnetmdTableNavigator();
			var netmdInfo = source.getNetmd();
			var prevId = self.getNextId(netmdInfo.netMd.globalId,netmdTableNavigator.getPgDataList());
			viewUI.viewNetmdDetails(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.NETLIMSGENERAL, constants.GENERALPTBURL);
		this.bindEvents(ptbParent,source);
	}
}