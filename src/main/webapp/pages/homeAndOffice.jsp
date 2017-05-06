<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="portfolio-modal modal fade" id="homeAndOffice" tabindex="-1"
	role="dialog" aria-hidden="true">
	<div class="modal-content">
		<div class="container">
			<div class="row" id="haoContainer">
				<div class="col-lg-10 col-lg-offset-1">
					<div class="modal-body">
						<div class="col-lg-12">
							<div class="col-lg-1" data-dismiss="modal">
								<a href="#"> <span
									class="glyphicon glyphicon-circle-arrow-left"></span>
								</a>
							</div>
							<div class="col-lg-10">
								<h2>Home & Office</h2>
								<hr class="star-primary">
							</div>
						</div>
						<ul class="list-inline item-details">
							<c:forEach items="${serviceList}" var="services">
								<c:if test="${services.serviceCategoryId==1}">
									<li><strong><button
												class="btn btn-default btn-xs"
												id='haoService<c:out
										value="${services.serviceId}" />'
												onClick='selectedService(<c:out
										value="${services.serviceId}" />)'>
												<c:out value="${services.serviceName}" /></strong></li>
								</c:if>
							</c:forEach>
						</ul>
						<div class="panel panel-default">
							<div class="panel-body" id="haoSelectedService"></div>
						</div>
						<div class="col-lg-12">
							<div>
								<p class="text-danger" id="haoServiceFailureMessage"></p>
							</div>
							<div>
								<p class="text-success" id="haoServiceSuccessMessage"></p>
							</div>
						</div>
						<div id="inputFormBox">
							<div class="row">
								<div class='col-xs-12'>
									<ul class="list-inline">
										I need it
										<li><input type="radio" name="time" value="0"
											onClick="hideDateTimePicker()" checked="true"><span
											class="text-warning"><strong>Immediately</strong></span></li>
										<li><input type="radio" name="time" value="1"
											onClick="showDateTimePicker()"><strong>Later</strong></li>
									</ul>
								</div>
							</div>
							<div class="row" id="haoDatetimepicker_start"
								style="display: none;">
								<div class="col-xs-6 floating-label-form-group">
									<input type='text' class="form-control"
										placeholder="Date(dd/mm/yyyy)" id="haoDate" name="haoDate"
										style="cursor: pointer;" />
									<p class="help-block text-danger" style="display: none;"
										id="haoPickupTimeWarning">Please select Date.</p>
								</div>
								<div class="col-xs-6 floating-label-form-group"
									style="padding-top: 10px; padding-bottom: 19px;">
									<select name="haoTimeHours" id="haoTimeHours"
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
									</select>&nbsp;<select name="haoTimeMinutes" id="haoTimeMinutes">
										<option value="00" selected>00</option>
										<option value="15">15</option>
										<option value="30">30</option>
										<option value="45">45</option>
									</select>&nbsp;<select name="haoTimeAmPm" id="haoTimeAmPm">
										<option value="am" selected>am</option>
										<option value="pm">pm</option>
									</select>
								</div>
							</div>
							<div class="row">
								<div
									class="form-group col-xs-12 floating-label-form-group controls">
									<label>Flat/House No</label> <input type="text"
										class="form-control" placeholder="Flat/House No."
										id="haoPickupAddress1">
									<p class="help-block text-danger" style="display: none;"
										id="haoPickupAddressWarning1">Please enter Flat/House No.</p>
								</div>
							</div>
							<div class="row">
								<div
									class="form-group col-xs-12 floating-label-form-group controls">
									<label>Locality/Area Name</label> <input type="text"
										class="form-control" placeholder="Locality/Area Name"
										id="haoPickupAddress2">
									<p class="help-block text-danger" style="display: none;"
										id="haoPickupAddressWarning2">Please enter Locality/Area
										Name.</p>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<ul class="list-inline">
										<li><input type="radio" name="haoPickUpCity"
											value="Gurgaon" checked="true">Gurgaon</radio></li>
										<li><input type="radio" name="haoPickUpCity"
											value="Noida">Noida</radio></li>
										<li><input type="radio" name="haoPickUpCity"
											value="Faridabad">Faridabad</radio></li>

									</ul>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-6 floating-label-form-group"
									style="padding-top: 20px;">
									<input type="text" class="form-control"
										placeholder="Phone Number" id="requestPhoneNumber"
										maxlength="10">
									<p class="help-block text-danger" style="display: none;"
										id="requestPhoneNumberWarning"></p>
								</div>
								<div class="col-xs-1">&nbsp;</div>
								<div class="col-xs-5 floating-label-form-group text-left"
									style="display: none;" id="otpBox">
									<span id="otpRequestMessage">Please enter the OTP
										received on your PhoneNumber.</span> <input type="text"
										class="form-control" placeholder="OTP" id="requestOTP"
										maxlength="4">
									<p class="help-block text-danger" style="display: none;"
										id="haoConfirmOtpWarning"></p>
								</div>
							</div>
							<div class="row">&nbsp;</div>
							<div class="row text-center">
								<button class="btn btn-success btn-default"
									style="display: none;" id="haoGetOTP"
									onclick="confirmRequestConfirmationOTP();">Confirm OTP</button>
							</div>
							<div class="row text-center">
								<button class="btn btn-success btn-default"
									id="haoConfirmRequest" onClick="confirmRequest();">Confirm
									Request</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="selectedServiceList">
		<div class="row" id="haoLoginForm"></div>
	</div>
</div>