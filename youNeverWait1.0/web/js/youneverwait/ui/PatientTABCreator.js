function PatientTABCreator(){}PatientTABCreator.prototype.create=function(url,parent){ajaxProcessor.setUrl(url);var tabdata=ajaxProcessor.get();$j(parent).html(new patienttabs(tabdata).result);$j(parent).tabs()}