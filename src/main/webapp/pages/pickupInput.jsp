<div class="row control-group" id="selectedService"></div>
<div class="row control-group">
	<div class="form-group col-xs-12 floating-label-form-group controls">
		<label>Pick from Flat/Door/Street No</label> <input type="text"
			class="form-control" placeholder="Flat/Door/Street No."
			id="pickupAddress1">
		<p class="help-block text-danger" id="pickupAddressWarning1">Please
			enter Flat/Door/Street No.</p>
	</div>
</div>
<div class="row control-group">
	<div class="form-group col-xs-12 floating-label-form-group controls">
		<label>Area/Location</label> <input type="text" class="form-control"
			placeholder="Area/Location" id="pickupAddress2">
		<p class="help-block text-danger" id="pickupAddressWarning2">Please
			enter Area/Location.</p>
	</div>
</div>
<div class="row control-group">
	<div class="form-group col-xs-12 floating-label-form-group">
		<ul class="list-inline item-details">
			<li><input type="radio" name="pickUpCity" value="Gurgaon"
				checked="true">Gurgaon</radio></li>
			<li><input type="radio" name="pickUpCity" value="Delhi"
				disabled="true">Delhi</radio></li>
			<li><input type="radio" name="pickUpCity" value="Noida"
				disabled="true">Noida</radio></li>
			<li><input type="radio" name="pickUpCity" value="Ghaziabad"
				disabled="true">Ghaziabad</radio></li>
			<li><input type="radio" name="pickUpCity" value="Faridabad"
				disabled="true">Faridabad</radio></li>
		</ul>
	</div>
</div>
<div class="row control-group">
	<div
		class='input-group date col-xs-12 floating-label-form-group controls'
		id='datetimepicker_start'>
		<input type='text' class="form-control" style="padding-left: 15px;"
			placeholder="Pickup time" id="pickupTime" name="pickupTime" /> <span
			class="input-group-addon"> <span
			class="glyphicon glyphicon-calendar"></span>
		</span>
	</div>
	<p class="help-block text-danger" id="pickupTimeWarning">Please
		enter pickup time.</p>
</div>

<p>&nbsp;</p>

<div class="alert alert-danger" id="noServiceWarning">
	<button class="close" aria-hidden="true" data-dismiss="alert"
		type="button">×</button>
	<strong>Please choose Services.</strong>
</div>
<div class="alert alert-success" id="serviceSuccessMsg">
	<button class="close" aria-hidden="true" data-dismiss="alert"
		type="button">×</button>
	<strong>Request Confirmed. We will call you shortly</strong>
</div>
<div class="row control-group">&nbsp;</div>
<button class="btn btn-success btn-default" id="confirmRequest">Confirm
	Request</button>