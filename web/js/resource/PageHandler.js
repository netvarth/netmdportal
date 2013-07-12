function PageHandler(startUrl) {
	this.startUrl = startUrl;
	
	this.getHomePageUrl=function() {
		this.startUrl;
	}
	
	this.create = function(url,parent) {
		ajaxProcessor.setUrl(url);
		var pageContent = ajaxProcessor.get();
		var page = new form(pageContent);
		$j(parent).html(page.result);
	}
	
	this.buttons = function(url, parent) {
		ajaxProcessor.setUrl(url);
		var response = ajaxProcessor.post();
		var buttonsDiv = new buttonsContainer(response);
		$j(parent).html(buttonsDiv.result);
	}
}