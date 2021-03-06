function tabs(jsondata) {
   this.result = function () {
        var maindiv = $j('<div/>');
        maindiv.attr('id', 'tabs-nohdr');
        if (jsondata != null) {
            var ulTag = $j('<ul>');
            $j(jsondata).each(function (index, subcontent) {
                var divTag = $j('<div/>');
                var pTag = $j('<p>');
                pTag.attr('id', subcontent.paraid);
                pTag.attr('name', subcontent.paraid);
                divTag.attr('id', subcontent.id);
                pTag.append(subcontent.details);
                divTag.append(pTag);
                maindiv.append(divTag);
				var liTag = $j('<li><a href="#' + subcontent.id + '">' + subcontent.title + '</a>');
                liTag.attr('class', subcontent.id);
                liTag.attr('style', subcontent.style);
                ulTag.append(liTag)
            });
            maindiv.prepend(ulTag)
        }
        return maindiv;
    }
}