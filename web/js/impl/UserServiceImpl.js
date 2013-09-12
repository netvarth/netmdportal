function UserServiceImpl () {
	
	this.setTableValues = function(tableObj, userResult) {
	
		$j(tableObj).dataTable().fnClearTable();
		if(userResult.user) {
			if(userResult.user.length>0) {			
				$j(userResult.user).each(function(index, user) {
					var id=user.uid;
					var rowData=$j(tableObj).dataTable().fnAddData([id,user.name,user.address,user.phone,user.roleName]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","userIdCol Ustyle");
				});
			}		
		}
	}
	
}

UserServiceImpl.prototype.createUser=function (userObj) {
	ajaxProcessor.setUrl(constants.CREATEUSERURL);
	return ajaxProcessor.post(userObj);
}
UserServiceImpl.prototype.updateUser=function(userObj) {
	ajaxProcessor.setUrl(constants.UPDATEUSERURL);
	return ajaxProcessor.post(userObj);
}
UserServiceImpl.prototype.deleteUser=function(userId) {
	ajaxProcessor.setUrl(constants.DELETEUSERURL + userId);
	return ajaxProcessor.get();
}
UserServiceImpl.prototype.viewUser=function(userId) {
	ajaxProcessor.setUrl(constants.VIEWUSERURL + userId);
	return ajaxProcessor.get();
}