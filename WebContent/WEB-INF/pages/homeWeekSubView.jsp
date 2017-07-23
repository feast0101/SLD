<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<link rel="shortcut icon" href="static/images/favicon.ico"
	type="image/x-icon">
<link rel="icon" href="static/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="static/css/metro-bootstrap.css">
<link href="static/css/iconFont.css" rel="stylesheet">
<link href="static/css/docs.css" rel="stylesheet">
<link href="static/js/prettify/prettify.css" rel="stylesheet">
<!-- Load JavaScript Libraries -->
<script src="static/js/jquery/jquery.min.js"></script>
<script src="static/js/jquery/jquery.widget.min.js"></script>
<script type="text/javascript" src="static/js/jsapi.js"></script>

<!-- Metro UI CSS JavaScript plugins -->
<script src="static/js/load-metro.js"></script>

<script type="text/javascript">
google.load('visualization', '1', {packages: ['corechart','controls','table']});  
</script>
<script type="text/javascript">
//google.setOnLoadCallback(drawVisualization2);
$(document).ready(function() {drawVisualization2();});
function drawVisualization2() {
	drawChart();
	var datafeed = '<c:out value="${weekdatafeed}"/>';
	datafeed = datafeed.replace(/&#039;/g, '\'');
	datafeed = eval("(" + datafeed + ")");
	
	var dashboard = new google.visualization.Dashboard(document.getElementById('dashboard2'));

	debugger;

	var control = new google.visualization.ControlWrapper({
		'controlType' : 'ChartRangeFilter',
		'containerId' : 'control2',
		'options' : {
			// Filter by the date axis.
			'filterColumnIndex' : 0,
			'ui' : {
				'chartType' : 'LineChart',
				'chartOptions' : {
					'chartArea' : {
						'width' : '90%'
					},
					'hAxis' : {
						'baselineColor' : 'none',
						'format': "EE"
					}
				},
				// Display a single series that shows the closing value of the stock.
				// Thus, this view has two columns: the date (axis) and the stock value (line series).
				'chartView' : {
					'columns' : [ 0, 8 ]
				},
				// 1 day in milliseconds = 24 * 60 * 60 * 1000 = 86,400,000
				'minRangeSize' : 86400000,
				'maxRangeSize' : 604800000
			}
		},
		// Initial range: 2012-02-09 to 2012-03-20.
		'state' : {
			'range' : {
				'start' : new Date(2011, 1, 1),
				'end' : new Date(2020, 12, 1)
			}
		}
	});

	debugger;
	var chart1 = new google.visualization.ChartWrapper({
		'chartType' : 'LineChart',
		'containerId' : 'chart2',
		'options' : {
			title : 'Weekly Trend line for each service usage',
			'hAxis' : {
				title : 'Week',
				'format': "EE",
				'slantedText' : false
			},
			'vAxis' : {
				title : 'Usage Count'
				//'logScale': true
			},
		    pointSize: 10,
			series: {
			        0: { pointShape: 'square' },
	                1: { pointShape: 'square' },
	                2: { pointShape: 'square' },
	                3: { pointShape: 'square' },
	                4: { pointShape: 'square' },
	                5: { pointShape: 'square' }
	            },
			'legend' : {
				'position' : 'right'
			}
		},
		// Convert the first column from 'date' to 'string'.
		'view' : {
			'columns' : [ {
				'calc' : function(dataTable, rowIndex) {
					return dataTable.getFormattedValue(rowIndex, 2);
				},
				'type' : 'string'
			},3,4,5,6,7,8 ]
		}
	});
	var chart2 = new google.visualization.ChartWrapper({
		'chartType' : 'ColumnChart',
		'containerId' : 'chart3',
		'options' : {
			//'isStacked' : true,
			title : 'Weekly Application Service Usage Breakup',
			'hAxis' : {
			   	title : 'Week',
				'format': "EE",
			   	'slantedText' : false
			},
			'vAxis' : {
				title : 'Usage Count'
				//'logScale': true
			}
		},
		// Convert the first column from 'date' to 'string'.
		'view' : {
			'columns' : [ {
				'calc' : function(dataTable, rowIndex) {
					return dataTable.getFormattedValue(rowIndex, 2);
				},
				'type' : 'string'
			},3,4,5,6,7,8]
		}
	});

	debugger;
	
	var data = google.visualization.arrayToDataTable(datafeed);
	dashboard.bind(control, [chart1, chart2]);
	google.visualization.events.addListener(dashboard, 'error', function (dashboard) { google.visualization.errors.removeAll(document.getElementById("dashboard2")); });
	google.visualization.events.addListener(control, 'error',  function (err) { google.visualization.errors.removeError(err.id); }); 
	dashboard.draw(data);
}
	function drawChart() {
		var datafeed = '<c:out value="${storagedatafeed}"/>';
		
		datafeed = datafeed.replace(/&#039;/g, '\'');
		datafeed = eval("(" + datafeed + ")");
		var data1 = google.visualization.arrayToDataTable(datafeed);

		var options = {
			title : 'Cloud Storage Usage Breakup within a month',
			//isStacked: true,
			hAxis : {
				title : 'Month',
				titleTextStyle : {
					color : 'black'
				}
			}
		};
		var chart = new google.visualization.ColumnChart(document
				.getElementById('chart_div'));
		google.visualization.events.addListener(chart, 'error',  function (err) { google.visualization.errors.removeError(err.id); }); 
		chart.draw(data1, options);
 }
	
</script>
</head>
<body>
   <h2>Weekly Report</h2> 
   ${noDataErrorMessage}
	<div id="dashboard2">
	 	<br> <br>
		<div id="chart2" style='width: 915px; height: 350px; display: none;'></div>
		<br> <br>
		<br> <br>
		<div id="chart3" style='width: 915px; height: 350px;'></div>
		<br> <br>
		<br> <br>
		<div id="chart_div" style='width: 915px; height: 200px;  display: none;'></div>
		<br> <br>
		<br> <br>
		<div id="control2" style='width: 915px; height: 50px; display: none;'></div>
	</div>
</body>
</html>