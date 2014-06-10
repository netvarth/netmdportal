function organztnTABCreator() {}

organztnTABCreator.prototype.create = function (url, parent) {
   ajaxProcessor.setUrl(url);
    var tabdata = ajaxProcessor.get();
    $j(parent).html(new tabs(tabdata).result);
    $j(parent).tabs()
}