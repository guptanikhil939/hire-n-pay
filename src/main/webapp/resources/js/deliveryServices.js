function hideDsDateTimePicker() {
	$('#dsDatetimepicker_start').hide();
}

function showDsDateTimePicker() {
	$('#dsDatetimepicker_start').show();
	$('#dsConfirmRequest').focus();
	$('#dsDate').focus();
	$('#dsDate').click();
}

$('#dsDate').click(function() {
	$('#dsPickupTimeWarning').hide();
});

function checkTime() {

	var dsTimeHours = $('#dsTimeHours').val();

	if (dsTimeHours < 9 || dsTimeHours == 12) {
		$("#dsTimeAmPm").val('pm');
	} else {
		$("#dsTimeAmPm").val('am');
	}
}

function dsValidateRequest() {
	pickupItem = $('#dsPickupItem').val();
	pickupAddress1 = $('#dsPickupAddress1').val();
	pickupAddress2 = $('#dsPickupAddress2').val();
	dropAddress1 = $('#dsDropAddress1').val();
	dropAddress2 = $('#dsDropAddress2').val();
	dsRequestPhoneNumber = $('#dsRequestPhoneNumber').val();
	requestDate = $('#dsDate').val();

	var time = $('input[name=dsTime]:checked').val();

	if (time == 1 && (requestDate == null || requestDate == "")) {
		$('#dsPickupTimeWarning').show();
		$('#dsPickupTime').focus();
		return false;
	} else if (time == 0
			|| (time == 1 && requestDate != null && requestDate != "")) {
		$('#dsPickupTimeWarning').hide();
		if (pickupItem == null || pickupItem == "") {
			$('#dsPickupItemWarning').show();
			$('#dsPickupItem').focus();
			return false;
		} else if (pickupItem != null && pickupItem != "") {
			$('#dsPickupItemWarning').hide();
			if (pickupAddress1 == null || pickupAddress1 == "") {
				$('#dsPickupAddressWarning1').show();
				$('#dsPickupAddress1').focus();
				return false;
			} else if (pickupAddress1 != null && pickupAddress1 != "") {
				$('#dsPickupAddressWarning1').hide();
				if (pickupAddress2 == null || pickupAddress2 == "") {
					$('#dsPickupAddressWarning2').show();
					$('#dsPickupAddress2').focus();
					return false;
				} else if (pickupAddress2 != null && pickupAddress2 != "") {
					$('#dsPickupAddressWarning2').hide();
					if (dropAddress1 == null || dropAddress1 == "") {
						$('#dsDropAddressWarning1').show();
						$('#dsDropAddress1').focus();
						return false;
					} else if (dropAddress1 != null && dropAddress1 != "") {
						$('#dsDropAddressWarning1').hide();
						if (dropAddress2 == null || dropAddress2 == "") {
							$('#dsDropAddressWarning2').show();
							$('#dsDropAddress2').focus();
							return false;
						} else if (dropAddress2 != null && dropAddress2 != "") {
							$('#dsDropAddressWarning2').hide();

							message = checkPhoneNumber(dsRequestPhoneNumber);

							if (dsRequestPhoneNumber == null
									|| dsRequestPhoneNumber == ""
									|| message != "success") {
								$('#dsRequestPhoneNumberWarning').html(message);
								$('#dsRequestPhoneNumberWarning').show();
								$('#dsRequestPhoneNumber').focus();
								return false;
							} else if (pickupAddress2 != null
									&& pickupAddress2 != ""
									&& message == "success") {
								$('#dsRequestPhoneNumberWarning').hide();
							}
						}
					}
				}
			}
		}
	}

	return true;
}

