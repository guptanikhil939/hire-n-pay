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

<title>Hire N Pay</title>

<!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/datepicker/css/datepicker.css" rel="stylesheet">
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
<link rel="stylesheet" href="resources/Morphext/dist/animate.css"
	type="text/css">
<link rel="stylesheet" href="resources/Morphext/dist/morphext.css"
	type="text/css">
<script src="resources/js/jquery.js"></script>
<script src="resources/datepicker/js/bootstrap-datepicker.js"></script>
<script src="resources/Morphext/dist/morphext.js"></script>
<style>
.googlePlay {
	background: black url("resources/img/downloadApp.png") no-repeat scroll
		center center/cover;
	display: inline-block;
	height: 75px;
	width: 250px;
}
</style>
<script>
	var sessionPhoneNumber = "<%=sessionPhoneNumber%>";
</script>
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

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div>
			<ul id="bs-example-navbar-collapse-1"
				class="navbar-collapse nav navbar-nav navbar-right">
				<li class="hidden"><a href="#page-top"></a></li>
				<li class="page-scroll"><a href="#services"
					id="servicesMenuOption">SERVICES</a></li>
				<li class="page-scroll" id="loginOption"><a href="#login">LOGIN</a></li>
				<li class="page-scroll" id="signUpOption"><a href="#contact">SIGN-UP</a></li>
				<li class="page-scroll"><a href="#about">ABOUT</a></li>
				<li style="display: none;" class=" dropdown page-scroll"
					id="account"><a class="dropdown-toggle" data-toggle="dropdown"
					href="#" title="My Account">ACCOUNT<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="/myRequests"><i
								class="fa fa-shopping-cart fa-fw"></i> My Orders</a></li>
						<li><a href="/myProfile"><i class="fa fa-user fa-fw"></i>
								My Profile</a></li>
						<li class="divider"></li>
						<li><a href="/logout">Logout</a></li>
					</ul></li>
			</ul>
		</div>

		<div></div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<!-- Header -->
	<header>
	<div class="container">
		<div class="row">
			<div class="col-lg-2">
				<a href="https://play.google.com/store/apps/details?id=com.hirenpay"
					target="new"><li class="googlePlay"></li></a>
			</div>
			<div class="col-lg-6">&nbsp;</div>
			<div
				class="col-lg-3 form-group floating-label-form-group controls text-left">
				<label>Enter your number to get the app</label> <input type="text"
					class="form-control" placeholder="Phone Number"
					id="subscriberPhoneNumber" maxlength="10"> <span
					class="text-danger" id="subscriberPhoneNumberWarning"
					style="display: none;">Please enter Phone Number.</span> <span
					class="text-danger" id="subscriberPhoneNumberLengthWarning"
					style="display: none;">Phone Number should be of 10 digits.</span>
				<span class="text-danger" id="failureSubscribeMessage"
					style="display: none;"></span> <span class="text-success"
					id="successSubscribeMessage" style="display: none;"></span>
			</div>
			<div class="col-lg-1">
				<button class="btn btn-success btn-md" id="getAppButton">GET
					APP</button>
			</div>
		</div>
		<div class="row">&nbsp;</div>
		<div class="row">&nbsp;</div>
		<div class="row">&nbsp;</div>
		<div class="col-lg-12">
			<div class="intro-text" id="homePageContent" style="display: none;">
				<span class="name" id="logoHireNPay"><span id="js-rotating1">Hire</span>
					<span id="js-rotating2">N</span> <span id="js-rotating3">Pay</span></span>
				<hr class="star-primary">
				<span class="skills" id="tagLine" style="display: none;">Now
					Convenience at your Fingertips in Gurgaon, Noida and Faridabad</span><br>
			</div>
		</div>
	</div>
	</header>
	<div class="text-center">
		<div class="col-lg-4">&nbsp;</div>
		<div class="col-lg-6 text-left" id="hintText" style="display: none;">
			Outsource your 'Daily Chores' & 'Service Needs'. <b
				class="text-warning"><span id="servicesName"
				style="display: none;">Electrician, Plumber, Carpenter,
					Painter, Doorstep Laundry, Bill Payments, AC Service/Repair, Car
					Care, House Renovator/Repair, Home Cleaning, Pest Control, Water
					Proofing</span>!</b>
		</div>
		<div class="col-lg-2">
			<p class="text-success text-center" style="display: none;">
				<strong id="successMessage"></strong>
			</p>
		</div>
	</div>
	<!-- Portfolio Grid Section -->
	<jsp:include page="services.jsp" />
	<!-- Login Section -->
	<jsp:include page="login.jsp" />
	<!-- SignUp Section -->
	<jsp:include page="signup.jsp" />
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
	<!-- Home And Office -->
	<jsp:include page="homeAndOffice.jsp" />
	<!-- Delivery Services -->
	<jsp:include page="deliveryServices.jsp" />
	<!-- Delivery Services -->
	<jsp:include page="eventsAndFunctions.jsp" />
	<!-- Delivery Services -->
	<jsp:include page="consultantsAndTutors.jsp" />
	<!-- Delivery Services -->
	<jsp:include page="designAndOnlineServices.jsp" />
	<!-- Delivery Services -->
	<jsp:include page="healthAndBeauty.jsp" />

	<!-- jQuery ui-->
	<script src="resources/js/jquery-ui/jquery-ui.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="resources/js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="resources/js/jquery.easing.min.js"></script>
	<script src="resources/js/classie.js"></script>
	<script src="resources/js/cbpAnimatedHeader.js"></script>

	<!-- Services JavaScript -->
	<script src="resources/js/home.js"></script>
	<script src="resources/js/deliveryServices.js"></script>
	<script src="resources/js/haoServiceOrder.js"></script>
	<script src="resources/js/login.js"></script>
	<script src="resources/js/signUp.js"></script>
	<script src="resources/js/commonUtil.js"></script>
	<script src="resources/js/userSession.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="resources/js/freelancer.js"></script>

	<script src="resources/js/moment-with-locales.js"></script>
	<script src="resources/js/bootstrap-datetimepicker.js"></script>

	<input type="hidden" value="<%=sessionPhoneNumber%>"
		id="sessionPhoneNumber">
	<input type="hidden" value="" id="selectedSerivceList">

</body>

</html>