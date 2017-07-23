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

<script type="text/javascript">
	var startDate;
	var endDate;
	$(document).ready(function() {
		$("#datepicker1").datetimepicker({
			timeFormat : 'hh:mm tt z',
			onSelect : function(dateText, inst) {
				startDate = $(this).datepicker('getDate');
			}
		});
		$("#datepicker2").datetimepicker({
			timeFormat : 'hh:mm tt z',
			onSelect : function(dateText, inst) {
				endDate = $(this).datepicker('getDate');
			}
		});
		$("#submitdate").submit(function() {
			//alert('Start Date :'+startDate+'end Date :'+endDate);
			if (startDate == null) {
				alert("Please enter the start date");
				return false;
			}
			if (endDate == null) {
				alert("Please enter the end date");
				return false;
			}
			return true;
		});
	});
</script>

<title>Data Automation & Visualization Dashboard 2.0</title>
</head>
<body class="metro">

	<div class="container" align="center">

		<form id="submitdate" action="fetchdata.do" method="post">

			<table>
				<tr>
					<td>
						<h2 id="__notice__">Start Date Time:</h2> <input id="datepicker1"
						name="startDate" />
					</td>
					<td></td>
					<td>
						<h2 id="__notice__">End Date Time:</h2> <input id="datepicker2"
						name="endDate" />
					</td>
					<td></td>
					<td>
						<div style="margin-top: 45px">
							<button class="button default">LOAD SELECTED DATA</button>
						</div>
					</td>
				</tr>

			</table>
		</form>
		<div style="margin-top: 20px">
			<a href="fetchdata2.do"><button class="button default">LOAD
					ALL DATA</button></a>
		</div>

		<!-- End of container -->
	</div>

</body>
</html>