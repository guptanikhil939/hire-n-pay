<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.css"
	media="screen">
<link rel="stylesheet"
	href="resources/bootstrap/css/bootstrap-datetimepicker.min.css"
	media="screen">
<link rel="stylesheet" href="resources/jQueryTE/jquery-te-1.4.0.css"
	media="screen">
<script src="resources/js/jquery.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/bootstrap/js/moment-with-locales.js"></script>
<script src="resources/bootstrap/js/bootstrap-datetimepicker.js"></script>
<script src="resources/jQueryTE/jquery-te-1.4.0.js"></script>
<style>
.a-blue {
	color: #157ab5;
	text-decoration: none;
}
</style>
<script type="text/javascript">
  $(function () {
      $('#datetimepicker_start').datetimepicker({
    	    format: 'DD/MM/YYYY HH:mm'
    	});
      $('#datetimepicker_end').datetimepicker({
  	    format: 'DD/MM/YYYY HH:mm'
  	});
  });
</script>
<script type="text/javascript">
	$(document).ready(function() {
		hideErrorMessages();
	    $(".editor").jqte();
	});

	function hideErrorMessages() {
		$("#firstNameMessageNull").hide();
		$("#phoneNumberMessageNull").hide();
		$("#phoneNumberMessageInvalid").hide();
		$("#successProfile").hide();
		$("#failProfile").hide();
		$("#successPhoto").hide();
		$("#failPhoto").hide();
		$("#successAbout").hide();
		$("#failAbout").hide();
		$("#successHobbies").hide();
		$("#failHobbies").hide();
		$("#eventNameNull").hide();
		$("#eventLocationNull").hide();
		$("#eventCoordinatorNameNull").hide();
		$("#eventStartTimeNull").hide();
		$("#eventEndTimeNull").hide();
		$("#successEvent").hide();
		$("#failEvent").hide();
	}

	function checkNull(fieldName) {
		if (null == fieldName || "" == fieldName || fieldName.length < 1) {
			return true;
		}
	}

	function validatePhoneNumber(phnNo) {
		return !((/^[0-9]*$/.test(phnNo)) && phnNo.length == 10);
	}

	function saveInfo() {
		isValid = true;
		phoneNumber = $("#phoneNumber").val();
		firstName = $("#firstName").val();
		lastName = $("#lastName").val();

		if (checkNull(firstName)) {
			$("#firstNameMessageNull").show();
			isValid = false;
		} else {
			$("#firstNameMessageNull").hide();
		}

		if (checkNull(phoneNumber)) {
			$("#phoneNumberMessageNull").show();
			$("#phoneNumberMessageInvalid").hide();
			isValid = false;
		} else if (validatePhoneNumber(phoneNumber)) {
			$("#phoneNumberMessageNull").hide();
			$("#phoneNumberMessageInvalid").show();
			isValid = false;
		} else {
			$("#phoneNumberMessageNull").hide();
			$("#phoneNumberMessageInvalid").hide();
		}

		if (isValid) {
			$.ajax({
				url : 'updateProfile',
				type : 'POST',
				data : {
					phoneNumber : phoneNumber,
					firstName : firstName,
					lastName : lastName
				},
				success : function(data) {
					if (data == "success") {
						$("#successProfile").show();//profile updated successfully
						$("#successProfile").fadeOut(2000);
					} else {
						$("#failProfile").show();
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#failProfile").show();
				}
			});
		}
	}

	function saveEvent(societyId) {
		isValid = true;
		eventName = $("#eventName").val();
		eventLocation = $("#eventLocation").val();
		eventStartDate = $("#eventStartDate").val();
		eventEndDate = $("#eventEndDate").val();
		eventCoordinator = $("#eventCoordinator").val();

		if (checkNull(eventName)) {
			$("#eventNameNull").show();
			isValid = false;
		} else {
			$("#eventNameNull").hide();
		}

		if (checkNull(eventLocation)) {
			$("#eventLocationNull").show();
			isValid = false;
		} else {
			$("#eventLocationNull").hide();
		}

		if (checkNull(eventStartDate)) {
			$("#eventStartTimeNull").show();
			isValid = false;
		} else {
			$("#eventStartTimeNull").hide();
		}

		if (checkNull(eventEndDate)) {
			$("#eventEndTimeNull").show();
			isValid = false;
		} else {
			$("#eventEndTimeNull").hide();
		}

		if (checkNull(eventCoordinator)) {
			$("#eventCoordinatorNameNull").show();
			isValid = false;
		} else {
			$("#eventCoordinatorNameNull").hide();
		}
		
		if (isValid) {
			$.ajax({
				url : 'createEvent',
				type : 'POST',
				data : {
					
					eventName : eventName,
					eventLocation : eventLocation,
					societyId : societyId,
					eventStartDate : eventStartDate,
					eventEndDate : eventEndDate,
					eventCoordinator : eventCoordinator
				},
				success : function(data) {
					if (data == "success") {
						$("#successEvent").show();//profile updated successfully
						$("#successEvent").fadeOut(2000);
					} else {
						$("#failEvent").show();
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#failEvent").show();
				}
			});
		}
	}
	
	function updatePhoto()
	{
		//fileUpload = $("#fileUpload").val();
		
		//alert(fileUpload);
		//$("#updatePhotoForm").submit();
		var formData = new FormData();
		formData.append('fileUpload', $('input[type=file]')[0].files[0]);
		//formData.append('fileUpload', $('input[name=fileUploadForm]')[0].files[0]);
		    
		$.ajax({
			url : 'updatePhoto',
			type : 'POST',
			contentType : false,
			processData : false,
			data : formData,
			success : function(data) {
				if (data == "success") {
					$("#successPhoto").show();//profile picture updated successfully
					$("#successPhoto").fadeOut(2000);
				} else {
					$("#failPhoto").show();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("#failPhoto").show();
			}
		});
	}

	function saveAbout() {
		about = $("#about").val();
		
		$.ajax({
			url : 'updateAbout',
			type : 'POST',
			data : {
				about : about
			},
			success : function(data) {
				if (data == "success") {
					$("#successAbout").show();//profile updated successfully
					$("#successAbout").fadeOut(2000);
				} else {
					$("#failAbout").show();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("#failAbout").show();
			}
		});
	}
	
	function saveHobbies() {
		hobbies = $("#hobbies").val();
		
		$.ajax({
			url : 'updateHobbies',
			type : 'POST',
			data : {
				hobbies : hobbies
			},
			success : function(data) {
				if (data == "success") {
					$("#successHobbies").show();//profile updated successfully
					$("#successHobbies").fadeOut(2000);
				} else {
					$("#failHobbies").show();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("#failHobbies").show();
			}
		});
	}
	
	function home() {
		$("#loginForm").submit();
	}

	function viewMemberId(memberId){
		$("#memberId").val(memberId);
		$("#viewMember").submit();
	}
	
	function viewAllMembers(societyId){
		$("#societyId").val(societyId);
		$("#viewAllMembers").submit();
	}
	
	function viewEventId(eventId){
		$("#eventId").val(eventId);
		$("#viewEvent").submit();
	}
	
	function viewAllEvents(societyId){
		$("#societyId").val(societyId);
		$("#viewAllEvents").submit();
	}
	
	function saveArticle(societyId) {
		article = $("#article").val();
		$("#societyId").val(societyId);

		$.ajax({
			url : 'saveArticle',
			type : 'POST',
			data : {
				article : article,
				societyId : societyId
			},
			success : function(data) {
				if (data == "success") {
					//show success dialog
				} else {
					//show failure dialog
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				//$("#fail").html(jqXHR.responseText);
				//alert(jqXHR.responseText);
			}
		});
	}
</script>
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-responsive-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">hirenpay</a>
			</div>
			<div class="navbar-collapse collapse navbar-responsive-collapse">
				<ul class="nav navbar-nav">
					<li><a href="#" onclick="home()">Home</a></li>
					<li><a href="#" onclick="viewAllMembers(${userDTO.societyId})">Members</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">My Account <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#"
								onclick="viewAllEvents(${userDTO.societyId})">Events</a></li>
							<li><a href="#">Transactions</a></li>
							<c:choose>
								<c:when test="${userDTO.isAdmin == true}">
									<li><a href="#">Edit Web Portal</a></li>
								</c:when>
							</c:choose>
						</ul></li>
				</ul>
				<form class="navbar-form navbar-right" action="logout">
					<button class="btn btn-primary btn-sm" data-toggle="modal"
						data-target="#register">Logout</button>
				</form>
				<c:choose>
					<c:when test="${userDTO.isAdmin == true}">
						<div class="navbar-form navbar-right">
							<button class="btn btn-primary btn-sm" data-toggle="modal"
								data-target="#createEvent">Create Event</button>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
	</div>
	<form id="loginForm" method="post" action="loginSuccess"></form>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>

	<div class="row">
		<div>&nbsp;</div>
		<div>&nbsp;</div>
		<div class="container">
			<div class="col-md-8">
				<div
					style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
					<div class="pull-left">
						<img src='<c:out value="${userDTO.profilePictureName}" />'
							class="media-object"
							alt='<c:out value="${userDTO.firstName} ${userDTO.lastName}" />'
							width="150"> <a href="#" class="pull-right"><img
							src="resources/images/upload.png" height="20" width="20"
							data-toggle="modal" data-target="#updatePhoto"></a>
					</div>
					<div class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;</div>
					<div class="media-body">
						<p>
						<h3 class="media-heading">
							<c:out value="${userDTO.firstName} ${userDTO.lastName}" />
							<a href="#"><img src="resources/images/pencil.png"
								height="20" width="20" data-toggle="modal"
								data-target="#updateInfo"></a>
						</h3>
						</p>
						<p>
							<c:choose>
								<c:when test="${userDTO.isAdmin == true}">
							Admin of
							</c:when>
								<c:otherwise>
							Member Of
							</c:otherwise>
							</c:choose>
							<a class="a-blue"
								href='<c:out value="${userDTO.societyWebAddress}" />.htm'
								target="new"> <c:out value="${userDTO.societyName}" /></a>
						</p>
						<p>
							<c:out value="${userDTO.phoneNumber}" />
						</p>
						<p>
							<c:out value="${userDTO.emailId}" />
						</p>
						<p>
							<button class="btn btn-primary pull-right" data-toggle="modal"
								data-target="#donate">Donate</button>
						</p>
						<p>&nbsp;</p>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="media-body"
					style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
					<h3 class="media-heading">
						About Me <a href="#"><img src="resources/images/pencil.png"
							height="20" width="20" data-toggle="modal"
							data-target="#updateAbout"></a>
					</h3>
					<c:if test="${userDTO.about.length() != 0 }">
						<c:out value="${userDTO.about}" />
					</c:if>
					<c:if
						test="${userDTO.about == null || userDTO.about.length() == 0}">
						<p>
							<i>Enter few lines about yourself</i>
						</p>
					</c:if>
				</div>
				<div class="row">&nbsp;</div>
				<div class="media-body"
					style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
					<h3 class="media-heading">
						Hobbies and Interests <a href="#"><img
							src="resources/images/pencil.png" height="20" width="20"
							data-toggle="modal" data-target="#updateHobbies"></a>
					</h3>
					<c:if test="${userDTO.hobbies.length() != 0 }">
						<c:out value="${userDTO.hobbies}" />
					</c:if>
					<c:if
						test="${userDTO.hobbies == null || userDTO.hobbies.length() == 0}">
						<p>
							<i>Enter your hobbies</i>
						</p>
					</c:if>
				</div>
				<div class="row">&nbsp;</div>
				<div class="media-body"
					style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
					<h3 class="media-heading">Web Site Content</h3>
					<textarea class="editor" id="article" name="article">Articles should be in here</textarea>
					<a id="preview" target="new" class="btn btn-info" role="button">Preview</a>
					<div class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;</div>
					<a id="submit" target="new" class="btn btn-info" role="button"
						onclick="saveArticle(${userDTO.societyId})">Submit</a>
				</div>

			</div>
			<div class="col-md-4">
				<div class="media-body"
					style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
					<h3 class="media-heading">Members of Your Society</h3>
					<div>
						<form id="viewMember" method="post" action="viewProfile">
							<input type="hidden" id="memberId" name="memberId"
								class="form-control">
							<c:forEach items="${memberList}" var="member"
								varStatus="theCount">
								<c:if test="${theCount.count < 4 }">
									<a href="#" class="pull-left a-blue" align="center"
										onclick="viewMemberId(${member.userId})"> <img
										src='<c:out value="${member.profilePictureName}" />'
										class="media-object" alt="Sample Image" width="100"> <c:out
											value="${member.firstName}" /><br> <c:out
											value="${member.lastName}" />
									</a>
									<div class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;</div>
								</c:if>
							</c:forEach>
						</form>
						<form id="viewAllMembers" method="post" action="viewAllMembers">
							<input type="hidden" id="societyId" name="societyId"
								class="form-control">
							<c:if test="${memberList.size() >= 4 }">
								<div class="pull-right">
									<a href="#" class="pull-left a-blue" align="center"
										onclick="viewAllMembers(${memberList[0].societyId})"> <c:out
											value="See all(${memberList.size()})" /></a>
								</div>
							</c:if>
						</form>
						<c:if test="${memberList.size() >= 4 }">
							<div class="pull-right">No members to show</div>
						</c:if>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<div class="media-body"
					style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
					<h3 class="media-heading">My Transactions</h3>
					<p>24-05-2015 : Donated Rs.2000</p>
					<p>24-05-2015 : Donated Rs.2000</p>
					<p>24-05-2015 : Donated Rs.2000</p>
					<p>24-05-2015 : Donated Rs.2000</p>
					<p>24-05-2015 : Donated Rs.2000</p>
				</div>
				<div class="row">&nbsp;</div>
				<div class="media-body"
					style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
					<h3 class="media-heading">All Events</h3>
					<div>
						<form id="viewEvent" method="post" action="viewEvent">
							<input type="hidden" id="eventId" name="eventId"
								class="form-control">
							<c:forEach items="${eventList}" var="event" varStatus="theCount">
								<c:if test="${theCount.count < 3 }">
									<a href="#" class="pull-left a-blue" align="center"
										onclick="viewEventId(${event.eventId})"> <fmt:formatDate
											value="${event.eventStartDate}" pattern="dd/MM/yyyy HH:mm" />
										&nbsp;&nbsp;<c:out value="${event.eventName}" />
									</a>
									<div class="row">&nbsp;</div>
								</c:if>
							</c:forEach>
						</form>
						<form id="viewAllEvents" method="post" action="viewAllEvents">
							<input type="hidden" id="societyId" name="societyId"
								class="form-control">
							<c:if test="${eventList.size() >= 3 }">
								<div class="pull-right">
									<a href="#" class="pull-left a-blue" align="center"
										onclick="viewAllEvents(${eventList[0].societyId})"> <c:out
											value="See all(${eventList.size()})" /></a>
								</div>
							</c:if>
						</form>
						<c:if test="${eventList.size() == 0 }">
							<div class="pull-left">no events to show</div>
						</c:if>
					</div>
				</div>
			</div>
			<div class="row">&nbsp;</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="updateInfo" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 500px;">
			<div class="modal-content">
				<div class="modal-header">
					<div>
						<div>
							<h4 class="modal-title" id="myModalLabel">Edit Profile</h4>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<div>
						<div class="text-danger" id="firstNameMessageNull"
							style="position: relative; text-align: left;">&#42;Please
							enter First Name</div>
						<div class="text-danger" id="phoneNumberMessageNull"
							style="position: relative; text-align: left;">&#42;Please
							enter Phone number</div>
						<div class="text-danger" id="phoneNumberMessageInvalid"
							style="position: relative; text-align: left;">&#42;Phone
							Number should be of 10 digits without any special character</div>
					</div>
					<form id="updateInfoForm" method="post">
						<div class="list-group" style="width: 400px;">
							<div id="admin" class="row" style="padding-left: 30px;">
								<div class="row">&nbsp;</div>
								<div class="form-group">
									<div>
										<div class="col-lg-6">
											<input type="text" id="firstName" name="firstName"
												class="form-control" value="${userDTO.firstName}">
										</div>
										<div class="col-lg-6">
											<input type="text" id="lastName" name="lastName"
												class="form-control" value="${userDTO.lastName}">
										</div>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div>
										<div class="col-lg-6">
											<input type="text" id="phoneNumber" name="phoneNumber"
												class="form-control" value="${userDTO.phoneNumber}">
										</div>
										<div class="col-lg-6">
											<a class="btn btn-success" onclick="saveInfo()" role="button">Save</a>
										</div>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div class="alert alert-success" id="successProfile">Profile
										Updated.</div>
									<div class="alert alert-danger" id="failProfile">Oops!
										Unable to update your Profile at this moment. We will shortly
										restore our services for you.</div>
								</div>
								<div class="row">&nbsp;</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="updatePhoto" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 500px;">
			<div class="modal-content">
				<div class="modal-header">
					<div>
						<div>
							<h4 class="modal-title" id="myModalLabel">Upload Photo</h4>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<form id="updatePhotoForm" method="post" action="updatePhoto"
						enctype="multipart/form-data">
						<div class="list-group" style="width: 400px;">
							<div id="admin" class="row" style="padding-left: 20px;">
								<div class="row">&nbsp;</div>
								<div class="form-group">
									<div>
										<input type="file" name="fileUpload" id="fileUpload" />
									</div>
									<div class="row">&nbsp;</div>
									<div>
										<a class="btn btn-success" onclick="updatePhoto()"
											role="button">Upload</a>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div class="alert alert-success" id="successPhoto">Profile
										Picture Updated.</div>
									<div class="alert alert-danger" id="failPhoto">Oops!
										Unable to update your Profile Picture at this moment. We will
										shortly restore our services for you.</div>
								</div>
								<div class="row">&nbsp;</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="donate" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 500px;">
			<div class="modal-content">
				<div class="modal-header">
					<div>
						<div>
							<h4 class="modal-title" id="myModalLabel">Donate</h4>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<p>To donate, kindly follow steps mentioned below :</p>
					<ol>
						<li>Make a DD in favour of "hirenpay"</li>
						<li>Write your name, phone number and address behind DD</li>
						<li>Send it at our Office Address "23/A High Street,
							Kakadpur, Bangalore - 121345"</li>
					</ol>
					<p>Once received we will send across your receipt in 7 working
						days and the same will be reflected in your "My Transactions"
						section.</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="updateAbout" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 500px;">
			<div class="modal-content">
				<div class="modal-header">
					<div>
						<div>
							<h4 class="modal-title" id="myModalLabel">About Myself</h4>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<form id="updateInfoAbout" method="post">
						<div class="list-group" style="width: 400px;">
							<div id="admin" class="row" style="padding-left: 30px;">
								<div class="row">&nbsp;</div>
								<div class="form-group">
									<div class="row">
										<input type="text" id="about" name="about"
											class="form-control" value="${userDTO.about}">
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">
										<a class="btn btn-success" onclick="saveAbout()" role="button">Save</a>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div class="alert alert-success" id="successAbout">Profile
										Updated.</div>
									<div class="alert alert-danger" id="failAbout">Oops!
										Unable to update your Profile at this moment. We will shortly
										restore our services for you.</div>
								</div>
								<div class="row">&nbsp;</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="updateHobbies" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 500px;">
			<div class="modal-content">
				<div class="modal-header">
					<div>
						<div>
							<h4 class="modal-title" id="myModalLabel">My Hobbies</h4>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<form id="updateInfoHobbies" method="post">
						<div class="list-group" style="width: 400px;">
							<div id="admin" class="row" style="padding-left: 30px;">
								<div class="row">&nbsp;</div>
								<div class="form-group">
									<div class="row">
										<input type="text" id="hobbies" name="hobbies"
											class="form-control" value="${userDTO.hobbies}">
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">
										<a class="btn btn-success" onclick="saveHobbies()"
											role="button">Save</a>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div class="alert alert-success" id="successHobbies">Profile
										Updated.</div>
									<div class="alert alert-danger" id="failHobbies">Oops!
										Unable to update your Profile at this moment. We will shortly
										restore our services for you.</div>
								</div>
								<div class="row">&nbsp;</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="createEvent" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 700px;">
			<div class="modal-content">
				<div class="modal-header">
					<div>
						<div>
							<h4 class="modal-title" id="myModalLabel">Create an Event</h4>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<div>
						<div class="text-danger" id="eventNameNull"
							style="position: relative; text-align: left;">&#42;Please
							enter Event Name</div>
						<div class="text-danger" id="eventLocationNull"
							style="position: relative; text-align: left;">&#42;Please
							enter Event location</div>
						<div class="text-danger" id="eventStartTimeNull"
							style="position: relative; text-align: left;">&#42;Please
							enter Event start Time</div>
						<div class="text-danger" id="eventEndTimeNull"
							style="position: relative; text-align: left;">&#42;Please
							enter Event end Time</div>
						<div class="text-danger" id="eventCoordinatorNameNull"
							style="position: relative; text-align: left;">&#42;Please
							enter Event Coordinator's name</div>
					</div>
					<form id="createEventForm" method="post">
						<div class="list-group" style="width: 600px;">
							<div id="admin" class="row" style="padding-left: 30px;">
								<div class="row">&nbsp;</div>
								<div class="form-group">
									<div>
										<div class="col-lg-6">
											<input type="text" id="eventName" name="eventName"
												class="form-control" placeholder="Event Name&#42;">
										</div>
										<div class="col-lg-6">
											<input type="text" id="eventLocation" name="eventLocation"
												class="form-control" placeholder="Event Location&#42;">
										</div>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div>
										<div class="col-lg-6">
											<div class='input-group date' id='datetimepicker_start'>
												<input type='text' class="form-control" id="eventStartDate"
													name="eventStartDate" /> <span class="input-group-addon">
													<span class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class='input-group date' id='datetimepicker_end'>
											<input type='text' class="form-control" id="eventEndDate"
												name="eventEndDate" /> <span class="input-group-addon">
												<span class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>
								</div>
								<div class="row">&nbsp;</div>
								<div class="row">&nbsp;</div>
								<div>
									<div class="col-lg-6">
										<input type="text" id="eventCoordinator"
											name="eventCoordinator" class="form-control"
											placeholder="Event Coordinator&#42;">
									</div>
									<div class="col-lg-6">
										<a class="btn btn-success"
											onclick="saveEvent(${userDTO.societyId})" role="button">Create
											Event</a>
									</div>
								</div>
								<div class="row">&nbsp;</div>
								<div class="row">&nbsp;</div>
								<div class="alert alert-success" id="successEvent">Event
									Created</div>
								<div class="alert alert-danger" id="failEvent">Oops!
									Unable to create event at this moment. We will shortly restore
									our services for you.</div>
							</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
						</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	</div>
</body>
</html>