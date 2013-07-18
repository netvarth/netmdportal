function form(formdata,resultId) {
    this.result = function () {
        var myForm = $j('<form/>');
        if (formdata.name) myForm.attr('name', formdata.name);
        if (formdata.id) myForm.attr('id', formdata.id);
        if (formdata.style) myForm.attr('style', formdata.style);
        $j(formdata.content).each(function (index, group) {
            var sepDiv = $j('<div/>');
            if (group.className) sepDiv.attr('class', group.className);
            if (group.id) sepDiv.attr('id', group.id);
            if (group.style) sepDiv.attr('style', group.style);
            var boxDiv = $j('<div/>');
            boxDiv.attr('class', 'box');
            var boxcontentDiv = $j('<div/>');
            boxcontentDiv.attr('class', 'box-content');
            $j(group.data).each(function (index, subgroup) {
                var cont = new Container(subgroup,resultId);
                boxcontentDiv.append(cont.result)
            });
            boxDiv.append(boxcontentDiv);
            sepDiv.append(boxDiv);
            myForm.append(sepDiv)
        });
        return myForm
    }
}