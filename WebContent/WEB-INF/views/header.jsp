<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script>
function refreshPage(){
    window.location.reload();
} 
function printPage(){
    window.print();
} </script>
</head>
<body class="metro">
	<nav class="navigation-bar dark">
	<div class="navigation-bar-content">
		<a href="showHomeDashboard.do" class="element"><span class="icon-globe"></span> SLD Automation Tool
			 <sup>beta</sup></a> <span class="element-divider"></span>

		<a class="pull-menu" href="#"></a>

		<div class="no-tablet-portrait">
			<span class="element-divider"></span> <a class="element brand"
				href="#" onclick="refreshPage()"><span class="icon-spin"></span></a>
	        <span class="element-divider"></span>
			<div class="element place-right">
				<a href="<c:url value="logout.do"/>" >Logout</a>
			</div>
		</div>
	</div>
	</nav>

</body>
</html>