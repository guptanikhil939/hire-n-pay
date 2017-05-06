<section id="login">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Login</h2>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 text-center">
				<form id="loginForm" method="post" action="login/success">
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Phone Number</label> <input type="text"
								class="form-control" placeholder="Phone Number"
								id="loginPhoneNumber" maxlength="10">
							<p class="help-block text-danger" id="loginPhoneNumberWarning"></p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Password</label> <input type="password"
								class="form-control" placeholder="Password" id="loginPassword">
							<p class="help-block text-danger" id="loginPasswordWarning">Please
								enter Password.</p>
						</div>
					</div>
					<p>&nbsp;</p>
					<div>
						<p class="text-danger" id="failureLoginMessage"></p>
						<p class="text-warning" id="warningLoginMessage"></p>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<button class="btn btn-success btn-lg" id="loginButton">Login</button>
							<button class="btn btn-warning btn-lg" id="forgotPasswordButton">Forgot
								Password</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>