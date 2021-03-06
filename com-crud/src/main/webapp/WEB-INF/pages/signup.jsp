 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<!DOCTYPE html>
<html>
<head>
<title>Signup</title>
<%@include file="includes/header.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script src="resources/static/plugins/jquery/jquery-3.3.1.js"></script>
<script src="resources/static/plugins/jquery/jquery.validate.min.js"></script>
<script type="application/x-javascript" src="resources/static/js/signup.js">
</script>
<!-- Custom Theme files -->
<link rel="stylesheet" href="resources/static/css/signup.css"/>
<!-- //Custom Theme files -->
<!-- web font -->
<link
	href="//fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i"
	rel="stylesheet">
<!-- //web font -->
</head>
<body>
	<!-- main -->
	<div class="main-w3layouts wrapper">
		<h1>SignUp</h1>
		<div class="main-agileinfo">
			<div class="agileits-top">
				<f:form action="signup" modelAttribute="usersBean">

					<div class="form-group">
						<f:input path="name" class="text" type="text" placeholder="Name" required="true"/>
						<f:errors path="name" cssClass="error" />
					</div>
					
					<div class="form-group">
						<f:input path="email" class="text email" type="email" placeholder="Email" required="true"/>
						<f:errors path="email" cssClass="error" />
					</div>
					
					<%-- <div class="form-group">
						<f:input path="pswd" class="text" type="password" placeholder="Password" required="true"/>
						<f:errors path="pswd" cssClass="error" />
					</div> --%>
					
					<div class="form-group">
						<f:input path="mobile" class="text" type="number" placeholder="Mobile"
						 required="true" accept="number" />
						<f:errors path="mobile" cssClass="error" />
					</div>
					
					<div class="form-group">
						<f:input path="city" class="text" type="text" name="city" 
						required="true" placeholder="City" />
						<f:errors path="city" cssClass="error" />
					</div>

					<input type="submit" value="SIGNUP">
				</f:form>
				<p>
				 	Already have account? <a href="login" class="btn btn-primary btn-sm">Login</a>
				</p>
			</div>
		</div>
		<!-- copyright -->
		<div class="w3copyright-agile">
			<p>
				� 2018 Spring MVC by Akash. All rights reserved | Design by <a
					href="https://www.techgig.com/akashmulik" target="_blank">Akash
					Mulik</a>
			</p>
		</div>
		<!-- //copyright -->
		<ul class="w3lsg-bubbles">
			<li></li>
			<!-- <li></li>
			<li></li> -->
			<li></li>
<!-- 			<li></li>
			<li></li>
			<li></li>
			<li></li> 
			<li></li>-->
			<li></li>
		</ul>
	</div>
	<!-- //main -->
</body>
</html>