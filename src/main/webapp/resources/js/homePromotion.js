$(document).ready(function() {
	$('#myModal').modal('show');
	$('#subscriberPhoneNumberWarning').hide();
	$('#subscriberPhoneNumberLengthWarning').hide();
	$("#success").hide();
	$("#successSubscribe").hide();
	$("#failureSubscribe").hide();
});

$('#subscribeButton').click(function() {
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
										"Thanks for subscribing. We will update you soon.");
						$("#successSubscribe").show();// login successful
						$("#failureSubscribe").hide();
						$('#subscriberPhoneNumber').val("");
					} else if (data == "failure") {
						$("#failureSubscribe").show();
						$("#failureSubscribeMessage")
								.html(
										"Unable to process your request at this moment. Please try again.");
						$("#successSubscribe").hide();// login unsuccessfull
						return false;
					} else {
						$("#failureSubscribe").show();
						$('#failureSubscribeMessage').html(data);
						$("#successSubscribe").hide();// login unsuccessfull
						return false;
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#failureSubscribe").show();
					$("#failureSubscribeMessage")
							.html(
									"Unable to process your request at this moment. Please try again.");
					$("#successSubscribe").hide();// login unsuccessfull
				}
			});
}