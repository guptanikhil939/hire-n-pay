$("#profileSuccessMessage").hide();
$("#profileFailureMessage").hide();

function udpateProfile(){
					
					firstName = $('#firstName').val();
					lastName = $('#lastName').val();
					email = $('#email').val();

					$
							.ajax({
								url : '/updateProfile',
								type : 'POST',
								async : false,
								data : {
									firstName : firstName,
									lastName : lastName,
									email : email
								},
								success : function(data) {
									if (data == "success") {
										$("#profileSuccessMessage").show();
										$("#profileSuccessMessage").html(
												"Profile Updated.");
									} else if (data == "failure") {
										$("#profileFailureMessage").show();
										$("#profileFailureMessage")
												.html(
														"Unable to process your request at this moment. Please try again.");
										return false;
									} else {
										$("#profileFailureMessage").show();
										$("#profileFailureMessage")
												.html(
														"Unable to process your request at this moment. Please try again.");
										return false;
									}
								},
								error : function(jqXHR, textStatus, errorThrown) {
									$("#profileFailureMessage").show();
									$("#profileFailureMessage")
											.html(
													"Unable to process your request at this moment. Please try again.");
								}
							});
}