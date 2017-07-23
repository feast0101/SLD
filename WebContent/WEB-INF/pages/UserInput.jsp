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
		var tabs = '<c:out value="${tables}"/>';
		tabs = tabs.replace(/&#034;/g, '\"');
		tabs = eval("(" + tabs + ")");
		$.each(tabs, function(val, text) {
			$('#tables').append($('<option></option>').val(text).html(text))
		});
	});
	function executeFunction() {
		var gt = document.getElementById('graphtype');
		var tb = document.getElementById('tables');
		makeAjaxCall(gt.options[gt.selectedIndex].value,
				tb.options[tb.selectedIndex].value);
	}
	function makeAjaxCall(graphType, tableName) {
		$("#wait").css("display", "block");
		$("#userGraphViewDiv").css("display", "none");
		$.ajax({
			url : 'showUserGraph.do',
			method : "GET",
			data : "graphType=" + graphType + "&tableName=" + tableName,
			success : function(response) {
				$("#wait").css("display", "none");
				$("#userGraphViewDiv").css("display", "block");
				$("#userGraphViewDiv").html(response);
			}
		});
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
							<div class="example">
								<div class="grid fluid">
									<div class="row">
										<div class="span12">
											<div class="panel">

												<div class="panel-header bg-lightBlue fg-white">Questionnaire
													</div>

												<table width = "80%">
												<tr>
												<td width="60%">Q1</td>
												<td width="40%">A1</td>
												</tr>
												<tr>
												<td width="60%">Q1</td>
												<td width="40%">A1</td>
												</tr>
												<tr>
												<td width="60%">Q1</td>
												<td width="40%">A1</td>
												</tr>
												<tr>
												<td width="60%">Q1</td>
												<td width="40%">A1</td>
												</tr>
												<tr>
													<td></td>
														<td>
															<div style="margin-top: 45px">
																<button class="button default"
																	onclick="executeFunction()">SUBMIT</button>
															</div>
														</td>
												</tr>
												</table>

											</div>
											<div id="viewDiv"
												style='width: 915px; height: 320px;'>
											<!--Div that will hold the loading circle-->
											<div id="wait" align="right"
												style="display: none; width: 50%; height: 50%; top: 50%; left: 50%; padding: 2px;">
												<br> <br> <br> <img
													src='static/images/482.GIF' width="35%" height="35%" /> <br>
												<br> <br> <b>Loading.......Please don't	refresh page</b>
											</div>
											<br><br>
											<div id="userGraphViewDiv"
												style='width: 915px; height: 320px;'>
												<br> <br>
												<h3 id="__notice__">Please select appropriate option based on which SLD will be generated </h3>
											</div>
										 </div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>

	</div>
	<jsp:include page="../views/footer.jsp"></jsp:include>
</body>
</html>