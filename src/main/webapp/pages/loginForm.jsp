<head>
<script src="resources/js/loginForm.js"></script>
</head>
<section id="loginForm">
	<div class="container">
		<div class="row">
			<div class="col-lg-3" id="backButton">
				<a href="#"> <span class="glyphicon glyphicon-circle-arrow-left"></span>
				</a>
			</div>
			<div class="col-lg-6">
				<h2>Login</h2>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<p class="text-danger" id="loginFormMessage"></p>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3">
				<div>
					<p class="text-danger" id="failureLoginFormMessage"></p>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<form id="loginForm1" method="post" action="/login/success">
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Phone Number</label> <input type="text"
								class="form-control" placeholder="Phone Number"
								id="loginFormPhoneNumber">
							<p class="help-block text-danger hide"
								id="loginFormPhoneNumberWarning">Please enter Phone Number.</p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Password</label> <input type="password"
								class="form-control" placeholder="Password"
								id="loginFormPassword">
							<p class="help-block text-danger hide"
								id="loginFormPasswordWarning">Please enter Password.</p>
						</div>
					</div>
					<p>&nbsp;</p>
					<div>
						<button class="btn btn-success btn-default" id="loginFormButton">Login</button>
						<button class="btn btn-primary btn-default" id="newUserButton">New
							User ?</button>
						<button class="btn btn-warning btn-default">Forgot
							Password</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>