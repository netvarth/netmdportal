function ViewNetPosPTB(viewNetPosUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewNetPosUI = viewNetPosUI;
	
	this.getViewNetPosUI = function() {
		return this.viewNetPosUI;
	}
		
	this.getNetPosUIStartup = function() {
		var viewNetPosUI = this.getViewNetPosUI();
		return viewNetPosUI.getNetPosUIStartup();
	}
	
	this.getNetPosTableNavigator = function() {
		var netPosUIStartup = this.getNetPosUIStartup();
		return netPosUIStartup.getNetPosTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewNetPosUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var netPosUIStartup = self.getNetPosUIStartup();
			netPosUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var netPosTableNavigator = self.getNetPosTableNavigator();
			var netPos = source.getNetPos();
			var prevId = self.getPrevId(netPos.getId(),netPosTableNavigator.getPgDataList());
			viewUI.viewNetPos(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var netPosTableNavigator = self.getNetPosTableNavigator();
			var netPos = source.getNetPos();
			var prevId = self.getNextId(netPos.getId(),netPosTableNavigator.getPgDataList());
			viewUI.viewNetPos(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.NETPOSGENERAL, constants.GENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}