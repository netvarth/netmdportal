function text(button){this.result=function(){var pTag=$j('<p/>');var spanTag=$j('<span/>');var title=button.title;if(button.image)title+=' (<img src="'+button.image+'"/>)';spanTag.html(title);var brTag=$j('<br/>');if(button.idforInner){var innerSpanTag=$j('<span/>');innerSpanTag.attr('id',button.idforInner);spanTag.append(innerSpanTag)}pTag.append(spanTag);if(button.required){spanTag=$j('<span class="req_astrisk">*</span>');if(button.spanId)spanTag.attr('id',button.spanId);pTag.append(spanTag)}pTag.append(brTag);var inputTag=$j('<input/>');inputTag.attr('type',button.type);if(button.name)inputTag.attr('name',button.name);if(button.maxlength)inputTag.attr('maxlength',button.maxlength);if(button.id)inputTag.attr('id',button.id);if(button.placeHolder)inputTag.attr('placeHolder',button.placeHolder);if(button.className)inputTag.attr('class',button.className);if(button.style)inputTag.attr('style',button.style);if(button.readonly)inputTag.attr('readonly',button.readonly);if(button.tabIndex)inputTag.attr('tabIndex',button.tabIndex);pTag.append(inputTag);return pTag}}