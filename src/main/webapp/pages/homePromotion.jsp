<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.hirenpay.dto.UserDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<%
	String sessionPhoneNumber = "";
	if (null != session && null != session.getAttribute("userDTO")) {
		sessionPhoneNumber = ((UserDTO) session.getAttribute("userDTO"))
				.getPhoneNumber();
	}
%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>HireNPay</title>

<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/css/freelancer.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body id="page-top" class="index">

	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#page-top" id="brand">Hire N Pay</a>
		</div>

		<div class="navbar-header page-scroll">
			<div class="alert alert-success text-center" id="success">
				<button class="close" aria-hidden="true" data-dismiss="alert"
					type="button">×</button>
				<strong id="successMessage"></strong>
			</div>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div>
			<ul id="bs-example-navbar-collapse-1"
				class="navbar-collapse nav navbar-nav navbar-right">
				<li class="hidden"><a href="#page-top"></a></li>
				<li class="page-scroll"><a href="#about">ABOUT US</a></li>
			</ul>
		</div>

		<div></div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<!-- Header -->
	<header>
	<div class="container">
		<div class="col-lg-12">
			<img src="resources/img/profile.png" alt="">
			<div class="intro-text">
				<span class="name">Hire N Pay</span>
				<hr class="star-light">
				<span class="skills">Convenience at your Fingertips</span><br>
			</div>
		</div>
	</div>
	</header>
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Hire N Pay</h4>
				</div>
				<div class="modal-body">We are Coming Soon. Subscribe to get
					notified.</div>
				<div class="row control-group">
					<div
						class="form-group col-xs-10 col-lg-offset-1 floating-label-form-group controls">
						<label>Phone Number</label> <input type="text"
							class="form-control" placeholder="Phone Number"
							id="subscriberPhoneNumber" maxlength="10">
						<p class="help-block text-danger"
							id="subscriberPhoneNumberWarning">Please enter Phone Number.</p>
						<p class="help-block text-danger"
							id="subscriberPhoneNumberLengthWarning">Phone Number should
							be of 10 digits.</p>

					</div>
				</div>
				<div>&nbsp;</div>
				<div class="alert alert-success col-xs-10 col-lg-offset-1"
					id="successSubscribe">
					<strong id="successSubscribeMessage"></strong>
				</div>
				<div class="alert alert-failure col-xs-10 col-lg-offset-1"
					id="failureSubscribe">
					<strong id="failureSubscribeMessage"></strong>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="subscribeButton">Subscribe</button>
				</div>
			</div>
		</div>
	</div>
	<!-- About Section -->
	<jsp:include page="about.jsp" />
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
	<div class="scroll-top page-scroll visible-xs visible-sm">
		<a class="btn btn-primary" href="#page-top"> <i
			class="fa fa-chevron-up"></i>
		</a>
	</div>

	<!-- jQuery -->
	<script src="resources/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="resources/js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="resources/js/jquery.easing.min.js"></script>
	<script src="resources/js/classie.js"></script>
	<script src="resources/js/cbpAnimatedHeader.js"></script>

	<script src="resources/js/homePromotion.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="resources/js/freelancer.js"></script>

	<script src="resources/js/moment-with-locales.js"></script>
</body>

</html>