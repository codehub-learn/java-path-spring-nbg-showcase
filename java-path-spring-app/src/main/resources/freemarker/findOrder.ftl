<!DOCTYPE html>
<html>
<#import "partial/siteTemplate.ftl" as siteTemplate>
<head>
	<title>Find Your Order</title>
    <@siteTemplate.importantLibraries/>
</head>
<body>
<@siteTemplate.navbar currentPage="findOrder"/>

<br>

<div class="container">
	<div class="row" align="center">
		<div class="col">
			<h1>Find Your Order</h1>
			<form method="get" action="/mvc/findOrder">
				<div class="form-group">
					<label for="orderId" class="">Order ID</label>
					<input type="number" class="" name="orderId" id="orderId" placeholder="Enter ID Here">
                    <#-- If value is not null-->
                    <#if error??>
						<div>
							<small class="text-danger">
								The order you searched for does not exist.
							</small>
						</div>
                    </#if>
				</div>
				<button type="submit" class="btn btn-primary">Search</button>
			</form>
		</div>
	</div>
	<hr>
    <#if order??>
        <#include "partial/order.ftl">
    </#if>
</div>

<#--Simple script to pre-fill order id in the form input cell if the user has searched for one-->
<script>
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const orderId = urlParams.get('orderId')
	if (typeof orderId !== 'undefined') {
		document.getElementById("orderId").value = orderId;
	}
</script>

<@siteTemplate.footer/>
</body>
</html>
