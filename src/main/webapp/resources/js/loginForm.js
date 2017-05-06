$('#loginFormPassword').bind("cut copy paste", function(e) {
	e.preventDefault();
});

$('#loginFormMessage').html(
		"You are not currently Logged In. Please Login to confirm request.");

$('#backButton').click(function() {
	$("#haoContainer").show("slide", {
		direction : "left"
	}, 1000);
	$("#haoLoginForm").hide();
});

$('#loginFormButton').click(function() {
	phoneNumber = $('#loginFormPhoneNumber').val();
	password = $('#loginFormPassword').val();

	if (phoneNumber == null || phoneNumber == "" || phoneNumber.length < 1) {
		$('#loginFormPhoneNumberWarning').removeClass("hide");
		$('#loginFormPhoneNumber').focus();
		return false;
	} else if ((password == null || password == "" || phoneNumber.length < 1)) {
		$('#loginFormPhoneNumberWarning').addClass("hide");
		$('#loginFormPasswordWarning').removeClass("hide");
		$('#loginFormPassword').focus();
		return false;
	} else {
		$('#loginFormPhoneNumberWarning').addClass("hide");
		$('#loginFormPasswordWarning').addClass("hide");
		loginForm(phoneNumber, password);
		return false;
	}
});

function loginForm(phoneNumber, password) {
	$
			.ajax({
				url : '/login/auth',
				type : 'POST',
				async : false,
				data : {
					phoneNumber : phoneNumber,
					password : password
				},
				success : function(data) {
					if (data == "success") {
						sessionPhoneNumber = phoneNumber;
						userLoggedIn();
						placeOrder(sessionPhoneNumber);
					} else if (data == "failure") {
						$("#failureLoginFormMessage")
								.html(
										"Unable to process your request at this moment. Please try again.");
						$('#haoSelectedService').html(
								"Please choose Services.");
						$('#inputFormBox').hide();
						return false;
					} else {
						$('#failureLoginFormMessage').html(data);
						$('#haoSelectedService').html(
								"Please choose Services.");
						$('#inputFormBox').hide();
						return false;
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#failureLoginFormMessage")
							.html(
									"Unable to process your request at this moment. Please try again.");
				}
			});
}