$('#loginPassword').bind("cut copy paste", function(e) {
	e.preventDefault();
});

$('#success').hide();
$('#failureLogin').hide();
$('#loginPhoneNumberWarning').hide();
$('#loginPasswordWarning').hide();

$('#loginButton')
		.click(
				function() {
					phoneNumber = $('#loginPhoneNumber').val();
					password = $('#loginPassword').val();

					if (phoneNumber == null || phoneNumber == ""
							|| phoneNumber.length < 1) {
						$('#loginPhoneNumberWarning').show();
						$('#loginPhoneNumberWarning').html(
								"Please enter Phone Number.");
						$('#loginPhoneNumber').focus();
						return false;
					} else if (phoneNumber.length != 10) {
						$('#loginPhoneNumberWarning').show();
						$('#loginPhoneNumberWarning').html(
								"Phone Number should be of 10 digits.");
						$('#loginPhoneNumber').focus();
						return false;
					} else if ((password == null || password == "" || password.length < 1)) {
						$('#loginPhoneNumberWarning').hide();
						$('#loginPasswordWarning').show();
						$('#loginPassword').focus();
						return false;
					} else if (phoneNumber != null && phoneNumber != "") {
						$('#loginPasswordWarning').hide();
						login(phoneNumber, password);
						return false;
					}
				});

$('#forgotPasswordButton').click(function() {
	phoneNumber = $('#loginPhoneNumber').val();

	if (phoneNumber == null || phoneNumber == "" || phoneNumber.length < 1) {
		$('#loginPhoneNumberWarning').show();
		$('#loginPhoneNumberWarning').html("Please enter Phone Number.");
		$('#loginPhoneNumber').focus();

		return false;
	} else {
		var message = checkPhoneNumber(phoneNumber);

		if (message == "success") {
			$('#loginPhoneNumberWarning').hide();
			forgotPassword(phoneNumber);

			return false;
		} else {
			$('#loginPhoneNumberWarning').show();
			$('#loginPhoneNumberWarning').html(message);
			$('#loginPhoneNumber').focus();

			return false;
		}
	}
});

function login(phoneNumber, password) {
	$
			.ajax({
				url : '/login/auth',
				type : 'POST',
				data : {
					phoneNumber : phoneNumber,
					password : password
				},
				success : function(data) {
					if (data == "success") {
						$("#success").show();// login successful
						$("#failureLoginMessage").hide();
						userLoggedIn();
						$('#loginPhoneNumber').val("");
						$('#loginPassword').val("");
						$('#brand').click();
						sessionPhoneNumber = phoneNumber;
						$("#warningLoginMessage").hide();
					} else if (data == "failure") {
						$("#failureLoginMessage").show();
						$("#failureLoginMessage")
								.html(
										"Unable to process your request at this moment. Please try again.");
						$("#successLogin").hide();// login unsuccessful
						$("#warningLoginMessage").hide();
						return false;
					} else {
						$("#failureLoginMessage").show();
						$('#failureLoginMessage').html(data);
						$("#success").hide();// login unsuccessful
						$("#warningLoginMessage").hide();
						return false;
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#failureLoginMessage").show();
					$("#warningLoginMessage").hide();
					$("#failureLoginMessage")
							.html(
									"Unable to process your request at this moment. Please try again.");
					$("#success").hide();// login unsuccessful
				}
			});
}

function forgotPassword(phoneNumber) {

	$
			.ajax({
				url : '/login/forgotPassword',
				type : 'POST',
				data : {
					phoneNumber : phoneNumber
				},
				success : function(data) {
					if (data == "success") {
						$("#warningLoginMessage").show();
						$("#warningLoginMessage")
								.html(
										"A temporary password has been sent on your mobile. Please change after login.");
					} else if (data == "failure") {
						$("#failureLoginMessage").show();
						$("#failureLoginMessage")
								.html(
										"Unable to process your request at this moment. Please try again.");
						$("#successLoginMessage").hide();
						$("#warningLoginMessage").hide();
						return false;
					} else {
						$("#failureLoginMessage").show();
						$('#failureLoginMessage').html(data);
						$("#success").hide();
						return false;
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#failureLoginMessage").show();
					$("#failureLoginMessage")
							.html(
									"Unable to process your request at this moment. Please try again.");
					$("#successLoginMessage").hide();
					$("#warningLoginMessage").hide();
					return false;
				}
			});
}