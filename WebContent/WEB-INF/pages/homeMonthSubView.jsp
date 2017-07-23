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
google.load('visualization', '1.1', {packages: ['corechart','controls','table']}); 
</script>

<script type="text/javascript">
var chart1;
var dashboard;
$(document).ready(function() {drawVisualization1();});

function drawVisualization1() {
	drawChart();
	drawChart2();
	var datafeed = '<c:out value="${monthdatafeed}"/>';
	datafeed = datafeed.replace(/&#039;/g, '\'');
	
	datafeed = eval("(" + datafeed + ")");
	dashboard = new google.visualization.Dashboard(document.getElementById('dashboard2'));

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
				'start' : new Date(2011, 1, 1), //data[0].values.min, 
				'end' :  new Date(2020, 12, 1)//data[0].values.max
			}
		}
	});

	debugger;
	chart1 = new google.visualization.ChartWrapper({
		'chartType' : 'LineChart',
		'containerId' : 'chart2',
		'options' : {
			title : 'Monthly Trend line for each service usage',
			'hAxis' : {
				title : 'Month',
				'format': "MMM",
				'slantedText' : false
				},
			'vAxis' : {
				 title : 'Usage Count',
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
		'containerId' : 'chart3',
		'options' : {
			//'isStacked' : true,
			title : 'Monthly Application Service Usage Breakup',
			'hAxis' : {
				title : 'Month',
				'format': "MMM",
				'slantedText' : false
			},
			'vAxis' : {
				 title : 'Usage Count',
				//'logScale': true
			},
			 animation:{
			        duration: 1000,
			        easing: 'out',
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
	
	debugger;
	google.visualization.events.addListener(dashboard, 'error', function (dashboard) { google.visualization.errors.removeAll(document.getElementById("dashboard2")); });
	google.visualization.events.addListener(control, 'error',  function (err) { google.visualization.errors.removeError(err.id); }); 
	var data = google.visualization.arrayToDataTable(datafeed);
	dashboard.bind(control,[chart1, chart2]);
	dashboard.draw(data);
}
	function drawChart2() {
		var datafeed = '<c:out value="${daydatafeed}"/>';
		
		datafeed = datafeed.replace(/&#039;/g, '\'');
		datafeed = eval("(" + datafeed + ")");
		var data1 = google.visualization.arrayToDataTable(datafeed);

		var options = {
			title : 'Date wise service usage breakup within a month',
			chartArea: {
				width:'75%',
				height:'60%'
			},
			isStacked: true,
			'vAxis' : {
				 title : 'Usage Count'
			},
			hAxis : {
				title : 'Date',
				'slantedText' : false
			},
			'view' : {
				'columns' : [ {
			    type: 'datetime',
			    label: data1.getColumnLabel(0),
			    calc: function (dt, rowIndex) {
			        var val = dt.getFormattedValue(rowIndex, 1);
			        return (val != '' && val != null) ?new Date(val): null;
			    }
			},2,3,4,5,6]
			},
			'explorer': {
		        maxZoomOut:7,
		       // actions: ['dragToZoom', 'rightClickToReset'],
		        //axis: 'horizontal',
		    	keepInBounds: true
		  },
			'legend' : {
				'position' : 'right'
			}
		};
		var chart = new google.visualization.ColumnChart(document
				.getElementById('chart_div3'));
		google.visualization.events.addListener(chart, 'error',  function (err) { google.visualization.errors.removeError(err.id); }); 
		chart.draw(data1, options);
   }
	function drawChart() {
		var datafeed = '<c:out value="${storagedatafeed}"/>';
		
		datafeed = datafeed.replace(/&#039;/g, '\'');
		datafeed = eval("(" + datafeed + ")");
		var data1 = google.visualization.arrayToDataTable(datafeed);

		var options = {
			title : 'Monthly Cloud Storage Usage Breakup',
			isStacked: true,
			'vAxis' : {
				 title : 'Usage Count'
			},
			hAxis : {
				title : 'Month',
				titleTextStyle : {
					color : 'black'
				}
			},
			 animation:{
			        duration: 1000,
			        easing: 'out',
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
   <h2>Monthly Report</h2> 
   ${noDataErrorMessage}
	<div id="dashboard2">
	    <div id="chart_div3" style='width: 915px; height: 320px;'></div>
		<br> <br>
		<div id="chart2" style='width: 915px; height: 320px; display: none;'></div>
		<br> <br>
		<br> <br>
		<div id="chart3" style='width: 915px; height: 320px;'></div>
		<br> <br>
		<br> <br>
		<div id="chart_div" style='width: 915px; height: 200px;'></div>
		<div id="control2" style='width: 915px; height: 50px; display: none;'></div>
	</div>
 <div id="proxyTable" style="display: none;"></div>
</body>
</html>