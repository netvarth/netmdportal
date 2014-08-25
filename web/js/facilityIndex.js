var ajaxProcessor=new ServerUrlProcessor();
dataTableProcessor = new DataTableProcessor();
constants = new Constants();
pageHandler = new PageHandler();
commonMethodInvoker = new CommonMethodInvoker();
var methodInvoker = new MethodInvoker();
var query = new Query();
$(function() {
	$('.filter-main').hide();
	$('#filter').click(function () {
        $('.filter-main').toggle(500);
		$('#filterWorkBench').hide();
	});
	var user = new User();
	user.setInfo();
	user.bindEvents();
	dpi_y = document.getElementById('testdiv').offsetHeight;
	methodInvoker.setDPI(dpi_y);
	order = new Order();
	order.init();
	
});

function Query() {
	this.viewUser = function(uid,branchId) {
		ajaxProcessor.setUrl(constants.USERURL + uid + "/" + branchId);
		return ajaxProcessor.get();
	}
}
function MethodInvoker() {
	this.setDPI=function(dpi){
		this.dpi = dpi;
	}
	this.getDPI = function(){
		return this.dpi;
	}
	this.setSetting= function(setting) {
		this.setting = setting;
	}
	this.getPageSettingByKey=function(key) {
		var self=this;
		var result = null; 
		$(self.setting).each(function(index, data){
			if(data.key==key){
				result = data;
				return false;
			}
		});
		return result;
	}
	this.getAgeValue=function(age){
		age= ""+age;
		var curAge="";
		if(age.indexOf('D')!=-1 || age.indexOf('d')!=-1){
			age = parseFloat(age);
			curAge = age/360;
		} else if(age.indexOf('M')!=-1 || age.indexOf('m')!=-1){
			age = parseFloat(age);
			curAge = (age*30)/360;
		} else if(age!=""){
			age=parseFloat(age);
			curAge = age;
		}
		return curAge;
	}
	this.getCurrentNormalRange = function(order, testResult) {
		var self=this;
		var curNormalRange = "";
		var age=order.header.age;
		var curAge = age.split("-");
		var curYear=0, curMonth=0; curDay=0;
		years = parseFloat(curAge[0],10)||0;
		curYear = years * 360;
		curMonth = parseFloat(curAge[1],10)||0;
		curMonth = curMonth * 30;
		curDay = parseFloat(curAge[2],10)||0;
		curDay = curDay + curMonth + curYear;
		curYear = curDay/360;
		age = curYear;
		var state=false;
		if(order.header.gender=='Male'){
			if(testResult.normalRange){
				$(testResult.normalRange).each(function(index, resultGenderLevel){
					if(resultGenderLevel.sex=='male'){
						$(resultGenderLevel.values).each(function(index1, resultAgeLevel){
							var curMinAge = self.getAgeValue(resultAgeLevel.minAge);
							var curMaxAge = self.getAgeValue(resultAgeLevel.maxAge);
							if(age=="")
								return;
								
							if(curMinAge<=age && curMaxAge>=age){
								if(resultAgeLevel.minValue!=null && resultAgeLevel.maxValue!=null){
									curNormalRange=resultAgeLevel.minValue + " - " + resultAgeLevel.maxValue + " " + testResult.testUnit;
								} else if(resultAgeLevel.minValue==null){
									curNormalRange="< " + resultAgeLevel.maxValue + " " + testResult.testUnit; 
								} else if(resultAgeLevel.maxValue==null){
									curNormalRange="> " + resultAgeLevel.minValue + " " + testResult.testUnit; 
								}
								state=true;
								return false;
							} else if(curMinAge==null && curMaxAge>=age){
								if(resultAgeLevel.minValue!=null && resultAgeLevel.maxValue!=null){
									curNormalRange=resultAgeLevel.minValue + " - " + resultAgeLevel.maxValue + " " + testResult.testUnit;
								} else if(resultAgeLevel.minValue==null){
									curNormalRange="< " + resultAgeLevel.maxValue + " " + testResult.testUnit; 
								} else if(resultAgeLevel.maxValue==null){
									curNormalRange="> " + resultAgeLevel.minValue + " " + testResult.testUnit; 
								}
								state=true;
								return false;
							} else if(curMinAge<age && resultAgeLevel.maxAge==null){
								if(resultAgeLevel.minValue!=null && resultAgeLevel.maxValue!=null){
									curNormalRange=resultAgeLevel.minValue + " - " + resultAgeLevel.maxValue + " " + testResult.testUnit;
								} else if(resultAgeLevel.minValue==null){
									curNormalRange="< " + resultAgeLevel.maxValue + " " + testResult.testUnit; 
								} else if(resultAgeLevel.maxValue==null){
									curNormalRange="> " + resultAgeLevel.minValue + " " + testResult.testUnit; 
								}
								state=true;
								return false;
							}															
						});
						if(state==true)
						return false;
					}
				});
			}
		} else {
			if(testResult.normalRange){
				$(testResult.normalRange).each(function(index, resultGenderLevel){
					if(resultGenderLevel.sex=='female'){
						$(resultGenderLevel.values).each(function(index1, resultAgeLevel){
							var curMinAge = self.getAgeValue(resultAgeLevel.minAge);
							var curMaxAge = self.getAgeValue(resultAgeLevel.maxAge);
							if(age=="")
								return;
								
							if(curMinAge<=age && curMaxAge>=age){
								if(resultAgeLevel.minValue!=null && resultAgeLevel.maxValue!=null){
									curNormalRange=resultAgeLevel.minValue + " - " + resultAgeLevel.maxValue + " " + testResult.testUnit;
								} else if(resultAgeLevel.minValue==null){
									curNormalRange="< " + resultAgeLevel.maxValue + " " + testResult.testUnit; 
								} else if(resultAgeLevel.maxValue==null){
									curNormalRange="> " + resultAgeLevel.minValue + " " + testResult.testUnit; 
								}
								state=true;
								return false;
							} else if(curMinAge==null && curMaxAge>=age){
								if(resultAgeLevel.minValue!=null && resultAgeLevel.maxValue!=null){
									curNormalRange=resultAgeLevel.minValue + " - " + resultAgeLevel.maxValue + " " + testResult.testUnit;
								} else if(resultAgeLevel.minValue==null){
									curNormalRange="< " + resultAgeLevel.maxValue + " " + testResult.testUnit; 
								} else if(resultAgeLevel.maxValue==null){
									curNormalRange="> " + resultAgeLevel.minValue + " " + testResult.testUnit; 
								}
								state=true;
								return false;
							} else if(curMinAge<age && resultAgeLevel.maxAge==null){
								if(resultAgeLevel.minValue!=null && resultAgeLevel.maxValue!=null){
									curNormalRange=resultAgeLevel.minValue + " - " + resultAgeLevel.maxValue + " " + testResult.testUnit;
								} else if(resultAgeLevel.minValue==null){
									curNormalRange="< " + resultAgeLevel.maxValue + " " + testResult.testUnit; 
								} else if(resultAgeLevel.maxValue==null){
									curNormalRange="> " + resultAgeLevel.minValue + " " + testResult.testUnit; 
								}
								state=true;
								return false;
							}															
						});
						if(state==true)
						return false;
					}
				});
			}
		}
		return curNormalRange;
	}
}
function Constants() {
	this.SELECTONEORDER = "Select atleast one order";
	this.SELECTONEORDERONLY = "Select only one order";
	this.VIEWORDERPAGEURL = "/youNeverWait/json/netlims/orderTests.json";
	this.DELIVERED="Delivered";
	this.LAYOUTGENERAL="General";
	this.LAYOUTGENERALONE="GeneralOne";
	this.LAYOUTGENERALMED="GeneralMed";
	this.LAYOUT1="Layout1";
	this.LAYOUTSEMEN = "Semen Layout";
	this.LAYOUTDC ="DC";
	this.LAYOUTCULTURE ="Culture Report";
	this.LAYOUTWATERCULTURE="WaterCultureReport";
	this.LAYOUTHAEMOGRAM = "Haemogram";
	this.LAYOUTHAEMOGRAMESR ="HaemogramESR";
	this.LAYOUTAMINOACIDOGRAM="Aminoacidogram";
	this.LAYOUTOSMOTIC="Osmotic";
	this.LAYOUTSTOOLANALYSIS="Stool Analysis";
	this.LAYOUTENA="ENA";
	this.LAYOUTSTONEANALYSIS ="Stone Analysis" ;
	this.LAYOUTURINE = "Urine";
	this.LAYOUTAFB = "AFB";
	this.LAYOUTCD = "CD";
	this.LAYOUTGTT = "GTT";
	this.LAYOUTPERIPHERAL = "Peripheral";
	this.LAYOUTPT = "PT";
	this.LAYOUTAPPT = "APPT";
	this.LAYOUTHISTOPATH = "HistoPath";
	this.LAYOUTLIPID = "LipidLayout";
	this.LAYOUTLFT = "LFT";
	this.LAYOUTANA = "ANA";
	this.LAYOUTGCT="GCT";
	this.LAYOUTBETAHCG = "BetaHCGLayout";
	this.MODEPRINT = "print";
	this.SELECTEDROWCOLOR = "background-color:#DCDCDC;"
	this.USERURL = "/youNeverWait/netlims/ui/lab/user/";
	this.HOME = "home";
	this.HOMEPAGETOOLBAR = "/youNeverWait/json/netlims/homePageBar.json";
	this.ORDER = "order";
	this.ORDERFILTERKEY = "";
	this.ORDERLISTURL = "/youNeverWait/json/netlims/orderFilter.json";
}
function VerifierDTO() {
	this.setUserId =function(userId) {
		this.userId=userId;
	}	
	this.setName = function(name) {
		this.name = name;
	}
	this.setDesignation = function(designation) {
		this.designation = designation;
	}
	
	this.getUserId = function() {
		return this.userId;
	}
	
	this.getName = function() {
		return this.name;
	}
	
	this.getDesignation = function() {
		return this.designation;
	}
	
}
function User(user) {
	this.setInfo = function() {
		ajaxProcessor.setUrl('/youNeverWait/netlims/auth/user');
		var user =ajaxProcessor.get();	
		$('#userName').html(user.name);
	}
	this.bindEvents = function() {
		$("#btnLogoutNetLims").die('click').live("click",function() {
			ajaxProcessor.setUrl('/youNeverWait/netlims/auth/logout');
			var response =ajaxProcessor.get(); 
			if(response==true)
				location.reload();
		})
	}
}
function OrderServiceImpl () {
	this.setTableValues = function(tableObj, orderResult) {
		$(tableObj).dataTable().fnClearTable();
		if(orderResult.list) {
			if(orderResult.list.length>0) {			
				$(orderResult.list).each(function(index, order) {
					var patient=order.patient;
					var createdOn=order.createdOn;
					var paymentStatus=order.orderStatus
					var branchId = order.branchId;
					if(order.headerData!=null) {
						var header = $.parseJSON(order.headerData).header;
						var ageheader = header.age.split("-");
						var age="";
						var age_year = "";
						var age_month = "";
						var age_day = "";
						if(ageheader[0]!="" && ageheader[0]!=undefined)
							age_year = ageheader[0] + "Y";
						if(ageheader[1]!="" && ageheader[1]!=undefined)
							age_month = ageheader[1] + "M";
						if(ageheader[2]!="" && ageheader[2]!=undefined)
							age_day = ageheader[2] + "D";
						age = age_year + age_month + age_day;
					var rowData=$(tableObj).dataTable().fnAddData([order.uid, header.patientName, age, createdOn,header.collectedAt,header.referral, order.branchName]);
					var row=$(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$(row).children("td:nth-child(9)").attr("class","column-2");
					$(row).attr('id',order.id + "_" + branchId );
					$(row).children("td:nth-child(1)").attr("class","orderIdCol Ustyle");
					}
				});
			}		
		}
	}
	this.getOrder = function(orderId){
		ajaxProcessor.setUrl("/youNeverWait/netlims/ui/order/"+orderId);
		order = ajaxProcessor.get();
		return order;
	}
	this.getOrderResult=function(orderId){
		ajaxProcessor.setUrl("/youNeverWait/netlims/ui/order/result/"+orderId);
		order = ajaxProcessor.get();
		return order;
	}
}

function Order() {
	this.pgTableName="#orders";
	this.listUrl = "/youNeverWait/netlims/ui/order/facility/getByFilter";
	this.pgTableContainer="#orderListTableCont";
	this.viewTestPage = "#viewTest";
	this.testTable= this.viewTestPage + " #testTable";
	this.pgTableRowClass =this.pgTableName + ' .orderIdCol';
	this.chkSelectTestClassName = "printTestSelection";
	this.chkSelectTest=  ".printTestSelection";
	this.chkSelectAllTest="#printTestSelectAll";
	this.pageTitle = $('#pageTitle');
	this.exp = new ExpressionListDTO();
	this.orderService= new OrderServiceImpl();
	this.orderTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.orderService,this.exp);
	this.ftbToolBar = "#order-filter-toolbar";
	this.filterActionButton = this.ftbToolBar + ' #btnGo';
	this.filter = '#filter';
	this.filterBench=$('#filterWorkBench');
	this.ftbContainer=$('#filterToolBar-Container');
	this.setOrder = function(orderId){
		this.order = this.orderService.getOrder(orderId);
	}
	this.getBranchId =function() {
		return this.branchId;
	}
	this.setBranchId = function(branchId) {
		this.branchId = branchId;
	}
	this.getOrderTableNavigator=function(){
		return this.orderTableNavigator;
	}
	this.getOrder = function() {
		return this.order;
	}
	this.getTestList = function() {
		return this.testList;
	}
	this.setPageTitle = function(value) {
		this.pageTitle.empty().html(value);
	}
	this.setTestList = function(testList) {
		this.testList=testList;
	}
	this.list = function() {
		this.orderTableNavigator.list();
		this.setPageTitle("Orders");
	}
	this.init = function() {
		var ftbProcessor = new FilterToolBarProcessor();
		ftbProcessor.create(constants.ORDER,constants.ORDERFILTERKEY,constants.ORDERLISTURL);
		dataTableProcessor.create(this.pgTableName,"/youNeverWait/json/netlims/orderTable.json",'#tabs-1');//Create Table for Listing Order
		dataTableProcessor.setCustomTable(this.pgTableName);
		$('#pageToolBar-Container').empty().html();
		this.list();
		this.listEvents();
	}
	this.view = function(order_Branch_Id) {
		orderbranchId = order_Branch_Id.split('_');
		orderId = orderbranchId[0];
		branchId = orderbranchId[1];
		this.setOrder(orderId);
		this.setBranchId(branchId);
		ajaxProcessor.setUrl("/youNeverWait/netlims/ui/order/getTests/"+orderId);
		var tests = ajaxProcessor.get();
		this.setTestList(tests);
		pageHandler.create(constants.VIEWORDERPAGEURL);
		commonMethodInvoker.setViewTable(this.testTable);
		var ptbProcessor = new PageToolBarProcessor();
		ptbProcessor.create(constants.HOME, constants.HOMEPAGETOOLBAR);
		this.setTests(tests);
		this.viewEvents();
	}
	this.generateSelectedTestList = function(selectedList){
		var resultList = [];
		$(this.getTestList()).each(function(index, test){
			if(commonMethodInvoker.isIndexExists(selectedList,test.uid)==true)
				resultList.push($.parseJSON(test.result));
		});
		return resultList;
	}
	this.setTests = function(tests) {
		self=this;
		var checkbox = '<input type="checkbox" class="' + this.chkSelectTestClassName + '"/>';
		$(tests).each(function(){
			var testTable=$(self.testTable).dataTable().fnAddData([this.uid, this.testName,checkbox]);
			var row=$(tableObj).dataTable().fnSettings().aoData[testTable].nTr;
			$(row).attr('id',this.uid);
			
		});
	}
	this.getOrderHeader = function() {
		return this.orderHeader;
	}
	this.getSetting = function() {
		return this.setting;
	}
	this.setResultPrintObj=function(resultPrintObj){
		this.resultPrintObj=resultPrintObj;
	}
	this.getResultPrintObj=function(){
		return this.resultPrintObj;
	}
	
	this.setOrderHeader_Setting = function(header_setting){
		this.orderHeader=$.parseJSON(header_setting).header;
		this.setting=$.parseJSON(header_setting).settings;
		methodInvoker.setSetting(this.setting);
	}
	this.getPageSettingByKey = function(key){
		var result = null; 
		var self=this;
		var result = null; 
		$(self.setting).each(function(index, data){
			if(data.key==key){
				result = data;
				return false;
			}
		});
		return result;
	}
	this.listEvents = function() {
		var self=this;
		$(this.pgTableRowClass).die('click').live('click',function(){
		   var rowId= $(this).parent().attr('id');
		  self.view(rowId);
		  self.setPageTitle("Order : " + $(this).text());
		});
		/*Page Tool Bar Events ends here*/
		/*Filter Tool Bar Events starts here*/
		$(self.ftbToolBar + " a:not(:selected)").die('click').live('click',function() {
			var curObjName = $(this).attr('name');
			$(this).attr('selected','selected');
			$(this).addClass('button_filter');
			$('#lst'+curObjName).show();
			$('#txt'+curObjName).show();
			$('#txt'+curObjName).focus();
		})
		$(self.ftbToolBar + " a[selected]").die('click').live('click',function() {
			var curObjName = $(this).attr('name');
			$(this).removeAttr('selected');
			$(this).removeClass('button_filter');
			$('#lst'+curObjName).hide();
			$('#txt'+curObjName).hide();
			$(self.filterActionButton).trigger('click');
		})
		$(self.filterActionButton).die('click').click(function(){
			var expList=new ExpressionListDTO();
			$(self.ftbToolBar + " a[selected]").each(function(){
				var selName=$(this).attr('name');
				var selTextValue=$("#txt"+ selName).val();
				var selOperator = 'like';
				if(selName=='referralName')
					selTextValue = 'Dr. ' + selTextValue;
				var expr = new ExpressionDTO(selName,selTextValue,selOperator);
				expList.add(expr);
			});
			var tableNavigator =self.getOrderTableNavigator();
			tableNavigator.setExp(expList);
			self.list();
		});
		$(self.ftbToolBar + ' input[type="text"]').die('keypress').live("keypress",function(e){
			if(e.keyCode==13)
				$(self.filterActionButton).trigger('click');
		});
	}
	this.viewEvents = function() {
		var self=this;
		$(self.filter).hide();
		var order = self.getOrder();
		self.setOrderHeader_Setting(order.headerData);
		$(self.chkSelectAllTest).die('click').live('click',function(e) {
			var obj=$(this);
			if(obj.attr('checked'))
				$(self.chkSelectTest).each(function(){
					$(this).closest('tr').attr('selected','selected');
					$(this).closest('tr').attr('style',constants.SELECTEDROWCOLOR);
					$(this).attr('checked',true);
				});
			else
				$(self.chkSelectTest).each(function(){
					$(this).removeAttr('checked');
					$(this).closest('tr').removeAttr('selected');
					$(this).closest('tr').removeAttr('style');
				});
		});
		$(self.testTable + ' tbody tr').die('click').live('click',function(){
			var obj = $(this);
			var objId=$(this).attr('id');		
			if($(this).attr('selected')) {
				$(this).removeAttr('selected');
				$(this).removeAttr('style');
			} else {	
				$(this).attr('selected','selected');
				//$(this).attr('style',constants.SELECTEDROWCOLOR);
			}
			if(obj.attr('selected')) {
				$(this).children('td').children(self.chkSelectTest).attr('checked',true);
				if($(self.testTable + " tbody tr td input[class='" + self.chkSelectTestClassName + "']:checked").length == $(self.testTable).dataTable().fnGetData().length)
					$(self.chkSelectAllTest).attr('checked',true);
			} else {
				$(this).children('td').children(self.chkSelectTest).removeAttr('checked');
				$(self.chkSelectAllTest).removeAttr('checked');
			}
		});
		$('#homePTBContainer #btn_home_ptb_id').die('click').live('click',function() {
			self.init();	
		});
		$("#btnPrint").die('click').live('click',function(e) {		
			var printStatus=true;
			var obj = $(this);
			/*Select Tests to Print*/
			var testArray = []; //To store the selected tests
			$(self.testTable + ' tbody tr td input[class="printTestSelection"]:checked').each(function(){
				testArray.push($(this).closest('tr').attr('id'));
			});
			var resultDataGenerator = new ResultDataGenerator();
			var orderHeader=self.getOrderHeader();
			var response = self.generateSelectedTestList(testArray);
			if(response.length<=0)
				return false;
			//var responseResult = $.parseJSON(response.result);
			/*var verifiedTestsLength = 0;
			if(response.testResult!=null)
				var verifiedTestsLength = response.testResult.length;
			if(verifiedTestsLength!=0 && testArray.length==0) 
				return false;
			if(verifiedTestsLength==0 || testArray.length==0)
				return false;*/
			status="";
			if(status==constants.DELIVERED) {
				var printResultArray = resultDataGenerator.generatedDeliveredResult(self, testArray, response);
				var resultEntryParent = $('<div class="parent"/>');
				var resultEntryForm=$('<div class="inner-data"/>');
				var resultProcessor = new ResultPreviewProcessor(self);
				$(printResultArray).each(function(index, result) {
					var previewResult = resultProcessor.preview("", $.parseJSON(result), orderHeader.uid, order,printStatus);
					resultEntryForm.append(previewResult);
				});	
				resultEntryParent.html(resultEntryForm);
				resultEntryAction = $('<div class="box" style="width:98%; margin-left:10px; padding:0px 0px 5px 0px"/>'); // create div for store the save button
				var savebutton = $('<input type="button" class="stdbtn" value="Print" id="btnPrintResult"/>');
				resultEntryAction.html(savebutton);	
				resultEntryParent.append(resultEntryAction);
				createResultModal(resultEntryParent,"finalresultModal","","215mm");
				$('.noBorder').parent().addClass('noBorder');
				e.preventDefault();
				openModalBox(obj,"finalresultModal");
			} else {
				var resultJson;
				var resultPrintObj = new ResultPrintResponseDTO();
				result = resultDataGenerator.generateResultData(self, testArray, response);	
				resultJson = $.parseJSON(result.getPrintResult());
				resultPrintObj.setOrderUid(result.getOrderUid());
				resultPrintObj.setResult(result.getResult());
				resultPrintObj.setReportDate(result.getReportDate());
				resultPrintObj.setReportTime(result.getReportTime());
				resultPrintObj.setTestList(result.getTestList());
				resultPrintObj.setPrinted(true);
				self.setResultPrintObj(resultPrintObj);
				var resultEntryParent = $('<div class="parent"/>');
				var resultEntryForm=$('<div class="inner-data"/>');
				var resultProcessor = new ResultPreviewProcessor(self);
				var previewResult = resultProcessor.preview("",resultJson,orderHeader.uid, order,printStatus);
				resultEntryForm.html(previewResult);
				resultEntryParent.html(resultEntryForm);
				resultEntryAction = $('<div class="box" style="width:98%; margin-left:10px; padding:0px 0px 5px 0px"/>'); // create div for store the save button
				var printbutton = $('<input type="button" class="stdbtn" value="Print" id="btnPrintResult"/>');
				resultEntryAction.html(printbutton);	
				resultEntryParent.append(resultEntryAction);
				createResultModal(resultEntryParent,"finalresultModal","","204mm");
				$('.noBorder').parent().addClass('noBorder');
				e.preventDefault();
				openModalBox(obj,"finalresultModal");
			}
		});
		$('#btnPrintResult').die('click').live('click',function(e) {
			e.preventDefault();
			commonMethodInvoker.removeErrors();
			var resultPrintObj = self.getResultPrintObj();
			var buffer=$('<div/>');
			var pageNos = $('#finalresultModal .pageRoot').length;
			var count=1;
			var layoutStatus = methodInvoker.getPageSettingByKey('general_layout');
			newLayouts=layoutStatus.visible;
			if(newLayouts==true) {		
				/*
					new Changes
				*/
				var resultHeight = $(".printresultTable").height() +  $(".remarks").height();
				var heightmm = commonMethodInvoker.getmmFromPixel(resultHeight, methodInvoker.getDPI()).toFixed();	
				var contentset =methodInvoker.getPageSettingByKey('content');
				var headerset =methodInvoker.getPageSettingByKey('header');
				var originalHeight = parseInt(contentset.height,10) || 0;
				var marginHeight = parseInt(contentset.marginTop,10) || 0;
				var totalHeight = originalHeight-marginHeight;
				var len = 0;
				var pagecount=1;
				var page = $('<div class="page">&nbsp;</div>');
				var header = $('.pageRoot .resultHeader');
				var footer = $('.pageRoot .printReportFooter'); 
				var content = $('<div class="pageResultContent" />');
				content.css("height",totalHeight + "mm");
				content.css("width",contentset.width+"mm");
				content.css("margin-top",contentset.marginTop+"mm");
				content.css("font-size",contentset.fontSize + "px");
				page.height("297mm");
				//page.append(header);
				content.css("margin-left",headerset.marginLeft + "mm");
				footer.css("margin-left",headerset.marginLeft + "mm");
				$('.pageRoot .one').each(function() {
					pixelvalue = $(this).height();
					content.append($(this));
					len=len + commonMethodInvoker.getmmFromPixel(pixelvalue, methodInvoker.getDPI());
					if(len>=totalHeight){
						pagecount+=1;
						len=0;
						page.append(header.clone());
						page.append(content);
						page.append(footer.clone());
						buffer.append(page.clone());
						page.empty();
						content.empty();
						var pageDiv = $('<div style="height:2mm;">&nbsp;</div>');
						buffer.append(pageDiv);
					}
				});
				if(len!=0) {
					page.append(header.clone());
					page.append(content);
					page.append(footer.clone());
					buffer.append(page);
				}
			} else {
				$('#finalresultModal .pageRoot').each(function() {
					var tbl = $(this);
					tbl.height("297mm");
					if(count>1) {
						var pageDiv = $('<div style="height:2mm;">&nbsp;</div>');
						buffer.append(pageDiv);
					}	
					buffer.append(tbl.html());
					count++;
				});
			}
			var printHandler = new ResultPrintHandler();
			var resultHTML = printHandler.generateHtmlString(buffer.html());
			printHandler.print(resultHTML);
			$('#finalresultModal').trigger('reveal:close',"veryfast");
		});
	}
}
//Return the selected Order Id from the list table
Order.prototype.getSelectedOrderId = function () {
	var self =this;
	var orderId="";
	if($(this.pgTableName).dataTable().fnGetData().length>0) {
		var selOrders = $(self.pgTableName + ' tbody tr[selected]');
		if(selOrders.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEORDER);
		} else if(selOrders.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEORDERONLY);
		else
			orderId=selOrders.attr('id');
	}
	return orderId;
}
Order.prototype.getSelectedOrderIds = function () {
	var self =this;
	var orderIds=[];
	if($(this.pgTableName).dataTable().fnGetData().length>0) {
		var selOrders = $(self.pgTableName + ' tbody tr[selected]');
		if(selOrders.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEORDER);
		}else {
			$(selOrders).each(function() {
				orderIds.push($(this).attr('id'));
			});
		}
	}
	return orderIds;
}
function TestSpecimenDTO(testUid, specimenUid) {
	this.testUid = testId;
	this.specimenUid = specimenUid;
}
function ResultDataGenerator() {
	this.arrangeByDeparment=function(testResult){
		var testResult = [];
		var departmentList = [];
		/*find departments*/
		$(testResult).each(function(index, result) {
			var currentResult = $.parseJSON(result.result);
			departmentName="";
			if(currentResult.departmentName)
				departmentName=currentResult.departmentName;
			if(!commonMethodInvoker.isIndexExists(departmentList, departmentName))
				departmentList.push(departmentName);
		});
		$(departmentList).each(function(index, department) {
			$(testResult).each(function(index, result) {
				var currentResult = $.parseJSON(result.result);
				departmentName="";
				if(currentResult.departmentName)
					departmentName=currentResult.departmentName;
				if(commonMethodInvoker.isEqual(departmentName, department))
					testResult.push(result);
			});
		});
		return testResult;
	}
	this.createFinalJson = function(orderReference , testResult, reportDate, reportTime) {
		var finalJson = new FinalJSONDTO();
		var testList = [];
		var resultHeader = orderReference.getOrderHeader();
		finalJson.setResultHeader(resultHeader);	
		$(testResult).each(function(index, result) {
			var testDetails = new TestListJsonDTO();
			//testDetails.setTestName(methodInvoker.getTestNameFromConfig(result.testUid));
			var specimens=[];
			/*if(result.specimenUid)
				//specimens.push(methodInvoker.getSpecimenNameFromConfig(result.specimenUid));
			else 
				//specimens = methodInvoker.getSpecimenNamesFromTestConfig(result.testUid);*/
			testDetails.setSpecimens(specimens);
			if(result.reportingDate)
				testDetails.setReportingDate(result.reportingDate);
			else
				testDetails.setReportingDate(reportDate);
			if(result.reportingTime)
				testDetails.setReportingTime(result.reportingTime);
			else
				testDetails.setReportingTime(reportTime);
			testDetails.setResult(result);
			testList.push(testDetails);
		});
		finalJson.setTestList(testList);
		return finalJson;
	}

	this.generateResultData =function(orderReference ,testArray, testResult) {
		var self = this;
		var orderHeader = orderReference.getOrderHeader();
		var resultViewResponse = new ResultViewResponseDTO();//response to return	
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		var curdate = new Date();
		var reportDate = commonMethodInvoker.getUserDateFromSystemDate(curdate);
		var reportTime = commonMethodInvoker.getUserTimeFromSystemDate(curdate);
		var finaljson = self.createFinalJson(orderReference, testResult, reportDate, reportTime);
		resultViewResponse.setResult(finaljson);
		var verifiedTestIdsList = [];
		var testSpecimenList = [];
		/*$(testResult).each(function(index, result) {
			testId = result.testUid;
			var tstId=testId;
			if(result.specimenUid)
				tstId = testId + "_" + result.specimenUid;
			verifiedTestIdsList.push(tstId);		
			if(commonMethodInvoker.isIndexExists(testArray, tstId)==false) 
				return;	
			var testSpecimenDTO = new TestSpecimenDTO(testId,result.specimenUid);
			testSpecimenList.push(testSpecimenDTO);	
		});*/
		var resultToPrint = this.generatePrintJson(orderReference, testArray, testResult,reportDate,reportTime);
		resultViewResponse.setPrintResult(resultToPrint);
		resultViewResponse.setTestList(testSpecimenList);
		resultViewResponse.setReportDate(reportDate);
		resultViewResponse.setReportTime(reportTime);
		resultViewResponse.setSuccess(true);	
		resultViewResponse.setPrinted(true);
		resultViewResponse.setOrderUid(orderHeader.uid);
		return resultViewResponse;
	}
	this.generatedDeliveredResult = function(orderReference, testArray, testResult) {
		var orderHeader = orderReference.getOrderHeader();
		var self=this;
		var resultHtml;
		var pageResult = [];
		var selectedTests = [];
		var finalResult = [];
		var orderResultResponseCopy = testResult;
		var status=true;
		$(orderResultResponseCopy).each(function(index, result) {
			var testId=result.testUid;
			if(result.specimenUid)
				testId = testId + "_" + result.specimenUid;
			if(!commonMethodInvoker.isIndexExists(testArray, testId))
				return;
			var reportingDate = result.reportingDate;
			var reportingTime = result.reportingTime;
			$(testResult).each(function(index1, result1) {
				testId=result1.testUid;
				if(result1.specimenUid)
					testId = testId + "_" + result1.specimenUid;
				if(!commonMethodInvoker.isIndexExists(testArray, testId))
					return;
				if(result1.reportingDate==reportingDate && result1.reportingTime==reportingTime) {
					if(!commonMethodInvoker.isIndexExists(selectedTests, testId)) {
						pageResult.push(result1);
						selectedTests.push(testId);
					}	
				}
			});
			if(pageResult.length>0)
				finalResult.push(pageResult);
			pageResult=[];
		});
		var verifiedUserProvider = new VerifiedUserProvider(); //For getting verified users
		//var resultSpecimenProcessor = new ResultSpecimenProcessor(); //specimen process handler	
		var resultPrintArray = [];
		var contentset =methodInvoker.getPageSettingByKey('content');
		var originalHeight = parseInt(contentset.height,10) || 0;
		var marginHeight = parseInt(contentset.marginTop,10) || 0;
		var totalHeight = originalHeight-marginHeight;
		$(finalResult).each(function(parentIndex,singleResult) {
			var resultArray = []; //storing tests info
			var resultTestPackageArray=[]; //For storing tests which should be in separate pages and testpackages
			var resultSet =[]; //
			var resultHeight=0;//for calculating the height of tests to include in one page
			var curReportDate;
			var curReportTime;
			$(singleResult).each(function (index,result ) {
				curReportDate = result.reportingDate;
				curReportTime = result.reportingTime;
				var testId = result.testUid;
				var tstId=testId;
				if(result.specimenUid)
					tstId = testId + "_" + result.specimenUid;
			//	var testPkgStatus = isTestPackage(testId);			
				var resultVar=result;					
				if(resultVar.testLayout=="General" || resultVar.testLayout=="GeneralOne" || resultVar.testLayout=="DC" || resultVar.testLayout=='GTT' || resultVar.testLayout=='Urine' || resultVar.testLayout=='LipidLayout' || resultVar.testLayout=='PT' || resultVar.testLayout==constants.LAYOUTGENERALMED
					|| resultVar.testLayout=="WidalLayoutMed" || resultVar.testLayout=="UrineLayoutMed" || resultVar.testLayout=="LipidLayoutMed" || resultVar.testLayout=="DCLayoutMed") {
					if(resultVar.layoutHeight)
						resultHeight+=resultVar.layoutHeight;	
					var heightmm = commonMethodInvoker.getmmFromPixel(resultHeight, methodInvoker.getDPI());			
					if(heightmm>totalHeight) {
					var specimens="";
						//var specimens = resultSpecimenProcessor.getSpecimenList('{"testResult":[' + resultArray + ']}',orderHeader.header.uid);
						var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(resultArray) + '}',"");
						resultSet.push('{"specimens":"' + specimens + '","testResult":' + JSON.stringify(resultArray) + ',"verifiedUsers":'+JSON.stringify(verifiedUserIds) + ',"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
						resultArray=[];
						resultHeight=resultVar.layoutHeight+25;
					}	
					resultArray.push(result.result);
				} else
					resultTestPackageArray.push(result.result);
			});	
			if(resultArray.length>0){
				var specimens="";
			//	var specimens = resultSpecimenProcessor.getSpecimenList('{"testResult":[' + resultArray + ']}',orderInfo.header.uid);
				var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(resultArray) + '}',"");
				resultSet.push('{"specimens":"' + specimens + '","testResult":' + JSON.stringify(resultArray) + ',"verifiedUsers":['+verifiedUserIds + '],"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
			}	
			$(resultTestPackageArray).each(function (index, testResult) {
				var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(testResult) + '}',"");
				resultSet.push('{"testResult":' + JSON.stringify(testResult) + ',"verifiedUsers":['+verifiedUserIds + '],"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
			});
			var resultHeader = orderReference.getOrderHeader();
			resultToPrint = '{"resultHeader":' + JSON.stringify(resultHeader) + ',"resultList":[' + resultSet + ']}';
			resultPrintArray.push(resultToPrint);
		});
		
		return resultPrintArray;
	}	
}
function VerifiedUserProvider() {
	this.getVerifiedUsers = function(result,orderId) {
		var userArray = [];
		$($.parseJSON(result).testResult).each(function (index,testInfo) {
			if(testInfo.userId) {
				var userId=testInfo.userId;
				if(!commonMethodInvoker.isIndexExists(userArray,userId))
					userArray.push(userId);
			}
		});
		var userList=[];
		$(userArray).each(function (index,id) {
			$($.parseJSON(result).testResult).each(function (index1,testInfo) {
				if(id==testInfo.userId){
					var user = new VerifierDTO();
					user.setUserId(id);
					user.setName(testInfo.userName);
					user.setDesignation(testInfo.userDesignation);
					userList.push(user);
					user=null;
					return false;
				}	
			});
		});
		return userList;
	}
	this.getTestPackageTitleArea = function() {
		var testpkgTitleDiv=$('<div class="box-content column-right">');
		var pTag = $('<p>');
		pTag.append($('<br/>'));
		var spanTag = $('<span />');
		var checkboxBtn=$('<input type="checkbox" name="title" value="title" id="testpkgTitle">');
		spanTag.append(checkboxBtn);
		pTag.append(spanTag);
		spanTag = $('<span />');
		spanTag.append(constants_testPkgTitle);
		pTag.append(spanTag);
		testpkgTitleDiv.append(pTag);
		return testpkgTitleDiv;
	}
}	
ResultDataGenerator.prototype.generatePrintJson = function(orderReference,selectedTestArray, testResult,reportDate,reportTime) {
	var self=this;
	var curReportDate = reportDate;
	var curReportTime = reportTime;
	var resultHeight=0;//for calculating the height of tests to include in one page
	var verifiedUserProvider = new VerifiedUserProvider(); //For getting verified users
	var resultSpecimenProcessor = new ResultSpecimenProcessor(); //specimen process handler	
	var resultArray = []; //storing tests info
	var resultTestPackageArray=[]; //For storing tests which should be in separate pages and testpackages
	var resultSet =[]; //

	/*new changes*/
	//orderResultResponse.testResult = self.arrangeByDeparment(orderResultResponse);

	/******/
	var contentset =methodInvoker.getPageSettingByKey('content');
	var originalHeight = parseInt(contentset.height,10) || 0;
	var marginHeight = parseInt(contentset.marginTop,10) || 0;
	var totalHeight = originalHeight-marginHeight;
	//$(	orderResultResponse.testResult).each(function(index, result) {
	$(testResult).each(function(index, result) {
		/*var testId = result.testUid;
		var tstId=testId;
		if(result.specimenUid)
			tstId = testId + "_" + result.specimenUid;
		if(commonMethodInvoker.isIndexExists(selectedTestArray,tstId)==false)
			return;
		var testPkgStatus = isTestPackage(testId);		*/	
		var resultVar=result;			
		var layoutStatus = methodInvoker.getPageSettingByKey('general_layout');
		newLayouts=layoutStatus.visible;
		if(newLayouts==false){				
		if(resultVar.testLayout=="General" || resultVar.testLayout=="GeneralOne" || resultVar.testLayout=="DC" || resultVar.testLayout=='GTT' || resultVar.testLayout=='Urine' || resultVar.testLayout=='LipidLayout' || resultVar.testLayout=='PT' 
			|| resultVar.testLayout==constants.LAYOUTGENERALMED || resultVar.testLayout=="WidalLayoutMed" || resultVar.testLayout=="UrineLayoutMed" || resultVar.testLayout=="LipidLayoutMed" || resultVar.testLayout=="DCLayoutMed") {
			if(resultVar.layoutHeight)
				resultHeight+=resultVar.layoutHeight;	
			var heightmm = commonMethodInvoker.getmmFromPixel(resultHeight,methodInvoker.getDPI());		
			if(heightmm>totalHeight) {
				//var specimens="";
				var specimens = resultSpecimenProcessor.getSpecimenList('{"testResult":' + JSON.stringify(resultArray)  + '}',"");
				var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(resultArray) + '}',"");
				resultSet.push('{"specimens":"' + specimens + '","testResult":' + JSON.stringify(resultArray) + ',"verifiedUsers":'+JSON.stringify(verifiedUserIds) + ',"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
				resultArray=[];
				resultHeight=resultVar.layoutHeight+25;
			}	
			resultArray.push(result);
		} else
			resultTestPackageArray.push(result);
		}else {
			resultArray.push(result);
		}
	});
	if(resultArray.length>0){
		var specimens="";
		var specimens = resultSpecimenProcessor.getSpecimenList('{"testResult":' + JSON.stringify(resultArray) + '}',"");
		var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(resultArray) + '}',"");
		resultSet.push('{"specimens":"' + specimens + '","testResult":' + JSON.stringify(resultArray) + ',"verifiedUsers":'+JSON.stringify(verifiedUserIds) + ',"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
	}	
	$(resultTestPackageArray).each(function (index, testResult) {
		var specimens="";
		var specimens = resultSpecimenProcessor.getSpecimenList('{"testResult":' + JSON.stringify(testResult) + '}',"");
		var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(testResult) + '}',"");
		resultSet.push('{"specimens":"' + specimens + '","testResult":[' + JSON.stringify(testResult) + '],"verifiedUsers":'+JSON.stringify(verifiedUserIds) + ',"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
	});
	var resultHeader = orderReference.getOrderHeader();
	resultToPrint = '{"resultHeader":' + JSON.stringify(resultHeader) + ',"resultList":[' + resultSet + ']}';
	return resultToPrint;
}
function ResultViewResponseDTO() {
	this.getOrderUid = function() {
		return this.orderUid;
	}
	this.setOrderUid = function(orderUid) {
		this.orderUid=orderUid;
	}
	this.getResult = function() {
		return this.result;
	}
	this.setResult = function(result) {
		this.result=result;
	}
	this.getPrintResult = function() {
		return this.printResult;
	}
	this.setPrintResult = function(printResult) {
		this.printResult=printResult;
	}
	this.getReportDate = function() {
		return this.reportDate;
	}
	this.setReportDate = function(reportDate) {
		this.reportDate=reportDate;
	}
	this.getReportTime = function() {
		return this.reportTime;
	}
	this.setReportTime = function(reportTime) {
		this.reportTime=reportTime;
	}
	this.isPrinted = function() {
		return this.printed;
	}
	this.setPrinted = function(printed) {
		this.printed=printed;
	}
	this.getTestList= function() {
		return this.testList;
	}
	this.setTestList= function(testList) {
		this.testList=testList;
	}
	this.getError= function() {
		return this.error;
	}
	this.setError= function(error) {
		this.error=error;
	}
	this.getSuccess= function() {
		return this.success;
	}
	this.setSuccess= function(success) {
		this.success=success;
	}
}
function FinalJSONDTO() {
	this.setResultHeader= function(resultHeader) {
		this.resultHeader= resultHeader;
	}
	this.getResultHeader= function() {
		return this.resultHeader;
	}
	this.setTestList= function(testList) {
		this.testList= testList;
	}
	this.getTestList= function() {
		return this.testList;
	}
}
function AFBLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var table=$('<table width="100%"/>');
		var thead=$('<thead></thead>');
		var theadRow=$('<tr/>');
		var tdTag=$('<td colspan=2 align="center";/>');
		var breakTag=$('<br/>');
		if(mode=="print") {
			var lblTag = $('<label />');
			lblTag.html(result.testTitle);
			tdTag.append(lblTag);
			tdTag.append(breakTag);
		}else {
			var header=$(' <input type="text" class="Title" style="width:100%;font-weight:bold;"  id="AFB_Title" value="'+result.testTitle+'"/>');
			tdTag.append(header);
			tdTag.append(breakTag);
		}
		theadRow.append(tdTag);
		thead.append(theadRow);
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each(function(index,data){
			var row=$('<tr/>');
			var rowNew=$('<tr><td colspan=2>&nbsp</td></tr>');
			var columnOne=$('<td style="width:25%" align="left"></td>');
			var columnTwo=$('<td style="width:75%" align="left"></td>');
			spanTagOne=$('<span />');
			spanTagTwo=$('<span />');
			breakTag=$('<br/>');
			if(mode=="print") {
			if(data.fieldval1.trim()!=""){  
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval1);
				spanTagOne.append(lblTag);
			}	
			if(data.fieldval2.trim()!=""){
				var lblTag = $('<label/>');
				lblTag.html(":"+data.fieldval2);
				spanTagTwo.append(lblTag);
			}
			} else {
				var inputTag=$('<input type="text" style="width:100%" id="AFB_' + index +'_1" />');
				inputTag.attr('value',data.fieldval1);
				if(data.classNameOne)
					inputTag.attr('class',data.classNameOne);
				spanTagOne.append(inputTag);
				var inputTagTwo=$('<input type="text" style="width:100%" id="AFB_' + index +'_2" />');
				inputTagTwo.attr('value',data.fieldval2);
				if(data.classNameTwo)
					inputTagTwo.attr('class',data.classNameTwo);
				spanTagTwo.append(inputTagTwo);
				}
			columnOne.append(spanTagOne);	
			columnTwo.append(spanTagTwo);
			row.append(columnOne);
			row.append(columnTwo);
			if(data.fieldval1.trim()!=""){
			  tbody.append(rowNew);
			  }
			tbody.append(row);	
		});
		table.append(tbody);
		return table;
	};
}
function aminoacidLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<div/>');
		//div for report title starts here
		var fstDiv = $('<div align="center" class="resultTitle">'+result.testName+'</div>');
		content.append(fstDiv);
	    var table=$('<table id="realTable" style="width:90%"/>');
		var thead=$('<thead/>');
		var headrow=$('<tr>');
		$(result.Resultheaders).each(function(index,headervalue){
		  var headcol=$('<th>'+headervalue.headername+'</th>');
		  headrow.append(headcol);
		});
		thead.append(headrow);
      	table.append(thead);
		var tbody=$('<tbody/>');
		$(result.Resultbody).each(function(index,resultbody){
		var newRow=$('<tr/>');
		var newColumns=$('<td>'+resultbody.headerOneval+'</td>');
		newRow.append(newColumns);
		newColumns=$('<td>');
		if(mode=="print"){
			var lblTag = $('<label/>');
			lblTag.html(resultbody.value);
			newColumns.addClass('middle');
			newColumns.html(lblTag);
		}
		else{
			var inputTag=$('<select type="text" class="Amino tiny" id="Aminoacid' + index +'_1" tabIndex="'+(index+1)+'" value="' + resultbody.value + '"/>');
			newColumns.append(inputTag);
		}	
			newRow.append(newColumns);
			tbody.append(newRow);
		});
		
		table.append(tbody);
		content.append(table);
		//div for footer title starts here
		var footerDiv = $('<div style="float:left;;margin-left:2%;margin-right:2%; width:100%;"/>');
		var count = 0;
		if(result.footer) {
			$(result.footer.values).each(function (footerIndex, footerdata) {
				if(count%3==0 && count!=0)
					footerDiv.append($('<br/>'));
				var spanTag =$('<div style="margin:0px 10px 0 10px; float:left; min-width:30%" />');	
				if(footerdata.name.trim()=="") 
					return;					
				if(mode=="print") {
					labelTag = $('<label>'+footerdata.value+'</label>');
					spanTag.html(labelTag);
				} else{
					inputTag=$('<input type="text" style="width:100%" class="large"/>');
					inputTag.attr('id',footerdata.name);
					inputTag.attr('value',footerdata.value);
					spanTag.html(inputTag);
				}
				footerDiv.append(spanTag);			
				count++;
			});
		}		
		content.append(footerDiv);
		return content;
		return content;
		
		};


}
function anaIfa(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div/>');
		var titleSection =$('<div class="resultTitle middle">'+result.testName + '</div>');
		content.append(titleSection);
		var table=$('<div width="100%" id="anaIfa" />');
		$(result.values).each (function(index,paramData){
			if(mode=='print') {
					var container = $('<div class="one"/>');
					var contentTag = $('<div class="one_first_column normal_height columnleftalign" />');
					contentTag.append(paramData.name);
					container.append(contentTag);

					contentTag = $('<div class="one_second_column normal_height columnleftalign"/>');
					contentTag.append(' : ');
					container.append(contentTag);
					
					container.append(contentTag);
					contentTag = $('<div class="one_third_column normal_height columnleftalign"/>');
					if(paramData.value.trim()=="")
						contentTag.append('&nbsp;');
					else
						contentTag.append(paramData.value);
					container.append(contentTag);
					table.append(container);
			} else {
				var container = $('<div class="one"/>');
				var contentTag = $('<div class="one_first_column normal_height columnleftalign"/>');
				contentTag.append(paramData.name);
				container.append(contentTag);
				contentTag = $('<div class="one_second_column normal_height columnleftalign"/>');
				contentTag.append(' : ');
				container.append(contentTag);
				contentTag = $('<div class="one_third_column normal_height columnleftalign"/>');
				elementTag = $('<input type="text" class="observed" />');
				if(paramData.className)
					elementTag.attr('class',paramData.className);
				if(paramData.value)
					elementTag.attr('value',paramData.value);
				$(elementTag).attr('id',paramData.id);
				contentTag.append(elementTag);
				container.append(contentTag);
				table.append(container);
			}		
		});
		var container = $('<div class="one column_height">&nbsp;</div>');
		table.append(container);
		var container = $('<div class="one middle column_height">INTERPRETATION GUIDELINES (Dilution- 1 : 100)</div>');
		table.append(container);
		var container = $('<div class="one"/>');
		var contentTag = $('<div class="one_first_column normal_height columnleftalign" />');
		contentTag.append('Negative');
		container.append(contentTag);
		contentTag = $('<div class="one_second_column normal_height columnleftalign"/>');
		contentTag.append(' : ');
		container.append(contentTag);			
		contentTag = $('<div class="one_third_column normal_height columnleftalign"/>');
		contentTag.append('No Immunoflourescence');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one"/>');
		var contentTag = $('<div class="one_first_column normal_height columnleftalign" />');
		contentTag.append('+');
		container.append(contentTag);
		contentTag = $('<div class="one_second_column normal_height columnleftalign"/>');
		contentTag.append(' : ');
		container.append(contentTag);
		contentTag = $('<div class="one_third_column normal_height columnleftalign"/>');
		contentTag.append('Weak Positive(1 : 100)');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one"/>');
		var contentTag = $('<div class="one_first_column normal_height columnleftalign" />');
		contentTag.append('++');
		container.append(contentTag);
		contentTag = $('<div class="one_second_column normal_height columnleftalign"/>');
		contentTag.append(' : ');
		container.append(contentTag);				
		container.append(contentTag);
		contentTag = $('<div class="one_third_column normal_height columnleftalign"/>');
		contentTag.append('Moderate Positive( 1 : 320)');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one"/>');
		var contentTag = $('<div class="one_first_column normal_height columnleftalign" />');
		contentTag.append('+++');
		container.append(contentTag);
		contentTag = $('<div class="one_second_column normal_height columnleftalign"/>');
		contentTag.append(' : ');
		container.append(contentTag);				
		container.append(contentTag);
		contentTag = $('<div class="one_third_column normal_height columnleftalign"/>');
		contentTag.append('Moderate Positive( 1 : 320)');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column normal_height columnleftalign" />');
		contentTag.append('++++');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column normal_height columnleftalign" />');
		contentTag.append(' : ');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column normal_height columnleftalign" />');
		contentTag.append('Very Strong Positive(1 :3200');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one middle column_height">TEST DESCRIPTION</div>');
		table.append(container);
		var container = $('<div class="one normal_height">Antinuclear antibodies (ANAs) are unusual antibodies, Detectable in the blood, that have the capacity of binding to certain structues within the nucleus of the cells. ANAs indicate the possible presence of autoimmunity and provide, therefore, an indication of autoimmune illness. Fluorescence techniques are frequently used to detect the anibodies in the cells. Thus ANA testing is sometimes referred to as fluorescent antinuclear antibody test (FANA). The ANA test is a sensitive screening test used to detect autoimmune diseases.</div>');
		table.append(container);
		content.append(table);
		return content;
	};	

}
function ANALayout(honorific,result,testId,mode) {
	this.result =	function(){ 
	var content =$('<div align="center" style="font-weight:bold">'+result.testName+'</div>');
		var table=$('<table id="SpHgtTable" />');
		var thead=$('<thead><th>ANTIGEN</th><th>CLASS</th><th>RESULTS</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			var column=$('<td></td>');
			if(mode=="print"){
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval1);
				column.html(lblTag);
			}
			else{
				var inputTag=$('<input type="text" id="ana_' + index +'_1" class="large" value="' + data.fieldval1 + '"/>');
				if(data.className1)
					inputTag.attr('class',data.className1);	
				column.append(inputTag);
			}
			row.append(column);
			if(mode=="print") {
				if(data.fieldval1.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
			column=$('<td></td>');
			if(mode=="print"){
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval2);
				column.addClass('middle');
				column.html(lblTag);
			}
			else {
				var inputTag=$('<input type="text" id="ana_' + index +'_2" class="large" value="' + data.fieldval2 + '"/>');
				if(data.className2)
					inputTag.attr('class',data.className2);
				column.append(inputTag);
			}
			row.append(column);
				if(mode=="print") {
				if(data.fieldval2.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
			column=$('<td></td>');
		    if(mode=="print"){
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval3);
				column.addClass('middle');
				column.html(lblTag);
			}
			else{
				var inputTag=$('<input type="text" id="ana_' + index +'_3" class="large" value="' + data.fieldval3 + '"/>');
				if(data.className3)
					inputTag.attr('class',data.className3);
				column.append(inputTag);
			}
			row.append(column);
			if(mode=="print") {
				if(data.fieldval3.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
		  });
		  table.append(tbody);
		  content.append(table);
		  return content;
		};
          

}
function APPTLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var tbody=$('<tbody/>');
		var trow = $('<tr><td style="font-size:100%;font-weight:bold;">INVESTIGATION</td><td style="font-size:100%;font-weight:bold;">OBSERVED VALUE</td><td style="font-size:100%;font-weight:bold;">REFERENCE RANGE&UNIT</td></tr>');
		tbody.append(trow);
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			if(data.FieldOneName){
				if(data.FieldOneName!=""){
					var column=$('<td style="width:39%;padding-left:5px;">'+data.FieldOneName+'</td>');
					row.append(column);
				}
			}
			if(index==1){
		        var column=$('<td>&nbsp;</td>');
				 row.append(column);
			}
			var column=$('<td style="width:28%;padding-left:5px;"/>');
			var spanTag=$('<div style="width:32%;float:left;">'+data.FieldTwoName+'</div>');
			column.append(spanTag);
			spanTag=$('<div style="width:5%;float:left;">:</div>');
		    column.append(spanTag);
			spanTag=$('<div style="width:32%;float:left;"/>');
		   if(mode=="print") {
				var lblTag = $('<label />');
				lblTag.html(data.FieldVal +" "+data.unitVal );
				spanTag.append(lblTag);
			} else{
				var inputTag=$('<input type="text"  id="APPT_' + index +'_1" class="large" value="' + data.FieldVal + '"/>');
				spanTag.append(inputTag);
			}
		    column.append(spanTag);
			spanTag=$('<div style="width:20%;float:left;"></div>');
			if(data.time){
				 if(mode=="print") {
					var lblTag = $('<label />');
					lblTag.html(data.UnitType);
					spanTag.append(lblTag);
				} else{
					var inputTag=$('<input type="text" class="'+data.time+'" value="'+data.unitVal+'" id="APPT_'+index+'_2"/>');
					spanTag.append(inputTag);
					if(data.UnitType)
						spanTag.html(data.UnitType);
				}	
			}			
		   	column.append(spanTag);
		   	row.append(column);
		  	if(data.FieldThreeName){
			   	if(data.FieldThreeName!=""){
					var column=$('<td style="width:33%;padding-left:5px;">'+data.FieldThreeName+'</td>');
					row.append(column);
			   } 
		    } 
			if(index==1){
				var column=$('<td>&nbsp;</td>');
				row.append(column);
			}
		  	tbody.append(row);
		});
		return tbody;
	};
}
function CDLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div align="center" style="font-weight:bold">'+result.testName+'</div>');
		var table=$('<table width="100%" id="SpHgtTable" />');
		var thead=$('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th><th>REFERENCE RANGE&UNIT</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			var column=$('<td></td>');
			if(mode=="print"){
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval1);
				column.html(lblTag);
			}
			else {
				var inputTag=$('<input type="text" style="width:100%" id="CD_' + index +'_1" class="large" value="' + data.fieldval1 + '"/>');
				if(data.className1)
					inputTag.attr('class',data.className1);
				column.append(inputTag);
			}
			row.append(column);
			if(mode=="print") {
				if(data.fieldval1.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
			column=$('<td></td>');
			if(mode=="print"){
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval2);
				column.addClass('middle');
				column.html(lblTag);
			}
			else {
				var inputTag=$('<input type="text" style="width:100%" id="CD_' + index +'_2" class="large" value="' + data.fieldval2 + '"/>');
				if(data.className2)
					inputTag.attr('class',data.className2);
				column.append(inputTag);
			}
			row.append(column);
			if(mode=="print") {
				if(data.fieldval2.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
			column=$('<td></td>');
			if(mode=="print"){
				var lblTag = $('<label/>');
				column.addClass('middle');
				lblTag.html(data.fieldval3);
				column.html(lblTag);
			}
			else {
				var inputTag=$('<input type="text" style="width:100%" id="CD_' + index +'_3" class="large" value="' + data.fieldval3 + '"/>');
			    if(data.className3)
					inputTag.attr('class',data.className3);
			    column.append(inputTag);
			}
			row.append(column);
			if(mode=="print") {
				if(data.fieldval3.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
		  });
		table.append(tbody);
		content.append(table);
		return content;
		};
          
}
function cultureLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<tbody class="noBorder"/>');
		var trRootTag = $('<tr/>');
		var tdRootTag = $('<td />');
		//div for report title starts here
		var headerTable = $('<table width="100%" class="cultureTable_Head">');
		
		var theadParentTag = $('<thead>');
		var trParentTag = $('<tr/>');
		var thParentTag = $('<th colspan="4">');
		thParentTag.html(result.analysis.resultTitle);
		trParentTag.html(thParentTag);
		theadParentTag.append(trParentTag);
		headerTable.append(theadParentTag);
		//div for report title ends here
		
		var tbodyParentTag = $('<tbody>');
		var trParentTag = $('<tr/>');
		var tdParentTag = $('<td colspan="2">');
		var labelTag = $('<label/>');
		labelTag.html(result.analysis.organismGrownLabel);
		tdParentTag.append(labelTag);
		if(mode=='print') {
			var lblTag = $('<label/>');
			if(result.analysis.organismGrownValue)
				lblTag.html(result.analysis.organismGrownValue);
			tdParentTag.append(lblTag);
		} else {
			var inputPTag=$('<input type="text" class="cultureList large" id="organismValue" style="width:86%"/>');
			if(result.analysis.organismGrownValue)
				inputPTag.attr('value',result.analysis.organismGrownValue);
			tdParentTag.append(inputPTag);
		}		
		trParentTag.html(tdParentTag);	
		tbodyParentTag.append(trParentTag);
		
		//Second Row
		trParentTag = $('<tr/>');
		tdParentTag = $('<td style="width:50%"/>');
		spanPTag = $('<span>1). </span>');
		tdParentTag.append(spanPTag);
		spanPTag = $('<span/>');
		if(mode=='print') {
			var lblTag = $('<label/>');
			if(result.analysis.columnValue1)
				lblTag.html(result.analysis.columnValue1+result.analysis.columnValue1Sub);
			spanPTag.html(lblTag);
			tdParentTag.append(spanPTag);
		} else {
			inputPTag=$('<input type="text" class="antibiotics" id="columnValue1" style="width:70%"/>');
			 if(result.analysis.columnValue1)
				inputPTag.attr('value',result.analysis.columnValue1); 
			spanPTag.append(inputPTag);
			var inputPTag2=$('<select class="raise2"  align="right;" id="columnValue1Sub" style="width:20%"/>');
			 if(result.analysis.columnValue1Sub)
				inputPTag2.attr('value',result.analysis.columnValue1Sub);
				spanPTag.append(inputPTag2);
			   tdParentTag.append(spanPTag);
		}	
		trParentTag.append(tdParentTag);	
		
		tdParentTag = $('<td style="width:50%"/>');
		spanPTag = $('<span>2). </span>');
		tdParentTag.append(spanPTag);
		spanPTag = $('<span/>');
		if(mode=='print') {
			var lblTag = $('<label/>');
			if(result.analysis.columnValue2)
				lblTag.html(result.analysis.columnValue2+result.analysis.columnValue2Sub); 
			spanPTag.html(lblTag);
			tdParentTag.append(spanPTag);
		} else {
			inputPTag=$('<input type="text" class="antibiotics " id="columnValue2" style="width:70%"/>');
			 if(result.analysis.columnValue2)
				inputPTag.attr('value',result.analysis.columnValue2);
			spanPTag.append(inputPTag);
			var inputPTag2=$('<select class="raise2"  align="right;"id="columnValue2Sub" style="width:20%"/>');
			 if(result.analysis.columnValue2Sub)
				inputPTag2.attr('value',result.analysis.columnValue2Sub);
				spanPTag.append(inputPTag2);
			   tdParentTag.append(spanPTag);
		}	
		trParentTag.append(tdParentTag);
		tbodyParentTag.append(trParentTag);
		headerTable.append(tbodyParentTag);
		
		tdRootTag.append(headerTable);
		
		var tblDiv = $('<div/>');
		var resultTable = $('<table id="cultureresultTable" style="float:left; margin-left:40px; width:90%" />');
		
		var theadTag = $('<thead/>');
		var trTag=$('<tr />');	
		for(i=0;i<2;i++) {
			$(result.analysis.columns).each(function(index, column) {
				var thTag = $('<th />');
				thTag.append(column);
				trTag.append(thTag);
			});
		}	
		theadTag.append(trTag);	
		resultTable.append(theadTag);
		
		var tbodyTag = $('<tbody/>');
		
		var length=result.analysis.data.length;
		var trTag;
		var tabIndex=0;
		var tabIndex1=0; //
		var tabIndex2=0; //
		var tabIndex3=0;
		$(result.analysis.data).each(function(index,data) {
			if(index%2==0){
				trTag=$('<tr />');		
					tabIndex1++;
					tabIndex=tabIndex1;
			}else {
				tabIndex2=tabIndex1 + 20;
				tabIndex=tabIndex2;
			}
			var tdTag;
			if(mode=="print") 
				tdTag = $('<td style="width:34%"><label>'+data.name+'</label></td>');
			else
			tdTag = $('<td style="width:34%"><input type="text"  value="'+data.name+'" id="' + data.idProperty + '_0" tabIndex=' + tabIndex + '></td>');
			//tdTag.append(data.name);
			trTag.append(tdTag);
				
			tdTag = $('<td style="width:8%;text-align:center;"/>');
			if(mode=="print") 
				inputTag = $('<label class="middle">'+data.value1+'</label>');
			else{
				inputTag=$('<select class="number" tabIndex=' + (tabIndex+40)+ '/>');
				inputTag.attr('id',data.idProperty+"_1");
				inputTag.addClass('middle');
				inputTag.attr('value',data.value1);
			}
			tdTag.append(inputTag);
			trTag.append(tdTag);			
			tdTag = $('<td style="width:8%;text-align:center;"/>');
			if(mode=="print") 
				inputTag = $('<label class="middle">'+data.value2+'</label>');
			else{
				inputTag=$('<select class="number" tabIndex=' + ((tabIndex+40)*2) +'/>');
				inputTag.attr('id',data.idProperty+"_2");
				inputTag.addClass('middle');
				inputTag.attr('value',data.value2);
			}
			tdTag.append(inputTag);
			trTag.append(tdTag);
				
			if((index+1)%2==0 || length==(index+1))
				tbodyTag.append(trTag);
		});
		resultTable.append(tbodyTag);
		tblDiv.append(resultTable);		
		tdRootTag.append(tblDiv);			
		//div for foeter title starts here
		var footerDiv = $('<div style="float:left;;margin-left:2%;margin-right:2%; width:100%;"/>');
		var count = 0;
		if(result.analysis.footer) {
			$(result.analysis.footer.values).each(function (footerIndex, footerdata) {
				if(count%3==0 && count!=0)
					footerDiv.append($('<br/>'));
				var spanTag =$('<div style="margin:0px 10px 0 10px; float:left; min-width:30%" />');	
				if(footerdata.name.trim()=="") 
					return;					
				if(mode=="print") {
					labelTag = $('<label>'+footerdata.value+'</label>');
					spanTag.html(labelTag);
				} else{
					inputTag=$('<input type="text" style="width:100%" class="large"/>');
					inputTag.attr('id',footerdata.name);
					inputTag.attr('value',footerdata.value);
					spanTag.html(inputTag);
				}
				footerDiv.append(spanTag);			
				count++;
			});
		}		
		tdRootTag.append(footerDiv);
		//div for report title ends here
		trRootTag.append(tdRootTag);
		content.append(trRootTag);
		return content;
	};	
}
function DCLayout(result, testId, mode,honorific) {
	this.result =	function(){ 
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			var column=$('<td ></td>');
			if(mode=="print") {
				var lblTag = $('<label/>');
				lblTag.html(data.key);
				column.html(lblTag);
			} else {	
				var inputTag=$('<input type="text" id="DC_' + index +'_1"  value="' + data.key + '"/>');
				if(data.className1)
					inputTag.attr('class',data.className1);
				column.append(inputTag);
			}	
			row.append(column);
			if(mode=="print") {
				if(data.key.trim()!="")
					tbody.append(row);
			}	
			else
				tbody.append(row);			
				column=$('<td class="middle"></td>');
				if(mode=="print") {
					var lblTag = $('<label/>');
					lblTag.html(data.fieldvaltwo + " " + data.unit);
					column.html(lblTag);
				} else {	
					var inputTag=$('<input type="text" class="observed" style="float:left;" id="DC_' + index +'_2" tabindex="' + (index +1)+'" value="' + data.fieldvaltwo + '"/>');
					var label=$('<label >' + data.unit + '</label>');					
					column.append(inputTag);
					column.append(label);
				}	
				row.append(column);		
				if(mode=="print") {
					if(data.key.trim()!="")
						tbody.append(row);
				}	
				else
					tbody.append(row);
				if(honorific!='Animal') {
					column=$('<td class="middle"></td>');
					if(mode=="print") {
						var lblTag = $('<label/>');
						lblTag.html(data.fieldvalthree+" " + data.unit);
						column.html(lblTag);
					} else {	
						var inputTag=$('<input type="text"  style="float:left;" id="DC_' + index +'_3"  value="' + data.fieldvalthree + '"/>');
						var label=$('<label >' + data.unit + '</label>');
						if(data.className3)
							inputTag.attr('class',data.className3);
						column.append(inputTag);
						column.append(label);
					}	
					row.append(column);
					if(mode=="print") {
						if(data.key.trim()!=""){
							tbody.append(row);
						}
					}	
					else
						tbody.append(row);	
				}		
		}); 
		return tbody;
	};
}
function DCLayoutHeader(honorific) {
	this.result =function(){
		var headTag='<thead><tr><th style="width:40%" class="head1" >INVESTIGATION</th><th class="head1" >OBSERVED VALUE</th>';
		if(honorific!='Animal')
			headTag+='<th class="head1" >REFERENCE RANGE&amp;UNIT</th>';
		headTag+='</tr></thead>';
		var theadTag =$(headTag);
		return theadTag;
	};	
}
function DCLayoutMed(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div/>');
		var titleSection =$('<div class="one singleheight bold underline">'+result.testName + '</div>');
		content.append(titleSection);
		var table=$('<div width="100%" id="dcTable" />');
		$(result.values).each (function(index,paramData){
			if(mode=='print') {
				if(paramData.value.trim()!="") {
					var container = $('<div class="one singleheight">&nbsp;</div>');
					var contentTag = $('<div class="onefirst">&nbsp;</div>');
					contentTag.empty().html(paramData.name);
					container.empty().html(contentTag);
					contentTag = $('<div class="onesecond">&nbsp;</div>');
					if(paramData.value!="")
						contentTag.empty().html(paramData.value);
					if(paramData.unit){
						var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
						spanTag.append(paramData.unit);
						contentTag.append(spanTag);	
					}
					container.append(contentTag);
					contentTag = $('<div class="onesecond">&nbsp;</div>');
					if(paramData.normal!="")
						contentTag.empty().html(paramData.normal);
					container.append(contentTag);
					table.append(container);
				}
			} else {
				var container = $('<div class="one singleheight">&nbsp;</div>');
				var contentTag = $('<div class="onefirst">&nbsp;</div>');
				contentTag.append(paramData.name);
				container.append(contentTag);
				contentTag = $('<div class="onesecond">&nbsp;</div>');
				elementTag = $('<input type="text" class="observed" />');
				if(paramData.className)
					elementTag.attr('class',paramData.className);
				if(paramData.value)
					elementTag.attr('value',paramData.value);
				$(elementTag).attr('id',paramData.id);
				contentTag.append(elementTag);
				if(paramData.unit)
					contentTag.append(paramData.unit);	
				container.append(contentTag);
				contentTag = $('<div class="onesecond">&nbsp;</div>');
				if(paramData.normal!="")
					contentTag.empty().html(paramData.normal);
				container.append(contentTag);
				table.append(container);
			}	
		});
		content.append(table);
		return content;
	};
}
function enaProfile(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<div/>');
		//div for report title starts here
		var fstDiv = $('<div  align="center" class="resultTitle">'+result.testName+'</div>');
		content.append(fstDiv);
		var SecDiv = $('<div class="five_sixth"/>');
	    var table=$('<table id="realTable"/>');
		var thead=$('<thead/>');
		var headrow=$('<tr>');
		$(result.Resultheaders).each(function(index,headervalue){
		  var headcol=$('<th>'+headervalue.headername+'</th>');
		  headrow.append(headcol);
		});
		thead.append(headrow);
      	table.append(thead);
		var tbody=$('<tbody/>');
		$(result.Resultbody).each(function(index,resultbody){
		var newRow=$('<tr/>');
		var newColumns=$('<td>'+resultbody.headerOneval+'</td><td> '+resultbody.headerTwoval+'</td>');
		newRow.append(newColumns);
		var newColumns=$('<td/>');
		var inputTag=$('<input type="text" id="Ena_' + index + '_1" value="'+resultbody.headerThreeval+'"/>');
		 if(mode=="print") {
						inputTag.addClass('readonlyStyle');
						inputTag.attr('readonly','readonly');
						inputTag.removeClass('middle');
						inputTag.addClass('column-2');
					}	
		newColumns.append(inputTag);
		newRow.append(newColumns);
		tbody.append(newRow);
		});	
		table.append(tbody);
		SecDiv.append(table);
		content.append(SecDiv);
		return content;	
		};
}
function foodAllergy(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div/>');
		var titleSection =$('<div class="resultTitle middle">'+result.testName + '</div>');
		content.append(titleSection);
		var table=$('<div width="100%" id="foodAllergy" />');
		var container = $('<div class="one smallheight" />');
		var contentTag = $('<div class="onethird foodallergy_onethird_border middle bold" />');
		contentTag.append('&nbsp;');
		container.append(contentTag);
		var contentTag = $('<div class="onethird foodallergy_onethird_border middle bold" />');
		contentTag.append('CONCENTRATION');
		container.append(contentTag);
		var contentTag = $('<div class="onethird foodallergy_onethird_border middle bold" />');
		contentTag.append('CLASS');
		container.append(contentTag);
		table.append(container);
		$(result.values).each (function(index,paramData){
			if(mode=='print') {
				if(paramData.value.trim()!="") {
					var container = $('<div class="one smallheight"/>');
					var contentTag = $('<div class="onethird foodallergy_onethird_border" />');
					contentTag.append(paramData.name);
					container.append(contentTag);
					contentTag = $('<div class="onethird foodallergy_onethird_border"/>');
					contentTag.addClass('middle');
					contentTag.append(paramData.value);
					if(paramData.unit){
						var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
						spanTag.append(paramData.unit);
						contentTag.append(spanTag);	
					}
					container.append(contentTag);
					contentTag = $('<div class="onethird foodallergy_onethird_border"/>');
					contentTag.addClass('middle');
					contentTag.append(paramData.classvalue);
					container.append(contentTag);
					table.append(container);
				}
			} else {
				var container = $('<div class="one"/>');
				var contentTag = $('<div class="onethird foodallergy_onethird_border"/>');
				contentTag.append(paramData.name);
				container.append(contentTag);
				contentTag = $('<div class="onethird foodallergy_onethird_border"/>');
				elementTag = $('<input type="text" class="observed" />');
				if(paramData.className)
					elementTag.attr('class',paramData.className);
				if(paramData.value)
					elementTag.attr('value',paramData.value);
				$(elementTag).attr('id',paramData.id);
				contentTag.append(elementTag);
				if(paramData.unit)
					contentTag.append(paramData.unit);	
				container.append(contentTag);
				contentTag = $('<div class="onethird foodallergy_onethird_border"/>');
				elementTag = $('<input type="text" class="observed" />');
				if(paramData.classvalue)
					elementTag.attr('value',paramData.classvalue);
				$(elementTag).attr('id',paramData.classid);
				contentTag.append(elementTag);
				container.append(contentTag);
				table.append(container);
			}	
		});
		var container = $('<div class="one smallheight">&nbsp;</div>');
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('Concentration [k/U/I]');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('Class');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('Explanation');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('< 0.35');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('0');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border columnleftalign" />');
		contentTag.append('No specific antibodies detected');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('0.35 - 0.7');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('1');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border columnleftalign" />');
		contentTag.append('Very weak antibody detection, often no clinical symptoms with an existing sensitization');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('0.7 - 3.5');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('2');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border columnleftalign" />');
		contentTag.append('Weak antibody detection, existing sensitization, often with clinical symptoms in upper levels');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('3.5 - 17.5');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('3');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border columnleftalign" />');
		contentTag.append('Definite antibody detection, clinical symptoms are often present');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('17.5 - 50');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('4');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border columnleftalign" />');
		contentTag.append('Strong antibody detection, almost always with existing symptoms');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('50 - 100');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('5');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border columnleftalign" />');
		contentTag.append('Very high antibody titer');
		container.append(contentTag);
		table.append(container);
		var container = $('<div class="one smallheight"/>');
		var contentTag = $('<div class="one_first_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('>100');
		container.append(contentTag);
		var contentTag = $('<div class="one_second_column foodallergy_onethird_border middle columnleftalign" />');
		contentTag.append('6');
		container.append(contentTag);
		var contentTag = $('<div class="one_third_column foodallergy_onethird_border columnleftalign" />');
		contentTag.append('Very high antibody titer');
		container.append(contentTag);
		table.append(container);
		content.append(table);
		return content;
	};	
}
function NVGCTLayout(result, testId, mode,honorific) {
	this.result =	function(){ 
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			var column=$('<td style="vertical-align:middle"></td>');
			if(mode=="print") {
				if(data.name=="")
					return;
				var lblTag = $('<label/>');
				lblTag.html(data.name);
				column.html(lblTag);
			} else {	
				var inputTag=$('<input type="text" id="GCT_' + index +'_1"  value="' + data.name + '"/>');
				if(data.className)
					inputTag.attr('class',data.className);
				column.append(inputTag);
			}	
			row.append(column);
			if(mode=="print") {
				if(data.name.trim()!="")
					tbody.append(row);
			}	
			else
				tbody.append(row);			
				column=$('<td class="middle"></td>');
				if(mode=="print") {
					var lblTag = $('<label/>');
					lblTag.html(data.current + " " + data.unit);
					column.html(lblTag);
				} else {	
					var inputTag=$('<input type="text" class="observed" style="float:left;" id="GCT_' + index +'_2" tabindex="' + (index +1)+'" value="' + data.current + '"/>');
					var label=$('<label >' + data.unit + '</label>');					
					column.append(inputTag);
					column.append(label);
				}	
				row.append(column);
				
				if(mode=="print") {
					if(data.name.trim()!="")
						tbody.append(row);
				}	
				else
					tbody.append(row);
				if(honorific!='Animal') {
					column=$('<td class="middle"></td>');
					if(mode=="print") {
						var lblTag = $('<label/>');
						lblTag.html(data.normal+" " + data.unit);
						column.html(lblTag);
					} else {	
						var inputTag=$('<input type="text"  style="float:left;" id="GCT_' + index +'_3"  value="' + data.normal + '"/>');
						var label=$('<label >' + data.unit + '</label>');
						if(data.className3)
							inputTag.attr('class',data.className3);
						column.append(inputTag);
						column.append(label);
					}	
					row.append(column);
					if(mode=="print") {
						if(data.name.trim()!=""){
							tbody.append(row);
						}
					}	
					else
						tbody.append(row);	
				}		
		}); 
		return tbody;
	};
}
function generalLayout(testResult, testId, mode,honorific,order) {
	this.result =	function(){ 
		if(testId==null || testId=="")
			testId=0;
		var response = $('<tbody/>');
		response.attr('name','tbody_'+testId);	
		var status=true;	
		if(honorific!='Animal') {
			$(testResult.analysis).each(function(analysisIndex,analysis) {
				$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
					$(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
						if(resultparam.key){
							if(status==true) {
								var trTag = $('<tr/>');
								var tdTag = $('<td style="vertical-align:middle"/>');
								tdTag.attr('colspan',resultparam.values.length+1);
								tdTag.html(testResult.testName);	
								trTag.append(tdTag);
								response.append(trTag);
								status=false;
							}						
						}
						var trTag = $('<tr/>');
						trTagId = testId + '_' + analysisIndex + "_" + resultcontentIndex + "_" + resultparamIndex;
						trTag.attr('id',trTagId);
						var tdTag= $('<td style="width:40%;vertical-align:middle"/>');
						if(resultparam.key)
							tdTag.html(resultparam.key);
						else {					
							tdTag.html(testResult.testName);
						}	
						trTag.append(tdTag);
						$(resultparam.values).each(function(paramvaluesIndex,paramValues) {
							$(paramValues.values).each(function(paramdataIndex,paramData) {
								var tdTag= $('<td style="vertical-align:middle" class="middle"/>');
								if(paramValues.headervalue=='NormalRange') 					
										tdTag.attr('style',"width:35%");
								var tblTag = $('<table width="100%"/>');
								if(paramData.values) {				
									$(paramData.values).each(function(paramdataValueIndex,paramDataValues){
										var newrow = $('<tr/>');
										if(paramDataValues.normalRangeAttribute) {	
											var colTag = $('<td style="vertical-align:middle;"  class="middle"/>');
											colTag.append(paramDataValues.normalRangeAttribute);	
											newrow.append(colTag);								
										}
										if(paramDataValues.values) {
											var colTag = $('<td class="middle"/>');
											var innerTblTag = $('<table width="100%"/>');
											$(paramDataValues.values).each(function(paramdataValueInnerIndex,paramDataValuesInner){
												var newrowInner = $('<tr/>');
												if(paramDataValuesInner.normalRangeAttribute) {	
													var colTag = $('<td class="middle"/>');
													colTag.append(paramDataValuesInner.normalRangeAttribute);	
													newrowInner.append(colTag);								
												}
												if(paramDataValuesInner.value) {
													var colTag = $('<td class="middle"/>');
													colTag.html(paramDataValuesInner.value);
													if(paramDataValuesInner.unit){
														var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
														spanTag.append(paramDataValuesInner.unit);
														colTag.append(spanTag);	
													}
													newrowInner.append(colTag);	
												}
												innerTblTag.append(newrowInner);
											});				
											colTag.append(innerTblTag);
											newrow.append(colTag);
										} else {
											if(paramDataValues.value) {
												var colTag = $('<td class="middle"/>');
												colTag.html(commonMethodInvoker.nl2br(paramDataValues.value));
												if(paramDataValues.unit){
													var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
													spanTag.append(paramDataValues.unit);
													colTag.append(spanTag);	
												}
												newrow.append(colTag);	
											}	
										}
										tblTag.append(newrow);
									});
								} else {
									var newrow = $('<tr/>');
									if(paramData.normalRangeAttribute) {	
										var colTag = $('<td />');
										colTag.append(paramData.normalRangeAttribute);	
										newrow.append(colTag);								
									}
									if(paramData.displayType) {	
										var colTag = $('<td />');
										if(paramData.headervalue=='NormalRange') {
											tdTag.attr('style',"max-width:38%;min-width:35%");
										}
										if(paramData.displayType=='text') {
											var elementTag;
											var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
											if(mode=='print') {
												colTag.addClass('middle');
												elementTag = $('<label />')
												if(paramData.value)
													elementTag.text(paramData.value);
											} else {
												elementTag = $('<input type="text" class="observed" style="float:left;"/>');
												if(paramData.className)
													elementTag.attr('class',paramData.className);
												if(paramData.value)
													elementTag.attr('value',paramData.value);
												colTag.attr('style',"padding:0px");	
											}	
											$(elementTag).attr('id',txtid);					
											colTag.append(elementTag);
											if(paramData.unit){
												var spanTag = $('<span style="padding:0px 0px 0px 5px;"/>');
												spanTag.append(paramData.unit);
												colTag.append(spanTag);	
											}
										}
										newrow.append(colTag);
									} else {	
										var curNormalRange = methodInvoker.getCurrentNormalRange($.parseJSON(order.headerData), testResult);
										if(curNormalRange!=""){
												var colTag = $('<td />');
												if(curNormalRange.length>60)
													colTag.addClass('leftalign');
												else
													colTag.addClass('middle');
												colTag.html(commonMethodInvoker.nl2br(curNormalRange));				
												newrow.append(colTag);
										} else{
											if(paramData.value) {			
												var colTag = $('<td />');
												if(paramData.value.length>60)
													colTag.addClass('leftalign');
												else
													colTag.addClass('middle');
												var normalValue = commonMethodInvoker.nl2br(paramData.value);
												normalValue  = normalValue.replaceAll(" ", "&nbsp;");
												colTag.html(normalValue);
												if(paramData.unit){
													var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
													spanTag.append(paramData.unit);
													colTag.append(spanTag);	
												}										
												newrow.append(colTag);
											}
										}
									}	
									tblTag.append(newrow);
								}														
								tdTag.append(tblTag);
								trTag.append(tdTag);							
							});
						});
						response.append(trTag);
					});
				});
			});
		} else {
			$(testResult.analysis).each(function(analysisIndex,analysis) {
				$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
					$(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
						var trTag = $('<tr/>');
						trTagId = testId + '_' + analysisIndex + "_" + resultcontentIndex + "_" + resultparamIndex;
						trTag.attr('id',trTagId);
						//Investigation Field
						var tdTag= $('<td style="width:40%;vertical-align:middle"/>');
						tdTag.html(testResult.testName);	
						trTag.append(tdTag);
						$(resultparam.values).each(function(paramvaluesIndex,paramValues) {
							$(paramValues.values).each(function(paramdataIndex,paramData) {
								var tdTag= $('<td style="vertical-align:middle" class="middle"/>');
								if(paramValues.headervalue!='NormalRange') { 					
									var tblTag = $('<table width="100%"/>');
									if(paramData.values) {				
										$(paramData.values).each(function(paramdataValueIndex,paramDataValues){
											//inside normal range
											var newrow = $('<tr/>');
											if(paramDataValues.normalRangeAttribute) {	
												var colTag = $('<td style="vertical-align:middle;"  class="middle"/>');
												colTag.append(paramDataValues.normalRangeAttribute);	
												newrow.append(colTag);								
											}
											if(paramDataValues.values) {
												var colTag = $('<td class="middle"/>');
												var innerTblTag = $('<table width="100%"/>');
												$(paramDataValues.values).each(function(paramdataValueInnerIndex,paramDataValuesInner){
													//inside normal range
													var newrowInner = $('<tr/>');
													if(paramDataValuesInner.normalRangeAttribute) {	
														var colTag = $('<td class="middle"/>');
														colTag.append(paramDataValuesInner.normalRangeAttribute);	
														newrowInner.append(colTag);								
													}
													if(paramDataValuesInner.value) {
														var colTag = $('<td class="middle"/>');
														colTag.html(paramDataValuesInner.value);
														if(paramDataValuesInner.unit){
															var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
															spanTag.append(paramDataValuesInner.unit);
															colTag.append(spanTag);	
														}
														newrowInner.append(colTag);	
													}
													innerTblTag.append(newrowInner);
												});				
												colTag.append(innerTblTag);
												newrow.append(colTag);
											} else {
												if(paramDataValues.value) {
													var colTag = $('<td class="middle"/>');
													colTag.html(commonMethodInvoker.nl2br(paramDataValues.value));
													if(paramDataValues.unit){
														var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
														spanTag.append(paramDataValues.unit);
														colTag.append(spanTag);	
													}
													newrow.append(colTag);	
												}	
											}
											tblTag.append(newrow);
										});
									} else {
										var newrow = $('<tr/>');
										if(paramData.normalRangeAttribute) {	
											var colTag = $('<td />');
											colTag.append(paramData.normalRangeAttribute);	
											newrow.append(colTag);								
										}
										if(paramData.displayType) {	
											var colTag = $('<td />');									
											if(paramData.displayType=='text') {
												var elementTag;
												var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
												if(mode=='print') {
													colTag.addClass('middle');
													elementTag = $('<label />')
													if(paramData.value)
														elementTag.text(paramData.value);
												} else {
													elementTag = $('<input type="text" class="observed" style="float:left;"/>');
													if(paramData.className)
														elementTag.attr('class',paramData.className);
													if(paramData.value)
														elementTag.attr('value',paramData.value);
													colTag.attr('style',"padding:0px");	
												}	
												$(elementTag).attr('id',txtid);					
												colTag.append(elementTag);
												if(paramData.unit){
													var spanTag = $('<span style="padding:0px 0px 0px 5px;"/>');
													spanTag.append(paramData.unit);
													colTag.append(spanTag);	
												}
											}
											newrow.append(colTag);
										} else {				
											if(paramData.value) {			
												var colTag = $('<td />');
												if(paramData.value.length>60)
													colTag.addClass('leftalign');
												else
													colTag.addClass('middle');
												var normalValue = commonMethodInvoker.nl2br(paramData.value);
												normalValue  = normalValue.replaceAll(" ", "&nbsp;");
												colTag.html(normalValue);
												if(paramData.unit){
													var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
													spanTag.append(paramData.unit);
													colTag.append(spanTag);	
												}										
												newrow.append(colTag);
											}
										}	
										tblTag.append(newrow);
									}														
									tdTag.append(tblTag);
									trTag.append(tdTag);	
								}		
							});
						});
						response.append(trTag);		
					});
				});
			});		
		}		
		return response;
	};
}
function generalLayoutHeader(result,honorific) {
	this.result =	function(){ 
		var theadTag = $('<thead/>');
		if(result.analysis){
			$(result.analysis).each(function(index,analysis) {
				$(analysis.resultContent).each(function(index1,rescont) {					
					var trTag=$('<tr/>');		
					$(rescont.resultParams).each(function(index2,resparam) {				
						var thTag = $('<th style="width:40%" class="head1">INVESTIGATION</th>');
						trTag.append(thTag);	
						$(resparam.values).each(function(index2,elemnt) {					
							if(honorific=="Animal" && elemnt.headervalue=="NormalRange") {
							}else{
								var thTag = $('<th class="head1" class="middle"/>');
								if(elemnt.headerLabel){	
									thTag.append(elemnt.headerLabel);
								}
								trTag.append(thTag);	
							}
						});
						theadTag.append(trTag);
						return false;
					});
				});	
			});
		}	
		return theadTag;
	};	
}
function generalLayoutMed(testResult, testId, mode, honorific, order) {
	this.result =	function(){ 
		if(testId==null || testId=="")
			testId=0;
		var response = $('<div/>');
		response.attr('name','tbody_'+testId);
		var status=true;
		$(testResult.analysis).each(function(analysisIndex,analysis) {
			$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
				$(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
					var trTag = $('<div class="one">&nbsp;</div>');
					trTagId = testId + '_' + analysisIndex + "_" + resultcontentIndex + "_" + resultparamIndex;
					trTag.attr('id',trTagId);
					var tdTag = $('<div class="onefirst">&nbsp;</div>');
					tdTag.empty().html(testResult.testName);	
					trTag.empty().html(tdTag);	
					$(resultparam.values).each(function(paramvaluesIndex,paramValues) {
						$(paramValues.values).each(function(paramdataIndex,paramData) {
							var tdTag= $('<div>&nbsp;</div>');
							if(paramData.displayType=='text') {
								var elementTag;
								var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
								if(mode=='print') {
									trTag.addClass('singleheight');
									elementTag = $('<label>&nbsp;</label>');
									if(paramData.value)
										elementTag.text(paramData.value);
								} else {
									elementTag = $('<input type="text" class="observed" />');
									if(paramData.className)
										elementTag.attr('class',paramData.className);
									if(paramData.value)
										elementTag.attr('value',paramData.value);
									$(elementTag).attr('id',txtid);
								}
								tdTag.append(elementTag);
								if(paramData.unit){
									var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
									spanTag.append(paramData.unit);
									tdTag.append(spanTag);	
								}
								tdTag.addClass('onesecond');
								trTag.append(tdTag);
							} else if(paramData.displayType=='textarea'){
								tdTag.addClass('onesecond');
								var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
								if(mode=='print'){
									if(paramData.value){
										var normalValue = commonMethodInvoker.nl2br(paramData.value);
										normalValue  = normalValue.replaceAll(" ", "&nbsp;");
										tdTag.html(normalValue);
									}
									trTag.append(tdTag);
								}else {
									var textarea = $('<textarea class="valueTextArea" />');
									textarea.attr('id',txtid);
									var curNormalRange = methodInvoker.getCurrentNormalRange(order, testResult);
									if(curNormalRange!="")
										textarea.html(commonMethodInvoker.br2nl(curNormalRange));
									else
										textarea.html(commonMethodInvoker.br2nl(paramData.value));
									tdTag.append(textarea);
									trTag.append(tdTag);
								}
							} else {
								if(paramData.value) {
									if(paramData.value.trim()!="")
									tdTag.html(paramData.value);
									if(paramData.unit){
										var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
										spanTag.append(paramData.unit);
										tdTag.append(spanTag);	
									}			
								} 
								tdTag.addClass('onesecond');
								trTag.append(tdTag);
							}	
						});
					});
					response.append(trTag);	
				});	
			});
		});		
		return response.html();
	};
}
function generalOneLayout(testResult,testId,mode,honorific) {
	this.result =	function(){
		var resultTBody = $('<tbody/>');
		resultTBody.attr('name','tbody_'+testId); // set the tbody id to test id		
		if(honorific!="Animal"){
			$(testResult.analysis).each(function(analysisIndex,analysis) {
			$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
			$(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
				var trTag = $('<tr />'); // Create the first row
				trTagId = testId + '_' + analysisIndex + "_" + resultcontentIndex + "_" + resultparamIndex;
				trTag.attr('id',trTagId); // set id for the row
				tdTag= $('<td style="width:40%;vertical-align:middle" />'); // First column for investigation
				tdTag.html(testResult.testName);
				//tdTag.attr('style','font-weight:bold;');
				trTag.append(tdTag); // Added Investigation Name to first column
				//Iterating through contents
				$(resultparam.values).each(function(paramvaluesIndex,paramValues) {
					//Creating the next columns like observed value, normal range etc...
					tdTag= $('<td class="middle" style="vertical-align:middle"/>');					
					if(paramValues.values) {
						$(paramValues.values).each(function(paramdataIndex,paramDataValues){
							if(paramValues.headervalue=='NormalRange') {
								tdTag.removeClass('middle');
								tdTag.attr('style',"width:35%");
							}
							if(mode=='print') {
								if(paramValues.headervalue!='NormalRange') {
									elementTag = $('<label />')
									if(paramDataValues.value)
										elementTag.text(commonMethodInvoker.nl2br(paramDataValues.value));
									tdTag.html(elementTag);
									if(paramDataValues.unit){
										var spanTag = $('<span style="padding:0px 0px 0px 5px"/>');
										spanTag.append(paramDataValues.unit);
										tdTag.append(spanTag);	
									}
								} else {
									var normalValue = commonMethodInvoker.nl2br(paramDataValues.value);
									normalValue  = normalValue.replaceAll(" ", "&nbsp;");
									tdTag.html(normalValue);
								}
							} else {
								var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
								if(paramValues.headervalue=='NormalRange') {
									var innerTblTag = $('<table width="100%"/>');
									var newrowInner = $('<tr/>');
									var columnTag = $('<td class="middle"/>');
									selectTag=$('<select class="select normalRange" style="width:100%;" >');
									columnTag.append(selectTag);
									newrowInner.append(columnTag);
									innerTblTag.append(newrowInner);
									newrowInner = $('<tr/>');
									columnTag = $('<td class="middle"/>');
									var textarea = $('<textarea class="normalVal" />');
									textarea.attr('id',txtid);
									textarea.html(commonMethodInvoker.br2nl(paramDataValues.value));
									columnTag.append(textarea);
									newrowInner.append(columnTag);
									innerTblTag.append(newrowInner);
									tdTag.html(innerTblTag);
								} else {
									var elementTag = $('<input type="text" style="float:left;" class="observed"/>');
									if(paramDataValues.className)
										elementTag.addClass(paramDataValues.className);
									elementTag.attr('id',txtid);
									elementTag.attr('value',paramDataValues.value);
									tdTag.html(elementTag);								
									if(paramDataValues.unit) {
										var lblTag = $('<label padding:0px 0px 0px 5px;"/>');
										lblTag.html(paramDataValues.unit);
										tdTag.append(lblTag);
									}									
								}
							}						
						});
					}
					trTag.append(tdTag);
				});
				resultTBody.append(trTag);
			});
			});
			});
		}
		else{
			$(testResult.analysis).each(function(analysisIndex,analysis) {
			$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
			$(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
				var trTag = $('<tr />'); // Create the first row
				trTagId = testId + '_' + analysisIndex + "_" + resultcontentIndex + "_" + resultparamIndex;
				trTag.attr('id',trTagId); // set id for the row
				tdTag= $('<td style="width:40%;vertical-align:middle" />'); // First column for investigation
				tdTag.html(testResult.testName);
				trTag.append(tdTag); // Added Investigation Name to first column
				//Iterating through contents
				$(resultparam.values).each(function(paramvaluesIndex,paramValues) {
					//Creating the next columns like observed value, normal range etc...
                    var tdTag;					
					if(paramValues.values) {
						$(paramValues.values).each(function(paramdataIndex,paramDataValues){
							if(mode=='print') {
								if(paramValues.headervalue!='NormalRange') {
								   tdTag= $('<td class="middle" style="vertical-align:middle"/>');
									elementTag = $('<label />')
									if(paramDataValues.value)
										elementTag.text(commonMethodInvoker.nl2br(paramDataValues.value));
									tdTag.html(elementTag);
									if(paramDataValues.unit){
										var spanTag = $('<span style="padding:0px 0px 0px 5px"/>');
										spanTag.append(paramDataValues.unit);
										tdTag.append(spanTag);	
									}
								} 
							} else {
								var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
								if(paramValues.headervalue!='NormalRange') {
								    tdTag= $('<td class="middle" style="vertical-align:middle"/>');
									var elementTag = $('<input type="text" style="float:left;" class="observed"/>');
									elementTag.attr('id',txtid);
									elementTag.attr('value',paramDataValues.value);
									tdTag.html(elementTag);								
									if(paramDataValues.unit) {
										var lblTag = $('<label padding:0px 0px 0px 5px;"/>');
										lblTag.html(paramDataValues.unit);
										tdTag.append(lblTag);
									}			
								
								} 
							}						
						});
					}
					trTag.append(tdTag);
				});
				resultTBody.append(trTag);
			});
			});
			});	 
		}	  
	return resultTBody; // return the result table body
	};
}
function GTTLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		if(mode=="print") {
			var content =$('<div align="center" />');
			var titleSection = $('<div align="center" style="font-size:18px; line-height:40px;">'+result.testName+'(<label style="margin-right:5px">'+result.testType+'</label>Oral Glucose)</div>');
			content.append(titleSection);
		} else
			var content =$('<div align="center" >'+result.testName+'(<input type="text" id="GTT_heading" class="heading micro" value="'+result.testType+'"/>Oral Glucose)</div>');
		var table=$('<table width="100%" id="realTable" />');
		var thead=$('<thead><th>INVESTIGATION</th><th>BLOOD SUGAR</th><th>URINE SUGAR</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			var column=$('<td />');
			if(mode=="print") {
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval1);
				column.html(lblTag);
			}
			else {
				var inputTag=$('<input type="text"  id="GTT_' + index +'_1" value="' + data.fieldval1 + '"/>');
				if(data.className1)
					inputTag.attr('class',data.className1 );
				column.append(inputTag);
			  }
			row.append(column);
			if(mode=="print") {
				if(data.fieldval1.trim()!=""){
					tbody.append(row);
				}
			}	
			else
				tbody.append(row); 
			column=$('<td>'  +data.BldUnit+'</td>');
			if(mode=="print") {
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval2+data.BldUnit);
				column.html(lblTag);
			}
			else{
				var inputTag=$('<input type="text"  id="GTT_' + index +'_2" class="medium" value="' + data.fieldval2 + '"/>');
				if(data.className2)
					inputTag.attr('class',data.className2 );	
				column.prepend(inputTag);
			  }
			row.append(column);
			if(mode=="print") {
				if(data.fieldval2.trim()!=""){
					tbody.append(row);
				}
			}	
			else
				tbody.append(row); 
			column=$('<td></td>');
			if(mode=="print") {
				var lblTag = $('<label/>');
				lblTag.html(data.fieldval3);
				column.html(lblTag);
			}
			else{
				var inputTag=$('<input type="text"  id="GTT_' + index +'_3" class="medium" value="' + data.fieldval3 + '"/>');
				if(data.className3)
					inputTag.attr('class',data.className3 );
				column.append(inputTag);
			}
			row.append(column);
			if(mode=="print") {
				if(data.fieldval3.trim()!=""){
					tbody.append(row);
				}
			}	
			else
				tbody.append(row); 
		});
		table.append(tbody);
		content.append(table);
		return content;
	};         
}
function haemogramLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<div/>');
		//div for report title starts here
		var fstDiv = $('<div align="center" class="resultTitle"> '+result.testName+'</div>');
		content.append(fstDiv);
		var SecDiv = $('<div class="five_sixth"/>');
		var table;
		if(honorific=="Animal"){
			table=$('<table id="realTable"/>');
			var mainrow=$('<thead><tr><th rowspan="2" ></th> <th colspan="4" align="center">PATIENT\'S VALUE  </th> </tr></thead>');
			table.append(mainrow);
			var tableheading=$('<thead ><tr><th style="width:29%">          </th><th style="width:19%">ABSOLUTE VALUE</th><th style="width:14%;">%VALUE</th></tr></thead>');
			table.append(tableheading);
			var tablebody=$('<tbody/>')
			$(result.values).each(function(index,tblVal){
				var row=$('<tr/>');
				var rowColumns=$('<td >'+tblVal.testnameval+'</td>');
				if(tblVal.testnameval=='ESR')
					rowColumns.attr('style','font-weight:bold;');
				row.append(rowColumns);
				if(mode=="print") {
					rowColumns=$('<td class="middle"/>');
					rowColumns.html(tblVal.absoluteValue + " " + tblVal.aVUnit);
				} else {	
					rowColumns=$('<td >'+tblVal.aVUnit+'</td>');
					var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_1" value="' + tblVal.absoluteValue + '"/>');
					rowColumns.prepend(inputTag);
				}
				row.append(rowColumns);
				if(tblVal.VUnit.trim()!=""){
					if(mode=="print") {
						rowColumns=$('<td class="middle"/>');
						rowColumns.html(tblVal.value  + " " + tblVal.VUnit);
					} else {
						rowColumns=$('<td>'+ tblVal.VUnit + '</td>');
						var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_2" value="' + tblVal.value + '"/>');
						rowColumns.prepend(inputTag);
					}
				 row.append(rowColumns);
				 }
				else
				rowColumns=$('<td></td>');
				row.append(rowColumns);
				tablebody.append(row);
			  });
			  table.append(tablebody);	 
		}
		else {
		 	table=$('<table id="realTable"/>');
			var mainrow=$('<thead><tr><th rowspan="2" ></th> <th colspan="4" align="center">PATIENT\'S VALUE  </th> </tr></thead>');
			table.append(mainrow);
			var tableheading=$('<thead ><tr><th style="width:29%">          </th><th style="width:19%">ABSOLUTE VALUE</th><th style="width:24%">NORMAL RANGE</th><th style="width:14%;">%VALUE</th><th style="width:14%">NORMAL RANGE</th></tr></thead>');
			table.append(tableheading);
			var tablebody=$('<tbody/>')
			$(result.values).each(function(index,tblVal){
				var row=$('<tr/>');
				var rowColumns=$('<td >'+tblVal.testnameval+'</td>');
				if(tblVal.testnameval=='ESR')
					rowColumns.attr('style','font-weight:bold;');
				row.append(rowColumns);
				if(mode=="print") {
					rowColumns=$('<td class="middle"/>');
					rowColumns.html(tblVal.absoluteValue + " " + tblVal.aVUnit);
				} else {	
					rowColumns=$('<td >'+tblVal.aVUnit+'</td>');
					var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_1" value="' + tblVal.absoluteValue + '"/>');
					rowColumns.prepend(inputTag);
				}
				row.append(rowColumns);
				var rowColumns= $('<td  class="middle">'+tblVal.normalRange+'</td>');
				row.append(rowColumns);
				
				if(tblVal.VUnit.trim()!=""){
					if(mode=="print") {
						rowColumns=$('<td class="middle"/>');
						rowColumns.html(tblVal.value  + " " + tblVal.VUnit);
					} else {
						rowColumns=$('<td>'+ tblVal.VUnit + '</td>');
						var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_2" value="' + tblVal.value + '"/>');
						rowColumns.prepend(inputTag);
					}
				 row.append(rowColumns);
				 }
				else
					rowColumns=$('<td></td>');
				row.append(rowColumns);
				rowColumns=$('<td >'+tblVal.NR+'</td>');
				row.append(rowColumns);
				tablebody.append(row);
			  });
			  table.append(tablebody);			  
	   } 
		SecDiv.append(table);
		content.append(SecDiv);
		return content;
	};			
}
function haemogramESRLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<div/>');
		//div for report title starts here
		var fstDiv = $('<div align="center" class="resultTitle"> '+result.testName+'</div>');
		content.append(fstDiv);
		var SecDiv = $('<div class="five_sixth"/>');
		var table;
		if(honorific=="Animal"){
			table=$('<table id="realTable"/>');
			var mainrow=$('<thead><tr><th rowspan="2" ></th> <th colspan="4" align="center">PATIENT\'S VALUE  </th> </tr></thead>');
			table.append(mainrow);
			var tableheading=$('<thead ><tr><th style="width:29%">          </th><th style="width:19%">ABSOLUTE VALUE</th><th style="width:14%;">%VALUE</th></tr></thead>');
			table.append(tableheading);
			var tablebody=$('<tbody/>')
			$(result.values).each(function(index,tblVal){
				var row=$('<tr/>');
				var rowColumns=$('<td >'+tblVal.testnameval+'</td>');
				if(tblVal.testnameval=='ESR')
					rowColumns.attr('style','font-weight:bold;');
				row.append(rowColumns);
				if(mode=="print") {
					rowColumns=$('<td class="middle"/>');
					rowColumns.html(tblVal.absoluteValue + " " + tblVal.aVUnit);
				} else {	
					rowColumns=$('<td >'+tblVal.aVUnit+'</td>');
					var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_1" value="' + tblVal.absoluteValue + '"/>');
					rowColumns.prepend(inputTag);
				}
				row.append(rowColumns);
				if(tblVal.VUnit.trim()!=""){
					if(mode=="print") {
						rowColumns=$('<td class="middle"/>');
						rowColumns.html(tblVal.value  + " " + tblVal.VUnit);
					} else {
						rowColumns=$('<td>'+ tblVal.VUnit + '</td>');
						var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_2" value="' + tblVal.value + '"/>');
						rowColumns.prepend(inputTag);
					}
				 row.append(rowColumns);
				 }
				else
				rowColumns=$('<td></td>');
				row.append(rowColumns);
				tablebody.append(row);
			  });
			  table.append(tablebody); 
		}
		else {
		 	table=$('<table id="realTable"/>');
			var mainrow=$('<thead><tr><th rowspan="2" ></th> <th colspan="4" align="center">PATIENT\'S VALUE  </th> </tr></thead>');
			table.append(mainrow);
			var tableheading=$('<thead ><tr><th style="width:29%">          </th><th style="width:19%">ABSOLUTE VALUE</th><th style="width:24%">NORMAL RANGE</th><th style="width:14%;">%VALUE</th><th style="width:14%">NORMAL RANGE</th></tr></thead>');
			table.append(tableheading);
			var tablebody=$('<tbody/>')
			$(result.values).each(function(index,tblVal){
				var row=$('<tr/>');
				var rowColumns=$('<td >'+tblVal.testnameval+'</td>');
				if(tblVal.testnameval=='ESR')
					rowColumns.attr('style','font-weight:bold;');
				row.append(rowColumns);
				if(mode=="print") {
					rowColumns=$('<td class="middle"/>');
					rowColumns.html(tblVal.absoluteValue + " " + tblVal.aVUnit);
				} else {	
					rowColumns=$('<td >'+tblVal.aVUnit+'</td>');
					var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_1" value="' + tblVal.absoluteValue + '"/>');
					rowColumns.prepend(inputTag);
				}
				row.append(rowColumns);
				var rowColumns= $('<td  class="middle">'+tblVal.normalRange+'</td>');
				row.append(rowColumns);		
				if(tblVal.VUnit.trim()!=""){
					if(mode=="print") {
						rowColumns=$('<td class="middle"/>');
						rowColumns.html(tblVal.value  + " " + tblVal.VUnit);
					} else {
						rowColumns=$('<td>'+ tblVal.VUnit + '</td>');
						var inputTag=$('<input type="text" class="medium" id="Haemogram_' + index +'_2" value="' + tblVal.value + '"/>');
						rowColumns.prepend(inputTag);
					}
				 row.append(rowColumns);
				 }
				else
					rowColumns=$('<td></td>');
				row.append(rowColumns);
				rowColumns=$('<td >'+tblVal.NR+'</td>');
				row.append(rowColumns);
				tablebody.append(row);
			  });
			  table.append(tablebody);			  
	   } 
		SecDiv.append(table);
		content.append(SecDiv);
		return content;
	};		
}
function haemogramLayoutMed(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div/>');
		var tableHead = $('<div width="100%" class="one singleheight bold" />');
		var contentTag = $('<div class="onesecond"/>');
		contentTag.append("Investigation");
		tableHead.append(contentTag);
		contentTag = $('<div class="onefive"/>');
		contentTag.append("Absolute Value");
		tableHead.append(contentTag);
		contentTag = $('<div class="onefive"/>');
		contentTag.append("Normal Range");
		tableHead.append(contentTag);
		contentTag = $('<div class="onefourth"/>');
		contentTag.append("% Value");
		tableHead.append(contentTag);
		contentTag = $('<div class="onefourth"/>');
		contentTag.append("Normal Range");
		tableHead.append(contentTag);
		content.append(tableHead);
		var table=$('<div width="100%" id="haemogramTable" />');
		$(result.values).each (function(index,paramData){
			if(mode=='print') {
				if(paramData.value.trim()!="") {
					var container = $('<div class="one singleheight"/>');
					//container.attr('min-height','25px');
					var contentTag = $('<div class="onesecond"/>');
					contentTag.append(paramData.name);
					container.append(contentTag);
					contentTag = $('<div class="onefive"/>');
					contentTag.append(paramData.value);
					if(paramData.unit){
						var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
						spanTag.append(paramData.unit);
						contentTag.append(spanTag);	
					}
					container.append(contentTag);
					contentTag = $('<div class="onefive"/>');
					contentTag.append(paramData.normal);
					container.append(contentTag);
					if(paramData.perc_id) {
						contentTag = $('<div class="onefourth"/>');
						contentTag.empty().html(paramData.perc_value);
						if(paramData.perc_unit){
							var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
							spanTag.append(paramData.perc_unit);
							contentTag.append(spanTag);	
						}
						container.append(contentTag);
						contentTag = $('<div class="onefourth">&nbsp;</div>');
						contentTag.empty().html(paramData.perc_normal);
						container.append(contentTag);
					}
					table.append(container);
				}
			} else {
				var container = $('<div class="one singleheight" />');
				var contentTag = $('<div class="onesecond"/>');
				contentTag.append(paramData.name);
				container.append(contentTag);
				contentTag = $('<div class="onefive"/>');
				elementTag = $('<input type="text" class="observed" />');
				if(paramData.className)
					elementTag.attr('class',paramData.className);
				if(paramData.value)
					elementTag.attr('value',paramData.value);
				$(elementTag).attr('id',paramData.id);
				contentTag.append(elementTag);
				if(paramData.unit)
					contentTag.append(paramData.unit);	
				container.append(contentTag);
				contentTag = $('<div class="onefive"/>');
				contentTag.append(paramData.normal);
				container.append(contentTag);
				contentTag = $('<div class="onefourth">&nbsp;</div>');
				if(paramData.perc_id) {
					elementTag = $('<input type="text" class="observed" />');
					if(paramData.className)
						elementTag.attr('class',paramData.className);
					if(paramData.value)
						elementTag.attr('value',paramData.value);
					$(elementTag).attr('id',paramData.perc_id);
					contentTag.append(elementTag);
					if(paramData.perc_unit){
						var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
						spanTag.append(paramData.perc_unit);
						contentTag.append(spanTag);	
					}
				}	
				container.append(contentTag);
				contentTag = $('<div class="onefourth">&nbsp;</div>');
				if(paramData.perc_id) 
					contentTag.empty().html(paramData.perc_normal);
				container.append(contentTag);
				table.append(container);
			}			
		});
		content.append(table);
		return content;
	};
}
function HistoPathLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div align="center" style="font-weight:bold">'+result.testName+'</div>');
		var table=$('<table width="100%" height="500px" id="realTable" />');
		var thead=$('<thead></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		var row=$('<tr/>');
		var tdTag = $('<td class="topAlign"/>');
		$(result.values).each (function(index,data){
			if(mode=="print"){
				var lblTag = $('<label/>');
				lblTag.html(commonMethodInvoker.nl2br(data.fieldval));
				tdTag.append(lblTag);	
			}
			else{
				var textarea=$('<textarea  style="width:100%; height:500px;" id="HistoPath_' + index +'_1" >'+ data.fieldval +'</textarea>');
				tdTag.append(textarea);					
				}	
		});
		row.append(tdTag);
		tbody.append(row);
		table.append(tbody);
		content.append(table);
		return content;
	};         
}
function layout1(honorific,testResult, testId, mode) {
	this.result =	function(){ 
		var response = $('<table />');
		$(testResult.analysis).each(function(analysisIndex,analysis) {
			var thead = $('<thead class="noBorder">');
			thead.attr('id','thead_'+analysisIndex);
			var headRow = $('<tr/>');
			var headCol = $('<th style="border:none;"/>');
			headCol.html(analysis.analysisType);
			headRow.html(headCol);
			thead.html(headRow);
			response.append(thead);
			var tbody=$('<tbody/>');
			var trTag = $('<tr />');
			var tdTag = $('<td style="border:none;"/>');
			$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
				var tblTag = $('<table style="width:90%"/>');
				$(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
					$(resultparam.values).each(function(paramvaluesIndex,paramValues) {						
						$(paramValues.values).each(function(paramdataIndex,paramData) {							
							if(paramData.values) {	
								var newrow = $('<tr/>');
								if(resultparam.headerLabel) {
									var colhead=$('<td>');
									colhead.html(resultparam.headerLabel);
									newrow.append(colhead);
								};	
								$(paramData.values).each(function(paramdataValueIndex,paramDataValue) {
									if(paramDataValue.normalRangeAttribute) {	
										var colTag = $('<td />');
										colTag.append(paramDataValue.normalRangeAttribute);	
										newrow.append(colTag);								
									}
									if(paramDataValue.displayType) {	
										var colTag = $('<td />');
										var spanTag=$('<span/>');
										if(paramDataValue.displayType=='text') {
											elementTag = $('<input type="text"/>');
											if(mode=='print') {
												elementTag.attr('class','readonlybox');
												elementTag.attr('readonly',true);
											}
											var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex+"_"+paramdataValueIndex;
											$(elementTag).attr('id',txtid);
											if(paramDataValue.className){
											elementTag.attr('class',paramDataValue.className)
											}
											if(paramDataValue.style){
											elementTag.attr('style',paramDataValue.style);
											}
											if(paramDataValue.value) {
											 elementTag.attr('value',paramDataValue.value);
											}
											colTag.append(elementTag);
												if(paramDataValue.unit) {
												colTag.append(paramDataValue.unit);
											}
										}
										newrow.append(colTag);
									} else {
										if(paramDataValue.value) {
											var colTag = $('<td />');
											colTag.html(paramDataValue.value);
											if(paramDataValue.unit)
												colTag.prepend(paramDataValue.unit);
											newrow.append(colTag);	
										}
									}
									tblTag.append(newrow);									
								});								
							} 																															
						});	
						var newrow = $('<tr/>');
						if(resultparam.headerLabel) {
							var colhead=$('<td>');
							newrow.append(colhead);
						};	
						$(paramValues.values).each(function(paramdataIndex,paramData) {															
							if(paramData.normalRangeAttribute) {	
								var colTag = $('<td  style="width:60%;padding-left:25%;text-align:left"/>');
								colTag.append(paramData.normalRangeAttribute);	
								newrow.append(colTag);								
							}
							var colTag = $('<td  style="text-align:center"/>');
							colTag.append("&nbsp; &nbsp;&nbsp;: &nbsp;&nbsp; ");	
							newrow.append(colTag);	
							if(paramData.displayType) {	
								var colTag = $('<td style="text-align:left"/>');
								if(paramData.displayType=='text') {
									elementTag = $('<input type="text" style="text-align:left"/>');
									if(mode=='print') {
										elementTag.attr('class','readonlybox');
										elementTag.attr('readonly',true);
									}
									var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
									$(elementTag).attr('id',txtid);
									if(paramData.className){
											elementTag.attr('class',paramData.className);
											}
										if(paramData.style){
										elementTag.attr('style',paramData.style);
											}
									if(paramData.readonly){
											elementTag.attr('readonly',true);
											}
									if(paramData.value) {
										elementTag.attr('value',paramData.value);
										elementTag.attr('style','font-size:100%;');
									}
									colTag.append(elementTag);
									if(paramData.unit) {
										colTag.append(paramData.unit);
									}
									 }
								if(paramData.displayType=="select") {
										elementTag = $('<select/>');
										if(paramData.list) {
											$(paramData.list).each(function(indexval,val) {
												var optTag = $('<option/>')
												if(val.value)optTag.attr("value",val.value);
												if(val.selected)optTag.attr("selected",val.selected);
												optTag.html(val.value);
												elementTag.append(optTag);
											});
										}
										if(paramData.id){
										elementTag.attr('id',paramData.id)
										}
										colTag.append(elementTag);																							
									}
								
								newrow.append(colTag);
							} else {
								if(paramData.value) {
									var colTag = $('<td />');
									colTag.html(paramData.value);
									if(paramData.unit)
										colTag.append(paramData.unit);
									newrow.append(colTag);	
								}
							}
						});
						tblTag.append(newrow);	
					});	
				});
				tdTag.append(tblTag);
				trTag.append(tdTag);
				tbody.append(trTag);
				response.append(tbody);
			});
			
		});	
		return response.html();
	};	
}
function LipidLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
	var content =$('<div align="center" />');
	var titleSection =$('<div align="center" style="font-size:18px; line-height:40px;">'+result.testName+'</div>');
	var table;
	if(honorific=="Animal"){
		if(mode!="print"){
			var spanTag=$('<span style="float:right; margin-right:10px;font-size:14px; line-height:6px;">Manual calculation</span>');
			 var inputTag=$('<input id="manualCalc" type="checkbox" />')
			 if(result.checked==true)
			   inputTag.attr('checked','true');
			spanTag.append(inputTag);
			titleSection.append(spanTag);
		}
		content.append(titleSection);
		var table=$('<table width="100%" id="SpHgtTable" />');
		var thead=$('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			if(mode=="print" && data.id=="LDL"){
			  if(result.checked==true )
					var column=$('<td style="width:40%">'+data.name+' (Direct)</td>');
				else
					var column=$('<td style="width:40%">'+data.name+'</td>');			
			}
			else
			var column=$('<td style="width:40%">'+data.name+'</td>');
			row.append(column);
			var testId=getTestId(data.name);
			var testInfo = getTestData(testId);
			var resultInfo=$.parseJSON(testInfo.result);
			column=$('<td class="middle"/>');
			if(mode=="print") {
				elementTag = $('<label />')
				elementTag.text(data.fieldval);
				column.append(elementTag);
			} else {
				var inputTag=$('<input type="text" class="'+data.className+'" id="LPT' + index +'_1"  value="' + data.fieldval + '"/>');
				  column.append(inputTag);
				
			}		
			var spanTag=$('<span>'+resultInfo.testUnit+'</span>');
			column.append(spanTag);
			row.append(column);
			  if(index==4 && result.checked==true ){
				 return;
			  }
			  else
			   tbody.append(row);
			  });
			  table.append(tbody);
	}
	else {
	     	if(mode!="print"){
			var spanTag=$('<span style="float:right; margin-right:10px;font-size:14px; line-height:6px;">Manual calculation</span>');
			 var inputTag=$('<input id="manualCalc" type="checkbox" />')
			 if(result.checked==true)
			   inputTag.attr('checked','true');
			spanTag.append(inputTag);
			titleSection.append(spanTag);
		}
		content.append(titleSection);
		var table=$('<table width="100%" id="SpHgtTable" />');
		var thead=$('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th><th>REFERENCE RANGE&UNIT</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			if(mode=="print" && data.id=="LDL"){
			  if(result.checked==true )
					var column=$('<td style="width:40%">'+data.name+' (Direct)</td>');
				else
					var column=$('<td style="width:40%">'+data.name+'</td>');			
			}
			else
			var column=$('<td style="width:40%">'+data.name+'</td>');
			row.append(column);
			var testId=getTestId(data.name);
			var testInfo = getTestData(testId);
			var resultInfo=$.parseJSON(testInfo.result);
			column=$('<td class="middle"/>');
			if(mode=="print") {
				elementTag = $('<label />')
				elementTag.text(data.fieldval);
				column.append(elementTag);
			} else {
				var inputTag=$('<input type="text" class="'+data.className+'" id="LPT' + index +'_1"  value="' + data.fieldval + '"/>');
				  column.append(inputTag);
				
			}		
			var spanTag=$('<span>'+resultInfo.testUnit+'</span>');
			column.append(spanTag);
			row.append(column);
			var newcolumn=$('<td style="width:35%" />');
			if(index==4){
				if(mode=="print") {
					elementTag = $('<label />')
					elementTag.text(data.fieldvalue);
					newcolumn.append(elementTag);
				}
				else{
				var inputTagNew=$('<input type="text" id="LPT4_2" value="'+data.fieldvalue+'"/>');
				newcolumn.append(inputTagNew);
				}
			}
			else{
				var spanTag=$('<span/>');
				spanTag.append(testInfo.normalRange);
				newcolumn.append(spanTag);
				}
			  row.append(newcolumn);
			  if(index==4 && result.checked==true ){
				 return;
			  }
			  else
			   tbody.append(row);
			  });
			  table.append(tbody);
		 
	}		 
	content.append(table);
	return content;
		};
}
function lipidLayoutMed(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div/>');
		var titleSection =$('<div class="one bold singleheight underline">'+result.testName+'</div>');
		content.append(titleSection);
		var table=$('<div width="100%" id="lipidTable" />');
		$(result.values).each (function(index,paramData){
			if(mode=='print') {
				if(paramData.value.trim()!="") {
					var container = $('<div class="one singleheight"/>');
					//container.attr('min-height','25px');
					var contentTag = $('<div class="onefirst"/>');
					contentTag.append(paramData.name);
					container.append(contentTag);

					contentTag = $('<div class="onesecond"/>');
					contentTag.append(paramData.value);
					if(paramData.unit){
						var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
						spanTag.append(paramData.unit);
						contentTag.append(spanTag);	
					}
					container.append(contentTag);
					contentTag = $('<div class="onesecond"/>');
					contentTag.append(paramData.normal);
					container.append(contentTag);
					table.append(container);
				}
			} else {
				var container = $('<div class="one singleheight" />');
				var contentTag = $('<div class="onefirst"/>');
				contentTag.append(paramData.name);
				container.append(contentTag);
				contentTag = $('<div class="onesecond"/>');
				elementTag = $('<input type="text" class="observed" />');
				if(paramData.className)
					elementTag.attr('class',paramData.className);
				if(paramData.value)
					elementTag.attr('value',paramData.value);
				$(elementTag).attr('id',paramData.id);
				contentTag.append(elementTag);
				if(paramData.unit)
					contentTag.append(paramData.unit);	
				container.append(contentTag);
				contentTag = $('<div class="onesecond"/>');
				contentTag.append(paramData.normal);
				container.append(contentTag);
				table.append(container);
			}
			
		});
		content.append(table);
		return content;
	};
}
function LiverLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
	var content =$('<div align="center"/>');
	var titleSection=$('<div align="center" class="resultTitle" >'+result.testName+'</div>');
	content.append(titleSection);
	var table;
	if(honorific=="Animal"){
		table=$('<table width="100%" id="SpHgtTable" />');
		var thead=$('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			var column=$('<td style="width:50%">'+data.name+'</td>');
			row.append(column);
			var testId=getTestId(data.name);
			var testInfo = getTestData(testId);
			var resultInfo=$.parseJSON(testInfo.result);
			column=$('<td style="width:50%"></td>');
			if(mode=="print") {
				elementTag = $('<label />')
				elementTag.text(data.fieldval);
				column.append(elementTag);
			}
			else{	
				var inputTag=$('<input type="text" class="'+data.className+'" id="LFT' + index +'_1"  value="' + data.fieldval + '" tabindex=' + (index+1) + '/>');
				 if(data.readonly)	
				 inputTag.attr("readonly","readonly");
				column.append(inputTag);
			}
			var spanTag=$('<span style="font-weight:normal">'+'&nbsp;&nbsp;'+resultInfo.testUnit+'</span>');
			column.append(spanTag);
			row.append(column);
			
			  tbody.append(row);
			  });
			  table.append(tbody);
	} 
	else{
		table=$('<table width="100%" id="SpHgtTable" />');
		var thead=$('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th><th>REFERENCE RANGE&UNIT</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			var column=$('<td style="width:30%">'+data.name+'</td>');
			row.append(column);
			var testId=getTestId(data.name);
			var testInfo = getTestData(testId);
			var resultInfo=$.parseJSON(testInfo.result);
			column=$('<td></td>');
			if(mode=="print") {
				elementTag = $('<label />')
				elementTag.text(data.fieldval);
				column.append(elementTag);
			}
			else{	
				var inputTag=$('<input type="text" class="'+data.className+'" id="LFT' + index +'_1"  value="' + data.fieldval + '" tabindex=' + (index+1) + '/>');
				 if(data.readonly)	
				 inputTag.attr("readonly","readonly");
				column.append(inputTag);
			}
			var spanTag=$('<span style="font-weight:normal">'+'&nbsp;&nbsp;'+resultInfo.testUnit+'</span>');
			column.append(spanTag);
			row.append(column);
			var newcolumn=$('<td style="width:40%;font-weight:normal"></td>');
			if(mode=="print") {
				newcolumn.append(commonMethodInvoker.space2tab(commonMethodInvoker.nl2br(data.OBSRDVal)));
			}
			else{
				if(testId=="T184"){
					var textarea=$('<textarea id="LFT' + index +'_2" />');
					textarea.html(commonMethodInvoker.br2nl(testInfo.normalRange));
					newcolumn.append(textarea);
				}
				else{
					var inputTag=$('<input type="text" id="LFT' + index +'_2"  value="' + commonMethodInvoker.br2nl(testInfo.normalRange) + '"/>');		
					newcolumn.append(inputTag);
				}
			}	
			  row.append(newcolumn);
			  tbody.append(row);
			  });
			  table.append(tbody);				
	}		
		  content.append(table);
		  return content;
		};         
}
function liverLayoutMed(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$('<div/>');
		var titleSection =$('<div class="one bold singleheight underline">'+result.testName+'</div>');
		content.append(titleSection);
		var table=$('<div width="100%" id="LFT_Table" />');
		$(result.values).each (function(index,paramData){
			if(mode=='print') {
				if(paramData.value.trim()!="") {
					var container = $('<div class="one singleheight"/>');
					//container.attr('min-height','25px');
					var contentTag = $('<div class="onefirst"/>');
					contentTag.append(paramData.name);
					container.append(contentTag);
					contentTag = $('<div class="onesecond"/>');
					contentTag.append(paramData.value);
					if(paramData.unit){
						var spanTag = $('<span style="padding:0px 0px 0px 5px" />');
						spanTag.append(paramData.unit);
						contentTag.append(spanTag);	
					}
					container.append(contentTag);
					contentTag = $('<div class="onesecond"/>');
					contentTag.append(paramData.normal);
					container.append(contentTag);
					table.append(container);
				}
			} else {
				var container = $('<div class="one singleheight" />');
				var contentTag = $('<div class="onefirst"/>');
				contentTag.append(paramData.name);
				container.append(contentTag);
				contentTag = $('<div class="onesecond"/>');
				elementTag = $('<input type="text" class="observed" />');
				if(paramData.className)
					elementTag.attr('class',paramData.className);
				if(paramData.value)
					elementTag.attr('value',paramData.value);
				$(elementTag).attr('id',paramData.id);
				contentTag.append(elementTag);
				if(paramData.unit)
					contentTag.append(paramData.unit);	
				container.append(contentTag);
				contentTag = $('<div class="onesecond"/>');
				elementTag = $('<input type="text" />');
				elementTag.attr('value',paramData.normal);
				contentTag.append(elementTag);
				container.append(contentTag);
				table.append(container);
			}			
		});
		content.append(table);
		return content;
	};
}
function osmoticLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<div/>');
		//div for report title starts here
		var fstDiv = $('<div  align="center" class="resultTitle">'+result.testName+'</div>');
		content.append(fstDiv);
	    var table=$('<table id="SpHgtTable"/>');
		var thead=$('<thead/>');
		var headrow=$('<tr>');
		$(result.Resultheaders).each(function(index,headervalue){
		  var headcol=$('<th style="width:33%">'+headervalue.headername+'</th>');
		  headrow.append(headcol);
		});
		thead.append(headrow);
      	table.append(thead);
		var tbody=$('<tbody/>');
		$(result.Resultbody).each(function(index,headerbody){
		var newRow=$('<tr/>');
		var newColumns=$('<td />');
		if(mode=="print"){
			var lblTag = $('<label/>');
			lblTag.html(headerbody.headerOneval);
			newColumns.addClass('middle');
			newColumns.html(lblTag);
		}
		else {
			var inputTag=$('<input type=text  align="center" id="Osmotic_' + index +'_1" value="' + headerbody.headerOneval +'"/> ');
			newColumns.append(inputTag);
		}
		newRow.append(newColumns);
		var newColumns=$('<td/>');
		if(mode=="print"){
			var lblTag = $('<label/>');
			lblTag.html(headerbody.headerTwoval);
			newColumns.addClass('middle');
			newColumns.html(lblTag);
		}
		else{
			var inputTag=$('<input type=text  align="center" id="Osmotic_' + index +'_2" tabIndex="'+(index+1)+'" value="' + headerbody.headerTwoval +'"/>');
			newColumns.append(inputTag);
		}
		newRow.append(newColumns);
		var newColumns=$('<td/>');
		if(mode=="print"){
			var lblTag = $('<label/>');
			lblTag.html(headerbody.headerThreeVal);
			newColumns.addClass('middle');
			newColumns.html(lblTag);
		}
		else{
			var inputTag=$('<input type=text align="center" id="Osmotic_' + index +'_3" value="' + headerbody.headerThreeVal +'"/>');
			newColumns.append(inputTag);
		}
		newRow.append(newColumns);
		if(mode=="print") {
				if(headerbody.headerOneval.trim()!=""){
					tbody.append(newRow);
				}	
		}	
		else
			tbody.append(newRow);
		});
		table.append(tbody);
		content.append(table);
		return content;		
		};
}
function PeripheralLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
	var content =$('<div align="center" style="font-weight:bold">'+result.testName+'</div>');
		var table=$('<table width="100%" id="realTable" />');
		var thead=$('<thead></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.values).each (function(index,data){
		  var row=$('<tr/>');
		  var column=$('<td></td>');
		  if(mode=="print"){
			var lblTag = $('<label/>');
			lblTag.html(data.fieldval);
			column.html(lblTag);
		  }
		  else{
				var inputTag=$('<input type="text"  style="width:100%;" id="Peripheral_' + index +'_1" value="' + data.fieldval + '"/>');
				column.append(inputTag);
			}	
		  row.append(column);
		  if(mode=="print") {
				if(data.fieldval.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
		  });
		  table.append(tbody);
		  content.append(table);
		  return content;
		};         
}
function PTLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var tbody=$('<tbody/>');
		var trow = $('<tr><td style="font-size:100%;font-weight:bold;">INVESTIGATION</td><td style="font-size:100%;font-weight:bold;">OBSERVED VALUE</td><td style="font-size:100%;font-weight:bold;">REFERENCE RANGE&UNIT</td></tr>');
		tbody.append(trow);
		$(result.values).each (function(index,data){
			var row=$('<tr/>');
			if(data.FieldOneName){
				if(data.FieldOneName!=""){
					var column=$('<td style="width:33%;padding-left:5px;">'+data.FieldOneName+'</td>');
					row.append(column);
				}
			}
			if(index==1){
		        var column=$('<td rowspan="3"></td>');
				 row.append(column);
			}
			var column=$('<td style="width:33%;padding-left:5px;"/>');
			var spanTag=$('<div style="width:35%;float:left;">'+data.FieldTwoName+'</div>');
			column.append(spanTag);
			var spanTag=$('<div style="width:5%;float:left;">:</div>');
		    column.append(spanTag);
			var spanTag=$('<div style="width:35%;float:left;"/>');
		   	if(mode=="print") {
				var lblTag = $('<label />');
				lblTag.html(data.FieldVal +" "+data.unitVal);
				spanTag.append(lblTag);
			} else {
				var inputTag=$('<input type="text"  id="PT_' + index +'_1" class="large" value="' + data.FieldVal + '"/>');
				spanTag.append(inputTag);
			}
		    column.append(spanTag);
			spanTag=$('<div style="width:20%;float:left;"></div>');
			if(data.time){
				if(mode=="print") {
					var lblTag = $('<label />');
					lblTag.html(data.UnitType);
					spanTag.append(lblTag);
				} else {
					var inputTag=$('<input type="text" class="'+data.time+'" value="'+data.unitVal+'" id="PT_'+index+'_2"/>');
					spanTag.append(inputTag);
					if(data.UnitType)
						spanTag.html(data.UnitType);
				}
			}		
		    column.append(spanTag);
		   	row.append(column);
		  	if(data.FieldThreeName){
			   	if(data.FieldThreeName!=""){
					var column=$('<td style="width:33%;padding-left:5px;">'+data.FieldThreeName+'</td>');
				   	row.append(column);
			   	} 
		    } 
			if(index==1){
				var column=$('<td rowspan="3"></td>');
				row.append(column);
			}
		  	tbody.append(row);
		});
		return tbody;
	};
}
function getContent(result) {
	this.result = function () {
		//Fill Data values
		var tmpTable = $('<table/>');		
		$(result.analysis).each(function(index,analysis) {
			var tmpHead=result.testName + "-";
			$(analysis.resultContent).each(function(index1,rescont) {
				var tbodyTag = $('<tbody/>');
				$(rescont.resultParams).each(function(index2,resparam) {
					var trTag=$('<tr/>');
					var tdTag = $('<td/>');
					if(resparam.key) {
						tmpHead +=resparam.key;
						tdTag.append(tmpHead);
						tmpHead="";
					}	
					else
						tdTag.append(result.testName);						
					trTag.append(tdTag);
					$(resparam.values).each(function(index2,elemnt) {
						tdTag = $('<td/>');						
						if(elemnt.values){
							$(elemnt.values).each(function(index2,elem) {
								var elemContainerTag=$('<div/>');
								if(elem.normalRangeLabel) {
									elemContainerTag.append(elem.normalRangeLabel);
								}
								if(elem.displayType){																	
									if(elem.displayType=="text") {
										elmnTag = $('<input type="text"/>');
										if(elemnt.value)
											elmnTag.attr('value',elemnt.value);
										elemContainerTag.append(elmnTag);
									}
									if(elem.displayType=="select") {
										elmnTag = $('<select/>');
										if(elem.list) {
											$(elem.list).each(function(indexval,val) {
												var optTag = $('<option/>')
												optTag.html(val.value);
												elmnTag.append(optTag);
											});
										}
										if(elemnt.value)
											elmnTag.attr('value',elemnt.value);
										elemContainerTag.append(elmnTag);
									}
								}else {
									elmnTag = $('<label/>');
									if(elem.value)
										elmnTag.html(elem.value);
									else
										elmnTag.html('&nbsp;');
									elemContainerTag.append(elmnTag);
								}								
								if(elem.unit){									
									elemContainerTag.append("/"+elem.unit);
								}else {
									elemContainerTag.append("&nbsp;");
								}
								tdTag.append(elemContainerTag);								
							});													
						}else {	
							var elemntContainerTag=$('<div width:30%; style="float:left;"/>');
							if(elemnt.displayType=="text") {
								elmnTag = $('<input type="text"/>');
								if(elemnt.value)
									elmnTag.attr('value',elemnt.value);
								elemntContainerTag.append(elmnTag);								
								tdTag.append(elemntContainerTag);
							}
							if(elemnt.displayType=="select") {
								elmnTag = $('<select/>');
								if(elemnt.list) {
									$(elemnt.list).each(function(indexval,val) {
										var optTag = $('<option/>')
										optTag.html(val.value);
										elmnTag.append(optTag);
									});
								}
								if(elemnt.value)
									elmnTag.attr('value',elemnt.value);
								elemntContainerTag.append(elmnTag);
								tdTag.append(elemntContainerTag);
							}							
							var divUnitTag = $('<div/>');
							if(elemnt.unit){									
								divUnitTag.html("/"+elemnt.unit);
							}else {
								divUnitTag.html("&nbsp;");
							}								
							tdTag.append(divUnitTag);
						}	
						trTag.append(tdTag);						
					});	
					tbodyTag.append(trTag);
					tmpTable.append(tbodyTag);
				});
			});	
		});
		return tmpTable.html();
	};
}
function SemenLayout(honorific,result,testId, mode) {
	this.result =function(){ 
		var azospermia = false;
		var content =$('<div align="center" class="resultTitle">'+result.testName+'</div>');
		var semenTable=$('<table width="100%" id="SemenTable" style="font-size:15px"/>');
		var tbodyTag;
		$(result.analysis).each(function(ResultContentIndex,analysis){
			if(analysis.analysisType){
				var theadTag = $('<thead />');
				var trTag=$('<tr></tr>');
				var tdTag = $('<th colspan="2"></th>');
				tdTag.html(analysis.analysisType);
				trTag.append(tdTag);	
				theadTag.html(trTag);
				semenTable.append(theadTag);
			}	
			tbodyTag=$('<tbody/>');
			if(analysis.analysisType=="MICROSCOPIC EXAMINATION"){
				var trTag = $('<tr/>');
				var spanTag;
				$(analysis.resultContent).each(function(index,resultcontent) {
					if(resultcontent.value.trim()=="Azospermia")
						azospermia = true;
					if(index!=2) {					
						tdTag = $('<td style="width:50%"/>');	
						spanTag = $('<span />');
					} else {
						spanTag = $('<span />');
					}					
					if(mode=='print') {
						var lblTag = $('<label/>');
						lblTag.html(resultcontent.value);
						spanTag.append(lblTag);
					} else {
						var inputTag=$('<input type="text" id="' + resultcontent.id +'" value="' + resultcontent.value + '" />');
						if(resultcontent.className)
							inputTag.attr('class',resultcontent.className);
						spanTag.append(inputTag);
					}
					tdTag.append(spanTag);
					if(index!=1)
						trTag.append(tdTag);
				});	
				tbodyTag.append(trTag);
			}else if(analysis.analysisType=="SPERM MORPHOLOGY"){//Sperm Morphology Section 
				var trParentTag = $('<tr />');
				var tdTag = $('<td colspan="2"/>');
				var innerTable = $('<table width="100%" style="font-size:15px"/>');
				var status =true;
				$(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
					var trTag=$('<tr/>');
					$(resultcontent.values).each(function(valuesIndex,values){	
						//abnormal Heading
						if(resultcontentIndex==0) {
							if(status==true) {
								var tdchildTag=$('<td >' + resultcontent.headerLabel +'</td>');
								trTag.append(tdchildTag);
								status=false;
							}	
							var tdchildTag = $('<td style="width:27%"/>');
							var spanTag = $('<span>' + values.normalRangeAttribute + '</span>');
							tdchildTag.append(spanTag);		
							spanTag = $('<span/>');
							if(mode=='print') {	
								var lblTag = $('<label/>');
								if(values.value=="")
									lblTag.html("--");
								else
									lblTag.html(values.value);							
								spanTag.html(lblTag);
								tdchildTag.append(spanTag);
							} else {
								var inputTag=$('<input type="text" id="'+values.id + '" value="' + values.value + '"/>');
								if(values.className)
									inputTag.attr('class',values.className);
								spanTag.html(inputTag);
								tdchildTag.append(spanTag);
							}
							if(values.unit) {
								spanTag = $('<span/>');
								if(azospermia==false && values.value.toUpperCase()!="NIL" && values.value.trim()!="" && values.value.trim()!="--") 
									spanTag.html(values.unit);
								tdchildTag.append(spanTag);
							}							
							trTag.append(tdchildTag);
						} else {
							var tdchildTag=$('<td colspan="2"/>');
							var spanTag = $('<span>' + values.normalRangeAttribute + '</span>');
							tdchildTag.append(spanTag);		
							spanTag = $('<span/>');
							if(mode=='print') {	
								var lblTag = $('<label/>');
								lblTag.html(values.value);							
								spanTag.html(lblTag);
								tdchildTag.append(spanTag);
							} else {
								var inputTag=$('<input type="text" id="'+values.id + '" value="' + values.value + '"/>');
								if(values.className)
									inputTag.attr('class',values.className);
								spanTag.html(inputTag);
								tdchildTag.append(spanTag);
							}							
							if(values.unit) {
								spanTag = $('<span/>');
								if(azospermia==false && values.value.toUpperCase()!="NIL" && values.value.trim()!=" "&& values.value.trim()!="--") 
									spanTag.html(values.unit);
								tdchildTag.append(spanTag);
							}
							trTag.append(tdchildTag);							
						}
					});						
					innerTable.append(trTag);
				});
				tdTag.append(innerTable);
				trParentTag.append(tdTag);
				tbodyTag.append(trParentTag);
			} else {		//Other Sections
				$(analysis.resultContent).each(function(index,resultcontent) {
					var trTag = $('<tr/>');
					if(resultcontent.normalRangeAttribute=="Liquefaction") {
						var tdTag = $('<td style="width:50%"/>');
						var spanTag = $('<span />');
						spanTag.html(resultcontent.normalRangeAttribute);
						tdTag.html(spanTag);
						spanTag = $('<span />');
						if(mode=='print') {
							var lblTag = $('<label style="float:right;"/>');
							lblTag.html(resultcontent.value);
							tdTag.append(lblTag);
						} else {
							var inputTag=$('<input style="float:right;" type="text" align="right" id="'+ resultcontent.id + '" value="' + resultcontent.value + '"/>');
							if(resultcontent.className1)
								inputTag.attr('class',resultcontent.className1);
							tdTag.append(inputTag); 
						}						
						trTag.append(tdTag);
						
						tdTag = $('<td style="width:50%"/>');
						if(mode=='print') {
							var lblTag = $('<label/>');
							lblTag.html(resultcontent.value1);
							tdTag.append(lblTag);
						} else {
							var inputTag=$('<input type="text" id="'+ resultcontent.id + '_1' +'" value="' + resultcontent.value1 + '"/>');
							if(resultcontent.className)
								inputTag.attr('class',resultcontent.className);
							tdTag.append(inputTag); 
						}	
						if(resultcontent.unit) {
							spanTag = $('<span/>');
							spanTag.html(resultcontent.unit);
							tdTag.append(spanTag);
						}
						trTag.append(tdTag);
						tbodyTag.append(trTag);
					} else {
						var tdTag = $('<td style="width:50%"/>');
						tdTag.html(resultcontent.normalRangeAttribute); // add first column commonly caption
						trTag.append(tdTag);
						tdTag = $('<td style="width:50%" align="left"/>');
						if(mode=='print') {
							if(analysis.analysisType=="SPERM MOTILITY")
								var lblTag = $('<div style="float:left;text-align:right;padding-right:5px;min-width:10%" />');
							else
								var lblTag = $('<div style="float:left;padding-right:5px;" />');
								if(resultcontent.value=="")
									lblTag.html("--");
								else
									lblTag.html(resultcontent.value);
							tdTag.html(lblTag);
						} else {
							var inputTag=$('<input type="text" id="'+resultcontent.id +'" value="' + resultcontent.value + '"/>');
							if(resultcontent.className)
								inputTag.attr('class',resultcontent.className);
							tdTag.html(inputTag); 
						}
						if(resultcontent.unit) {
							spanTag = $('<span/>');
							if(analysis.analysisType=="SPERM MOTILITY"){
								if(azospermia==false && resultcontent.value.toUpperCase()!="NIL" && resultcontent.value!="--" && resultcontent.value!="-" && resultcontent.value!="") 
									spanTag.html(resultcontent.unit);
								else
                                    spanTag.html("");								
							}else
							 spanTag.html(resultcontent.unit);
							tdTag.append(spanTag);
						}
						trTag.append(tdTag);
						tbodyTag.append(trTag);
					}
				});
			}	
			semenTable.append(tbodyTag);			
		});	
		content.append(semenTable);
		return content; 
	};
}	
function stone(honorific,result,testId,mode) {
this.result =function(){ 
		var content =$('<div/>');
		var table=$('<table width="100%" />');
		var thead=$('<thead><th colspan="2">'+result.analysisType+'</th></thead>');
		table.append(thead);
		var tbody=$('<tbody/>');
		$(result.resultContent).each(function(ResultContentIndex,resultContentVal){
			if(resultContentVal.title){
				var newrow=$('<tr><td>'+resultContentVal.title+'</td></tr>');
				tbody.append(newrow);
			}
			newrow=$('<tr><td></td></tr>');
			newtd=$('<td/>');
			var newtable=$('<table id="StoneTable"/>');
			var newtablebody=$('<tbody/>');
			$(resultContentVal.resultParams).each(function(index,tblVal){
				 var tablerow=$('<tr/>');
				 var tablecolumns=$('<td colspan="2">'+tblVal.Attribute+'<td>');
				 tablerow.append(tablecolumns);
				 if(tblVal.colon){
					tablecolumns=$('<td >'+tblVal.colon+'<td>');
					tablerow.append(tablecolumns);
				}
				tablecolumns=$('<td width="100%"/>');
				if(tblVal.displayType=="text"){
					var spanTag=$('<span />');
					if(mode=="print") {
						var lblTag = $('<label/>');
						lblTag.html(tblVal.value);
						spanTag.append(lblTag);
					}
					else{		
						var inputTag=$('<input type="text" class="'+tblVal.className+'" id="Stone_' + index +'_1" value="' + tblVal.value + '"/>');
						spanTag.append(inputTag)			
			          }
					tablecolumns.append(spanTag);
					tablerow.append(tablecolumns);
					newtablebody.append(tablerow);
			    }
				else if(tblVal.displayType=="textarea"){
					var spanTag=$('<span />');
					if(mode=="print") {
						var lblTag = $('<label/>');
						lblTag.html(tblVal.value);
						spanTag.append(lblTag);
					}														
					else{		
						 var textarea=$('<textarea class="large" id="Stone_3_1" style="width:100%" >'+tblVal.value+'</textarea>');	
						spanTag.append(textarea);						 
						
					}
					tablecolumns.append(spanTag);
					tablerow.append(tablecolumns);
					newtablebody.append(tablerow);
			    } 
			});
			newtable.append(newtablebody);
			newtd.append(newtable)
			newrow.append(newtd);
			tbody.append(newrow);   
			 
		 });
		 table.append(tbody);
		 content.append(table);
		return content; 
	};
}	
function stoolLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<div/>');
		var fstDiv = $('<div  height="20px;" style="font-size:18px; line-height:40px;" align="center"> STOOL ANALYSIS</div>');
		content.append(fstDiv);
		var SecDiv = $('<div class="five_sixth"/>');
	    var table=$('<table id="SpHgtTable" style="width:550px;"/>');
		var tablebody=$('<tbody/>')
		var count;
		$(result.values).each(function(index,tblVal){
		    var row=$('<tr/>');
			var rowColumns=$('<td width="35%" >'+tblVal.testExamine+'</td>');
			row.append(rowColumns);
			 var rowColumns=$('<td/>');
			 var spanTag=$('<span />');
			 if(mode=="print") {
				var lblTag = $('<label/>');
				lblTag.html(tblVal.value);
				spanTag.append(lblTag);
			}
			else{
				 var inputTag=$('<input type="text" class="'+tblVal.className+'"  style="font-size:15px;"id="Stvalue_' + index +'_1" value="' + tblVal.value + '"/>');
				 spanTag.append(inputTag);
			}
            rowColumns.append(spanTag);			
			row.append(rowColumns);
			tablebody.append(row);			
		});
		var newrow=$('<tr><td colspan="2" align="center" style="font-size:18px;" class="microscopy">Microscopy</td></tr>');
		tablebody.append(newrow);
		$(result.testMicrosopy).each(function(index,tblVal){
			var row=$('<tr/>');	
			if(mode=="print") {
			    var column=$('<td width="35%" ></td>');
				var spanTag=$('<span />');
				var lblTag = $('<label/>');
				lblTag.html(tblVal.MSresult1);
				spanTag.append(lblTag);
				column.append(spanTag);
				row.append(column);
				column=$('<td></td>');
				spanTag=$('<span />');		
				lblTag = $('<label/>');
				lblTag.html(tblVal.MSresult2);
				spanTag.append(lblTag);
				column.append(spanTag);
				row.append(column);	
				}
			else{
				var column=$('<td width="35%" ></td>');
				var spanTag=$('<span />');				
				var inputTag=$('<input type="text" id="StMicro_' + index +'_1" class="'+tblVal.className+'"  value="' + tblVal.MSresult1 + '"/>');
				spanTag.append(inputTag);
				column.append(spanTag);
				row.append(column);
				column=$('<td/>');
				spanTag=$('<span />');
				inputTag=$('<input type="text" id="StMicro_' + index +'_2" class="'+tblVal.className+'"  value="' + tblVal.MSresult2 + '"/>');
				spanTag.append(inputTag);
				column.append(spanTag);
				row.append(column);
				}
					
			if(mode=="print") {
				if(tblVal.MSresult1.trim()!="") 
					tablebody.append(row); 
				
			} else
				tablebody.append(row);
		});
		var lastrow=$('<tr><td> Occultblood</td></tr>');
		var newRow=$('<td/>');
		var spanTag=$('<span />');
		if(mode=="print") {
			var lblTag = $('<label/>');
			lblTag.html(result.Occubld);
			spanTag.append(lblTag);
		}else{
			var inputTag=$('<input type="text" id="StOccubld_0_1" style="font-size:15px;" class="medium common" value="' + result.Occubld + '"/>');
			spanTag.append(inputTag);
		}
		newRow.append(spanTag);
		lastrow.append(newRow);				
		tablebody.append(lastrow);
		table.append(tablebody);
		SecDiv.append(table);
		content.append(SecDiv);
		return content;
	};
}
function UrineLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		//div for report title starts here
		var content =$('<div align="center"/>');
		var titleSection = $('<div align="center" class="resultTitle">'+result.testName + '</div>');
		content.append(titleSection);			
		var tableTag=$('<table id="SpHgtTable" width="100%"/>');
		var tbodyTag=$('<tbody/>');		
		$(result.values).each(function(index,values){
			var trTag=$('<tr/>');
			var tdTag=$('<td style="width:20%">'+values.testnameval+'</td>'); //first Column
			trTag.append(tdTag);
			tdTag=$('<td style="width:30%"></td>'); 			
			if(mode=='print') {
				var lblTag = $('<label/>');
				lblTag.html(values.value1);
				tdTag.append(lblTag);
			} else {
				if(values.displayType1=="text")
					var inputTag=$('<input type="text" id="US_'+ index +'_11" name="US_'+ index +'_1" value="'+values.value1+'" tabIndex=' +values.tabIndex1 + '/>');
				if(values.displayType1=="select")
					var inputTag=$('<select id="US_'+ index +'_11" name="US_'+ index +'_1" value="'+values.value1+'" tabIndex=' +values.tabIndex1 +'/>');	
				if(values.className1)
					inputTag.attr('class',values.className1);
				tdTag.append(inputTag);				
			}
			trTag.append(tdTag);			
			if(index=='0'){
				tdTag=$('<td colspan="2" style="text-align:center; width:50%">'+values.testnameval2+'</td>');
				trTag.append(tdTag);
			} else { // index !=0
				if(values.testnameval2==""){
					tdTag=$('<td style="width:20%"/>');					
					if(mode=='print') {
						var lblTag = $('<label/>');
						lblTag.html(values.editField);
						tdTag.append(lblTag);
					} else {
						var inputTag=$('<input type="text" id="US_'+ index +'_02" value="'+values.editField+'"/>');
						tdTag.append(inputTag);
					} 					 
				} else
					tdTag=$('<td style="width:20%">'+values.testnameval2+'</td>');				
				trTag.append(tdTag);				
				tdTag=$('<td style="width:30%"/>');			
				if(mode=='print') {
					var lblTag = $('<label/>');
					lblTag.html(values.value2);
					tdTag.append(lblTag);
				} else {
					if(values.displayType2=="text")
						var inputTag=$('<input type="text" id="US_'+ index +'_12" value="'+values.value2+'" tabIndex=' +values.tabIndex2 +'/>');
				    if(values.displayType2=="select")
						var inputTag=$('<select id="US_'+ index +'_12" value="'+values.value2+'" tabIndex=' +values.tabIndex2 +'/>');
						if(values.className2)
							inputTag.attr('class',values.className2);
						tdTag.append(inputTag);
							
				}
				trTag.append(tdTag);	
			}
			tbodyTag.append(trTag);
		});
		tableTag.append(tbodyTag);
		content.append(tableTag);
		return content;
	};
}
function urineLayoutMed(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content=$('<div class="urineLayoutMed"/>');
		$(result.group).each(function(index, group){
			if(group.groupName) {
				var urineGroupRow = $('<div class="one singleheight">&nbsp;</div>');
				var urineRow =  $('<div class="onefirst bold underline">&nbsp;</div>');
				urineRow.empty().html(group.groupName);
				if(group.valueRequired==true) {
					if(mode=="print")
						urineRow.append(group.value);
					else {
						var elementTag = $('<input type="text"/>');
						elementTag.attr('id',group.id);
						elementTag.html(group.value);
						urineRow.append(elementTag);
					}
				}
				urineGroupRow.append(urineRow);
				content.append(urineGroupRow);
			}
			$(group.params).each(function(index, param){
				var urineRow = $('<div class="one singleheight">&nbsp;</div>');
				var urineKey = $('<div class="onefirst">&nbsp;</div>');
				urineKey.empty().html(param.key);
				urineRow.empty().html(urineKey);
				var urineVal = $('<div class="onesecond">&nbsp;</div>');
				if(mode=="print"){
					urineVal.append(param.value);
				}else {
					var elementTag = $('<input type="text"/>');
					elementTag.attr('id',param.id);
					elementTag.html(param.value);
					if(param.className)
						elementTag.addClass(param.className);
					urineVal.append(elementTag);
				}
				urineRow.append(urineVal);
				var urineRef = $('<div class="onesecond">&nbsp;</div>');
				urineRef.append(param.normalvalue);
				urineRow.append(urineRef);
				content.append(urineRow);
			});
		});
		return content;
	};	
}
function waterCultureLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $('<div/>');
		//div for report title starts here
		var headerDiv = $('<div style="text-align:middle;"/>');
		var headerContent=$('<h3 align="center" style="padding:10px 0 10px 0px"/>');
		headerContent.html(result.analysis.resultTitle);
		headerDiv.append(headerContent);
		content.append(headerDiv);
		//div for report title ends here
		var tblDiv = $('<div/>');
		var resultTable = $('<table id="cultureresultTable" style="float:left; margin-left:40px; margin-bottom:10px; width:90%;font-size:92%" />');
		var theadTag = $('<thead/>');
		var trTag=$('<tr />');	
		$(result.analysis.columns).each(function(index, column) {
			var thTag = $('<th />');
			thTag.append(column);
			trTag.append(thTag);
		});
		theadTag.append(trTag);	
		resultTable.append(theadTag);
		var tbodyTag = $('<tbody/>');
		 var	trTag=$('<tr />');		
		$(result.analysis.data).each(function(index,data) {			
			var tdTag = $('<td style="vertical-align:middle; padding-left:5px; line-height:50px;"/>');
			var inputTag;
			if(data.display) {
				if(data.display=='text') {					
					if(mode=="print") {
						tdTag.append(data.value + data.unit);
					} else {
						inputTag=$('<input type="text" style="width:50px;"/>');
						inputTag.attr('id',"txtWaterCSResult");
						inputTag.addClass('middle');
						inputTag.val(data.value);
						tdTag.append(inputTag);
						spanTag=$('<span style="margin-left:10px;"/>');
						spanTag.append(data.unit);
						tdTag.append(spanTag);
					}					
					tdTag.append($('<br/>'));
					tdTag.append($('<br/>'));
					spanTag=$('<span style="margin-left:10px;">Water comes under </span>');
					tdTag.append(spanTag);					
					if(mode=="print") {
						tdTag.append(data.result);
					} else {
						inputTag=$('<input type="text" id="lblwatercsResult"/>');
						inputTag.val(data.result);
						tdTag.append(inputTag);
					}					
				} else if(data.display=="textarea"){
					if(mode=="print") {
						tdTag.append(commonMethodInvoker.nl2br(data.value));
					} else {
						inputTag=$('<textarea style="min-height:150px"/>');
						inputTag.attr('id',"txt"+index);
						inputTag.text(data.value);
						tdTag.append(inputTag);
					}										
				} else {
					tdTag.append(data.value);
				}
			}	
			trTag.append(tdTag);
			tbodyTag.append(trTag);
		});
		resultTable.append(tbodyTag);
		tblDiv.append(resultTable);	
		content.append(tblDiv);		
		return content;
	};	
}
function widal(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content=$('<div />');
		var widaltitle = $('<div class="one bold underline singleheight">&nbsp;</div>');
		widaltitle.append(result.testTitle);
		content.append(widaltitle);
		$(result.values).each(function(index, value){
			var widalRow = $('<div class="one singleheight">&nbsp;</div>');
			var widalKey = $('<div class="onefirst">&nbsp;</div>');
			widalKey.empty().html(value.key);
			widalRow.empty().html(widalKey);
			var widalVal = $('<div class="onesecond">&nbsp;</div>');
			if(mode=="print"){
				if(value.value!="")
					widalVal.empty().html(value.value);
			}else {
				var elementTag = $('<input type="text"/>');
				elementTag.attr('id',value.id);
				elementTag.html(value.value);
				widalVal.empty().html(elementTag);
			}
			widalRow.append(widalVal);
			var widalRef = $('<div class="onesecond">&nbsp;</div>');
			if(value.normalvalue!="")
				widalRef.empty().html(value.normalvalue);
			widalRow.append(widalRef);
			content.append(widalRow);
		});
		return content;
	};	
}
function ResultPrintResponseDTO() {
}
ResultPrintResponseDTO.prototype.getOrderUid = function() {
	return this.orderUid;
}
ResultPrintResponseDTO.prototype.setOrderUid = function(orderUid) {
	this.orderUid=orderUid;
}
ResultPrintResponseDTO.prototype.getResult = function() {
	return this.result;
}
ResultPrintResponseDTO.prototype.setResult = function(result) {
	this.result=result;
}
ResultPrintResponseDTO.prototype.getReportDate = function() {
	return this.reportingDate;
}
ResultPrintResponseDTO.prototype.setReportDate = function(reportingDate) {
	this.reportingDate=reportingDate;
}
ResultPrintResponseDTO.prototype.getReportTime = function() {
	return this.reportingTime;
}
ResultPrintResponseDTO.prototype.setReportTime = function(reportingTime) {
	this.reportingTime=reportingTime;
}
ResultPrintResponseDTO.prototype.isPrinted = function() {
	return this.printed;
}
ResultPrintResponseDTO.prototype.setPrinted = function(printed) {
	this.printed=printed;
}
ResultPrintResponseDTO.prototype.getTestList= function() {
	return this.testList;
}
ResultPrintResponseDTO.prototype.setTestList= function(testList) {
	this.testList=testList;
}
function ResultPreviewProcessor(orderReference) {
	this.orderReference = orderReference;
	this.getOrderUI = function(){
		return this.orderReference;
	}
	//Function creating the Preview of Results
	this.preview = function( honorific,combinedResult,orderId, order,printStatus) {
		self = this;
		var layoutStatus = methodInvoker.getPageSettingByKey('general_layout');
		newLayouts=layoutStatus.visible;
		var reportRoot = $('<div id="resultRoot"/>');
		if(combinedResult.resultList)
			resultList=combinedResult.resultList;
		else	
			resultList=combinedResult;
		$(resultList).each(function (index, result) {
			if(newLayouts==false)
				reportRoot.append(self.getResult(honorific,result,combinedResult,orderId,order,printStatus));
			else
				reportRoot.append(self.generate(honorific,result,combinedResult,orderId,order));
		});	
		return reportRoot;
	}	
	this.getResult = function(honorific,result,combinedResult,orderId,order,printStatus) {
		var orderUI = this.getOrderUI();
		var ReportPageRoot = $('<div class="pageRoot" />');	
		if(printStatus!=true){
		var wrapper = $('<div style="background-color:#D6FCFF;" class="groupResultTable" />');
		}else{
		var wrapper = $('<div class="groupResultTable" />');
		}
		var resultContent = $('<div class="pageResultContent"  />');
		var resultParent = result;
		var parentTable = $('<table  style="width:90%; margin:auto; position:relative;"  />');	
		var remarksDiv = $('<div class="remarks"/>');
		var remarksHeaderTag = $('<p><b>Remarks</b></p>');
		var remarksTag = $('<p/>');	
		//Array used for holding the test/testpackage ids already listed
		var displayedIndex =[];
		var tpCount = 0;
		while((displayedIndex.length+tpCount)<result.testResult.length) {
			var trTag= $('<tr/>');
			var tdTag=$('<td>');
			var tblObj = $('<table width="95%" class="printresultTable"/>');
			var status=true;
			var headerData="";	
			// Iterating through tests and packages			
			$(result.testResult).each(function (resultIndex, singleResult) {
				//check whether the result already displayed
				isExistStatus =commonMethodInvoker.isIndexExists(displayedIndex, resultIndex);					
				if(isExistStatus==false) {
					if(singleResult.IsTstPkgTitle!=undefined) {
						if(singleResult.IsTstPkgTitle==false) {							
							$(singleResult.testResult).each(function (resultIndex1, tpTestResult) {
								if(headerData=="") {											
									if(tpTestResult.testLayout==constants.LAYOUTGENERAL || tpTestResult.testLayout==constants.LAYOUTGENERALMED || tpTestResult.testLayout==constants.LAYOUTGENERALONE || tpTestResult.testLayout==constants.LAYOUTDC) {
										if(status==true) {
											if(tpTestResult.testLayout==constants.LAYOUTDC)
												headerData = new DCLayoutHeader(honorific);
											else
												headerData = new generalLayoutHeader(tpTestResult, honorific);
											tblObj.append(headerData.result);
												status=false;
										}
										if(tpTestResult.testLayout==constants.LAYOUTGENERAL)
											var modalData = new generalLayout(tpTestResult, null, constants.MODEPRINT,honorific, order);	
										else if(tpTestResult.testLayout==constants.LAYOUTGENERALMED)
											var modalData = new generalLayoutMed(tpTestResult, null, constants.MODEPRINT,honorific);	
										else if(tpTestResult.testLayout==constants.LAYOUTDC)
											var modalData = new DCLayout(tpTestResult, null, constants.MODEPRINT,honorific);	
										else
											var modalData = new generalOneLayout(tpTestResult, null, constants.MODEPRINT,honorific);	
										tblObj.append(modalData.result);
									} 	
								} else {
									if(tpTestResult.testLayout==constants.LAYOUTGENERALONE) {						 
										var tmpheaderData = new generalLayoutHeader(tpTestResult, honorific);
										var header1= $('<div/>');
										header1.append(headerData.result);	
										var header2= $('<div/>');
										header2.append(tmpheaderData.result);						
										findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
										if(findHeaderstat==true) {							
											var modalData = new generalOneLayout(tpTestResult, null, constants.MODEPRINT,honorific);
											tblObj.append(modalData.result);
										}
									} else if(tpTestResult.testLayout==constants.LAYOUTGENERAL) {					
										var tmpheaderData = new generalLayoutHeader(tpTestResult,honorific);
										var header1= $('<div/>');
										header1.append(headerData.result);	
										var header2= $('<div/>');
										header2.append(tmpheaderData.result);		
										//findHeaderstat = isEqual(header1.html(),header2.html());
										//if(findHeaderstat==true) {							
										var modalData = new generalLayout(tpTestResult, null, constants.MODEPRINT,honorific, order);
										tblObj.append(modalData.result);
										//}
									}else if(tpTestResult.testLayout==constants.LAYOUTGENERALMED) {						
										var modalData = new generalLayoutMed(tpTestResult, null, constants.MODEPRINT,honorific);
										tblObj.append(modalData.result);
									}else if(tpTestResult.testLayout==constants.LAYOUTDC) {					
										var tmpheaderData = new DCLayoutHeader(honorific);
										var header1= $('<div/>');
										header1.append(headerData.result);	
										var header2= $('<div/>');
										header2.append(tmpheaderData.result);
										findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
										if(findHeaderstat==true) {	
											var modalData = new DCLayout(tpTestResult, null, constants.MODEPRINT,honorific);
											tblObj.append(modalData.result);
										}
									}	
								}
							});
							displayedIndex.push(resultIndex);
						} else 
							tpCount++;
					} else {
						if(headerData=="") {
							if(singleResult.testLayout==constants.LAYOUTGENERAL || singleResult.testLayout==constants.LAYOUTGENERALONE || singleResult.testLayout==constants.LAYOUTDC) {
								if(status==true) {
									if(singleResult.testLayout==constants.LAYOUTDC)
										headerData = new DCLayoutHeader(honorific);
									else
										headerData = new generalLayoutHeader(singleResult, honorific);
									tblObj.append(headerData.result);
									displayedIndex.push(resultIndex);
									status=false;
								}
								var modalData;
								if(singleResult.testLayout==constants.LAYOUTGENERAL)
									modalData = new generalLayout(singleResult, null, constants.MODEPRINT,honorific,order);	
								else if(singleResult.testLayout==constants.LAYOUTDC)
									modalData = new DCLayout(singleResult, null, constants.MODEPRINT,honorific);	
								else if(singleResult.testLayout==constants.LAYOUTGENERALMED){
									modalData = new generalLayoutMed(singleResult, null,constants.MODEPRINT,honorific);		
								} else
									var modalData = new generalOneLayout(singleResult, null, constants.MODEPRINT,honorific);	
								tblObj.append(modalData.result);
							} else if(singleResult.testLayout==constants.LAYOUTGENERALMED){
									var modalData = new generalLayoutMed(singleResult, null, constants.MODEPRINT,honorific);	
									tblObj.append(modalData.result);
									headerData = modalData.result;
									displayedIndex.push(resultIndex);		
							} else if(singleResult.testLayout==constants.LAYOUTCULTURE || singleResult.testLayout=="CultureReport" ) {
								var modalData = new cultureLayout(honorific,singleResult,null, constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTWATERCULTURE){
								var modalData = new waterCultureLayout(honorific,singleResult,null, constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTHAEMOGRAM){
								var modalData = new haemogramLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTHAEMOGRAMESR){
								var modalData = new haemogramESRLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTAMINOACIDOGRAM){
								var modalData = new aminoacidLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTOSMOTIC){
								var modalData = new osmoticLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTSTOOLANALYSIS  || singleResult.testLayout=="StoolAnalysis"){
								var modalData = new stoolLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTENA){
								var modalData = new enaProfile(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							}  else if(singleResult.testLayout==constants.LAYOUTSTONEANALYSIS  || singleResult.testLayout=="StoneAnalysis"){
								var modalData = new stone(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTURINE){
								var modalData = new UrineLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTAFB){
								var modalData = new AFBLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTCD){
								var modalData = new CDLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTGTT){
								var modalData = new GTTLayout(honorific,singleResult, null, constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTPERIPHERAL){
								var modalData = new PeripheralLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTPT){
								var modalData = new PTLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTAPPT){
								var modalData = new APPTLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTHISTOPATH){
								var modalData = new HistoPathLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTLIPID){
								var modalData = new LipidLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTLFT){
								var modalData = new LiverLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTDC){
								var modalData = new DCLayout(singleResult,null, constants.MODEPRINT,honorific);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTANA){
								var modalData = new ANALayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTBETAHCG) {	
								tblObj.attr('style',"border:none;");
								var modalData = new generalLayout(singleResult, null, constants.MODEPRINT,honorific,order);
								tblObj.append(modalData.result);
								displayedIndex.push(resultIndex);
								tblObj.append('<br/>');
								headerData = "";									
								return false;
							} else if(singleResult.testLayout==constants.LAYOUTSEMEN || singleResult.testLayout=="SemenLayout"){
								var modalData = new SemenLayout(honorific,singleResult, null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTGCT){
								headerData = new DCLayoutHeader(honorific);
								tblObj.append(headerData.result);
								var modalData = new NVGCTLayout(singleResult, null,constants.MODEPRINT,honorific);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="WidalLayoutMed"){
								var modalData = new widal(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="UrineLayoutMed") {
								var modalData = new urineLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							}  else if(singleResult.testLayout=="FoodAllergy") {
								var modalData = new foodAllergy(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							}  else if(singleResult.testLayout=="ANAIFALayout") {
								var modalData = new anaIfa(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="DCLayoutMed") {
								var modalData = new DCLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="LipidLayoutMed"){
								var modalData = new lipidLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="LFTLayoutMed") {
								var modalData = new liverLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="HaemogramLayoutMed") {
								var modalData = new haemogramLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else {
								var modalData = new layout1(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;
							}	
						} else {
						    if(singleResult.testLayout==constants.LAYOUTGENERALONE) {						 
								var tmpheaderData = new generalLayoutHeader(singleResult,honorific);
								var header1= $('<div/>');
								header1.append(headerData.result);	
								var header2= $('<div/>');
								header2.append(tmpheaderData.result);						
								findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
								if(findHeaderstat==true) {							
									var modalData = new generalOneLayout(singleResult, null, constants.MODEPRINT,honorific);
									tblObj.append(modalData.result);
									displayedIndex.push(resultIndex);
								}
							} else if(singleResult.testLayout==constants.LAYOUTGENERAL) {					
								var tmpheaderData = new generalLayoutHeader(singleResult,honorific);
								var header1= $('<div/>');
								header1.append(headerData.result);	
								var header2= $('<div/>');
								header2.append(tmpheaderData.result);						
								findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
								if(findHeaderstat==true) {							
									var modalData = new generalLayout(singleResult, null, constants.MODEPRINT,honorific,order);
									tblObj.append(modalData.result);
									displayedIndex.push(resultIndex);
								}
							} else if(singleResult.testLayout==constants.LAYOUTDC) {					
								var tmpheaderData = new DCLayoutHeader(honorific);
								var header1= $('<div/>');
								header1.append(headerData.result);	
								var header2= $('<div/>');
								header2.append(tmpheaderData.result);
								findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
								if(findHeaderstat==true) {	
									var modalData = new DCLayout(singleResult, null, constants.MODEPRINT,honorific);
									tblObj.append(modalData.result);
									displayedIndex.push(resultIndex);
								}
							} else if(singleResult.testLayout==constants.LAYOUTGENERALMED){
								var modalData = new generalLayoutMed(singleResult, null, constants.MODEPRINT,honorific);									
								tblObj.append(modalData.result);
								displayedIndex.push(resultIndex);
							}	
						}
					}	
				}		
			});	
			tdTag.append(tblObj);
			trTag.append(tdTag);	
			parentTable.append(trTag);	
		}
		if(tpCount>0) {
			$(result.testResult).each(function (resultIndex, singleResult) {
				if(singleResult.IsTstPkgTitle!=undefined) {
					if(singleResult.IsTstPkgTitle==true) {
						trTag= $('<tr/>');
						tdTag=$('<td class="titleClass" />');
						tdTag.html(singleResult.testName);
						trTag.append(tdTag);
						parentTable.append(trTag);
						trTag= $('<tr/>');
						tdTag=$('<td />');					
						var tblObj = $('<table width="100%" class="printresultTable"/>');
						var status=true;
						headerData = "";
						$(singleResult.testResult).each(function (resultIndex, tpTestResult) {
							if(headerData=="") {											
								if(tpTestResult.testLayout==constants.LAYOUTGENERAL || tpTestResult.testLayout==constants.LAYOUTGENERALMED || tpTestResult.testLayout==constants.LAYOUTGENERALONE || tpTestResult.testLayout==constants.LAYOUTDC) {

									if(status==true) {
										if(tpTestResult.testLayout==constants.LAYOUTDC)
											headerData = new DCLayoutHeader(honorific);
										else
											headerData = new generalLayoutHeader(tpTestResult,honorific);
										tblObj.append(headerData.result);
											status=false;
									}
									if(tpTestResult.testLayout==constants.LAYOUTGENERAL)
										var modalData = new generalLayout(tpTestResult, null, constants.MODEPRINT,honorific,order);	
									else if(tpTestResult.testLayout==constants.LAYOUTGENERALMED)
										var modalData = new generalLayoutMed(tpTestResult, null, constants.MODEPRINT,honorific);				
									else if(tpTestResult.testLayout==constants.LAYOUTDC)
										var modalData = new DCLayout(tpTestResult, null, constants.MODEPRINT,honorific);	
									else
										var modalData = new generalOneLayout(tpTestResult, null, constants.MODEPRINT,honorific);	
									tblObj.append(modalData.result);
								} 	
							} else {
								if(tpTestResult.testLayout==constants.LAYOUTGENERALONE) {						 
									var tmpheaderData = new generalLayoutHeader(tpTestResult,honorific);
									var header1= $('<div/>');
									header1.append(headerData.result);	
									var header2= $('<div/>');
									header2.append(tmpheaderData.result);						
									findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
									if(findHeaderstat==true) {							
										var modalData = new generalOneLayout(tpTestResult, null, constants.MODEPRINT,honorific);
										tblObj.append(modalData.result); 
									}
								} else if(tpTestResult.testLayout==constants.LAYOUTGENERAL) {					
									var tmpheaderData = new generalLayoutHeader(tpTestResult,honorific);
									var header1= $('<div/>');
									header1.append(headerData.result);	
									var header2= $('<div/>');
									header2.append(tmpheaderData.result);						
									findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
									if(findHeaderstat==true) {							
										var modalData = new generalLayout(tpTestResult, null, constants.MODEPRINT,honorific,order);
										tblObj.append(modalData.result);
									}
								}else if(tpTestResult.testLayout==constants.LAYOUTGENERALMED) {					
									var modalData = new generalLayoutMed(tpTestResult, null, constants.MODEPRINT,honorific);
									tblObj.append(modalData.result);
								}else if(tpTestResult.testLayout==constants.LAYOUTDC) {					
									var tmpheaderData = new DCLayoutHeader(honorific);
									var header1= $('<div/>');
									header1.append(headerData.result);	
									var header2= $('<div/>');
									header2.append(tmpheaderData.result);
									findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
									if(findHeaderstat==true) {	
										var modalData = new DCLayout(tpTestResult, null, constants.MODEPRINT,honorific);
										tblObj.append(modalData.result);
									}
								}	
							}
						});
						tdTag.append(tblObj);
						trTag.append(tdTag);	
						parentTable.append(trTag);	
					}
				}
			});
		}
		//remarks section
		if(!result.testName) {				
				$(result.testResult).each(function (resultIndex, singleResult) {
						var remark=singleResult.remarks;
						if(remark!=null && remark.trim()!="") {
							var pTag = $('<p/>');	
							var remarkInfo = commonMethodInvoker.nl2br(remark);
							remarkInfo  = remarkInfo.replaceAll(" ", "&nbsp;");
							pTag.append(remarkInfo);
							remarksTag.append(pTag);
						}
				});
		} else {
			var remark=result.remarks;
			if(remark!=null && remark.trim()!="") {
				var pTag = $('<p/>');	
				var remarkInfo = commonMethodInvoker.nl2br(remark);
				remarkInfo  = remarkInfo.replaceAll(" ", "&nbsp;");
				pTag.append(remarkInfo);
				remarksTag.append(pTag);
			}
		}
		if(remarksTag.html().trim().length!=0) {
			remarksDiv.append(remarksHeaderTag);
			remarksDiv.append(remarksTag.html());
		}
		resultContent.append(parentTable);
		resultContent.append(remarksDiv);
		var header = new ResultHeaderProcessor(orderUI);
		var repHeader = header.create(resultParent,combinedResult,orderId,honorific,printStatus);
		wrapper.append(repHeader);
		var contentset =methodInvoker.getPageSettingByKey('content');
		var originalHeight = parseInt(contentset.height,10) || 0;
		var marginHeight = parseInt(contentset.marginTop,10) || 0;
		var totalHeight = originalHeight-marginHeight;
		resultContent.css("height",totalHeight + "mm");
		resultContent.css("width",contentset.width+"mm");
		resultContent.css("margin-top",contentset.marginTop+"mm");
		resultContent.css("font-size",contentset.fontSize + "px");
		wrapper.append(resultContent);
		var headerset =methodInvoker.getPageSettingByKey('header');
		var footerset =methodInvoker.getPageSettingByKey('footer');
		var footer = new ResultFooterProcessor(orderUI);
		var repFooter = footer.create(resultParent,combinedResult,orderId);
		wrapper.append(repFooter);
		totalHeight=parseInt(headerset.height) + parseInt(footerset.height) +parseInt(contentset.height);
		wrapper.css("height","297mm");
		//wrapper.css("height",totalHeight+"mm");
		wrapper.css("width",contentset.width+"mm");
		ReportPageRoot.append(wrapper);
		return ReportPageRoot;
	}
	this.generate = function(honorific,result,combinedResult,orderId,order) {
		var orderUI = this.getOrderUI();
		ReportPageRoot = $('<div class="pageRoot"/>');		
		var wrapper = $('<div class="groupResultTable" />');
		var resultContent = $('<div class="pageResultContent"  />');
		var resultParent = result;
		var parentTable = $('<div  style="width:100%;"  />');	
		var remarksDiv = $('<div class="remarks"/>');
		var remarksHeaderTag = $('<p class="nomargin"><b>Remarks</b></p>');
		var remarksTag = $('<p/>');	
		remarksTag.addClass('nomargin');
		var tblObj = $('<div class="printresultTable" style="float:left;width:100%"/>');
		var departmentList = [];
		$(result.testResult).each(function (resultIndex, singleResult) {
			departmentName=null;
			if(singleResult.departmentName)
				departmentName = singleResult.departmentName;
			else
				departmentName=" ";
			if(!commonMethodInvoker.isIndexExists(departmentList,departmentName)){
				departmentList.push(departmentName);
				if(departmentName!=null){
					var tbody=$('<div class="one singleheight "/>');
					var tdTag = $('<div class="onefirst">&nbsp;</div>');
					tbody.empty().html(tdTag);
					tdTag = $('<div class="onesecond bold underline">&nbsp;</div>');
					tdTag.empty().html(singleResult.departmentName);
					tbody.append(tdTag);
					tdTag = $('<div class="onesecond">&nbsp;</div>');
					tbody.append(tdTag);
					tblObj.append(tbody);
					parentTable.append(tblObj);
				}	
			}
			if(singleResult.testResult) {
				if(singleResult.IsTstPkgTitle==true){
					var tbody=$('<div class="one underline bold"/>');
					tbody.append(singleResult.testName);
					tblObj.append(tbody);
				}
				$(singleResult.testResult).each(function (resultIndex1, subResult) {
					var modalData = self.generateLayoutData(subResult, honorific, order);
					tblObj.append(modalData.result);
				});
			} else {
				var modalData = self.generateLayoutData(singleResult, honorific, order);
				tblObj.append(modalData.result);
			}
		});	
		//remarks section
		if(!result.testName) {			
			$(result.testResult).each(function (resultIndex, singleResult) {
				var remark=singleResult.remarks;
				if(remark!=null && remark.trim()!="") {
					var pTag = $('<p />');	
					pTag.addClass('nomargin');
					var remarkInfo = commonMethodInvoker.nl2br(remark);
					remarkInfo  = remarkInfo.replaceAll(" ", "&nbsp;");
					pTag.append(remarkInfo);
					remarksTag.append(pTag);
				}
			});
		} else {
			var remark=result.remarks;
			if(remark!=null && remark.trim()!="") {
				var pTag = $('<p/>');	
				pTag.addClass('nomargin');
				var remarkInfo = commonMethodInvoker.nl2br(remark);
				remarkInfo  = remarkInfo.replaceAll(" ", "&nbsp;");
				pTag.append(remarkInfo);
				remarksTag.append(pTag);
			}
		}
		if(remarksTag.html().trim().length!=0) {
			remarksDiv.append(remarksHeaderTag);
			remarksDiv.append(remarksTag.html());
		}
		remarksDiv.addClass("one");
		remarksDiv.addClass("columnleftalign");
		tblObj.append(remarksDiv);
		parentTable.append(tblObj);
		resultContent.append(parentTable);
		var header = new ResultHeaderProcessor(orderUI);
		var repHeader = header.create(resultParent,combinedResult,orderId,honorific,printStatus);
		var repHeader = header.generate(resultParent,combinedResult,orderId,honorific);
		wrapper.append(repHeader);
		var contentset =methodInvoker.getPageSettingByKey('content');
		var headerset =methodInvoker.getPageSettingByKey('header');
		var footerset =methodInvoker.getPageSettingByKey('footer');
		var originalHeight = parseInt(contentset.height,10) || 0;
		var marginHeight = parseInt(contentset.marginTop,10) || 0;
		var totalHeight = originalHeight-marginHeight;
		resultContent.css("height",totalHeight + "mm");
		resultContent.css("width",contentset.width+"mm");
		resultContent.css("margin-top",contentset.marginTop+"mm");
		resultContent.css("font-size",contentset.fontSize + "px");
		resultContent.css("margin-left",headerset.marginLeft + "mm");
		wrapper.append(resultContent);
		var footer = new ResultFooterProcessor(orderUI);
		var repFooter = footer.generalFooter();
		wrapper.append(repFooter);
		totalHeight=parseInt(headerset.height) + parseInt(footerset.height) +parseInt(contentset.height);
		wrapper.css("height",totalHeight+"mm");
		//wrapper.css("width",contentset.width+"mm");
		ReportPageRoot.append(wrapper);
		return ReportPageRoot;
	}
	this.generateLayoutData =function(singleResult,honorific,order) {
		if(singleResult.testLayout==constants.LAYOUTGENERAL)
			var modalData = new generalLayout(singleResult, null, constants.MODEPRINT,honorific,order);	
		else if(singleResult.testLayout==constants.LAYOUTDC)
			var modalData = new DCLayout(singleResult, null, constants.MODEPRINT,honorific);	
		else if(singleResult.testLayout==constants.LAYOUTGENERALONE)
			var modalData = new generalOneLayout(singleResult, null, constants.MODEPRINT,honorific);	
		else if(singleResult.testLayout==constants.LAYOUTGENERALMED)
			var modalData = new generalLayoutMed(singleResult, null, constants.MODEPRINT,honorific);									
		else if(singleResult.testLayout==constants.LAYOUTCULTURE || singleResult.testLayout=="CultureReport" )
			var modalData = new cultureLayout(honorific,singleResult,null, constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTWATERCULTURE)
			var modalData = new waterCultureLayout(honorific,singleResult,null, constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTHAEMOGRAM)
			var modalData = new haemogramLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTHAEMOGRAMESR)
			var modalData = new haemogramESRLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTAMINOACIDOGRAM)
			var modalData = new aminoacidLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTOSMOTIC)
			var modalData = new osmoticLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTSTOOLANALYSIS  || singleResult.testLayout=="StoolAnalysis")
			var modalData = new stoolLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTENA)
			var modalData = new enaProfile(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTSTONEANALYSIS  || singleResult.testLayout=="StoneAnalysis")
			var modalData = new stone(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTURINE)
			var modalData = new UrineLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTAFB)
			var modalData = new AFBLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTCD)
			var modalData = new CDLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTGTT)
			var modalData = new GTTLayout(honorific,singleResult, null, constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTPERIPHERAL)
			var modalData = new PeripheralLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTPT)
			var modalData = new PTLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTAPPT)
			var modalData = new APPTLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTHISTOPATH)
			var modalData = new HistoPathLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTLIPID)
			var modalData = new LipidLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTLFT)
			var modalData = new LiverLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTDC)
			var modalData = new DCLayout(singleResult,null, constants.MODEPRINT,honorific);
		else if(singleResult.testLayout==constants.LAYOUTANA)
			var modalData = new ANALayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTBETAHCG)
			var modalData = new generalLayout(singleResult, null, constants.MODEPRINT,honorific,order);
		else if(singleResult.testLayout==constants.LAYOUTSEMEN || singleResult.testLayout=="SemenLayout")
			var modalData = new SemenLayout(honorific,singleResult, null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTGCT)
			var modalData = new NVGCTLayout(singleResult, null,constants.MODEPRINT,honorific);
		else if(singleResult.testLayout=="WidalLayoutMed")
			var modalData = new widal(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="UrineLayoutMed")
			var modalData = new urineLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="LipidLayoutMed")
			var modalData = new lipidLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="DCLayoutMed")
			var modalData = new DCLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="FoodAllergy") 
			var modalData = new foodAllergy(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="ANAIFALayout") 
			var modalData = new anaIfa(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="LFTLayoutMed") 
			var modalData = new liverLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="HaemogramLayoutMed") 
			var modalData = new haemogramLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
		else 
			var modalData = new layout1(honorific,singleResult,null,constants.MODEPRINT);
		return modalData;
	}
}
function ResultFooterProcessor(orderUI) {
	this.create = function(result,combinedResult,orderId) {
		var self=this;
		var footer = $('<div class="printReportFooter " />');
		var headerSetting =methodInvoker.getPageSettingByKey('footer');
		var signSetting = methodInvoker.getPageSettingByKey('USIGN');
		footer.css('width', headerSetting.width+ "mm");
		footer.css('height',headerSetting.height+ "mm");
		var linehgt = headerSetting.height/5;
		if(headerSetting.visible==false) {
			footer.append('&nbsp;');
		} else {
			var tblFooter = $('<table style="width:100%; height:100%;vertical-align:bottom; float:left;"/>');
			trTag = $('<tr/>');
			tdTag = $('<td style="vertical-align:top"/>');
			var noOfUsers = result.verifiedUsers.length; 
			$(result.verifiedUsers).each(function(index, user) {	
				var userInfo=query.viewUser(user.userId, orderUI.getBranchId());
				if(noOfUsers==2) {
					if(index==0){
						divTag = $('<div style="width:46%;float:left;margin-left:2%" />');
					}else{
						divTag = $('<div style="width:46%;float:right;margin-right:2%;text-align:right" />');
					}
					var signTag=$('<div>&nbsp;</div>');
					if(userInfo.signature && signSetting.visible==true) {
						signTag.html('<img src=' + userInfo.signature + ' width="200" height="40" style="margin-left:5%;"/>');
					}	
					divTag.append(signTag);
					var childTag = $('<div/>');
					childTag.html(user.name);
					divTag.append(childTag);
					if(user.designation) {
						childTag = $('<div/>');
						childTag.html(user.designation);
						divTag.append(childTag);
					}
					divTag.attr('height',(linehgt*3)+"mm");	
					tdTag.append(divTag);
				}else {
					if(index==0)
						divTag = $('<div style="min-width:31%;float:left;margin-left:1%;text-align:left" />')
					else if(index==1)
						divTag = $('<div style="min-width:31%;float:left;margin-left:1%;text-align:center" />')
					else
						divTag = $('<div style="min-width:31%;float:right;margin-right:1%;text-align:right" />')

					var childTag = $('<div/>');
					var signTag=$('<div>&nbsp;</div>');
					if(userInfo.signature  && signSetting.visible==true) {
						signTag.html('<img src=' + userInfo.signature + ' width="200" height="40" style="margin-left:5%;"/>');
					}
					divTag.append(signTag);
					childTag.html(user.name);
					divTag.append(childTag);
					if(user.designation) {
						childTag = $('<div/>');
						childTag.html(user.designation);
						divTag.append(childTag);
					}	
					divTag.attr('height',(linehgt*3)+"mm");
					tdTag.append(divTag);
					if(index==2)
						return false;
					}
			});
			var f1l = methodInvoker.getPageSettingByKey('F1L');
			var f1m = methodInvoker.getPageSettingByKey('F1M');
			var f1r = methodInvoker.getPageSettingByKey('F1R');
			var f2l = methodInvoker.getPageSettingByKey('F2L');
			var f2m = methodInvoker.getPageSettingByKey('F2M');
			var f2r = methodInvoker.getPageSettingByKey('F2R');
			
			if(f1l.label.trim()=="" && f1m.label.trim()=="" && f1r.label.trim()==""){

			} else {
				var f1Div  = $('<div class="left oneHeader" style="clear:both; margin-left:0.5%;text-align:left"/>');
				f1Div.attr('height',linehgt+"mm");
				var f1 = $('<div class="onethird">&nbsp;</div>')
				if(f1l.label.trim()!=""){
					f1.append(f1l.label);
					self.setStyles(f1, f1l);
				}
				f1Div.append(f1);
				var f2 = $('<div class="onethird">&nbsp;</div>')
				if(f1m.label.trim()!=""){
					f2.append(f1m.label);
					self.setStyles(f2, f1m);
				}
				f1Div.append(f2);
				var f3 = $('<div class="onethird ">&nbsp;</div>')
				if(f1r.label.trim()!=""){
					f3.append(f1r.label);
					self.setStyles(f3, f1r);
				}
				f1Div.append(f3);
				tdTag.append(f1Div);
			}
			if(f2l.label.trim()=="" && f2m.label.trim()=="" && f2r.label.trim()==""){

			} else {
				var f2Div  = $('<div class="left oneHeader" style="clear:both;margin-left:0.5%;text-align:left"/>');
				f2Div.attr('height',linehgt+"mm");
				var f1 = $('<div class="onethird">&nbsp;</div>')
				if(f2l.label.trim()!=""){
					f1.append(f2l.label);
					self.setStyles(f1, f2l);
				}
				f2Div.append(f1);
				var f2 = $('<div class="onethird">&nbsp;</div>')
				if(f2m.label.trim()!=""){
					f2.append(f2m.label);
					self.setStyles(f2, f2m);
				}
				f2Div.append(f2);
				var f3 = $('<div class="onethird ">&nbsp;</div>')
				if(f2r.label.trim()!=""){
					f3.append(f2r.label);
					self.setStyles(f3, f2r);
				}
				f2Div.append(f3);
				tdTag.append(f2Div);
			}
			trTag.append(tdTag);
			tblFooter.append(trTag);
			footer.append(tblFooter);
		}
		return footer;
	}
	this.setStyles=function(column, settings){
		if(settings.bold==true)
			column.css("font-weight","bold");
		if(settings.italics==true)
			column.css("font-style","italic");
		if(settings.underline==true)
			column.css("text-decoration","underline");
		if(settings.fontSize!="")
			column.css("font-size",settings.fontSize+"px");
		if(settings.width!="")
			column.css("width",settings.width+"mm");
		if(settings.height!="")
			column.css("height",settings.height+"mm");
	}
	this.generalFooter = function() {
		var self=this;
		var footer = $('<div class="printReportFooter " style="vertical-align:bottom;"/>');
		var headerSetting =methodInvoker.getPageSettingByKey('footer');
		footer.css('width', headerSetting.width+ "mm");
		footer.css('height',headerSetting.height+ "mm");
		var f1l = methodInvoker.getPageSettingByKey('F1L');
		var f1m = methodInvoker.getPageSettingByKey('F1M');
		var f1r = methodInvoker.getPageSettingByKey('F1R');
		var f2l = methodInvoker.getPageSettingByKey('F2L');
		var f2m = methodInvoker.getPageSettingByKey('F2M');
		var f2r = methodInvoker.getPageSettingByKey('F2R');
		var f1Div  = $('<div class="left"/>');
		if(f1l.label.trim()=="" && f1m.label.trim()=="" && f1r.label.trim()){

		} else {
			var f1 = $('<div class="onethird">&nbsp;</div>')
			if(f1l.label.trim()!=""){
				f1.append(f1l.label);
				self.setStyles(f1, f1l);
			}
			f1Div.append(f1);
			var f2 = $('<div class="onethird">&nbsp;</div>')
			if(f1m.label.trim()!=""){
				f2.append(f1m.label);
				self.setStyles(f2, f1m);
			}
			f1Div.append(f2);
			var f3 = $('<div class="onethird ">&nbsp;</div>')
			if(f1r.label.trim()!=""){
				f3.append(f1r.label);
				self.setStyles(f3, f1r);
			}
			f1Div.append(f3);
		}
		footer.append(f1Div);
		var f2Div  = $('<div class="left"/>');
		if(f2l.label.trim()=="" && f2m.label.trim()=="" && f2r.label.trim()){

		} else {
			var f1 = $('<div class="onethird">&nbsp;</div>')
			if(f2l.label.trim()!=""){
				f1.append(f2l.label);
				self.setStyles(f1, f2l);
			}
			f2Div.append(f1);
			var f2 = $('<div class="onethird">&nbsp;</div>')
			if(f2m.label.trim()!=""){
				f2.append(f2m.label);
				self.setStyles(f2, f2m);
			}
			f2Div.append(f2);
			var f3 = $('<div class="onethird ">&nbsp;</div>')
			if(f2r.label.trim()!=""){
				f3.append(f2r.label);
				self.setStyles(f3, f2r);
			}
			f2Div.append(f3);
		}
		footer.append(f2Div);
		return footer;
	}
}
function ResultHeaderProcessor(orderUI) {
	this.orderUI = orderUI;
	
	this.getOrderUI = function(){
		return this.orderUI;
	}
	this.create = function(result,combinedResult,orderId,honorific,status) {
		/*Set the Header Properties*/
		var self=this;
		var headerSetting =methodInvoker.getPageSettingByKey('header');
		var header = $("<div class='resultHeader' />");
		header.css('width', (headerSetting.width - headerSetting.marginLeft)+ "mm");
		header.css('height', headerSetting.height+ "mm");
		header.css('margin-left',headerSetting.marginLeft+ "mm");
		if(parseInt(headerSetting.fontSize)!=0)
			header.css('font-size',headerSetting.fontSize + "px")
		if(parseInt(headerSetting.marginTop)!=0){
			var newDiv = $("<div id='header'/>");
			if(status!=true)
			{
				var imgTag=$('<img src="'+headerSetting.bgImage+'"  style="height:30mm; width:216mm;">');
				newDiv.append(imgTag);
			}
			newDiv.css("height",headerSetting.marginTop+"mm");
			newDiv.css("width",headerSetting.width+"mm");
			header.append(newDiv);
		}
		/*Create the Header Sections*/
		var headerTable = $("<table cellpadding=0 cellspacing=0/>");
		//headerTable.css("height",(headerSetting.height - headerSetting.marginTop));
		var l1c = methodInvoker.getPageSettingByKey('L1C');
		var l1v = methodInvoker.getPageSettingByKey('L1V');
		var m1c = methodInvoker.getPageSettingByKey('M1C');
		var m1v = methodInvoker.getPageSettingByKey('M1V');
		var r1c = methodInvoker.getPageSettingByKey('R1C');
		var r1v = methodInvoker.getPageSettingByKey('R1V');
		if(l1v.label!="select" || m1v.label!="select" || r1v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l1c,l1v,m1c,m1v,r1c,r1v));	
		}
		var l2c = methodInvoker.getPageSettingByKey('L2C');
		var l2v = methodInvoker.getPageSettingByKey('L2V');
		var m2c = methodInvoker.getPageSettingByKey('M2C');
		var m2v = methodInvoker.getPageSettingByKey('M2V');
		var r2c = methodInvoker.getPageSettingByKey('R2C');
		var r2v = methodInvoker.getPageSettingByKey('R2V');
		if(l2v.label!="select" || m2v.label!="select" || r2v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l2c,l2v,m2c,m2v,r2c,r2v));	
		}
		var l3c = methodInvoker.getPageSettingByKey('L3C');
		var l3v = methodInvoker.getPageSettingByKey('L3V');
		var m3c = methodInvoker.getPageSettingByKey('M3C');
		var m3v = methodInvoker.getPageSettingByKey('M3V');
		var r3c = methodInvoker.getPageSettingByKey('R3C');
		var r3v = methodInvoker.getPageSettingByKey('R3V');
		if(l3v.label!="select" || m3v.label!="select" || r3v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l3c,l3v,m3c,m3v,r3c,r3v));	
		}
		var l4c = methodInvoker.getPageSettingByKey('L4C');
		var l4v = methodInvoker.getPageSettingByKey('L4V');
		var m4c = methodInvoker.getPageSettingByKey('M4C');
		var m4v = methodInvoker.getPageSettingByKey('M4V');
		var r4c = methodInvoker.getPageSettingByKey('R4C');
		var r4v = methodInvoker.getPageSettingByKey('R4V');
		if(l4v.label!="select" || m4v.label!="select" || r4v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l4c,l4v,m4c,m4v,r4c,r4v));	
		}
		var l5c = methodInvoker.getPageSettingByKey('L5C');
		var l5v = methodInvoker.getPageSettingByKey('L5V');
		var m5c = methodInvoker.getPageSettingByKey('M5C');
		var m5v = methodInvoker.getPageSettingByKey('M5V');
		var r5c = methodInvoker.getPageSettingByKey('R5C');
		var r5v = methodInvoker.getPageSettingByKey('R5V');
		if(l5v.label!="select" || m5v.label!="select" || r5v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l5c,l5v,m5c,m5v,r5c,r5v));	
		}
		var l6c = methodInvoker.getPageSettingByKey('L6C');
		var l6v = methodInvoker.getPageSettingByKey('L6V');
		var m6c = methodInvoker.getPageSettingByKey('M6C');
		var m6v = methodInvoker.getPageSettingByKey('M6V');
		var r6c = methodInvoker.getPageSettingByKey('R6C');
		var r6v = methodInvoker.getPageSettingByKey('R6V');
		if(l6v.label!="select" || m6v.label!="select" || r6v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l6c,l6v,m6c,m6v,r6c,r6v));	
		}
		header.append(headerTable);
		return header;
	}
	this.setStyles=function(column, settings){
		if(settings.bold==true)
			column.css("font-weight","bold");
		if(settings.italics==true)
			column.css("font-style","italics");
		if(settings.underline==true)
			column.css("text-decoration","underline");
		if(settings.fontSize!="")
			column.css("font-size",settings.fontSize+"px");
		if(settings.width!="")
			column.css("width",settings.width+"mm");
		if(settings.height!="")
			column.css("height",settings.height+"mm");
	}
	this.setStylesWithoutMeasure=function(column, settings){
		if(settings.bold==true)
			column.css("font-weight","bold");
		if(settings.italics==true)
			column.css("font-style","italics");
		if(settings.underline==true)
			column.css("text-decoration","underline");
		if(settings.fontSize!="")
			column.css("font-size",settings.fontSize+"px");
	}
	this.generateRow = function(result, combinedResult, orderId, honorific, lnc, lnv, mnc, mnv, rnc, rnv){
		var self=this;
		var row = $("<tr/>");

		var columnSpan=1;

		if(mnc.label=='' && mnv.label=='blank')
			columnSpan+=2;

		if(columnSpan!=1){
			var lnccolumn = $("<td>&nbsp;</td>");
			self.setStyles(lnccolumn, lnc);
			if(lnc.visible!=false && lnc.label!="") 
				lnccolumn.empty().html(lnc.label);
			row.append(lnccolumn);
			var lnvcolumn = $("<td colspan='3'/>");
			self.setStyles(lnvcolumn, lnv);
			self.setStylesWithoutMeasure(lnvcolumn, lnv);
			if(lnv.visible!=false && (lnv.label!="blank" || lnv.label!="select" )){
				var refName = self.getValue(result, combinedResult, orderId, honorific,lnv.label);
				if(refName!=undefined)
					if(refName.length>50)
						lnvcolumn.css('font-size','13px');
				lnvcolumn.empty().html(refName);
			}
			row.append(lnvcolumn);
		} else {
			var lnccolumn = $("<td>&nbsp;</td>");
			self.setStyles(lnccolumn, lnc);
			if(lnc.visible!=false && lnc.label!="") 
				lnccolumn.empty().html(lnc.label);
			row.append(lnccolumn);
			var lnvcolumn = $("<td>&nbsp;</td>");
			self.setStyles(lnvcolumn, lnv);
			if(lnv.visible!=false && (lnv.label!="blank" || lnv.label!="select" ))
				lnvcolumn.empty().html(self.getValue(result, combinedResult, orderId, honorific,lnv.label));
			row.append(lnvcolumn);
			var mnccolumn = $("<td>&nbsp;</td>");
			self.setStyles(mnccolumn, mnc);
			if(mnc.visible!=false && mnc.label!="") 
				mnccolumn.empty().html(mnc.label);
			row.append(mnccolumn);
			var mnvcolumn = $("<td>&nbsp;</td>");
			self.setStyles(mnvcolumn, mnv);
			if(mnv.visible!=false && (mnv.label!="blank" || mnv.label!="select" ))
				mnvcolumn.empty().html(self.getValue(result, combinedResult, orderId, honorific,mnv.label));
			row.append(mnvcolumn);
		}
		var rnccolumn = $("<td>&nbsp;</td>");
		self.setStyles(rnccolumn, rnc);
		if(rnc.visible!=false && rnc.label!="") 
			rnccolumn.empty().html(rnc.label);
		row.append(rnccolumn);
		var rnvcolumn = $("<td>&nbsp;</td>");
		self.setStyles(rnvcolumn, rnv);
		if(rnv.visible!=false && (rnv.label!="blank" || rnv.label!="select" ))
			rnvcolumn.empty().html(self.getValue(result, combinedResult, orderId, honorific,rnv.label));
		row.append(rnvcolumn);

		return row;	
	}
	this.getValue = function(result, combinedResult, orderId, honorific, key ) {
		if(key=='name')
			return combinedResult.resultHeader.patientName;
		if(key=='age') {
			curAge = "";
			if(combinedResult.resultHeader.age) {
				var age = combinedResult.resultHeader.age;
				var tempAge = age.split("-");
				if(tempAge.length>1){
					curYear = parseFloat(tempAge[0],10)||0;
					curMonth = parseFloat(tempAge[1],10)||0;
					curDay = parseFloat(tempAge[2],10)||0;
					if(curYear!=0)
						curAge = curYear + "Y ";
					if(curMonth!=0)
						curAge = curAge + curMonth + "M ";
					if(curDay!=0)
						curAge = curAge + curDay + "D";	
				} else
					curAge = tempAge[0];
			}	
			return curAge;	
		}	
		if(key=='orderId')
			return combinedResult.resultHeader.orderId;
		if(key=='referredBy')
			return combinedResult.resultHeader.referral;
		if(key=='sex')
			return combinedResult.resultHeader.gender;
		if(key=='collectedAt'){
			if(combinedResult.resultHeader.collectedAt) {
				if(combinedResult.resultHeader.collectedAt!=null && combinedResult.resultHeader.collectedAt!='null')
					return combinedResult.resultHeader.collectedAt;
			}
			return "";
		}
		if(key=='collectionDate'){
			if(combinedResult.resultHeader.date)
				return combinedResult.resultHeader.date;
			return "";
		}
		if(key=='collectionTime'){
			if(combinedResult.resultHeader.time)
				return combinedResult.resultHeader.time;
			return "";
		}
		if(key=='reportingDate') {
			if(result!=null)
				if(result.reportDate)
					return result.reportDate;
			return "";
		}
		if(key=='reportingTime') {
			if(result!=null)
				if(result.reportTime)
					return result.reportTime;
			return "";
		}
		if(key=='specimen'){
			//var resultSpecimenProcessor = new ResultSpecimenProcessor();
			if(result!=null){	
				if(result.specimens)
					return (' ' + result.specimens + ' ');
				else {
					//var specimenArray=resultSpecimenProcessor.getSpecimenList(JSON.stringify(result),orderId);
					var specimenArray=[];
					if(specimenArray.length==0)
						return "";
					else {
						specimenName="";
						$(specimenArray).each(function(index,specimen) {
							if(specimenName=="")
								specimenName=specimen;
							else
								specimenName+=','+ specimen;
						});
						return (' ' + specimenName + ' ');
					}	
				}
			}
		}
	}
	
	this.generate = function(result,combinedResult,orderId,honorific) {
		var self=this;
		var headerSetting =methodInvoker.getPageSettingByKey('header');
		var header = $("<div class='resultHeader' />");
		//header.css('width', (headerSetting.width - headerSetting.marginLeft)+ "mm");
		header.css('width', headerSetting.width+"mm");
		header.css('height', headerSetting.height+ "mm");
		header.css('margin-left',headerSetting.marginLeft+ "mm");
		if(parseInt(headerSetting.fontSize)!=0)
			header.css('font-size',headerSetting.fontSize + "px")
		if(parseInt(headerSetting.marginTop)!=0){
			var newDiv = $("<div>&nbsp;</div>");
			newDiv.append(" ");
			newDiv.css("height",headerSetting.marginTop+"mm");
			newDiv.css("width",headerSetting.width+"mm");
			header.append(newDiv);
		}
		var l1c = methodInvoker.getPageSettingByKey('L1C');
		var l1v = methodInvoker.getPageSettingByKey('L1V');
		var m1c = methodInvoker.getPageSettingByKey('M1C');
		var m1v = methodInvoker.getPageSettingByKey('M1V');
		var r1c = methodInvoker.getPageSettingByKey('R1C');
		var r1v = methodInvoker.getPageSettingByKey('R1V');
		header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l1c,l1v,m1c,m1v,r1c,r1v));
		var l2c = methodInvoker.getPageSettingByKey('L2C');
		var l2v = methodInvoker.getPageSettingByKey('L2V');
		var m2c = methodInvoker.getPageSettingByKey('M2C');
		var m2v = methodInvoker.getPageSettingByKey('M2V');
		var r2c = methodInvoker.getPageSettingByKey('R2C');
		var r2v = methodInvoker.getPageSettingByKey('R2V');
		if(l2v.label!="select" || m2v.label!="select" || r2v.label!="select"){
			header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l2c,l2v,m2c,m2v,r2c,r2v));	
		}
		var l3c = methodInvoker.getPageSettingByKey('L3C');
		var l3v = methodInvoker.getPageSettingByKey('L3V');
		var m3c = methodInvoker.getPageSettingByKey('M3C');
		var m3v = methodInvoker.getPageSettingByKey('M3V');
		var r3c = methodInvoker.getPageSettingByKey('R3C');
		var r3v = methodInvoker.getPageSettingByKey('R3V');
		if(l3v.label!="select" || m3v.label!="select" || r3v.label!="select"){
			header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l3c,l3v,m3c,m3v,r3c,r3v));	
		}
		var l4c = methodInvoker.getPageSettingByKey('L4C');
		var l4v = methodInvoker.getPageSettingByKey('L4V');
		var m4c = methodInvoker.getPageSettingByKey('M4C');
		var m4v = methodInvoker.getPageSettingByKey('M4V');
		var r4c = methodInvoker.getPageSettingByKey('R4C');
		var r4v = methodInvoker.getPageSettingByKey('R4V');
		if(l4v.label!="select" || m4v.label!="select" || r4v.label!="select"){
			header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l4c,l4v,m4c,m4v,r4c,r4v));	
		}
		var l5c = methodInvoker.getPageSettingByKey('L5C');
		var l5v = methodInvoker.getPageSettingByKey('L5V');
		var m5c = methodInvoker.getPageSettingByKey('M5C');
		var m5v = methodInvoker.getPageSettingByKey('M5V');
		var r5c = methodInvoker.getPageSettingByKey('R5C');
		var r5v = methodInvoker.getPageSettingByKey('R5V');
		if(l5v.label!="select" || m5v.label!="select" || r5v.label!="select"){
			header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l5c,l5v,m5c,m5v,r5c,r5v));	
		}
		var l6c = methodInvoker.getPageSettingByKey('L6C');
		var l6v = methodInvoker.getPageSettingByKey('L6V');
		var m6c = methodInvoker.getPageSettingByKey('M6C');
		var m6v = methodInvoker.getPageSettingByKey('M6V');
		var r6c = methodInvoker.getPageSettingByKey('R6C');
		var r6v = methodInvoker.getPageSettingByKey('R6V');
		if(l6v.label!="select" || m6v.label!="select" || r6v.label!="select"){
			header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l6c,l6v,m6c,m6v,r6c,r6v));	
		}
		
		return header;
	}
	this.generateRowNew = function(result, combinedResult, orderId, honorific, lnc, lnv, mnc, mnv, rnc, rnv){
		var self=this;
		var row="";
		var curwidth=0;
		var curcol = "";
		if(lnv.label!='blank' || mnv.label!="blank" || rnv.label!="blank" || 
			lnc.label.trim()!="" || mnc.label.trim()!="" || rnc.label.trim()!=""){
			row=$('<div class="oneHeader" >&nbsp;</div>');
			var column = $('<div class="columnleftalign">&nbsp;</div>');
			column.append(lnc.label);
			self.setStyles(column,lnc);
			curwidth = parseInt(lnc.width),10||0;
			curcol = column;
			row.append(column);
			if(lnv.visible!=false){
				var column = $('<div class="columnleftalign">&nbsp;</div>');
				column.append(self.getValue(result, combinedResult, orderId, honorific, lnv.label));
				self.setStyles(column,lnv);
				curwidth = parseInt(lnv.width);
				curcol=column;
				row.append(column);
			} else {
				curwidth+=parseInt(lnv.width);
				curcol.css('width',curwidth+"mm");
			}
			if(mnc.visible!=false){
				var column = $('<div class="columnleftalign">&nbsp;</div>');
				column.append(mnc.label);
				self.setStyles(column,mnc);
				curwidth = parseInt(mnc.width);
				curcol=column;
				row.append(column);
			} else {
				curwidth+=parseInt(mnc.width);
				curcol.css('width',curwidth+"mm");
			}
			if(mnv.visible!=false){
				var column = $('<div class="columnleftalign">&nbsp;</div>');
				column.append(self.getValue(result, combinedResult, orderId, honorific, mnv.label));
				self.setStyles(column,mnv);
				curwidth = parseInt(mnv.width);
				curcol=column;
				row.append(column);
			} else {
				curwidth+=parseInt(mnv.width);
				curcol.css('width',curwidth+"mm");
			}
			if(rnc.visible!=false){
				var column = $('<div class="columnleftalign">&nbsp;</div>');
				column.append(rnc.label);
				self.setStyles(column,rnc);
				curwidth = parseInt(rnc.width);
				curcol=column;
				row.append(column);
			} else {
				curwidth+=parseInt(rnc.width);
				curcol.css('width',curwidth+"mm");
			}
			if(rnv.visible!=false){
				var column = $('<div class="columnleftalign">&nbsp;</div>');
				column.append(self.getValue(result, combinedResult, orderId, honorific, rnv.label));
				self.setStyles(column,rnv);
				curwidth = parseInt(rnv.width);
				curcol=column;
				row.append(column);
			} else {
				curwidth+=parseInt(rnv.width);
				curcol.css('width',curwidth+"mm");
			}
		}
		return row;
	}
}
function TestListJsonDTO() {
	
}
TestListJsonDTO.prototype.setTestName= function(testName) {
	this.testName= testName;
}
TestListJsonDTO.prototype.getTestName= function() {
	return this.testName;
}
TestListJsonDTO.prototype.setReportingDate= function(reportingDate) {
	this.reportingDate= reportingDate;
}
TestListJsonDTO.prototype.getReportingDate= function() {
	return this.reportingDate;
}
TestListJsonDTO.prototype.setReportingTime= function(reportingTime) {
	this.reportingTime= reportingTime;
}
TestListJsonDTO.prototype.getReportingTime= function() {
	return this.reportingTime;
}
TestListJsonDTO.prototype.setSpecimens= function(specimens) {
	this.specimens= specimens;
}
TestListJsonDTO.prototype.getSpecimens= function() {
	return this.specimens;
}
TestListJsonDTO.prototype.setResult= function(result) {
	this.result= result;
}
TestListJsonDTO.prototype.getResult= function() {
	return this.result;
}
function ResultPrintHandler() {
	this.generateHtmlString = function(result) {
		var strHtml = '<html><head>'; 
		strHtml += "<style>";
		strHtml += "<!--";
		strHtml += ".groupResultTable{font-family:'arial';page-break-after:always; padding:none; margin:none;} ";
		strHtml += ".page{font-family:'arial';page-break-after:always; padding:none; margin:none;} ";
		strHtml += ".one {width:100%;}";
		strHtml += ".nomargin{margin:0px}";
		strHtml += ".singleheight {height:6.5mm;}";
		strHtml += ".oneHeader{width:100%}";
 		strHtml += ".onethird {width:33%;float:left}";
 		strHtml += ".onesecond {width:30%;float:left}";
 		strHtml += ".onefirst{width:39%;float:left}";
 		strHtml += ".onefive {width:20%;float:left;}";
 		strHtml += ".onefourth {width:15%;float:left;}";
		//strHtml += ".remarks { margin:auto; width:90%}";
		strHtml += ".column-2{text-align:right}";
		strHtml += ".middle{text-align:center}";
		strHtml += ".bold {font-weight:bold}";
		strHtml += ".underline{text-decoration:underline}";
		strHtml += ".columnleftalign{float:left;}";
		strHtml += ".one_first_column {width:17%}";
 		strHtml += ".one_second_column{width:5%}";
 		strHtml += ".one_third_column{width:77%}";
 		strHtml += "#foodAllergy {font-size:12px}";
		strHtml += ".foodallergy_onethird_border {border:1px solid};";
		strHtml += "#foodAllergy .one_third_column{font-size:11px}";
		strHtml += ".smallheight {line-height:15px;}"
		strHtml += ".column_height {line-height:15mm;}"
		strHtml += ".normal_height{line-height:7mm}";

		var generalLayoutset =methodInvoker.getPageSettingByKey('general_layout');


		strHtml += ".printresultTable {";

		if(generalLayoutset.border==true) {
			strHtml += "	border:1px solid #000;";
			strHtml += "	margin:auto;";
			strHtml += "	border-spacing:15px;";
			strHtml += "	border-collapse:collapse;";
		}
		//strHtml += "	line-height:25px;";
		//strHtml += "	font-size:16px;";
		strHtml += "}";
		if(generalLayoutset.border==true) {
			strHtml += ".printresultTable thead tr th {border:1px solid #000; line-height:25px;}";
			strHtml += ".printresultTable tbody tr td{border:1px solid #000;line-height:25px;}";
			strHtml += ".printresultTable table tbody tr td{border:none;}";
		}


		strHtml += "input.readonlyStyle {";
		strHtml += "	background-color:#FAFAFA;";
		strHtml += "	border-style:none;";
		strHtml += "	border-width:0px;";
		strHtml += "	color:#000;";
		strHtml += "}";
		strHtml += ".resultTitle{ font-weight:bold; font-size:17px;margin-bottom:5px;}";
		strHtml += "#cultureresultTable{";
		strHtml += "	border:1px solid #000;";
		strHtml += "	width:95%;";
		strHtml += "	margin:auto;";
		strHtml += "	border-spacing:15px;";
		strHtml += "	border-collapse:collapse;";
		strHtml += "	line-height:15px;";
		strHtml += "	font-size:90%";
		strHtml += "}";
		strHtml += "#cultureresultTable thead tr th {border:1px solid #000;}";
		strHtml += "#cultureresultTable tbody tr td{border:1px solid #000; line-height:19px;}";
		strHtml += "#cultureresultTable  table tbody tr td{border:1px solid #000;}";
		strHtml += "#cultureresultTable  td{padding:none;margin:none; line-height:19px;}";

		strHtml += ".cultureTable_Head thead tr th {border:0px solid #000; font-size:16px;}";
		strHtml += ".cultureTable_Head tbody tr td{border:0px solid #000;}";

		strHtml += "input.readonlybox {";
		strHtml += "	background:none;";
		strHtml += "	border:none;";
		strHtml += "	color:#000;";
		strHtml += "	padding:0px;";
		strHtml += "	margin:0px;";
		strHtml += "	width:50%;";
		strHtml += "	text-align:right;	";
		strHtml += "}";
		strHtml += "input.plainText {";
		strHtml += "	width:90%;";
		strHtml += "	background-color:#FAFAFA;";
		strHtml += "	border-style:none;";
		strHtml += "	border-width:0px;";
		strHtml += "	color:#000;";
		strHtml += "}";
		strHtml += "input.middle{";
		strHtml += "	text-align:center;";
		strHtml += "}";
		strHtml += "#realTable{";
		strHtml += "	border:1px solid #000;";
		strHtml += "	width:100%;";
		strHtml += "	margin:auto;";
		strHtml += "	border-spacing:15px;";
		strHtml += "	border-collapse:collapse;";
		strHtml += "	line-height:21px;font-size:90%;";
		strHtml += "}";
		strHtml += "#realTable thead tr th {border:1px solid #000;font-size:12px;}";
		strHtml += "#realTable tbody tr td{border:1px solid #000;}";
		strHtml += "#realTable  table tbody tr td{border:1px solid #000;}";
		strHtml += "#realTable  td{padding:none;margin:none;}";
		strHtml += "#realTable input.medium { width: 35%;}";

		strHtml += "#SpHgtTable{";
		strHtml += "	border:1px solid #000;";
		strHtml += "	width:100%;";
		strHtml += "	margin:auto;";
		strHtml += "	border-spacing:15px;";
		strHtml += "	border-collapse:collapse;";
		strHtml += "	line-height:25px;";
		strHtml += "}";
		strHtml += "#SpHgtTable thead tr th {border:1px solid #000;}";
		strHtml += "#SpHgtTable tbody tr td{border:1px solid #000;}";
		strHtml += "#SpHgtTable  table tbody tr td{border:1px solid #000;}";
		strHtml += "#SpHgtTable  td{padding-left:1%;margin:none;}";
		strHtml += "#SpHgtTable input.medium { width: 40%;}";
		strHtml += "#SpHgtTable input.part { width: 75%;}";
		strHtml += "#SpHgtTable input.large { width: 100%;}";
		strHtml += "#SpHgtTable td.microscopy {font-weight:bold;font-size:20px;}";
		strHtml += "#SpHgtTable input.mystyle{ width:100%;height:28px;font-size:15px;}";
		strHtml += "#StoneTable input.medium { width: 40%;}";
		strHtml += "#StoneTable input.part { width: 75%;}";
		strHtml += "#SemenTable{";
		strHtml += "	border:1px solid #000;";
		strHtml += "	width:90%;";
		strHtml += "	margin:auto;";
		strHtml += "	border-spacing:15px;";
		strHtml += "	border-collapse:collapse;";
		strHtml += "}";
		strHtml += "";
		strHtml += "#SemenTable thead tr th {border:1px solid #000;}";
		strHtml += "#SemenTable tbody tr td{border:1px ;}";
		strHtml += "#SemenTable  tbody tr {border:1px;}";
		strHtml += "#SemenTable  td{padding:none;margin:none;}";
		strHtml += "#SemenTable input.medium { width:30%;}";
		strHtml += "#SemenTable input.tiny { width:10%;}";
		strHtml += "#SemenTable span {padding-right:5px;}";
		strHtml += "#SemenTable label {padding-right:3px;}";
		strHtml += "";
		strHtml += "#SpHgtTable #NoBorderTable{";
		strHtml += "	border:none;";
		strHtml += "	width:90%;";
		strHtml += "	margin:auto;";
		strHtml += "	border-spacing:15px;";
		strHtml += "	border-collapse:collapse;";
		strHtml += "	line-height:25px;";
		strHtml += "	width:100%;";
		strHtml += "	font-weight:normal;";
		strHtml += "}";
		strHtml += "#SpHgtTable #NoBorderTable thead tr th {border:none;}";
		strHtml += "#SpHgtTable #NoBorderTable tbody tr td{border:none;font-weight:italic}";
		strHtml += "#SpHgtTable #NoBorderTable  tbody tr {border:none;}";
		strHtml += "#SpHgtTable #NoBorderTable  td{padding:none;margin:none;}";
		strHtml += "#SpHgtTable #NoBorderTable input.medium { width:30%;}";
		strHtml += "";
		strHtml += ".column-left { float: left; width: 49%;}";
		strHtml += ".column-first { float: left; width: 38%;}";
		strHtml += ".column-second { float: left; width: 30%;}";
		strHtml += ".column-right {";
		strHtml += "  float: right;";
		strHtml += "  width: 49%;";
		strHtml += "  padding:0 0 0 0px;";
		strHtml += "}";
		strHtml += "#SpHgtTable  table tbody tr td{font-size:16px;}";
		strHtml += "form .column-left, form .column-right { width: 47%;  padding:0 2% 0 0;}";
		strHtml += ".titleClass {";
		strHtml += "	font-size:16px;";
		strHtml += "	font-weight:bold;";
		strHtml += "	text-align:center;";
		strHtml += "	line-height:25px;";
		strHtml += "	text-decoration: underline; ";
		strHtml += "}";
		strHtml += ".topAlign {vertical-align:top;}";
		strHtml += "table .noBorder {border:none;}";
		strHtml += "table .noBorder tbody tr td{border:none;}";
		strHtml += ".remarks p { margin-top:0px;margin-bottom:0px;}";
		strHtml += "--></style>";
		strHtml+='</head><body><div id="printDiv">';
		strHtml+= result + '</div></body></html>';
		return strHtml;
	}
	
	this.print=function(htmlString) {
		var WindowObject = window.open('', 'PrintWindow', 'width=1000,height=650,top=50,left=50,toolbars=no,scrollbars=yes,status=no,resizable=yes');
		var writestat = WindowObject.document.writeln(htmlString);
		WindowObject.focus();
		WindowObject.print();
		//WindowObject.document.close();
		//WindowObject.close();
		return;
	}
	
}
function ResultSpecimenProcessor() {
	this.getSpecimenList = function(result,orderId) {
		result = $.parseJSON(result);
		var specimenArray = [];
		$(result.testResult).each(function (index,testInfo) {
			$(testInfo.specimen).each(function(index1, specimen){
				if(!commonMethodInvoker.isIndexExists(specimenArray,specimen))
					specimenArray.push(specimen);
				return false;
			});	
		});
		return specimenArray;
	}
}