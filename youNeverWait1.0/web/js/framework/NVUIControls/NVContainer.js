function Container(contentgroup,resultId) {
    this.result = function () {
     
	   var content = $j('<div>');
        content.attr('class', contentgroup.className);
        if (contentgroup.id) content.attr('id', contentgroup.id);
        if (contentgroup.style) content.attr('style', contentgroup.style);
        $j(contentgroup.elements).each(function (index, buttonelement) {
            if (buttonelement.type == "radio") content.append((new radio(buttonelement).result));
            if (buttonelement.type == "checkbox") content.append((new checkbox(buttonelement).result));
            if (buttonelement.type == "combocheckbox") content.append((new combocheckbox(buttonelement).result));
            if (buttonelement.type == "text") content.append((new text(buttonelement).result));
            if (buttonelement.type == "label") content.append((new label(buttonelement).result));
            if (buttonelement.type == "password") content.append((new password(buttonelement).result));
            if (buttonelement.type == "textarea") content.append((new textarea(buttonelement).result));
            if (buttonelement.type == "select") content.append((new select(buttonelement).result));
            if (buttonelement.type == "combined") content.append((new combinedControl(buttonelement).result));
            if (buttonelement.type == "table") content.append((new Table(buttonelement.content).result));
            if (buttonelement.type == "file") content.append((new file(buttonelement).result));
            if (buttonelement.type == "newlabel") content.append((new newlabel(buttonelement).result));
            if (buttonelement.type == "datalabel") content.append((new datalabel(buttonelement).result));
            if (buttonelement.type == "head1") content.append((new head1(buttonelement).result));
            if (buttonelement.type == "head2") content.append((new head2(buttonelement).result));
			
			if(buttonelement.type=="TestNameTable"){
			   var response = postdataToServer("/youNeverWait/ws/ui/patient/patientTestResult/",resultId); 
			  //alert(JSON.stringify(response));
			   if(response.success==true)
			  var sample=$j.parseJSON(response.testResult);
			  //alert(JSON.stringify(sample.testList));
				content.append((new TestNameTable(sample.testList,resultId).result)); 
			}

		   if (buttonelement.type == "completeTable") {
                var response = getRequestData('/weblims/ws/ui/admin/permissionList/');
                if (response.success == true) content.append((new CompleteTable(response).result))
            }
            if (buttonelement.type == "button") {
                $j(buttonelement.content.buttons).each(function (index, btn) {
                    content.append((new button(btn).result))
                })
            }
            if (buttonelement.type == "tab") {
                content.append((new tabs(buttonelement.content.tabs).result))
            }
            if (buttonelement.type == "hidden") content.append((new hidden(buttonelement).result));
            if (buttonelement.data) {
                $j(buttonelement.data).each(function (index, group) {
                    var contain = new Container(group);
                    content.append(contain.result)
                })
            }
        });
        $j(contentgroup.data).each(function (index, group) {
            var contain = new Container(group);
            content.append(contain.result)
        });
        return content
    }
}