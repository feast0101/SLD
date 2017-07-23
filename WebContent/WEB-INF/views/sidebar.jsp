<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- Load JavaScript Libraries -->
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
<title></title>
<script type="text/javascript">
    jQuery(document).ready(function($){
        // Get current url
        // Select an a element that has the matching href and apply a class of 'active'.
        var path = window.location.pathname;
        path=path.replace(/\/$/,"");
        path=decodeURIComponent(path);
        $('.menu a').each(function(){
        	var href=$(this).attr('href'); 
        	if((path.substring((path.length-href.length),(path.length-0)))==href){
        		$(this).closest('li').addClass('active');
        	}
        });
    });
</script>

</head>
<body>
	<nav class="sidebar dark">
	<ul class="menu">
		<li class="stick bg-yellow"><a href="showUserInput.do"><i class="icon-accessibility"></i>User Input</a></li>
		<li class="stick bg-green"><a href="showHomeDashboard.do"><i class="icon-stats"></i>SLD</a></li>
		<li class="stick bg-blue"><a href="showCompanyDashboard.do"><i class="icon-pie"></i>Export</a></li>
		<li class="stick bg-orange"><a href="showAnalytics.do"> <i class="icon-stats-up"></i>Analytics</a></li>
		<li class="stick bg-red"><a href="getdata.do"> <i class="icon-database"></i>Export Excel</a></li>
	</ul> 
	</nav>

</body>
</html>