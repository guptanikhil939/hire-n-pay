$('#haoSelectedService').html("Please choose Services.");
$('#inputFormBox').hide();

var selectedServiceList = new Array();

function hideDateTimePicker() {
	$('#haoDatetimepicker_start').hide();
}

function showDateTimePicker() {
	$('#haoDatetimepicker_start').show();
	$('#haoConfirmRequest').focus();
	$('#haoDate').focus();
	$('#haoDate').click();
}

$('#haoDate').click(function() {
	$('#haoPickupTimeWarning').hide();
});

function checkTime() {

	var haoTimeHours = $('#haoTimeHours').val();

	if (haoTimeHours < 9 || haoTimeHours == 12) {
		$("#haoTimeAmPm").val('pm');
	} else {
		$("#haoTimeAmPm").val('am');
	}
}

function selectedService(serviceId) {

	$("#haoServiceSuccessMessage").hide();

	if (selectedServiceList.length == 0) {
		$('#haoSelectedService').html("");
		$('#inputFormBox').show();
	}

	deliveryItem = $('#haoService' + serviceId).html();
	itemAlreadyInList = false;

	for (i = 0; i < selectedServiceList.length; i++) {
		serviceName = "service" + serviceId;
		if (serviceName == selectedServiceList[i]) {
			itemAlreadyInList = true;
		}
	}

	if (itemAlreadyInList == false) {
		selectedServiceList.push("service" + serviceId);
		item = "<div class='badge' id='haoRemove"
				+ serviceId
				+ "'>"
				+ deliveryItem
				+ "&nbsp;&nbsp;&nbsp;&nbsp;<a href ='#' onclick=\"removeItemFromList('"
				+ serviceId + "')\">X</a></div>&nbsp;&nbsp;";
		$('#haoSelectedService').append(item);
		$("#haoServiceFailureMessage").hide();
		$('#haoPickupAddress1').focus();
	}
}

function removeItemFromList(serviceId) {
	var index = selectedServiceList.indexOf("service" + serviceId);

	if (index != -1) {
		selectedServiceList.splice(index, 1);
		$('#haoRemove' + serviceId).remove();
	}

	if (selectedServiceList.length == 0) {
		$('#haoSelectedService').html("Please choose Services.");
		$('#inputFormBox').hide();
	}
}

function validateRequest() {
	pickupAddress1 = $('#haoPickupAddress1').val();
	pickupAddress2 = $('#haoPickupAddress2').val();
	requestPhoneNumber = $('#requestPhoneNumber').val();
	requestDate = $('#haoDate').val();

	var time = $('input[name=time]:checked').val();

	if (time == 1 && (requestDate == null || requestDate == "")) {
		$('#haoPickupTimeWarning').show();
		$('#haoPickupTime').focus();
		return false;
	} else if (time == 0
			|| (time == 1 && requestDate != null && requestDate != "")) {
		$('#haoPickupTimeWarning').hide();

		if (pickupAddress1 == null || pickupAddress1 == "") {
			$('#haoPickupAddressWarning1').show();
			$('#haoPickupAddress1').focus();
			return false;
		} else if (pickupAddress1 != null && pickupAddress1 != "") {
			$('#haoPickupAddressWarning1').hide();
			if (pickupAddress2 == null || pickupAddress2 == "") {
				$('#haoPickupAddressWarning2').show();
				$('#haoPickupAddress2').focus();
				return false;
			} else if (pickupAddress2 != null && pickupAddress2 != "") {
				$('#haoPickupAddressWarning2').hide();
				message = checkPhoneNumber(requestPhoneNumber)
				
				if (requestPhoneNumber == null || requestPhoneNumber == "" || message!="success") {
					$('#requestPhoneNumberWarning').html(message);
					$('#requestPhoneNumberWarning').show();
					$('#requestPhoneNumber').focus();
					return false;
				} else if (pickupAddress2 != null && pickupAddress2 != "" && message=="success") {
					$('#requestPhoneNumberWarning').hide();
				}
			}
		}
	}

	if (pickupAddress1 != null
			&& pickupAddress1 != ""
			&& pickupAddress2 != null
			&& pickupAddress2 != ""
			&& ((time == 1 && requestDate != null && requestDate != "") || time == 0)
			&& selectedServiceList.length < 1) {
		$('#haoServiceFailureMessage').html("Please choose Services.");
		$('#haoServiceFailureMessage').show();
		return false;
	}

	if (pickupAddress1 != null
			&& pickupAddress1 != ""
			&& pickupAddress2 != null
			&& pickupAddress2 != ""
			&& ((time == 1 && requestDate != null && requestDate != "") || time == 0)
			&& selectedServiceList.length > 0) {

		$('#selectedServiceList').val(selectedServiceList);
	}

	return true;
}

function confirmRequest() {
	result = validateRequest();

	if (result) {
		sendRequestConfirmationOTP();
	}
}

