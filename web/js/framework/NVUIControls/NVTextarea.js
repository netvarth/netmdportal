function textarea(button) {

	this.result = function() {

		var pTag = $j('<p/>'); // Create a  ptag [outer-1]
		var spanTag = $j('<span/>'); // creating a span tag inside the ptag
		spanTag.html(button.title); //Add data inside the span Tag
		var brTag = $j('<br/>'); // Create a br for new Line
		pTag.append(spanTag); //Add the span tag inside the P tag
		pTag.append(brTag); // Add the br line tag inside the P tag
		
		var textareaTag = $j('<textarea/>');

		if(button.name) textareaTag.attr('name',button.name);
		if(button.id) textareaTag.attr('id',button.id);
		if(button.className) textareaTag.attr('class',button.className);
		if(button.tabIndex) textareaTag.attr('tabIndex',button.tabIndex);
		if(button.style) textareaTag.attr('style',button.style)
		pTag.append(textareaTag);
		return pTag;
	};
}