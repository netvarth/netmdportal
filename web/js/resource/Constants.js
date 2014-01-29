function Constants() {
	this.PATIENTTABCREATEVIEWURL = "/youNeverWait/json/view/patientTabs.json";
	this.PARENTTABOBJ = "#tabs-1";
	this.PATIENTAPOINTMENTVIEWURL="/youNeverWait/ws/ui/patient/listOfAppointments/";
	this.PATIENTPASTAPOINTMENTVIEWURL="/youNeverWait/ws/ui/patient/listOfPastAppointments/";
	this.PATIENTAPPOINTMENTOBJ="#appointmentInfoTab";
	this.PATIENTCLINICSOBJ="#clinicsInfoTab";
	this.PATIENTRESULTVIEWURL="/youNeverWait/ws/ui/result/list/";
	this.PATIENTRESULTOBJ="#resultsInfoTab";
	/*****************-------------General-----------**----*************/
	this.BRANCHLIST="BRANCH LIST";
	this.BRANCHCREATESUCCESS="Branch created successfully";
	this.GENERALPTBURL="/youNeverWait/json/toolbars/viewBranchGeneralToolbar1.json";
	this.BRANCHUPDATESUCCESS="Branch updated successfully";
	this.NETLIMSBRANCHDELETESUCCESS="Inactivated Branch Successfully";
	this.NETMDBRANCHDELETESUCCESS="Inactivated Branch Successfully";
	this.ADMINTOOLBARJSON ="/youNeverWait/json/toolbars/netlimsAccChangePwd.json";
	this.OLDPASSWRDREQUIRED="Old Password Required";
	this.NEWPASSWRDREQUIRED="New Password Required";
	this.CONFIRMPASSWRDREQUIRED="Confirm Password Required";
	this.PASSWRDMISMATCH="Password Do Not Match";
	this.PASSWRDCHANGDSUCCES="Password changed successfully";
	this.SYNCINTERVALREQUIRED="Synchronisation Interval Required";
	
	/*****************-------------netlimsaccount-----------**----*************/
	this.NETLIMSACCRIBBONURL="/youNeverWait/json/toolbars/netlimsGlobalToolbar.json";
	this.NETLIMSACCLEFTPANEURL="/youNeverWait/json/toolbars/netlimsLeftPaneToolbar.json";
	this.NETLIMSACCBRANCHLISTURL="/youNeverWait/ws/ui/lab/branchList";
	this.NEWBRANCHMODAL="netlimsAccBranchModal";
	this.NEWNETLIMSACCBRANCHJSON="/youNeverWait/json/new/newBranchNetlimsAcc.json";
	this.NETLIMSBRANCH="BRANCH";
	this.NETLIMSACCPAGEBRANCHJSON="/youNeverWait/json/toolbars/netlimsAccBranchPageToolBar.json";
	this.NETLIMSACCBRANCHLISTJSON="/youNeverWait/json/list/netlimsAccBranchTable.json";
	this.CREATENETLIMSACCBRANCHURL="/youNeverWait/ws/ui/lab/createBranch";
	this.TODAYSORDERLIST="Today's Branch Orders";
	this.NETLIMSACCTODAYSORDERLISTURL="/youNeverWait/json/list/netlimsAccBranchOrdersTable.json";
	this.NETLIMSACCTODAYSORDERLISTTABLEURL="/youNeverWait/ws/ui/lab/viewBranchOrders/";
	this.VIEWNETLIMSACCBRCHPAGEURL="/youNeverWait/json/view/viewNetlimsAccBranchDetails.json";
	this.VIEWNETLIMSACCBRCHURL="/youNeverWait/ws/ui/lab/viewBranch/";
	this.NETLIMSACCBRCHGENERAL="netlimsaccbrach";
	this.UPDATENETLIMSACCBRCHURL="/youNeverWait/ws/ui/lab/updateBranch";
	this.DELETENETLIMSBRANCHURL="/youNeverWait/ws/ui/lab/deleteBranch";
	this.NEWNETLIMSACCPASWRDCHGMODAL="changepwdModalNetLims";
	this.NEWNETLIMSACCPASWRDCHGJSON="/youNeverWait/json/changepassword/netlimsAccChangePassword.json";
	this.CHANGEPASSWORDURL="/youNeverWait/ws/ui/lab/changePassword";
	this.ORDERTYPEMODAL="orderTypeModalNetLims";
	this.NEWNETLIMSORDERTYPEJSON="/youNeverWait/json/OrderType.json";
	this.VIEWNETLIMSORDERTYPEURL="/youNeverWait/ws/ui/order/getOrderType/";
	this.VIEWNETLIMSSETORDERTYPEURL="/youNeverWait/ws/ui/order/setOrderType";
	this.NETLIMSACCSYNCMODAL="netlimsAccSycSetModal";
	this.NETLIMSACCSYNCJSON="/youNeverWait/json/new/netlimsAccsetSync.json";
	this.SYNCDATANETLIMSACCURL="/youNeverWait/ws/ui/lab/getBranchSyncDetails/";
	this.SETACCBRACHSYCURL="/youNeverWait/ws/ui/lab/setBranchSync";
	this.SHOWORDERMODAL="ShowOrdersModal";
	this.BRANCHNETLIMSORDERLIST='/youNeverWait/json/BranchNetlimsOrders.json';
	this.GETORDERLISTURL="/youNeverWait/ws/ui/lab/orderList";
	/*****************-------------netmdAccount-----------**----*************/
	this.NETMDACCRIBBONURL="/youNeverWait/json/toolbars/netMdGlobalToolBar.json";
	this.NETMDACCLEFTPANEURL="/youNeverWait/json/toolbars/netMdLeftPaneToolBar.json";
	this.NETMDACCBRANCHLISTURL="/youNeverWait/ws/ui/netMd/netmdBranchList";
	this.NETMDBRANCH="BRANCHNETMD";
	this.NETMDACCPAGEBRANCHJSON="/youNeverWait/json/toolbars/netmdAccBranchPageToolBar.json";
	this.NETMDACCBRANCHLISTJSON="/youNeverWait/json/list/netmdAccBranchTable.json";
	this.NEWNETMDACCBRANCHJSON="/youNeverWait/json/new/newBranchNetmdAcc.json";
	this.NEWNETMDACCBRANCHMODAL="netmdAccBranchModal";
	this.DEVICENUMBERREQUIRED="Number of devices Required";
	this.CREATENETMDACCBRANCHURL="/youNeverWait/ws/ui/netMd/createNetMdBranch";
	this.VIEWNETMDACCBRCHURL="/youNeverWait/ws/ui/netMd/viewNetMdBranch/";
	this.VIEWNETMDACCBRCHPAGEURL="/youNeverWait/json/view/viewNetmdAccBranchDetails.json";
	this.NETMDACCBRCHGENERAL="netmdaccbrach";
	this.UPDATENETMDACCBRCHURL="/youNeverWait/ws/ui/netMd/updateNetMdBranch";
	this.DELETENETMDACCBRANCHURL="/youNeverWait/ws/ui/netMd/deleteNetMdBranch/";
	this.ADMINNETMDACCTOOLBARJSON="/youNeverWait/json/toolbars/netMdAccChangePwd.json";
	this.NEWNETMDACCPASWRDCHGMODAL="changepwdModalNetMd";
	this.NEWNETMDACCPASWRDCHGJSON="/youNeverWait/json/changepassword/netMdAccChangePassword.json";
	this.CHANGEPASSWORDNETMDURL="/youNeverWait/ws/ui/netMd/changePassword";
	this.SHOWBILLLISTMODAL="ShowBillModal";
	this.BRANCHNETMDBILLLISTJSON="/youNeverWait/json/BranchAccBillList.json";
	this.GETBILLLISTURL="/youNeverWait/ws/ui/netMd/billList";
	this.NETMDACCSYNCMODAL="netMdAccSycSetModal";
	this.NETMDACCSYNCJSON="/youNeverWait/json/new/netmdAccBranchsetSync.json";
	this.SYNCDATANETMDACCURL="/youNeverWait/ws/ui/netMd/getBranchSyncDetails/";
	this.SETNETMDACCBRACHSYCURL="/youNeverWait/ws/ui/netMd/setNetMdBranchSync";
	/*****************-------------netrxAccount-----------**----*************/
	this.NETRXACCRIBBONURL="/youNeverWait/json/toolbars/netRxGlobalToolBar.json";
	this.NETRXACCLEFTPANEURL="/youNeverWait/json/toolbars/netRxLeftPaneToolBar.json";
	this.NETRXACCBRANCHLISTURL="/youNeverWait/ws/ui/netRx/netRxBranchList";
	this.NETRXBRANCH="BRANCHNETRX";
	this.NETRXACCPAGEBRANCHJSON="/youNeverWait/json/toolbars/netrxAccBranchPageToolBar.json";
	this.NETRXACCBRANCHLISTJSON="/youNeverWait/json/list/netrxAccBranchTable.json";
	this.NEWNETRXACCBRANCHJSON="/youNeverWait/json/new/newBranchNetrxAcc.json";
	this.NEWNETRXBRANCHMODAL="netrxAccBranchModal";
	this.CREATENETRXACCBRANCHURL="/youNeverWait/ws/ui/netRx/createNetRxBranch";
	this.VIEWNETRXACCBRCHPAGEURL="/youNeverWait/json/view/viewNetrxAccBranchDetails.json";
	this.NETRXACCBRCHGENERAL="netrxaccbrach";
	this.UPDATENETRXACCBRCHURL="/youNeverWait/ws/ui/netRx/updateNetRxBranch";
	this.VIEWNETRXACCBRCHURL="/youNeverWait/ws/ui/netRx/viewNetRxBranch/";
	this.DELETENETRXACCBRANCHURL="/youNeverWait/ws/ui/netRx/deleteNetRxBranch/";
	this.NETRXACCSYNCJSON="/youNeverWait/json/new/netrxAccBranchsetSync.json";
	this.NETRXACCSYNCMODAL="netrxAccBranchSycSetModal";
	this.SYNCDATANETRXACCURL="/youNeverWait/ws/ui/netRx/getBranchSyncDetails/";
	this.SETNETRXACCBRACHSYCURL="/youNeverWait/ws/ui/netRx/setNetRxBranchSync";
	this.ADMINNETRXACCTOOLBARJSON="/youNeverWait/json/toolbars/netRxAccChangePwd.json";
	this.NEWNETRXACCPASWRDCHGJSON="/youNeverWait/json/changepassword/netRxAccChangePassword.json";
	this.NEWNETRXACCPASWRDCHGMODAL="changepwdModalNetrx";
	this.CHANGEPASSWORDNETRXURL="/youNeverWait/ws/ui/netRx/changePassword";
	/****************************-----organizationAccount----****************************/
	this.ORGNZTNTABCREATEVIEWURL="/youNeverWait/json/view/organizationTabs.json";
	this.ORGTABOBJ="#tabs-1";
	this.ORGREPORTTABOBJ="#reportInfoTab";
	this.ORGNZTNREPORTTABVIEWURL="/youNeverWait/json/view/organizationReport.json";
	this.ORGNZTNREPORTFILTERVIEWURL="/youNeverWait/json/view/orgReportfiltr.json";
	this.ORGNZTNREPORTFILTERNETMDLISTURL="/youNeverWait/ws/ui/netMd/getNetMdBrnchList";
	this.REPORTGENERATEURL="/youNeverWait/ws/ui/orgn/report/generate";
	/*****************----------superadmin-------------**************/
	this.SUPERADMINRIBBONURL="/youNeverWait/json/toolbars/globalToolbar.json";
	this.SUPERADMINLEFTPANEURL="/youNeverWait/json/toolbars/leftPaneToolBar.json";
	/*****-----netlims---*****/
	this.NETLIMSBRANCHLISTURL="/youNeverWait/ws/ui/superAdmin/labList";
	this.NETLIMSLIST="NETLIMS LIST";
	this.NETLIMSPAGEJSON="/youNeverWait/json/toolbars/netlimsPageToolBar.json";
	this.NETLIMS="NETLIMS";
	this.NETLIMSLISTJSON="/youNeverWait/json/list/netlimsTable.json";
	this.NEWNETLIMSJSON="/youNeverWait/json/new/newNetLims.json";
	this.NEWNETLIMSMODAL="netlimsModal";
	this.CREATENETLIMSURL="/youNeverWait/ws/ui/superAdmin/createLab";
	this.NETLIMSCREATESUCCESS="Netlims Created Successfully";
	this.VIEWNETLIMSDETAILPAGEURL="/youNeverWait/json/view/viewnetlimsdetails.json";
	this.VIEWNETLIMSDETAILURL="/youNeverWait/ws/ui/superAdmin/viewLab/";
	this.UPDATENETLIMSURL="/youNeverWait/ws/ui/superAdmin/updateLab";
	this.NETLIMSGENERAL="netlims";
	this.DELETENETLIMSURL="/youNeverWait/ws/ui/superAdmin/deleteLab/";
	this.NETLIMSDELETESUCCESS="Inactivated netlims Successfully";
	this.VIEWNETLIMSORDERLISTURL="/youNeverWait/ws/ui/superAdmin/viewBranchOrders/";
	this.NETLIMSLABBRANCHLISTURL="/youNeverWait/ws/ui/superAdmin/branchList";
	this.CREATENETLIMSBRANCHURL="/youNeverWait/ws/ui/superAdmin/createBranch";
	this.UPDATENETLIMSBRCHURL="/youNeverWait/ws/ui/superAdmin/updateBranch";
	this.VIEWNETLIMSBRCHURL="/youNeverWait/ws/ui/superAdmin/viewBranch/";
	this.DELETENETLIMSLABBRANCHURL="/youNeverWait/ws/ui/superAdmin/deleteBranch";
	this.SYNCDATANETLIMSBRCHURL="/youNeverWait/ws/ui/lab/getBranchSyncDetails/";
	this.SETNETLIMSBRACHSYCURL="/youNeverWait/ws/ui/superAdmin/setBranchSync";
	this.GETNETLIMSBRANCHORDERLISTURL="/youNeverWait/ws/ui/superAdmin/orderList";
	this.NETLIMSBRNCHPAGEBRANCHJSON="/youNeverWait/json/toolbars/netLimsbranchPageToolBar.json";
	this.VIEWNETLIMSBRCHPAGEURL="/youNeverWait/json/view/viewbranchdetails.json";
	this.NEWMACIDCLEARMODAL="macIdClearModal";
	this.NEWNETLIMSBRANCHMACIDCLEARJSON="/youNeverWait/json/macidConfirm.json";
	this.SETNETLIMSBRACHCLEARMACURL="/youNeverWait/ws/ui/superAdmin/clearNetlimsMacId";
	this.HEALTHMONITORMODAL="graphHealthmonitorModal";
	this.GRAPHHEALTHMONITORJSON="/youNeverWait/json/graphHealth.json";
	this.HEALTHMONITORPATH="/youNeverWait/js/youneverwait/ui/netlims/netlimsbranch/branchHealthmonitor.js";
	this.VIEWHEALTHMONITORETLIMSBRCHURL='/youNeverWait/ws/ui/superAdmin/viewBranchSystemInfo/';
	this.UPDATEHEALTHMONITORURL="/youNeverWait/ws/ui/superAdmin/updateLabBranchSystemInfo";
	this.NETLIMSSYNCMODAL="netlimssyncmodal";
	this.SYNCDATANETLIMSURL="/youNeverWait/ws/ui/lab/getLabSyncDetails/";
	this.SETNETLIMSSYCURL="/youNeverWait/ws/ui/superAdmin/setLabSync";
	/***********netmd***************/
	this.NETMDBRANCHLISTURL="/youNeverWait/ws/ui/superAdmin/netmdList";
	this.NETMDLIST="NetMD LIST";
	this.NETMD="NETMD";
	this.NETMDPAGEJSON="/youNeverWait/json/toolbars/netmdPageToolBar.json";
	this.NETMDLISTJSON="/youNeverWait/json/list/netmdTable.json";
	this.NEWNETMDMODAL="netmdModal";
	this.NEWNETMDJSON="/youNeverWait/json/new/newNetMd.json";
	this.CREATENETMDURL="/youNeverWait/ws/ui/superAdmin/createNetMd";
	this.NETMDCREATESUCCESS="Netmd Created Successfully";
	this.UPDATENETMDURL="/youNeverWait/ws/ui/superAdmin/updateNetMd";
	this.VIEWNETMDDETAILPAGEURL="/youNeverWait/json/view/viewnetmddetails.json";
	this.VIEWNETMDDETAILURL="/youNeverWait/ws/ui/superAdmin/viewNetMd/";
	this.NETMDUPDATESUCCESS="Netmd Updated Successfully";
	this.NETMDGENERAL="netmd";
	this.NETMDDELETESUCCESS="Inactivated netmd Successfully";
	this.DELETENETMDURL="/youNeverWait/ws/ui/superAdmin/deleteNetMd/";
	this.NETMDPAGEBRANCHJSON="/youNeverWait/json/toolbars/branchPageToolBar.json";
	this.VIEWNETMDBRCHPAGEURL="/youNeverWait/json/view/viewNetmdBranchDetails.json";
	this.NETMDSUPERBRANCHLISTURL="/youNeverWait/ws/ui/superAdmin/netmdBranchList";
	this.CREATENETMDBRANCHURL="/youNeverWait/ws/ui/superAdmin/createNetMdBranch";
	this.UPDATENETMDBRCHURL="/youNeverWait/ws/ui/superAdmin/updateNetMdBranch";
	this.VIEWNETMDBRCHURL="/youNeverWait/ws/ui/superAdmin/viewNetMdBranch/";
	this.DELETENETMDBRANCHURL="/youNeverWait/ws/ui/superAdmin/deleteNetMdBranch/";
	this.SETNETMDBRACHSYCURL="/youNeverWait/ws/ui/superAdmin/setNetMdBranchSync";
	this.GETNETMDBILLLISTURL="/youNeverWait/ws/ui/superAdmin/billList";
	this.NEWNETMDMACIDCLEARMODAL="netmdmacIdClearModal";
	this.SETNETMDBRACHCLEARMACURL="/youNeverWait/ws/ui/superAdmin/clearNetMdMacId";
	this.SETNETMDBRACHCHANGEPRIMARYURL="/youNeverWait/ws/ui/superAdmin/makePrimary";
	this.NETMDSYNCMODAL="netmdsyncmodal";
	this.SYNCDATANETMDURL="/youNeverWait/ws/ui/netMd/getNetmdSyncDetails/";
	this.SETNETMDSYCURL="/youNeverWait/ws/ui/superAdmin/setNetMdSync";
	/*********organization********/
	this.ORGANZTNBRANCHLISTURL="/youNeverWait/ws/ui/superAdmin/getOrganisationList";
	this.ORGANZTNLIST="ORGANIZATION LIST";
	this.ORGANZTN="ORGANZTN";
	this.ORGANZTNPAGEJSON="/youNeverWait/json/toolbars/netmdPageToolBar.json";
	this.ORGANZTNLISTJSON="/youNeverWait/json/list/organizationTable.json";
	this.NEWORGANZTNMODAL="organizationModal";
	this.NEWORGANZTNJSON="/youNeverWait/json/new/newOrganization.json";
	this.CREATEORGNZURL="/youNeverWait/ws/ui/superAdmin/createOrganisation";
	this.ORGANZTNCREATESUCCESS="Organization Created Successfully";
	this.UPDATEORGNZURL="/youNeverWait/ws/ui/superAdmin/updateOrganisation";
	this.VIEWORGDETAILPAGEURL="/youNeverWait/json/view/viewOrganizationdetails.json";
	this.VIEWORGDETAILURL="/youNeverWait/ws/ui/superAdmin/viewOrganisation/";
	this.ORGNGENERAL="orgn";
	this.ORGANZTNDELETESUCCESS="Inactivated organization Successfully";
	this.DELETEORGURL="/youNeverWait/ws/ui/superAdmin/deleteOrganisation/";
	this.ORGNPAGEBRANCHJSON="/youNeverWait/json/toolbars/branchPageToolBar.json";
	//this.VIEWNETMDBRCHPAGEURL="/youNeverWait/json/view/viewNetmdBranchDetails.json";
	this.ORGAZSUPERUSERLISTURL="/youNeverWait/ws/ui/superAdmin/getOrganisationUserList";
	this.CREATEORGUSERURL="/youNeverWait/ws/ui/superAdmin/createOrganisationUser";
	this.UPDATEORGUSERURL="/youNeverWait/ws/ui/superAdmin/updateOrganisationUser";
	this.USERUPDATESUCCESS="User Updated Successfully";
	this.VIEWORGUSERURL="/youNeverWait/ws/ui/superAdmin/viewOrganisationUser/";
	this.DELETEORGUSERURL="/youNeverWait/ws/ui/superAdmin/deleteOrganisationUser/";
	this.ORGUSERDELETESUCCESS="Inactivated organization User Successfully";
	this.USERLIST="USER LIST";
	this.ORGNBRANCH="BRANCHORG";
	this.ORGNACCBRANCHLISTJSON="/youNeverWait/json/list/orgnAccUserTable.json";
	this.NEWORGACCUSERMODAL="organizationusermodal";
	this.NEWORGACCUSERJSON="/youNeverWait/json/new/newUserOrgAcc.json";
	this.ORGBRCHGENERAL="orgaccbrach";
	this.VIEWORGBRCHPAGEURL="/youNeverWait/json/view/viewOrgnznUserDetails.json";
	this.DEPARTMENTLISTURL="/youNeverWait/ws/ui/auth/getEnumsList/";
	this.USERTYPEREQUIRED="Usertype Required";
	this.USERTYPEINVALID="User type invalid";
	/*********netrx********/
	this.NETRXBRANCHLISTURL="/youNeverWait/ws/ui/superAdmin/netrxList";
	this.NETRX="NETRX";
	this.NETRXPAGEJSON="/youNeverWait/json/toolbars/netrxPageToolBar.json";
	this.NETRXLISTJSON="/youNeverWait/json/list/netrxTable.json";
	this.NETRXLIST="NETRX LIST";
	this.NEWNETRXMODAL="netrxmodal";
	this.NEWNETRXJSON="/youNeverWait/json/new/newNetRx.json";
	this.NETRXCREATESUCCESS="Netrx created Successfully";
	this.CREATENETRXURL="/youNeverWait/ws/ui/superAdmin/createNetRx";
	this.VIEWNETRXDETAILPAGEURL="/youNeverWait/json/view/viewnetrxdetails.json";
	this.VIEWNETRXDETAILURL="/youNeverWait/ws/ui/superAdmin/viewNetRx/";
	this.NETRXGENERAL="netrx";
	this.NETRXUPDATESUCCESS="Netrx Updated Successfully";
	this.UPDATENETRXURL="/youNeverWait/ws/ui/superAdmin/updateNetRx";
	this.DELETENETRXURL="/youNeverWait/ws/ui/superAdmin/deleteNetRx/";
	this.NETRXDELETESUCCESS="Netrx Inactivated Successfully";
	this.NETRXSUPERBRANCHLISTURL="/youNeverWait/ws/ui/superAdmin/netRxBranchList";
	this.CREATENETRXBRANCHURL="/youNeverWait/ws/ui/superAdmin/createNetRxBranch"
	this.VIEWNETRXBRCHPAGEURL="/youNeverWait/json/view/viewNetrxBranchDetails.json";
	this.UPDATENETRXBRCHURL="/youNeverWait/ws/ui/superAdmin/updateNetRxBranch";
	this.SETNETRXBRACHCLEARMACURL="/youNeverWait/ws/ui/superAdmin/clearNetRxMacId";
	this.NEWNETRXMACIDCLEARMODAL="netrxmacIdClearModal";
	this.SETNETRXBRACHSYCURL="/youNeverWait/ws/ui/superAdmin/setNetRxBranchSync";
	this.NETRXSYNCMODAL="netrxsyncmodal";
	this.SYNCDATANETRXURL="/youNeverWait/ws/ui/netRx/getNetrxSyncDetails/";
	this.SETNETRXSYCURL="/youNeverWait/ws/ui/superAdmin/setNetRxSync";
	/*****************-------------General-----------**----*************/
	this.NAME = "name";
	this.ADDRESS = "address";
	this.PHONE = "phone";
	this.MOBILE = "mobile";
	this.DESIGNATION = "designation";
	this.LOGINID = "loginid";
	this.PASSWORD = "password";
	this.EMAIL = "email";
	this.STDRATE="stdRate";
	this.PATIENTSECTION = "patientdiv";
	this.REFERRALSECTION = "referral";
	this.TESTSECTION = "testWorkArea";
	this.EMAILREQUIRED = "Email required";
	this.EMAILINVALID = "Enter valid email";
	this.NAMEREQUIRED = "Name required";
	this.NOTEREQUIRED= "Note required";
	this.PHONEREQUIRED="Phone number required";
	this.PHONEINVALID = "Enter valid landline number";
	this.MOBILEREQUIRED="Mobile number required";
	this.MOBILEINVALID = "Enter valid mobile number";
	this.NAMEINVALID = "Enter valid name";
	this.DESIGNATIONREQUIRED = "Designation required";
	this.LOGINREQUIRED = "Login Id required";
	this.PASSWORDREQUIRED = "Password required";
	this.SELECTSPECIMEN = "Select specimen"; 
	this.AMOUNTREQUIRED = "Amount required";
	this.DOCTORFEEREQUIRED = "Doctor fee required";
	this.COLLECTIONCHARGEREQUIRED = "Collection charge required";
	this.SELECTTYPE="Select type";
	this.NOSPECIALPKGINSIDESPECIALPKG="Can't enter specialpackage inside specialpackage";
	this.TESTDUPLICATION="Test already entered";
	this.GENERALLAYOUT="Only test having general layout should be entered";
	this.DELETEIMAGETAG='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="delete" src="/youNeverWait/images/remove-btn.png"></a>';
	this.TESTAUTOFIELD='testAutoField';
	this.TESTREQUIRED="Test required";
	this.STDRATEREQUIRED="Rate required";
	this.SELECTSPECIMEN="Select specimen";
	this.VALIDTEST="Enter valid test/package";
	this.AUTOFIELD="autoField";
	this.LISTREQUIRED=" List required";	
	this.SPECIALTESTSGETURL ="/youNeverWait/ws/ui/test/getSpecimenOnDemandTests";
	this.GETSPECIMENSURL = "/youNeverWait/ws/ui/order/getSpecimens";
	this.PERMISSIONURL = "/youNeverWait/ws/ui/admin/pageControles/";	
	this.ACTIONCREATE ="new";
	this.ACTIONDELETE = "Delete";
	this.ACTIONUPDATE = "Update";
	this.ACTIONVIEW = "view";
	this.ACTIONADD="Add";
	this.MODEPRINT = "print";
	this.SELECTEDROWCOLOR = "background-color:#DCDCDC;"
	this.GETERRORCODEURL="/youNeverWait/ws/ui/auth/getErrorCodes";
	this.NETMDTRANSFERURL = "/youNeverWait/ws/ui/sync/transferResultToNetMd";
	this.GETORDERIDFROMBARCODE = "/youNeverWait/ws/ui/test/getOrderIdFromBarcode/";
	this.EQUALSOPERATOR = "eq";
	this.LIKEOPERATOR ='like';
	this.ERROR = "Error";
	this.STATUSENUM="StatusEnum";
	this.PAYSTATUSENUM = "CollectStatusEnum";
	this.PRIORITYENUM="PriorityEnum";
	
	this.DEFAULTCOLLECTEDAT = "Jeeva";
	this.LISTDUPLICATION=" List already entered"
	this.COLLECTED="Collected";
	this.NOTCOLLECTED= "Not Collected";
	this.LAYOUTGENERAL="General";
	this.LAYOUTGENERALONE="GeneralOne";
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
	this.LAYOUTBETAHCG = "BetaHCGLayout";
	this.ORDERSEQMODAL = "OrderSequenceModal";
	this.CREATESEQORDERUI="/weblims/json/newOrderSequence.json";
	this.UPDATESEQUENCEURL = "/youNeverWait/ws/ui/order/updateTestSequence";
	/*****************-------------Specimen-----------**----*************/
	this.SPECIMENFILTERKEY = "SpecimenPropertyEnum";
	this.SPECIMENNAME = "specimenName";
	this.SPECIMENLISTURL="/youNeverWait/ws/ui/superAdmin/testSpecimenList";
	this.SPECIMENLISTTABLEURL="/youNeverWait/json/list/specimenTable.json";
	this.VIEWSPECIMENPAGEURL = "/youNeverWait/json/viewSpecimen.json";
	this.SPECIMENGENERALPTBURL='/youNeverWait/json/toolbars/viewGeneralToolbar.json';
	this.SPECIMEN="specimen";
	this.SPECIMENPAGETOOLBAR="/youNeverWait/json/toolbars/specimenPageToolBar.json";
	this.SPECIMENGENERAL = "specimenGeneral";
	this.SPECIMENBUTTONSCONTAINER = 'specimenViewButtonsDiv';
	this.SPECIMENVIEWINFOBUTTONS = "/youNeverWait/json/specimenViewbtns.json";
	this.SPECIMENVIEWINFO = "Specimen - ";
	this.SPECIMENS = "Specimens";
	this.CREATESPECIMENUI = "/youNeverWait/json/new/newSpecimen.json";
	this.CREATESPECIMENURL = "/youNeverWait/ws/ui/specimen/createSpecimen";
	this.UPDATESPECIMENURL = "/youNeverWait/ws/ui/specimen/updateSpecimen";
	this.DELETESPECIMENURL = "/youNeverWait/ws/ui/specimen/deleteSpecimen/";
	this.VIEWSPECIMENURL = "/youNeverWait/ws/ui/specimen/viewSpecimen/";
	this.SPECIMENMODAL = "specimenModal";
	this.SPECIMENCREATESUCCESS = "Specimen created successfully";
	this.SPECIMENDELETESUCCESS = "Specimen deleted successfully";
	this.SPECIMENUPDATESUCCESS = "Specimen updated successfully";
	this.SELECTONESPECIMEN = "Select atleast one specimen";
	this.SELECTONESPECIMENONLY = "Select only one specimen";
	this.SPECIMENDELETECONFIRM = "Do you really want to delete specimen ";
	/*****************-----------------------------------****************/
	
	/*****************---------------Order----------------****************/
	this.JV="JV";
	this.AG="AG";
	this.IB="IB";
	this.BO="BO";
	this.ORDERFILTERKEY = "OrderPropertyEnum";
	this.ORDERNAME = "orderName";
	this.ORDERLISTURL="/youNeverWait/ws/ui/order/list";
	this.ORDERLISTTABLEURL="/weblims/json/tables/allOrderTable.json";
	this.VIEWORDERPAGEURL = "/weblims/json/viewOrder.json";
	this.ORDERGENERALPTBURL='/weblims/json/toolbars/viewGeneralToolbar.json';
	this.ORDER="order";
	this.ORDERPAGETOOLBAR="orderPageToolBar";
	this.ORDERGENERAL = "orderGeneral";
	this.ORDERVIEWINFO = "Order - ";
	this.ORDERS = "Orders";
	this.CREATEORDERUI = "/weblims/json/newOrder.json";
	this.CREATEORDERURL = "/youNeverWait/ws/ui/order/create";
	this.UPDATEORDERURL = "/youNeverWait/ws/ui/order/updateSpecimen";
	this.DELETEORDERURL = "/youNeverWait/ws/ui/order/deleteSpecimen/";
	this.UPDATEPAYMENTURL = "/youNeverWait/ws/ui/order/updatePayment";
	this.VIEWORDERURL = "/youNeverWait/ws/ui/order/view/";
	this.ORDERMODAL = "orderModal";
	this.ORDERCREATESUCCESS = "Order created successfully";
	this.ORDERDELETESUCCESS = "Order deleted successfully";
	this.ORDERUPDATESUCCESS = "Order updated successfully";
	this.SELECTONEBRANCH = "Select atleast one branch";
	this.SELECTONEBRANCHONLY = "Select only one branch";
	this.ORDERDELETECONFIRM = "Do you really want to delete specimen ";
	this.ORDERBUTTONSCONTAINER = 'orderViewButtonsDiv';
	this.ORDERVIEWINFOBUTTONS = "orderViewButtons";
	this.ORDERS = "Orders";
	this.RESULTNOTREADY ="Results not ready";
	this.SELECTREADYRESULTS ="Selection not contains ready results";
	this.ORDERHOLDORCANCEL = "Order is on Hold or Cancelled ";
	this.SAVEORDERRESULTURL = "/youNeverWait/ws/ui/result/saveOrderResult";
	this.GETORDERRESULTURL = "/youNeverWait/ws/ui/result/getOrderResult/";
	this.GETORDERRESULTMAILURL="/youNeverWait/ws/ui/result/emailOrderResult";
	this.COLLECTIONCHARGE = "Collection Charge";
	this.DOCTORFEE = "Doctor Fee";
	this.PREMISES = "Premises";
	this.AGENT = "Agent";
	this.BLANKETORDER="Blanket Order";
	this.OTHERBRANCH="Other Branch";
	this.BRANCH = "Branch";
	this.DOCTOR = "Doctor";
	this.CONFIRMCLEARBILLTABLE = "This will clear the entered tests. Do you want to continue?";
	this.TP = "TP";
	this.SPECIALPACKAGE = "Special Package";
	this.ONDEMAND = "OnDemand";
	this.GETDISCOUNTVALUE="/youNeverWait/ws/ui/admin/getDiscountValue";
	this.COLLECTIONNOTBEZERO = "Collection charge should not be zero.";
	this.DOCTORAMOUNTNOTZERO = "Doctor charge should not be zero";
	this.GETAGENTBYREFERRAL = "/youNeverWait/ws/ui/agent/getAgentByReferral/";
	this.GETAGENTBYFACILITY = "/youNeverWait/ws/ui/agent/getAgentByFacility/";
	this.GETFACILITYBYAREA = "/youNeverWait/ws/ui/agent/getFacilityByArea/";
	this.GETFACILITIES = "/youNeverWait/ws/ui/agent/getFacilities/";
	this.REFNOTBENULL = "Ref By not be null/invalid";
	this.PERCENTAGE = "Percentage";
	this.PERCENTAGESYMBOL = "(%)";
	this.RUPEESYMBOL = "(<img src='/youNeverWait/images/rupee-symbel.png'/>)";
	this.BLANKETID_NAMEINVALID = "Invalid blanket id/name";
	this.BLANKETORDERINVALID = "Blanket Order not be null/invalid";
	this.AGENTNOTBENULL = "Agent not be null/invalid";
	this.FACILITYNOTBENULL = "Facility not be null/invalid";
	this.BRANCHNOTBENULL_INVALID = "Branch not be null/invalid";
	this.BRANCHNOTBENULL = "Branch not be null";
	this.BLANKETORDEREXISTS = "Blanket order exists";
	this.BLANKETORDERTESTSURL = "/youNeverWait/ws/ui/order/getTestsByBOUid/";
	this.GETTESTRATEBYUID = "/youNeverWait/ws/ui/order/getTestRateByUid/";
	this.BLANKETID_NAMENOTNULL="Blanket Id/Name not be null";
	this.SELECTFACILITY = "Select facility";
	this.SELECTAREA = "Select area";
	this.SELECTAGENT = "Select agent";
	this.DAILY = "Daily";
	this.MONTHLY = "Monthly";
	this.SELECTPAYMENTMODE = "Select mode";
	
	this.CREATESPECIMENSTATUSUI="/weblims/json/changeSpecimenStatus.json";
	this.SPECIMENSTATUSMODAL="specimenStatusModal";
	this.NOSPECIMEN = "No specimen";
	this.ORDERSTATUSMODAL="statusModal";
	this.CREATEORDERSTATUSUI="/weblims/json/changeStatus.json";
	this.UPDATEORDERSTATUS="/youNeverWait/ws/ui/order/updateOrderStatus";
	this.CREATECOLLECTIONCHARGEUI="/weblims/json/viewCollectionCharge.json";
	this.COLLECTIONCHARGEMODAL="collectionModal";
	this.GETBARCODE = "/youNeverWait/ws/ui/remote/getBarcodeNo/";
	this.VIEWHISTORYPAGEURL = "/weblims/json/tables/orderHistory.json";
	this.GETORDERHISTORY = "/youNeverWait/ws/ui/order/viewHistory/";
	this.UPDATECOLLECTIONCHARGE = "/youNeverWait/ws/ui/order/updateCollectedCharges";
	this.COLLECTIONCHARGEUPDATESUCCESS = "Charges updated successfully";
	this.ISSPECIMENCOLLECTED = "/youNeverWait/ws/ui/result/isSpecimenCollected";
	this.VIEWTESTRESULT="/youNeverWait/ws/ui/result/viewTestResult";
	this.UPDATETESTRESULT = "/youNeverWait/ws/ui/result/updateTestResult";
	this.SELECTTEST_TESTPACKAGE ="Select test/test package";
	this.SELECTONETEST_TESTPACKAGE="Select only one test/test package";
	this.CREATERESULTTRANSFERUI="/weblims/json/transferSendForm.json";
	this.RESULTTRANSFERMODAL = "transferModal";
	this.CREATERESULTMAILUI="/weblims/json/emailSendForm.json";
	this.RESULTMAILMODAL = "emailModal";
	this.BILLHEADER1 = "BillHead1";
	this.BILLHEADER2 = "BillHead2";
	this.BILLFOOTER1 = "BillFooter1";
	this.BILLFOOTER2 = "BillFooter2";
	/*****************-----------------------------------****************/
	
	/*****************---------------Referral----------------****************/
	this.VIEWREFERRALURL = "/youNeverWait/ws/ui/referral/view/";
	this.REFERRALCREATESUCCESS ="Referral created successfully";
	/*****************-----------------------------------****************/
	/*****************---------------TestPackage----------------****************/
	this.TESTPACKAGE="testPackage";
	this.TESTPACKAGES="Test Packages";
	this.TESTPACKAGEPAGETOOLBAR="testPackagePageToolBar";
	this.TESTPACKAGEFILTERKEY = "TestPkgPropertyEnum";
	this.TESTPACKAGELISTTABLEURL="/weblims/json/tables/testPkgTable.json";
	this.TESTPACKAGELISTURL = "/youNeverWait/ws/ui/test/getTestPkgs";
	this.CREATETESTPACKAGEUI = "/weblims/json/newTestPkg.json";
	this.TESTPACKAGEMODAL = "testPackageModal";
	this.TESTPACKAGEDELETESUCCESS = "TestPackage deleted successfully";
	this.TESTPACKAGECREATESUCCESS = "TestPackage created successfully";
	this.TESTPACKAGEGENERAL = "testPackageGeneral";
	this.TESTPACKAGEGENERALPTBURL='/weblims/json/toolbars/viewGeneralToolbar.json';
	this.VIEWTESTPACKAGEPAGEURL = "/weblims/json/viewTestPackage.json";
	this.TESTPACKAGEVIEWINFOBUTTONS = "testPackageViewButtons";
	this.TESTPACKAGEBUTTONSCONTAINER = 'testPackageViewButtonsDiv';
	this.TESTPACKAGEVIEWINFO = "TestPackage -";
	this.CREATETESTPACKAGEURL = "/youNeverWait/ws/ui/test/addTestPkg";
	this.UPDATETESTPACKAGEURL = "/youNeverWait/ws/ui/test/updateTestPkg";
	this.DELETETESTPACKAGEURL = "/youNeverWait/ws/ui/test/deleteTestPkg/";
	this.VIEWTESTPACKAGEURL = "/youNeverWait/ws/ui/test/viewTestPkg/";
	this.GETTESTPACKAGERATEURL="/youNeverWait/ws/ui/order/getTestRateByUid/";
	this.SELECTONETESTPACKAGE = "Select atleast one package";
	this.SELECTONETESTPACKAGEONLY = "Select only one package";
	this.TESTPACKAGEDELETECONFIRM = "Do you really want to delete package ";
	this.NORMALPACKAGE="Normal Package";
	/*****************-----------------------------------****************/
	this.GETDISCOUNTURL = "/youNeverWait/ws/ui/admin/viewDiscount/";
	/*****************-----------------------------------****************/
	this.PRINTSETUPMODAL = "printSetupModal";
	this.CREATEPRINTSETTINGS = "/weblims/json/printSetup.json";
	
	this.CREATEREFERRALURL = "/youNeverWait/ws/ui/referral/create";
	/******************---------------------***********************/
	/*****************---------------Setting----------------****************/
	this.SETTINGLISTURL="/weblims/ws/ui/result/settinglist";
	this.SETTING="setting";
	this.SETTINGTITLE="Settings";
	this.CREATESETTINGURL="/weblims/ws/ui/result/createSetting";
	this.DELETESETTINGURL="/weblims/ws/ui/result/deleteSetting/";
	this.SETTINGPAGETOOLBAR="settingPageToolBar";
	this.SETTINGFILTERKEY="SettingProperyEnum";
	this.SETTINGLISTTABLEURL="/weblims/json/tables/settingTable.json";
    this.CREATESETTINGUI="/weblims/json/newSetting.json";
	this.SETTINGMODAL="settingModal";
	this.SETTINGCREATESUCCESS="Setting created successfully";
	this.VIEWSETTINGURL="/youNeverWait/ws/ui/result/viewSetting/";
	this.VIEWSETTINGBYNAMEURL="/youNeverWait/ws/ui/settings/getByName/";
	this.VIEWSETTINGPAGEURL="/weblims/json/viewSetting.json";
	this.SETTINGVIEWINFOBUTTONS="settingViewButtons";
	this.SETTINGBUTTONSCONTAINER="settingViewButtonsDiv";
	this.SETTINGVIEWINFO="Setting";
	this.SETTINGGENERAL="settingGeneral";
	this.SETTINGGENERALPTBURL="/weblims/json/toolbars/viewGeneralToolbar.json";
	this.UPDATESETTINGURL="/youNeverWait/ws/ui/result/updateSetting";
	this.SETTINGUPDATESUCCESS="Setting updated successfully";
	this.SETTINGDELETECONFIRM="Do you really want to delete  ";
	this.SETTINGDELETESUCCESS="Setting deleted successfully"
	/*************--------------------------------------------------**********/
	
}