function dsSendRequestConfirmationOTP() {

	requestPhoneNumber = $('#dsRequestPhoneNumber').val();

	$
			.ajax({
				url : '/orderWeb/sendRequestConfirmationOTP',
				type : 'POST',
				async : false,
				data : {
					phoneNumber : requestPhoneNumber
				},
				success : function(data) {
					if (data == "success") {
						$('#dsOtpBox').show();
						$('#dsOtpRequestMessage').show();
						$('#dsConfirmRequest').hide();
						$('#dsGetOTP').show();
					} else {
						$("#dsConfirmOtpWarning")
								.html(
										"Unable to send OTP at this moment. Please try again later.");
						$("#dsConfirmOtpWarning").show();
						$("#dsOtpBox").hide();
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#dsConfirmOtpWarning")
							.html(
									"Unable to process your request at this moment. Please try again.");
					$("#dsServiceSuccessMessage").hide();// login
					// unsuccessful
				}
			});
}

function dsConfirmRequestConfirmationOTP() {

	requestPhoneNumber = $('#dsRequestPhoneNumber').val();
	requestOTP = $('#dsRequestOTP').val();

	$
			.ajax({
				url : '/orderWeb/confirmRequestConfirmationOTP',
				type : 'POST',
				async : false,
				data : {
					phoneNumber : requestPhoneNumber,
					otp : requestOTP
				},
				success : function(data) {
					if (data == "success") {
						$("#dsConfirmOtpWarning").hide();
						if (dsValidateRequest()) {
							dsPlaceOrder();
						}
					} else {
						$("#dsConfirmOtpWarning").html(
								"Invalid OTP. Please try again.");
						$("#dsConfirmOtpWarning").show();
						$("#dsServiceSuccessMessage").hide();
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#dsConfirmOtpWarning")
							.html(
									"Unable to process your request at this moment. Please try again.");
					$("#dsServiceSuccessMessage").hide();// login unsuccessful
				}
			});

	return false;
}

function dsPlaceOrder() {
	
	dsPickupItem = $('#dsPickupItem').val();
	requestPhoneNumber = $('#dsRequestPhoneNumber').val();
	pickupFlatNumber = $('#dsPickupAddress1').val();
	pickupLocation = $('#dsPickupAddress2').val();
	dropFlatNumber = $('#dsDropAddress1').val();
	dropLocation = $('#dsDropAddress2').val();
	dsDate = $('#dsDate').val();
	dsTimeHours = $('#dsTimeHours').val();
	dsTimeMinutes = $('#dsTimeMinutes').val();
	dsTimeAmPm = $('#dsTimeAmPm').val();
	time = $('input[name=time]:checked').val();
	pickupCity = $('input[name=dsPickupCity]:checked').val();
	dropCity = $('input[name=dsDropCity]:checked').val();

	dsServiceCategoryId = "2";

	$
			.ajax({
				url : '/orderWeb/confirmRequest',
				type : 'POST',
				async : false,
				data : {
					itemName : dsPickupItem,
					pickupPhoneNumber : requestPhoneNumber,
					pickupFlatNumber : pickupFlatNumber,
					pickupLocation : pickupLocation,
					pickupCity : pickupCity,
					dropFlatNumber : dropFlatNumber,
					dropLocation : dropLocation,
					dropCity : dropCity,
					date : dsDate,
					timeHours : dsTimeHours,
					timeMinutes : dsTimeMinutes,
					timeAmPm : dsTimeAmPm,
					time : time,
					serviceCategoryId : dsServiceCategoryId
				},
				success : function(data) {
					if (data == "success") {
						$('#dsServiceSuccessMessage').show();
						$('#dsServiceSuccessMessage').html(
								"Request Confirmed. We will call you shortly at "
										+ requestPhoneNumber + ".");
						$('#dsServiceFailure').hide();
						$('#dsPickupAddress1').val("");
						$('#dsPickupAddress2').val("");
						$('#dsDropAddress1').val("");
						$('#dsDropAddress2').val("");
						$('#dsPickupTime').val("");
						$('#dsRequestPhoneNumber').val("");
						$('#dsDate').val("");
						$('#dsTimeHours').val("");
						$('#dsTimeMinutes').val("");
						$('#dsTimeAmPm').val("");
						$('#dsOtpBox').val("");
						$('#dsOtpBox').hide();
						
						$('#dsPickupItem').focus();

						return false;

					} else {
						$("#dsServiceFailure").show();
						$("#dsServiceFailureMessage")
								.html(
										"Unable to process your request at this moment. Please try again.");
						$("#dsServiceSuccessMessage").hide();// login unsuccessful
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#dsServiceFailureMessage")
							.html(
									"Unable to process your request at this moment. Please try again.");
					$("#dsServiceSuccessMessage").hide();// login unsuccessful
				}
			});
}

function dsConfirmRequest() {
	result = dsValidateRequest();

	if (result) {
		dsSendRequestConfirmationOTP();
	}
}

$(function() {
	$('#dsDate').datepicker({
		format : 'dd/mm/yyyy',
		sideBySide : true,
		startDate : '0d',
		endDate : '+7d',
		useCurrent : true
	});
});