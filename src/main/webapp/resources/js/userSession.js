function checkAndPlaceOrder() {
	$
			.ajax({
				url : '/login/checkUserSession',
				type : 'POST',
				async : false,
				data : {
					phoneNumber : sessionPhoneNumber
				},
				success : function(data) {
					if (data == "success") {
						userLoggedIn();
						placeOrder(sessionPhoneNumber);
					} else {
						$("#haoContainer").hide();
						$("#haoLoginForm").show("slide", {
							direction : "right"
						}, 1000);
						$('#haoLoginForm').load("/login/form");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#haoServiceFailureMessage")
							.html(
									"Unable to process your request at this moment. Please try again.");
					$("#success").hide();// login unsuccessful
				}
			});
}