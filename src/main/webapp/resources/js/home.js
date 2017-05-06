$(document).ready(function() {

	if (null != sessionPhoneNumber && sessionPhoneNumber != "") {
		$('#account').show();
		$('#loginOption').hide();
		$('#login').hide();
		$('#signUpOption').hide();
		$('#contact').hide();
	}

	var morphext1 = $("#js-rotating1").Morphext({
		animation : "bounceInLeft",
		complete : function() {
			// Hire
		}
	});

	$("#homePageContent").show();

	var data1 = morphext1.data("plugin_Morphext");

	var morphext2 = $("#js-rotating2").Morphext({
		animation : "bounceInDown",
		complete : function() {
			// N
		}
	});

	var data2 = morphext2.data("plugin_Morphext");

	var morphext3 = $("#js-rotating3").Morphext({
		animation : "bounceInRight",
		complete : function() {
			// Pay
		}
	});

	var data3 = morphext3.data("plugin_Morphext");

	$("#servicesMenuOption").Morphext({
		animation : "wobble",
		complete : function() {
			this.stop();
		}
	});

	$("#hintText").show();
	$("#servicesName").show();

	$("#servicesName").Morphext({
		animation : "fadeIn",
		speed : 3000,
		complete : function() {
		}
	});

	data1.stop();
	data2.stop();
	data3.stop();
	
	$("#tagLine").show();
	
	var morphext4 = $("#tagLine").Morphext({
		animation : "flipInX",
		separator : "|",
		complete : function() {
		}
	});

	var data4 = morphext4.data("plugin_Morphext");
	
	data4.stop();
});

function userLoggedIn() {
	$("#successMessage").show();
	$("#successMessage").html("Logged In");
	$("#successMessage").fadeOut(10000);
	$('#account').show();
	$('#loginOption').hide();
	$('#login').hide();
	$('#signUpOption').hide();
	$('#contact').hide();
}

$('#getAppButton').click(function() {
	phoneNumber = $('#subscriberPhoneNumber').val();

	if (phoneNumber == null || phoneNumber == "" || phoneNumber.length < 1) {
		$('#subscriberPhoneNumberWarning').show();
		$('#subscriberPhoneNumberLengthWarning').hide();
		$('#subscriberPhoneNumber').focus();
		return false;
	} else if (phoneNumber.length != 10) {
		$('#subscriberPhoneNumberWarning').hide();
		$('#subscriberPhoneNumberLengthWarning').show();
		$('#subscriberPhoneNumber').focus();
		return false;
	} else if (phoneNumber != null && phoneNumber != "") {
		$('#subscriberPhoneNumberWarning').hide();
		$('#subscriberPhoneNumberLengthWarning').hide();
		subscribe(phoneNumber);
		return false;
	}
});

function subscribe(subscriberPhoneNumber) {
	$
			.ajax({
				url : '/subscribe',
				type : 'POST',
				data : {
					subscriberPhoneNumber : subscriberPhoneNumber
				},
				success : function(data) {

					if (data == "success") {
						$('#successSubscribeMessage')
								.html(
										"We have sent you a text message to download app.");
						$("#successSubscribeMessage").show();// Invitation
						// Sent
						// Successfully
						$("#failureSubscribeMessage").hide();
						$('#subscriberPhoneNumber').val("");
					} else if (data == "failure") {
						$("#failureSubscribeMessage")
								.html(
										"Unable to process your request at this moment. Please try again later.");
						$("#failureSubscribeMessage").show();
						$("#successSubscribeMessage").hide();
						return false;
					} else {
						$('#failureSubscribeMessage').html(data);
						$("#failureSubscribeMessage").show();
						$("#successSubscribeMessage").hide();
						return false;
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#failureSubscribeMessage")
							.html(
									"Unable to process your request at this moment. Please try again laters.");
					$("#failureSubscribeMessage").show();
					$("#successSubscribeMessage").hide();
				}
			});
}