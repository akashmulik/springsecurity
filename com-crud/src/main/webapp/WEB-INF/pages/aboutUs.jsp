 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>

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
				<h3>ABout Us</h3>
			</div>
			<div id="home">
			<hr>
			<br>
			<p>
				Spring MVC application designed and developed by <a
					href="https://www.techgig.com/akashmulik" target="_blank">Akash
					Mulik</a>.<br>
			</p>

			<dl>
				<dt>Features:</dt>
				<dd>- Spring mvc 4.3.4</dd>
				<dd>- Hibernate 5.0.5</dd>
				<dd>- Spring security 4.2.0</dd>
				<dd>- User authentication</dd>
				<dd>- User role based authorization</dd>
				<dd>- Suspend/activate user feature</dd>
				<dd>- CRUD operations</dd>
				<dd>- Export EXCEL Reports</dd>
				<dd>- Quartz scheduler</dd>
				<dd>- Images uploading to database</dd>
			</dl>
		</div>
	</div>
</body>
</html>