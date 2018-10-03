 <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Profile</title>
<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>

<!-- JS and CSS references -->
<link rel="stylesheet" href="resources/static/css/myProfile.css"/>
<script src="resources/static/js/myProfile.js"></script>

</head>
<body onload="profWrapper.loadMyProfile()">
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	%>
	<div class="container-fluid"><br>
	<div class="page-header">
		<h3>Profile</h3>
	</div>
	<hr><br><br><br>
		
		<div class="row">
			<div class="col-md-12">
				<div class="col-md-10">
					<div class="row">
						<div class="col-md-5">
							<div class="row justify-content-center">
								<img alt="Profile_image" class="img-circle" src="" id="profImg">
							</div><br>
							<div class="row justify-content-center">
								<img alt="Sign_image" id="signImg">
							</div><br>
							<div class="row justify-content-center">
								<button class="btn btn-primary btn-sm"
									data-toggle="modal" data-target="#imageModal">Upload Photo &
									Sign</button>
							</div>
						</div>
						<div class="col-md-7">
							<div class="row"><h3 class="font-italic" id="name">Name</h3></div><hr>
							<div class="row">
								<div class="col-md-3 text-right">
									<span>User ID</span>
								</div>
								<div class="col-md-9">
									<p id="id">123</p>
								</div>
							</div><hr>
							<div class="row">
								<div class="col-md-3 text-right">
									<span>Email</span>
								</div>
								<div class="col-md-9">
									<p id="email">email@domain.com</p>
								</div>
							</div><hr>
							<div class="row">
								<div class="col-md-3 text-right">
									<span>Mobile</span>
								</div>
								<div class="col-md-9">
									<p id="mobile">+91 XX XX XXX XXX</p>
								</div>
							</div><hr>
							<div class="row">
								<div class="col-md-3 text-right">
									<span class="label label-default">City</span>
								</div>
								<div class="col-md-9">
									<p id="city">Pune</p>
								</div>
							</div><hr>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--image upload Modal -->
		<div class="modal fade" id="imageModal" role="dialog">
			<div class="modal-dialog">
<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Upload Profile Photo & Sign</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<br> <input name="id" type="hidden" id="id" /> 
						<input type="hidden" name="${_csrf.parameterName}" id="csrfTkn" value="${_csrf.token}" />
						<form id="fileUploadForm" enctype="multipart/form-data" method="POST">
							<div class="form-group">
								<label class="col-md-3">Photo</label> 
								<input type="file" class="col-md-8" id="photo" name="photo"
									accept="image/jpeg, image/jpg, image/png">
							</div>
							<div class="form-group">
								<label class="col-md-3">Sign</label> 
								<input type="file" class="col-md-8" id="sign" name="sign"
									accept="image/jpeg, image/jpg, image/png">
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success"
							onclick="profWrapper.uploadPhotoSign()">Upload</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>