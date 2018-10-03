 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Reports</title>
<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- JS and CSS ref -->
<link rel="stylesheet" href="resources/static/css/reportsPage.css"/>

</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	%>
	<div class="container-fluid">
		<br>
		<div class="page-header">
			<h3>Reports</h3>
		</div>
		<hr>
		<br>
		<p><a href="Users_Details_report">Generate</a> excel report of users data!</p>
	</div>
</body>
</html>