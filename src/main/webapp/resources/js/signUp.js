$('#password').bind("cut copy paste", function(e) {
	e.preventDefault();
});

$('#confirmPassword').bind("cut copy paste", function(e) {
	e.preventDefault();
});

$('#loginPassword').bind("cut copy paste", function(e) {
	e.preventDefault();
});

$('#phoneNumberWarning').hide();
$('#passwordWarning').hide();
$('#confirmPasswordWarning').hide();
$('#OTPbox').hide();
$('#otpWarning').hide();
$('#confirmOTP').hide();
$('#failureSignup').hide();
$('#successSignup').hide();

$('#signUp').click(
		function() {
			phoneNumber = $('#phoneNumber').val();
			password = $('#password').val();
			confirmPassword = $('#confirmPassword').val();

			if (phoneNumber == null || phoneNumber == "") {
				$('#phoneNumberWarning').show();
				$('#phoneNumberWarning').html("Please enter Phone Number.");
				$('#phoneNumber').focus();
				return false;
			} else if (phoneNumber != null && phoneNumber != "") {
				$('#phoneNumberWarning').hide();

				var message = checkPhoneNumber(phoneNumber);

				if (message == "success") {
					$('#phoneNumberWarning').hide();

					var message = checkPassword(phoneNumber, password,
							confirmPassword);

					if (message == "success") {
						$('#passwordWarning').hide();
						$('#otp').val("");
						sendOTP(phoneNumber);
					} else {
						$('#passwordWarning').show();
						$('#passwordWarning').html(message);
						$('#password').focus();
						return false;
					}

				} else {
					$('#phoneNumberWarning').show();
					$('#phoneNumberWarning').html(message);
					$('#phoneNumber').focus();

					return false;
				}
			}
		});

function sendOTP(phoneNumber) {

	$
			.ajax({
				url : '/web/register',
				type : 'POST',
				data : {
					phoneNumber : phoneNumber
				},
				success : function(data) {
					if (data == "success") {
						$('#confirmOTP').show();
						$('#signUp').hide();
						$('#OTPbox').show();
						$('#failureSignupMessage').hide();
						$('#successSignupMessage').show();
						$('#successSignupMessage').html(
								"OTP Sent to your mobile number " + phoneNumber
										+ ".");
					} else if (data == "failure") {
						$('#confirmOTP').hide();
						$('#signUp').show();
						$('#OTPbox').hide();
						$('#successSignupMessage').hide();
						$('#failureSignupMessage').show();
						$('#failureSignupMessage')
								.html(
										"Unable to process your request at this moment. Please try again.");
					} else {
						$('#confirmOTP').hide();
						$('#signUp').show();
						$('#OTPbox').hide();
						$('#successSignupMessage').hide();
						$('#failureSignupMessage').show();
						$('#failureSignupMessage').html(data);
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$('#confirmOTP').hide();
					$('#signUp').show();
					$('#OTPbox').hide();
					$('#successSignupMessage').hide();
					$('#failureSignupMessage').show();
					$('#failureSignupMessage')
							.html(
									"Unable to process your request at this moment. Please try again.");
				}
			});
}

$('#confirmOTP').click(function() {

	otp = $('#otp').val();

	if (otp == null || otp == "") {
		$('#otpWarning').show();
		$('#otp').focus();
		return false;
	} else if (otp != null && otp != "") {
		$('#otpWarning').hide();

		confirmOTP(otp);
	}
});

function confirmOTP(otp) {
	$
			.ajax({
				url : '/web/confirmOTP',
				type : 'POST',
				data : {
					phoneNumber : phoneNumber,
					password : password,
					otp : otp
				},
				success : function(data) {
					if (data == "success") {
						$('#phoneNumber').val("");
						$('#password').val("");
						$('#confirmPassword').val("");
						$('#otp').val("");
						$('#confirmOTP').hide();
						$('#signUp').show();
						$('#OTPbox').hide();
						$('#failureSignup').hide();
						$('#successSignup').show();
						$('#successSignupMessage').html(
								"Registration Succesful.");
					} else if (data == "failure") {
						$('#confirmOTP').hide();
						$('#signUp').show();
						$('#OTPbox').hide();
						$('#successSignup').hide();
						$('#failureSignup').show();
						$('#failureSignupMessage')
								.html(
										"Unable to process your request at this moment. Please try again.");
					} else {
						$('#confirmOTP').hide();
						$('#signUp').show();
						$('#OTPbox').hide();
						$('#successSignup').hide();
						$('#failureSignup').show();
						$('#failureSignupMessage').html(data);
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$('#confirmOTP').hide();
					$('#signUp').show();
					$('#OTPbox').hide();
					$('#successSignup').hide();
					$('#failureSignup').show();
					$('#failureSignupMessage')
							.html(
									"Unable to process your request at this moment. Please try again.");
				}
			});
}