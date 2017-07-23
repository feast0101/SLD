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
google.load('visualization', '1', {packages: ['corechart','controls','table','motionchart']});  
</script>
<script type="text/javascript">
$(document).ready(function() {drawTimelineChart();
drawMotionChart();
});
function drawTimelineChart() {
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
			    type: 'date',
			    label: data1.getColumnLabel(0),
			    calc: function (dt, rowIndex) {
			        var val = dt.getFormattedValue(rowIndex, 1);
			        return (val != '' && val != null) ?new Date(val): null;
			    }
			},2,3,4,5,6]
			},
			'explorer': {
		        maxZoomOut:9,
		       // actions: ['dragToZoom', 'rightClickToReset'],
		        //axis: 'horizontal',
		    	keepInBounds: true
		  },
			'legend' : {
				'position' : 'right'
			}
		};
		var chart = new google.visualization.ColumnChart(document
				.getElementById('chart_div'));
		//google.visualization.events.addListener(chart, 'error',  function (err) { google.visualization.errors.removeError(err.id); }); 
		chart.draw(data1, options);
   }
function drawMotionChart() {
	var datafeed = '<c:out value="${motiondatafeed}"/>';
	datafeed = datafeed.replace(/&#039;/g, '\'');
	datafeed = eval("(" + datafeed + ")");
	var data = google.visualization.arrayToDataTable(datafeed);
    var chart = new google.visualization.MotionChart(document.getElementById('motionchart_div'));

    chart.draw(data, {width: 600, height:300});
  }

</script>

<title>Usage status analysis tool</title>
</head>
<body class="metro">
	<jsp:include page="../views/header.jsp"></jsp:include>
	<!-- Start of container -->
	<div class="container" align="center">
		<div>
			<table>
				<tr>
					<td class="span3"
						style='vertical-align: top; background-color: #3D3D3D'><jsp:include
							page="../views/sidebar.jsp"></jsp:include></td>
					<td>
						
						<h2 id="__notice__">User Input page:</h2>
						<div class="example">
							<h2 id="__panel__">Input Panel</h2>
							<div class="example">
								<div class="grid fluid">
									<div class="row">
										<div class="span12">
												<div class="panel">
												<div class="panel-header bg-lightBlue fg-white">Trendline Motion Chart</div>
			     								<div id="motionchart_div" style='width: 915px; height: 320px;'></div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="span12">
												<div class="panel">
												<div class="panel-header bg-lightBlue fg-white">Enter Time frame for the data analysis</div>
												<jsp:include page="DatePicker.jsp"></jsp:include>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="span12">
												<div class="panel">
												<div class="panel-header bg-lightBlue fg-white">Zoomable Time Line chart of Service Usage</div>
			     								<div id="chart_div" style='width: 915px; height: 320px;'></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div></td>
				</tr>
			</table>
		</div>

	</div>
	<jsp:include page="../views/footer.jsp"></jsp:include>
</body>
</html>