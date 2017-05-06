<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="portfolio-modal modal fade" id="deliveryServices"
	tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-content">
		<div class="container">
			<div class="row" id="dsContainer">
				<div class="col-lg-10 col-lg-offset-1">
					<div class="modal-body">
						<div class="col-lg-12">
							<div class="col-lg-1" data-dismiss="modal">
								<a href="#"> <span
									class="glyphicon glyphicon-circle-arrow-left"></span>
								</a>
							</div>
							<div class="col-lg-10">
								<h2>Delivery Services</h2>
								<hr class="star-primary">
							</div>
						</div>
						<div class="col-lg-12">
							<div>
								<p class="text-danger" id="dsServiceFailureMessage"></p>
							</div>
							<div>
								<p class="text-success" id="dsServiceSuccessMessage"></p>
							</div>
						</div>
						<div class="row">
							<div class='col-xs-12'>
								<ul class="list-inline">
									I need it
									<li><input type="radio" name="dsTime" value="0"
										onClick="hideDsDateTimePicker()" checked="true"><span
										class="text-warning"><strong>Immediately</strong></span></li>
									<li><input type="radio" name="dsTime" value="1"
										onClick="showDsDateTimePicker()"><strong>Later</strong></li>
								</ul>
							</div>
						</div>
						<div class="row" id="dsDatetimepicker_start"
							style="display: none;">
							<div class="col-xs-6 floating-label-form-group">
								<input type='text' class="form-control"
									placeholder="Date(dd/mm/yyyy)" id="dsDate" name="dsDate"
									style="cursor: pointer;" />
								<p class="help-block text-danger" style="display: none;"
									id="dsPickupTimeWarning">Please select Date.</p>
							</div>
							<div class="col-xs-6 floating-label-form-group"
								style="padding-top: 10px; padding-bottom: 19px;">
								<select name="dsTimeHours" id="dsTimeHours"
									onChange="checkTime()" onkeyup="checkTime()">
									<option value="09" selected>09</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="01">01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04">04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="07">07</option>
									<option value="08">08</option>
								</select>&nbsp;<select name="dsTimeMinutes" id="dsTimeMinutes">
									<option value="00" selected>00</option>
									<option value="15">15</option>
									<option value="30">30</option>
									<option value="45">45</option>
								</select>&nbsp;<select name="dsTimeAmPm" id="dsTimeAmPm">
									<option value="am" selected>am</option>
									<option value="pm">pm</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div
								class="form-group col-xs-12 floating-label-form-group controls">
								<label>Pickup Item</label> <input type="text"
									class="form-control" placeholder="Pickup Item"
									id="dsPickupItem">
								<p class="help-block text-danger" style="display: none;"
									id="dsPickupItemWarning">Please enter Pickup Item.</p>
							</div>
						</div>
						<div class="row">
							<div
								class="form-group col-xs-12 floating-label-form-group controls">
								<label>Pick from Flat/House No.</label> <input type="text"
									class="form-control" placeholder="Pick from Flat/House No."
									id="dsPickupAddress1">
								<p class="help-block text-danger" style="display: none;"
									id="dsPickupAddressWarning1">Please enter pickup
									Locality/Area Name.</p>
							</div>
						</div>
						<div class="row">
							<div
								class="form-group col-xs-12 floating-label-form-group controls">
								<label>Area/Location</label> <input type="text"
									class="form-control" placeholder="Locality/Area Name"
									id="dsPickupAddress2">
								<p class="help-block text-danger" style="display: none;"
									id="dsPickupAddressWarning2">Please enter pickup
									Locality/Area Name.</p>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<ul class="list-inline">
									<li><input type="radio" name="dsPickupCity"
										value="Gurgaon" checked="true">Gurgaon</radio></li>
									<li><input type="radio" name="dsPickupCity" value="Noida">Noida</radio></li>
									<li><input type="radio" name="dsPickupCity"
										value="Faridabad">Faridabad</radio></li>

								</ul>
							</div>
						</div>
						<div class="row">
							<div
								class="form-group col-xs-12 floating-label-form-group controls">
								<label>Drop at Flat/House No.</label> <input type="text"
									class="form-control" placeholder="Drop at Flat/House No."
									id="dsDropAddress1">
								<p class="help-block text-danger" style="display: none;"
									id="dsDropAddressWarning1">Please enter Flat/House No.</p>
							</div>
						</div>
						<div class="row">
							<div
								class="form-group col-xs-12 floating-label-form-group controls">
								<label>Area/Location</label> <input type="text"
									class="form-control" placeholder="Locality/Area Name"
									id="dsDropAddress2">
								<p class="help-block text-danger" style="display: none;"
									id="dsDropAddressWarning2">Please enter drop Locality/Area
									Name.</p>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<ul class="list-inline">
									<li><input type="radio" name="dsDropCity" value="Gurgaon"
										checked="true">Gurgaon</radio></li>
									<li><input type="radio" name="dsDropCity" value="Noida">Noida</radio></li>
									<li><input type="radio" name="dsDropCity"
										value="Faridabad">Faridabad</radio></li>
								</ul>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-6 floating-label-form-group"
								style="padding-top: 20px;">
								<input type="text" class="form-control"
									placeholder="Phone Number" id="dsRequestPhoneNumber"
									maxlength="10">
								<p class="help-block text-danger" style="display: none;"
									id="dsRequestPhoneNumberWarning"></p>
							</div>
							<div class="col-xs-1">&nbsp;</div>
							<div class="col-xs-5 floating-label-form-group text-left"
								style="display: none;" id="dsOtpBox">
								<span id="otpRequestMessage">Please enter the OTP
									received on your PhoneNumber.</span> <input type="text"
									class="form-control" placeholder="OTP" id="dsRequestOTP"
									maxlength="4">
								<p class="help-block text-danger" style="display: none;"
									id="dsConfirmOtpWarning"></p>
							</div>
						</div>
						<div class="row">&nbsp;</div>
						<div class="row text-center">
							<button class="btn btn-success btn-default"
								style="display: none;" id="dsGetOTP"
								onclick="dsConfirmRequestConfirmationOTP();">Confirm
								OTP</button>
						</div>
						<div class="row text-center">
							<button class="btn btn-success btn-default" id="dsConfirmRequest"
								onClick="dsConfirmRequest();">Confirm Request</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>