 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- JS and CSS ref -->
<link rel="stylesheet" href="resources/static/css/homePage.css"/>
<script src="resources/static/js/viewUsers.js"></script>

</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	%>
	<div class="container-fluid">
			<br>
			<div class="page-header">
				<h3>Home</h3>
			</div>
			<div id="home">
			<hr>
			<br>
			<p>
				Welcome to Spring MVC application.!!<br>
			</p>
		</div>
	</div>
</body>
</html>