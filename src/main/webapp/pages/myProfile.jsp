<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />
<head>
<script src="resources/js/myProfile.js"></script>
</head>

<section>
	<div class="container">
		<div class="row">&nbsp;</div>
		<div class="col-md-12">
			<div class="media-body"
				style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
				<h3 class="media-heading">My Profile</h3>
				<div>&nbsp;</div>
				<div class="col-md-6">
					<table class="table table-condensed">
						<thead>
							<tr>
								<td><input type="text" name="firstName" id="firstName"
									class="form-control" placeholder="First Name"
									value="${userDTO.firstName}"></td>
							</tr>
							<tr>
								<td><input type="text" name="lastName" id="lastName"
									class="form-control" placeholder="Last Name"
									value="${userDTO.lastName}"></td>
							</tr>
							<tr>
								<td><input type="text" name="phoneNumber" id="phoneNumber"
									class="form-control" placeholder="Phone Number"
									value="${userDTO.phoneNumber}"></td>
							</tr>
							<tr>
								<td><input type="text" name="email" id="email"
									class="form-control" placeholder="Email Id"
									value="${userDTO.emailId}"></td>
							</tr>
							<tr>
								<td colspan="2"><button class="btn btn-success btn-small"
										id="updateButton" name="updateButton" onclick=udpateProfile();>Update</button></td>
							</tr>
						</thead>
					</table>
				</div>
				<div class="col-md-6">&nbsp;</div>
				<div class="row col-md-12">&nbsp;</div>
				<div class="row col-md-12">
					<div>
						<p class="text-danger" id="profileFailureMessage"></p>
					</div>
					<div>
						<p class="text-success" id="profileSuccessMessage"></p>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>