function PageHandler() {
	this.getHomePageUrl=function() {
		return "/weblims/ws/ui/html/startUp";
	}
	
	this.create = function(url) {
		ajaxProcessor.setUrl(url);
		var pageContent = ajaxProcessor.get();
		var page = new form(pageContent);
		$j('#tabs-1').html(page.result);
	}
	
	this.buttons = function(url, parent) {
		//alert(url);
		ajaxProcessor.setUrl(url);
		var response = ajaxProcessor.post();
		//var response=url
		var buttonsDiv = new buttonsContainer(response);
		$j(parent).html(buttonsDiv.result);
	}
}