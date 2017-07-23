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
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<!-- Metro UI CSS JavaScript plugins -->
<script src="static/js/load-metro.js"></script>
<script src="static/js/jquery.bxslider.js"></script>
<!-- bxSlider CSS file -->
<link href="static/css/jquery.bxslider.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
	$(document).ready(function() {
		$('.bxslider').bxSlider({
			auto : true
		});
	});
</script>

<title>SLD Automation Tool</title>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body class="metro">
	<nav class="navigation-bar dark">
		<div class="navigation-bar-content">
			<a href="login.do" class="element"><span class="icon-globe"></span> SLD Automation Tool
		    <sup>beta</sup></a> <span
				class="element-divider"></span> <a class="pull-menu" href="#"></a>
		</div>
	</nav>

	<div class="container" align="left">
		<div>
			<table>
				<tr>
					<td>
						<div class="" align="center"
							style="width: 1350px; height: 550px;">
							<table>
								<tr>
									<td>
										<div id="dashboard" style="width: 500px; height: 310px;"
											align="center">
											<c:if test="${not empty param.error}">
												<font color="red"> Login error. <br /> Reason :
													${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
												</font>
											</c:if>
								<h2>You have successfully logged out of the application</h2>
								<br>
								<input type="button" onclick="location.href='login.do'" value="Re-Login"/>
										</div>
									</td>
								</tr>
							</table>
						</div> <jsp:include page="WEB-INF/views/footer.jsp"></jsp:include> <!-- End of container -->
						</div>
</body>
</html>