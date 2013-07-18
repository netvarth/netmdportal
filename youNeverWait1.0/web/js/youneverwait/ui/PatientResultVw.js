function PatientResultVw() {
	this.patientresultId=$j("#select").val();
}

PatientResultVw.prototype.getNameListObj = function(patientresultId) {
	return this.patientresultId;
}


PatientResultVw.prototype.viewresult= function(url,parent) {
	
	var PatientresId=this.getNameListObj();
		PatientresId=PatientresId.split("_")[0];
	/* var resultview=url+PatientresId;	
	ajaxProcessor.setUrl(resultview);
	var tabdata =ajaxProcessor.get();
	alert(JSON.stringify(tabdata));
	$j(parent).html(new resultpatient(tabdata).result); */
	
	
	var pgNetLimsList;
	var exp=[]; //For storing the filter expressions
	var pgTableName = "#labTable"; // Table showing netlims list
	pgTableContainer = "#labTableCont"; // Parent container of the netlims list table
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 8;// Interval between pages
	var curPage = 1;//Current selected page
	var operator="eq";
	
	
	
	var filter=getResultFilterlistUrl(PatientresId,operator);
	var resultTable=setResultTableStructure();
	$j(parent).html(resultTable.html());
		setCustomDataTable(pgTableName);
	
	var passresultjson=passingJson(filter,(curPage-1),interval);
	pgNetlimsList = fillResultLabTable(passresultjson,pgTableName);
	if(pgNetlimsList.count)
		maxRecords = pgNetlimsList.count;	
	if(maxRecords%interval!=0)
		maxPages = parseInt(maxRecords/interval) + 1;
	else
		maxPages = parseInt(maxRecords/interval);	
	setPaginationFields(curPage, maxPages, pgTableContainer);
	
	
$j(pgTableContainer +' #next').die('click').click(function() {
	if(curPage!=maxPages && curPage<maxPages) {
	curPage+=1;
	var startValue = interval*(curPage-1);
	 passresultjson=passingJson(filter,startValue,interval);
	pgNetlimsList = fillResultLabTable(passresultjson,pgTableName);
	setPaginationFields(curPage, maxPages,pgTableContainer);
}
});
$j(pgTableContainer +' #previous').die('click').click(function() {
	if(curPage!=1) {
	curPage-=1;
	var startValue = interval*(curPage-1);
	 passresultjson=passingJson(filter,startValue,interval);
	pgNetlimsList = fillResultLabTable(passresultjson,pgTableName);
	setPaginationFields(curPage, maxPages,pgTableContainer);
}
});
$j(pgTableContainer +' #first').die('click').click(function() {
if(curPage!=1) {
curPage=1;
startValue = 0;
 passresultjson=passingJson(filter,startValue,interval);
	pgNetlimsList = fillResultLabTable(passresultjson,pgTableName);

setPaginationFields(curPage, maxPages, pgTableContainer);
}
});
$j(pgTableContainer +' #last').die('click').click(function() {
if(curPage!=maxPages && curPage<maxPages) {
curPage =maxPages;
startValue = (curPage-1)*interval;
 passresultjson=passingJson(filter,startValue,interval);
	pgNetlimsList = fillResultLabTable(passresultjson,pgTableName);
setPaginationFields(curPage, maxPages, pgTableContainer);
}
});	
	
}
 function getResultFilterlistUrl(PatientresId,operator){

	var listJson='[{"name":"' + "patientId" + '", "value":"' + PatientresId + '","operator":"'+operator + '"}]';
	return listJson;
} 

function passingJson(filter,curPage,interval){
var passjson='{"exp":'+ filter +',';
	passjson+='"from":'+ curPage +',';
	passjson+='"count":'+interval+',';
	passjson+='"asc":'+ false+'';
	passjson+='}';
	return passjson;
	}
function setResultTableStructure() {
//alert(" set refferal tbl");
var tblData = getRequestData('/youNeverWait/json/view/labResultTable.json');
var boxDiv = $j('<div/>');
$j(tblData.data).each(function(index,subgroup) {
var cont = new Container(subgroup);
boxDiv.append(cont.result);
});
return boxDiv;
}

function fillResultLabTable(passjson,pgTableName) {
	
	var response=postdataToServer("/youNeverWait/ws/ui/patient/getresultList", passjson );
	//alert(JSON.stringify(response));
	if(response.resultList) {
		if(response.resultList.length>0) {	
			$j(pgTableName).dataTable().fnClearTable();
			$j(response.resultList).each(function (index, past) {
				var id=past.orderId;
				
				var rowData=$j(pgTableName).dataTable().fnAddData([id,past.orderDate,past.labName,past.labBranch]);
				var row=$j(pgTableName).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				$j(row).children("td:nth-child(1)").attr("class","resultIdCol Ustyle");
				});	
		}
	}		
	return response;
	
	
}

$j('.resultIdCol').die('click').live('click',function(){
		    var resId= $j(this).parent().attr('id');
		   if(resId!="") {
				var obj=$j(this);
				removeErrors();
					 var PatientId=$j("#select").val();
						PatientId=PatientId.split("_")[0];
					var resultId='{"patientId":'+ PatientId +',';
						resultId+='"orderId":"'+ resId +'"';
						resultId+='}';
				createModal('/youNeverWait/json/resulttest.json','testNamesModal',resultId);		
				openModalBox(obj,'testNamesModal')			
			}	 
		
		}); 
		
	$j(".accordion").die ('click').live('click', function() {
		var source = $j(this).children('th').children('img');
		if(source.attr('name')=="expand") {
			source.attr('src','/youNeverWait/images/collapse.jpg');
			source.attr('name','collapse');
			source.parents('thead').next('tbody').show();
		}
		else {
			 source.attr('src','/youNeverWait/images/expand.jpg');
			 source.attr('name','expand');
			 source.parents('thead').next('tbody').hide();
			}  
	});	