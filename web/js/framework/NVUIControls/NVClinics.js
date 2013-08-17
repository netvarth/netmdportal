function clinics(contents) {
this.result =function(){ 
		var content =$j('<div></div>');
		var calenderui=$j('<ul class="creteapp" style="margin-left: -1px;display:none;height:6%;"><h1 id="pageTitle"></h1><div id="pageToolBar-Container"></div></ul>');
		content.append(calenderui);
		
		if(contents.netmdBranch!=""){
		var nextPrev=$j('<div class="nextprev" style="margin: -1% 0% 1% 0%"><div style="margin:1% 0% 0% 90%;"><input type="button" class="prevbtn"><input type="text"readonly="true" style="width:25%;border:2px solid #a1a1a1;border-radius:5px;" id="prevnext"><input type="button"class="nextbtn"></div></div>');
		 content.append(nextPrev);
		var table=$j('<div class="branch"> </div>');
		var subdivcurr;
		var mobile="";
		var phone="";
		var addres="";
		$j(contents.netmdBranch).each(function(ResultContentIndex,netmd){
			 if(netmd.address==null){addres="";}else{addres=netmd.address} 
			 if(netmd.mobile==null){mobile="";}else{mobile=netmd.mobile}
			 if(netmd.phone==null){phone="";}else{phone=netmd.phone}
				var common=$j('<div class="common"/>');
				var newrow=$j('<div class="boldtable clinics"></div>');
				var divleft=$j('<div class="clinicleft"></div>');
				var divcenter=$j('<div class="clinicCentr" ></div>');
				var divright=$j('<div class="clinicright" ></div>');
				var pTag=$j('<div  style="height: 10%;"><p class="phead">'+netmd.name+'</p>'+'<p>'+addres+'</p>'+'<div class="pMobile"><p>'+mobile+'</p></div>'+'<div class="pPhone"><p>'+phone+'</p></div></div>');
				divcenter.append(pTag);
				var buttonTag=$j('<div style="float: right;margin: 10px 10px 0px 0px;"><p><a href="#"><input type="button" name="'+netmd.globalId+'" class="doctor doctorbeforeswitch "></a></p></div>');	
                divcenter.append(buttonTag);
                 newrow.append(divleft);
				 newrow.append(divcenter);
				 newrow.append(divright);
				common.append(newrow);
				subdivcurr=$j('<div id="'+netmd.globalId+'_'+netmd.netmdId+'" class="view boldtabledoc"style="display:none;width: 85%;margin-left: 5%;"></div>');	
				common.append(subdivcurr);
				table.append(common);		
		 });
		 content.append(table);
		  
		} else{var msage=$j('<div style="font-weight: bold;" align="center">There are no clinics</div>');
				content.append(msage);
			  }
		return content;
	};
}	
