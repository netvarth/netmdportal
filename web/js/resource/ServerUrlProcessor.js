function ServerUrlProcessor() {
	
	
	 this.setUrl=function(url) {
		this.url = url;
		
	}

	this.post = function(param) {
		var postResponse;
		$j("#dvLoading").show();
		jQuery.ajax({
			type: "POST",
			url: this.url,
			data: JSON.stringify(param),
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			async: false,
			success:function(response) {
				if(response==null){
				//	window.location.href=pageHandler.getHomePageUrl();
				//	location.reload();
					return false;
				}	
				else{
					postResponse = response;
				}
				$j("#dvLoading").hide();
			},
			error: function(xhr, ajaxOptions, thrownError) {
				alert(thrownError);
				postResponse = false;	
			//	window.location.href=pageHandler.getHomePageUrl();
			//	location.reload();  
			}
		});	
		return postResponse;	
	}

	this.get = function() {
		var response;
		var getCall=jQuery.getJSON(this.url,function(result){
			response=result;
		});

		if(response==undefined) {
			response=false;
		//	window.location.href=pageHandler.getHomePageUrl();
		//	location.reload();
		}
		return response;	
	} 
}	