$(function() {
	$('#haoDate').datepicker({
		format : 'dd/mm/yyyy',
		sideBySide : true,
		startDate : '0d',
		endDate : '+7d',
		useCurrent : true
	});
});

function sendRequestConfirmationOTP() {

	requestPhoneNumber = $('#requestPhoneNumber').val();

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
						$('#otpBox').show();
						$('#otpRequestMessage').show();
						$('#haoConfirmRequest').hide();
						$('#haoGetOTP').show();
					} else {
						$("#haoConfirmOtpWarning")
								.html(
										"Unable to send OTP at this moment. Please try again later.");
						$("#haoConfirmOtpWarning").show();
						$("#otpBox").hide();
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#haoConfirmOtpWarning")
							.html(
									"Unable to process your request at this moment. Please try again.");
					$("#haoServiceSuccessMessage").hide();// login unsuccessful
				}
			});
}

function confirmRequestConfirmationOTP() {

	requestPhoneNumber = $('#requestPhoneNumber').val();
	requestOTP = $('#requestOTP').val();

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
						$("#haoConfirmOtpWarning").hide();
						if (validateRequest()) {
							placeOrder();
						}
					} else {
						$("#haoConfirmOtpWarning").html(
								"Invalid OTP. Please try again.");
						$("#haoConfirmOtpWarning").show();
						$("#haoServiceSuccessMessage").hide();
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#haoConfirmOtpWarning")
							.html(
									"Unable to process your request at this moment. Please try again.");
					$("#haoServiceSuccessMessage").hide();// login unsuccessful
				}
			});

	return false;
}

function placeOrder() {
	requestPhoneNumber = $('#requestPhoneNumber').val();
	pickupFlatNumber = $('#haoPickupAddress1').val();
	pickupLocation = $('#haoPickupAddress2').val();
	haoDate = $('#haoDate').val();
	haoTimeHours = $('#haoTimeHours').val();
	haoTimeMinutes = $('#haoTimeMinutes').val();
	haoTimeAmPm = $('#haoTimeAmPm').val();
	time = $('input[name=time]:checked').val()
	pickupCity = $('input[name=haoPickUpCity]:checked').val()

	finalSelectedServiceList = $('#selectedServiceList').val();
	serviceCategoryId = "1";

	$
			.ajax({
				url : '/orderWeb/confirmRequest',
				type : 'POST',
				async : false,
				data : {
					pickupPhoneNumber : requestPhoneNumber,
					pickupFlatNumber : pickupFlatNumber,
					pickupLocation : pickupLocation,
					pickupCity : pickupCity,
					date : haoDate,
					timeHours : haoTimeHours,
					timeMinutes : haoTimeMinutes,
					timeAmPm : haoTimeAmPm,
					time : time,
					serviceCategoryId : serviceCategoryId,
					selectedServiceList : finalSelectedServiceList
				},
				success : function(data) {
					if (data == "success") {
						$('#haoLoginForm').hide();
						$("#haoContainer").show("slide", {
							direction : "left"
						}, 1000);
						$('#haoServiceSuccessMessage').show();
						$('#haoServiceSuccessMessage').html(
								"Request Confirmed. We will call you shortly at "
										+ requestPhoneNumber + ".");
						$('#haoServiceFailure').hide();
						$('#haoPickupAddress1').val("");
						$('#haoPickupAddress2').val("");
						$('#haoPickupTime').val("");
						$('#inputFormBox').hide();
						$('#haoPickupAddress1').focus();
						
						clearSelectedServices();
						
						return falsel
						
					} else {
						$('#haoContainer').show();
						$('#haoLoginForm').hide();
						$("#haoServiceFailure").show();
						$('#haoSelectedService')
								.html("Please choose Services.");
						$('#inputFormBox').hide();
						$("#haoServiceFailureMessage")
								.html(
										"Unable to process your request at this moment. Please try again.");
						$("#haoServiceSuccessMessage").hide();// login unsuccessful
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$('#haoContainer').show();
					$('#haoLoginForm').hide();
					$('#haoSelectedService').html("Please choose Services.");
					$('#inputFormBox').hide();
					$("#haoServiceFailureMessage")
							.html(
									"Unable to process your request at this moment. Please try again.");
					$("#haoServiceSuccessMessage").hide();// login unsuccessful
				}
			});
}

function clearSelectedServices() {

	selectedServiceList = $('#selectedServiceList').val();

	serviceCount = selectedServiceList.length;

	for (i = 0; i < serviceCount; i++) {
		var index = selectedServiceList.indexOf(selectedServiceList[0]);

		if (index != -1) {
			var service = selectedServiceList[0].split("service");
			serviceId = service[1];
			selectedServiceList.splice(index, 1);
			$('#haoRemove' + serviceId).remove();
			$('#haoSelectedService').html("Please choose Services.");
		}
	}
}