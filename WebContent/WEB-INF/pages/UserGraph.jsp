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

<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script src="static/js/jquery/jquery-ui-timepicker-addon.js"></script>
<!-- Metro UI CSS JavaScript plugins -->
<script src="static/js/load-metro.js"></script>
<script type="text/javascript" src="static/js/jsapi.js"></script>

<script type="text/javascript">
	google.load('visualization', '1', {
		packages : [ 'corechart', 'controls', 'table', 'motionchart' ]
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		drawChart1();
		drawChart2();
		drawChart3();
	});
	
	function drawChart1() {
		var datafeed = '<c:out value="${graphdatafeed}"/>';
		datafeed = datafeed.replace(/&#039;/g, '\'');
		datafeed = eval("(" + datafeed + ")");
		var data = google.visualization.arrayToDataTable(datafeed);
		var options = {
				//title : 'Monthly Cloud Storage Usage Breakup',
				//isStacked: true,
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
			var chart1 = new google.visualization.ColumnChart(document
					.getElementById('chart1'));
			google.visualization.events.addListener(chart1, 'error',  function (err) { google.visualization.errors.removeError(err.id); alert("No Data available"); }); 
			chart1.draw(data, options);
	}  
	function drawChart2() {
		var datafeed = '<c:out value="${graphdatafeed}"/>';
		datafeed = datafeed.replace(/&#039;/g, '\'');
		datafeed = eval("(" + datafeed + ")");
		var data = google.visualization.arrayToDataTable(datafeed);
		var options = {
				//title : 'Monthly Cloud Storage Usage Breakup',
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
			var chart2 = new google.visualization.ColumnChart(document
					.getElementById('chart2'));
		    google.visualization.events.addListener(chart2, 'error',  function (err) { google.visualization.errors.removeError(err.id); }); 
			chart2.draw(data, options);
	}  
	function drawChart3() {
		var datafeed = '<c:out value="${graphdatafeed}"/>';
		datafeed = datafeed.replace(/&#039;/g, '\'');
		datafeed = eval("(" + datafeed + ")");
		var data = google.visualization.arrayToDataTable(datafeed);
		var options = {
				//title : 'Monthly Cloud Storage Usage Breakup',
				//isStacked: true,
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
			var chart3 = new google.visualization.LineChart(document
					.getElementById('chart3'));
			google.visualization.events.addListener(chart3, 'error',  function (err) {
				google.visualization.errors.removeError(err.id); 
				//google.visualization.errors.removeAll(document.getElementById("charts")); 
			}); 
			chart3.draw(data, options);
	}  
	var graphtype = '<c:out value="${graphtype}"/>';
	if (graphtype == "VerticalBarChart") {
	    $('#chart1').show();
	    $('#chart2').hide();
	    $('#chart3').hide();
	} else if (graphtype == "StackedVerticalBarChart"){
		$('#chart1').hide();
		$('#chart2').show();
		$('#chart3').hide();
	}else if(graphtype == "VerticalLineChart"){
		$('#chart1').hide();
		$('#chart2').hide();
		$('#chart3').show();
	}
</script>
</head>
<body class="metro">	
<div id="charts" style='width: 915px; height: 400px;'><h3>${errorMessage}</h3> 
	<div id="chart1"></div>
	<div id="chart2"></div>
	<div id="chart3"></div>
</div>
</body>
</html>