//$j(document).ready(function () {
	
	var branchId = getSelectedBranchId("#branchNetlimsAcc");
	//alert(branchId);
	var response=getRequestData('/youNeverWait/ws/ui/superAdmin/viewBranchSystemInfo/'+branchId);
	//alert(JSON.stringify(response));
	var brName=response.branchName;
	$j('#graphHealthmonitorModal h1').text("Health Monitor-"+brName);
	var memory = [];
	var cpu = [];
	var harddisk = [];
	var lastTime=[];
	var index = 1325376000000;
	$j(response.healthMonitorList).each(function(healthIndex, health) {
		var datetime=[];
		datetime.push(health.createdDateTime);
		lastTime.push(datetime);
		
		var cpuVal = [];
		cpuVal.push(index);
		cpuVal.push(health.cpuUsage);
		cpu.push(cpuVal);
		
		var memoryVal = [];
		memoryVal.push(index);
		memoryVal.push(health.memoryUsage);
		memory.push(memoryVal);
		
		var harddiskVal = [];
		harddiskVal.push(index);
		harddiskVal.push(health.hardDiskSpaceUasge);
		harddisk.push(harddiskVal);
		
		index+=2678400000;
	});
	
	
	var lastInterval="Last 10 Intervals Till("+lastTime[9]+")";
    var data1 = [
        {
            label: "Memory",
            data: memory,
            bars: {
                show: true,
                barWidth: 12*24*60*60*500,
                fill: true,
                lineWidth: 1,
                order: 1,
                fillColor:  "#AA4643"
            },
            color: "#AA4643"
        },
        {
            label: "CPU",
            data: cpu,
            bars: {
                show: true,
                barWidth: 12*24*60*60*500,
                fill: true,
                lineWidth: 1,
                order: 2,
                fillColor:  "#89A54E"
            },
            color: "#89A54E"
        },
        {
            label: "Hard Disk",
            data: harddisk,
            bars: {
                show: true,
                barWidth: 12*24*60*60*500,
                fill: true,
                lineWidth: 1,
                order: 3,
                fillColor:  "#4572A7"
            },
            color: "#4572A7"
        }
    ];
 
    $j.plot($j("#healthdiv"), data1, {
        xaxis: {
           // min: (new Date(2011, 11, 15)).getTime(),
           // max: (new Date(2012, 04, 18)).getTime(),
            mode: "time",
            timeformat: "%b",
            tickSize: [1, "month"],
            monthNames: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"],
            tickLength: 0, // hide gridlines
            axisLabel: lastInterval,
            axisLabelUseCanvas: false,
            axisLabelFontSizePixels: 12,
            axisLabelFontFamily: 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
            axisLabelPadding: 5
        },
        yaxis: {
            axisLabel: 'Usage(%)',
            axisLabelUseCanvas: false,
            axisLabelFontSizePixels: 12,
            axisLabelFontFamily: 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
            axisLabelPadding: 5
        },
        grid: {
            hoverable: true,
            clickable: false,
            borderWidth: 1
        },
        legend: {
            labelBoxBorderColor: "none",
            position: "right"
        },
        series: {
            shadowSize: 1
        }
    });
 
    function showTooltip(x, y, contents, z) {
        $j('<div id="flot-tooltip">' + contents + '</div>').css({
            top: y - 20,
            left: x - 90,
            'border-color': z,
        }).appendTo("body").show();
    }
 
    function getMonthName(newTimestamp) {
        var d = new Date(newTimestamp);
 
        var numericMonth = d.getMonth();
        var monthArray = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"];
 
        var alphaMonth = monthArray[numericMonth];
 
        return alphaMonth;
    }
 
    $j("#healthdiv").bind("plothover", function (event, pos, item) {
        if (item) {
            if (previousPoint != item.datapoint) {
                previousPoint = item.datapoint;
                $j("#flot-tooltip").remove();
 
                var originalPoint;
 
                if (item.datapoint[0] == item.series.data[0][3]) {
                    originalPoint = item.series.data[0][0];
                } else if (item.datapoint[0] == item.series.data[1][3]){
                    originalPoint = item.series.data[1][0];
                } else if (item.datapoint[0] == item.series.data[2][3]){
                    originalPoint = item.series.data[2][0];
                } else if (item.datapoint[0] == item.series.data[3][3]){
                    originalPoint = item.series.data[3][0];
                } else if (item.datapoint[0] == item.series.data[4][3]){
                    originalPoint = item.series.data[4][0];
                } else if (item.datapoint[0] == item.series.data[5][3]){
                    originalPoint = item.series.data[5][0];
                } else if (item.datapoint[0] == item.series.data[6][3]){
                    originalPoint = item.series.data[6][0];
                } else if (item.datapoint[0] == item.series.data[7][3]){
                    originalPoint = item.series.data[7][0];
                } else if (item.datapoint[0] == item.series.data[8][3]){
                    originalPoint = item.series.data[8][0];
                } else if (item.datapoint[0] == item.series.data[9][3]){
                    originalPoint = item.series.data[9][0];
                }
 
                var x = getMonthName(originalPoint);
                y = item.datapoint[1];
                z = item.series.color;
 
                showTooltip(item.pageX, item.pageY,
                    "<b>" + item.series.label + "</b><br /> " + x + " = " + y + "MB",
                    z);
            }
        } else {
            $j("#flot-tooltip").remove();
            previousPoint = null;
        }
    });

function getSelectedBranchId(pgTableName) {
		var branchId="";
		if($j(pgTableName).dataTable().fnGetData().length>0) {
			var selBranch = $j(pgTableName + ' tbody tr[selected]');
			if(selBranch.length==0){	
				updateTipsNew("Select atleast one branch",$j('#errorDivData'),$j('#errorDivHeader'));
			} else if(selBranch.length>1) 
				updateTipsNew("Select only one branch",$j('#errorDivData'),$j('#errorDivHeader'));
			else
				branchId=selBranch.attr('id');
		}		
		return branchId;
	}
//});