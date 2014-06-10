function resultpatient(contents) {
    this.result = function () {
        var content = $j('<div/>');
        var table = $j('<div></div>');
        var subdiv;
        if ((contents.resultList) != "") {
            $j(contents.resultList).each(function (ResultContentIndex, result) {
                var common = $j('<div class="common"/>');
                var testname = "";
                if (result.testName == null) {
                    testname = ""
                } else {
                    testname = result.testName
                }
                var newResult = $j('<div class="result boldtable" ><div><label style="margin-left: 4%;">' + result.time + '</label><label style="margin-left: 35%;">' + testname + '</label><div  style="margin: -2% 0% 1% 85%;"><input type=button value="View Result"class="stdbtn ViewResult"></div></div></div>');
                common.append(newResult);
                subdiv = $j('<div  class="view boldtable"style="display:none;width: 75%;margin-left: 10%;"></div>');
                var viewresult = $j('<div class="boldtable patientresult"style="overflow: hidden;"></div>');
                subdiv.append(viewresult);
                common.append(subdiv);
                table.append(common)
            });
            content.append(table)
        }
		else{var msg=$j('<div style="font-weight: bold;" align="center">No results available</div>');
				table.append(msg)
				content.append(table)
			}
        return content
    }
}