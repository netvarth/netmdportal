var methodInvoker = new MethodInvoker();
$(function() {
	$('.filter-main').hide();
	$('#filter').click(function () {
        $('.filter-main').toggle(500);
		$('#filterWorkBench').hide();
		dpi_y = document.getElementById('testdiv').offsetHeight;
		methodInvoker.setDPI(dpi_y);
	});
	var homePage = new SuperadminHome();
	homePage.init();
});	
function SuperadminHome(){
	this.showMyName = function() {return "SuperadminHome";}
	this.setUser=function(user){this.user = user;}
	this.getUser = function(){return this.user;}
	this.init=function(){
		
	}

}
function MethodInvoker() {
	this.setDPI=function(dpi){this.dpi = dpi;}
	this.getDPI = function(){return this.dpi;}
}