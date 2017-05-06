<section id="contact">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Sign-Up</h2>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 text-center">
				<div class="row control-group">
					<div
						class="form-group col-xs-12 floating-label-form-group controls">
						<label>Phone Number</label> <input type="text" class="form-control"
							placeholder="Phone Number" id="phoneNumber" maxlength="10">
						<p class="help-block text-danger text-center"
							id="phoneNumberWarning"></p>
					</div>
				</div>
				<div class="row control-group">
					<div
						class="form-group col-xs-12 floating-label-form-group controls">
						<label>Password</label> <input type="password"
							class="form-control" placeholder="Password" id="password">
					</div>
				</div>
				<div class="row control-group">
					<div
						class="form-group col-xs-12 floating-label-form-group controls">
						<label>Confirm Password</label> <input type="password"
							class="form-control" placeholder="Confirm Password"
							id="confirmPassword">
					</div>
				</div>
				<div class="row control-group" id="OTPbox">
					<div
						class="form-group col-xs-12 floating-label-form-group controls">
						<label>OTP</label> <input type="text" class="form-control"
							placeholder="OTP" id="otp" maxlength="50">
						<p class="help-block text-danger  text-center" id="otpWarning">Please
							enter OTP.</p>
					</div>
				</div>
				<p>&nbsp;</p>
				<div class="row">
					<p class="help-block text-danger text-center" id="passwordWarning"></p>
				</div>
				<div>
					<p class="text-danger" id="failureSignupMessage"></p>
				</div>
				<div>
					<p class="text-success" id="successSignupMessage"></p>
				</div>
				<div class="row">
					<div class="form-group col-xs-12">
						<button class="btn btn-success btn-lg" id="signUp">Create
							Account</button>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-xs-12">
						<button class="btn btn-success btn-lg" id="confirmOTP">Confirm
							OTP</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>