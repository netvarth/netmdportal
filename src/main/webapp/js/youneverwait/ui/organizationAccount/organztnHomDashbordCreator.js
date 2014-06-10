function organztnHomeCreator() {
	this.newhomeReportPage = '#reportHomeViewForm';
	this.homeTable = "#reportHomeViewForm #homeDetailsViewTable";
	
}


organztnHomeCreator.prototype.viewHome= function(url,parent) {
	self =this;
	ajaxProcessor.setUrl(url);
	var tabdata =ajaxProcessor.get();
	$j(parent).html(new form(tabdata).result);
	self.setviewhomeListTable(self.homeTable);
}

organztnHomeCreator.prototype.setviewhomeListTable= function(tableObj) {
	self =this;
	var rows = "";
	rows += "<tr><td><b>" +"Total Number of Hospitals" + "</b></td><td><b>" + "5" + "</b></td></tr>";
	rows += "<tr><td><b>" +"Total Number of Births" + "</b></td><td><b>" + "10" + "</b></td></tr>";
    $j(rows).appendTo("#homeDetailsViewTable");
}
