function checkPassword(phoneNumber, password, confirmPassword) {
	if (password == "") {
		message = "Please enter password.";
		return message;
	}

	if (confirmPassword == "") {
		message = "Please confirm password.";
		return message;
	}

	if (password == confirmPassword) {
		if (password.length < 6) {
			message = "Password must contain at least six characters.";
			return message;
		}
		if (password == phoneNumber) {
			message = "Password must be different from Username.";
			return message;
		}
		re = /[0-9]/;
		if (!re.test(password)) {
			message = "Password must contain at least one number (0-9).";
			return message;
		}
		re = /[a-z]/;
		if (!re.test(password)) {
			message = "Password must contain at least one lowercase letter (a-z).";
			return message;
		}
		re = /[A-Z]/;
		if (!re.test(password)) {
			message = "Password must contain at least one uppercase letter (A-Z).";
			return message;
		}
	} else {
		message = "Password does not match.";
		return message;
	}

	return "success";
}

function checkPhoneNumber(phoneNumber) {

	var phoneno = /^\d{10}$/;

	if (phoneNumber.match(phoneno)) {
		message = "success";
	} else {
		message = "Please enter 10 digit Phone Number.";
	}

	return message;
}