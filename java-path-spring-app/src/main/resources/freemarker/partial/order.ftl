<div class="row" align="center">
	<div class="col">
		<h4>Customer</h4>
		<form>
			<fieldset disabled>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputEmail4">First Name</label>
						<input class="form-control" value="${order.customer.firstname}">
					</div>
					<div class="form-group col-md-6">
						<label for="inputPassword4">Last Name</label>
						<input class="form-control" value="${order.customer.lastname}">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputEmail4">Email</label>
						<input class="form-control" value="${order.customer.email}">
					</div>
					<div class="form-group col-md-6">
						<label for="inputPassword4">Address</label>
						<input class="form-control" value="${order.customer.address}">
					</div>
				</div>
				<br>
				<h4>Order Details</h4>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Submission Date</label>
						<input class="form-control" value="${order.submitDate}">
					</div>
					<div class="form-group col-md-4">
						<label>Payment Method</label>
						<input class="form-control" value="${order.paymentMethod}">
					</div>
					<div class="form-group col-md-2">
						<label>Total Cost</label>
						<input class="form-control" value="${order.cost}">
					</div>
				</div>
			</fieldset>
		</form>
		<table class="table table-hover">
			<thead>
			<tr class="table-light">
				<th>Product</th>
				<th>Quantity</th>
				<th>Individual Price</th>
			</tr>
			</thead>
			<tbody>
            <#list order.orderItems as item>
				<tr class="table">
					<td>${item.product.name}</td>
					<td align="center">${item.quantity}</td>
                    <#-- "&euro;" = € but for HTML, so that browsers can read it properly -->
					<td align="right">${item.price?string["#.00"]}&euro;</td>
				</tr>
            </#list>
			</tbody>
		</table>
	</div>
</div>
