function reportModal(modaldata,title){this.result=function(){var mainDiv=$j('<div/>');var modalTitleDiv=$j('<div class="reveal-modal-head"/>');var headtitle='<h1>'+title+'</h1>';modalTitleDiv.html(headtitle);mainDiv.append(modalTitleDiv);var modalContentDiv=$j('<div class="reveal-modal-content"/>');var boxDiv=$j('<div/>');boxDiv.attr('class','box');var boxcontentDiv=$j('<div/>');boxcontentDiv.attr('class','box-content');boxcontentDiv.attr('style','padding:5px 0% 5px 15%');boxcontentDiv.append(modaldata);boxDiv.append(boxcontentDiv);modalContentDiv.append(boxDiv);mainDiv.append(modalContentDiv);var CloseModalTag=$j('<a class="close-reveal-modal">&#215;</a>');mainDiv.append(CloseModalTag);return mainDiv}}