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
<script src="static/js/jquery/jquery-2.1.1.js"></script>
<script src="static/js/jquery/jquery.widget.min.js"></script>
<script type="text/javascript" src="static/js/jsapi.js"></script>

<!-- Metro UI CSS JavaScript plugins -->
<script src="static/js/load-metro.js"></script>

<script type="text/javascript">
google.load('visualization', '1', {packages: ['corechart','controls','table','orgchart']});  
</script>
<script type="text/javascript">
$(document).ready(function() {drawVisualization1();});

function drawVisualization1() {
	
 	var datafeed = '<c:out value="${monthdatafeed}"/>';
 	datafeed = datafeed.replace(/&#039;/g, '\'');
	datafeed = eval("(" + datafeed + ")");
	var dashboard = new google.visualization.Dashboard(document.getElementById('dashboard2'));

	var control = new google.visualization.ControlWrapper({
		'controlType' : 'ChartRangeFilter',
		'containerId' : 'control3',
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
						'format': "MMM"
					}
				},
				'chartView' : {
					'columns' : [ 0, 7 ]
				},
				// 1 day in milliseconds = 24 * 60 * 60 * 1000 = 86,400,000
				'minRangeSize' : 2592000000,
				'maxRangeSize' : 2592000000
			}
		},
		// Initial range: 2012-02-09 to 2012-03-20.
		'state' : {
			'range' : {
				'start' : new Date(2011, 12, 1),
				'end' : new Date(2020, 12, 1)
			}
		}
	});

	var chart1 = new google.visualization.ChartWrapper({
		'chartType' : 'LineChart',
		'containerId' : 'chart1',
		'options' : {
			title : 'Trend line for each service',
			strictFirstColumnType: true,
			// Use the same chart area width as the control for axis alignment.
			'chartArea' : {
				'height' : '80%',
				'width' : '70%'
			},
			'hAxis' : {
				'slantedText' : false
			},
			'vAxis' : {
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
					return dataTable.getFormattedValue(rowIndex, 1);
				},
				'type' : 'string'
			}, 2,3,4,5,6,7 ]
		}
	});
	var chart2 = new google.visualization.ChartWrapper({
		'chartType' : 'ColumnChart',
		'containerId' : 'chart2',
		'options' : {
			'isStacked' : true,
			title : 'Application Service Usage Breakup',
			// Use the same chart area width as the control for axis alignment.
			'chartArea' : {
				'height' : '80%',
				'width' : '70%'
			},
			'hAxis' : {
				'slantedText' : false
			},
			'vAxis' : {
				//'logScale': true,
				 title : 'Usage Count'
			},
			'legend' : {
				'position' : 'right'
			}
		},
		// Convert the first column from 'date' to 'string'.
		'view' : {
			'columns' : [ {
				'calc' : function(dataTable, rowIndex) {
					return dataTable.getFormattedValue(rowIndex, 1);
				},
				'type' : 'string'
			},2,3,4,5,6,7]
		}
	});
	
	var data = google.visualization.arrayToDataTable(datafeed);
	dashboard.bind([control],[chart1, chart2]);
	google.visualization.events.addListener(dashboard, 'error', function (dashboard) { google.visualization.errors.removeAll(document.getElementById("dashboard2")); });
	google.visualization.events.addListener(control, 'error',  function (err) { google.visualization.errors.removeError(err.id); }); 
	dashboard.draw(data,{strictFirstColumnType: true });
}
</script>
</head>
<body> 
<h2>Monthly Report</h2>
${noDataErrorMessage}
	<div id="dashboard2">
		<br> <br>
		<div id="chart1" style='width: 915px; height: 200px;'></div>
		<br> <br>
		<br> <br>
		<div id="chart2" style='width: 915px; height: 200px;'></div>
		<br> <br>
		<br> <br>
		<br> <br>
		<div id="control3" style='width: 915px; height: 50px;'></div>		
	</div>

</body>
</html>