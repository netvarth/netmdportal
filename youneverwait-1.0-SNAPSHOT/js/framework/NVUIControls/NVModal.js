function Modal(modaldata,resultId) {
    this.result = function () {
        var mainDiv = $j('<div/>');
        var modalTitleDiv = $j('<div class="reveal-modal-head"/>');
        modalTitleDiv.html('<h1>' + modaldata.title + '</h1>');
        mainDiv.append(modalTitleDiv);
        var modalContentDiv = $j('<div class="reveal-modal-content"/>');
        $j(modaldata.data).each(function (index, myform) {
            var contentForm = new form(myform,resultId);
            modalContentDiv.append(contentForm.result)
        });
        mainDiv.append(modalContentDiv);
        var CloseModalTag = $j('<a class="close-reveal-modal">&#215;</a>');
        mainDiv.append(CloseModalTag);
        return mainDiv
    }
}