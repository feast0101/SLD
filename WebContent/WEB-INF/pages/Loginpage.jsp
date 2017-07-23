<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

			<div class="no-tablet-portrait">
				<span class="element-divider"></span> <a class="element brand"
					href="#"><span class="icon-spin"></span></a>
			</div>
		</div>
	</nav>

	<div class="container" align="left">
		<div>
			<table>
				<tr>
					<td>
						<h2 id="__notice__">Login:</h2>
						<div class="" align="center"
							style="width: 1350px; height: 500px;">
							<table><br><br>
								<tr>
								
									<td>
										<div id="dashboard" style="width: 500px; height: 300px;"
											align="center">

											<form:form method="POST" commandName="user">
												<form:errors path="*" cssClass="errorblock" element="div" />
												<table>
													<tr>
														<td>UserName :</td>
														<td><form:input path="userName" /></td>
														<td><form:errors path="userName" cssClass="error" /></td>
													</tr>
													<tr>
														<td>Password :</td>
														<td><form:password path="password" /></td>
														<td><form:errors path="password" cssClass="error" /></td>
													</tr>

													<tr>
														<td colspan="3" align="center"><br> <input
															type="submit" name="submit" value="submit" /></td>
													</tr>
												</table>
											</form:form>
										</div>
									</td>
								</tr>

							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<jsp:include page="../views/footer.jsp"></jsp:include>
		<!-- End of container -->
	</div>
</body>
</html>