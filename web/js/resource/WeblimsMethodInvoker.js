/*JS contains the general methods for weblims application*/
function WeblimsMethodInvoker() {
	this.fillRoles = function(controlName, type, url) {
		$j(controlName).empty();
		ajaxProcessor.setUrl(url);
		var rolesList = ajaxProcessor.get();
		if(type=='select') {
			$j(rolesList.role).each(function (roleIndex, role) {
				var optTag = $j('<option />');
				optTag.attr('value',role.uid);
				optTag.html(role.name);
				$j(controlName).append(optTag);
			});
		}
	}
	this.getRoleName = function(roleId) {
		ajaxProcessor.setUrl(constants.VIEWROLEURL+roleId);
		var response= ajaxProcessor.get();
		return response.name;
	}
	this.getAreaName = function(areaId) {
		ajaxProcessor.setUrl(constants.VIEWAREAURL+areaId);
		var response= ajaxProcessor.get();
		return response.name;
	}
	this.getSpecimenName = function(specimenId) {
		ajaxProcessor.setUrl(constants.VIEWSPECIMENURL+specimenId);
		var response= ajaxProcessor.get();
		return response.name;
	}
	this.setEnumList=function() {
		ajaxProcessor.setUrl(constants.ENUMLISTURL);
		this.enumList =ajaxProcessor.get();
	}
	this.getEnumList=function() {
		return this.enumList;
	}
	this.setConfigData = function() {
		ajaxProcessor.setUrl(constants.CONFIGDATAURL);
		this.configData =ajaxProcessor.get();
	}
	this.setDefaultData = function() {
		ajaxProcessor.setUrl(constants.DEFAULTDATAURL);
		this.defaultData =ajaxProcessor.get();
	}
	this.getDiscountInfo=function(discountuid) {
		ajaxProcessor.setUrl(constants.GETDISCOUNTURL + discountuid);
		var response=ajaxProcessor.get();
		return response;
	}
	this.getConfigData = function() {
		return this.configData;
	}
	this.getDefaultData = function() {
		return this.defaultData;
	}
	this.setSpecialTestList = function() {
		var testSpecialList =[];
		ajaxProcessor.setUrl(constants.SPECIALTESTSGETURL);
		var response=ajaxProcessor.get();
		if(response.success==true)
			$j(response.tests).each(function(index, test){
				testSpecialList.push(test.toUpperCase());
			});
		this.testSpecialList=testSpecialList;
	}
	this.getReferralInfo = function(referralId) {
		ajaxProcessor.setUrl(constants.VIEWREFERRALURL+referralId);
		var response=ajaxProcessor.get();
		return response;
	}	
	this.getSpecialTestList=function() {
		return this.testSpecialList;
	}
	this.getTestIdFromConfig = function(testName_Id) {
		var testId="";
		$j(configData.test).each(function(index,testDetail){
			if(testName_Id.trim().toUpperCase()==testDetail.uid.toUpperCase() || testName_Id.trim().toUpperCase()==testDetail.name.trim().toUpperCase()) {
				testId=testDetail.uid;
				return false;
			}	
		});
		if(testId=="") {
			$j(defaultData.testPkg).each(function(index,testDetail){  
				if(testName_Id.trim().toUpperCase()==testDetail.uid.toUpperCase() || testName_Id.trim().toUpperCase()==testDetail.name.trim().toUpperCase()) {
					testId=testDetail.uid;
					return false;
				}	
			});
		}
		return testId;
	}
	this.getReferralIdFromDefault = function(refName_Id) {
		var refId="";
		var defaultInfo = this.getDefaultData();
		$j(defaultInfo.referral).each(function(index,referral){  	
			if(refName_Id==referral.uid || refName_Id.toUpperCase()==referral.name.toUpperCase()){
				refId=referral.uid;
				return false;
			}	
		});
		return refId;
	}
	this.getReferralNameFromDefault = function(refName_Id) {
		var defaultInfo = this.getDefaultData();
		var refName="";
		$j(defaultInfo.referral).each(function(index,referral){  	
			if(refName_Id==referral.uid) {
				if(referral.active==true)
					refName=referral.name;
				return false;
			}	
		});
		return refName;
	}
	this.getReferralActiveId=function(refName_Id) {
		var refId="";
		var defaultInfo = this.getDefaultData();
		$j(defaultInfo.referral).each(function(index,referral){  	
			if(refName_Id==referral.uid || refName_Id.toUpperCase()==referral.name.toUpperCase()){
				if(referral.active==true)
					refId=referral.uid;
				return false;
			}	
		});
		return refId;
	}
	this.getSettingsItemValue=function(itemName) {
		ajaxProcessor.setUrl(constants.VIEWSETTINGURL + itemName);
		return ajaxProcessor.get();
	}
	this.getTestNameFromConfig = function(testId) {
		var configInfo = this.getConfigData();
		var defaultInfo = this.getDefaultData();
		var testName="";
		$j(configInfo.test).each(function(index,testDetail){  
			if(testId.toUpperCase()==testDetail.uid.toUpperCase()) {
				testName=testDetail.name;
				return false;
			}	
		});
		if(testName==""){
			$j(defaultInfo.testPkg).each(function(index,testDetail){  
				if(testId.toUpperCase()==testDetail.uid.toUpperCase()) {
					testName=testDetail.name;
					return false;
				}	
			});
		}
		return testName;
	}
	this.getBlanketIdFromDefault = function(blankName_Id) {
		var defaultInfo = this.getDefaultData();
		var blanketId="";
		$j(defaultInfo.blanketOrder).each(function(index,blanket){  
			if(blankName_Id==blanket.uid || blankName_Id.toUpperCase()==blanket.orgName.toUpperCase()) {
				blanketId=blanket.uid;
				return false;
			}	
		});
		return blanketId;
	}
	this.getBlanketNameFromDefault = function(blanketId) {
		var defaultInfo = this.getDefaultData();
		var blanketName;
		$j(defaultInfo.blanketOrder).each(function(index,blanket){  
			if(blanketId==blanket.uid) {
				blanketName=blanket.orgName;
				return false;
			}	
		});
		return blanketName;
	}
	this.getAreaIdFromDefault = function(areaName) {
		var defaultInfo = this.getDefaultData();
		var areaId="";
		$j(defaultInfo.area).each(function(index,area){ 
			if(areaName==area.name) {
				areaId=area.uid;
				return false;
			}
		});
		return areaId;
	}
	this.getAreaNameFromDefault = function(areaId) {
		var defaultInfo = this.getDefaultData();
		var areaName="";
		$j(defaultInfo.area).each(function(index,area){ 
			if(areaId==area.uid) {
				areaName=area.name;
				return false;
			}
		});
		return areaName;
	}
	this.getAgentIdFromDefault = function(agName_Id) {
		var defaultInfo = this.getDefaultData();
		var agentId="";
		$j(defaultInfo.agent).each(function(index,agent){  
			if(agName_Id==agent.uid || agName_Id.toUpperCase()==agent.name.toUpperCase()) {
				agentId=agent.uid;
				return false;
			}	
		});
		return agentId;
	}
	
	this.getAgentNameFromDefault = function(agentId) {
		var defaultInfo = this.getDefaultData();
		var agentName;
		$j(defaultInfo.agent).each(function(index,agent){  
			if(agentId==agent.uid) {
				agentName=agent.name;
				return false;
			}	
		});
		return agentName;
	}
	this.getFacilityIdFromDefault = function(facName_Id) {
		var defaultInfo = this.getDefaultData();
		var facilityId="";
		$j(defaultInfo.facility).each(function(index,facility){  
			if(facName_Id==facility.uid || facName_Id.toUpperCase()==facility.name.toUpperCase()) {
				facilityId = facility.uid;
				return false;
			}	
		});
		return facilityId;
	}
	this.getFacilityNameFromDefault = function(facilityId) {
		var defaultInfo = this.getDefaultData();
		var facilityName;
		$j(defaultData.facility).each(function(index,facility){  
			if(facilityId==facility.uid) {
				facilityName=facility.name;
				return false;
			}	
		});
		return facilityName;
	}
	this.getBranchIdFromConfig = function(brName_Id) {
		var configInfo = this.getConfigData();
		var brId="";
		$j(configInfo.branch).each(function(index,branch){  
			if(brName_Id==branch.uid || brName_Id.toUpperCase()==branch.name.toUpperCase()) {
				brId = branch.uid;
				return false;
			}	
		});
		return brId;	
	}
	this.getBranchNameFromConfig = function(brId) {
		var configInfo = this.getConfigData();
		var brName="";
		$j(configInfo.branch).each(function(index,branch){  
			if(brId==branch.uid) {
				brName = branch.name;
				return false;
			}	
		});
		return brName;
	}
	this.getSpecimenIdFromConfig = function(specimenName) {
		var configInfo = this.getConfigData();
		var specimenId;
		$j(configInfo.specimen).each(function(index,spec){  
			if(specimenName==spec.name) {
				specimenId=spec.uid;
				return false;
			}	
		});
		return specimenId;
	}
	this.getSpecimenNameFromConfig = function(specimenId) {
		var specimenName;
		$j(configData.specimen).each(function(index,spec){  
			if(specimenId==spec.uid) {
				specimenName=spec.name;
				return false;
			}	
		});
		return specimenName;
	}
	this.getSpecimenNamesFromTestConfig = function(testId) {
		var configInfo = this.getConfigData();
		var specimens=[];
		$j(configInfo.test).each(function(index,testdet) {
			if(testdet.uid==testId){
				$j(testdet.testSpecimen).each(function(index,spec) {
					$j(configInfo.specimen).each(function(specindex,speci) {	
						if(spec.specimenUid==speci.uid) {
							specimens.push(speci.name);
						}
					});
				});
				return false;
			}	
		});
		return specimens;		
	}
	this.getSpecimenObjFromTestConfig = function(testId) {
		var configInfo = this.getConfigData();
		var specimens=[];
		$j(configInfo.test).each(function(index,testdet) {
			if(testdet.uid==testId){
				$j(testdet.testSpecimen).each(function(index,spec) {
					var specimenDTO = new SpecimenDTO();
					$j(configInfo.specimen).each(function(specindex,speci) {	
						if(spec.specimenUid==speci.uid) {
							specimenDTO.setId(speci.uid);
							specimenDTO.setName(speci.name);
							specimenDTO.setUnit(speci.unit);
							specimens.push(specimenDTO);
							return false;
						}
					});
				});
				return false;
			}	
		});
		return specimens;		
	}
	this.getSpecialCharge=function(value) {
		var defaultInfo = this.getDefaultData();
		var valueAmt;
		$j(defaultInfo.consumable).each(function(index,consumable){  
			if(value==consumable.name) {
				valueAmt=consumable.rate;
				return false;
			}	
		});
		return valueAmt;
	}
	this.getSpecialChargeId=function(value) {
		var defaultInfo = this.getDefaultData();
		var specialId;
		$j(defaultInfo.consumable).each(function(index,consumable){  
			if(value==consumable.name) {
				specialId=consumable.uid;
				return false;
			}	
		});
		return specialId;
	}	
	this.getTestResult = function(testId){
		var configInfo = this.getConfigData();
		var testResult="";
		$j(configInfo.test).each(function(index,testDetail){  
			if(testId==testDetail.uid) {
				testResult=testDetail.result;
				return false;
			}	
		});
		if(testResult==undefined)
			testResult="";
		return testResult;
	}
	this.getAgentByFacilityAndFill=function(facilityId,controlObj) {
		$j(controlObj).empty();
		ajaxProcessor.setUrl(constants.GETAGENTBYFACILITY + facilityId);
		var response = ajaxProcessor.get();
		$j(response.agent).each(function(index,agent){ 
			$j(controlObj).append('<option value='+agent.uid+'>'+agent.name+'</option>');
		});
	}
	this.fillTestNamesToControl=function(controlName) {
		var autoCompleteArray=[]; // array used to make the sourcedata  for controls for auto complete
		//Add autocomplete feature to Test Id/Test Name text box
		var configInfo = this.getConfigData();
		var defaultInfo = this.getDefaultData();
		$j(configInfo.test).each(function(index,test){  
		   if(test.active==true){
			autoCompleteArray.push(test.name);	
		   }		
		});
		if(defaultInfo.testPkg.length>0) {
			$j(defaultInfo.testPkg).each(function(index,testPkg){
			if(testPkg.active==true){
				autoCompleteArray.push(testPkg.name);
			  }				
			});
		}
		commonMethodInvoker.makeautoComplete(controlName, autoCompleteArray);
	}
	this.fillReferralNamesToControl=function(controlName,active) {
		var defaultInfo = this.getDefaultData();
		var autoCompleteArray=[]; 
		$j(defaultInfo.referral).each(function(index,referral){ 
			if(active==true){
				if(referral.active==true)
					autoCompleteArray.push(referral.name);	
			} else
				autoCompleteArray.push(referral.name);
		});
		commonMethodInvoker.makeautoComplete(controlName, autoCompleteArray);
	}
	this.fillPaymentModesToControl=function(controlObj) {
		this.initSelect(controlObj,constants.SELECTPAYMENTMODE);
		$j(controlObj).append('<option value="' + constants.DAILY + '">'+ constants.DAILY + '</option>');
		$j(controlObj).append('<option value="' + constants.MONTHLY + '">'+ constants.MONTHLY + '</option>');
	}
	this.fillAreasToControl=function(controlObj) {
		var defaultInfo = this.getDefaultData();
		this.initSelect(controlObj,constants.SELECTAREA);
		$j(defaultInfo.area).each(function(index,area){ 
			$j(controlObj).append('<option value='+area.uid+'>'+area.name+'</option>');
		});
	}
	this.setAreas=function(controlName) {
		var defaultInfo = this.getDefaultData();
		var autoCompleteArray =[];
		$j(defaultInfo.area).each(function(index,area){ 
			autoCompleteArray.push(area.name);
		});
		commonMethodInvoker.makeautoComplete(controlName, autoCompleteArray);
	}
	this.fillFacilityNamesToSelectControl=function(controlObj) {
		var defaultInfo = this.getDefaultData();
		this.facilityNamesToSelectFromResponse(controlObj,defaultInfo);
	}
	this.facilityNamesToSelectFromResponse = function(controlObj, response) {
		this.initSelect(controlObj,constants.SELECTFACILITY);
		$j(response.facility).each(function(index,facility){ 
			$j(controlObj).append('<option value='+facility.uid+'>'+facility.name+'</option>');
		});
	}
	this.fillAgentNamesToSelectControl=function(controlObj){
		var defaultInfo = this.getDefaultData();
		this.initSelect(controlObj,constants.SELECTAGENT);
		$j(defaultInfo.agent).each(function(index,agent){ 
			$j(controlObj).append('<option value='+agent.uid+'>'+agent.name+'</option>');
		});
	}
	this.fillFacilityNamesToControl=function(controlName,active) {
		var defaultInfo = this.getDefaultData();
		var autoCompleteArray=[]; 
		$j(defaultInfo.facility).each(function(index,facility){ 
			if(active==true){
				if(facility.active==true)
					autoCompleteArray.push(facility.name);		
			} else
				autoCompleteArray.push(facility.name);		
		});
		commonMethodInvoker.makeautoComplete(controlName, autoCompleteArray);
	}
	this.fillBlanketOrderNamesToControl=function(controlName) {
		var defaultInfo = this.getDefaultData();
		var autoCompleteArray=[]; 
		$j(defaultInfo.blanketOrder).each(function(index,blanket){ 
			if(blanket.active==true)
				autoCompleteArray.push(blanket.orgName);	
		});
		commonMethodInvoker.makeautoComplete(controlName, autoCompleteArray);
	}
	this.fillAgentNamesToControl=function(controlName, active) {
		var defaultInfo = this.getDefaultData();
		var autoCompleteArray=[]; 
		$j(defaultInfo.agent).each(function(index,agent){
			if(active==true){
				if(agent.active==true)
					autoCompleteArray.push(agent.name);	
			} else
				autoCompleteArray.push(agent.name);	
		});
		commonMethodInvoker.makeautoComplete(controlName, autoCompleteArray);
	}
	this.fillCollectedAtToControl=function(controlName) {
		var defaultInfo = this.getDefaultData();
		var autoCompleteArray = [];
		$j(defaultInfo.facility).each(function(index,facility){ 
			autoCompleteArray.push(facility.name);	
		});
		commonMethodInvoker.makeautoComplete(controlName, autoCompleteArray);
	}
	this.fillDiscountsToControl=function(controlName) {
		var defaultInfo = this.getDefaultData();
		$j(defaultInfo.discount).each(function(index,discount){ 
			$j(controlName).append('<option value='+discount.uid+'>'+discount.name+'</option>');
		});
	}
	this.fillBranchToControl=function(controlName) {
		var configInfo = this.getConfigData();
		//Add autocomplete feature to Branch text box
		autoCompleteArray=[];
		$j(configInfo.branch).each(function(index,branchitem){ 
			autoCompleteArray.push(branchitem.name);	
		});
		commonMethodInvoker.makeautoComplete(controlName, autoCompleteArray);
	}
	this.fillHonourificToControl=function(controlObj) {
		$j(controlObj).empty();
		$j(controlObj).append('<option value=' + constants.MR + '>' + constants.MR + '</option>');
		$j(controlObj).append('<option value=' + constants.MRS + '>' + constants.MRS + '</option>');
		$j(controlObj).append('<option value=' + constants.MS + '>' + constants.MS + '</option>');
		$j(controlObj).append('<option value=' + constants.DR + '>' + constants.DR + '</option>');
		$j(controlObj).append('<option value=' + constants.MASTER + '>' + constants.MASTER + '</option>');
		$j(controlObj).append('<option value=' + constants.BABY + '>' + constants.BABY + '</option>');
		$j(controlObj).append('<option value=' + constants.BABYOF + '>' + constants.BABYOF + '</option>');
		$j(controlObj).append('<option value=' + constants.SR + '>' + constants.SR + '</option>');
		$j(controlObj).append('<option value=' + constants.FR + '>' + constants.FR + '</option>');
		$j(controlObj).append('<option value=' + constants.BR + '>' + constants.BR + '</option>');
		$j(controlObj).append('<option value=' + constants.ANIMAL + '>' + constants.ANIMAL + '</option>');
	}
	this.fillSpecimensToSelect=function(controlObj) {	
		this.initSelect(controlObj,constants.SELECTSPECIMEN);
		ajaxProcessor.setUrl(constants.GETSPECIMENSURL);
		var specimenList = ajaxProcessor.get();
		$j(specimenList.specimen).each(function(index,specimen){ 
			$j(controlObj).append('<option value='+specimen.uid+'>'+specimen.name+'</option>');
		});
	}
	this.isTestPackage=function(testId) {
		var status=false;
		if(testId.startsWith(constants.TP))
			status=true;
		return status;
	}
	this.isSpecialPackage=function(testId){
		var defaultInfo = this.getDefaultData();
		var status=false;
		$j(defaultInfo.testPkg).each(function(index,testPkg){
			if(testPkg.uid==testId){ 
				if(testPkg.category==constants.SPECIALPACKAGE)
					status=true;
			}
		});
		return status;
	}
	this.isTestActive=function(testId){
		var configInfo = this.getConfigData();
		var defaultInfo = this.getDefaultData();
		var status=false;
		$j(configInfo.test).each(function(index,test){  
			if(test.uid==testId && test.active==true)
				status=true;		
		});
		if(defaultInfo.testPkg.length>0) {
			$j(defaultInfo.testPkg).each(function(index,testPkg){
				if(testPkg.uid==testId && testPkg.active==true) 
					status=true;		     
			});
		}
		return status;	
	}
	this.initSelect = function(controlObj, displayText) {
		$j(controlObj).empty();
		$j(controlObj).append('<option value="select">' + displayText + '</option>');
	}
	this.fillTestandTestPkgsToControl=function(controlName){
		var autoCompleteArray=[]; 
		var configInfo = this.getConfigData();
		var defaultInfo = this.getDefaultData();
		$j(configInfo.test).each(function(index,test){  
			if(test.active==true)
				autoCompleteArray.push(test.name);					     
		});
		if(defaultInfo.testPkg.length>0) {
			$j(defaultInfo.testPkg).each(function(index,testPkg){
			 if(testPkg.category==constants.NORMALPACKAGE) 
				if(testPkg.active==true)
					autoCompleteArray.push(testPkg.name);		     
			});
		}
		commonMethodInvoker.makeautoComplete(controlName, autoCompleteArray);
	}
	this.getChangableStatusList=function() {
		var statuslist = [];
		var enumList = this.getEnumList();
		$j(enumList.enumListDTO).each(function (index, list) {
			if(list.key==constants.STATUSENUM) {
				$j(list.enumValues).each(function (index, list) {
					if(list!=constants.NEW && list!=constants.INPROGRESS && list!=constants.PARTIALRESULTS && list!=constants.RESULTSREADY)
						statuslist.push(list);
				});
			}
		});
		return statuslist;
	}
	this.fillnormalvalueToControl = function (controlName, values) {
		var array = values.split("<br/>");
		$j(controlName).empty();
    	$j.each(array,function(i){ 
			$j(controlName).append('<option>'+array[i]+'</option>');
		});
	}
	this.getCollectStatusList=function() {
		var statusoplist = [];
		var enumList = this.getEnumList();
		$j(enumList.enumListDTO).each(function (index, list) {
			if(list.key==constants.PAYSTATUSENUM) {
				statusoplist = list.enumValues;
				return false;
			}
		});
		return statusoplist;
	}
	this.getStatusList=function() {
		var statuslist = [];
		var enumList = this.getEnumList();
		$j(enumList.enumListDTO).each(function (index, list) {
			if(list.key==constants.STATUSENUM) {
				statuslist = list.enumValues;
				return false;
			}
		});
		return statuslist;
	}
	this.getPriorityStatusList=function() {
		var statusoplist = [];
		var enumList = this.getEnumList();
		$j(enumList.enumListDTO).each(function (index, list) {
			if(list.key==constants.PRIORITYENUM) {
				statusoplist = list.enumValues;
				return false;
			}
		});
		return statusoplist;
	}
	this.getRolesListFor_A_C=function() {
		var rolesList=[];
		ajaxProcessor.setUrl(constants.GETROLES);
		var response= ajaxProcessor.get();
		$j(response.role).each(function(index,role){  
			rolesList.push(role.name);
		});
		return rolesList;
	}
	this.getFilterParameterType = function(button){
		var type=null;
		if(button=="orderSpecimenName") 
			type="text";
		if(button=="orderUid")
			type="text";
		if(button=="referralName")
			type="text";
		if(button=="orderStatus")
			type="text";
		if(button=="collectStatus")
			type="text";
		if(button=="paymentStatus")
			type="text";
		//if(button=="origin")
		//	type="text";
		if(button=="patientName")
			type="text";
		if(button=="patientPhone")
			type="text";
		if(button=="patientMobile")
			type="text";
		if(button=="priority")
			type="text";
		if(button=="agentName")
			type="text";
		if(button=="facilityName")
			type="text";
		if(button=="facilityArea")
			type="text";
		if(button=="blanketOrderName")
			type="text";
		if(button=="orderTestName")
			type="text";
		if(button=="orderTestPkgName")
			type="text";
		if(button=="orderSpecialTestPkgName")
			type="text";
		if(button=="orderCreatedOn")
			type="date";
		if(button=="specimenName")
			type="text";
		if(button=="specimentUid")
			type="number";
		if(button=="unit")
			type="text";
		if(button=="testName")
			type="text";
		if(button=="TestPkgName")
			type="text";
		if(button=="testPkgName")
			type="text";
		if(button=="testPkgUid")
			type="number";
		if(button=="testRate")
			type="number";
		if(button=="blanketOrderUid")
			type="text";
		return type;
	}
	this.setReportFilterValues=function(referalName) {
		var configInfo = this.getConfigData();
		var defaultInfo = this.getDefaultData();
		if(referalName=='orderSpecimenName'){
			autoCompleteArray=[];
			$j(configInfo.specimen).each(function(index,spec){  
				autoCompleteArray.push(''+spec.name+'');	
			});
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		if(referalName=='specimenName'){
			autoCompleteArray=[];
			$j(configInfo.specimen).each(function(index,spec){  
				autoCompleteArray.push(''+spec.name+'');	
			});
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		if(referalName=='collectStatus'){
			var autoCompleteArray= this.getCollectStatusList();
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		if(referalName=='referralName')
			this.fillReferralNamesToControl("#txt"+referalName,true);
		if(referalName=='orderStatus'){
			var autoCompleteArray= getStatusList();
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		if(referalName=='orderSpecimenStatus'){
			var autoCompleteArray= [];
			autoCompleteArray.push("Collected");
			autoCompleteArray.push("Not Collected");
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		if(referalName=='agentName')
			this.fillAgentNamesToControl("#txt"+referalName,true);
		if(referalName=='AgentName')
			this.fillAgentNamesToControl("#txt"+referalName);
		if(referalName=='facilityName')
			this.fillFacilityNamesToControl("#txt"+referalName,true);
		if(referalName=='WalkinFacilityName')
			this.fillFacilityNamesToControl("#txt"+referalName);
		if(referalName=='ReferralName')
			this.fillReferralNamesToControl("#txt"+referalName);
		if(referalName=='branchName')
			this.fillBranchToControl("#txt"+referalName);
		if(referalName=='areaName')
			this.setAreas("#txt"+referalName);
		if(referalName=='facilityArea')
			this.setAreas("#txt"+referalName);
		if(referalName=='area')
			this.setAreas("#txt"+referalName)
		if(referalName=='DiscountName'){
			autoCompleteArray=[];
			$j(defaultInfo.discount).each(function(index,discount){ 
				autoCompleteArray.push(''+discount.name+'');
			});
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		if(referalName=='TestName'){
			autoCompleteArray=[];
			$j(configInfo.test).each(function(index,test){
				autoCompleteArray.push(''+test.name+'');	
			});
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		if(referalName=='testName'){
			autoCompleteArray=[];
			$j(configInfo.test).each(function(index,test){
				autoCompleteArray.push(''+test.name+'');	
			});
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		if(referalName=='PaymentMode') {
			autoCompleteArray=[];
			autoCompleteArray.push('Daily');
			autoCompleteArray.push('Monthly');
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		//Add autocomplete feature to Test search bar text box
		if(referalName=='orderTestName'){
			autoCompleteArray=[];
			$j(configInfo.test).each(function(index,test){
				autoCompleteArray.push(''+test.name+'');	
			});
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		//Add autocomplete feature to Test search bar text box
		if(referalName=='orderTestPkgName'){
			autoCompleteArray=[];
			$j(defaultInfo.testPkg).each(function(index,testPackage){
				if(testDetail.category=='Normal Package')
					autoCompleteArray.push(''+testPackage.name+'');
			});	
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		if(referalName=='TestPkgName'){
			autoCompleteArray=[];
			$j(defaultInfo.testPkg).each(function(index,testPackage){
				autoCompleteArray.push(''+testPackage.name+'');
			});	
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		if(referalName=='testPkgName'){
			autoCompleteArray=[];
			$j(defaultInfo.testPkg).each(function(index,testPackage){
				autoCompleteArray.push(''+testPackage.name+'');
			});	
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		if(referalName=='orderSpecialTestPkgName'){
			autoCompleteArray=[];
			$j(defaultInfo.testPkg).each(function(index,testDetail){  	
				if(testDetail.category=='Special Package')
					autoCompleteArray.push(''+testDetail.name+'');
			});	
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		if(referalName=='roleName'){
			autoCompleteArray=this.getRolesListFor_A_C();
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		//Order Status
		if(referalName=='priority'){
			var autoCompleteArray= this.getPriorityStatusList();
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
		//Created Date
		if(referalName=='orderCreatedOn' || referalName=='orderDueOn' ){
			$j("#txt"+referalName).datepicker();
		}
		//Blanket Order
		if(referalName=='organization')
			this.fillBlanketOrderNamesToControl("#txt"+referalName);
		//Blanket Order
		if(referalName=='blanketOrderName'){
			var autoCompleteArray=[]; 
			$j(defaultInfo.blanketOrder).each(function(index,BlanketOrder){ 
				autoCompleteArray.push(BlanketOrder.orgName);	
			});
			commonMethodInvoker.makeautoComplete("#txt"+referalName, autoCompleteArray);
		}
	}	
}
