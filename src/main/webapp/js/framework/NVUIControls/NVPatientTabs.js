function patienttabs(jsondata) {
  
	this.result = function() {
		var maindiv=$j('<div/>');
		maindiv.attr('id','tabs-nohdr');
		if(jsondata!=null)
		{
		var ulTag=$j('<ul style="height:10%;margin-left:30%">');
			$j(jsondata).each(function(index,subcontent){
				var divTag = $j('<div/>');
				var pTag=$j('<p>');
				pTag.attr('id',subcontent.paraid);
				pTag.attr('name',subcontent.paraid);
				divTag.attr('id',subcontent.id);
				pTag.append(subcontent.details);
				divTag.append(pTag);
				maindiv.append(divTag)
				var liTag=$j('<li></li>');
				var aTag=$j('<a href="#'+subcontent.id +'"class="aTag'+subcontent.id +'"><div class="imagediv"></div><div class="divcontent">'+subcontent.title+'</div></a>');
				/*if(subcontent.image){
				   var image=$j('<div><img src='+subcontent.image+' class="icon'+subcontent.id +'"></div>');
				   aTag.append(image);
				   }*/
				
				liTag.append(aTag);
				liTag.attr('class',subcontent.id);
				liTag.attr('style',subcontent.style);
				ulTag.append(liTag);
			});
			maindiv.prepend(ulTag);
		}

		return maindiv;
	}
}